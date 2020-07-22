package sepe.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.*;
import org.springframework.stereotype.Service;
import sepe.config.Constants;
import sepe.domain.company.*;
import sepe.dto.SpPtlCompJobRecrOffPersDTO;
import sepe.dto.UserDTO;
import sepe.repository.company.*;
import sepe.service.CompanyStoredProcedures;
import sepe.service.CurrentUserDetailsService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

@Service
@RepositoryEventHandler(SpPtlCompJobRecrOff.class)
public class CompanyJobRecrOffEventHandler {
    @Autowired
    private CompanyStoredProcedures companyStoredProcedures;

    @Autowired
    private SpPtlCompJobRecrOffRepo spPtlCompJobRecrOffRepo;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private TEmployerBranchIKARepo ikaRepo;

    @Autowired
    private SpPtlCompJobRecrOffPersRepo spPtlCompJobRecrOffPersRepo;

    @Autowired
    SpPtlCompanyUserPrivilagesRepository companyUserPrivilagesRepository;

    @PersistenceContext
    private EntityManager entityManager;

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyJobRecrOffEventHandler.class);

    @HandleBeforeSave
    public void handleBeforeSave(SpPtlCompJobRecrOff spPtlCompJobRecrOff) throws Exception {
        LOGGER.debug("Handling SpPtlCompJobRecrOff for: User ID " + CurrentUserDetailsService.getCurrentUser().getUsername());

        UserDTO userDTO = CurrentUserDetailsService.getCurrentUserByPrincipal();
        if (userDTO.getRole() == Constants.USER_TYPE.COMPANY_USER.getCode()) {
            TCompanyUserPrivilages priv = companyUserPrivilagesRepository.findByUser(userDTO.getId());
            ArrayList<Integer> list = new ArrayList(Arrays.asList(priv.getPrivilages().split("\\s*,\\s*")));
            if(null != priv.getPrivilages() && list.contains(String.valueOf(Constants.COMPANY_SERVICE.JOB_RECRUITMENT_OFFICE.getCode()))) {
                Long companyId = priv.getCompId();
                spPtlCompJobRecrOff.setCompanyId(companyId);
            }
            else {
                throw new Exception("Ο χρήστης δεν έχει εξουσιοδότηση σε αυτό το είδος αίτησης.");
            }
        }
        else {
            spPtlCompJobRecrOff.setCompanyId(CurrentUserDetailsService.getCurrentUser().getUserId());
        }

/*        SpPtlCompJobRecrOff hasDisputeSubmitted = spPtlCompJobRecrOffRepo.findOne(spPtlCompJobRecrOff.getId());
        if(null != hasDisputeSubmitted && hasDisputeSubmitted.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode())
        {
            throw new Exception("To αίτημα σας έχει ήδη υποβληθεί.");
        }*/

        /*SpPtlCompJobRecrOffPersDTO[] spPtlCompJobRecrOffPers = spPtlCompJobRecrOff.getPers();
        if(null != spPtlCompJobRecrOffPers) {
            HashSet<SpPtlCompJobRecrOffPers> compJobRecrOffPers = new HashSet<SpPtlCompJobRecrOffPers>();
            for (int i = 0; i < spPtlCompJobRecrOffPers.length; i++) {
                SpPtlCompJobRecrOffPers jobRecrOffPers = new SpPtlCompJobRecrOffPers();
                jobRecrOffPers.setCompanyId(spPtlCompJobRecrOff.getCompanyId());
                jobRecrOffPers.setCompJobRecrOff(spPtlCompJobRecrOff);
                jobRecrOffPers.setIncNum(spPtlCompJobRecrOffPers[i].getIncNum());
                jobRecrOffPers.setEmpFirstname(spPtlCompJobRecrOffPers[i].getEmpFirstname());
                jobRecrOffPers.setEmpLastname(spPtlCompJobRecrOffPers[i].getEmpLastname());
                jobRecrOffPers.setEmpCardNumber(spPtlCompJobRecrOffPers[i].getEmpCardNumber());
                jobRecrOffPers.setEmpSpeciality(spPtlCompJobRecrOffPers[i].getEmpSpeciality());
                jobRecrOffPers.setEmpCategoryId(spPtlCompJobRecrOffPers[i].getEmpCategoryId());
                jobRecrOffPers.setEmpEduLevelId(spPtlCompJobRecrOffPers[i].getEmpEduLevelId());
                jobRecrOffPers.setEmpAfm(spPtlCompJobRecrOffPers[i].getEmpAfm());
                jobRecrOffPers.setEmpAmka(spPtlCompJobRecrOffPers[i].getEmpAmka());
                jobRecrOffPers.setEmpSex(spPtlCompJobRecrOffPers[i].getEmpSex());
                jobRecrOffPers.setEmpAge(spPtlCompJobRecrOffPers[i].getEmpAge());
                jobRecrOffPers.setEmpPlacementNum(spPtlCompJobRecrOffPers[i].getEmpPlacementNum());
                jobRecrOffPers.setCompAfm(spPtlCompJobRecrOffPers[i].getCompAfm());
                jobRecrOffPers.setCompTitle(spPtlCompJobRecrOffPers[i].getCompTitle());
                jobRecrOffPers.setCompAddr(spPtlCompJobRecrOffPers[i].getCompAddr());
                jobRecrOffPers.setEmpCardType(spPtlCompJobRecrOffPers[i].getEmpCardType());
                compJobRecrOffPers.add(jobRecrOffPers);
            }
            spPtlCompJobRecrOff.setCompJobRecrOffPersons(compJobRecrOffPers);
        }*/

        if (spPtlCompJobRecrOff.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode())
            try {
                spPtlCompJobRecrOff.setSubStatus(Constants.SUBMIT_STATUS.SAVED.getCode());
                spPtlCompJobRecrOffRepo.save(spPtlCompJobRecrOff);
                if (isJobRecrOffValid(spPtlCompJobRecrOff)) {
                    spPtlCompJobRecrOff.setSubStatus(Constants.SUBMIT_STATUS.SUBMITTED.getCode());
                    (companyStoredProcedures).procIntArchiveComJobRecrOff(spPtlCompJobRecrOff);
                }
            } catch (Exception e) {
                e.printStackTrace();
                spPtlCompJobRecrOff.setSubStatus(Constants.SUBMIT_STATUS.SAVED.getCode());
                spPtlCompJobRecrOffRepo.save(spPtlCompJobRecrOff);
                throw new Exception("To αίτημα σας δεν υποβλήθηκε, έχει όμως αποθηκευθεί.");
            }
    }


    @HandleBeforeCreate
    public void handleBeforeCreates(SpPtlCompJobRecrOff spPtlCompJobRecrOff) throws Exception {
        LOGGER.debug("Handling SpPtlCompJobRecrOff for: User ID " + CurrentUserDetailsService.getCurrentUser().getUsername());

        UserDTO userDTO = CurrentUserDetailsService.getCurrentUserByPrincipal();
        if (userDTO.getRole() == Constants.USER_TYPE.COMPANY_USER.getCode()) {
            TCompanyUserPrivilages priv = companyUserPrivilagesRepository.findByUser(userDTO.getId());
            ArrayList<Integer> list = new ArrayList(Arrays.asList(priv.getPrivilages().split("\\s*,\\s*")));
            if(null != priv.getPrivilages() && list.contains(String.valueOf(Constants.COMPANY_SERVICE.JOB_RECRUITMENT_OFFICE.getCode()))) {
                Long companyId = priv.getCompId();
                spPtlCompJobRecrOff.setCompanyId(companyId);
            }
            else {
                throw new Exception("Ο χρήστης δεν έχει εξουσιοδότηση σε αυτό το είδος αίτησης.");
            }
        }
        else {
            spPtlCompJobRecrOff.setCompanyId(CurrentUserDetailsService.getCurrentUser().getUserId());
        }

        /*SpPtlCompJobRecrOffPersDTO[] spPtlCompJobRecrOffPers = spPtlCompJobRecrOff.getPers();
        if(null != spPtlCompJobRecrOffPers) {
            HashSet<SpPtlCompJobRecrOffPers> compJobRecrOffPers = new HashSet<SpPtlCompJobRecrOffPers>();
            for (int i = 0; i < spPtlCompJobRecrOffPers.length; i++) {
                SpPtlCompJobRecrOffPers jobRecrOffPers = new SpPtlCompJobRecrOffPers();
                jobRecrOffPers.setCompanyId(spPtlCompJobRecrOff.getCompanyId());
                jobRecrOffPers.setCompJobRecrOff(spPtlCompJobRecrOff);
                jobRecrOffPers.setIncNum(spPtlCompJobRecrOffPers[i].getIncNum());
                jobRecrOffPers.setEmpFirstname(spPtlCompJobRecrOffPers[i].getEmpFirstname());
                jobRecrOffPers.setEmpLastname(spPtlCompJobRecrOffPers[i].getEmpLastname());
                jobRecrOffPers.setEmpCardNumber(spPtlCompJobRecrOffPers[i].getEmpCardNumber());
                jobRecrOffPers.setEmpSpeciality(spPtlCompJobRecrOffPers[i].getEmpSpeciality());
                jobRecrOffPers.setEmpCategoryId(spPtlCompJobRecrOffPers[i].getEmpCategoryId());
                jobRecrOffPers.setEmpEduLevelId(spPtlCompJobRecrOffPers[i].getEmpEduLevelId());
                jobRecrOffPers.setEmpAfm(spPtlCompJobRecrOffPers[i].getEmpAfm());
                jobRecrOffPers.setEmpAmka(spPtlCompJobRecrOffPers[i].getEmpAmka());
                jobRecrOffPers.setEmpSex(spPtlCompJobRecrOffPers[i].getEmpSex());
                jobRecrOffPers.setEmpAge(spPtlCompJobRecrOffPers[i].getEmpAge());
                jobRecrOffPers.setEmpPlacementNum(spPtlCompJobRecrOffPers[i].getEmpPlacementNum());
                jobRecrOffPers.setCompAfm(spPtlCompJobRecrOffPers[i].getCompAfm());
                jobRecrOffPers.setCompTitle(spPtlCompJobRecrOffPers[i].getCompTitle());
                jobRecrOffPers.setCompAddr(spPtlCompJobRecrOffPers[i].getCompAddr());
                jobRecrOffPers.setEmpCardType(spPtlCompJobRecrOffPers[i].getEmpCardType());
                compJobRecrOffPers.add(jobRecrOffPers);
            }
            spPtlCompJobRecrOff.setCompJobRecrOffPersons(compJobRecrOffPers);
        }*/

        if (spPtlCompJobRecrOff.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode())
            try {
                spPtlCompJobRecrOff.setSubStatus(Constants.SUBMIT_STATUS.SAVED.getCode());
                spPtlCompJobRecrOffRepo.save(spPtlCompJobRecrOff);
                if (isJobRecrOffValid(spPtlCompJobRecrOff)) {
                    spPtlCompJobRecrOff.setSubStatus(Constants.SUBMIT_STATUS.SUBMITTED.getCode());
                    (companyStoredProcedures).procIntArchiveComJobRecrOff(spPtlCompJobRecrOff);
                }
            } catch (Exception e) {
                e.printStackTrace();
                spPtlCompJobRecrOff.setSubStatus(Constants.SUBMIT_STATUS.SAVED.getCode());
                spPtlCompJobRecrOffRepo.save(spPtlCompJobRecrOff);
                throw new Exception("To αίτημα σας δεν υποβλήθηκε, έχει όμως αποθηκευθεί.");
            }
    }

    @HandleBeforeDelete
    public void handleBeforeDeletes(SpPtlCompJobRecrOff spPtlCompJobRecrOff) throws Exception {
        if (spPtlCompJobRecrOff.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode()) {
            throw new Exception("To αίτημα σας δεν γίνεται να διαγραφεί επειδή είναι ήδη υποβεβλημένο.");
        }
    }

    public boolean isJobRecrOffValid(SpPtlCompJobRecrOff spPtlCompJobRecrOff)
    {
        UserDTO user = CurrentUserDetailsService.getCurrentUser().getUserDTO();
        TCompany company = companyRepository.findOne(spPtlCompJobRecrOff.getCompanyId());

        //get company missing fields from MainBranchIKA
        //TEmployerBranchIKA ika = ikaRepo.findCompanyMainBranchByAme(company.getAme());
        TEmployerBranchIKA ika = ikaRepo.findCompanyMainBranchByAfmAndAme(company.getAfm()
                ,company.getAme());

        if(Constants.StringIsNullOrEmpty(spPtlCompJobRecrOff.getCompAfm()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEbrTaxationNumber()))
                spPtlCompJobRecrOff.setCompAfm("");
            else
                spPtlCompJobRecrOff.setCompAfm(ika.getRgEbrTaxationNumber());
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompJobRecrOff.getCompName()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEmpFullname()))
                spPtlCompJobRecrOff.setCompName("");
            else
                spPtlCompJobRecrOff.setCompName(ika.getRgEmpFullname());
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompJobRecrOff.getCompPhone()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEbrPhoneNumber()))
                spPtlCompJobRecrOff.setCompPhone("");
            else {
                if(ika.getRgEbrPhoneNumber().contains("-"))
                    ika.setRgEbrPhoneNumber(ika.getRgEbrPhoneNumber().replace("-",""));

                spPtlCompJobRecrOff.setCompPhone(ika.getRgEbrPhoneNumber());
            }
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompJobRecrOff.getCompAddr()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEbrAddressStreet()))
                spPtlCompJobRecrOff.setCompAddr("");
            else
                spPtlCompJobRecrOff.setCompAddr(ika.getRgEbrAddressStreet());
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompJobRecrOff.getCompAddrTk()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEbrZipCode()))
                spPtlCompJobRecrOff.setCompAddrTk("");
            else
                spPtlCompJobRecrOff.setCompAddrTk(ika.getRgEbrZipCode());
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompJobRecrOff.getCompAddrP()))
        {
            spPtlCompJobRecrOff.setCompAddrP("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlCompJobRecrOff.getCompAddrPe()))
        {
            spPtlCompJobRecrOff.setCompAddrPe("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlCompJobRecrOff.getCompAddrD()))
        {
            spPtlCompJobRecrOff.setCompAddrD("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlCompJobRecrOff.getCompAddrK()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEbrKallikratis()))
                spPtlCompJobRecrOff.setCompAddrK("");
            else
                spPtlCompJobRecrOff.setCompAddrK(ika.getRgEbrKallikratis());
        }

        if(Constants.LongIsNullOrEmpty(spPtlCompJobRecrOff.getCompEbrBranch0Id())
                || spPtlCompJobRecrOff.getCompEbrBranch0Id() == 0
                || spPtlCompJobRecrOff.getCompEbrBranch0Id() == -1)
        {
            if(Constants.LongIsNullOrEmpty(ika.getRgEbrEmpSepeId()))
                spPtlCompJobRecrOff.setCompEbrBranch0Id(new Long(-1));
            else
                spPtlCompJobRecrOff.setCompEbrBranch0Id(ika.getRgEbrEmpSepeId());
        }

        if(Constants.LongIsNullOrEmpty(spPtlCompJobRecrOff.getCompEbrBranchId()))
        {
            spPtlCompJobRecrOff.setCompEbrBranchId(new Long(-1));
        }

        if(spPtlCompJobRecrOff.getRequestEmpty()== null)
        {
            spPtlCompJobRecrOff.setRequestEmpty(BigInteger.valueOf(-1));
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompJobRecrOff.getRequestYear()))
        {
            spPtlCompJobRecrOff.setRequestYear("");
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompJobRecrOff.getRequestHalfYear()))
        {
            spPtlCompJobRecrOff.setRequestHalfYear("");
        }

        if(Constants.LongIsNullOrEmpty(spPtlCompJobRecrOff.getCompDoyId()))
        {
            if(Constants.LongIsNullOrEmpty(Long.parseLong(ika.getRgEbrTaxationOfficeCode())))
                spPtlCompJobRecrOff.setCompDoyId(new Long(-1));
            else
                spPtlCompJobRecrOff.setCompDoyId(Long.parseLong(ika.getRgEbrTaxationOfficeCode()));
        }

        if(Constants.LongIsNullOrEmpty(spPtlCompJobRecrOff.getCompLegalFormId()))
        {
            if (ika.getRgEbrLegalCategoryCode() != null) {
                if(Constants.LongIsNullOrEmpty(Long.parseLong(ika.getRgEbrLegalCategoryCode())))
                    spPtlCompJobRecrOff.setCompLegalFormId(new Long(-1));
                else
                    spPtlCompJobRecrOff.setCompLegalFormId(Long.parseLong(ika.getRgEbrLegalCategoryCode()));
            } else {
                spPtlCompJobRecrOff.setCompLegalFormId(new Long(-1));
            }
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompJobRecrOff.getCompKad()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEbrSecStakod5()))
                spPtlCompJobRecrOff.setCompKad("");
            else
                spPtlCompJobRecrOff.setCompKad(ika.getRgEbrSecStakod5());
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompJobRecrOff.getNotes()))
        {
            spPtlCompJobRecrOff.setNotes("");
        }

        entityManager.detach(ika);
        return true;
    }
}