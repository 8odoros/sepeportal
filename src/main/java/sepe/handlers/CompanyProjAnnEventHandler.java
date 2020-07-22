package sepe.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.*;
import org.springframework.stereotype.Service;
import sepe.config.Constants;
import sepe.domain.company.*;
import sepe.dto.*;
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
@RepositoryEventHandler(SpPtlCompProjAnn.class)
public class CompanyProjAnnEventHandler {
    @Autowired
    private CompanyStoredProcedures companyStoredProcedures;

    @Autowired
    private SpPtlCompProjAnnRepo spPtlCompProjAnnRepo;

    @Autowired
    private SpPtlCompProjAnnContrRepo spPtlCompProjAnnContrRepo;

    @Autowired
    private SpPtlCompProjAnnEngRepo spPtlCompProjAnnEngRepo;

    @Autowired
    private SpPtlCompProjAnnSelfemplRepo spPtlCompProjAnnSelfemplRepo;

    @Autowired
    private SpPtlCompProjAnnStudierRepo spPtlCompProjAnnStudierRepo;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private TEmployerBranchIKARepo ikaRepo;

    @Autowired
    SpPtlCompanyUserPrivilagesRepository companyUserPrivilagesRepository;

    @PersistenceContext
    private EntityManager entityManager;

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyProjAnnEventHandler.class);

    @HandleBeforeSave
    public void handleBeforeSave(SpPtlCompProjAnn spPtlCompProjAnn) throws Exception {
        LOGGER.debug("Handling SpPtlCompProjAnn for: User ID " + CurrentUserDetailsService.getCurrentUser().getUsername());

        UserDTO userDTO = CurrentUserDetailsService.getCurrentUserByPrincipal();
        if (userDTO.getRole() == Constants.USER_TYPE.COMPANY_USER.getCode()) {
            TCompanyUserPrivilages priv = companyUserPrivilagesRepository.findByUser(userDTO.getId());
            ArrayList<Integer> list = new ArrayList(Arrays.asList(priv.getPrivilages().split("\\s*,\\s*")));
            if (null != priv.getPrivilages() && list.contains(String.valueOf(Constants.COMPANY_SERVICE.PROJECT_ANNOUNCEMENT.getCode()))) {
                Long companyId = priv.getCompId();
                spPtlCompProjAnn.setUserId(companyId);
            } else {
                throw new Exception("Ο χρήστης δεν έχει εξουσιοδότηση σε αυτό το είδος αίτησης.");
            }
        } else {
            spPtlCompProjAnn.setUserId(CurrentUserDetailsService.getCurrentUser().getUserId());
        }

/*        SpPtlCompProjAnn hasDisputeSubmitted = spPtlCompProjAnnRepo.findOne(spPtlCompProjAnn.getId());
        if (null != hasDisputeSubmitted && hasDisputeSubmitted.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode()) {
            throw new Exception("To αίτημα σας έχει ήδη υποβληθεί.");
        }*/

        spPtlCompProjAnn.setProjAnnContrs(addProjAnnContractors(spPtlCompProjAnn));
        spPtlCompProjAnn.setProjAnnEngs(addProjAnnEngineers(spPtlCompProjAnn));
        spPtlCompProjAnn.setProjAnnSelfempls(addProjAnnSelfEmployers(spPtlCompProjAnn));
        spPtlCompProjAnn.setProjAnnStudiers(addProjAnnStudiers(spPtlCompProjAnn));

        if (spPtlCompProjAnn.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode())
            try {
                spPtlCompProjAnn.setSubStatus(Constants.SUBMIT_STATUS.SAVED.getCode());
                spPtlCompProjAnn.setProjAnnContrs(addProjAnnContractors(spPtlCompProjAnn));
                spPtlCompProjAnn.setProjAnnEngs(addProjAnnEngineers(spPtlCompProjAnn));
                spPtlCompProjAnn.setProjAnnSelfempls(addProjAnnSelfEmployers(spPtlCompProjAnn));
                spPtlCompProjAnn.setProjAnnStudiers(addProjAnnStudiers(spPtlCompProjAnn));
                spPtlCompProjAnnRepo.save(spPtlCompProjAnn);
                if (isProjAnnValid(spPtlCompProjAnn))
                    (companyStoredProcedures).procIntArchiveCompProjAnn(spPtlCompProjAnn);
                    spPtlCompProjAnn.setSubStatus(Constants.SUBMIT_STATUS.SUBMITTED.getCode());
            } catch (Exception e) {
                e.printStackTrace();
                spPtlCompProjAnn.setSubStatus(Constants.SUBMIT_STATUS.SAVED.getCode());
                spPtlCompProjAnn.setProjAnnContrs(addProjAnnContractors(spPtlCompProjAnn));
                spPtlCompProjAnn.setProjAnnEngs(addProjAnnEngineers(spPtlCompProjAnn));
                spPtlCompProjAnn.setProjAnnSelfempls(addProjAnnSelfEmployers(spPtlCompProjAnn));
                spPtlCompProjAnn.setProjAnnStudiers(addProjAnnStudiers(spPtlCompProjAnn));
                spPtlCompProjAnnRepo.save(spPtlCompProjAnn);
                throw new Exception("To αίτημα σας δεν υποβλήθηκε, έχει όμως αποθηκευθεί.");
            }
    }

    @HandleBeforeCreate
    public void handleBeforeCreates(SpPtlCompProjAnn spPtlCompProjAnn) throws Exception {
        LOGGER.debug("Handling SpPtlCompProjAnn for: User ID " + CurrentUserDetailsService.getCurrentUser().getUsername());

        UserDTO userDTO = CurrentUserDetailsService.getCurrentUserByPrincipal();
        if (userDTO.getRole() == Constants.USER_TYPE.COMPANY_USER.getCode()) {
            TCompanyUserPrivilages priv = companyUserPrivilagesRepository.findByUser(userDTO.getId());
            ArrayList<Integer> list = new ArrayList(Arrays.asList(priv.getPrivilages().split("\\s*,\\s*")));
            if(null != priv.getPrivilages() && list.contains(String.valueOf(Constants.COMPANY_SERVICE.PROJECT_ANNOUNCEMENT.getCode()))) {
                Long companyId = priv.getCompId();
                spPtlCompProjAnn.setUserId(companyId);
            }
            else {
                throw new Exception("Ο χρήστης δεν έχει εξουσιοδότηση σε αυτό το είδος αίτησης.");
            }
        }
        else {
            spPtlCompProjAnn.setUserId(CurrentUserDetailsService.getCurrentUser().getUserId());
        }

        spPtlCompProjAnn.setProjAnnContrs(addProjAnnContractors(spPtlCompProjAnn));
        spPtlCompProjAnn.setProjAnnEngs(addProjAnnEngineers(spPtlCompProjAnn));
        spPtlCompProjAnn.setProjAnnSelfempls(addProjAnnSelfEmployers(spPtlCompProjAnn));
        spPtlCompProjAnn.setProjAnnStudiers(addProjAnnStudiers(spPtlCompProjAnn));

        if (spPtlCompProjAnn.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode())
            try {
                spPtlCompProjAnn.setSubStatus(Constants.SUBMIT_STATUS.SAVED.getCode());
                spPtlCompProjAnn.setProjAnnContrs(addProjAnnContractors(spPtlCompProjAnn));
                spPtlCompProjAnn.setProjAnnEngs(addProjAnnEngineers(spPtlCompProjAnn));
                spPtlCompProjAnn.setProjAnnSelfempls(addProjAnnSelfEmployers(spPtlCompProjAnn));
                spPtlCompProjAnn.setProjAnnStudiers(addProjAnnStudiers(spPtlCompProjAnn));
                spPtlCompProjAnnRepo.save(spPtlCompProjAnn);
                if (isProjAnnValid(spPtlCompProjAnn)) {
                    spPtlCompProjAnn.setSubStatus(Constants.SUBMIT_STATUS.SUBMITTED.getCode());
                    (companyStoredProcedures).procIntArchiveCompProjAnn(spPtlCompProjAnn);
                }
            } catch (Exception e) {
                e.printStackTrace();
                spPtlCompProjAnn.setSubStatus(Constants.SUBMIT_STATUS.SAVED.getCode());
                spPtlCompProjAnn.setProjAnnContrs(addProjAnnContractors(spPtlCompProjAnn));
                spPtlCompProjAnn.setProjAnnEngs(addProjAnnEngineers(spPtlCompProjAnn));
                spPtlCompProjAnn.setProjAnnSelfempls(addProjAnnSelfEmployers(spPtlCompProjAnn));
                spPtlCompProjAnn.setProjAnnStudiers(addProjAnnStudiers(spPtlCompProjAnn));
                spPtlCompProjAnnRepo.save(spPtlCompProjAnn);
                throw new Exception("To αίτημα σας δεν υποβλήθηκε, έχει όμως αποθηκευθεί.");
            }
    }

    @HandleBeforeDelete
    public void handleBeforeDeletes(SpPtlCompProjAnn spPtlCompProjAnn) throws Exception {
        if (spPtlCompProjAnn.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode()) {
            throw new Exception("To αίτημα σας δεν γίνεται να διαγραφεί επειδή είναι ήδη υποβεβλημένο.");
        }
    }

    public HashSet<SpPtlCompProjAnnContr>  addProjAnnContractors(SpPtlCompProjAnn spPtlCompProjAnn) {
        SpPtlCompProjAnnContrDTO[] spPtlCompProjAnnContr = spPtlCompProjAnn.getProjscontrs();
        if(null != spPtlCompProjAnnContr) {
            HashSet<SpPtlCompProjAnnContr> compProjAnnContrs = new HashSet<SpPtlCompProjAnnContr>();
            for (int i = 0; i < spPtlCompProjAnnContr.length; i++) {
                SpPtlCompProjAnnContr projAnnContr = new SpPtlCompProjAnnContr();
                projAnnContr.setCompanyId(spPtlCompProjAnn.getUserId());
                projAnnContr.setCompProjAnn_contr(spPtlCompProjAnn);
                projAnnContr.setFirstname(spPtlCompProjAnnContr[i].getFirstname());
                projAnnContr.setLastname(spPtlCompProjAnnContr[i].getLastname());
                projAnnContr.setAddr(spPtlCompProjAnnContr[i].getAddr());
                projAnnContr.setAfm(spPtlCompProjAnnContr[i].getAfm());
                projAnnContr.setType(spPtlCompProjAnnContr[i].getType());
                projAnnContr.setPhone(spPtlCompProjAnnContr[i].getPhone());
                compProjAnnContrs.add(projAnnContr);
            }
            return compProjAnnContrs;
        }
        return null;
    }

    public HashSet<SpPtlCompProjAnnEng>  addProjAnnEngineers(SpPtlCompProjAnn spPtlCompProjAnn) {
        SpPtlCompProjAnnEngDTO[] spPtlCompProjAnnEng = spPtlCompProjAnn.getProjsengs();
        if(null != spPtlCompProjAnnEng) {
            HashSet<SpPtlCompProjAnnEng> compProjAnnEngs = new HashSet<SpPtlCompProjAnnEng>();
            for (int i = 0; i < spPtlCompProjAnnEng.length; i++) {
                SpPtlCompProjAnnEng projAnnEng = new SpPtlCompProjAnnEng();
                projAnnEng.setCompanyId(spPtlCompProjAnn.getUserId());
                projAnnEng.setCompProjAnn_eng(spPtlCompProjAnn);
                projAnnEng.setFirstname(spPtlCompProjAnnEng[i].getFirstname());
                projAnnEng.setLastname(spPtlCompProjAnnEng[i].getLastname());
                projAnnEng.setAddr(spPtlCompProjAnnEng[i].getAddr());
                projAnnEng.setAfm(spPtlCompProjAnnEng[i].getAfm());
                projAnnEng.setEngineerSpeciality(spPtlCompProjAnnEng[i].getEngineerSpeciality());
                projAnnEng.setInvlolvementType(spPtlCompProjAnnEng[i].getInvlolvementType());
                projAnnEng.setTeeNum(spPtlCompProjAnnEng[i].getTeeNum());
                compProjAnnEngs.add(projAnnEng);
            }
            return compProjAnnEngs;
        }
        return null;
    }

    public HashSet<SpPtlCompProjAnnSelfempl> addProjAnnSelfEmployers(SpPtlCompProjAnn spPtlCompProjAnn) {
        SpPtlCompProjAnnSelfEmplDTO[] spPtlCompProjAnnSelfempl = spPtlCompProjAnn.getProjsselfempls();
        if(null != spPtlCompProjAnnSelfempl) {
            HashSet<SpPtlCompProjAnnSelfempl> compProjAnnSelfempls = new HashSet<SpPtlCompProjAnnSelfempl>();
            for (int i = 0; i < spPtlCompProjAnnSelfempl.length; i++) {
                SpPtlCompProjAnnSelfempl projAnnSelfEmpl = new SpPtlCompProjAnnSelfempl();
                projAnnSelfEmpl.setCompanyId(spPtlCompProjAnn.getUserId());
                projAnnSelfEmpl.setCompProjAnn_selfempl(spPtlCompProjAnn);
                projAnnSelfEmpl.setFirstname(spPtlCompProjAnnSelfempl[i].getFirstname());
                projAnnSelfEmpl.setLastname(spPtlCompProjAnnSelfempl[i].getLastname());
                projAnnSelfEmpl.setAddr(spPtlCompProjAnnSelfempl[i].getAddr());
                projAnnSelfEmpl.setAfm(spPtlCompProjAnnSelfempl[i].getAfm());
                compProjAnnSelfempls.add(projAnnSelfEmpl);
            }
            return compProjAnnSelfempls;
        }
        return null;
    }

    public HashSet<SpPtlCompProjAnnStudier> addProjAnnStudiers(SpPtlCompProjAnn spPtlCompProjAnn) {
        SpPtlCompProjAnnStudierDTO[] spPtlCompProjAnnStudier = spPtlCompProjAnn.getProjsstudiers();
        if(null != spPtlCompProjAnnStudier) {
            HashSet<SpPtlCompProjAnnStudier> compProjAnnStudiers = new HashSet<SpPtlCompProjAnnStudier>();
            for (int i = 0; i < spPtlCompProjAnnStudier.length; i++) {
                SpPtlCompProjAnnStudier projAnnStudier = new SpPtlCompProjAnnStudier();
                projAnnStudier.setCompanyId(spPtlCompProjAnn.getUserId());
                projAnnStudier.setCompProjAnn_studier(spPtlCompProjAnn);
                projAnnStudier.setFirstname(spPtlCompProjAnnStudier[i].getFirstname());
                projAnnStudier.setLastname(spPtlCompProjAnnStudier[i].getLastname());
                projAnnStudier.setAddr(spPtlCompProjAnnStudier[i].getAddr());
                projAnnStudier.setAfm(spPtlCompProjAnnStudier[i].getAfm());
                projAnnStudier.setType(spPtlCompProjAnnStudier[i].getType());
                compProjAnnStudiers.add(projAnnStudier);
            }
            return compProjAnnStudiers;
        }
        return null;
    }


    public boolean isProjAnnValid(SpPtlCompProjAnn spPtlCompProjAnn)
    {
        TCompany company = companyRepository.findOne(spPtlCompProjAnn.getUserId());

        //get company missing fields from MainBranchIKA
        //TEmployerBranchIKA ika = ikaRepo.findCompanyMainBranchByAme(company.getAme());
        TEmployerBranchIKA ika = ikaRepo.findCompanyMainBranchByAfmAndAme(company.getAfm(),company.getAme());

        if(Constants.StringIsNullOrEmpty(spPtlCompProjAnn.getAmoe()))
        {
            spPtlCompProjAnn.setAmoe("");
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompProjAnn.getProjAddr()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEbrAddressStreet()))
                spPtlCompProjAnn.setProjAddr("");
            else
                spPtlCompProjAnn.setProjAddr(ika.getRgEbrAddressStreet());
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompProjAnn.getProjAddrTk()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEbrZipCode()))
                spPtlCompProjAnn.setProjAddrTk("");
            else
                spPtlCompProjAnn.setProjAddrTk(ika.getRgEbrZipCode());
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompProjAnn.getProjAddrP()))
        {
            spPtlCompProjAnn.setProjAddrP("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlCompProjAnn.getProjAddrPe()))
        {
            spPtlCompProjAnn.setProjAddrPe("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlCompProjAnn.getProjAddrD()))
        {
            spPtlCompProjAnn.setProjAddrD("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlCompProjAnn.getProjAddrK()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEbrKallikratis()))
                spPtlCompProjAnn.setProjAddrK("");
            else
                spPtlCompProjAnn.setProjAddrK(ika.getRgEbrKallikratis());
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompProjAnn.getMaxEmployeeNum()))
        {
            spPtlCompProjAnn.setMaxEmployeeNum("");
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompProjAnn.getProjDuration()))
        {
            spPtlCompProjAnn.setProjDuration("");
        }

        if(Constants.TimestampIsNullOrEmpty(spPtlCompProjAnn.getProjStartDate()))
        {
            spPtlCompProjAnn.setProjStartDate(new Timestamp(0));
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompProjAnn.getSubcontractorNum()))
        {
            spPtlCompProjAnn.setSubcontractorNum("");
        }

        if(Constants.IntegerIsNullOrEmpty(spPtlCompProjAnn.getProjType()))
        {
            spPtlCompProjAnn.setProjType(-1);
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompProjAnn.getLicenceNum()))
        {
            spPtlCompProjAnn.setLicenceNum("");
        }

        if(Constants.LongIsNullOrEmpty(spPtlCompProjAnn.getAttachedDocId()))
        {
            spPtlCompProjAnn.setAttachedDocId(new Long(-1));
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompProjAnn.getDescr()))
        {
            spPtlCompProjAnn.setDescr("");
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompProjAnn.getNotes()))
        {
            spPtlCompProjAnn.setNotes("");
        }

        entityManager.detach(ika);
        return true;
    }
}