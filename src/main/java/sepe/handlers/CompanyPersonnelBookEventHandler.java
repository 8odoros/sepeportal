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
import sepe.repository.company.CompanyDailyCardtRepository;
import sepe.service.CurrentUserDetailsService;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@Service
@RepositoryEventHandler(SpPtlCompanyPersonnelBook.class)
public class CompanyPersonnelBookEventHandler {

    @Autowired
    CompanyDailyCardtRepository companyDailyCardtRepository;


    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyPersonnelBookEventHandler.class);

    @HandleBeforeSave
    public void handleBeforeSave(SpPtlCompanyPersonnelBook personnelBook) throws SepeRestException {
        LOGGER.debug("Handling SpPtlCompanyProjects for: Company ID " + CurrentUserDetailsService.getCurrentUser().getUsername());

        SpringUserDetails currentUser = CurrentUserDetailsService.getCurrentUser();
        int dailyCardId = personnelBook.getDCard();
        SpPtlCompanyDailyCard cpr = companyDailyCardtRepository.findOne((long) dailyCardId);
        personnelBook.setSpPtlCompanyDailyCard(cpr);

        if (personnelBook.getIncNum() == null) personnelBook.setIncNum(0);

        //Set edit time and submit time to current date if they are not provided
        Date today = new Date();
        personnelBook.setEditTime(personnelBook.getEditTime() != null ? personnelBook.getEditTime() : new Timestamp(today.getTime()));
        personnelBook.setSubmitTime(personnelBook.getSubmitTime() != null ? personnelBook.getSubmitTime() : new Timestamp(today.getTime()));

        //Exception when user tries to edit a submitted form
        Date editDate = new Date(personnelBook.getEditTime().getTime());
        Calendar calEditDate = Calendar.getInstance();
        calEditDate.setTime(editDate);

        Date submitDate = new Date(personnelBook.getSubmitTime().getTime());
        Calendar calSubmitDate = Calendar.getInstance();
        calSubmitDate.setTime(submitDate);

        boolean sameEditSubmitDay = calEditDate.get(Calendar.YEAR) == calSubmitDate.get(Calendar.YEAR) &&
                calEditDate.get(Calendar.DAY_OF_YEAR) == calSubmitDate.get(Calendar.DAY_OF_YEAR);
        if (sameEditSubmitDay == false) {
            throw new SepeRestException("Μπορείτε να επεξεργαστείτε μόνο σημερινές καρτέλες προσωπικού.");
        }

        //Exception if user tries to create/edit a personel book of an old card
        Date date = new Date(cpr.getCardDate().getTime());
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        Calendar calToday = Calendar.getInstance();
        calToday.setTime(today);

        boolean sameDay = cal.get(Calendar.YEAR) == calToday.get(Calendar.YEAR) &&
                cal.get(Calendar.DAY_OF_YEAR) == calToday.get(Calendar.DAY_OF_YEAR);
        if (null != cpr.getCardDate() && sameDay == false) {
            throw new SepeRestException("Η καρτέλα προσωπικού πρέπει να αντιστοιχεί σε καρτέλα ημέρας με σημερινή ημερομηνία.");
        }
    }

    @HandleBeforeCreate
    public void handleBeforeCreates(SpPtlCompanyPersonnelBook personnelBook) throws SepeRestException {
        LOGGER.debug("Handling SpPtlCompanyProjects for: Company ID " + CurrentUserDetailsService.getCurrentUser().getUsername());

        SpringUserDetails currentUser = CurrentUserDetailsService.getCurrentUser();
        int dailyCardId = personnelBook.getDCard();
        SpPtlCompanyDailyCard cpr = companyDailyCardtRepository.findOne((long) dailyCardId);
        personnelBook.setSpPtlCompanyDailyCard(cpr);

        if (personnelBook.getIncNum() == null) personnelBook.setIncNum(0);

        //Set edit time and submit time to current date if they are not provided
        Date today = new Date();
        personnelBook.setEditTime(personnelBook.getEditTime() != null ? personnelBook.getEditTime() : new Timestamp(today.getTime()));
        personnelBook.setSubmitTime(personnelBook.getSubmitTime() != null ? personnelBook.getSubmitTime() : new Timestamp(today.getTime()));

        //Exception when user tries to edit a submitted form
        Date editDate = new Date(personnelBook.getEditTime().getTime());
        Calendar calEditDate = Calendar.getInstance();
        calEditDate.setTime(editDate);

        Date submitDate = new Date(personnelBook.getSubmitTime().getTime());
        Calendar calSubmitDate = Calendar.getInstance();
        calSubmitDate.setTime(submitDate);

        boolean sameEditSubmitDay = calEditDate.get(Calendar.YEAR) == calSubmitDate.get(Calendar.YEAR) &&
                calEditDate.get(Calendar.DAY_OF_YEAR) == calSubmitDate.get(Calendar.DAY_OF_YEAR);
        if (sameEditSubmitDay == false) {
            throw new SepeRestException("Μπορείτε να επεξεργαστείτε μόνο σημερινές καρτέλες προσωπικού.");
        }

        //Exception if user tries to create/edit a personel book of an old card
        Date date = new Date(cpr.getCardDate().getTime());
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        Calendar calToday = Calendar.getInstance();
        calToday.setTime(today);

        boolean sameDay = cal.get(Calendar.YEAR) == calToday.get(Calendar.YEAR) &&
                cal.get(Calendar.DAY_OF_YEAR) == calToday.get(Calendar.DAY_OF_YEAR);
        if (null != cpr.getCardDate() && sameDay == false) {
            throw new SepeRestException("Η καρτέλα προσωπικού πρέπει να αντιστοιχεί σε καρτέλα ημέρας με σημερινή ημερομηνία.");
        }
    }
}