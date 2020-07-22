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
import sepe.domain.company.SpPtlCompDisputeNeg;
import sepe.domain.company.TCompany;
import sepe.domain.company.TCompanyUserPrivilages;
import sepe.domain.company.TEmployerBranchIKA;
import sepe.dto.UserDTO;
import sepe.repository.company.CompanyRepository;
import sepe.repository.company.SpPtlCompDisputeNegRepo;
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
@RepositoryEventHandler(SpPtlCompDisputeNeg.class)
public class CompanyDisputeNegEventHandler {
    @Autowired
    private CompanyStoredProcedures companyStoredProcedures;

    @Autowired
    private SpPtlCompDisputeNegRepo spPtlCompDisputeNegRepo;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private TEmployerBranchIKARepo ikaRepo;

    @Autowired
    SpPtlCompanyUserPrivilagesRepository companyUserPrivilagesRepository;

    @PersistenceContext
    private EntityManager entityManager;

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyDisputeNegEventHandler.class);

    @HandleBeforeSave
    public void handleBeforeSave(SpPtlCompDisputeNeg spPtlCompDisputeNeg) throws Exception {
        LOGGER.debug("Handling SpPtlCompDisputeNeg for: User ID " + CurrentUserDetailsService.getCurrentUser().getUsername());

        UserDTO userDTO = CurrentUserDetailsService.getCurrentUserByPrincipal();
        if (userDTO.getRole() == Constants.USER_TYPE.COMPANY_USER.getCode()) {
            TCompanyUserPrivilages priv = companyUserPrivilagesRepository.findByUser(userDTO.getId());
            ArrayList<Integer> list = new ArrayList(Arrays.asList(priv.getPrivilages().split("\\s*,\\s*")));
            if(null != priv.getPrivilages() && list.contains(String.valueOf(Constants.COMPANY_SERVICE.DISPUTE_NEG.getCode()))) {
                Long companyId = priv.getCompId();
                spPtlCompDisputeNeg.setCompanyId(companyId);
            }
            else {
                throw new Exception("Ο χρήστης δεν έχει εξουσιοδότηση σε αυτό το είδος αίτησης.");
            }
        }
        else {
            spPtlCompDisputeNeg.setCompanyId(CurrentUserDetailsService.getCurrentUser().getUserId());
        }

/*        SpPtlCompDisputeNeg hasDisputeSubmitted = spPtlCompDisputeNegRepo.findOne(spPtlCompDisputeNeg.getId());
        if(null != hasDisputeSubmitted && hasDisputeSubmitted.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode())
        {
            throw new Exception("To αίτημα σας έχει ήδη υποβληθεί.");
        }*/

        if (spPtlCompDisputeNeg.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode())
            try {
                spPtlCompDisputeNeg.setSubStatus(Constants.SUBMIT_STATUS.SAVED.getCode());
                spPtlCompDisputeNegRepo.save(spPtlCompDisputeNeg);
                if (isDisputeNegValid(spPtlCompDisputeNeg)) {
                    spPtlCompDisputeNeg.setSubStatus(Constants.SUBMIT_STATUS.SUBMITTED.getCode());
                    (companyStoredProcedures).procIntArchiveCompDisputeNeg(spPtlCompDisputeNeg);
                }
            } catch (Exception e) {
                e.printStackTrace();
                spPtlCompDisputeNeg.setSubStatus(Constants.SUBMIT_STATUS.SAVED.getCode());
                spPtlCompDisputeNegRepo.save(spPtlCompDisputeNeg);
                throw new Exception("To αίτημα σας δεν υποβλήθηκε, έχει όμως αποθηκευθεί.");
            }
    }

    @HandleBeforeCreate
    public void handleBeforeCreates(SpPtlCompDisputeNeg spPtlCompDisputeNeg) throws Exception {
        LOGGER.debug("Handling SpPtlCompDisputeNeg for: User ID " + CurrentUserDetailsService.getCurrentUser().getUsername());

        UserDTO userDTO = CurrentUserDetailsService.getCurrentUserByPrincipal();
        if (userDTO.getRole() == Constants.USER_TYPE.COMPANY_USER.getCode()) {
            TCompanyUserPrivilages priv = companyUserPrivilagesRepository.findByUser(userDTO.getId());
            ArrayList<Integer> list = new ArrayList(Arrays.asList(priv.getPrivilages().split("\\s*,\\s*")));
            if(null != priv.getPrivilages() && list.contains(String.valueOf(Constants.COMPANY_SERVICE.DISPUTE_NEG.getCode()))) {
                Long companyId = priv.getCompId();
                spPtlCompDisputeNeg.setCompanyId(companyId);
            }
            else {
                throw new Exception("Ο χρήστης δεν έχει εξουσιοδότηση σε αυτό το είδος αίτησης.");
            }
        }
        else {
            spPtlCompDisputeNeg.setCompanyId(CurrentUserDetailsService.getCurrentUser().getUserId());
        }

        if (spPtlCompDisputeNeg.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode())
            try {
                spPtlCompDisputeNeg.setSubStatus(Constants.SUBMIT_STATUS.SAVED.getCode());
                spPtlCompDisputeNegRepo.save(spPtlCompDisputeNeg);
                if (isDisputeNegValid(spPtlCompDisputeNeg)) {
                    spPtlCompDisputeNeg.setSubStatus(Constants.SUBMIT_STATUS.SUBMITTED.getCode());
                    (companyStoredProcedures).procIntArchiveCompDisputeNeg(spPtlCompDisputeNeg);
                }
            } catch (Exception e) {
                e.printStackTrace();
                spPtlCompDisputeNeg.setSubStatus(Constants.SUBMIT_STATUS.SAVED.getCode());
                spPtlCompDisputeNegRepo.save(spPtlCompDisputeNeg);
                throw new Exception("To αίτημα σας δεν υποβλήθηκε, έχει όμως αποθηκευθεί.");
            }
    }

    @HandleBeforeDelete
    public void handleBeforeDeletes(SpPtlCompDisputeNeg spPtlCompDisputeNeg) throws Exception {
        if (spPtlCompDisputeNeg.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode()) {
            throw new Exception("To αίτημα σας δεν γίνεται να διαγραφεί επειδή είναι ήδη υποβεβλημένο.");
        }
    }

    public boolean isDisputeNegValid(SpPtlCompDisputeNeg spPtlCompDisputeNeg)
    {
        UserDTO user = CurrentUserDetailsService.getCurrentUser().getUserDTO();
        TCompany company = companyRepository.findOne(spPtlCompDisputeNeg.getCompanyId());

        //get company missing fields from MainBranchIKA
        //TEmployerBranchIKA ika = ikaRepo.findCompanyMainBranchByAme(company.getAme());
        TEmployerBranchIKA ika = ikaRepo.findCompanyMainBranchByAfmAndAme(company.getAfm(),company.getAme());

        if(Constants.StringIsNullOrEmpty(spPtlCompDisputeNeg.getCompAfm()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEbrTaxationNumber()))
                spPtlCompDisputeNeg.setCompAfm("");
            else
                spPtlCompDisputeNeg.setCompAfm(ika.getRgEbrTaxationNumber());
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompDisputeNeg.getCompDoy()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEbrTaxationOfficeCode()))
                spPtlCompDisputeNeg.setCompDoy("");
            else
                spPtlCompDisputeNeg.setCompDoy(ika.getRgEbrTaxationOfficeCode());
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompDisputeNeg.getCompAmeIka()))
        {
            if(Constants.StringIsNullOrEmpty(company.getAme()))
                spPtlCompDisputeNeg.setCompAmeIka("");
            else
                spPtlCompDisputeNeg.setCompAmeIka(company.getAme());
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompDisputeNeg.getCompName()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEmpFullname()))
                spPtlCompDisputeNeg.setCompName("");
            else
                spPtlCompDisputeNeg.setCompName(ika.getRgEmpFullname());
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompDisputeNeg.getCompPhone()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEbrPhoneNumber()))
                spPtlCompDisputeNeg.setCompPhone("");
            else {
                if(ika.getRgEbrPhoneNumber().contains("-"))
                    ika.setRgEbrPhoneNumber(ika.getRgEbrPhoneNumber().replace("-",""));

                spPtlCompDisputeNeg.setCompPhone(ika.getRgEbrPhoneNumber());
            }
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompDisputeNeg.getCompAddr()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEbrAddressStreet()))
                spPtlCompDisputeNeg.setCompAddr("");
            else
                spPtlCompDisputeNeg.setCompAddr(ika.getRgEbrAddressStreet());
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompDisputeNeg.getCompAddrTk()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEbrZipCode()))
                spPtlCompDisputeNeg.setCompAddrTk("");
            else
                spPtlCompDisputeNeg.setCompAddrTk(ika.getRgEbrZipCode());
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompDisputeNeg.getCompAddrP()))
        {
            spPtlCompDisputeNeg.setCompAddrP("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlCompDisputeNeg.getCompAddrPe()))
        {
            spPtlCompDisputeNeg.setCompAddrPe("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlCompDisputeNeg.getCompAddrD()))
        {
            spPtlCompDisputeNeg.setCompAddrD("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlCompDisputeNeg.getCompAddrK()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEbrKallikratis()))
                spPtlCompDisputeNeg.setCompAddrK("");
            else
                spPtlCompDisputeNeg.setCompAddrK(ika.getRgEbrKallikratis());
        }

        if(Constants.LongIsNullOrEmpty(spPtlCompDisputeNeg.getBranch0Id())
                || spPtlCompDisputeNeg.getBranch0Id() == 0
                || spPtlCompDisputeNeg.getBranch0Id() == -1)
        {
            if(Constants.LongIsNullOrEmpty(ika.getRgEbrEmpSepeId()))
                spPtlCompDisputeNeg.setBranch0Id(new Long(-1));
            else
                spPtlCompDisputeNeg.setBranch0Id(ika.getRgEbrEmpSepeId());
        }

        if(Constants.LongIsNullOrEmpty(spPtlCompDisputeNeg.getBranch1Id()))
        {
            spPtlCompDisputeNeg.setBranch1Id(new Long(-1));
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompDisputeNeg.getRtstakLevel1()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEbrSecStakod1()))
                spPtlCompDisputeNeg.setRtstakLevel1("");
            else
                spPtlCompDisputeNeg.setRtstakLevel1(ika.getRgEbrSecStakod1());
        }
        if(Constants.StringIsNullOrEmpty(spPtlCompDisputeNeg.getRtstakLevel2()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEbrSecStakod2()))
                spPtlCompDisputeNeg.setRtstakLevel2("");
            else
                spPtlCompDisputeNeg.setRtstakLevel2(ika.getRgEbrSecStakod2());
        }
        if(Constants.StringIsNullOrEmpty(spPtlCompDisputeNeg.getRtstakLevel3()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEbrSecStakod3()))
                spPtlCompDisputeNeg.setRtstakLevel3("");
            else
                spPtlCompDisputeNeg.setRtstakLevel3(ika.getRgEbrSecStakod3());
        }
        if(Constants.StringIsNullOrEmpty(spPtlCompDisputeNeg.getRtstakLevel4()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEbrSecStakod4()))
                spPtlCompDisputeNeg.setRtstakLevel4("");
            else
                spPtlCompDisputeNeg.setRtstakLevel4(ika.getRgEbrSecStakod4());
        }
        if(Constants.StringIsNullOrEmpty(spPtlCompDisputeNeg.getRtstakLevel5()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEbrSecStakod5()))
                spPtlCompDisputeNeg.setRtstakLevel5("");
            else
                spPtlCompDisputeNeg.setRtstakLevel5(ika.getRgEbrSecStakod5());
        }

        if (Constants.StringIsNullOrEmpty(spPtlCompDisputeNeg.getCompRepresentative())) {
            spPtlCompDisputeNeg.setCompRepresentative("");
        }

        if (Constants.StringIsNullOrEmpty(spPtlCompDisputeNeg.getEmpFirstname())) {
            spPtlCompDisputeNeg.setEmpFirstname(user.getFirstname());
        }
        if (Constants.StringIsNullOrEmpty(spPtlCompDisputeNeg.getEmpLastname())) {
            spPtlCompDisputeNeg.setEmpLastname(user.getLastname());
        }
        if (Constants.StringIsNullOrEmpty(spPtlCompDisputeNeg.getEmpPhone())) {
            spPtlCompDisputeNeg.setEmpPhone("");
        }
        if (Constants.StringIsNullOrEmpty(spPtlCompDisputeNeg.getEmpMobile())) {
            spPtlCompDisputeNeg.setEmpMobile("");
        }
        if (Constants.StringIsNullOrEmpty(spPtlCompDisputeNeg.getEmpAmka())) {
            spPtlCompDisputeNeg.setEmpAmka("");
        }
        if (Constants.StringIsNullOrEmpty(spPtlCompDisputeNeg.getEmpAfm())) {
            spPtlCompDisputeNeg.setEmpAfm("");
        }
        if (Constants.StringIsNullOrEmpty(spPtlCompDisputeNeg.getEmpSex())) {
            spPtlCompDisputeNeg.setEmpSex("");
        }
        if (Constants.IntegerIsNullOrEmpty(spPtlCompDisputeNeg.getEmpAge())) {
            spPtlCompDisputeNeg.setEmpAge(-1);
        }
        if (Constants.LongIsNullOrEmpty(spPtlCompDisputeNeg.getEmpCitizenship())) {
            spPtlCompDisputeNeg.setEmpCitizenship(new Long(-1));
        }
        if (Constants.LongIsNullOrEmpty(spPtlCompDisputeNeg.getEmpMaritalStatus())) {
            spPtlCompDisputeNeg.setEmpMaritalStatus(new Long(-1));
        }
        if (Constants.StringIsNullOrEmpty(spPtlCompDisputeNeg.getEmpCardNumber())) {
            spPtlCompDisputeNeg.setEmpCardNumber("");
        }
        if (Constants.LongIsNullOrEmpty(spPtlCompDisputeNeg.getEmpCardType())) {
            spPtlCompDisputeNeg.setEmpCardType(new Long(-1));
        }

        if (Constants.LongIsNullOrEmpty(spPtlCompDisputeNeg.getEmpSpecialityId())) {
            spPtlCompDisputeNeg.setEmpSpecialityId(new Long(-1));
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompDisputeNeg.getEmpAddr()))
        {
            spPtlCompDisputeNeg.setEmpAddr("");
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompDisputeNeg.getEmpAddrTk()))
        {
            spPtlCompDisputeNeg.setEmpAddrTk("");
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompDisputeNeg.getEmpAddrP()))
        {
            spPtlCompDisputeNeg.setEmpAddrP("");
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompDisputeNeg.getEmpAddrPe()))
        {
            spPtlCompDisputeNeg.setEmpAddrPe("");
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompDisputeNeg.getEmpAddrD()))
        {
            spPtlCompDisputeNeg.setEmpAddrD("");
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompDisputeNeg.getEmpAddrK()))
        {
            spPtlCompDisputeNeg.setEmpAddrK("");
        }

        if(Constants.IntegerIsNullOrEmpty(spPtlCompDisputeNeg.getEmpChildrenNum()))
        {
            spPtlCompDisputeNeg.setEmpChildrenNum(-1);
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompDisputeNeg.getEmpNetSalary()))
        {
            spPtlCompDisputeNeg.setEmpNetSalary("");
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompDisputeNeg.getEmpGrossSalary()))
        {
            spPtlCompDisputeNeg.setEmpGrossSalary("");
        }

        if(Constants.TimestampIsNullOrEmpty(spPtlCompDisputeNeg.getEmpFromDate()))
        {
            spPtlCompDisputeNeg.setEmpFromDate(new Timestamp(0));
        }

        if(Constants.TimestampIsNullOrEmpty(spPtlCompDisputeNeg.getEmpUntilDate()))
        {
            spPtlCompDisputeNeg.setEmpUntilDate(new Timestamp(0));
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompDisputeNeg.getEmpWorkingHours()))
        {
            spPtlCompDisputeNeg.setEmpWorkingHours("");
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompDisputeNeg.getCompBrAddr()))
        {
            spPtlCompDisputeNeg.setCompBrAddr("");
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompDisputeNeg.getCompBrAddrTk()))
        {
            spPtlCompDisputeNeg.setCompBrAddrTk("");
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompDisputeNeg.getCompBrAddrP()))
        {
            spPtlCompDisputeNeg.setCompBrAddrP("");
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompDisputeNeg.getCompBrAddrPe()))
        {
            spPtlCompDisputeNeg.setCompBrAddrPe("");
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompDisputeNeg.getCompBrAddrD()))
        {
            spPtlCompDisputeNeg.setCompBrAddrD("");
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompDisputeNeg.getCompBrAddrK()))
        {
            spPtlCompDisputeNeg.setCompBrAddrK("");
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompDisputeNeg.getDescr()))
        {
            spPtlCompDisputeNeg.setDescr("");
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompDisputeNeg.getNotes()))
        {
            spPtlCompDisputeNeg.setNotes("");
        }

        if(Constants.LongIsNullOrEmpty(spPtlCompDisputeNeg.getDocIdAttached()))
        {
            spPtlCompDisputeNeg.setDocIdAttached(new Long(-1));
        }

        if(Constants.IntegerIsNullOrEmpty(spPtlCompDisputeNeg.getDisputeOrConciliation()))
        {
            spPtlCompDisputeNeg.setDisputeOrConciliation(-1);
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompDisputeNeg.getDisputeConciliationCategId()))
        {
            spPtlCompDisputeNeg.setDisputeConciliationCategId("");
        }

        //APPLICANT_TYPE
        //APPLICATION_TYPE

        entityManager.detach(ika);
        return true;
    }
}