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
import sepe.dto.SpPtlCompSundayPmtPersDTO;
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
@RepositoryEventHandler(SpPtlCompSundayPmt.class)
public class CompanySundayPermitEventHandler {
    @Autowired
    private CompanyStoredProcedures companyStoredProcedures;

    @Autowired
    private SpPtlCompSundayPmtRepo spPtlCompSundayPmtRepo;

    @Autowired
    private SpPtlCompSundayPmtPersRepo spPtlCompSundayPmtPersRepo;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private TEmployerBranchIKARepo ikaRepo;

    @Autowired
    SpPtlCompanyUserPrivilagesRepository companyUserPrivilagesRepository;

    @PersistenceContext
    private EntityManager entityManager;

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanySundayPermitEventHandler.class);

    @HandleBeforeSave
    public void handleBeforeSave(SpPtlCompSundayPmt spPtlCompSundayPmt) throws Exception {
        LOGGER.debug("Handling SpPtlCompSundayPmt for: User ID " + CurrentUserDetailsService.getCurrentUser().getUsername());

        UserDTO userDTO = CurrentUserDetailsService.getCurrentUserByPrincipal();
        if (userDTO.getRole() == Constants.USER_TYPE.COMPANY_USER.getCode()) {
            TCompanyUserPrivilages priv = companyUserPrivilagesRepository.findByUser(userDTO.getId());
            ArrayList<Integer> list = new ArrayList(Arrays.asList(priv.getPrivilages().split("\\s*,\\s*")));
            if(null != priv.getPrivilages() && list.contains(String.valueOf(Constants.COMPANY_SERVICE.SUNDAY_PERMISSION.getCode()))) {
                Long companyId = priv.getCompId();
                spPtlCompSundayPmt.setCompanyId(companyId);
            }
            else {
                throw new Exception("Ο χρήστης δεν έχει εξουσιοδότηση σε αυτό το είδος αίτησης.");
            }
        }
        else {
            spPtlCompSundayPmt.setCompanyId(CurrentUserDetailsService.getCurrentUser().getUserId());
        }

        SpPtlCompSundayPmtPersDTO[] spPtlCompSundayPmtPers = spPtlCompSundayPmt.getPers();
        if(null != spPtlCompSundayPmtPers) {
            HashSet<SpPtlCompSundayPmtPers> companySundayPers = new HashSet<SpPtlCompSundayPmtPers>();
            for (int i = 0; i < spPtlCompSundayPmtPers.length; i++) {
                SpPtlCompSundayPmtPers sundayPmtPers = new SpPtlCompSundayPmtPers();
                sundayPmtPers.setCompanyId(spPtlCompSundayPmt.getCompanyId());
                sundayPmtPers.setCompSundayPmt(spPtlCompSundayPmt);
                sundayPmtPers.setIncNum(spPtlCompSundayPmtPers[i].getIncNum());
                sundayPmtPers.setEmpFirstname(spPtlCompSundayPmtPers[i].getEmpFirstname());
                sundayPmtPers.setEmpLastname(spPtlCompSundayPmtPers[i].getEmpLastname());
                sundayPmtPers.setEmpIka(spPtlCompSundayPmtPers[i].getEmpIka());
                sundayPmtPers.setEmpSpeciality(spPtlCompSundayPmtPers[i].getEmpSpeciality());
                sundayPmtPers.setEmpWorkHourStart(spPtlCompSundayPmtPers[i].getEmpWorkHourStart());
                sundayPmtPers.setEmpWorkHourStop(spPtlCompSundayPmtPers[i].getEmpWorkHourStop());
                sundayPmtPers.setEmpAlternateRestingDay(spPtlCompSundayPmtPers[i].getEmpAlternateRestingDay());
                sundayPmtPers.setInspectorComments(spPtlCompSundayPmtPers[i].getInspectorComments());
                sundayPmtPers.setEmpAfm(spPtlCompSundayPmtPers[i].getEmpAfm());
                sundayPmtPers.setEmpAmka(spPtlCompSundayPmtPers[i].getEmpAmka());
                sundayPmtPers.setEmpSex(spPtlCompSundayPmtPers[i].getEmpSex());
                companySundayPers.add(sundayPmtPers);
            }
            spPtlCompSundayPmt.setCompSundayPmtPers(companySundayPers);
        }

/*        SpPtlCompSundayPmt hasDisputeSubmitted = spPtlCompSundayPmtRepo.findOne(spPtlCompSundayPmt.getId());
        if(null != hasDisputeSubmitted && hasDisputeSubmitted.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode())
        {
            throw new Exception("To αίτημα σας έχει ήδη υποβληθεί.");
        }*/

        if (spPtlCompSundayPmt.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode())
            try {
                spPtlCompSundayPmt.setSubStatus(Constants.SUBMIT_STATUS.SAVED.getCode());
                spPtlCompSundayPmtRepo.save(spPtlCompSundayPmt);
                if (isSundayPermissionValid(spPtlCompSundayPmt)) {
                    spPtlCompSundayPmt.setSubStatus(Constants.SUBMIT_STATUS.SUBMITTED.getCode());
                    (companyStoredProcedures).procIntArchiveSundayPermission(spPtlCompSundayPmt);
                }
            } catch (Exception e) {
                e.printStackTrace();
                spPtlCompSundayPmt.setSubStatus(Constants.SUBMIT_STATUS.SAVED.getCode());
                spPtlCompSundayPmtRepo.save(spPtlCompSundayPmt);
                throw new Exception("To αίτημα σας δεν υποβλήθηκε, έχει όμως αποθηκευθεί.");
            }
    }


    @HandleBeforeCreate
    public void handleBeforeCreates(SpPtlCompSundayPmt spPtlCompSundayPmt) throws Exception {
        LOGGER.debug("Handling SpPtlCompSundayPmt for: User ID " + CurrentUserDetailsService.getCurrentUser().getUsername());

        UserDTO userDTO = CurrentUserDetailsService.getCurrentUserByPrincipal();
        if (userDTO.getRole() == Constants.USER_TYPE.COMPANY_USER.getCode()) {
            TCompanyUserPrivilages priv = companyUserPrivilagesRepository.findByUser(userDTO.getId());
            ArrayList<Integer> list = new ArrayList(Arrays.asList(priv.getPrivilages().split("\\s*,\\s*")));
            if(null != priv.getPrivilages() && list.contains(String.valueOf(Constants.COMPANY_SERVICE.SUNDAY_PERMISSION.getCode()))) {
                Long companyId = priv.getCompId();
                spPtlCompSundayPmt.setCompanyId(companyId);
            }
            else {
                throw new Exception("Ο χρήστης δεν έχει εξουσιοδότηση σε αυτό το είδος αίτησης.");
            }
        }
        else {
            spPtlCompSundayPmt.setCompanyId(CurrentUserDetailsService.getCurrentUser().getUserId());
        }

        SpPtlCompSundayPmtPersDTO[] spPtlCompSundayPmtPers = spPtlCompSundayPmt.getPers();
        if(null != spPtlCompSundayPmtPers) {
            HashSet<SpPtlCompSundayPmtPers> companySundayPers = new HashSet<SpPtlCompSundayPmtPers>();
            for (int i = 0; i < spPtlCompSundayPmtPers.length; i++) {
                SpPtlCompSundayPmtPers sundayPmtPers = new SpPtlCompSundayPmtPers();
                sundayPmtPers.setCompanyId(spPtlCompSundayPmt.getCompanyId());
                sundayPmtPers.setCompSundayPmt(spPtlCompSundayPmt);
                sundayPmtPers.setIncNum(spPtlCompSundayPmtPers[i].getIncNum());
                sundayPmtPers.setEmpFirstname(spPtlCompSundayPmtPers[i].getEmpFirstname());
                sundayPmtPers.setEmpLastname(spPtlCompSundayPmtPers[i].getEmpLastname());
                sundayPmtPers.setEmpIka(spPtlCompSundayPmtPers[i].getEmpIka());
                sundayPmtPers.setEmpSpeciality(spPtlCompSundayPmtPers[i].getEmpSpeciality());
                sundayPmtPers.setEmpWorkHourStart(spPtlCompSundayPmtPers[i].getEmpWorkHourStart());
                sundayPmtPers.setEmpWorkHourStop(spPtlCompSundayPmtPers[i].getEmpWorkHourStop());
                sundayPmtPers.setEmpAlternateRestingDay(spPtlCompSundayPmtPers[i].getEmpAlternateRestingDay());
                sundayPmtPers.setInspectorComments(spPtlCompSundayPmtPers[i].getInspectorComments());
                sundayPmtPers.setEmpAfm(spPtlCompSundayPmtPers[i].getEmpAfm());
                sundayPmtPers.setEmpAmka(spPtlCompSundayPmtPers[i].getEmpAmka());
                sundayPmtPers.setEmpSex(spPtlCompSundayPmtPers[i].getEmpSex());
                companySundayPers.add(sundayPmtPers);
            }
            spPtlCompSundayPmt.setCompSundayPmtPers(companySundayPers);
        }

        if (spPtlCompSundayPmt.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode())
            try {
                spPtlCompSundayPmt.setSubStatus(Constants.SUBMIT_STATUS.SAVED.getCode());
                spPtlCompSundayPmtRepo.save(spPtlCompSundayPmt);
                if (isSundayPermissionValid(spPtlCompSundayPmt)) {
                    spPtlCompSundayPmt.setSubStatus(Constants.SUBMIT_STATUS.SUBMITTED.getCode());
                    (companyStoredProcedures).procIntArchiveSundayPermission(spPtlCompSundayPmt);
                }
            } catch (Exception e) {
                e.printStackTrace();
                spPtlCompSundayPmt.setSubStatus(Constants.SUBMIT_STATUS.SAVED.getCode());
                spPtlCompSundayPmtRepo.save(spPtlCompSundayPmt);
                throw new Exception("To αίτημα σας δεν υποβλήθηκε, έχει όμως αποθηκευθεί.");
            }
    }

    @HandleBeforeDelete
    public void handleBeforeDeletes(SpPtlCompSundayPmt spPtlCompSundayPmt) throws Exception {
        if (spPtlCompSundayPmt.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode()) {
            throw new Exception("To αίτημα σας δεν γίνεται να διαγραφεί επειδή είναι ήδη υποβεβλημένο.");
        }
    }

    public boolean isSundayPermissionValid(SpPtlCompSundayPmt spPtlCompSundayPmt)
    {
        UserDTO user = CurrentUserDetailsService.getCurrentUser().getUserDTO();
        TCompany company = companyRepository.findOne(spPtlCompSundayPmt.getCompanyId());

        //get company missing fields from MainBranchIKA
        //TEmployerBranchIKA ika = ikaRepo.findCompanyMainBranchByAme(company.getAme());
        TEmployerBranchIKA ika = ikaRepo.findCompanyMainBranchByAfmAndAme(company.getAfm(),company.getAme());

        if(Constants.StringIsNullOrEmpty(spPtlCompSundayPmt.getInspAddr()))
        {
            spPtlCompSundayPmt.setInspAddr("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlCompSundayPmt.getInspAddrTk()))
        {
            spPtlCompSundayPmt.setInspAddrTk("");
        }
        //Kallikratis topou elegxou
        if(Constants.StringIsNullOrEmpty(spPtlCompSundayPmt.getInspAddrP()))
        {
            spPtlCompSundayPmt.setInspAddrP("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlCompSundayPmt.getInspAddrPe()))
        {
            spPtlCompSundayPmt.setInspAddrPe("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlCompSundayPmt.getInspAddrD()))
        {
            spPtlCompSundayPmt.setInspAddrD("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlCompSundayPmt.getInspAddrK()))
        {
            spPtlCompSundayPmt.setInspAddrK("");
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompSundayPmt.getCompAfm()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEbrTaxationNumber()))
                spPtlCompSundayPmt.setCompAfm("");
            else
                spPtlCompSundayPmt.setCompAfm(ika.getRgEbrTaxationNumber());
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompSundayPmt.getCompName()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEmpFullname()))
                spPtlCompSundayPmt.setCompName("");
            else
                spPtlCompSundayPmt.setCompName(ika.getRgEmpFullname());
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompSundayPmt.getCompPhone()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEbrPhoneNumber()))
                spPtlCompSundayPmt.setCompPhone("");
            else {
                if(ika.getRgEbrPhoneNumber().contains("-"))
                    ika.setRgEbrPhoneNumber(ika.getRgEbrPhoneNumber().replace("-",""));

                spPtlCompSundayPmt.setCompPhone(ika.getRgEbrPhoneNumber());
            }
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompSundayPmt.getCompAddr()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEbrAddressStreet()))
                spPtlCompSundayPmt.setCompAddr("");
            else
                spPtlCompSundayPmt.setCompAddr(ika.getRgEbrAddressStreet());
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompSundayPmt.getCompAddrTk()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEbrZipCode()))
                spPtlCompSundayPmt.setCompAddrTk("");
            else
                spPtlCompSundayPmt.setCompAddrTk(ika.getRgEbrZipCode());
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompSundayPmt.getCompAddrP()))
        {
            spPtlCompSundayPmt.setCompAddrP("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlCompSundayPmt.getCompAddrPe()))
        {
            spPtlCompSundayPmt.setCompAddrPe("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlCompSundayPmt.getCompAddrD()))
        {
            spPtlCompSundayPmt.setCompAddrD("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlCompSundayPmt.getCompAddrK()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEbrKallikratis()))
                spPtlCompSundayPmt.setCompAddrK("");
            else
                spPtlCompSundayPmt.setCompAddrK(ika.getRgEbrKallikratis());
        }

        if(Constants.LongIsNullOrEmpty(spPtlCompSundayPmt.getCompEbrBranch0Id())
                || spPtlCompSundayPmt.getCompEbrBranch0Id() == 0
                || spPtlCompSundayPmt.getCompEbrBranch0Id() == -1)
        {
            if(Constants.LongIsNullOrEmpty(ika.getRgEbrEmpSepeId()))
                spPtlCompSundayPmt.setCompEbrBranch0Id(new Long(-1));
            else
                spPtlCompSundayPmt.setCompEbrBranch0Id(ika.getRgEbrEmpSepeId());
        }

        if(Constants.LongIsNullOrEmpty(spPtlCompSundayPmt.getCompEbrBranchId()))
        {
            spPtlCompSundayPmt.setCompEbrBranchId(new Long(-1));
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompSundayPmt.getEtiology()))
        {
            spPtlCompSundayPmt.setEtiology("");
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompSundayPmt.getCompStatement()))
        {
            spPtlCompSundayPmt.setCompStatement("");
        }

        if(Constants.TimestampIsNullOrEmpty(spPtlCompSundayPmt.getSundayDate()))
        {
            //spPtlCompSundayPmt.setSundayDate(new Timestamp(0));
        }

        if(Constants.LongIsNullOrEmpty(spPtlCompSundayPmt.getCompDoyId()))
        {
            if(Constants.LongIsNullOrEmpty(Long.parseLong(ika.getRgEbrTaxationOfficeCode())))
                spPtlCompSundayPmt.setCompDoyId(new Long(-1));
            else
                spPtlCompSundayPmt.setCompDoyId(Long.parseLong(ika.getRgEbrTaxationOfficeCode()));
        }

        if(Constants.LongIsNullOrEmpty(spPtlCompSundayPmt.getCompLegalFormId()))
        {
            if (ika.getRgEbrLegalCategoryCode() != null) {
                if (Constants.LongIsNullOrEmpty(Long.parseLong(ika.getRgEbrLegalCategoryCode())))
                    spPtlCompSundayPmt.setCompLegalFormId(new Long(-1));
                else
                    spPtlCompSundayPmt.setCompLegalFormId(Long.parseLong(ika.getRgEbrLegalCategoryCode()));
            } else {
                spPtlCompSundayPmt.setCompLegalFormId(new Long(-1));
            }
        }

        if(Constants.IntegerIsNullOrEmpty(spPtlCompSundayPmt.getInspectorName()))
        {
            spPtlCompSundayPmt.setInspectorName(-1);
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompSundayPmt.getCompKad()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEbrSecStakod5()))
                spPtlCompSundayPmt.setCompKad("");
            else
                spPtlCompSundayPmt.setCompKad(ika.getRgEbrSecStakod5());
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompSundayPmt.getCompPhysicalFlag()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEmpPhysicalFlag()))
                spPtlCompSundayPmt.setCompPhysicalFlag("");
            else
                spPtlCompSundayPmt.setCompPhysicalFlag(ika.getRgEmpPhysicalFlag());
        }

        entityManager.detach(ika);
        return true;
    }
}