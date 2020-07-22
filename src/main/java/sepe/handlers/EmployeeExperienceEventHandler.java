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
import sepe.domain.company.TEmployerBranchIKA;
import sepe.domain.employee.TEmployee;
import sepe.domain.employee.SpPtlEmployeeExperience;
import sepe.dto.UserDTO;
import sepe.repository.company.TEmployerBranchIKARepo;
import sepe.repository.employee.EmployeeExperienceRepository;
import sepe.repository.employee.EmployeeRepository;
import sepe.service.CurrentUserDetailsService;
import sepe.service.EmployeeStoredProcedures;

import java.sql.Timestamp;

@Service
@RepositoryEventHandler(SpPtlEmployeeExperience.class)
public class EmployeeExperienceEventHandler {
    @Autowired
    private EmployeeStoredProcedures employeeStoredProcedures;

    @Autowired
    private EmployeeExperienceRepository employeeExperienceRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TEmployerBranchIKARepo tEmployerBranchIKARepo;

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeExperienceEventHandler.class);

    @HandleBeforeSave
    public void handleBeforeSave(SpPtlEmployeeExperience spPtlEmployeeExperience) throws Exception {
        LOGGER.debug("Handling EmployeeComplaint for: User ID " + CurrentUserDetailsService.getCurrentUser().getUsername());
        spPtlEmployeeExperience.setUserId(CurrentUserDetailsService.getCurrentUser().getUserId());

        SpPtlEmployeeExperience hasDisputeSubmitted = employeeExperienceRepository.findOne(spPtlEmployeeExperience.getId());
        if(null != hasDisputeSubmitted && hasDisputeSubmitted.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode())
        {
            throw new Exception("To αίτημα σας έχει ήδη υποβληθεί.");
        }

        if (spPtlEmployeeExperience.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode())
            try {
                spPtlEmployeeExperience.setSubStatus(Constants.SUBMIT_STATUS.SAVED.getCode());
                employeeExperienceRepository.save(spPtlEmployeeExperience);
                if (isExperienceValid(spPtlEmployeeExperience)) {
                    spPtlEmployeeExperience.setSubStatus(Constants.SUBMIT_STATUS.SUBMITTED.getCode());
                    (employeeStoredProcedures).procIntArchiveExperience(spPtlEmployeeExperience);
                }
            } catch (Exception e) {
                e.printStackTrace(); //handle exception
                spPtlEmployeeExperience.setSubStatus(Constants.SUBMIT_STATUS.SAVED.getCode());
                employeeExperienceRepository.save(spPtlEmployeeExperience);
                throw new Exception("To αίτημα σας δεν υποβλήθηκε, έχει όμως αποθηκευθεί.");
            }
    }

    @HandleBeforeCreate
    public void handleBeforeCreates(SpPtlEmployeeExperience spPtlEmployeeExperience) throws Exception {
        LOGGER.debug("Handling EmployeeComplaint for: User ID " + CurrentUserDetailsService.getCurrentUser().getUsername());
        spPtlEmployeeExperience.setUserId(CurrentUserDetailsService.getCurrentUser().getUserId());

        if (spPtlEmployeeExperience.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode())
            try {
                spPtlEmployeeExperience.setSubStatus(Constants.SUBMIT_STATUS.SAVED.getCode());
                employeeExperienceRepository.save(spPtlEmployeeExperience);
                if (isExperienceValid(spPtlEmployeeExperience)) {
                    spPtlEmployeeExperience.setSubStatus(Constants.SUBMIT_STATUS.SUBMITTED.getCode());
                    (employeeStoredProcedures).procIntArchiveExperience(spPtlEmployeeExperience);
                }
            } catch (Exception e) {
                e.printStackTrace(); //handle exception
                spPtlEmployeeExperience.setSubStatus(Constants.SUBMIT_STATUS.SAVED.getCode());
                employeeExperienceRepository.save(spPtlEmployeeExperience);
                throw new Exception("To αίτημα σας δεν υποβλήθηκε, έχει όμως αποθηκευθεί.");
            }
    }

    @HandleBeforeDelete
    public void handleBeforeDeletes(SpPtlEmployeeExperience spPtlEmployeeExperience) throws Exception {
        if (spPtlEmployeeExperience.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode()) {
            throw new Exception("To αίτημα σας δεν γίνεται να διαγραφεί επειδή είναι ήδη υποβεβλημένο.");
        }
    }

    public boolean isExperienceValid(SpPtlEmployeeExperience spPtlEmployeeExperience)
    {
        //Employee Details
        UserDTO user = CurrentUserDetailsService.getCurrentUser().getUserDTO();
        if(Constants.StringIsNullOrEmpty(spPtlEmployeeExperience.getEmpLastname()))
        {
            if(Constants.StringIsNullOrEmpty(user.getLastname()))
                spPtlEmployeeExperience.setEmpLastname("");
            else
                spPtlEmployeeExperience.setEmpLastname(user.getLastname());
        }

        if(Constants.StringIsNullOrEmpty(spPtlEmployeeExperience.getEmpFirstname()))
        {
            if(Constants.StringIsNullOrEmpty(user.getFirstname()))
                spPtlEmployeeExperience.setEmpFirstname("");
            else
                spPtlEmployeeExperience.setEmpFirstname(user.getFirstname());
        }

        if(Constants.LongIsNullOrEmpty(spPtlEmployeeExperience.getEmpSpecialityId()))
        {
            spPtlEmployeeExperience.setEmpSpecialityId(new Long(-1));
        }

        if(Constants.TimestampIsNullOrEmpty(spPtlEmployeeExperience.getEmpFromDate()))
        {
            spPtlEmployeeExperience.setEmpFromDate(new Timestamp(0));
        }

        if(Constants.TimestampIsNullOrEmpty(spPtlEmployeeExperience.getEmpUntilDate()))
        {
            spPtlEmployeeExperience.setEmpUntilDate(new Timestamp(0));
        }

        if(Constants.StringIsNullOrEmpty(spPtlEmployeeExperience.getEmpAddr()))
        {
            spPtlEmployeeExperience.setEmpAddr("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlEmployeeExperience.getEmpAddrTk()))
        {
            spPtlEmployeeExperience.setEmpAddrTk("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlEmployeeExperience.getEmpAddrP()))
        {
            spPtlEmployeeExperience.setEmpAddrP("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlEmployeeExperience.getEmpAddrPe()))
        {
            spPtlEmployeeExperience.setEmpAddrPe("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlEmployeeExperience.getEmpAddrD()))
        {
            spPtlEmployeeExperience.setEmpAddrD("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlEmployeeExperience.getEmpAddrK()))
        {
            spPtlEmployeeExperience.setEmpAddrK("");
        }

        final TEmployee employee = employeeRepository.findByUserId(spPtlEmployeeExperience.getUserId());
        if(Constants.IntegerIsNullOrEmpty(spPtlEmployeeExperience.getEmpCardType()))
        {
            if(Constants.IntegerIsNullOrEmpty(employee.getCardType()))
                spPtlEmployeeExperience.setEmpCardType(-1);
            else
                spPtlEmployeeExperience.setEmpCardType(employee.getCardType());
        }

        if(Constants.StringIsNullOrEmpty(spPtlEmployeeExperience.getEmpCardNumber()))
        {
            if(Constants.StringIsNullOrEmpty(employee.getCardNumber()))
                spPtlEmployeeExperience.setEmpCardNumber("");
            else
                spPtlEmployeeExperience.setEmpCardNumber(employee.getCardNumber());
        }

        if(Constants.StringIsNullOrEmpty(spPtlEmployeeExperience.getEmpAmka()))
        {
            if(Constants.StringIsNullOrEmpty(employee.getAmka()))
                spPtlEmployeeExperience.setEmpAmka("");
            else
                spPtlEmployeeExperience.setEmpAmka(employee.getAmka());
        }
        if(Constants.StringIsNullOrEmpty(spPtlEmployeeExperience.getEmpAfm()))
        {
            if(Constants.StringIsNullOrEmpty(employee.getAfm()))
                spPtlEmployeeExperience.setEmpAfm("");
            else
                spPtlEmployeeExperience.setEmpAfm(employee.getAfm());
        }

        if(Constants.StringIsNullOrEmpty(spPtlEmployeeExperience.getEmpPhone()))
        {
            if(Constants.StringIsNullOrEmpty(user.getPhone()))
                spPtlEmployeeExperience.setEmpPhone("");
            else
                spPtlEmployeeExperience.setEmpPhone(user.getPhone());
        }

        if(Constants.StringIsNullOrEmpty(spPtlEmployeeExperience.getEmpMobile()))
        {
            if(Constants.StringIsNullOrEmpty(user.getMobile()))
                spPtlEmployeeExperience.setEmpMobile("");
            else
                spPtlEmployeeExperience.setEmpMobile(user.getMobile());
        }

        //Company Details
        //TEmployerBranchIKA tEmployerBranchIKA = tEmployerBranchIKARepo.findCompanyMainBranchByAme(spPtlEmployeeExperience.getCompAmeIka());
        TEmployerBranchIKA tEmployerBranchIKA = tEmployerBranchIKARepo.findCompanyMainBranchByAfmAndAme(spPtlEmployeeExperience.getCompAfm(),spPtlEmployeeExperience.getCompAmeIka());
        spPtlEmployeeExperience.setBranch0Id(tEmployerBranchIKA.getRgEbrEmpSepeId().intValue());
        spPtlEmployeeExperience.setRgEbrKallikratis(tEmployerBranchIKA.getRgEbrKallikratis());

        if(Constants.StringIsNullOrEmpty(spPtlEmployeeExperience.getCompName()))
        {
            spPtlEmployeeExperience.setCompName("");
        }

        if(Constants.StringIsNullOrEmpty(spPtlEmployeeExperience.getRtstakLevel5()))
        {
            spPtlEmployeeExperience.setRtstakLevel5(""); //todo change it....
        }

        if(Constants.StringIsNullOrEmpty(spPtlEmployeeExperience.getCompAfm()))
        {
            spPtlEmployeeExperience.setCompAfm("");
        }

        if(Constants.StringIsNullOrEmpty(spPtlEmployeeExperience.getCompAmeIka()))
        {
            spPtlEmployeeExperience.setCompAmeIka("");
        }

        if(Constants.StringIsNullOrEmpty(spPtlEmployeeExperience.getCompPhone()))
        {
            spPtlEmployeeExperience.setCompPhone("");
        }

        if(Constants.IntegerIsNullOrEmpty(spPtlEmployeeExperience.getIntentedUse()))
        {
            spPtlEmployeeExperience.setIntentedUse(-1);
        }

        if(Constants.StringIsNullOrEmpty(spPtlEmployeeExperience.getNotes()))
        {
            spPtlEmployeeExperience.setNotes("");
        }

        if(Constants.LongIsNullOrEmpty(spPtlEmployeeExperience.getAttachedDocIdEmplVer()))
        {
            spPtlEmployeeExperience.setAttachedDocIdEmplVer(new Long(-1));
        }

        if(Constants.LongIsNullOrEmpty(spPtlEmployeeExperience.getAttachedDocIdIka()))
        {
            spPtlEmployeeExperience.setAttachedDocIdIka(new Long(-1));
        }

        if(Constants.LongIsNullOrEmpty(spPtlEmployeeExperience.getAttachedDocIdJudgmnt()))
        {
            spPtlEmployeeExperience.setAttachedDocIdJudgmnt(new Long(-1));
        }

        if(Constants.LongIsNullOrEmpty(spPtlEmployeeExperience.getAttachedDocIdSepe()))
        {
            spPtlEmployeeExperience.setAttachedDocIdSepe(new Long(-1));
        }

        if(Constants.LongIsNullOrEmpty(spPtlEmployeeExperience.getUserId()))
        {
            spPtlEmployeeExperience.setUserId(user.getId());
        }

        return true;
    }
    //public boolean isAFMValid(TEmployeeDispute tEmployeeDispute)
    //{
    //    if(tEmployeeDispute.getCompAfm())
    //        return false;
    //}

    //public boolean isAMEIKAValid(TEmployeeDispute tEmployeeDispute)
    //{
    //    if(tEmployeeDispute.getCompAfm())
    //        return false;
    //}
}