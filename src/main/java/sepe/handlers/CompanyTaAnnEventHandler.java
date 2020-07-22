package sepe.handlers;

import org.eclipse.persistence.jpa.jpql.parser.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.*;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.DateUtils;
import sepe.config.Constants;
import sepe.config.SpringUserDetails;
import sepe.domain.TUser;
import sepe.domain.company.*;
import sepe.domain.general.TNotificationsAccountEntity;
import sepe.domain.technician.SpPtlTechnicianRegrequest;
import sepe.domain.technician.SpPtlVCompTaAnnDiaryB;
import sepe.domain.technician.SpPtlVTechnicianRegrequest;
import sepe.domain.technician.TSafetyTechnician;
import sepe.dto.SpPtlCompTaAnnDiaryEntryDTO;
import sepe.dto.SpPtlCompTaAnnTaEntryDTO;
import sepe.dto.UserDTO;
import sepe.repository.NotificationsRestRepository;
import sepe.repository.UserRepository;
import sepe.repository.company.*;
import sepe.repository.technician.*;
import sepe.service.CompanyStoredProcedures;
import sepe.service.CurrentUserDetailsService;
import sepe.service.MailService;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.zone.ZoneRules;
import java.util.*;

/**
 * Created by Annita on 5/20/2015.
 */

@Service
@RepositoryEventHandler(SpPtlCompTaAnn.class)
public class CompanyTaAnnEventHandler {
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
    private SpPtlCompTaAnnRepo spPtlCompTaAnnRepo;

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
    SpPtlVTechnicianRegrequestRepo spPtlVTechnicianRegrequestRepo;

    @Autowired
    SpPtlVRgVwExyppRepo spPtlVRgVwExyppRepo;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MailService mailService;

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyTaAnnEventHandler.class);

    @HandleBeforeSave
    public void handleBeforeSave(SpPtlCompTaAnn spPtlCompTaAnn) throws SepeRestException {

        LOGGER.debug("Handling SpPtlCompTaAnn for: Company ID " + CurrentUserDetailsService.getCurrentUser().getUsername());

        SpringUserDetails currentUser = CurrentUserDetailsService.getCurrentUser();
        UserDTO userDTO = CurrentUserDetailsService.getCurrentUserByPrincipal();
        if (userDTO.getRole() == Constants.USER_TYPE.COMPANY_USER.getCode()) {
            TCompanyUserPrivilages priv = companyUserPrivilagesRepository.findByUser(userDTO.getId());
            ArrayList<Integer> list = new ArrayList(Arrays.asList(priv.getPrivilages().split("\\s*,\\s*")));
            if(null != priv.getPrivilages() && list.contains(String.valueOf(Constants.COMPANY_SERVICE.ASSIGN_NOTIFICATION.getCode()))) {
                Long companyId = priv.getCompId();
                spPtlCompTaAnn.setUserId(priv.getUserId());
                spPtlCompTaAnn.setCompanyId(companyId);
            }
            else {
                throw new SepeRestException(":ERR:Ο χρήστης δεν έχει εξουσιοδότηση σε αυτό το είδος αίτησης.");
            }
        }
        else {
            spPtlCompTaAnn.setCompanyId(currentUser.getUserId());
        }

        //PERIPTWSI ANTIKATASTASIS
        if (null != spPtlCompTaAnn.getCompTaAnnPrevId()) {

            //Company should select another technician to replace the old one
            SpPtlCompTaAnn previousAnn = spPtlCompTaAnnRepo.findOne(spPtlCompTaAnn.getCompTaAnnPrevId());
            //elegxos 1 to the technicians
            if (previousAnn.getTechnicianRegrequestUserId().equals(spPtlCompTaAnn.getTechnicianRegrequestUserId())) {
                throw new SepeRestException("Πρέπει να επιλέξετε έναν άλλο τεχνικό εργασίας για αυτές τις ημερομηνίες.");
            }

            Date todayDate = new Date();
            Timestamp today = new Timestamp(todayDate.getTime());
            previousAnn.setDateEnd(today);
            previousAnn.setTaAnnStatus(Constants.COMP_IE_TA_ANN_STATUS.EXPIRED.getCode());
            previousAnn.setReqStatus(Constants.REQUEST_STATUS.REJECTED.getCode());
            spPtlCompTaAnnRepo.save(previousAnn);
        }

        if(spPtlCompTaAnn.getCooperationTypeBasic() == null || spPtlCompTaAnn.getCooperationTypeBasic()!=3)
            spPtlCompTaAnn.setExyppBasic(null);

        try {
            SpPtlCompTaAnnDiaryEntryDTO[] spPtlCompTaAnnDiaryEntry = spPtlCompTaAnn.getDiaryEntries();

            if (null != spPtlCompTaAnnDiaryEntry) {
                boolean datesConficts = false;
                String conflicts = "Οι ημερομηνίες που επικαλύπτονται: ";
                StringBuilder conflictsConcat = new StringBuilder();
                try {
                    SpPtlCompTaAnnTaEntryDTO[] spPtlCompTaAnnTaEntry = spPtlCompTaAnn.getTaEntries();
                    if (spPtlCompTaAnn.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode()){

                        int daySec = 1 * 24 * 60 * 60 * 1000;
                        Long dateStart = new Date(spPtlCompTaAnn.getDateStart().getTime()).getTime()/1000 - daySec;
                        Long dateEnd = new Date(spPtlCompTaAnn.getDateEnd().getTime()).getTime()/1000 + daySec;
                        /*List<Long> ieVisitConflictQueryTechnicianRegrequestUserId = spPtlVCompTaAnnDiaryBRepo.taVisitConflict1(dateStart, dateEnd);
                        List<Integer> ieVisitConflictQueryVisitDurationMinutes = spPtlVCompTaAnnDiaryBRepo.taVisitConflict3(dateStart, dateEnd);
                        List<Date> ieVisitConflictQueryVisitDate = spPtlVCompTaAnnDiaryBRepo.taVisitConflict4(dateStart, dateEnd);
                        List<String> ieVisitConflictQueryVisitTime = spPtlVCompTaAnnDiaryBRepo.taVisitConflict5(dateStart, dateEnd);*/
                        String dates = "";
                        int cnt = 0;
                        for (SpPtlCompTaAnnDiaryEntryDTO i : spPtlCompTaAnn.getDiaryEntries())
                        {
                            if (cnt == 0)
                                dates = dates + "'" + new SimpleDateFormat("dd-MM-yyyy").format(new Date(i.getVisitDate().getTime())) + "','" + new SimpleDateFormat("dd-MM-yyyy").format(new Date(i.getVisitDate().getTime() + daySec)) + "'";
                            else if (!dates.contains(new SimpleDateFormat("dd-MM-yyyy").format(new Date(i.getVisitDate().getTime()))))
                                dates = dates + ",'" + new SimpleDateFormat("dd-MM-yyyy").format(new Date(i.getVisitDate().getTime())) + "','" + new SimpleDateFormat("dd-MM-yyyy").format(new Date(i.getVisitDate().getTime() + daySec)) + "'";
                            cnt++;
                        }
                        List<SpPtlVCompTaAnnDiaryB> ieVisitConflictQuery = null;
                        List<Long> techUserIds = new ArrayList<>();
                        Date date;
                        Long lVisitDate, lVisitTime, dateAndTime, durationMins, technicianRegrequestUserId, visitDateEpochSec, conflictVisitDate, conflictVisitTime;
                        Integer visitDurationMinutes;
                        int offset, conflictOffset;
                        for (int i = 0; i < spPtlCompTaAnnDiaryEntry.length; i++) {

                            if (!techUserIds.contains(spPtlCompTaAnnDiaryEntry[i].getCompTaAnnTaId()))
                            {
                                techUserIds.add(spPtlCompTaAnnDiaryEntry[i].getCompTaAnnTaId());
                                ieVisitConflictQuery = entityManager.createNativeQuery("SELECT * FROM SP_PTL_V_COMP_TA_ANN_DIARY_B WHERE TO_CHAR(VISIT_DATE, 'DD-MM-YYYY') IN ("+dates+") AND TECHNICIAN_REGREQUEST_USER_ID = "+spPtlCompTaAnnDiaryEntry[i].getCompTaAnnTaId()+" ORDER BY VISIT_DATE ASC", SpPtlVCompTaAnnDiaryB.class).getResultList();
                            }

                            if (techUserIds.contains(spPtlCompTaAnnDiaryEntry[i].getCompTaAnnTaId()) && techUserIds.indexOf(spPtlCompTaAnnDiaryEntry[i].getCompTaAnnTaId()) != techUserIds.size()-1)
                                ieVisitConflictQuery = entityManager.createNativeQuery("SELECT * FROM SP_PTL_V_COMP_TA_ANN_DIARY_B WHERE TO_CHAR(VISIT_DATE, 'DD-MM-YYYY') IN ("+dates+") AND TECHNICIAN_REGREQUEST_USER_ID = "+spPtlCompTaAnnDiaryEntry[i].getCompTaAnnTaId()+" ORDER BY VISIT_DATE ASC", SpPtlVCompTaAnnDiaryB.class).getResultList();

                            date = new Date(spPtlCompTaAnnDiaryEntry[i].getVisitDate().getTime());
                            offset = -date.getTimezoneOffset()/60;
                            lVisitDate = date.getTime() / 1000;
                            lVisitTime = Time.valueOf(spPtlCompTaAnnDiaryEntry[i].getVisitTime() + ":00").getTime() / 1000;
                            if (offset == 3)
                                dateAndTime = lVisitDate + lVisitTime - 3600; //remove 1 hour
                            else
                                dateAndTime = lVisitDate + lVisitTime;
                            durationMins = new Long(spPtlCompTaAnnDiaryEntry[i].getVisitDurationMinutes());


                            for (int j = 0; j < ieVisitConflictQuery.size(); j++)
                            {
                                technicianRegrequestUserId = ieVisitConflictQuery.get(j).getTechnicianRegrequestUserId();

                                conflictVisitDate = (long)Math.round((ieVisitConflictQuery.get(j).getVisitDate().getTime() / 1000) / 86400) * 86400;
                                conflictVisitTime = Time.valueOf(ieVisitConflictQuery.get(j).getVisitTime() + ":00").getTime() / 1000;
                                conflictOffset = conflictVisitDate.equals(lVisitDate) ? offset : -ieVisitConflictQuery.get(j).getVisitDate().getTimezoneOffset() / 60;
                                if (conflictOffset == 3)
                                    visitDateEpochSec = conflictVisitDate + conflictVisitTime - 3600; //remove 1 hour
                                else
                                    visitDateEpochSec = conflictVisitDate + conflictVisitTime;

                                visitDurationMinutes = ieVisitConflictQuery.get(j).getVisitDurationMinutes();

                                if (technicianRegrequestUserId == (long)spPtlCompTaAnnDiaryEntry[i].getCompTaAnnTaId() && ((dateAndTime <= visitDateEpochSec && (dateAndTime + (durationMins*60)) > visitDateEpochSec) ||
                                        (dateAndTime >= visitDateEpochSec && (dateAndTime + (durationMins*60)) <= (visitDateEpochSec + visitDurationMinutes*60)) ||
                                        (dateAndTime < (visitDateEpochSec + visitDurationMinutes*60) && (dateAndTime + (durationMins*60)) >= (visitDateEpochSec + visitDurationMinutes*60))))
                                {
                                    datesConficts = true;
                                    conflictsConcat.append(i);
                                    conflictsConcat.append(" ");
                                    break;
                                }
                            }
                        }
                        conflicts += conflictsConcat.toString();
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
                        SpPtlCompTaAnnTaEntryDTO[] spPtlCompTaAnnTaEntry = spPtlCompTaAnn.getTaEntries();
                        if (spPtlCompTaAnn.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode()){
                            for (int i = 0; i < spPtlCompTaAnnDiaryEntry.length; i++) {
                                Date date = new Date(spPtlCompTaAnnDiaryEntry[i].getVisitDate().getTime());
                                Long lVisitDate = date.getTime();
                                Long lVisitTime = Time.valueOf(spPtlCompTaAnnDiaryEntry[i].getVisitTime() + ":00").getTime();
                                offset = -date.getTimezoneOffset()/60;
                                if (offset == 3)
                                    dateAndTime = lVisitDate + lVisitTime - (3600 * 1000); //remove 1 hour
                                else
                                    dateAndTime = lVisitDate + lVisitTime;
                                Date dateOne = new Date(dateAndTime);
                                Date dateOneEnd = new Date(dateAndTime + spPtlCompTaAnnDiaryEntry[i].getVisitDurationMinutes() * 60000);
                                Long compTaAnnTaAnnIdOne = spPtlCompTaAnnDiaryEntry[i].getCompTaAnnTaId();
                                for (int ii = 0; ii < spPtlCompTaAnnDiaryEntry.length; ii++) {
                                    if (i != ii) {
                                        Date dateN2 = new Date(spPtlCompTaAnnDiaryEntry[ii].getVisitDate().getTime());
                                        Long lVisitDate2 = dateN2.getTime();
                                        Long lVisitTime2 = Time.valueOf(spPtlCompTaAnnDiaryEntry[ii].getVisitTime() + ":00").getTime();
                                        offset2 = -dateN2.getTimezoneOffset()/60;
                                        if (offset2 == 3)
                                            dateAndTime2 = lVisitDate2 + lVisitTime2 - (3600 * 1000); //remove 1 hour
                                        else
                                            dateAndTime2 = lVisitDate2 + lVisitTime2;
                                        Date dateTwo = new Date(dateAndTime2);
                                        Date dateTwoEnd = new Date(dateAndTime2 + spPtlCompTaAnnDiaryEntry[ii].getVisitDurationMinutes() * 60000);
                                        Long compTaAnnTaAnnIdTwo = spPtlCompTaAnnDiaryEntry[ii].getCompTaAnnTaId();

                                        if (dateOne.before(dateTwoEnd) && dateTwo.before(dateOneEnd) && compTaAnnTaAnnIdOne.equals(compTaAnnTaAnnIdTwo)) {
                                            datesConfictsCurrent = true;
                                        }
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
                        HashSet<SpPtlCompTaAnnDiaryEntry> compTaAnnDiaryEntry = new HashSet<SpPtlCompTaAnnDiaryEntry>();
                        List<SpPtlCompTaAnnDiaryEntry> ieAnnDiaryEntryList = new ArrayList<>();
                        ieAnnDiaryEntryList.addAll(spPtlCompTaAnn.getCompTaAnnDiaryEntries());
                        SpPtlCompTaAnnDiaryEntry ieAnnDiaryEntry;

                        for (int i = 0; i < spPtlCompTaAnnDiaryEntry.length; i++) {
                            if (i < ieAnnDiaryEntryList.size())
                                ieAnnDiaryEntry = ieAnnDiaryEntryList.get(i);
                            else
                                ieAnnDiaryEntry = new SpPtlCompTaAnnDiaryEntry();

                            ieAnnDiaryEntry.setCompanyId(spPtlCompTaAnn.getCompanyId());
                            ieAnnDiaryEntry.setCompTaAnn(spPtlCompTaAnn);
                            ieAnnDiaryEntry.setVisitTime(spPtlCompTaAnnDiaryEntry[i].getVisitTime());
                            ieAnnDiaryEntry.setVisitDurationMinutes(spPtlCompTaAnnDiaryEntry[i].getVisitDurationMinutes());
                            ieAnnDiaryEntry.setVisitDate(spPtlCompTaAnnDiaryEntry[i].getVisitDate());
                            ieAnnDiaryEntry.setCompTaAnnTaId(spPtlCompTaAnnDiaryEntry[i].getCompTaAnnTaId());
                            if (spPtlCompTaAnn.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode())
                                ieAnnDiaryEntry.setIsSubmitted(2);
                            else
                                ieAnnDiaryEntry.setIsSubmitted(1);
                            compTaAnnDiaryEntry.add(ieAnnDiaryEntry);
                        }
                        spPtlCompTaAnn.setCompTaAnnDiaryEntries(compTaAnnDiaryEntry);
                    }
                }
            }
        }
        catch (SepeRestException ex){
            //spPtlCompTaAnnRepo.delete(spPtlCompTaAnn);
            throw new SepeRestException(ex.getMessage());
        }

        try {
            SpPtlCompTaAnnTaEntryDTO[] spPtlCompTaAnnTaEntry = spPtlCompTaAnn.getTaEntries();
            if (null != spPtlCompTaAnnTaEntry) {
                boolean taConficts = false;
                String conflicts = "Οι τεχνικοί ασφαλείας που επαναλαμβάνονται: ";
                try {
                    if (spPtlCompTaAnn.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode()){
                        for (int i = 0; i < spPtlCompTaAnnTaEntry.length; i++) {
                            for (int j = i + 1; j < spPtlCompTaAnnTaEntry.length; j++) {
                                if (spPtlCompTaAnnTaEntry[i].getTechnicianRegrequestUserId().equals(spPtlCompTaAnnTaEntry[j].getTechnicianRegrequestUserId()) && spPtlCompTaAnnTaEntry[i].getCooperationType().equals(spPtlCompTaAnnTaEntry[j].getCooperationType())) {
                                    taConficts = true;
                                }
                            }
                        }
                    }
                } catch (Exception ex) {
                    throw new SepeRestException(":ERR:Δεν έγινε σωστή αποθήκευση των τεχνικών. Προσπαθήστε ξανά.");
                }
                int countExypps=0;
                try {
                    for (int i = 0; i < spPtlCompTaAnnTaEntry.length; i++) {
                        if(spPtlCompTaAnnTaEntry[i].getCooperationType()==3){
                            countExypps++;
                        }
                    }
                } catch (Exception ex) {
                    throw new SepeRestException(":ERR:Δεν έγινε σωστή αποθήκευση των τεχνικών. Προσπαθήστε ξανά.");
                }

                if (countExypps > 1) {
                    throw new SepeRestException(":ERR:Μπορείτε να έχετε μόνο μια ΕΞΥΠΠ.");
                } else if (taConficts ==true) {
                    throw new SepeRestException(":ERR:Επαναλαμβάνεται κάποιος τεχνικός.");
                }  else {
                    HashSet<SpPtlCompTaAnnTaEntry> compTaAnnTaEntry = new HashSet<SpPtlCompTaAnnTaEntry>();
                    List<SpPtlCompTaAnnTaEntry> ieAnnTaEntryList = new ArrayList<>();
                    ieAnnTaEntryList.addAll(spPtlCompTaAnn.getCompTaAnnTaEntries());
                    SpPtlCompTaAnnTaEntry taAnnTaEntry;
                    for (int i = 0; i < spPtlCompTaAnnTaEntry.length; i++) {

                        if (i < ieAnnTaEntryList.size())
                            taAnnTaEntry = ieAnnTaEntryList.get(i);
                        else
                            taAnnTaEntry = new SpPtlCompTaAnnTaEntry();

                        if(spPtlCompTaAnnTaEntry[i].getCooperationType()!=3){
                            if (spPtlCompTaAnn.getSubStatus() == 1 && (spPtlCompTaAnnTaEntry[i].getTaAfm().equals("") || spPtlCompTaAnnTaEntry[i].getTaAfm() == null))
                                continue;
                            List<SpPtlVTechnicianRegrequest> technicianRequests = spPtlVTechnicianRegrequestRepo.findByAfm(spPtlCompTaAnnTaEntry[i].getTaAfm());
                            if (technicianRequests.size() == 0) {
                                throw new SepeRestException(":ERR:Ο τεχνικός με το συγκεκριμένο ΑΦΜ δεν είναι καταχωρημένος στο μητρώο.");
                            }
                            //SpPtlTechnicianRegrequest technician = technicianRequests.get(0);
                            //spPtlCompTaAnn.setTechnicianRegrequestUserId(technician.getUserId());
                        }
                        else{
                            List<SpPtlVRgVwExypp> technicianRequests = spPtlVRgVwExyppRepo.findByAfm(spPtlCompTaAnnTaEntry[i].getTaAfm());
                            if (technicianRequests.size() == 0) {
                                throw new SepeRestException(":ERR:Η ΕΞΥΠΠ με το συγκεκριμένο ΑΦΜ δεν είναι καταχωρημένη στο μητρώο.");
                            }
                            //SpPtlVRgVwExypp technician = technicianRequests.get(0);
                            //spPtlCompTaAnn.setTechnicianRegrequestUserId(technician.getId());
                        }

                        taAnnTaEntry.setCompanyId(spPtlCompTaAnn.getCompanyId());
                        taAnnTaEntry.setCompTaAnn(spPtlCompTaAnn);
                        taAnnTaEntry.setCooperationType(spPtlCompTaAnnTaEntry[i].getCooperationType());
                        taAnnTaEntry.setTaAfm(spPtlCompTaAnnTaEntry[i].getTaAfm());
                        taAnnTaEntry.setTaFullname(spPtlCompTaAnnTaEntry[i].getTaFullname());
                        if (spPtlCompTaAnnTaEntry[i].getTaSpeciality().contains("[")) {
                            spPtlCompTaAnnTaEntry[i].setTaSpeciality(spPtlCompTaAnnTaEntry[i].getTaSpeciality().replace("[", "").replace("]", ""));
                        }
                        taAnnTaEntry.setTaSpeciality(spPtlCompTaAnnTaEntry[i].getTaSpeciality());
                        taAnnTaEntry.setTaSpecialityOther(spPtlCompTaAnnTaEntry[i].getTaSpecialityOther());
                        taAnnTaEntry.setTechnicianRegrequestId(spPtlCompTaAnnTaEntry[i].getTechnicianRegrequestId());
                        taAnnTaEntry.setTechnicianRegrequestUserId(spPtlCompTaAnnTaEntry[i].getTechnicianRegrequestUserId());
                        taAnnTaEntry.setTaAnnTaStatus(spPtlCompTaAnnTaEntry[i].getTaAnnTaStatus());

                        compTaAnnTaEntry.add(taAnnTaEntry);
                    }
                    spPtlCompTaAnn.setCompTaAnnTaEntries(compTaAnnTaEntry);
                }
            }
        }
        catch (SepeRestException ex){
            //spPtlCompTaAnnRepo.delete(spPtlCompTaAnn);
            throw new SepeRestException(ex.getMessage());
        }

        String date2 = spPtlCompTaAnn.getDateStart().toString().split(" ")[0];
        String dateIn2 = date2.split("-")[2]+"/"+date2.split("-")[1]+"/"+date2.split("-")[0];
        String date3 = spPtlCompTaAnn.getDateEnd().toString().split(" ")[0];
        String dateIn3 = date3.split("-")[2]+"/"+date3.split("-")[1]+"/"+date3.split("-")[0];
        Long countActiveAnns = spPtlCompTaAnnRepo.countTaAnn(spPtlCompTaAnn.getCompPtlBranchId(), dateIn2, dateIn3);
        if(countActiveAnns > 0) {
            if (countActiveAnns > 1) {
                throw new SepeRestException(":ERR:Οι ημερομηνίες που επιλέξατε επικαλύπτονται με ημερομηνίες μιας ήδη ενεργής αναγγελίας.");
            }
        }

        spPtlCompTaAnn.setTaAnnTaTotal(spPtlCompTaAnn.getTaEntries().length);
        spPtlCompTaAnn.setTaAnnTaTotalAccept(0);
        spPtlCompTaAnn.setTaAnnTaTotalReject(0);

        completeCompanyFields(spPtlCompTaAnn);
        spPtlCompTaAnnRepo.save(spPtlCompTaAnn);
    }

    @HandleAfterSave
    public void handleAfterSave(SpPtlCompTaAnn spPtlCompTaAnn) throws Exception {
        LOGGER.debug("Handling SpPtlCompTaAnn for: User ID " + CurrentUserDetailsService.getCurrentUser().getUsername());

        if (spPtlCompTaAnn.getCompTaAnnPrevId() != null) {

            SpPtlCompTaAnn previousAnn = spPtlCompTaAnnRepo.taFindOne(spPtlCompTaAnn.getCompTaAnnPrevId());

            if (previousAnn.getCompTaAnnTaEntries().size()>0) {

                SpPtlCompPtlBranch ptlBranch = spPtlCompPtlBranchRepo.findById(previousAnn.getCompPtlBranchId());
                String announcementIdentifier = new String(ptlBranch.getBrDescr() + " " + ptlBranch.getBrAddr() + " TK " + ptlBranch.getBrAddrTk());

                Set<SpPtlCompTaAnnTaEntry> spPtlCompTaAnnTaEntry2 = previousAnn.getCompTaAnnTaEntries();
                Iterator<SpPtlCompTaAnnTaEntry> interateTas = spPtlCompTaAnnTaEntry2.iterator();
                while(interateTas.hasNext()){
                    SpPtlCompTaAnnTaEntry next = interateTas.next();
                    TNotificationsAccountEntity notification = new TNotificationsAccountEntity();
                    notification.setDocId(new Long(-1));
                    notification.setTitle("Παύση εργασίας");
                    notification.setMessage("Έγινε παύση εργασίας σας από το υποκατάστημα "+ announcementIdentifier + " της εταιρείας " + previousAnn.getCompFullName() + " με αριθμό πρωτοκόλλου: " + previousAnn.getProtNoPause() + ". Για περισσότερες λεπτομέρειες πλοηγηθείτε στο μενού αριστερά με την ένδειξη 'Εκκρεμούσες αναγγελίες'.");
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

                        mailService.sendNotification(user.getEmail(), user.getEmailNotifEn(), "Παύση εργασίας", "Έγινε παύση εργασίας σας από το υποκατάστημα "+ announcementIdentifier + " της εταιρείας " + previousAnn.getCompFullName() + " με αριθμό πρωτοκόλλου: " + previousAnn.getProtNoPause() + ". Για περισσότερες λεπτομέρειες πλοηγηθείτε στο μενού αριστερά με την ένδειξη 'Εκκρεμούσες αναγγελίες'.");
                    } else {
                        TCompany safetyPreviousTechnician = companyRepository.findByUserId(next.getTechnicianRegrequestUserId());
                        notification.setAccountId(safetyPreviousTechnician.getUserId());

                        notificationsRestRepository.save(notification);
                        TUser user = userRepository.findOne(safetyPreviousTechnician.getUserId());
                        mailService.sendNotification(user.getEmail(), user.getEmailNotifEn(), "Παύση εργασίας", "Έγινε παύση εργασίας σας από το υποκατάστημα "+ announcementIdentifier + " της εταιρείας " + previousAnn.getCompFullName() + " με αριθμό πρωτοκόλλου: " + previousAnn.getProtNoPause() + ". Για περισσότερες λεπτομέρειες πλοηγηθείτε στο μενού αριστερά με την ένδειξη 'Εκκρεμούσες αναγγελίες'.");
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
        }
    }

    @HandleBeforeCreate
    public void handleBeforeCreates(SpPtlCompTaAnn spPtlCompTaAnn) throws SepeRestException {
        LOGGER.debug("Handling SpPtlCompTaAnn for: Company ID " + CurrentUserDetailsService.getCurrentUser().getUsername());

        SpringUserDetails currentUser = CurrentUserDetailsService.getCurrentUser();
        UserDTO userDTO = CurrentUserDetailsService.getCurrentUserByPrincipal();
        if (userDTO.getRole() == Constants.USER_TYPE.COMPANY_USER.getCode()) {
            TCompanyUserPrivilages priv = companyUserPrivilagesRepository.findByUser(userDTO.getId());
            ArrayList<Integer> list = new ArrayList(Arrays.asList(priv.getPrivilages().split("\\s*,\\s*")));
            if (null != priv.getPrivilages() && list.contains(String.valueOf(Constants.COMPANY_SERVICE.ASSIGN_NOTIFICATION.getCode()))) {
                Long companyId = priv.getCompId();
                spPtlCompTaAnn.setUserId(priv.getUserId());
                spPtlCompTaAnn.setCompanyId(companyId);
            } else {
                throw new SepeRestException(":ERR:Ο χρήστης δεν έχει εξουσιοδότηση σε αυτό το είδος αίτησης.");
            }
        } else {
            spPtlCompTaAnn.setCompanyId(currentUser.getUserId());
        }

        String date2 = spPtlCompTaAnn.getDateStart().toString().split(" ")[0];
        String dateIn2=date2.split("-")[2]+"/"+date2.split("-")[1]+"/"+date2.split("-")[0];
        String date3 = spPtlCompTaAnn.getDateEnd().toString().split(" ")[0];
        String dateIn3=date3.split("-")[2]+"/"+date3.split("-")[1]+"/"+date3.split("-")[0];
        Long countActiveAnns = spPtlCompTaAnnRepo.countTaAnn(spPtlCompTaAnn.getCompPtlBranchId(), dateIn2, dateIn3);
        if(countActiveAnns > 0) {
            if (null != spPtlCompTaAnn.getCompTaAnnPrevId() && countActiveAnns > 1) {
                throw new SepeRestException(":ERR:Οι ημερομηνίες που επιλέξατε επικαλύπτονται με ημερομηνίες μιας ήδη ενεργής αναγγελίας.");
            }
        }

        //PERIPTWSI ANTIKATASTASIS
        if (null != spPtlCompTaAnn.getCompTaAnnPrevId()) {

            //Company should select another technician to replace the old one
            SpPtlCompTaAnn previousAnn = spPtlCompTaAnnRepo.findOne(spPtlCompTaAnn.getCompTaAnnPrevId());
           /* if (previousAnn.getTechnicianRegrequestUserId().equals(spPtlCompTaAnn.getTechnicianRegrequestUserId()) &&
                    previousAnn.getTaAnnStatus() != Constants.COMP_IE_TA_ANN_STATUS.EXPIRED.getCode()) {
                throw new SepeRestException("Πρέπει να επιλέξετε έναν άλλο τεχνικό εργασίας για αυτές τις ημερομηνίες.");
            }*/

            Date todayDate = new Date();
            Timestamp today = new Timestamp(todayDate.getTime());
            previousAnn.setDateEnd(today);
            previousAnn.setTaAnnStatus(Constants.COMP_IE_TA_ANN_STATUS.EXPIRED.getCode());
            previousAnn.setReqStatus(Constants.REQUEST_STATUS.REJECTED.getCode());
            spPtlCompTaAnnRepo.save(previousAnn);
        }

        if(spPtlCompTaAnn.getCooperationTypeBasic() == null || spPtlCompTaAnn.getCooperationTypeBasic()!=3)
            spPtlCompTaAnn.setExyppBasic(null);

        try {
            SpPtlCompTaAnnTaEntryDTO[] spPtlCompTaAnnTaEntry = spPtlCompTaAnn.getTaEntries();
            if (null != spPtlCompTaAnnTaEntry) {
                boolean taConficts = false;
                String conflicts = "Οι τεχνικοί ασφαλείας που επαναλαμβάνονται: ";
                try {
                    for (int i = 0; i < spPtlCompTaAnnTaEntry.length; i++) {
                        for (int j = i+1; j < spPtlCompTaAnnTaEntry.length; j++) {
                            if (spPtlCompTaAnnTaEntry[i].getTechnicianRegrequestUserId() != null && spPtlCompTaAnnTaEntry[j].getTechnicianRegrequestUserId() != null) {
                                if (spPtlCompTaAnnTaEntry[i].getTechnicianRegrequestUserId().equals(spPtlCompTaAnnTaEntry[j].getTechnicianRegrequestUserId()) && spPtlCompTaAnnTaEntry[i].getCooperationType().equals(spPtlCompTaAnnTaEntry[j].getCooperationType())) {
                                    taConficts = true;
                                }
                            }
                        }
                    }
                } catch (Exception ex) {
                    throw new SepeRestException(":ERR:Δεν έγινε σωστή αποθήκευση των τεχνικών. Προσπαθήστε ξανά.");
                }
                int countExypps=0;
                try {
                    if (spPtlCompTaAnn.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode()) {
                        for (int i = 0; i < spPtlCompTaAnnTaEntry.length; i++) {
                            if (spPtlCompTaAnnTaEntry[i].getCooperationType() == 3) {
                                countExypps++;
                            }
                        }
                    }
                } catch (Exception ex) {
                    throw new SepeRestException(":ERR:Δεν έγινε σωστή αποθήκευση των τεχνικών. Προσπαθήστε ξανά.");
                }

                if (countExypps > 1) {
                    throw new SepeRestException(":ERR:Μπορείτε να έχετε μόνο μια ΕΞΥΠΠ.");
                } else if (taConficts ==true) {
                    throw new SepeRestException(":ERR:Επαναλαμβάνεται κάποιος τεχνικός.");
                } else {
                    HashSet<SpPtlCompTaAnnTaEntry> compTaAnnTaEntry = new HashSet<SpPtlCompTaAnnTaEntry>();
                    List<SpPtlCompTaAnnTaEntry> ieAnnTaEntryList = new ArrayList<>();
                    ieAnnTaEntryList.addAll(spPtlCompTaAnn.getCompTaAnnTaEntries());
                    SpPtlCompTaAnnTaEntry taAnnTaEntry;
                    for (int i = 0; i < spPtlCompTaAnnTaEntry.length; i++) {

                        if (i < ieAnnTaEntryList.size())
                            taAnnTaEntry = ieAnnTaEntryList.get(i);
                        else
                            taAnnTaEntry = new SpPtlCompTaAnnTaEntry();

                        if(spPtlCompTaAnnTaEntry[i].getCooperationType()!=3){
                            if (spPtlCompTaAnnTaEntry[i].getTaAfm() == "" || spPtlCompTaAnnTaEntry[i].getTaAfm() == null)
                                continue;
                            List<SpPtlVTechnicianRegrequest> technicianRequests = spPtlVTechnicianRegrequestRepo.findByAfm(spPtlCompTaAnnTaEntry[i].getTaAfm());
                            if (technicianRequests.size() == 0) {
                                throw new SepeRestException(":ERR:Ο τεχνικός με το συγκεκριμένο ΑΦΜ δεν είναι καταχωρημένος στο μητρώο.");
                            }
                            //SpPtlTechnicianRegrequest technician = technicianRequests.get(0);
                            //spPtlCompTaAnn.setTechnicianRegrequestUserId(technician.getUserId());
                        }
                        else{
                            List<SpPtlVRgVwExypp> technicianRequests = spPtlVRgVwExyppRepo.findByAfm(spPtlCompTaAnnTaEntry[i].getTaAfm());
                            if (technicianRequests.size() == 0) {
                                throw new SepeRestException(":ERR:Η ΕΞΥΠΠ με το συγκεκριμένο ΑΦΜ δεν είναι καταχωρημένη στο μητρώο.");
                            }
                            //SpPtlVRgVwExypp technician = technicianRequests.get(0);
                            //spPtlCompTaAnn.setTechnicianRegrequestUserId(technician.getId());
                        }

                        taAnnTaEntry.setCompanyId(spPtlCompTaAnn.getCompanyId());
                        taAnnTaEntry.setCompTaAnn(spPtlCompTaAnn);
                        taAnnTaEntry.setCooperationType(spPtlCompTaAnnTaEntry[i].getCooperationType());
                        taAnnTaEntry.setTaAfm(spPtlCompTaAnnTaEntry[i].getTaAfm());
                        taAnnTaEntry.setTaFullname(spPtlCompTaAnnTaEntry[i].getTaFullname());
                        taAnnTaEntry.setTaSpeciality(spPtlCompTaAnnTaEntry[i].getTaSpeciality());
                        taAnnTaEntry.setTaSpecialityOther(spPtlCompTaAnnTaEntry[i].getTaSpecialityOther());
                        taAnnTaEntry.setTechnicianRegrequestId(spPtlCompTaAnnTaEntry[i].getTechnicianRegrequestId());
                        taAnnTaEntry.setTechnicianRegrequestUserId(spPtlCompTaAnnTaEntry[i].getTechnicianRegrequestUserId());
                        taAnnTaEntry.setTaAnnTaStatus(spPtlCompTaAnnTaEntry[i].getTaAnnTaStatus());

                        compTaAnnTaEntry.add(taAnnTaEntry);
                    }
                    spPtlCompTaAnn.setCompTaAnnTaEntries(compTaAnnTaEntry);
                }
            }
        }
        catch (SepeRestException ex){
            spPtlCompTaAnnRepo.delete(spPtlCompTaAnn);
            throw new SepeRestException(ex.getMessage());
        }

        try {
            SpPtlCompTaAnnDiaryEntryDTO[] spPtlCompTaAnnDiaryEntry = spPtlCompTaAnn.getDiaryEntries();
            if (null != spPtlCompTaAnnDiaryEntry) {
                boolean datesConficts = false;
                String conflicts = "Οι ημερομηνίες που επικαλύπτονται: ";
                StringBuilder conflictsConcat = new StringBuilder();
                try {
                    SpPtlCompTaAnnTaEntryDTO[] spPtlCompTaAnnTaEntry = spPtlCompTaAnn.getTaEntries();
                    if (spPtlCompTaAnn.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode()) {

                        int daySec = 1 * 24 * 60 * 60 * 1000;
                        Long dateStart = new Date(spPtlCompTaAnn.getDateStart().getTime()).getTime()/1000 - daySec;
                        Long dateEnd = new Date(spPtlCompTaAnn.getDateEnd().getTime()).getTime()/1000 + daySec;
                        /*List<Long> ieVisitConflictQueryTechnicianRegrequestUserId = spPtlVCompTaAnnDiaryBRepo.taVisitConflict1(dateStart, dateEnd);
                        List<Integer> ieVisitConflictQueryVisitDurationMinutes = spPtlVCompTaAnnDiaryBRepo.taVisitConflict3(dateStart, dateEnd);
                        List<Date> ieVisitConflictQueryVisitDate = spPtlVCompTaAnnDiaryBRepo.taVisitConflict4(dateStart, dateEnd);
                        List<String> ieVisitConflictQueryVisitTime = spPtlVCompTaAnnDiaryBRepo.taVisitConflict5(dateStart, dateEnd);*/
                        String dates = "";
                        int cnt = 0;
                        for (SpPtlCompTaAnnDiaryEntryDTO i : spPtlCompTaAnn.getDiaryEntries())
                        {
                            if (cnt == 0)
                                dates = dates + "'" + new SimpleDateFormat("dd-MM-yyyy").format(new Date(i.getVisitDate().getTime())) + "','" + new SimpleDateFormat("dd-MM-yyyy").format(new Date(i.getVisitDate().getTime() + daySec)) + "'";
                            else if (!dates.contains(new SimpleDateFormat("dd-MM-yyyy").format(new Date(i.getVisitDate().getTime()))))
                                dates = dates + ",'" + new SimpleDateFormat("dd-MM-yyyy").format(new Date(i.getVisitDate().getTime())) + "','" + new SimpleDateFormat("dd-MM-yyyy").format(new Date(i.getVisitDate().getTime() + daySec)) + "'";
                            cnt++;
                        }
                        List<SpPtlVCompTaAnnDiaryB> ieVisitConflictQuery = null;
                        List<Long> techUserIds = new ArrayList<>();
                        Date date;
                        Long lVisitDate, lVisitTime, dateAndTime, durationMins, technicianRegrequestUserId, visitDateEpochSec, conflictVisitDate, conflictVisitTime;
                        Integer visitDurationMinutes;
                        int offset, conflictOffset;
                        for (int i = 0; i < spPtlCompTaAnnDiaryEntry.length; i++) {

                            if (!techUserIds.contains(spPtlCompTaAnnDiaryEntry[i].getCompTaAnnTaId()))
                            {
                                techUserIds.add(spPtlCompTaAnnDiaryEntry[i].getCompTaAnnTaId());
                                ieVisitConflictQuery = entityManager.createNativeQuery("SELECT * FROM SP_PTL_V_COMP_TA_ANN_DIARY_B WHERE TO_CHAR(VISIT_DATE, 'DD-MM-YYYY') IN ("+dates+") AND TECHNICIAN_REGREQUEST_USER_ID = "+spPtlCompTaAnnDiaryEntry[i].getCompTaAnnTaId()+" ORDER BY VISIT_DATE ASC", SpPtlVCompTaAnnDiaryB.class).getResultList();
                            }

                            if (techUserIds.contains(spPtlCompTaAnnDiaryEntry[i].getCompTaAnnTaId()) && techUserIds.indexOf(spPtlCompTaAnnDiaryEntry[i].getCompTaAnnTaId()) != techUserIds.size()-1)
                                ieVisitConflictQuery = entityManager.createNativeQuery("SELECT * FROM SP_PTL_V_COMP_TA_ANN_DIARY_B WHERE TO_CHAR(VISIT_DATE, 'DD-MM-YYYY') IN ("+dates+") AND TECHNICIAN_REGREQUEST_USER_ID = "+spPtlCompTaAnnDiaryEntry[i].getCompTaAnnTaId()+" ORDER BY VISIT_DATE ASC", SpPtlVCompTaAnnDiaryB.class).getResultList();

                            date = new Date(spPtlCompTaAnnDiaryEntry[i].getVisitDate().getTime());
                            offset = -date.getTimezoneOffset()/60;
                            lVisitDate = date.getTime() / 1000;
                            lVisitTime = Time.valueOf(spPtlCompTaAnnDiaryEntry[i].getVisitTime() + ":00").getTime() / 1000;
                            if (offset == 3)
                                dateAndTime = lVisitDate + lVisitTime - 3600; //remove 1 hour
                            else
                                dateAndTime = lVisitDate + lVisitTime;
                            durationMins = new Long(spPtlCompTaAnnDiaryEntry[i].getVisitDurationMinutes());

                            for (int j = 0; j < ieVisitConflictQuery.size(); j++)
                            {
                                technicianRegrequestUserId = ieVisitConflictQuery.get(j).getTechnicianRegrequestUserId();

                                conflictVisitDate = (long)Math.round((ieVisitConflictQuery.get(j).getVisitDate().getTime() / 1000) / 86400) * 86400;
                                conflictVisitTime = Time.valueOf(ieVisitConflictQuery.get(j).getVisitTime() + ":00").getTime() / 1000;
                                conflictOffset = conflictVisitDate.equals(lVisitDate) ? offset : -ieVisitConflictQuery.get(j).getVisitDate().getTimezoneOffset() / 60;
                                if (conflictOffset == 3)
                                    visitDateEpochSec = conflictVisitDate + conflictVisitTime - 3600; //remove 1 hour
                                else
                                    visitDateEpochSec = conflictVisitDate + conflictVisitTime;

                                visitDurationMinutes = ieVisitConflictQuery.get(j).getVisitDurationMinutes();

                                if (technicianRegrequestUserId == (long)spPtlCompTaAnnDiaryEntry[i].getCompTaAnnTaId() && ((dateAndTime <= visitDateEpochSec && (dateAndTime + (durationMins*60)) > visitDateEpochSec) ||
                                        (dateAndTime >= visitDateEpochSec && (dateAndTime + (durationMins*60)) <= (visitDateEpochSec + visitDurationMinutes*60)) ||
                                        (dateAndTime < (visitDateEpochSec + visitDurationMinutes*60) && (dateAndTime + (durationMins*60)) >= (visitDateEpochSec + visitDurationMinutes*60))))
                                {
                                    datesConficts = true;
                                    conflictsConcat.append(i);
                                    conflictsConcat.append(" ");
                                    break;
                                }
                            }
                        }
                        conflicts += conflictsConcat.toString();
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
                        SpPtlCompTaAnnTaEntryDTO[] spPtlCompTaAnnTaEntry = spPtlCompTaAnn.getTaEntries();
                        if (spPtlCompTaAnn.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode()) {
                            for (int i = 0; i < spPtlCompTaAnnDiaryEntry.length; i++) {
                                Date date = new Date(spPtlCompTaAnnDiaryEntry[i].getVisitDate().getTime());
                                Long lVisitDate = date.getTime();
                                Long lVisitTime = Time.valueOf(spPtlCompTaAnnDiaryEntry[i].getVisitTime() + ":00").getTime();
                                offset = -date.getTimezoneOffset()/60;
                                if (offset == 3)
                                    dateAndTime = lVisitDate + lVisitTime - (3600 * 1000); //remove 1 hour
                                else
                                    dateAndTime = lVisitDate + lVisitTime;
                                Date dateOne = new Date(dateAndTime);
                                Date dateOneEnd = new Date(dateAndTime + spPtlCompTaAnnDiaryEntry[i].getVisitDurationMinutes() * 60000);
                                Long compTaAnnTaAnnIdOne = spPtlCompTaAnnDiaryEntry[i].getCompTaAnnTaId();
                                for (int ii = 0; ii < spPtlCompTaAnnDiaryEntry.length; ii++) {
                                    if (i != ii) {
                                        Date dateN2 = new Date(spPtlCompTaAnnDiaryEntry[ii].getVisitDate().getTime());
                                        Long lVisitDate2 = dateN2.getTime();
                                        Long lVisitTime2 = Time.valueOf(spPtlCompTaAnnDiaryEntry[ii].getVisitTime() + ":00").getTime();
                                        offset2 = -dateN2.getTimezoneOffset()/60;
                                        if (offset2 == 3)
                                            dateAndTime2 = lVisitDate2 + lVisitTime2 - (3600 * 1000); //remove 1 hour
                                        else
                                            dateAndTime2 = lVisitDate2 + lVisitTime2;
                                        Date dateTwo = new Date(dateAndTime2);
                                        Date dateTwoEnd = new Date(dateAndTime2 + spPtlCompTaAnnDiaryEntry[ii].getVisitDurationMinutes() * 60000);
                                        Long compTaAnnTaAnnIdTwo = spPtlCompTaAnnDiaryEntry[ii].getCompTaAnnTaId();

                                        if (dateOne.before(dateTwoEnd) && dateTwo.before(dateOneEnd) && compTaAnnTaAnnIdOne.equals(compTaAnnTaAnnIdTwo)) {
                                            datesConfictsCurrent = true;
                                        }
                                    }
                                }

                            }
                        }
                    } catch (Exception ex) {
                        throw new SepeRestException(":ERR:Υπάρχει επικάλυψη στο ωράριο που δηλώσατε.");
                    }


                    if (datesConfictsCurrent == true) {
                        throw new SepeRestException(":ERR:Υπάρχει επικάλυψη στο ωράριο που δηλώσατε εντός ίδιας μέρας.");
                    } else {
                        HashSet<SpPtlCompTaAnnDiaryEntry> compTaAnnDiaryEntry = new HashSet<SpPtlCompTaAnnDiaryEntry>();
                        List<SpPtlCompTaAnnDiaryEntry> ieAnnDiaryEntryList = new ArrayList<>();
                        ieAnnDiaryEntryList.addAll(spPtlCompTaAnn.getCompTaAnnDiaryEntries());
                        SpPtlCompTaAnnDiaryEntry ieAnnDiaryEntry;
                        for (int i = 0; i < spPtlCompTaAnnDiaryEntry.length; i++) {
                            if (i < ieAnnDiaryEntryList.size())
                                ieAnnDiaryEntry = ieAnnDiaryEntryList.get(i);
                            else
                                ieAnnDiaryEntry = new SpPtlCompTaAnnDiaryEntry();
                            ieAnnDiaryEntry.setCompanyId(spPtlCompTaAnn.getCompanyId());
                            ieAnnDiaryEntry.setCompTaAnn(spPtlCompTaAnn);
                            ieAnnDiaryEntry.setVisitTime(spPtlCompTaAnnDiaryEntry[i].getVisitTime());
                            ieAnnDiaryEntry.setVisitDurationMinutes(spPtlCompTaAnnDiaryEntry[i].getVisitDurationMinutes());
                            ieAnnDiaryEntry.setVisitDate(spPtlCompTaAnnDiaryEntry[i].getVisitDate());
                            ieAnnDiaryEntry.setCompTaAnnTaId(spPtlCompTaAnnDiaryEntry[i].getCompTaAnnTaId());
                            if (spPtlCompTaAnn.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode() && (spPtlCompTaAnn.getReqStatus() == Constants.REQUEST_STATUS.PENDING.getCode() || spPtlCompTaAnn.getReqStatus() == Constants.REQUEST_STATUS.ACCEPTED.getCode()))
                                ieAnnDiaryEntry.setIsSubmitted(2);
                            else
                                ieAnnDiaryEntry.setIsSubmitted(1);
                            compTaAnnDiaryEntry.add(ieAnnDiaryEntry);
                        }
                        spPtlCompTaAnn.setCompTaAnnDiaryEntries(compTaAnnDiaryEntry);
                    }
                }
            }
        }
        catch (SepeRestException ex){
            spPtlCompTaAnnRepo.delete(spPtlCompTaAnn);
            throw new SepeRestException(ex.getMessage());
        }

        spPtlCompTaAnn.setTaAnnTaTotal(spPtlCompTaAnn.getTaEntries().length);
        spPtlCompTaAnn.setTaAnnTaTotalAccept(0);
        spPtlCompTaAnn.setTaAnnTaTotalReject(0);

        completeCompanyFields(spPtlCompTaAnn);
        spPtlCompTaAnnRepo.save(spPtlCompTaAnn);
    }

    @HandleAfterCreate
    public void handleAfterCreates(SpPtlCompTaAnn spPtlCompTaAnn) throws Exception {
        LOGGER.debug("Handling SpPtlCompTaAnn for: User ID " + CurrentUserDetailsService.getCurrentUser().getUsername());

        if(spPtlCompTaAnn.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode()) {
            if (spPtlCompTaAnn.getCompTaAnnPrevId() != null) {

                SpPtlCompTaAnn previousAnn = spPtlCompTaAnnRepo.taFindOne(spPtlCompTaAnn.getCompTaAnnPrevId());

                if (previousAnn.getCompTaAnnTaEntries().size()>0) {

                    SpPtlCompPtlBranch ptlBranch = spPtlCompPtlBranchRepo.findById(previousAnn.getCompPtlBranchId());
                    String announcementIdentifier = new String(ptlBranch.getBrDescr() + " " + ptlBranch.getBrAddr() + " TK " + ptlBranch.getBrAddrTk());

                    Set<SpPtlCompTaAnnTaEntry> spPtlCompTaAnnTaEntry2 = previousAnn.getCompTaAnnTaEntries();
                    Iterator<SpPtlCompTaAnnTaEntry> interateTas = spPtlCompTaAnnTaEntry2.iterator();
                    while(interateTas.hasNext()){
                        SpPtlCompTaAnnTaEntry next = interateTas.next();
                        TNotificationsAccountEntity notification = new TNotificationsAccountEntity();
                        notification.setDocId(new Long(-1));
                        notification.setTitle("Παύση εργασίας");
                        notification.setMessage("Έγινε παύση εργασίας σας από το υποκατάστημα "+ announcementIdentifier + " της εταιρείας " + previousAnn.getCompFullName() + " με αριθμό πρωτοκόλλου: " + previousAnn.getProtNoPause() + ". Για περισσότερες λεπτομέρειες πλοηγηθείτε στο μενού αριστερά με την ένδειξη 'Εκκρεμούσες αναγγελίες'.");
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
                            mailService.sendNotification(user.getEmail(), user.getEmailNotifEn(), "Παύση εργασίας", "Έγινε παύση εργασίας σας από το υποκατάστημα "+ announcementIdentifier + " της εταιρείας " + previousAnn.getCompFullName() + " με αριθμό πρωτοκόλλου: " + previousAnn.getProtNoPause() + ". Για περισσότερες λεπτομέρειες πλοηγηθείτε στο μενού αριστερά με την ένδειξη 'Εκκρεμούσες αναγγελίες'.");
                        } else {
                            TCompany safetyPreviousTechnician = companyRepository.findByUserId(next.getTechnicianRegrequestUserId());
                            notification.setAccountId(safetyPreviousTechnician.getUserId());

                            notificationsRestRepository.save(notification);
                            TUser user = userRepository.findOne(safetyPreviousTechnician.getUserId());
                            mailService.sendNotification(user.getEmail(), user.getEmailNotifEn(), "Παύση εργασίας", "Έγινε παύση εργασίας σας από το υποκατάστημα "+ announcementIdentifier + " της εταιρείας " + previousAnn.getCompFullName() + " με αριθμό πρωτοκόλλου: " + previousAnn.getProtNoPause() + ". Για περισσότερες λεπτομέρειες πλοηγηθείτε στο μενού αριστερά με την ένδειξη 'Εκκρεμούσες αναγγελίες'.");
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
        }
    }

    public void completeCompanyFields(SpPtlCompTaAnn spPtlCompTaAnn) {
        TCompany company = companyRepository.findOne(spPtlCompTaAnn.getCompanyId());

        //get company missing fields from MainBranchIKA
        //TEmployerBranchIKA ika = ikaRepo.findCompanyMainBranchByAme(company.getAme());
        TEmployerBranchIKA ika = ikaRepo.findCompanyMainBranchByAfmAndAme(company.getAfm(),company.getAme());

        spPtlCompTaAnn.setCompAme(company.getAme());

        if (Constants.StringIsNullOrEmpty(spPtlCompTaAnn.getCompTaxNumber())) {
            if (Constants.StringIsNullOrEmpty(ika.getRgEbrTaxationNumber()))
                spPtlCompTaAnn.setCompTaxNumber("");
            else
                spPtlCompTaAnn.setCompTaxNumber(ika.getRgEbrTaxationNumber());
        }

        if (Constants.StringIsNullOrEmpty(spPtlCompTaAnn.getCompFullName())) {
            if (Constants.StringIsNullOrEmpty(ika.getRgEmpFullname()))
                spPtlCompTaAnn.setCompFullName("");
            else
                spPtlCompTaAnn.setCompFullName(ika.getRgEmpFullname());
        }

        if (Constants.StringIsNullOrEmpty(spPtlCompTaAnn.getCompPhone())) {
            if (Constants.StringIsNullOrEmpty(ika.getRgEbrPhoneNumber()))
                spPtlCompTaAnn.setCompPhone("");
            else {
                if (ika.getRgEbrPhoneNumber().contains("-"))
                    ika.setRgEbrPhoneNumber(ika.getRgEbrPhoneNumber().replace("-", ""));

                spPtlCompTaAnn.setCompPhone(ika.getRgEbrPhoneNumber());
            }
        }

        if (Constants.StringIsNullOrEmpty(spPtlCompTaAnn.getCompAddr())) {
            if (Constants.StringIsNullOrEmpty(ika.getRgEbrAddressStreet()))
                spPtlCompTaAnn.setCompAddr("");
            else
                spPtlCompTaAnn.setCompAddr(ika.getRgEbrAddressStreet());
        }

        if (Constants.StringIsNullOrEmpty(spPtlCompTaAnn.getCompAddrTk())) {
            if (Constants.StringIsNullOrEmpty(ika.getRgEbrZipCode()))
                spPtlCompTaAnn.setCompAddrTk("");
            else
                spPtlCompTaAnn.setCompAddrTk(ika.getRgEbrZipCode());
        }

        if (Constants.StringIsNullOrEmpty(spPtlCompTaAnn.getCompAddrP())) {
            spPtlCompTaAnn.setCompAddrP("");
        }
        if (Constants.StringIsNullOrEmpty(spPtlCompTaAnn.getCompAddrPe())) {
            spPtlCompTaAnn.setCompAddrPe("");
        }
        if (Constants.StringIsNullOrEmpty(spPtlCompTaAnn.getCompAddrD())) {
            spPtlCompTaAnn.setCompAddrD("");
        }
        if (Constants.StringIsNullOrEmpty(spPtlCompTaAnn.getCompAddrK())) {
            if (Constants.StringIsNullOrEmpty(ika.getRgEbrKallikratis()))
                spPtlCompTaAnn.setCompAddrK("");
            else
                spPtlCompTaAnn.setCompAddrK(ika.getRgEbrKallikratis());
        }
        if(Constants.LongIsNullOrEmpty(spPtlCompTaAnn.getCompEbrBranch0Id()))
        {
            spPtlCompTaAnn.setCompEbrBranchId(new Long(-1));
        }

        if(Constants.LongIsNullOrEmpty(spPtlCompTaAnn.getCompEbrBranchId()))
        {
            spPtlCompTaAnn.setCompEbrBranchId(new Long(-1));
        }

        entityManager.detach(ika);
    }
}