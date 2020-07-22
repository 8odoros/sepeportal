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
import sepe.domain.company.SpPtlCompGenreq;
import sepe.domain.company.TCompany;
import sepe.domain.company.TCompanyUserPrivilages;
import sepe.domain.company.TEmployerBranchIKA;
import sepe.dto.UserDTO;
import sepe.repository.company.CompanyRepository;
import sepe.repository.company.SpPtlCompGenreqRepo;
import sepe.repository.company.SpPtlCompanyUserPrivilagesRepository;
import sepe.repository.company.TEmployerBranchIKARepo;
import sepe.service.CompanyStoredProcedures;
import sepe.service.CurrentUserDetailsService;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Annita on 5/11/2015.
 */

@Service
@RepositoryEventHandler(SpPtlCompGenreq.class)
public class CompanyGenReqEventHandler {
    @Autowired
    private CompanyStoredProcedures companyStoredProcedures;

    @Autowired
    private SpPtlCompGenreqRepo spPtlCompGenreqRepo;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private TEmployerBranchIKARepo ikaRepo;

    @Autowired
    SpPtlCompanyUserPrivilagesRepository companyUserPrivilagesRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyGenReqEventHandler.class);

    @HandleBeforeSave
    public void handleBeforeSave(SpPtlCompGenreq spPtlCompGenreq) throws Exception {
        LOGGER.debug("Handling SpPtlCompGenreq for: User ID " + CurrentUserDetailsService.getCurrentUser().getUsername());

        UserDTO userDTO = CurrentUserDetailsService.getCurrentUserByPrincipal();
        if (userDTO.getRole() == Constants.USER_TYPE.COMPANY_USER.getCode()) {
            TCompanyUserPrivilages priv = companyUserPrivilagesRepository.findByUser(userDTO.getId());
            ArrayList<Integer> list = new ArrayList(Arrays.asList(priv.getPrivilages().split("\\s*,\\s*")));
            if (spPtlCompGenreq.getRequestTypeId() == 24) {
                if(null != priv.getPrivilages() && list.contains(String.valueOf(Constants.COMPANY_SERVICE.CERTIFICATE.getCode()))) {
                    Long companyId = priv.getCompId();
                    spPtlCompGenreq.setCompanyId(companyId);
                }
                else {
                    throw new Exception("Ο χρήστης δεν έχει εξουσιοδότηση σε αυτό το είδος αίτησης.");
                }
            } else {
                if(null != priv.getPrivilages() && list.contains(String.valueOf(Constants.COMPANY_SERVICE.GEN_REQUEST.getCode()))) {
                    Long companyId = priv.getCompId();
                    spPtlCompGenreq.setCompanyId(companyId);
                }
                else {
                    throw new Exception("Ο χρήστης δεν έχει εξουσιοδότηση σε αυτό το είδος αίτησης.");
                }
            }

        }
        else {
            spPtlCompGenreq.setCompanyId(CurrentUserDetailsService.getCurrentUser().getUserId());
        }

/*        SpPtlCompGenreq hasDisputeSubmitted = spPtlCompGenreqRepo.findOne(spPtlCompGenreq.getId());
        if(null != hasDisputeSubmitted && hasDisputeSubmitted.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode())
        {
            throw new Exception("To αίτημα σας έχει ήδη υποβληθεί.");
        }*/

        if (spPtlCompGenreq.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode())
            try {
                spPtlCompGenreq.setSubStatus(Constants.SUBMIT_STATUS.SAVED.getCode());
                spPtlCompGenreqRepo.save(spPtlCompGenreq);
                if (isGenReqValid(spPtlCompGenreq)) {
                    spPtlCompGenreq.setSubStatus(Constants.SUBMIT_STATUS.SUBMITTED.getCode());
                    if (spPtlCompGenreq.getRequestTypeId() == 24) {
                        (companyStoredProcedures).procIntArchiveCompGenReqCertificate(spPtlCompGenreq);
                    } else {
                        (companyStoredProcedures).procIntArchiveCompGenReq(spPtlCompGenreq);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                spPtlCompGenreq.setSubStatus(Constants.SUBMIT_STATUS.SAVED.getCode());
                spPtlCompGenreqRepo.save(spPtlCompGenreq);
                throw new Exception("To αίτημα σας δεν υποβλήθηκε, έχει όμως αποθηκευθεί.");
            }
    }

    @HandleBeforeCreate
    public void handleBeforeCreates(SpPtlCompGenreq spPtlCompGenreq) throws Exception {
        LOGGER.debug("Handling SpPtlCompGenreq for: User ID " + CurrentUserDetailsService.getCurrentUser().getUsername());

        UserDTO userDTO = CurrentUserDetailsService.getCurrentUserByPrincipal();
        if (userDTO.getRole() == Constants.USER_TYPE.COMPANY_USER.getCode()) {
            TCompanyUserPrivilages priv = companyUserPrivilagesRepository.findByUser(userDTO.getId());
            ArrayList<Integer> list = new ArrayList(Arrays.asList(priv.getPrivilages().split("\\s*,\\s*")));
            if (spPtlCompGenreq.getRequestTypeId() == 24) {
                if(null != priv.getPrivilages() && list.contains(String.valueOf(Constants.COMPANY_SERVICE.CERTIFICATE.getCode()))) {
                    Long companyId = priv.getCompId();
                    spPtlCompGenreq.setCompanyId(companyId);
                }
                else {
                    throw new Exception("Ο χρήστης δεν έχει εξουσιοδότηση σε αυτό το είδος αίτησης.");
                }
            } else {
                if(null != priv.getPrivilages() && list.contains(String.valueOf(Constants.COMPANY_SERVICE.GEN_REQUEST.getCode()))) {
                    Long companyId = priv.getCompId();
                    spPtlCompGenreq.setCompanyId(companyId);
                }
                else {
                    throw new Exception("Ο χρήστης δεν έχει εξουσιοδότηση σε αυτό το είδος αίτησης.");
                }
            }
        }
        else {
            spPtlCompGenreq.setCompanyId(CurrentUserDetailsService.getCurrentUser().getUserId());
        }

        if (spPtlCompGenreq.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode())
            try {
                spPtlCompGenreq.setSubStatus(Constants.SUBMIT_STATUS.SAVED.getCode());
                spPtlCompGenreqRepo.save(spPtlCompGenreq);
                if (isGenReqValid(spPtlCompGenreq)) {
                    spPtlCompGenreq.setSubStatus(Constants.SUBMIT_STATUS.SUBMITTED.getCode());
                    if (spPtlCompGenreq.getRequestTypeId() == 24) {
                        (companyStoredProcedures).procIntArchiveCompGenReqCertificate(spPtlCompGenreq);
                    } else {
                        (companyStoredProcedures).procIntArchiveCompGenReq(spPtlCompGenreq);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                spPtlCompGenreq.setSubStatus(Constants.SUBMIT_STATUS.SAVED.getCode());
                spPtlCompGenreqRepo.save(spPtlCompGenreq);
                throw new Exception("To αίτημα σας δεν υποβλήθηκε, έχει όμως αποθηκευθεί.");
            }
    }

    @HandleBeforeDelete
    public void handleBeforeDeletes(SpPtlCompGenreq spPtlCompGenreq) throws Exception {
        if (spPtlCompGenreq.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode()) {
            throw new Exception("To αίτημα σας δεν γίνεται να διαγραφεί επειδή είναι ήδη υποβεβλημένο.");
        }
    }

    public boolean isGenReqValid(SpPtlCompGenreq spPtlCompGenreq) {
        UserDTO user = CurrentUserDetailsService.getCurrentUser().getUserDTO();
        TCompany company = companyRepository.findOne(spPtlCompGenreq.getCompanyId());
        TEmployerBranchIKA ika = ikaRepo.findCompanyMainBranchByAfmAndAme(company.getAfm(), company.getAme());

        if (Constants.StringIsNullOrEmpty(spPtlCompGenreq.getReprFirstname())) {
            spPtlCompGenreq.setReprFirstname("");
        }

        if (Constants.StringIsNullOrEmpty(spPtlCompGenreq.getReprLastname())) {
            spPtlCompGenreq.setReprLastname("");
        }

        if (Constants.StringIsNullOrEmpty(spPtlCompGenreq.getReprFathername())) {
            spPtlCompGenreq.setReprFathername("");
        }

        if (Constants.StringIsNullOrEmpty(spPtlCompGenreq.getReprMothername())) {
            spPtlCompGenreq.setReprMothername("");
        }

        if (Constants.StringIsNullOrEmpty(spPtlCompGenreq.getReprAfm())) {
            spPtlCompGenreq.setReprAfm("");
        }

        if (Constants.StringIsNullOrEmpty(spPtlCompGenreq.getReprAmka())) {
            spPtlCompGenreq.setReprAmka("");
        }

        if (Constants.StringIsNullOrEmpty(spPtlCompGenreq.getReprPhone())) {
            spPtlCompGenreq.setReprPhone("");
        }

        if (Constants.StringIsNullOrEmpty(spPtlCompGenreq.getReprMobile())) {
            spPtlCompGenreq.setReprMobile("");
        }

        if (Constants.StringIsNullOrEmpty(spPtlCompGenreq.getReprEmail())) {
            spPtlCompGenreq.setReprEmail("");
        }

        if (Constants.StringIsNullOrEmpty(spPtlCompGenreq.getReprCardNumber())) {
            spPtlCompGenreq.setReprCardNumber("");
        }

        if (Constants.LongIsNullOrEmpty(spPtlCompGenreq.getReprCardType())) {
            spPtlCompGenreq.setReprCardType(new Long(-1));
        }

        if (Constants.StringIsNullOrEmpty(spPtlCompGenreq.getReprAddr())) {
            spPtlCompGenreq.setReprAddr("");
        }

        if (Constants.StringIsNullOrEmpty(spPtlCompGenreq.getReprAddrTk())) {
            spPtlCompGenreq.setReprAddrTk("");
        }

        if (Constants.StringIsNullOrEmpty(spPtlCompGenreq.getReprAddrP())) {
            spPtlCompGenreq.setReprAddrP("");
        }
        if (Constants.StringIsNullOrEmpty(spPtlCompGenreq.getReprAddrPe())) {
            spPtlCompGenreq.setReprAddrPe("");
        }
        if (Constants.StringIsNullOrEmpty(spPtlCompGenreq.getReprAddrD())) {
            spPtlCompGenreq.setReprAddrD("");
        }
        if (Constants.StringIsNullOrEmpty(spPtlCompGenreq.getReprAddrK())) {
            spPtlCompGenreq.setReprAddrK("");
        }

        if (Constants.StringIsNullOrEmpty(spPtlCompGenreq.getDescr())) {
            spPtlCompGenreq.setDescr("");
        }

        if (Constants.StringIsNullOrEmpty(spPtlCompGenreq.getNotes())) {
            spPtlCompGenreq.setNotes("");
        }

        if (Constants.LongIsNullOrEmpty(spPtlCompGenreq.getBranch0Id())
                || spPtlCompGenreq.getBranch0Id() == 0
                || spPtlCompGenreq.getBranch0Id() == -1) {
            if (Constants.LongIsNullOrEmpty(ika.getRgEbrEmpSepeId()))
                spPtlCompGenreq.setBranch0Id(new Long(-1));
            else
                spPtlCompGenreq.setBranch0Id(ika.getRgEbrEmpSepeId());
        }

        if (Constants.LongIsNullOrEmpty(spPtlCompGenreq.getAttachedDocId())) {
            spPtlCompGenreq.setAttachedDocId(new Long(-1));
        }

        if (Constants.LongIsNullOrEmpty(spPtlCompGenreq.getRequestTypeId())) {
            spPtlCompGenreq.setRequestTypeId(new Long(-1));
        }

        return true;
    }
}