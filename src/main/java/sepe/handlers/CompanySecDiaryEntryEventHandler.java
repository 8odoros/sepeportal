package sepe.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Service;
import sepe.config.Constants;
import sepe.domain.company.SpPtlCompSecDiary;
import sepe.domain.company.SpPtlCompSecDiaryEntry;
import sepe.domain.company.TCompanyUserPrivilages;
import sepe.dto.UserDTO;
import sepe.repository.company.*;
import sepe.service.CompanyStoredProcedures;
import sepe.service.CurrentUserDetailsService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

@Service
@RepositoryEventHandler(SpPtlCompSecDiaryEntry.class)
public class CompanySecDiaryEntryEventHandler {
    @Autowired
    private CompanyStoredProcedures companyStoredProcedures;

    @Autowired
    private SpPtlCompSecDiaryRepo spPtlCompSecDiaryRepo;

    @Autowired
    private SpPtlCompSecDiaryEntryRepo spPtlCompSecDiaryEntryRepo;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private TEmployerBranchIKARepo ikaRepo;

    @Autowired
    SpPtlCompanyUserPrivilagesRepository companyUserPrivilagesRepository;

    @PersistenceContext
    private EntityManager entityManager;

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanySecDiaryEntryEventHandler.class);

    @HandleBeforeSave
    public void handleBeforeSave(SpPtlCompSecDiaryEntry spPtlCompSecDiaryEntry) throws Exception {
        LOGGER.debug("Handling SpPtlCompSecDiaryEntry for: User ID " + CurrentUserDetailsService.getCurrentUser().getUsername());

        UserDTO userDTO = CurrentUserDetailsService.getCurrentUserByPrincipal();
        if (userDTO.getRole() == Constants.USER_TYPE.COMPANY_USER.getCode()) {
            TCompanyUserPrivilages priv = companyUserPrivilagesRepository.findByUser(userDTO.getId());
            ArrayList<Integer> list = new ArrayList(Arrays.asList(priv.getPrivilages().split("\\s*,\\s*")));
            if (null != priv.getPrivilages() && list.contains(String.valueOf(Constants.COMPANY_SERVICE.DIARY_SAFETY.getCode()))) {
                Long companyId = priv.getCompId();
                spPtlCompSecDiaryEntry.setCompanyId(companyId);
            } else {
                throw new Exception("Ο χρήστης δεν έχει εξουσιοδότηση σε αυτό το είδος αίτησης.");
            }
        } else {
            spPtlCompSecDiaryEntry.setCompanyId(CurrentUserDetailsService.getCurrentUser().getUserId());
        }

        SpPtlCompSecDiary secDiary = spPtlCompSecDiaryRepo.findOne(spPtlCompSecDiaryEntry.getCompSecDiaryId());
        if(null == secDiary) {
            throw new SepeRestException("Δεν βρέθηκε Ημερολόγιο Μέτρων Ασφαλείας. Προσπαθήστε ξανά.");
        }
        else {
            if(secDiary.getSubStatus() != 2)
                throw new SepeRestException("Θα πρέπει να υποβάλλετε πρώτα το Ημερολόγιο Μέτρων Ασφαλείας για να μπορέσετε να εισάγετε νέες καταχωρήσεις.");
        }


        Date createDate = new Date(spPtlCompSecDiaryEntry.getCreationDate().getTime());
        Calendar calCreated = Calendar.getInstance();
        calCreated.setTime(createDate);
        Date editDate = new Date(spPtlCompSecDiaryEntry.getUpdateDate().getTime());
        Calendar calEditDate = Calendar.getInstance();
        calEditDate.setTime(editDate);

        boolean sameDay = calCreated.get(Calendar.YEAR) == calEditDate.get(Calendar.YEAR) &&
                calCreated.get(Calendar.DAY_OF_YEAR) == calEditDate.get(Calendar.DAY_OF_YEAR);
        if (sameDay == false) {
            throw new SepeRestException("Μπορείτε να κάνετε αλλαγές μόνο την ίδια μέρα που έγινε η καταχώρηση του ημερολογίου.");
        }

    }

    @HandleBeforeCreate
    public void handleBeforeCreates(SpPtlCompSecDiaryEntry spPtlCompSecDiaryEntry) throws Exception {
        LOGGER.debug("Handling SpPtlCompSecDiaryEntry for: User ID " + CurrentUserDetailsService.getCurrentUser().getUsername());

        UserDTO userDTO = CurrentUserDetailsService.getCurrentUserByPrincipal();
        if (userDTO.getRole() == Constants.USER_TYPE.COMPANY_USER.getCode()) {
            TCompanyUserPrivilages priv = companyUserPrivilagesRepository.findByUser(userDTO.getId());
            ArrayList<Integer> list = new ArrayList(Arrays.asList(priv.getPrivilages().split("\\s*,\\s*")));
            if (null != priv.getPrivilages() && list.contains(String.valueOf(Constants.COMPANY_SERVICE.DIARY_SAFETY.getCode()))) {
                Long companyId = priv.getCompId();
                spPtlCompSecDiaryEntry.setCompanyId(companyId);
            } else {
                throw new Exception("Ο χρήστης δεν έχει εξουσιοδότηση σε αυτό το είδος αίτησης.");
            }
        } else {
            spPtlCompSecDiaryEntry.setCompanyId(CurrentUserDetailsService.getCurrentUser().getUserId());
        }

        SpPtlCompSecDiary secDiary = spPtlCompSecDiaryRepo.findOne(spPtlCompSecDiaryEntry.getCompSecDiaryId());
        if(null == secDiary) {
            throw new SepeRestException("Δεν βρέθηκε Ημερολόγιο Μέτρων Ασφαλείας. Προσπαθήστε ξανά.");
        }
        else {
            if(secDiary.getSubStatus() != 2)
                throw new SepeRestException("Θα πρέπει να υποβάλλετε πρώτα το Ημερολόγιο Μέτρων Ασφαλείας για να μπορέσετε να εισάγετε νέες καταχωρήσεις.");
        }


        Date createDate = new Date(spPtlCompSecDiaryEntry.getCreationDate().getTime());
        Calendar calCreated = Calendar.getInstance();
        calCreated.setTime(createDate);
        Date editDate = new Date(spPtlCompSecDiaryEntry.getUpdateDate().getTime());
        Calendar calEditDate = Calendar.getInstance();
        calEditDate.setTime(editDate);

        boolean sameDay = calCreated.get(Calendar.YEAR) == calEditDate.get(Calendar.YEAR) &&
                calCreated.get(Calendar.DAY_OF_YEAR) == calEditDate.get(Calendar.DAY_OF_YEAR);
        if (sameDay == false) {
            throw new SepeRestException("Μπορείτε να κάνετε αλλαγές μόνο την ίδια μέρα που έγινε η καταχώρηση του ημερολογίου.");
        }

    }

}