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
import sepe.dto.SpPtlCompanyAccidentWitnDTO;
import sepe.dto.UserDTO;
import sepe.repository.company.*;
import sepe.service.CompanyStoredProcedures;
import sepe.service.CurrentUserDetailsService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

@Service
@RepositoryEventHandler(SpPtlCompanyAccident.class)
public class CompanyAccidentEventHandler {
    @Autowired
    private CompanyStoredProcedures companyStoredProcedures;

    @Autowired
    private SpPtlCompanyAccidentRepo spPtlCompanyAccidentRepo;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private TEmployerBranchIKARepo ikaRepo;

    @Autowired
    SpPtlCompanyUserPrivilagesRepository companyUserPrivilagesRepository;

    @PersistenceContext
    private EntityManager entityManager;

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyAccidentEventHandler.class);

    @HandleBeforeSave
    public void handleBeforeSave(SpPtlCompanyAccident spPtlCompanyAccident) throws Exception {
        LOGGER.debug("Handling SpPtlCompanyAccident for: User ID " + CurrentUserDetailsService.getCurrentUser().getUsername());

        UserDTO userDTO = CurrentUserDetailsService.getCurrentUserByPrincipal();
        if (userDTO.getRole() == Constants.USER_TYPE.COMPANY_USER.getCode()) {
            TCompanyUserPrivilages priv = companyUserPrivilagesRepository.findByUser(userDTO.getId());
            ArrayList<Integer> list = new ArrayList(Arrays.asList(priv.getPrivilages().split("\\s*,\\s*")));
            if(null != priv.getPrivilages() && list.contains(String.valueOf(Constants.COMPANY_SERVICE.ACCIDENT.getCode()))) {
                Long companyId = priv.getCompId();
                spPtlCompanyAccident.setCompanyId(companyId);
            }
            else {
                throw new Exception("Ο χρήστης δεν έχει εξουσιοδότηση σε αυτό το είδος αίτησης.");
            }
        }
        else {
            spPtlCompanyAccident.setCompanyId(CurrentUserDetailsService.getCurrentUser().getUserId());
        }

/*        SpPtlCompanyAccident hasDisputeSubmitted = spPtlCompanyAccidentRepo.findOne(spPtlCompanyAccident.getId());
        if(null != hasDisputeSubmitted && hasDisputeSubmitted.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode())
        {
            throw new Exception("To αίτημα σας έχει ήδη υποβληθεί.");
        }*/

        SpPtlCompanyAccidentWitnDTO[] spPtlCompanyAccidentWitn = spPtlCompanyAccident.getWitnessesObj();
        if(null != spPtlCompanyAccidentWitn) {
            HashSet<SpPtlCompanyAccidentWitn> companyAccidentWitn = new HashSet<SpPtlCompanyAccidentWitn>();
            for (int i = 0; i < spPtlCompanyAccidentWitn.length; i++) {
                SpPtlCompanyAccidentWitn accidentWitn = new SpPtlCompanyAccidentWitn();
                accidentWitn.setCompanyId(spPtlCompanyAccident.getCompanyId());
                accidentWitn.setIncNum(spPtlCompanyAccidentWitn[i].getIncNum());
                accidentWitn.setCompanyAccident(spPtlCompanyAccident);
                accidentWitn.setFirstname(spPtlCompanyAccidentWitn[i].getFirstname());
                accidentWitn.setLastname(spPtlCompanyAccidentWitn[i].getLastname());
                accidentWitn.setAddr(spPtlCompanyAccidentWitn[i].getAddr());
                accidentWitn.setAddrTk(spPtlCompanyAccidentWitn[i].getAddrTk());
                accidentWitn.setTypeId(spPtlCompanyAccidentWitn[i].getTypeId());
                accidentWitn.setCitizenshipCd(spPtlCompanyAccidentWitn[i].getCitizenshipCd());
                accidentWitn.setPhone(spPtlCompanyAccidentWitn[i].getPhone());
                companyAccidentWitn.add(accidentWitn);
            }
            spPtlCompanyAccident.setCompanyAccidentWitnesses(companyAccidentWitn);
        }

        if (spPtlCompanyAccident.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode())
            try {
                spPtlCompanyAccident.setSubStatus(Constants.SUBMIT_STATUS.SAVED.getCode());
                spPtlCompanyAccidentRepo.save(spPtlCompanyAccident);
                if (isAccidentValid(spPtlCompanyAccident)) {
                    spPtlCompanyAccident.setSubStatus(Constants.SUBMIT_STATUS.SUBMITTED.getCode());
                    (companyStoredProcedures).procIntArchiveAccident(spPtlCompanyAccident);
                }
            } catch (Exception e) {
                e.printStackTrace();
                spPtlCompanyAccident.setSubStatus(Constants.SUBMIT_STATUS.SAVED.getCode());
                spPtlCompanyAccidentRepo.save(spPtlCompanyAccident);
                throw new Exception("To αίτημα σας δεν υποβλήθηκε, έχει όμως αποθηκευθεί.");
            }
    }

    @HandleBeforeCreate
    public void handleBeforeCreates(SpPtlCompanyAccident spPtlCompanyAccident) throws Exception {
        LOGGER.debug("Handling SpPtlCompanyAccident for: User ID " + CurrentUserDetailsService.getCurrentUser().getUsername());

        UserDTO userDTO = CurrentUserDetailsService.getCurrentUserByPrincipal();
        if (userDTO.getRole() == Constants.USER_TYPE.COMPANY_USER.getCode()) {
            TCompanyUserPrivilages priv = companyUserPrivilagesRepository.findByUser(userDTO.getId());
            ArrayList<Integer> list = new ArrayList(Arrays.asList(priv.getPrivilages().split("\\s*,\\s*")));
            if(null != priv.getPrivilages() && list.contains(String.valueOf(Constants.COMPANY_SERVICE.ACCIDENT.getCode()))) {
                Long companyId = priv.getCompId();
                spPtlCompanyAccident.setCompanyId(companyId);
            }
            else {
                throw new Exception("Ο χρήστης δεν έχει εξουσιοδότηση σε αυτό το είδος αίτησης.");
            }
        }
        else {
            spPtlCompanyAccident.setCompanyId(CurrentUserDetailsService.getCurrentUser().getUserId());
        }

        SpPtlCompanyAccidentWitnDTO[] spPtlCompanyAccidentWitn = spPtlCompanyAccident.getWitnessesObj();
        if(null != spPtlCompanyAccidentWitn) {
            HashSet<SpPtlCompanyAccidentWitn> companyAccidentWitn = new HashSet<SpPtlCompanyAccidentWitn>();
            for (int i = 0; i < spPtlCompanyAccidentWitn.length; i++) {
                SpPtlCompanyAccidentWitn accidentWitn = new SpPtlCompanyAccidentWitn();
                accidentWitn.setCompanyId(spPtlCompanyAccident.getCompanyId());
                accidentWitn.setIncNum(spPtlCompanyAccidentWitn[i].getIncNum());
                accidentWitn.setCompanyAccident(spPtlCompanyAccident);
                accidentWitn.setFirstname(spPtlCompanyAccidentWitn[i].getFirstname());
                accidentWitn.setLastname(spPtlCompanyAccidentWitn[i].getLastname());
                accidentWitn.setAddr(spPtlCompanyAccidentWitn[i].getAddr());
                accidentWitn.setAddrTk(spPtlCompanyAccidentWitn[i].getAddrTk());
                accidentWitn.setTypeId(spPtlCompanyAccidentWitn[i].getTypeId());
                accidentWitn.setCitizenshipCd(spPtlCompanyAccidentWitn[i].getCitizenshipCd());
                accidentWitn.setPhone(spPtlCompanyAccidentWitn[i].getPhone());
                companyAccidentWitn.add(accidentWitn);
            }
            spPtlCompanyAccident.setCompanyAccidentWitnesses(companyAccidentWitn);
        }

        if (spPtlCompanyAccident.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode())
            try {
                spPtlCompanyAccident.setSubStatus(Constants.SUBMIT_STATUS.SAVED.getCode());
                spPtlCompanyAccidentRepo.save(spPtlCompanyAccident);
                if (isAccidentValid(spPtlCompanyAccident)) {
                    spPtlCompanyAccident.setSubStatus(Constants.SUBMIT_STATUS.SUBMITTED.getCode());
                    (companyStoredProcedures).procIntArchiveAccident(spPtlCompanyAccident);
                }
            } catch (Exception e) {
                e.printStackTrace();
                spPtlCompanyAccident.setSubStatus(Constants.SUBMIT_STATUS.SAVED.getCode());
                spPtlCompanyAccidentRepo.save(spPtlCompanyAccident);
                throw new Exception("To αίτημα σας δεν υποβλήθηκε, έχει όμως αποθηκευθεί.");
            }
    }

    @HandleBeforeDelete
    public void handleBeforeDeletes(SpPtlCompanyAccident spPtlCompanyAccident) throws Exception {
        if (spPtlCompanyAccident.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode()) {
            throw new Exception("To αίτημα σας δεν γίνεται να διαγραφεί επειδή είναι ήδη υποβεβλημένο.");
        }
    }

    public boolean isAccidentValid(SpPtlCompanyAccident spPtlCompanyAccident)
    {
        UserDTO user = CurrentUserDetailsService.getCurrentUser().getUserDTO();
        TCompany company = companyRepository.findOne(spPtlCompanyAccident.getCompanyId());

        //get company missing fields from MainBranchIKA
        //TEmployerBranchIKA ika = ikaRepo.findCompanyMainBranchByAme(company.getAme());
        TEmployerBranchIKA ika = ikaRepo.findCompanyMainBranchByAfmAndAme(company.getAfm(),company.getAme());

        if(Constants.StringIsNullOrEmpty(spPtlCompanyAccident.getCompTaxNumber()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEbrTaxationNumber()))
                spPtlCompanyAccident.setCompTaxNumber("");
            else
                spPtlCompanyAccident.setCompTaxNumber(ika.getRgEbrTaxationNumber());
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompanyAccident.getCompFullName()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEmpFullname()))
                spPtlCompanyAccident.setCompFullName("");
            else
                spPtlCompanyAccident.setCompFullName(ika.getRgEmpFullname());
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompanyAccident.getCompPhone()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEbrPhoneNumber()))
                spPtlCompanyAccident.setCompPhone("");
            else {
                if(ika.getRgEbrPhoneNumber().contains("-"))
                    ika.setRgEbrPhoneNumber(ika.getRgEbrPhoneNumber().replace("-",""));

                spPtlCompanyAccident.setCompPhone(ika.getRgEbrPhoneNumber());
            }
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompanyAccident.getCompNameTe()))
        {
            spPtlCompanyAccident.setCompNameTe("");
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompanyAccident.getCompNameIe()))
        {
            spPtlCompanyAccident.setCompNameIe("");
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompanyAccident.getCompAddr()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEbrAddressStreet()))
                spPtlCompanyAccident.setCompAddr("");
            else
                spPtlCompanyAccident.setCompAddr(ika.getRgEbrAddressStreet());
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompanyAccident.getCompAddrTk()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEbrZipCode()))
                spPtlCompanyAccident.setCompAddrTk("");
            else
                spPtlCompanyAccident.setCompAddrTk(ika.getRgEbrZipCode());
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompanyAccident.getCompAddrP()))
        {
            spPtlCompanyAccident.setCompAddrP("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlCompanyAccident.getCompAddrPe()))
        {
            spPtlCompanyAccident.setCompAddrPe("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlCompanyAccident.getCompAddrD()))
        {
            spPtlCompanyAccident.setCompAddrD("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlCompanyAccident.getCompAddrK()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEbrKallikratis()))
                spPtlCompanyAccident.setCompAddrK("");
            else
                spPtlCompanyAccident.setCompAddrK(ika.getRgEbrKallikratis());
        }

        if(Constants.LongIsNullOrEmpty(spPtlCompanyAccident.getCompEbrBranch0Id())
                || spPtlCompanyAccident.getCompEbrBranch0Id() == 0
                || spPtlCompanyAccident.getCompEbrBranch0Id() == -1)
        {
            if(Constants.LongIsNullOrEmpty(ika.getRgEbrEmpSepeId()))
                spPtlCompanyAccident.setCompEbrBranch0Id(new Long(-1));
            else
                spPtlCompanyAccident.setCompEbrBranch0Id(ika.getRgEbrEmpSepeId());
        }

        if(Constants.LongIsNullOrEmpty(spPtlCompanyAccident.getCompEbrBranchId()))
        {
            spPtlCompanyAccident.setCompEbrBranchId(new Long(-1));
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompanyAccident.getEmpAmkaNumber()))
        {
            spPtlCompanyAccident.setEmpAmkaNumber("");
        }

        if(Constants.LongIsNullOrEmpty(spPtlCompanyAccident.getEmpInsuranceBureauCode()))
        {
            spPtlCompanyAccident.setEmpInsuranceBureauCode(new Long(-1));
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompanyAccident.getEmpTaxNumber()))
        {
            spPtlCompanyAccident.setEmpTaxNumber("");
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompanyAccident.getEmpSurname()))
        {
            spPtlCompanyAccident.setEmpSurname("");
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompanyAccident.getEmpName()))
        {
            spPtlCompanyAccident.setEmpName("");
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompanyAccident.getEmpSpecialty()))
        {
            spPtlCompanyAccident.setEmpSpecialty("");
        }

        if(Constants.IntegerIsNullOrEmpty(spPtlCompanyAccident.getEmpSexDesc()))
        {
            spPtlCompanyAccident.setEmpSexDesc(-1);
        }

        if(Constants.LongIsNullOrEmpty(spPtlCompanyAccident.getEmpMaritalStatusCd()))
        {
            spPtlCompanyAccident.setEmpMaritalStatusCd(new Long(-1));
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompanyAccident.getEmpAge()))
        {
            spPtlCompanyAccident.setEmpAge("");
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompanyAccident.getEmpCitizenshipCd()))
        {
            spPtlCompanyAccident.setEmpCitizenshipCd("");
        }

        if(Constants.TimestampIsNullOrEmpty(spPtlCompanyAccident.getEmpRecruitmentDate()))
        {
            spPtlCompanyAccident.setEmpRecruitmentDate(new Timestamp(0));
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompanyAccident.getEmpJobPositionCode()))
        {
            spPtlCompanyAccident.setEmpJobPositionCode("");
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompanyAccident.getEmpTimeCode()))
        {
            spPtlCompanyAccident.setEmpTimeCode("");
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompanyAccident.getEmpAddr()))
        {
            spPtlCompanyAccident.setEmpAddr("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlCompanyAccident.getEmpAddrTk()))
        {
            spPtlCompanyAccident.setEmpAddrTk("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlCompanyAccident.getEmpAddrP()))
        {
            spPtlCompanyAccident.setEmpAddrP("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlCompanyAccident.getEmpAddrPe()))
        {
            spPtlCompanyAccident.setEmpAddrPe("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlCompanyAccident.getEmpAddrD()))
        {
            spPtlCompanyAccident.setEmpAddrD("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlCompanyAccident.getEmpAddrK()))
        {
            spPtlCompanyAccident.setEmpAddrK("");
        }

        if(Constants.LongIsNullOrEmpty(spPtlCompanyAccident.getEmpCardType()))
        {
            spPtlCompanyAccident.setEmpCardType(new Long(-1));
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompanyAccident.getEmpCardNumber()))
        {
            spPtlCompanyAccident.setEmpCardNumber("");
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompanyAccident.getEmpPhone()))
        {
            if(Constants.StringIsNullOrEmpty(user.getPhone()))
                spPtlCompanyAccident.setEmpPhone("");
            else
                spPtlCompanyAccident.setEmpPhone(user.getPhone());
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompanyAccident.getEmpMobile()))
        {
            if(Constants.StringIsNullOrEmpty(user.getMobile()))
                spPtlCompanyAccident.setEmpMobile("");
            else
                spPtlCompanyAccident.setEmpMobile(user.getMobile());
        }

        if(Constants.TimestampIsNullOrEmpty(spPtlCompanyAccident.getAccidentDate()))
        {
            spPtlCompanyAccident.setAccidentDate(new Timestamp(0));
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompanyAccident.getAccidentTime()))
        {
            spPtlCompanyAccident.setAccidentTime("");
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompanyAccident.getAccidentAddr()))
        {
            spPtlCompanyAccident.setAccidentAddr("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlCompanyAccident.getAccidentAddrTk()))
        {
            spPtlCompanyAccident.setAccidentAddrTk("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlCompanyAccident.getAccidentAddrP()))
        {
            spPtlCompanyAccident.setAccidentAddrP("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlCompanyAccident.getAccidentAddrPe()))
        {
            spPtlCompanyAccident.setAccidentAddrPe("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlCompanyAccident.getAccidentAddrD()))
        {
            spPtlCompanyAccident.setAccidentAddrD("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlCompanyAccident.getAccidentAddrK()))
        {
            spPtlCompanyAccident.setAccidentAddrK("");
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompanyAccident.getAccidentJobsComment()))
        {
            spPtlCompanyAccident.setAccidentJobsComment("");
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompanyAccident.getAccidentDescription()))
        {
            spPtlCompanyAccident.setAccidentDescription("");
        }

        if(Constants.IntegerIsNullOrEmpty(spPtlCompanyAccident.getTrainingFlag()))
        {
            spPtlCompanyAccident.setTrainingFlag(-1);
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompanyAccident.getWitnesses()))
        {
            spPtlCompanyAccident.setWitnesses("");
        }

        if(Constants.LongIsNullOrEmpty(spPtlCompanyAccident.getAttachedDocId()))
        {
            spPtlCompanyAccident.setAttachedDocId(new Long(-1));
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompanyAccident.getCompAme()))
        {
            if(Constants.StringIsNullOrEmpty(company.getAme()))
                spPtlCompanyAccident.setCompAme("");
            else
                spPtlCompanyAccident.setCompAme(company.getAme());
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompanyAccident.getAccidentPlace()))
        {
            spPtlCompanyAccident.setAccidentPlace("");
        }

        if(Constants.TimestampIsNullOrEmpty(spPtlCompanyAccident.getEmpBirthdate()))
        {
            spPtlCompanyAccident.setEmpBirthdate(new Timestamp(0));
        }

        entityManager.detach(ika);
        return true;
    }
}