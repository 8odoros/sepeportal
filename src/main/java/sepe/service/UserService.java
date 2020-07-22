package sepe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import sepe.config.Constants;
import sepe.config.SpringUserDetails;
import sepe.domain.citizen.TCitizen;
import sepe.domain.company.TCompany;
import sepe.domain.company.TCompanyUserPrivilages;
import sepe.domain.doctor.TWorkplaceDoctor;
import sepe.domain.employee.TEmployee;
import sepe.domain.TUser;
import sepe.domain.general.SpRgGgdeMhtrwoUsers;
import sepe.domain.technician.TSafetyTechnician;
import sepe.dto.*;
import sepe.dto.converter.*;
import sepe.handlers.EmailExistsException;
import sepe.handlers.UsernameExistsException;
import sepe.repository.*;
import sepe.repository.citizen.CitizenRepository;
import sepe.repository.company.CompanyRepository;
import sepe.repository.company.SpPtlCompanyUserPrivilagesRepositoryInternal;
import sepe.repository.doctor.WorkplaceDoctorRepository;
import sepe.repository.employee.EmployeeRepository;
import sepe.repository.technician.SafetyTechnicianRepository;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.security.SecureRandom;
import java.util.List;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

@Service
public final class UserService {

    private final UserRepository userRepository;
    private final EmployeeRepository employeeRepository;
    private final UserToUserDTO userDTOConverter;
    private final EmployeeToEmployeeDTO employeeDTOConverter;
    private final CitizenRepository citizenRepository;
    private final CompanyRepository companyRepository;
    private final CompanyToCompanyDTO companyDTOConverter;
    private final WorkplaceDoctorRepository workplaceDoctorRepository;
    private final WorkplaceDoctorToDoctorDTO workplaceDoctorDTOConverter;
    private final SafetyTechnicianRepository safetyTechnicianRepository;
    private final SafetyTechnicianToTechnicianDTO safetyTechnicianDTOConverter;

    @Autowired
    private SpPtlCompanyUserPrivilagesRepositoryInternal companyUserPrivilagesRepositoryInternal;

    @Autowired
    private CompanyService companyService;

    @Autowired
    public UserService(
            @Nonnull final UserRepository userRepository,
            @Nonnull EmployeeRepository employeeRepository,
            @Nonnull final UserToUserDTO userDTOConverter,
            @Nonnull final EmployeeToEmployeeDTO employeeDTOConverter,
            @Nonnull final CitizenRepository citizenRepository,
            @Nonnull final CompanyRepository companyRepository,
            @Nonnull final CompanyToCompanyDTO companyDTOConverter,
            @Nonnull final WorkplaceDoctorRepository workplaceDoctorRepository,
            @Nonnull final WorkplaceDoctorToDoctorDTO workplaceDoctorDTOConverter,
            @Nonnull final SafetyTechnicianRepository safetyTechnicianRepository,
            @Nonnull final SafetyTechnicianToTechnicianDTO safetyTechnicianDTOConverter
    ) {

        this.userRepository = userRepository;
        this.employeeRepository = employeeRepository;
        this.userDTOConverter = userDTOConverter;
        this.employeeDTOConverter = employeeDTOConverter;
        this.citizenRepository = citizenRepository;
        this.companyRepository = companyRepository;
        this.companyDTOConverter = companyDTOConverter;
        this.workplaceDoctorRepository = workplaceDoctorRepository;
        this.workplaceDoctorDTOConverter = workplaceDoctorDTOConverter;
        this.safetyTechnicianRepository = safetyTechnicianRepository;
        this.safetyTechnicianDTOConverter = safetyTechnicianDTOConverter;
    }

    @Nullable
    public UserDTO findUser(@Nonnull final Long userId) {
        final TUser user = userRepository.findOne(userId);
        return userDTOConverter.convert(user);
    }

    @Nullable
    public UserDTO findUserByEmail(@Nonnull final String email) {
        final TUser user = userRepository.findByEmailEquals(email);
        return userDTOConverter.convert(user);
    }

    @Nullable
    public UserDTO findUserByUsername(@Nonnull final String username) {
        final TUser user = userRepository.findByUsernameEquals(username.toUpperCase());
        return userDTOConverter.convert(user);
    }

    @Nullable
    public EmployeeDTO findEmployee(@Nonnull final Long userId) {
        final TEmployee employee = employeeRepository.findByUserId(userId);
        return employeeDTOConverter.convert(employee);
    }

    @Nullable
    public CompanyDTO findCompany(@Nonnull final Long userId) {
        final TCompany company = companyRepository.findByUserId(userId);
        return companyDTOConverter.convert(company);
    }

    @Nullable
    public DoctorDTO findDoctor(@Nonnull final Long userId) {
        final TWorkplaceDoctor doctor = workplaceDoctorRepository.findByUserId(userId);
        return workplaceDoctorDTOConverter.convert(doctor);
    }

    @Nullable
    public TechnicianDTO findTechnician(@Nonnull final Long userId) {
        final TSafetyTechnician technician = safetyTechnicianRepository.findByUserId(userId);
        return safetyTechnicianDTOConverter.convert(technician);
    }


    @Nullable
    public UserDTO findAdmin(@Nonnull final Long userId) {
        final TUser admin = userRepository.findOne(userId);
        return userDTOConverter.convert(admin);
    }

    @Nonnull
    public List<UserDTO> findAllUsers() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .map((TUser user) -> userDTOConverter.convert(user))
                .collect(toList());
    }

    public void createUser(
            @Nonnull final UserDTO userDTO
    ) throws UsernameExistsException {
        /*if (emailExist(userDTO.getEmail())) {
            throw new EmailExistsException("Υπάρχει ήδη λογαριασμός με τη διεύθυνση email: " + userDTO.getEmail());
        }*/
        if (usernameExist(userDTO.getUsername())) {
            throw new UsernameExistsException("Υπάρχει ήδη λογαριασμός με το όνομα χρήστη: " + userDTO.getUsername());
        }

        UserDTOToUser userConverter = new UserDTOToUser();
        TUser user = userConverter.convert(userDTO);

        if (user.getRole() == Constants.USER_TYPE.ADMIN.getCode())
            user.setIsAdmin(1);
        if (user.getRole() == Constants.USER_TYPE.ASSOCIATION.getCode())
            user.setRole(Constants.USER_TYPE.COMPANY.getCode());

        userRepository.save(user);
    }

    public void updateUser(@Nonnull final UserDTO userDTO) {
        updateUser(userDTO, null);
    }

    public void updateUser(
            @Nonnull final UserDTO userDTO,
            @Nullable final String newPassword
    ) {
        final TUser user = userRepository.findOne(userDTO.getId());
        user.setEmail(userDTO.getEmail());
        user.setFirstname(userDTO.getFirstname());
        user.setLastname(userDTO.getLastname());
        user.setAddr(userDTO.getAddress());
        user.setAddrTk(userDTO.getAddrTk());
        user.setPhone(userDTO.getPhone());
        user.setMobile(userDTO.getMobile());
        user.setFax(userDTO.getFax());
        user.setEmailNotifEn(userDTO.getEmailNotifEn());
        if (newPassword != null && !newPassword.isEmpty()) {
            user.setPassword(encryptPassword(newPassword));
        }
        userRepository.save(user);
        refreshAuthenticatedUser();
    }

    public void createEmployee(
            @Nonnull final Long userID
    ) {
        TEmployee employee = new TEmployee();
        employee.setId(userID);
        employee.setUserId(userID);
        employeeRepository.save(employee);
    }

    public void createEmployeeOAuth(
            @Nonnull final Long userID,
            @Nonnull final SpRgGgdeMhtrwoUsers userDetails
    ) {
        TEmployee employee = new TEmployee();
        employee.setId(userID);
        employee.setUserId(userID);
        employee.setAfm(userDetails.getAfm());
        String[] nameDetails = userDetails.getOnomasia().split(",");
        employee.setFatherName(nameDetails[3]);
        employee.setMotherName(userDetails.getMothersFirstName());
        employee.setCompanyName(userDetails.getFirmCommerTitle());
        employee.setCardType(1);
        employee.setCardNumber(userDetails.getCardNo());
        employee.setBirthDate(userDetails.getBirthdate());
        employee.setCitizenshipCode(null);
        employee.setJobTitle(userDetails.getActMainDescr());
        employee.setSex("0");

        employeeRepository.save(employee);
    }

    public void createCitizen(
            @Nonnull final Long userID
    ) {
        TCitizen citizen = new TCitizen();
        citizen.setId(userID);
        citizen.setUserId(userID);
        citizenRepository.save(citizen);
    }

    public void createCompany(
            @Nonnull final Long userID,
            @Nonnull final String afm,
            @Nonnull final String ame,
            @Nonnull final Integer isAssociation,
            @Nonnull final String title
    ) {
        TCompany company = new TCompany();
        company.setId(userID);
        company.setUserId(userID);
        company.setAfm(afm);
        company.setAme(ame);
        company.setIsAssociation(isAssociation);
        company.setTitle(title);
        company.setName(title);
        companyRepository.save(company);
    }

    public void createCompanyUser(
            @Nonnull final Long userID
    ) {
        TUser user = new TUser();
        user.setId(userID);
        userRepository.save(user);
    }

    public void createWorkplaceDoctor(
            @Nonnull final Long userID,
            @Nonnull final String afm,
            @Nonnull final String cardNo,
            @Nonnull final String cardKind
    ) {
        TWorkplaceDoctor doctor = new TWorkplaceDoctor();
        doctor.setId(userID);
        doctor.setUserId(userID);
        doctor.setAfm(afm);
        doctor.setCardType(Long.getLong(cardKind));
        doctor.setCardNumber(cardNo);
        workplaceDoctorRepository.save(doctor);
    }

    public void createSafetyTechnician(
            @Nonnull final Long userID,
            @Nonnull final String afm,
            @Nonnull final String cardNo,
            @Nonnull final String cardKind
    ) {
        TSafetyTechnician technician = new TSafetyTechnician();
        technician.setId(userID);
        technician.setUserId(userID);
        technician.setAfm(afm);
        technician.setCardType(Long.getLong(cardKind));
        technician.setCardNumber(cardNo);
        safetyTechnicianRepository.save(technician);
    }


    public void updateEmployee(@Nonnull final EmployeeDTO employeeDTO) {
        final TEmployee employee = employeeRepository.findByUserId(employeeDTO.getUserId());
        //employee.setAfm(employeeDTO.getAfm());
        employee.setAmka(employeeDTO.getAmka());
        /*employee.setCardNumber(employeeDTO.getCardNumber());
        employee.setFatherName(employeeDTO.getFatherName());
        employee.setCompanyName(employeeDTO.getCompanyName());
        employee.setJobTitle(employeeDTO.getProfession());*/
        employee.setSex(employeeDTO.getSex());
        /*employee.setCitizenshipCode(employeeDTO.getCitizenCode());
        employee.setCardType(employeeDTO.getCardType());*/
        employee.setCardNumber(employeeDTO.getCardNumber());
        employee.setFatherName(employeeDTO.getFatherName());
        employee.setMotherName(employeeDTO.getMotherName());
        employeeRepository.save(employee);
    }

    public void updateCompany(@Nonnull final CompanyDTO companyDTO) {
        final TCompany company = companyRepository.findByUserId(companyDTO.getUserId());
        company.setName(companyDTO.getName());
        company.setTitle(companyDTO.getTitle());
        company.setAfm(companyDTO.getAfm());
        company.setAme(companyDTO.getAme());
        //company.setIsAssociation(companyDTO.getIsAssociation());
        companyRepository.save(company);
    }

    public void updateCompanyExtra(@Nonnull final CompanyDTO companyDTO) {
        final TCompany company = companyRepository.findByUserId(companyDTO.getUserId());
        company.setIsTaNoneEmplrEmple(companyDTO.getIsTaNoneEmplrEmple());
        company.setIsValidAllData(companyDTO.getIsValidAllData());
        company.setIsValidData1(companyDTO.getIsValidData1());
        company.setIsValidData2(companyDTO.getIsValidData2());
        company.setSumEmplA(companyDTO.getSumEmplA());
        company.setSumEmplB(companyDTO.getSumEmplB());
        company.setSumEmplC(companyDTO.getSumEmplC());
        company.setSumEmplTotal(companyDTO.getSumEmplTotal());
        company.setTaAfm(companyDTO.getTaAfm());
        company.setTaFullname(companyDTO.getTaFullname());
        company.setTaDegree(companyDTO.getTaDegree());

        companyRepository.save(company);
    }

    public void updateWorkplaceDoctor(@Nonnull final DoctorDTO doctorDTO) {
        final TWorkplaceDoctor doctor = workplaceDoctorRepository.findByUserId(doctorDTO.getUserId());
        doctor.setAfm(doctorDTO.getAfm());
        doctor.setCardType(doctorDTO.getCardType());
        doctor.setCardNumber(doctorDTO.getCardNumber());
        workplaceDoctorRepository.save(doctor);
    }

    public void updateSafetyTechnician(@Nonnull final TechnicianDTO technicianDTO) {
        final TSafetyTechnician technician = safetyTechnicianRepository.findByUserId(technicianDTO.getUserId());
        technician.setAfm(technicianDTO.getAfm());
        technician.setCardType(technicianDTO.getCardType());
        technician.setCardNumber(technicianDTO.getCardNumber());
        safetyTechnicianRepository.save(technician);
    }


    public void refreshAuthenticatedUser() {
        final Authentication currentAuthentication = SecurityContextHolder.getContext().getAuthentication();
        if (currentAuthentication.getPrincipal() instanceof SpringUserDetails) {
            final SpringUserDetails currentPrincipal = (SpringUserDetails) currentAuthentication.getPrincipal();
            final TUser refreshedUser = userRepository.findOne(currentPrincipal.getUserDTO().getId());
            final UserDTO userDTO = userDTOConverter.convert(refreshedUser);
            CompanyDTO company2 = null;
            String branchIds = null;
            if (currentPrincipal.getUserDTO().getRole() == Constants.USER_TYPE.COMPANY_USER.getCode()) {
                TCompanyUserPrivilages priv = companyUserPrivilagesRepositoryInternal.findByUser(userDTO.getId());
                Long companyId = priv.getCompId();

                final TCompany company = companyRepository.findOne(companyId);
                company2 = companyDTOConverter.convert(company);
                if (priv.getActive() == (long) 1)
                    branchIds = priv.getBranchIds();
                else
                    throw new UsernameNotFoundException("Δεν υπάρχει ο χρήστης");
            }
            final SpringUserDetails refreshedPrincipal = new SpringUserDetails(userDTO, userDTO.getId(), refreshedUser.getPassword(), company2, branchIds);
            final Authentication newAuthentication = new UsernamePasswordAuthenticationToken(refreshedPrincipal, refreshedUser.getPassword(), currentPrincipal.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(newAuthentication);
        } else {
            System.out.println("The currently-authenticated principal is not an instance of type SecurityConfig.SpringUserDetails");
        }
    }

    public boolean verifyPassword(
            @Nonnull final UserDTO userDTO,
            @Nonnull final String password
    ) {
        final TUser user = userRepository.findOne(userDTO.getId());
        final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(password, user.getPassword());
    }

    @Nonnull
    public String encryptPassword(@Nonnull final String rawPassword) {
        final String salt = BCrypt.gensalt(10, new SecureRandom());
        return BCrypt.hashpw(rawPassword, salt);
    }

    @Nullable
    public String getPasswordHash(@Nonnull final UserDTO userDTO) {
        final TUser user = userRepository.findOne(userDTO.getId());
        return user.getPassword();
    }

    @Nullable
    public String getPasswordHashForNewUser(@Nonnull final UserDTO userDTO) {
        final TUser user = userRepository.findByEmailEquals(userDTO.getEmail());
        return user.getPassword();
    }

    public void changeUserPassword(final UserDTO userDTO, final String password) {
        final TUser user = userRepository.findOne(userDTO.getId());
        if (user != null) {
            System.out.println(userDTO.getId() + password);
            user.setPassword(encryptPassword(password));
            userRepository.save(user);
        }
    }
/*
    public void changeUserPassword(final TUser user, final String password) {
        user.setPassword(encryptPassword(password));
        userRepository.save(user);
    }*/

    private boolean emailExist(final String email) {
        final TUser user = userRepository.findByEmailEquals(email);
        if (user != null) {
            return true;
        }
        return false;
    }

    private boolean usernameExist(final String username) {
        final TUser user = userRepository.findByUsernameEquals(username.toUpperCase());
        if (user != null) {
            return true;
        }
        return false;
    }
}
