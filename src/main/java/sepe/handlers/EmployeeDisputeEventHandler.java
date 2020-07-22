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
import sepe.domain.employee.TEmployeeDispute;
import sepe.dto.UserDTO;
import sepe.repository.company.TEmployerBranchIKARepo;
import sepe.repository.employee.EmployeeDisputeRepository;
import sepe.repository.employee.EmployeeRepository;
import sepe.service.CurrentUserDetailsService;
import sepe.service.EmployeeStoredProcedures;

import java.sql.Timestamp;

@Service
@RepositoryEventHandler(TEmployeeDispute.class)
public class EmployeeDisputeEventHandler {
    @Autowired
    private EmployeeStoredProcedures employeeStoredProcedures;

    @Autowired
    private EmployeeDisputeRepository employeeDisputeRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TEmployerBranchIKARepo tEmployerBranchIKARepo;

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeDisputeEventHandler.class);

    @HandleBeforeSave
    public void handleBeforeSave(TEmployeeDispute tEmployeeDispute) throws Exception {
        LOGGER.debug("Handling EmployeeDispute for: User ID " + CurrentUserDetailsService.getCurrentUser().getUsername());
        tEmployeeDispute.setUserId(CurrentUserDetailsService.getCurrentUser().getUserId());

/*        TEmployeeDispute hasDisputeSubmitted = employeeDisputeRepository.findOne(tEmployeeDispute.getId());
        if(null != hasDisputeSubmitted && hasDisputeSubmitted.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode())
        {
            throw new Exception("To αίτημα σας έχει ήδη υποβληθεί.");
        }*/

        if (tEmployeeDispute.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode())
            try {
                tEmployeeDispute.setSubStatus(Constants.SUBMIT_STATUS.SAVED.getCode());
                employeeDisputeRepository.save(tEmployeeDispute);
                if (isDisputeValid(tEmployeeDispute)) {
                    tEmployeeDispute.setSubStatus(Constants.SUBMIT_STATUS.SUBMITTED.getCode());
                    (employeeStoredProcedures).procIntArchiveLad(tEmployeeDispute);
                }
            } catch (Exception e) {
                e.printStackTrace(); //handle exception
                tEmployeeDispute.setSubStatus(Constants.SUBMIT_STATUS.SAVED.getCode());
                employeeDisputeRepository.save(tEmployeeDispute);
                throw new Exception("To αίτημα σας δεν υποβλήθηκε, έχει όμως αποθηκευθεί.");
            }
    }

    @HandleBeforeCreate
    public void handleBeforeCreates(TEmployeeDispute tEmployeeDispute) throws Exception {
        LOGGER.debug("Handling EmployeeDispute for: User ID " + CurrentUserDetailsService.getCurrentUser().getUsername());
        tEmployeeDispute.setUserId(CurrentUserDetailsService.getCurrentUser().getUserId());

        if (tEmployeeDispute.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode())
            try {
                tEmployeeDispute.setSubStatus(Constants.SUBMIT_STATUS.SAVED.getCode());
                employeeDisputeRepository.save(tEmployeeDispute);
                if (isDisputeValid(tEmployeeDispute)) {
                    tEmployeeDispute.setSubStatus(Constants.SUBMIT_STATUS.SUBMITTED.getCode());
                    (employeeStoredProcedures).procIntArchiveLad(tEmployeeDispute);
                }
            } catch (Exception e) {
                e.printStackTrace(); //handle exception
                tEmployeeDispute.setSubStatus(Constants.SUBMIT_STATUS.SAVED.getCode());
                employeeDisputeRepository.save(tEmployeeDispute);
                throw new Exception("To αίτημα σας δεν υποβλήθηκε, έχει όμως αποθηκευθεί.");
            }
    }

    @HandleBeforeDelete
    public void handleBeforeDeletes(TEmployeeDispute tEmployeeDispute) throws Exception {
        if (tEmployeeDispute.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode()) {
            throw new Exception("To αίτημα σας δεν γίνεται να διαγραφεί επειδή είναι ήδη υποβεβλημένο.");
        }
    }

    public boolean isDisputeValid(TEmployeeDispute tEmployeeDispute)
    {
        if(Constants.IntegerIsNullOrEmpty(tEmployeeDispute.getApplicantType()))
        {
            tEmployeeDispute.setApplicantType(1);
        }
        if(Constants.IntegerIsNullOrEmpty(tEmployeeDispute.getApplicationType()))
        {
            tEmployeeDispute.setApplicationType(1);
        }

        //Employee Details
        UserDTO user = CurrentUserDetailsService.getCurrentUser().getUserDTO();
        if(Constants.IntegerIsNullOrEmpty(tEmployeeDispute.getUserId().intValue()))
        {
            tEmployeeDispute.setUserId(user.getId());
        }
        final TEmployee employee = employeeRepository.findByUserId(tEmployeeDispute.getUserId());
        if(Constants.StringIsNullOrEmpty(tEmployeeDispute.getEmpAmka()))
        {
            if(Constants.StringIsNullOrEmpty(employee.getAmka()))
                tEmployeeDispute.setEmpAmka("");
            else
                tEmployeeDispute.setEmpAmka(employee.getAmka());
        }
        if(Constants.StringIsNullOrEmpty(tEmployeeDispute.getEmpAfm()))
        {
            if(Constants.StringIsNullOrEmpty(employee.getAfm()))
                tEmployeeDispute.setEmpAfm("");
            else
                tEmployeeDispute.setEmpAfm(employee.getAfm());
        }
        if(Constants.StringIsNullOrEmpty(tEmployeeDispute.getEmpFirstname()))
        {
            if(Constants.StringIsNullOrEmpty(user.getFirstname()))
                tEmployeeDispute.setEmpFirstname("");
            else
                tEmployeeDispute.setEmpFirstname(user.getFirstname());
        }
        if(Constants.StringIsNullOrEmpty(tEmployeeDispute.getEmpLastname()))
        {
            if(Constants.StringIsNullOrEmpty(user.getLastname()))
                tEmployeeDispute.setEmpLastname("");
            else
                tEmployeeDispute.setEmpLastname(user.getLastname());
        }
        if(Constants.LongIsNullOrEmpty(tEmployeeDispute.getEmpSpecialityId()))
        {
            tEmployeeDispute.setEmpSpecialityId(new Long(-1));
        }
        if(Constants.StringIsNullOrEmpty(tEmployeeDispute.getEmpSex()))
        {
            if(Constants.StringIsNullOrEmpty(employee.getSex()))
                tEmployeeDispute.setEmpSex("0");
            else
                tEmployeeDispute.setEmpSex(employee.getSex());
        }
        if(Constants.IntegerIsNullOrEmpty(tEmployeeDispute.getEmpMaritalStatus()))
        {
            tEmployeeDispute.setEmpMaritalStatus(0);
        }
        if(Constants.IntegerIsNullOrEmpty(tEmployeeDispute.getEmpAge()))
        {
            tEmployeeDispute.setEmpAge(0);
        }
        if(Constants.IntegerIsNullOrEmpty(tEmployeeDispute.getEmpCitizenship()))
        {
            if(Constants.IntegerIsNullOrEmpty(employee.getCitizenshipCode()))
                tEmployeeDispute.setEmpCitizenship(0);
            else
                tEmployeeDispute.setEmpCitizenship(employee.getCitizenshipCode());
        }
        if(Constants.IntegerIsNullOrEmpty(tEmployeeDispute.getEmpChildrenNum()))
        {
            tEmployeeDispute.setEmpChildrenNum(0);
        }
        if(Constants.StringIsNullOrEmpty(tEmployeeDispute.getEmpNetSalary()))
        {
            tEmployeeDispute.setEmpNetSalary("");
        }
        if(Constants.StringIsNullOrEmpty(tEmployeeDispute.getEmpGrossSalary()))
        {
            tEmployeeDispute.setEmpGrossSalary("");
        }
        if(Constants.TimestampIsNullOrEmpty(tEmployeeDispute.getEmpFromDate()))
        {
            tEmployeeDispute.setEmpFromDate(new Timestamp(0));
        }
        if(Constants.TimestampIsNullOrEmpty(tEmployeeDispute.getEmpUntilDate()))
        {
            tEmployeeDispute.setEmpUntilDate(new Timestamp(0));
        }
        if(Constants.StringIsNullOrEmpty(tEmployeeDispute.getEmpWorkingHours()))
        {
            tEmployeeDispute.setEmpWorkingHours("");
        }
        if(Constants.StringIsNullOrEmpty(tEmployeeDispute.getEmpAddr()))
        {
            tEmployeeDispute.setEmpAddr("");
        }
        if(Constants.StringIsNullOrEmpty(tEmployeeDispute.getEmpAddrTk()))
        {
            tEmployeeDispute.setEmpAddrTk("");
        }
        if(Constants.StringIsNullOrEmpty(tEmployeeDispute.getEmpAddrP()))
        {
            tEmployeeDispute.setEmpAddrP("");
        }
        if(Constants.StringIsNullOrEmpty(tEmployeeDispute.getEmpAddrPe()))
        {
            tEmployeeDispute.setEmpAddrPe("");
        }
        if(Constants.StringIsNullOrEmpty(tEmployeeDispute.getEmpAddrD()))
        {
            tEmployeeDispute.setEmpAddrD("");
        }
        if(Constants.StringIsNullOrEmpty(tEmployeeDispute.getEmpAddrK()))
        {
            tEmployeeDispute.setEmpAddrK("");
        }
        if(Constants.IntegerIsNullOrEmpty(tEmployeeDispute.getEmpCardType()))
        {
            if(Constants.IntegerIsNullOrEmpty(employee.getCardType()))
                tEmployeeDispute.setEmpCardType(0);
            else
                tEmployeeDispute.setEmpCardType(employee.getCardType());
        }
        if(Constants.StringIsNullOrEmpty(tEmployeeDispute.getEmpCardNumber()))
        {
            if(Constants.StringIsNullOrEmpty(employee.getCardNumber()))
                tEmployeeDispute.setEmpCardNumber("");
            else
                tEmployeeDispute.setEmpCardNumber(employee.getCardNumber());
        }
        if(Constants.StringIsNullOrEmpty(tEmployeeDispute.getEmpPhone()))
        {
            if(Constants.StringIsNullOrEmpty(user.getPhone()))
                tEmployeeDispute.setEmpPhone("");
            else
                tEmployeeDispute.setEmpPhone(user.getPhone());
        }
        if(Constants.StringIsNullOrEmpty(tEmployeeDispute.getEmpMobile()))
        {
            if(Constants.StringIsNullOrEmpty(user.getMobile()))
                tEmployeeDispute.setEmpMobile("");
            else
                tEmployeeDispute.setEmpMobile(user.getMobile());
        }

        //Company Details
        //TEmployerBranchIKA tEmployerBranchIKA = tEmployerBranchIKARepo.findCompanyMainBranchByAme(tEmployeeDispute.getCompAmeIka());
        TEmployerBranchIKA tEmployerBranchIKA = tEmployerBranchIKARepo.findCompanyMainBranchByAfmAndAme(tEmployeeDispute.getCompAfm(),tEmployeeDispute.getCompAmeIka());
        tEmployeeDispute.setBranch0Id(tEmployerBranchIKA.getRgEbrEmpSepeId().intValue());

        if(Constants.StringIsNullOrEmpty(tEmployeeDispute.getCompAfm()))
        {
            tEmployeeDispute.setCompAfm("");
        }
        if(Constants.StringIsNullOrEmpty(tEmployeeDispute.getCompName()))
        {
            tEmployeeDispute.setCompName("");
        }
        if(Constants.StringIsNullOrEmpty(tEmployeeDispute.getCompScope()))
        {
            tEmployeeDispute.setCompScope("");
        }
        if(Constants.StringIsNullOrEmpty(tEmployeeDispute.getCompRepresentative()))
        {
            tEmployeeDispute.setCompRepresentative("");
        }
        if(Constants.StringIsNullOrEmpty(tEmployeeDispute.getCompAddr()))
        {
            tEmployeeDispute.setCompAddr("");
        }
        if(Constants.StringIsNullOrEmpty(tEmployeeDispute.getCompAddrTk()))
        {
            tEmployeeDispute.setCompAddrTk("");
        }
        if(Constants.StringIsNullOrEmpty(tEmployeeDispute.getCompAddrP()))
        {
            tEmployeeDispute.setCompAddrP("");
        }
        if(Constants.StringIsNullOrEmpty(tEmployeeDispute.getCompAddrPe()))
        {
            tEmployeeDispute.setCompAddrPe("");
        }
        if(Constants.StringIsNullOrEmpty(tEmployeeDispute.getCompAddrD()))
        {
            tEmployeeDispute.setCompAddrD("");
        }
        if(Constants.StringIsNullOrEmpty(tEmployeeDispute.getCompAddrK()))
        {
            tEmployeeDispute.setCompAddrK("");
        }
        if(Constants.StringIsNullOrEmpty(tEmployeeDispute.getCompDoy()))
        {
            tEmployeeDispute.setCompDoy("");
        }
        if(Constants.StringIsNullOrEmpty(tEmployeeDispute.getCompAmeIka()))
        {
            tEmployeeDispute.setCompAmeIka("");
        }
        if(Constants.StringIsNullOrEmpty(tEmployeeDispute.getCompPhone()))
        {
            tEmployeeDispute.setCompPhone("");
        }
        if(Constants.StringIsNullOrEmpty(tEmployeeDispute.getCompBrAddr()))
        {
            tEmployeeDispute.setCompBrAddr("");
        }
        if(Constants.StringIsNullOrEmpty(tEmployeeDispute.getCompBrAddrTk()))
        {
            tEmployeeDispute.setCompBrAddrTk("");
        }
        if(Constants.StringIsNullOrEmpty(tEmployeeDispute.getCompBrAddrP()))
        {
            tEmployeeDispute.setCompBrAddrP("");
        }
        if(Constants.StringIsNullOrEmpty(tEmployeeDispute.getCompBrAddrPe()))
        {
            tEmployeeDispute.setCompBrAddrPe("");
        }
        if(Constants.StringIsNullOrEmpty(tEmployeeDispute.getCompBrAddrD()))
        {
            tEmployeeDispute.setCompBrAddrD("");
        }
        if(Constants.StringIsNullOrEmpty(tEmployeeDispute.getCompBrAddrK()))
        {
            tEmployeeDispute.setCompBrAddrK("");
        }
        if(Constants.LongIsNullOrEmpty(tEmployeeDispute.getDepartment()))
        {
            tEmployeeDispute.setDepartment(new Long(-1));
        }

        if(Constants.IntegerIsNullOrEmpty(tEmployeeDispute.getGroupDiscussion()))
        {
            tEmployeeDispute.setGroupDiscussion(-1);
        }
        if(Constants.StringIsNullOrEmpty(tEmployeeDispute.getNotes()))
        {
            tEmployeeDispute.setNotes("");
        }
        if(Constants.LongIsNullOrEmpty(tEmployeeDispute.getDocIdAttached()))
        {
            tEmployeeDispute.setDocIdAttached(-1L);
        }

        if(Constants.StringIsNullOrEmpty(tEmployeeDispute.getRtstakLevel5()))
        {
            tEmployeeDispute.setRtstakLevel5("0311"); //todo change it....
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