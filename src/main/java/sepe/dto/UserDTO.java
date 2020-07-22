package sepe.dto;

import org.hibernate.validator.constraints.NotEmpty;
import sepe.handlers.PasswordMatches;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@PasswordMatches
public final class UserDTO implements Serializable {

    private long id;

    private Integer isAdmin;
    private Integer role;
    private Integer status;

    @NotNull
    @NotEmpty
    private String username;

    @NotNull
    @NotEmpty
    @Size(min = 6, max = 6)
    private String password;

    //@NotNull
    //@NotEmpty
    private String matchingPassword;


    @NotNull
    @NotEmpty
    private String email;

    private Integer emailNotifEn;

    @NotNull
    @NotEmpty
    private String firstname;

    @NotNull
    @NotEmpty
    private String lastname;
    private String phone;


    @NotNull
    @NotEmpty
    @Size(min = 10, max = 10)
    private String mobile;
    private String fax;
    private String address;
    private String addrTk;

    public UserDTO(
            @Nonnull final Long id,

            final Integer isAdmin,
            @Nonnull final String email,
            @Nonnull final Integer role,
            final Integer status,
            final String username,
            final String password,
            final Integer emailNotifEn,
            final String firstname,
            final String lastname,
            final String phone,
            final String mobile,
            final String fax,
            final String address,
            final String addrTk
    ) {
        this.id = id;

        this.isAdmin = isAdmin;
        this.email = email;
        this.role = role;
        this.status = status;
        this.username = username;
        this.password = password;
        this.emailNotifEn = emailNotifEn;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.mobile = mobile;
        this.fax = fax;
        this.address = address;
        this.addrTk = addrTk;
    }

    public UserDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Nullable
    public Integer getEmailNotifEn() { return emailNotifEn; }

    public void setEmailNotifEn(@Nonnull Integer emailNotifEn) {
        this.emailNotifEn = emailNotifEn;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddrTk() {
        return addrTk;
    }

    public void setAddrTk(String addrTk) {
        this.addrTk = addrTk;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    @Override
    public boolean equals(final Object other) {
        boolean equals = false;
        if (other instanceof UserDTO) {
            final UserDTO that = (UserDTO) other;
            equals = this.getId() == that.getId()
                    && this.getEmail().equals(that.getEmail())
            ;
        }
        return equals;
    }

    /*
    @Override
    public int hashCode() {
        final int idHash = (id == null) ? 0 : id.hashCode();

        final int emailHash = (email == null) ? 0 : email.hashCode();
        final int roleHash = (role == null) ? 0 : role.hashCode();

        return idHash + emailHash + roleHash;
    }
*/
}
