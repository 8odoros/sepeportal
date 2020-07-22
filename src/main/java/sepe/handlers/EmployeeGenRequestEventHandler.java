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
import sepe.domain.employee.SpPtlEmployeeGenrequest;
import sepe.domain.employee.TEmployee;
import sepe.dto.UserDTO;
import sepe.repository.employee.EmployeeRepository;
import sepe.repository.employee.SpPtlEmployeeGenrequestRepo;
import sepe.service.CurrentUserDetailsService;
import sepe.service.EmployeeStoredProcedures;

@Service
@RepositoryEventHandler(SpPtlEmployeeGenrequest.class)
public class EmployeeGenRequestEventHandler {
    @Autowired
    private EmployeeStoredProcedures employeeStoredProcedures;

    @Autowired
    private SpPtlEmployeeGenrequestRepo spPtlEmployeeGenrequestRepo;

    @Autowired
    private EmployeeRepository employeeRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeGenRequestEventHandler.class);

    @HandleBeforeSave
    public void handleBeforeSave(SpPtlEmployeeGenrequest spPtlEmployeeGenrequest) throws Exception {
        LOGGER.debug("Handling EmployeeGenRequest for: User ID " + CurrentUserDetailsService.getCurrentUser().getUsername());
        spPtlEmployeeGenrequest.setUserId(CurrentUserDetailsService.getCurrentUser().getUserId());

        /*SpPtlEmployeeGenrequest hasDisputeSubmitted = spPtlEmployeeGenrequestRepo.findOne(spPtlEmployeeGenrequest.getId());
        if(null != hasDisputeSubmitted && hasDisputeSubmitted.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode())
        {
            throw new Exception("To αίτημα σας έχει ήδη υποβληθεί.");
        }*/

        if (spPtlEmployeeGenrequest.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode())
            try {
                spPtlEmployeeGenrequest.setSubStatus(Constants.SUBMIT_STATUS.SAVED.getCode());
                spPtlEmployeeGenrequestRepo.save(spPtlEmployeeGenrequest);
                if (isExperienceValid(spPtlEmployeeGenrequest)) {
                    spPtlEmployeeGenrequest.setSubStatus(Constants.SUBMIT_STATUS.SUBMITTED.getCode());
                    (employeeStoredProcedures).procIntArchiveGenRequest(spPtlEmployeeGenrequest);
                }
            } catch (Exception e) {
                e.printStackTrace(); //handle exception
                spPtlEmployeeGenrequest.setSubStatus(Constants.SUBMIT_STATUS.SAVED.getCode());
                spPtlEmployeeGenrequestRepo.save(spPtlEmployeeGenrequest);
                throw new Exception("To αίτημα σας δεν υποβλήθηκε, έχει όμως αποθηκευθεί.");
            }
    }

    @HandleBeforeCreate
    public void handleBeforeCreates(SpPtlEmployeeGenrequest spPtlEmployeeGenrequest) throws Exception {
        LOGGER.debug("Handling EmployeeGenRequest for: User ID " + CurrentUserDetailsService.getCurrentUser().getUsername());
        spPtlEmployeeGenrequest.setUserId(CurrentUserDetailsService.getCurrentUser().getUserId());

        if (spPtlEmployeeGenrequest.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode())
            try {
                spPtlEmployeeGenrequest.setSubStatus(Constants.SUBMIT_STATUS.SAVED.getCode());
                spPtlEmployeeGenrequestRepo.save(spPtlEmployeeGenrequest);
                if (isExperienceValid(spPtlEmployeeGenrequest)) {
                    spPtlEmployeeGenrequest.setSubStatus(Constants.SUBMIT_STATUS.SUBMITTED.getCode());
                    (employeeStoredProcedures).procIntArchiveGenRequest(spPtlEmployeeGenrequest);
                }
            } catch (Exception e) {
                e.printStackTrace(); //handle exception
                spPtlEmployeeGenrequest.setSubStatus(Constants.SUBMIT_STATUS.SAVED.getCode());
                spPtlEmployeeGenrequestRepo.save(spPtlEmployeeGenrequest);
                throw new Exception("To αίτημα σας δεν υποβλήθηκε, έχει όμως αποθηκευθεί.");
            }
    }

    @HandleBeforeDelete
    public void handleBeforeDeletes(SpPtlEmployeeGenrequest spPtlEmployeeGenrequest) throws Exception {
        if (spPtlEmployeeGenrequest.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode()) {
            throw new Exception("To αίτημα σας δεν γίνεται να διαγραφεί επειδή είναι ήδη υποβεβλημένο.");
        }
    }

    public boolean isExperienceValid(SpPtlEmployeeGenrequest spPtlEmployeeGenrequest)
    {
        if(Constants.IntegerIsNullOrEmpty(spPtlEmployeeGenrequest.getReqSubject()))
        {
            spPtlEmployeeGenrequest.setReqSubject(-1);
        }

        if(Constants.StringIsNullOrEmpty(spPtlEmployeeGenrequest.getReqAddrP()))
        {
            spPtlEmployeeGenrequest.setReqAddrP("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlEmployeeGenrequest.getReqAddrPe()))
        {
            spPtlEmployeeGenrequest.setReqAddrPe("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlEmployeeGenrequest.getReqAddrD()))
        {
            spPtlEmployeeGenrequest.setReqAddrD("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlEmployeeGenrequest.getReqAddrK()))
        {
            spPtlEmployeeGenrequest.setReqAddrK("");
        }

        if(Constants.IntegerIsNullOrEmpty(spPtlEmployeeGenrequest.getReqType()))
        {
            spPtlEmployeeGenrequest.setReqType(-1);
        }

        //Employee Details
        UserDTO user = CurrentUserDetailsService.getCurrentUser().getUserDTO();
        if(Constants.StringIsNullOrEmpty(spPtlEmployeeGenrequest.getEmpLastname()))
        {
            if(Constants.StringIsNullOrEmpty(user.getLastname()))
                spPtlEmployeeGenrequest.setEmpLastname("");
            else
                spPtlEmployeeGenrequest.setEmpLastname(user.getLastname());
        }

        if(Constants.StringIsNullOrEmpty(spPtlEmployeeGenrequest.getEmpFirstname()))
        {
            if(Constants.StringIsNullOrEmpty(user.getFirstname()))
                spPtlEmployeeGenrequest.setEmpFirstname("");
            else
                spPtlEmployeeGenrequest.setEmpFirstname(user.getFirstname());
        }

        final TEmployee employee = employeeRepository.findByUserId(spPtlEmployeeGenrequest.getUserId());
        if(Constants.StringIsNullOrEmpty(spPtlEmployeeGenrequest.getEmpFathername()))
        {
            if(Constants.StringIsNullOrEmpty(employee.getFatherName()))
                spPtlEmployeeGenrequest.setEmpFathername("");
            else
                spPtlEmployeeGenrequest.setEmpFathername(employee.getFatherName());
        }

        if(Constants.StringIsNullOrEmpty(spPtlEmployeeGenrequest.getEmpMothername()))
        {
            if(Constants.StringIsNullOrEmpty(employee.getMotherName()))
                spPtlEmployeeGenrequest.setEmpMothername("");
            else
                spPtlEmployeeGenrequest.setEmpMothername(employee.getMotherName());
        }

        if(Constants.StringIsNullOrEmpty(spPtlEmployeeGenrequest.getEmpAddr()))
        {
            spPtlEmployeeGenrequest.setEmpAddr("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlEmployeeGenrequest.getEmpAddrTk()))
        {
            spPtlEmployeeGenrequest.setEmpAddrTk("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlEmployeeGenrequest.getEmpAddrP()))
        {
            spPtlEmployeeGenrequest.setEmpAddrP("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlEmployeeGenrequest.getEmpAddrPe()))
        {
            spPtlEmployeeGenrequest.setEmpAddrPe("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlEmployeeGenrequest.getEmpAddrD()))
        {
            spPtlEmployeeGenrequest.setEmpAddrD("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlEmployeeGenrequest.getEmpAddrK()))
        {
            spPtlEmployeeGenrequest.setEmpAddrK("");
        }

        if(Constants.LongIsNullOrEmpty(spPtlEmployeeGenrequest.getEmpCardType()))
        {
            if(Constants.IntegerIsNullOrEmpty(employee.getCardType()))
                spPtlEmployeeGenrequest.setEmpCardType(new Long(-1));
            else
                spPtlEmployeeGenrequest.setEmpCardType(employee.getCardType().longValue());
        }

        if(Constants.StringIsNullOrEmpty(spPtlEmployeeGenrequest.getEmpCardNumber()))
        {
            if(Constants.StringIsNullOrEmpty(employee.getCardNumber()))
                spPtlEmployeeGenrequest.setEmpCardNumber("");
            else
                spPtlEmployeeGenrequest.setEmpCardNumber(employee.getCardNumber());
        }

        if(Constants.StringIsNullOrEmpty(spPtlEmployeeGenrequest.getEmpAmka()))
        {
            if(Constants.StringIsNullOrEmpty(employee.getAmka()))
                spPtlEmployeeGenrequest.setEmpAmka("");
            else
                spPtlEmployeeGenrequest.setEmpAmka(employee.getAmka());
        }

        if(Constants.StringIsNullOrEmpty(spPtlEmployeeGenrequest.getEmpAfm()))
        {
            if(Constants.StringIsNullOrEmpty(employee.getAfm()))
                spPtlEmployeeGenrequest.setEmpAfm("");
            else
                spPtlEmployeeGenrequest.setEmpAfm(employee.getAfm());
        }

        if(Constants.StringIsNullOrEmpty(spPtlEmployeeGenrequest.getEmpPhone()))
        {
            if(Constants.StringIsNullOrEmpty(user.getPhone()))
                spPtlEmployeeGenrequest.setEmpPhone("");
            else
                spPtlEmployeeGenrequest.setEmpPhone(user.getPhone());
        }

        if(Constants.StringIsNullOrEmpty(spPtlEmployeeGenrequest.getEmpMobile()))
        {
            if(Constants.StringIsNullOrEmpty(user.getMobile()))
                spPtlEmployeeGenrequest.setEmpMobile("");
            else
                spPtlEmployeeGenrequest.setEmpMobile(user.getMobile());
        }

        if(Constants.StringIsNullOrEmpty(spPtlEmployeeGenrequest.getReqDescription()))
        {
            spPtlEmployeeGenrequest.setReqDescription("");
        }

        if(Constants.StringIsNullOrEmpty(spPtlEmployeeGenrequest.getReqRemarks()))
        {
            spPtlEmployeeGenrequest.setReqRemarks("");
        }

        if(Constants.LongIsNullOrEmpty(spPtlEmployeeGenrequest.getDocId()))
        {
            spPtlEmployeeGenrequest.setDocId(new Long(-1));
        }

        if(Constants.LongIsNullOrEmpty(spPtlEmployeeGenrequest.getUserId()))
        {
            spPtlEmployeeGenrequest.setUserId(user.getId());
        }

        return true;
    }
    //public boolean isAFMValid(SpPtlEmployeeGenrequest spPtlEmployeeGenrequest)

    //public boolean isAMEIKAValid(SpPtlEmployeeGenrequest spPtlEmployeeGenrequest)

}