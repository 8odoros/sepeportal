package sepe.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Service;
import sepe.config.Constants;
import sepe.domain.company.SpPtlCompVehicleBook;
import sepe.domain.company.SpPtlCompVehicleBookEntry;
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
@RepositoryEventHandler(SpPtlCompVehicleBookEntry.class)
public class CompanyVehicleBookEntryEventHandler {
    @Autowired
    private CompanyStoredProcedures companyStoredProcedures;

    @Autowired
    private SpPtlCompVehicleBookRepo spPtlCompVehicleBookRepo;

    @Autowired
    private SpPtlCompVehicleBookEntryRepo spPtlCompVehicleBookEntryRepo;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private TEmployerBranchIKARepo ikaRepo;

    @Autowired
    SpPtlCompanyUserPrivilagesRepository companyUserPrivilagesRepository;

    @PersistenceContext
    private EntityManager entityManager;

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyVehicleBookEntryEventHandler.class);

    @HandleBeforeSave
    public void handleBeforeSave(SpPtlCompVehicleBookEntry spPtlCompVehicleBookEntry) throws Exception {
        LOGGER.debug("Handling SpPtlCompVehicleBookEntry for: User ID " + CurrentUserDetailsService.getCurrentUser().getUsername());

        UserDTO userDTO = CurrentUserDetailsService.getCurrentUserByPrincipal();
        if (userDTO.getRole() == Constants.USER_TYPE.COMPANY_USER.getCode()) {
            TCompanyUserPrivilages priv = companyUserPrivilagesRepository.findByUser(userDTO.getId());
            ArrayList<Integer> list = new ArrayList(Arrays.asList(priv.getPrivilages().split("\\s*,\\s*")));
            if (null != priv.getPrivilages() && list.contains(String.valueOf(Constants.COMPANY_SERVICE.DIARY_VEHICLE_SCHEDULE.getCode()))) {
                Long companyId = priv.getCompId();
                spPtlCompVehicleBookEntry.setCompanyId(companyId);
            } else {
                throw new Exception("Ο χρήστης δεν έχει εξουσιοδότηση σε αυτό το είδος αίτησης.");
            }
        } else {
            spPtlCompVehicleBookEntry.setCompanyId(CurrentUserDetailsService.getCurrentUser().getUserId());
        }

        SpPtlCompVehicleBook vehicleBook = spPtlCompVehicleBookRepo.findOne(spPtlCompVehicleBookEntry.getSpPtlCompVehicleBookId());
        if(null == vehicleBook) {
            throw new SepeRestException("Δεν βρέθηκε Βιβλίο Οχημάτων. Προσπαθήστε ξανά.");
        }
        else {
            if(vehicleBook.getSubStatus() != 2)
                throw new SepeRestException("Θα πρέπει να καταχωρήσετε πρώτα το συγκεκριμένο Βιβλίο Οχημάτων για να μπορέσετε να εισάγετε εγγραφές νέων δρομολογίων.");
        }


        Date createDate = new Date(spPtlCompVehicleBookEntry.getDateCreated().getTime());
        Calendar calCreated = Calendar.getInstance();
        calCreated.setTime(createDate);
        Date editDate = new Date(spPtlCompVehicleBookEntry.getDateUpdated().getTime());
        Calendar calEditDate = Calendar.getInstance();
        calEditDate.setTime(editDate);

        boolean sameDay = calCreated.get(Calendar.YEAR) == calEditDate.get(Calendar.YEAR) &&
                calCreated.get(Calendar.DAY_OF_YEAR) == calEditDate.get(Calendar.DAY_OF_YEAR);
        if (sameDay == false) {
            throw new SepeRestException("Μπορείτε να κάνετε αλλαγές μόνο την ίδια μέρα που έγινε η καταχώρηση του δρομολογίου.");
        }

    }

    @HandleBeforeCreate
    public void handleBeforeCreates(SpPtlCompVehicleBookEntry spPtlCompVehicleBookEntry) throws Exception {
        LOGGER.debug("Handling SpPtlCompVehicleBookEntry for: User ID " + CurrentUserDetailsService.getCurrentUser().getUsername());

        UserDTO userDTO = CurrentUserDetailsService.getCurrentUserByPrincipal();
        if (userDTO.getRole() == Constants.USER_TYPE.COMPANY_USER.getCode()) {
            TCompanyUserPrivilages priv = companyUserPrivilagesRepository.findByUser(userDTO.getId());
            ArrayList<Integer> list = new ArrayList(Arrays.asList(priv.getPrivilages().split("\\s*,\\s*")));
            if (null != priv.getPrivilages() && list.contains(String.valueOf(Constants.COMPANY_SERVICE.DIARY_VEHICLE_SCHEDULE.getCode()))) {
                Long companyId = priv.getCompId();
                spPtlCompVehicleBookEntry.setCompanyId(companyId);
            } else {
                throw new Exception("Ο χρήστης δεν έχει εξουσιοδότηση σε αυτό το είδος αίτησης.");
            }
        } else {
            spPtlCompVehicleBookEntry.setCompanyId(CurrentUserDetailsService.getCurrentUser().getUserId());
        }

        SpPtlCompVehicleBook vehicleBook = spPtlCompVehicleBookRepo.findOne(spPtlCompVehicleBookEntry.getSpPtlCompVehicleBookId());
        if(null == vehicleBook) {
            throw new SepeRestException("Δεν βρέθηκε Βιβλίο Οχημάτων. Προσπαθήστε ξανά.");
        }
        else {
            if(vehicleBook.getSubStatus() != 2)
                throw new SepeRestException("Θα πρέπει να καταχωρήσετε πρώτα το συγκεκριμένο Βιβλίο Οχημάτων για να μπορέσετε να εισάγετε εγγραφές νέων δρομολογίων.");
        }


        Date createDate = new Date(spPtlCompVehicleBookEntry.getDateCreated().getTime());
        Calendar calCreated = Calendar.getInstance();
        calCreated.setTime(createDate);
        Date editDate = new Date(spPtlCompVehicleBookEntry.getDateUpdated().getTime());
        Calendar calEditDate = Calendar.getInstance();
        calEditDate.setTime(editDate);

        boolean sameDay = calCreated.get(Calendar.YEAR) == calEditDate.get(Calendar.YEAR) &&
                calCreated.get(Calendar.DAY_OF_YEAR) == calEditDate.get(Calendar.DAY_OF_YEAR);
        if (sameDay == false) {
            throw new SepeRestException("Μπορείτε να κάνετε αλλαγές μόνο την ίδια μέρα που έγινε η καταχώρηση του δρομολογίου.");
        }

    }

}