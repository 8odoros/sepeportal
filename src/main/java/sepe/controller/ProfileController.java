package sepe.controller;


import com.itextpdf.text.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.*;
import org.codehaus.jettison.json.JSONObject;
import org.glassfish.jersey.client.oauth1.AccessToken;
import org.glassfish.jersey.client.oauth1.ConsumerCredentials;
import org.glassfish.jersey.client.oauth1.OAuth1AuthorizationFlow;
import org.glassfish.jersey.client.oauth1.OAuth1ClientSupport;
import org.glassfish.jersey.oauth1.signature.PlaintextMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sepe.config.Constants;
import sepe.config.SpringUserDetails;
import sepe.domain.SpPtlVUserSafetyTechnician;
import sepe.domain.SpPtlVUserWorkplaceDoctor;
import sepe.domain.TUser;
import sepe.domain.company.*;
import sepe.domain.doctor.*;
import sepe.domain.employee.TEmployee;
import sepe.domain.general.*;
import sepe.domain.technician.*;
import sepe.dto.*;
import sepe.handlers.*;
import sepe.repository.NotificationsRestRepository;
import sepe.repository.UserRepository;
import sepe.repository.citizen.CitizenRepository;
import sepe.repository.company.*;
import sepe.repository.doctor.*;
import sepe.repository.employee.EmployeeRepository;
import sepe.repository.general.*;
import sepe.repository.technician.*;
import sepe.service.*;

import java.sql.Time;
import java.sql.Timestamp;

import javax.annotation.Nonnull;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import java.io.*;
import java.net.InetAddress;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.List;
import java.util.stream.LongStream;

import static sepe.config.Constants.StringIsNullOrEmpty;
import static sepe.webservicessepe.WebServicesMainMethodPortClient.callIkaWS;

@Controller("ProfileController")
public final class ProfileController extends AbstractController {
    private static final Random RANDOM = new SecureRandom();
    private final UserService userService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ProfileController.class);

    @Autowired
    private SpPtlVCompTaAnnDiaryBRepo spPtlVCompTaAnnDiaryBRepo;

    @Autowired
    private CompanyStoredProcedures companyStoredProcedures;

    @Autowired
    private TEmployerBranchIKARepo ikaRepo;

    @Autowired
    private SpPtlTechnicianRegrequestRepo spPtlTechnicianRegrequestRepo;

    @Autowired
    private SpPtlDoctorRegrequestRepo spPtlDoctorRegrequestRepo;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private MailService mailService;

    @Autowired
    SpPtlCompanyUserPrivilagesRepository companyUserPrivilagesRepository;

    @Autowired
    SpPtlDoctorRegrequestRepo doctorRegrequestRepo;

    @Autowired
    SpPtlTechnicianRegrequestRepo technicianRegrequestRepo;

    @Autowired
    SpPtlVTechnicianRegrequestRepo technicianVRegrequestRepo;

    @Autowired
    SpRtMedicalAssocRepo spRtMedicalAssocRepo;

    @Autowired
    SpPtlDoctorCountyRepo spPtlDoctorCountyRepo;

    @Autowired
    SpPtlCompIeAnnRepo spPtlCompIeAnnRepo;

    @Autowired
    SpPtlCompTaAnnRepo spPtlCompTaAnnRepo;

    @Autowired
    SpPtlCompSundayPmtRepo spPtlCompSundayPmtRepo;

    @Autowired
    TSepeDepartmentRepository tSepeDepartmentRepository;

    @Autowired
    TSepeDepartmentRepository tSepeDepartment;

    @Autowired
    SpPtlCompTaAnnTaEntryRepo spPtlCompTaAnnTaEntryRepo;

    @Autowired
    SpPtlTechnicianDiplomaRepo spPtlTechnicianDiplomaRepo;

    @Autowired
    SpPtlDoctorDiplomaRepo spPtlDoctorDiplomaRepo;

    @Autowired
    SpPtlCompIeAnnIeEntryRepo spPtlCompIeAnnIeEntryRepo;

    @Autowired
    SpPtlCompTaSannRepo spPtlCompTaSannRepo;

    @Autowired
    private SpPtlVCompTaAnnDiaryRepo spPtlVCompTaAnnDiaryRepo;

    @Autowired
    private SpPtlCompTaAnnDiaryEntryRepo spPtlCompTaAnnDiaryEntryRepo;

    @Autowired
    private SpPtlVCompIeAnnDiaryRepo spPtlVCompIeAnnDiaryRepo;

    @Autowired
    private SpPtlCompIeAnnDiaryEntryRepo spPtlCompIeAnnDiaryEntryRepo;

    @Autowired
    SpPtlCompIeAnnBookNoteRepo spPtlCompIeAnnBookNoteRepo;

    @Autowired
    SpPtlCompTaAnnBookNoteRepo spPtlCompTaAnnBookNoteRepo;

    @Autowired
    SpPtlCompTaSannBookNoteRepo spPtlCompTaSannBookNoteRepo;

    @Autowired
    SpPtlCompIeAnnBookRepo spPtlCompIeAnnBookRepo;

    @Autowired
    SpPtlCompTaAnnBookRepo spPtlCompTaAnnBookRepo;

    @Autowired
    SpPtlCompTaSannBookRepo spPtlCompTaSannBookRepo;

    @Autowired
    TEmployerBranchIKARepo tEmployerBranchIKARepo;

    @Autowired
    private CompanyService companyService;

    @Autowired
    SpPtlCompanyServiceRepo spPtlCompanyServiceRepo;

    @Autowired
    SpPtlCompPtlBranchRepo spPtlCompPtlBranchRepo;

    @Autowired
    SpPtlCompShipRepo spPtlCompShipRepo;

    @Autowired
    NotificationsRestRepository notificationsRestRepository;

    @Autowired
    public ProfileController(@Nonnull final UserService userService) {
        this.userService = userService;
    }

    @Autowired
    private UserStoredProcedures userStoredProcedures;

    @Autowired
    SpRgGgdeMhtrwoUsersRestRepo spRgGgdeMhtrwoUsersRestRepo;

    @Autowired
    SnRgVwTaRegNezRepo snRgVwTaRegNezRepo;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    SafetyTechnicianRepository safetyTechnicianRepository;

    @Autowired
    WorkplaceDoctorRepository workplaceDoctorRepository;

    @Autowired
    SpRtTaSpecialityRepo spRtTaSpecialityRepository;

    @Autowired
    SpRtTaBranchSectorRepo spRtTaBranchSectorRepository;

    @Autowired
    CitizenRepository citizenRepository;

    @Autowired
    SpPtlCompGenreqRepo spPtlCompGenreqRepo;

    @Autowired
    DocumentStoreRest documentStoreRest;

    @Autowired
    private DocumentStoredProcedures documentStoredProcedures;

    @PersistenceContext
    private EntityManager entityManager;

    @RequestMapping(value = {"/employeeProfile"}, method = RequestMethod.GET)
    @Nonnull
    public final String viewEmployeeProfile(
            @Nonnull final Model model
    ) throws Exception {
        SpringUserDetails springUserDetails = currentAuthenticatedUser();
        if (springUserDetails == null) {
            throw new Exception("Error");
        }
        final UserDTO user = springUserDetails.getUserDTO();
        final EmployeeDTO employee = userService.findEmployee(user.getId());
        if (employee == null) {
            throw new Exception("No Employee Object for this User");
        }
        if (employee.getAmka() == null)
            employee.setAmka("");
        if (employee.getCardNumber() == null)
            employee.setCardNumber("");
        if (employee.getCompanyName() == null)
            employee.setCompanyName("");
        model.addAttribute("employee", employee);
        model.addAttribute("user", user);
        return Constants.EMPLOYEE_PROFILE_TEMPLATE;
    }

    @RequestMapping(value = {"/employeeProfile/save"}, method = RequestMethod.POST)
    @Nonnull
    public final String updateEmployeeProfile(
            @Nonnull
            @RequestParam(value = "currentPassword")
            final String currentPassword,

            @Nonnull
            @RequestParam(value = "newPassword")
            final String newPassword,

            @Nonnull
            @RequestParam(value = "reenterNewPassword")
            final String reenterNewPassword,

            @Nonnull
            @ModelAttribute("employee")
            final EmployeeDTO employee,

            //@Valid
            @Nonnull
            @ModelAttribute("user")
                    UserDTO user,

            @Nonnull
            final BindingResult result,

            @Nonnull
            final Model model

    ) throws Exception {
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            model.addAttribute("profileSaveError", "Το email δεν είναι σωστό. Προσπαθήστε ξανά!");
            return Constants.EMPLOYEE_PROFILE_TEMPLATE;
        }
        if (currentPassword == null || currentPassword.isEmpty()) {
            model.addAttribute("profileSaveError", "Παρακαλώ εισάγετε το τρέχον password για να μπορέσετε να σώσετε τις αλλαγές.");

        } else if (newPassword != null && !newPassword.isEmpty() && reenterNewPassword != null && !reenterNewPassword.equals(newPassword)) {
            model.addAttribute("profileSaveError", "Το 'Νέο Password' και 'Επιβεβαίωση νέου Password' πεδία πρέπει να συμφωνούν.");
        } else {

            if (newPassword.isEmpty()) {
                Integer funcChangeEmailResult;
                Map<String, Object> changeEmailInput = new LinkedHashMap<String, Object>();
                changeEmailInput.put("p_username", user.getUsername());
                changeEmailInput.put("p_newemail", user.getEmail());

                // call function to change email of a specific user in Oracle Identity Management / LDAP
                funcChangeEmailResult = (userStoredProcedures).secOimCallFunction(changeEmailInput, "PCG_SEC_OIM.SEC_OIM_CHANGE_EMAIL");
                if (funcChangeEmailResult != 0) {
                    model.addAttribute("profileSaveError", "Προέκυψε κάποιο πρόβλημα με την αλλαγή του email.");
                } else {
                    userService.updateUser(user, null);
                    userService.updateEmployee(employee);
                    // Update the user in the active Spring Security session
                    final UserDTO updatedUser = userService.findUser(user.getId());
                    final String passwordHash = userService.getPasswordHash(user);
                    final EmployeeDTO updatedEmployee = userService.findEmployee(employee.getUserId());
                    final SpringUserDetails newUserDetails = new SpringUserDetails(updatedUser, updatedUser.getId(), passwordHash, null, null);
                    SpringUserDetails currentUser = CurrentUserDetailsService.getCurrentUser();
                    final UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(newUserDetails, passwordHash, currentUser.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(newAuth);
                }
            } else if (!newPassword.isEmpty() && newPassword.length() < 6) {
                model.addAttribute("profileSaveError", "Το νέο password πρέπει να αποτελείται από τουλάχιστον 6 χαρακτήρες.");
            } else if (!newPassword.isEmpty() && !newPassword.matches(".*[a-zA-Z]+.*")) {
                model.addAttribute("profileSaveError", "Παρακαλώ το password πρέπει να περιέχει τουλάχιστον 1 αλφαβητικό (latin) χαρακτήρα");
            } else if (!newPassword.isEmpty() && !newPassword.matches(".*\\d+.*")) {
                model.addAttribute("profileSaveError", "Παρακαλώ το password πρέπει να περιέχει τουλάχιστον 1 αριθμητικό χαρακτήρα.");
            } else if (!newPassword.isEmpty() && (newPassword.toLowerCase().contains(user.getUsername().toLowerCase()) || newPassword.toLowerCase().contains(user.getLastname().toLowerCase()))) {
                model.addAttribute("profileSaveError", "Παρακαλώ το password πρέπει να μην περιέχει το username, το επώνυμο ή το όνομα.");
            } else {

                Integer funcResetPassResult;
                Map<String, Object> resetPassInput = new LinkedHashMap<String, Object>();
                resetPassInput.put("p_username", user.getUsername());
                resetPassInput.put("p_new_pass", newPassword);

                // call function to Reset password of a specific user in Oracle Identity Management / LDAP
                funcResetPassResult = (userStoredProcedures).secOimCallFunction(resetPassInput, "PCG_SEC_OIM.SEC_OIM_RESET_PASS");
                if (funcResetPassResult != 0) {
                    model.addAttribute("profileSaveError", "Προέκυψε κάποιο πρόβλημα με την αλλαγή password.");
                } else {
                    Integer funcChangeEmailResult;
                    Map<String, Object> changeEmailInput = new LinkedHashMap<String, Object>();
                    changeEmailInput.put("p_username", user.getUsername());
                    changeEmailInput.put("p_newemail", user.getEmail());

                    // call function to change email of a specific user in Oracle Identity Management / LDAP
                    funcChangeEmailResult = (userStoredProcedures).secOimCallFunction(changeEmailInput, "PCG_SEC_OIM.SEC_OIM_CHANGE_EMAIL");
                    if (funcChangeEmailResult != 0) {
                        model.addAttribute("profileSaveError", "Προέκυψε κάποιο πρόβλημα με την αλλαγή του email.");
                    } else {
                        userService.updateUser(user, newPassword);
                        userService.updateEmployee(employee);
                        // Update the user in the active Spring Security session
                        final UserDTO updatedUser = userService.findUser(user.getId());
                        final String passwordHash = userService.getPasswordHash(user);
                        final EmployeeDTO updatedEmployee = userService.findEmployee(employee.getUserId());
                        final SpringUserDetails newUserDetails = new SpringUserDetails(updatedUser, updatedUser.getId(), passwordHash, null, null);
                        SpringUserDetails currentUser = CurrentUserDetailsService.getCurrentUser();
                        final UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(newUserDetails, passwordHash, currentUser.getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(newAuth);
                    }
                }
            }
        }

        return viewEmployeeProfile(model);
    }

    @RequestMapping(value = {"/citizenProfile"}, method = RequestMethod.GET)
    @Nonnull
    public final String viewCitizenProfile(
            @Nonnull final Model model
    ) throws Exception {
        SpringUserDetails springUserDetails = currentAuthenticatedUser();
        if (springUserDetails == null) {
            throw new Exception("Error");
        }
        final UserDTO user = springUserDetails.getUserDTO();
        model.addAttribute("user", user);

        return Constants.CITIZEN_PROFILE_TEMPLATE;
    }

    @RequestMapping(value = {"/citizenProfile/save"}, method = RequestMethod.POST)
    @Nonnull
    public final String updateCitizenProfile(
            @Nonnull
            @RequestParam(value = "currentPassword")
            final String currentPassword,

            @Nonnull
            @RequestParam(value = "newPassword")
            final String newPassword,

            @Nonnull
            @RequestParam(value = "reenterNewPassword")
            final String reenterNewPassword,

            @Nonnull
            @ModelAttribute("user")
                    UserDTO user,

            @Nonnull
            final BindingResult result,

            @Nonnull
            final Model model
    ) throws Exception {
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            model.addAttribute("profileSaveError", "Το email δεν είναι σωστό. Προσπαθήστε ξανά!");
            return Constants.CITIZEN_PROFILE_TEMPLATE;
        }
        if (currentPassword == null || currentPassword.isEmpty()) {
            model.addAttribute("profileSaveError", "Παρακαλώ εισάγετε το τρέχον password για να μπορέσετε να σώσετε τις αλλαγές.");
            //} else if (!userService.verifyPassword(user, currentPassword)) {
            //    model.addAttribute("profileSaveError", "The password entered does not match the current password.");
        } else if (newPassword != null && !newPassword.isEmpty() && reenterNewPassword != null && !reenterNewPassword.equals(newPassword)) {
            model.addAttribute("profileSaveError", "Το 'Νέο Password' και 'Επιβεβαίωση νέου Password' πεδία πρέπει να συμφωνούν.");
        } else {

            if (newPassword.isEmpty()) {
                Integer funcChangeEmailResult;
                Map<String, Object> changeEmailInput = new LinkedHashMap<String, Object>();
                changeEmailInput.put("p_username", user.getUsername());
                changeEmailInput.put("p_newemail", user.getEmail());

                // call function to change email of a specific user in Oracle Identity Management / LDAP
                funcChangeEmailResult = (userStoredProcedures).secOimCallFunction(changeEmailInput, "PCG_SEC_OIM.SEC_OIM_CHANGE_EMAIL");
                if (funcChangeEmailResult != 0) {
                    model.addAttribute("profileSaveError", "Προέκυψε κάποιο πρόβλημα με την αλλαγή του email.");
                } else {
                    userService.updateUser(user, null);
                    // Update the user in the active Spring Security session
                    final UserDTO updatedUser = userService.findUser(user.getId());
                    final String passwordHash = userService.getPasswordHash(user);
                    final SpringUserDetails newUserDetails = new SpringUserDetails(updatedUser, updatedUser.getId(), passwordHash, null, null);
                    SpringUserDetails currentUser = CurrentUserDetailsService.getCurrentUser();
                    final UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(newUserDetails, passwordHash, currentUser.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(newAuth);
                }
            } else if (!newPassword.isEmpty() && newPassword.length() < 6) {
                model.addAttribute("profileSaveError", "Το νέο password πρέπει να αποτελείται από τουλάχιστον 6 χαρακτήρες.");
            } else if (!newPassword.isEmpty() && !newPassword.matches(".*[a-zA-Z]+.*")) {
                model.addAttribute("profileSaveError", "Παρακαλώ το password πρέπει να περιέχει τουλάχιστον 1 αλφαβητικό (latin) χαρακτήρα");
            } else if (!newPassword.isEmpty() && !newPassword.matches(".*\\d+.*")) {
                model.addAttribute("profileSaveError", "Παρακαλώ το password πρέπει να περιέχει τουλάχιστον 1 αριθμητικό χαρακτήρα.");
            } else if (!newPassword.isEmpty() && (newPassword.toLowerCase().contains(user.getUsername().toLowerCase()) || newPassword.toLowerCase().contains(user.getLastname().toLowerCase()))) {
                model.addAttribute("profileSaveError", "Παρακαλώ το password πρέπει να μην περιέχει το username, το επώνυμο ή το όνομα.");
            } else {

                Integer funcResetPassResult;
                Map<String, Object> resetPassInput = new LinkedHashMap<String, Object>();
                resetPassInput.put("p_username", user.getUsername());
                resetPassInput.put("p_new_pass", newPassword);

                // call function to Reset password of a specific user in Oracle Identity Management / LDAP
                funcResetPassResult = (userStoredProcedures).secOimCallFunction(resetPassInput, "PCG_SEC_OIM.SEC_OIM_RESET_PASS");
                if (funcResetPassResult != 0) {
                    model.addAttribute("profileSaveError", "Προέκυψε κάποιο πρόβλημα με την αλλαγή password.");
                } else {
                    Integer funcChangeEmailResult;
                    Map<String, Object> changeEmailInput = new LinkedHashMap<String, Object>();
                    changeEmailInput.put("p_username", user.getUsername());
                    changeEmailInput.put("p_newemail", user.getEmail());

                    // call function to change email of a specific user in Oracle Identity Management / LDAP
                    funcChangeEmailResult = (userStoredProcedures).secOimCallFunction(changeEmailInput, "PCG_SEC_OIM.SEC_OIM_CHANGE_EMAIL");
                    if (funcChangeEmailResult != 0) {
                        model.addAttribute("profileSaveError", "Προέκυψε κάποιο πρόβλημα με την αλλαγή του email.");
                    } else {
                        userService.updateUser(user, newPassword);
                        // Update the user in the active Spring Security session
                        final UserDTO updatedUser = userService.findUser(user.getId());
                        final String passwordHash = userService.getPasswordHash(user);
                        final SpringUserDetails newUserDetails = new SpringUserDetails(updatedUser, updatedUser.getId(), passwordHash, null, null);
                        SpringUserDetails currentUser = CurrentUserDetailsService.getCurrentUser();
                        final UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(newUserDetails, passwordHash, currentUser.getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(newAuth);
                    }
                }
            }
        }
        return viewCitizenProfile(model);
    }

    @RequestMapping(value = {"/companyProfile"}, method = RequestMethod.GET)
    @Nonnull
    public final String viewCompanyProfile(
            @Nonnull final Model model
    ) throws Exception {
        SpringUserDetails springUserDetails = currentAuthenticatedUser();
        if (springUserDetails == null) {
            throw new Exception("Error");
        }
        final UserDTO user = springUserDetails.getUserDTO();
        final CompanyDTO company = userService.findCompany(user.getId());
        if (company == null) {
            throw new Exception("No Company Object for this User");
        }
        model.addAttribute("company", company);
        model.addAttribute("user", user);
        return Constants.COMPANY_PROFILE_TEMPLATE;
    }


    @RequestMapping(value = {"/companyIsExypp"}, method = RequestMethod.GET)
    @Nonnull
    public
    @ResponseBody
    Map<String, Object> viewCompanyIsExypp() throws Exception {
        SpringUserDetails currentUser = CurrentUserDetailsService.getCurrentUser();

        Map<String, Object> response = new HashMap<String, Object>();
        if (currentUser == null) {
            response.put("success", Boolean.FALSE);
        }
        final UserDTO user = currentUser.getUserDTO();
        final CompanyDTO company = userService.findCompany(user.getId());
        if (company == null) {
            response.put("success", Boolean.FALSE);
        }

        response.put("success", Boolean.TRUE);
        if (company.getIsExypp() == null) {
            response.put("isExypp", Boolean.FALSE);
        } else {
            response.put("isExypp", Boolean.TRUE);

        }
        return response;
    }

    @RequestMapping(value = {"/companyIsMilitary"}, method = RequestMethod.GET)
    @Nonnull
    public
    @ResponseBody
    Map<String, Object> viewCompanyIsMilitary() throws Exception {
        SpringUserDetails currentUser = CurrentUserDetailsService.getCurrentUser();

        Map<String, Object> response = new HashMap<String, Object>();
        if (currentUser == null) {
            response.put("success", Boolean.FALSE);
        }
        final UserDTO user = currentUser.getUserDTO();
        final CompanyDTO company = userService.findCompany(user.getId());
        if (company == null) {
            response.put("success", Boolean.FALSE);
        }

        response.put("success", Boolean.TRUE);
        if (company.getIsMilitary() == null) {
            response.put("isMilitary", Boolean.FALSE);
        } else {
            response.put("isMilitary", Boolean.TRUE);

        }
        return response;
    }

    @RequestMapping(value = {"/companyProfile/save"}, method = RequestMethod.POST)
    @Nonnull
    public final String updateCompanyProfile(
            @Nonnull
            @RequestParam(value = "currentPassword")
            final String currentPassword,

            @Nonnull
            @RequestParam(value = "newPassword")
            final String newPassword,

            @Nonnull
            @RequestParam(value = "reenterNewPassword")
            final String reenterNewPassword,

            @Nonnull
            @ModelAttribute("company")
            final CompanyDTO company,

            @Nonnull
            @ModelAttribute("user")
                    UserDTO user,

            @Nonnull
            final BindingResult result,

            @Nonnull
            final Model model

    ) throws Exception {
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            model.addAttribute("profileSaveError", "Το email δεν είναι σωστό. Προσπαθήστε ξανά!");
            return Constants.COMPANY_PROFILE_TEMPLATE;
        }
        if (currentPassword == null || currentPassword.isEmpty()) {
            model.addAttribute("profileSaveError", "Παρακαλώ εισάγετε το τρέχον password για να μπορέσετε να σώσετε τις αλλαγές.");

        } else if (newPassword != null && !newPassword.isEmpty() && reenterNewPassword != null && !reenterNewPassword.equals(newPassword)) {
            model.addAttribute("profileSaveError", "Το 'Νέο Password' και 'Επιβεβαίωση νέου Password' πεδία πρέπει να συμφωνούν.");
        } else {

            if (newPassword.isEmpty()) {
                Integer funcChangeEmailResult;
                Map<String, Object> changeEmailInput = new LinkedHashMap<String, Object>();
                changeEmailInput.put("p_username", user.getUsername());
                changeEmailInput.put("p_newemail", user.getEmail());

                // call function to change email of a specific user in Oracle Identity Management / LDAP
                funcChangeEmailResult = (userStoredProcedures).secOimCallFunction(changeEmailInput, "PCG_SEC_OIM.SEC_OIM_CHANGE_EMAIL");
                if (funcChangeEmailResult != 0) {
                    model.addAttribute("profileSaveError", "Προέκυψε κάποιο πρόβλημα με την αλλαγή του email.");
                } else {
                    userService.updateCompany(company);
                    userService.updateUser(user, null);
                    // Update the user in the active Spring Security session
                    final UserDTO updatedUser = userService.findUser(user.getId());
                    final String passwordHash = userService.getPasswordHash(user);
                    final CompanyDTO updatedCompany = userService.findCompany(company.getUserId());
                    List<TEmployerBranchIKA> branchIKAs = tEmployerBranchIKARepo.findByCompanyAme(company.getAme());
                    String branchIds = companyService.getCompanyBRanchIds(updatedCompany);
                    branchIds = branchIds.substring(0, branchIds.length()); //remove ending ,
                    final SpringUserDetails newUserDetails = new SpringUserDetails(updatedUser, updatedUser.getId(), passwordHash, updatedCompany, branchIds);
                    SpringUserDetails currentUser = CurrentUserDetailsService.getCurrentUser();
                    final UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(newUserDetails, passwordHash, currentUser.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(newAuth);
                }
            } else if (!newPassword.isEmpty() && newPassword.length() < 6) {
                model.addAttribute("profileSaveError", "Το νέο password πρέπει να αποτελείται από τουλάχιστον 6 χαρακτήρες.");
            } else if (!newPassword.isEmpty() && !newPassword.matches(".*[a-zA-Z]+.*")) {
                model.addAttribute("profileSaveError", "Παρακαλώ το password πρέπει να περιέχει τουλάχιστον 1 αλφαβητικό (latin) χαρακτήρα");
            } else if (!newPassword.isEmpty() && !newPassword.matches(".*\\d+.*")) {
                model.addAttribute("profileSaveError", "Παρακαλώ το password πρέπει να περιέχει τουλάχιστον 1 αριθμητικό χαρακτήρα.");
            } else if (!newPassword.isEmpty() && (newPassword.toLowerCase().contains(user.getUsername().toLowerCase()) || newPassword.toLowerCase().contains(user.getLastname().toLowerCase()))) {
                model.addAttribute("profileSaveError", "Παρακαλώ το password πρέπει να μην περιέχει το username, το επώνυμο ή το όνομα.");
            } else {

                Integer funcResetPassResult;
                Map<String, Object> resetPassInput = new LinkedHashMap<String, Object>();
                resetPassInput.put("p_username", user.getUsername());
                resetPassInput.put("p_new_pass", newPassword);

                // call function to Reset password of a specific user in Oracle Identity Management / LDAP
                funcResetPassResult = (userStoredProcedures).secOimCallFunction(resetPassInput, "PCG_SEC_OIM.SEC_OIM_RESET_PASS");
                if (funcResetPassResult != 0) {
                    model.addAttribute("profileSaveError", "Προέκυψε κάποιο πρόβλημα με την αλλαγή password.");
                } else {
                    Integer funcChangeEmailResult;
                    Map<String, Object> changeEmailInput = new LinkedHashMap<String, Object>();
                    changeEmailInput.put("p_username", user.getUsername());
                    changeEmailInput.put("p_newemail", user.getEmail());

                    // call function to change email of a specific user in Oracle Identity Management / LDAP
                    funcChangeEmailResult = (userStoredProcedures).secOimCallFunction(changeEmailInput, "PCG_SEC_OIM.SEC_OIM_CHANGE_EMAIL");
                    if (funcChangeEmailResult != 0) {
                        model.addAttribute("profileSaveError", "Προέκυψε κάποιο πρόβλημα με την αλλαγή του email.");
                    } else {
                        userService.updateCompany(company);
                        userService.updateUser(user, newPassword);

                        // Update the user in the active Spring Security session
                        final UserDTO updatedUser = userService.findUser(user.getId());
                        final String passwordHash = userService.getPasswordHash(user);
                        final CompanyDTO updatedCompany = userService.findCompany(company.getUserId());
                        List<TEmployerBranchIKA> branchIKAs = tEmployerBranchIKARepo.findByCompanyAme(company.getAme());
                        String branchIds = companyService.getCompanyBRanchIds(updatedCompany);
                        branchIds = branchIds.substring(0, branchIds.length()); //remove ending ,
                        final SpringUserDetails newUserDetails = new SpringUserDetails(updatedUser, updatedUser.getId(), passwordHash, updatedCompany, branchIds);
                        SpringUserDetails currentUser = CurrentUserDetailsService.getCurrentUser();
                        final UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(newUserDetails, passwordHash, currentUser.getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(newAuth);
                    }
                }
            }
        }

        return viewCompanyProfile(model);
    }

    @RequestMapping(value = {"/companyExtraInfo"}, method = RequestMethod.GET)
    @Nonnull
    public
    @ResponseBody
    Map<String, Object> viewCompanyExtraInfo() throws Exception {
        SpringUserDetails currentUser = CurrentUserDetailsService.getCurrentUser();

        Map<String, Object> response = new HashMap<String, Object>();
        if (currentUser == null) {
            response.put("success", Boolean.FALSE);
            return response;
        }
        final UserDTO user = currentUser.getUserDTO();
        TCompanyUserPrivilages priv = companyUserPrivilagesRepository.findByUser(user.getId());
        CompanyDTO company = null;
        if (user.getRole() == 3) {
            company = userService.findCompany(user.getId());
            if (company == null) {
                response.put("success", Boolean.FALSE);
                return response;
            }
        }
        if (user.getRole() == 7) {
            if (priv == null) {
                response.put("success", Boolean.FALSE);
                return response;
            }
            company = userService.findCompany(priv.getCompId());
            if (company == null) {
                response.put("success", Boolean.FALSE);
                return response;
            }
        }


        response.put("success", Boolean.TRUE);
        response.put("companyId", company.getId());
        response.put("categANum", company.getSumEmplA());
        response.put("categBNum", company.getSumEmplB());
        response.put("categCNum", company.getSumEmplC());
        response.put("totalEmpls", company.getSumEmplTotal());
        response.put("isTaNoneEmployerEmployee", company.getIsTaNoneEmplrEmple());
        response.put("taAfm", company.getTaAfm());
        response.put("taFullname", company.getTaFullname());
        response.put("isValidData1", company.getIsValidData1());
        response.put("isValidData2", company.getIsValidData2());
        response.put("isValidAllData", company.getIsValidAllData());
        response.put("taDegree", company.getTaDegree());
        response.put("role", user.getRole());
        return response;
    }

    @RequestMapping(value = {"/getPauseExplanation"}, method = RequestMethod.GET)
    @Nonnull
    public
    @ResponseBody
    Map<String, Object> getPauseExplanation(
            @Nonnull
            @RequestParam(value = "compTaAnnId")
            final Long compTaAnnId
    ) throws Exception {

        Long test = compTaAnnId;
        Map<String, Object> response = new HashMap<String, Object>();
        SpPtlCompTaAnn pauseExplanation = spPtlCompTaAnnRepo.findOne(compTaAnnId);
        response.put("success", Boolean.TRUE);
        response.put("pauseExplanation", pauseExplanation.getPauseExplanation());
        response.put("protNoPause", pauseExplanation.getProtNoPause());
        response.put("protNoResign", pauseExplanation.getProtNoResign());
        try
        {
            response.put("protDatePause", new SimpleDateFormat("dd-MM-yyyy").format(new java.sql.Date(pauseExplanation.getProtDatePause().getTime())));
        }
        catch (NullPointerException e)
        {
            response.put("protDatePause", null);
        }
        if (pauseExplanation.getProtDateResign() != null) response.put("protDateResign", new SimpleDateFormat("dd-MM-yyyy").format(new java.sql.Date(pauseExplanation.getProtDateResign().getTime())));
        else response.put("protDateResign", null);
        response.put("attachedDocIdPause", pauseExplanation.getAttachedDocIdPause());
        response.put("attachedDocId", pauseExplanation.getAttachedDocId());
        return response;
    }

    @RequestMapping(value = {"/companyExtraInfo/save"}, method = RequestMethod.POST)
    @Nonnull
    public
    @ResponseBody
    Map<String, Object> setCompanyExtraInfo(
            @RequestBody CompanyDTO info
    ) throws Exception {
        SpringUserDetails currentUser = CurrentUserDetailsService.getCurrentUser();

        Map<String, Object> response = new HashMap<String, Object>();
        if (currentUser == null) {
            response.put("success", Boolean.FALSE);
            response.put("error", "Δεν υπάρχει ο χρήστης.");
            return response;
        }
        final UserDTO user = currentUser.getUserDTO();
        final CompanyDTO company = userService.findCompany(user.getId());
        if (company == null) {
            response.put("success", Boolean.FALSE);
            response.put("error", "Δεν υπάρχει ο Οργανισμός για αυτό τον χρήστη.");
            return response;
        }

        if (info.getSumEmplA() + info.getSumEmplB() + info.getSumEmplC() != info.getSumEmplTotal()) {
            response.put("success", Boolean.FALSE);
            response.put("error", "Σφάλμα. Η ενημέρωση δεν μπορεί να πραγματοποιηθεί.");
            return response;
        }

        if (info.getSumEmplA() > 0 || (info.getSumEmplB() > 50 && info.getSumEmplC() > 50)) {
            if (info.getTaAfm() != null && info.getTaFullname() != null) {
                response.put("success", Boolean.FALSE);
                response.put("error", "Σφάλμα. Η ενημέρωση δεν μπορεί να πραγματοποιηθεί.");
                return response;
            }
        }

        info.setUserId(company.getUserId());
        try {
            userService.updateCompanyExtra(info);
            response.put("success", Boolean.TRUE);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", Boolean.FALSE);
            response.put("error", "Σφάλμα. Η ενημέρωση δεν μπορεί να πραγματοποιηθεί.");
            return response;
        }
    }


    @RequestMapping(value = {"/companyExtraInfo/calcTaHours"}, method = RequestMethod.POST)
    @Nonnull
    public
    @ResponseBody
    Map<String, Object> getCalcTaHours(
            Double sumDepA,
            Double sumDepB,
            Double sumDepC,
            Integer numOfDays,
            String dateFrom,
            String dateTo
    ) throws Exception {
        SpringUserDetails currentUser = CurrentUserDetailsService.getCurrentUser();

        Map<String, Object> input = new LinkedHashMap<String, Object>();
        Map<String, Object> response = new HashMap<String, Object>();
        if (currentUser == null) {
            response.put("success", Boolean.FALSE);
            response.put("error", "Δεν υπάρχει ο χρήστης.");
            return response;
        }
        /*final UserDTO user = currentUser.getUserDTO();
        final CompanyDTO company = userService.findCompany(user.getId());
        if (company == null) {
            response.put("success", Boolean.FALSE);
            response.put("error", "Δεν υπάρχει ο Οργανισμός για αυτό τον χρήστη.");
            return response;
        }*/
        final UserDTO user = currentUser.getUserDTO();
        TCompanyUserPrivilages priv = companyUserPrivilagesRepository.findByUser(user.getId());
        CompanyDTO company = null;
        if (user.getRole() == 3) {
            company = userService.findCompany(user.getId());
            if (company == null) {
                response.put("success", Boolean.FALSE);
                response.put("error", "Δεν υπάρχει ο Οργανισμός για αυτό τον χρήστη.");
                return response;
            }
        }
        if (user.getRole() == 7) {
            if (priv == null) {
                response.put("success", Boolean.FALSE);
                response.put("error", "Δεν υπάρχει ο Οργανισμός για αυτό τον χρήστη.");
                return response;
            }
            company = userService.findCompany(priv.getCompId());
            if (company == null) {
                response.put("success", Boolean.FALSE);
                response.put("error", "Δεν υπάρχει ο Οργανισμός για αυτό τον χρήστη.");
                return response;
            }
        }

        if (company.getSumEmplA() == null || company.getSumEmplB() == null || company.getSumEmplC() == null) {
            response.put("success", Boolean.FALSE);
            response.put("code", 0);
            response.put("error", "Για να χρησιμοποιήσετε την υπηρεσία πρέπει να αρχικοποιήσετε τους αριθμούς εργαζομένων επιλέγοντας από το μενού την αντίστοιχη υπηρεσία.");
            return response;
        }

        if (company.getSumEmplA() >= sumDepA && company.getSumEmplB() >= sumDepB && company.getSumEmplC() >= sumDepC) {
            input.put("SUMCOMPANY", company.getSumEmplTotal());
            input.put("SUMCOMPANY_A", company.getSumEmplA());
            input.put("SUMCOMPANY_B", company.getSumEmplB());
            input.put("SUMCOMPANY_C", company.getSumEmplC());
            input.put("SUMDEPARTMENT_A", sumDepA);
            input.put("SUMDEPARTMENT_B", sumDepB);
            input.put("SUMDEPARTMENT_C", sumDepC);
            input.put("NO_OF_DAYS", numOfDays);
            input.put("DATE_FROM", (new SimpleDateFormat("yyyy-M-d")).parse(dateFrom));
            input.put("DATE_TO", (new SimpleDateFormat("yyyy-M-d")).parse(dateTo));

            try {

                Integer computedHours = (companyService).funcIntCalculateTaHours(input);
                response.put("hours", computedHours);
                if (computedHours > -1) {
                    response.put("success", Boolean.TRUE);
                } else {
                    response.put("success", Boolean.FALSE);
                    response.put("code", 0);
                    response.put("error", "Σφάλμα. Ο υπολογισμός δεν μπορεί να πραγματοποιηθεί.");
                }
                return response;
            } catch (Exception e) {
                e.printStackTrace();
                response.put("success", Boolean.FALSE);
                response.put("code", 0);
                response.put("error", "Σφάλμα. Ο υπολογισμός δεν μπορεί να πραγματοποιηθεί.");
                return response;
            }
        } else {
            response.put("success", Boolean.FALSE);
            response.put("code", 1);
            response.put("error", "Συμπληρώσατε περισσότερους υπαλλήλους σε κατηγορία από ότι έχετε δηλώσει ως σύνολο ανά κατηγορία. Αν θέλετε να αλλάξετε αυτό τον αριθμό επιλέξτε από το μενού την κατάλληλη επιλογή.");
            return response;
        }


    }

    @RequestMapping(value = {"/companyExtraInfo/calcMinTaHours"}, method = RequestMethod.POST)
    @Nonnull
    public
    @ResponseBody
    Map<String, Object> getCalcMinTaHours(
            Double sumDepA,
            Double sumDepB,
            Double sumDepC
    ) throws Exception {
        SpringUserDetails currentUser = CurrentUserDetailsService.getCurrentUser();

        Map<String, Object> input = new LinkedHashMap<String, Object>();
        Map<String, Object> response = new HashMap<String, Object>();
        if (currentUser == null) {
            response.put("success", Boolean.FALSE);
            response.put("error", "Δεν υπάρχει ο χρήστης.");
            return response;
        }
        /*final UserDTO user = currentUser.getUserDTO();
        final CompanyDTO company = userService.findCompany(user.getId());
        if (company == null) {
            response.put("success", Boolean.FALSE);
            response.put("error", "Δεν υπάρχει ο Οργανισμός για αυτό τον χρήστη.");
            return response;
        }*/
        final UserDTO user = currentUser.getUserDTO();
        TCompanyUserPrivilages priv = companyUserPrivilagesRepository.findByUser(user.getId());
        CompanyDTO company = null;
        if (user.getRole() == 3) {
            company = userService.findCompany(user.getId());
            if (company == null) {
                response.put("success", Boolean.FALSE);
                response.put("error", "Δεν υπάρχει ο Οργανισμός για αυτό τον χρήστη.");
                return response;
            }
        }
        if (user.getRole() == 7) {
            if (priv == null) {
                response.put("success", Boolean.FALSE);
                response.put("error", "Δεν υπάρχει ο Οργανισμός για αυτό τον χρήστη.");
                return response;
            }
            company = userService.findCompany(priv.getCompId());
            if (company == null) {
                response.put("success", Boolean.FALSE);
                response.put("error", "Δεν υπάρχει ο Οργανισμός για αυτό τον χρήστη.");
                return response;
            }
        }


        /*if (company.getSumEmplA() == null || company.getSumEmplB() == null || company.getSumEmplC() == null) {
            response.put("success", Boolean.FALSE);
            response.put("code", 0);
            response.put("error", "Για να χρησιμοποιήσετε την υπηρεσία πρέπει να αρχικοποιήσετε τους αριθμούς εργαζομένων.");
            return response;
        }*/

        if (sumDepA != null && sumDepB != null && sumDepC != null) {
            input.put("SUMCOMPANY_A", sumDepA);
            input.put("SUMCOMPANY_B", sumDepB);
            input.put("SUMCOMPANY_C", sumDepC);

            try {

                Integer computedHours = (companyService).funcIntCalculateMinTaHours(input);
                response.put("hours", computedHours);
                if (computedHours > -1) {
                    response.put("success", Boolean.TRUE);
                } else {
                    response.put("success", Boolean.FALSE);
                    response.put("code", 0);
                    response.put("error", "Σφάλμα. Ο υπολογισμός δεν μπορεί να πραγματοποιηθεί.");
                }
                return response;
            } catch (Exception e) {
                e.printStackTrace();
                response.put("success", Boolean.FALSE);
                response.put("code", 0);
                response.put("error", "Σφάλμα. Ο υπολογισμός δεν μπορεί να πραγματοποιηθεί.");
                return response;
            }
        } else {
            response.put("success", Boolean.FALSE);
            response.put("code", 1);
            response.put("error", "Για να χρησιμοποιήσετε την υπηρεσία πρέπει να αρχικοποιήσετε τους αριθμούς εργαζομένων.");
            return response;
        }


    }


    @RequestMapping(value = {"/companyExtraInfo/calcIeHours"}, method = RequestMethod.POST)
    @Nonnull
    public
    @ResponseBody
    Map<String, Object> getCalcIeHours(
            Integer sumDepA,
            Integer sumDepB,
            Integer sumDepC,
            Integer numOfDays,
            String dateFrom,
            String dateTo
    ) throws Exception {
        SpringUserDetails currentUser = CurrentUserDetailsService.getCurrentUser();

        Map<String, Object> input = new LinkedHashMap<String, Object>();
        Map<String, Object> response = new HashMap<String, Object>();
        if (currentUser == null) {
            response.put("success", Boolean.FALSE);
            response.put("error", "Δεν υπάρχει ο χρήστης.");
            return response;
        }
        /*final UserDTO user = currentUser.getUserDTO();
        final CompanyDTO company = userService.findCompany(user.getId());
        if (company == null) {
            response.put("success", Boolean.FALSE);
            response.put("error", "Δεν υπάρχει ο Οργανισμός για αυτό τον χρήστη.");
            return response;
        }*/
        final UserDTO user = currentUser.getUserDTO();
        TCompanyUserPrivilages priv = companyUserPrivilagesRepository.findByUser(user.getId());
        CompanyDTO company = null;
        if (user.getRole() == 3) {
            company = userService.findCompany(user.getId());
            if (company == null) {
                response.put("success", Boolean.FALSE);
                response.put("error", "Δεν υπάρχει ο Οργανισμός για αυτό τον χρήστη.");
                return response;
            }
        }
        if (user.getRole() == 7) {
            if (priv == null) {
                response.put("success", Boolean.FALSE);
                response.put("error", "Δεν υπάρχει ο Οργανισμός για αυτό τον χρήστη.");
                return response;
            }
            company = userService.findCompany(priv.getCompId());
            if (company == null) {
                response.put("success", Boolean.FALSE);
                response.put("error", "Δεν υπάρχει ο Οργανισμός για αυτό τον χρήστη.");
                return response;
            }
        }

        if (company.getSumEmplA() == null || company.getSumEmplB() == null || company.getSumEmplC() == null) {
            response.put("success", Boolean.FALSE);
            response.put("code", 0);
            response.put("error", "Για να χρησιμοποιήσετε την υπηρεσία πρέπει να αρχικοποιήσετε τους αριθμούς εργαζομένων επιλέγοντας από το μενού την αντίστοιχη υπηρεσία.");
            return response;
        }

        if (company.getSumEmplA() >= sumDepA && company.getSumEmplB() >= sumDepB && company.getSumEmplC() >= sumDepC) {
            input.put("SUMCOMPANY", company.getSumEmplTotal());
            input.put("SUMCOMPANY_A", company.getSumEmplA());
            input.put("SUMCOMPANY_B", company.getSumEmplB());
            input.put("SUMCOMPANY_C", company.getSumEmplC());
            input.put("SUMDEPARTMENT_A", sumDepA);
            input.put("SUMDEPARTMENT_B", sumDepB);
            input.put("SUMDEPARTMENT_C", sumDepC);
            input.put("NO_OF_DAYS", numOfDays);
            input.put("DATE_FROM", (new SimpleDateFormat("yyyy-M-d")).parse(dateFrom));
            input.put("DATE_TO", (new SimpleDateFormat("yyyy-M-d")).parse(dateTo));

            try {

                Integer computedHours = (companyService).funcIntCalculateIeHours(input);
                response.put("hours", computedHours);
                if (computedHours > -1) {
                    response.put("success", Boolean.TRUE);
                } else {
                    response.put("success", Boolean.FALSE);
                    response.put("code", 0);
                    response.put("error", "Σφάλμα. Ο υπολογισμός δεν μπορεί να πραγματοποιηθεί.");
                }
                return response;
            } catch (Exception e) {
                e.printStackTrace();
                response.put("success", Boolean.FALSE);
                response.put("code", 0);
                response.put("error", "Σφάλμα. Ο υπολογισμός δεν μπορεί να πραγματοποιηθεί.");
                return response;
            }
        } else {
            response.put("success", Boolean.FALSE);
            response.put("code", 1);
            response.put("error", "Συμπληρώσατε περισσότερους υπαλλήλους σε κατηγορία από ότι έχετε δηλώσει ως σύνολο ανά κατηγορία. Αν θέλετε να αλλάξετε αυτό τον αριθμό επιλέξτε από το μενού την κατάλληλη επιλογή.");
            return response;
        }


    }

    @RequestMapping(value = {"/technicianExtraInfo/editDiploma"}, method = RequestMethod.POST)
    @Nonnull
    public
    @ResponseBody
    Map<String, Object> editDiploma(
            String diplomaDescr,
            String diplomaYear,
            Long id,
            Long attachedDocId,
            Long userId
    ) throws Exception {
        SpringUserDetails currentUser = CurrentUserDetailsService.getCurrentUser();

        Map<String, Object> response = new HashMap<String, Object>();
        if (currentUser == null) {
            response.put("success", Boolean.FALSE);
            response.put("error", "Δεν υπάρχει ο χρήστης.");
            return response;
        }
        final SpPtlTechnicianDiploma diploma = spPtlTechnicianDiplomaRepo.findOne(id);
        if (diploma == null) {
            response.put("success", Boolean.FALSE);
            response.put("error", "Δεν υπάρχει πτυχίο για αυτό το χρήστη.");
            return response;
        }


        Timestamp diplomaYearDate = null;
        if (diplomaYear != null) {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS'+0000'");
                dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                Date parsedDate = dateFormat.parse(diplomaYear);
                diplomaYearDate = new java.sql.Timestamp(parsedDate.getTime());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {
            diploma.setDiplomaDescr(diplomaDescr);
            diploma.setDiplomaYear(diplomaYearDate);
            spPtlTechnicianDiplomaRepo.save(diploma);

            response.put("success", Boolean.TRUE);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", Boolean.FALSE);
            response.put("error", "Σφάλμα. Η αλλαγή δεν μπορεί να πραγματοποιηθεί.");
            return response;
        }
    }

    @RequestMapping(value = {"/technicianExtraInfo/deleteDiploma"}, method = RequestMethod.POST)
    @Nonnull
    public
    @ResponseBody
    Map<String, Object> deleteDiploma(
            Long id
    ) throws Exception {
        SpringUserDetails currentUser = CurrentUserDetailsService.getCurrentUser();

        Map<String, Object> response = new HashMap<String, Object>();
        if (currentUser == null) {
            response.put("success", Boolean.FALSE);
            response.put("error", "Δεν υπάρχει ο χρήστης.");
            return response;
        }
        final SpPtlTechnicianDiploma diploma = spPtlTechnicianDiplomaRepo.findOne(id);
        if (diploma == null) {
            response.put("success", Boolean.FALSE);
            response.put("error", "Δεν υπάρχει πτυχίο για αυτό το χρήστη.");
            return response;
        }

        try {
            spPtlTechnicianDiplomaRepo.delete(id);
            response.put("success", Boolean.TRUE);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", Boolean.FALSE);
            response.put("error", "Σφάλμα. Η αλλαγή δεν μπορεί να πραγματοποιηθεί.");
            return response;
        }
    }

    @RequestMapping(value = {"/doctorExtraInfo/editDiploma"}, method = RequestMethod.POST)
    @Nonnull
    public
    @ResponseBody
    Map<String, Object> editDiploma(
            String speciality,
            Long id,
            Long attachedDocId,
            Long userId
    ) throws Exception {
        SpringUserDetails currentUser = CurrentUserDetailsService.getCurrentUser();

        Map<String, Object> response = new HashMap<String, Object>();
        if (currentUser == null) {
            response.put("success", Boolean.FALSE);
            response.put("error", "Δεν υπάρχει ο χρήστης.");
            return response;
        }
        final SpPtlDoctorDiploma diploma = spPtlDoctorDiplomaRepo.findOne(id);
        if (diploma == null) {
            response.put("success", Boolean.FALSE);
            response.put("error", "Δεν υπάρχει πτυχίο για αυτό το χρήστη.");
            return response;
        }

        try {
            diploma.setSpeciality(speciality);
            spPtlDoctorDiplomaRepo.save(diploma);

            response.put("success", Boolean.TRUE);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", Boolean.FALSE);
            response.put("error", "Σφάλμα. Η αλλαγή δεν μπορεί να πραγματοποιηθεί.");
            return response;
        }
    }

    @RequestMapping(value = {"/doctorExtraInfo/deleteDoctorDiploma"}, method = RequestMethod.POST)
    @Nonnull
    public
    @ResponseBody
    Map<String, Object> deleteDoctorDiploma(
            Long id
    ) throws Exception {
        SpringUserDetails currentUser = CurrentUserDetailsService.getCurrentUser();

        Map<String, Object> response = new HashMap<String, Object>();
        if (currentUser == null) {
            response.put("success", Boolean.FALSE);
            response.put("error", "Δεν υπάρχει ο χρήστης.");
            return response;
        }
        final SpPtlDoctorDiploma diploma = spPtlDoctorDiplomaRepo.findOne(id);
        if (diploma == null) {
            response.put("success", Boolean.FALSE);
            response.put("error", "Δεν υπάρχει πτυχίο για αυτό το χρήστη.");
            return response;
        }

        try {
            spPtlDoctorDiplomaRepo.delete(id);
            response.put("success", Boolean.TRUE);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", Boolean.FALSE);
            response.put("error", "Σφάλμα. Η αλλαγή δεν μπορεί να πραγματοποιηθεί.");
            return response;
        }
    }

    @RequestMapping(value = {"/doctorExtraInfo/deleteMedicalAssoc"}, method = RequestMethod.POST)
    @Nonnull
    public
    @ResponseBody
    Map<String, Object> deleteMedicalAssoc(
            Long id
    ) throws Exception {
        SpringUserDetails currentUser = CurrentUserDetailsService.getCurrentUser();

        Map<String, Object> response = new HashMap<String, Object>();
        if (currentUser == null) {
            response.put("success", Boolean.FALSE);
            response.put("error", "Δεν υπάρχει ο χρήστης.");
            return response;
        }
        final SpPtlDoctorCounty doctorCounty = spPtlDoctorCountyRepo.findOne(id);
        if (doctorCounty == null) {
            response.put("success", Boolean.FALSE);
            response.put("error", "Δεν υπάρχει βεβαίωση ιατρικού συλλόγου για αυτό το χρήστη.");
            return response;
        }

        try {
            spPtlDoctorCountyRepo.delete(id);
            response.put("success", Boolean.TRUE);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", Boolean.FALSE);
            response.put("error", "Σφάλμα. Η αλλαγή δεν μπορεί να πραγματοποιηθεί.");
            return response;
        }
    }


    @RequestMapping(value = {"/companyUserProfile"}, method = RequestMethod.GET)
    @Nonnull
    public final String viewCompanyUserProfile(
            @Nonnull final Model model
    ) throws Exception {
        SpringUserDetails springUserDetails = currentAuthenticatedUser();
        if (springUserDetails == null) {
            throw new Exception("Error");
        }
        final UserDTO user = springUserDetails.getUserDTO();
        model.addAttribute("user", user);

        return Constants.COMPANY_USER_PROFILE_TEMPLATE;
    }

    @RequestMapping(value = {"/companyUserProfile/save"}, method = RequestMethod.POST)
    @Nonnull
    public final String updateCompanyUserProfile(
            @Nonnull
            @RequestParam(value = "currentPassword")
            final String currentPassword,

            @Nonnull
            @RequestParam(value = "newPassword")
            final String newPassword,

            @Nonnull
            @RequestParam(value = "reenterNewPassword")
            final String reenterNewPassword,

            @Nonnull
            @ModelAttribute("user")
                    UserDTO user,

            @Nonnull
            final BindingResult result,

            @Nonnull
            final Model model
    ) throws Exception {
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            model.addAttribute("profileSaveError", "Το email δεν είναι σωστό. Προσπαθήστε ξανά!");
            return Constants.COMPANY_USER_PROFILE_TEMPLATE;
        }
        if (currentPassword == null || currentPassword.isEmpty()) {
            model.addAttribute("profileSaveError", "Παρακαλώ εισάγετε το τρέχον password για να μπορέσετε να σώσετε τις αλλαγές.");
            //} else if (!userService.verifyPassword(user, currentPassword)) {
            //    model.addAttribute("profileSaveError", "The password entered does not match the current password.");
        } else if (newPassword != null && !newPassword.isEmpty() && reenterNewPassword != null && !reenterNewPassword.equals(newPassword)) {
            model.addAttribute("profileSaveError", "Το 'Νέο Password' και 'Επιβεβαίωση νέου Password' πεδία πρέπει να συμφωνούν.");
        } else {

            if (newPassword.isEmpty()) {
                Integer funcChangeEmailResult;
                Map<String, Object> changeEmailInput = new LinkedHashMap<String, Object>();
                changeEmailInput.put("p_username", user.getUsername());
                changeEmailInput.put("p_newemail", user.getEmail());

                // call function to change email of a specific user in Oracle Identity Management / LDAP
                funcChangeEmailResult = (userStoredProcedures).secOimCallFunction(changeEmailInput, "PCG_SEC_OIM.SEC_OIM_CHANGE_EMAIL");
                if (funcChangeEmailResult != 0) {
                    model.addAttribute("profileSaveError", "Προέκυψε κάποιο πρόβλημα με την αλλαγή του email.");
                } else {
                    userService.updateUser(user, null);
                    // Update the user in the active Spring Security session
                    //final UserDTO updatedUser = userService.findUser(user.getId());
                    final String passwordHash = userService.getPasswordHash(user);
                    //final CompanyDTO updatedCompany = userService.findCompany(updatedUser.getId());
                    //String branchIds = companyService.getCompanyBRanchIds(updatedCompany);
                    //final SpringUserDetails newUserDetails = new SpringUserDetails(updatedUser, updatedUser.getId(), passwordHash, updatedCompany, branchIds);
                    SpringUserDetails currentUser = CurrentUserDetailsService.getCurrentUser();
                    final UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(currentUser, passwordHash, currentUser.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(newAuth);
                }
            } else if (!newPassword.isEmpty() && newPassword.length() < 6) {
                model.addAttribute("profileSaveError", "Το νέο password πρέπει να αποτελείται από τουλάχιστον 6 χαρακτήρες.");
            } else if (!newPassword.isEmpty() && !newPassword.matches(".*[a-zA-Z]+.*")) {
                model.addAttribute("profileSaveError", "Παρακαλώ το password πρέπει να περιέχει τουλάχιστον 1 αλφαβητικό (latin) χαρακτήρα");
            } else if (!newPassword.isEmpty() && !newPassword.matches(".*\\d+.*")) {
                model.addAttribute("profileSaveError", "Παρακαλώ το password πρέπει να περιέχει τουλάχιστον 1 αριθμητικό χαρακτήρα.");
            } else if (!newPassword.isEmpty() && (newPassword.toLowerCase().contains(user.getUsername().toLowerCase()) || newPassword.toLowerCase().contains(user.getLastname().toLowerCase()))) {
                model.addAttribute("profileSaveError", "Παρακαλώ το password πρέπει να μην περιέχει το username, το επώνυμο ή το όνομα.");
            } else {

                Integer funcResetPassResult;
                Map<String, Object> resetPassInput = new LinkedHashMap<String, Object>();
                resetPassInput.put("p_username", user.getUsername());
                resetPassInput.put("p_new_pass", newPassword);

                // call function to Reset password of a specific user in Oracle Identity Management / LDAP
                funcResetPassResult = (userStoredProcedures).secOimCallFunction(resetPassInput, "PCG_SEC_OIM.SEC_OIM_RESET_PASS");
                if (funcResetPassResult != 0) {
                    model.addAttribute("profileSaveError", "Προέκυψε κάποιο πρόβλημα με την αλλαγή password.");
                } else {
                    Integer funcChangeEmailResult;
                    Map<String, Object> changeEmailInput = new LinkedHashMap<String, Object>();
                    changeEmailInput.put("p_username", user.getUsername());
                    changeEmailInput.put("p_newemail", user.getEmail());

                    // call function to change email of a specific user in Oracle Identity Management / LDAP
                    funcChangeEmailResult = (userStoredProcedures).secOimCallFunction(changeEmailInput, "PCG_SEC_OIM.SEC_OIM_CHANGE_EMAIL");
                    if (funcChangeEmailResult != 0) {
                        model.addAttribute("profileSaveError", "Προέκυψε κάποιο πρόβλημα με την αλλαγή του email.");
                    } else {
                        userService.updateUser(user, newPassword);
                        // Update the user in the active Spring Security session
                        //final UserDTO updatedUser = userService.findUser(user.getId());
                        final String passwordHash = userService.getPasswordHash(user);
                        //final CompanyDTO updatedCompany = userService.findCompany(updatedUser.getId());
                        //String branchIds = companyService.getCompanyBRanchIds(updatedCompany);
                        //final SpringUserDetails newUserDetails = new SpringUserDetails(updatedUser, updatedUser.getId(), passwordHash, updatedCompany, branchIds);
                        SpringUserDetails currentUser = CurrentUserDetailsService.getCurrentUser();
                        final UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(currentUser, passwordHash, currentUser.getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(newAuth);
                    }
                }
            }
        }
        return viewCompanyUserProfile(model);
    }

    @RequestMapping(value = {"/doctorProfile"}, method = RequestMethod.GET)
    @Nonnull
    public final String viewDoctorProfile(
            @Nonnull final Model model
    ) throws Exception {
        SpringUserDetails springUserDetails = currentAuthenticatedUser();
        if (springUserDetails == null) {
            throw new Exception("Error");
        }
        final UserDTO user = springUserDetails.getUserDTO();
        final DoctorDTO doctor = userService.findDoctor(user.getId());
        if (doctor == null) {
            throw new Exception("No Doctor Object for this User");
        }
        model.addAttribute("doctor", doctor);
        model.addAttribute("user", user);
        return Constants.WORKPLACE_DOCTOR_PROFILE_TEMPLATE;
    }

    @RequestMapping(value = {"/doctorProfile/save"}, method = RequestMethod.POST)
    @Nonnull
    public final String updateDoctorProfile(
            @Nonnull
            @RequestParam(value = "currentPassword")
            final String currentPassword,

            @Nonnull
            @RequestParam(value = "newPassword")
            final String newPassword,

            @Nonnull
            @RequestParam(value = "reenterNewPassword")
            final String reenterNewPassword,

            @Nonnull
            @ModelAttribute("doctor")
            final DoctorDTO doctor,

            @Nonnull
            @ModelAttribute("user")
                    UserDTO user,

            @Nonnull
            final BindingResult result,

            @Nonnull
            final Model model

    ) throws Exception {
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            model.addAttribute("profileSaveError", "Το email δεν είναι σωστό. Προσπαθήστε ξανά!");
            return Constants.WORKPLACE_DOCTOR_PROFILE_TEMPLATE;
        }
        if (currentPassword == null || currentPassword.isEmpty()) {
            model.addAttribute("profileSaveError", "Παρακαλώ εισάγετε το τρέχον password για να μπορέσετε να σώσετε τις αλλαγές.");

        } else if (newPassword != null && !newPassword.isEmpty() && reenterNewPassword != null && !reenterNewPassword.equals(newPassword)) {
            model.addAttribute("profileSaveError", "Το 'Νέο Password' και 'Επιβεβαίωση νέου Password' πεδία πρέπει να συμφωνούν.");
        } else {

            if (newPassword.isEmpty()) {
                Integer funcChangeEmailResult;
                Map<String, Object> changeEmailInput = new LinkedHashMap<String, Object>();
                changeEmailInput.put("p_username", user.getUsername());
                changeEmailInput.put("p_newemail", user.getEmail());

                // call function to change email of a specific user in Oracle Identity Management / LDAP
                funcChangeEmailResult = (userStoredProcedures).secOimCallFunction(changeEmailInput, "PCG_SEC_OIM.SEC_OIM_CHANGE_EMAIL");
                if (funcChangeEmailResult != 0) {
                    model.addAttribute("profileSaveError", "Προέκυψε κάποιο πρόβλημα με την αλλαγή του email.");
                } else {
                    userService.updateWorkplaceDoctor(doctor);
                    userService.updateUser(user, null);

                    // Update the user in the active Spring Security session
                    final UserDTO updatedUser = userService.findUser(user.getId());
                    final String passwordHash = userService.getPasswordHash(user);
                    //final DoctorDTO updatedDoctor = userService.findDoctor(doctor.getUserId());
                    final SpringUserDetails newUserDetails = new SpringUserDetails(updatedUser, updatedUser.getId(), passwordHash, null, null);
                    SpringUserDetails currentUser = CurrentUserDetailsService.getCurrentUser();
                    final UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(newUserDetails, passwordHash, currentUser.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(newAuth);
                }
            } else if (!newPassword.isEmpty() && newPassword.length() < 6) {
                model.addAttribute("profileSaveError", "Το νέο password πρέπει να αποτελείται από τουλάχιστον 6 χαρακτήρες.");
            } else if (!newPassword.isEmpty() && !newPassword.matches(".*[a-zA-Z]+.*")) {
                model.addAttribute("profileSaveError", "Παρακαλώ το password πρέπει να περιέχει τουλάχιστον 1 αλφαβητικό (latin) χαρακτήρα");
            } else if (!newPassword.isEmpty() && !newPassword.matches(".*\\d+.*")) {
                model.addAttribute("profileSaveError", "Παρακαλώ το password πρέπει να περιέχει τουλάχιστον 1 αριθμητικό χαρακτήρα.");
            } else if (!newPassword.isEmpty() && (newPassword.toLowerCase().contains(user.getUsername().toLowerCase()) || newPassword.toLowerCase().contains(user.getLastname().toLowerCase()))) {
                model.addAttribute("profileSaveError", "Παρακαλώ το password πρέπει να μην περιέχει το username, το επώνυμο ή το όνομα.");
            } else {

                Integer funcResetPassResult;
                Map<String, Object> resetPassInput = new LinkedHashMap<String, Object>();
                resetPassInput.put("p_username", user.getUsername());
                resetPassInput.put("p_new_pass", newPassword);

                // call function to Reset password of a specific user in Oracle Identity Management / LDAP
                funcResetPassResult = (userStoredProcedures).secOimCallFunction(resetPassInput, "PCG_SEC_OIM.SEC_OIM_RESET_PASS");
                if (funcResetPassResult != 0) {
                    model.addAttribute("profileSaveError", "Προέκυψε κάποιο πρόβλημα με την αλλαγή password.");
                } else {
                    Integer funcChangeEmailResult;
                    Map<String, Object> changeEmailInput = new LinkedHashMap<String, Object>();
                    changeEmailInput.put("p_username", user.getUsername());
                    changeEmailInput.put("p_newemail", user.getEmail());

                    // call function to change email of a specific user in Oracle Identity Management / LDAP
                    funcChangeEmailResult = (userStoredProcedures).secOimCallFunction(changeEmailInput, "PCG_SEC_OIM.SEC_OIM_CHANGE_EMAIL");
                    if (funcChangeEmailResult != 0) {
                        model.addAttribute("profileSaveError", "Προέκυψε κάποιο πρόβλημα με την αλλαγή του email.");
                    } else {
                        userService.updateWorkplaceDoctor(doctor);
                        userService.updateUser(user, newPassword);

                        // Update the user in the active Spring Security session
                        final UserDTO updatedUser = userService.findUser(user.getId());
                        final String passwordHash = userService.getPasswordHash(user);
                        //final DoctorDTO updatedDoctor = userService.findDoctor(doctor.getUserId());
                        final SpringUserDetails newUserDetails = new SpringUserDetails(updatedUser, updatedUser.getId(), passwordHash, null, null);
                        SpringUserDetails currentUser = CurrentUserDetailsService.getCurrentUser();
                        final UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(newUserDetails, passwordHash, currentUser.getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(newAuth);
                    }
                }
            }
        }

        return viewDoctorProfile(model);
    }

    @RequestMapping(value = {"/technicianProfile"}, method = RequestMethod.GET)
    @Nonnull
    public final String viewTechnicianProfile(
            @Nonnull final Model model
    ) throws Exception {
        SpringUserDetails springUserDetails = currentAuthenticatedUser();
        if (springUserDetails == null) {
            throw new Exception("Error");
        }
        final UserDTO user = springUserDetails.getUserDTO();
        final TechnicianDTO technician = userService.findTechnician(user.getId());
        if (technician == null) {
            throw new Exception("No Technician Object for this User");
        }
        model.addAttribute("technician", technician);
        model.addAttribute("user", user);
        return Constants.SAFETY_TECHNICIAN_PROFILE_TEMPLATE;
    }

    @RequestMapping(value = {"/technicianProfile/save"}, method = RequestMethod.POST)
    @Nonnull
    public final String updateTechnicianProfile(
            @Nonnull
            @RequestParam(value = "currentPassword")
            final String currentPassword,

            @Nonnull
            @RequestParam(value = "newPassword")
            final String newPassword,

            @Nonnull
            @RequestParam(value = "reenterNewPassword")
            final String reenterNewPassword,

            @Nonnull
            @ModelAttribute("technician")
            final TechnicianDTO technician,

            @Nonnull
            @ModelAttribute("user")
                    UserDTO user,

            @Nonnull
            final BindingResult result,

            @Nonnull
            final Model model

    ) throws Exception {
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            model.addAttribute("profileSaveError", "Το email δεν είναι σωστό. Προσπαθήστε ξανά!");
            return Constants.SAFETY_TECHNICIAN_PROFILE_TEMPLATE;
        }
        if (currentPassword == null || currentPassword.isEmpty()) {
            model.addAttribute("profileSaveError", "Παρακαλώ εισάγετε το τρέχον password για να μπορέσετε να σώσετε τις αλλαγές.");

        } else if (newPassword != null && !newPassword.isEmpty() && reenterNewPassword != null && !reenterNewPassword.equals(newPassword)) {
            model.addAttribute("profileSaveError", "Το 'Νέο Password' και 'Επιβεβαίωση νέου Password' πεδία πρέπει να συμφωνούν.");
        } else {

            if (newPassword.isEmpty()) {
                Integer funcChangeEmailResult;
                Map<String, Object> changeEmailInput = new LinkedHashMap<String, Object>();
                changeEmailInput.put("p_username", user.getUsername());
                changeEmailInput.put("p_newemail", user.getEmail());

                // call function to change email of a specific user in Oracle Identity Management / LDAP
                funcChangeEmailResult = (userStoredProcedures).secOimCallFunction(changeEmailInput, "PCG_SEC_OIM.SEC_OIM_CHANGE_EMAIL");
                if (funcChangeEmailResult != 0) {
                    model.addAttribute("profileSaveError", "Προέκυψε κάποιο πρόβλημα με την αλλαγή του email.");
                } else {
                    userService.updateSafetyTechnician(technician);
                    userService.updateUser(user, null);

                    // Update the user in the active Spring Security session
                    final UserDTO updatedUser = userService.findUser(user.getId());
                    final String passwordHash = userService.getPasswordHash(user);
                    //final TechnicianDTO updatedTechnician = userService.findTechnician(technician.getUserId());
                    final SpringUserDetails newUserDetails = new SpringUserDetails(updatedUser, updatedUser.getId(), passwordHash, null, null);
                    SpringUserDetails currentUser = CurrentUserDetailsService.getCurrentUser();
                    final UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(newUserDetails, passwordHash, currentUser.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(newAuth);
                }
            } else if (!newPassword.isEmpty() && newPassword.length() < 6) {
                model.addAttribute("profileSaveError", "Το νέο password πρέπει να αποτελείται από τουλάχιστον 6 χαρακτήρες.");
            } else if (!newPassword.isEmpty() && !newPassword.matches(".*[a-zA-Z]+.*")) {
                model.addAttribute("profileSaveError", "Παρακαλώ το password πρέπει να περιέχει τουλάχιστον 1 αλφαβητικό (latin) χαρακτήρα");
            } else if (!newPassword.isEmpty() && !newPassword.matches(".*\\d+.*")) {
                model.addAttribute("profileSaveError", "Παρακαλώ το password πρέπει να περιέχει τουλάχιστον 1 αριθμητικό χαρακτήρα.");
            } else if (!newPassword.isEmpty() && (newPassword.toLowerCase().contains(user.getUsername().toLowerCase()) || newPassword.toLowerCase().contains(user.getLastname().toLowerCase()))) {
                model.addAttribute("profileSaveError", "Παρακαλώ το password πρέπει να μην περιέχει το username, το επώνυμο ή το όνομα.");
            } else {

                Integer funcResetPassResult;
                Map<String, Object> resetPassInput = new LinkedHashMap<String, Object>();
                resetPassInput.put("p_username", user.getUsername());
                resetPassInput.put("p_new_pass", newPassword);

                // call function to Reset password of a specific user in Oracle Identity Management / LDAP
                funcResetPassResult = (userStoredProcedures).secOimCallFunction(resetPassInput, "PCG_SEC_OIM.SEC_OIM_RESET_PASS");
                if (funcResetPassResult != 0) {
                    model.addAttribute("profileSaveError", "Προέκυψε κάποιο πρόβλημα με την αλλαγή password.");
                } else {
                    Integer funcChangeEmailResult;
                    Map<String, Object> changeEmailInput = new LinkedHashMap<String, Object>();
                    changeEmailInput.put("p_username", user.getUsername());
                    changeEmailInput.put("p_newemail", user.getEmail());

                    // call function to change email of a specific user in Oracle Identity Management / LDAP
                    funcChangeEmailResult = (userStoredProcedures).secOimCallFunction(changeEmailInput, "PCG_SEC_OIM.SEC_OIM_CHANGE_EMAIL");
                    if (funcChangeEmailResult != 0) {
                        model.addAttribute("profileSaveError", "Προέκυψε κάποιο πρόβλημα με την αλλαγή του email.");
                    } else {
                        userService.updateSafetyTechnician(technician);
                        userService.updateUser(user, newPassword);

                        // Update the user in the active Spring Security session
                        final UserDTO updatedUser = userService.findUser(user.getId());
                        final String passwordHash = userService.getPasswordHash(user);
                        //final TechnicianDTO updatedTechnician = userService.findTechnician(technician.getUserId());
                        final SpringUserDetails newUserDetails = new SpringUserDetails(updatedUser, updatedUser.getId(), passwordHash, null, null);
                        SpringUserDetails currentUser = CurrentUserDetailsService.getCurrentUser();
                        final UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(newUserDetails, passwordHash, currentUser.getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(newAuth);
                    }
                }
            }
        }

        return viewTechnicianProfile(model);
    }


    @RequestMapping(value = {"/adminProfile"}, method = RequestMethod.GET)
    @Nonnull
    public final String viewAdminProfile(
            @Nonnull final Model model
    ) throws Exception {
        SpringUserDetails springUserDetails = currentAuthenticatedUser();
        if (springUserDetails == null) {
            throw new Exception("Error");
        }
        final UserDTO user = springUserDetails.getUserDTO();
        model.addAttribute("user", user);
        return Constants.ADMIN_PROFILE_TEMPLATE;
    }

    @RequestMapping(value = {"/adminProfile/save"}, method = RequestMethod.POST)
    @Nonnull
    public final String updateAdminProfile(
            @Nonnull
            @RequestParam(value = "currentPassword")
            final String currentPassword,

            @Nonnull
            @RequestParam(value = "newPassword")
            final String newPassword,

            @Nonnull
            @RequestParam(value = "reenterNewPassword")
            final String reenterNewPassword,

            @Nonnull
            @ModelAttribute("user")
                    UserDTO admin,

            @Nonnull
            final BindingResult result,

            @Nonnull
            final Model model

    ) throws Exception {
        userValidator.validate(admin, result);
        if (result.hasErrors()) {
            model.addAttribute("profileSaveError", "Το email δεν είναι σωστό. Προσπαθήστε ξανά!");
            return Constants.ADMIN_PROFILE_TEMPLATE;
        }
        if (currentPassword == null || currentPassword.isEmpty()) {
            model.addAttribute("profileSaveError", "Παρακαλώ εισάγετε το τρέχον password για να μπορέσετε να σώσετε τις αλλαγές.");

        } else if (newPassword != null && !newPassword.isEmpty() && reenterNewPassword != null && !reenterNewPassword.equals(newPassword)) {
            model.addAttribute("profileSaveError", "Το 'Νέο Password' και 'Επιβεβαίωση νέου Password' πεδία πρέπει να συμφωνούν.");
        } else {

            if (newPassword.isEmpty()) {
                Integer funcChangeEmailResult;
                Map<String, Object> changeEmailInput = new LinkedHashMap<String, Object>();
                changeEmailInput.put("p_username", admin.getUsername());
                changeEmailInput.put("p_newemail", admin.getEmail());

                // call function to change email of a specific user in Oracle Identity Management / LDAP
                funcChangeEmailResult = (userStoredProcedures).secOimCallFunction(changeEmailInput, "PCG_SEC_OIM.SEC_OIM_CHANGE_EMAIL");
                if (funcChangeEmailResult != 0) {
                    model.addAttribute("profileSaveError", "Προέκυψε κάποιο πρόβλημα με την αλλαγή του email.");
                } else {
                    userService.updateUser(admin, null);
                    // Update the user in the active Spring Security session
                    final UserDTO updatedUser = userService.findUser(admin.getId());
                    final String passwordHash = userService.getPasswordHash(admin);
                    final SpringUserDetails newUserDetails = new SpringUserDetails(updatedUser, updatedUser.getId(), passwordHash, null, null);
                    SpringUserDetails currentUser = CurrentUserDetailsService.getCurrentUser();
                    final UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(newUserDetails, passwordHash, currentUser.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(newAuth);
                }
            } else if (!newPassword.isEmpty() && newPassword.length() < 6) {
                model.addAttribute("profileSaveError", "Το νέο password πρέπει να αποτελείται από τουλάχιστον 6 χαρακτήρες.");
            } else if (!newPassword.isEmpty() && !newPassword.matches(".*[a-zA-Z]+.*")) {
                model.addAttribute("profileSaveError", "Παρακαλώ το password πρέπει να περιέχει τουλάχιστον 1 αλφαβητικό (latin) χαρακτήρα");
            } else if (!newPassword.isEmpty() && !newPassword.matches(".*\\d+.*")) {
                model.addAttribute("profileSaveError", "Παρακαλώ το password πρέπει να περιέχει τουλάχιστον 1 αριθμητικό χαρακτήρα.");
            } else if (!newPassword.isEmpty() && (newPassword.toLowerCase().contains(admin.getUsername().toLowerCase()) || newPassword.toLowerCase().contains(admin.getLastname().toLowerCase()))) {
                model.addAttribute("profileSaveError", "Παρακαλώ το password πρέπει να μην περιέχει το username, το επώνυμο ή το όνομα.");
            } else {

                Integer funcResetPassResult;
                Map<String, Object> resetPassInput = new LinkedHashMap<String, Object>();
                resetPassInput.put("p_username", admin.getUsername());
                resetPassInput.put("p_new_pass", newPassword);

                // call function to Reset password of a specific user in Oracle Identity Management / LDAP
                funcResetPassResult = (userStoredProcedures).secOimCallFunction(resetPassInput, "PCG_SEC_OIM.SEC_OIM_RESET_PASS");
                if (funcResetPassResult != 0) {
                    model.addAttribute("profileSaveError", "Προέκυψε κάποιο πρόβλημα με την αλλαγή password.");
                } else {
                    Integer funcChangeEmailResult;
                    Map<String, Object> changeEmailInput = new LinkedHashMap<String, Object>();
                    changeEmailInput.put("p_username", admin.getUsername());
                    changeEmailInput.put("p_newemail", admin.getEmail());

                    // call function to change email of a specific user in Oracle Identity Management / LDAP
                    funcChangeEmailResult = (userStoredProcedures).secOimCallFunction(changeEmailInput, "PCG_SEC_OIM.SEC_OIM_CHANGE_EMAIL");
                    if (funcChangeEmailResult != 0) {
                        model.addAttribute("profileSaveError", "Προέκυψε κάποιο πρόβλημα με την αλλαγή του email.");
                    } else {
                        userService.updateUser(admin, newPassword);
                        // Update the user in the active Spring Security session
                        final UserDTO updatedUser = userService.findUser(admin.getId());
                        final String passwordHash = userService.getPasswordHash(admin);
                        final SpringUserDetails newUserDetails = new SpringUserDetails(updatedUser, updatedUser.getId(), passwordHash, null, null);
                        SpringUserDetails currentUser = CurrentUserDetailsService.getCurrentUser();
                        final UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(newUserDetails, passwordHash, currentUser.getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(newAuth);
                    }
                }
            }

            if (newPassword.isEmpty()) {
                userService.updateUser(admin, null);
            } else {
                userService.updateUser(admin, newPassword);
            }
        }

        return viewAdminProfile(model);
    }

    @RequestMapping(value = {"/register"}, method = RequestMethod.GET)
    @Nonnull
    public final String register(
            @Nonnull final Model model

    ) throws Exception {
        final UserDTO user = new UserDTO();
        user.setUsername("");
        user.setEmail("");
        user.setFirstname("");
        user.setLastname("");

        SpringUserDetails currentUser = CurrentUserDetailsService.getCurrentUser();
        UserDTO adminDTO = currentUser.getUserDTO();
        model.addAttribute("user", adminDTO);
        model.addAttribute("newuser", user);
        return "register";
    }

    @RequestMapping(value = {"/registerTa/oauth/{speciality}"}, method = RequestMethod.GET)
    @Nonnull
    public final String registerTaoauth(HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest, @PathVariable("speciality") String speciality

    ) throws Exception {
        httpServletRequest.getSession().invalidate();
        ConsumerCredentials consumerCredentials = new ConsumerCredentials(Constants.OAUTH_CONSUMER_KEY, Constants.OAUTH_CONSUMER_SECRET);

        OAuth1AuthorizationFlow authFlow = OAuth1ClientSupport.builder(consumerCredentials).signatureMethod(PlaintextMethod.NAME)
                .authorizationFlow(
                        Constants.OAUTH_REQUEST_TOKEN_URI,
                        Constants.OAUTH_ACCESS_TOKEN_URI,
                        Constants.OAUTH_CONFIRM_ACCESS_URI
                ).callbackUri(Constants.OAUTH_CALLBACK_URI_TECHNICIAN+"/"+speciality)
                .build();

        String authorizationUri = authFlow.start();
        httpServletRequest.getSession().removeAttribute("oauth");
        httpServletRequest.getSession().setAttribute("oauth", authFlow);
        System.out.println("SESSIONID: " + httpServletRequest.getSession().getId());

        return "redirect:" + authorizationUri;
    }

    @RequestMapping(value = {"/registerTa/oauth/confirmed/{speciality}"}, method = RequestMethod.GET)
    @Nonnull
    public final String registerTa(HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest,
                                   @Nonnull
                                   @RequestParam(value = "oauth_token")
                                   final String oauth_token,

                                   @Nonnull
                                   @ModelAttribute("oauth_verifier")
                                   final String oauth_verifier,

                                  /*@RequestParam("afm2")  // @ModelAttribute("afm2")
                                  final String afm2,*/

                                   @Nonnull final Model model,

                                   @PathVariable("speciality") String speciality

    ) throws Exception {

        System.out.println("SESSIONID2: " + httpServletRequest.getSession().getId());
        OAuth1AuthorizationFlow authFlow = (OAuth1AuthorizationFlow) httpServletRequest.getSession().getAttribute("oauth");
        if (authFlow == null) {
            System.out.println("SESSIONID2 FAILED: " + httpServletRequest.getSession().getId());
        }

        httpServletResponse.setCharacterEncoding("UTF-8");
        //httpServletRequest.getSession(false);

        String logoutToken = httpServletRequest.getParameter("oauth_token");
        AccessToken accessToken = authFlow.finish(oauth_verifier); // TODO remove comment

        Client client = ClientBuilder.newClient(); //for logout

        //OAuthReturnedMessageDemo response; //comment out for production
        //OAuthReturnedMessage response = null; // comment out for test must be defined and change response class for PRODUCTION
        Response response;
        String responseText;


        //MARIOS TODO before each return must log out and destroy session with OAuth
        //if finish ok continue else logout+redirect to register2 with error
        if (authFlow.getAuthorizedClient().target(Constants.OAUTH_RESOURCE_ONE_URI).request().get().getStatusInfo().getStatusCode() < 400) {
            System.out.println("Before get response!");
            //comment out for production
            //response = authFlow.getAuthorizedClient().target(Constants.OAUTH_RESOURCE_ONE_URI).request().get(OAuthReturnedMessageDemo.class);
            //comment out for test
            response = authFlow.getAuthorizedClient().target(Constants.OAUTH_RESOURCE_ONE_URI).request().get();
            responseText = response.readEntity(String.class);
            System.out.println(responseText);
        } else {
            client.target(Constants.OAUTH_LOGOUT_URI + logoutToken).request().get();
            httpServletRequest.getSession().invalidate();
            model.addAttribute("profileLoadError", "Δεν επιστρέφεται το ΑΦΜ χρήστη από την ΓΓΠΣ.");
            return "registerError";
        }

        // Needed for PROD
        String comments = "";
        String tin = "";
        try {
            JSONObject jObject = new JSONObject(responseText); // json
            JSONObject webUserDetails = jObject.getJSONObject("WebUserDetails"); // get data object
            comments = webUserDetails.getString("comments"); // get the name from data.
            tin = webUserDetails.getString("tin"); // get the name from data.
        } catch (Exception ex) {
            client.target(Constants.OAUTH_LOGOUT_URI + logoutToken).request().get();
            httpServletRequest.getSession().invalidate();
            model.addAttribute("profileLoadError", "Δεν επιστρέφεται το ΑΦΜ χρήστη από την ΓΓΠΣ.");
            return "registerError";
        }

        String afm = "";

        /*if (response.getMessageCode().equals(Constants.OAUTH_RESOURCE_ONE_CODE))
            afm = response.getMessageText();
        else {
            client.target(Constants.OAUTH_LOGOUT_URI + logoutToken).request().get();
            httpServletRequest.getSession().invalidate();
            model.addAttribute("profileLoadError", "Δεν επιστρέφεται ορθό ΑΦΜ χρήστη από την ΓΓΠΣ.");
            return "registerError";
        }*/

        // Needed for PROD
        if (tin != null && tin.length() >= 9 && comments.equals(Constants.OAUTH_RESOURCE_ONE_CODE))
            afm = tin;
        else {
            client.target(Constants.OAUTH_LOGOUT_URI + logoutToken).request().get();
            httpServletRequest.getSession().invalidate();
            model.addAttribute("profileLoadError", "Δεν επιστρέφεται ορθό ΑΦΜ χρήστη από την ΓΓΠΣ.");
            return "registerError";
        }

        //afm="019771771"; //MARIOS TODO remove
        //afm = afm2;

        String browserDetails = httpServletRequest.getHeader("User-Agent");
        String userAgent = browserDetails;

        String os = "";
        if (userAgent.toLowerCase().indexOf("windows") >= 0) {
            os = "Windows";
        } else if (userAgent.toLowerCase().indexOf("mac") >= 0) {
            os = "Mac";
        } else if (userAgent.toLowerCase().indexOf("x11") >= 0) {
            os = "Unix";
        } else if (userAgent.toLowerCase().indexOf("android") >= 0) {
            os = "Android";
        } else if (userAgent.toLowerCase().indexOf("iphone") >= 0) {
            os = "IPhone";
        } else {
            os = "UnKnown";
        }

        InetAddress addr = InetAddress.getLocalHost();
        String ipAddress = addr.getHostAddress();
        String hostname = addr.getHostName();

        Map<String, Object> input = new LinkedHashMap<String, Object>();
        input.put("afm", afm);
        input.put("auditEndUserApplUser", afm);
        input.put("auditEndUserOsUser", os); //  httpServletRequest.getRemoteHost()
        input.put("auditUnitCode", "clientPortal");
        input.put("auditUnitDescr", "clientPortal");
        input.put("auditEndUserHostIp", httpServletRequest.getRemoteAddr());
        input.put("auditEndUserHostName", httpServletRequest.getRemoteHost());
        input.put("logApplicationUserId", 1);

        Calendar calendar = Calendar.getInstance();
        java.sql.Date sqldate = new java.sql.Date(calendar.getTime().getTime());

        input.put("logAccessDate", sqldate);
        input.put("logEmployeeId", 1);
        input.put("logIPs", ipAddress);
        input.put("logUsernames", hostname);

        //call stored proc if fail logout+redirect to register2 with error
        Map<String, Object> bcallGGDEWS;
        try {
            bcallGGDEWS = (userStoredProcedures).procRegProcCallGGDEWS(input);
        } catch (Exception e) {
            e.printStackTrace();
            client.target(Constants.OAUTH_LOGOUT_URI + logoutToken).request().get();
            httpServletRequest.getSession().invalidate();
            model.addAttribute("profileLoadError", "Δεν υπάρχουν τα στοιχεία σας στο μητρώο.");
            return "registerError";
        }

        //retrieve callId and at it to session to get user data later in save
        httpServletRequest.getSession().removeAttribute("callId");
        httpServletRequest.getSession().removeAttribute("afmGGDE");
        httpServletRequest.getSession().setAttribute("callId", (Integer) bcallGGDEWS.get("out_CallId"));
        httpServletRequest.getSession().setAttribute("afmGGDE", afm);
        //logout
        //add to model the user
        client.target(Constants.OAUTH_LOGOUT_URI + logoutToken).request().get();

        return registerTa(httpServletRequest, model, speciality);
    }

    @RequestMapping(value = {"/registerTa"}, method = RequestMethod.GET)
    @Nonnull
    public final String registerTa(@Nonnull HttpServletRequest httpServletRequest,
                                   @Nonnull final Model model,
                                   @Nonnull String speciality

    ) throws Exception {
        Integer callId = (Integer) httpServletRequest.getSession().getAttribute("callId");
        SpRgGgdeMhtrwoUsers mhtrwoUser = spRgGgdeMhtrwoUsersRestRepo.findBySeqId(callId);

        System.out.println("callId " + callId);
        System.out.println(speciality+" ");

        if (Integer.parseInt(mhtrwoUser.getiInFlag()) == Constants.PERSON_TYPE.NATURAL.getCode()) {

            if (speciality.equals("technician"))
            {
                TSafetyTechnician tSafetyTechnician = safetyTechnicianRepository.findByAfmEquals(mhtrwoUser.getAfm());
                if (tSafetyTechnician != null) {
                    model.addAttribute("profileLoadError", "Υπάρχει ήδη τεχνικός ασφάλειας με αυτό το ΑΦΜ.");
                    return "registerError";
                }
            }
            else if (speciality.equals("doctor"))
            {
                TWorkplaceDoctor tWorkplaceDoctor = workplaceDoctorRepository.findByAfmEquals(mhtrwoUser.getAfm());
                if (tWorkplaceDoctor != null) {
                    model.addAttribute("profileLoadError", "Υπάρχει ήδη ιατρός εργασίας με αυτό το ΑΦΜ.");
                    return "registerError";
                }
            }

            final UserDTO user = new UserDTO();
            user.setUsername("");
            user.setEmail("");
            String[] nameDetails = mhtrwoUser.getOnomasia().split(",");
            if (nameDetails != null && nameDetails.length > 1) {
                user.setFirstname(nameDetails[2]);
            } else {
                user.setFirstname("");
            }
            //user.setFirstname(nameDetails[2]);
            if (nameDetails[1] != "")
                user.setLastname(nameDetails[0] + " " + nameDetails[1]);
            else
                user.setLastname(nameDetails[0]);
            String afm = mhtrwoUser.getAfm();
            model.addAttribute("newuser", user);
            model.addAttribute("afm", afm);
            if (speciality.equals("technician"))
                return "registerTa";
            else return "registerIe";
        } else {
            if (speciality.equals("technician"))
                model.addAttribute("profileLoadError", "Η δυνατότητα εγγραφής Τεχνικού Ασφαλείας δίνεται μόνο σε φυσικά πρόσωπα.");
            if (speciality.equals("doctor"))
                model.addAttribute("profileLoadError", "Η δυνατότητα εγγραφής Ιατρού Εργασίας δίνεται μόνο σε φυσικά πρόσωπα.");
            return "registerError";
        }
    }

    @RequestMapping(value = {"/register2/oauth"}, method = RequestMethod.GET)
    @Nonnull
    public final String register2oauth(HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest

    ) throws Exception {
        httpServletRequest.getSession().invalidate();
        ConsumerCredentials consumerCredentials = new ConsumerCredentials(Constants.OAUTH_CONSUMER_KEY, Constants.OAUTH_CONSUMER_SECRET);

        OAuth1AuthorizationFlow authFlow = OAuth1ClientSupport.builder(consumerCredentials).signatureMethod(PlaintextMethod.NAME)
                .authorizationFlow(
                        Constants.OAUTH_REQUEST_TOKEN_URI,
                        Constants.OAUTH_ACCESS_TOKEN_URI,
                        Constants.OAUTH_CONFIRM_ACCESS_URI
                ).callbackUri(Constants.OAUTH_CALLBACK_URI)
                .build();

        String authorizationUri = authFlow.start();
        httpServletRequest.getSession().removeAttribute("oauth");
        httpServletRequest.getSession().setAttribute("oauth", authFlow);
        System.out.println("SESSIONID: " + httpServletRequest.getSession().getId());

        return "redirect:" + authorizationUri;
    }

    @RequestMapping(value = {"/register2/oauth/confirmed"}, method = RequestMethod.GET)
    @Nonnull
    public final String register2(HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest,
                                  @Nonnull
                                  @RequestParam(value = "oauth_token")
                                  final String oauth_token,

                                  @Nonnull
                                  @ModelAttribute("oauth_verifier")
                                  final String oauth_verifier,

          /*@RequestParam("afm2")  // @ModelAttribute("afm2")
              final String afm2,*/

                                  @Nonnull final Model model

    ) throws Exception {

        System.out.println("SESSIONID2: " + httpServletRequest.getSession().getId());
        OAuth1AuthorizationFlow authFlow = (OAuth1AuthorizationFlow) httpServletRequest.getSession().getAttribute("oauth");
        if (authFlow == null) {
            System.out.println("SESSIONID2 FAILED: " + httpServletRequest.getSession().getId());
        }

        httpServletResponse.setCharacterEncoding("UTF-8");
        //httpServletRequest.getSession(false);

        String logoutToken = httpServletRequest.getParameter("oauth_token");
        AccessToken accessToken = authFlow.finish(oauth_verifier); // TODO remove comment

        Client client = ClientBuilder.newClient(); //for logout

        //OAuthReturnedMessageDemo response; //comment out for production
        //OAuthReturnedMessage response = null; // comment out for test must be defined and change response class for PRODUCTION
        Response response;
        String responseText;


        //MARIOS TODO before each return must log out and destroy session with OAuth
        //if finish ok continue else logout+redirect to register2 with error
        if (authFlow.getAuthorizedClient().target(Constants.OAUTH_RESOURCE_ONE_URI).request().get().getStatusInfo().getStatusCode() < 400) {
            System.out.println("Before get response!");
            //comment out for production
            //response = authFlow.getAuthorizedClient().target(Constants.OAUTH_RESOURCE_ONE_URI).request().get(OAuthReturnedMessageDemo.class);
            //comment out for test
            response = authFlow.getAuthorizedClient().target(Constants.OAUTH_RESOURCE_ONE_URI).request().get();
            responseText = response.readEntity(String.class);
            System.out.println(responseText);
        } else {
            client.target(Constants.OAUTH_LOGOUT_URI + logoutToken).request().get();
            httpServletRequest.getSession().invalidate();
            model.addAttribute("profileLoadError", "Δεν επιστρέφεται το ΑΦΜ χρήστη από την ΓΓΠΣ.");
            return "registerError";
        }

        // Needed for PROD
        String comments = "";
        String tin = "";
        try {
            JSONObject jObject = new JSONObject(responseText); // json
            JSONObject webUserDetails = jObject.getJSONObject("WebUserDetails"); // get data object
            comments = webUserDetails.getString("comments"); // get the name from data.
            tin = webUserDetails.getString("tin"); // get the name from data.
        } catch (Exception ex) {
            client.target(Constants.OAUTH_LOGOUT_URI + logoutToken).request().get();
            httpServletRequest.getSession().invalidate();
            model.addAttribute("profileLoadError", "Δεν επιστρέφεται το ΑΦΜ χρήστη από την ΓΓΠΣ.");
            return "registerError";
        }

        String afm = "";

        //DEV
        /*if (response.getMessageCode().equals(Constants.OAUTH_RESOURCE_ONE_CODE))
            afm = response.getMessageText();
        else {
            client.target(Constants.OAUTH_LOGOUT_URI + logoutToken).request().get();
            httpServletRequest.getSession().invalidate();
            model.addAttribute("profileLoadError", "Δεν επιστρέφεται ορθό ΑΦΜ χρήστη από την ΓΓΠΣ.");
            return "registerError";
        }*/

        // Needed for PROD
        if (tin != null && tin.length() >= 9 && comments.equals(Constants.OAUTH_RESOURCE_ONE_CODE))
            afm = tin;
        else {
            client.target(Constants.OAUTH_LOGOUT_URI + logoutToken).request().get();
            httpServletRequest.getSession().invalidate();
            model.addAttribute("profileLoadError", "Δεν επιστρέφεται ορθό ΑΦΜ χρήστη από την ΓΓΠΣ.");
            return "registerError";
        }

        //DEV
        //afm="107608168"; //MARIOS TODO remove
        //or
        //afm = afm2;

        String browserDetails = httpServletRequest.getHeader("User-Agent");
        String userAgent = browserDetails;

        String os = "";
        if (userAgent.toLowerCase().indexOf("windows") >= 0) {
            os = "Windows";
        } else if (userAgent.toLowerCase().indexOf("mac") >= 0) {
            os = "Mac";
        } else if (userAgent.toLowerCase().indexOf("x11") >= 0) {
            os = "Unix";
        } else if (userAgent.toLowerCase().indexOf("android") >= 0) {
            os = "Android";
        } else if (userAgent.toLowerCase().indexOf("iphone") >= 0) {
            os = "IPhone";
        } else {
            os = "UnKnown";
        }

        InetAddress addr = InetAddress.getLocalHost();
        String ipAddress = addr.getHostAddress();
        String hostname = addr.getHostName();

        Map<String, Object> input = new LinkedHashMap<String, Object>();
        input.put("afm", afm);
        input.put("auditEndUserApplUser", afm);
        input.put("auditEndUserOsUser", os); //  httpServletRequest.getRemoteHost()
        input.put("auditUnitCode", "clientPortal");
        input.put("auditUnitDescr", "clientPortal");
        input.put("auditEndUserHostIp", httpServletRequest.getRemoteAddr());
        input.put("auditEndUserHostName", httpServletRequest.getRemoteHost());
        input.put("logApplicationUserId", 1);

        Calendar calendar = Calendar.getInstance();
        java.sql.Date sqldate = new java.sql.Date(calendar.getTime().getTime());

        input.put("logAccessDate", sqldate);
        input.put("logEmployeeId", 1);
        input.put("logIPs", ipAddress);
        input.put("logUsernames", hostname);

        //call stored proc if fail logout+redirect to register2 with error
        Map<String, Object> bcallGGDEWS;
        try {
            bcallGGDEWS = (userStoredProcedures).procRegProcCallGGDEWS(input);
        } catch (Exception e) {
            e.printStackTrace();
            client.target(Constants.OAUTH_LOGOUT_URI + logoutToken).request().get();
            httpServletRequest.getSession().invalidate();
            model.addAttribute("profileLoadError", "Δεν υπάρχουν τα στοιχεία σας στο μητρώο.");
            return "registerError";
        }

        //retrieve callId and at it to session to get user data later in save
        httpServletRequest.getSession().removeAttribute("callId");
        httpServletRequest.getSession().removeAttribute("afmGGDE");
        httpServletRequest.getSession().setAttribute("callId", (Integer) bcallGGDEWS.get("out_CallId"));
        httpServletRequest.getSession().setAttribute("afmGGDE", afm);
        //logout
        //add to model the user
        client.target(Constants.OAUTH_LOGOUT_URI + logoutToken).request().get();

        return register2(httpServletRequest, model);
    }

    @RequestMapping(value = {"/register2"}, method = RequestMethod.GET)
    @Nonnull
    public final String register2(@Nonnull HttpServletRequest httpServletRequest,
                                  @Nonnull final Model model

    ) throws Exception {
        Integer callId = (Integer) httpServletRequest.getSession().getAttribute("callId");
        SpRgGgdeMhtrwoUsers mhtrwoUser = spRgGgdeMhtrwoUsersRestRepo.findBySeqId(callId);

        System.out.println("callId " + callId);
        if (Integer.parseInt(mhtrwoUser.getiInFlag()) == Constants.PERSON_TYPE.LEGAL.getCode()) {
            System.out.println("company ");
            return "ikaLogin";
        } else if (Integer.parseInt(mhtrwoUser.getiInFlag()) == Constants.PERSON_TYPE.NATURAL.getCode()) { //  && mhtrwoUser.getFirmFlag().equals("1")
            //if user exists with this afm in our user employee domain reject
            boolean existInEmployee = false;
            boolean existInCompany = false;
            TEmployee employee = employeeRepository.findByAfm(mhtrwoUser.getAfm());
            if (employee != null) {
                existInEmployee = true;
            }
            TCompany company = companyRepository.findByAfm(mhtrwoUser.getAfm());
            if (company != null) {
                existInCompany = true;
            }

            if (existInEmployee && existInCompany) {
                httpServletRequest.getSession().invalidate();
                model.addAttribute("profileLoadError", "Υπάρχει ήδη χρήστης με αυτό το ΑΦΜ.");
                return "registerError";
            } else if (existInEmployee && !existInCompany) {
                return "ikaLogin";
            } else if (!existInEmployee && existInCompany) {
                final UserDTO user = new UserDTO();
                user.setUsername("");
                user.setEmail("");
                String[] nameDetails = mhtrwoUser.getOnomasia().split(",");
                if (nameDetails != null && nameDetails.length > 1) {
                    user.setFirstname(nameDetails[2]);
                } else {
                    user.setFirstname("");
                }
                //user.setFirstname(nameDetails[2]);
                if (nameDetails[1] != "")
                    user.setLastname(nameDetails[0] + " " + nameDetails[1]);
                else
                    user.setLastname(nameDetails[0]);
                String afm = mhtrwoUser.getAfm();
                model.addAttribute("newuser", user);
                model.addAttribute("afm", afm);
                return "register2";
            } else {
                System.out.println("epitideumatias ");
                return "chooseEmployeeOrCompany";
            }
        } else {
            System.out.println("employee ");

            //if user exists with this afm in our user employee domain reject
            TEmployee employee = employeeRepository.findByAfm(mhtrwoUser.getAfm());
            if (employee != null) {
                //client.target(Constants.OAUTH_LOGOUT_URI + logoutToken).request().get();
                httpServletRequest.getSession().invalidate();
                model.addAttribute("profileLoadError", "Υπάρχει ήδη χρήστης με αυτό το ΑΦΜ.");
                return "registerError";
            }

            final UserDTO user = new UserDTO();
            user.setUsername("");
            user.setEmail("");
            String[] nameDetails = mhtrwoUser.getOnomasia().split(",");
            if (nameDetails != null && nameDetails.length > 1) {
                user.setFirstname(nameDetails[2]);
            } else {
                user.setFirstname("");
            }
            //user.setFirstname(nameDetails[2]);
            if (nameDetails[1] != "")
                user.setLastname(nameDetails[0] + " " + nameDetails[1]);
            else
                user.setLastname(nameDetails[0]);
            String afm = mhtrwoUser.getAfm();
            model.addAttribute("newuser", user);
            model.addAttribute("afm", afm);
            return "register2";
        }
    }

    @RequestMapping(value = {"/register2/oauth/callIkaWSClient"}, method = RequestMethod.POST)
    @Nonnull
    public final String callIkaWSClient(HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest,

                                        @Nonnull
                                        @RequestParam(value = "username")
                                        final String username,

                                        @Nonnull
                                        @RequestParam(value = "password")
                                        final String password,

                                        @Nonnull
                                        final Model model

    ) throws Exception {
        //System.out.println("username " + username + ", password " + password);
        String newPass = password;
        if (password.length() > 12) {
            newPass = password.substring(0, 12);
        }

        String ameIka = callIkaWS(username, newPass);
        //String ameIka = username;

        if (ameIka == null || ameIka.isEmpty()) {
            System.out.println("ameIka " + ameIka);
            model.addAttribute("profileSaveError", "Τα στοιχεία που δώσατε είναι λάθος!");

            return "ikaLogin";
        } else {
            httpServletRequest.getSession().removeAttribute("ameIka");
            httpServletRequest.getSession().setAttribute("ameIka", ameIka);

            return register3(httpServletRequest, model);
            //return null;
        }
    }

    @RequestMapping(value = {"/employeeProfile/updateData2"}, method = RequestMethod.POST)
    @Nonnull
    public final String updateData2(HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest,

                                    @Nonnull
                                    final Model model

    ) throws Exception {

        SpringUserDetails springUserDetails = currentAuthenticatedUser();
        if (springUserDetails == null) {
            throw new Exception("Error");
        }
        final UserDTO user = springUserDetails.getUserDTO();
        final EmployeeDTO employee = userService.findEmployee(user.getId());
        String afm = employee.getAfm();

        String browserDetails = httpServletRequest.getHeader("User-Agent");
        String userAgent = browserDetails;

        String os = "";
        if (userAgent.toLowerCase().indexOf("windows") >= 0) {
            os = "Windows";
        } else if (userAgent.toLowerCase().indexOf("mac") >= 0) {
            os = "Mac";
        } else if (userAgent.toLowerCase().indexOf("x11") >= 0) {
            os = "Unix";
        } else if (userAgent.toLowerCase().indexOf("android") >= 0) {
            os = "Android";
        } else if (userAgent.toLowerCase().indexOf("iphone") >= 0) {
            os = "IPhone";
        } else {
            os = "UnKnown";
        }

        InetAddress addr = InetAddress.getLocalHost();
        String ipAddress = addr.getHostAddress();
        String hostname = addr.getHostName();

        Map<String, Object> input = new LinkedHashMap<String, Object>();
        input.put("afm", afm);
        input.put("auditEndUserApplUser", afm);
        input.put("auditEndUserOsUser", os); //  httpServletRequest.getRemoteHost()
        input.put("auditUnitCode", "clientPortal");
        input.put("auditUnitDescr", "clientPortal");
        input.put("auditEndUserHostIp", httpServletRequest.getRemoteAddr());
        input.put("auditEndUserHostName", httpServletRequest.getRemoteHost());
        input.put("auditEndUserHostIp", ipAddress);
        input.put("auditEndUserHostName", hostname);
        input.put("logApplicationUserId", 1);

        Calendar calendar = Calendar.getInstance();
        java.sql.Date sqldate = new java.sql.Date(calendar.getTime().getTime());

        input.put("logAccessDate", sqldate);
        input.put("logEmployeeId", 1);
        input.put("logIPs", ipAddress);
        input.put("logUsernames", hostname);

        Map<String, Object> bcallGGDEWS;
        try {
            bcallGGDEWS = (userStoredProcedures).procRegProcCallGGDEWS(input);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("profileLoadError", "Δεν υπάρχουν τα στοιχεία σας στο μητρώο.");
            return viewEmployeeProfile(model);
        }

        httpServletRequest.getSession().setAttribute("callId", (Integer) bcallGGDEWS.get("out_CallId"));

        Integer callId = (Integer) httpServletRequest.getSession().getAttribute("callId");
        SpRgGgdeMhtrwoUsers mhtrwoUser = spRgGgdeMhtrwoUsersRestRepo.findBySeqId(callId);

        System.out.println("employee");

        String addr1 = mhtrwoUser.getFirmAddress();
        String addr2 = mhtrwoUser.getFirmAddressNo();
        String addr3 = mhtrwoUser.getFirmZipCode();
        String addr4 = mhtrwoUser.getFirmParDescr();
        String[] fullname = mhtrwoUser.getOnomasia().split(",");

        user.setAddress(addr1 + " " + addr2 + " - " + addr4);
        user.setAddrTk(addr3);

        user.setFirstname(fullname[2]);
        if  (fullname[1] != "")
            user.setLastname(fullname[0] + " " + fullname[1]);
        else
            user.setLastname(fullname[0]);
        employee.setCardNumber(mhtrwoUser.getCardNo());
        employee.setFatherName(fullname[3]);
        employee.setMotherName(mhtrwoUser.getMothersFirstName());

        System.out.println("employee");

        userService.updateEmployee(employee);
        userService.updateUser(user);

        final UserDTO updatedUser = userService.findUser(user.getId());
        final String passwordHash = userService.getPasswordHash(user);
        final EmployeeDTO updatedEmployee = userService.findEmployee(employee.getUserId());
        final SpringUserDetails newUserDetails = new SpringUserDetails(updatedUser, updatedUser.getId(), passwordHash, null, null);
        SpringUserDetails currentUser = CurrentUserDetailsService.getCurrentUser();
        final UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(newUserDetails, passwordHash, currentUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(newAuth);

        return viewEmployeeProfile(model);
    }

    @RequestMapping(value = {"/technicianProfile/updateData3"}, method = RequestMethod.POST)
    @Nonnull
    public final String updateData3(HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest,

                                    @Nonnull
                                    final Model model

    ) throws Exception {

        SpringUserDetails springUserDetails = currentAuthenticatedUser();
        if (springUserDetails == null) {
            throw new Exception("Error");
        }
        final UserDTO user = springUserDetails.getUserDTO();
        final TechnicianDTO technician = userService.findTechnician(user.getId());
        String afm = technician.getAfm();

        String browserDetails = httpServletRequest.getHeader("User-Agent");
        String userAgent = browserDetails;

        String os = "";
        if (userAgent.toLowerCase().indexOf("windows") >= 0) {
            os = "Windows";
        } else if (userAgent.toLowerCase().indexOf("mac") >= 0) {
            os = "Mac";
        } else if (userAgent.toLowerCase().indexOf("x11") >= 0) {
            os = "Unix";
        } else if (userAgent.toLowerCase().indexOf("android") >= 0) {
            os = "Android";
        } else if (userAgent.toLowerCase().indexOf("iphone") >= 0) {
            os = "IPhone";
        } else {
            os = "UnKnown";
        }

        InetAddress addr = InetAddress.getLocalHost();
        String ipAddress = addr.getHostAddress();
        String hostname = addr.getHostName();

        Map<String, Object> input = new LinkedHashMap<String, Object>();
        input.put("afm", afm);
        input.put("auditEndUserApplUser", afm);
        input.put("auditEndUserOsUser", os); //  httpServletRequest.getRemoteHost()
        input.put("auditUnitCode", "clientPortal");
        input.put("auditUnitDescr", "clientPortal");
        input.put("auditEndUserHostIp", httpServletRequest.getRemoteAddr());
        input.put("auditEndUserHostName", httpServletRequest.getRemoteHost());
        input.put("auditEndUserHostIp", ipAddress);
        input.put("auditEndUserHostName", hostname);
        input.put("logApplicationUserId", 1);

        Calendar calendar = Calendar.getInstance();
        java.sql.Date sqldate = new java.sql.Date(calendar.getTime().getTime());

        input.put("logAccessDate", sqldate);
        input.put("logEmployeeId", 1);
        input.put("logIPs", ipAddress);
        input.put("logUsernames", hostname);

        Map<String, Object> bcallGGDEWS;
        try {
            bcallGGDEWS = (userStoredProcedures).procRegProcCallGGDEWS(input);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("profileLoadError", "Δεν υπάρχουν τα στοιχεία σας στο μητρώο.");
            return viewTechnicianProfile(model);
        }

        httpServletRequest.getSession().setAttribute("callId", (Integer) bcallGGDEWS.get("out_CallId"));

        Integer callId = (Integer) httpServletRequest.getSession().getAttribute("callId");
        SpRgGgdeMhtrwoUsers mhtrwoUser = spRgGgdeMhtrwoUsersRestRepo.findBySeqId(callId);

        System.out.println("technician");

        String addr1 = mhtrwoUser.getFirmAddress();
        String addr2 = mhtrwoUser.getFirmAddressNo();
        String addr3 = mhtrwoUser.getFirmZipCode();
        String addr4 = mhtrwoUser.getFirmParDescr();
        String[] fullname = mhtrwoUser.getOnomasia().split(",");

        user.setAddress(addr1 + " " + addr2 + " - " + addr4);
        user.setAddrTk(addr3);

        user.setFirstname(fullname[2]);
        if (fullname[1] != "")
            user.setLastname(fullname[0] + " " + fullname[1]);
        else
            user.setLastname(fullname[0]);
        technician.setCardNumber(mhtrwoUser.getCardNo());

        System.out.println("technician");

        userService.updateSafetyTechnician(technician);
        userService.updateUser(user);

        final UserDTO updatedUser = userService.findUser(user.getId());
        final String passwordHash = userService.getPasswordHash(user);
        final TechnicianDTO updatedTechnician = userService.findTechnician(technician.getUserId());
        final SpringUserDetails newUserDetails = new SpringUserDetails(updatedUser, updatedUser.getId(), passwordHash, null, null);
        SpringUserDetails currentUser = CurrentUserDetailsService.getCurrentUser();
        final UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(newUserDetails, passwordHash, currentUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(newAuth);

        return viewTechnicianProfile(model);
    }

    @RequestMapping(value = {"/companyProfile/updateData"}, method = RequestMethod.POST)
    @Nonnull
    public final String updateData(HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest,

                                   @Nonnull
                                   final Model model

    ) throws Exception {

        SpringUserDetails springUserDetails = currentAuthenticatedUser();
        if (springUserDetails == null) {
            throw new Exception("Error");
        }
        final UserDTO user = springUserDetails.getUserDTO();
        final CompanyDTO company = userService.findCompany(user.getId());

        String afm = company.getAfm();

        String browserDetails = httpServletRequest.getHeader("User-Agent");
        String userAgent = browserDetails;

        String os = "";
        if (userAgent.toLowerCase().indexOf("windows") >= 0) {
            os = "Windows";
        } else if (userAgent.toLowerCase().indexOf("mac") >= 0) {
            os = "Mac";
        } else if (userAgent.toLowerCase().indexOf("x11") >= 0) {
            os = "Unix";
        } else if (userAgent.toLowerCase().indexOf("android") >= 0) {
            os = "Android";
        } else if (userAgent.toLowerCase().indexOf("iphone") >= 0) {
            os = "IPhone";
        } else {
            os = "UnKnown";
        }

        InetAddress addr = InetAddress.getLocalHost();
        String ipAddress = addr.getHostAddress();
        String hostname = addr.getHostName();


        Map<String, Object> input = new LinkedHashMap<String, Object>();
        input.put("afm", afm);
        input.put("auditEndUserApplUser", afm);
        input.put("auditEndUserOsUser", os); //  httpServletRequest.getRemoteHost()
        input.put("auditUnitCode", "clientPortal");
        input.put("auditUnitDescr", "clientPortal");
        input.put("auditEndUserHostIp", httpServletRequest.getRemoteAddr());
        input.put("auditEndUserHostName", httpServletRequest.getRemoteHost());
        input.put("auditEndUserHostIp", ipAddress);
        input.put("auditEndUserHostName", hostname);
        input.put("logApplicationUserId", 1);

        Calendar calendar = Calendar.getInstance();
        java.sql.Date sqldate = new java.sql.Date(calendar.getTime().getTime());

        input.put("logAccessDate", sqldate);
        input.put("logEmployeeId", 1);
        input.put("logIPs", ipAddress);
        input.put("logUsernames", hostname);

        Map<String, Object> bcallGGDEWS;
        try {
            bcallGGDEWS = (userStoredProcedures).procRegProcCallGGDEWS(input);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("profileLoadError", "Δεν υπάρχουν τα στοιχεία σας στο μητρώο.");
            return viewCompanyProfile(model);
        }

        httpServletRequest.getSession().setAttribute("callId", (Integer) bcallGGDEWS.get("out_CallId"));

        Integer callId = (Integer) httpServletRequest.getSession().getAttribute("callId");
        SpRgGgdeMhtrwoUsers mhtrwoUser = spRgGgdeMhtrwoUsersRestRepo.findBySeqId(callId);

        System.out.println("company");

        // Set company
        company.setName(mhtrwoUser.getOnomasia());
        if (mhtrwoUser.getFirmCommerTitle() != null)
            company.setTitle(mhtrwoUser.getFirmCommerTitle());
        else
            company.setTitle(mhtrwoUser.getOnomasia());

        // Set user

        // Check if is person or company
        try {
            if (mhtrwoUser.getOnomasia().contains(",")) {
                if (mhtrwoUser.getOnomasia().split(",")[1] != "")
                    user.setLastname(mhtrwoUser.getOnomasia().split(",")[0] + " " + mhtrwoUser.getOnomasia().split(",")[1]);
                else
                    user.setLastname(mhtrwoUser.getOnomasia().split(",")[0]);
                user.setFirstname(mhtrwoUser.getOnomasia().split(",")[2]);
            } else {
                user.setFirstname("");
                if (mhtrwoUser.getFirmCommerTitle() != null)
                    user.setLastname(mhtrwoUser.getFirmCommerTitle());
                else
                    user.setLastname(mhtrwoUser.getOnomasia());
            }
        } catch (Exception e) {
            LOGGER.error("companyProfileUpdateDataError: " + e + " id:" + company.getId());
            user.setFirstname("");
            if (mhtrwoUser.getFirmCommerTitle() != null)
                user.setLastname(mhtrwoUser.getFirmCommerTitle());
            else
                user.setLastname(mhtrwoUser.getOnomasia());
        }

        user.setRole(Constants.USER_TYPE.COMPANY.getCode());

        System.out.println("company");

        userService.updateCompany(company);
        userService.updateUser(user);

        final UserDTO updatedUser = userService.findUser(user.getId());
        final String passwordHash = userService.getPasswordHash(user);
        final CompanyDTO updatedCompany = userService.findCompany(company.getUserId());
        List<TEmployerBranchIKA> branchIKAs = tEmployerBranchIKARepo.findByCompanyAme(company.getAme());
        String branchIds = companyService.getCompanyBRanchIds(updatedCompany);
        branchIds = branchIds.substring(0, branchIds.length()); //remove ending ,
        final SpringUserDetails newUserDetails = new SpringUserDetails(updatedUser, updatedUser.getId(), passwordHash, updatedCompany, branchIds);
        SpringUserDetails currentUser = CurrentUserDetailsService.getCurrentUser();
        final UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(newUserDetails, passwordHash, currentUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(newAuth);


        return viewCompanyProfile(model);
    }

    @RequestMapping(value = {"/register2/oauth/goToEmployee"}, method = RequestMethod.POST)
    @Nonnull
    public final String goToRegister2(HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest,

                                      @Nonnull
                                      final Model model

    ) throws Exception {
        Integer callId = (Integer) httpServletRequest.getSession().getAttribute("callId");
        SpRgGgdeMhtrwoUsers mhtrwoUser = spRgGgdeMhtrwoUsersRestRepo.findBySeqId(callId);

        final UserDTO user = new UserDTO();
        user.setUsername("");
        user.setEmail("");
        String[] nameDetails = mhtrwoUser.getOnomasia().split(",");
        if (nameDetails != null && nameDetails.length > 1) {
            user.setFirstname(nameDetails[2]);
        } else {
            user.setFirstname("");
        }
        //user.setFirstname(nameDetails[2]);
        if (nameDetails[1] != "")
            user.setLastname(nameDetails[0] + " " + nameDetails[1]);
        else
            user.setLastname(nameDetails[0]);
        String afm = mhtrwoUser.getAfm();
        model.addAttribute("newuser", user);
        model.addAttribute("afm", afm);

        return "register2";
    }

    @RequestMapping(value = {"/register2/oauth/goToCompany"}, method = RequestMethod.POST)
    @Nonnull
    public final String goToCompany(HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest,

                                    @Nonnull
                                    final Model model

    ) throws Exception {
        return "ikaLogin";
    }

    @RequestMapping(value = {"/register3"}, method = RequestMethod.GET)
    @Nonnull
    public final String register3(@Nonnull HttpServletRequest httpServletRequest,
                                  @Nonnull final Model model

    ) throws Exception {
        String ameIka = (String) httpServletRequest.getSession().getAttribute("ameIka");
        String afmGGDE = (String) httpServletRequest.getSession().getAttribute("afmGGDE");

        System.out.println("ΑΦΜ: " + afmGGDE + ", ΑμεΙΚΑ: " + ameIka);
        if (afmGGDE == null) {
            System.out.println("SESSIONID3: " + httpServletRequest.getSession().getId());
        }

        TCompany company = companyRepository.findTCompanyByAfmAndAme(afmGGDE, ameIka);
        if (company != null) {
            model.addAttribute("profileSaveError", "Υπάρχει ήδη εγγεγραμμένος χρήστης με τα στοιχεία σας.\n" +
                    "Αν δεν θυμάστε τα στοιχεία σύνδεσης επιλέξτε «Ξέχασα τον Κωδικό μου» από το βασικό μενού.");
            return "ikaLogin";
        }

        TEmployerBranchIKA tEmployerBranchIKA = tEmployerBranchIKARepo.findCompanyMainBranchByAfmAndAme(afmGGDE, ameIka);
        //TEmployerBranchIKA tEmployerBranchIKA = tEmployerBranchIKARepo.findCompanyMainBranchByAfm("099985737");
        if (tEmployerBranchIKA == null) {
            model.addAttribute("profileSaveError", "Τα στοιχεία που δώσατε (ΑΦΜ: " + afmGGDE + ", ΑμεΙΚΑ: " + ameIka + ") δεν βρέθηκαν στο μητρώο του ΕΡΓΑΝΗ.\n Το παραπάνω μπορεί να συμβεί\n " +
                    "είτε γιατί το ΑΦΜ δεν ανήκει στην εταιρία,\n " +
                    "είτε γιατί η επιχείρησή σας δεν έχει λογαριασμό στο ΕΡΓΑΝΗ,\n " +
                    "είτε γιατί δημιουργήσατε λογαριασμό στο ΕΡΓΑΝΗ σήμερα και δεν έχει ολοκληρωθεί ο συγχρονισμός των στοιχείων.");
            return "ikaLogin";
        } else {
            final UserDTO user = new UserDTO();
            user.setUsername("");
            user.setEmail("");
            user.setFirstname(tEmployerBranchIKA.getRgEmpName() != null ? tEmployerBranchIKA.getRgEmpName() : "");
            user.setLastname(tEmployerBranchIKA.getRgEmpFullname() != null ? tEmployerBranchIKA.getRgEmpFullname() : "");
            user.setRole(Constants.USER_TYPE.COMPANY.getCode());
            model.addAttribute("newuser", user);
            model.addAttribute("afm", tEmployerBranchIKA.getRgEbrTaxationNumber());
            return "register3";
        }
    }

    @RequestMapping(value = {"/register2/saveCompany"}, method = RequestMethod.POST)
    @Nonnull
    public final String saveNewUserOAuthCompany(@Nonnull HttpServletRequest httpServletRequest,
                                                @Nonnull
                                                @RequestParam(value = "password")
                                                final String password,

                                                @Nonnull
                                                @RequestParam(value = "reenterEmail")
                                                final String reenterEmail,

                                                @Nonnull
                                                @ModelAttribute("afm")
                                                final String afm,

                                                @Nonnull
                                                @ModelAttribute("newuser")
                                                        UserDTO userDTO,

                                                @Nonnull
                                                final BindingResult result,

                                                @Nonnull
                                                final Model model

    ) throws Exception {
        userValidator.validate(userDTO, result);
        if (result.hasErrors()) {
            model.addAttribute("profileSaveError", "Υπάρχουν λάθη!");

            userDTO.setPassword("");
            model.addAttribute("newuser", userDTO);
            model.addAttribute("afm", afm);
            return "register3";
        } else if (reenterEmail != null && !userDTO.getEmail().equals(reenterEmail)) {
            model.addAttribute("profileSaveError", "Τα πεδία 'Email' και 'Επιβεβαίωση Email' πρέπει να συμφωνούν.");

            userDTO.setPassword("");
            model.addAttribute("newuser", userDTO);
            model.addAttribute("afm", afm);
            return "register3";

        } else if (password == null || password.isEmpty()) {
            model.addAttribute("profileSaveError", "Παρακαλώ εισάγετε το τρέχον password για να μπορέσετε να σώσετε τις αλλαγές.");

            userDTO.setPassword("");
            model.addAttribute("newuser", userDTO);
            model.addAttribute("afm", afm);
            return "register3";

        } else if (password.length() < 6) {
            model.addAttribute("profileSaveError", "Παρακαλώ το password πρέπει να αποτελείται από τουλάχιστον 6 χαρακτήρες.");

            userDTO.setPassword("");
            model.addAttribute("newuser", userDTO);
            model.addAttribute("afm", afm);
            return "register3";

        } else if (!password.matches(".*[a-zA-Z]+.*")) {
            model.addAttribute("profileSaveError", "Παρακαλώ το password πρέπει να περιέχει τουλάχιστον 1 αλφαβητικό (latin) χαρακτήρα");

            userDTO.setPassword("");
            model.addAttribute("newuser", userDTO);
            model.addAttribute("afm", afm);
            return "register3";

        } else if (!password.matches(".*\\d+.*")) {
            model.addAttribute("profileSaveError", "Παρακαλώ το password πρέπει να περιέχει τουλάχιστον 1 αριθμητικό χαρακτήρα.");

            userDTO.setPassword("");
            model.addAttribute("newuser", userDTO);
            model.addAttribute("afm", afm);
            return "register3";

        } else if (password.toLowerCase().contains(userDTO.getUsername().toLowerCase()) || password.toLowerCase().contains(userDTO.getLastname().toLowerCase())) {
            model.addAttribute("profileSaveError", "Παρακαλώ το password πρέπει να μην περιέχει το username, το επώνυμο ή το όνομα.");

            userDTO.setPassword("");
            model.addAttribute("newuser", userDTO);
            model.addAttribute("afm", afm);
            return "register3";

        } else if (!userDTO.getUsername().matches("[a-zA-Z0-9-@#!*$%^&.'_={}()]*")) {
            model.addAttribute("profileSaveError", "Παρακαλώ το username δεν επιτρέπεται να περιέχει ελληνικούς χαρακτήρες, κενά ή τους ειδικούς χαρακτήρες + , ; / < >");

            userDTO.setPassword("");
            model.addAttribute("newuser", userDTO);
            model.addAttribute("afm", afm);
            return "register3";

        } else if (userDTO.getUsername().length() < 5) {
            model.addAttribute("profileSaveError", "Παρακαλώ εισάγετε κάποιο username.");

            userDTO.setPassword("");
            model.addAttribute("newuser", userDTO);
            model.addAttribute("afm", afm);
            return "register3";

        } else {
            if (!password.isEmpty()) {
                if (!(userDTO.getUsername().substring(0, 4).equals("ptl-"))) {
                    userDTO.setUsername("ptl-" + userDTO.getUsername());
                }

                String ameIka = (String) httpServletRequest.getSession().getAttribute("ameIka");
                String afmGGDE = (String) httpServletRequest.getSession().getAttribute("afmGGDE");
                TEmployerBranchIKA tEmployerBranchIKA = tEmployerBranchIKARepo.findCompanyMainBranchByAfmAndAme(afm, ameIka);
                //TEmployerBranchIKA tEmployerBranchIKA = tEmployerBranchIKARepo.findCompanyMainBranchByAfm("099985737");
                userDTO.setAddress(tEmployerBranchIKA.getRgEbrAddressStreet());
                userDTO.setAddrTk(tEmployerBranchIKA.getRgEbrZipCode());
                userDTO.setFirstname(tEmployerBranchIKA.getRgEmpName());
                userDTO.setLastname(tEmployerBranchIKA.getRgEmpFullname());
                userDTO.setPhone(tEmployerBranchIKA.getRgEbrPhoneNumber());

                try {
                    userService.createUser(userDTO);
                } catch (UsernameExistsException ex) {
                    model.addAttribute("profileSaveError", ex.getMessage());
                    return register3(httpServletRequest, model);
                }
                UserDTO updatedUser = userService.findUserByUsername(userDTO.getUsername());
                if (userDTO.getRole() == Constants.USER_TYPE.CITIZEN.getCode()) {
                    userService.createCitizen(updatedUser.getId());
                } else if (userDTO.getRole() == Constants.USER_TYPE.EMPLOYEE.getCode()) {
                    userService.createEmployee(updatedUser.getId());
                } else if (userDTO.getRole() == Constants.USER_TYPE.COMPANY.getCode()) {
                    userService.createCompany(updatedUser.getId(), afm, ameIka, 0, updatedUser.getLastname());
                } else if (userDTO.getRole() == Constants.USER_TYPE.DOCTOR.getCode()) {
                    //userService.createWorkplaceDoctor(updatedUser.getId());
                } else if (userDTO.getRole() == Constants.USER_TYPE.TECHNICIAN.getCode()) {
                    //userService.createSafetyTechnician(updatedUser.getId());
                } else if (userDTO.getRole() == Constants.USER_TYPE.ASSOCIATION.getCode()) {
                    userService.createCompany(updatedUser.getId(), afm, ameIka, 1, updatedUser.getLastname()); //Swmateio
                } else if (userDTO.getRole() == Constants.USER_TYPE.COMPANY_USER.getCode()) {
                    userService.createCompanyUser(updatedUser.getId());
                } else if (userDTO.getRole() == Constants.USER_TYPE.ADMIN.getCode()) {
                    userService.createCitizen(updatedUser.getId());
                }


                // ***** Oracle Identity Management / LDAP Functions *****
                Map<String, Object> createUserInput = new LinkedHashMap<String, Object>();
                createUserInput.put("p_surname", userDTO.getLastname());
                createUserInput.put("p_fathers_name", "");
                createUserInput.put("p_name", userDTO.getFirstname());
                createUserInput.put("p_email", userDTO.getEmail());
                createUserInput.put("p_telephonenumber", userDTO.getPhone());
                createUserInput.put("p_mobile", "");
                createUserInput.put("p_username", userDTO.getUsername());
                createUserInput.put("p_emp_number", "0");

                Integer funcCreateUserResult = -1;
                Integer funcGrantRoleResult = -1;
                Integer funcResetPassResult = -1;
                String msg = "Προέκυψε κάποιο πρόβλημα κατά την εγγραφή. Δοκιμάστε ξανά.";
                Boolean rollback = false;

                try {
                    // call function to Add user to Oracle Identity Management / LDAP
                    funcCreateUserResult = (userStoredProcedures).secOimCallFunction(createUserInput, "PCG_SEC_OIM.SEC_OIM_CREATE_USER");
                    if (funcCreateUserResult != 0) {
                        rollback = true;
                        msg = "Προέκυψε κάποιο πρόβλημα με την εγγραφή του χρήστη.";
                    } else {
                        String p_role_code = "PortalEmployee";
                        if (userDTO.getRole().equals(Constants.USER_TYPE.EMPLOYEE.getCode()))
                            p_role_code = "PortalEmployee";
                        else if (userDTO.getRole().equals(Constants.USER_TYPE.DOCTOR.getCode()))
                            p_role_code = "PortalDoctor";
                        else if (userDTO.getRole().equals(Constants.USER_TYPE.TECHNICIAN.getCode()))
                            p_role_code = "PortalSafetyTechnician";
                        else if (userDTO.getRole().equals(Constants.USER_TYPE.COMPANY.getCode()))
                            p_role_code = "PortalEmployer";
                        else if (userDTO.getRole().equals(Constants.USER_TYPE.ASSOCIATION.getCode()))
                            p_role_code = "PortalAssociation";
                        else if (userDTO.getRole().equals(Constants.USER_TYPE.CITIZEN.getCode()))
                            p_role_code = "PortalCitizen";
                        else if (userDTO.getRole().equals(Constants.USER_TYPE.COMPANY_USER.getCode()))
                            p_role_code = "PortalEmployerUser";
                        else if (userDTO.getRole().equals(Constants.USER_TYPE.ADMIN.getCode()))
                            p_role_code = "PortalAdmin";
                        else if (userDTO.getRole().equals(Constants.USER_TYPE.UNKNOWN.getCode()))
                            p_role_code = "PortalOther";
                        Map<String, Object> grantRoleToUserInput = new LinkedHashMap<String, Object>();
                        grantRoleToUserInput.put("p_username", userDTO.getUsername());
                        grantRoleToUserInput.put("p_role_code", p_role_code);

                        // call function to Give the user a specific role to Oracle Identity Management / LDAP
                        funcGrantRoleResult = (userStoredProcedures).secOimCallFunction(grantRoleToUserInput, "PCG_SEC_OIM.SEC_OIM_GRANT_ROLE_TO_USER");
                        if (funcGrantRoleResult != 0) {
                            rollback = true;
                            msg = "Προέκυψε κάποιο πρόβλημα με την ανάθεση ρόλου στο χρήστη.";
                        } else {
                            Map<String, Object> resetPassInput = new LinkedHashMap<String, Object>();
                            resetPassInput.put("p_username", userDTO.getUsername());
                            resetPassInput.put("p_new_pass", password);
                            System.out.println("p_new_pass " + password);

                            // call function to Reset password of a specific user in Oracle Identity Management / LDAP
                            funcResetPassResult = (userStoredProcedures).secOimCallFunction(resetPassInput, "PCG_SEC_OIM.SEC_OIM_RESET_PASS");
                            if (funcResetPassResult != 0) {
                                rollback = true;
                                msg = "Προέκυψε κάποιο πρόβλημα με το password του χρήστη.";
                            } else {
                                // All went OK!
                                try {
                                    mailService.createNewUser(updatedUser.getEmail(), updatedUser.getUsername(), password, updatedUser.getFirstname(), updatedUser.getLastname());
                                } catch (Exception ex) {
                                    model.addAttribute("profileSaveError", ex.getMessage());
                                    model.addAttribute("newuser", userDTO);
                                    model.addAttribute("afm", afm);
                                    return "register3";
                                    //return register3(httpServletRequest, model);
                                }
                            }
                        }
                    }
                } catch (Exception ex) {
                    System.out.println("msg : " + msg);
                    System.out.println("userDTO.getId() : " + userDTO.getId());
                    System.out.println("updatedUser.getId() : " + updatedUser.getId());
                    if (updatedUser.getRole() == Constants.USER_TYPE.CITIZEN.getCode()) {
                        citizenRepository.delete(updatedUser.getId());
                    } else if (updatedUser.getRole() == Constants.USER_TYPE.EMPLOYEE.getCode()) {
                        employeeRepository.delete(updatedUser.getId());
                    } else if (updatedUser.getRole() == Constants.USER_TYPE.COMPANY.getCode()) {
                        companyRepository.delete(updatedUser.getId());
                    } else if (updatedUser.getRole() == Constants.USER_TYPE.DOCTOR.getCode()) {
                        workplaceDoctorRepository.delete(updatedUser.getId());
                    } else if (updatedUser.getRole() == Constants.USER_TYPE.TECHNICIAN.getCode()) {
                        safetyTechnicianRepository.delete(updatedUser.getId());
                    } else if (updatedUser.getRole() == Constants.USER_TYPE.ASSOCIATION.getCode()) {
                        companyRepository.delete(updatedUser.getId());
                    } else if (updatedUser.getRole() == Constants.USER_TYPE.COMPANY_USER.getCode()) {
                        userRepository.delete(updatedUser.getId());
                    } else if (updatedUser.getRole() == Constants.USER_TYPE.ADMIN.getCode()) {
                        citizenRepository.delete(updatedUser.getId());
                    }
                    userRepository.delete(updatedUser.getId());

                    model.addAttribute("profileSaveError", msg);
                    model.addAttribute("newuser", userDTO);
                    model.addAttribute("afm", afm);
                    return "register3";
                }
                if (rollback == true) {
                    System.out.println("msg : " + msg);
                    System.out.println("userDTO.getId() : " + userDTO.getId());
                    System.out.println("updatedUser.getId() : " + updatedUser.getId());
                    if (updatedUser.getRole() == Constants.USER_TYPE.CITIZEN.getCode()) {
                        citizenRepository.delete(updatedUser.getId());
                    } else if (updatedUser.getRole() == Constants.USER_TYPE.EMPLOYEE.getCode()) {
                        employeeRepository.delete(updatedUser.getId());
                    } else if (updatedUser.getRole() == Constants.USER_TYPE.COMPANY.getCode()) {
                        companyRepository.delete(updatedUser.getId());
                    } else if (updatedUser.getRole() == Constants.USER_TYPE.DOCTOR.getCode()) {
                        workplaceDoctorRepository.delete(updatedUser.getId());
                    } else if (updatedUser.getRole() == Constants.USER_TYPE.TECHNICIAN.getCode()) {
                        safetyTechnicianRepository.delete(updatedUser.getId());
                    } else if (updatedUser.getRole() == Constants.USER_TYPE.ASSOCIATION.getCode()) {
                        companyRepository.delete(updatedUser.getId());
                    } else if (updatedUser.getRole() == Constants.USER_TYPE.COMPANY_USER.getCode()) {
                        userRepository.delete(updatedUser.getId());
                    } else if (updatedUser.getRole() == Constants.USER_TYPE.ADMIN.getCode()) {
                        citizenRepository.delete(updatedUser.getId());
                    }
                    userRepository.delete(updatedUser.getId());

                    model.addAttribute("profileSaveError", msg);
                    model.addAttribute("newuser", userDTO);
                    model.addAttribute("afm", afm);
                    return "register3";
                    //return register3(httpServletRequest, model);
                }
                // *************************
            }
        }

        return accountSaved2(userDTO, model);
    }

    @RequestMapping(value = {"/register2/save"}, method = RequestMethod.POST)
    @Nonnull
    public final String saveNewUserOAuth(@Nonnull HttpServletRequest httpServletRequest,
                                         @Nonnull
                                         @RequestParam(value = "password")
                                         final String password,

                                         @Nonnull
                                         @RequestParam(value = "reenterEmail")
                                         final String reenterEmail,

                                         @Nonnull
                                         @RequestParam(value = "afm")
                                         final String afm,

                                         @Nonnull
                                         @ModelAttribute("newuser")
                                                 UserDTO userDTO,

                                         @Nonnull
                                         final BindingResult result,

                                         @Nonnull
                                         final Model model

    ) throws Exception {
        userValidator.validate(userDTO, result);
        if (result.hasErrors()) {
            model.addAttribute("profileSaveError", "Υπάρχουν λάθη!");

            userDTO.setPassword("");
            model.addAttribute("newuser", userDTO);
            model.addAttribute("afm", afm);
            return "register2";
        } else if (reenterEmail != null && !userDTO.getEmail().equals(reenterEmail)) {
            model.addAttribute("profileSaveError", "Τα πεδία 'Email' και 'Επιβεβαίωση Email' πρέπει να συμφωνούν.");

            userDTO.setPassword("");
            model.addAttribute("newuser", userDTO);
            model.addAttribute("afm", afm);
            return "register2";

        } else if (password == null || password.isEmpty()) {
            model.addAttribute("profileSaveError", "Παρακαλώ εισάγετε το τρέχον password για να μπορέσετε να σώσετε τις αλλαγές.");

            userDTO.setPassword("");
            model.addAttribute("newuser", userDTO);
            model.addAttribute("afm", afm);
            return "register2";

        } else if (password.length() < 6) {
            model.addAttribute("profileSaveError", "Παρακαλώ το password πρέπει να αποτελείται από τουλάχιστον 6 χαρακτήρες.");

            userDTO.setPassword("");
            model.addAttribute("newuser", userDTO);
            model.addAttribute("afm", afm);
            return "register2";

        } else if (!password.matches(".*[a-zA-Z]+.*")) {
            model.addAttribute("profileSaveError", "Παρακαλώ το password πρέπει να περιέχει τουλάχιστον 1 αλφαβητικό (latin) χαρακτήρα");

            userDTO.setPassword("");
            model.addAttribute("newuser", userDTO);
            model.addAttribute("afm", afm);
            return "register2";

        } else if (!password.matches(".*\\d+.*")) {
            model.addAttribute("profileSaveError", "Παρακαλώ το password πρέπει να περιέχει τουλάχιστον 1 αριθμητικό χαρακτήρα.");

            userDTO.setPassword("");
            model.addAttribute("newuser", userDTO);
            model.addAttribute("afm", afm);
            return "register2";

        } else if (password.toLowerCase().contains(userDTO.getUsername().toLowerCase()) || password.toLowerCase().contains(userDTO.getLastname().toLowerCase())) {
            model.addAttribute("profileSaveError", "Παρακαλώ το password πρέπει να μην περιέχει το username, το επώνυμο ή το όνομα.");

            userDTO.setPassword("");
            model.addAttribute("newuser", userDTO);
            model.addAttribute("afm", afm);
            return "register2";

        } else if (!userDTO.getUsername().matches("[a-zA-Z0-9-@#!*$%^&.'_={}()]*")) {
            model.addAttribute("profileSaveError", "Παρακαλώ το username δεν επιτρέπεται να περιέχει ελληνικούς χαρακτήρες, κενά ή τους ειδικούς χαρακτήρες + , ; / < >");

            userDTO.setPassword("");
            model.addAttribute("newuser", userDTO);
            model.addAttribute("afm", afm);
            return "register2";

        } else if (userDTO.getUsername().length() < 5) {
            model.addAttribute("profileSaveError", "Παρακαλώ εισάγετε κάποιο username.");

            userDTO.setPassword("");
            model.addAttribute("newuser", userDTO);
            model.addAttribute("afm", afm);
            return "register2";

        } else {
            if (!password.isEmpty()) {
                if (!(userDTO.getUsername().substring(0, 4).equals("ptl-"))) {
                    userDTO.setUsername("ptl-" + userDTO.getUsername());
                }
                Integer callId = (Integer) httpServletRequest.getSession().getAttribute("callId");
                SpRgGgdeMhtrwoUsers mhtrwoUser = spRgGgdeMhtrwoUsersRestRepo.findBySeqId(callId);
                userDTO.setAddress(mhtrwoUser.getRecidenceAddress() + " " + mhtrwoUser.getRecidenceAddressNo());
                userDTO.setAddrTk(mhtrwoUser.getRecidenceZipCode());
                String[] nameDetails = mhtrwoUser.getOnomasia().split(",");
                if (nameDetails != null && nameDetails.length > 1) {
                    userDTO.setFirstname(nameDetails[2]);
                } else {
                    userDTO.setFirstname("");
                }
                //userDTO.setFirstname(nameDetails[2]);
                if (nameDetails[1] != "")
                    userDTO.setLastname(nameDetails[0] + " " + nameDetails[1]);
                else
                    userDTO.setLastname(nameDetails[0]);
                userDTO.setPhone(mhtrwoUser.getIndPhone());

                if (mhtrwoUser != null)
                    System.out.println("mhtrwoUser OK!");

                try {
                    userService.createUser(userDTO);
                    System.out.println("User created!");
                } catch (UsernameExistsException ex) {
                    model.addAttribute("profileSaveError", ex.getMessage());
                    model.addAttribute("newuser", userDTO);
                    model.addAttribute("afm", afm);
                    return "register2";
                    //model.addAttribute("profileSaveError", ex.getMessage());
                    //return register2(httpServletRequest, model);
                }
                UserDTO updatedUser = userService.findUserByUsername(userDTO.getUsername());
                System.out.println("updatedUser " + updatedUser.getId());
                if (userDTO.getRole() == Constants.USER_TYPE.CITIZEN.getCode()) {
                    userService.createCitizen(updatedUser.getId());
                } else if (userDTO.getRole() == Constants.USER_TYPE.EMPLOYEE.getCode()) {
                    userService.createEmployeeOAuth(updatedUser.getId(), mhtrwoUser);
                } else if (userDTO.getRole() == Constants.USER_TYPE.COMPANY.getCode()) {
                    //userService.createCompany(updatedUser.getId(), afm, ame, 0);
                } else if (userDTO.getRole() == Constants.USER_TYPE.DOCTOR.getCode()) {
                    //userService.createWorkplaceDoctor(updatedUser.getId(), afm, mhtrwoUser.getCardNo(), mhtrwoUser.getCardKind());
                } else if (userDTO.getRole() == Constants.USER_TYPE.TECHNICIAN.getCode()) {
                    //userService.createSafetyTechnician(updatedUser.getId());
                } else if (userDTO.getRole() == Constants.USER_TYPE.ASSOCIATION.getCode()) {
                    //userService.createCompany(updatedUser.getId(), afm, ame, 1); //Swmateio
                } else if (userDTO.getRole() == Constants.USER_TYPE.COMPANY_USER.getCode()) {
                    userService.createCompanyUser(updatedUser.getId());
                } else if (userDTO.getRole() == Constants.USER_TYPE.ADMIN.getCode()) {
                    userService.createCitizen(updatedUser.getId());
                }

                // ***** Oracle Identity Management / LDAP Functions *****
                Map<String, Object> createUserInput = new LinkedHashMap<String, Object>();
                createUserInput.put("p_surname", userDTO.getLastname());
                createUserInput.put("p_fathers_name", "");
                createUserInput.put("p_name", userDTO.getFirstname());
                createUserInput.put("p_email", userDTO.getEmail());
                createUserInput.put("p_telephonenumber", userDTO.getPhone());
                createUserInput.put("p_mobile", "");
                createUserInput.put("p_username", userDTO.getUsername());
                createUserInput.put("p_emp_number", "0");

                Integer funcCreateUserResult, funcGrantRoleResult, funcResetPassResult;
                String msg = "Προέκυψε κάποιο πρόβλημα κατά την εγγραφή. Δοκιμάστε ξανά.";
                Boolean rollback = false;

                try {
                    // call function to Add user to Oracle Identity Management / LDAP
                    funcCreateUserResult = (userStoredProcedures).secOimCallFunction(createUserInput, "PCG_SEC_OIM.SEC_OIM_CREATE_USER");
                    if (funcCreateUserResult != 0) {
                        rollback = true;
                        msg = "Προέκυψε κάποιο πρόβλημα με την εγγραφή του χρήστη.";
                    } else {
                        String p_role_code = "PortalEmployee";
                        if (userDTO.getRole().equals(Constants.USER_TYPE.EMPLOYEE.getCode()))
                            p_role_code = "PortalEmployee";
                        else if (userDTO.getRole().equals(Constants.USER_TYPE.DOCTOR.getCode()))
                            p_role_code = "PortalDoctor";
                        else if (userDTO.getRole().equals(Constants.USER_TYPE.TECHNICIAN.getCode()))
                            p_role_code = "PortalSafetyTechnician";
                        else if (userDTO.getRole().equals(Constants.USER_TYPE.COMPANY.getCode()))
                            p_role_code = "PortalEmployer";
                        else if (userDTO.getRole().equals(Constants.USER_TYPE.ASSOCIATION.getCode()))
                            p_role_code = "PortalAssociation";
                        else if (userDTO.getRole().equals(Constants.USER_TYPE.CITIZEN.getCode()))
                            p_role_code = "PortalCitizen";
                        else if (userDTO.getRole().equals(Constants.USER_TYPE.COMPANY_USER.getCode()))
                            p_role_code = "PortalEmployerUser";
                        else if (userDTO.getRole().equals(Constants.USER_TYPE.ADMIN.getCode()))
                            p_role_code = "PortalAdmin";
                        else if (userDTO.getRole().equals(Constants.USER_TYPE.UNKNOWN.getCode()))
                            p_role_code = "PortalOther";
                        Map<String, Object> grantRoleToUserInput = new LinkedHashMap<String, Object>();
                        grantRoleToUserInput.put("p_username", userDTO.getUsername());
                        grantRoleToUserInput.put("p_role_code", p_role_code);

                        // call function to Give the user a specific role to Oracle Identity Management / LDAP
                        funcGrantRoleResult = (userStoredProcedures).secOimCallFunction(grantRoleToUserInput, "PCG_SEC_OIM.SEC_OIM_GRANT_ROLE_TO_USER");
                        if (funcGrantRoleResult != 0) {
                            rollback = true;
                            msg = "Προέκυψε κάποιο πρόβλημα με την ανάθεση ρόλου στο χρήστη.";
                        } else {
                            Map<String, Object> resetPassInput = new LinkedHashMap<String, Object>();
                            resetPassInput.put("p_username", userDTO.getUsername());
                            resetPassInput.put("p_new_pass", password);

                            // call function to Reset password of a specific user in Oracle Identity Management / LDAP
                            funcResetPassResult = (userStoredProcedures).secOimCallFunction(resetPassInput, "PCG_SEC_OIM.SEC_OIM_RESET_PASS");
                            if (funcResetPassResult != 0) {
                                rollback = true;
                                msg = "Προέκυψε κάποιο πρόβλημα με το password του χρήστη.";
                            } else {
                                // All went OK!
                                try {
                                    mailService.createNewUser(updatedUser.getEmail(), updatedUser.getUsername(), password, updatedUser.getFirstname(), updatedUser.getLastname());
                                } catch (Exception ex) {
                                    model.addAttribute("profileSaveError", ex.getMessage());
                                    //return register2(httpServletRequest, model);
                                    model.addAttribute("newuser", userDTO);
                                    model.addAttribute("afm", afm);
                                    return "register2";
                                }
                            }
                        }
                    }
                } catch (Exception ex) {
                    System.out.println("msg : " + msg);
                    System.out.println("userDTO.getId() : " + userDTO.getId());
                    System.out.println("updatedUser.getId() : " + updatedUser.getId());
                    if (updatedUser.getRole() == Constants.USER_TYPE.CITIZEN.getCode()) {
                        citizenRepository.delete(updatedUser.getId());
                    } else if (updatedUser.getRole() == Constants.USER_TYPE.EMPLOYEE.getCode()) {
                        employeeRepository.delete(updatedUser.getId());
                    } else if (updatedUser.getRole() == Constants.USER_TYPE.COMPANY.getCode()) {
                        //userService.createCompany(updatedUser.getId(), afm, ame, 0);
                    } else if (updatedUser.getRole() == Constants.USER_TYPE.DOCTOR.getCode()) {
                        workplaceDoctorRepository.delete(updatedUser.getId());
                    } else if (updatedUser.getRole() == Constants.USER_TYPE.TECHNICIAN.getCode()) {
                        safetyTechnicianRepository.delete(updatedUser.getId());
                    } else if (updatedUser.getRole() == Constants.USER_TYPE.ASSOCIATION.getCode()) {
                        //userService.createCompany(updatedUser.getId(), afm, ame, 1); //Swmateio
                    } else if (updatedUser.getRole() == Constants.USER_TYPE.COMPANY_USER.getCode()) {
                        userRepository.delete(updatedUser.getId());
                    } else if (updatedUser.getRole() == Constants.USER_TYPE.ADMIN.getCode()) {
                        citizenRepository.delete(updatedUser.getId());
                    }
                    userRepository.delete(updatedUser.getId());

                    model.addAttribute("profileSaveError", msg);
                    //return register2(httpServletRequest, model);
                    model.addAttribute("newuser", userDTO);
                    model.addAttribute("afm", afm);
                    return "register2";
                }
                if (rollback == true) {
                    System.out.println("msg : " + msg);
                    System.out.println("userDTO.getId() : " + userDTO.getId());
                    System.out.println("updatedUser.getId() : " + updatedUser.getId());
                    if (updatedUser.getRole() == Constants.USER_TYPE.CITIZEN.getCode()) {
                        citizenRepository.delete(updatedUser.getId());
                    } else if (updatedUser.getRole() == Constants.USER_TYPE.EMPLOYEE.getCode()) {
                        employeeRepository.delete(updatedUser.getId());
                    } else if (updatedUser.getRole() == Constants.USER_TYPE.COMPANY.getCode()) {
                        //userService.createCompany(updatedUser.getId(), afm, ame, 0);
                    } else if (updatedUser.getRole() == Constants.USER_TYPE.DOCTOR.getCode()) {
                        workplaceDoctorRepository.delete(updatedUser.getId());
                    } else if (updatedUser.getRole() == Constants.USER_TYPE.TECHNICIAN.getCode()) {
                        safetyTechnicianRepository.delete(updatedUser.getId());
                    } else if (updatedUser.getRole() == Constants.USER_TYPE.ASSOCIATION.getCode()) {
                        //userService.createCompany(updatedUser.getId(), afm, ame, 1); //Swmateio
                    } else if (updatedUser.getRole() == Constants.USER_TYPE.COMPANY_USER.getCode()) {
                        userRepository.delete(updatedUser.getId());
                    } else if (updatedUser.getRole() == Constants.USER_TYPE.ADMIN.getCode()) {
                        citizenRepository.delete(updatedUser.getId());
                    }
                    userRepository.delete(updatedUser.getId());

                    model.addAttribute("profileSaveError", msg);
                    //return register2(httpServletRequest, model);
                    model.addAttribute("newuser", userDTO);
                    model.addAttribute("afm", afm);
                    return "register2";
                }
                // *************************


                // Update the user in the active Spring Security session
                /*final String passwordHash = userService.getPasswordHashForNewUser(adminDTO);
                final SpringUserDetails newUserDetails = new SpringUserDetails(adminDTO, adminDTO.getId(), passwordHash, null, null);
                final UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(newUserDetails, passwordHash, null);
                SecurityContextHolder.getContext().setAuthentication(newAuth);*/
            }
        }

        return accountSaved2(userDTO, model);
    }

    @RequestMapping(value = {"/register2/saveTa"}, method = RequestMethod.POST)
    @Nonnull
    public final String saveNewUserOAuthTa(@Nonnull HttpServletRequest httpServletRequest,
                                           @Nonnull
                                           @RequestParam(value = "password")
                                           final String password,

                                           @Nonnull
                                           @RequestParam(value = "reenterEmail")
                                           final String reenterEmail,

                                           @Nonnull
                                           @ModelAttribute("afm")
                                           final String afm,

                                           @Nonnull
                                           @ModelAttribute("newuser")
                                                   UserDTO userDTO,

                                           @Nonnull
                                           final BindingResult result,

                                           @Nonnull
                                           final Model model

    ) throws Exception {
        userValidator.validate(userDTO, result);
        if (result.hasErrors()) {
            model.addAttribute("profileSaveError", "Υπάρχουν λάθη!");

            userDTO.setPassword("");
            model.addAttribute("newuser", userDTO);
            model.addAttribute("afm", afm);
            return "registerTa";
        } else if (reenterEmail != null && !userDTO.getEmail().equals(reenterEmail)) {
            model.addAttribute("profileSaveError", "Τα πεδία 'Email' και 'Επιβεβαίωση Email' πρέπει να συμφωνούν.");

            userDTO.setPassword("");
            model.addAttribute("newuser", userDTO);
            model.addAttribute("afm", afm);
            return "registerTa";

        } else if (password == null || password.isEmpty()) {
            model.addAttribute("profileSaveError", "Παρακαλώ εισάγετε το τρέχον password για να μπορέσετε να σώσετε τις αλλαγές.");

            userDTO.setPassword("");
            model.addAttribute("newuser", userDTO);
            model.addAttribute("afm", afm);
            return "registerTa";

        } else if (password.length() < 6) {
            model.addAttribute("profileSaveError", "Παρακαλώ το password πρέπει να αποτελείται από τουλάχιστον 6 χαρακτήρες.");

            userDTO.setPassword("");
            model.addAttribute("newuser", userDTO);
            model.addAttribute("afm", afm);
            return "registerTa";

        } else if (!password.matches(".*[a-zA-Z]+.*")) {
            model.addAttribute("profileSaveError", "Παρακαλώ το password πρέπει να περιέχει τουλάχιστον 1 αλφαβητικό (latin) χαρακτήρα");

            userDTO.setPassword("");
            model.addAttribute("newuser", userDTO);
            model.addAttribute("afm", afm);
            return "registerTa";

        } else if (!password.matches(".*\\d+.*")) {
            model.addAttribute("profileSaveError", "Παρακαλώ το password πρέπει να περιέχει τουλάχιστον 1 αριθμητικό χαρακτήρα.");

            userDTO.setPassword("");
            model.addAttribute("newuser", userDTO);
            model.addAttribute("afm", afm);
            return "registerTa";

        } else if (password.toLowerCase().contains(userDTO.getUsername().toLowerCase()) || password.toLowerCase().contains(userDTO.getLastname().toLowerCase())) {
            model.addAttribute("profileSaveError", "Παρακαλώ το password πρέπει να μην περιέχει το username, το επώνυμο ή το όνομα.");

            userDTO.setPassword("");
            model.addAttribute("newuser", userDTO);
            model.addAttribute("afm", afm);
            return "registerTa";

        } else if (!userDTO.getUsername().matches("[a-zA-Z0-9-@#!*$%^&.'_={}()]*")) {
            model.addAttribute("profileSaveError", "Παρακαλώ το username δεν επιτρέπεται να περιέχει ελληνικούς χαρακτήρες, κενά ή τους ειδικούς χαρακτήρες + , ; / < >");

            userDTO.setPassword("");
            model.addAttribute("newuser", userDTO);
            model.addAttribute("afm", afm);
            return "registerTa";

        } else if (userDTO.getUsername().length() < 5) {
            model.addAttribute("profileSaveError", "Παρακαλώ εισάγετε κάποιο username.");

            userDTO.setPassword("");
            model.addAttribute("newuser", userDTO);
            model.addAttribute("afm", afm);
            return "registerTa";

        } else {
            if (!password.isEmpty()) {
                if (!(userDTO.getUsername().substring(0, 4).equals("ptl-"))) {
                    userDTO.setUsername("ptl-" + userDTO.getUsername());
                }

                Integer callId = (Integer) httpServletRequest.getSession().getAttribute("callId");
                SpRgGgdeMhtrwoUsers mhtrwoUser = spRgGgdeMhtrwoUsersRestRepo.findBySeqId(callId);
                userDTO.setAddress(mhtrwoUser.getRecidenceAddress() + " " + mhtrwoUser.getRecidenceAddressNo());
                userDTO.setAddrTk(mhtrwoUser.getRecidenceZipCode());
                String[] nameDetails = mhtrwoUser.getOnomasia().split(",");
                if (nameDetails != null && nameDetails.length > 1) {
                    userDTO.setFirstname(nameDetails[2]);
                } else {
                    userDTO.setFirstname("");
                }
                //userDTO.setFirstname(nameDetails[2]);
                if (nameDetails[1] != "")
                    userDTO.setLastname(nameDetails[0] + " " + nameDetails[1]);
                else
                    userDTO.setLastname(nameDetails[0]);
                userDTO.setPhone(mhtrwoUser.getIndPhone());

                try {
                    userService.createUser(userDTO);
                } catch (UsernameExistsException ex) {
                    model.addAttribute("profileSaveError", ex.getMessage());
                    return registerTa(httpServletRequest, model, "technician");
                }
                UserDTO updatedUser = userService.findUserByUsername(userDTO.getUsername());
                if (userDTO.getRole() == Constants.USER_TYPE.CITIZEN.getCode()) {
                    userService.createCitizen(updatedUser.getId());
                } else if (userDTO.getRole() == Constants.USER_TYPE.EMPLOYEE.getCode()) {
                    userService.createEmployee(updatedUser.getId());
                } else if (userDTO.getRole() == Constants.USER_TYPE.COMPANY.getCode()) {
                    //userService.createCompany(updatedUser.getId(), afm, ameIka, 0, updatedUser.getLastname());
                } else if (userDTO.getRole() == Constants.USER_TYPE.DOCTOR.getCode()) {
                    userService.createWorkplaceDoctor(updatedUser.getId(), afm, mhtrwoUser.getCardNo(), mhtrwoUser.getCardKind());
                } else if (userDTO.getRole() == Constants.USER_TYPE.TECHNICIAN.getCode()) {
                    userService.createSafetyTechnician(updatedUser.getId(), afm, mhtrwoUser.getCardNo(), mhtrwoUser.getCardKind());
                } else if (userDTO.getRole() == Constants.USER_TYPE.ASSOCIATION.getCode()) {
                    //userService.createCompany(updatedUser.getId(), afm, ameIka, 1, updatedUser.getLastname()); //Swmateio
                } else if (userDTO.getRole() == Constants.USER_TYPE.COMPANY_USER.getCode()) {
                    userService.createCompanyUser(updatedUser.getId());
                } else if (userDTO.getRole() == Constants.USER_TYPE.ADMIN.getCode()) {
                    userService.createCitizen(updatedUser.getId());
                }


                // ***** Oracle Identity Management / LDAP Functions *****
                Map<String, Object> createUserInput = new LinkedHashMap<String, Object>();
                createUserInput.put("p_surname", userDTO.getLastname());
                createUserInput.put("p_fathers_name", "");
                createUserInput.put("p_name", userDTO.getFirstname());
                createUserInput.put("p_email", userDTO.getEmail());
                createUserInput.put("p_telephonenumber", userDTO.getPhone());
                createUserInput.put("p_mobile", "");
                createUserInput.put("p_username", userDTO.getUsername());
                createUserInput.put("p_emp_number", "0");

                Integer funcCreateUserResult = -1;
                Integer funcGrantRoleResult = -1;
                Integer funcResetPassResult = -1;
                String msg = "Προέκυψε κάποιο πρόβλημα κατά την εγγραφή. Δοκιμάστε ξανά.";
                Boolean rollback = false;

                try {
                    // call function to Add user to Oracle Identity Management / LDAP
                    funcCreateUserResult = (userStoredProcedures).secOimCallFunction(createUserInput, "PCG_SEC_OIM.SEC_OIM_CREATE_USER");
                    if (funcCreateUserResult != 0) {
                        rollback = true;
                        msg = "Προέκυψε κάποιο πρόβλημα με την εγγραφή του χρήστη.";
                    } else {
                        String p_role_code = "PortalEmployee";
                        if (userDTO.getRole().equals(Constants.USER_TYPE.EMPLOYEE.getCode()))
                            p_role_code = "PortalEmployee";
                        else if (userDTO.getRole().equals(Constants.USER_TYPE.DOCTOR.getCode()))
                            p_role_code = "PortalDoctor";
                        else if (userDTO.getRole().equals(Constants.USER_TYPE.TECHNICIAN.getCode()))
                            p_role_code = "PortalSafetyTechnician";
                        else if (userDTO.getRole().equals(Constants.USER_TYPE.COMPANY.getCode()))
                            p_role_code = "PortalEmployer";
                        else if (userDTO.getRole().equals(Constants.USER_TYPE.ASSOCIATION.getCode()))
                            p_role_code = "PortalAssociation";
                        else if (userDTO.getRole().equals(Constants.USER_TYPE.CITIZEN.getCode()))
                            p_role_code = "PortalCitizen";
                        else if (userDTO.getRole().equals(Constants.USER_TYPE.COMPANY_USER.getCode()))
                            p_role_code = "PortalEmployerUser";
                        else if (userDTO.getRole().equals(Constants.USER_TYPE.ADMIN.getCode()))
                            p_role_code = "PortalAdmin";
                        else if (userDTO.getRole().equals(Constants.USER_TYPE.UNKNOWN.getCode()))
                            p_role_code = "PortalOther";
                        Map<String, Object> grantRoleToUserInput = new LinkedHashMap<String, Object>();
                        grantRoleToUserInput.put("p_username", userDTO.getUsername());
                        grantRoleToUserInput.put("p_role_code", p_role_code);

                        // call function to Give the user a specific role to Oracle Identity Management / LDAP
                        funcGrantRoleResult = (userStoredProcedures).secOimCallFunction(grantRoleToUserInput, "PCG_SEC_OIM.SEC_OIM_GRANT_ROLE_TO_USER");
                        if (funcGrantRoleResult != 0) {
                            rollback = true;
                            msg = "Προέκυψε κάποιο πρόβλημα με την ανάθεση ρόλου στο χρήστη.";
                        } else {
                            Map<String, Object> resetPassInput = new LinkedHashMap<String, Object>();
                            resetPassInput.put("p_username", userDTO.getUsername());
                            resetPassInput.put("p_new_pass", password);
                            System.out.println("p_new_pass " + password);

                            // call function to Reset password of a specific user in Oracle Identity Management / LDAP
                            funcResetPassResult = (userStoredProcedures).secOimCallFunction(resetPassInput, "PCG_SEC_OIM.SEC_OIM_RESET_PASS");
                            if (funcResetPassResult != 0) {
                                rollback = true;
                                msg = "Προέκυψε κάποιο πρόβλημα με το password του χρήστη.";
                            } else {
                                // All went OK!
                                try {
                                    mailService.createNewUser(updatedUser.getEmail(), updatedUser.getUsername(), password, updatedUser.getFirstname(), updatedUser.getLastname());
                                } catch (Exception ex) {
                                    model.addAttribute("profileSaveError", ex.getMessage());
                                    model.addAttribute("newuser", userDTO);
                                    model.addAttribute("afm", afm);
                                    return "registerTa";
                                    //return register3(httpServletRequest, model);
                                }
                            }
                        }
                    }
                } catch (Exception ex) {
                    System.out.println("msg : " + msg);
                    System.out.println("userDTO.getId() : " + userDTO.getId());
                    System.out.println("updatedUser.getId() : " + updatedUser.getId());
                    if (updatedUser.getRole() == Constants.USER_TYPE.CITIZEN.getCode()) {
                        citizenRepository.delete(updatedUser.getId());
                    } else if (updatedUser.getRole() == Constants.USER_TYPE.EMPLOYEE.getCode()) {
                        employeeRepository.delete(updatedUser.getId());
                    } else if (updatedUser.getRole() == Constants.USER_TYPE.COMPANY.getCode()) {
                        companyRepository.delete(updatedUser.getId());
                    } else if (updatedUser.getRole() == Constants.USER_TYPE.DOCTOR.getCode()) {
                        workplaceDoctorRepository.delete(updatedUser.getId());
                    } else if (updatedUser.getRole() == Constants.USER_TYPE.TECHNICIAN.getCode()) {
                        safetyTechnicianRepository.delete(updatedUser.getId());
                    } else if (updatedUser.getRole() == Constants.USER_TYPE.ASSOCIATION.getCode()) {
                        companyRepository.delete(updatedUser.getId());
                    } else if (updatedUser.getRole() == Constants.USER_TYPE.COMPANY_USER.getCode()) {
                        userRepository.delete(updatedUser.getId());
                    } else if (updatedUser.getRole() == Constants.USER_TYPE.ADMIN.getCode()) {
                        citizenRepository.delete(updatedUser.getId());
                    }
                    userRepository.delete(updatedUser.getId());

                    model.addAttribute("profileSaveError", msg);
                    model.addAttribute("newuser", userDTO);
                    model.addAttribute("afm", afm);
                    return "registerTa";
                }
                if (rollback == true) {
                    System.out.println("msg : " + msg);
                    System.out.println("userDTO.getId() : " + userDTO.getId());
                    System.out.println("updatedUser.getId() : " + updatedUser.getId());
                    if (updatedUser.getRole() == Constants.USER_TYPE.CITIZEN.getCode()) {
                        citizenRepository.delete(updatedUser.getId());
                    } else if (updatedUser.getRole() == Constants.USER_TYPE.EMPLOYEE.getCode()) {
                        employeeRepository.delete(updatedUser.getId());
                    } else if (updatedUser.getRole() == Constants.USER_TYPE.COMPANY.getCode()) {
                        companyRepository.delete(updatedUser.getId());
                    } else if (updatedUser.getRole() == Constants.USER_TYPE.DOCTOR.getCode()) {
                        workplaceDoctorRepository.delete(updatedUser.getId());
                    } else if (updatedUser.getRole() == Constants.USER_TYPE.TECHNICIAN.getCode()) {
                        safetyTechnicianRepository.delete(updatedUser.getId());
                    } else if (updatedUser.getRole() == Constants.USER_TYPE.ASSOCIATION.getCode()) {
                        companyRepository.delete(updatedUser.getId());
                    } else if (updatedUser.getRole() == Constants.USER_TYPE.COMPANY_USER.getCode()) {
                        userRepository.delete(updatedUser.getId());
                    } else if (updatedUser.getRole() == Constants.USER_TYPE.ADMIN.getCode()) {
                        citizenRepository.delete(updatedUser.getId());
                    }
                    userRepository.delete(updatedUser.getId());

                    model.addAttribute("profileSaveError", msg);
                    model.addAttribute("newuser", userDTO);
                    model.addAttribute("afm", afm);
                    return "registerTa";
                    //return register3(httpServletRequest, model);
                }
                // *************************
            }
        }

        return accountSaved2(userDTO, model);
    }

    @RequestMapping(value = {"/register2/saveIe"}, method = RequestMethod.POST)
    @Nonnull
    public final String saveNewUserOAuthIe(@Nonnull HttpServletRequest httpServletRequest,
                                           @Nonnull
                                           @RequestParam(value = "password")
                                           final String password,

                                           @Nonnull
                                           @RequestParam(value = "reenterEmail")
                                           final String reenterEmail,

                                           @Nonnull
                                           @ModelAttribute("afm")
                                           final String afm,

                                           @Nonnull
                                           @ModelAttribute("newuser")
                                                   UserDTO userDTO,

                                           @Nonnull
                                           final BindingResult result,

                                           @Nonnull
                                           final Model model

    ) throws Exception {
        userValidator.validate(userDTO, result);
        if (result.hasErrors()) {
            model.addAttribute("profileSaveError", "Υπάρχουν λάθη!");

            userDTO.setPassword("");
            model.addAttribute("newuser", userDTO);
            model.addAttribute("afm", afm);
            return "registerIe";
        } else if (reenterEmail != null && !userDTO.getEmail().equals(reenterEmail)) {
            model.addAttribute("profileSaveError", "Τα πεδία 'Email' και 'Επιβεβαίωση Email' πρέπει να συμφωνούν.");

            userDTO.setPassword("");
            model.addAttribute("newuser", userDTO);
            model.addAttribute("afm", afm);
            return "registerIe";

        } else if (password == null || password.isEmpty()) {
            model.addAttribute("profileSaveError", "Παρακαλώ εισάγετε το τρέχον password για να μπορέσετε να σώσετε τις αλλαγές.");

            userDTO.setPassword("");
            model.addAttribute("newuser", userDTO);
            model.addAttribute("afm", afm);
            return "registerIe";

        } else if (password.length() < 6) {
            model.addAttribute("profileSaveError", "Παρακαλώ το password πρέπει να αποτελείται από τουλάχιστον 6 χαρακτήρες.");

            userDTO.setPassword("");
            model.addAttribute("newuser", userDTO);
            model.addAttribute("afm", afm);
            return "registerIe";

        } else if (!password.matches(".*[a-zA-Z]+.*")) {
            model.addAttribute("profileSaveError", "Παρακαλώ το password πρέπει να περιέχει τουλάχιστον 1 αλφαβητικό (latin) χαρακτήρα");

            userDTO.setPassword("");
            model.addAttribute("newuser", userDTO);
            model.addAttribute("afm", afm);
            return "registerIe";

        } else if (!password.matches(".*\\d+.*")) {
            model.addAttribute("profileSaveError", "Παρακαλώ το password πρέπει να περιέχει τουλάχιστον 1 αριθμητικό χαρακτήρα.");

            userDTO.setPassword("");
            model.addAttribute("newuser", userDTO);
            model.addAttribute("afm", afm);
            return "registerIe";

        } else if (password.toLowerCase().contains(userDTO.getUsername().toLowerCase()) || password.toLowerCase().contains(userDTO.getLastname().toLowerCase())) {
            model.addAttribute("profileSaveError", "Παρακαλώ το password πρέπει να μην περιέχει το username, το επώνυμο ή το όνομα.");

            userDTO.setPassword("");
            model.addAttribute("newuser", userDTO);
            model.addAttribute("afm", afm);
            return "registerIe";

        } else if (!userDTO.getUsername().matches("[a-zA-Z0-9-@#!*$%^&.'_={}()]*")) {
            model.addAttribute("profileSaveError", "Παρακαλώ το username δεν επιτρέπεται να περιέχει ελληνικούς χαρακτήρες, κενά ή τους ειδικούς χαρακτήρες + , ; / < >");

            userDTO.setPassword("");
            model.addAttribute("newuser", userDTO);
            model.addAttribute("afm", afm);
            return "registerIe";

        } else if (userDTO.getUsername().length() < 5) {
            model.addAttribute("profileSaveError", "Παρακαλώ εισάγετε κάποιο username.");

            userDTO.setPassword("");
            model.addAttribute("newuser", userDTO);
            model.addAttribute("afm", afm);
            return "registerIe";

        } else {
            if (!password.isEmpty()) {
                if (!(userDTO.getUsername().substring(0, 4).equals("ptl-"))) {
                    userDTO.setUsername("ptl-" + userDTO.getUsername());
                }

                Integer callId = (Integer) httpServletRequest.getSession().getAttribute("callId");
                SpRgGgdeMhtrwoUsers mhtrwoUser = spRgGgdeMhtrwoUsersRestRepo.findBySeqId(callId);
                userDTO.setAddress(mhtrwoUser.getRecidenceAddress() + " " + mhtrwoUser.getRecidenceAddressNo());
                userDTO.setAddrTk(mhtrwoUser.getRecidenceZipCode());
                String[] nameDetails = mhtrwoUser.getOnomasia().split(",");
                if (nameDetails != null && nameDetails.length > 1) {
                    userDTO.setFirstname(nameDetails[2]);
                } else {
                    userDTO.setFirstname("");
                }
                //userDTO.setFirstname(nameDetails[2]);
                if (nameDetails[1] != "")
                    userDTO.setLastname(nameDetails[0] + " " + nameDetails[1]);
                else
                    userDTO.setLastname(nameDetails[0]);
                userDTO.setPhone(mhtrwoUser.getIndPhone());

                try {
                    userService.createUser(userDTO);
                } catch (UsernameExistsException ex) {
                    model.addAttribute("profileSaveError", ex.getMessage());
                    return registerTa(httpServletRequest, model, "technician");
                }
                UserDTO updatedUser = userService.findUserByUsername(userDTO.getUsername());
                if (userDTO.getRole() == Constants.USER_TYPE.CITIZEN.getCode()) {
                    userService.createCitizen(updatedUser.getId());
                } else if (userDTO.getRole() == Constants.USER_TYPE.EMPLOYEE.getCode()) {
                    userService.createEmployee(updatedUser.getId());
                } else if (userDTO.getRole() == Constants.USER_TYPE.COMPANY.getCode()) {
                    //userService.createCompany(updatedUser.getId(), afm, ameIka, 0, updatedUser.getLastname());
                } else if (userDTO.getRole() == Constants.USER_TYPE.DOCTOR.getCode()) {
                    userService.createWorkplaceDoctor(updatedUser.getId(), afm, mhtrwoUser.getCardNo(), mhtrwoUser.getCardKind());
                } else if (userDTO.getRole() == Constants.USER_TYPE.TECHNICIAN.getCode()) {
                    userService.createSafetyTechnician(updatedUser.getId(), afm, mhtrwoUser.getCardNo(), mhtrwoUser.getCardKind());
                } else if (userDTO.getRole() == Constants.USER_TYPE.ASSOCIATION.getCode()) {
                    //userService.createCompany(updatedUser.getId(), afm, ameIka, 1, updatedUser.getLastname()); //Swmateio
                } else if (userDTO.getRole() == Constants.USER_TYPE.COMPANY_USER.getCode()) {
                    userService.createCompanyUser(updatedUser.getId());
                } else if (userDTO.getRole() == Constants.USER_TYPE.ADMIN.getCode()) {
                    userService.createCitizen(updatedUser.getId());
                }


                // ***** Oracle Identity Management / LDAP Functions *****
                Map<String, Object> createUserInput = new LinkedHashMap<String, Object>();
                createUserInput.put("p_surname", userDTO.getLastname());
                createUserInput.put("p_fathers_name", "");
                createUserInput.put("p_name", userDTO.getFirstname());
                createUserInput.put("p_email", userDTO.getEmail());
                createUserInput.put("p_telephonenumber", userDTO.getPhone());
                createUserInput.put("p_mobile", "");
                createUserInput.put("p_username", userDTO.getUsername());
                createUserInput.put("p_emp_number", "0");

                Integer funcCreateUserResult = -1;
                Integer funcGrantRoleResult = -1;
                Integer funcResetPassResult = -1;
                String msg = "Προέκυψε κάποιο πρόβλημα κατά την εγγραφή. Δοκιμάστε ξανά.";
                Boolean rollback = false;

                try {
                    // call function to Add user to Oracle Identity Management / LDAP
                    funcCreateUserResult = (userStoredProcedures).secOimCallFunction(createUserInput, "PCG_SEC_OIM.SEC_OIM_CREATE_USER");
                    if (funcCreateUserResult != 0) {
                        rollback = true;
                        msg = "Προέκυψε κάποιο πρόβλημα με την εγγραφή του χρήστη.";
                    } else {
                        String p_role_code = "PortalEmployee";
                        if (userDTO.getRole().equals(Constants.USER_TYPE.EMPLOYEE.getCode()))
                            p_role_code = "PortalEmployee";
                        else if (userDTO.getRole().equals(Constants.USER_TYPE.DOCTOR.getCode()))
                            p_role_code = "PortalDoctor";
                        else if (userDTO.getRole().equals(Constants.USER_TYPE.TECHNICIAN.getCode()))
                            p_role_code = "PortalSafetyTechnician";
                        else if (userDTO.getRole().equals(Constants.USER_TYPE.COMPANY.getCode()))
                            p_role_code = "PortalEmployer";
                        else if (userDTO.getRole().equals(Constants.USER_TYPE.ASSOCIATION.getCode()))
                            p_role_code = "PortalAssociation";
                        else if (userDTO.getRole().equals(Constants.USER_TYPE.CITIZEN.getCode()))
                            p_role_code = "PortalCitizen";
                        else if (userDTO.getRole().equals(Constants.USER_TYPE.COMPANY_USER.getCode()))
                            p_role_code = "PortalEmployerUser";
                        else if (userDTO.getRole().equals(Constants.USER_TYPE.ADMIN.getCode()))
                            p_role_code = "PortalAdmin";
                        else if (userDTO.getRole().equals(Constants.USER_TYPE.UNKNOWN.getCode()))
                            p_role_code = "PortalOther";
                        Map<String, Object> grantRoleToUserInput = new LinkedHashMap<String, Object>();
                        grantRoleToUserInput.put("p_username", userDTO.getUsername());
                        grantRoleToUserInput.put("p_role_code", p_role_code);

                        // call function to Give the user a specific role to Oracle Identity Management / LDAP
                        funcGrantRoleResult = (userStoredProcedures).secOimCallFunction(grantRoleToUserInput, "PCG_SEC_OIM.SEC_OIM_GRANT_ROLE_TO_USER");
                        if (funcGrantRoleResult != 0) {
                            rollback = true;
                            msg = "Προέκυψε κάποιο πρόβλημα με την ανάθεση ρόλου στο χρήστη.";
                        } else {
                            Map<String, Object> resetPassInput = new LinkedHashMap<String, Object>();
                            resetPassInput.put("p_username", userDTO.getUsername());
                            resetPassInput.put("p_new_pass", password);
                            System.out.println("p_new_pass " + password);

                            // call function to Reset password of a specific user in Oracle Identity Management / LDAP
                            funcResetPassResult = (userStoredProcedures).secOimCallFunction(resetPassInput, "PCG_SEC_OIM.SEC_OIM_RESET_PASS");
                            if (funcResetPassResult != 0) {
                                rollback = true;
                                msg = "Προέκυψε κάποιο πρόβλημα με το password του χρήστη.";
                            } else {
                                // All went OK!
                                try {
                                    mailService.createNewUser(updatedUser.getEmail(), updatedUser.getUsername(), password, updatedUser.getFirstname(), updatedUser.getLastname());
                                } catch (Exception ex) {
                                    model.addAttribute("profileSaveError", ex.getMessage());
                                    model.addAttribute("newuser", userDTO);
                                    model.addAttribute("afm", afm);
                                    return "registerIe";
                                    //return register3(httpServletRequest, model);
                                }
                            }
                        }
                    }
                } catch (Exception ex) {
                    System.out.println("msg : " + msg);
                    System.out.println("userDTO.getId() : " + userDTO.getId());
                    System.out.println("updatedUser.getId() : " + updatedUser.getId());
                    if (updatedUser.getRole() == Constants.USER_TYPE.CITIZEN.getCode()) {
                        citizenRepository.delete(updatedUser.getId());
                    } else if (updatedUser.getRole() == Constants.USER_TYPE.EMPLOYEE.getCode()) {
                        employeeRepository.delete(updatedUser.getId());
                    } else if (updatedUser.getRole() == Constants.USER_TYPE.COMPANY.getCode()) {
                        companyRepository.delete(updatedUser.getId());
                    } else if (updatedUser.getRole() == Constants.USER_TYPE.DOCTOR.getCode()) {
                        workplaceDoctorRepository.delete(updatedUser.getId());
                    } else if (updatedUser.getRole() == Constants.USER_TYPE.TECHNICIAN.getCode()) {
                        safetyTechnicianRepository.delete(updatedUser.getId());
                    } else if (updatedUser.getRole() == Constants.USER_TYPE.ASSOCIATION.getCode()) {
                        companyRepository.delete(updatedUser.getId());
                    } else if (updatedUser.getRole() == Constants.USER_TYPE.COMPANY_USER.getCode()) {
                        userRepository.delete(updatedUser.getId());
                    } else if (updatedUser.getRole() == Constants.USER_TYPE.ADMIN.getCode()) {
                        citizenRepository.delete(updatedUser.getId());
                    }
                    userRepository.delete(updatedUser.getId());

                    model.addAttribute("profileSaveError", msg);
                    model.addAttribute("newuser", userDTO);
                    model.addAttribute("afm", afm);
                    return "registerIe";
                }
                if (rollback == true) {
                    System.out.println("msg : " + msg);
                    System.out.println("userDTO.getId() : " + userDTO.getId());
                    System.out.println("updatedUser.getId() : " + updatedUser.getId());
                    if (updatedUser.getRole() == Constants.USER_TYPE.CITIZEN.getCode()) {
                        citizenRepository.delete(updatedUser.getId());
                    } else if (updatedUser.getRole() == Constants.USER_TYPE.EMPLOYEE.getCode()) {
                        employeeRepository.delete(updatedUser.getId());
                    } else if (updatedUser.getRole() == Constants.USER_TYPE.COMPANY.getCode()) {
                        companyRepository.delete(updatedUser.getId());
                    } else if (updatedUser.getRole() == Constants.USER_TYPE.DOCTOR.getCode()) {
                        workplaceDoctorRepository.delete(updatedUser.getId());
                    } else if (updatedUser.getRole() == Constants.USER_TYPE.TECHNICIAN.getCode()) {
                        safetyTechnicianRepository.delete(updatedUser.getId());
                    } else if (updatedUser.getRole() == Constants.USER_TYPE.ASSOCIATION.getCode()) {
                        companyRepository.delete(updatedUser.getId());
                    } else if (updatedUser.getRole() == Constants.USER_TYPE.COMPANY_USER.getCode()) {
                        userRepository.delete(updatedUser.getId());
                    } else if (updatedUser.getRole() == Constants.USER_TYPE.ADMIN.getCode()) {
                        citizenRepository.delete(updatedUser.getId());
                    }
                    userRepository.delete(updatedUser.getId());

                    model.addAttribute("profileSaveError", msg);
                    model.addAttribute("newuser", userDTO);
                    model.addAttribute("afm", afm);
                    return "registerIe";
                    //return register3(httpServletRequest, model);
                }
                // *************************
            }
        }

        return accountSaved2(userDTO, model);
    }


    @RequestMapping(value = {"/register/save"}, method = RequestMethod.POST)
    @Nonnull
    public final String saveNewUser(
            @Nonnull
            @RequestParam(value = "password")
            final String password,

            @Nonnull
            @ModelAttribute("newuser")
                    UserDTO userDTO,

            @Nonnull
            @RequestParam(value = "afm")
            final String afm,

            @Nonnull
            @RequestParam(value = "ame")
            final String ame,

            @Nonnull
            final BindingResult result,

            @Nonnull
            final Model model

    ) throws Exception {
        SpringUserDetails currentUser = CurrentUserDetailsService.getCurrentUser();
        UserDTO adminDTO = currentUser.getUserDTO();
        userValidator.validate(userDTO, result);
        if (result.hasErrors()) {
            model.addAttribute("profileSaveError", "Υπάρχουν λάθη!");

            final UserDTO user = new UserDTO();
            user.setUsername("");
            user.setEmail("");
            user.setFirstname("");
            user.setLastname("");
            model.addAttribute("user", adminDTO);
            model.addAttribute("newuser", user);
            return "register";
        } else if (password == null || password.isEmpty()) {
            model.addAttribute("profileSaveError", "Παρακαλώ εισάγετε το τρέχον password για να μπορέσετε να σώσετε τις αλλαγές.");
            final UserDTO user = new UserDTO();
            user.setUsername("");
            user.setEmail("");
            user.setFirstname("");
            user.setLastname("");
            model.addAttribute("user", adminDTO);
            model.addAttribute("newuser", user);
            return "register";

        } else if (password.length() < 6) {
            model.addAttribute("profileSaveError", "Παρακαλώ το password πρέπει να αποτελείται από τουλάχιστον 6 χαρακτήρες.");
            final UserDTO user = new UserDTO();
            user.setUsername("");
            user.setEmail("");
            user.setFirstname("");
            user.setLastname("");
            model.addAttribute("user", adminDTO);
            model.addAttribute("newuser", user);
            return "register";

        } else if (!password.matches(".*[a-zA-Z]+.*")) {
            model.addAttribute("profileSaveError", "Παρακαλώ το password πρέπει να περιέχει τουλάχιστον 1 αλφαβητικό (latin) χαρακτήρα.");
            final UserDTO user = new UserDTO();
            user.setUsername("");
            user.setEmail("");
            user.setFirstname("");
            user.setLastname("");
            model.addAttribute("user", adminDTO);
            model.addAttribute("newuser", user);
            return "register";

        } else if (!password.matches(".*\\d+.*")) {
            model.addAttribute("profileSaveError", "Παρακαλώ το password πρέπει να περιέχει τουλάχιστον 1 αριθμητικό χαρακτήρα");
            final UserDTO user = new UserDTO();
            user.setUsername("");
            user.setEmail("");
            user.setFirstname("");
            user.setLastname("");
            model.addAttribute("user", adminDTO);
            model.addAttribute("newuser", user);
            return "register";

        } else if (password.toLowerCase().contains(userDTO.getUsername().toLowerCase()) || password.toLowerCase().contains(userDTO.getLastname().toLowerCase())) {
            model.addAttribute("profileSaveError", "Παρακαλώ το password πρέπει να μην περιέχει το username, το επώνυμο ή το όνομα.");
            final UserDTO user = new UserDTO();
            user.setUsername("");
            user.setEmail("");
            user.setFirstname("");
            user.setLastname("");
            model.addAttribute("user", adminDTO);
            model.addAttribute("newuser", user);
            return "register";

        } else if (!userDTO.getUsername().matches("[a-zA-Z0-9-@#!*$%^&.'_={}()]*")) {
            model.addAttribute("profileSaveError", "Παρακαλώ το username δεν επιτρέπεται να περιέχει ελληνικούς χαρακτήρες, κενά ή τους ειδικούς χαρακτήρες + , ; / < >");
            final UserDTO user = new UserDTO();
            user.setUsername("");
            user.setEmail("");
            user.setFirstname("");
            user.setLastname("");
            model.addAttribute("user", adminDTO);
            model.addAttribute("newuser", user);
            return "register";

        } else if (userDTO.getUsername().length() < 5) {
            model.addAttribute("profileSaveError", "Παρακαλώ εισάγετε κάποιο username.");
            final UserDTO user = new UserDTO();
            user.setUsername("");
            user.setEmail("");
            user.setFirstname("");
            user.setLastname("");
            model.addAttribute("user", adminDTO);
            model.addAttribute("newuser", user);
            return "register";

        } else {
            if (!password.isEmpty()) {
                try {
                    userService.createUser(userDTO);
                } catch (UsernameExistsException ex) {
                    model.addAttribute("profileSaveError", ex.getMessage());
                    return register(model);
                }
                UserDTO updatedUser = userService.findUserByUsername(userDTO.getUsername());
                if (userDTO.getRole() == Constants.USER_TYPE.CITIZEN.getCode()) {
                    userService.createCitizen(updatedUser.getId());
                } else if (userDTO.getRole() == Constants.USER_TYPE.EMPLOYEE.getCode()) {
                    userService.createEmployee(updatedUser.getId());
                } else if (userDTO.getRole() == Constants.USER_TYPE.COMPANY.getCode()) {
                    userService.createCompany(updatedUser.getId(), afm, ame, 0, updatedUser.getLastname());
                } else if (userDTO.getRole() == Constants.USER_TYPE.DOCTOR.getCode()) {
                    userService.createWorkplaceDoctor(updatedUser.getId(), afm, "0", "1");
                } else if (userDTO.getRole() == Constants.USER_TYPE.TECHNICIAN.getCode()) {
                    userService.createSafetyTechnician(updatedUser.getId(), afm, "0", "1");
                } else if (userDTO.getRole() == Constants.USER_TYPE.ASSOCIATION.getCode()) {
                    userService.createCompany(updatedUser.getId(), afm, ame, 1, updatedUser.getLastname()); //Swmateio
                } else if (userDTO.getRole() == Constants.USER_TYPE.COMPANY_USER.getCode()) {
                    userService.createCompanyUser(updatedUser.getId());
                } else if (userDTO.getRole() == Constants.USER_TYPE.ADMIN.getCode()) {
                    userService.createCitizen(updatedUser.getId());
                }


                // ***** Oracle Identity Management / LDAP Functions *****
                Map<String, Object> createUserInput = new LinkedHashMap<String, Object>();
                createUserInput.put("p_surname", userDTO.getLastname());
                createUserInput.put("p_fathers_name", "");
                createUserInput.put("p_name", userDTO.getFirstname());
                createUserInput.put("p_email", userDTO.getEmail());
                createUserInput.put("p_telephonenumber", userDTO.getPhone());
                createUserInput.put("p_mobile", "");
                createUserInput.put("p_username", userDTO.getUsername());
                createUserInput.put("p_emp_number", "0");

                Integer funcCreateUserResult, funcGrantRoleResult, funcResetPassResult;
                String msg = "Προέκυψε κάποιο πρόβλημα κατά την εγγραφή. Δοκιμάστε ξανά.";
                Boolean rollback = false;

                try {
                    // call function to Add user to Oracle Identity Management / LDAP
                    funcCreateUserResult = (userStoredProcedures).secOimCallFunction(createUserInput, "PCG_SEC_OIM.SEC_OIM_CREATE_USER");
                    if (funcCreateUserResult != 0) {
                        rollback = true;
                        msg = "Προέκυψε κάποιο πρόβλημα με την εγγραφή του χρήστη.";
                    } else {
                        String p_role_code = "PortalEmployee";
                        if (userDTO.getRole().equals(Constants.USER_TYPE.EMPLOYEE.getCode()))
                            p_role_code = "PortalEmployee";
                        else if (userDTO.getRole().equals(Constants.USER_TYPE.DOCTOR.getCode()))
                            p_role_code = "PortalDoctor";
                        else if (userDTO.getRole().equals(Constants.USER_TYPE.TECHNICIAN.getCode()))
                            p_role_code = "PortalSafetyTechnician";
                        else if (userDTO.getRole().equals(Constants.USER_TYPE.COMPANY.getCode()))
                            p_role_code = "PortalEmployer";
                        else if (userDTO.getRole().equals(Constants.USER_TYPE.ASSOCIATION.getCode()))
                            p_role_code = "PortalAssociation";
                        else if (userDTO.getRole().equals(Constants.USER_TYPE.CITIZEN.getCode()))
                            p_role_code = "PortalCitizen";
                        else if (userDTO.getRole().equals(Constants.USER_TYPE.COMPANY_USER.getCode()))
                            p_role_code = "PortalEmployerUser";
                        else if (userDTO.getRole().equals(Constants.USER_TYPE.ADMIN.getCode()))
                            p_role_code = "PortalAdmin";
                        else if (userDTO.getRole().equals(Constants.USER_TYPE.UNKNOWN.getCode()))
                            p_role_code = "PortalOther";
                        Map<String, Object> grantRoleToUserInput = new LinkedHashMap<String, Object>();
                        grantRoleToUserInput.put("p_username", userDTO.getUsername());
                        grantRoleToUserInput.put("p_role_code", p_role_code);

                        // call function to Give the user a specific role to Oracle Identity Management / LDAP
                        funcGrantRoleResult = (userStoredProcedures).secOimCallFunction(grantRoleToUserInput, "PCG_SEC_OIM.SEC_OIM_GRANT_ROLE_TO_USER");
                        if (funcGrantRoleResult != 0) {
                            rollback = true;
                            msg = "Προέκυψε κάποιο πρόβλημα με την ανάθεση ρόλου στο χρήστη.";
                        } else {
                            Map<String, Object> resetPassInput = new LinkedHashMap<String, Object>();
                            resetPassInput.put("p_username", userDTO.getUsername());
                            resetPassInput.put("p_new_pass", userDTO.getPassword());

                            // call function to Reset password of a specific user in Oracle Identity Management / LDAP
                            funcResetPassResult = (userStoredProcedures).secOimCallFunction(resetPassInput, "PCG_SEC_OIM.SEC_OIM_RESET_PASS");
                            if (funcResetPassResult != 0) {
                                rollback = true;
                                msg = "Προέκυψε κάποιο πρόβλημα με το password του χρήστη.";
                            } else {
                                // All went OK!
                                try {
                                    mailService.createNewUser(updatedUser.getEmail(), updatedUser.getUsername(), password, updatedUser.getFirstname(), updatedUser.getLastname());
                                } catch (Exception ex) {
                                    model.addAttribute("profileSaveError", ex.getMessage());
                                    return register(model);
                                }
                            }
                        }
                    }
                } catch (Exception ex) {
                    userRepository.delete(updatedUser.getId());
                    if (updatedUser.getRole() == Constants.USER_TYPE.CITIZEN.getCode()) {
                        citizenRepository.delete(updatedUser.getId());
                    } else if (updatedUser.getRole() == Constants.USER_TYPE.EMPLOYEE.getCode()) {
                        employeeRepository.delete(updatedUser.getId());
                    } else if (updatedUser.getRole() == Constants.USER_TYPE.COMPANY.getCode()) {
                        companyRepository.delete(updatedUser.getId());
                    } else if (updatedUser.getRole() == Constants.USER_TYPE.DOCTOR.getCode()) {
                        workplaceDoctorRepository.delete(updatedUser.getId());
                    } else if (updatedUser.getRole() == Constants.USER_TYPE.TECHNICIAN.getCode()) {
                        safetyTechnicianRepository.delete(updatedUser.getId());
                    } else if (updatedUser.getRole() == Constants.USER_TYPE.ASSOCIATION.getCode()) {
                        companyRepository.delete(updatedUser.getId());
                    } else if (updatedUser.getRole() == Constants.USER_TYPE.COMPANY_USER.getCode()) {
                        userRepository.delete(updatedUser.getId());
                    } else if (updatedUser.getRole() == Constants.USER_TYPE.ADMIN.getCode()) {
                        citizenRepository.delete(updatedUser.getId());
                    }
                    model.addAttribute("profileSaveError", msg);
                    return register(model);
                }
                if (rollback) {
                    userRepository.delete(updatedUser.getId());
                    if (updatedUser.getRole() == Constants.USER_TYPE.CITIZEN.getCode()) {
                        citizenRepository.delete(updatedUser.getId());
                    } else if (updatedUser.getRole() == Constants.USER_TYPE.EMPLOYEE.getCode()) {
                        employeeRepository.delete(updatedUser.getId());
                    } else if (updatedUser.getRole() == Constants.USER_TYPE.COMPANY.getCode()) {
                        companyRepository.delete(updatedUser.getId());
                    } else if (updatedUser.getRole() == Constants.USER_TYPE.DOCTOR.getCode()) {
                        workplaceDoctorRepository.delete(updatedUser.getId());
                    } else if (updatedUser.getRole() == Constants.USER_TYPE.TECHNICIAN.getCode()) {
                        safetyTechnicianRepository.delete(updatedUser.getId());
                    } else if (updatedUser.getRole() == Constants.USER_TYPE.ASSOCIATION.getCode()) {
                        companyRepository.delete(updatedUser.getId());
                    } else if (updatedUser.getRole() == Constants.USER_TYPE.COMPANY_USER.getCode()) {
                        userRepository.delete(updatedUser.getId());
                    } else if (updatedUser.getRole() == Constants.USER_TYPE.ADMIN.getCode()) {
                        citizenRepository.delete(updatedUser.getId());
                    }
                    model.addAttribute("profileSaveError", msg);
                    return register(model);
                }
                // *************************

                // Update the user in the active Spring Security session
                /*final String passwordHash = userService.getPasswordHashForNewUser(adminDTO);
                final SpringUserDetails newUserDetails = new SpringUserDetails(adminDTO, adminDTO.getId(), passwordHash, null, null);
                final UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(newUserDetails, passwordHash, null);
                SecurityContextHolder.getContext().setAuthentication(newAuth);*/
            }
        }

        return accountSaved(userDTO, adminDTO, model);
    }

    @RequestMapping(value = {"/accountSaved"}, method = RequestMethod.GET)
    @Nonnull
    public final String accountSaved(
            @Nonnull UserDTO userDTO, @Nonnull UserDTO adminDTO, @Nonnull final Model model

    ) throws Exception {
        model.addAttribute("newuser", userDTO);
        model.addAttribute("user", adminDTO);
        return "accountSaved";
    }

    @RequestMapping(value = {"/accountSaved2"}, method = RequestMethod.GET)
    @Nonnull
    public final String accountSaved2(
            @Nonnull UserDTO userDTO, @Nonnull final Model model

    ) throws Exception {
        model.addAttribute("newuser", userDTO);
        return "accountSaved2";
    }

    @RequestMapping(value = {"/passwordreset"}, method = RequestMethod.POST)
    @Nonnull
    public final String saveNewUser(
            @Nonnull
            @RequestParam(value = "afm")
            final String afm,

            @RequestParam(value = "ameIka")
            final String ameIka,

            @RequestParam(value = "role")
            final String role,

            @Nonnull
            final Model model
    ) throws Exception {
        if (StringIsNullOrEmpty(afm)) {
            model.addAttribute("profileSaveError", "Το ΑΦΜ δεν είναι έγκυρο. Προσπαθήστε ξανά!");
            return Constants.PASSWORD_TEMPLATE;
        }
        if (role.equals("3") && StringIsNullOrEmpty(ameIka)) {
            model.addAttribute("profileSaveError", "Το AME IKA δεν είναι έγκυρο. Προσπαθήστε ξανά!");
            return Constants.PASSWORD_TEMPLATE;
        }
        boolean sendMail = false;
        Long userId = null;

        if (role.equals("1")) {
            final TEmployee employee = employeeRepository.findByAfm(afm);
            if (employee == null) {
                model.addAttribute("profileSaveError", "Το ΑΦΜ δεν είναι έγκυρο. Προσπαθήστε ξανά!");
                return Constants.PASSWORD_TEMPLATE;
            } else {
                sendMail = true;
                userId = employee.getId();
            }
        } else if (role.equals("3")) {
            final TCompany company = companyRepository.findTCompanyByAfmAndAme(afm, ameIka);
            if (company == null) {
                model.addAttribute("profileSaveError", "Το ΑΦΜ ή/και το AME IKA δεν είναι έγκυρα. Προσπαθήστε ξανά!");
                return Constants.PASSWORD_TEMPLATE;
            } else {
                sendMail = true;
                userId = company.getId();
            }
        } else if (role.equals("4")) {
            final TSafetyTechnician tecnhician = safetyTechnicianRepository.findByAfmEquals(afm);
            if (tecnhician == null) {
                model.addAttribute("profileSaveError", "Το ΑΦΜ δεν είναι έγκυρο. Προσπαθήστε ξανά!");
                return Constants.PASSWORD_TEMPLATE;
            } else {
                sendMail = true;
                userId = tecnhician.getId();
            }
        }
        else if (role.equals("5")) {
            final TWorkplaceDoctor doctor = workplaceDoctorRepository.findByAfmEquals(afm);
            if (doctor == null) {
                model.addAttribute("profileSaveError", "Το ΑΦΜ δεν είναι έγκυρο. Προσπαθήστε ξανά!");
                return Constants.PASSWORD_TEMPLATE;
            } else {
                sendMail = true;
                userId = doctor.getId();
            }
        }
        if (sendMail == true) {
            model.addAttribute("profileSaveError", "Τα στοιχεία σας είναι έγκυρα. Σας έχει σταλεί με Εμαιλ ο νέος σας κωδικός!");
            String newpassword = "p1" + generateRandomPassword(6);
            UserDTO user = userService.findUser(userId);
            if (user != null) {
                Integer funcResetPassResult = -1;
                try {
                    Map<String, Object> resetPassInput = new LinkedHashMap<String, Object>();
                    resetPassInput.put("p_username", user.getUsername());
                    resetPassInput.put("p_new_pass", newpassword);

                    // call function to Reset password of a specific user in Oracle Identity Management / LDAP
                    funcResetPassResult = (userStoredProcedures).secOimCallFunction(resetPassInput, "PCG_SEC_OIM.SEC_OIM_RESET_PASS");
                } catch (Exception e) {
                    e.printStackTrace();
                    model.addAttribute("profileSaveError", "Προέκυψε κάποιο πρόβλημα στην διαδικασία αλλαγής του νέου σας κωδικού.");
                    return Constants.PASSWORD_TEMPLATE;
                }
                if (funcResetPassResult != 0) {
                    model.addAttribute("profileSaveError", "Προέκυψε κάποιο πρόβλημα με την αποστολή του νέου σας κωδικού.");
                } else {
                    userService.changeUserPassword(user, newpassword);
                    mailService.passwordReminder(user.getUsername(), newpassword, user.getEmail());
                }
                return Constants.PASSWORD_TEMPLATE;
            } else {
                model.addAttribute("profileSaveError", "Το ΑΦΜ ή/και το AME IKA δεν είναι έγκυρα. Προσπαθήστε ξανά!");
                return Constants.PASSWORD_TEMPLATE;
            }
        }
        return Constants.PASSWORD_TEMPLATE;
    }


    public static String generateRandomPassword(int length) {
        String letters = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ23456789+@";

        String pw = "";
        for (int i = 0; i < length; i++) {
            int index = (int) (RANDOM.nextDouble() * letters.length());
            pw += letters.substring(index, index + 1);
        }
        return pw;
    }

    public static String userDTOValidation(UserDTO u) {

        String errorMessage = "";
        if (Constants.StringIsNullOrEmpty(u.getUsername())) {
            errorMessage = "Απαιτείται να εισάγεται όνομα χρήστη.";
        } else if (Constants.StringIsNullOrEmpty(u.getPassword())) {
            errorMessage = "Απαιτείται να εισάγεται κωδικό.";
        } else if (Constants.StringIsNullOrEmpty(u.getFirstname())) {
            errorMessage = "Απαιτείται να εισάγεται όνομα.";
        } else if (Constants.StringIsNullOrEmpty(u.getLastname())) {
            errorMessage = "Απαιτείται να εισάγεται επώνυμο.";
        }
        if (EmailValidator.validateEmail(u.getEmail()) == false) {
            errorMessage = "To email είναι λάθος μορφής.";
        }
        return errorMessage;
    }

    @RequestMapping(value = {"/companyUsers"}, method = RequestMethod.GET)
    @Nonnull
    public
    @ResponseBody
    List<UserDTO> companyUsers() throws Exception {
        SpringUserDetails currentUser = CurrentUserDetailsService.getCurrentUser();
        List<TCompanyUserPrivilages> privs = companyUserPrivilagesRepository.findAll();
        List<UserDTO> answer = new ArrayList<UserDTO>();
        for (TCompanyUserPrivilages p : privs) {
            UserDTO u = userService.findUser(p.getUserId());
            answer.add(u);
        }
        return answer;
    }

    @RequestMapping(value = {"/companyUsersPrevs"}, method = RequestMethod.GET)
    @Nonnull
    public
    @ResponseBody
    List companyUsersPrevs() throws Exception {
        SpringUserDetails currentUser = CurrentUserDetailsService.getCurrentUser();
        List<TCompanyUserPrivilages> privs = companyUserPrivilagesRepository.findAll();
        List answer = new ArrayList();
        for (TCompanyUserPrivilages p : privs) {
            Map<String, String> prevss = new HashMap<String, String>();
            prevss.put("userId", p.getUserId().toString());
            prevss.put("branchIds", p.getBranchIds());
            prevss.put("privilagesIds", p.getPrivilages());
            prevss.put("active", p.getActive().toString());
            answer.add(prevss);
        }
        return answer;
    }

    @RequestMapping(value = {"/registerCompanyUser/save"}, method = RequestMethod.POST)
    @Nonnull
    public
    @ResponseBody
    final Map<String, Object> saveNewCompanyUser(
            @Nonnull
            //@ModelAttribute("user")
            @RequestBody
                    UserDTO userDTO,

            String userId,
            String branchIds,
            String serviceIds


    ) throws Exception {
        SpringUserDetails currentUser = CurrentUserDetailsService.getCurrentUser();

        Map<String, Object> jsonResult = new HashMap<String, Object>();
        String errorMsg = userDTOValidation(userDTO);
        if (!errorMsg.equals("")) {
            jsonResult.put("success", Boolean.FALSE);
            jsonResult.put("error", errorMsg);
            return jsonResult;
        } else if (userDTO.getPassword().length() < 6) {
            jsonResult.put("success", Boolean.FALSE);
            jsonResult.put("error", "Παρακαλώ το password πρέπει να αποτελείται από τουλάχιστον 6 χαρακτήρες.");
            return jsonResult;
        } else if (!userDTO.getPassword().matches(".*[a-zA-Z]+.*")) {
            jsonResult.put("success", Boolean.FALSE);
            jsonResult.put("error", "Παρακαλώ το password πρέπει να περιέχει τουλάχιστον 1 αλφαβητικό (latin) χαρακτήρα.");
            return jsonResult;
        } else if (!userDTO.getPassword().matches(".*\\d+.*")) {
            jsonResult.put("success", Boolean.FALSE);
            jsonResult.put("error", "Παρακαλώ το password πρέπει να περιέχει τουλάχιστον 1 αριθμητικό χαρακτήρα.");
            return jsonResult;
        } else if (userDTO.getPassword().toLowerCase().contains(userDTO.getUsername().toLowerCase()) || userDTO.getPassword().toLowerCase().contains(userDTO.getLastname().toLowerCase())) {
            jsonResult.put("success", Boolean.FALSE);
            jsonResult.put("error", "Παρακαλώ το password πρέπει να μην περιέχει το username, το επώνυμο ή το όνομα.");
            return jsonResult;
        } else if (!userDTO.getUsername().matches("[a-zA-Z0-9-@#!*$%^&.'_={}()]*")) {
            jsonResult.put("success", Boolean.FALSE);
            jsonResult.put("error", "Παρακαλώ το username δεν επιτρέπεται να περιέχει ελληνικούς χαρακτήρες, κενά ή τους ειδικούς χαρακτήρες + , ; / < >");
            return jsonResult;
        } else if (userDTO.getUsername().length() < 5) {
            jsonResult.put("success", Boolean.FALSE);
            jsonResult.put("error", "Παρακαλώ εισάγετε κάποιο username.");
            return jsonResult;
        } else {
            if (!(userDTO.getUsername().substring(0, 4).equals("ptl-"))) {
                userDTO.setUsername("ptl-" + userDTO.getUsername());
            }
            try {
                userDTO.setRole(7);
                userService.createUser(userDTO);
            } catch (UsernameExistsException ex) {
                jsonResult.put("success", Boolean.FALSE);
                jsonResult.put("error", "Το Όνομα Χρήστη (username) χρησιμοποιείται!");
                return jsonResult;
            }
            UserDTO updatedUser = userService.findUserByUsername(userDTO.getUsername());
            TCompanyUserPrivilages pr = new TCompanyUserPrivilages();
            pr.setActive((long) 1);
            pr.setCompId(currentUser.getUserId());
            pr.setBranchIds("");
            pr.setPrivilages("");
            pr.setUserId(updatedUser.getId());
            companyUserPrivilagesRepository.save(pr);
            jsonResult.put("userId", updatedUser.getId());

            // ***** Oracle Identity Management / LDAP Functions *****
            Map<String, Object> createUserInput = new LinkedHashMap<String, Object>();
            createUserInput.put("p_surname", userDTO.getLastname());
            createUserInput.put("p_fathers_name", "");
            createUserInput.put("p_name", userDTO.getFirstname());
            createUserInput.put("p_email", userDTO.getEmail());
            createUserInput.put("p_telephonenumber", userDTO.getPhone());
            createUserInput.put("p_mobile", userDTO.getMobile());
            createUserInput.put("p_username", userDTO.getUsername());
            createUserInput.put("p_emp_number", "0");

            Integer funcCreateUserResult, funcGrantRoleResult, funcResetPassResult;
            String msg = "Προέκυψε κάποιο πρόβλημα κατά την εγγραφή. Δοκιμάστε ξανά.";
            Boolean rollback = false;

            try {
                // call function to Add user to Oracle Identity Management / LDAP
                funcCreateUserResult = (userStoredProcedures).secOimCallFunction(createUserInput, "PCG_SEC_OIM.SEC_OIM_CREATE_USER");
                if (funcCreateUserResult != 0) {
                    rollback = true;
                    msg = "Προέκυψε κάποιο πρόβλημα με την εγγραφή του χρήστη.";
                } else {
                    String p_role_code = "PortalEmployerUser";
                    Map<String, Object> grantRoleToUserInput = new LinkedHashMap<String, Object>();
                    grantRoleToUserInput.put("p_username", userDTO.getUsername());
                    grantRoleToUserInput.put("p_role_code", p_role_code);

                    // call function to Give the user a specific role to Oracle Identity Management / LDAP
                    funcGrantRoleResult = (userStoredProcedures).secOimCallFunction(grantRoleToUserInput, "PCG_SEC_OIM.SEC_OIM_GRANT_ROLE_TO_USER");
                    if (funcGrantRoleResult != 0) {
                        rollback = true;
                        msg = "Προέκυψε κάποιο πρόβλημα με την ανάθεση ρόλου στο χρήστη.";
                    } else {
                        Map<String, Object> resetPassInput = new LinkedHashMap<String, Object>();
                        resetPassInput.put("p_username", userDTO.getUsername());
                        resetPassInput.put("p_new_pass", userDTO.getPassword());

                        // call function to Reset password of a specific user in Oracle Identity Management / LDAP
                        funcResetPassResult = (userStoredProcedures).secOimCallFunction(resetPassInput, "PCG_SEC_OIM.SEC_OIM_RESET_PASS");
                        if (funcResetPassResult != 0) {
                            rollback = true;
                            msg = "Προέκυψε κάποιο πρόβλημα με το password του χρήστη.";
                        } else {
                            // All went OK!
                            try {
                                String[] branchIds1 = branchIds.substring(1).split(",");
                                String[] serviceIds1 = serviceIds.split(",");

                                long[] inLongBranches = new long[branchIds1.length];
                                for (int i = 0; i < branchIds1.length; i++) {
                                    inLongBranches[i] = Long.valueOf(branchIds1[i]);
                                }

                                String inStringServices = "";
                                for (int i = 0; i < serviceIds1.length; i++) {
                                    if (i == 0)
                                        inStringServices = inStringServices + "'" + serviceIds1[i] + "'";
                                    else
                                        inStringServices = inStringServices + ",'" + serviceIds1[i] + "'";
                                }

                                List<TEmployerBranchIKA> companyBranches = tEmployerBranchIKARepo.findBySession();
                                try {
                                    companyBranches.removeIf(br -> !LongStream.of(inLongBranches).anyMatch(x -> x == br.getRgEbrBranchId()));
                                } catch (NullPointerException e) {

                                }
                                List<String> companyServices = entityManager.createQuery("SELECT DISTINCT a.description FROM SpPtlCompanyService a WHERE  a.id IN (" + inStringServices + ") order by a.description").getResultList();
                                mailService.createNewCompanyUser(updatedUser.getEmail(), updatedUser.getUsername(), userDTO.getPassword(), updatedUser.getFirstname(), updatedUser.getLastname(), currentUser.getCompany().getName(), currentUser.getCompany().getAfm(), companyBranches, companyServices);
                                //mailService.createNewUser(updatedUser.getEmail(), updatedUser.getUsername(), userDTO.getPassword(), updatedUser.getFirstname(), updatedUser.getLastname());
                            } catch (Exception ex) {
                                jsonResult.put("success", Boolean.FALSE);
                                jsonResult.put("error", "Υπάρχουν προβλήματα στην αποστολή του Email.");
                                return jsonResult;
                            }
                        }
                    }
                }
            } catch (Exception ex) {
                userRepository.delete(updatedUser.getId());
                companyUserPrivilagesRepository.delete(pr.getId());
                jsonResult.put("success", Boolean.FALSE);
                jsonResult.put("error", msg);
                return jsonResult;
            }
            if (rollback == true) {
                userRepository.delete(updatedUser.getId());
                companyUserPrivilagesRepository.delete(pr.getId());
                jsonResult.put("success", Boolean.FALSE);
                jsonResult.put("error", msg);
                return jsonResult;
            }
        }
        jsonResult.put("success", Boolean.TRUE);
        return jsonResult;
    }


    @RequestMapping(value = {"/registerCompanyUserPrivilages/save"}, method = RequestMethod.POST)
    @Nonnull
    public
    @ResponseBody
    Map<String, Object> saveCompanyUserPrivilages(
            String userId,
            String branchIds,
            String serviceIds
    ) throws Exception {
        SpringUserDetails currentUser = CurrentUserDetailsService.getCurrentUser();

        Map<String, Object> jsonResult = new HashMap<String, Object>();
        if (Constants.StringIsNullOrEmpty(userId) || Constants.StringIsNullOrEmpty(branchIds) || Constants.StringIsNullOrEmpty(serviceIds)) {
            jsonResult.put("success", Boolean.FALSE);
            jsonResult.put("error", "Διορθώστε τα λάθη");
            return jsonResult;
        } else {
            Long uid = Long.parseLong(userId);

            TCompanyUserPrivilages priv = companyUserPrivilagesRepository.findByCompanyUser(currentUser.getUserId(), uid);
            if (priv == null) {
                jsonResult.put("success", Boolean.FALSE);
                jsonResult.put("error", "Δεν επιτρέπεται η ενημέρωση!");
                return jsonResult;
            }

            priv.setUserId(uid);
            priv.setCompId(currentUser.getUserId());
            priv.setBranchIds(branchIds);
            priv.setPrivilages(serviceIds);
            priv.setActive((long) 1);
            companyUserPrivilagesRepository.save(priv);
        }
        jsonResult.put("success", Boolean.TRUE);
        return jsonResult;
    }

    @RequestMapping(value = {"/getEmployeeInfo"}, method = RequestMethod.GET)
    @Nonnull
    public
    @ResponseBody
    Map<String, Object> getEmployeeInfo(
    ) throws Exception {
        Map<String, Object> jsonResult = new HashMap<String, Object>();
        UserDTO user = CurrentUserDetailsService.getCurrentUser().getUserDTO();

        TEmployee employee = employeeRepository.findByUserId(user.getId());

        //List<SpPtlTechnicianRegrequest> tech = spPtlTechnicianRegrequestRepo.findByUserId(String.valueOf(user.getId()));
        //SpPtlTechnicianRegrequest technicianRegrequest = tech.get(0);

        jsonResult.put("success", Boolean.TRUE);
        jsonResult.put("empFirstname", user.getFirstname());
        jsonResult.put("empLastname", user.getLastname());
        jsonResult.put("empAmka", employee.getAmka());
        jsonResult.put("empCardNumber", employee.getCardNumber());
        jsonResult.put("empAfm", employee.getAfm());
        jsonResult.put("empFathername", employee.getFatherName());
        jsonResult.put("empMothername", employee.getMotherName());
        jsonResult.put("userId", employee.getUserId());

        return jsonResult;
    }


    @RequestMapping(value = {"/getTechnicianInfo"}, method = RequestMethod.GET)
    @Nonnull
    public
    @ResponseBody
    Map<String, Object> getTechnicianInfo(
            int mode
    ) throws Exception {
        Map<String, Object> jsonResult = new HashMap<String, Object>();
        UserDTO user = CurrentUserDetailsService.getCurrentUser().getUserDTO();

        if (mode == 0) {
            SpPtlVUserSafetyTechnician spPtlVUserSafetyTechnician = (SpPtlVUserSafetyTechnician) entityManager.createQuery("SELECT o FROM SpPtlVUserSafetyTechnician o where o.id = " + user.getId()).getSingleResult();
            jsonResult.put("success", Boolean.TRUE);
            jsonResult.put("firstname", user.getFirstname());
            jsonResult.put("lastname", user.getLastname());
            jsonResult.put("fatherrname", "");
            jsonResult.put("afm", spPtlVUserSafetyTechnician.getAfm());
            jsonResult.put("amka", "");
            jsonResult.put("cardNumber", spPtlVUserSafetyTechnician.getCardNumber());
        } else {
            List<SpPtlTechnicianRegrequest> tech = spPtlTechnicianRegrequestRepo.findByUserId2(String.valueOf(user.getId()));
            try {
                SpPtlTechnicianRegrequest technicianRegrequest = tech.get(0);
                jsonResult.put("success", Boolean.TRUE);
                jsonResult.put("firstname", technicianRegrequest.getFirstname());
                jsonResult.put("lastname", technicianRegrequest.getLastname());
                jsonResult.put("fatherrname", technicianRegrequest.getFathername());
                jsonResult.put("afm", technicianRegrequest.getAfm());
                jsonResult.put("amka", technicianRegrequest.getAmka());
                jsonResult.put("cardNumber", technicianRegrequest.getCardNumber());
            } catch (ArrayIndexOutOfBoundsException e) {
                jsonResult.put("success", Boolean.FALSE);
            }
        }

        return jsonResult;
    }

    @RequestMapping(value = {"/getDoctorInfo"}, method = RequestMethod.GET)
    @Nonnull
    public
    @ResponseBody
    Map<String, Object> getDoctorInfo(
            int mode
    ) throws Exception {
        Map<String, Object> jsonResult = new HashMap<String, Object>();
        UserDTO user = CurrentUserDetailsService.getCurrentUser().getUserDTO();

        if (mode == 0) {
            SpPtlVUserWorkplaceDoctor spPtlVUserWorkplaceDoctor = (SpPtlVUserWorkplaceDoctor) entityManager.createQuery("SELECT o FROM SpPtlVUserWorkplaceDoctor o where o.id = " + user.getId()).getSingleResult();
            jsonResult.put("success", Boolean.TRUE);
            jsonResult.put("firstname", user.getFirstname());
            jsonResult.put("lastname", user.getLastname());
            jsonResult.put("fatherrname", "");
            jsonResult.put("afm", spPtlVUserWorkplaceDoctor.getAfm());
            jsonResult.put("amka", "");
            jsonResult.put("cardNumber", spPtlVUserWorkplaceDoctor.getCardNumber());
        } else {
            List<SpPtlDoctorRegrequest> tech = spPtlDoctorRegrequestRepo.findByUserId(String.valueOf(user.getId()));
            try {
                SpPtlDoctorRegrequest doctorRegrequest = tech.get(0);
                jsonResult.put("success", Boolean.TRUE);
                jsonResult.put("firstname", doctorRegrequest.getFirstname());
                jsonResult.put("lastname", doctorRegrequest.getLastname());
                jsonResult.put("fatherrname", doctorRegrequest.getFathername());
                jsonResult.put("afm", doctorRegrequest.getAfm());
                jsonResult.put("amka", doctorRegrequest.getAmka());
                jsonResult.put("cardNumber", doctorRegrequest.getCardNumber());
            } catch (ArrayIndexOutOfBoundsException e) {
                jsonResult.put("success", Boolean.FALSE);
            }
        }

        return jsonResult;
    }

    @RequestMapping(value = {"/checkDoctorSpecialList"}, method = RequestMethod.GET)
    @Nonnull
    public
    @ResponseBody
    Map<String, Object> checkDoctorSpecialList(
            String afm
    ) throws Exception {
        Map<String, Object> jsonResult = new HashMap<>();
        try{
            SpPtlDoctorSpecialList spPtlDoctorSpecialList = (SpPtlDoctorSpecialList) entityManager.createQuery("SELECT o FROM SpPtlDoctorSpecialList o where o.afm = '" + afm + "'").getSingleResult();
            jsonResult.put("count", 1);
            jsonResult.put("speciality", spPtlDoctorSpecialList.getSpeciality());
        } catch (Exception e)
        {
            jsonResult.put("count", 0);
        }
            jsonResult.put("success", Boolean.TRUE);

        return jsonResult;
    }

    @RequestMapping(value = {"/getCompanyInfo"}, method = RequestMethod.GET)
    @Nonnull
    public
    @ResponseBody
    Map<String, Object> getCompanyInfo(
    ) throws Exception {
        SpringUserDetails currentUser = CurrentUserDetailsService.getCurrentUser();

        Map<String, Object> jsonResult = new HashMap<String, Object>();
        if (currentUser == null) {
            jsonResult.put("success", Boolean.FALSE);
            return jsonResult;
        }

        try {
            TCompany company = companyRepository.findOne(currentUser.getCompany().getId());
            //TEmployerBranchIKA ika = ikaRepo.findCompanyMainBranchByAme(company.getAme());
            TEmployerBranchIKA ika = ikaRepo.findCompanyMainBranchByAfmAndAme(company.getAfm(), company.getAme());

            jsonResult.put("success", Boolean.TRUE);
            jsonResult.put("compFullName", ika.getRgEmpFullname());
            jsonResult.put("compTaxNumber", ika.getRgEbrTaxationNumber());
            jsonResult.put("compAme", company.getAme());
            jsonResult.put("compAddr", ika.getRgEbrAddressStreet());
            jsonResult.put("compAddrK", ika.getRgEbrKallikratis());
            jsonResult.put("compAddrTk", ika.getRgEbrZipCode());
            if (ika.getRgEbrPhoneNumber() == null) ika.setRgEbrPhoneNumber("");
            if (ika.getRgEbrPhoneNumber().contains("-"))
                ika.setRgEbrPhoneNumber(ika.getRgEbrPhoneNumber().replace("-", ""));
            jsonResult.put("compPhone", ika.getRgEbrPhoneNumber());
            return jsonResult;
        } catch (Exception e) {
            if (currentUser == null)
                LOGGER.error("getCompanyInfoError: " + e + " id:" + currentUser.getCompany().getId());
            jsonResult.put("success", Boolean.FALSE);
            return jsonResult;
        }
    }

    @RequestMapping(value = {"/getCompanyUserPrivilages"}, method = RequestMethod.GET)
    @Nonnull
    public
    @ResponseBody
    Map<String, Object> saveCompanyUserPrivilages(
    ) throws Exception {
        SpringUserDetails currentUser = CurrentUserDetailsService.getCurrentUser();
        Map<String, Object> jsonResult = new HashMap<String, Object>();
        if (currentUser.getUserDTO().getRole() == Constants.USER_TYPE.COMPANY_USER.getCode()) {
            TCompanyUserPrivilages priv = companyUserPrivilagesRepository.findByUser(currentUser.getUserDTO().getId());
            jsonResult.put("active", priv.getActive());
            jsonResult.put("branchIds", priv.getBranchIds());
            jsonResult.put("privilages", priv.getPrivilages());
            /*jsonResult.put("branchIds", "*"));
            jsonResult.put("privilages", "*");*/
            return jsonResult;
        }

        if (currentUser.getUserDTO().getRole() != Constants.USER_TYPE.COMPANY_USER.getCode()) {
            jsonResult.put("active", "");
            jsonResult.put("branchIds", "");
            jsonResult.put("privilages", "");
            return jsonResult;
        } else {
            TCompanyUserPrivilages priv = companyUserPrivilagesRepository.findByUser(currentUser.getUserDTO().getId());
            if (priv == null) {
                jsonResult.put("active", "");
                jsonResult.put("branchIds", "");
                jsonResult.put("privilages", "");
                return jsonResult;
            }

            jsonResult.put("active", priv.getActive());
            jsonResult.put("branchIds", priv.getBranchIds());
            jsonResult.put("privilages", priv.getPrivilages());
            return jsonResult;
        }

    }


    @RequestMapping(value = {"/companyUser/deactivate"}, method = RequestMethod.POST)
    @Nonnull
    public
    @ResponseBody
    Map<String, Object> deactivateCompanyUser(
            String userId

    ) throws Exception {
        SpringUserDetails currentUser = CurrentUserDetailsService.getCurrentUser();

        Map<String, Object> jsonResult = new HashMap<String, Object>();
        if (Constants.StringIsNullOrEmpty(userId)) {
            jsonResult.put("success", Boolean.FALSE);
            jsonResult.put("error", "Διορθώστε τα λάθη");
            return jsonResult;
        } else {
            Long uid = Long.parseLong(userId);

            TCompanyUserPrivilages priv = companyUserPrivilagesRepository.findByCompanyUser(currentUser.getUserId(), uid);
            if (priv == null) {
                jsonResult.put("success", Boolean.FALSE);
                jsonResult.put("error", "Δεν επιτρέπεται η ενημέρωση!");
                return jsonResult;
            }

            priv.setActive((long) 0);
            companyUserPrivilagesRepository.save(priv);
        }
        jsonResult.put("success", Boolean.TRUE);
        return jsonResult;
    }

    /***********************************************************************************************************************************
     * IE controllers
     **********************************************************************************************************************************/
    // http://localhost:7001/portal/ieAnnResign?compIeAnnId=1441
    // TODO return only if IE has active gen request (and not only te latest one)
    @RequestMapping(value = {"/ieAnnResign"}, method = RequestMethod.GET)
    @Nonnull
    public
    @ResponseBody
    Map<String, Object> ieAnnResign(
            Long compIeAnnId,
            Long compIeAnnIeId
    ) throws Exception {
        SpringUserDetails currentUser = CurrentUserDetailsService.getCurrentUser();

        Map<String, Object> jsonResult = new HashMap<String, Object>();
        if (Constants.LongIsNullOrEmpty(compIeAnnId)) {
            jsonResult.put("success", Boolean.FALSE);
            return jsonResult;
        }

        /*
        * Find the IE announcement
        */
        SpPtlCompIeAnn compIeAnn = spPtlCompIeAnnRepo.ieFindOne(compIeAnnId);
        if (compIeAnn == null) {
            jsonResult.put("success", Boolean.FALSE);
            jsonResult.put("error", "error 1");
            return jsonResult;
        }

        /*
        * Check that this IE has access to the particular announcement?
        */
        SpringUserDetails springUserDetails = currentAuthenticatedUser();
        if (springUserDetails == null) {
            throw new Exception("Error");
        }

        Long userId = springUserDetails.getUserId();

        if (!compIeAnn.getDoctorRegrequestUserId().equals(userId)) {
            jsonResult.put("success", Boolean.FALSE);
            jsonResult.put("error", compIeAnn.getDoctorRegrequestUserId().toString() + "-" + userId.toString());
            return jsonResult;
        }

        /*
        * Check that the status of the announcement allows resignation
        */
        if ((compIeAnn.getReqStatus() != 6) || (compIeAnn.getIeAnnStatus() != Constants.COMP_IE_TA_ANN_STATUS.ACCEPTED.getCode())) {
            jsonResult.put("success", Boolean.FALSE);
            jsonResult.put("error", "error 3");
            return jsonResult;
        }

        /*
        * Update announ status
        */
        compIeAnn.setIeAnnStatus(Constants.COMP_IE_TA_ANN_STATUS.EXPIRED.getCode()); //todo do we really switch to expired?

        /*
        * Update end date
         */
        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        compIeAnn.setDateEnd(new java.sql.Timestamp(now.getTime()));

        /*
        * Save announ
         */
        try {
            spPtlCompIeAnnRepo.save(compIeAnn);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.put("success", Boolean.FALSE);
            jsonResult.put("error", "error 4");
            return jsonResult;
        }

        /*
        * Send message to company
         */

        //Get branch info
        SpPtlCompPtlBranch ptlBranch = spPtlCompPtlBranchRepo.findById(compIeAnn.getCompPtlBranchId());
        if (ptlBranch == null) {
            jsonResult.put("success", Boolean.FALSE);
            jsonResult.put("error", "error 5");
            return jsonResult;
        }

        String announcementIdentifier = new String(ptlBranch.getBrDescr() + " " + ptlBranch.getBrAddr() + " TK " + ptlBranch.getBrAddrTk());


        SpPtlDoctorRegrequest regRequest = doctorRegrequestRepo.findOne(compIeAnn.getDoctorRegrequestId());
        if (regRequest == null) {
            jsonResult.put("success", Boolean.FALSE);
            jsonResult.put("error", "error 6");
            return jsonResult;
        }

        TNotificationsAccountEntity notification = new TNotificationsAccountEntity();
        notification.setDocId(new Long(-1));
        notification.setTitle("Δήλωση Παραίτησης Αναγγελιας");
        notification.setMessage("Ο ιατρός εργασίας " + regRequest.getFirstname() + " " + regRequest.getLastname() + "[ΑΦΜ " + regRequest.getAfm() + "] πραγματοποίησε δήλωση παραίτησης απο την αναγγέλια του υποκαταστήματος " + announcementIdentifier + ".");
        notification.setSender(regRequest.getFirstname() + " " + regRequest.getLastname());
        notification.setPriority(new Long(1));
        notification.setViewed(new Long(0));

        Date todayDate = new Date();
        Timestamp today = new Timestamp(todayDate.getTime());
        notification.setDateTime(today);

        notification.setAccountId(compIeAnn.getCompanyId());

        try {
            notificationsRestRepository.save(notification);
        } catch (Exception e) {
            /* Now what? Announ updated but notification was not sent */
            jsonResult.put("error", "error 6");
        }


        jsonResult.put("success", Boolean.TRUE);
        return jsonResult;
    }


    // http://localhost:7001/findIeByAfm?afm=11111190
    // http://localhost:7001/findIeByAfm?afm=0123456789
    // TODO return only if IE has active gen request (and not only te latest one)
    @RequestMapping(value = {"/findIeByAfm"}, method = RequestMethod.GET)
    @Nonnull
    public
    @ResponseBody
    Map<String, Object> findIeByAfm(
            String afm
    ) throws Exception {
        SpringUserDetails currentUser = CurrentUserDetailsService.getCurrentUser();

        Map<String, Object> jsonResult = new HashMap<String, Object>();
        if (Constants.StringIsNullOrEmpty(afm)) {
            jsonResult.put("success", Boolean.FALSE);
            jsonResult.put("error", "Διορθώστε τα λάθη");
            return jsonResult;
        } else {

            List<SpPtlDoctorRegrequest> doctorRequests = doctorRegrequestRepo.findByAfm(afm);
            if (doctorRequests.size() == 0) {
                jsonResult.put("success", Boolean.FALSE);
                jsonResult.put("error", "Ο ιατρός με το συγκεκριμένο ΑΦΜ δεν είναι καταχωρημένος στο μητρώο.");
                return jsonResult;
            }

            SpPtlDoctorRegrequest doctorRegrequest = doctorRequests.get(0);

            if (doctorRegrequest == null) {
                jsonResult.put("success", Boolean.FALSE);
                jsonResult.put("error", "Δεν επιτρέπεται η ενημέρωση!");
                return jsonResult;
            } else {
                jsonResult.put("success", Boolean.TRUE);
                jsonResult.put("fullname", doctorRegrequest.getFirstname() + " " + doctorRegrequest.getLastname());
                jsonResult.put("speciality", doctorRegrequest.getSpeciality());
                jsonResult.put("specialityOther", doctorRegrequest.getSpecialityOther());
                jsonResult.put("id", doctorRegrequest.getId());
                jsonResult.put("userId", doctorRegrequest.getUserId());
            }

        }

        return jsonResult;
    }

    // http://localhost:7001/compIeAnnBookNoteRead?id=1
    @RequestMapping(value = {"/compIeAnnBookNoteRead"}, method = RequestMethod.POST)
    @Nonnull
    public
    @ResponseBody
    Map<String, Object> compIeAnnBookNoteRead(
            String id
    ) throws Exception {
        SpringUserDetails currentUser = CurrentUserDetailsService.getCurrentUser();

        Map<String, Object> jsonResult = new HashMap<String, Object>();
        if (Constants.StringIsNullOrEmpty(id)) {
            jsonResult.put("success", Boolean.FALSE);
            return jsonResult;
        } else {

            SpPtlCompIeAnnBookNote compIeAnnBookNote = spPtlCompIeAnnBookNoteRepo.findOne(Long.parseLong(id));

            if (compIeAnnBookNote == null) {
                jsonResult.put("success", Boolean.FALSE);
                return jsonResult;
            } else {

                if (Constants.IntegerIsNullOrEmpty(compIeAnnBookNote.getRead())) {
                    compIeAnnBookNote.setRead(0);
                }

                if (compIeAnnBookNote.getRead() != 1) {
                    compIeAnnBookNote.setRead(1);

                    Calendar calendar = Calendar.getInstance();
                    java.util.Date now = calendar.getTime();

                    compIeAnnBookNote.setReadDate(new java.sql.Timestamp(now.getTime()));

                    try {
                        spPtlCompIeAnnBookNoteRepo.save(compIeAnnBookNote);
                    } catch (Exception e) {
                        e.printStackTrace();
                        jsonResult.put("success", Boolean.FALSE);
                        return jsonResult;
                    }
                } else {
                    /* Already read.. */
                }
            }

        }

        jsonResult.put("success", Boolean.TRUE);

        return jsonResult;
    }

    // http://localhost:7001/cCompIeAnnRespond?compIeAnnId=1350&response=1 [COMP_IE_TA_ANN_STATUS.ACCEPTED / REJECTED]
    @RequestMapping(value = {"/cCompIeAnnRespond"}, method = RequestMethod.POST)
    @Nonnull
    public
    @ResponseBody
    Map<String, Object> cCompIeAnnRespond(
            Long compIeAnnId,
            Long compIeAnnIeId,
            Integer response
    ) throws Exception {
        SpringUserDetails currentUser = CurrentUserDetailsService.getCurrentUser();

        Map<String, Object> jsonResult = new HashMap<String, Object>();

        if (Constants.LongIsNullOrEmpty(compIeAnnId)) {
            jsonResult.put("success", Boolean.FALSE);
            return jsonResult;
        }
        if (Constants.LongIsNullOrEmpty(compIeAnnIeId)) {
            jsonResult.put("success", Boolean.FALSE);
            return jsonResult;
        }

        if (Constants.IntegerIsNullOrEmpty(response)) {
            jsonResult.put("success", Boolean.FALSE);
            return jsonResult;
        } else {
            if ((response != Constants.COMP_IE_TA_ANN_STATUS.ACCEPTED.getCode()) && (response != Constants.COMP_IE_TA_ANN_STATUS.REJECTED.getCode())) {
                jsonResult.put("success", Boolean.FALSE);
                return jsonResult;
            }
        }

        /*
        * Find the IE announcement
        */
        SpPtlCompIeAnn compIeAnn = spPtlCompIeAnnRepo.ieFindOne(compIeAnnId);
        if (compIeAnn == null) {
            jsonResult.put("success", Boolean.FALSE);
            return jsonResult;
        }
        SpPtlCompIeAnn oldCompIeAnn = spPtlCompIeAnnRepo.ieFindOne(compIeAnnId);
        SpPtlCompIeAnnIeEntry compIeAnnIeEntry = spPtlCompIeAnnIeEntryRepo.ieFindOne(compIeAnnIeId);
        if (compIeAnnIeEntry == null) {
            jsonResult.put("success", Boolean.FALSE);
            return jsonResult;
        }
        SpPtlCompIeAnnIeEntry oldCompIeAnnIeEntry = spPtlCompIeAnnIeEntryRepo.ieFindOne(compIeAnnIeId);


        /*
        * Check that this IE has access
        */
        SpringUserDetails springUserDetails = currentAuthenticatedUser();
        if (springUserDetails == null) {
            jsonResult.put("success", Boolean.FALSE);
            return jsonResult;
        }

        Long userId = springUserDetails.getUserId();

        if (!compIeAnnIeEntry.getDoctorRegrequestUserId().equals(userId)) {
            jsonResult.put("success", Boolean.FALSE);
            return jsonResult;
        }

        SpPtlCompPtlBranch ptlBranch = spPtlCompPtlBranchRepo.findById(compIeAnn.getCompPtlBranchId());
        if (ptlBranch == null) {
            jsonResult.put("success", Boolean.FALSE);
            return jsonResult;
        }

        /*
        * Update IE announcement's status
        */
        if (compIeAnn.getIeAnnStatus() != Constants.COMP_IE_TA_ANN_STATUS.ISSUED.getCode()) {
            jsonResult.put("success", Boolean.FALSE);
            return jsonResult;
        }


        if (response == 1) {
            compIeAnnIeEntry.setIeAnnIeStatus(1);
            compIeAnn.setIeAnnIeTotalAccept(compIeAnn.getIeAnnIeTotalAccept() + 1);
            if (compIeAnn.getIeAnnIeTotalAccept() == compIeAnn.getIeAnnIeTotal()) {
                compIeAnn.setReqStatus(6);
                compIeAnn.setIeAnnStatus(1);
            }
            if ((compIeAnn.getIeAnnIeTotalAccept() + compIeAnn.getIeAnnIeTotalReject()) == compIeAnn.getIeAnnIeTotal() && compIeAnn.getIeAnnIeTotalReject() != 0) {
                compIeAnn.setReqStatus(7);
                compIeAnn.setIeAnnStatus(-1);
            }
        } else if (response == -1) {
            compIeAnn.setReqStatus(7);
            compIeAnnIeEntry.setIeAnnIeStatus(-1);
            compIeAnn.setIeAnnIeTotalReject(compIeAnn.getIeAnnIeTotalReject() + 1);
            compIeAnn.setIeAnnStatus(-1);
        }
        //compIeAnn.setIeAnnStatus(response);
        /*
        * Save IE announcement
        */
        try {
            if (compIeAnn.getIeAnnStatus() != Constants.COMP_IE_TA_ANN_STATUS.ISSUED.getCode()) {
                //TO DO enable when exists on DB
                try {
                    (companyStoredProcedures).procIntArchiveCompIeAnn(compIeAnn);
                } catch (Exception e) {
                    spPtlCompIeAnnRepo.save(oldCompIeAnn);
                    spPtlCompIeAnnIeEntryRepo.save(oldCompIeAnnIeEntry);
                    e.printStackTrace();
                    jsonResult.put("success", Boolean.FALSE);
                    return jsonResult;
                }
            }

            spPtlCompIeAnnRepo.save(compIeAnn);
            spPtlCompIeAnnIeEntryRepo.save(compIeAnnIeEntry);
            TNotificationsAccountEntity notification = new TNotificationsAccountEntity();
            notification.setDocId(new Long(-1));
            notification.setTitle("Αποδοχή Αναγγελιας Τεχνικού");

            String announcementIdentifier = new String(ptlBranch.getBrDescr() + " " + ptlBranch.getBrAddr() + " TK " + ptlBranch.getBrAddrTk());
            notification.setMessage("Ο Ιατρός εργασίας/ΕΞΥΠΠ " + compIeAnnIeEntry.getIeFullname() + " " + "[ΑΦΜ " + compIeAnnIeEntry.getIeAfm() + "] δέχτηκε την αναγγέλια του υποκαταστήματος " + announcementIdentifier + ".");
            notification.setSender(compIeAnnIeEntry.getIeFullname());
            notification.setPriority(new Long(1));
            notification.setViewed(new Long(0));

            Date todayDate = new Date();
            Timestamp today = new Timestamp(todayDate.getTime());
            notification.setDateTime(today);

            notification.setAccountId(compIeAnn.getCompanyId());

            try {
                notificationsRestRepository.save(notification);
            } catch (Exception e) {
            }
            jsonResult.put("success", Boolean.TRUE);
            return jsonResult;
        } catch (Exception e) {
            spPtlCompIeAnnRepo.save(oldCompIeAnn);
            spPtlCompIeAnnIeEntryRepo.save(oldCompIeAnnIeEntry);
            e.printStackTrace();
            jsonResult.put("success", Boolean.FALSE);
            return jsonResult;
        }
    }

    /*
    * IEresponds to the company request to terminate the announcement
    */
    // http://localhost:7001/portal/compIeAnnCessationRespond?compIeAnnId=1&response=1 [0= paraitisi, 1 = apolisy]
    @RequestMapping(value = {"/compIeAnnCessationRespond"}, method = RequestMethod.POST)
    @Nonnull
    public
    @ResponseBody
    Map<String, Object> compIeAnnCessationRespond(
            Long compIeAnnId,
            Long compIeAnnIeId,
            Integer response
    ) throws Exception {
        SpringUserDetails currentUser = CurrentUserDetailsService.getCurrentUser();

        Map<String, Object> jsonResult = new HashMap<String, Object>();

        if (Constants.LongIsNullOrEmpty(compIeAnnId)) {
            jsonResult.put("success", Boolean.FALSE);
            return jsonResult;
        }

        if (Constants.IntegerIsNullOrEmpty(response)) {
            jsonResult.put("success", Boolean.FALSE);
            return jsonResult;
        } else {
            if ((response != 0) && (response != 1)) {
                jsonResult.put("success", Boolean.FALSE);
                return jsonResult;
            }
        }

        /*
        * Find the IE announcement
        */
        SpPtlCompIeAnn compIeAnn = spPtlCompIeAnnRepo.ieFindOne(compIeAnnId);
        if (compIeAnn == null) {
            jsonResult.put("success", Boolean.FALSE);
            jsonResult.put("error", "error 1");
            return jsonResult;
        }

        /*
        * Check that this IE has access
        */
        SpringUserDetails springUserDetails = currentAuthenticatedUser();
        if (springUserDetails == null) {
            jsonResult.put("success", Boolean.FALSE);
            jsonResult.put("error", "error 2");
            return jsonResult;
        }

        Long userId = springUserDetails.getUserId();

        if (!compIeAnn.getDoctorRegrequestUserId().equals(userId)) {
            jsonResult.put("success", Boolean.FALSE);
            jsonResult.put("error", "error 3");
            return jsonResult;
        }

        /*
        * Update TA announcement's status
        */
        if (compIeAnn.getIeAnnStatus() != Constants.COMP_IE_TA_ANN_STATUS.EXPIRED.getCode()) {
            jsonResult.put("success", Boolean.FALSE);
            jsonResult.put("error", "error 4");
            return jsonResult;
        }

        if (compIeAnn.getReqStatus() != 7) {
            jsonResult.put("success", Boolean.FALSE);
            jsonResult.put("error", "error 5");
            return jsonResult;
        }

        compIeAnn.setIeReplaceResponse(response);

        /*
        * Save TA announcement
        */
        try {
            spPtlCompIeAnnRepo.save(compIeAnn);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.put("success", Boolean.FALSE);
            jsonResult.put("error", "error 6");
            return jsonResult;
        }

        /*
        * Send message to company
         */

        //Get branch info
        SpPtlCompPtlBranch ptlBranch = spPtlCompPtlBranchRepo.findById(compIeAnn.getCompPtlBranchId());
        if (ptlBranch == null) {
            jsonResult.put("success", Boolean.FALSE);
            jsonResult.put("error", "error 7");
            return jsonResult;
        }

        String announcementIdentifier = new String(ptlBranch.getBrDescr() + " " + ptlBranch.getBrAddr() + " TK " + ptlBranch.getBrAddrTk());

        SpPtlDoctorRegrequest regRequest = doctorRegrequestRepo.findOne(compIeAnn.getDoctorRegrequestId());
        if (regRequest == null) {
            jsonResult.put("success", Boolean.FALSE);
            jsonResult.put("error", "error 8");
            return jsonResult;
        }

        TNotificationsAccountEntity notification = new TNotificationsAccountEntity();
        notification.setDocId(new Long(-1));
        notification.setTitle("Αποδοχή Παραίτησης Αναγγελιας");
        notification.setMessage("Ο Ιατρός Εργασίας " + regRequest.getFirstname() + " " + regRequest.getLastname() + "[ΑΦΜ " + regRequest.getAfm() + "] δέχτηκε την παύση του απο την αναγγέλια του υποκαταστήματος " + announcementIdentifier + ".");
        notification.setSender(regRequest.getFirstname() + " " + regRequest.getLastname());
        notification.setPriority(new Long(1));
        notification.setViewed(new Long(0));

        Date todayDate = new Date();
        Timestamp today = new Timestamp(todayDate.getTime());
        notification.setDateTime(today);

        notification.setAccountId(compIeAnn.getCompanyId());

        try {
            notificationsRestRepository.save(notification);
        } catch (Exception e) {
            /* Now what? Announ updated but notification was not sent */
            jsonResult.put("error", "error 9");
        }


        jsonResult.put("success", Boolean.TRUE);
        return jsonResult;
    }


    // http://localhost:7001/doctorBookNoteAdd?compIeAnnId=1350&branchId=1261&notes=TTTTTTTTTTTTTTTTT
    // @Transactional(propagation = Propagation.REQUIRES_NEW)
    @RequestMapping(value = {"/doctorBookNoteAdd"}, method = RequestMethod.GET)
    @Nonnull
    public
    @ResponseBody
    Map<String, Object> doctorBookNoteAdd(
            Long compIeAnnId,
            Long branchId,
            String notes
    ) throws Exception {
        SpringUserDetails currentUser = CurrentUserDetailsService.getCurrentUser();

        Map<String, Object> jsonResult = new HashMap<String, Object>();

        if (Constants.LongIsNullOrEmpty(compIeAnnId)) {
            jsonResult.put("success", Boolean.FALSE);
            return jsonResult;
        }
        if (Constants.LongIsNullOrEmpty(branchId)) {
            jsonResult.put("success", Boolean.FALSE);
            return jsonResult;
        }
        /* todo passing note instead of notes bypass this check!!!! */
        if (Constants.StringIsNullOrEmpty(notes)) {
            jsonResult.put("success", Boolean.FALSE);
            return jsonResult;
        }

        /*
        * Find the compIeAnnBook
        */
        SpPtlCompIeAnnBook compIeAnnBook = spPtlCompIeAnnBookRepo.findByPtlBranchId(branchId);
        if (compIeAnnBook == null) {
            jsonResult.put("success", Boolean.FALSE);
            return jsonResult;
        }

        /*
        * Find the the compIeAnn
        */
        SpPtlCompIeAnn compIeAnn = spPtlCompIeAnnRepo.findOne(compIeAnnId);
        if (compIeAnn == null) {
            jsonResult.put("success", Boolean.FALSE);
            return jsonResult;
        }

        /*
        * Check that this IE has access
        */
        SpringUserDetails springUserDetails = currentAuthenticatedUser();
        if (springUserDetails == null) {
            jsonResult.put("success", Boolean.FALSE);
            return jsonResult;
        }

        Long userId = springUserDetails.getUserId();

        if (!compIeAnn.getDoctorRegrequestUserId().equals(userId)) {
            jsonResult.put("success", Boolean.FALSE);
            return jsonResult;
        }

        /*
        * Get IE name
         */
        SpPtlDoctorRegrequest regReq = doctorRegrequestRepo.findOne(compIeAnn.getDoctorRegrequestId());
        if (regReq == null) {
            jsonResult.put("success", Boolean.FALSE);
            return jsonResult;
        }

        String bookNoteAuthorName = new String(regReq.getFirstname() + " " + regReq.getLastname());


        /*
        * Get current date
         */
        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();

        /*
        * Save book note
        */
        SpPtlCompIeAnnBookNote annBookNote = new SpPtlCompIeAnnBookNote();
        annBookNote.setCompanyId(compIeAnnBook.getCompanyId());
        annBookNote.setCompIeAnn(compIeAnn);
        annBookNote.setAuthorName(bookNoteAuthorName);

        annBookNote.setCompIeAnnBook(compIeAnnBook);
        Set<SpPtlCompIeAnnBookNote> annBookNotes = compIeAnnBook.getCompIeAnnBookNotes();
        annBookNotes.add(annBookNote);
        compIeAnnBook.setCompIeAnnBookNotes(annBookNotes);

        annBookNote.setNotes(notes);
        annBookNote.setRead(0);
        annBookNote.setReadDate(null);
        annBookNote.setDateCreated(new java.sql.Timestamp(now.getTime()));


        try {
            spPtlCompIeAnnBookRepo.save(compIeAnnBook);
            jsonResult.put("success", Boolean.TRUE);
            return jsonResult;
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.put("success", Boolean.FALSE);
            return jsonResult;
        }

    }

    /***********************************************************************************************************************************
     * TA controllers [PTL BRANCH]
     **********************************************************************************************************************************/
    // http://localhost:7001/portal/ieAnnResign?compIeAnnId=1441
    // TODO return only if TA has active gen request (and not only te latest one)
    @RequestMapping(value = {"/taAnnResign"}, method = RequestMethod.POST)
    @Nonnull
    public
    @ResponseBody
    Map<String, Object> taAnnResign(
            Long compTaAnnId,
            Long compTaAnnTaId
    ) throws Exception {
        SpringUserDetails currentUser = CurrentUserDetailsService.getCurrentUser();

        Map<String, Object> jsonResult = new HashMap<String, Object>();
        if (Constants.LongIsNullOrEmpty(compTaAnnId)) {
            jsonResult.put("success", Boolean.FALSE);
            return jsonResult;
        }

        /*
        * Find the IE announcement
        */
        SpPtlCompTaAnn compTaAnn = spPtlCompTaAnnRepo.taFindOne(compTaAnnId);
        if (compTaAnn == null) {
            jsonResult.put("success", Boolean.FALSE);
            jsonResult.put("error", "error 1");
            return jsonResult;
        }

        /*
        * Check that this IE has access to the particular announcement?
        */
        SpringUserDetails springUserDetails = currentAuthenticatedUser();
        if (springUserDetails == null) {
            throw new Exception("Error");
        }

        Long userId = springUserDetails.getUserId();

        SpPtlCompTaAnnTaEntry compTaAnnTaEntry = spPtlCompTaAnnTaEntryRepo.taFindOne(compTaAnnTaId);
        if (compTaAnnTaEntry == null) {
            jsonResult.put("success", Boolean.FALSE);
            jsonResult.put("error", "error 6");
            return jsonResult;
        }

        if (!compTaAnnTaEntry.getTechnicianRegrequestUserId().equals(userId)) {
            jsonResult.put("success", Boolean.FALSE);
            //jsonResult.put("error", compTaAnn.getDoctorRegrequestUserId().toString() + "-" +  userId.toString());
            return jsonResult;
        }

        /*
        * Check that the status of the announcement allows resignation
        */
        if ((compTaAnn.getReqStatus() != 6) || (compTaAnn.getTaAnnStatus() != Constants.COMP_IE_TA_ANN_STATUS.ACCEPTED.getCode())) {
            jsonResult.put("success", Boolean.FALSE);
            jsonResult.put("error", "error 3");
            return jsonResult;
        }

        /*
        * Update announ status
        */
        compTaAnn.setTaAnnStatus(Constants.COMP_IE_TA_ANN_STATUS.EXPIRED.getCode()); //todo do we really switch to expired?

        /*
        * Update end date
         */
        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        compTaAnn.setDateEnd(new java.sql.Timestamp(now.getTime()));

        /*
        * Save announ
         */
        try {
            compTaAnn = (companyStoredProcedures).procIntArchiveCompTaAnnResign(compTaAnn);
            spPtlCompTaAnnRepo.save(compTaAnn);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.put("success", Boolean.FALSE);
            jsonResult.put("error", "error 4");
            return jsonResult;
        }

        /*
        * Send message to company
         */

        //Get branch info
        SpPtlCompPtlBranch ptlBranch = spPtlCompPtlBranchRepo.findById(compTaAnn.getCompPtlBranchId());
        if (ptlBranch == null) {
            jsonResult.put("success", Boolean.FALSE);
            jsonResult.put("error", "error 5");
            return jsonResult;
        }

        String announcementIdentifier = new String(ptlBranch.getBrDescr() + " " + ptlBranch.getBrAddr() + " TK " + ptlBranch.getBrAddrTk());


        compTaAnnTaEntry.setTaAnnTaStatus(Constants.COMP_IE_TA_ANN_STATUS.EXPIRED.getCode()); //todo do we really switch to expired?

        try {
            spPtlCompTaAnnTaEntryRepo.save(compTaAnnTaEntry);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.put("success", Boolean.FALSE);
            jsonResult.put("error", "error 4");
            return jsonResult;
        }
        /*SpPtlTechnicianRegrequest regRequest = technicianRegrequestRepo.findOne(compTaAnn.getTechnicianRegrequestId());
        if (regRequest == null) {
            jsonResult.put("success", Boolean.FALSE);
            jsonResult.put("error", "error 6");
            return jsonResult;
        }*/

        TNotificationsAccountEntity notification = new TNotificationsAccountEntity();
        notification.setDocId(new Long(-1));
        notification.setTitle("Δήλωση Παραίτησης Αναγγελιας");
        notification.setMessage("Ο Τεχνικός Ασφάλειας/ΕΞΥΠΠ " + compTaAnnTaEntry.getTaFullname() + " " + "[ΑΦΜ " + compTaAnnTaEntry.getTaAfm() + "] πραγματοποίησε δήλωση παραίτησης απο την αναγγέλια του υποκαταστήματος " + announcementIdentifier + ".");
        notification.setSender(compTaAnnTaEntry.getTaFullname());
        notification.setPriority(new Long(1));
        notification.setViewed(new Long(0));

        Date todayDate = new Date();
        Timestamp today = new Timestamp(todayDate.getTime());
        notification.setDateTime(today);

        notification.setAccountId(compTaAnn.getCompanyId());

        try {
            notificationsRestRepository.save(notification);
        } catch (Exception e) {
            /* Now what? Announ updated but notification was not sent */
            jsonResult.put("error", "error 6");
        }


        jsonResult.put("success", Boolean.TRUE);
        return jsonResult;
    }

    // http://localhost:7001/findTaByAfm?afm=1111112
    // TODO return only if IE has active gen request (and not only te latest one)
    @RequestMapping(value = {"/findTaByAfm"}, method = RequestMethod.GET)
    @Nonnull
    public
    @ResponseBody
    Map<String, Object> findTaByAfm(
            String afm, String ta, Long branchSectorId, Integer taSum
    ) throws Exception {
        SpringUserDetails currentUser = CurrentUserDetailsService.getCurrentUser();

        Map<String, Object> jsonResult = new HashMap<String, Object>();
        if (Constants.StringIsNullOrEmpty(afm) || Constants.StringIsNullOrEmpty(ta) || Constants.LongIsNullOrEmpty(branchSectorId)) {
            jsonResult.put("success", Boolean.FALSE);
            jsonResult.put("error", "Διορθώστε τα λάθη");
            return jsonResult;
        } else {

            if (ta.equals("tas")) {
                SnRgVwTaRegNez snRgVwTaRegNez = snRgVwTaRegNezRepo.findByAfm(afm);
                if (snRgVwTaRegNez == null) {
                    jsonResult.put("success", Boolean.FALSE);
                    jsonResult.put("error", "Ο τεχνικός ασφαλείας με το συγκεκριμένο ΑΦΜ δεν είναι καταχωρημένος στο μητρώο.");
                    return jsonResult;
                } else {
                    jsonResult.put("success", Boolean.TRUE);
                    jsonResult.put("fullname", snRgVwTaRegNez.getRgTaName() + " " + snRgVwTaRegNez.getRgTaSurname());
                    jsonResult.put("speciality", snRgVwTaRegNez.getRgTaSpecialityCode());
                    jsonResult.put("specialityOther", snRgVwTaRegNez.getRgTaSpecialityDesc());
                    jsonResult.put("id", snRgVwTaRegNez.getRgTaDocId());
                    jsonResult.put("userId", 0);
                }
            } else {
                List<SpPtlVTechnicianRegrequest> technicianRegrequests = technicianVRegrequestRepo.findByAfm(afm);
                if (technicianRegrequests.size() == 0) {
                    jsonResult.put("success", Boolean.FALSE);
                    jsonResult.put("error", "Ο τεχνικός ασφαλείας με το συγκεκριμένο ΑΦΜ δεν είναι καταχωρημένος στο μητρώο.");
                    return jsonResult;
                }

                SpPtlVTechnicianRegrequest technicianRegrequest = technicianRegrequests.get(0);

                if (technicianRegrequest == null) {
                    jsonResult.put("success", Boolean.FALSE);
                    jsonResult.put("error", "Δεν επιτρέπεται η ενημέρωση!");
                    return jsonResult;
                } else {

                    SpRtTaBranchSector spRtTaBranchSector = spRtTaBranchSectorRepository.findOne(branchSectorId);
                    if (spRtTaBranchSector == null) {
                        jsonResult.put("success", Boolean.FALSE);
                        jsonResult.put("error", "Δυστυχώς δεν έγινε η ενημέρωση!");
                        return jsonResult;
                    }
                    Long spTabrsectCheck = spRtTaBranchSector.getSpTabrsectCheck();
                    if (spTabrsectCheck == 1L) {
                        List<SpRtTaSpeciality> spRtTaSpecialities = null;
                        Long spObligCheck = spRtTaBranchSector.getSpObligCheck();
                        if (spObligCheck == 1 || taSum > 1) {
                            spRtTaSpecialities = spRtTaSpecialityRepository.findByBranchSectorId(branchSectorId);
                        } else {
                            spRtTaSpecialities = spRtTaSpecialityRepository.findByBranchSectorIdAndObligId(branchSectorId, 1L);
                        }

                        String[] temp = technicianRegrequest.getSpeciality().split(",");
                        boolean isTaSpeciality = false;
                        if (spRtTaSpecialities != null) {
                            for (int i = 0; i < temp.length; i++) {
                                String specialityIdString = temp[i].replace("\"", "");
                                Long specialityId = Long.parseLong(specialityIdString);
                                if (spRtTaSpecialities.stream().filter(o -> o.getId().equals(specialityId)).findFirst().isPresent()) {
                                    isTaSpeciality = true;
                                }
                            }
                        }
                        if (!isTaSpeciality) {
                            jsonResult.put("success", Boolean.FALSE);
                            jsonResult.put("error", "Οι συγκεκριμένες ειδικότητες αυτού του τεχνικού δεν επιτρέπονται για αυτό το κλάδο εργασίας.");
                            return jsonResult;
                        }
                    }

                    jsonResult.put("success", Boolean.TRUE);
                    jsonResult.put("fullname", technicianRegrequest.getFirstname() + " " + technicianRegrequest.getLastname());
                    jsonResult.put("speciality", technicianRegrequest.getSpeciality());
                    jsonResult.put("specialityOther", technicianRegrequest.getSpecialityOther());
                    jsonResult.put("id", technicianRegrequest.getId());
                    jsonResult.put("userId", technicianRegrequest.getUserId());
                }
            }
        }

        return jsonResult;
    }


    // http://localhost:7001/compTaAnnBookNoteRead?id=1
    @RequestMapping(value = {"/compTaAnnBookNoteRead"}, method = RequestMethod.POST)
    @Nonnull
    public
    @ResponseBody
    Map<String, Object> compTaAnnBookNoteRead(
            String id
    ) throws Exception {
        SpringUserDetails currentUser = CurrentUserDetailsService.getCurrentUser();

        Map<String, Object> jsonResult = new HashMap<String, Object>();
        if (Constants.StringIsNullOrEmpty(id)) {
            jsonResult.put("success", Boolean.FALSE);
            return jsonResult;
        } else {

            SpPtlCompTaAnnBookNote compTaAnnBookNote = spPtlCompTaAnnBookNoteRepo.findOne(Long.parseLong(id));

            if (compTaAnnBookNote == null) {
                jsonResult.put("success", Boolean.FALSE);
                return jsonResult;
            } else {

                if (Constants.IntegerIsNullOrEmpty(compTaAnnBookNote.getRead())) {
                    compTaAnnBookNote.setRead(0);
                }

                if (compTaAnnBookNote.getRead() != 1) {
                    compTaAnnBookNote.setRead(1);

                    Calendar calendar = Calendar.getInstance();
                    java.util.Date now = calendar.getTime();

                    compTaAnnBookNote.setReadDate(new java.sql.Timestamp(now.getTime()));

                    try {
                        spPtlCompTaAnnBookNoteRepo.save(compTaAnnBookNote);
                    } catch (Exception e) {
                        e.printStackTrace();
                        jsonResult.put("success", Boolean.FALSE);
                        return jsonResult;
                    }
                } else {
                    /* Already read.. */
                }
            }

        }

        jsonResult.put("success", Boolean.TRUE);

        return jsonResult;
    }

    // http://localhost:7001/cCompTaAnnRespond?compTaAnnId=1350&response=1 [COMP_IE_TA_ANN_STATUS.ACCEPTED / REJECTED]
    @RequestMapping(value = {"/cCompTaAnnRespond"}, method = RequestMethod.POST)
    @Nonnull
    public
    @ResponseBody
    Map<String, Object> cCompTaAnnRespond(
            Long compTaAnnId,
            Long compTaAnnTaId,
            Integer response,
            String dateStart,
            String dateEnd,
            Long[] technicianRegrequestUserIds,
            String[] visitDate,
            String[] visitTime,
            Integer[] visitDurationMinutes
    ) throws Exception {
        SpringUserDetails currentUser = CurrentUserDetailsService.getCurrentUser();
        Long technicianRegrequestUserId = currentUser.getUserId();

        Map<String, Object> jsonResult = new HashMap<String, Object>();

        jsonResult.put("visitConflict", Boolean.FALSE);
        jsonResult.put("previousDates", Boolean.FALSE);

        if (Constants.LongIsNullOrEmpty(compTaAnnId)) {
            jsonResult.put("success", Boolean.FALSE);
            return jsonResult;
        }
        if (Constants.LongIsNullOrEmpty(compTaAnnTaId)) {
            jsonResult.put("success", Boolean.FALSE);
            return jsonResult;
        }
        if (Constants.IntegerIsNullOrEmpty(response)) {
            jsonResult.put("success", Boolean.FALSE);
            return jsonResult;
        } else {
            if ((response != Constants.COMP_IE_TA_ANN_STATUS.ACCEPTED.getCode()) && (response != Constants.COMP_IE_TA_ANN_STATUS.REJECTED.getCode())) {
                jsonResult.put("success", Boolean.FALSE);
                return jsonResult;
            }
        }


        if (response == 1) {

            // Έλεγχος επικαλυπτόμενων επισκέψεων

            boolean datesConflicts = false;
            String conflicts = "Οι ημερομηνίες που επικαλύπτονται: ";
            StringBuilder conflictsConcat = new StringBuilder();
            int daySec = 1 * 24 * 60 * 60 * 1000;
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
            SimpleDateFormat formatter2 = new SimpleDateFormat("HH:mm:ss");
            //Long dateStartL = formatter.parse(dateStart).getTime() / 1000 - daySec;
            //Long dateEndL = formatter.parse(dateEnd).getTime() / 1000 + daySec;
            /*List<Long> ieVisitConflictQueryTechnicianRegrequestUserId = spPtlVCompTaAnnDiaryBRepo.taVisitConflict1(dateStartL, dateEndL);
            List<Integer> ieVisitConflictQueryVisitDurationMinutes = spPtlVCompTaAnnDiaryBRepo.taVisitConflict3(dateStartL, dateEndL);
            List<Date> ieVisitConflictQueryVisitDate = spPtlVCompTaAnnDiaryBRepo.taVisitConflict4(dateStartL, dateEndL);
            List<String> ieVisitConflictQueryVisitTime = spPtlVCompTaAnnDiaryBRepo.taVisitConflict5(dateStartL, dateEndL);*/
            String dates = "";
            int cnt = 0;
            String visitDateString;
            String currentDateString;
            for (String i : visitDate) {
                visitDateString = (i.split("T")[0]+'T'+visitTime[cnt]+":00");
                currentDateString = formatter.format(new Date()).split("\\.")[0];
                if (visitDateString.compareTo(currentDateString) < 0)
                {
                    jsonResult.put("success", Boolean.FALSE);
                    jsonResult.put("previousDates", Boolean.TRUE);
                    return jsonResult;
                }
                if (cnt == 0)
                    dates = dates + "'" + new SimpleDateFormat("dd-MM-yyyy").format(new Date(formatter.parse(i).getTime())) + "','" + new SimpleDateFormat("dd-MM-yyyy").format(new Date(formatter.parse(i).getTime() + daySec)) + "'";
                else if (!dates.contains(new SimpleDateFormat("dd-MM-yyyy").format(new Date(formatter.parse(i).getTime()))))
                    dates = dates + ",'" + new SimpleDateFormat("dd-MM-yyyy").format(new Date(formatter.parse(i).getTime())) + "','" + new SimpleDateFormat("dd-MM-yyyy").format(new Date(formatter.parse(i).getTime() + daySec)) + "'";
                cnt++;
            }
            List<SpPtlVCompTaAnnDiaryB> ieVisitConflictQuery = null;
            List<Long> techUserIds = new ArrayList<>();
            Date date;
            Long lVisitDate, lVisitTime, dateAndTime, durationMins, technicianRegrequestUserId1, visitDateEpochSec, conflictVisitDate, conflictVisitTime;
            Integer visitDurationMinutes1;
            int offset, conflictOffset;

            // Ελέγχω αν είναι ΕΞΥΠΠ
            final UserDTO user = currentUser.getUserDTO();
            final CompanyDTO company = userService.findCompany(user.getId());
            boolean isExypp = false;
            if (company != null && company.getIsExypp() != null) {
                isExypp = true;
            }

            for (int i = 0; i < visitDate.length; i++) {

                if (!techUserIds.contains(technicianRegrequestUserIds[i])) {
                    techUserIds.add(technicianRegrequestUserIds[i]);
                    ieVisitConflictQuery = entityManager.createNativeQuery("SELECT * FROM SP_PTL_V_COMP_TA_ANN_DIARY_B WHERE TO_CHAR(VISIT_DATE, 'DD-MM-YYYY') IN (" + dates + ") AND TECHNICIAN_REGREQUEST_USER_ID = " + technicianRegrequestUserIds[i] + " ORDER BY VISIT_DATE ASC", SpPtlVCompTaAnnDiaryB.class).getResultList();
                }

                if (techUserIds.contains(technicianRegrequestUserIds[i]) && techUserIds.indexOf(technicianRegrequestUserIds[i]) != techUserIds.size() - 1)
                    ieVisitConflictQuery = entityManager.createNativeQuery("SELECT * FROM SP_PTL_V_COMP_TA_ANN_DIARY_B WHERE TO_CHAR(VISIT_DATE, 'DD-MM-YYYY') IN (" + dates + ") AND TECHNICIAN_REGREQUEST_USER_ID = " + technicianRegrequestUserIds[i] + " ORDER BY VISIT_DATE ASC", SpPtlVCompTaAnnDiaryB.class).getResultList();


                date = new Date(formatter.parse(visitDate[i]).getTime());
                offset = -date.getTimezoneOffset() / 60;
                lVisitDate = date.getTime() / 1000;
                lVisitTime = Time.valueOf(visitTime[i] + ":00").getTime() / 1000;
                if (offset == 3)
                    dateAndTime = lVisitDate + lVisitTime - 3600; //remove 1 hour
                else
                    dateAndTime = lVisitDate + lVisitTime;
                durationMins = new Long(visitDurationMinutes[i]);
                if (isExypp) {
                    technicianRegrequestUserId = technicianRegrequestUserIds[i];
                } else if (!technicianRegrequestUserId.equals(technicianRegrequestUserIds[i])) continue;

                for (int j = 0; j < ieVisitConflictQuery.size(); j++) {
                    technicianRegrequestUserId1 = ieVisitConflictQuery.get(j).getTechnicianRegrequestUserId();

                    conflictVisitDate = (long)Math.round((ieVisitConflictQuery.get(j).getVisitDate().getTime() / 1000) / 86400) * 86400;
                    conflictVisitTime = Time.valueOf(ieVisitConflictQuery.get(j).getVisitTime() + ":00").getTime() / 1000;
                    conflictOffset = conflictVisitDate.equals(lVisitDate) ? offset : -ieVisitConflictQuery.get(j).getVisitDate().getTimezoneOffset() / 60;
                    if (conflictOffset == 3)
                        visitDateEpochSec = conflictVisitDate + conflictVisitTime - 3600; //remove 1 hour
                    else
                        visitDateEpochSec = conflictVisitDate + conflictVisitTime;

                    visitDurationMinutes1 = ieVisitConflictQuery.get(j).getVisitDurationMinutes();

                    if (technicianRegrequestUserId1 == (long) technicianRegrequestUserId && ((dateAndTime <= visitDateEpochSec && (dateAndTime + (durationMins * 60)) > visitDateEpochSec) ||
                            (dateAndTime >= visitDateEpochSec && (dateAndTime + (durationMins * 60)) <= (visitDateEpochSec + visitDurationMinutes1 * 60)) ||
                            (dateAndTime < (visitDateEpochSec + visitDurationMinutes1 * 60) && (dateAndTime + (durationMins * 60)) >= (visitDateEpochSec + visitDurationMinutes1 * 60)))) {
                        datesConflicts = true;
                        conflictsConcat.append(i);
                        conflictsConcat.append(" ");
                        break;
                    }
                }
            }
            if (datesConflicts) {
                jsonResult.put("success", Boolean.FALSE);
                jsonResult.put("visitConflict", Boolean.TRUE);
                conflicts += conflictsConcat.toString();
                jsonResult.put("conflicts", conflicts);
                return jsonResult;
            }
        }

        /*
        * Find the TA announcement
        */
        SpPtlCompTaAnn compTaAnn = spPtlCompTaAnnRepo.taFindOne(compTaAnnId);
        if (compTaAnn == null) {
            jsonResult.put("success", Boolean.FALSE);
            return jsonResult;
        }
        SpPtlCompTaAnn oldCompTaAnn = spPtlCompTaAnnRepo.taFindOne(compTaAnnId);
        SpPtlCompTaAnnTaEntry compTaAnnTaEntry = spPtlCompTaAnnTaEntryRepo.taFindOne(compTaAnnTaId);
        if (compTaAnnTaEntry == null) {
            jsonResult.put("success", Boolean.FALSE);
            return jsonResult;
        }
        SpPtlCompTaAnnTaEntry oldCompTaAnnTaEntry = spPtlCompTaAnnTaEntryRepo.taFindOne(compTaAnnTaId);
        /*
        * Check that this TA has access
        */
        SpringUserDetails springUserDetails = currentAuthenticatedUser();
        if (springUserDetails == null) {
            jsonResult.put("success", Boolean.FALSE);
            return jsonResult;
        }

        Long userId = springUserDetails.getUserId();

        if (!compTaAnnTaEntry.getTechnicianRegrequestUserId().equals(userId)) {
            jsonResult.put("success", Boolean.FALSE);
            return jsonResult;
        }

        SpPtlCompPtlBranch ptlBranch = spPtlCompPtlBranchRepo.findById(compTaAnn.getCompPtlBranchId());
        if (ptlBranch == null) {
            jsonResult.put("success", Boolean.FALSE);
            return jsonResult;
        }

        /*
        * Update TA announcement's status
        */
        if (compTaAnn.getTaAnnStatus() != Constants.COMP_IE_TA_ANN_STATUS.ISSUED.getCode()) {
            jsonResult.put("success", Boolean.FALSE);
            return jsonResult;
        }

        if (compTaAnnTaEntry.getTaAnnTaStatus() == 0) {
            if (response == 1) {
                compTaAnnTaEntry.setTaAnnTaStatus(1);
                compTaAnn.setTaAnnTaTotalAccept(compTaAnn.getTaAnnTaTotalAccept() + 1);
                if (compTaAnn.getTaAnnTaTotalAccept() == compTaAnn.getTaAnnTaTotal()) {
                    compTaAnn.setReqStatus(6);
                    compTaAnn.setTaAnnStatus(1);
                }
                if ((compTaAnn.getTaAnnTaTotalAccept() + compTaAnn.getTaAnnTaTotalReject()) == compTaAnn.getTaAnnTaTotal() && compTaAnn.getTaAnnTaTotalReject() != 0) {
                    compTaAnn.setReqStatus(7);
                    compTaAnn.setTaAnnStatus(-1);
                }
            } else if (response == -1) {
                compTaAnn.setReqStatus(7);
                compTaAnnTaEntry.setTaAnnTaStatus(-1);
                compTaAnn.setTaAnnTaTotalReject(compTaAnn.getTaAnnTaTotalReject() + 1);
                compTaAnn.setTaAnnStatus(-1);
            }


        /*
        * Save TA announcement
        */
            // TODO Call procedure only if accepted! -- > change ta_ann_status from ISSUED to ACCEPTED
            try {
                System.out.println("Before try, compTaAnnId: "+compTaAnnId+" TaAnnStatus: "+compTaAnn.getTaAnnStatus()+" TaEntryId: "+compTaAnnTaEntry.getId()+" TaAnnTaStatus: "+compTaAnnTaEntry.getTaAnnTaStatus());
                if (compTaAnn.getTaAnnStatus() == Constants.COMP_IE_TA_ANN_STATUS.ACCEPTED.getCode()) {
                    //TO DO enable when exists on DB
                    try {
                        System.out.println("After try, compTaAnnId: "+compTaAnnId+" TaAnnStatus: "+compTaAnn.getTaAnnStatus()+" TaEntryId: "+compTaAnnTaEntry.getId()+" TaAnnTaStatus: "+compTaAnnTaEntry.getTaAnnTaStatus());
                        compTaAnn = (companyStoredProcedures).procIntArchiveCompTaAnn(compTaAnn);
                    } catch (Exception e) {
                        System.out.println("After try with error, compTaAnnId: "+compTaAnnId+" TaAnnStatus: "+compTaAnn.getTaAnnStatus()+" TaEntryId: "+compTaAnnTaEntry.getId()+" TaAnnTaStatus: "+compTaAnnTaEntry.getTaAnnTaStatus());
                        spPtlCompTaAnnRepo.save(oldCompTaAnn);
                        spPtlCompTaAnnTaEntryRepo.save(oldCompTaAnnTaEntry);
                        e.printStackTrace();
                        jsonResult.put("success", Boolean.FALSE);
                        return jsonResult;
                    }
                }

                spPtlCompTaAnnRepo.save(compTaAnn);
                spPtlCompTaAnnTaEntryRepo.save(compTaAnnTaEntry);

                // Αποστολή notification προς τον εαυτό του και την επιχείρηση
                String announcementIdentifier = new String(ptlBranch.getBrDescr() + " " + ptlBranch.getBrAddr() + " TK " + ptlBranch.getBrAddrTk());
                TCompany cmp = companyRepository.findOne(compTaAnn.getCompanyId());
                String titleTa = "";
                String titleCompany = "";
                String messageTa = "";
                String messageCompany = "";
                if (response == 1) {
                    titleCompany = "Αποδοχή Αναγγελιας Τεχνικού";
                    titleTa = "Επιτυχημένη Αποδοχή Αναγγελίας";
                    messageCompany = "Ο Τεχνικός Ασφάλειας/ΕΞΥΠΠ " + compTaAnnTaEntry.getTaFullname() + " " + "[ΑΦΜ " + compTaAnnTaEntry.getTaAfm() + "] δέχτηκε την αναγγέλια του υποκαταστήματος " + announcementIdentifier + ".";
                    messageTa = "Η αποδοχή της αναγγελίας του υποκαταστήματος " + announcementIdentifier + " της επιχείρησης " + cmp.getTitle() + " έγινε με επιτυχία. Πρέπει να αποδεχτούν όλοι οι Τεχνικοί Ασφάλειας/ΕΞΥΠΠ για να δωθεί αριθμός πρωτοκόλλου.";
                    if (compTaAnn.getTaAnnTaTotalAccept() == compTaAnn.getTaAnnTaTotal()) {
                        messageCompany = "Ο Τεχνικός Ασφάλειας/ΕΞΥΠΠ " + compTaAnnTaEntry.getTaFullname() + " " + "[ΑΦΜ " + compTaAnnTaEntry.getTaAfm() + "] δέχτηκε την αναγγέλια του υποκαταστήματος " + announcementIdentifier + ". Η αναγγελία αποδέχτηκε από όλους, λαμβάνωντας αριθμό πρωτοκόλλου: " + compTaAnn.getProtNo();
                        messageTa = "Η αποδοχή της αναγγελίας του υποκαταστήματος " + announcementIdentifier + " της επιχείρησης " + cmp.getTitle() + " έγινε με επιτυχία, με αριθμό πρωτοκόλλου: " + compTaAnn.getProtNo();
                    }
                } else if (response == -1) {
                    titleCompany = "Απόρριψη Αναγγελιας Τεχνικού";
                    titleTa = "Επιτυχημένη Απόρριψη Αναγγελίας";
                    messageCompany = "Ο Τεχνικός Ασφάλειας/ΕΞΥΠΠ " + compTaAnnTaEntry.getTaFullname() + " " + "[ΑΦΜ " + compTaAnnTaEntry.getTaAfm() + "] απέρριψε την αναγγέλια του υποκαταστήματος " + announcementIdentifier + ".";
                    messageTa = "Η απόρριψη της αναγγελίας του υποκαταστήματος " + announcementIdentifier + " έγινε με επιτυχία.";
                }

                // Send notification to company
                TNotificationsAccountEntity notification = new TNotificationsAccountEntity();
                notification.setDocId(new Long(-1));
                notification.setTitle(titleCompany);


                notification.setMessage(messageCompany);
                notification.setSender(compTaAnnTaEntry.getTaFullname());
                notification.setPriority(new Long(1));
                notification.setViewed(new Long(0));

                Date todayDate = new Date();
                Timestamp today = new Timestamp(todayDate.getTime());
                notification.setDateTime(today);

                notification.setAccountId(compTaAnn.getCompanyId());

                try {
                    notificationsRestRepository.save(notification);
                    TUser user = userRepository.findOne(compTaAnn.getCompanyId());
                    mailService.sendNotification(user.getEmail(), user.getEmailNotifEn(), titleCompany, messageCompany);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                // Send notification to technicianForm Creator (if it is Company User)
                if (compTaAnn.getUserId() != null && compTaAnn.getUserId() != compTaAnn.getCompanyId()) {
                    TNotificationsAccountEntity notification3 = new TNotificationsAccountEntity();
                    notification3.setDocId(new Long(-1));
                    notification3.setTitle(titleCompany);


                    notification3.setMessage(messageCompany);
                    notification3.setSender(compTaAnnTaEntry.getTaFullname());
                    notification3.setPriority(new Long(1));
                    notification3.setViewed(new Long(0));
                    notification3.setDateTime(today);

                    notification3.setAccountId(compTaAnn.getUserId());

                    try {
                        notificationsRestRepository.save(notification3);
                        TUser user = userRepository.findOne(compTaAnn.getUserId());
                        mailService.sendNotification(user.getEmail(), user.getEmailNotifEn(), titleCompany, messageCompany);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                // Send notification to all Technicians + EXYPP
                Set<SpPtlCompTaAnnTaEntry> spPtlCompTaAnnTaEntries = compTaAnn.getCompTaAnnTaEntries();
                for (SpPtlCompTaAnnTaEntry spPtlCompTaAnnTaEntry : spPtlCompTaAnnTaEntries) {

                    TNotificationsAccountEntity notification2 = new TNotificationsAccountEntity();
                    notification2.setDocId(new Long(-1));
                    notification2.setTitle(titleTa);
                    notification2.setMessage(messageTa);
                    notification2.setSender(compTaAnnTaEntry.getTaFullname());
                    notification2.setPriority(new Long(1));
                    notification2.setViewed(new Long(0));
                    notification2.setDateTime(today);

                    if (spPtlCompTaAnnTaEntry.getCooperationType() != 3) {
                        TSafetyTechnician safetyTechnician = safetyTechnicianRepository.findByAfmEquals(spPtlCompTaAnnTaEntry.getTaAfm());
                        notification2.setAccountId(safetyTechnician.getUserId());
                        try {
                            notificationsRestRepository.save(notification2);
                            TUser user = userRepository.findOne(safetyTechnician.getUserId());
                            mailService.sendNotification(user.getEmail(), user.getEmailNotifEn(), titleTa, messageTa);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        TCompany safetyTechnician = companyRepository.findByUserId(spPtlCompTaAnnTaEntry.getTechnicianRegrequestUserId());
                        notification2.setAccountId(safetyTechnician.getUserId());
                        try {
                            notificationsRestRepository.save(notification2);
                            TUser user = userRepository.findOne(safetyTechnician.getUserId());
                            mailService.sendNotification(user.getEmail(), user.getEmailNotifEn(), titleTa, messageTa);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

                jsonResult.put("success", Boolean.TRUE);
                return jsonResult;
            } catch (Exception e) {

                spPtlCompTaAnnRepo.save(oldCompTaAnn);
                spPtlCompTaAnnTaEntryRepo.save(oldCompTaAnnTaEntry);
                e.printStackTrace();
                jsonResult.put("success", Boolean.FALSE);
                return jsonResult;
            }

        } else {
            jsonResult.put("success", Boolean.FALSE);
            return jsonResult;
        }

    }


    /*
    * TA responds to the company request to terminate the announcement
     */
    // http://localhost:7001/portal/compTaAnnCessationRespond?compTaAnnId=1&response=1 [0= paraitisi, 1 = apolisy]
    @RequestMapping(value = {"/compTaAnnCessationRespond"}, method = RequestMethod.POST)
    @Nonnull
    public
    @ResponseBody
    Map<String, Object> compTaAnnCessationRespond(
            Long compTaAnnId,
            Long compTaAnnTaId,
            Integer response
    ) throws Exception {
        SpringUserDetails currentUser = CurrentUserDetailsService.getCurrentUser();

        Map<String, Object> jsonResult = new HashMap<String, Object>();

        if (Constants.LongIsNullOrEmpty(compTaAnnId)) {
            jsonResult.put("success", Boolean.FALSE);
            return jsonResult;
        }

        if (Constants.IntegerIsNullOrEmpty(response)) {
            jsonResult.put("success", Boolean.FALSE);
            return jsonResult;
        } else {
            if ((response != 0) && (response != 1)) {
                jsonResult.put("success", Boolean.FALSE);
                return jsonResult;
            }
        }

        /*
        * Find the TA announcement
        */
        SpPtlCompTaAnn compTaAnn = spPtlCompTaAnnRepo.taFindOne(compTaAnnId);
        if (compTaAnn == null) {
            jsonResult.put("success", Boolean.FALSE);
            jsonResult.put("error", "error 1");
            return jsonResult;
        }

        /*
        * Check that this TA has access
        */
        SpringUserDetails springUserDetails = currentAuthenticatedUser();
        if (springUserDetails == null) {
            jsonResult.put("success", Boolean.FALSE);
            jsonResult.put("error", "error 2");
            return jsonResult;
        }

        Long userId = springUserDetails.getUserId();

        SpPtlCompTaAnnTaEntry compTaAnnTaEntry = spPtlCompTaAnnTaEntryRepo.taFindOne(compTaAnnTaId);
        if (compTaAnnTaEntry == null) {
            jsonResult.put("success", Boolean.FALSE);
            jsonResult.put("error", "error 8");
            return jsonResult;
        }

        if (!compTaAnnTaEntry.getTechnicianRegrequestUserId().equals(userId)) {
            jsonResult.put("success", Boolean.FALSE);
            jsonResult.put("error", "error 3");
            //jsonResult.put("error", compTaAnn.getDoctorRegrequestUserId().toString() + "-" +  userId.toString());
            return jsonResult;
        }
        /*
        if(!compTaAnn.getTechnicianRegrequestUserId().equals(userId)){
            jsonResult.put("success", Boolean.FALSE);
            //jsonResult.put("error", "error 3:" + compTaAnn.getTechnicianRegrequestUserId().toString() + "!="+ userId.toString());
            jsonResult.put("error", "error 3");
            return jsonResult;
        }
*/
        /*
        * Update TA announcement's status
        */
        if (compTaAnn.getTaAnnStatus() != Constants.COMP_IE_TA_ANN_STATUS.EXPIRED.getCode()) {
            jsonResult.put("success", Boolean.FALSE);
            jsonResult.put("error", "error 4");
            return jsonResult;
        }

        if (compTaAnn.getReqStatus() != 7) {
            jsonResult.put("success", Boolean.FALSE);
            jsonResult.put("error", "error 5");
            return jsonResult;
        }


        compTaAnnTaEntry.setTaAnnTaReplaceResponse(response);
        //compTaAnn.setTaReplaceResponse(response);

        /*
        * Save TA announcement
        */
        try {
            spPtlCompTaAnnTaEntryRepo.save(compTaAnnTaEntry);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.put("success", Boolean.FALSE);
            jsonResult.put("error", "error 6");
            return jsonResult;
        }

        /*
        * Send message to company
         */

        //Get branch info
        SpPtlCompPtlBranch ptlBranch = spPtlCompPtlBranchRepo.findById(compTaAnn.getCompPtlBranchId());
        if (ptlBranch == null) {
            jsonResult.put("success", Boolean.FALSE);
            jsonResult.put("error", "error 7");
            return jsonResult;
        }

        String announcementIdentifier = new String(ptlBranch.getBrDescr() + " " + ptlBranch.getBrAddr() + " TK " + ptlBranch.getBrAddrTk());

        /*SpPtlTechnicianRegrequest regRequest = technicianRegrequestRepo.findOne(compTaAnn.getTechnicianRegrequestId());
        if (regRequest == null) {
            jsonResult.put("success", Boolean.FALSE);
            jsonResult.put("error", "error 8");
            return jsonResult;
        }*/

        TNotificationsAccountEntity notification = new TNotificationsAccountEntity();
        notification.setDocId(new Long(-1));
        notification.setTitle("Αποδοχή Παραίτησης Αναγγελιας");
        notification.setMessage("Ο Τεχνικός Ασφάλειας/ΕΞΥΠΠ " + compTaAnnTaEntry.getTaFullname() + "[ΑΦΜ " + compTaAnnTaEntry.getTaAfm() + "] δέχτηκε την παύση του απο την αναγγέλια του υποκαταστήματος " + announcementIdentifier + ".");
        notification.setSender(compTaAnnTaEntry.getTaFullname());
        notification.setPriority(new Long(1));
        notification.setViewed(new Long(0));

        Date todayDate = new Date();
        Timestamp today = new Timestamp(todayDate.getTime());
        notification.setDateTime(today);

        notification.setAccountId(compTaAnn.getCompanyId());

        try {
            notificationsRestRepository.save(notification);
            TUser user = userRepository.findOne(compTaAnn.getCompanyId());
            mailService.sendNotification(user.getEmail(), user.getEmailNotifEn(), "Αποδοχή Παραίτησης Αναγγελιας", "Ο Τεχνικός Ασφάλειας/ΕΞΥΠΠ " + compTaAnnTaEntry.getTaFullname() + "[ΑΦΜ " + compTaAnnTaEntry.getTaAfm() + "] δέχτηκε την παύση του απο την αναγγέλια του υποκαταστήματος " + announcementIdentifier + ".");
        } catch (Exception e) {
            /* Now what? Announ updated but notification was not sent */
            jsonResult.put("error", "error 9");
        }


        jsonResult.put("success", Boolean.TRUE);
        return jsonResult;
    }


    // http://localhost:7001/doctorBookNoteAdd?compTaAnnId=1350&branchId=1261&notes=aabbababab
    // @Transactional(propagation = Propagation.REQUIRES_NEW)
    @RequestMapping(value = {"/technicianBookNoteAdd"}, method = RequestMethod.POST)
    @Nonnull
    public
    @ResponseBody
    Map<String, Object> technicianBookNoteAdd(
            Long compTaAnnId,
            Long branchId,
            String notes
    ) throws Exception {
        SpringUserDetails currentUser = CurrentUserDetailsService.getCurrentUser();

        Map<String, Object> jsonResult = new HashMap<String, Object>();

        if (Constants.LongIsNullOrEmpty(compTaAnnId)) {
            jsonResult.put("success", Boolean.FALSE);
            return jsonResult;
        }
        if (Constants.LongIsNullOrEmpty(branchId)) {
            jsonResult.put("success", Boolean.FALSE);
            return jsonResult;
        }
        /* todo passing note instead of notes bypass this check!!!! */
        if (Constants.StringIsNullOrEmpty(notes)) {
            jsonResult.put("success", Boolean.FALSE);
            return jsonResult;
        }

        /*
        * Find the compTaAnnBook
        */
        SpPtlCompTaAnnBook compTaAnnBook = spPtlCompTaAnnBookRepo.findByPtlBranchId(branchId);
        if (compTaAnnBook == null) {
            jsonResult.put("success", Boolean.FALSE);
            return jsonResult;
        }

        /*
        * Find the the compTaAnn
        */
        SpPtlCompTaAnn compTaAnn = spPtlCompTaAnnRepo.findOne(compTaAnnId);
        if (compTaAnn == null) {
            jsonResult.put("success", Boolean.FALSE);
            return jsonResult;
        }

        /*
        * Check that this TA has access
        */
        SpringUserDetails springUserDetails = currentAuthenticatedUser();
        if (springUserDetails == null) {
            jsonResult.put("success", Boolean.FALSE);
            return jsonResult;
        }

        Long userId = springUserDetails.getUserId();
        SpPtlCompTaAnnTaEntry realCompTaAnnTa = new SpPtlCompTaAnnTaEntry();
        boolean flag1 = false;
        for (SpPtlCompTaAnnTaEntry compTaAnnTa : compTaAnn.getCompTaAnnTaEntries())
            if (compTaAnnTa.getTechnicianRegrequestUserId().equals(userId)) {
                realCompTaAnnTa = compTaAnnTa;
                flag1 = true;
            }
        if (!flag1) {
            jsonResult.put("success", Boolean.FALSE);
            return jsonResult;
        }

        /*
        * Get TA name
         */
        String bookNoteAuthorName = new String(currentUser.getUserDTO().getFirstname());
        Long bookNoteAuthorId = currentUser.getUserId();
        if (realCompTaAnnTa.getCooperationType() != 3) {
            SpPtlTechnicianRegrequest regReq = technicianRegrequestRepo.findOne(realCompTaAnnTa.getTechnicianRegrequestId());
            if (regReq == null) {
                jsonResult.put("success", Boolean.FALSE);
                return jsonResult;
            }
            bookNoteAuthorName = new String(regReq.getFirstname() + " " + regReq.getLastname());
        } else {
            bookNoteAuthorName = new String(currentUser.getCompany().getName());
        }




        /*
        * Get current date
        */
        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();

        /*
        * Save book note
        */
        SpPtlCompTaAnnBookNote annBookNote = new SpPtlCompTaAnnBookNote();
        annBookNote.setCompanyId(compTaAnnBook.getCompanyId());
        annBookNote.setCompTaAnn(compTaAnn);
        annBookNote.setAuthorName(bookNoteAuthorName);
        annBookNote.setAuthorUserId(bookNoteAuthorId);

        annBookNote.setCompTaAnnBook(compTaAnnBook);
        Set<SpPtlCompTaAnnBookNote> annBookNotes = compTaAnnBook.getCompTaAnnBookNotes();
        annBookNotes.add(annBookNote);
        compTaAnnBook.setCompTaAnnBookNotes(annBookNotes);

        annBookNote.setNotes(notes);
        annBookNote.setRead(0);
        annBookNote.setReadDate(null);
        annBookNote.setDateCreated(new java.sql.Timestamp(now.getTime()));


        try {
            spPtlCompTaAnnBookRepo.save(compTaAnnBook);
            jsonResult.put("success", Boolean.TRUE);
            return jsonResult;
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.put("success", Boolean.FALSE);
            return jsonResult;
        }

    }


    /***********************************************************************************************************************************
     * TA controllers [SHIP]
     **********************************************************************************************************************************/
    // http://localhost:7001/portal/ieAnnResign?compIeAnnId=1441
    // TODO return only if IE has active gen request (and not only te latest one)
    @RequestMapping(value = {"/taSannResign"}, method = RequestMethod.POST)
    @Nonnull
    public
    @ResponseBody
    Map<String, Object> taSannResign(
            Long compTaSannId
    ) throws Exception {
        SpringUserDetails currentUser = CurrentUserDetailsService.getCurrentUser();

        Map<String, Object> jsonResult = new HashMap<String, Object>();
        if (Constants.LongIsNullOrEmpty(compTaSannId)) {
            jsonResult.put("success", Boolean.FALSE);
            return jsonResult;
        }

        /*
        * Find the IE announcement
        */
        SpPtlCompTaSann compTaSann = spPtlCompTaSannRepo.taFindOne(compTaSannId);
        if (compTaSann == null) {
            jsonResult.put("success", Boolean.FALSE);
            jsonResult.put("error", "error 1");
            return jsonResult;
        }

        /*
        * Check that this IE has access to the particular announcement?
        */
        SpringUserDetails springUserDetails = currentAuthenticatedUser();
        if (springUserDetails == null) {
            throw new Exception("Error");
        }

        Long userId = springUserDetails.getUserId();

        if (!compTaSann.getTechnicianRegrequestUserId().equals(userId)) {
            jsonResult.put("success", Boolean.FALSE);
            //jsonResult.put("error", compTaAnn.getDoctorRegrequestUserId().toString() + "-" +  userId.toString());
            return jsonResult;
        }

        /*
        * Check that the status of the announcement allows resignation
        */
        if ((compTaSann.getReqStatus() != 6) || (compTaSann.getTaSannStatus() != Constants.COMP_IE_TA_ANN_STATUS.ACCEPTED.getCode())) {
            jsonResult.put("success", Boolean.FALSE);
            jsonResult.put("error", "error 3");
            return jsonResult;
        }

        /*
        * Update announ status
        */
        compTaSann.setTaSannStatus(Constants.COMP_IE_TA_ANN_STATUS.EXPIRED.getCode()); //todo do we really switch to expired?

        /*
        * Update end date
         */
        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        compTaSann.setDateEnd(new java.sql.Timestamp(now.getTime()));

        /*
        * Save announ
         */
        try {
            spPtlCompTaSannRepo.save(compTaSann);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.put("success", Boolean.FALSE);
            jsonResult.put("error", "error 4");
            return jsonResult;
        }

        /*
        * Send message to company
         */

        //Get ship info
        SpPtlCompShip ship = spPtlCompShipRepo.findByCompanyId(compTaSann.getCompanyId(), compTaSann.getCompShipId());
        if (ship == null) {
            jsonResult.put("success", Boolean.FALSE);
            jsonResult.put("error", "error 5");
            return jsonResult;
        }

        String announcementIdentifier = new String(ship.getShipName() + " " + ship.getShipImo());


        SpPtlTechnicianRegrequest regRequest = technicianRegrequestRepo.findOne(compTaSann.getTechnicianRegrequestId());
        if (regRequest == null) {
            jsonResult.put("success", Boolean.FALSE);
            jsonResult.put("error", "error 6");
            return jsonResult;
        }

        TNotificationsAccountEntity notification = new TNotificationsAccountEntity();
        notification.setDocId(new Long(-1));
        notification.setTitle("Δήλωση Παραίτησης Αναγγελιας");
        notification.setMessage("Ο Τεχνικός Ασφάλειας " + regRequest.getFirstname() + " " + regRequest.getLastname() + "[ΑΦΜ " + regRequest.getAfm() + "] πραγματοποίησε δήλωση παραίτησης απο την αναγγέλια του υποκαταστήματος " + announcementIdentifier + ".");
        notification.setSender(regRequest.getFirstname() + " " + regRequest.getLastname());
        notification.setPriority(new Long(1));
        notification.setViewed(new Long(0));

        Date todayDate = new Date();
        Timestamp today = new Timestamp(todayDate.getTime());
        notification.setDateTime(today);

        notification.setAccountId(compTaSann.getCompanyId());

        try {
            notificationsRestRepository.save(notification);
        } catch (Exception e) {
            /* Now what? Announ updated but notification was not sent */
            jsonResult.put("error", "error 6");
        }


        jsonResult.put("success", Boolean.TRUE);
        return jsonResult;
    }

    // http://localhost:7001/compTaSannBookNoteRead?id=1
    @RequestMapping(value = {"/compTaSannBookNoteRead"}, method = RequestMethod.POST)
    @Nonnull
    public
    @ResponseBody
    Map<String, Object> compTaSannBookNoteRead(
            String id
    ) throws Exception {
        SpringUserDetails currentUser = CurrentUserDetailsService.getCurrentUser();

        Map<String, Object> jsonResult = new HashMap<String, Object>();
        if (Constants.StringIsNullOrEmpty(id)) {
            jsonResult.put("success", Boolean.FALSE);
            return jsonResult;
        } else {

            SpPtlCompTaSannBookNote compTaSannBookNote = spPtlCompTaSannBookNoteRepo.findOne(Long.parseLong(id));

            if (compTaSannBookNote == null) {
                jsonResult.put("success", Boolean.FALSE);
                return jsonResult;
            } else {

                if (Constants.IntegerIsNullOrEmpty(compTaSannBookNote.getRead())) {
                    compTaSannBookNote.setRead(0);
                }

                if (compTaSannBookNote.getRead() != 1) {
                    compTaSannBookNote.setRead(1);

                    Calendar calendar = Calendar.getInstance();
                    java.util.Date now = calendar.getTime();

                    compTaSannBookNote.setReadDate(new java.sql.Timestamp(now.getTime()));

                    try {
                        spPtlCompTaSannBookNoteRepo.save(compTaSannBookNote);
                    } catch (Exception e) {
                        e.printStackTrace();
                        jsonResult.put("success", Boolean.FALSE);
                        return jsonResult;
                    }
                } else {
                    /* Already read.. */
                }
            }
        }

        jsonResult.put("success", Boolean.TRUE);

        return jsonResult;
    }


    // http://localhost:7001/cCompTaSannRespond?compTaSannId=1350&response=1 [COMP_IE_TA_ANN_STATUS.ACCEPTED / REJECTED]
    @RequestMapping(value = {"/cCompTaSannRespond"}, method = RequestMethod.POST)
    @Nonnull
    public
    @ResponseBody
    Map<String, Object> cCompTaSannRespond(
            Long compTaSannId,
            Integer response
    ) throws Exception {
        SpringUserDetails currentUser = CurrentUserDetailsService.getCurrentUser();

        Map<String, Object> jsonResult = new HashMap<String, Object>();

        if (Constants.LongIsNullOrEmpty(compTaSannId)) {
            jsonResult.put("success", Boolean.FALSE);
            return jsonResult;
        }

        if (Constants.IntegerIsNullOrEmpty(response)) {
            jsonResult.put("success", Boolean.FALSE);
            return jsonResult;
        } else {
            if ((response != Constants.COMP_IE_TA_ANN_STATUS.ACCEPTED.getCode()) && (response != Constants.COMP_IE_TA_ANN_STATUS.REJECTED.getCode())) {
                jsonResult.put("success", Boolean.FALSE);
                return jsonResult;
            }
        }

        /*
        * Find the TA ship announcement
        */
        SpPtlCompTaSann compTaSann = spPtlCompTaSannRepo.taFindOne(compTaSannId);
        SpPtlCompTaSann oldCompTaSann = spPtlCompTaSannRepo.taFindOne(compTaSannId);
        if (compTaSann == null) {
            jsonResult.put("success", Boolean.FALSE);
            return jsonResult;
        }

        /*
        * Check that this TA has access
        */
        SpringUserDetails springUserDetails = currentAuthenticatedUser();
        if (springUserDetails == null) {
            jsonResult.put("success", Boolean.FALSE);
            return jsonResult;
        }

        /*Long userId = springUserDetails.getUserId();

        if (!compTaSann.getTechnicianRegrequestUserId().equals(userId)) {
            jsonResult.put("success", Boolean.FALSE);
            return jsonResult;
        }*/


        /*
        * Update IE announcement's status
        */
        if (compTaSann.getTaSannStatus() != Constants.COMP_IE_TA_ANN_STATUS.ISSUED.getCode()) {
            jsonResult.put("success", Boolean.FALSE);
            return jsonResult;
        }


        if (response == 1) {
            compTaSann.setReqStatus(6);
        } else if (response == -1) {
            compTaSann.setReqStatus(7);
        }

        compTaSann.setTaSannStatus(response);

        /*
        * Save IE announcement
        */

        try {
            try {
                spPtlCompTaSannRepo.save(compTaSann);
                (companyStoredProcedures).procIntArchiveCompTaAnn(compTaSann); // 2/9/15 by Marios K
            } catch (Exception e) {
                spPtlCompTaSannRepo.save(compTaSann);
                e.printStackTrace();
                jsonResult.put("success", Boolean.FALSE);
                return jsonResult;
            }

            spPtlCompTaSannRepo.save(compTaSann);
            TNotificationsAccountEntity notification = new TNotificationsAccountEntity();
            notification.setDocId(new Long(-1));
            notification.setTitle("Αποδοχή Αναγγελιας Τεχνικού");

            String announcementIdentifier = new String(compTaSann.getCompAddrD() + " " + compTaSann.getCompAddr() + " TK " + compTaSann.getCompAddrTk());
            notification.setMessage("Ο Τεχνικός Ασφάλειας/ΕΞΥΠΠ " + compTaSann.getTaFullname() + " " + "[ΑΦΜ " + compTaSann.getTaAfm() + "] δέχτηκε την αναγγέλια του υποκαταστήματος " + announcementIdentifier + ".");
            notification.setSender(compTaSann.getTaFullname());
            notification.setPriority(new Long(1));
            notification.setViewed(new Long(0));

            Date todayDate = new Date();
            Timestamp today = new Timestamp(todayDate.getTime());
            notification.setDateTime(today);

            notification.setAccountId(compTaSann.getCompanyId());

            try {
                notificationsRestRepository.save(notification);
            } catch (Exception e) {
            }


            TNotificationsAccountEntity notification2 = new TNotificationsAccountEntity();
            notification2.setDocId(new Long(-1));
            notification2.setTitle("Επιτυχημένη Αποδοχή Αναγγελίας");

            TCompany cmp = companyRepository.findOne(compTaSann.getCompanyId());
            notification2.setMessage("Η αποδοχή της αναγγελίας του υποκαταστήματος " + announcementIdentifier + " της επιχείρησης " + cmp.getTitle() + " έγινε με επιτυχία, με αριθμό πρωτοκόλλου: " + compTaSann.getProtNo());
            notification2.setSender(compTaSann.getTaFullname());
            notification2.setPriority(new Long(1));
            notification2.setViewed(new Long(0));
            notification2.setDateTime(today);

            notification2.setAccountId(compTaSann.getTechnicianRegrequestUserId());

            try {
                notificationsRestRepository.save(notification2);
            } catch (Exception e) {
            }

            jsonResult.put("success", Boolean.TRUE);
            return jsonResult;

        } catch (Exception e) {
            spPtlCompTaSannRepo.save(compTaSann);

            e.printStackTrace();
            jsonResult.put("success", Boolean.FALSE);
            return jsonResult;
        }

    }

    /*
    * TA responds to the company re quest to terminate the announcement
     */
    // http://localhost:7001/portal/compTaSannCessationRespond?compTaSannId=1&response=1 [0= paraitisi, 1 = apolisy]
    @RequestMapping(value = {"/compTaSannCessationRespond"}, method = RequestMethod.POST)
    @Nonnull
    public
    @ResponseBody
    Map<String, Object> compTaSannCessationRespond(
            Long compTaSannId,
            Integer response
    ) throws Exception {
        SpringUserDetails currentUser = CurrentUserDetailsService.getCurrentUser();

        Map<String, Object> jsonResult = new HashMap<String, Object>();

        if (Constants.LongIsNullOrEmpty(compTaSannId)) {
            jsonResult.put("success", Boolean.FALSE);
            return jsonResult;
        }

        if (Constants.IntegerIsNullOrEmpty(response)) {
            jsonResult.put("success", Boolean.FALSE);
            return jsonResult;
        } else {
            if ((response != 0) && (response != 1)) {
                jsonResult.put("success", Boolean.FALSE);
                return jsonResult;
            }
        }

        /*
        * Find the TA announcement
        */
        SpPtlCompTaSann compTaSann = spPtlCompTaSannRepo.taFindOne(compTaSannId);
        if (compTaSann == null) {
            jsonResult.put("success", Boolean.FALSE);
            jsonResult.put("error", "error 1");
            return jsonResult;
        }

        /*
        * Check that this TA has access
        */
        SpringUserDetails springUserDetails = currentAuthenticatedUser();
        if (springUserDetails == null) {
            jsonResult.put("success", Boolean.FALSE);
            jsonResult.put("error", "error 2");
            return jsonResult;
        }

        Long userId = springUserDetails.getUserId();

        if (!compTaSann.getTechnicianRegrequestUserId().equals(userId)) {
            jsonResult.put("success", Boolean.FALSE);
            jsonResult.put("error", "error 3");
            return jsonResult;
        }

        /*
        * Update TA announcement's status
        */
        if (compTaSann.getTaSannStatus() != Constants.COMP_IE_TA_ANN_STATUS.EXPIRED.getCode()) {
            jsonResult.put("success", Boolean.FALSE);
            jsonResult.put("error", "error 4");
            return jsonResult;
        }

        if (compTaSann.getReqStatus() != 7) {
            jsonResult.put("success", Boolean.FALSE);
            jsonResult.put("error", "error 5");
            return jsonResult;
        }

        compTaSann.setTaResponseReplace(response);

        /*
        * Save TA announcement
        */
        try {
            spPtlCompTaSannRepo.save(compTaSann);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.put("success", Boolean.FALSE);
            jsonResult.put("error", "error 6");
            return jsonResult;
        }

        /*
        * Send message to company
         */

        //Get ship info
        SpPtlCompShip ship = spPtlCompShipRepo.findOne(compTaSann.getCompShipId());
        if (ship == null) {
            jsonResult.put("success", Boolean.FALSE);
            jsonResult.put("error", "error 5");
            return jsonResult;
        }

        String announcementIdentifier = new String(ship.getShipName() + " " + ship.getShipImo());

        SpPtlTechnicianRegrequest regRequest = technicianRegrequestRepo.findOne(compTaSann.getTechnicianRegrequestId());
        if (regRequest == null) {
            jsonResult.put("success", Boolean.FALSE);
            jsonResult.put("error", "error 8");
            return jsonResult;
        }

        TNotificationsAccountEntity notification = new TNotificationsAccountEntity();
        notification.setDocId(new Long(-1));
        notification.setTitle("Αποδοχή Παραίτησης Αναγγελιας");
        notification.setMessage("Ο Τεχνικός Ασφάλειας " + regRequest.getFirstname() + " " + regRequest.getLastname() + "[ΑΦΜ " + regRequest.getAfm() + "] δέχτηκε την παύση του απο την αναγγέλια του υποκαταστήματος " + announcementIdentifier + ".");
        notification.setSender(regRequest.getFirstname() + " " + regRequest.getLastname());
        notification.setPriority(new Long(1));
        notification.setViewed(new Long(0));

        Date todayDate = new Date();
        Timestamp today = new Timestamp(todayDate.getTime());
        notification.setDateTime(today);

        notification.setAccountId(compTaSann.getCompanyId());

        try {
            notificationsRestRepository.save(notification);
        } catch (Exception e) {
            /* Now what? Announ updated but notification was not sent */
            jsonResult.put("error", "error 9");
        }


        jsonResult.put("success", Boolean.TRUE);
        return jsonResult;
    }


    // http://localhost:7001/technicianSannBookNoteAdd?compTaSannId=1350&shipId=1261&notes=aabbababab
    @RequestMapping(value = {"/technicianSannBookNoteAdd"}, method = RequestMethod.POST)
    @Nonnull
    public
    @ResponseBody
    Map<String, Object> technicianSannBookNoteAdd(
            Long compTaSannId,
            Long shipId,
            String notes
    ) throws Exception {
        SpringUserDetails currentUser = CurrentUserDetailsService.getCurrentUser();

        Map<String, Object> jsonResult = new HashMap<String, Object>();

        if (Constants.LongIsNullOrEmpty(compTaSannId)) {
            jsonResult.put("success", Boolean.FALSE);
            return jsonResult;
        }
        if (Constants.LongIsNullOrEmpty(shipId)) {
            jsonResult.put("success", Boolean.FALSE);
            return jsonResult;
        }
        /* todo passing note instead of notes bypass this check!!!! */
        if (Constants.StringIsNullOrEmpty(notes)) {
            jsonResult.put("success", Boolean.FALSE);
            return jsonResult;
        }

        /*
        * Find the compTaSannBook
        */
        SpPtlCompTaSannBook compTaSannBook = spPtlCompTaSannBookRepo.findByCompShipId(shipId);
        if (compTaSannBook == null) {
            jsonResult.put("success", Boolean.FALSE);
            return jsonResult;
        }

        /*
        * Find the the compTaSann
        */
        SpPtlCompTaSann compTaSann = spPtlCompTaSannRepo.findOne(compTaSannId);
        if (compTaSann == null) {
            jsonResult.put("success", Boolean.FALSE);
            return jsonResult;
        }

        /*
        * Check that this TA has access
        */
        SpringUserDetails springUserDetails = currentAuthenticatedUser();
        if (springUserDetails == null) {
            jsonResult.put("success", Boolean.FALSE);
            return jsonResult;
        }

        Long userId = springUserDetails.getUserId();

        if (!compTaSann.getTechnicianRegrequestUserId().equals(userId)) {
            jsonResult.put("success", Boolean.FALSE);
            return jsonResult;
        }

        /*
        * Get TA name
        */
        SpPtlTechnicianRegrequest regReq = technicianRegrequestRepo.findOne(compTaSann.getTechnicianRegrequestId());
        if (regReq == null) {
            jsonResult.put("success", Boolean.FALSE);
            return jsonResult;
        }

        String bookNoteAuthorName = new String(regReq.getFirstname() + " " + regReq.getLastname());

        /*
        * Get current date
        */
        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();

        /*
        * Save book note
        */
        SpPtlCompTaSannBookNote sannBookNote = new SpPtlCompTaSannBookNote();
        sannBookNote.setCompanyId(compTaSannBook.getCompanyId());
        sannBookNote.setCompTaSannId(compTaSannId);
        sannBookNote.setAuthorName(bookNoteAuthorName);

        sannBookNote.setCompTaSannBook(compTaSannBook);
        Set<SpPtlCompTaSannBookNote> sannBookNotes = compTaSannBook.getCompTaSannBookNotes();
        sannBookNotes.add(sannBookNote);
        compTaSannBook.setCompTaSannBookNotes(sannBookNotes);

        sannBookNote.setNotes(notes);
        sannBookNote.setRead(0);
        sannBookNote.setReadDate(null);
        sannBookNote.setDateCreated(new java.sql.Timestamp(now.getTime()));


        try {
            spPtlCompTaSannBookRepo.save(compTaSannBook);
            jsonResult.put("success", Boolean.TRUE);
            return jsonResult;
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.put("success", Boolean.FALSE);
            return jsonResult;
        }

    }

    @RequestMapping(value = {"/updateCompPtlBranch"}, method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object> updateCompPtlBranch(
            Long companyId,
            Long ptlBranchId,
            String brDescr,
            Long brActive
    ) throws Exception {


        Map<String, Object> jsonResult = new HashMap<String, Object>();

        SpringUserDetails springUserDetails = currentAuthenticatedUser();
        if (springUserDetails == null) {
            jsonResult.put("success", Boolean.FALSE);
            return jsonResult;
        }

        System.out.println("UserId: " + springUserDetails.getUserId());
        Long userId = springUserDetails.getCompany().getId();

        if (!companyId.equals(userId)) {
            jsonResult.put("success", Boolean.FALSE);
            return jsonResult;
        }

        SpPtlCompPtlBranch spPtlCompPtlBranch = null;
        if (ptlBranchId != null) {
            spPtlCompPtlBranch = spPtlCompPtlBranchRepo.findOne(ptlBranchId);
        }

        if (spPtlCompPtlBranch != null && brDescr != null) {
            try {
                spPtlCompPtlBranch.setBrDescr(brDescr);
                spPtlCompPtlBranch.setBrActive(brActive);
                spPtlCompPtlBranchRepo.save(spPtlCompPtlBranch);

                jsonResult.put("success", Boolean.TRUE);
                return jsonResult;

            } catch (Exception e) {
                e.printStackTrace();
                jsonResult.put("success", Boolean.FALSE);
                jsonResult.put("error", Boolean.FALSE);
                return jsonResult;
            }
        } else {
            jsonResult.put("success", Boolean.FALSE);
            jsonResult.put("error", Boolean.FALSE);
            return jsonResult;
        }

    }

    @RequestMapping(value = {"/checkCompPtlBranchPrivlages"}, method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object> checkCompPtlBranchPrivlages() throws Exception {


        Map<String, Object> jsonResult = new HashMap<String, Object>();

        UserDTO userDTO = CurrentUserDetailsService.getCurrentUserByPrincipal();
        if (userDTO == null) {
            jsonResult.put("success", Boolean.FALSE);
            return jsonResult;
        }

        if (userDTO.getRole() == Constants.USER_TYPE.COMPANY_USER.getCode()) {
            TCompanyUserPrivilages priv = companyUserPrivilagesRepository.findByUser(userDTO.getId());
            ArrayList<Integer> branchList = new ArrayList(Arrays.asList(priv.getBranchIds().split("\\s*,\\s*")));
            if (null != priv.getBranchIds() && branchList.contains("0")) {
                jsonResult.put("success", Boolean.TRUE);
                return jsonResult;
            }
        } else if (userDTO.getRole() == Constants.USER_TYPE.COMPANY.getCode()) {
            jsonResult.put("success", Boolean.TRUE);
            return jsonResult;
        }

        jsonResult.put("success", Boolean.FALSE);
        return jsonResult;

    }


    @RequestMapping(value = {"/pauseTaAnnProgram"}, method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object> pauseTaAnnProgram(
            @RequestBody
                    SpPtlCompTaAnn jsonData
    ) throws Exception {


        Map<String, Object> jsonResult = new HashMap<String, Object>();

        SpringUserDetails springUserDetails = currentAuthenticatedUser();
        if (springUserDetails == null) {
            jsonResult.put("success", Boolean.FALSE);
            return jsonResult;
        }

        Long userId = springUserDetails.getCompany().getId();

        if (!jsonData.getCompanyId().equals(userId)) {
            jsonResult.put("success", Boolean.FALSE);
            return jsonResult;
        }

        SpPtlCompTaAnn spPtlCompTaAnnAfterSubmit = spPtlCompTaAnnRepo.findOne(jsonData.getId());

        /*
        * Check that the status of the announcement allows resignation
        */
        if ((spPtlCompTaAnnAfterSubmit.getReqStatus() != 6) || (spPtlCompTaAnnAfterSubmit.getTaAnnStatus() != Constants.COMP_IE_TA_ANN_STATUS.ACCEPTED.getCode())) {
            jsonResult.put("success", Boolean.FALSE);
            jsonResult.put("error", "error 3");
            return jsonResult;
        }

        //  ΠΕΡΙΠΤΩΣΗ ΠΑΥΣΗΣ
        if (spPtlCompTaAnnAfterSubmit.getSubStatus() == Constants.SUBMIT_STATUS.SUBMITTED.getCode()) {
            try {
                // Save pause explanation field value
                spPtlCompTaAnnAfterSubmit.setPauseExplanation(jsonData.getPauseExplanation());
                // Set pause attached doc id
                spPtlCompTaAnnAfterSubmit.setAttachedDocIdPause(jsonData.getAttachedDocIdPause());
                // Call Procedure
                spPtlCompTaAnnAfterSubmit = (companyStoredProcedures).procIntArchiveCompTaAnnPause(spPtlCompTaAnnAfterSubmit);

                /*
                * Update end date
                */
                Calendar calendar = Calendar.getInstance();
                java.util.Date now = calendar.getTime();
                spPtlCompTaAnnAfterSubmit.setDateEnd(new java.sql.Timestamp(now.getTime()));
                spPtlCompTaAnnAfterSubmit.setTaAnnStatus(Constants.COMP_IE_TA_ANN_STATUS.PAUSED.getCode());
                //spPtlCompTaAnnAfterSubmit.setReqStatus(Constants.REQUEST_STATUS.REJECTED.getCode());
                spPtlCompTaAnnRepo.save(spPtlCompTaAnnAfterSubmit);

                if (spPtlCompTaAnnAfterSubmit.getProtNo() != null) {

                    if (spPtlCompTaAnnAfterSubmit.getCompTaAnnTaEntries().size() > 0) {

                        SpPtlCompPtlBranch ptlBranch = spPtlCompPtlBranchRepo.findById(spPtlCompTaAnnAfterSubmit.getCompPtlBranchId());
                        String announcementIdentifier = new String(ptlBranch.getBrDescr() + " " + ptlBranch.getBrAddr() + " TK " + ptlBranch.getBrAddrTk());

                        Set<SpPtlCompTaAnnTaEntry> spPtlCompTaAnnTaEntry2 = spPtlCompTaAnnAfterSubmit.getCompTaAnnTaEntries();
                        Iterator<SpPtlCompTaAnnTaEntry> interateTas = spPtlCompTaAnnTaEntry2.iterator();
                        while (interateTas.hasNext()) {
                            SpPtlCompTaAnnTaEntry next = interateTas.next();
                            TNotificationsAccountEntity notification = new TNotificationsAccountEntity();
                            notification.setDocId(new Long(-1));
                            notification.setTitle("Παύση εργασίας");
                            notification.setMessage("Έγινε παύση εργασίας σας από το υποκατάστημα "+ announcementIdentifier + " της εταιρείας " + spPtlCompTaAnnAfterSubmit.getCompFullName() + " με αριθμό πρωτοκόλλου: " + spPtlCompTaAnnAfterSubmit.getProtNoPause() + ". Για περισσότερες λεπτομέρειες πλοηγηθείτε στο μενού αριστερά με την ένδειξη 'Εκκρεμούσες αναγγελίες'.");
                            notification.setSender(spPtlCompTaAnnAfterSubmit.getCompFullName());
                            notification.setPriority(new Long(1));
                            notification.setViewed(new Long(0));
                            Date todayDate = new Date();
                            Timestamp today = new Timestamp(todayDate.getTime());
                            notification.setDateTime(today);

                            if (next.getCooperationType() != 3) {
                                TSafetyTechnician safetyPreviousTechnician = safetyTechnicianRepository.findByAfmEquals(next.getTaAfm());
                                notification.setAccountId(safetyPreviousTechnician.getUserId());

                                try {
                                    notificationsRestRepository.save(notification);
                                    TUser user = userRepository.findOne(safetyPreviousTechnician.getUserId());
                                    mailService.sendNotification(user.getEmail(), user.getEmailNotifEn(), "Παύση εργασίας", "Έγινε παύση εργασίας σας από το υποκατάστημα "+ announcementIdentifier + " της εταιρείας " + spPtlCompTaAnnAfterSubmit.getCompFullName() + " με αριθμό πρωτοκόλλου: " + spPtlCompTaAnnAfterSubmit.getProtNoPause() + ". Για περισσότερες λεπτομέρειες πλοηγηθείτε στο μενού αριστερά με την ένδειξη 'Εκκρεμούσες αναγγελίες'.");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            } else {
                                TCompany safetyPreviousTechnician = companyRepository.findByUserId(next.getTechnicianRegrequestUserId());
                                notification.setAccountId(safetyPreviousTechnician.getUserId());

                                try {
                                    notificationsRestRepository.save(notification);
                                    TUser user = userRepository.findOne(safetyPreviousTechnician.getUserId());
                                    mailService.sendNotification(user.getEmail(), user.getEmailNotifEn(), "Παύση εργασίας", "Έγινε παύση εργασίας σας από το υποκατάστημα "+ announcementIdentifier + " της εταιρείας " + spPtlCompTaAnnAfterSubmit.getCompFullName() + " με αριθμό πρωτοκόλλου: " + spPtlCompTaAnnAfterSubmit.getProtNoPause() + ". Για περισσότερες λεπτομέρειες πλοηγηθείτε στο μενού αριστερά με την ένδειξη 'Εκκρεμούσες αναγγελίες'.");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }
                        }
                    }
                }
                jsonResult.put("success", Boolean.TRUE);
                return jsonResult;

            } catch (Exception e) {
                e.printStackTrace();
                jsonResult.put("success", Boolean.FALSE);
                jsonResult.put("error", Boolean.FALSE);
                return jsonResult;
            }
        } else {
            jsonResult.put("success", Boolean.FALSE);
            jsonResult.put("error", Boolean.FALSE);
            return jsonResult;
        }

    }

    @RequestMapping(value = {"/updateTaAnnProgram"}, method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object> updateTaAnnProgram(
            @RequestBody
                    SpPtlCompTaAnn jsonData
    ) throws Exception {


        Map<String, Object> jsonResult = new HashMap<String, Object>();

        SpringUserDetails springUserDetails = currentAuthenticatedUser();
        if (springUserDetails == null) {
            jsonResult.put("success", Boolean.FALSE);
            return jsonResult;
        }

        Long userId = springUserDetails.getCompany().getId();

        if (!jsonData.getCompanyId().equals(userId)) {
            jsonResult.put("success", Boolean.FALSE);
            return jsonResult;
        }

        try {
            SpPtlCompTaAnnDiaryEntryDTO[] spPtlCompTaAnnDiaryEntry = jsonData.getDiaryEntries();
            if (null != spPtlCompTaAnnDiaryEntry) {
                boolean totalMinutesConficts = false;
                int totalM = 0;
                try {
                    for (int i = 0; i < spPtlCompTaAnnDiaryEntry.length; i++) {
                        totalM = totalM + spPtlCompTaAnnDiaryEntry[i].getVisitDurationMinutes();
                    }
                    int mins = spPtlCompTaAnnDiaryEntryRepo.sumOfTaAnnMinutes(jsonData.getId()).intValue();
                    if (totalM < mins) {
                        totalMinutesConficts = true;
                    }
                } catch (Exception ex) {
                    jsonResult.put("success", Boolean.FALSE);
                    return jsonResult;
                }


                if (totalMinutesConficts == true) {
                    jsonResult.put("success", Boolean.FALSE);
                    jsonResult.put("error", Boolean.TRUE);
                    jsonResult.put("errorMsg", "Οι συνολικές ώρες πρέπει να είναι περισσότερες ή ίδιες με τις ώρες πριν την αλλαγή.");
                    return jsonResult;
                }
            }
        } catch (Exception ex) {
            throw new SepeRestException("Σφάλμα στον υπολογισμό ωρών.");
        }
        SpPtlCompTaAnn actualAnn = spPtlCompTaAnnRepo.findOne(jsonData.getId());
        Set<SpPtlCompTaAnnDiaryEntry> spPtlCompTaAnnDiaryEntryPrev = actualAnn.getCompTaAnnDiaryEntries();
        SpPtlCompTaAnn actualAnnOld = actualAnn;
        try {
            SpPtlCompTaAnnDiaryEntryDTO[] spPtlCompTaAnnDiaryEntry = jsonData.getDiaryEntries();
            if (null != spPtlCompTaAnnDiaryEntry) {
                boolean datesConficts = false;
                String conflicts = "Οι ημερομηνίες που επικαλύπτονται: ";
                StringBuilder conflictsConcat = new StringBuilder();
                try {
                    SpPtlCompTaAnnTaEntryDTO[] spPtlCompTaAnnTaEntry = jsonData.getTaEntries();

                    int daySec = 1 * 24 * 60 * 60 * 1000;
                    Long dateStart = new Date(jsonData.getDateStart().getTime()).getTime() / 1000 - daySec;
                    Long dateEnd = new Date(jsonData.getDateEnd().getTime()).getTime() / 1000 + daySec;
                        /*List<Long> ieVisitConflictQueryTechnicianRegrequestUserId = spPtlVCompTaAnnDiaryBRepo.taVisitConflict1(dateStart, dateEnd);
                        List<Integer> ieVisitConflictQueryVisitDurationMinutes = spPtlVCompTaAnnDiaryBRepo.taVisitConflict3(dateStart, dateEnd);
                        List<Long> ieVisitConflictQueryTaAnnId = spPtlVCompTaAnnDiaryBRepo.taVisitConflict2(dateStart, dateEnd);
                        List<Date> ieVisitConflictQueryVisitDate = spPtlVCompTaAnnDiaryBRepo.taVisitConflict4(dateStart, dateEnd);
                        List<String> ieVisitConflictQueryVisitTime = spPtlVCompTaAnnDiaryBRepo.taVisitConflict5(dateStart, dateEnd);*/
                    String dates = "";
                    int cnt = 0;
                    for (SpPtlCompTaAnnDiaryEntryDTO i : jsonData.getDiaryEntries()) {
                        if (cnt == 0)
                            dates = dates + "'" + new SimpleDateFormat("dd-MM-yyyy").format(new Date(i.getVisitDate().getTime())) + "','" + new SimpleDateFormat("dd-MM-yyyy").format(new Date(i.getVisitDate().getTime() + daySec)) + "'";
                        else if (!dates.contains(new SimpleDateFormat("dd-MM-yyyy").format(new Date(i.getVisitDate().getTime()))))
                            dates = dates + ",'" + new SimpleDateFormat("dd-MM-yyyy").format(new Date(i.getVisitDate().getTime())) + "','" + new SimpleDateFormat("dd-MM-yyyy").format(new Date(i.getVisitDate().getTime() + daySec)) + "'";
                        cnt++;
                    }
                    List<SpPtlVCompTaAnnDiaryB> ieVisitConflictQuery = null;
                    List<Long> techUserIds = new ArrayList<>();
                    Date date;
                    Long lVisitDate, lVisitTime, dateAndTime, durationMins, technicianRegrequestUserId, visitDateEpochSec, conflictVisitDate, conflictVisitTime, conflictTaAnnId, taAnnId;
                    Integer visitDurationMinutes;
                    int offset, conflictOffset;
                    taAnnId = jsonData.getId();
                    for (int i = 0; i < spPtlCompTaAnnDiaryEntry.length; i++) {

                        if (!techUserIds.contains(spPtlCompTaAnnDiaryEntry[i].getCompTaAnnTaId())) {
                            techUserIds.add(spPtlCompTaAnnDiaryEntry[i].getCompTaAnnTaId());
                            ieVisitConflictQuery = entityManager.createNativeQuery("SELECT * FROM SP_PTL_V_COMP_TA_ANN_DIARY_B WHERE TO_CHAR(VISIT_DATE, 'DD-MM-YYYY') IN (" + dates + ") AND TECHNICIAN_REGREQUEST_USER_ID = " + spPtlCompTaAnnDiaryEntry[i].getCompTaAnnTaId() + " ORDER BY VISIT_DATE ASC", SpPtlVCompTaAnnDiaryB.class).getResultList();
                        }

                        if (techUserIds.contains(spPtlCompTaAnnDiaryEntry[i].getCompTaAnnTaId()) && techUserIds.indexOf(spPtlCompTaAnnDiaryEntry[i].getCompTaAnnTaId()) != techUserIds.size() - 1)
                            ieVisitConflictQuery = entityManager.createNativeQuery("SELECT * FROM SP_PTL_V_COMP_TA_ANN_DIARY_B WHERE TO_CHAR(VISIT_DATE, 'DD-MM-YYYY') IN (" + dates + ") AND TECHNICIAN_REGREQUEST_USER_ID = " + spPtlCompTaAnnDiaryEntry[i].getCompTaAnnTaId() + " ORDER BY VISIT_DATE ASC", SpPtlVCompTaAnnDiaryB.class).getResultList();


                        date = new Date(spPtlCompTaAnnDiaryEntry[i].getVisitDate().getTime());
                        offset = -date.getTimezoneOffset() / 60;
                        lVisitDate = date.getTime() / 1000;
                        lVisitTime = Time.valueOf(spPtlCompTaAnnDiaryEntry[i].getVisitTime() + ":00").getTime() / 1000;
                        if (offset == 3)
                            dateAndTime = lVisitDate + lVisitTime - 3600; //remove 1 hour
                        else
                            dateAndTime = lVisitDate + lVisitTime;
                        durationMins = new Long(spPtlCompTaAnnDiaryEntry[i].getVisitDurationMinutes());

                        for (int j = 0; j < ieVisitConflictQuery.size(); j++) {
                            technicianRegrequestUserId = ieVisitConflictQuery.get(j).getTechnicianRegrequestUserId();

                            conflictVisitDate = (long)Math.round((ieVisitConflictQuery.get(j).getVisitDate().getTime() / 1000) / 86400) * 86400;
                            conflictVisitTime = Time.valueOf(ieVisitConflictQuery.get(j).getVisitTime() + ":00").getTime() / 1000;
                            conflictOffset = conflictVisitDate.equals(lVisitDate) ? offset : -ieVisitConflictQuery.get(j).getVisitDate().getTimezoneOffset() / 60;
                            if (conflictOffset == 3)
                                visitDateEpochSec = conflictVisitDate + conflictVisitTime - 3600; //remove 1 hour
                            else
                                visitDateEpochSec = conflictVisitDate + conflictVisitTime;

                            visitDurationMinutes = ieVisitConflictQuery.get(j).getVisitDurationMinutes();
                            conflictTaAnnId = ieVisitConflictQuery.get(j).getCompTaAnnId();

                            if ((!conflictTaAnnId.equals(taAnnId)) && technicianRegrequestUserId == (long) spPtlCompTaAnnDiaryEntry[i].getCompTaAnnTaId() && ((dateAndTime <= visitDateEpochSec && (dateAndTime + (durationMins * 60)) > visitDateEpochSec) ||
                                    (dateAndTime >= visitDateEpochSec && (dateAndTime + (durationMins * 60)) <= (visitDateEpochSec + visitDurationMinutes * 60)) ||
                                    (dateAndTime < (visitDateEpochSec + visitDurationMinutes * 60) && (dateAndTime + (durationMins * 60)) >= (visitDateEpochSec + visitDurationMinutes * 60)))) {
                                datesConficts = true;
                                conflictsConcat.append(i);
                                conflictsConcat.append(" ");
                                break;
                            }
                        }
                    }
                    conflicts += conflictsConcat.toString();
                } catch (Exception ex) {
                    jsonResult.put("success", Boolean.FALSE);
                    jsonResult.put("error", Boolean.FALSE);
                    return jsonResult;
                }


                if (datesConficts == true) {
                    throw new SepeRestException(conflicts);
                } else {
                    boolean datesConfictsCurrent = false;
                    try {
                        int offset, offset2;
                        Long dateAndTime, dateAndTime2;
                        for (int i = 0; i < spPtlCompTaAnnDiaryEntry.length; i++) {
                            Date date = new Date(spPtlCompTaAnnDiaryEntry[i].getVisitDate().getTime());
                            Long lVisitDate = date.getTime();
                            Long lVisitTime = Time.valueOf(spPtlCompTaAnnDiaryEntry[i].getVisitTime() + ":00").getTime();
                            offset = -date.getTimezoneOffset()/60;
                            if (offset == 3)
                                dateAndTime = lVisitDate + lVisitTime - (3600 * 1000); //remove 1 hour
                            else
                                dateAndTime = lVisitDate + lVisitTime;
                            Date dateOne = new Date(dateAndTime);
                            Date dateOneEnd = new Date(dateAndTime + spPtlCompTaAnnDiaryEntry[i].getVisitDurationMinutes() * 60000);
                            for (int ii = 0; ii < spPtlCompTaAnnDiaryEntry.length; ii++) {
                                if (i != ii && spPtlCompTaAnnDiaryEntry[i].getCompTaAnnTaId().equals(spPtlCompTaAnnDiaryEntry[ii].getCompTaAnnTaId())) {
                                    Date dateN2 = new Date(spPtlCompTaAnnDiaryEntry[ii].getVisitDate().getTime());
                                    Long lVisitDate2 = dateN2.getTime();
                                    Long lVisitTime2 = Time.valueOf(spPtlCompTaAnnDiaryEntry[ii].getVisitTime() + ":00").getTime();
                                    offset2 = -dateN2.getTimezoneOffset()/60;
                                    if (offset2 == 3)
                                        dateAndTime2 = lVisitDate2 + lVisitTime2 - (3600 * 1000); //remove 1 hour
                                    else
                                        dateAndTime2 = lVisitDate2 + lVisitTime2;
                                    Date dateTwo = new Date(dateAndTime2);
                                    Date dateTwoEnd = new Date(dateAndTime2 + spPtlCompTaAnnDiaryEntry[ii].getVisitDurationMinutes() * 60000);

                                    if (dateOne.before(dateTwoEnd) && dateTwo.before(dateOneEnd)) {
                                        datesConfictsCurrent = true;
                                    }
                                }
                            }

                        }
                    } catch (Exception ex) {
                        throw new SepeRestException("Υπάρχει επικάλυψη στο ωράριο που δηλώσατε.");
                    }


                    if (datesConfictsCurrent == true) {
                        jsonResult.put("success", Boolean.FALSE);
                        jsonResult.put("error", Boolean.TRUE);
                        jsonResult.put("errorMsg", "Υπάρχει επικάλυψη στο ωράριο που δηλώσατε εντός ίδιας μέρας.");
                        return jsonResult;
                    } else {
                        HashSet<SpPtlCompTaAnnDiaryEntry> compTaAnnDiaryEntry = new HashSet<SpPtlCompTaAnnDiaryEntry>();
                        List<SpPtlCompTaAnnDiaryEntry> ieAnnDiaryEntryList = new ArrayList<>();
                        ieAnnDiaryEntryList.addAll(actualAnnOld.getCompTaAnnDiaryEntries());
                        for (int i = 0; i < spPtlCompTaAnnDiaryEntry.length; i++) {
                            SpPtlCompTaAnnDiaryEntry ieAnnDiaryEntry;
                            if (i < ieAnnDiaryEntryList.size())
                                ieAnnDiaryEntry = ieAnnDiaryEntryList.get(i);
                            else
                                ieAnnDiaryEntry = new SpPtlCompTaAnnDiaryEntry();
                            ieAnnDiaryEntry.setCompanyId(actualAnn.getCompanyId());
                            ieAnnDiaryEntry.setCompTaAnn(actualAnn);
                            ieAnnDiaryEntry.setVisitTime(spPtlCompTaAnnDiaryEntry[i].getVisitTime());
                            ieAnnDiaryEntry.setVisitDurationMinutes(spPtlCompTaAnnDiaryEntry[i].getVisitDurationMinutes());
                            ieAnnDiaryEntry.setVisitDate(spPtlCompTaAnnDiaryEntry[i].getVisitDate());
                            ieAnnDiaryEntry.setCompTaAnnTaId(spPtlCompTaAnnDiaryEntry[i].getCompTaAnnTaId());
                            ieAnnDiaryEntry.setIsSubmitted(2);
                            compTaAnnDiaryEntry.add(ieAnnDiaryEntry);
                        }
                        actualAnn.setCompTaAnnDiaryEntries(compTaAnnDiaryEntry);
                    }

                }
            }
        } catch (SepeRestException ex) {
            spPtlCompTaAnnRepo.save(actualAnnOld);
            throw new SepeRestException(ex.getMessage());
        }
        try {
            spPtlCompTaAnnRepo.save(actualAnn);
            if (actualAnn.getProtNo() != null) {

                if (actualAnn.getCompTaAnnTaEntries().size() > 0) {

                    Set<SpPtlCompTaAnnTaEntry> spPtlCompTaAnnTaEntry2 = actualAnn.getCompTaAnnTaEntries();
                    Iterator<SpPtlCompTaAnnTaEntry> interateTas = spPtlCompTaAnnTaEntry2.iterator();
                    while (interateTas.hasNext()) {
                        SpPtlCompTaAnnTaEntry next = interateTas.next();
                        TNotificationsAccountEntity notification = new TNotificationsAccountEntity();
                        notification.setDocId(new Long(-1));
                        notification.setTitle("Αλλαγή Προγράμματος");
                        notification.setMessage("Έχει γίνει κάποια αλλαγή στο πρόγραμμα από την εταιρεία " + actualAnn.getCompFullName() + ". Για περισσότερες λεπτομέρειες πλοηγηθείτε στο μενού αριστερά με την ένδειξη 'Πρόγραμμα Επισκέψεων'.");
                        notification.setSender(actualAnn.getCompFullName());
                        notification.setPriority(new Long(1));
                        notification.setViewed(new Long(0));
                        Date todayDate = new Date();
                        Timestamp today = new Timestamp(todayDate.getTime());
                        notification.setDateTime(today);

                        if (next.getCooperationType() != 3) {
                            TSafetyTechnician safetyPreviousTechnician = safetyTechnicianRepository.findByAfmEquals(next.getTaAfm());
                            notification.setAccountId(safetyPreviousTechnician.getUserId());

                            try {
                                notificationsRestRepository.save(notification);
                                TUser user = userRepository.findOne(safetyPreviousTechnician.getUserId());
                                mailService.sendNotification(user.getEmail(), user.getEmailNotifEn(), "Αλλαγή Προγράμματος", "Έχει γίνει κάποια αλλαγή στο πρόγραμμα από την εταιρεία " + actualAnn.getCompFullName() + ". Για περισσότερες λεπτομέρειες πλοηγηθείτε στο μενού αριστερά με την ένδειξη 'Πρόγραμμα Επισκέψεων'.");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            TCompany safetyPreviousTechnician = companyRepository.findByUserId(next.getTechnicianRegrequestUserId());
                            notification.setAccountId(safetyPreviousTechnician.getUserId());

                            try {
                                notificationsRestRepository.save(notification);
                                TUser user = userRepository.findOne(safetyPreviousTechnician.getUserId());
                                mailService.sendNotification(user.getEmail(), user.getEmailNotifEn(), "Αλλαγή Προγράμματος", "Έχει γίνει κάποια αλλαγή στο πρόγραμμα από την εταιρεία " + actualAnn.getCompFullName() + ". Για περισσότερες λεπτομέρειες πλοηγηθείτε στο μενού αριστερά με την ένδειξη 'Πρόγραμμα Επισκέψεων'.");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
            jsonResult.put("success", Boolean.TRUE);
            return jsonResult;
        } catch (Exception ex) {
            jsonResult.put("success", Boolean.FALSE);
            jsonResult.put("error", Boolean.FALSE);
            return jsonResult;
        }
    }

    @RequestMapping(value = {"/updateIeAnnProgram"}, method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object> updateIeAnnProgram(
            @RequestBody
                    SpPtlCompIeAnn jsonData
    ) throws Exception {


        Map<String, Object> jsonResult = new HashMap<String, Object>();

        SpringUserDetails springUserDetails = currentAuthenticatedUser();
        if (springUserDetails == null) {
            jsonResult.put("success", Boolean.FALSE);
            return jsonResult;
        }

        Long userId = springUserDetails.getCompany().getId();

        if (!jsonData.getCompanyId().equals(userId)) {
            jsonResult.put("success", Boolean.FALSE);
            return jsonResult;
        }

        try {
            SpPtlCompIeAnnDiaryEntryDTO[] spPtlCompIeAnnDiaryEntry = jsonData.getDiaryEntries();
            if (null != spPtlCompIeAnnDiaryEntry) {
                boolean totalMinutesConficts = false;
                int totalM = 0;
                try {
                    for (int i = 0; i < spPtlCompIeAnnDiaryEntry.length; i++) {
                        totalM = totalM + spPtlCompIeAnnDiaryEntry[i].getVisitDurationMinutes();
                    }
                    int mins = spPtlCompIeAnnDiaryEntryRepo.sumOfIeAnnMinutes(jsonData.getId()).intValue();
                    if (totalM < mins) {
                        totalMinutesConficts = true;
                    }
                } catch (Exception ex) {
                    jsonResult.put("success", Boolean.FALSE);
                    return jsonResult;
                }


                if (totalMinutesConficts == true) {
                    jsonResult.put("success", Boolean.FALSE);
                    jsonResult.put("error", Boolean.TRUE);
                    jsonResult.put("errorMsg", "Οι συνολικές ώρες πρέπει να είναι περισσότερες ή ίδιες με τις ώρες πριν την αλλαγή.");
                    return jsonResult;
                }
            }
        } catch (Exception ex) {
            throw new SepeRestException("Σφάλμα στον υπολογισμό ωρών.");
        }
        SpPtlCompIeAnn actualAnn = spPtlCompIeAnnRepo.findOne(jsonData.getId());
        Set<SpPtlCompIeAnnDiaryEntry> spPtlCompIeAnnDiaryEntryPrev = actualAnn.getCompIeAnnDiaryEntries();
        SpPtlCompIeAnn actualAnnOld = actualAnn;
        try {
            SpPtlCompIeAnnDiaryEntryDTO[] spPtlCompIeAnnDiaryEntry = jsonData.getDiaryEntries();
            if (null != spPtlCompIeAnnDiaryEntry) {
                boolean datesConficts = false;
                String conflicts = "Οι ημερομηνίες που επικαλύπτονται: ";
                try {
                    for (int i = 0; i < spPtlCompIeAnnDiaryEntry.length; i++) {
                        if (spPtlCompIeAnnDiaryEntry[i].getVisitDate().after(new Timestamp(new Date().getTime()))) {
                            Date date = new Date(spPtlCompIeAnnDiaryEntry[i].getVisitDate().getTime());
                            Long lVisitDate = date.getTime() / 1000;
                            Long lVisitTime = Time.valueOf(spPtlCompIeAnnDiaryEntry[i].getVisitTime() + ":00").getTime() / 1000;
                            Long dateAndTime = lVisitDate + lVisitTime - 3600; //remove 1 hour
                            List<Long> ieVisitConflicts = spPtlVCompIeAnnDiaryRepo.ieVisitConflictNotOne(spPtlCompIeAnnDiaryEntry[i].getCompIeAnnIeId(), dateAndTime, new Long(spPtlCompIeAnnDiaryEntry[i].getVisitDurationMinutes()), new Long(jsonData.getId()));

                            if (ieVisitConflicts.size() > 0) {
                                Long ieVisitConflict = ieVisitConflicts.get(0);

                                if (null != ieVisitConflict && ieVisitConflict > 0) {
                                    datesConficts = true;
                                    conflicts += i + " ";
                                }
                            }
                        }

                    }
                } catch (Exception ex) {
                    jsonResult.put("success", Boolean.FALSE);
                    jsonResult.put("error", Boolean.FALSE);
                    return jsonResult;
                }


                if (datesConficts == true) {
                    throw new SepeRestException(conflicts);
                } else {
                    boolean datesConfictsCurrent = false;
                    try {
                        int offset, offset2;
                        Long dateAndTime, dateAndTime2;
                        for (int i = 0; i < spPtlCompIeAnnDiaryEntry.length; i++) {
                            Date date = new Date(spPtlCompIeAnnDiaryEntry[i].getVisitDate().getTime());
                            Long lVisitDate = date.getTime();
                            Long lVisitTime = Time.valueOf(spPtlCompIeAnnDiaryEntry[i].getVisitTime() + ":00").getTime();
                            offset = -date.getTimezoneOffset()/60;
                            if (offset == 3)
                                dateAndTime = lVisitDate + lVisitTime - (3600 * 1000); //remove 1 hour
                            else
                                dateAndTime = lVisitDate + lVisitTime;
                            Date dateOne = new Date(dateAndTime);
                            Date dateOneEnd = new Date(dateAndTime + spPtlCompIeAnnDiaryEntry[i].getVisitDurationMinutes() * 60000);
                            for (int ii = 0; ii < spPtlCompIeAnnDiaryEntry.length; ii++) {
                                if (i != ii && spPtlCompIeAnnDiaryEntry[i].getCompIeAnnIeId().equals(spPtlCompIeAnnDiaryEntry[ii].getCompIeAnnIeId())) {
                                    Date dateN2 = new Date(spPtlCompIeAnnDiaryEntry[ii].getVisitDate().getTime());
                                    Long lVisitDate2 = dateN2.getTime();
                                    Long lVisitTime2 = Time.valueOf(spPtlCompIeAnnDiaryEntry[ii].getVisitTime() + ":00").getTime();
                                    offset2 = -dateN2.getTimezoneOffset()/60;
                                    if (offset2 == 3)
                                        dateAndTime2 = lVisitDate2 + lVisitTime2 - (3600 * 1000); //remove 1 hour
                                    else
                                        dateAndTime2 = lVisitDate2 + lVisitTime2;
                                    Date dateTwo = new Date(dateAndTime2);
                                    Date dateTwoEnd = new Date(dateAndTime2 + spPtlCompIeAnnDiaryEntry[ii].getVisitDurationMinutes() * 60000);

                                    if (dateOne.before(dateTwoEnd) && dateTwo.before(dateOneEnd)) {
                                        datesConfictsCurrent = true;
                                    }
                                }
                            }

                        }
                    } catch (Exception ex) {
                        throw new SepeRestException("Υπάρχει επικάλυψη στο ωράριο που δηλώσατε.");
                    }


                    if (datesConfictsCurrent == true) {
                        jsonResult.put("success", Boolean.FALSE);
                        jsonResult.put("error", Boolean.TRUE);
                        jsonResult.put("errorMsg", "Υπάρχει επικάλυψη στο ωράριο που δηλώσατε εντός ίδιας μέρας.");
                        return jsonResult;
                    } else {
                        HashSet<SpPtlCompIeAnnDiaryEntry> compIeAnnDiaryEntry = new HashSet<SpPtlCompIeAnnDiaryEntry>();
                        for (int i = 0; i < spPtlCompIeAnnDiaryEntry.length; i++) {
                            SpPtlCompIeAnnDiaryEntry ieAnnDiaryEntry = new SpPtlCompIeAnnDiaryEntry();
                            ieAnnDiaryEntry.setCompanyId(jsonData.getCompanyId());
                            ieAnnDiaryEntry.setCompIeAnn(actualAnn);
                            ieAnnDiaryEntry.setVisitTime(spPtlCompIeAnnDiaryEntry[i].getVisitTime());
                            ieAnnDiaryEntry.setVisitDurationMinutes(spPtlCompIeAnnDiaryEntry[i].getVisitDurationMinutes());
                            ieAnnDiaryEntry.setVisitDate(spPtlCompIeAnnDiaryEntry[i].getVisitDate());
                            ieAnnDiaryEntry.setCompIeAnnIeId(spPtlCompIeAnnDiaryEntry[i].getCompIeAnnIeId());
                            compIeAnnDiaryEntry.add(ieAnnDiaryEntry);
                        }
                        actualAnn.setCompIeAnnDiaryEntries(compIeAnnDiaryEntry);
                    }

                }
            }
        } catch (SepeRestException ex) {
            spPtlCompIeAnnRepo.save(actualAnnOld);
            throw new SepeRestException(ex.getMessage());
        }
        try {
            spPtlCompIeAnnRepo.save(actualAnn);
            if (actualAnn.getProtNo() != null) {

                if (actualAnn.getCompIeAnnIeEntries().size() > 0) {

                    Set<SpPtlCompIeAnnIeEntry> spPtlCompIeAnnIeEntry2 = actualAnn.getCompIeAnnIeEntries();
                    Iterator<SpPtlCompIeAnnIeEntry> interateIes = spPtlCompIeAnnIeEntry2.iterator();
                    while (interateIes.hasNext()) {
                        SpPtlCompIeAnnIeEntry next = interateIes.next();
                        TNotificationsAccountEntity notification = new TNotificationsAccountEntity();
                        notification.setDocId(new Long(-1));
                        notification.setTitle("Αλλαγή Προγράμματος");
                        notification.setMessage("Έχει γίνει κάποια αλλαγή στο πρόγραμμα από την εταιρεία " + actualAnn.getCompFullName() + ". Για περισσότερες λεπτομέρειες πλοηγηθείτε στο μενού αριστερά με την ένδειξη 'Εκκρεμούσες αναγγελίες'.");
                        notification.setSender(actualAnn.getCompFullName());
                        notification.setPriority(new Long(1));
                        notification.setViewed(new Long(0));
                        Date todayDate = new Date();
                        Timestamp today = new Timestamp(todayDate.getTime());
                        notification.setDateTime(today);

                        if (next.getCooperationType() != 3) {
                            TWorkplaceDoctor tWorkplaceDoctor = workplaceDoctorRepository.findByAfmEquals(next.getIeAfm());
                            notification.setAccountId(tWorkplaceDoctor.getUserId());

                            notificationsRestRepository.save(notification);
                        } else {
                            TCompany safetyPreviousTechnician = companyRepository.findByUserId(next.getDoctorRegrequestUserId());
                            notification.setAccountId(safetyPreviousTechnician.getUserId());

                            notificationsRestRepository.save(notification);
                        }
                    }
                }
            }
            jsonResult.put("success", Boolean.TRUE);
            return jsonResult;
        } catch (Exception ex) {
            jsonResult.put("success", Boolean.FALSE);
            jsonResult.put("error", Boolean.FALSE);
            return jsonResult;
        }
    }

    @RequestMapping(value = {"/userExtraInfo/editUserCompany"}, method = RequestMethod.POST)
    @Nonnull
    public
    @ResponseBody
    Map<String, Object> editUserCompany(
            Long id, String email
    ) throws Exception {
        SpringUserDetails currentUser = CurrentUserDetailsService.getCurrentUser();

        Map<String, Object> jsonResult = new HashMap<String, Object>();

        TUser user = userRepository.findOne(id);
        if (user == null) {
            jsonResult.put("success", Boolean.FALSE);
            jsonResult.put("error", "Ο χρήστης με το συγκεκριμένο ID δεν είναι καταχωρημένος στο μητρώο.");
            return jsonResult;
        } else {
            if (EmailValidator.validateEmail(email) == false) {
                jsonResult.put("success", Boolean.FALSE);
                jsonResult.put("error", "Το email που δηλώσατε δεν είναι έγκυρο.");
                return jsonResult;
            } else {
                user.setEmail(email);

                Integer funcChangeEmailResult;
                Map<String, Object> changeEmailInput = new LinkedHashMap<String, Object>();
                changeEmailInput.put("p_username", user.getUsername());
                changeEmailInput.put("p_newemail", email);
                // call function to change email of a specific user in Oracle Identity Management / LDAP
                funcChangeEmailResult = (userStoredProcedures).secOimCallFunction(changeEmailInput, "PCG_SEC_OIM.SEC_OIM_CHANGE_EMAIL");
                if (funcChangeEmailResult != 0) {
                    jsonResult.put("success", Boolean.FALSE);
                    jsonResult.put("error", "Προέκυψε κάποιο πρόβλημα κατά την αλλαγή του email.");
                    return jsonResult;
                } else {
                    try {
                        userRepository.save(user);
                    } catch (Exception ex) {
                        jsonResult.put("success", Boolean.FALSE);
                        jsonResult.put("error", Boolean.FALSE);
                        return jsonResult;
                    }
                    String newpassword = "p1" + generateRandomPassword(6);
                    Integer funcResetPassResult = -1;
                    try {
                        Map<String, Object> resetPassInput = new LinkedHashMap<String, Object>();
                        resetPassInput.put("p_username", user.getUsername());
                        resetPassInput.put("p_new_pass", newpassword);

                        // call function to Reset password of a specific user in Oracle Identity Management / LDAP
                        funcResetPassResult = (userStoredProcedures).secOimCallFunction(resetPassInput, "PCG_SEC_OIM.SEC_OIM_RESET_PASS");
                    } catch (Exception e) {
                        jsonResult.put("success", Boolean.FALSE);
                        jsonResult.put("error", "Προέκυψε κάποιο πρόβλημα στην διαδικασία αλλαγής του νέου κωδικού.");
                        return jsonResult;
                    }
                    if (funcResetPassResult != 0) {
                        jsonResult.put("success", Boolean.FALSE);
                        jsonResult.put("error", "Προέκυψε κάποιο πρόβλημα με την αποστολή του νέου κωδικού.");
                        return jsonResult;
                    } else {
                        mailService.passwordReminder(user.getUsername(), newpassword, user.getEmail());
                        jsonResult.put("success", Boolean.TRUE);
                    }
                }
            }
        }
        return jsonResult;
    }

    @RequestMapping(value = {"/userExtraInfo/editUserEmployee"}, method = RequestMethod.POST)
    @Nonnull
    public
    @ResponseBody
    Map<String, Object> editUserEmloyee(
            Long id, String email
    ) throws Exception {
        SpringUserDetails currentUser = CurrentUserDetailsService.getCurrentUser();

        Map<String, Object> jsonResult = new HashMap<String, Object>();

        TUser user = userRepository.findOne(id);
        if (user == null) {
            jsonResult.put("success", Boolean.FALSE);
            jsonResult.put("error", "Ο χρήστης με το συγκεκριμένο ID δεν είναι καταχωρημένος στο μητρώο.");
            return jsonResult;
        } else {
            if (EmailValidator.validateEmail(email) == false) {
                jsonResult.put("success", Boolean.FALSE);
                jsonResult.put("error", "Το email που δηλώσατε δεν είναι έγκυρο.");
                return jsonResult;
            } else {
                user.setEmail(email);

                Integer funcChangeEmailResult;
                Map<String, Object> changeEmailInput = new LinkedHashMap<String, Object>();
                changeEmailInput.put("p_username", user.getUsername());
                changeEmailInput.put("p_newemail", email);
                // call function to change email of a specific user in Oracle Identity Management / LDAP
                funcChangeEmailResult = (userStoredProcedures).secOimCallFunction(changeEmailInput, "PCG_SEC_OIM.SEC_OIM_CHANGE_EMAIL");
                if (funcChangeEmailResult != 0) {
                    jsonResult.put("success", Boolean.FALSE);
                    jsonResult.put("error", "Προέκυψε κάποιο πρόβλημα κατά την αλλαγή του email.");
                    return jsonResult;
                } else {
                    try {
                        userRepository.save(user);
                    } catch (Exception ex) {
                        jsonResult.put("success", Boolean.FALSE);
                        jsonResult.put("error", Boolean.FALSE);
                        return jsonResult;
                    }
                    String newpassword = "p1" + generateRandomPassword(6);
                    Integer funcResetPassResult = -1;
                    try {
                        Map<String, Object> resetPassInput = new LinkedHashMap<String, Object>();
                        resetPassInput.put("p_username", user.getUsername());
                        resetPassInput.put("p_new_pass", newpassword);

                        // call function to Reset password of a specific user in Oracle Identity Management / LDAP
                        funcResetPassResult = (userStoredProcedures).secOimCallFunction(resetPassInput, "PCG_SEC_OIM.SEC_OIM_RESET_PASS");
                    } catch (Exception e) {
                        jsonResult.put("success", Boolean.FALSE);
                        jsonResult.put("error", "Προέκυψε κάποιο πρόβλημα στην διαδικασία αλλαγής του νέου κωδικού.");
                        return jsonResult;
                    }
                    if (funcResetPassResult != 0) {
                        jsonResult.put("success", Boolean.FALSE);
                        jsonResult.put("error", "Προέκυψε κάποιο πρόβλημα με την αποστολή του νέου κωδικού.");
                        return jsonResult;
                    } else {
                        mailService.passwordReminder(user.getUsername(), newpassword, user.getEmail());
                        jsonResult.put("success", Boolean.TRUE);
                    }

                }
            }
        }
        return jsonResult;
    }

    @RequestMapping(value = {"/userExtraInfo/editUserSafetyTechnician"}, method = RequestMethod.POST)
    @Nonnull
    public
    @ResponseBody
    Map<String, Object> editUserSafetyTechnician(
            Long id, String email
    ) throws Exception {
        SpringUserDetails currentUser = CurrentUserDetailsService.getCurrentUser();

        Map<String, Object> jsonResult = new HashMap<String, Object>();

        TUser user = userRepository.findOne(id);
        if (user == null) {
            jsonResult.put("success", Boolean.FALSE);
            jsonResult.put("error", "Ο χρήστης με το συγκεκριμένο ID δεν είναι καταχωρημένος στο μητρώο.");
            return jsonResult;
        } else {
            if (EmailValidator.validateEmail(email) == false) {
                jsonResult.put("success", Boolean.FALSE);
                jsonResult.put("error", "Το email που δηλώσατε δεν είναι έγκυρο.");
                return jsonResult;
            } else {
                user.setEmail(email);

                Integer funcChangeEmailResult;
                Map<String, Object> changeEmailInput = new LinkedHashMap<String, Object>();
                changeEmailInput.put("p_username", user.getUsername());
                changeEmailInput.put("p_newemail", email);
                // call function to change email of a specific user in Oracle Identity Management / LDAP
                funcChangeEmailResult = (userStoredProcedures).secOimCallFunction(changeEmailInput, "PCG_SEC_OIM.SEC_OIM_CHANGE_EMAIL");
                if (funcChangeEmailResult != 0) {
                    jsonResult.put("success", Boolean.FALSE);
                    jsonResult.put("error", "Προέκυψε κάποιο πρόβλημα κατά την αλλαγή του email.");
                    return jsonResult;
                } else {
                    try {
                        userRepository.save(user);
                    } catch (Exception ex) {
                        jsonResult.put("success", Boolean.FALSE);
                        jsonResult.put("error", Boolean.FALSE);
                        return jsonResult;
                    }
                    String newpassword = "p1" + generateRandomPassword(6);
                    Integer funcResetPassResult = -1;
                    try {
                        Map<String, Object> resetPassInput = new LinkedHashMap<String, Object>();
                        resetPassInput.put("p_username", user.getUsername());
                        resetPassInput.put("p_new_pass", newpassword);

                        // call function to Reset password of a specific user in Oracle Identity Management / LDAP
                        funcResetPassResult = (userStoredProcedures).secOimCallFunction(resetPassInput, "PCG_SEC_OIM.SEC_OIM_RESET_PASS");
                    } catch (Exception e) {
                        jsonResult.put("success", Boolean.FALSE);
                        jsonResult.put("error", "Προέκυψε κάποιο πρόβλημα στην διαδικασία αλλαγής του νέου κωδικού.");
                        return jsonResult;
                    }
                    if (funcResetPassResult != 0) {
                        jsonResult.put("success", Boolean.FALSE);
                        jsonResult.put("error", "Προέκυψε κάποιο πρόβλημα με την αποστολή του νέου κωδικού.");
                        return jsonResult;
                    } else {
                        mailService.passwordReminder(user.getUsername(), newpassword, user.getEmail());
                        jsonResult.put("success", Boolean.TRUE);
                    }

                }
            }
        }
        return jsonResult;
    }

    @RequestMapping(value = {"/userExtraInfo/editUserWorkplaceDoctor"}, method = RequestMethod.POST)
    @Nonnull
    public
    @ResponseBody
    Map<String, Object> editUserWorkplaceDoctor(
            Long id, String email
    ) throws Exception {
        SpringUserDetails currentUser = CurrentUserDetailsService.getCurrentUser();

        Map<String, Object> jsonResult = new HashMap<String, Object>();

        TUser user = userRepository.findOne(id);
        if (user == null) {
            jsonResult.put("success", Boolean.FALSE);
            jsonResult.put("error", "Ο χρήστης με το συγκεκριμένο ID δεν είναι καταχωρημένος στο μητρώο.");
            return jsonResult;
        } else {
            if (EmailValidator.validateEmail(email) == false) {
                jsonResult.put("success", Boolean.FALSE);
                jsonResult.put("error", "Το email που δηλώσατε δεν είναι έγκυρο.");
                return jsonResult;
            } else {
                user.setEmail(email);

                Integer funcChangeEmailResult;
                Map<String, Object> changeEmailInput = new LinkedHashMap<String, Object>();
                changeEmailInput.put("p_username", user.getUsername());
                changeEmailInput.put("p_newemail", email);
                // call function to change email of a specific user in Oracle Identity Management / LDAP
                funcChangeEmailResult = (userStoredProcedures).secOimCallFunction(changeEmailInput, "PCG_SEC_OIM.SEC_OIM_CHANGE_EMAIL");
                if (funcChangeEmailResult != 0) {
                    jsonResult.put("success", Boolean.FALSE);
                    jsonResult.put("error", "Προέκυψε κάποιο πρόβλημα κατά την αλλαγή του email.");
                    return jsonResult;
                } else {
                    try {
                        userRepository.save(user);
                    } catch (Exception ex) {
                        jsonResult.put("success", Boolean.FALSE);
                        jsonResult.put("error", Boolean.FALSE);
                        return jsonResult;
                    }
                    String newpassword = "p1" + generateRandomPassword(6);
                    Integer funcResetPassResult = -1;
                    try {
                        Map<String, Object> resetPassInput = new LinkedHashMap<String, Object>();
                        resetPassInput.put("p_username", user.getUsername());
                        resetPassInput.put("p_new_pass", newpassword);

                        // call function to Reset password of a specific user in Oracle Identity Management / LDAP
                        funcResetPassResult = (userStoredProcedures).secOimCallFunction(resetPassInput, "PCG_SEC_OIM.SEC_OIM_RESET_PASS");
                    } catch (Exception e) {
                        jsonResult.put("success", Boolean.FALSE);
                        jsonResult.put("error", "Προέκυψε κάποιο πρόβλημα στην διαδικασία αλλαγής του νέου κωδικού.");
                        return jsonResult;
                    }
                    if (funcResetPassResult != 0) {

                        jsonResult.put("success", Boolean.FALSE);
                        jsonResult.put("error", "Προέκυψε κάποιο πρόβλημα με την αποστολή του νέου κωδικού.");
                        return jsonResult;
                    } else {
                        mailService.passwordReminder(user.getUsername(), newpassword, user.getEmail());
                        jsonResult.put("success", Boolean.TRUE);
                    }

                }
            }
        }
        return jsonResult;
    }


    @RequestMapping(value = {"/getAppVersion"}, method = RequestMethod.POST)
    @Nonnull
    public
    @ResponseBody
    Map<String, Object> getAppVersion() throws Exception {

        Map<String, Object> jsonResult = new HashMap<String, Object>();

        jsonResult.put("success", Boolean.TRUE);
        jsonResult.put("version", "v_1.0.235");
        return jsonResult;


    }

    @RequestMapping(value = {"/getFormInPdf"}, method = RequestMethod.POST)
    @Nonnull
    public
    @ResponseBody
    void getFormInPdf(HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) throws Exception {

        /*try {
            // Get the text that will be added to the PDF
            String formInHtml = httpServletRequest.getParameter("formInHtml");
            if (formInHtml == null || formInHtml.trim().length() == 0) {
                formInHtml = "<html><body> There is no text! Please contact with administrator. </body></html>";
            }
            //String xhtml = Jsoup.parse(formInHtml, "UTF-8").html();
            byte[] xhtmlBytes = formInHtml.getBytes(StandardCharsets.UTF_8);

            // step 1
            Document document = new Document();
            // step 2
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter writer = PdfWriter.getInstance(document, baos);
            document.open();
            //StringBuilder htmlString = new StringBuilder();
            //htmlString.append(formInHtml);

            InputStream is = new ByteArrayInputStream(xhtmlBytes);
            XMLWorkerHelper.getInstance().parseXHtml(writer, document, is, Charset.forName("UTF-8"));

            document.close();


            httpServletResponse.setCharacterEncoding("UTF-8");
            httpServletResponse.setHeader("Content-Disposition", "attachment; filename=\"test.pdf\"");
            httpServletResponse.setContentType("application/pdf");
            httpServletResponse.setContentLength(baos.size());
            // write ByteArrayOutputStream to the ServletOutputStream
            OutputStream os = httpServletResponse.getOutputStream();
            baos.writeTo(os);
            os.flush();
            os.close();
        }
        catch(DocumentException e) {
            throw new IOException(e.getMessage());
        }*/

    }

    @RequestMapping(value = {"/getCertificateInPdf"}, method = RequestMethod.POST)
    @Nonnull
    public
    @ResponseBody
    void getCertificateInPdf(HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest, String compGenReqProtNo) throws Exception {

        Map<String, Object> jsonResult = new HashMap<String, Object>();

        //SpringUserDetails springUserDetails = currentAuthenticatedUser();
        SpringUserDetails springUserDetails = CurrentUserDetailsService.getCurrentUser();
        Long userId = springUserDetails.getUserId();
        try {
            if (compGenReqProtNo != null) {
                SpPtlCompGenreq genreq = spPtlCompGenreqRepo.findByProtNo(compGenReqProtNo);
                if (genreq != null) {
                    CompanyDTO company = userService.findCompany(genreq.getCompanyId());
                    if (company != null) {
                        String companyName = company.getName();
                        String date = new SimpleDateFormat("d-M-yyyy").format(new java.sql.Date(genreq.getProtDate().getTime()));

                        //String k = "<html><body> This is my Project </body></html>";
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        OutputStream outputStream = httpServletResponse.getOutputStream(); //new FileOutputStream(new File("C:\\Temp\\dest\\certificate.pdf"));
                        ClassLoader classLoader = getClass().getClassLoader();
                        InputStream inputStream = classLoader.getResourceAsStream("certificate_template.pdf");
                        //Initialize PDF document
                        //PdfDocument pdf = new PdfDocument(new PdfReader("C:\\Temp\\Test.pdf"), new PdfWriter("C:\\Temp\\dest\\certificate.pdf"));

                        //Initialize document
                        Document document = new Document(PageSize.A4);
                        PdfWriter writer = PdfWriter.getInstance(document, byteArrayOutputStream);
                        document.open();
                        PdfContentByte cb = writer.getDirectContent();

                        // Load existing PDF
                        PdfReader reader = new PdfReader(inputStream);
                        PdfImportedPage page = writer.getImportedPage(reader, 1);

                        // Copy first page of existing PDF into output PDF
                        document.newPage();
                        cb.addTemplate(page, 0, 0);

                        PdfContentByte canvas = writer.getDirectContent();
                        BaseFont bfTimes = BaseFont.createFont("arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                        Font times = new Font(bfTimes, 12, Font.NORMAL);
                        Font times2 = new Font(bfTimes, 10, Font.NORMAL);
                        Phrase dateVal = new Phrase("Αθήνα, " + date, times);
                        Phrase protNumWithLabelVal = new Phrase("Aρ. Πρωτ: ΕΞ - " + genreq.getAnswerPdfProtNo(), times);
                        //Phrase compNameVal = new Phrase(companyName + "\n" + genreq.getInspAddr(), times);
                        String compNameAndAddress = companyName + "\n" + genreq.getInspAddr();
                        if (compNameAndAddress.length() > 210) {
                            compNameAndAddress = compNameAndAddress.substring(0, 210);
                        }
                        Phrase compNameVal = new Phrase(compNameAndAddress, times2);
                        Phrase compAddressVal = new Phrase(genreq.getInspAddr(), times);
                        Phrase protNumVal = new Phrase(genreq.getProtNo() + "/" + genreq.getProtYear(), times);
                        ColumnText ct = new ColumnText(cb);
                        ct.setSimpleColumn(319f, 602f, 570f, 250f);
                        ct.addElement(compNameVal);
                        ct.go();

                        ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, dateVal, 319, 712, 0);
                        ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, protNumWithLabelVal, 319, 688, 0);
                        //ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, compNameVal, 319, 589, 0);
                        //ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, compAddressVal, 319, 577, 0);
                        ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, protNumVal, 175, 441, 0);

                        httpServletResponse.setCharacterEncoding("UTF-8");
                        httpServletResponse.setHeader("Content-Disposition", "attachment; filename=\"certificate.pdf\"");
                        httpServletResponse.setContentType("application/pdf");

                        document.close();

                        byte[] bytes = byteArrayOutputStream.toByteArray();
                        outputStream.write(bytes);

                        // insert pdf as version

                        Long dID = documentStoreRest.versionDocument(genreq.getAnswerPdfDocId(), genreq.getAnswerPdfProtNo(), "certificate.pdf", bytes);
                        documentStoredProcedures.setDocument(springUserDetails != null ? springUserDetails.getUserId() : -1, dID);
                        if (dID < 0) {
                            System.out.println("Προέκυψε κάποιο σφάλμα κατά την εισαγωγή του αρχείου ως έκδοση.");
                            System.out.println("GenReqId: " + genreq.getId());
                        } else {
                            TNotificationsAccountEntity notification = new TNotificationsAccountEntity();
                            notification.setDocId(new Long(-1));
                            notification.setTitle("Απάντηση (αντί χορήγησης πιστοποιητικού Ν.4412/2016)");
                            notification.setMessage("Έχει αποθηκευθεί το έγγραφο 'Απάντηση αντί χορήγησης πιστοποιητικού της παρ. 2Γ του άρθρου 73 του Ν. 4412/2016' με αριθμό πρωτοκόλλου: " + genreq.getAnswerPdfProtNo() + ". Μπορείτε να το δείτε πατώντας στον αντίστοιχο σύνδεσμο.");
                            notification.setSender(companyName);
                            notification.setPriority(new Long(1));
                            notification.setViewed(new Long(0));
                            notification.setDocId(genreq.getAnswerPdfDocId());
                            Date todayDate = new Date();
                            Timestamp today = new Timestamp(todayDate.getTime());
                            notification.setDateTime(today);
                            notification.setAccountId(userId);

                            try {
                                notificationsRestRepository.save(notification);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        outputStream.close();


                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //return jsonResult;

    }

}
