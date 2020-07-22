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
import sepe.domain.technician.SpPtlTechnicianDiploma;
import sepe.domain.technician.SpPtlTechnicianRegrequest;
import sepe.domain.technician.TSafetyTechnician;
import sepe.dto.UserDTO;
import sepe.repository.technician.SafetyTechnicianRepository;
import sepe.repository.technician.SpPtlTechnicianDiplomaRepo;
import sepe.repository.technician.SpPtlTechnicianRegrequestRepo;
import sepe.service.CurrentUserDetailsService;
import sepe.service.SafetyTechnicianStoredProcedures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RepositoryEventHandler(SpPtlTechnicianRegrequest.class)
public class TechnicianRegRequestEventHandler {
    @Autowired
    private SafetyTechnicianStoredProcedures safetyTechnicianStoredProcedures;

    @Autowired
    private SpPtlTechnicianDiplomaRepo spPtlTechnicianDiplomaRepo;

    @Autowired
    private SpPtlTechnicianRegrequestRepo spPtlTechnicianRegrequestRepo;

    @Autowired
    private SafetyTechnicianRepository safetyTechnicianRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(TechnicianRegRequestEventHandler.class);

    @HandleBeforeSave
    public void handleBeforeSave(SpPtlTechnicianRegrequest spPtlTechnicianRegrequest) throws Exception {
        LOGGER.debug("Handling TechnicianRegrequest for: User ID " + CurrentUserDetailsService.getCurrentUser().getUsername());
        spPtlTechnicianRegrequest.setUserId(CurrentUserDetailsService.getCurrentUser().getUserId());
        boolean openReq = isAnotherTechnicianRegRequestOpen();

/*        SpPtlTechnicianRegrequest hasDisputeSubmitted = spPtlTechnicianRegrequestRepo.findOne(spPtlTechnicianRegrequest.getId());
        if(null != hasDisputeSubmitted && hasDisputeSubmitted.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode())
        {
            throw new Exception("To αίτημα σας έχει ήδη υποβληθεί.");
        }*/

        saveTechnicianDiplomas(spPtlTechnicianRegrequest);
        if (spPtlTechnicianRegrequest.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode())
            try {
                if (!openReq) {
                    if (isTechnicianRegRequestValid(spPtlTechnicianRegrequest))
                        (safetyTechnicianStoredProcedures).procIntArchiveTARegrequest(spPtlTechnicianRegrequest);
                } else {
                    spPtlTechnicianRegrequest.setSubStatus(Constants.SUBMIT_STATUS.SAVED.getCode());
                    spPtlTechnicianRegrequestRepo.save(spPtlTechnicianRegrequest);
                    throw new Exception("To αίτημα σας δεν υποβλήθηκε γιατί υπάρχουν ήδη αιτήματα 'Σε Εκκρεμότητα'. Έχει όμως αποθηκευθεί.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                spPtlTechnicianRegrequest.setSubStatus(Constants.SUBMIT_STATUS.SAVED.getCode());
                spPtlTechnicianRegrequestRepo.save(spPtlTechnicianRegrequest);
                throw new Exception("To αίτημα σας δεν υποβλήθηκε, έχει όμως αποθηκευθεί.");
            }
    }

    @HandleBeforeCreate
    public void handleBeforeCreates(SpPtlTechnicianRegrequest spPtlTechnicianRegrequest) throws Exception {
        LOGGER.debug("Handling TechnicianRegrequest for: User ID " + CurrentUserDetailsService.getCurrentUser().getUsername());
        spPtlTechnicianRegrequest.setUserId(CurrentUserDetailsService.getCurrentUser().getUserId());
        boolean openReq = isAnotherTechnicianRegRequestOpen();

        saveTechnicianDiplomas(spPtlTechnicianRegrequest);
        if (spPtlTechnicianRegrequest.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode())
            try {
                if (!openReq) {
                    if (isTechnicianRegRequestValid(spPtlTechnicianRegrequest))
                        (safetyTechnicianStoredProcedures).procIntArchiveTARegrequest(spPtlTechnicianRegrequest);
                } else {
                    spPtlTechnicianRegrequest.setSubStatus(Constants.SUBMIT_STATUS.SAVED.getCode());
                    spPtlTechnicianRegrequestRepo.save(spPtlTechnicianRegrequest);
                    throw new Exception("To αίτημα σας δεν υποβλήθηκε γιατί υπάρχουν ήδη αιτήματα 'Σε Εκκρεμότητα'. Έχει όμως αποθηκευθεί.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                spPtlTechnicianRegrequest.setSubStatus(Constants.SUBMIT_STATUS.SAVED.getCode());
                spPtlTechnicianRegrequestRepo.save(spPtlTechnicianRegrequest);
                throw new Exception("To αίτημα σας δεν υποβλήθηκε, έχει όμως αποθηκευθεί.");
            }
    }

    @HandleBeforeDelete
    public void handleBeforeDeletes(SpPtlTechnicianRegrequest spPtlTechnicianRegrequest) throws Exception {
        if (spPtlTechnicianRegrequest.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode()) {
            throw new Exception("To αίτημα σας δεν γίνεται να διαγραφεί επειδή είναι ήδη υποβεβλημένο.");
        }
    }

    public void saveTechnicianDiplomas(SpPtlTechnicianRegrequest spPtlTechnicianRegrequest) {
        String str = spPtlTechnicianRegrequest.getDiplomaIdComma();
        List<String> diplomas = new ArrayList<String>(Arrays.asList(str.split(",")));
        for (String diploma : diplomas) {
            Long diplomaID = Constants.StringIsNullOrEmpty(diploma) ? 1 : Long.parseLong(diploma);
            SpPtlTechnicianDiploma spPtlTechnicianDiploma = spPtlTechnicianDiplomaRepo.findOne(diplomaID);
            spPtlTechnicianRegrequest.getTechnicianDiplomas().add(spPtlTechnicianDiploma);
        }
    }

    public boolean isAnotherTechnicianRegRequestOpen()
    {
        boolean isAnotherTechnicianRegRequestOpen = false;
        Integer reqStatus = 5;
        Integer subStatus = 2;
        List<SpPtlTechnicianRegrequest> spPtlTechnicianRegrequests = spPtlTechnicianRegrequestRepo.findByReqStatus(reqStatus,subStatus);
        if (spPtlTechnicianRegrequests != null && spPtlTechnicianRegrequests.size() > 0) {
            isAnotherTechnicianRegRequestOpen = true;
        }
        return isAnotherTechnicianRegRequestOpen;
    }

    public boolean isTechnicianRegRequestValid(SpPtlTechnicianRegrequest spPtlTechnicianRegrequest)
    {
        UserDTO user = CurrentUserDetailsService.getCurrentUser().getUserDTO();
        if(Constants.LongIsNullOrEmpty(spPtlTechnicianRegrequest.getUserId()))
        {
            spPtlTechnicianRegrequest.setUserId(user.getId());
        }

        if(Constants.StringIsNullOrEmpty(spPtlTechnicianRegrequest.getLastname()))
        {
            if(Constants.StringIsNullOrEmpty(user.getLastname()))
                spPtlTechnicianRegrequest.setLastname("");
            else
                spPtlTechnicianRegrequest.setLastname(user.getLastname());
        }

        if(Constants.StringIsNullOrEmpty(spPtlTechnicianRegrequest.getFirstname()))
        {
            if(Constants.StringIsNullOrEmpty(user.getFirstname()))
                spPtlTechnicianRegrequest.setFirstname("");
            else
                spPtlTechnicianRegrequest.setFirstname(user.getFirstname());
        }

        if(Constants.LongIsNullOrEmpty(spPtlTechnicianRegrequest.getEducationLevel()))
        {
            spPtlTechnicianRegrequest.setEducationLevel(new Long(-1));
        }

        final TSafetyTechnician tSafetyTechnician = safetyTechnicianRepository.findByUserId(spPtlTechnicianRegrequest.getUserId());
        if(Constants.StringIsNullOrEmpty(spPtlTechnicianRegrequest.getAfm()))
        {
            if (tSafetyTechnician != null) {
                if(Constants.StringIsNullOrEmpty(tSafetyTechnician.getAfm()))
                    spPtlTechnicianRegrequest.setAfm("");
                else
                    spPtlTechnicianRegrequest.setAfm(tSafetyTechnician.getAfm());
            }
        }

        if(Constants.StringIsNullOrEmpty(spPtlTechnicianRegrequest.getAmka()))
        {
            spPtlTechnicianRegrequest.setAmka("");
        }

        if(Constants.StringIsNullOrEmpty(spPtlTechnicianRegrequest.getCardNumber()))
        {
            if (tSafetyTechnician != null) {
                if(Constants.StringIsNullOrEmpty(tSafetyTechnician.getCardNumber()))
                    spPtlTechnicianRegrequest.setCardNumber("");
                else
                    spPtlTechnicianRegrequest.setCardNumber(tSafetyTechnician.getCardNumber());
            }
        }

        if(Constants.StringIsNullOrEmpty(spPtlTechnicianRegrequest.getPhone()))
        {
            if(Constants.StringIsNullOrEmpty(user.getPhone()))
                spPtlTechnicianRegrequest.setPhone("");
            else
                spPtlTechnicianRegrequest.setPhone(user.getPhone());
        }

        if(Constants.StringIsNullOrEmpty(spPtlTechnicianRegrequest.getMobile())) {
            if(Constants.StringIsNullOrEmpty(user.getMobile()))
                spPtlTechnicianRegrequest.setMobile("");
            else
                spPtlTechnicianRegrequest.setMobile(user.getMobile());
        }

        if(Constants.StringIsNullOrEmpty(spPtlTechnicianRegrequest.getDescr()))
        {
            spPtlTechnicianRegrequest.setDescr("");
        }

        if(Constants.LongIsNullOrEmpty(spPtlTechnicianRegrequest.getCardType()))
        {
            if (tSafetyTechnician != null) {
                if(Constants.LongIsNullOrEmpty(tSafetyTechnician.getCardType()))
                    spPtlTechnicianRegrequest.setCardType(new Long(-1));
                else
                    spPtlTechnicianRegrequest.setCardType(tSafetyTechnician.getCardType());
            }
        }

        if(Constants.LongIsNullOrEmpty(spPtlTechnicianRegrequest.getAttachedDocId()))
        {
            spPtlTechnicianRegrequest.setAttachedDocId(new Long(-1));
        }

        if(Constants.StringIsNullOrEmpty(spPtlTechnicianRegrequest.getTeeNumber()))
        {
            spPtlTechnicianRegrequest.setTeeNumber("");
        }

        if(Constants.StringIsNullOrEmpty(spPtlTechnicianRegrequest.getFathername()))
        {
            spPtlTechnicianRegrequest.setFathername("");
        }

        /*if(Constants.LongIsNullOrEmpty(spPtlTechnicianRegrequest.getSpeciality()))
        {
            spPtlTechnicianRegrequest.setSpeciality(new Long(-1));
        }*/

        if (spPtlTechnicianRegrequest.getSpeciality().contains("[")) {
            spPtlTechnicianRegrequest.setSpeciality(spPtlTechnicianRegrequest.getSpeciality().replace("[", "").replace("]", ""));
        }

        if(Constants.IntegerIsNullOrEmpty(spPtlTechnicianRegrequest.getShipyardDuties()))
        {
            spPtlTechnicianRegrequest.setShipyardDuties(-1);
        }

        if(Constants.StringIsNullOrEmpty(spPtlTechnicianRegrequest.getEmail()))
        {
            if(Constants.StringIsNullOrEmpty(user.getEmail()))
                spPtlTechnicianRegrequest.setEmail("");
            else
                spPtlTechnicianRegrequest.setEmail(user.getEmail());
        }

        if(Constants.IntegerIsNullOrEmpty(spPtlTechnicianRegrequest.getEducation100()))
        {
            spPtlTechnicianRegrequest.setEducation100(-1);
        }

        if(Constants.IntegerIsNullOrEmpty(spPtlTechnicianRegrequest.getEducation3510()))
        {
            spPtlTechnicianRegrequest.setEducation3510(-1);
        }

        if(Constants.LongIsNullOrEmpty(spPtlTechnicianRegrequest.getAttachedDocIdEmplTraining()))
        {
            spPtlTechnicianRegrequest.setAttachedDocIdEmplTraining(new Long(-1));
        }

        if(Constants.StringIsNullOrEmpty(spPtlTechnicianRegrequest.getSpecialityOther()))
        {
            spPtlTechnicianRegrequest.setSpecialityOther("");
        }

        if(Constants.StringIsNullOrEmpty(spPtlTechnicianRegrequest.getFax()))
        {
            if(Constants.StringIsNullOrEmpty(user.getFax()))
                spPtlTechnicianRegrequest.setFax("");
            else
                spPtlTechnicianRegrequest.setFax(user.getFax());
        }

        if(Constants.StringIsNullOrEmpty(spPtlTechnicianRegrequest.getNotes()))
        {
            spPtlTechnicianRegrequest.setNotes("");
        }

        if(Constants.IntegerIsNullOrEmpty(spPtlTechnicianRegrequest.getSepeProfilStatus()))
        {
            spPtlTechnicianRegrequest.setSepeProfilStatus(-1);
        }

        if(Constants.StringIsNullOrEmpty(spPtlTechnicianRegrequest.getReqAddrP()))
        {
            spPtlTechnicianRegrequest.setReqAddrP("");
        }

        if(Constants.StringIsNullOrEmpty(spPtlTechnicianRegrequest.getReqAddrPe()))
        {
            spPtlTechnicianRegrequest.setReqAddrPe("");
        }

        if(Constants.StringIsNullOrEmpty(spPtlTechnicianRegrequest.getReqAddrD()))
        {
            spPtlTechnicianRegrequest.setReqAddrD("");
        }

        if(Constants.StringIsNullOrEmpty(spPtlTechnicianRegrequest.getReqAddrK()))
        {
            spPtlTechnicianRegrequest.setReqAddrK("");
        }

        return true;
    }
    //public boolean isAFMValid(SpPtlTechnicianRegrequest spPtlTechnicianRegrequest)

    //public boolean isAMEIKAValid(SpPtlTechnicianRegrequest spPtlTechnicianRegrequest)

}