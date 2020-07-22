package sepe.domain.employee;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Nikolas on 1/24/2015.
 */
@Entity
@Table(name = "SP_PTL_EMPLOYEE", schema = "SP_PTL", catalog = "")
public class TEmployee {
    private Long id;
    private Long userId;
    private String cardNumber;
    private String afm;
    private String amka;
    private String fatherName;
    private String companyName;
    private String jobTitle;
    private Integer cardType;
    private Integer citizenshipCode;
    private String sex;
    private Timestamp birthDate;
    private String motherName;

    @Id
    @Column(name = "ID", nullable = false, insertable = true, updatable = true, precision = 0)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "USER_ID", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
    @Column(name = "FATHER_NAME", nullable = true, insertable = true, updatable = true, length = 200)
    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    @Basic
    @Column(name = "COMPANY_NAME", nullable = true, insertable = true, updatable = true, length = 300)
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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
    @Column(name = "CARD_TYPE", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getCardType() {
        return cardType;
    }

    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

    @Basic
    @Column(name = "CITIZENSHIP_CODE", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getCitizenshipCode() {
        return citizenshipCode;
    }

    public void setCitizenshipCode(Integer citizenshipCode) {
        this.citizenshipCode = citizenshipCode;
    }

    @Basic
    @Column(name = "SEX", nullable = true, insertable = true, updatable = true, length = 1)
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "BIRTH_DATE", nullable = true, insertable = true, updatable = true)
    public Timestamp getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Timestamp birthDate) {
        this.birthDate = birthDate;
    }

    @Basic
    @Column(name = "MOTHER_NAME", nullable = true, insertable = true, updatable = true, length = 200)
    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TEmployee that = (TEmployee) o;

        if (id != that.id) return false;
        if (afm != null ? !afm.equals(that.afm) : that.afm != null) return false;
        if (amka != null ? !amka.equals(that.amka) : that.amka != null) return false;
        if (birthDate != null ? !birthDate.equals(that.birthDate) : that.birthDate != null) return false;
        if (cardNumber != null ? !cardNumber.equals(that.cardNumber) : that.cardNumber != null) return false;
        if (cardType != null ? !cardType.equals(that.cardType) : that.cardType != null) return false;
        if (citizenshipCode != null ? !citizenshipCode.equals(that.citizenshipCode) : that.citizenshipCode != null)
            return false;
        if (companyName != null ? !companyName.equals(that.companyName) : that.companyName != null) return false;
        if (fatherName != null ? !fatherName.equals(that.fatherName) : that.fatherName != null) return false;
        if (jobTitle != null ? !jobTitle.equals(that.jobTitle) : that.jobTitle != null) return false;
        if (motherName != null ? !motherName.equals(that.motherName) : that.motherName != null) return false;
        if (sex != null ? !sex.equals(that.sex) : that.sex != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (cardNumber != null ? cardNumber.hashCode() : 0);
        result = 31 * result + (afm != null ? afm.hashCode() : 0);
        result = 31 * result + (amka != null ? amka.hashCode() : 0);
        result = 31 * result + (fatherName != null ? fatherName.hashCode() : 0);
        result = 31 * result + (companyName != null ? companyName.hashCode() : 0);
        result = 31 * result + (jobTitle != null ? jobTitle.hashCode() : 0);
        result = 31 * result + (cardType != null ? cardType.hashCode() : 0);
        result = 31 * result + (citizenshipCode != null ? citizenshipCode.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
        result = 31 * result + (motherName != null ? motherName.hashCode() : 0);
        return result;
    }
}