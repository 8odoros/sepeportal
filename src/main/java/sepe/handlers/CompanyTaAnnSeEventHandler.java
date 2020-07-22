package sepe.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.*;
import org.springframework.stereotype.Service;
import sepe.config.Constants;
import sepe.config.SpringUserDetails;
import sepe.domain.TUser;
import sepe.domain.company.*;
import sepe.domain.general.TNotificationsAccountEntity;
import sepe.domain.technician.SpPtlTechnicianRegrequest;
import sepe.domain.technician.TSafetyTechnician;
import sepe.dto.SpPtlCompTaAnnDiaryEntryDTO;
import sepe.dto.SpPtlCompTaAnnTaEntryDTO;
import sepe.dto.UserDTO;
import sepe.repository.NotificationsRestRepository;
import sepe.repository.UserRepository;
import sepe.repository.company.*;
import sepe.repository.technician.SafetyTechnicianRepository;
import sepe.repository.technician.SpPtlTechnicianRegrequestRepo;
import sepe.repository.technician.SpPtlVCompTaAnnDiaryBRepo;
import sepe.repository.technician.SpPtlVCompTaAnnDiaryRepo;
import sepe.service.CompanyStoredProcedures;
import sepe.service.CurrentUserDetailsService;
import sepe.service.MailService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by dimitrisf on 5/10/2018.
 */
@Service
@RepositoryEventHandler(SpPtlCompTaAnnSe.class)
public class CompanyTaAnnSeEventHandler {

    @Autowired
    private CompanyStoredProcedures companyStoredProcedures;

    @Autowired
    SpPtlCompPtlBranchRepo spPtlCompPtlBranchRepo;

    @Autowired
    private TEmployerBranchIKARepo ikaRepo;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private SpPtlCompTaAnnDiaryEntryRepo spPtlCompTaAnnDiaryEntryRepo;

    @Autowired
    private SpPtlCompTaAnnSeRepo spPtlCompTaAnnSeRepo;

    @Autowired
    private SpPtlVCompTaAnnDiaryRepo spPtlVCompTaAnnDiaryRepo;

    @Autowired
    private SpPtlVCompTaAnnDiaryBRepo spPtlVCompTaAnnDiaryBRepo;

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
    SpPtlVRgVwExyppRepo spPtlVRgVwExyppRepo;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MailService mailService;

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyTaAnnSeEventHandler.class);

    @HandleBeforeSave
    public void handleBeforeSave(SpPtlCompTaAnnSe spPtlCompTaAnnSe) throws SepeRestException {

        LOGGER.debug("Handling SpPtlCompTaAnn for: Company ID " + CurrentUserDetailsService.getCurrentUser().getUsername());

        SpringUserDetails currentUser = CurrentUserDetailsService.getCurrentUser();
        UserDTO userDTO = CurrentUserDetailsService.getCurrentUserByPrincipal();
        if (userDTO.getRole() == Constants.USER_TYPE.COMPANY_USER.getCode()) {
            TCompanyUserPrivilages priv = companyUserPrivilagesRepository.findByUser(userDTO.getId());
            ArrayList<Integer> list = new ArrayList(Arrays.asList(priv.getPrivilages().split("\\s*,\\s*")));
            if(null != priv.getPrivilages() && list.contains(String.valueOf(Constants.COMPANY_SERVICE.ASSIGN_NOTIFICATION.getCode()))) {
                Long companyId = priv.getCompId();
                spPtlCompTaAnnSe.setUserId(priv.getUserId());
                spPtlCompTaAnnSe.setCompanyId(companyId);
            }
            else {
                throw new SepeRestException(":ERR:Ο χρήστης δεν έχει εξουσιοδότηση σε αυτό το είδος αίτησης.");
            }
        }
        else {
            spPtlCompTaAnnSe.setCompanyId(currentUser.getUserId());
        }

        try{
        SpPtlCompTaAnnDiaryEntryDTO[] spPtlCompTaAnnDiaryEntry = spPtlCompTaAnnSe.getDiaryEntries();

        HashSet<SpPtlCompTaAnnSeDiary> compTaAnnSeDiary = new HashSet<SpPtlCompTaAnnSeDiary>();
        List<SpPtlCompTaAnnSeDiary> ieAnnDiaryEntryList = new ArrayList<>();
        ieAnnDiaryEntryList.addAll(spPtlCompTaAnnSe.getCompTaAnnSeDiaryEntries());
        SpPtlCompTaAnnSeDiary ieAnnDiaryEntry;

        for (int i = 0; i < spPtlCompTaAnnDiaryEntry.length; i++) {
            if (i < ieAnnDiaryEntryList.size())
                ieAnnDiaryEntry = ieAnnDiaryEntryList.get(i);
            else
                ieAnnDiaryEntry = new SpPtlCompTaAnnSeDiary();

            ieAnnDiaryEntry.setCompanyId(spPtlCompTaAnnSe.getCompanyId());
            ieAnnDiaryEntry.setCompTaAnnSe(spPtlCompTaAnnSe);
            ieAnnDiaryEntry.setVisitTime(spPtlCompTaAnnDiaryEntry[i].getVisitTime());
            ieAnnDiaryEntry.setVisitDurationMinutes(spPtlCompTaAnnDiaryEntry[i].getVisitDurationMinutes());
            ieAnnDiaryEntry.setVisitDate(spPtlCompTaAnnDiaryEntry[i].getVisitDate());
            compTaAnnSeDiary.add(ieAnnDiaryEntry);
        }
            spPtlCompTaAnnSe.setCompTaAnnSeDiaryEntries(compTaAnnSeDiary);
        }
        catch (SepeRestException ex){
            //spPtlCompTaAnnSeRepo.delete(spPtlCompTaAnnSe);
            throw new SepeRestException(ex.getMessage());
        }

        completeCompanyFields(spPtlCompTaAnnSe);
        spPtlCompTaAnnSeRepo.save(spPtlCompTaAnnSe);

        if (spPtlCompTaAnnSe.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode())
        {
            try
            {
                (companyStoredProcedures).procIntArchiveCompTaAnnSe(spPtlCompTaAnnSe);
            } catch (Exception e) {
                e.printStackTrace();
                spPtlCompTaAnnSe.setSubStatus(Constants.SUBMIT_STATUS.SAVED.getCode());
                spPtlCompTaAnnSeRepo.save(spPtlCompTaAnnSe);
                throw new SepeRestException("To αίτημα σας δεν υποβλήθηκε, έχει όμως αποθηκευθεί.");
            }
        }
    }

    @HandleAfterSave
    public void handleAfterSave(SpPtlCompTaAnnSe spPtlCompTaAnnSe) throws Exception {
        LOGGER.debug("Handling SpPtlCompTaAnn for: User ID " + CurrentUserDetailsService.getCurrentUser().getUsername());

        /*if (spPtlCompTaAnn.getCompTaAnnPrevId() != null) {

            SpPtlCompTaAnn previousAnn = spPtlCompTaAnnRepo.taFindOne(spPtlCompTaAnn.getCompTaAnnPrevId());

            if (previousAnn.getCompTaAnnTaEntries().size()>0) {

                Set<SpPtlCompTaAnnTaEntry> spPtlCompTaAnnTaEntry2 = previousAnn.getCompTaAnnTaEntries();
                Iterator<SpPtlCompTaAnnTaEntry> interateTas = spPtlCompTaAnnTaEntry2.iterator();
                while(interateTas.hasNext()){
                    SpPtlCompTaAnnTaEntry next = interateTas.next();
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
                        TSafetyTechnician safetyPreviousTechnician = safetyTechnicianRepository.findByAfmEquals(next.getTaAfm());
                        notification.setAccountId(safetyPreviousTechnician.getUserId());

                        notificationsRestRepository.save(notification);
                        TUser user = userRepository.findOne(safetyPreviousTechnician.getUserId());
                        mailService.sendNotification(user.getEmail(), user.getEmailNotifEn(), "Παύση εργασίας", "Έγινε παύση εργασίας σας από την εταιρεία " + previousAnn.getCompFullName() + ". Για περισσότερες λεπτομέρειες πλοηγηθείτε στο μενού αριστερά με την ένδειξη 'Εκκρεμούσες αναγγελίες'.");
                    } else {
                        TCompany safetyPreviousTechnician = companyRepository.findByUserId(next.getTechnicianRegrequestUserId());
                        notification.setAccountId(safetyPreviousTechnician.getUserId());

                        notificationsRestRepository.save(notification);
                        TUser user = userRepository.findOne(safetyPreviousTechnician.getUserId());
                        mailService.sendNotification(user.getEmail(), user.getEmailNotifEn(), "Παύση εργασίας", "Έγινε παύση εργασίας σας από την εταιρεία " + previousAnn.getCompFullName() + ". Για περισσότερες λεπτομέρειες πλοηγηθείτε στο μενού αριστερά με την ένδειξη 'Εκκρεμούσες αναγγελίες'.");
                    }
                }


            }
        }

        //Send notification to new active IE account

        SpPtlCompTaAnnTaEntryDTO[] spPtlCompTaAnnTaEntry = spPtlCompTaAnn.getTaEntries();
        for (int i=0; i<spPtlCompTaAnnTaEntry.length; i++) {

            TNotificationsAccountEntity notification = new TNotificationsAccountEntity();
            notification.setDocId(new Long(-1));
            notification.setTitle("Νέα αναγγελία");
            notification.setMessage("Έχει προστεθεί μια νέα αναγγελία στο όνομά σας. Για περισσότερες λεπτομέρειες πλοηγηθείτε στο μενού αριστερά με την ένδειξη 'Εκκρεμούσες αναγγελίες' για να την αποδεχτείτε ή να την απορρίψετε.");
            notification.setSender(spPtlCompTaAnn.getCompFullName());
            notification.setPriority(new Long(1));
            notification.setViewed(new Long(0));

            Date todayDate = new Date();
            Timestamp today = new Timestamp(todayDate.getTime());
            notification.setDateTime(today);

            if (spPtlCompTaAnn.getSubStatus() != 1) {
                if (spPtlCompTaAnnTaEntry[i].getCooperationType() != 3) {
                    TSafetyTechnician safetyTechnician = safetyTechnicianRepository.findByAfmEquals(spPtlCompTaAnnTaEntry[i].getTaAfm());
                    notification.setAccountId(safetyTechnician.getUserId());
                    notificationsRestRepository.save(notification);
                    TUser user = userRepository.findOne(safetyTechnician.getUserId());
                    mailService.sendNotification(user.getEmail(), user.getEmailNotifEn(), "Νέα αναγγελία", "Έχει προστεθεί μια νέα αναγγελία στο όνομά σας. Για περισσότερες λεπτομέρειες πλοηγηθείτε στο μενού αριστερά με την ένδειξη 'Εκκρεμούσες αναγγελίες' για να την αποδεχτείτε ή να την απορρίψετε.");
                } else {
                    TCompany safetyTechnician = companyRepository.findByUserId(spPtlCompTaAnnTaEntry[i].getTechnicianRegrequestUserId());
                    notification.setAccountId(safetyTechnician.getUserId());
                    notificationsRestRepository.save(notification);
                    TUser user = userRepository.findOne(safetyTechnician.getUserId());
                    mailService.sendNotification(user.getEmail(), user.getEmailNotifEn(), "Νέα αναγγελία", "Έχει προστεθεί μια νέα αναγγελία στο όνομά σας. Για περισσότερες λεπτομέρειες πλοηγηθείτε στο μενού αριστερά με την ένδειξη 'Εκκρεμούσες αναγγελίες' για να την αποδεχτείτε ή να την απορρίψετε.");
                }
            }
        }*/
    }

    @HandleBeforeCreate
    public void handleBeforeCreates(SpPtlCompTaAnnSe spPtlCompTaAnnSe) throws SepeRestException {
        LOGGER.debug("Handling SpPtlCompTaAnn for: Company ID " + CurrentUserDetailsService.getCurrentUser().getUsername());

        SpringUserDetails currentUser = CurrentUserDetailsService.getCurrentUser();
        UserDTO userDTO = CurrentUserDetailsService.getCurrentUserByPrincipal();
        if (userDTO.getRole() == Constants.USER_TYPE.COMPANY_USER.getCode()) {
            TCompanyUserPrivilages priv = companyUserPrivilagesRepository.findByUser(userDTO.getId());
            ArrayList<Integer> list = new ArrayList(Arrays.asList(priv.getPrivilages().split("\\s*,\\s*")));
            if (null != priv.getPrivilages() && list.contains(String.valueOf(Constants.COMPANY_SERVICE.ASSIGN_NOTIFICATION.getCode()))) {
                Long companyId = priv.getCompId();
                spPtlCompTaAnnSe.setUserId(priv.getUserId());
                spPtlCompTaAnnSe.setCompanyId(companyId);
            } else {
                throw new SepeRestException(":ERR:Ο χρήστης δεν έχει εξουσιοδότηση σε αυτό το είδος αίτησης.");
            }
        } else {
            spPtlCompTaAnnSe.setCompanyId(currentUser.getUserId());
        }



        try {
        SpPtlCompTaAnnDiaryEntryDTO[] spPtlCompTaAnnDiaryEntry = spPtlCompTaAnnSe.getDiaryEntries();
        HashSet<SpPtlCompTaAnnSeDiary> compTaAnnDiaryEntry = new HashSet<SpPtlCompTaAnnSeDiary>();
        List<SpPtlCompTaAnnSeDiary> ieAnnDiaryEntryList = new ArrayList<>();
        ieAnnDiaryEntryList.addAll(spPtlCompTaAnnSe.getCompTaAnnSeDiaryEntries());
        SpPtlCompTaAnnSeDiary ieAnnDiaryEntry;
        for (int i = 0; i < spPtlCompTaAnnDiaryEntry.length; i++) {
            if (i < ieAnnDiaryEntryList.size())
                ieAnnDiaryEntry = ieAnnDiaryEntryList.get(i);
            else
                ieAnnDiaryEntry = new SpPtlCompTaAnnSeDiary();
            ieAnnDiaryEntry.setCompanyId(spPtlCompTaAnnSe.getCompanyId());
            ieAnnDiaryEntry.setCompTaAnnSe(spPtlCompTaAnnSe);
            ieAnnDiaryEntry.setVisitTime(spPtlCompTaAnnDiaryEntry[i].getVisitTime());
            ieAnnDiaryEntry.setVisitDurationMinutes(spPtlCompTaAnnDiaryEntry[i].getVisitDurationMinutes());
            ieAnnDiaryEntry.setVisitDate(spPtlCompTaAnnDiaryEntry[i].getVisitDate());
            compTaAnnDiaryEntry.add(ieAnnDiaryEntry);
        }
            spPtlCompTaAnnSe.setCompTaAnnSeDiaryEntries(compTaAnnDiaryEntry);
        }
        catch (SepeRestException ex){
            spPtlCompTaAnnSeRepo.delete(spPtlCompTaAnnSe);
            throw new SepeRestException(ex.getMessage());
        }

        completeCompanyFields(spPtlCompTaAnnSe);
        spPtlCompTaAnnSeRepo.save(spPtlCompTaAnnSe);

        if (spPtlCompTaAnnSe.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode())
        {
            try
            {
                (companyStoredProcedures).procIntArchiveCompTaAnnSe(spPtlCompTaAnnSe);
            } catch (Exception e) {
                e.printStackTrace();
                spPtlCompTaAnnSe.setSubStatus(Constants.SUBMIT_STATUS.SAVED.getCode());
                spPtlCompTaAnnSeRepo.save(spPtlCompTaAnnSe);
                throw new SepeRestException("To αίτημα σας δεν υποβλήθηκε, έχει όμως αποθηκευθεί.");
            }
        }
    }

    @HandleAfterCreate
    public void handleAfterCreates(SpPtlCompTaAnn spPtlCompTaAnn) throws Exception {
        LOGGER.debug("Handling SpPtlCompTaAnn for: User ID " + CurrentUserDetailsService.getCurrentUser().getUsername());

        /*if(spPtlCompTaAnn.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode()) {
            if (spPtlCompTaAnn.getCompTaAnnPrevId() != null) {

                SpPtlCompTaAnn previousAnn = spPtlCompTaAnnRepo.taFindOne(spPtlCompTaAnn.getCompTaAnnPrevId());

                if (previousAnn.getCompTaAnnTaEntries().size()>0) {

                    Set<SpPtlCompTaAnnTaEntry> spPtlCompTaAnnTaEntry2 = previousAnn.getCompTaAnnTaEntries();
                    Iterator<SpPtlCompTaAnnTaEntry> interateTas = spPtlCompTaAnnTaEntry2.iterator();
                    while(interateTas.hasNext()){
                        SpPtlCompTaAnnTaEntry next = interateTas.next();
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
                            TSafetyTechnician safetyPreviousTechnician = safetyTechnicianRepository.findByAfmEquals(next.getTaAfm());
                            notification.setAccountId(safetyPreviousTechnician.getUserId());

                            notificationsRestRepository.save(notification);
                            TUser user = userRepository.findOne(safetyPreviousTechnician.getUserId());
                            mailService.sendNotification(user.getEmail(), user.getEmailNotifEn(), "Παύση εργασίας", "Έγινε παύση εργασίας σας από την εταιρεία " + previousAnn.getCompFullName() + ". Για περισσότερες λεπτομέρειες πλοηγηθείτε στο μενού αριστερά με την ένδειξη 'Εκκρεμούσες αναγγελίες'.");
                        } else {
                            TCompany safetyPreviousTechnician = companyRepository.findByUserId(next.getTechnicianRegrequestUserId());
                            notification.setAccountId(safetyPreviousTechnician.getUserId());

                            notificationsRestRepository.save(notification);
                            TUser user = userRepository.findOne(safetyPreviousTechnician.getUserId());
                            mailService.sendNotification(user.getEmail(), user.getEmailNotifEn(), "Παύση εργασίας", "Έγινε παύση εργασίας σας από την εταιρεία " + previousAnn.getCompFullName() + ". Για περισσότερες λεπτομέρειες πλοηγηθείτε στο μενού αριστερά με την ένδειξη 'Εκκρεμούσες αναγγελίες'.");
                        }
                    }


                }
            }

            //Send notification to new active IE account

            SpPtlCompTaAnnTaEntryDTO[] spPtlCompTaAnnTaEntry = spPtlCompTaAnn.getTaEntries();
            for (int i = 0; i < spPtlCompTaAnnTaEntry.length; i++) {

                TNotificationsAccountEntity notification = new TNotificationsAccountEntity();
                notification.setDocId(new Long(-1));
                notification.setTitle("Νέα αναγγελία");
                notification.setMessage("Έχει προστεθεί μια νέα αναγγελία στο όνομά σας. Για περισσότερες λεπτομέρειες πλοηγηθείτε στο μενού αριστερά με την ένδειξη 'Εκκρεμούσες αναγγελίες' για να την αποδεχτείτε ή να την απορρίψετε.");
                notification.setSender(spPtlCompTaAnn.getCompFullName());
                notification.setPriority(new Long(1));
                notification.setViewed(new Long(0));

                Date todayDate = new Date();
                Timestamp today = new Timestamp(todayDate.getTime());
                notification.setDateTime(today);

                if (spPtlCompTaAnnTaEntry[i].getCooperationType() != 3) {
                    TSafetyTechnician safetyTechnician = safetyTechnicianRepository.findByAfmEquals(spPtlCompTaAnnTaEntry[i].getTaAfm());
                    notification.setAccountId(safetyTechnician.getUserId());
                    notificationsRestRepository.save(notification);
                    TUser user = userRepository.findOne(safetyTechnician.getUserId());
                    mailService.sendNotification(user.getEmail(), user.getEmailNotifEn(), "Νέα αναγγελία", "Έχει προστεθεί μια νέα αναγγελία στο όνομά σας. Για περισσότερες λεπτομέρειες πλοηγηθείτε στο μενού αριστερά με την ένδειξη 'Εκκρεμούσες αναγγελίες' για να την αποδεχτείτε ή να την απορρίψετε.");
                } else {
                    TCompany safetyTechnician = companyRepository.findByUserId(spPtlCompTaAnnTaEntry[i].getTechnicianRegrequestUserId());
                    notification.setAccountId(safetyTechnician.getUserId());
                    notificationsRestRepository.save(notification);
                    TUser user = userRepository.findOne(safetyTechnician.getUserId());
                    mailService.sendNotification(user.getEmail(), user.getEmailNotifEn(), "Νέα αναγγελία", "Έχει προστεθεί μια νέα αναγγελία στο όνομά σας. Για περισσότερες λεπτομέρειες πλοηγηθείτε στο μενού αριστερά με την ένδειξη 'Εκκρεμούσες αναγγελίες' για να την αποδεχτείτε ή να την απορρίψετε.");
                }

            }
        }*/
    }

    public void completeCompanyFields(SpPtlCompTaAnnSe spPtlCompTaAnnSe) {
        TCompany company = companyRepository.findOne(spPtlCompTaAnnSe.getCompanyId());

        //get company missing fields from MainBranchIKA
        //TEmployerBranchIKA ika = ikaRepo.findCompanyMainBranchByAme(company.getAme());
        TEmployerBranchIKA ika = ikaRepo.findCompanyMainBranchByAfmAndAme(company.getAfm(),company.getAme());

        spPtlCompTaAnnSe.setCompAme(company.getAme());

        if (Constants.StringIsNullOrEmpty(spPtlCompTaAnnSe.getCompTaxNumber())) {
            if (Constants.StringIsNullOrEmpty(ika.getRgEbrTaxationNumber()))
                spPtlCompTaAnnSe.setCompTaxNumber("");
            else
                spPtlCompTaAnnSe.setCompTaxNumber(ika.getRgEbrTaxationNumber());
        }

        if (Constants.StringIsNullOrEmpty(spPtlCompTaAnnSe.getCompFullName())) {
            if (Constants.StringIsNullOrEmpty(ika.getRgEmpFullname()))
                spPtlCompTaAnnSe.setCompFullName("");
            else
                spPtlCompTaAnnSe.setCompFullName(ika.getRgEmpFullname());
        }

        if (Constants.StringIsNullOrEmpty(spPtlCompTaAnnSe.getCompPhone())) {
            if (Constants.StringIsNullOrEmpty(ika.getRgEbrPhoneNumber()))
                spPtlCompTaAnnSe.setCompPhone("");
            else {
                if (ika.getRgEbrPhoneNumber().contains("-"))
                    ika.setRgEbrPhoneNumber(ika.getRgEbrPhoneNumber().replace("-", ""));

                spPtlCompTaAnnSe.setCompPhone(ika.getRgEbrPhoneNumber());
            }
        }

        if (Constants.StringIsNullOrEmpty(spPtlCompTaAnnSe.getCompAddr())) {
            if (Constants.StringIsNullOrEmpty(ika.getRgEbrAddressStreet()))
                spPtlCompTaAnnSe.setCompAddr("");
            else
                spPtlCompTaAnnSe.setCompAddr(ika.getRgEbrAddressStreet());
        }

        if (Constants.StringIsNullOrEmpty(spPtlCompTaAnnSe.getCompAddrTk())) {
            if (Constants.StringIsNullOrEmpty(ika.getRgEbrZipCode()))
                spPtlCompTaAnnSe.setCompAddrTk("");
            else
                spPtlCompTaAnnSe.setCompAddrTk(ika.getRgEbrZipCode());
        }

        if (Constants.StringIsNullOrEmpty(spPtlCompTaAnnSe.getCompAddrP())) {
            spPtlCompTaAnnSe.setCompAddrP("");
        }
        if (Constants.StringIsNullOrEmpty(spPtlCompTaAnnSe.getCompAddrPe())) {
            spPtlCompTaAnnSe.setCompAddrPe("");
        }
        if (Constants.StringIsNullOrEmpty(spPtlCompTaAnnSe.getCompAddrD())) {
            spPtlCompTaAnnSe.setCompAddrD("");
        }
        if (Constants.StringIsNullOrEmpty(spPtlCompTaAnnSe.getCompAddrK())) {
            if (Constants.StringIsNullOrEmpty(ika.getRgEbrKallikratis()))
                spPtlCompTaAnnSe.setCompAddrK("");
            else
                spPtlCompTaAnnSe.setCompAddrK(ika.getRgEbrKallikratis());
        }
        if(Constants.LongIsNullOrEmpty(spPtlCompTaAnnSe.getCompEbrBranch0Id()))
        {
            spPtlCompTaAnnSe.setCompEbrBranchId(new Long(-1));
        }

        if(Constants.LongIsNullOrEmpty(spPtlCompTaAnnSe.getCompEbrBranchId()))
        {
            spPtlCompTaAnnSe.setCompEbrBranchId(new Long(-1));
        }

        entityManager.detach(ika);
    }

}
