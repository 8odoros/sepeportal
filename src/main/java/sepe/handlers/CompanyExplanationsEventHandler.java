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
import sepe.domain.company.SpPtlCompExplanation;
import sepe.domain.company.TCompany;
import sepe.domain.company.TCompanyUserPrivilages;
import sepe.domain.company.TEmployerBranchIKA;
import sepe.dto.UserDTO;
import sepe.repository.company.CompanyRepository;
import sepe.repository.company.SpPtlCompExplanationRepo;
import sepe.repository.company.SpPtlCompanyUserPrivilagesRepository;
import sepe.repository.company.TEmployerBranchIKARepo;
import sepe.service.CompanyStoredProcedures;
import sepe.service.CurrentUserDetailsService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Annita on 5/11/2015.
 */

@Service
@RepositoryEventHandler(SpPtlCompExplanation.class)
public class CompanyExplanationsEventHandler {
    @Autowired
    private CompanyStoredProcedures companyStoredProcedures;

    @Autowired
    private SpPtlCompExplanationRepo spPtlCompExplanationRepo;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private TEmployerBranchIKARepo ikaRepo;

    @Autowired
    SpPtlCompanyUserPrivilagesRepository companyUserPrivilagesRepository;

    @PersistenceContext
    private EntityManager entityManager;

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyExplanationsEventHandler.class);

    @HandleBeforeSave
    public void handleBeforeSave(SpPtlCompExplanation spPtlCompExplanation) throws Exception {
        LOGGER.debug("Handling SpPtlCompExplanation for: User ID " + CurrentUserDetailsService.getCurrentUser().getUsername());

        UserDTO userDTO = CurrentUserDetailsService.getCurrentUserByPrincipal();
        if (userDTO.getRole() == Constants.USER_TYPE.COMPANY_USER.getCode()) {
            TCompanyUserPrivilages priv = companyUserPrivilagesRepository.findByUser(userDTO.getId());
            ArrayList<Integer> list = new ArrayList(Arrays.asList(priv.getPrivilages().split("\\s*,\\s*")));
            if(null != priv.getPrivilages() && list.contains(String.valueOf(Constants.COMPANY_SERVICE.EXPLANATION.getCode()))) {
                Long companyId = priv.getCompId();
                spPtlCompExplanation.setCompanyId(companyId);
            }
            else {
                throw new Exception("Ο χρήστης δεν έχει εξουσιοδότηση σε αυτό το είδος αίτησης.");
            }
        }
        else {
            spPtlCompExplanation.setCompanyId(CurrentUserDetailsService.getCurrentUser().getUserId());
        }

/*        SpPtlCompExplanation hasDisputeSubmitted = spPtlCompExplanationRepo.findOne(spPtlCompExplanation.getId());
        if(null != hasDisputeSubmitted && hasDisputeSubmitted.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode())
        {
            throw new Exception("To αίτημα σας έχει ήδη υποβληθεί.");
        }*/

        if (spPtlCompExplanation.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode())
            try {
                spPtlCompExplanation.setSubStatus(Constants.SUBMIT_STATUS.SAVED.getCode());
                spPtlCompExplanationRepo.save(spPtlCompExplanation);
                if (isCompanyExplanationValid(spPtlCompExplanation)) {
                    spPtlCompExplanation.setSubStatus(Constants.SUBMIT_STATUS.SUBMITTED.getCode());
                    (companyStoredProcedures).procIntArchiveCompExplanation(spPtlCompExplanation);
                }
            } catch (Exception e) {
                e.printStackTrace();
                spPtlCompExplanation.setSubStatus(Constants.SUBMIT_STATUS.SAVED.getCode());
                spPtlCompExplanationRepo.save(spPtlCompExplanation);
                throw new Exception("To αίτημα σας δεν υποβλήθηκε, έχει όμως αποθηκευθεί.");
            }
    }

    @HandleBeforeCreate
    public void handleBeforeCreates(SpPtlCompExplanation spPtlCompExplanation) throws Exception {
        LOGGER.debug("Handling SpPtlCompExplanation for: User ID " + CurrentUserDetailsService.getCurrentUser().getUsername());

        UserDTO userDTO = CurrentUserDetailsService.getCurrentUserByPrincipal();
        if (userDTO.getRole() == Constants.USER_TYPE.COMPANY_USER.getCode()) {
            TCompanyUserPrivilages priv = companyUserPrivilagesRepository.findByUser(userDTO.getId());
            ArrayList<Integer> list = new ArrayList(Arrays.asList(priv.getPrivilages().split("\\s*,\\s*")));
            if(null != priv.getPrivilages() && list.contains(String.valueOf(Constants.COMPANY_SERVICE.EXPLANATION.getCode()))) {
                Long companyId = priv.getCompId();
                spPtlCompExplanation.setCompanyId(companyId);
            }
            else {
                throw new Exception("Ο χρήστης δεν έχει εξουσιοδότηση σε αυτό το είδος αίτησης.");
            }
        }
        else {
            spPtlCompExplanation.setCompanyId(CurrentUserDetailsService.getCurrentUser().getUserId());
        }

        if (spPtlCompExplanation.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode())
            try {
                spPtlCompExplanation.setSubStatus(Constants.SUBMIT_STATUS.SAVED.getCode());
                spPtlCompExplanationRepo.save(spPtlCompExplanation);
                if (isCompanyExplanationValid(spPtlCompExplanation)) {
                    spPtlCompExplanation.setSubStatus(Constants.SUBMIT_STATUS.SUBMITTED.getCode());
                    (companyStoredProcedures).procIntArchiveCompExplanation(spPtlCompExplanation);
                }
            } catch (Exception e) {
                e.printStackTrace();
                spPtlCompExplanation.setSubStatus(Constants.SUBMIT_STATUS.SAVED.getCode());
                spPtlCompExplanationRepo.save(spPtlCompExplanation);
                throw new Exception("To αίτημα σας δεν υποβλήθηκε, έχει όμως αποθηκευθεί.");
            }
    }

    @HandleBeforeDelete
    public void handleBeforeDeletes(SpPtlCompExplanation spPtlCompExplanation) throws Exception {
        if (spPtlCompExplanation.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode()) {
            throw new Exception("To αίτημα σας δεν γίνεται να διαγραφεί επειδή είναι ήδη υποβεβλημένο.");
        }
    }

    public boolean isCompanyExplanationValid(SpPtlCompExplanation spPtlCompExplanation)
    {
        UserDTO user = CurrentUserDetailsService.getCurrentUser().getUserDTO();
        TCompany company = companyRepository.findOne(spPtlCompExplanation.getCompanyId());

        //get company missing fields from MainBranchIKA
        //TEmployerBranchIKA ika = ikaRepo.findCompanyMainBranchByAme(company.getAme());
        TEmployerBranchIKA ika = ikaRepo.findCompanyMainBranchByAfmAndAme(company.getAfm(),company.getAme());

        if(Constants.StringIsNullOrEmpty(spPtlCompExplanation.getCompAfm()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEbrTaxationNumber()))
                spPtlCompExplanation.setCompAfm("");
            else
                spPtlCompExplanation.setCompAfm(ika.getRgEbrTaxationNumber());
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompExplanation.getCompAme()))
        {
            if(Constants.StringIsNullOrEmpty(company.getAme()))
                spPtlCompExplanation.setCompAme("");
            else
                spPtlCompExplanation.setCompAme(company.getAme());
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompExplanation.getCompName()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEmpFullname()))
                spPtlCompExplanation.setCompName("");
            else
                spPtlCompExplanation.setCompName(ika.getRgEmpFullname());
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompExplanation.getCompPhone()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEbrPhoneNumber()))
                spPtlCompExplanation.setCompPhone("");
            else {
                if(ika.getRgEbrPhoneNumber().contains("-"))
                    ika.setRgEbrPhoneNumber(ika.getRgEbrPhoneNumber().replace("-",""));

                spPtlCompExplanation.setCompPhone(ika.getRgEbrPhoneNumber());
            }
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompExplanation.getCompAddr()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEbrAddressStreet()))
                spPtlCompExplanation.setCompAddr("");
            else
                spPtlCompExplanation.setCompAddr(ika.getRgEbrAddressStreet());
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompExplanation.getCompAddrTk()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEbrZipCode()))
                spPtlCompExplanation.setCompAddrTk("");
            else
                spPtlCompExplanation.setCompAddrTk(ika.getRgEbrZipCode());
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompExplanation.getCompAddrP()))
        {
            spPtlCompExplanation.setCompAddrP("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlCompExplanation.getCompAddrPe()))
        {
            spPtlCompExplanation.setCompAddrPe("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlCompExplanation.getCompAddrD()))
        {
            spPtlCompExplanation.setCompAddrD("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlCompExplanation.getCompAddrK()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEbrKallikratis()))
                spPtlCompExplanation.setCompAddrK("");
            else
                spPtlCompExplanation.setCompAddrK(ika.getRgEbrKallikratis());
        }

        if(Constants.LongIsNullOrEmpty(spPtlCompExplanation.getBranch0Id())
                || spPtlCompExplanation.getBranch0Id() == 0
                || spPtlCompExplanation.getBranch0Id() == -1)
        {
            if(Constants.LongIsNullOrEmpty(ika.getRgEbrEmpSepeId()))
                spPtlCompExplanation.setBranch0Id(new Long(-1));
            else
                spPtlCompExplanation.setBranch0Id(ika.getRgEbrEmpSepeId());
        }

        if(Constants.LongIsNullOrEmpty(spPtlCompExplanation.getBranch1Id()))
        {
            spPtlCompExplanation.setBranch1Id(new Long(-1));
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompExplanation.getDescr()))
        {
            spPtlCompExplanation.setDescr("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlCompExplanation.getTaFullname()))
        {
            spPtlCompExplanation.setTaFullname("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlCompExplanation.getIeFullname()))
        {
            spPtlCompExplanation.setIeFullname("");
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompExplanation.getAttachedDocDescr()))
        {
            spPtlCompExplanation.setAttachedDocDescr("");
        }

        if(Constants.LongIsNullOrEmpty(spPtlCompExplanation.getAttachedDocId()))
        {
            spPtlCompExplanation.setAttachedDocId(new Long(-1));
        }

        if(Constants.LongIsNullOrEmpty(spPtlCompExplanation.getCaseId()))
        {
            spPtlCompExplanation.setCaseId(new Long(-1));
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompExplanation.getControlStatementNum()))
        {
            spPtlCompExplanation.setControlStatementNum("");
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompExplanation.getInspectorName()))
        {
            spPtlCompExplanation.setInspectorName("");
        }

        if(Constants.TimestampIsNullOrEmpty(spPtlCompExplanation.getInspectionDate()))
        {
            spPtlCompExplanation.setInspectionDate(new Timestamp(0));
        }

        entityManager.detach(ika);
        return true;
    }
}