package sepe.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.*;
import org.springframework.stereotype.Service;
import sepe.config.Constants;
import sepe.config.SpringUserDetails;
import sepe.domain.company.*;
import sepe.domain.general.TNotificationsAccountEntity;
import sepe.domain.technician.SpPtlTechnicianRegrequest;
import sepe.domain.technician.TSafetyTechnician;
import sepe.dto.SpPtlCompTaSannContrDTO;
import sepe.dto.SpPtlCompTaSannDiaryEntryDTO;
import sepe.dto.UserDTO;
import sepe.repository.NotificationsRestRepository;
import sepe.repository.company.*;
import sepe.repository.technician.SafetyTechnicianRepository;
import sepe.repository.technician.SpPtlTechnicianRegrequestRepo;
import sepe.repository.technician.SpPtlVCompTaSannDiaryBRepo;
import sepe.repository.technician.SpPtlVCompTaSannDiaryRepo;
import sepe.service.CompanyService;
import sepe.service.CurrentUserDetailsService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

/**
 * Created by Annita on 5/20/2015.
 */

@Service
@RepositoryEventHandler(SpPtlCompTaSann.class)
public class CompanyTaSAnnEventHandler {

    @Autowired
    SpPtlCompPtlBranchRepo spPtlCompPtlBranchRepo;

    @Autowired
    private TEmployerBranchIKARepo ikaRepo;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private SpPtlCompTaSannRepo spPtlCompTaSannRepo;

    @Autowired
    private SpPtlVCompTaSannDiaryRepo spPtlVCompTaSannDiaryRepo;

    @Autowired
    private SpPtlVCompTaSannDiaryBRepo spPtlVCompTaSannDiaryBRepo;

    @Autowired
    private SpPtlCompTaSannDiaryEntrRepo spPtlCompTaSannDiaryEntryRepo;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    SpPtlCompanyUserPrivilagesRepository companyUserPrivilagesRepository;

    @Autowired
    SafetyTechnicianRepository safetyTechnicianRepository;

    @Autowired
    NotificationsRestRepository notificationsRestRepository;

    @Autowired
    SpPtlTechnicianRegrequestRepo spPtlTechnicianRegrequestRepo;

    @Autowired
    SnRgVwTaRegNezRepo snRgVwTaRegNezRepo;

    @Autowired
    private CompanyService companyService;

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyTaSAnnEventHandler.class);

    @HandleBeforeSave
    public void handleBeforeSave(SpPtlCompTaSann spPtlCompTaSann) throws SepeRestException {
        LOGGER.debug("Handling SpPtlCompTaSann for: Company ID " + CurrentUserDetailsService.getCurrentUser().getUsername());

        SpringUserDetails currentUser = CurrentUserDetailsService.getCurrentUser();
        UserDTO userDTO = CurrentUserDetailsService.getCurrentUserByPrincipal();
        if (userDTO.getRole() == Constants.USER_TYPE.COMPANY_USER.getCode()) {
            TCompanyUserPrivilages priv = companyUserPrivilagesRepository.findByUser(userDTO.getId());
            ArrayList<Integer> list = new ArrayList(Arrays.asList(priv.getPrivilages().split("\\s*,\\s*")));
            if(null != priv.getPrivilages() && list.contains(String.valueOf(Constants.COMPANY_SERVICE.ASSIGN_NOTIFICATION.getCode()))) {
                Long companyId = priv.getCompId();
                spPtlCompTaSann.setCompanyId(companyId);
            }
            else {
                throw new SepeRestException("Ο χρήστης δεν έχει εξουσιοδότηση σε αυτό το είδος αίτησης.");
            }
        }
        else {
            spPtlCompTaSann.setCompanyId(currentUser.getUserId());
        }

        SnRgVwTaRegNez technician = snRgVwTaRegNezRepo.findByAfm(spPtlCompTaSann.getTaAfm());
        if (technician == null) {
            throw new SepeRestException("Ο τεχνικός με το συγκεκριμένο ΑΦΜ δεν είναι καταχωρημένος στο μητρώο.");
        }

        /*List<SpPtlTechnicianRegrequest> technicianRequests = spPtlTechnicianRegrequestRepo.findByAfm(spPtlCompTaSann.getTaAfm());
        if (technicianRequests.size() == 0) {
            throw new SepeRestException("Ο τεχνικός με το συγκεκριμένο ΑΦΜ δεν είναι καταχωρημένος στο μητρώο.");
        }

        SpPtlTechnicianRegrequest technician = technicianRequests.get(0);*/

        //spPtlCompTaSann.setTechnicianRegrequestUserId(0L);  06/03/2018


        String date2 = spPtlCompTaSann.getDateStart().toString().split(" ")[0];
        String dateIn=date2.split("-")[2]+"/"+date2.split("-")[1]+"/"+date2.split("-")[0];
        Long countActiveAnns = spPtlCompTaSannRepo.countTaSann(spPtlCompTaSann.getCompShipId(), dateIn);
        if(countActiveAnns > 0) {
            throw new SepeRestException("Οι ημερομηνίες που επιλέξατε επικαλύπτονται με ημερομηνίες μιας ήδη ενεργής αναγγελίας.");
        }

        Map<String, Object> input = new LinkedHashMap<String, Object>();
        input.put("DATE_FROM", (spPtlCompTaSann.getDateStart()));
        input.put("DATE_TO", (spPtlCompTaSann.getDateEnd()));
        input.put("TA_SHIP_ID", spPtlCompTaSann.getTechnicianRegrequestUserId());
        input.put("SHIP_ID", spPtlCompTaSann.getCompShipId());
        input.put("PORT_AUTHORITY_ID", spPtlCompTaSann.getPortAuthority());
        input.put("SHIPYARD_ID", spPtlCompTaSann.getShipyard());
        try {
            Integer result = (companyService).funcIntValidateTaShipAnncmnt(input);
            if (result < 0) {
                throw new SepeRestException("Δεν γίνεται να δηλωθεί ο συγκεκριμένος τεχνικός ασφαλείας.");
            }
        } catch (Exception e) {
            throw new SepeRestException("Δεν γίνεται να δηλωθεί ο συγκεκριμένος τεχνικός ασφαλείας.");
        }

        //PERIPTWSI ANTIKATASTASIS
        if (null != spPtlCompTaSann.getCompTaSannPrevId()) {

            //Company should select another technician to replace the old one
            SpPtlCompTaSann previousAnn = spPtlCompTaSannRepo.findOne(spPtlCompTaSann.getCompTaSannPrevId());
            if (previousAnn.getTechnicianRegrequestUserId().equals(spPtlCompTaSann.getTechnicianRegrequestUserId())) {
                throw new SepeRestException("Πρέπει να επιλέξετε έναν άλλο τεχνικό εργασίας για αυτές τις ημερομηνίες.");
            }

            Date todayDate = new Date();
            Timestamp today = new Timestamp(todayDate.getTime());
            previousAnn.setDateEnd(today);
            previousAnn.setTaSannStatus(Constants.COMP_IE_TA_ANN_STATUS.EXPIRED.getCode());
            previousAnn.setReqStatus(Constants.REQUEST_STATUS.REJECTED.getCode());
            spPtlCompTaSannRepo.save(previousAnn);
        }

        try {
            SpPtlCompTaSannDiaryEntryDTO[] spPtlCompTaSannDiaryEntry = spPtlCompTaSann.getDiaryEntries();
            if (null != spPtlCompTaSannDiaryEntry) {
                boolean datesConficts = false;
                String conflicts = "Οι ημερομηνίες επικαλύπτονται.";
                try {
                    for (int i = 0; i < spPtlCompTaSannDiaryEntry.length; i++) {
                        Date date = new Date(spPtlCompTaSannDiaryEntry[i].getVisitDate().getTime());
                        Long lVisitDate = date.getTime() / 1000;
                        Long lVisitTime = Time.valueOf(spPtlCompTaSannDiaryEntry[i].getVisitTime() + ":00").getTime() / 1000;

                        Long dateAndTime = lVisitDate + lVisitTime - 3600; //remove 1 hour
                        List<Long> ieVisitConflicts = spPtlVCompTaSannDiaryBRepo.taVisitConflict(spPtlCompTaSann.getTechnicianRegrequestUserId(), dateAndTime, new Long(spPtlCompTaSannDiaryEntry[i].getVisitDurationMinutes()));

                        if (ieVisitConflicts.size() > 0) {
                            Long ieVisitConflict = ieVisitConflicts.get(0);
                            if (null != ieVisitConflict && ieVisitConflict > 0) {
                                datesConficts = true;
                            }
                        }
                    }
                } catch (Exception ex) {
                    throw new SepeRestException("Δεν έγινε σωστή αποθήκευση των επισκέψεων. Προσπαθήστε ξανά.");
                }


                if (datesConficts == true) {
                    throw new SepeRestException(conflicts);
                } else {
                    HashSet<SpPtlCompTaSannDiaryEntr> compTaSannDiaryEntry = new HashSet<SpPtlCompTaSannDiaryEntr>();
                    for (int i = 0; i < spPtlCompTaSannDiaryEntry.length; i++) {
                        SpPtlCompTaSannDiaryEntr ieAnnDiaryEntry = new SpPtlCompTaSannDiaryEntr();
                        ieAnnDiaryEntry.setCompanyId(spPtlCompTaSann.getCompanyId());
                        ieAnnDiaryEntry.setCompTaSann(spPtlCompTaSann);
                        ieAnnDiaryEntry.setVisitTime(spPtlCompTaSannDiaryEntry[i].getVisitTime());
                        ieAnnDiaryEntry.setVisitDurationMinutes(spPtlCompTaSannDiaryEntry[i].getVisitDurationMinutes());
                        ieAnnDiaryEntry.setVisitDate(spPtlCompTaSannDiaryEntry[i].getVisitDate());
                        compTaSannDiaryEntry.add(ieAnnDiaryEntry);
                    }
                    spPtlCompTaSann.setCompTaSannDiaryEntries(compTaSannDiaryEntry);
                }
            }
            spPtlCompTaSann.setCompTaSannContrs(addTaSannContractors(spPtlCompTaSann));
        }
        catch (SepeRestException ex){
            spPtlCompTaSannRepo.delete(spPtlCompTaSann);
            throw new SepeRestException(ex.getMessage());
        }
        completeCompanyFields(spPtlCompTaSann);
    }

    @HandleAfterSave
    public void handleAfterSave(SpPtlCompTaSann spPtlCompTaSann) throws Exception {
        LOGGER.debug("Handling SpPtlCompTaSann for: User ID " + CurrentUserDetailsService.getCurrentUser().getUsername());

        if(spPtlCompTaSann.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode()) {
            SpPtlCompTaSann previousAnn = spPtlCompTaSannRepo.findOne(spPtlCompTaSann.getCompTaSannPrevId());
            if (null != previousAnn) {

                //Send notification to previous active technician
                TNotificationsAccountEntity notification = new TNotificationsAccountEntity();
                notification.setDocId(new Long(-1));
                notification.setTitle("Παύση εργασίας");
                notification.setMessage("Έγινε παύση εργασίας σας από την εταιρεία " + previousAnn.getCompFullName() + ". Για περισσότερες λεπτομέρειες πλοηγηθείτε στο μενού αριστερά με την ένδειξη 'Εκκρεμούσες αναγγελίες'.");
                notification.setSender(previousAnn.getCompFullName());
                notification.setPriority(new Long(1));
                notification.setViewed(new Long(0));

                Date todayDate = new Date();
                Timestamp today = new Timestamp(todayDate.getTime());
                notification.setDateTime(today);

                TSafetyTechnician safetyPreviousTechnician = safetyTechnicianRepository.findByAfmEquals(previousAnn.getTaAfm());
                notification.setAccountId(safetyPreviousTechnician.getUserId());

                notificationsRestRepository.save(notification);
            }

            //Send notification to new active IE account
            TNotificationsAccountEntity notification = new TNotificationsAccountEntity();
            notification.setDocId(new Long(-1));
            notification.setTitle("Νέα αναγγελία");
            notification.setMessage("Έχει προστεθεί μια νέα αναγγελία στο όνομά σας. Για περισσότερες λεπτομέρειες πλοηγηθείτε στο μενού αριστερά με την ένδειξη 'Εκκρεμούσες αναγγελίες' για να την αποδεχτείτε ή να την απορρίψετε.");
            notification.setSender(spPtlCompTaSann.getCompFullName());
            notification.setPriority(new Long(1));
            notification.setViewed(new Long(0));

            Date todayDate = new Date();
            Timestamp today = new Timestamp(todayDate.getTime());
            notification.setDateTime(today);

            TSafetyTechnician safetyTechnician = safetyTechnicianRepository.findByAfmEquals(spPtlCompTaSann.getTaAfm());
            notification.setAccountId(safetyTechnician.getUserId());

            notificationsRestRepository.save(notification);
        }
    }

    @HandleBeforeCreate
    public void handleBeforeCreates(SpPtlCompTaSann spPtlCompTaSann) throws SepeRestException {
        LOGGER.debug("Handling SpPtlCompTaSann for: Company ID " + CurrentUserDetailsService.getCurrentUser().getUsername());

        SpringUserDetails currentUser = CurrentUserDetailsService.getCurrentUser();
        UserDTO userDTO = CurrentUserDetailsService.getCurrentUserByPrincipal();
        if (userDTO.getRole() == Constants.USER_TYPE.COMPANY_USER.getCode()) {
            TCompanyUserPrivilages priv = companyUserPrivilagesRepository.findByUser(userDTO.getId());
            ArrayList<Integer> list = new ArrayList(Arrays.asList(priv.getPrivilages().split("\\s*,\\s*")));
            if (null != priv.getPrivilages() && list.contains(String.valueOf(Constants.COMPANY_SERVICE.ASSIGN_NOTIFICATION.getCode()))) {
                Long companyId = priv.getCompId();
                spPtlCompTaSann.setCompanyId(companyId);
            } else {
                throw new SepeRestException("Ο χρήστης δεν έχει εξουσιοδότηση σε αυτό το είδος αίτησης.");
            }
        } else {
            spPtlCompTaSann.setCompanyId(currentUser.getUserId());
        }

        SnRgVwTaRegNez technician = snRgVwTaRegNezRepo.findByAfm(spPtlCompTaSann.getTaAfm());
        if (technician == null) {
            throw new SepeRestException("Ο τεχνικός με το συγκεκριμένο ΑΦΜ δεν είναι καταχωρημένος στο μητρώο.");
        }

        /*List<SpPtlTechnicianRegrequest> technicianRequests = spPtlTechnicianRegrequestRepo.findByAfm(spPtlCompTaSann.getTaAfm());
        if (technicianRequests.size() == 0) {
            throw new SepeRestException("Ο τεχνικός με το συγκεκριμένο ΑΦΜ δεν είναι καταχωρημένος στο μητρώο.");
        }

        SpPtlTechnicianRegrequest technician = technicianRequests.get(0);*/

        //spPtlCompTaSann.setTechnicianRegrequestUserId(0L);

        String date2 = spPtlCompTaSann.getDateStart().toString().split(" ")[0];
        String dateIn=date2.split("-")[2]+"/"+date2.split("-")[1]+"/"+date2.split("-")[0];
        Long countActiveAnns = spPtlCompTaSannRepo.countTaSann(spPtlCompTaSann.getCompShipId(), dateIn);
        if(null != spPtlCompTaSann.getCompTaSannPrevId() && countActiveAnns > 0) {
            throw new SepeRestException("Οι ημερομηνίες που επιλέξατε επικαλύπτονται με ημερομηνίες μιας ήδη ενεργής αναγγελίας.");
        }

        Map<String, Object> input = new LinkedHashMap<String, Object>();
        input.put("DATE_FROM", (new java.sql.Date(spPtlCompTaSann.getDateStart().getTime())));
        input.put("DATE_TO", (new java.sql.Date(spPtlCompTaSann.getDateEnd().getTime())));
        input.put("TA_SHIP_ID", spPtlCompTaSann.getTechnicianRegrequestUserId());
        input.put("SHIP_ID", spPtlCompTaSann.getCompShipId());
        input.put("PORT_AUTHORITY_ID", spPtlCompTaSann.getPortAuthority());
        input.put("SHIPYARD_ID", spPtlCompTaSann.getShipyard());
        try {
            Integer result = (companyService).funcIntValidateTaShipAnncmnt(input);
            if (result < 0) {
                throw new SepeRestException("Η αναγγελία του Τεχνικού Ασφάλειας δεν έγινε αποδεκτή, καθώς αυτός υπερβαίνει τον μέγιστο επιτρεπόμενο αριθμό πλοίων που μπορεί να αναλάβει.");
            }
        } catch (Exception e) {
            throw new SepeRestException("Η αναγγελία του Τεχνικού Ασφάλειας δεν έγινε αποδεκτή, καθώς αυτός υπερβαίνει τον μέγιστο επιτρεπόμενο αριθμό πλοίων που μπορεί να αναλάβει.");
        }

        //PERIPTWSI ANTIKATASTASIS
        if (null != spPtlCompTaSann.getCompTaSannPrevId()) {

            //Company should select another technician to replace the old one
            SpPtlCompTaSann previousAnn = spPtlCompTaSannRepo.findOne(spPtlCompTaSann.getCompTaSannPrevId());
            if (previousAnn.getTechnicianRegrequestUserId().equals(spPtlCompTaSann.getTechnicianRegrequestUserId()) &&
                    previousAnn.getTaSannStatus() != Constants.COMP_IE_TA_ANN_STATUS.EXPIRED.getCode()) {
                throw new SepeRestException("Πρέπει να επιλέξετε έναν άλλο τεχνικό εργασίας για αυτές τις ημερομηνίες.");
            }

            Date todayDate = new Date();
            Timestamp today = new Timestamp(todayDate.getTime());
            previousAnn.setDateEnd(today);
            previousAnn.setTaSannStatus(Constants.COMP_IE_TA_ANN_STATUS.EXPIRED.getCode());
            previousAnn.setReqStatus(Constants.REQUEST_STATUS.REJECTED.getCode());
            spPtlCompTaSannRepo.save(previousAnn);
        }

        try {
            SpPtlCompTaSannDiaryEntryDTO[] spPtlCompTaSannDiaryEntry = spPtlCompTaSann.getDiaryEntries();
            if (null != spPtlCompTaSannDiaryEntry) {
                boolean datesConficts = false;
                String conflicts = "Οι ημερομηνίες επικαλύπτονται.";
                try {
                    for (int i = 0; i < spPtlCompTaSannDiaryEntry.length; i++) {
                        Date date = new Date(spPtlCompTaSannDiaryEntry[i].getVisitDate().getTime());
                        Long lVisitDate = date.getTime() / 1000;
                        Long lVisitTime = Time.valueOf(spPtlCompTaSannDiaryEntry[i].getVisitTime() + ":00").getTime() / 1000;

                        Long dateAndTime = lVisitDate + lVisitTime - 3600; //remove 1 hour
                        List<Long> ieVisitConflicts = spPtlVCompTaSannDiaryBRepo.taVisitConflict(spPtlCompTaSann.getTechnicianRegrequestUserId(), dateAndTime, new Long(spPtlCompTaSannDiaryEntry[i].getVisitDurationMinutes()));

                        if (ieVisitConflicts.size() > 0) {
                            Long ieVisitConflict = ieVisitConflicts.get(0);
                            if (null != ieVisitConflict && ieVisitConflict > 0) {
                                datesConficts = true;
                            }
                        }
                    }
                } catch (Exception ex) {
                    throw new SepeRestException("Δεν έγινε σωστή αποθήκευση των επισκέψεων. Προσπαθήστε ξανά.");
                }


                if (datesConficts == true) {
                    throw new SepeRestException(conflicts);
                } else {
                    HashSet<SpPtlCompTaSannDiaryEntr> compTaSannDiaryEntry = new HashSet<SpPtlCompTaSannDiaryEntr>();
                    for (int i = 0; i < spPtlCompTaSannDiaryEntry.length; i++) {
                        SpPtlCompTaSannDiaryEntr ieAnnDiaryEntry = new SpPtlCompTaSannDiaryEntr();
                        ieAnnDiaryEntry.setCompanyId(spPtlCompTaSann.getCompanyId());
                        ieAnnDiaryEntry.setCompTaSann(spPtlCompTaSann);
                        ieAnnDiaryEntry.setVisitTime(spPtlCompTaSannDiaryEntry[i].getVisitTime());
                        ieAnnDiaryEntry.setVisitDurationMinutes(spPtlCompTaSannDiaryEntry[i].getVisitDurationMinutes());
                        ieAnnDiaryEntry.setVisitDate(spPtlCompTaSannDiaryEntry[i].getVisitDate());
                        compTaSannDiaryEntry.add(ieAnnDiaryEntry);
                    }
                    spPtlCompTaSann.setCompTaSannDiaryEntries(compTaSannDiaryEntry);
                }
            }
            spPtlCompTaSann.setCompTaSannContrs(addTaSannContractors(spPtlCompTaSann));
        }
        catch (SepeRestException ex){
            spPtlCompTaSannRepo.delete(spPtlCompTaSann);
            throw new SepeRestException(ex.getMessage());
        }
        completeCompanyFields(spPtlCompTaSann);
    }

    @HandleAfterCreate
    public void handleAfterCreates(SpPtlCompTaSann spPtlCompTaSann) throws Exception {
        LOGGER.debug("Handling SpPtlCompTaSann for: User ID " + CurrentUserDetailsService.getCurrentUser().getUsername());

        if(spPtlCompTaSann.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode()) {
            SpPtlCompTaSann previousAnn = spPtlCompTaSannRepo.findOne(spPtlCompTaSann.getCompTaSannPrevId());
            if (null != previousAnn) {

                //Send notification to previous active technician
                TNotificationsAccountEntity notification = new TNotificationsAccountEntity();
                notification.setDocId(new Long(-1));
                notification.setTitle("Παύση εργασίας");
                notification.setMessage("Έγινε παύση εργασίας σας από την εταιρεία " + previousAnn.getCompFullName() + ". Για περισσότερες λεπτομέρειες πλοηγηθείτε στο μενού αριστερά με την ένδειξη 'Εκκρεμούσες αναγγελίες'.");
                notification.setSender(previousAnn.getCompFullName());
                notification.setPriority(new Long(1));
                notification.setViewed(new Long(0));

                Date todayDate = new Date();
                Timestamp today = new Timestamp(todayDate.getTime());
                notification.setDateTime(today);

                TSafetyTechnician safetyPreviousTechnician = safetyTechnicianRepository.findByAfmEquals(previousAnn.getTaAfm());
                notification.setAccountId(safetyPreviousTechnician.getUserId());

                notificationsRestRepository.save(notification);
            }

            //Send notification to new active IE account
            TNotificationsAccountEntity notification = new TNotificationsAccountEntity();
            notification.setDocId(new Long(-1));
            notification.setTitle("Νέα αναγγελία");
            notification.setMessage("Έχει προστεθεί μια νέα αναγγελία στο όνομά σας. Για περισσότερες λεπτομέρειες πλοηγηθείτε στο μενού αριστερά με την ένδειξη 'Εκκρεμούσες αναγγελίες' για να την αποδεχτείτε ή να την απορρίψετε.");
            notification.setSender(spPtlCompTaSann.getCompFullName());
            notification.setPriority(new Long(1));
            notification.setViewed(new Long(0));

            Date todayDate = new Date();
            Timestamp today = new Timestamp(todayDate.getTime());
            notification.setDateTime(today);

            TSafetyTechnician safetyTechnician = safetyTechnicianRepository.findByAfmEquals(spPtlCompTaSann.getTaAfm());
            notification.setAccountId(safetyTechnician.getUserId());

            notificationsRestRepository.save(notification);
        }
    }

    public HashSet<SpPtlCompTaSannContr> addTaSannContractors(SpPtlCompTaSann spPtlCompTaSann) {
        SpPtlCompTaSannContrDTO[] spPtlCompTaSannContr = spPtlCompTaSann.getProjscontrs();
        if(null != spPtlCompTaSannContr) {
            HashSet<SpPtlCompTaSannContr> compTaSannContrs = new HashSet<SpPtlCompTaSannContr>();
            for (int i = 0; i < spPtlCompTaSannContr.length; i++) {
                SpPtlCompTaSannContr projAnnContr = new SpPtlCompTaSannContr();
                projAnnContr.setCompanyId(spPtlCompTaSann.getCompanyId());
                projAnnContr.setCompTaSann_contr(spPtlCompTaSann);
                projAnnContr.setFirstname(spPtlCompTaSannContr[i].getFirstname());
                projAnnContr.setLastname(spPtlCompTaSannContr[i].getLastname());
                projAnnContr.setAddr(spPtlCompTaSannContr[i].getAddr());
                projAnnContr.setAfm(spPtlCompTaSannContr[i].getAfm());
                projAnnContr.setType(spPtlCompTaSannContr[i].getType());

                projAnnContr.setContractorSpecialty(spPtlCompTaSannContr[i].getContractorSpecialty());
                projAnnContr.setBirthplace(spPtlCompTaSannContr[i].getBirthplace());
                projAnnContr.setBirthdate(spPtlCompTaSannContr[i].getBirthdate());
                projAnnContr.setCardNumber(spPtlCompTaSannContr[i].getCardNumber());
                projAnnContr.setCardType(spPtlCompTaSannContr[i].getCardType());
                projAnnContr.setCardIssuingAuth(spPtlCompTaSannContr[i].getCardIssuingAuth());
                compTaSannContrs.add(projAnnContr);
            }
            return compTaSannContrs;
        }
        return null;
    }

    public void completeCompanyFields(SpPtlCompTaSann spPtlCompTaSann) {
        TCompany company = companyRepository.findOne(spPtlCompTaSann.getCompanyId());

        //get company missing fields from MainBranchIKA
        //TEmployerBranchIKA ika = ikaRepo.findCompanyMainBranchByAme(company.getAme());
        TEmployerBranchIKA ika = ikaRepo.findCompanyMainBranchByAfmAndAme(company.getAfm(),company.getAme());

        spPtlCompTaSann.setCompAme(company.getAme());

        if (Constants.StringIsNullOrEmpty(spPtlCompTaSann.getCompTaxNumber())) {
            if (Constants.StringIsNullOrEmpty(ika.getRgEbrTaxationNumber()))
                spPtlCompTaSann.setCompTaxNumber("");
            else
                spPtlCompTaSann.setCompTaxNumber(ika.getRgEbrTaxationNumber());
        }

        if (Constants.StringIsNullOrEmpty(spPtlCompTaSann.getCompFullName())) {
            if (Constants.StringIsNullOrEmpty(ika.getRgEmpFullname()))
                spPtlCompTaSann.setCompFullName("");
            else
                spPtlCompTaSann.setCompFullName(ika.getRgEmpFullname());
        }

        if (Constants.StringIsNullOrEmpty(spPtlCompTaSann.getCompPhone())) {
            if (Constants.StringIsNullOrEmpty(ika.getRgEbrPhoneNumber()))
                spPtlCompTaSann.setCompPhone("");
            else {
                if (ika.getRgEbrPhoneNumber().contains("-"))
                    ika.setRgEbrPhoneNumber(ika.getRgEbrPhoneNumber().replace("-", ""));

                spPtlCompTaSann.setCompPhone(ika.getRgEbrPhoneNumber());
            }
        }

        if (Constants.StringIsNullOrEmpty(spPtlCompTaSann.getCompAddr())) {
            if (Constants.StringIsNullOrEmpty(ika.getRgEbrAddressStreet()))
                spPtlCompTaSann.setCompAddr("");
            else
                spPtlCompTaSann.setCompAddr(ika.getRgEbrAddressStreet());
        }

        if (Constants.StringIsNullOrEmpty(spPtlCompTaSann.getCompAddrTk())) {
            if (Constants.StringIsNullOrEmpty(ika.getRgEbrZipCode()))
                spPtlCompTaSann.setCompAddrTk("");
            else
                spPtlCompTaSann.setCompAddrTk(ika.getRgEbrZipCode());
        }

        if (Constants.StringIsNullOrEmpty(spPtlCompTaSann.getCompAddrP())) {
            spPtlCompTaSann.setCompAddrP("");
        }
        if (Constants.StringIsNullOrEmpty(spPtlCompTaSann.getCompAddrPe())) {
            spPtlCompTaSann.setCompAddrPe("");
        }
        if (Constants.StringIsNullOrEmpty(spPtlCompTaSann.getCompAddrD())) {
            spPtlCompTaSann.setCompAddrD("");
        }
        if (Constants.StringIsNullOrEmpty(spPtlCompTaSann.getCompAddrK())) {
            if (Constants.StringIsNullOrEmpty(ika.getRgEbrKallikratis()))
                spPtlCompTaSann.setCompAddrK("");
            else
                spPtlCompTaSann.setCompAddrK(ika.getRgEbrKallikratis());
        }
        if(Constants.LongIsNullOrEmpty(spPtlCompTaSann.getCompEbrBranch0Id()))
        {
            spPtlCompTaSann.setCompEbrBranchId(new Long(-1));
        }

        if(Constants.LongIsNullOrEmpty(spPtlCompTaSann.getCompEbrBranchId()))
        {
            spPtlCompTaSann.setCompEbrBranchId(new Long(-1));
        }

        entityManager.detach(ika);
    }
}