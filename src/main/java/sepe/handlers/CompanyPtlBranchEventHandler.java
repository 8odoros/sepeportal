package sepe.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Service;
import sepe.config.Constants;
import sepe.domain.company.SpPtlCompPtlBranch;
import sepe.domain.company.TCompanyUserPrivilages;
import sepe.dto.UserDTO;
import sepe.repository.company.SpPtlCompPtlBranchRepo;
import sepe.repository.company.SpPtlCompanyUserPrivilagesRepository;
import sepe.service.CurrentUserDetailsService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

/**
 * Created by Annita on 5/20/2015.
 */

@Service
@RepositoryEventHandler(SpPtlCompPtlBranch.class)
public class CompanyPtlBranchEventHandler {

    @Autowired
    SpPtlCompanyUserPrivilagesRepository companyUserPrivilagesRepository;

    @Autowired
    SpPtlCompPtlBranchRepo spPtlCompPtlBranchRepo;

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyPtlBranchEventHandler.class);

    @HandleBeforeSave
    public void handleBeforeSave(SpPtlCompPtlBranch spPtlCompPtlBranch) throws SepeRestException {
        LOGGER.debug("Handling SpPtlCompanyProjects for: Company ID " + CurrentUserDetailsService.getCurrentUser().getUsername());

        UserDTO userDTO = CurrentUserDetailsService.getCurrentUserByPrincipal();
        if (userDTO.getRole() == Constants.USER_TYPE.COMPANY_USER.getCode()) {
            TCompanyUserPrivilages priv = companyUserPrivilagesRepository.findByUser(userDTO.getId());
            ArrayList<Integer> list = new ArrayList(Arrays.asList(priv.getPrivilages().split("\\s*,\\s*")));
            if(null != priv.getPrivilages() && list.contains(String.valueOf(Constants.COMPANY_SERVICE.ASSIGN_NOTIFICATION.getCode()))) {
                Long companyId = priv.getCompId();
                spPtlCompPtlBranch.setCompanyId(companyId);
            }
            else {
                throw new SepeRestException("Ο χρήστης δεν έχει εξουσιοδότηση σε αυτό το είδος αίτησης.");
            }
        }
        else {
            spPtlCompPtlBranch.setCompanyId(CurrentUserDetailsService.getCurrentUser().getUserId());
        }

        Set<SpPtlCompPtlBranch> branchList = spPtlCompPtlBranchRepo.findAllByCompanyId(spPtlCompPtlBranch.getCompanyId());
        for (SpPtlCompPtlBranch branch : branchList) {
            if(branch.getBrAddr().equals(spPtlCompPtlBranch.getBrAddr())
                    && branch.getBrAddrTk().equals(spPtlCompPtlBranch.getBrAddrTk())
                    && branch.getBrAddrK().equals(spPtlCompPtlBranch.getBrAddrK())){

                throw new SepeRestException("Υπάρχει ήδη καταχωρημένο Παράρτημα με αυτή τη διεύθυνση.");
            }
        }
    }

    @HandleBeforeCreate
    public void handleBeforeCreates(SpPtlCompPtlBranch spPtlCompPtlBranch) throws SepeRestException {
        LOGGER.debug("Handling SpPtlCompanyProjects for: Company ID " + CurrentUserDetailsService.getCurrentUser().getUsername());

        UserDTO userDTO = CurrentUserDetailsService.getCurrentUserByPrincipal();
        if (userDTO.getRole() == Constants.USER_TYPE.COMPANY_USER.getCode()) {
            TCompanyUserPrivilages priv = companyUserPrivilagesRepository.findByUser(userDTO.getId());
            ArrayList<Integer> list = new ArrayList(Arrays.asList(priv.getPrivilages().split("\\s*,\\s*")));
            if(null != priv.getPrivilages() && list.contains(String.valueOf(Constants.COMPANY_SERVICE.ASSIGN_NOTIFICATION.getCode()))) {
                Long companyId = priv.getCompId();
                spPtlCompPtlBranch.setCompanyId(companyId);
            }
            else {
                throw new SepeRestException("Ο χρήστης δεν έχει εξουσιοδότηση σε αυτό το είδος αίτησης.");
            }
        }
        else {
            spPtlCompPtlBranch.setCompanyId(CurrentUserDetailsService.getCurrentUser().getUserId());
        }

        Set<SpPtlCompPtlBranch> branchList = spPtlCompPtlBranchRepo.findAllByCompanyId(spPtlCompPtlBranch.getCompanyId());
        for (SpPtlCompPtlBranch branch : branchList) {
            if(branch.getBrAddr().equals(spPtlCompPtlBranch.getBrAddr())
                    && branch.getBrAddrTk().equals(spPtlCompPtlBranch.getBrAddrTk())
                    && branch.getBrAddrK().equals(spPtlCompPtlBranch.getBrAddrK())){

                throw new SepeRestException("Υπάρχει ήδη καταχωρημένο Παράρτημα με αυτή τη διεύθυνση.");
            }
        }
    }
}

