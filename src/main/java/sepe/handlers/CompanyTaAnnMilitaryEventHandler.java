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
import sepe.domain.company.*;
import sepe.dto.UserDTO;
import sepe.repository.company.*;
import sepe.service.CompanyStoredProcedures;
import sepe.service.CurrentUserDetailsService;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by dimitrisf on 5/07/2018.
 */

@Service
@RepositoryEventHandler(SpPtlCompTaAnnMilitary.class)
public class CompanyTaAnnMilitaryEventHandler {
    @Autowired
    private CompanyStoredProcedures companyStoredProcedures;

    @Autowired
    private SpPtlCompTaAnnMilitaryRepo spPtlCompTaAnnMilitaryRepo;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private TEmployerBranchIKARepo ikaRepo;

    @Autowired
    SpPtlCompanyUserPrivilagesRepository companyUserPrivilagesRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyTaAnnMilitaryEventHandler.class);

    @HandleBeforeSave
    public void handleBeforeSave(SpPtlCompTaAnnMilitary spPtlCompTaAnnMilitary) throws Exception {
        LOGGER.debug("Handling SpPtlCompTaAnnMilitary for: User ID " + CurrentUserDetailsService.getCurrentUser().getUsername());

        UserDTO userDTO = CurrentUserDetailsService.getCurrentUserByPrincipal();
        if (userDTO.getRole() == Constants.USER_TYPE.COMPANY_USER.getCode()) {
            TCompanyUserPrivilages priv = companyUserPrivilagesRepository.findByUser(userDTO.getId());
            ArrayList<Integer> list = new ArrayList(Arrays.asList(priv.getPrivilages().split("\\s*,\\s*")));
                if(null != priv.getPrivilages() && list.contains(String.valueOf(Constants.COMPANY_SERVICE.MILITARY.getCode()))) {
                    Long companyId = priv.getCompId();
                    spPtlCompTaAnnMilitary.setCompanyId(companyId);
                }
                else {
                    throw new Exception("Ο χρήστης δεν έχει εξουσιοδότηση σε αυτό το είδος αίτησης.");
                }
        }
        else {
            spPtlCompTaAnnMilitary.setCompanyId(CurrentUserDetailsService.getCurrentUser().getUserId());
        }

        if (spPtlCompTaAnnMilitary.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode())
            try {
                spPtlCompTaAnnMilitary.setSubStatus(Constants.SUBMIT_STATUS.SAVED.getCode());
                spPtlCompTaAnnMilitaryRepo.save(spPtlCompTaAnnMilitary);
                if (isTaAnnMilitaryValid(spPtlCompTaAnnMilitary)) {
                    spPtlCompTaAnnMilitary.setSubStatus(Constants.SUBMIT_STATUS.SUBMITTED.getCode());
                    (companyStoredProcedures).procIntArchiveCompTaAnnMilitary(spPtlCompTaAnnMilitary);

                }
            } catch (Exception e) {
                e.printStackTrace();
                spPtlCompTaAnnMilitary.setSubStatus(Constants.SUBMIT_STATUS.SAVED.getCode());
                spPtlCompTaAnnMilitaryRepo.save(spPtlCompTaAnnMilitary);
                throw new Exception("To αίτημα σας δεν υποβλήθηκε, έχει όμως αποθηκευθεί.");
            }
    }

    @HandleBeforeCreate
    public void handleBeforeCreates(SpPtlCompTaAnnMilitary spPtlCompTaAnnMilitary) throws Exception {
        LOGGER.debug("Handling SpPtlCompTaAnnMilitary for: User ID " + CurrentUserDetailsService.getCurrentUser().getUsername());

        UserDTO userDTO = CurrentUserDetailsService.getCurrentUserByPrincipal();
        if (userDTO.getRole() == Constants.USER_TYPE.COMPANY_USER.getCode()) {
            TCompanyUserPrivilages priv = companyUserPrivilagesRepository.findByUser(userDTO.getId());
            ArrayList<Integer> list = new ArrayList(Arrays.asList(priv.getPrivilages().split("\\s*,\\s*")));
                if(null != priv.getPrivilages() && list.contains(String.valueOf(Constants.COMPANY_SERVICE.MILITARY.getCode()))) {
                    Long companyId = priv.getCompId();
                    spPtlCompTaAnnMilitary.setCompanyId(companyId);
                }
                else {
                    throw new Exception("Ο χρήστης δεν έχει εξουσιοδότηση σε αυτό το είδος αίτησης.");
                }
        }
        else {
            spPtlCompTaAnnMilitary.setCompanyId(CurrentUserDetailsService.getCurrentUser().getUserId());
        }

        if (spPtlCompTaAnnMilitary.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode())
            try {
                spPtlCompTaAnnMilitary.setSubStatus(Constants.SUBMIT_STATUS.SAVED.getCode());
                spPtlCompTaAnnMilitaryRepo.save(spPtlCompTaAnnMilitary);
                if (isTaAnnMilitaryValid(spPtlCompTaAnnMilitary)) {
                    spPtlCompTaAnnMilitary.setSubStatus(Constants.SUBMIT_STATUS.SUBMITTED.getCode());
                    (companyStoredProcedures).procIntArchiveCompTaAnnMilitary(spPtlCompTaAnnMilitary);
                }
            } catch (Exception e) {
                e.printStackTrace();
                spPtlCompTaAnnMilitary.setSubStatus(Constants.SUBMIT_STATUS.SAVED.getCode());
                spPtlCompTaAnnMilitaryRepo.save(spPtlCompTaAnnMilitary);
                throw new Exception("To αίτημα σας δεν υποβλήθηκε, έχει όμως αποθηκευθεί.");
            }
    }

    @HandleBeforeDelete
    public void handleBeforeDeletes(SpPtlCompTaAnnMilitary spPtlCompTaAnnMilitary) throws Exception {
        if (spPtlCompTaAnnMilitary.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode()) {
            throw new Exception("To αίτημα σας δεν γίνεται να διαγραφεί επειδή είναι ήδη υποβεβλημένο.");
        }
    }

    public boolean isTaAnnMilitaryValid(SpPtlCompTaAnnMilitary spPtlCompTaAnnMilitary)
    {
        UserDTO user = CurrentUserDetailsService.getCurrentUser().getUserDTO();
        TCompany company = companyRepository.findOne(spPtlCompTaAnnMilitary.getCompanyId());
        TEmployerBranchIKA ika = ikaRepo.findCompanyMainBranchByAfmAndAme(company.getAfm(),company.getAme());

        if(Constants.StringIsNullOrEmpty(spPtlCompTaAnnMilitary.getReprFirstname()))
        {
            spPtlCompTaAnnMilitary.setReprFirstname("");
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompTaAnnMilitary.getReprLastname()))
        {
            spPtlCompTaAnnMilitary.setReprLastname("");
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompTaAnnMilitary.getReprPhone()))
        {
            spPtlCompTaAnnMilitary.setReprPhone("");
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompTaAnnMilitary.getReprMobile()))
        {
            spPtlCompTaAnnMilitary.setReprMobile("");
        }

        if(Constants.LongIsNullOrEmpty(spPtlCompTaAnnMilitary.getBranch0Id())
                || spPtlCompTaAnnMilitary.getBranch0Id() == 0
                || spPtlCompTaAnnMilitary.getBranch0Id() == -1)
        {
            if(Constants.LongIsNullOrEmpty(ika.getRgEbrEmpSepeId()))
                spPtlCompTaAnnMilitary.setBranch0Id(new Long(-1));
            else
                spPtlCompTaAnnMilitary.setBranch0Id(ika.getRgEbrEmpSepeId());
        }

        if(Constants.LongIsNullOrEmpty(spPtlCompTaAnnMilitary.getAttachedDocId()))
        {
            spPtlCompTaAnnMilitary.setAttachedDocId(new Long(-1));
        }

        if(Constants.LongIsNullOrEmpty(spPtlCompTaAnnMilitary.getRequestTypeId()))
        {
            spPtlCompTaAnnMilitary.setRequestTypeId(new Long(-1));
        }

        return true;
    }
}