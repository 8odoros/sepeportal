package sepe.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Service;
import sepe.config.SpringUserDetails;
import sepe.domain.company.SpPtlCompanyDailyCard;
import sepe.domain.company.SpPtlCompanyPersonnelBook;
import sepe.domain.company.SpPtlCompanyProjects;
import sepe.repository.company.CompanyDailyCardtRepository;
import sepe.repository.company.CompanyPersonnelBooktRepository;
import sepe.repository.company.CompanyProjectRepository;
import sepe.service.CurrentUserDetailsService;

import java.sql.Timestamp;
import java.util.*;

@Service
@RepositoryEventHandler(SpPtlCompanyDailyCard.class)
public class CompanyDailyBookEventHandler {

    @Autowired
    CompanyProjectRepository companyProjectRepository;

    @Autowired
    CompanyPersonnelBooktRepository companyPersonnelBooktRepository;

    @Autowired
    CompanyDailyCardtRepository companyDailyCardtRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyDailyBookEventHandler.class);

    @HandleBeforeSave
    public void handleBeforeSave(SpPtlCompanyDailyCard companyDailyCard) throws SepeRestException {
        LOGGER.debug("Handling SpPtlCompanyProjects for: Company ID " + CurrentUserDetailsService.getCurrentUser().getUsername());

        SpringUserDetails currentUser = CurrentUserDetailsService.getCurrentUser();
        int projectId = companyDailyCard.getProjectIdd();
        SpPtlCompanyProjects cpr=companyProjectRepository.findOne((long) projectId);
        companyDailyCard.setSpPtlCompanyProjects(cpr);
        Set<SpPtlCompanyPersonnelBook> pbook=companyPersonnelBooktRepository.findByDailyCardIdInternal(companyDailyCard.getId());
        companyDailyCard.setSpPtlCompanyPersonnelBookById(pbook);
        if (companyDailyCard.getIncNum()==null) companyDailyCard.setIncNum(0);

        Date today = new Date();
        companyDailyCard.setCardDate(companyDailyCard.getCardDate() != null ? companyDailyCard.getCardDate() : new Timestamp(today.getTime()));

        //Exception when a card exists for the current date
        Date date = new Date(companyDailyCard.getCardDate().getTime());
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        Calendar calToday = Calendar.getInstance();
        calToday.setTime(today);

        boolean sameDay = cal.get(Calendar.YEAR) == calToday.get(Calendar.YEAR) &&
                cal.get(Calendar.DAY_OF_YEAR) == calToday.get(Calendar.DAY_OF_YEAR);
        if (sameDay == false) {
            throw new SepeRestException("Η καρτέλα πρέπει να έχει τη σημερινή ημερομηνία. Διορθώστε την ώρα του συστήματος σας.");
        }

        //Exception if at the same project, another card exists with the same date
        Date companyDailyCardDate = new Date(companyDailyCard.getCardDate().getTime());
        Calendar calCompanyDailyCard = Calendar.getInstance();
        calCompanyDailyCard.setTime(companyDailyCardDate);

        Set<SpPtlCompanyDailyCard> cardList = companyDailyCardtRepository.findAllCardsByProjectId(new Long(projectId));
        for (SpPtlCompanyDailyCard card : cardList) {
            Date cardDate = new Date(card.getCardDate().getTime());
            Calendar calCardDate = Calendar.getInstance();
            calCardDate.setTime(cardDate);

            boolean sameDayWitDailyCard = calCardDate.get(Calendar.YEAR) == calCompanyDailyCard.get(Calendar.YEAR) &&
                    calCardDate.get(Calendar.DAY_OF_YEAR) == calCompanyDailyCard.get(Calendar.DAY_OF_YEAR);
            if (sameDayWitDailyCard == true) {
                throw new SepeRestException("Υπάρχει ήδη υποβεβλημένη κάρτα με αυτή την ημερομηνία.");
            }
        }
    }

    @HandleBeforeCreate
    public void handleBeforeCreates(SpPtlCompanyDailyCard companyDailyCard) throws SepeRestException {
        LOGGER.debug("Handling SpPtlCompanyProjects for: Company ID " + CurrentUserDetailsService.getCurrentUser().getUsername());

        SpringUserDetails currentUser = CurrentUserDetailsService.getCurrentUser();
        int projectId = companyDailyCard.getProjectIdd();
        SpPtlCompanyProjects cpr=companyProjectRepository.findOne((long) projectId);
        companyDailyCard.setSpPtlCompanyProjects(cpr);

        Set<SpPtlCompanyPersonnelBook> pbook=companyPersonnelBooktRepository.findByDailyCardIdInternal(companyDailyCard.getId());
        companyDailyCard.setSpPtlCompanyPersonnelBookById(pbook);
        if (companyDailyCard.getIncNum()==null) companyDailyCard.setIncNum(0);

        Date today = new Date();
        companyDailyCard.setCardDate(companyDailyCard.getCardDate() != null ? companyDailyCard.getCardDate() : new Timestamp(today.getTime()));

        //Exception when a card exists for the current date
        Date date = new Date(companyDailyCard.getCardDate().getTime());
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        Calendar calToday = Calendar.getInstance();
        calToday.setTime(today);

        boolean sameDay = cal.get(Calendar.YEAR) == calToday.get(Calendar.YEAR) &&
                cal.get(Calendar.DAY_OF_YEAR) == calToday.get(Calendar.DAY_OF_YEAR);
        if (sameDay == false) {
            throw new SepeRestException("Η καρτέλα πρέπει να έχει τη σημερινή ημερομηνία. Διορθώστε την ώρα του συστήματος σας.");
        }

        //Exception if at the same project, another card exists with the same date
        Date companyDailyCardDate = new Date(companyDailyCard.getCardDate().getTime());
        Calendar calCompanyDailyCard = Calendar.getInstance();
        calCompanyDailyCard.setTime(companyDailyCardDate);

        Set<SpPtlCompanyDailyCard> cardList = companyDailyCardtRepository.findAllCardsByProjectId(new Long(projectId));
        for (SpPtlCompanyDailyCard card : cardList) {
            Date cardDate = new Date(card.getCardDate().getTime());
            Calendar calCardDate = Calendar.getInstance();
            calCardDate.setTime(cardDate);

            boolean sameDayWitDailyCard = calCardDate.get(Calendar.YEAR) == calCompanyDailyCard.get(Calendar.YEAR) &&
                    calCardDate.get(Calendar.DAY_OF_YEAR) == calCompanyDailyCard.get(Calendar.DAY_OF_YEAR);
            if (sameDayWitDailyCard == true) {
                throw new SepeRestException("Υπάρχει ήδη υποβεβλημένη κάρτα με αυτή την ημερομηνία.");
            }
        }
    }

}