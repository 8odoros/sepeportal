package sepe.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Service;
import sepe.config.Constants;
import sepe.domain.company.SpPtlCompDangerAssess;
import sepe.domain.company.TCompany;
import sepe.domain.company.TCompanyUserPrivilages;
import sepe.domain.company.TEmployerBranchIKA;
import sepe.dto.UserDTO;
import sepe.repository.company.CompanyRepository;
import sepe.repository.company.SpPtlCompDangerAssessRepo;
import sepe.repository.company.SpPtlCompanyUserPrivilagesRepository;
import sepe.repository.company.TEmployerBranchIKARepo;
import sepe.service.CompanyStoredProcedures;
import sepe.service.CurrentUserDetailsService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Arrays;

@Service
@RepositoryEventHandler(SpPtlCompDangerAssess.class)
public class CompanyDangerAssessEventHandler {
    @Autowired
    private CompanyStoredProcedures companyStoredProcedures;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private TEmployerBranchIKARepo ikaRepo;

    @Autowired
    private SpPtlCompDangerAssessRepo spPtlCompDangerAssessRepo;

    @Autowired
    SpPtlCompanyUserPrivilagesRepository companyUserPrivilagesRepository;

    @PersistenceContext
    private EntityManager entityManager;

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyDangerAssessEventHandler.class);

    @HandleBeforeSave
    public void handleBeforeSave(SpPtlCompDangerAssess spPtlCompDangerAssess) throws Exception {
        LOGGER.debug("Handling SpPtlCompDangerAssess for: User ID " + CurrentUserDetailsService.getCurrentUser().getUsername());

        UserDTO userDTO = CurrentUserDetailsService.getCurrentUserByPrincipal();
        if (userDTO.getRole() == Constants.USER_TYPE.COMPANY_USER.getCode()) {
            TCompanyUserPrivilages priv = companyUserPrivilagesRepository.findByUser(userDTO.getId());
            ArrayList<Integer> list = new ArrayList(Arrays.asList(priv.getPrivilages().split("\\s*,\\s*")));
            if (null != priv.getPrivilages() && list.contains(String.valueOf(Constants.COMPANY_SERVICE.ACCIDENT.getCode()))) {
                Long companyId = priv.getCompId();
                spPtlCompDangerAssess.setCompanyId(companyId);
            } else {
                throw new Exception("Ο χρήστης δεν έχει εξουσιοδότηση σε αυτό το είδος αίτησης.");
            }
        } else {
            spPtlCompDangerAssess.setCompanyId(CurrentUserDetailsService.getCurrentUser().getUserId());
        }

        fillBranchIDs(spPtlCompDangerAssess);
    }

    @HandleBeforeCreate
    public void handleBeforeCreates(SpPtlCompDangerAssess spPtlCompDangerAssess) throws Exception {
        LOGGER.debug("Handling SpPtlCompDangerAssess for: User ID " + CurrentUserDetailsService.getCurrentUser().getUsername());

        UserDTO userDTO = CurrentUserDetailsService.getCurrentUserByPrincipal();
        if (userDTO.getRole() == Constants.USER_TYPE.COMPANY_USER.getCode()) {
            TCompanyUserPrivilages priv = companyUserPrivilagesRepository.findByUser(userDTO.getId());
            ArrayList<Integer> list = new ArrayList(Arrays.asList(priv.getPrivilages().split("\\s*,\\s*")));
            if (null != priv.getPrivilages() && list.contains(String.valueOf(Constants.COMPANY_SERVICE.ACCIDENT.getCode()))) {
                Long companyId = priv.getCompId();
                spPtlCompDangerAssess.setCompanyId(companyId);
            } else {
                throw new Exception("Ο χρήστης δεν έχει εξουσιοδότηση σε αυτό το είδος αίτησης.");
            }
        } else {
            spPtlCompDangerAssess.setCompanyId(CurrentUserDetailsService.getCurrentUser().getUserId());
        }

        Long dangerAssess = spPtlCompDangerAssessRepo.findByBranchId(spPtlCompDangerAssess.getBranch1Id());
        if (dangerAssess > 0) {
            throw new SepeRestException("Αυτό το παράρτημα έχει ήδη αντιστοιχισμένη φόρμα εκτίμησης επαγγελματικού κινδύνου.");
        }

        fillBranchIDs(spPtlCompDangerAssess);
    }

    public boolean fillBranchIDs(SpPtlCompDangerAssess spPtlCompDangerAssess) {
        TCompany company = companyRepository.findOne(spPtlCompDangerAssess.getCompanyId());

        //get company missing fields from MainBranchIKA
        //TEmployerBranchIKA ika = ikaRepo.findCompanyMainBranchByAme(company.getAme());
        TEmployerBranchIKA ika = ikaRepo.findCompanyMainBranchByAfmAndAme(company.getAfm(),company.getAme());

        if (Constants.LongIsNullOrEmpty(spPtlCompDangerAssess.getBranch0Id())
                || spPtlCompDangerAssess.getBranch0Id() == 0
                || spPtlCompDangerAssess.getBranch0Id() == -1) {
            if (Constants.LongIsNullOrEmpty(ika.getRgEbrEmpSepeId()))
                spPtlCompDangerAssess.setBranch0Id(new Long(-1));
            else
                spPtlCompDangerAssess.setBranch0Id(ika.getRgEbrEmpSepeId());
        }

        if (Constants.LongIsNullOrEmpty(spPtlCompDangerAssess.getBranch1Id())) {
            spPtlCompDangerAssess.setBranch1Id(new Long(-1));
        }

        entityManager.detach(ika);
        return true;
    }
}