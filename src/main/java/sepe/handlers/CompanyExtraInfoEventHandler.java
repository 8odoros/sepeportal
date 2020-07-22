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
import sepe.domain.company.SpPtlCompExtraInfo;
import sepe.domain.company.TCompany;
import sepe.domain.company.TCompanyUserPrivilages;
import sepe.domain.company.TEmployerBranchIKA;
import sepe.dto.UserDTO;
import sepe.repository.company.CompanyRepository;
import sepe.repository.company.SpPtlCompExtraInfoRepo;
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
@RepositoryEventHandler(SpPtlCompExtraInfo.class)
public class CompanyExtraInfoEventHandler {
    @Autowired
    private CompanyStoredProcedures companyStoredProcedures;

    @Autowired
    private SpPtlCompExtraInfoRepo spPtlCompExtraInfoRepo;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private TEmployerBranchIKARepo ikaRepo;

    @Autowired
    SpPtlCompanyUserPrivilagesRepository companyUserPrivilagesRepository;

    @PersistenceContext
    private EntityManager entityManager;

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyExtraInfoEventHandler.class);

    @HandleBeforeSave
    public void handleBeforeSave(SpPtlCompExtraInfo spPtlCompExtraInfo) throws Exception {
        LOGGER.debug("Handling SpPtlCompExtraInfo for: User ID " + CurrentUserDetailsService.getCurrentUser().getUsername());

        UserDTO userDTO = CurrentUserDetailsService.getCurrentUserByPrincipal();
        if (userDTO.getRole() == Constants.USER_TYPE.COMPANY_USER.getCode()) {
            TCompanyUserPrivilages priv = companyUserPrivilagesRepository.findByUser(userDTO.getId());
            ArrayList<Integer> list = new ArrayList(Arrays.asList(priv.getPrivilages().split("\\s*,\\s*")));
            if(null != priv.getPrivilages() && list.contains(String.valueOf(Constants.COMPANY_SERVICE.EXTRA_INFO.getCode()))) {
                Long companyId = priv.getCompId();
                spPtlCompExtraInfo.setCompanyId(companyId);
            }
            else {
                throw new Exception("Ο χρήστης δεν έχει εξουσιοδότηση σε αυτό το είδος αίτησης.");
            }
        }
        else {
            spPtlCompExtraInfo.setCompanyId(CurrentUserDetailsService.getCurrentUser().getUserId());
        }

/*        SpPtlCompExtraInfo hasDisputeSubmitted = spPtlCompExtraInfoRepo.findOne(spPtlCompExtraInfo.getId());
        if(null != hasDisputeSubmitted && hasDisputeSubmitted.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode())
        {
            throw new Exception("To αίτημα σας έχει ήδη υποβληθεί.");
        }*/

        if (spPtlCompExtraInfo.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode())
            try {
                spPtlCompExtraInfo.setSubStatus(Constants.SUBMIT_STATUS.SAVED.getCode());
                spPtlCompExtraInfoRepo.save(spPtlCompExtraInfo);
                if (isCompanyExtraInfoValid(spPtlCompExtraInfo)) {
                    spPtlCompExtraInfo.setSubStatus(Constants.SUBMIT_STATUS.SUBMITTED.getCode());
                    (companyStoredProcedures).procIntArchiveCompExtraInfo(spPtlCompExtraInfo);
                }
            } catch (Exception e) {
                e.printStackTrace();
                spPtlCompExtraInfo.setSubStatus(Constants.SUBMIT_STATUS.SAVED.getCode());
                spPtlCompExtraInfoRepo.save(spPtlCompExtraInfo);
                throw new Exception("To αίτημα σας δεν υποβλήθηκε, έχει όμως αποθηκευθεί.");
            }
    }

    @HandleBeforeCreate
    public void handleBeforeCreates(SpPtlCompExtraInfo spPtlCompExtraInfo) throws Exception {
        LOGGER.debug("Handling SpPtlCompExtraInfo for: User ID " + CurrentUserDetailsService.getCurrentUser().getUsername());

        UserDTO userDTO = CurrentUserDetailsService.getCurrentUserByPrincipal();
        if (userDTO.getRole() == Constants.USER_TYPE.COMPANY_USER.getCode()) {
            TCompanyUserPrivilages priv = companyUserPrivilagesRepository.findByUser(userDTO.getId());
            ArrayList<Integer> list = new ArrayList(Arrays.asList(priv.getPrivilages().split("\\s*,\\s*")));
            if(null != priv.getPrivilages() && list.contains(String.valueOf(Constants.COMPANY_SERVICE.EXTRA_INFO.getCode()))) {
                Long companyId = priv.getCompId();
                spPtlCompExtraInfo.setCompanyId(companyId);
            }
            else {
                throw new Exception("Ο χρήστης δεν έχει εξουσιοδότηση σε αυτό το είδος αίτησης.");
            }
        }
        else {
            spPtlCompExtraInfo.setCompanyId(CurrentUserDetailsService.getCurrentUser().getUserId());
        }

        if (spPtlCompExtraInfo.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode())
            try {
                spPtlCompExtraInfo.setSubStatus(Constants.SUBMIT_STATUS.SAVED.getCode());
                spPtlCompExtraInfoRepo.save(spPtlCompExtraInfo);
                if (isCompanyExtraInfoValid(spPtlCompExtraInfo)) {
                    spPtlCompExtraInfo.setSubStatus(Constants.SUBMIT_STATUS.SUBMITTED.getCode());
                    (companyStoredProcedures).procIntArchiveCompExtraInfo(spPtlCompExtraInfo);
                }
            } catch (Exception e) {
                e.printStackTrace();
                spPtlCompExtraInfo.setSubStatus(Constants.SUBMIT_STATUS.SAVED.getCode());
                spPtlCompExtraInfoRepo.save(spPtlCompExtraInfo);
                throw new Exception("To αίτημα σας δεν υποβλήθηκε, έχει όμως αποθηκευθεί.");
            }
    }

    @HandleBeforeDelete
    public void handleBeforeDeletes(SpPtlCompExtraInfo spPtlCompExtraInfo) throws Exception {
        if (spPtlCompExtraInfo.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode()) {
            throw new Exception("To αίτημα σας δεν γίνεται να διαγραφεί επειδή είναι ήδη υποβεβλημένο.");
        }
    }

    public boolean isCompanyExtraInfoValid(SpPtlCompExtraInfo spPtlCompExtraInfo)
    {
        UserDTO user = CurrentUserDetailsService.getCurrentUser().getUserDTO();
        TCompany company = companyRepository.findOne(spPtlCompExtraInfo.getCompanyId());

        //get company missing fields from MainBranchIKA
        //TEmployerBranchIKA ika = ikaRepo.findCompanyMainBranchByAme(company.getAme());
        TEmployerBranchIKA ika = ikaRepo.findCompanyMainBranchByAfmAndAme(company.getAfm(),company.getAme());

        if(Constants.StringIsNullOrEmpty(spPtlCompExtraInfo.getCompAfm()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEbrTaxationNumber()))
                spPtlCompExtraInfo.setCompAfm("");
            else
                spPtlCompExtraInfo.setCompAfm(ika.getRgEbrTaxationNumber());
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompExtraInfo.getCompAme()))
        {
            if(Constants.StringIsNullOrEmpty(company.getAme()))
                spPtlCompExtraInfo.setCompAme("");
            else
                spPtlCompExtraInfo.setCompAme(company.getAme());
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompExtraInfo.getCompName()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEmpFullname()))
                spPtlCompExtraInfo.setCompName("");
            else
                spPtlCompExtraInfo.setCompName(ika.getRgEmpFullname());
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompExtraInfo.getCompPhone()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEbrPhoneNumber()))
                spPtlCompExtraInfo.setCompPhone("");
            else {
                if(ika.getRgEbrPhoneNumber().contains("-"))
                    ika.setRgEbrPhoneNumber(ika.getRgEbrPhoneNumber().replace("-",""));

                spPtlCompExtraInfo.setCompPhone(ika.getRgEbrPhoneNumber());
            }
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompExtraInfo.getCompAddr()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEbrAddressStreet()))
                spPtlCompExtraInfo.setCompAddr("");
            else
                spPtlCompExtraInfo.setCompAddr(ika.getRgEbrAddressStreet());
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompExtraInfo.getCompAddrTk()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEbrZipCode()))
                spPtlCompExtraInfo.setCompAddrTk("");
            else
                spPtlCompExtraInfo.setCompAddrTk(ika.getRgEbrZipCode());
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompExtraInfo.getCompAddrP()))
        {
            spPtlCompExtraInfo.setCompAddrP("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlCompExtraInfo.getCompAddrPe()))
        {
            spPtlCompExtraInfo.setCompAddrPe("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlCompExtraInfo.getCompAddrD()))
        {
            spPtlCompExtraInfo.setCompAddrD("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlCompExtraInfo.getCompAddrK()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEbrKallikratis()))
                spPtlCompExtraInfo.setCompAddrK("");
            else
                spPtlCompExtraInfo.setCompAddrK(ika.getRgEbrKallikratis());
        }

        if(Constants.LongIsNullOrEmpty(spPtlCompExtraInfo.getBranch0Id())
                || spPtlCompExtraInfo.getBranch0Id() == 0
                || spPtlCompExtraInfo.getBranch0Id() == -1)
        {
            if(Constants.LongIsNullOrEmpty(ika.getRgEbrEmpSepeId()))
                spPtlCompExtraInfo.setBranch0Id(new Long(-1));
            else
                spPtlCompExtraInfo.setBranch0Id(ika.getRgEbrEmpSepeId());
        }

        if(Constants.LongIsNullOrEmpty(spPtlCompExtraInfo.getBranch1Id()))
        {
            spPtlCompExtraInfo.setBranch1Id(new Long(-1));
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompExtraInfo.getRtstakLevel1()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEbrSecStakod1()))
                spPtlCompExtraInfo.setRtstakLevel1("");
            else
                spPtlCompExtraInfo.setRtstakLevel1(ika.getRgEbrSecStakod1());
        }
        if(Constants.StringIsNullOrEmpty(spPtlCompExtraInfo.getRtstakLevel2()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEbrSecStakod2()))
                spPtlCompExtraInfo.setRtstakLevel2("");
            else
                spPtlCompExtraInfo.setRtstakLevel2(ika.getRgEbrSecStakod2());
        }
        if(Constants.StringIsNullOrEmpty(spPtlCompExtraInfo.getRtstakLevel3()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEbrSecStakod3()))
                spPtlCompExtraInfo.setRtstakLevel3("");
            else
                spPtlCompExtraInfo.setRtstakLevel3(ika.getRgEbrSecStakod3());
        }
        if(Constants.StringIsNullOrEmpty(spPtlCompExtraInfo.getRtstakLevel4()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEbrSecStakod4()))
                spPtlCompExtraInfo.setRtstakLevel4("");
            else
                spPtlCompExtraInfo.setRtstakLevel4(ika.getRgEbrSecStakod4());
        }
        if(Constants.StringIsNullOrEmpty(spPtlCompExtraInfo.getRtstakLevel5()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEbrSecStakod5()))
                spPtlCompExtraInfo.setRtstakLevel5("");
            else
                spPtlCompExtraInfo.setRtstakLevel5(ika.getRgEbrSecStakod5());
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompExtraInfo.getDescr()))
        {
            spPtlCompExtraInfo.setDescr("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlCompExtraInfo.getTaFullname()))
        {
            spPtlCompExtraInfo.setTaFullname("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlCompExtraInfo.getIeFullname()))
        {
            spPtlCompExtraInfo.setIeFullname("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlCompExtraInfo.getInspectorName()))
        {
            spPtlCompExtraInfo.setInspectorName("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlCompExtraInfo.getControlStatementNum()))
        {
            spPtlCompExtraInfo.setControlStatementNum("");
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompExtraInfo.getAttachedDocDescr()))
        {
            spPtlCompExtraInfo.setAttachedDocDescr("");
        }

        if(Constants.LongIsNullOrEmpty(spPtlCompExtraInfo.getAttachedDocId()))
        {
            spPtlCompExtraInfo.setAttachedDocId(new Long(-1));
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompExtraInfo.getTeKe()))
        {
            spPtlCompExtraInfo.setTeKe("");
        }

        entityManager.detach(ika);
        return true;
    }
}