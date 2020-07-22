package sepe.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sepe.config.Constants;
import sepe.config.SpringUserDetails;
import sepe.domain.company.TCompany;
import sepe.domain.company.TCompanyUserPrivilages;
import sepe.domain.company.TEmployerBranchIKA;
import sepe.domain.employee.TEmployee;
import sepe.domain.TUser;
import sepe.dto.CompanyDTO;
import sepe.dto.EmployeeDTO;
import sepe.dto.UserDTO;
import sepe.dto.converter.CompanyToCompanyDTO;
import sepe.dto.converter.EmployeeToEmployeeDTO;
import sepe.dto.converter.UserToUserDTO;
import sepe.repository.*;
import sepe.repository.company.CompanyRepository;
import sepe.repository.company.SpPtlCompanyUserPrivilagesRepository;
import sepe.repository.company.SpPtlCompanyUserPrivilagesRepositoryInternal;
import sepe.repository.company.TEmployerBranchIKARepo;
import sepe.repository.employee.EmployeeRepository;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class CurrentUserDetailsService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CurrentUserDetailsService.class);
    private final UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private UserToUserDTO userDTOConverter;


    //@Autowired
    //SpPtlCompanyUserPrivilagesRepository companyUserPrivilagesRepository;

    @Autowired
    SpPtlCompanyUserPrivilagesRepositoryInternal companyUserPrivilagesRepositoryInternal;


    @Autowired
    TEmployerBranchIKARepo tEmployerBranchIKARepo;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    private EmployeeToEmployeeDTO employeeDTOConverter;

    @Autowired
    private CompanyToCompanyDTO companyDTOConverter;

    @Autowired
    private CompanyService companyService;

    @Autowired
    public CurrentUserDetailsService(@Nonnull final UserService userService) {
        this.userService = userService;
    }

    @Override
    public SpringUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOGGER.debug("Authenticating user with username={}", username.replaceFirst("@.*", "@***"));
        if (userRepository == null) {
            System.out.println("NULL USER REPOSITORY");
        }
        final TUser user = userRepository.findByUsernameEquals(username.toUpperCase());
        if (user == null) return null;
        final UserDTO userDTO = userDTOConverter.convert(user);

        if (userDTO.getRole() == Constants.USER_TYPE.COMPANY_USER.getCode()) {
            TCompanyUserPrivilages priv = companyUserPrivilagesRepositoryInternal.findByUser(userDTO.getId());
            Long companyId = priv.getCompId();

            TCompany company = companyRepository.findOne(companyId);
            CompanyDTO company2 = companyDTOConverter.convert(company);
            if (priv.getActive() == (long) 1)
                return new SpringUserDetails(userDTO, userDTO.getId(), user.getPassword(), company2, priv.getBranchIds());
            else
                throw new UsernameNotFoundException("Δεν υπάρχει ο χρήστης");
        }


        if (userDTO.getRole() == Constants.USER_TYPE.COMPANY.getCode()) {
            TCompany company = companyRepository.findByUserId(userDTO.getId());
            String branchIds = "";
            try {
               // if (getCurrentUser() != null)
                branchIds = companyService.getCompanyBRanchIds(company);
            } catch (Exception e) {
                throw new UsernameNotFoundException(e.getMessage());
            }
            CompanyDTO company2 = companyDTOConverter.convert(company);
            return new SpringUserDetails(userDTO, userDTO.getId(), user.getPassword(), company2, branchIds);
        }

        return new SpringUserDetails(userDTO, userDTO.getId(), user.getPassword(), null, null);
    }

    public static SpringUserDetails getCurrentUser() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof SpringUserDetails) {
            return (SpringUserDetails) authentication.getPrincipal();
        } else {
            return null;
        }
    }

    public static UserDTO getCurrentUserByPrincipal() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof SpringUserDetails) {
            return ((SpringUserDetails) authentication.getPrincipal()).getUserDTO();
        } else {
            return null;
        }
    }

    public EmployeeDTO getEmployeeDTO() {
        TEmployee eo = employeeRepository.findByUserId(getCurrentUser().getUserId());
        if (eo == null) return null;
        EmployeeDTO eoDTO = employeeDTOConverter.convert(eo);
        return eoDTO;
    }
}