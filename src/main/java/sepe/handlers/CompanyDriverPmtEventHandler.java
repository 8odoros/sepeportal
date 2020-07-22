package sepe.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.*;
import org.springframework.stereotype.Service;
import sepe.config.Constants;
import sepe.domain.company.*;
import sepe.dto.SpPtlCompDriverPmtEntryDTO;
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
@RepositoryEventHandler(SpPtlCompDriverPmt.class)
public class CompanyDriverPmtEventHandler {
    @Autowired
    private CompanyStoredProcedures companyStoredProcedures;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private TEmployerBranchIKARepo ikaRepo;

    @Autowired
    private SpPtlCompDriverPmtRepo spPtlCompDriverPmtRepo;

    @Autowired
    SpPtlCompanyUserPrivilagesRepository companyUserPrivilagesRepository;

    @PersistenceContext
    private EntityManager entityManager;

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyDriverPmtEventHandler.class);

    @HandleBeforeSave
    public void handleBeforeSave(SpPtlCompDriverPmt spPtlCompDriverPmt) throws Exception {
        LOGGER.debug("Handling SpPtlCompDriverPmt for: User ID " + CurrentUserDetailsService.getCurrentUser().getUsername());

        UserDTO userDTO = CurrentUserDetailsService.getCurrentUserByPrincipal();
        if (userDTO.getRole() == Constants.USER_TYPE.COMPANY_USER.getCode()) {
            TCompanyUserPrivilages priv = companyUserPrivilagesRepository.findByUser(userDTO.getId());
            ArrayList<Integer> list = new ArrayList(Arrays.asList(priv.getPrivilages().split("\\s*,\\s*")));
            if(null != priv.getPrivilages() && list.contains(String.valueOf(Constants.COMPANY_SERVICE.ACCIDENT.getCode()))) {
                Long companyId = priv.getCompId();
                spPtlCompDriverPmt.setCompanyId(companyId);
            }
            else {
                throw new Exception("Ο χρήστης δεν έχει εξουσιοδότηση σε αυτό το είδος αίτησης.");
            }
        }
        else {
            spPtlCompDriverPmt.setCompanyId(CurrentUserDetailsService.getCurrentUser().getUserId());
        }

        SpPtlCompDriverPmt hasDisputeSubmitted = spPtlCompDriverPmtRepo.findOne(spPtlCompDriverPmt.getId());
        if(null != hasDisputeSubmitted && hasDisputeSubmitted.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode())
        {
            throw new Exception("To αίτημα σας έχει ήδη υποβληθεί.");
        }

        //Check if there is any other booklet for the same driver with the same month, year
        SpPtlCompDriverPmt driverPmt = spPtlCompDriverPmtRepo.driverPmtExists(spPtlCompDriverPmt.getOwnerAfm(), spPtlCompDriverPmt.getPmtMonth(), spPtlCompDriverPmt.getPmtYear(), 2);
        if (null != driverPmt) {
            throw new SepeRestException("Έχει καταχωρηθεί ήδη ο οδηγός με βιβλιάριο αυτού του μήνα και χρόνου.");
        }

        SpPtlCompDriverPmtEntryDTO[] spPtlCompDriverPmtEntryDTO = spPtlCompDriverPmt.getOffdays();
        if(null != spPtlCompDriverPmtEntryDTO) {
            HashSet<SpPtlCompDriverPmtEntry> compDriverPmtEntry = new HashSet<SpPtlCompDriverPmtEntry>();
            for (int i = 0; i < spPtlCompDriverPmtEntryDTO.length; i++) {
                SpPtlCompDriverPmtEntry driverPmtEntry = new SpPtlCompDriverPmtEntry();
                driverPmtEntry.setCompanyId(spPtlCompDriverPmt.getCompanyId());
                driverPmtEntry.setCompDriverPmt(spPtlCompDriverPmt);
                driverPmtEntry.setWeek(spPtlCompDriverPmtEntryDTO[i].getWeek());
                driverPmtEntry.setDay(spPtlCompDriverPmtEntryDTO[i].getDay());
                compDriverPmtEntry.add(driverPmtEntry);
            }
            spPtlCompDriverPmt.setCompDriverPmtEntries(compDriverPmtEntry);
        }

        if (spPtlCompDriverPmt.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode())
            try {
                spPtlCompDriverPmt.setSubStatus(Constants.SUBMIT_STATUS.SAVED.getCode());
                spPtlCompDriverPmtRepo.save(spPtlCompDriverPmt);
                if (isDriverPmtValid(spPtlCompDriverPmt)) {
                    spPtlCompDriverPmt.setSubStatus(Constants.SUBMIT_STATUS.SUBMITTED.getCode());
                    (companyStoredProcedures).procIntArchiveCompDriverPmt(spPtlCompDriverPmt);
                }
            } catch (Exception e) {
                e.printStackTrace();
                spPtlCompDriverPmt.setSubStatus(Constants.SUBMIT_STATUS.SAVED.getCode());
                spPtlCompDriverPmtRepo.save(spPtlCompDriverPmt);
                throw new Exception("To αίτημα σας δεν υποβλήθηκε, έχει όμως αποθηκευθεί.");
            }
    }

    @HandleBeforeCreate
    public void handleBeforeCreates(SpPtlCompDriverPmt spPtlCompDriverPmt) throws Exception {
        LOGGER.debug("Handling SpPtlCompDriverPmt for: User ID " + CurrentUserDetailsService.getCurrentUser().getUsername());

        UserDTO userDTO = CurrentUserDetailsService.getCurrentUserByPrincipal();
        if (userDTO.getRole() == Constants.USER_TYPE.COMPANY_USER.getCode()) {
            TCompanyUserPrivilages priv = companyUserPrivilagesRepository.findByUser(userDTO.getId());
            ArrayList<Integer> list = new ArrayList(Arrays.asList(priv.getPrivilages().split("\\s*,\\s*")));
            if(null != priv.getPrivilages() && list.contains(String.valueOf(Constants.COMPANY_SERVICE.ACCIDENT.getCode()))) {
                Long companyId = priv.getCompId();
                spPtlCompDriverPmt.setCompanyId(companyId);
            }
            else {
                throw new Exception("Ο χρήστης δεν έχει εξουσιοδότηση σε αυτό το είδος αίτησης.");
            }
        }
        else {
            spPtlCompDriverPmt.setCompanyId(CurrentUserDetailsService.getCurrentUser().getUserId());
        }

        //Check if there is any other booklet for the same driver with the same month, year
        SpPtlCompDriverPmt driverPmt = spPtlCompDriverPmtRepo.driverPmtExists(spPtlCompDriverPmt.getOwnerAfm(), spPtlCompDriverPmt.getPmtMonth(), spPtlCompDriverPmt.getPmtYear(), 2);
        if (null != driverPmt) {
            throw new SepeRestException("Έχει καταχωρηθεί ήδη ο οδηγός με βιβλιάριο αυτού του μήνα και χρόνου.");
        }

        SpPtlCompDriverPmtEntryDTO[] spPtlCompDriverPmtEntryDTO = spPtlCompDriverPmt.getOffdays();
        if(null != spPtlCompDriverPmtEntryDTO) {
            HashSet<SpPtlCompDriverPmtEntry> compDriverPmtEntry = new HashSet<SpPtlCompDriverPmtEntry>();
            for (int i = 0; i < spPtlCompDriverPmtEntryDTO.length; i++) {
                SpPtlCompDriverPmtEntry driverPmtEntry = new SpPtlCompDriverPmtEntry();
                driverPmtEntry.setCompanyId(spPtlCompDriverPmt.getCompanyId());
                driverPmtEntry.setCompDriverPmt(spPtlCompDriverPmt);
                driverPmtEntry.setWeek(spPtlCompDriverPmtEntryDTO[i].getWeek());
                driverPmtEntry.setDay(spPtlCompDriverPmtEntryDTO[i].getDay());
                compDriverPmtEntry.add(driverPmtEntry);
            }
            spPtlCompDriverPmt.setCompDriverPmtEntries(compDriverPmtEntry);
        }

        if (spPtlCompDriverPmt.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode())
            try {
                spPtlCompDriverPmt.setSubStatus(Constants.SUBMIT_STATUS.SAVED.getCode());
                spPtlCompDriverPmtRepo.save(spPtlCompDriverPmt);
                if (isDriverPmtValid(spPtlCompDriverPmt)) {
                    spPtlCompDriverPmt.setSubStatus(Constants.SUBMIT_STATUS.SUBMITTED.getCode());
                    (companyStoredProcedures).procIntArchiveCompDriverPmt(spPtlCompDriverPmt);
                }
            } catch (Exception e) {
                e.printStackTrace();
                spPtlCompDriverPmt.setSubStatus(Constants.SUBMIT_STATUS.SAVED.getCode());
                spPtlCompDriverPmtRepo.save(spPtlCompDriverPmt);
                throw new Exception("To αίτημα σας δεν υποβλήθηκε, έχει όμως αποθηκευθεί.");
            }
    }

    @HandleBeforeDelete
    public void handleBeforeDeletes(SpPtlCompDriverPmt spPtlCompDriverPmt) throws Exception {
        if (spPtlCompDriverPmt.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode()) {
            throw new Exception("To αίτημα σας δεν γίνεται να διαγραφεί επειδή είναι ήδη υποβεβλημένο.");
        }
    }

    public boolean isDriverPmtValid(SpPtlCompDriverPmt spPtlCompDriverPmt)
    {
        TCompany company = companyRepository.findOne(spPtlCompDriverPmt.getCompanyId());

        //get company missing fields from MainBranchIKA
        //TEmployerBranchIKA ika = ikaRepo.findCompanyMainBranchByAme(company.getAme());
        TEmployerBranchIKA ika = ikaRepo.findCompanyMainBranchByAfmAndAme(company.getAfm(),company.getAme());

        if(Constants.StringIsNullOrEmpty(spPtlCompDriverPmt.getCompAfm()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEbrTaxationNumber()))
                spPtlCompDriverPmt.setCompAfm("");
            else
                spPtlCompDriverPmt.setCompAfm(ika.getRgEbrTaxationNumber());
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompDriverPmt.getCompName()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEmpFullname()))
                spPtlCompDriverPmt.setCompName("");
            else
                spPtlCompDriverPmt.setCompName(ika.getRgEmpFullname());
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompDriverPmt.getCompPhone()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEbrPhoneNumber()))
                spPtlCompDriverPmt.setCompPhone("");
            else {
                if(ika.getRgEbrPhoneNumber().contains("-"))
                    ika.setRgEbrPhoneNumber(ika.getRgEbrPhoneNumber().replace("-",""));

                spPtlCompDriverPmt.setCompPhone(ika.getRgEbrPhoneNumber());
            }
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompDriverPmt.getCompAddr()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEbrAddressStreet()))
                spPtlCompDriverPmt.setCompAddr("");
            else
                spPtlCompDriverPmt.setCompAddr(ika.getRgEbrAddressStreet());
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompDriverPmt.getCompAddrTk()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEbrZipCode()))
                spPtlCompDriverPmt.setCompAddrTk("");
            else
                spPtlCompDriverPmt.setCompAddrTk(ika.getRgEbrZipCode());
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompDriverPmt.getCompAddrP()))
        {
            spPtlCompDriverPmt.setCompAddrP("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlCompDriverPmt.getCompAddrPe()))
        {
            spPtlCompDriverPmt.setCompAddrPe("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlCompDriverPmt.getCompAddrD()))
        {
            spPtlCompDriverPmt.setCompAddrD("");
        }
        if(Constants.StringIsNullOrEmpty(spPtlCompDriverPmt.getCompAddrK()))
        {
            if(Constants.StringIsNullOrEmpty(ika.getRgEbrKallikratis()))
                spPtlCompDriverPmt.setCompAddrK("");
            else
                spPtlCompDriverPmt.setCompAddrK(ika.getRgEbrKallikratis());
        }

        if(Constants.LongIsNullOrEmpty(spPtlCompDriverPmt.getBranch0Id()))
        {
            spPtlCompDriverPmt.setBranch0Id(new Long(-1));
        }

        if(Constants.LongIsNullOrEmpty(spPtlCompDriverPmt.getBranch1Id()))
        {
            spPtlCompDriverPmt.setBranch1Id(new Long(-1));
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompDriverPmt.getOwnerFirstname()))
        {
            spPtlCompDriverPmt.setOwnerFirstname("");
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompDriverPmt.getOwnerLastname()))
        {
            spPtlCompDriverPmt.setOwnerLastname("");
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompDriverPmt.getOwnerAfm()))
        {
            spPtlCompDriverPmt.setOwnerAfm("");
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompDriverPmt.getOwnerCardNumber()))
        {
            spPtlCompDriverPmt.setOwnerCardNumber("");
        }

        if(Constants.LongIsNullOrEmpty(spPtlCompDriverPmt.getOwnerCardType()))
        {
            spPtlCompDriverPmt.setOwnerCardType(new Long(-1));
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompDriverPmt.getOwnerCardIssuer()))
        {
            spPtlCompDriverPmt.setOwnerCardIssuer("");
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompDriverPmt.getOwnerLicenceNum()))
        {
            spPtlCompDriverPmt.setOwnerLicenceNum("");
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompDriverPmt.getVehicleLicenceNum()))
        {
            spPtlCompDriverPmt.setVehicleLicenceNum("");
        }

        if(Constants.IntegerIsNullOrEmpty(spPtlCompDriverPmt.getPmtMonth()))
        {
            spPtlCompDriverPmt.setPmtMonth(-1);
        }

        if(Constants.IntegerIsNullOrEmpty(spPtlCompDriverPmt.getPmtYear()))
        {
            spPtlCompDriverPmt.setPmtYear(-1);
        }

        if(Constants.IntegerIsNullOrEmpty(spPtlCompDriverPmt.getInspectorName()))
        {
            spPtlCompDriverPmt.setInspectorName(-1);
        }

        if(Constants.StringIsNullOrEmpty(spPtlCompDriverPmt.getEtiology()))
        {
            spPtlCompDriverPmt.setEtiology("");
        }

        entityManager.detach(ika);
        return true;
    }
}