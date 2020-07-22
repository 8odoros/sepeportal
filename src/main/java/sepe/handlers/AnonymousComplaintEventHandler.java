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
import sepe.domain.citizen.SpPtlAnonymousComplaint;
import sepe.domain.company.TEmployerBranchIKA;
import sepe.repository.citizen.AnonymousComplaintRepo;
import sepe.repository.company.TEmployerBranchIKARepo;
import sepe.service.UserStoredProcedures;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@RepositoryEventHandler(SpPtlAnonymousComplaint.class)
public class AnonymousComplaintEventHandler {
    @Autowired
    private UserStoredProcedures userStoredProcedures;

    @Autowired
    private AnonymousComplaintRepo anonymousComplaintRepo;

    @Autowired
    private TEmployerBranchIKARepo tEmployerBranchIKARepo;

    private static final Logger LOGGER = LoggerFactory.getLogger(AnonymousComplaintEventHandler.class);

    @HandleBeforeSave
    public void handleBeforeSave(SpPtlAnonymousComplaint spPtlAnonymousComplaint) throws Exception {
        LOGGER.debug("Handling SpPtlAnonymousComplaint...");

        try {
            if (isAnonymousComplaintValid(spPtlAnonymousComplaint))
                (userStoredProcedures).procIntArchiveComplaintAnonymous(spPtlAnonymousComplaint);
        } catch (Exception e) {
            e.printStackTrace();
            anonymousComplaintRepo.save(spPtlAnonymousComplaint);
            throw new Exception("To αίτημα σας δεν υποβλήθηκε, προσπαθήστε ξανά.");
        }
    }

    @HandleBeforeCreate
    public void handleBeforeCreate(SpPtlAnonymousComplaint spPtlAnonymousComplaint) throws Exception {
        LOGGER.debug("Handling EmployeeComplaint");

        try {
            if (isAnonymousComplaintValid(spPtlAnonymousComplaint))
                (userStoredProcedures).procIntArchiveComplaintAnonymous(spPtlAnonymousComplaint);
        } catch (Exception e) {
            e.printStackTrace();
            anonymousComplaintRepo.save(spPtlAnonymousComplaint);
            throw new Exception("To αίτημα σας δεν υποβλήθηκε, προσπαθήστε ξανά.");
        }
    }

    @HandleBeforeDelete
    public void handleBeforeDeletes(SpPtlAnonymousComplaint spPtlAnonymousComplaint) throws Exception {
            throw new Exception("To αίτημα σας δεν γίνεται να διαγραφεί επειδή είναι ήδη υποβεβλημένο.");
    }

    public boolean isAnonymousComplaintValid(SpPtlAnonymousComplaint spPtlAnonymousComplaint)
    {
        //Texniki epithewrisi
        if(Constants.IntegerIsNullOrEmpty(spPtlAnonymousComplaint.getComplInvlovesSafetyInsp()))
        {
            spPtlAnonymousComplaint.setComplInvlovesSafetyInsp(-1);
        }
        //Koinoniki epithewrisi
        if(Constants.IntegerIsNullOrEmpty(spPtlAnonymousComplaint.getComplInvolvesLabRelations()))
        {
            spPtlAnonymousComplaint.setComplInvolvesLabRelations(-1);
        }
        if(Constants.StringIsNullOrEmpty(spPtlAnonymousComplaint.getComplDescr()))
        {
            spPtlAnonymousComplaint.setComplDescr("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlAnonymousComplaint.getComplAddressDesc()))
        {
            spPtlAnonymousComplaint.setComplAddressDesc("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlAnonymousComplaint.getComplInspTime()))
        {
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            spPtlAnonymousComplaint.setComplInspTime((dateFormat.format(date)).toString());
        }

        if (Constants.StringIsNullOrEmpty(spPtlAnonymousComplaint.getEmpFirstname())) {
            spPtlAnonymousComplaint.setEmpFirstname("");
        }
        if (Constants.StringIsNullOrEmpty(spPtlAnonymousComplaint.getEmpLastname())) {
            spPtlAnonymousComplaint.setEmpLastname("");
        }
        if (Constants.StringIsNullOrEmpty(spPtlAnonymousComplaint.getEmpPhone())) {
            spPtlAnonymousComplaint.setEmpPhone("");
        }

        if(Constants.StringIsNullOrEmpty(spPtlAnonymousComplaint.getInspAddr()))
        {
            spPtlAnonymousComplaint.setInspAddr("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlAnonymousComplaint.getInspAddrTk()))
        {
            spPtlAnonymousComplaint.setInspAddrTk("");
        }
        //Kallikratis topou elegxou
        if(Constants.StringIsNullOrEmpty(spPtlAnonymousComplaint.getInspAddrP()))
        {
            spPtlAnonymousComplaint.setInspAddrP("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlAnonymousComplaint.getInspAddrPe()))
        {
            spPtlAnonymousComplaint.setInspAddrPe("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlAnonymousComplaint.getInspAddrD()))
        {
            spPtlAnonymousComplaint.setInspAddrD("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlAnonymousComplaint.getInspAddrK()))
        {
            spPtlAnonymousComplaint.setInspAddrK("");
        }
        if (Constants.StringIsNullOrEmpty(spPtlAnonymousComplaint.getCompAfm())) {
            spPtlAnonymousComplaint.setCompAfm("");
        }
        if (Constants.StringIsNullOrEmpty(spPtlAnonymousComplaint.getCompName())) {
            spPtlAnonymousComplaint.setCompName("");
        }
        if (Constants.StringIsNullOrEmpty(spPtlAnonymousComplaint.getCompScope())) {
            spPtlAnonymousComplaint.setCompScope("");
        }
        if (Constants.StringIsNullOrEmpty(spPtlAnonymousComplaint.getCompEmpNum())) {
            spPtlAnonymousComplaint.setCompEmpNum("");
        }
        if(Constants.IntegerIsNullOrEmpty(spPtlAnonymousComplaint.getCompHasLabourUnion()))
        {
            spPtlAnonymousComplaint.setCompHasLabourUnion(-1);
        }
        if(Constants.StringIsNullOrEmpty(spPtlAnonymousComplaint.getCompPhone()))
        {
            spPtlAnonymousComplaint.setCompPhone("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlAnonymousComplaint.getCompAddr()))
        {
            spPtlAnonymousComplaint.setCompAddr("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlAnonymousComplaint.getCompAddrTk()))
        {
            spPtlAnonymousComplaint.setCompAddrTk("");
        }
        //Kallikratis epixeirisis
        if(Constants.StringIsNullOrEmpty(spPtlAnonymousComplaint.getCompAddrP()))
        {
            spPtlAnonymousComplaint.setCompAddrP("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlAnonymousComplaint.getCompAddrPe()))
        {
            spPtlAnonymousComplaint.setCompAddrPe("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlAnonymousComplaint.getCompAddrD()))
        {
            spPtlAnonymousComplaint.setCompAddrD("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlAnonymousComplaint.getCompAddrK()))
        {
            spPtlAnonymousComplaint.setCompAddrK("");
        }
        if(Constants.LongIsNullOrEmpty(spPtlAnonymousComplaint.getDocIdAttached()))
        {
            spPtlAnonymousComplaint.setDocIdAttached(-1L);
        }

        if(Constants.StringIsNullOrEmpty(spPtlAnonymousComplaint.getRtstakLevel5()))
        {
            spPtlAnonymousComplaint.setRtstakLevel5(""); //todo change it....
        }

        if (spPtlAnonymousComplaint.getComplMatter().contains("[")) {
            spPtlAnonymousComplaint.setComplMatter(spPtlAnonymousComplaint.getComplMatter().replace("[", "").replace("]", "")); //Kallikratis
        }

        TEmployerBranchIKA tEmployerBranchIKA = tEmployerBranchIKARepo.findCompanyMainBranchByAfm(spPtlAnonymousComplaint.getCompAfm());
        if (tEmployerBranchIKA != null) {
            spPtlAnonymousComplaint.setBranch0Id(tEmployerBranchIKA.getRgEbrEmpSepeId());
        } else {
            spPtlAnonymousComplaint.setBranch0Id(0L);
        }
        //spPtlAnonymousComplaint.setBranch0Id(tEmployerBranchIKA.getRgEbrEmpSepeId());


        return true;
    }

    //public boolean isAFMValid(SpPtlAnonymousComplaint spPtlAnonymousComplaint)
    //{
    //    if(spPtlAnonymousComplaint.getCompAfm())
    //        return false;
    //}
}