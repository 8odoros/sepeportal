package sepe.domain;

import sepe.domain.company.SpPtlCompTaAnnDiaryEntry;
import sepe.domain.company.SpPtlCompanyAccident;

import javax.annotation.Nonnull;
import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "SP_PTL_USER", schema = "SP_PTL", catalog = "")
public class TUser {
    private Long id;
    private Integer isAdmin;
    private Integer role;
    private Integer status;
    private String username;
    private String password;
    private String email;
    private Integer emailNotifEn;
    private String firstname;
    private String lastname;
    private String phone;
    private String mobile;
    private String addr;
    private String addrTk;
    private String fax;


    public TUser(){

    }
    public TUser(Long id, String email, String password) {
        this.id=id;
        this.email=email;
        this.password=password;
    }

    public TUser(
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
            final String addr,
            final String addrTk
    ) {
        //this.id = id;

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
        this.addr = addr;
        this.addrTk = addrTk;
    }


    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false, insertable = true, updatable = true, precision = 0)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "IS_ADMIN", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Basic
    @Column(name = "ROLE", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    @Basic
    @Column(name = "STATUS", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Basic
    @Column(name = "USERNAME", nullable = true, insertable = true, updatable = true, length = 50)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "PASSWORD", nullable = true, insertable = true, updatable = true, length = 124)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "EMAIL", nullable = true, insertable = true, updatable = true, length = 50)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "EMAIL_NOTIF_EN", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getEmailNotifEn() {
        return emailNotifEn;
    }

    public void setEmailNotifEn(Integer emailNotifEn) {
        this.emailNotifEn = emailNotifEn;
    }

    @Basic
    @Column(name = "FIRSTNAME", nullable = true, insertable = true, updatable = true, length = 50)
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Basic
    @Column(name = "LASTNAME", nullable = true, insertable = true, updatable = true, length = 50)
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Basic
    @Column(name = "PHONE", nullable = true, insertable = true, updatable = true, length = 50)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "MOBILE", nullable = true, insertable = true, updatable = true, length = 50)
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Basic
    @Column(name = "ADDR", nullable = true, insertable = true, updatable = true, length = 100)
    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    @Basic
    @Column(name = "ADDR_TK", nullable = true, insertable = true, updatable = true, length = 20)
    public String getAddrTk() {
        return addrTk;
    }

    public void setAddrTk(String addrTk) {
        this.addrTk = addrTk;
    }

    @Basic
    @Column(name = "FAX", nullable = true, insertable = true, updatable = true, length = 50)
    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }


    /////////////////////////////////////////////////
    // /*
    /*private Set<SpPtlCompanyAccident> spPtlCompanyAccident = new HashSet<SpPtlCompanyAccident>(0);

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "tUser", cascade = CascadeType.ALL, orphanRemoval = true) // SHOULD BE EQUAL TO ATTRIBUTE NAME OF OTHER CLASS!!!!
    public Set<SpPtlCompanyAccident> getSpPtlCompanyAccident() {
        return this.spPtlCompanyAccident;
    }

    public void setSpPtlCompanyAccident(Set<SpPtlCompanyAccident> spPtlCompanyAccident) {
        this.spPtlCompanyAccident = spPtlCompanyAccident;
    }*/
    // */
    /////////////////////////////////////////////////

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TUser tUser = (TUser) o;

        if (id != tUser.id) return false;
        if (addr != null ? !addr.equals(tUser.addr) : tUser.addr != null) return false;
        if (addrTk != null ? !addrTk.equals(tUser.addrTk) : tUser.addrTk != null) return false;
        if (email != null ? !email.equals(tUser.email) : tUser.email != null) return false;
        if (emailNotifEn != null ? !emailNotifEn.equals(tUser.emailNotifEn) : tUser.emailNotifEn != null) return false;
        if (fax != null ? !fax.equals(tUser.fax) : tUser.fax != null) return false;
        if (firstname != null ? !firstname.equals(tUser.firstname) : tUser.firstname != null) return false;
        if (isAdmin != null ? !isAdmin.equals(tUser.isAdmin) : tUser.isAdmin != null) return false;
        if (lastname != null ? !lastname.equals(tUser.lastname) : tUser.lastname != null) return false;
        if (mobile != null ? !mobile.equals(tUser.mobile) : tUser.mobile != null) return false;
        if (password != null ? !password.equals(tUser.password) : tUser.password != null) return false;
        if (phone != null ? !phone.equals(tUser.phone) : tUser.phone != null) return false;
        if (role != null ? !role.equals(tUser.role) : tUser.role != null) return false;
        if (status != null ? !status.equals(tUser.status) : tUser.status != null) return false;
        if (username != null ? !username.equals(tUser.username) : tUser.username != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (isAdmin != null ? isAdmin.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (emailNotifEn != null ? emailNotifEn.hashCode() : 0);
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
        result = 31 * result + (addr != null ? addr.hashCode() : 0);
        result = 31 * result + (addrTk != null ? addrTk.hashCode() : 0);
        result = 31 * result + (fax != null ? fax.hashCode() : 0);
        return result;
    }
}
