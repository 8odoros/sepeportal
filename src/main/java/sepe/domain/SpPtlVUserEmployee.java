package sepe.domain;

        import javax.annotation.Nonnull;
        import javax.persistence.*;
        import java.sql.Timestamp;

@Entity
@Table(name = "SP_PTL_V_USER_EMPLOYEE", schema = "SP_PTL", catalog = "")
public class SpPtlVUserEmployee {
    private Long id;
    private Integer isAdmin;
    private Integer role;
    private Integer status;
    private String username;
    private String email;
    private Integer emailNotifEn;
    private String firstname;
    private String lastname;
    private String phone;
    private String mobile;
    private String addr;
    private String addrTk;
    private String fax;
    private Integer isExypp;

    private String cardNumber;
    private String afm;
    private String amka;
    private String jobTitle;
    private Timestamp birthDate;


    public SpPtlVUserEmployee(){

    }
    public SpPtlVUserEmployee(Long id, String email) {
        this.id=id;
        this.email=email;
    }

    public SpPtlVUserEmployee(
            @Nonnull final Long id,
            final Integer isAdmin,
            @Nonnull final String email,
            @Nonnull final Integer role,
            final Integer status,
            final String username,
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

    @Basic
    @Column(name = "IS_EXYPP")
    public Integer getIsExypp() {
        return isExypp;
    }

    public void setIsExypp(Integer isExypp) {
        this.isExypp = isExypp;
    }

    @Basic
    @Column(name = "CARD_NUMBER", nullable = true, insertable = true, updatable = true, length = 50)
    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Basic
    @Column(name = "AFM", nullable = true, insertable = true, updatable = true, length = 50)
    public String getAfm() {
        return afm;
    }

    public void setAfm(String afm) {
        this.afm = afm;
    }

    @Basic
    @Column(name = "AMKA", nullable = true, insertable = true, updatable = true, length = 50)
    public String getAmka() {
        return amka;
    }

    public void setAmka(String amka) {
        this.amka = amka;
    }

    @Basic
    @Column(name = "JOB_TITLE", nullable = true, insertable = true, updatable = true, length = 100)
    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @Basic
    @Column(name = "BIRTH_DATE", nullable = true, insertable = true, updatable = true)
    public Timestamp getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Timestamp birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpPtlVUserEmployee spPtlVUserEmployee = (SpPtlVUserEmployee) o;

        if (id != spPtlVUserEmployee.id) return false;
        if (addr != null ? !addr.equals(spPtlVUserEmployee.addr) : spPtlVUserEmployee.addr != null) return false;
        if (addrTk != null ? !addrTk.equals(spPtlVUserEmployee.addrTk) : spPtlVUserEmployee.addrTk != null) return false;
        if (email != null ? !email.equals(spPtlVUserEmployee.email) : spPtlVUserEmployee.email != null) return false;
        if (emailNotifEn != null ? !emailNotifEn.equals(spPtlVUserEmployee.emailNotifEn) : spPtlVUserEmployee.emailNotifEn != null) return false;
        if (fax != null ? !fax.equals(spPtlVUserEmployee.fax) : spPtlVUserEmployee.fax != null) return false;
        if (firstname != null ? !firstname.equals(spPtlVUserEmployee.firstname) : spPtlVUserEmployee.firstname != null) return false;
        if (isAdmin != null ? !isAdmin.equals(spPtlVUserEmployee.isAdmin) : spPtlVUserEmployee.isAdmin != null) return false;
        if (lastname != null ? !lastname.equals(spPtlVUserEmployee.lastname) : spPtlVUserEmployee.lastname != null) return false;
        if (mobile != null ? !mobile.equals(spPtlVUserEmployee.mobile) : spPtlVUserEmployee.mobile != null) return false;
        if (phone != null ? !phone.equals(spPtlVUserEmployee.phone) : spPtlVUserEmployee.phone != null) return false;
        if (role != null ? !role.equals(spPtlVUserEmployee.role) : spPtlVUserEmployee.role != null) return false;
        if (status != null ? !status.equals(spPtlVUserEmployee.status) : spPtlVUserEmployee.status != null) return false;
        if (username != null ? !username.equals(spPtlVUserEmployee.username) : spPtlVUserEmployee.username != null) return false;
        if (isExypp != null ? !isExypp.equals(spPtlVUserEmployee.isExypp) : spPtlVUserEmployee.isExypp != null) return false;
        if (cardNumber != null ? !cardNumber.equals(spPtlVUserEmployee.cardNumber) : spPtlVUserEmployee.cardNumber != null) return false;
        if (afm != null ? !afm.equals(spPtlVUserEmployee.afm) : spPtlVUserEmployee.afm != null) return false;
        if (amka != null ? !amka.equals(spPtlVUserEmployee.amka) : spPtlVUserEmployee.amka != null) return false;
        if (jobTitle != null ? !jobTitle.equals(spPtlVUserEmployee.jobTitle) : spPtlVUserEmployee.jobTitle != null) return false;
        if (birthDate != null ? !birthDate.equals(spPtlVUserEmployee.birthDate) : spPtlVUserEmployee.birthDate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (isAdmin != null ? isAdmin.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (emailNotifEn != null ? emailNotifEn.hashCode() : 0);
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
        result = 31 * result + (addr != null ? addr.hashCode() : 0);
        result = 31 * result + (addrTk != null ? addrTk.hashCode() : 0);
        result = 31 * result + (fax != null ? fax.hashCode() : 0);
        result = 31 * result + (isExypp != null ? isExypp.hashCode() : 0);
        result = 31 * result + (cardNumber != null ? cardNumber.hashCode() : 0);
        result = 31 * result + (afm != null ? afm.hashCode() : 0);
        result = 31 * result + (amka != null ? amka.hashCode() : 0);
        result = 31 * result + (jobTitle != null ? jobTitle.hashCode() : 0);
        result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
        return result;
    }
}
