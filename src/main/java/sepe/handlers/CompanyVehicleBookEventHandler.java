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
import sepe.domain.company.SpPtlCompVehicleBook;
import sepe.domain.company.TCompany;
import sepe.domain.company.TCompanyUserPrivilages;
import sepe.domain.company.TEmployerBranchIKA;
import sepe.dto.UserDTO;
import sepe.repository.company.CompanyRepository;
import sepe.repository.company.SpPtlCompVehicleBookRepo;
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
@RepositoryEventHandler(SpPtlCompVehicleBook.class)
public class CompanyVehicleBookEventHandler {
    @Autowired
    private CompanyStoredProcedures companyStoredProcedures;

    @Autowired
    private SpPtlCompVehicleBookRepo spPtlCompVehicleBookRepo;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private TEmployerBranchIKARepo ikaRepo;

    @Autowired
    SpPtlCompanyUserPrivilagesRepository companyUserPrivilagesRepository;

    @PersistenceContext
    private EntityManager entityManager;

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyVehicleBookEventHandler.class);

    @HandleBeforeSave
    public void handleBeforeSave(SpPtlCompVehicleBook spPtlCompVehicleBook) throws Exception {
        LOGGER.debug("Handling SpPtlCompVehicleBook for: User ID " + CurrentUserDetailsService.getCurrentUser().getUsername());

        UserDTO userDTO = CurrentUserDetailsService.getCurrentUserByPrincipal();
        if (userDTO.getRole() == Constants.USER_TYPE.COMPANY_USER.getCode()) {
            TCompanyUserPrivilages priv = companyUserPrivilagesRepository.findByUser(userDTO.getId());
            ArrayList<Integer> list = new ArrayList(Arrays.asList(priv.getPrivilages().split("\\s*,\\s*")));
            if(null != priv.getPrivilages() && list.contains(String.valueOf(Constants.COMPANY_SERVICE.DIARY_VEHICLE_SCHEDULE.getCode()))) {
                Long companyId = priv.getCompId();
                spPtlCompVehicleBook.setCompanyId(companyId);
            }
            else {
                throw new Exception("Ο χρήστης δεν έχει εξουσιοδότηση σε αυτό το είδος αίτησης.");
            }
        }
        else {
            spPtlCompVehicleBook.setCompanyId(CurrentUserDetailsService.getCurrentUser().getUserId());
        }

        SpPtlCompVehicleBook hasDisputeSubmitted = spPtlCompVehicleBookRepo.findOne(spPtlCompVehicleBook.getId());
        if(null != hasDisputeSubmitted && hasDisputeSubmitted.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode())
        {
            throw new Exception("To αίτημα σας έχει ήδη υποβληθεί.");
        }


        if (spPtlCompVehicleBook.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode())
            try {
                spPtlCompVehicleBook.setSubStatus(Constants.SUBMIT_STATUS.SAVED.getCode());
                spPtlCompVehicleBookRepo.save(spPtlCompVehicleBook);
                if (isVehicleBookValid(spPtlCompVehicleBook)) {
                    spPtlCompVehicleBook.setSubStatus(Constants.SUBMIT_STATUS.SUBMITTED.getCode());
                    (companyStoredProcedures).procIntArchiveCompVehicleBook(spPtlCompVehicleBook);
                }
            } catch (Exception e) {
                e.printStackTrace();
                spPtlCompVehicleBook.setSubStatus(Constants.SUBMIT_STATUS.SAVED.getCode());
                spPtlCompVehicleBookRepo.save(spPtlCompVehicleBook);
                throw new Exception("To αίτημα σας δεν υποβλήθηκε, έχει όμως αποθηκευθεί.");
            }
    }

    @HandleBeforeCreate
    public void handleBeforeCreates(SpPtlCompVehicleBook spPtlCompVehicleBook) throws Exception {
        LOGGER.debug("Handling SpPtlCompVehicleBook for: User ID " + CurrentUserDetailsService.getCurrentUser().getUsername());

        UserDTO userDTO = CurrentUserDetailsService.getCurrentUserByPrincipal();
        if (userDTO.getRole() == Constants.USER_TYPE.COMPANY_USER.getCode()) {
            TCompanyUserPrivilages priv = companyUserPrivilagesRepository.findByUser(userDTO.getId());
            ArrayList<Integer> list = new ArrayList(Arrays.asList(priv.getPrivilages().split("\\s*,\\s*")));
            if(null != priv.getPrivilages() && list.contains(String.valueOf(Constants.COMPANY_SERVICE.DIARY_VEHICLE_SCHEDULE.getCode()))) {
                Long companyId = priv.getCompId();
                spPtlCompVehicleBook.setCompanyId(companyId);
            }
            else {
                throw new Exception("Ο χρήστης δεν έχει εξουσιοδότηση σε αυτό το είδος αίτησης.");
            }
        }
        else {
            spPtlCompVehicleBook.setCompanyId(CurrentUserDetailsService.getCurrentUser().getUserId());
        }


        if (spPtlCompVehicleBook.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode())
            try {
                spPtlCompVehicleBook.setSubStatus(Constants.SUBMIT_STATUS.SAVED.getCode());
                spPtlCompVehicleBookRepo.save(spPtlCompVehicleBook);
                if (isVehicleBookValid(spPtlCompVehicleBook)) {
                    spPtlCompVehicleBook.setSubStatus(Constants.SUBMIT_STATUS.SUBMITTED.getCode());
                    (companyStoredProcedures).procIntArchiveCompVehicleBook(spPtlCompVehicleBook);
                }
            } catch (Exception e) {
                e.printStackTrace();
                spPtlCompVehicleBook.setSubStatus(Constants.SUBMIT_STATUS.SAVED.getCode());
                spPtlCompVehicleBookRepo.save(spPtlCompVehicleBook);
                throw new Exception("To αίτημα σας δεν υποβλήθηκε, έχει όμως αποθηκευθεί.");
            }
    }

    @HandleBeforeDelete
    public void handleBeforeDeletes(SpPtlCompVehicleBook spPtlCompVehicleBook) throws Exception {
        if (spPtlCompVehicleBook.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode()) {
            throw new Exception("To αίτημα σας δεν γίνεται να διαγραφεί επειδή είναι ήδη υποβεβλημένο.");
        }
    }

    public boolean isVehicleBookValid(SpPtlCompVehicleBook spPtlCompVehicleBook)
    {
        TCompany company = companyRepository.findOne(spPtlCompVehicleBook.getCompanyId());

        //get company missing fields from MainBranchIKA
        //TEmployerBranchIKA ika = ikaRepo.findCompanyMainBranchByAme(company.getAme());
        TEmployerBranchIKA ika = ikaRepo.findCompanyMainBranchByAfmAndAme(company.getAfm(),company.getAme());

        if(Constants.StringIsNullOrEmpty(spPtlCompVehicleBook.getCompAfm()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEbrTaxationNumber()))
                spPtlCompVehicleBook.setCompAfm("");
            else
                spPtlCompVehicleBook.setCompAfm(ika.getRgEbrTaxationNumber());
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompVehicleBook.getCompName()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEmpFullname()))
                spPtlCompVehicleBook.setCompName("");
            else
                spPtlCompVehicleBook.setCompName(ika.getRgEmpFullname());
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompVehicleBook.getCompPhone()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEbrPhoneNumber()))
                spPtlCompVehicleBook.setCompPhone("");
            else {
                if(ika.getRgEbrPhoneNumber().contains("-"))
                    ika.setRgEbrPhoneNumber(ika.getRgEbrPhoneNumber().replace("-",""));

                spPtlCompVehicleBook.setCompPhone(ika.getRgEbrPhoneNumber());
            }
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompVehicleBook.getCompAddr()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEbrAddressStreet()))
                spPtlCompVehicleBook.setCompAddr("");
            else
                spPtlCompVehicleBook.setCompAddr(ika.getRgEbrAddressStreet());
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompVehicleBook.getCompAddrTk()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEbrZipCode()))
                spPtlCompVehicleBook.setCompAddrTk("");
            else
                spPtlCompVehicleBook.setCompAddrTk(ika.getRgEbrZipCode());
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompVehicleBook.getCompAddrP()))
        {
            spPtlCompVehicleBook.setCompAddrP("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlCompVehicleBook.getCompAddrPe()))
        {
            spPtlCompVehicleBook.setCompAddrPe("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlCompVehicleBook.getCompAddrD()))
        {
            spPtlCompVehicleBook.setCompAddrD("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlCompVehicleBook.getCompAddrK()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEbrKallikratis()))
                spPtlCompVehicleBook.setCompAddrK("");
            else
                spPtlCompVehicleBook.setCompAddrK(ika.getRgEbrKallikratis());
        }

        if(Constants.LongIsNullOrEmpty(spPtlCompVehicleBook.getCompEbrBranch0Id())
                || spPtlCompVehicleBook.getCompEbrBranch0Id() == 0
                || spPtlCompVehicleBook.getCompEbrBranch0Id() == -1)
        {
            if(Constants.LongIsNullOrEmpty(ika.getRgEbrEmpSepeId()))
                spPtlCompVehicleBook.setCompEbrBranch0Id(new Long(-1));
            else
                spPtlCompVehicleBook.setCompEbrBranch0Id(ika.getRgEbrEmpSepeId());
        }

        if(Constants.LongIsNullOrEmpty(spPtlCompVehicleBook.getCompEbrBranch1Id()))
        {
            spPtlCompVehicleBook.setCompEbrBranch1Id(new Long(-1));
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompVehicleBook.getOwnerFirstname()))
        {
            spPtlCompVehicleBook.setOwnerFirstname("");
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompVehicleBook.getOwnerLastname()))
        {
            spPtlCompVehicleBook.setOwnerLastname("");
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompVehicleBook.getOwnerAfm()))
        {
            spPtlCompVehicleBook.setOwnerAfm("");
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompVehicleBook.getOwnerCardNumber()))
        {
            spPtlCompVehicleBook.setOwnerCardNumber("");
        }

        if(Constants.LongIsNullOrEmpty(spPtlCompVehicleBook.getOwnerCardType()))
        {
            spPtlCompVehicleBook.setOwnerCardType(new Long(-1));
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompVehicleBook.getOwnerCardIssuingAuth()))
        {
            spPtlCompVehicleBook.setOwnerCardIssuingAuth("");
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompVehicleBook.getOwnerBirthplace()))
        {
            spPtlCompVehicleBook.setOwnerBirthplace("");
        }

        if(Constants.TimestampIsNullOrEmpty(spPtlCompVehicleBook.getOwnerBirthdate()))
        {
            spPtlCompVehicleBook.setOwnerBirthdate(new Timestamp(0));
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompVehicleBook.getOwnerAddr()))
        {
            spPtlCompVehicleBook.setOwnerAddr("");
        }

        if(Constants.LongIsNullOrEmpty(spPtlCompVehicleBook.getVehicleType()))
        {
            spPtlCompVehicleBook.setVehicleType(new Long(-1));
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompVehicleBook.getVehicleLicenceNum()))
        {
            spPtlCompVehicleBook.setVehicleLicenceNum("");
        }

        entityManager.detach(ika);
        return true;
    }
}