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
import sepe.config.SpringUserDetails;
import sepe.domain.company.TEmployerBranchIKA;
import sepe.domain.employee.TEmployeeComplaint;
import sepe.dto.UserDTO;
import sepe.repository.company.TEmployerBranchIKARepo;
import sepe.repository.employee.EmployeeComplaintRestRepository;
import sepe.service.CurrentUserDetailsService;
import sepe.service.EmployeeStoredProcedures;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@RepositoryEventHandler(TEmployeeComplaint.class)
public class EmployeeComplaintEventHandler {
    @Autowired
    private EmployeeStoredProcedures employeeStoredProcedures;

    @Autowired
    private EmployeeComplaintRestRepository employeeComplaintRestRepository;

    @Autowired
    private TEmployerBranchIKARepo tEmployerBranchIKARepo;

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeComplaintEventHandler.class);

    @HandleBeforeSave
    public void handleBeforeSave(TEmployeeComplaint tEmployeeComplaint) throws Exception {
        LOGGER.debug("Handling EmployeeComplaint for: User ID " + CurrentUserDetailsService.getCurrentUser().getUsername());
        SpringUserDetails currentUser = CurrentUserDetailsService.getCurrentUser();
        tEmployeeComplaint.setUserId(currentUser.getUserId());
        tEmployeeComplaint.setEmpFirstname(currentUser.getUserDTO().getFirstname());
        tEmployeeComplaint.setEmpLastname(currentUser.getUserDTO().getLastname());

/*        TEmployeeComplaint hasComplaintSubmitted = employeeComplaintRestRepository.findById(tEmployeeComplaint.getId());
        if(null != hasComplaintSubmitted && hasComplaintSubmitted.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode())
        {
            throw new Exception("To αίτημα σας έχει ήδη υποβληθεί.");
        }*/

        if (tEmployeeComplaint.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode())
            try {
                tEmployeeComplaint.setSubStatus(Constants.SUBMIT_STATUS.SAVED.getCode());
                employeeComplaintRestRepository.save(tEmployeeComplaint);
                if (isComplaintValid(tEmployeeComplaint)) {
                    tEmployeeComplaint.setSubStatus(Constants.SUBMIT_STATUS.SUBMITTED.getCode());
                    (employeeStoredProcedures).procIntArchiveComplaint(tEmployeeComplaint);
                }
            } catch (Exception e) {
                e.printStackTrace(); //handle exception
                tEmployeeComplaint.setSubStatus(Constants.SUBMIT_STATUS.SAVED.getCode());
                employeeComplaintRestRepository.save(tEmployeeComplaint);
                throw new Exception("To αίτημα σας δεν υποβλήθηκε, έχει όμως αποθηκευθεί.");
            }
    }

    @HandleBeforeCreate
    public void handleBeforeCreates(TEmployeeComplaint tEmployeeComplaint) throws Exception {
        LOGGER.debug("Handling EmployeeComplaint for: User ID " + CurrentUserDetailsService.getCurrentUser().getUsername());
        SpringUserDetails currentUser = CurrentUserDetailsService.getCurrentUser();
        tEmployeeComplaint.setUserId(currentUser.getUserId());
        tEmployeeComplaint.setEmpFirstname(currentUser.getUserDTO().getFirstname());
        tEmployeeComplaint.setEmpLastname(currentUser.getUserDTO().getLastname());

        if (tEmployeeComplaint.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode())
            try {
                tEmployeeComplaint.setSubStatus(Constants.SUBMIT_STATUS.SAVED.getCode());
                employeeComplaintRestRepository.save(tEmployeeComplaint);
                if (isComplaintValid(tEmployeeComplaint)) {
                    tEmployeeComplaint.setSubStatus(Constants.SUBMIT_STATUS.SUBMITTED.getCode());
                    (employeeStoredProcedures).procIntArchiveComplaint(tEmployeeComplaint);
                }
            } catch (Exception e) {
                e.printStackTrace(); //handle exception
                tEmployeeComplaint.setSubStatus(Constants.SUBMIT_STATUS.SAVED.getCode());
                employeeComplaintRestRepository.save(tEmployeeComplaint);
                throw new Exception("To αίτημα σας δεν υποβλήθηκε, έχει όμως αποθηκευθεί.");
            }
    }

    @HandleBeforeDelete
    public void handleBeforeDeletes(TEmployeeComplaint tEmployeeComplaint) throws Exception {
        if (tEmployeeComplaint.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode()) {
            throw new Exception("To αίτημα σας δεν γίνεται να διαγραφεί επειδή είναι ήδη υποβεβλημένο.");
        }
    }

    public boolean isComplaintValid(TEmployeeComplaint tEmployeeComplaint)
    {
        if(Constants.IntegerIsNullOrEmpty(tEmployeeComplaint.getComplIsAnonymous()))
        {
            tEmployeeComplaint.setComplIsAnonymous(-1);
        }
        //Texniki epithewrisi
        if(Constants.IntegerIsNullOrEmpty(tEmployeeComplaint.getComplInvlovesSafetyInsp()))
        {
            tEmployeeComplaint.setComplInvlovesSafetyInsp(-1);
        }
        //Koinoniki epithewrisi
        if(Constants.IntegerIsNullOrEmpty(tEmployeeComplaint.getComplInvolvesLabRelations()))
        {
            tEmployeeComplaint.setComplInvolvesLabRelations(-1);
        }
        if(Constants.StringIsNullOrEmpty(tEmployeeComplaint.getComplDescr()))
        {
            tEmployeeComplaint.setComplDescr("");
        }
        if(Constants.StringIsNullOrEmpty(tEmployeeComplaint.getComplAddressDesc()))
        {
            tEmployeeComplaint.setComplAddressDesc("");
        }
        if(Constants.StringIsNullOrEmpty(tEmployeeComplaint.getComplInspTime()))
        {
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            tEmployeeComplaint.setComplInspTime((dateFormat.format(date)).toString());
        }

        UserDTO user = CurrentUserDetailsService.getCurrentUser().getUserDTO();
        if(Constants.IntegerIsNullOrEmpty(tEmployeeComplaint.getUserId().intValue()))
        {
            tEmployeeComplaint.setUserId(user.getId());
        }
        if (Constants.StringIsNullOrEmpty(tEmployeeComplaint.getEmpFirstname()) && tEmployeeComplaint.getComplIsAnonymous() == Constants.COMPLAINT_TYPE_DESC.NAMED.getCode()) {
            tEmployeeComplaint.setEmpFirstname(user.getFirstname());
        }
        if (Constants.StringIsNullOrEmpty(tEmployeeComplaint.getEmpLastname()) && tEmployeeComplaint.getComplIsAnonymous() == Constants.COMPLAINT_TYPE_DESC.NAMED.getCode()) {
            tEmployeeComplaint.setEmpLastname(user.getLastname());
        }
        if (Constants.StringIsNullOrEmpty(tEmployeeComplaint.getEmpPhone())) {
            tEmployeeComplaint.setEmpPhone("");
        }

        if(Constants.StringIsNullOrEmpty(tEmployeeComplaint.getInspAddr()))
        {
            tEmployeeComplaint.setInspAddr("");
        }
        if(Constants.StringIsNullOrEmpty(tEmployeeComplaint.getInspAddrTk()))
        {
            tEmployeeComplaint.setInspAddrTk("");
        }
        //Kallikratis topou elegxou
        if(Constants.StringIsNullOrEmpty(tEmployeeComplaint.getInspAddrP()))
        {
            tEmployeeComplaint.setInspAddrP("");
        }
        if(Constants.StringIsNullOrEmpty(tEmployeeComplaint.getInspAddrPe()))
        {
            tEmployeeComplaint.setInspAddrPe("");
        }
        if(Constants.StringIsNullOrEmpty(tEmployeeComplaint.getInspAddrD()))
        {
            tEmployeeComplaint.setInspAddrD("");
        }
        if(Constants.StringIsNullOrEmpty(tEmployeeComplaint.getInspAddrK()))
        {
            tEmployeeComplaint.setInspAddrK("");
        }
        if (Constants.StringIsNullOrEmpty(tEmployeeComplaint.getCompAfm())) {
            tEmployeeComplaint.setCompAfm("");
        }
        if (Constants.StringIsNullOrEmpty(tEmployeeComplaint.getCompName())) {
            tEmployeeComplaint.setCompName("");
        }
        if (Constants.StringIsNullOrEmpty(tEmployeeComplaint.getCompScope())) {
            tEmployeeComplaint.setCompScope("");
        }
        if (Constants.StringIsNullOrEmpty(tEmployeeComplaint.getCompEmpNum())) {
            tEmployeeComplaint.setCompEmpNum("");
        }
        if(Constants.IntegerIsNullOrEmpty(tEmployeeComplaint.getCompHasLabourUnion()))
        {
            tEmployeeComplaint.setCompHasLabourUnion(-1);
        }
        if(Constants.StringIsNullOrEmpty(tEmployeeComplaint.getCompPhone()))
        {
            tEmployeeComplaint.setCompPhone("");
        }
        if(Constants.StringIsNullOrEmpty(tEmployeeComplaint.getCompAddr()))
        {
            tEmployeeComplaint.setCompAddr("");
        }
        if(Constants.StringIsNullOrEmpty(tEmployeeComplaint.getCompAddrTk()))
        {
            tEmployeeComplaint.setCompAddrTk("");
        }
        //Kallikratis epixeirisis
        if(Constants.StringIsNullOrEmpty(tEmployeeComplaint.getCompAddrP()))
        {
            tEmployeeComplaint.setCompAddrP("");
        }
        if(Constants.StringIsNullOrEmpty(tEmployeeComplaint.getCompAddrPe()))
        {
            tEmployeeComplaint.setCompAddrPe("");
        }
        if(Constants.StringIsNullOrEmpty(tEmployeeComplaint.getCompAddrD()))
        {
            tEmployeeComplaint.setCompAddrD("");
        }
        if(Constants.StringIsNullOrEmpty(tEmployeeComplaint.getCompAddrK()))
        {
            tEmployeeComplaint.setCompAddrK("");
        }
        if(Constants.LongIsNullOrEmpty(tEmployeeComplaint.getDocIdAttached()))
        {
            tEmployeeComplaint.setDocIdAttached(-1L);
        }

        if(Constants.StringIsNullOrEmpty(tEmployeeComplaint.getRtstakLevel5()))
        {
            tEmployeeComplaint.setRtstakLevel5("0311-0"); //todo change it....
        }

        if (tEmployeeComplaint.getComplMatter().contains("[")) {
            tEmployeeComplaint.setComplMatter(tEmployeeComplaint.getComplMatter().replace("[", "").replace("]", "")); //Kallikratis
        }

        TEmployerBranchIKA tEmployerBranchIKA = tEmployerBranchIKARepo.findCompanyMainBranchByAfm(tEmployeeComplaint.getCompAfm());
        tEmployeeComplaint.setBranch0Id(tEmployerBranchIKA.getRgEbrEmpSepeId());

        return true;
    }

    //public boolean isAFMValid(TEmployeeComplaint tEmployeeComplaint)
    //{
    //    if(tEmployeeComplaint.getCompAfm())
    //        return false;
    //}
}