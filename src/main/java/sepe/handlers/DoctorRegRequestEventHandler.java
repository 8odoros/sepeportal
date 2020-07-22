package sepe.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeDelete;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Service;
import sepe.config.Constants;
import sepe.domain.doctor.SpPtlDoctorCounty;
import sepe.domain.doctor.SpPtlDoctorRegrequest;
import sepe.domain.doctor.TWorkplaceDoctor;
import sepe.dto.UserDTO;
import sepe.repository.doctor.SpPtlDoctorCountyRepo;
import sepe.repository.doctor.SpPtlDoctorRegrequestRepo;
import sepe.repository.doctor.WorkplaceDoctorRepository;
import sepe.service.CurrentUserDetailsService;
import sepe.service.WorkplaceDoctorStoredProcedures;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RepositoryEventHandler(SpPtlDoctorRegrequest.class)
public class DoctorRegRequestEventHandler {
    @Autowired
    private WorkplaceDoctorStoredProcedures workplaceDoctorStoredProcedures;

    @Autowired
    private SpPtlDoctorCountyRepo spPtlDoctorCountyRepo;

    @Autowired
    private SpPtlDoctorRegrequestRepo spPtlDoctorRegrequestRepo;

    @Autowired
    private WorkplaceDoctorRepository workplaceDoctorRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(DoctorRegRequestEventHandler.class);

    @HandleBeforeSave
    public void handleBeforeSave(SpPtlDoctorRegrequest spPtlDoctorRegrequest) throws Exception {
        LOGGER.debug("Handling DoctorRegrequest for: User ID " + CurrentUserDetailsService.getCurrentUser().getUsername());
        spPtlDoctorRegrequest.setUserId(CurrentUserDetailsService.getCurrentUser().getUserId());

        SpPtlDoctorRegrequest hasDisputeSubmitted = spPtlDoctorRegrequestRepo.findOne(spPtlDoctorRegrequest.getId());
        if(null != hasDisputeSubmitted && hasDisputeSubmitted.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode())
        {
            throw new Exception("To αίτημα σας έχει ήδη υποβληθεί.");
        }

        //saveDoctorCounties(spPtlDoctorRegrequest);
        if (spPtlDoctorRegrequest.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode())
            try {
                spPtlDoctorRegrequest.setSubStatus(Constants.SUBMIT_STATUS.SAVED.getCode());
                spPtlDoctorRegrequestRepo.save(spPtlDoctorRegrequest);
                if (isDoctorRegRequestValid(spPtlDoctorRegrequest)) {
                    spPtlDoctorRegrequest.setSubStatus(Constants.SUBMIT_STATUS.SUBMITTED.getCode());
                    (workplaceDoctorStoredProcedures).procIntArchiveIERegrequest(spPtlDoctorRegrequest);
                }
            } catch (Exception e) {
                e.printStackTrace();
                spPtlDoctorRegrequest.setSubStatus(Constants.SUBMIT_STATUS.SAVED.getCode());
                spPtlDoctorRegrequestRepo.save(spPtlDoctorRegrequest);
                throw new Exception("To αίτημα σας δεν υποβλήθηκε, έχει όμως αποθηκευθεί.");
            }
    }

    @HandleBeforeCreate
    public void handleBeforeCreates(SpPtlDoctorRegrequest spPtlDoctorRegrequest) throws Exception {
        LOGGER.debug("Handling DoctorRegrequest for: User ID " + CurrentUserDetailsService.getCurrentUser().getUsername());
        spPtlDoctorRegrequest.setUserId(CurrentUserDetailsService.getCurrentUser().getUserId());

        //saveDoctorCounties(spPtlDoctorRegrequest);
        if (spPtlDoctorRegrequest.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode())
            try {
                spPtlDoctorRegrequest.setSubStatus(Constants.SUBMIT_STATUS.SAVED.getCode());
                spPtlDoctorRegrequestRepo.save(spPtlDoctorRegrequest);
                if (isDoctorRegRequestValid(spPtlDoctorRegrequest))
                    spPtlDoctorRegrequest.setSubStatus(Constants.SUBMIT_STATUS.SUBMITTED.getCode());
                    (workplaceDoctorStoredProcedures).procIntArchiveIERegrequest(spPtlDoctorRegrequest);
            } catch (Exception e) {
                e.printStackTrace();
                spPtlDoctorRegrequest.setSubStatus(Constants.SUBMIT_STATUS.SAVED.getCode());
                spPtlDoctorRegrequestRepo.save(spPtlDoctorRegrequest);
                throw new Exception("To αίτημα σας δεν υποβλήθηκε, έχει όμως αποθηκευθεί.");
            }
    }

    @HandleBeforeDelete
    public void handleBeforeDeletes(SpPtlDoctorRegrequest spPtlDoctorRegrequest) throws Exception {
        if (spPtlDoctorRegrequest.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode()) {
            throw new Exception("To αίτημα σας δεν γίνεται να διαγραφεί επειδή είναι ήδη υποβεβλημένο.");
        }
    }

    public void saveDoctorCounties(SpPtlDoctorRegrequest spPtlDoctorRegrequest) {
        String str = spPtlDoctorRegrequest.getSpDifferentCounty();
        List<String> counties = new ArrayList<String>(Arrays.asList(str.split(",")));
        for (String county : counties) {
            Long countyID = Constants.StringIsNullOrEmpty(county) ? 1 : Long.parseLong(county);
            SpPtlDoctorCounty spPtlDoctorCounty = spPtlDoctorCountyRepo.findOne(countyID);
            spPtlDoctorRegrequest.getDoctorCounties().add(spPtlDoctorCounty);
        }
    }

    public boolean isDoctorRegRequestValid(SpPtlDoctorRegrequest spPtlDoctorRegrequest)
    {
        UserDTO user = CurrentUserDetailsService.getCurrentUser().getUserDTO();
        if(Constants.LongIsNullOrEmpty(spPtlDoctorRegrequest.getUserId()))
        {
            spPtlDoctorRegrequest.setUserId(user.getId());
        }

        if(Constants.StringIsNullOrEmpty(spPtlDoctorRegrequest.getLastname()))
        {
            if(Constants.StringIsNullOrEmpty(user.getLastname()))
                spPtlDoctorRegrequest.setLastname("");
            else
                spPtlDoctorRegrequest.setLastname(user.getLastname());
        }

        if(Constants.StringIsNullOrEmpty(spPtlDoctorRegrequest.getFirstname()))
        {
            if(Constants.StringIsNullOrEmpty(user.getFirstname()))
                spPtlDoctorRegrequest.setFirstname("");
            else
                spPtlDoctorRegrequest.setFirstname(user.getFirstname());
        }

        /*if(Constants.LongIsNullOrEmpty(spPtlDoctorRegrequest.getEducationLevel()))
        {
            spPtlDoctorRegrequest.setEducationLevel(new Long(-1));
        }*/

        final TWorkplaceDoctor workplaceDoctor = workplaceDoctorRepository.findByUserId(spPtlDoctorRegrequest.getUserId());
        if(Constants.StringIsNullOrEmpty(spPtlDoctorRegrequest.getAfm()))
        {
            if(Constants.StringIsNullOrEmpty(workplaceDoctor.getAfm()))
                spPtlDoctorRegrequest.setAfm("");
            else
                spPtlDoctorRegrequest.setAfm(workplaceDoctor.getAfm());
        }

        if(Constants.StringIsNullOrEmpty(spPtlDoctorRegrequest.getAmka()))
        {
            spPtlDoctorRegrequest.setAmka("");
        }

        if(Constants.StringIsNullOrEmpty(spPtlDoctorRegrequest.getCardNumber()))
        {
            if(Constants.StringIsNullOrEmpty(workplaceDoctor.getCardNumber()))
                spPtlDoctorRegrequest.setCardNumber("");
            else
                spPtlDoctorRegrequest.setCardNumber(workplaceDoctor.getCardNumber());
        }

        if(Constants.StringIsNullOrEmpty(spPtlDoctorRegrequest.getPhone()))
        {
            if(Constants.StringIsNullOrEmpty(user.getPhone()))
                spPtlDoctorRegrequest.setPhone("");
            else
                spPtlDoctorRegrequest.setPhone(user.getPhone());
        }

        if(Constants.StringIsNullOrEmpty(spPtlDoctorRegrequest.getDescr()))
        {
            spPtlDoctorRegrequest.setDescr("");
        }

        if(Constants.LongIsNullOrEmpty(spPtlDoctorRegrequest.getCardType()))
        {
            if(Constants.LongIsNullOrEmpty(workplaceDoctor.getCardType()))
                spPtlDoctorRegrequest.setCardType(new Long(-1));
            else
                spPtlDoctorRegrequest.setCardType(workplaceDoctor.getCardType());
        }

        if(Constants.LongIsNullOrEmpty(spPtlDoctorRegrequest.getAttachedDocId()))
        {
            spPtlDoctorRegrequest.setAttachedDocId(new Long(-1));
        }

        /*if(Constants.StringIsNullOrEmpty(spPtlDoctorRegrequest.getTeeNumber()))
        {
            spPtlDoctorRegrequest.setTeeNumber("");
        }*/

        if(Constants.StringIsNullOrEmpty(spPtlDoctorRegrequest.getFathername()))
        {
            spPtlDoctorRegrequest.setFathername("");
        }

        /*if(Constants.StringIsNullOrEmpty(spPtlDoctorRegrequest.getSpeciality()))
        {
            spPtlDoctorRegrequest.setSpeciality("");
        }*/

        if(Constants.StringIsNullOrEmpty(spPtlDoctorRegrequest.getEmail()))
        {
            if(Constants.StringIsNullOrEmpty(user.getEmail()))
                spPtlDoctorRegrequest.setEmail("");
            else
                spPtlDoctorRegrequest.setEmail(user.getEmail());
        }

        /*if(Constants.IntegerIsNullOrEmpty(spPtlDoctorRegrequest.getEducation100()))
        {
            spPtlDoctorRegrequest.setEducation100(-1);
        }*/

        /*if(Constants.LongIsNullOrEmpty(spPtlDoctorRegrequest.getAttachedDocIdEmplTraining()))
        {
            spPtlDoctorRegrequest.setAttachedDocIdEmplTraining(new Long(-1));
        }*/

        /*if(Constants.StringIsNullOrEmpty(spPtlDoctorRegrequest.getSpecialityOther()))
        {
            spPtlDoctorRegrequest.setSpecialityOther("");
        }*/

        if(Constants.StringIsNullOrEmpty(spPtlDoctorRegrequest.getFax()))
        {
            if(Constants.StringIsNullOrEmpty(user.getFax()))
                spPtlDoctorRegrequest.setFax("");
            else
                spPtlDoctorRegrequest.setFax(user.getFax());
        }

        /*if(Constants.LongIsNullOrEmpty(spPtlDoctorRegrequest.getAttachedDocIdDiploma()))
        {
            spPtlDoctorRegrequest.setAttachedDocIdDiploma(new Long(-1));
        }*/

        if(Constants.StringIsNullOrEmpty(spPtlDoctorRegrequest.getNotes()))
        {
            spPtlDoctorRegrequest.setNotes("");
        }

        if(Constants.IntegerIsNullOrEmpty(spPtlDoctorRegrequest.getSepeProfilStatus()))
        {
            spPtlDoctorRegrequest.setSepeProfilStatus(-1);
        }

        /*if(Constants.IntegerIsNullOrEmpty(spPtlDoctorRegrequest.getBelongsToPedy()))
        {
            spPtlDoctorRegrequest.setBelongsToPedy(-1);
        }*/

        /*if(Constants.LongIsNullOrEmpty(spPtlDoctorRegrequest.getAttachedDocIdPedyYes()))
        {
            spPtlDoctorRegrequest.setAttachedDocIdPedyYes(new Long(-1));
        }

        if(Constants.LongIsNullOrEmpty(spPtlDoctorRegrequest.getAttachedDocIdPedyNo()))
        {
            spPtlDoctorRegrequest.setAttachedDocIdPedyNo(new Long(-1));
        }*/

        /*if(Constants.LongIsNullOrEmpty(spPtlDoctorRegrequest.getCooperationType()))
        {
            spPtlDoctorRegrequest.setCooperationType(new Long(-1));
        }*/

        /*if(Constants.StringIsNullOrEmpty(spPtlDoctorRegrequest.getIeDocProtNo()))
        {
            spPtlDoctorRegrequest.setIeDocProtNo("");
        }

        if(Constants.TimestampIsNullOrEmpty(spPtlDoctorRegrequest.getIeDocDate()))
        {
            spPtlDoctorRegrequest.setIeDocDate(new Timestamp(0));
        }

        if(Constants.StringIsNullOrEmpty(spPtlDoctorRegrequest.getIeDocCompanyName()))
        {
            spPtlDoctorRegrequest.setIeDocCompanyName("");
        }

        if(Constants.StringIsNullOrEmpty(spPtlDoctorRegrequest.getIeDocDepartment()))
        {
            spPtlDoctorRegrequest.setIeDocDepartment("");
        }

        if(Constants.LongIsNullOrEmpty(spPtlDoctorRegrequest.getAttachedDocIdMedassoc()))
        {
            spPtlDoctorRegrequest.setAttachedDocIdMedassoc(new Long(-1));
        }

        if(Constants.StringIsNullOrEmpty(spPtlDoctorRegrequest.getMedicalAssocNumber()))
        {
            spPtlDoctorRegrequest.setMedicalAssocNumber("");
        }

        if(Constants.StringIsNullOrEmpty(spPtlDoctorRegrequest.getSpDifferentCounty()))
        {
            spPtlDoctorRegrequest.setSpDifferentCounty("");
        }*/

        if(Constants.StringIsNullOrEmpty(spPtlDoctorRegrequest.getMobile())) {
            if(Constants.StringIsNullOrEmpty(user.getMobile()))
                spPtlDoctorRegrequest.setMobile("");
            else
                spPtlDoctorRegrequest.setMobile(user.getMobile());
        }

        /*if(Constants.IntegerIsNullOrEmpty(spPtlDoctorRegrequest.getIsMilitaryDoctor()))
        {
            spPtlDoctorRegrequest.setIsMilitaryDoctor(-1);
        }

        if(Constants.LongIsNullOrEmpty(spPtlDoctorRegrequest.getAttachedDocIdMilitary()))
        {
            spPtlDoctorRegrequest.setAttachedDocIdMilitary(new Long(-1));
        }*/

        if(Constants.StringIsNullOrEmpty(spPtlDoctorRegrequest.getReqAddrP()))
        {
            spPtlDoctorRegrequest.setReqAddrP("");
        }

        if(Constants.StringIsNullOrEmpty(spPtlDoctorRegrequest.getReqAddrPe()))
        {
            spPtlDoctorRegrequest.setReqAddrPe("");
        }

        if(Constants.StringIsNullOrEmpty(spPtlDoctorRegrequest.getReqAddrD()))
        {
            spPtlDoctorRegrequest.setReqAddrD("");
        }

        if(Constants.StringIsNullOrEmpty(spPtlDoctorRegrequest.getReqAddrK()))
        {
            spPtlDoctorRegrequest.setReqAddrK("");
        }

        return true;
    }
    //public boolean isAFMValid(SpPtlDoctorRegrequest spPtlDoctorRegrequest)

    //public boolean isAMEIKAValid(SpPtlDoctorRegrequest spPtlDoctorRegrequest)

}