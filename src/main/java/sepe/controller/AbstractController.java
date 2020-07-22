package sepe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sepe.config.Constants;
import sepe.config.SpringUserDetails;
import sepe.dto.CompanyDTO;
import sepe.dto.UserDTO;
import sepe.dto.converter.CitizenToCitizenDTO;
import sepe.dto.converter.EmployeeToEmployeeDTO;
import sepe.repository.citizen.CitizenRepository;
import sepe.repository.employee.EmployeeRepository;
import sepe.service.UserService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyEditor;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class AbstractController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CitizenRepository citizenRepository;

    private EmployeeToEmployeeDTO employeeDTOConverter;
    private CitizenToCitizenDTO citizenDTOConverter;

    private class StringEditor implements PropertyEditor {

        private String text;

        @Override
        public void setValue(final Object value) {
            this.text = String.valueOf(value);
        }

        @Override
        public Object getValue() {
            return this.text;
        }

        @Override
        public boolean isPaintable() {
            return false;
        }

        @Override
        public void paintValue(final Graphics gfx, final Rectangle box) {

        }

        @Override
        public String getJavaInitializationString() {
            return null;
        }

        @Override
        public String getAsText() {
            return this.text;
        }

        @Override
        public void setAsText(final String text) throws IllegalArgumentException {
            if (TODAY.equals(text)) {
                setValue(dateFormat.format(new Date()));
            } else {
                setValue(text);
            }
        }

        @Override
        public String[] getTags() {
            return new String[0];
        }

        @Override
        public Component getCustomEditor() {
            return null;
        }

        @Override
        public boolean supportsCustomEditor() {
            return false;
        }

        @Override
        public void addPropertyChangeListener(final PropertyChangeListener listener) {

        }

        @Override
        public void removePropertyChangeListener(final PropertyChangeListener listener) {

        }
    }

    public static final String ERROR_404 = "404";
    public static final String ERROR_401 = "401";
    public static final String ERROR_403 = "403";
    public static final String ERROR_500 = "500";
    public static final String TODAY = "today";

    protected final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * When a controller method parameter has a default value of TODAY, the binder registration here
     * will cause the default value to be the current date in "yyyy-MM-dd" format.
     */
    @InitBinder
    public void initBinder(@Nonnull final WebDataBinder binder) throws Exception {
        binder.registerCustomEditor(String.class, new StringEditor());
    }

    /**
     * Used by child class controllers to obtain the currently authenticated user from Spring Security.
     */
    @Nullable
    public final SpringUserDetails currentAuthenticatedUser() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof SpringUserDetails) {
            return (SpringUserDetails) authentication.getPrincipal();
        } else {
            return null;
        }
    }

    @Nullable
    protected final java.sql.Date stringToSqlDate(@Nonnull final String dateString) {
        java.sql.Date date = null;
        try {
            final Date utilDate = dateFormat.parse(dateString);
            date = new java.sql.Date(utilDate.getTime());
        } catch (ParseException e) {
        }
        return date;
    }

    @RequestMapping(value = {"/", "/profile"}, method = RequestMethod.GET)
    @Nonnull
    public final String viewMainProfilePage(
            @Nonnull final Model model
    ) throws Exception {
        SpringUserDetails springUserDetails = currentAuthenticatedUser();
        if (springUserDetails == null) {
            throw new Exception("Error");
        }
        final UserDTO user = springUserDetails.getUserDTO();
        model.addAttribute("user", user);
        if (user.getRole() == Constants.USER_TYPE.EMPLOYEE.getCode()) {
            return "employee";
        } else if (user.getRole() == Constants.USER_TYPE.CITIZEN.getCode()) {
            return "citizen";
        } else if (user.getRole() == Constants.USER_TYPE.COMPANY.getCode()) { //TODO for COMPANY_USER
            final Integer isAssociation = userService.findCompany(user.getId()).getIsAssociation();
            if (isAssociation == null) {
                throw new Exception("No Company Object for this User");
            }
            model.addAttribute("isAssociation", isAssociation);
            return "company";
        } else if (user.getRole() == Constants.USER_TYPE.COMPANY_USER.getCode()) {
            return "companyUser";
        }   else if (user.getRole() == Constants.USER_TYPE.TECHNICIAN.getCode()) {
            return "technician";
        } else if (user.getRole() == Constants.USER_TYPE.DOCTOR.getCode()) {
            return "doctor";
        } else if (user.getRole() == Constants.USER_TYPE.ASSOCIATION.getCode()) {
            return "company";
        } else if (user.getIsAdmin() == 1 && user.getRole() == Constants.USER_TYPE.ADMIN.getCode()) {
            return "admin";
        } else
            return ERROR_500;
    }

    @RequestMapping(value = {"/404"}, method = RequestMethod.GET)
    @Nonnull
    public final String view404Page(
            @Nonnull final Model model
    ) {
        return ERROR_404;
    }

    @RequestMapping(value = {"/403"}, method = RequestMethod.GET)
    @Nonnull
    public final String view403Page(
            @Nonnull final Model model
    ) {
        return ERROR_403;
    }

    @RequestMapping(value = {"/401"}, method = RequestMethod.GET)
    @Nonnull
    public final String view401Page(
            @Nonnull final Model model
    ) {
        return ERROR_401;
    }

    @RequestMapping(value = {"/password"}, method = RequestMethod.GET)
    @Nonnull
    public final String viewPasswordPage(
            @Nonnull final Model model
    ) {
        return Constants.PASSWORD_TEMPLATE;
    }

    @RequestMapping(value = {"/employee"}, method = RequestMethod.GET)
    @Nonnull
    public final String employee(
            @Nonnull final Model model
    ) throws Exception {
        SpringUserDetails springUserDetails = currentAuthenticatedUser();
        if (springUserDetails == null) {
            throw new Exception("Error");
        }
        final UserDTO user = springUserDetails.getUserDTO();
        model.addAttribute("user", user);
        return "employee";
    }

    @RequestMapping(value = {"/doctor"}, method = RequestMethod.GET)
    @Nonnull
    public final String doctor(
            @Nonnull final Model model
    ) throws Exception {
        SpringUserDetails springUserDetails = currentAuthenticatedUser();
        if (springUserDetails == null) {
            throw new Exception("Error");
        }
        final UserDTO user = springUserDetails.getUserDTO();
        model.addAttribute("user", user);
        return "doctor";
    }

    @RequestMapping(value = {"/technician"}, method = RequestMethod.GET)
    @Nonnull
    public final String technician(
            @Nonnull final Model model
    ) throws Exception {
        SpringUserDetails springUserDetails = currentAuthenticatedUser();
        if (springUserDetails == null) {
            throw new Exception("Error");
        }
        final UserDTO user = springUserDetails.getUserDTO();
        model.addAttribute("user", user);
        return "technician";
    }

    @RequestMapping(value = {"/association"}, method = RequestMethod.GET)
    @Nonnull
    public final String association(
            @Nonnull final Model model
    ) throws Exception {
        SpringUserDetails springUserDetails = currentAuthenticatedUser();
        if (springUserDetails == null) {
            throw new Exception("Error");
        }
        final UserDTO user = springUserDetails.getUserDTO();
        model.addAttribute("user", user);
        return "company";
    }

    @RequestMapping(value = {"/company"}, method = RequestMethod.GET)
    @Nonnull
    public final String company(
            @Nonnull final Model model
    ) throws Exception {
        SpringUserDetails springUserDetails = currentAuthenticatedUser();
        if (springUserDetails == null) {
            throw new Exception("Error");
        }
        final UserDTO user = springUserDetails.getUserDTO();
        model.addAttribute("user", user);

        if (user.getRole()==Constants.USER_TYPE.COMPANY.getCode()){
            final Integer isAssociation = userService.findCompany(user.getId()).getIsAssociation();
            if (isAssociation == null) {
                throw new Exception("No Company Object for this User");
            }
            model.addAttribute("isAssociation", isAssociation);
            return "company";
        }
        return "companyUser";

    }

    @RequestMapping(value = {"/citizen"}, method = RequestMethod.GET)
    @Nonnull
    public final String citizen(
            @Nonnull final Model model
    ) throws Exception {
        SpringUserDetails springUserDetails = currentAuthenticatedUser();
        if (springUserDetails == null) {
            throw new Exception("Error");
        }
        final UserDTO user = springUserDetails.getUserDTO();
        model.addAttribute("user", user);
        return "citizen";
    }

    @RequestMapping(value = {"/admin"}, method = RequestMethod.GET)
    @Nonnull
    public final String admin(
            @Nonnull final Model model
    ) throws Exception {
        SpringUserDetails springUserDetails = currentAuthenticatedUser();
        if (springUserDetails == null) {
            throw new Exception("Error");
        }
        final UserDTO user = springUserDetails.getUserDTO();
        model.addAttribute("user", user);
        return "admin";
    }

    @RequestMapping(value = {"/anonymous"}, method = RequestMethod.GET)
    @Nonnull
    public final String anonymous(
            @Nonnull final Model model
    ) {
        return "anonymous";
    }


    @RequestMapping(value = {"/error"}, method = RequestMethod.GET)
    @Nonnull
    public final String viewErrorPage(
            @Nonnull final Model model
    ) {
        return ERROR_500;
    }

    @RequestMapping(value = {"/fileupload"}, method = RequestMethod.GET)
    @Nonnull
    public final String fileupload(
            @Nonnull final Model model
    ) {
        return "fileupload";
    }

}
