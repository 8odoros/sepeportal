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
import sepe.domain.company.SpPtlCompanyIllness;
import sepe.domain.company.TCompany;
import sepe.domain.company.TCompanyUserPrivilages;
import sepe.domain.company.TEmployerBranchIKA;
import sepe.dto.UserDTO;
import sepe.repository.company.CompanyRepository;
import sepe.repository.company.SpPtlCompanyIllnessRepo;
import sepe.repository.company.SpPtlCompanyUserPrivilagesRepository;
import sepe.repository.company.TEmployerBranchIKARepo;
import sepe.service.CompanyStoredProcedures;
import sepe.service.CurrentUserDetailsService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;

@Service
@RepositoryEventHandler(SpPtlCompanyIllness.class)
public class CompanyIllnessEventHandler {
    @Autowired
    private CompanyStoredProcedures companyStoredProcedures;

    @Autowired
    private SpPtlCompanyIllnessRepo spPtlCompanyIllnessRepo;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private TEmployerBranchIKARepo ikaRepo;

    @Autowired
    SpPtlCompanyUserPrivilagesRepository companyUserPrivilagesRepository;

    @PersistenceContext
    private EntityManager entityManager;

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyIllnessEventHandler.class);

    @HandleBeforeSave
    public void handleBeforeSave(SpPtlCompanyIllness spPtlCompanyIllness) throws Exception {
        LOGGER.debug("Handling SpPtlCompanyIllness for: User ID " + CurrentUserDetailsService.getCurrentUser().getUsername());

        UserDTO userDTO = CurrentUserDetailsService.getCurrentUserByPrincipal();
        if (userDTO.getRole() == Constants.USER_TYPE.COMPANY_USER.getCode()) {
            TCompanyUserPrivilages priv = companyUserPrivilagesRepository.findByUser(userDTO.getId());
            ArrayList<Integer> list = new ArrayList(Arrays.asList(priv.getPrivilages().split("\\s*,\\s*")));
            if(null != priv.getPrivilages() && list.contains(String.valueOf(Constants.COMPANY_SERVICE.ILLNESS.getCode()))) {
                Long companyId = priv.getCompId();
                spPtlCompanyIllness.setCompanyId(companyId);
            }
            else {
                throw new Exception("Ο χρήστης δεν έχει εξουσιοδότηση σε αυτό το είδος αίτησης.");
            }
        }
        else {
            spPtlCompanyIllness.setCompanyId(CurrentUserDetailsService.getCurrentUser().getUserId());
        }

/*        SpPtlCompanyIllness hasDisputeSubmitted = spPtlCompanyIllnessRepo.findOne(spPtlCompanyIllness.getId());
        if(null != hasDisputeSubmitted && hasDisputeSubmitted.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode())
        {
            throw new Exception("To αίτημα σας έχει ήδη υποβληθεί.");
        }*/

        if (spPtlCompanyIllness.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode())
            try {
                spPtlCompanyIllness.setSubStatus(Constants.SUBMIT_STATUS.SAVED.getCode());
                spPtlCompanyIllnessRepo.save(spPtlCompanyIllness);
                if (isIllnessValid(spPtlCompanyIllness)) {
                    spPtlCompanyIllness.setSubStatus(Constants.SUBMIT_STATUS.SUBMITTED.getCode());
                    (companyStoredProcedures).procIntArchiveIllness(spPtlCompanyIllness);
                }
            } catch (Exception e) {
                e.printStackTrace();
                spPtlCompanyIllness.setSubStatus(Constants.SUBMIT_STATUS.SAVED.getCode());
                spPtlCompanyIllnessRepo.save(spPtlCompanyIllness);
                throw new Exception("To αίτημα σας δεν υποβλήθηκε, έχει όμως αποθηκευθεί.");
            }
    }

    @HandleBeforeCreate
    public void handleBeforeCreates(SpPtlCompanyIllness spPtlCompanyIllness) throws Exception {
        LOGGER.debug("Handling SpPtlCompanyIllness for: User ID " + CurrentUserDetailsService.getCurrentUser().getUsername());

        UserDTO userDTO = CurrentUserDetailsService.getCurrentUserByPrincipal();
        if (userDTO.getRole() == Constants.USER_TYPE.COMPANY_USER.getCode()) {
            TCompanyUserPrivilages priv = companyUserPrivilagesRepository.findByUser(userDTO.getId());
            ArrayList<Integer> list = new ArrayList(Arrays.asList(priv.getPrivilages().split("\\s*,\\s*")));
            if(null != priv.getPrivilages() && list.contains(String.valueOf(Constants.COMPANY_SERVICE.ILLNESS.getCode()))) {
                Long companyId = priv.getCompId();
                spPtlCompanyIllness.setCompanyId(companyId);
            }
            else {
                throw new Exception("Ο χρήστης δεν έχει εξουσιοδότηση σε αυτό το είδος αίτησης.");
            }
        }
        else {
            spPtlCompanyIllness.setCompanyId(CurrentUserDetailsService.getCurrentUser().getUserId());
        }

        if (spPtlCompanyIllness.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode())
            try {
                spPtlCompanyIllness.setSubStatus(Constants.SUBMIT_STATUS.SAVED.getCode());
                spPtlCompanyIllnessRepo.save(spPtlCompanyIllness);
                if (isIllnessValid(spPtlCompanyIllness))
                    spPtlCompanyIllness.setSubStatus(Constants.SUBMIT_STATUS.SUBMITTED.getCode());
                    (companyStoredProcedures).procIntArchiveIllness(spPtlCompanyIllness);
            } catch (Exception e) {
                e.printStackTrace();
                spPtlCompanyIllness.setSubStatus(Constants.SUBMIT_STATUS.SAVED.getCode());
                spPtlCompanyIllnessRepo.save(spPtlCompanyIllness);
                throw new Exception("To αίτημα σας δεν υποβλήθηκε, έχει όμως αποθηκευθεί.");
            }
    }

    @HandleBeforeDelete
    public void handleBeforeDeletes(SpPtlCompanyIllness spPtlCompanyIllness) throws Exception {
        if (spPtlCompanyIllness.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode()) {
            throw new Exception("To αίτημα σας δεν γίνεται να διαγραφεί επειδή είναι ήδη υποβεβλημένο.");
        }
    }

    public boolean isIllnessValid(SpPtlCompanyIllness spPtlCompanyIllness)
    {
        UserDTO user = CurrentUserDetailsService.getCurrentUser().getUserDTO();
        final TCompany company = companyRepository.findOne(spPtlCompanyIllness.getCompanyId());

        //TEmployerBranchIKA ika = ikaRepo.findCompanyMainBranchByAme(company.getAme());
        TEmployerBranchIKA ika = ikaRepo.findCompanyMainBranchByAfmAndAme(company.getAfm(),company.getAme());

        if(Constants.StringIsNullOrEmpty(spPtlCompanyIllness.getCompTaxNumber()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEbrTaxationNumber()))
                spPtlCompanyIllness.setCompTaxNumber("");
            else
                spPtlCompanyIllness.setCompTaxNumber(ika.getRgEbrTaxationNumber());
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompanyIllness.getCompFullName()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEmpFullname()))
                spPtlCompanyIllness.setCompFullName("");
            else
                spPtlCompanyIllness.setCompFullName(ika.getRgEmpFullname());
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompanyIllness.getCompPhone()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEbrPhoneNumber()))
                spPtlCompanyIllness.setCompPhone("");
            else {
                if(ika.getRgEbrPhoneNumber().contains("-"))
                    ika.setRgEbrPhoneNumber(ika.getRgEbrPhoneNumber().replace("-",""));

                spPtlCompanyIllness.setCompPhone(ika.getRgEbrPhoneNumber());
            }
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompanyIllness.getCompAddr()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEbrAddressStreet()))
                spPtlCompanyIllness.setCompAddr("");
            else
                spPtlCompanyIllness.setCompAddr(ika.getRgEbrAddressStreet());
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompanyIllness.getCompAddrTk()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEbrZipCode()))
                spPtlCompanyIllness.setCompAddrTk("");
            else
                spPtlCompanyIllness.setCompAddrTk(ika.getRgEbrZipCode());
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompanyIllness.getCompAddrP()))
        {
            spPtlCompanyIllness.setCompAddrP("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlCompanyIllness.getCompAddrPe()))
        {
            spPtlCompanyIllness.setCompAddrPe("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlCompanyIllness.getCompAddrD()))
        {
            spPtlCompanyIllness.setCompAddrD("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlCompanyIllness.getCompAddrK()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEbrKallikratis()))
                spPtlCompanyIllness.setCompAddrK("");
            else
                spPtlCompanyIllness.setCompAddrK(ika.getRgEbrKallikratis());
        }

        if(Constants.LongIsNullOrEmpty(spPtlCompanyIllness.getCompEbrBranch0Id())
                || spPtlCompanyIllness.getCompEbrBranch0Id() == 0
                || spPtlCompanyIllness.getCompEbrBranch0Id() == -1)
        {
            if(Constants.LongIsNullOrEmpty(ika.getRgEbrEmpSepeId()))
                spPtlCompanyIllness.setCompEbrBranch0Id(new Long(-1));
            else
                spPtlCompanyIllness.setCompEbrBranch0Id(ika.getRgEbrEmpSepeId());
        }

        if(Constants.LongIsNullOrEmpty(spPtlCompanyIllness.getCompEbrBranchId()))
        {
            spPtlCompanyIllness.setCompEbrBranchId(new Long(-1));
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompanyIllness.getCompAme()))
        {
            if(Constants.StringIsNullOrEmpty(company.getAme()))
                spPtlCompanyIllness.setCompAme("");
            else
                spPtlCompanyIllness.setCompAme(company.getAme());
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompanyIllness.getEmpAmkaNumber()))
        {
            spPtlCompanyIllness.setEmpAmkaNumber("");
        }

        if(Constants.LongIsNullOrEmpty(spPtlCompanyIllness.getEmpInsuranceBureauCode()))
        {
            spPtlCompanyIllness.setEmpInsuranceBureauCode(new Long(-1));
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompanyIllness.getEmpTaxNumber()))
        {
            spPtlCompanyIllness.setEmpTaxNumber("");
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompanyIllness.getEmpSurname()))
        {
            spPtlCompanyIllness.setEmpSurname("");
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompanyIllness.getEmpName()))
        {
            spPtlCompanyIllness.setEmpName("");
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompanyIllness.getEmpSpecialty()))
        {
            spPtlCompanyIllness.setEmpSpecialty("");
        }

        if(Constants.IntegerIsNullOrEmpty(spPtlCompanyIllness.getEmpSexDesc()))
        {
            spPtlCompanyIllness.setEmpSexDesc(-1);
        }

        if(Constants.LongIsNullOrEmpty(spPtlCompanyIllness.getEmpMaritalStatusCd()))
        {
            spPtlCompanyIllness.setEmpMaritalStatusCd(new Long(-1));
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompanyIllness.getEmpAge()))
        {
            spPtlCompanyIllness.setEmpAge("");
        }

        if(Constants.TimestampIsNullOrEmpty(spPtlCompanyIllness.getEmpBirthdate()))
        {
            spPtlCompanyIllness.setEmpBirthdate(new Timestamp(0));
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompanyIllness.getEmpCitizenshipCd()))
        {
            spPtlCompanyIllness.setEmpCitizenshipCd("");
        }

        if(Constants.TimestampIsNullOrEmpty(spPtlCompanyIllness.getEmpRecruitmentDate()))
        {
            spPtlCompanyIllness.setEmpRecruitmentDate(new Timestamp(0));
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompanyIllness.getEmpTimeCode()))
        {
            spPtlCompanyIllness.setEmpTimeCode("");
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompanyIllness.getEmpAddr()))
        {
            spPtlCompanyIllness.setEmpAddr("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlCompanyIllness.getEmpAddrTk()))
        {
            spPtlCompanyIllness.setEmpAddrTk("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlCompanyIllness.getEmpAddrP()))
        {
            spPtlCompanyIllness.setEmpAddrP("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlCompanyIllness.getEmpAddrPe()))
        {
            spPtlCompanyIllness.setEmpAddrPe("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlCompanyIllness.getEmpAddrD()))
        {
            spPtlCompanyIllness.setEmpAddrD("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlCompanyIllness.getEmpAddrK()))
        {
            spPtlCompanyIllness.setEmpAddrK("");
        }

        if(Constants.LongIsNullOrEmpty(spPtlCompanyIllness.getEmpCardType()))
        {
            spPtlCompanyIllness.setEmpCardType(new Long(-1));
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompanyIllness.getEmpCardNumber()))
        {
            spPtlCompanyIllness.setEmpCardNumber("");
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompanyIllness.getEmpPhone()))
        {
            if(Constants.StringIsNullOrEmpty(user.getPhone()))
                spPtlCompanyIllness.setEmpPhone("");
            else
                spPtlCompanyIllness.setEmpPhone(user.getPhone());
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompanyIllness.getEmpMobile()))
        {
            if(Constants.StringIsNullOrEmpty(user.getMobile()))
                spPtlCompanyIllness.setEmpMobile("");
            else
                spPtlCompanyIllness.setEmpMobile(user.getMobile());
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompanyIllness.getIllnessComments()))
        {
            spPtlCompanyIllness.setIllnessComments("");
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompanyIllness.getElementsComments()))
        {
            spPtlCompanyIllness.setElementsComments("");
        }

        if(Constants.TimestampIsNullOrEmpty(spPtlCompanyIllness.getDiagnosisDate()))
        {
            spPtlCompanyIllness.setDiagnosisDate(new Timestamp(0));
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompanyIllness.getInfestationComments()))
        {
            spPtlCompanyIllness.setInfestationComments("");
        }

        if(Constants.TimestampIsNullOrEmpty(spPtlCompanyIllness.getPrDiagnosisDate()))
        {
            spPtlCompanyIllness.setPrDiagnosisDate(new Timestamp(0));
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompanyIllness.getFactorComments()))
        {
            spPtlCompanyIllness.setFactorComments("");
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompanyIllness.getComments()))
        {
            spPtlCompanyIllness.setComments("");
        }

        if(Constants.LongIsNullOrEmpty(spPtlCompanyIllness.getAttachedDocId()))
        {
            spPtlCompanyIllness.setAttachedDocId(new Long(-1));
        }

        entityManager.detach(ika);
        return true;
    }
}