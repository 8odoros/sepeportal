package sepe.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import sepe.service.CurrentUserDetailsService;
import sepe.service.UserStoredProcedures;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by kirikosm on 4/8/2016.
 */
@Component
public class AuthenticationHandler {

    @Autowired
    private UserStoredProcedures userStoredProcedures;

    public int authenticate (Authentication authentication) {

        Integer funcLoginUserResult = -1;
        Map<String, Object> loginUserInput = new LinkedHashMap<String, Object>();
        loginUserInput.put("p_username", authentication.getPrincipal());
        loginUserInput.put("p_password", authentication.getCredentials() != null ? authentication.getCredentials().toString() : "");

        // call function to Reset password of a specific user in Oracle Identity Management / LDAP

        try{
            funcLoginUserResult = (userStoredProcedures).secOimCallFunction(loginUserInput, "PCG_SEC_OIM.SEC_OIM_LOGIN_USER");
            System.out.println(funcLoginUserResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return funcLoginUserResult;
    }

}
