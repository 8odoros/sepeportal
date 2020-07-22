package sepe.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class CurrentUserControllerAdvice {

    @ModelAttribute("currentUser")
    public SpringUserDetails getCurrentUser() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) return null;
        if (authentication.getPrincipal().equals("anonymousUser")) return null;

        return (SpringUserDetails) authentication.getPrincipal();
    }
}
