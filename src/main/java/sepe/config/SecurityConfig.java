package sepe.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
import sepe.domain.TUser;
import sepe.dto.UserDTO;
import sepe.dto.converter.EmployeeToEmployeeDTO;
import sepe.dto.converter.UserToUserDTO;
import sepe.repository.UserRepository;
import sepe.repository.employee.EmployeeRepository;
import sepe.service.CurrentUserDetailsService;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebMvcSecurity
@EnableAutoConfiguration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    public final class LoginListener implements ApplicationListener<InteractiveAuthenticationSuccessEvent> {
        @Override
        public void onApplicationEvent(final InteractiveAuthenticationSuccessEvent event) {

            // Retrieve the user
            final SpringUserDetails userDetails = (SpringUserDetails) event.getAuthentication().getPrincipal();
            final UserDTO userDTO = userDetails.getUserDTO();
            final TUser user = userRepository.findOne(userDTO.getId());
            Logger logger = LoggerFactory.getLogger("SecurityConfig");
            logger.debug("Just logged in user " + user.getEmail() + " with id " + user.getId());
        }
    }

    @Autowired
    UserRepository userRepository;

    @Autowired
    private UserToUserDTO userDTOConverter;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeToEmployeeDTO employeeDTOConverter;

    @Autowired
    private CurrentUserDetailsService userDetailsService;

    @Bean
    public LoginListener loginListener() {
        return new LoginListener();
    }

    @Autowired
    SecurityEvaluationContextExtension securityExtension() {
        return new SecurityEvaluationContextExtension();
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);

        http.addFilterBefore(filter, CsrfFilter.class);
        http.csrf().disable();
        http.headers().frameOptions().disable();
        http.headers().cacheControl().disable();

        //http.sessionManagement().sessionFixation().none();

        http.authorizeRequests()
                //Public Access (not post, not put, read only)
                //Citizen
                .antMatchers("/favicon.ico").permitAll()
                .antMatchers("/certificate_template.pdf").permitAll()
                .antMatchers("/arial.ttf").permitAll()
                .antMatchers("/static/bootstrap/**").permitAll()
                .antMatchers("/static/bootstrap-datepicker/**").permitAll()
                .antMatchers("/static/css/**").permitAll()
                .antMatchers("/passwordreset/**").permitAll()
                .antMatchers("/password/**").permitAll()
                .antMatchers("/static/userapp/ext/**").permitAll()
                .antMatchers("/static/userapp/resources/**").permitAll()
                .antMatchers("/static/userapp/anonymous.js").permitAll()
                .antMatchers("/static/userapp/app/view/anonymous/**").permitAll()
                .antMatchers("/static/userapp/app/view/shared/**").permitAll()
                .antMatchers("/static/userapp/app/store/address/**").permitAll()
                .antMatchers("/static/userapp/app/store/address1/**").permitAll()
                .antMatchers("/static/userapp/app/store/compScope/**").permitAll()
                .antMatchers("/employeeComplaintMatter/**").permitAll()
                .antMatchers("/TKalD/**").permitAll()
                .antMatchers("/TKalP/**").permitAll()
                .antMatchers("/TKalPe/**").permitAll()
                .antMatchers("/TKalK/**").permitAll()
                .antMatchers("/RtStakLevel1/**").permitAll()
                .antMatchers("/RtStakLevel2/**").permitAll()
                .antMatchers("/RtStakLevel3/**").permitAll()
                .antMatchers("/RtStakLevel4/**").permitAll()
                .antMatchers("/RtStakLevel5/**").permitAll()
                .antMatchers("/RtStakLevel5AA/**").permitAll()
                .antMatchers("/SpPtlVShipyard/**").permitAll()
                .antMatchers("/SpPtlVPortAuth/**").permitAll()
                .antMatchers("/setDocument/**").permitAll()

                .antMatchers("/anonymous").permitAll()

                //Public Access (post)
                //Citizen
                .antMatchers("/anonymousComplaint/**").permitAll()

                //Authenticated
                .antMatchers("/TProtocol/**").authenticated()
                .antMatchers("/TReqStatus/**").authenticated()
                .antMatchers("/TSepeDepartment/**").authenticated()
                .antMatchers("/TMaritalStatus/**").authenticated()
                .antMatchers("/TDoy/**").authenticated()
                .antMatchers("/static/userapp/store/shared/**").authenticated()
                .antMatchers("/educLevel/**").authenticated()
                .antMatchers("/degreeType/**").authenticated()
                .antMatchers("/cooperationType/**").authenticated()
                .antMatchers("/medicalAsso/**").authenticated()
                .antMatchers("/contrSpeciality/**").authenticated()
                .antMatchers("/nationality").authenticated()
                //.antMatchers("/fileupload").authenticated()

                //Employee
                .antMatchers("/static/userapp/employee.js").hasRole("EMPLOYEE")
                .antMatchers("/static/userapp/view/employee/**").hasRole("EMPLOYEE")
                .antMatchers("/static/userapp/store/employee/**").hasRole("EMPLOYEE")
                .antMatchers("/tEmployeeComplaints/**").hasRole("EMPLOYEE")
                .antMatchers("/tEmployeeDisputes/**").hasAnyRole("EMPLOYEE", "EMPLOYER")
                .antMatchers("/tEmployeeDisputeSubjects/**").hasAnyRole("EMPLOYEE", "EMPLOYER")
                .antMatchers("/spPtlEmployeeExperiences/**").hasRole("EMPLOYEE")
                .antMatchers("/TEmployeeSpeciality/**").hasRole("EMPLOYEE")
                .antMatchers("/emplExperienceCertUse/**").hasRole("EMPLOYEE")
                .antMatchers("/employeeGenrequest/**").hasRole("EMPLOYEE")
                .antMatchers("/emplGenrequestType/**").hasRole("EMPLOYEE")

                //Doctor
                .antMatchers("/static/userapp/doctor.js").hasRole("DOCTOR")
                .antMatchers("/static/userapp/view/doctor/**").hasRole("DOCTOR")
                .antMatchers("/doctorRegrequest/**").hasRole("DOCTOR")
                .antMatchers("/doctorCounty/**").hasRole("DOCTOR")
                .antMatchers("/vCompIeAnnDiary/**").hasRole("DOCTOR")
                .antMatchers("/cCompIeAnnRespond/**").hasAnyRole("DOCTOR", "EMPLOYER", "COMPANY_USER")
                .antMatchers("/doctorBookNoteAdd/**").hasRole("DOCTOR")
                .antMatchers("/ieAnnResign/**").hasRole("DOCTOR")
                .antMatchers("/compIeAnnCessationRespond/**").hasRole("DOCTOR")


                //Technician
                .antMatchers("/static/userapp/technician.js").hasRole("TECHNICIAN")
                .antMatchers("/static/userapp/view/technician/**").hasRole("TECHNICIAN")
                .antMatchers("/technicianRegrequest/**").hasRole("TECHNICIAN")
                .antMatchers("/technicianDiploma/**").hasRole("TECHNICIAN")
                .antMatchers("/taAnnResign/**").hasAnyRole("TECHNICIAN", "EMPLOYER", "COMPANY_USER")
                .antMatchers("/taSannResign/**").hasRole("TECHNICIAN")
                .antMatchers("/compTaAnnCessationRespond/**").hasAnyRole("TECHNICIAN", "EMPLOYER", "COMPANY_USER")
                .antMatchers("/compTaSannCessationRespond/**").hasRole("TECHNICIAN")
                .antMatchers("/SpPtlVCompTaSannDiaryRepo/**").hasRole("TECHNICIAN")
                .antMatchers("/cCompTaAnnRespond/**").hasAnyRole("TECHNICIAN", "EMPLOYER", "COMPANY_USER")
                .antMatchers("/cCompTaSannRespond/**").hasAnyRole("TECHNICIAN", "EMPLOYER", "COMPANY_USER")
                .antMatchers("/technicianSannBookNoteAdd/**").hasRole("TECHNICIAN")
                .antMatchers("/exypp/**").hasAnyRole("EMPLOYER", "COMPANY_USER", "TECHNICIAN")


                //Technician + company
                //Doctor + company
                .antMatchers("/vCompIeAnn/**").hasAnyRole("DOCTOR", "EMPLOYER", "COMPANY_USER")
                .antMatchers("/vCompTaSann/**").hasAnyRole("TECHNICIAN", "EMPLOYER", "COMPANY_USER")
                .antMatchers("/vCompTaAnn/**").hasAnyRole("TECHNICIAN", "EMPLOYER", "COMPANY_USER")
                .antMatchers("/compPtlBranch/**").hasAnyRole("EMPLOYER", "COMPANY_USER", "DOCTOR")
                .antMatchers("/compShip/**").hasAnyRole("EMPLOYER", "COMPANY_USER", "TECHNICIAN")
                .antMatchers("/compTaSannBookNote/**").hasAnyRole("EMPLOYER", "COMPANY_USER", "TECHNICIAN")
                .antMatchers("/compIeAnnBookNote/**").hasAnyRole("EMPLOYER", "COMPANY_USER", "DOCTOR")
                .antMatchers("/taSpeciality/**").hasAnyRole("TECHNICIAN", "EMPLOYER", "COMPANY_USER")
                .antMatchers("/ieSpeciality/**").hasAnyRole("DOCTOR", "EMPLOYER", "COMPANY_USER")


                .antMatchers("/TEmployerBranchIKA/**").hasAnyRole("EMPLOYEE", "EMPLOYER", "COMPANY_USER")
                .antMatchers("/emplGenrequestSubject/**").hasAnyRole("EMPLOYEE", "EMPLOYER", "COMPANY_USER")
                .antMatchers("/tEmployeeDisputeSubjects/**").hasAnyRole("EMPLOYEE", "EMPLOYER", "COMPANY_USER")
                .antMatchers("/TEmployeeSpeciality/**").hasAnyRole("EMPLOYER", "COMPANY_USER", "EMPLOYEE") //will be replaces i think
                .antMatchers("/emplGenrequestType/**").hasAnyRole("EMPLOYER", "COMPANY_USER", "EMPLOYEE") //will be replaces i think
                .antMatchers("/vwGenreq").hasAnyRole("EMPLOYER", "COMPANY_USER", "EMPLOYEE")

                //Company and CompanyUser
                .antMatchers("/static/userapp/company.js").hasAnyRole("EMPLOYER", "COMPANY_USER")
                .antMatchers("/static/userapp/view/company/**").hasAnyRole("EMPLOYER", "COMPANY_USER")
                .antMatchers("/companyProjects/**").hasAnyRole("EMPLOYER", "COMPANY_USER")
                .antMatchers("/companyDailyCards/**").hasAnyRole("EMPLOYER", "COMPANY_USER")
                .antMatchers("/companyPersonnelBook/**").hasAnyRole("EMPLOYER", "COMPANY_USER")
                .antMatchers("/compProjAnn/**").hasAnyRole("EMPLOYER", "COMPANY_USER")
                .antMatchers("/compProjAnnContr/**").hasAnyRole("EMPLOYER", "COMPANY_USER")
                .antMatchers("/compProjAnnEng/**").hasAnyRole("EMPLOYER", "COMPANY_USER")
                .antMatchers("/compProjAnnSelfempl/**").hasAnyRole("EMPLOYER", "COMPANY_USER")
                .antMatchers("/compProjAnnStudier/**").hasAnyRole("EMPLOYER", "COMPANY_USER")
                .antMatchers("/companyAccident/**").hasAnyRole("EMPLOYER", "COMPANY_USER")
                .antMatchers("/companyAccidentWitn/**").hasAnyRole("EMPLOYER", "COMPANY_USER")
                .antMatchers("/companyIllness/**").hasAnyRole("EMPLOYER", "COMPANY_USER")
                .antMatchers("/compSundayPmt/**").hasAnyRole("EMPLOYER", "COMPANY_USER")
                .antMatchers("/compSundayPmtPers/**").hasAnyRole("EMPLOYER", "COMPANY_USER")
                .antMatchers("/accidentAffectedPosition/**").hasAnyRole("EMPLOYER", "COMPANY_USER")
                .antMatchers("/accidentAffectedTime/**").hasAnyRole("EMPLOYER", "COMPANY_USER")
                .antMatchers("/witnessType/**").hasAnyRole("EMPLOYER", "COMPANY_USER")
                .antMatchers("/compJobRecrOff/**").hasAnyRole("EMPLOYER", "COMPANY_USER")
                .antMatchers("/compJobRecrOffPers/**").hasAnyRole("EMPLOYER", "COMPANY_USER")
                .antMatchers("/compDisputeNeg/**").hasAnyRole("EMPLOYER", "COMPANY_USER")
                .antMatchers("/compComplaint/**").hasAnyRole("EMPLOYER", "COMPANY_USER")
                .antMatchers("/compGenreq/**").hasAnyRole("EMPLOYER", "COMPANY_USER")
                .antMatchers("/registerCompanyUser/**").hasAnyRole("EMPLOYER", "COMPANY_USER")
                .antMatchers("/registerCompanyUser/**").hasAnyRole("EMPLOYER", "COMPANY_USER")
                .antMatchers("/compExtraInfo/**").hasAnyRole("EMPLOYER", "COMPANY_USER")
                .antMatchers("/compExplanation/**").hasAnyRole("EMPLOYER", "COMPANY_USER")
                .antMatchers("/compIeAnnBook/**").hasAnyRole("EMPLOYER", "COMPANY_USER")
                .antMatchers("/compIeAnnDiaryEntry/**").hasAnyRole("EMPLOYER", "COMPANY_USER")
                .antMatchers("/compIeAnn/**").hasAnyRole("EMPLOYER", "COMPANY_USER")
                .antMatchers("/compIeAnnBookNoteRead/**").hasAnyRole("EMPLOYER", "COMPANY_USER")
                .antMatchers("/findIeByAfm/**").hasAnyRole("EMPLOYER", "COMPANY_USER")
                .antMatchers("/compDriverPmt/**").hasAnyRole("EMPLOYER", "COMPANY_USER")
                .antMatchers("/compDriverPmtEntry/**").hasAnyRole("EMPLOYER", "COMPANY_USER")
                .antMatchers("/compSecDiary/**").hasAnyRole("EMPLOYER", "COMPANY_USER")
                .antMatchers("/compSecDiaryContr/**").hasAnyRole("EMPLOYER", "COMPANY_USER")
                .antMatchers("/compSecDiaryEng/**").hasAnyRole("EMPLOYER", "COMPANY_USER")
                .antMatchers("/compSecDiaryEntry/**").hasAnyRole("EMPLOYER", "COMPANY_USER")
                .antMatchers("/compDangerAssess/**").hasAnyRole("EMPLOYER", "COMPANY_USER")
                .antMatchers("/compVehicleBook/**").hasAnyRole("EMPLOYER", "COMPANY_USER")
                .antMatchers("/compVehicleBookEntry/**").hasAnyRole("EMPLOYER", "COMPANY_USER")
                .antMatchers("/compTaSannBook/**").hasAnyRole("EMPLOYER", "COMPANY_USER")
                .antMatchers("/compTaSannContr/**").hasAnyRole("EMPLOYER", "COMPANY_USER")
                .antMatchers("/compTaSannDiaryEntr/**").hasAnyRole("EMPLOYER", "COMPANY_USER")
                .antMatchers("/compTaSann/**").hasAnyRole("EMPLOYER", "COMPANY_USER", "TECHNICIAN")
                .antMatchers("/findTaByAfm/**").hasAnyRole("EMPLOYER", "COMPANY_USER")
                .antMatchers("/compTaSannBookNoteRead/**").hasAnyRole("EMPLOYER", "COMPANY_USER")
                .antMatchers("/empIncharges/**").hasAnyRole("EMPLOYER", "COMPANY_USER")
                .antMatchers("/inspector/**").hasAnyRole("EMPLOYER", "COMPANY_USER")
                .antMatchers("/insuranceBureau").hasAnyRole("EMPLOYER", "COMPANY_USER")
                .antMatchers("/companyExtraInfo/**").hasAnyRole("EMPLOYER", "COMPANY_USER")
                .antMatchers("/companyIsExypp/**").hasAnyRole("EMPLOYER", "COMPANY_USER")
                .antMatchers("/updateTaAnnProgram/**").hasAnyRole("EMPLOYER", "COMPANY_USER")

                //Company
                .antMatchers("/companyUsers/**").hasRole("EMPLOYER")
                .antMatchers("/companyUsersPrevs/**").hasRole("EMPLOYER")

                //Association
                .antMatchers("/static/userapp/association.js").permitAll()
                .antMatchers("/static/userapp/view/association/**").permitAll()

                //Admin
                .antMatchers("/register/**").hasRole("ADMIN")
                .antMatchers("/static/userapp/admin.js").hasRole("ADMIN")
                .antMatchers("/static/userapp/view/admin/**").hasRole("ADMIN")
                .antMatchers("/static/userapp/store/admin/**").hasRole("ADMIN")
                .antMatchers("/tUsers/**").hasRole("ADMIN")
                .antMatchers("/spPtlVUserCompany/**").hasRole("ADMIN")
                .antMatchers("/spPtlVUserEmployee/**").hasRole("ADMIN")
                .antMatchers("/register2/**").permitAll()
                .antMatchers("/registerTa/**").permitAll()

                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .and()
                .logout().permitAll()
        ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.eraseCredentials(false);
        auth.authenticationProvider(buildDaoAuthenticationProvider());
    }

    @Bean
    public MyDaoAuthenticationProvider buildDaoAuthenticationProvider() throws Exception{
        MyDaoAuthenticationProvider daoAuthenticationProvider = new MyDaoAuthenticationProvider();
        //daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        //daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return daoAuthenticationProvider;
    }


    /*@Nonnull
    public class MyAuthenticationProvider implements AuthenticationProvider {

        @Override
        public Authentication authenticate(Authentication authentication) throws AuthenticationException {

            SpringUserDetails user = (SpringUserDetails) authentication.getPrincipal();
            String password = (String) authentication.getCredentials();

            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            authorities.add(new SimpleGrantedAuthority("EMPLOYEE"));
            authorities.add(new SimpleGrantedAuthority("DOCTOR"));

            UsernamePasswordAuthenticationToken authenticationToken = null;



            if (true) {
                authenticationToken = new UsernamePasswordAuthenticationToken(user, password, authorities);
            } else {
                throw new BadCredentialsException("Invalid credentials supplied. Please try again.");
            }
            return authenticationToken;
        }


        @Override
        public boolean supports(Class<?> authentication) {
            boolean supports = authentication.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
            return supports;
        }

    }*/
}
