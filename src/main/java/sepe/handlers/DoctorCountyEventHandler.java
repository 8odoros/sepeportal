package sepe.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Service;
import sepe.config.SpringUserDetails;
import sepe.domain.doctor.SpPtlDoctorCounty;
import sepe.service.CurrentUserDetailsService;

@Service
@RepositoryEventHandler(SpPtlDoctorCounty.class)
public class DoctorCountyEventHandler {


    private static final Logger LOGGER = LoggerFactory.getLogger(DoctorCountyEventHandler.class);

    @HandleBeforeSave
    public void handleBeforeSave(SpPtlDoctorCounty doctorCounty) throws Exception {
        LOGGER.debug("Handling SpPtlDoctorCounty for: User ID " + CurrentUserDetailsService.getCurrentUser().getUsername());
        SpringUserDetails currentUser = CurrentUserDetailsService.getCurrentUser();
        doctorCounty.setUserId(currentUser.getUserId());
    }

    @HandleBeforeCreate
    public void handleBeforeCreates(SpPtlDoctorCounty doctorCounty) throws Exception {
        LOGGER.debug("Handling SpPtlDoctorCounty for: User ID " + CurrentUserDetailsService.getCurrentUser().getUsername());
        SpringUserDetails currentUser = CurrentUserDetailsService.getCurrentUser();
        doctorCounty.setUserId(currentUser.getUserId());
    }


}