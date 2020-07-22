package sepe.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Service;
import sepe.config.Constants;
import sepe.config.SpringUserDetails;
import sepe.domain.company.SpPtlCompTaAnnBook;
import sepe.domain.company.TCompanyUserPrivilages;
import sepe.dto.UserDTO;
import sepe.repository.company.SpPtlCompanyUserPrivilagesRepository;
import sepe.service.CurrentUserDetailsService;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Annita on 5/20/2015.
 */

@Service
@RepositoryEventHandler(SpPtlCompTaAnnBook.class)
public class CompanyTaAnnBookEventHandler {

    @Autowired
    SpPtlCompanyUserPrivilagesRepository companyUserPrivilagesRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyTaAnnBookEventHandler.class);

    @HandleBeforeSave
    public void handleBeforeSave(SpPtlCompTaAnnBook compTaAnnBook) throws Exception {
        LOGGER.debug("Handling SpPtlCompTaAnnBook for: User ID " + CurrentUserDetailsService.getCurrentUser().getUsername());
        SpringUserDetails currentUser = CurrentUserDetailsService.getCurrentUser();

        UserDTO userDTO = CurrentUserDetailsService.getCurrentUserByPrincipal();
        if (userDTO.getRole() == Constants.USER_TYPE.COMPANY_USER.getCode()) {
            TCompanyUserPrivilages priv = companyUserPrivilagesRepository.findByUser(userDTO.getId());
            ArrayList<Integer> list = new ArrayList(Arrays.asList(priv.getPrivilages().split("\\s*,\\s*")));
            if(null != priv.getPrivilages() && list.contains(String.valueOf(Constants.COMPANY_SERVICE.BOOK_SUGGESTIONS.getCode()))) {
                Long companyId = priv.getCompId();
                compTaAnnBook.setCompanyId(companyId);
            }
            else {
                throw new SepeRestException("Ο χρήστης δεν έχει εξουσιοδότηση σε αυτό το είδος αίτησης.");
            }
        }
        else {
            compTaAnnBook.setCompanyId(currentUser.getUserId());
        }
    }

    @HandleBeforeCreate
    public void handleBeforeCreates(SpPtlCompTaAnnBook compTaAnnBook) throws Exception {
        LOGGER.debug("Handling SpPtlCompTaAnnBook for: User ID " + CurrentUserDetailsService.getCurrentUser().getUsername());
        SpringUserDetails currentUser = CurrentUserDetailsService.getCurrentUser();

        UserDTO userDTO = CurrentUserDetailsService.getCurrentUserByPrincipal();
        if (userDTO.getRole() == Constants.USER_TYPE.COMPANY_USER.getCode()) {
            TCompanyUserPrivilages priv = companyUserPrivilagesRepository.findByUser(userDTO.getId());
            ArrayList<Integer> list = new ArrayList(Arrays.asList(priv.getPrivilages().split("\\s*,\\s*")));
            if(null != priv.getPrivilages() && list.contains(String.valueOf(Constants.COMPANY_SERVICE.BOOK_SUGGESTIONS.getCode()))) {
                Long companyId = priv.getCompId();
                compTaAnnBook.setCompanyId(companyId);
            }
            else {
                throw new SepeRestException("Ο χρήστης δεν έχει εξουσιοδότηση σε αυτό το είδος αίτησης.");
            }
        }
        else {
            compTaAnnBook.setCompanyId(currentUser.getUserId());
        }
    }

}