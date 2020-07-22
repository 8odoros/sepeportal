package sepe.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.*;
import org.springframework.stereotype.Service;
import sepe.config.Constants;
import sepe.config.SpringUserDetails;
import sepe.domain.company.*;
import sepe.domain.doctor.SpPtlDoctorRegrequest;
import sepe.domain.doctor.TWorkplaceDoctor;
import sepe.domain.general.TNotificationsAccountEntity;
import sepe.dto.SpPtlCompIeAnnDiaryEntryDTO;
import sepe.dto.SpPtlCompIeAnnIeEntryDTO;
import sepe.dto.UserDTO;
import sepe.repository.NotificationsRestRepository;
import sepe.repository.company.*;
import sepe.repository.doctor.SpPtlDoctorRegrequestRepo;
import sepe.repository.doctor.SpPtlVCompIeAnnDiaryRepo;
import sepe.repository.doctor.WorkplaceDoctorRepository;
import sepe.service.CurrentUserDetailsService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by Annita on 5/20/2015.
 */

@Service
@RepositoryEventHandler(SpPtlCompIeAnn.class)
public class CompanyIEAnnounEventHandler {

    @Autowired
    SpPtlCompPtlBranchRepo spPtlCompPtlBranchRepo;

    @Autowired
    private TEmployerBranchIKARepo ikaRepo;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private SpPtlCompIeAnnDiaryEntryRepo spPtlCompIeAnnDiaryEntryRepo;

    @Autowired
    private SpPtlCompIeAnnRepo spPtlCompIeAnnRepo;

    @Autowired
    private SpPtlVCompIeAnnDiaryRepo spPtlVCompIeAnnDiaryRepo;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    SpPtlCompanyUserPrivilagesRepository companyUserPrivilagesRepository;

    @Autowired
    WorkplaceDoctorRepository workplaceDoctorRepository;

    @Autowired
    NotificationsRestRepository notificationsRestRepository;

    @Autowired
    SpPtlDoctorRegrequestRepo spPtlDoctorRegrequestRepo;

    @Autowired
    SpPtlVRgVwExyppRepo spPtlVRgVwExyppRepo;

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyIEAnnounEventHandler.class);

    @HandleBeforeSave
    public void handleBeforeSave(SpPtlCompIeAnn spPtlCompIeAnn) throws SepeRestException {
        LOGGER.debug("Handling SpPtlCompIeAnn for: Company ID " + CurrentUserDetailsService.getCurrentUser().getUsername());

        SpringUserDetails currentUser = CurrentUserDetailsService.getCurrentUser();
        UserDTO userDTO = CurrentUserDetailsService.getCurrentUserByPrincipal();
        if (userDTO.getRole() == Constants.USER_TYPE.COMPANY_USER.getCode()) {
            TCompanyUserPrivilages priv = companyUserPrivilagesRepository.findByUser(userDTO.getId());
            ArrayList<Integer> list = new ArrayList(Arrays.asList(priv.getPrivilages().split("\\s*,\\s*")));
            if(null != priv.getPrivilages() && list.contains(String.valueOf(Constants.COMPANY_SERVICE.ASSIGN_NOTIFICATION.getCode()))) {
                Long companyId = priv.getCompId();
                spPtlCompIeAnn.setCompanyId(companyId);
            }
            else {
                throw new SepeRestException("Ο χρήστης δεν έχει εξουσιοδότηση σε αυτό το είδος αίτησης.");
            }
        }
        else {
            spPtlCompIeAnn.setCompanyId(currentUser.getUserId());
        }


        /*List<SpPtlDoctorRegrequest> doctorRequests = spPtlDoctorRegrequestRepo.findByAfm(spPtlCompIeAnn.getIeAfm());
        if (doctorRequests.size() == 0) {
            throw new SepeRestException("Ο ιατρός με το συγκεκριμένο ΑΦΜ δεν είναι καταχωρημένος στο μητρώο.");
        }

        SpPtlDoctorRegrequest doctor = doctorRequests.get(0);

        spPtlCompIeAnn.setDoctorRegrequestUserId(doctor.getUserId());*/


        String date2 = spPtlCompIeAnn.getDateStart().toString().split(" ")[0];
        String dateIn=date2.split("-")[2]+"/"+date2.split("-")[1]+"/"+date2.split("-")[0];
        Long countActiveAnns = spPtlCompIeAnnRepo.countIeAnn(spPtlCompIeAnn.getCompPtlBranchId(), dateIn);
        if(countActiveAnns > 0) {
            throw new SepeRestException("Οι ημερομηνίες που επιλέξατε επικαλύπτονται με ημερομηνίες μιας ήδη ενεργής αναγγελίας.");
        }

        //PERIPTWSI ANTIKATASTASIS
        if (null != spPtlCompIeAnn.getCompIeAnnPrevId()) {

            //Company should select another doctor to replace the old one
            SpPtlCompIeAnn previousAnn = spPtlCompIeAnnRepo.findOne(spPtlCompIeAnn.getCompIeAnnPrevId());
            if (previousAnn.getDoctorRegrequestUserId().equals(spPtlCompIeAnn.getDoctorRegrequestUserId())) {
                throw new SepeRestException("Πρέπει να επιλέξετε έναν άλλο ιατρό εργασίας για αυτές τις ημερομηνίες.");
            }

            Date todayDate = new Date();
            Timestamp today = new Timestamp(todayDate.getTime());
            previousAnn.setDateEnd(today);
            previousAnn.setIeAnnStatus(Constants.COMP_IE_TA_ANN_STATUS.EXPIRED.getCode());
            previousAnn.setReqStatus(Constants.REQUEST_STATUS.REJECTED.getCode());
            spPtlCompIeAnnRepo.save(previousAnn);
        }

        try {
            SpPtlCompIeAnnIeEntryDTO[] spPtlCompIeAnnIeEntry = spPtlCompIeAnn.getIeEntries();
            if (null != spPtlCompIeAnnIeEntry) {
                boolean ieConficts = false;
                String conflicts = "Οι τεχνικοί ασφαλείας που επαναλαμβάνονται: ";
                try {
                    for (int i = 0; i < spPtlCompIeAnnIeEntry.length; i++) {
                        for (int j = i+1; j < spPtlCompIeAnnIeEntry.length; j++) {
                            if(spPtlCompIeAnnIeEntry[i].getDoctorRegrequestUserId().equals(spPtlCompIeAnnIeEntry[j].getDoctorRegrequestUserId()) && spPtlCompIeAnnIeEntry[i].getCooperationType().equals(spPtlCompIeAnnIeEntry[j].getCooperationType())){
                                ieConficts=true;
                            }
                        }
                    }
                } catch (Exception ex) {
                    throw new SepeRestException(":ERR:Δεν έγινε σωστή αποθήκευση των ιατρών. Προσπαθήστε ξανά.");
                }
                int countExypps=0;
                try {
                    for (int i = 0; i < spPtlCompIeAnnIeEntry.length; i++) {
                        if(spPtlCompIeAnnIeEntry[i].getCooperationType()==3){
                            countExypps++;
                        }
                    }
                } catch (Exception ex) {
                    throw new SepeRestException(":ERR:Δεν έγινε σωστή αποθήκευση των ιατρών. Προσπαθήστε ξανά.");
                }

                if (countExypps > 1) {
                    throw new SepeRestException(":ERR:Μπορείτε να έχετε μόνο μια ΕΞΥΠΠ.");
                } else if (ieConficts ==true) {
                    throw new SepeRestException(":ERR:Επαναλαμβάνεται κάποιος ιατρός.");
                }  else {
                    HashSet<SpPtlCompIeAnnIeEntry> compIeAnnIeEntry = new HashSet<SpPtlCompIeAnnIeEntry>();
                    for (int i = 0; i < spPtlCompIeAnnIeEntry.length; i++) {
                        SpPtlCompIeAnnIeEntry ieAnnIeEntry = new SpPtlCompIeAnnIeEntry();
                        if(spPtlCompIeAnnIeEntry[i].getCooperationType()!=3){
                            List<SpPtlDoctorRegrequest> doctorRequests = spPtlDoctorRegrequestRepo.findByAfm(spPtlCompIeAnnIeEntry[i].getIeAfm());
                            if (doctorRequests.size() == 0) {
                                throw new SepeRestException(":ERR:Ο τεχνικός με το συγκεκριμένο ΑΦΜ δεν είναι καταχωρημένος στο μητρώο.");
                            }
                            //SpPtlTechnicianRegrequest technician = technicianRequests.get(0);
                            //spPtlCompTaAnn.setTechnicianRegrequestUserId(technician.getUserId());
                        }
                        else{
                            List<SpPtlVRgVwExypp> doctorRequests = spPtlVRgVwExyppRepo.findByAfm(spPtlCompIeAnnIeEntry[i].getIeAfm());
                            if (doctorRequests.size() == 0) {
                                throw new SepeRestException(":ERR:Η ΕΞΥΠΠ με το συγκεκριμένο ΑΦΜ δεν είναι καταχωρημένη στο μητρώο.");
                            }
                            //SpPtlVRgVwExypp technician = technicianRequests.get(0);
                            //spPtlCompTaAnn.setTechnicianRegrequestUserId(technician.getId());
                        }

                        ieAnnIeEntry.setCompanyId(spPtlCompIeAnn.getCompanyId());
                        ieAnnIeEntry.setCompIeAnn(spPtlCompIeAnn);
                        ieAnnIeEntry.setCooperationType(spPtlCompIeAnnIeEntry[i].getCooperationType());
                        ieAnnIeEntry.setIeAfm(spPtlCompIeAnnIeEntry[i].getIeAfm());
                        ieAnnIeEntry.setIeFullname(spPtlCompIeAnnIeEntry[i].getIeFullname());
                        ieAnnIeEntry.setIeSpeciality(spPtlCompIeAnnIeEntry[i].getIeSpeciality());
                        ieAnnIeEntry.setIeSpecialityOther(spPtlCompIeAnnIeEntry[i].getIeSpecialityOther());
                        ieAnnIeEntry.setDoctorRegrequestId(spPtlCompIeAnnIeEntry[i].getDoctorRegrequestId());
                        ieAnnIeEntry.setDoctorRegrequestUserId(spPtlCompIeAnnIeEntry[i].getDoctorRegrequestUserId());
                        ieAnnIeEntry.setIeAnnIeStatus(spPtlCompIeAnnIeEntry[i].getIeAnnIeStatus());

                        compIeAnnIeEntry.add(ieAnnIeEntry);
                    }
                    spPtlCompIeAnn.setCompIeAnnIeEntries(compIeAnnIeEntry);
                }
            }
        }
        catch (SepeRestException ex){
            spPtlCompIeAnnRepo.delete(spPtlCompIeAnn);
            throw new SepeRestException(ex.getMessage());
        }
        try {
            SpPtlCompIeAnnDiaryEntryDTO[] spPtlCompIeAnnDiaryEntry = spPtlCompIeAnn.getDiaryEntries();
            if (null != spPtlCompIeAnnDiaryEntry) {
                boolean datesConficts = false;
                String conflicts = "Οι ημερομηνίες που επικαλύπτονται: ";
                try {
                    SpPtlCompIeAnnIeEntryDTO[] spPtlCompIeAnnIeEntry = spPtlCompIeAnn.getIeEntries();
                    for (int i = 0; i < spPtlCompIeAnnDiaryEntry.length; i++) {
                        Date date = new Date(spPtlCompIeAnnDiaryEntry[i].getVisitDate().getTime());
                        Long lVisitDate = date.getTime() / 1000;
                        Long lVisitTime = Time.valueOf(spPtlCompIeAnnDiaryEntry[i].getVisitTime() + ":00").getTime() / 1000;

                        Long dateAndTime = lVisitDate + lVisitTime - 3600; //remove 1 hour
                        //Long ieVisitConflict = spPtlVCompIeAnnDiaryRepo.IeVisitConflict(spPtlCompIeAnn.getDoctorRegrequestUserId(), dateAndTime, new Long(spPtlCompIeAnnDiaryEntry[i].getVisitDurationMinutes()));
                        //Todo: validate this!
                        List<Long> ieVisitConflicts = spPtlVCompIeAnnDiaryRepo.IeVisitConflict(spPtlCompIeAnn.getDoctorRegrequestUserId(), dateAndTime, new Long(spPtlCompIeAnnDiaryEntry[i].getVisitDurationMinutes()));

                        if (ieVisitConflicts.size() > 0) {
                            Long ieVisitConflict = ieVisitConflicts.get(0);

                            if (null != ieVisitConflict && ieVisitConflict > 0) {
                                datesConficts = true;
                                conflicts += i + " ";
                            }
                        }
                    }
                } catch (Exception ex) {
                    throw new SepeRestException("Δεν έγινε σωστή αποθήκευση των επισκέψεων. Προσπαθήστε ξανά.");
                }

                if (datesConficts == true) {
                    throw new SepeRestException(conflicts);
                } else {
                    boolean datesConfictsCurrent = false;
                    try {
                        Long dateAndTime, dateAndTime2;
                        int offset, offset2;
                        SpPtlCompIeAnnIeEntryDTO[] spPtlCompIeAnnIeEntry = spPtlCompIeAnn.getIeEntries();
                        for (int i = 0; i < spPtlCompIeAnnDiaryEntry.length; i++) {
                            Date date = new Date(spPtlCompIeAnnDiaryEntry[i].getVisitDate().getTime());
                            Long lVisitDate = date.getTime();
                            Long lVisitTime = Time.valueOf(spPtlCompIeAnnDiaryEntry[i].getVisitTime() + ":00").getTime();
                            offset = -date.getTimezoneOffset()/60;
                            if (offset == 3)
                                dateAndTime = lVisitDate + lVisitTime - (3600 * 1000); //remove 1 hour
                            else
                                dateAndTime = lVisitDate + lVisitTime;
                            Date dateOne = new Date(dateAndTime);
                            Date dateOneEnd = new Date(dateAndTime + spPtlCompIeAnnDiaryEntry[i].getVisitDurationMinutes() * 60000);
                            for (int ii = 0; ii < spPtlCompIeAnnDiaryEntry.length; ii++) {
                                if (i != ii && spPtlCompIeAnnDiaryEntry[i].getCompIeAnnIeId().equals(spPtlCompIeAnnDiaryEntry[ii].getCompIeAnnIeId())) {
                                    Date dateN2 = new Date(spPtlCompIeAnnDiaryEntry[ii].getVisitDate().getTime());
                                    Long lVisitDate2 = dateN2.getTime();
                                    Long lVisitTime2 = Time.valueOf(spPtlCompIeAnnDiaryEntry[ii].getVisitTime() + ":00").getTime();
                                    offset2 = -dateN2.getTimezoneOffset()/60;
                                    if (offset2 == 3)
                                        dateAndTime2 = lVisitDate2 + lVisitTime2 - (3600 * 1000); //remove 1 hour
                                    else
                                        dateAndTime2 = lVisitDate2 + lVisitTime2;
                                    Date dateTwo = new Date(dateAndTime2);
                                    Date dateTwoEnd = new Date(dateAndTime2 + spPtlCompIeAnnDiaryEntry[ii].getVisitDurationMinutes() * 60000);

                                    if (dateOne.before(dateTwoEnd) && dateTwo.before(dateOneEnd)) {
                                        datesConfictsCurrent = true;
                                    }
                                }
                            }

                        }
                    } catch (Exception ex) {
                        throw new SepeRestException(":ERR:Υπάρχει επικάλυψη στο ωράριο που δηλώσατε.");
                    }


                    if (datesConfictsCurrent == true) {
                        throw new SepeRestException(":ERR:Υπάρχει επικάλυψη στο ωράριο που δηλώσατε.");
                    } else {
                        HashSet<SpPtlCompIeAnnDiaryEntry> compIeAnnDiaryEntry = new HashSet<SpPtlCompIeAnnDiaryEntry>();
                        for (int i = 0; i < spPtlCompIeAnnDiaryEntry.length; i++) {
                            SpPtlCompIeAnnDiaryEntry ieAnnDiaryEntry = new SpPtlCompIeAnnDiaryEntry();
                            ieAnnDiaryEntry.setCompanyId(spPtlCompIeAnn.getCompanyId());
                            ieAnnDiaryEntry.setCompIeAnn(spPtlCompIeAnn);
                            ieAnnDiaryEntry.setVisitTime(spPtlCompIeAnnDiaryEntry[i].getVisitTime());
                            ieAnnDiaryEntry.setVisitDurationMinutes(spPtlCompIeAnnDiaryEntry[i].getVisitDurationMinutes());
                            ieAnnDiaryEntry.setVisitDate(spPtlCompIeAnnDiaryEntry[i].getVisitDate());
                            ieAnnDiaryEntry.setCompIeAnnIeId(spPtlCompIeAnnDiaryEntry[i].getCompIeAnnIeId());
                            compIeAnnDiaryEntry.add(ieAnnDiaryEntry);
                        }
                        spPtlCompIeAnn.setCompIeAnnDiaryEntries(compIeAnnDiaryEntry);
                    }
                }
            }
        }
        catch (SepeRestException ex){
            spPtlCompIeAnnRepo.delete(spPtlCompIeAnn);
            throw new SepeRestException(ex.getMessage());
        }

        spPtlCompIeAnn.setIeAnnIeTotal(spPtlCompIeAnn.getIeEntries().length);
        spPtlCompIeAnn.setIeAnnIeTotalAccept(0);
        spPtlCompIeAnn.setIeAnnIeTotalReject(0);


        completeCompanyFields(spPtlCompIeAnn);
    }

    @HandleAfterSave
    public void handleAfterSave(SpPtlCompIeAnn spPtlCompIeAnn) throws Exception {
        LOGGER.debug("Handling SpPtlCompIeAnn for: User ID " + CurrentUserDetailsService.getCurrentUser().getUsername());
        if(spPtlCompIeAnn.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode()) {
            if (spPtlCompIeAnn.getCompIeAnnPrevId() != null) {

                SpPtlCompIeAnn previousAnn = spPtlCompIeAnnRepo.ieFindOne(spPtlCompIeAnn.getCompIeAnnPrevId());

                if (previousAnn.getCompIeAnnIeEntries().size() > 0) {

                    Set<SpPtlCompIeAnnIeEntry> spPtlCompIeAnnIeEntry2 = previousAnn.getCompIeAnnIeEntries();
                    Iterator<SpPtlCompIeAnnIeEntry> interateIes = spPtlCompIeAnnIeEntry2.iterator();
                    while (interateIes.hasNext()) {
                        SpPtlCompIeAnnIeEntry next = interateIes.next();
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

                        if (next.getCooperationType() != 3) {
                            TWorkplaceDoctor workplacePreviousDoctor = workplaceDoctorRepository.findByAfmEquals(next.getIeAfm());
                            notification.setAccountId(workplacePreviousDoctor.getUserId());

                            notificationsRestRepository.save(notification);
                        } else {
                            TCompany safetyPreviousDoctor = companyRepository.findByUserId(next.getDoctorRegrequestUserId());
                            notification.setAccountId(safetyPreviousDoctor.getUserId());

                            notificationsRestRepository.save(notification);
                        }
                    }


                }
            }
            SpPtlCompIeAnnIeEntryDTO[] spPtlCompIeAnnIeEntry = spPtlCompIeAnn.getIeEntries();
            for (int i = 0; i < spPtlCompIeAnnIeEntry.length; i++) {

                TNotificationsAccountEntity notification = new TNotificationsAccountEntity();
                notification.setDocId(new Long(-1));
                notification.setTitle("Νέα αναγγελία");
                notification.setMessage("Έχει προστεθεί μια νέα αναγγελία στο όνομά σας. Για περισσότερες λεπτομέρειες πλοηγηθείτε στο μενού αριστερά με την ένδειξη 'Εκκρεμούσες αναγγελίες' για να την αποδεχτείτε ή να την απορρίψετε.");
                notification.setSender(spPtlCompIeAnn.getCompFullName());
                notification.setPriority(new Long(1));
                notification.setViewed(new Long(0));

                Date todayDate = new Date();
                Timestamp today = new Timestamp(todayDate.getTime());
                notification.setDateTime(today);

                if (spPtlCompIeAnnIeEntry[i].getCooperationType() != 3) {
                    TWorkplaceDoctor workplaceDoctor = workplaceDoctorRepository.findByAfmEquals(spPtlCompIeAnnIeEntry[i].getIeAfm());
                    notification.setAccountId(workplaceDoctor.getUserId());
                    notificationsRestRepository.save(notification);
                } else {
                    TCompany safetyDoctor = companyRepository.findByUserId(spPtlCompIeAnnIeEntry[i].getDoctorRegrequestUserId());
                    notification.setAccountId(safetyDoctor.getUserId());
                    notificationsRestRepository.save(notification);
                }
            }
        }
    }

    @HandleBeforeCreate
    public void handleBeforeCreates(SpPtlCompIeAnn spPtlCompIeAnn) throws SepeRestException {
        LOGGER.debug("Handling SpPtlCompIeAnn for: Company ID " + CurrentUserDetailsService.getCurrentUser().getUsername());

        SpringUserDetails currentUser = CurrentUserDetailsService.getCurrentUser();
        UserDTO userDTO = CurrentUserDetailsService.getCurrentUserByPrincipal();
        if (userDTO.getRole() == Constants.USER_TYPE.COMPANY_USER.getCode()) {
            TCompanyUserPrivilages priv = companyUserPrivilagesRepository.findByUser(userDTO.getId());
            ArrayList<Integer> list = new ArrayList(Arrays.asList(priv.getPrivilages().split("\\s*,\\s*")));
            if (null != priv.getPrivilages() && list.contains(String.valueOf(Constants.COMPANY_SERVICE.ASSIGN_NOTIFICATION.getCode()))) {
                Long companyId = priv.getCompId();
                spPtlCompIeAnn.setCompanyId(companyId);
            } else {
                throw new SepeRestException(":ERR:Ο χρήστης δεν έχει εξουσιοδότηση σε αυτό το είδος αίτησης.");
            }
        } else {
            spPtlCompIeAnn.setCompanyId(currentUser.getUserId());
        }


            /*List<SpPtlDoctorRegrequest> doctorRequests = spPtlDoctorRegrequestRepo.findByAfm(spPtlCompIeAnn.getIeAfm());
            if (doctorRequests.size() == 0) {
                throw new SepeRestException("Ο ιατρός με το συγκεκριμένο ΑΦΜ δεν είναι καταχωρημένος στο μητρώο.");
            }

            SpPtlDoctorRegrequest doctor = doctorRequests.get(0);

            spPtlCompIeAnn.setDoctorRegrequestUserId(doctor.getUserId());*/


        String date2 = spPtlCompIeAnn.getDateStart().toString().split(" ")[0];
        String dateIn=date2.split("-")[2]+"/"+date2.split("-")[1]+"/"+date2.split("-")[0];
        Long countActiveAnns = spPtlCompIeAnnRepo.countIeAnn(spPtlCompIeAnn.getCompPtlBranchId(), dateIn);
        if(countActiveAnns > 0) {
            if (null != spPtlCompIeAnn.getCompIeAnnPrevId() && countActiveAnns > 1) {
                throw new SepeRestException(":ERR:Οι ημερομηνίες που επιλέξατε επικαλύπτονται με ημερομηνίες μιας ήδη ενεργής αναγγελίας.");
            }
        }

        //PERIPTWSI ANTIKATASTASIS
        if (null != spPtlCompIeAnn.getCompIeAnnPrevId()) {

            //Company should select another doctor to replace the old one
            SpPtlCompIeAnn previousAnn = spPtlCompIeAnnRepo.findOne(spPtlCompIeAnn.getCompIeAnnPrevId());
            if (previousAnn.getDoctorRegrequestUserId().equals(spPtlCompIeAnn.getDoctorRegrequestUserId()) &&
                    previousAnn.getIeAnnStatus() != Constants.COMP_IE_TA_ANN_STATUS.EXPIRED.getCode()) {
                throw new SepeRestException("Πρέπει να επιλέξετε έναν άλλο ιατρό εργασίας για αυτές τις ημερομηνίες.");
            }

            Date todayDate = new Date();
            Timestamp today = new Timestamp(todayDate.getTime());
            previousAnn.setDateEnd(today);
            previousAnn.setIeAnnStatus(Constants.COMP_IE_TA_ANN_STATUS.EXPIRED.getCode());
            previousAnn.setReqStatus(Constants.REQUEST_STATUS.REJECTED.getCode());
            spPtlCompIeAnnRepo.save(previousAnn);
        }

        if(spPtlCompIeAnn.getCooperationTypeBasic()!=3)
            spPtlCompIeAnn.setExyppBasic(null);

        try {
            SpPtlCompIeAnnIeEntryDTO[] spPtlCompIeAnnIeEntry = spPtlCompIeAnn.getIeEntries();
            if (null != spPtlCompIeAnnIeEntry) {
                boolean ieConficts = false;
                String conflicts = "Οι ιατροί εργασίας που επαναλαμβάνονται: ";
                try {
                    for (int i = 1; i < spPtlCompIeAnnIeEntry.length; i++) {
                        for (int j = i+1; j < spPtlCompIeAnnIeEntry.length; j++) {
                            if(spPtlCompIeAnnIeEntry[i].getDoctorRegrequestUserId().equals(spPtlCompIeAnnIeEntry[j].getDoctorRegrequestUserId())){
                                ieConficts=true;
                            }
                        }
                    }
                } catch (Exception ex) {
                    throw new SepeRestException(":ERR:Δεν έγινε σωστή αποθήκευση των ιατρών. Προσπαθήστε ξανά.");
                }
                int countExypps=0;
                try {
                    for (int i = 0; i < spPtlCompIeAnnIeEntry.length; i++) {
                        if(spPtlCompIeAnnIeEntry[i].getCooperationType()==3){
                            countExypps++;
                        }
                    }
                } catch (Exception ex) {
                    throw new SepeRestException(":ERR:Δεν έγινε σωστή αποθήκευση των ιατρών. Προσπαθήστε ξανά.");
                }

                if (countExypps > 1) {
                    throw new SepeRestException(":ERR:Μπορείτε να έχετε μόνο μια ΕΞΥΠΠ.");
                } else if (ieConficts ==true) {
                    throw new SepeRestException(":ERR:Επαναλαμβάνεται κάποιος ιατρός.");
                } else {
                    HashSet<SpPtlCompIeAnnIeEntry> compIeAnnIeEntry = new HashSet<SpPtlCompIeAnnIeEntry>();
                    for (int i = 0; i < spPtlCompIeAnnIeEntry.length; i++) {
                        SpPtlCompIeAnnIeEntry ieAnnIeEntry = new SpPtlCompIeAnnIeEntry();
                        if(spPtlCompIeAnnIeEntry[i].getCooperationType()!=3){
                            List<SpPtlDoctorRegrequest> doctorRequests = spPtlDoctorRegrequestRepo.findByAfm(spPtlCompIeAnnIeEntry[i].getIeAfm());
                            if (doctorRequests.size() == 0) {
                                throw new SepeRestException(":ERR:Ο ιατρός με το συγκεκριμένο ΑΦΜ δεν είναι καταχωρημένος στο μητρώο.");
                            }
                            //SpPtlTechnicianRegrequest technician = technicianRequests.get(0);
                            //spPtlCompTaAnn.setTechnicianRegrequestUserId(technician.getUserId());
                        }
                        else{
                            List<SpPtlVRgVwExypp> doctorRequests = spPtlVRgVwExyppRepo.findByAfm(spPtlCompIeAnnIeEntry[i].getIeAfm());
                            if (doctorRequests.size() == 0) {
                                throw new SepeRestException(":ERR:Η ΕΞΥΠΠ με το συγκεκριμένο ΑΦΜ δεν είναι καταχωρημένη στο μητρώο.");
                            }
                            //SpPtlVRgVwExypp doctor = doctorRequests.get(0);
                            //spPtlCompTaAnn.setDoctorRegrequestUserId(technician.getId());
                        }

                        ieAnnIeEntry.setCompanyId(spPtlCompIeAnn.getCompanyId());
                        ieAnnIeEntry.setCompIeAnn(spPtlCompIeAnn);
                        ieAnnIeEntry.setCooperationType(spPtlCompIeAnnIeEntry[i].getCooperationType());
                        ieAnnIeEntry.setIeAfm(spPtlCompIeAnnIeEntry[i].getIeAfm());
                        ieAnnIeEntry.setIeFullname(spPtlCompIeAnnIeEntry[i].getIeFullname());
                        ieAnnIeEntry.setIeSpeciality(spPtlCompIeAnnIeEntry[i].getIeSpeciality());
                        ieAnnIeEntry.setIeSpecialityOther(spPtlCompIeAnnIeEntry[i].getIeSpecialityOther());
                        ieAnnIeEntry.setDoctorRegrequestId(spPtlCompIeAnnIeEntry[i].getDoctorRegrequestId());
                        ieAnnIeEntry.setDoctorRegrequestUserId(spPtlCompIeAnnIeEntry[i].getDoctorRegrequestUserId());
                        ieAnnIeEntry.setIeAnnIeStatus(spPtlCompIeAnnIeEntry[i].getIeAnnIeStatus());

                        compIeAnnIeEntry.add(ieAnnIeEntry);
                    }
                    spPtlCompIeAnn.setCompIeAnnIeEntries(compIeAnnIeEntry);
                }
            }
        }
        catch (SepeRestException ex){
            spPtlCompIeAnnRepo.delete(spPtlCompIeAnn);
            throw new SepeRestException(ex.getMessage());
        }

        try {
            SpPtlCompIeAnnDiaryEntryDTO[] spPtlCompIeAnnDiaryEntry = spPtlCompIeAnn.getDiaryEntries();
            if (null != spPtlCompIeAnnDiaryEntry) {
                boolean datesConficts = false;
                String conflicts = "Οι ημερομηνίες που επικαλύπτονται: ";
                try {
                    for (int i = 0; i < spPtlCompIeAnnDiaryEntry.length; i++) {
                        Date date = new Date(spPtlCompIeAnnDiaryEntry[i].getVisitDate().getTime());
                        Long lVisitDate = date.getTime() / 1000;
                        Long lVisitTime = Time.valueOf(spPtlCompIeAnnDiaryEntry[i].getVisitTime() + ":00").getTime() / 1000;

                        Long dateAndTime = lVisitDate + lVisitTime - 3600; //remove 1 hour
                        //Long ieVisitConflict = spPtlVCompIeAnnDiaryRepo.IeVisitConflict(spPtlCompIeAnn.getDoctorRegrequestUserId(), dateAndTime, new Long(spPtlCompIeAnnDiaryEntry[i].getVisitDurationMinutes()));
                        //Todo: validate this!
                        List<Long> ieVisitConflicts = spPtlVCompIeAnnDiaryRepo.IeVisitConflict(spPtlCompIeAnn.getDoctorRegrequestUserId(), dateAndTime, new Long(spPtlCompIeAnnDiaryEntry[i].getVisitDurationMinutes()));

                        if (ieVisitConflicts.size() > 0) {
                            Long ieVisitConflict = ieVisitConflicts.get(0);

                            if (null != ieVisitConflict && ieVisitConflict > 0) {
                                datesConficts = true;
                                conflicts += i + " ";
                            }
                        }
                    }
                } catch (Exception ex) {
                    throw new SepeRestException(":ERR:Δεν έγινε σωστή αποθήκευση των επισκέψεων. Προσπαθήστε ξανά.");
                }

                if (datesConficts == true) {
                    throw new SepeRestException(conflicts);
                } else {
                    boolean datesConfictsCurrent = false;
                    try {
                        Long dateAndTime, dateAndTime2;
                        int offset, offset2;
                        SpPtlCompIeAnnIeEntryDTO[] spPtlCompIeAnnIeEntry = spPtlCompIeAnn.getIeEntries();
                        for (int i = 0; i < spPtlCompIeAnnDiaryEntry.length; i++) {
                            Date date = new Date(spPtlCompIeAnnDiaryEntry[i].getVisitDate().getTime());
                            Long lVisitDate = date.getTime();
                            Long lVisitTime = Time.valueOf(spPtlCompIeAnnDiaryEntry[i].getVisitTime() + ":00").getTime();
                            offset = -date.getTimezoneOffset()/60;
                            if (offset == 3)
                                dateAndTime = lVisitDate + lVisitTime - (3600 * 1000); //remove 1 hour
                            else
                                dateAndTime = lVisitDate + lVisitTime;
                            Date dateOne = new Date(dateAndTime);
                            Date dateOneEnd = new Date(dateAndTime + spPtlCompIeAnnDiaryEntry[i].getVisitDurationMinutes() * 60000);
                            for (int ii = 0; ii < spPtlCompIeAnnDiaryEntry.length; ii++) {
                                if (i != ii && spPtlCompIeAnnDiaryEntry[i].getCompIeAnnIeId().equals(spPtlCompIeAnnDiaryEntry[ii].getCompIeAnnIeId())) {
                                    Date dateN2 = new Date(spPtlCompIeAnnDiaryEntry[ii].getVisitDate().getTime());
                                    Long lVisitDate2 = dateN2.getTime();
                                    Long lVisitTime2 = Time.valueOf(spPtlCompIeAnnDiaryEntry[ii].getVisitTime() + ":00").getTime();
                                    offset2 = -dateN2.getTimezoneOffset()/60;
                                    if (offset2 == 3)
                                        dateAndTime2 = lVisitDate2 + lVisitTime2 - (3600 * 1000); //remove 1 hour
                                    else
                                        dateAndTime2 = lVisitDate2 + lVisitTime2;
                                    Date dateTwo = new Date(dateAndTime2);
                                    Date dateTwoEnd = new Date(dateAndTime2 + spPtlCompIeAnnDiaryEntry[ii].getVisitDurationMinutes() * 60000);

                                    if (dateOne.before(dateTwoEnd) && dateTwo.before(dateOneEnd)) {
                                        datesConfictsCurrent = true;
                                    }
                                }
                            }

                        }
                    } catch (Exception ex) {
                        throw new SepeRestException(":ERR:Υπάρχει επικάλυψη στο ωράριο που δηλώσατε.");
                    }


                    if (datesConfictsCurrent == true) {
                        throw new SepeRestException(":ERR:Υπάρχει επικάλυψη στο ωράριο που δηλώσατε.");
                    } else {
                        HashSet<SpPtlCompIeAnnDiaryEntry> compIeAnnDiaryEntry = new HashSet<SpPtlCompIeAnnDiaryEntry>();
                        for (int i = 0; i < spPtlCompIeAnnDiaryEntry.length; i++) {
                            SpPtlCompIeAnnDiaryEntry ieAnnDiaryEntry = new SpPtlCompIeAnnDiaryEntry();
                            ieAnnDiaryEntry.setCompanyId(spPtlCompIeAnn.getCompanyId());
                            ieAnnDiaryEntry.setCompIeAnn(spPtlCompIeAnn);
                            ieAnnDiaryEntry.setVisitTime(spPtlCompIeAnnDiaryEntry[i].getVisitTime());
                            ieAnnDiaryEntry.setVisitDurationMinutes(spPtlCompIeAnnDiaryEntry[i].getVisitDurationMinutes());
                            ieAnnDiaryEntry.setVisitDate(spPtlCompIeAnnDiaryEntry[i].getVisitDate());
                            ieAnnDiaryEntry.setCompIeAnnIeId(spPtlCompIeAnnDiaryEntry[i].getCompIeAnnIeId());
                            compIeAnnDiaryEntry.add(ieAnnDiaryEntry);
                        }
                        spPtlCompIeAnn.setCompIeAnnDiaryEntries(compIeAnnDiaryEntry);
                    }
                }
            }
        }
        catch (SepeRestException ex){
            spPtlCompIeAnnRepo.delete(spPtlCompIeAnn);
            throw new SepeRestException(ex.getMessage());
        }

        spPtlCompIeAnn.setIeAnnIeTotal(spPtlCompIeAnn.getIeEntries().length);
        spPtlCompIeAnn.setIeAnnIeTotalAccept(0);
        spPtlCompIeAnn.setIeAnnIeTotalReject(0);

        completeCompanyFields(spPtlCompIeAnn);

    }

    @HandleAfterCreate
    public void handleAfterCreates(SpPtlCompIeAnn spPtlCompIeAnn) throws Exception {
        LOGGER.debug("Handling SpPtlCompIeAnn for: User ID " + CurrentUserDetailsService.getCurrentUser().getUsername());
        if (spPtlCompIeAnn.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode()) {
            if (spPtlCompIeAnn.getCompIeAnnPrevId() != null) {

                SpPtlCompIeAnn previousAnn = spPtlCompIeAnnRepo.ieFindOne(spPtlCompIeAnn.getCompIeAnnPrevId());

                if (previousAnn.getCompIeAnnIeEntries().size() > 0) {

                    Set<SpPtlCompIeAnnIeEntry> spPtlCompIeAnnIeEntry2 = previousAnn.getCompIeAnnIeEntries();
                    Iterator<SpPtlCompIeAnnIeEntry> interateIes = spPtlCompIeAnnIeEntry2.iterator();
                    while (interateIes.hasNext()) {
                        SpPtlCompIeAnnIeEntry next = interateIes.next();
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

                        if (next.getCooperationType() != 3) {
                            TWorkplaceDoctor workplacePreviousDoctor = workplaceDoctorRepository.findByAfmEquals(next.getIeAfm());
                            notification.setAccountId(workplacePreviousDoctor.getUserId());

                            notificationsRestRepository.save(notification);
                        } else {
                            TCompany safetyPreviousDoctor = companyRepository.findByUserId(next.getDoctorRegrequestUserId());
                            notification.setAccountId(safetyPreviousDoctor.getUserId());

                            notificationsRestRepository.save(notification);
                        }
                    }


                }
            }
            SpPtlCompIeAnnIeEntryDTO[] spPtlCompIeAnnIeEntry = spPtlCompIeAnn.getIeEntries();
            for (int i = 0; i < spPtlCompIeAnnIeEntry.length; i++) {

                TNotificationsAccountEntity notification = new TNotificationsAccountEntity();
                notification.setDocId(new Long(-1));
                notification.setTitle("Νέα αναγγελία");
                notification.setMessage("Έχει προστεθεί μια νέα αναγγελία στο όνομά σας. Για περισσότερες λεπτομέρειες πλοηγηθείτε στο μενού αριστερά με την ένδειξη 'Εκκρεμούσες αναγγελίες' για να την αποδεχτείτε ή να την απορρίψετε.");
                notification.setSender(spPtlCompIeAnn.getCompFullName());
                notification.setPriority(new Long(1));
                notification.setViewed(new Long(0));

                Date todayDate = new Date();
                Timestamp today = new Timestamp(todayDate.getTime());
                notification.setDateTime(today);

                if (spPtlCompIeAnnIeEntry[i].getCooperationType() != 3) {
                    TWorkplaceDoctor workplaceDoctor = workplaceDoctorRepository.findByAfmEquals(spPtlCompIeAnnIeEntry[i].getIeAfm());
                    notification.setAccountId(workplaceDoctor.getUserId());
                    notificationsRestRepository.save(notification);
                } else {
                    TCompany safetyDoctor = companyRepository.findByUserId(spPtlCompIeAnnIeEntry[i].getDoctorRegrequestUserId());
                    notification.setAccountId(safetyDoctor.getUserId());
                    notificationsRestRepository.save(notification);
                }

            }

        }
    }


    public void completeCompanyFields(SpPtlCompIeAnn spPtlCompIeAnn) {
        TCompany company = companyRepository.findOne(spPtlCompIeAnn.getCompanyId());

        //get company missing fields from MainBranchIKA
        //TEmployerBranchIKA ika = ikaRepo.findCompanyMainBranchByAme(company.getAme());
        TEmployerBranchIKA ika = ikaRepo.findCompanyMainBranchByAfmAndAme(company.getAfm(),company.getAme());

        spPtlCompIeAnn.setCompAme(company.getAme());

        if (Constants.StringIsNullOrEmpty(spPtlCompIeAnn.getCompTaxNumber())) {
            if (Constants.StringIsNullOrEmpty(ika.getRgEbrTaxationNumber()))
                spPtlCompIeAnn.setCompTaxNumber("");
            else
                spPtlCompIeAnn.setCompTaxNumber(ika.getRgEbrTaxationNumber());
        }

        if (Constants.StringIsNullOrEmpty(spPtlCompIeAnn.getCompFullName())) {
            if (Constants.StringIsNullOrEmpty(ika.getRgEmpFullname()))
                spPtlCompIeAnn.setCompFullName("");
            else
                spPtlCompIeAnn.setCompFullName(ika.getRgEmpFullname());
        }

        if (Constants.StringIsNullOrEmpty(spPtlCompIeAnn.getCompPhone())) {
            if (Constants.StringIsNullOrEmpty(ika.getRgEbrPhoneNumber()))
                spPtlCompIeAnn.setCompPhone("");
            else {
                if (ika.getRgEbrPhoneNumber().contains("-"))
                    ika.setRgEbrPhoneNumber(ika.getRgEbrPhoneNumber().replace("-", ""));

                spPtlCompIeAnn.setCompPhone(ika.getRgEbrPhoneNumber());
            }
        }

        if (Constants.StringIsNullOrEmpty(spPtlCompIeAnn.getCompAddr())) {
            if (Constants.StringIsNullOrEmpty(ika.getRgEbrAddressStreet()))
                spPtlCompIeAnn.setCompAddr("");
            else
                spPtlCompIeAnn.setCompAddr(ika.getRgEbrAddressStreet());
        }

        if (Constants.StringIsNullOrEmpty(spPtlCompIeAnn.getCompAddrTk())) {
            if (Constants.StringIsNullOrEmpty(ika.getRgEbrZipCode()))
                spPtlCompIeAnn.setCompAddrTk("");
            else
                spPtlCompIeAnn.setCompAddrTk(ika.getRgEbrZipCode());
        }

        if (Constants.StringIsNullOrEmpty(spPtlCompIeAnn.getCompAddrP())) {
            spPtlCompIeAnn.setCompAddrP("");
        }
        if (Constants.StringIsNullOrEmpty(spPtlCompIeAnn.getCompAddrPe())) {
            spPtlCompIeAnn.setCompAddrPe("");
        }
        if (Constants.StringIsNullOrEmpty(spPtlCompIeAnn.getCompAddrD())) {
            spPtlCompIeAnn.setCompAddrD("");
        }
        if (Constants.StringIsNullOrEmpty(spPtlCompIeAnn.getCompAddrK())) {
            if (Constants.StringIsNullOrEmpty(ika.getRgEbrKallikratis()))
                spPtlCompIeAnn.setCompAddrK("");
            else
                spPtlCompIeAnn.setCompAddrK(ika.getRgEbrKallikratis());
        }
        if(Constants.LongIsNullOrEmpty(spPtlCompIeAnn.getCompEbrBranch0Id()))
        {
            spPtlCompIeAnn.setCompEbrBranchId(new Long(-1));
        }

        if(Constants.LongIsNullOrEmpty(spPtlCompIeAnn.getCompEbrBranchId()))
        {
            spPtlCompIeAnn.setCompEbrBranchId(new Long(-1));
        }

        entityManager.detach(ika);
    }
}