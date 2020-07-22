package sepe.config;

import org.eclipse.persistence.jpa.JpaEntityManager;
import org.eclipse.persistence.queries.StoredFunctionCall;
import org.eclipse.persistence.queries.ValueReadQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.authentication.encoding.PlaintextPasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import sepe.service.CurrentUserDetailsService;
import sepe.service.UserStoredProcedures;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by kirikosm on 4/8/2016.
 */
public class MyDaoAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    CurrentUserDetailsService currentUserDetailsService;

    @Autowired
    private ApplicationContext appContext;

    private static final String USER_NOT_FOUND_PASSWORD = "userNotFoundPassword";

    private PasswordEncoder passwordEncoder;

    private String userNotFoundEncodedPassword;

    private SaltSource saltSource;

    private UserDetailsService userDetailsService;

    public MyDaoAuthenticationProvider() {
        setPasswordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        SpringUserDetails user = currentUserDetailsService.loadUserByUsername(username);

        Object salt = null;

        if (this.saltSource != null) {
            salt = this.saltSource.getSalt(user);
        }

        /*if (user == null || !user.getUserDTO().getUsername().equalsIgnoreCase(username)) {
            throw new BadCredentialsException("Username not found.");
        }

        if (!passwordEncoder.isPasswordValid(user.getPassword(), password, salt)) {
            throw new BadCredentialsException("Wrong password.");
        }*/

        System.out.println(Constants.PAPYROS_FILE_UPLOAD_URI);
        Integer funcLoginUserResult;
        Map<String, Object> loginUserInput = new LinkedHashMap<String, Object>();
        loginUserInput.put("p_username", username);
        loginUserInput.put("p_password", password);
        // call function to Reset password of a specific user in Oracle Identity Management / LDAP
        try{
            //funcLoginUserResult = (userStoredProcedures).secOimCallFunction(loginUserInput, "PCG_SEC_OIM.SEC_OIM_LOGIN_USER");
            EntityManagerFactory fb = (EntityManagerFactory)appContext.getBean("entityManagerFactory");
            EntityManager em = fb.createEntityManager();

            StoredFunctionCall func = new StoredFunctionCall();
            func.setProcedureName("PCG_SEC_OIM.SEC_OIM_LOGIN_USER");
            func.setResult("RESULT",Integer.class);

            for(String key : loginUserInput.keySet()){
                func.addNamedArgumentValue(key, loginUserInput.get(key));
            }
            func.addNamedOutputArgument("p_message", "");

            ValueReadQuery valQuery = new ValueReadQuery();
            valQuery.setCall(func);

            Query query = ((JpaEntityManager)em.getDelegate()).createQuery(valQuery);

            funcLoginUserResult = (Integer)query.getSingleResult();
            System.out.println(funcLoginUserResult);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadCredentialsException("Wrong password.");
        }
        if (funcLoginUserResult != 0) {
            throw new BadCredentialsException("Wrong password.");
        }


        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

        return new UsernamePasswordAuthenticationToken(user, password, authorities);
    }

    public boolean supports(Class<?> arg0) {
        return true;
    }

    public void setPasswordEncoder(Object passwordEncoder) {
        Assert.notNull(passwordEncoder, "passwordEncoder cannot be null");

        if (passwordEncoder instanceof PasswordEncoder) {
            setPasswordEncoder((PasswordEncoder) passwordEncoder);
            return;
        }

        if (passwordEncoder instanceof org.springframework.security.crypto.password.PasswordEncoder) {
            final org.springframework.security.crypto.password.PasswordEncoder delegate = (org.springframework.security.crypto.password.PasswordEncoder) passwordEncoder;
            setPasswordEncoder(new PasswordEncoder() {
                public String encodePassword(String rawPass, Object salt) {
                    checkSalt(salt);
                    return delegate.encode(rawPass);
                }

                public boolean isPasswordValid(String encPass, String rawPass, Object salt) {
                    checkSalt(salt);
                    return delegate.matches(rawPass, encPass);
                }

                private void checkSalt(Object salt) {
                    Assert.isNull(salt,
                            "Salt value must be null when used with crypto module PasswordEncoder");
                }
            });

            return;
        }

        throw new IllegalArgumentException(
                "passwordEncoder must be a PasswordEncoder instance");
    }

    private void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        Assert.notNull(passwordEncoder, "passwordEncoder cannot be null");

        this.userNotFoundEncodedPassword = passwordEncoder.encodePassword(
                USER_NOT_FOUND_PASSWORD, null);
        this.passwordEncoder = passwordEncoder;
    }

    protected PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    /**
     * The source of salts to use when decoding passwords. <code>null</code> is a valid
     * value, meaning the <code>DaoAuthenticationProvider</code> will present
     * <code>null</code> to the relevant <code>PasswordEncoder</code>.
     * <p>
     * Instead, it is recommended that you use an encoder which uses a random salt and
     * combines it with the password field. This is the default approach taken in the
     * {@code org.springframework.security.crypto.password} package.
     *
     * @param saltSource to use when attempting to decode passwords via the
     * <code>PasswordEncoder</code>
     */
    public void setSaltSource(SaltSource saltSource) {
        this.saltSource = saltSource;
    }

    protected SaltSource getSaltSource() {
        return saltSource;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    protected UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

}
