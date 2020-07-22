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
import sepe.dto.SpPtlCompSecDiaryContrDTO;
import sepe.dto.SpPtlCompSecDiaryEngDTO;
import sepe.dto.UserDTO;
import sepe.repository.company.*;
import sepe.service.CompanyStoredProcedures;
import sepe.service.CurrentUserDetailsService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

@Service
@RepositoryEventHandler(SpPtlCompSecDiary.class)
public class CompanySecDiaryEventHandler {
    @Autowired
    private CompanyStoredProcedures companyStoredProcedures;

    @Autowired
    private SpPtlCompSecDiaryRepo spPtlCompSecDiaryRepo;

    @Autowired
    private SpPtlCompSecDiaryContrRepo spPtlCompSecDiaryContrRepo;

    @Autowired
    private SpPtlCompSecDiaryEngRepo spPtlCompSecDiaryEngRepo;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private TEmployerBranchIKARepo ikaRepo;

    @Autowired
    SpPtlCompanyUserPrivilagesRepository companyUserPrivilagesRepository;

    @PersistenceContext
    private EntityManager entityManager;

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanySecDiaryEventHandler.class);

    @HandleBeforeSave
    public void handleBeforeSave(SpPtlCompSecDiary spPtlCompSecDiary) throws Exception {
        LOGGER.debug("Handling SpPtlCompSecDiary for: User ID " + CurrentUserDetailsService.getCurrentUser().getUsername());

        UserDTO userDTO = CurrentUserDetailsService.getCurrentUserByPrincipal();
        if (userDTO.getRole() == Constants.USER_TYPE.COMPANY_USER.getCode()) {
            TCompanyUserPrivilages priv = companyUserPrivilagesRepository.findByUser(userDTO.getId());
            ArrayList<Integer> list = new ArrayList(Arrays.asList(priv.getPrivilages().split("\\s*,\\s*")));
            if (null != priv.getPrivilages() && list.contains(String.valueOf(Constants.COMPANY_SERVICE.DIARY_SAFETY.getCode()))) {
                Long companyId = priv.getCompId();
                spPtlCompSecDiary.setCompanyId(companyId);
            } else {
                throw new Exception("Ο χρήστης δεν έχει εξουσιοδότηση σε αυτό το είδος αίτησης.");
            }
        } else {
            spPtlCompSecDiary.setCompanyId(CurrentUserDetailsService.getCurrentUser().getUserId());
        }

        SpPtlCompSecDiary hasDisputeSubmitted = spPtlCompSecDiaryRepo.findOne(spPtlCompSecDiary.getId());
        if (null != hasDisputeSubmitted && hasDisputeSubmitted.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode()) {
            throw new Exception("To αίτημα σας έχει ήδη υποβληθεί.");
        }

        if (spPtlCompSecDiary.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode())
            try {
                isSecDiaryValid(spPtlCompSecDiary);
            } catch (Exception e) {
                e.printStackTrace();
                spPtlCompSecDiary.setSubStatus(Constants.SUBMIT_STATUS.SAVED.getCode());
                spPtlCompSecDiary.setCompSecDiaryContrs(addProjAnnContractors(spPtlCompSecDiary));
                spPtlCompSecDiary.setCompSecDiaryEngs(addProjAnnEngineers(spPtlCompSecDiary));
                spPtlCompSecDiaryRepo.save(spPtlCompSecDiary);
                throw new Exception("To αίτημα σας δεν υποβλήθηκε, έχει όμως αποθηκευθεί.");
            }
        spPtlCompSecDiary.setCompSecDiaryContrs(addProjAnnContractors(spPtlCompSecDiary));
        spPtlCompSecDiary.setCompSecDiaryEngs(addProjAnnEngineers(spPtlCompSecDiary));
    }

    @HandleBeforeCreate
    public void handleBeforeCreates(SpPtlCompSecDiary spPtlCompSecDiary) throws Exception {
        LOGGER.debug("Handling SpPtlCompSecDiary for: User ID " + CurrentUserDetailsService.getCurrentUser().getUsername());

        UserDTO userDTO = CurrentUserDetailsService.getCurrentUserByPrincipal();
        if (userDTO.getRole() == Constants.USER_TYPE.COMPANY_USER.getCode()) {
            TCompanyUserPrivilages priv = companyUserPrivilagesRepository.findByUser(userDTO.getId());
            ArrayList<Integer> list = new ArrayList(Arrays.asList(priv.getPrivilages().split("\\s*,\\s*")));
            if(null != priv.getPrivilages() && list.contains(String.valueOf(Constants.COMPANY_SERVICE.DIARY_SAFETY.getCode()))) {
                Long companyId = priv.getCompId();
                spPtlCompSecDiary.setCompanyId(companyId);
            }
            else {
                throw new Exception("Ο χρήστης δεν έχει εξουσιοδότηση σε αυτό το είδος αίτησης.");
            }
        }
        else {
            spPtlCompSecDiary.setCompanyId(CurrentUserDetailsService.getCurrentUser().getUserId());
        }

        if (spPtlCompSecDiary.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode())
            try {
                isSecDiaryValid(spPtlCompSecDiary);
            } catch (Exception e) {
                e.printStackTrace();
                spPtlCompSecDiary.setSubStatus(Constants.SUBMIT_STATUS.SAVED.getCode());
                spPtlCompSecDiary.setCompSecDiaryContrs(addProjAnnContractors(spPtlCompSecDiary));
                spPtlCompSecDiary.setCompSecDiaryEngs(addProjAnnEngineers(spPtlCompSecDiary));
                spPtlCompSecDiaryRepo.save(spPtlCompSecDiary);
                throw new Exception("To αίτημα σας δεν υποβλήθηκε, έχει όμως αποθηκευθεί.");
            }
        spPtlCompSecDiary.setCompSecDiaryContrs(addProjAnnContractors(spPtlCompSecDiary));
        spPtlCompSecDiary.setCompSecDiaryEngs(addProjAnnEngineers(spPtlCompSecDiary));
    }

    @HandleBeforeDelete
    public void handleBeforeDeletes(SpPtlCompSecDiary spPtlCompSecDiary) throws Exception {
        if (spPtlCompSecDiary.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode()) {
            throw new Exception("To αίτημα σας δεν γίνεται να διαγραφεί επειδή είναι ήδη υποβεβλημένο.");
        }
    }

    public HashSet<SpPtlCompSecDiaryContr> addProjAnnContractors(SpPtlCompSecDiary spPtlCompSecDiary) {
        SpPtlCompSecDiaryContrDTO[] spPtlCompSecDiaryContr = spPtlCompSecDiary.getProjscontrs();
        if(null != spPtlCompSecDiaryContr) {
            HashSet<SpPtlCompSecDiaryContr> compProjAnnContrs = new HashSet<SpPtlCompSecDiaryContr>();
            for (int i = 0; i < spPtlCompSecDiaryContr.length; i++) {
                SpPtlCompSecDiaryContr projAnnContr = new SpPtlCompSecDiaryContr();
                projAnnContr.setCompanyId(spPtlCompSecDiary.getCompanyId());
                projAnnContr.setCompSecDiary_contr(spPtlCompSecDiary);
                projAnnContr.setFirstname(spPtlCompSecDiaryContr[i].getFirstname());
                projAnnContr.setLastname(spPtlCompSecDiaryContr[i].getLastname());
                projAnnContr.setAddr(spPtlCompSecDiaryContr[i].getAddr());
                projAnnContr.setAfm(spPtlCompSecDiaryContr[i].getAfm());
                projAnnContr.setType(spPtlCompSecDiaryContr[i].getType());
                projAnnContr.setContractorSpecialty(spPtlCompSecDiaryContr[i].getContractor_specialty());
                projAnnContr.setBirthplace(spPtlCompSecDiaryContr[i].getBirthplace());
                projAnnContr.setBirthdate(spPtlCompSecDiaryContr[i].getBirthdate());
                projAnnContr.setCardNumber(spPtlCompSecDiaryContr[i].getCard_number());
                projAnnContr.setCardType(spPtlCompSecDiaryContr[i].getCard_type());
                projAnnContr.setCardIssuingAuth(spPtlCompSecDiaryContr[i].getCard_issuing_auth());
                projAnnContr.setDateStart(spPtlCompSecDiaryContr[i].getDate_start());
                projAnnContr.setDateEnd(spPtlCompSecDiaryContr[i].getDate_end());
                compProjAnnContrs.add(projAnnContr);
            }
            return compProjAnnContrs;
        }
        return null;
    }

    public HashSet<SpPtlCompSecDiaryEng>  addProjAnnEngineers(SpPtlCompSecDiary spPtlCompSecDiary) {
        SpPtlCompSecDiaryEngDTO[] spPtlCompSecDiaryEng = spPtlCompSecDiary.getProjsengs();
        if(null != spPtlCompSecDiaryEng) {
            HashSet<SpPtlCompSecDiaryEng> compProjAnnEngs = new HashSet<SpPtlCompSecDiaryEng>();
            for (int i = 0; i < spPtlCompSecDiaryEng.length; i++) {
                SpPtlCompSecDiaryEng projAnnEng = new SpPtlCompSecDiaryEng();
                projAnnEng.setCompanyId(spPtlCompSecDiary.getCompanyId());
                projAnnEng.setCompSecDiary_eng(spPtlCompSecDiary);
                projAnnEng.setFirstname(spPtlCompSecDiaryEng[i].getFirstname());
                projAnnEng.setLastname(spPtlCompSecDiaryEng[i].getLastname());
                projAnnEng.setAddr(spPtlCompSecDiaryEng[i].getAddr());
                projAnnEng.setAfm(spPtlCompSecDiaryEng[i].getAfm());
                projAnnEng.setEngineerSpeciality(spPtlCompSecDiaryEng[i].getEngineerSpeciality());
                projAnnEng.setTeeNum(spPtlCompSecDiaryEng[i].getTeeNum());
                projAnnEng.setAddr(spPtlCompSecDiaryEng[i].getAddr());
                projAnnEng.setCardNumber(spPtlCompSecDiaryEng[i].getCardNumber());
                projAnnEng.setCardType(spPtlCompSecDiaryEng[i].getCardType());
                projAnnEng.setBirthdate(spPtlCompSecDiaryEng[i].getBirthdate());
                projAnnEng.setBirthplace(spPtlCompSecDiaryEng[i].getBirthplace());
                projAnnEng.setCardIssuingAuth(spPtlCompSecDiaryEng[i].getCardIssuingAuth());
                compProjAnnEngs.add(projAnnEng);
            }
            return compProjAnnEngs;
        }
        return null;
    }

    public boolean isSecDiaryValid(SpPtlCompSecDiary spPtlCompSecDiary)
    {
        TCompany company = companyRepository.findOne(spPtlCompSecDiary.getCompanyId());

        //get company missing fields from MainBranchIKA
        //TEmployerBranchIKA ika = ikaRepo.findCompanyMainBranchByAme(company.getAme());
        TEmployerBranchIKA ika = ikaRepo.findCompanyMainBranchByAfmAndAme(company.getAfm(),company.getAme());

        if(Constants.StringIsNullOrEmpty(spPtlCompSecDiary.getProjAddr()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEbrAddressStreet()))
                spPtlCompSecDiary.setProjAddr("");
            else
                spPtlCompSecDiary.setProjAddr(ika.getRgEbrAddressStreet());
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompSecDiary.getProjAddrTk()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEbrZipCode()))
                spPtlCompSecDiary.setProjAddrTk("");
            else
                spPtlCompSecDiary.setProjAddrTk(ika.getRgEbrZipCode());
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompSecDiary.getProjAddrP()))
        {
            spPtlCompSecDiary.setProjAddrP("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlCompSecDiary.getProjAddrPe()))
        {
            spPtlCompSecDiary.setProjAddrPe("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlCompSecDiary.getProjAddrD()))
        {
            spPtlCompSecDiary.setProjAddrD("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlCompSecDiary.getProjAddrK()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEbrKallikratis()))
                spPtlCompSecDiary.setProjAddrK("");
            else
                spPtlCompSecDiary.setProjAddrK(ika.getRgEbrKallikratis());
        }



        if(Constants.LongIsNullOrEmpty(spPtlCompSecDiary.getAttachedDocId()))
        {
            spPtlCompSecDiary.setAttachedDocId(new Long(-1));
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompSecDiary.getDescr()))
        {
            spPtlCompSecDiary.setDescr("");
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompSecDiary.getNotes()))
        {
            spPtlCompSecDiary.setNotes("");
        }

        entityManager.detach(ika);
        return true;
    }
}