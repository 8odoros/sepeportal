package sepe.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeDelete;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Service;
import sepe.config.SpringUserDetails;
import sepe.domain.general.TNotificationsAccountEntity;
import sepe.repository.NotificationsRestRepository;
import sepe.service.CurrentUserDetailsService;

@Service
@RepositoryEventHandler(TNotificationsAccountEntity.class)
public class NotificationsViewedEventHandler {


    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationsViewedEventHandler.class);

    @Autowired
    NotificationsRestRepository notificationsRestRepository;

    @HandleBeforeSave
    public void handleBeforeSave(TNotificationsAccountEntity notification) throws Exception {
        LOGGER.debug("Handling TNotificationsAccountEntity Save for: User ID " + CurrentUserDetailsService.getCurrentUser().getUsername());
        SpringUserDetails currentUser = CurrentUserDetailsService.getCurrentUser();

        TNotificationsAccountEntity dbNotification = notificationsRestRepository.findById(notification.getId());

        if (dbNotification == null) {
            throw new Exception("Notification not persisted");
        }

        if (dbNotification.getDateTime().equals(notification.getDateTime()) &&
                dbNotification.getDateTime().equals(notification.getDateTime()) &&
                dbNotification.getDocId().equals(notification.getDocId()) &&
                dbNotification.getMessage().equals(notification.getMessage()) &&
                dbNotification.getPriority().equals(notification.getPriority()) &&
                dbNotification.getSender().equals(notification.getSender()) &&
                dbNotification.getTitle().equals(notification.getTitle())) {

        } else {
            throw new Exception("Viewing notification exception.");
        }

    }

    @HandleBeforeCreate
    public void handleBeforeCreates(TNotificationsAccountEntity notification) throws Exception {
        LOGGER.debug("TNotificationsAccountEntity for: User ID " + CurrentUserDetailsService.getCurrentUser().getUsername());
        throw new Exception("Create is not allowded");
    }

    @HandleBeforeDelete
    public void handleBeforeDeletes(TNotificationsAccountEntity notification) throws Exception {
        LOGGER.debug("TNotificationsAccountEntity for: User ID " + CurrentUserDetailsService.getCurrentUser().getUsername());
        throw new Exception("Delete is not allowded");
    }
}