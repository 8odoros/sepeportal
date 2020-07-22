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
import sepe.domain.company.SpPtlCompComplaint;
import sepe.domain.company.TCompany;
import sepe.domain.company.TCompanyUserPrivilages;
import sepe.domain.company.TEmployerBranchIKA;
import sepe.dto.UserDTO;
import sepe.repository.company.CompanyRepository;
import sepe.repository.company.SpPtlCompComplaintRepo;
import sepe.repository.company.SpPtlCompanyUserPrivilagesRepository;
import sepe.repository.company.TEmployerBranchIKARepo;
import sepe.service.CompanyStoredProcedures;
import sepe.service.CurrentUserDetailsService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Annita on 5/11/2015.
 */

@Service
@RepositoryEventHandler(SpPtlCompComplaint.class)
public class CompanyComplaintEventHandler {
    @Autowired
    private CompanyStoredProcedures companyStoredProcedures;

    @Autowired
    private SpPtlCompComplaintRepo spPtlCompComplaintRepo;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private TEmployerBranchIKARepo ikaRepo;

    @Autowired
    SpPtlCompanyUserPrivilagesRepository companyUserPrivilagesRepository;

    @PersistenceContext
    private EntityManager entityManager;

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyComplaintEventHandler.class);

    @HandleBeforeSave
    public void handleBeforeSave(SpPtlCompComplaint spPtlCompComplaint) throws Exception {
        LOGGER.debug("Handling SpPtlCompComplaint for: User ID " + CurrentUserDetailsService.getCurrentUser().getUsername());

        UserDTO userDTO = CurrentUserDetailsService.getCurrentUserByPrincipal();
        if (userDTO.getRole() == Constants.USER_TYPE.COMPANY_USER.getCode()) {
            TCompanyUserPrivilages priv = companyUserPrivilagesRepository.findByUser(userDTO.getId());
            ArrayList<Integer> list = new ArrayList(Arrays.asList(priv.getPrivilages().split("\\s*,\\s*")));
            if(null != priv.getPrivilages() && list.contains(String.valueOf(Constants.COMPANY_SERVICE.COMPLAINT.getCode()))) {
                Long companyId = priv.getCompId();
                spPtlCompComplaint.setCompanyId(companyId);
            }
            else {
                throw new Exception("Ο χρήστης δεν έχει εξουσιοδότηση σε αυτό το είδος αίτησης.");
            }
        }
        else {
            spPtlCompComplaint.setCompanyId(CurrentUserDetailsService.getCurrentUser().getUserId());
        }

        SpPtlCompComplaint hasDisputeSubmitted = spPtlCompComplaintRepo.findOne(spPtlCompComplaint.getId());
        if(null != hasDisputeSubmitted && hasDisputeSubmitted.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode())
        {
            throw new Exception("To αίτημα σας έχει ήδη υποβληθεί.");
        }

        if (spPtlCompComplaint.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode())
            try {
                spPtlCompComplaint.setSubStatus(Constants.SUBMIT_STATUS.SAVED.getCode());
                spPtlCompComplaintRepo.save(spPtlCompComplaint);
                if (isCompanyComplaintValid(spPtlCompComplaint)){
                    spPtlCompComplaint.setSubStatus(Constants.SUBMIT_STATUS.SUBMITTED.getCode());
                    (companyStoredProcedures).procIntArchiveCompComplaint(spPtlCompComplaint);
                }
            } catch (Exception e) {
                e.printStackTrace();
                spPtlCompComplaint.setSubStatus(Constants.SUBMIT_STATUS.SAVED.getCode());
                spPtlCompComplaintRepo.save(spPtlCompComplaint);
                throw new Exception("To αίτημα σας δεν υποβλήθηκε, έχει όμως αποθηκευθεί.");
            }
    }

    @HandleBeforeCreate
    public void handleBeforeCreates(SpPtlCompComplaint spPtlCompComplaint) throws Exception {
        LOGGER.debug("Handling SpPtlCompComplaint for: User ID " + CurrentUserDetailsService.getCurrentUser().getUsername());

        UserDTO userDTO = CurrentUserDetailsService.getCurrentUserByPrincipal();
        if (userDTO.getRole() == Constants.USER_TYPE.COMPANY_USER.getCode()) {
            TCompanyUserPrivilages priv = companyUserPrivilagesRepository.findByUser(userDTO.getId());
            ArrayList<Integer> list = new ArrayList(Arrays.asList(priv.getPrivilages().split("\\s*,\\s*")));
            if(null != priv.getPrivilages() && list.contains(String.valueOf(Constants.COMPANY_SERVICE.COMPLAINT.getCode()))) {
                Long companyId = priv.getCompId();
                spPtlCompComplaint.setCompanyId(companyId);
            }
            else {
                throw new Exception("Ο χρήστης δεν έχει εξουσιοδότηση σε αυτό το είδος αίτησης.");
            }
        }
        else {
            spPtlCompComplaint.setCompanyId(CurrentUserDetailsService.getCurrentUser().getUserId());
        }

        if (spPtlCompComplaint.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode())
            try {
                spPtlCompComplaint.setSubStatus(Constants.SUBMIT_STATUS.SAVED.getCode());
                spPtlCompComplaintRepo.save(spPtlCompComplaint);
                if (isCompanyComplaintValid(spPtlCompComplaint)) {
                    spPtlCompComplaint.setSubStatus(Constants.SUBMIT_STATUS.SUBMITTED.getCode());
                    (companyStoredProcedures).procIntArchiveCompComplaint(spPtlCompComplaint);
                }
            } catch (Exception e) {
                e.printStackTrace();
                spPtlCompComplaint.setSubStatus(Constants.SUBMIT_STATUS.SAVED.getCode());
                spPtlCompComplaintRepo.save(spPtlCompComplaint);
                throw new Exception("To αίτημα σας δεν υποβλήθηκε, έχει όμως αποθηκευθεί.");
            }
    }

    @HandleBeforeDelete
    public void handleBeforeDeletes(SpPtlCompComplaint spPtlCompComplaint) throws Exception {
        if (spPtlCompComplaint.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode()) {
            throw new Exception("To αίτημα σας δεν γίνεται να διαγραφεί επειδή είναι ήδη υποβεβλημένο.");
        }
    }

    public boolean isCompanyComplaintValid(SpPtlCompComplaint spPtlCompComplaint)
    {
        UserDTO user = CurrentUserDetailsService.getCurrentUser().getUserDTO();
        TCompany company = companyRepository.findOne(spPtlCompComplaint.getCompanyId());

        //get company missing fields from MainBranchIKA
        //TEmployerBranchIKA ika = ikaRepo.findCompanyMainBranchByAme(company.getAme());
        TEmployerBranchIKA ika = ikaRepo.findCompanyMainBranchByAfmAndAme(company.getAfm(),company.getAme());

        if(Constants.StringIsNullOrEmpty(spPtlCompComplaint.getCompAfm()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEbrTaxationNumber()))
                spPtlCompComplaint.setCompAfm("");
            else
                spPtlCompComplaint.setCompAfm(ika.getRgEbrTaxationNumber());
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompComplaint.getCompAme()))
        {
            if(Constants.StringIsNullOrEmpty(company.getAme()))
                spPtlCompComplaint.setCompAme("");
            else
                spPtlCompComplaint.setCompAme(company.getAme());
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompComplaint.getCompName()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEmpFullname()))
                spPtlCompComplaint.setCompName("");
            else
                spPtlCompComplaint.setCompName(ika.getRgEmpFullname());
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompComplaint.getCompPhone()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEbrPhoneNumber()))
                spPtlCompComplaint.setCompPhone("");
            else {
                if(ika.getRgEbrPhoneNumber().contains("-"))
                    ika.setRgEbrPhoneNumber(ika.getRgEbrPhoneNumber().replace("-",""));

                spPtlCompComplaint.setCompPhone(ika.getRgEbrPhoneNumber());
            }
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompComplaint.getCompAddr()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEbrAddressStreet()))
                spPtlCompComplaint.setCompAddr("");
            else
                spPtlCompComplaint.setCompAddr(ika.getRgEbrAddressStreet());
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompComplaint.getCompAddrTk()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEbrZipCode()))
                spPtlCompComplaint.setCompAddrTk("");
            else
                spPtlCompComplaint.setCompAddrTk(ika.getRgEbrZipCode());
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompComplaint.getCompAddrP()))
        {
            spPtlCompComplaint.setCompAddrP("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlCompComplaint.getCompAddrPe()))
        {
            spPtlCompComplaint.setCompAddrPe("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlCompComplaint.getCompAddrD()))
        {
            spPtlCompComplaint.setCompAddrD("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlCompComplaint.getCompAddrK()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEbrKallikratis()))
                spPtlCompComplaint.setCompAddrK("");
            else
                spPtlCompComplaint.setCompAddrK(ika.getRgEbrKallikratis());
        }

        if(Constants.LongIsNullOrEmpty(spPtlCompComplaint.getBranch0Id())
                || spPtlCompComplaint.getBranch0Id() == 0
                || spPtlCompComplaint.getBranch0Id() == -1)
        {
            if(Constants.LongIsNullOrEmpty(ika.getRgEbrEmpSepeId()))
                spPtlCompComplaint.setBranch0Id(new Long(-1));
            else
                spPtlCompComplaint.setBranch0Id(ika.getRgEbrEmpSepeId());
        }

        if(Constants.LongIsNullOrEmpty(spPtlCompComplaint.getBranch1Id()))
        {
            spPtlCompComplaint.setBranch1Id(new Long(-1));
        }

        if(Constants.IntegerIsNullOrEmpty(spPtlCompComplaint.getCompHasLabourUnion()))
        {
            spPtlCompComplaint.setCompHasLabourUnion(-1);
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompComplaint.getRtstakLevel1()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEbrSecStakod1()))
                spPtlCompComplaint.setRtstakLevel1("");
            else
                spPtlCompComplaint.setRtstakLevel1(ika.getRgEbrSecStakod1());
        }
        if(Constants.StringIsNullOrEmpty(spPtlCompComplaint.getRtstakLevel2()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEbrSecStakod2()))
                spPtlCompComplaint.setRtstakLevel2("");
            else
                spPtlCompComplaint.setRtstakLevel2(ika.getRgEbrSecStakod2());
        }
        if(Constants.StringIsNullOrEmpty(spPtlCompComplaint.getRtstakLevel3()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEbrSecStakod3()))
                spPtlCompComplaint.setRtstakLevel3("");
            else
                spPtlCompComplaint.setRtstakLevel3(ika.getRgEbrSecStakod3());
        }
        if(Constants.StringIsNullOrEmpty(spPtlCompComplaint.getRtstakLevel4()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEbrSecStakod4()))
                spPtlCompComplaint.setRtstakLevel4("");
            else
                spPtlCompComplaint.setRtstakLevel4(ika.getRgEbrSecStakod4());
        }
        if(Constants.StringIsNullOrEmpty(spPtlCompComplaint.getRtstakLevel5()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEbrSecStakod5()))
                spPtlCompComplaint.setRtstakLevel5("");
            else
                spPtlCompComplaint.setRtstakLevel5(ika.getRgEbrSecStakod5());
        }

        if (Constants.StringIsNullOrEmpty(spPtlCompComplaint.getEmpFirstname())
                && spPtlCompComplaint.getComplIsAnonymous() == Constants.COMPLAINT_TYPE_DESC.NAMED.getCode()) {
            spPtlCompComplaint.setEmpFirstname(user.getFirstname());
        }
        if (Constants.StringIsNullOrEmpty(spPtlCompComplaint.getEmpLastname())
                && spPtlCompComplaint.getComplIsAnonymous() == Constants.COMPLAINT_TYPE_DESC.NAMED.getCode()) {
            spPtlCompComplaint.setEmpLastname(user.getLastname());
        }
        if (Constants.StringIsNullOrEmpty(spPtlCompComplaint.getEmpPhone())) {
            spPtlCompComplaint.setEmpPhone("");
        }
        if (Constants.StringIsNullOrEmpty(spPtlCompComplaint.getEmpMobile())) {
            spPtlCompComplaint.setEmpMobile("");
        }
        if (Constants.StringIsNullOrEmpty(spPtlCompComplaint.getEmpAmka())) {
            spPtlCompComplaint.setEmpAmka("");
        }
        if (Constants.StringIsNullOrEmpty(spPtlCompComplaint.getEmpAfm())) {
            spPtlCompComplaint.setEmpAfm("");
        }
        if (Constants.StringIsNullOrEmpty(spPtlCompComplaint.getEmpSexDesc())) {
            spPtlCompComplaint.setEmpSexDesc("");
        }
        if (Constants.StringIsNullOrEmpty(spPtlCompComplaint.getEmpAge())) {
            spPtlCompComplaint.setEmpAge("");
        }
        if (Constants.StringIsNullOrEmpty(spPtlCompComplaint.getEmpCitizenshipCd())) {
            spPtlCompComplaint.setEmpCitizenshipCd("");
        }
        if (Constants.StringIsNullOrEmpty(spPtlCompComplaint.getEmpCardNumber())) {
            spPtlCompComplaint.setEmpCardNumber("");
        }
        if (Constants.LongIsNullOrEmpty(spPtlCompComplaint.getEmpCardType())) {
            spPtlCompComplaint.setEmpCardType(new Long(-1));
        }

        if (spPtlCompComplaint.getComplMatter().contains("\"")) {
            spPtlCompComplaint.setComplMatter(spPtlCompComplaint.getComplMatter().replace("\"", "").replace("\"", ""));
        }

        if (Constants.StringIsNullOrEmpty(spPtlCompComplaint.getComplMatter())) {
            spPtlCompComplaint.setComplMatter("");
        }
        if (Constants.StringIsNullOrEmpty(spPtlCompComplaint.getComplDescr())) {
            spPtlCompComplaint.setComplDescr("");
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompComplaint.getInspAddr()))
        {
            spPtlCompComplaint.setInspAddr("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlCompComplaint.getInspAddrTk()))
        {
            spPtlCompComplaint.setInspAddrTk("");
        }
        //Kallikratis topou elegxou
        if(Constants.StringIsNullOrEmpty(spPtlCompComplaint.getInspAddrP()))
        {
            spPtlCompComplaint.setInspAddrP("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlCompComplaint.getInspAddrPe()))
        {
            spPtlCompComplaint.setInspAddrPe("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlCompComplaint.getInspAddrD()))
        {
            spPtlCompComplaint.setInspAddrD("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlCompComplaint.getInspAddrK()))
        {
            spPtlCompComplaint.setInspAddrK("");
        }

        if(Constants.IntegerIsNullOrEmpty(spPtlCompComplaint.getComplIsAnonymous()))
        {
            spPtlCompComplaint.setComplIsAnonymous(-1);
        }
        //Texniki epithewrisi
        if(Constants.IntegerIsNullOrEmpty(spPtlCompComplaint.getComplInvlovesSafetyInsp()))
        {
            spPtlCompComplaint.setComplInvlovesSafetyInsp(-1);
        }
        //Koinoniki epithewrisi
        if(Constants.IntegerIsNullOrEmpty(spPtlCompComplaint.getComplInvolvesLabRelations()))
        {
            spPtlCompComplaint.setComplInvolvesLabRelations(-1);
        }

        if(Constants.LongIsNullOrEmpty(spPtlCompComplaint.getDocIdAttached()))
        {
            spPtlCompComplaint.setDocIdAttached(new Long(-1));
        }

        //(1 - ΕΡΓΑΖΟΜΕΝΟΣ,2 ΠΟΛΙΤΗΣ,3 ΕΠΙΧΕΙΡΗΣΗ/ΕΡΓΟΔΟΤΗΣ,4 ΤΕΧΝΙΚΟΣ ΑΣΦΑΛΕΙΑΣ
        //5 ΙΑΤΡΟΣ ΕΡΓΑΣΙΑΣ,6 ΣΩΜΑΤΕΙΟ ΕΡΓΑΖΟΜΈΝΩΝ,7 ΑΓΝΩΣΤΟ)

        entityManager.detach(ika);
        return true;
    }
}