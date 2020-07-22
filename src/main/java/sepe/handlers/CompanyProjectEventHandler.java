package sepe.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Service;
import sepe.config.Constants;
import sepe.domain.company.SpPtlCompanyProjects;
import sepe.domain.company.TCompanyUserPrivilages;
import sepe.dto.UserDTO;
import sepe.repository.company.SpPtlCompanyUserPrivilagesRepository;
import sepe.service.CurrentUserDetailsService;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

@Service
@RepositoryEventHandler(SpPtlCompanyProjects.class)
public class CompanyProjectEventHandler {

    @Autowired
    SpPtlCompanyUserPrivilagesRepository companyUserPrivilagesRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyProjectEventHandler.class);

    @HandleBeforeSave
    public void handleBeforeSave(SpPtlCompanyProjects companyProjects) throws SepeRestException {
        LOGGER.debug("Handling SpPtlCompanyProjects for: Company ID " + CurrentUserDetailsService.getCurrentUser().getUsername());

        UserDTO userDTO = CurrentUserDetailsService.getCurrentUserByPrincipal();
        if (userDTO.getRole() == Constants.USER_TYPE.COMPANY_USER.getCode()) {
            TCompanyUserPrivilages priv = companyUserPrivilagesRepository.findByUser(userDTO.getId());
            ArrayList<Integer> list = new ArrayList(Arrays.asList(priv.getPrivilages().split("\\s*,\\s*")));
            if(null != priv.getPrivilages() && list.contains(String.valueOf(Constants.COMPANY_SERVICE.DAILY_CARD.getCode()))) {
                Long companyId = priv.getCompId();
                companyProjects.setCompanyid(companyId);
            }
            else {
                throw new SepeRestException("Ο χρήστης δεν έχει εξουσιοδότηση σε αυτό το είδος αίτησης.");
            }
        }
        else {
            companyProjects.setCompanyid(CurrentUserDetailsService.getCurrentUser().getUserId());
        }

        Date today = new Date();
        companyProjects.setCreationDate(companyProjects.getCreationDate() != null ? companyProjects.getCreationDate() : new Timestamp(today.getTime()));

        //Exception when user tries to create a project with creationDate different from today
        Date date = new Date(companyProjects.getCreationDate().getTime());
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        Calendar calToday = Calendar.getInstance();
        calToday.setTime(today);

        boolean sameDay = cal.get(Calendar.YEAR) == calToday.get(Calendar.YEAR) &&
                cal.get(Calendar.DAY_OF_YEAR) == calToday.get(Calendar.DAY_OF_YEAR);
        if (sameDay == false) {
            throw new SepeRestException("Το βιβλίο έργου πρέπει να έχει τη σημερινή ημερομηνία");
        }
    }

    @HandleBeforeCreate
    public void handleBeforeCreates(SpPtlCompanyProjects companyProjects) throws SepeRestException {
        LOGGER.debug("Handling SpPtlCompanyProjects for: Company ID " + CurrentUserDetailsService.getCurrentUser().getUsername());

        UserDTO userDTO = CurrentUserDetailsService.getCurrentUserByPrincipal();
        if (userDTO.getRole() == Constants.USER_TYPE.COMPANY_USER.getCode()) {
            TCompanyUserPrivilages priv = companyUserPrivilagesRepository.findByUser(userDTO.getId());
            ArrayList<Integer> list = new ArrayList(Arrays.asList(priv.getPrivilages().split("\\s*,\\s*")));
            if(null != priv.getPrivilages() && list.contains(String.valueOf(Constants.COMPANY_SERVICE.DAILY_CARD.getCode()))) {
                Long companyId = priv.getCompId();
                companyProjects.setCompanyid(companyId);
            }
            else {
                throw new SepeRestException("Ο χρήστης δεν έχει εξουσιοδότηση σε αυτό το είδος αίτησης.");
            }
        }
        else {
            companyProjects.setCompanyid(CurrentUserDetailsService.getCurrentUser().getUserId());
        }

        Date today = new Date();
        companyProjects.setCreationDate(companyProjects.getCreationDate() != null ? companyProjects.getCreationDate() : new Timestamp(today.getTime()));

        //Exception when user tries to create a project with creationDate different from today
        Date date = new Date(companyProjects.getCreationDate().getTime());
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        Calendar calToday = Calendar.getInstance();
        calToday.setTime(today);

        boolean sameDay = cal.get(Calendar.YEAR) == calToday.get(Calendar.YEAR) &&
                cal.get(Calendar.DAY_OF_YEAR) == calToday.get(Calendar.DAY_OF_YEAR);
        if (sameDay == false) {
            throw new SepeRestException("Το βιβλίο έργου πρέπει να έχει τη σημερινή ημερομηνία");
        }
    }
}