package sepe.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Service;
import sepe.config.SpringUserDetails;
import sepe.domain.doctor.SpPtlDoctorDiploma;
import sepe.service.CurrentUserDetailsService;

@Service
@RepositoryEventHandler(SpPtlDoctorDiploma.class)
public class DoctorDiplomaEventHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(DoctorDiplomaEventHandler.class);

    @HandleBeforeSave
    public void handleBeforeSave(SpPtlDoctorDiploma doctorCounty) throws Exception {
        LOGGER.debug("Handling SpPtlTechnicianDiploma for: User ID " + CurrentUserDetailsService.getCurrentUser().getUsername());
        SpringUserDetails currentUser = CurrentUserDetailsService.getCurrentUser();
        doctorCounty.setUserId(currentUser.getUserId());
    }

    @HandleBeforeCreate
    public void handleBeforeCreates(SpPtlDoctorDiploma doctorCounty) throws Exception {
        LOGGER.debug("Handling SpPtlTechnicianDiploma for: User ID " + CurrentUserDetailsService.getCurrentUser().getUsername());
        SpringUserDetails currentUser = CurrentUserDetailsService.getCurrentUser();
        doctorCounty.setUserId(currentUser.getUserId());
    }


}