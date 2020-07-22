package sepe.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Service;
import sepe.config.Constants;
import sepe.domain.company.SpPtlCompShip;
import sepe.domain.company.TCompanyUserPrivilages;
import sepe.dto.UserDTO;
import sepe.repository.company.SpPtlCompShipRepo;
import sepe.repository.company.SpPtlCompanyUserPrivilagesRepository;
import sepe.service.CurrentUserDetailsService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

/**
 * Created by Annita on 5/20/2015.
 */

@Service
@RepositoryEventHandler(SpPtlCompShip.class)
public class CompanyShipEventHandler {

    @Autowired
    SpPtlCompanyUserPrivilagesRepository companyUserPrivilagesRepository;

    @Autowired
    SpPtlCompShipRepo spPtlCompShipRepo;

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyShipEventHandler.class);

    @HandleBeforeSave
    public void handleBeforeSave(SpPtlCompShip spPtlCompShip) throws SepeRestException {
        LOGGER.debug("Handling SpPtlCompanyProjects for: Company ID " + CurrentUserDetailsService.getCurrentUser().getUsername());

        UserDTO userDTO = CurrentUserDetailsService.getCurrentUserByPrincipal();
        if (userDTO.getRole() == Constants.USER_TYPE.COMPANY_USER.getCode()) {
            TCompanyUserPrivilages priv = companyUserPrivilagesRepository.findByUser(userDTO.getId());
            ArrayList<Integer> list = new ArrayList(Arrays.asList(priv.getPrivilages().split("\\s*,\\s*")));
            if(null != priv.getPrivilages() && list.contains(String.valueOf(Constants.COMPANY_SERVICE.ASSIGN_NOTIFICATION.getCode()))) {
                Long companyId = priv.getCompId();
                spPtlCompShip.setCompanyId(companyId);
            }
            else {
                throw new SepeRestException("Ο χρήστης δεν έχει εξουσιοδότηση σε αυτό το είδος αίτησης.");
            }
        }
        else {
            spPtlCompShip.setCompanyId(CurrentUserDetailsService.getCurrentUser().getUserId());
        }

        Set<SpPtlCompShip> branchList = spPtlCompShipRepo.findAllByCompanyId(spPtlCompShip.getCompanyId());
        for (SpPtlCompShip branch : branchList) {
            if(branch.getShipImo().equals(spPtlCompShip.getShipImo())){

                throw new SepeRestException("Υπάρχει ήδη καταχωρημένο Πλοίο με τον ίδιο αριθμό.");
            }
        }
    }

    @HandleBeforeCreate
    public void handleBeforeCreates(SpPtlCompShip spPtlCompShip) throws SepeRestException {
        LOGGER.debug("Handling SpPtlCompanyProjects for: Company ID " + CurrentUserDetailsService.getCurrentUser().getUsername());

        UserDTO userDTO = CurrentUserDetailsService.getCurrentUserByPrincipal();
        if (userDTO.getRole() == Constants.USER_TYPE.COMPANY_USER.getCode()) {
            TCompanyUserPrivilages priv = companyUserPrivilagesRepository.findByUser(userDTO.getId());
            ArrayList<Integer> list = new ArrayList(Arrays.asList(priv.getPrivilages().split("\\s*,\\s*")));
            if(null != priv.getPrivilages() && list.contains(String.valueOf(Constants.COMPANY_SERVICE.ASSIGN_NOTIFICATION.getCode()))) {
                Long companyId = priv.getCompId();
                spPtlCompShip.setCompanyId(companyId);
            }
            else {
                throw new SepeRestException("Ο χρήστης δεν έχει εξουσιοδότηση σε αυτό το είδος αίτησης.");
            }
        }
        else {
            spPtlCompShip.setCompanyId(CurrentUserDetailsService.getCurrentUser().getUserId());
        }

        Set<SpPtlCompShip> branchList = spPtlCompShipRepo.findAllByCompanyId(spPtlCompShip.getCompanyId());
        for (SpPtlCompShip branch : branchList) {
            if(branch.getShipImo().equals(spPtlCompShip.getShipImo())){

                throw new SepeRestException("Υπάρχει ήδη καταχωρημένο Πλοίο με τον ίδιο αριθμό.");
            }
        }
    }
}

