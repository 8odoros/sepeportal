package sepe.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Service;
import sepe.config.SpringUserDetails;

import sepe.domain.technician.SpPtlTechnicianDiploma;
import sepe.service.CurrentUserDetailsService;

@Service
@RepositoryEventHandler(SpPtlTechnicianDiploma.class)
public class TechnicianDiplomaEventHandler {



    private static final Logger LOGGER = LoggerFactory.getLogger(TechnicianDiplomaEventHandler.class);

    @HandleBeforeSave
    public void handleBeforeSave(SpPtlTechnicianDiploma doctorCounty) throws Exception {
        LOGGER.debug("Handling SpPtlTechnicianDiploma for: User ID " + CurrentUserDetailsService.getCurrentUser().getUsername());
        SpringUserDetails currentUser = CurrentUserDetailsService.getCurrentUser();
        doctorCounty.setUserId(currentUser.getUserId());
    }

    @HandleBeforeCreate
    public void handleBeforeCreates(SpPtlTechnicianDiploma doctorCounty) throws Exception {
        LOGGER.debug("Handling SpPtlTechnicianDiploma for: User ID " + CurrentUserDetailsService.getCurrentUser().getUsername());
        SpringUserDetails currentUser = CurrentUserDetailsService.getCurrentUser();
        doctorCounty.setUserId(currentUser.getUserId());
    }


}