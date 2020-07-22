package sepe.domain.company;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Nikos on 6/10/2015.
 */
@Entity
@javax.persistence.Table(name = "SP_PTL_COMP_SEC_DIARY_CONTR", schema = "SP_PTL", catalog = "")
public class SpPtlCompSecDiaryContr {
    private Long id;

    @Id
    @GeneratedValue
    @javax.persistence.Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String firstname;

    @Basic
    @javax.persistence.Column(name = "FIRSTNAME")
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    private String lastname;

    @Basic
    @javax.persistence.Column(name = "LASTNAME")
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    private String afm;

    @Basic
    @javax.persistence.Column(name = "AFM")
    public String getAfm() {
        return afm;
    }

    public void setAfm(String afm) {
        this.afm = afm;
    }

/*
    private Long compSecDiaryId;

    @Basic
    @javax.persistence.Column(name = "COMP_SEC_DIARY_ID")
    public Long getCompSecDiaryId() {
        return compSecDiaryId;
    }

    public void setCompSecDiaryId(Long compSecDiaryId) {
        this.compSecDiaryId = compSecDiaryId;
    }
*/

    private String addr;

    @Basic
    @javax.persistence.Column(name = "ADDR")
    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    private Integer type;

    @Basic
    @javax.persistence.Column(name = "TYPE")
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    private Long companyId;

    @Basic
    @javax.persistence.Column(name = "COMPANY_ID")
    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    private Long contractorSpecialty;

    @Basic
    @javax.persistence.Column(name = "CONTRACTOR_SPECIALTY")
    public Long getContractorSpecialty() {
        return contractorSpecialty;
    }

    public void setContractorSpecialty(Long contractorSpecialty) {
        this.contractorSpecialty = contractorSpecialty;
    }

    private String birthplace;

    @Basic
    @javax.persistence.Column(name = "BIRTHPLACE")
    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    private Timestamp birthdate;

    @Basic
    @javax.persistence.Column(name = "BIRTHDATE")
    public Timestamp getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Timestamp birthdate) {
        this.birthdate = birthdate;
    }

    private String cardNumber;

    @Basic
    @javax.persistence.Column(name = "CARD_NUMBER")
    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    private Long cardType;

    @Basic
    @javax.persistence.Column(name = "CARD_TYPE")
    public Long getCardType() {
        return cardType;
    }

    public void setCardType(Long cardType) {
        this.cardType = cardType;
    }


    private String cardIssuingAuth;

    @Basic
    @javax.persistence.Column(name = "CARD_ISSUING_AUTH")
    public String getCardIssuingAuth() {
        return cardIssuingAuth;
    }

    public void setCardIssuingAuth(String cardIssuingAuth) {
        this.cardIssuingAuth = cardIssuingAuth;
    }

    private Timestamp dateStart;

    @Basic
    @javax.persistence.Column(name = "DATE_START")
    public Timestamp getDateStart() {
        return dateStart;
    }

    public void setDateStart(Timestamp dateStart) {
        this.dateStart = dateStart;
    }

    private Timestamp dateEnd;

    @Basic
    @javax.persistence.Column(name = "DATE_END")
    public Timestamp getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Timestamp dateEnd) {
        this.dateEnd = dateEnd;
    }

    /////////////////////////////////////////////////
    // /*
    private SpPtlCompSecDiary compSecDiary_contr;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "COMP_SEC_DIARY_ID", nullable = true, insertable = true, updatable = true) // Name is the DB column name of the attribute
    public SpPtlCompSecDiary getCompSecDiary_contr() {
        return this.compSecDiary_contr;
    }

    public void setCompSecDiary_contr(SpPtlCompSecDiary compSecDiary_contr) {
        this.compSecDiary_contr = compSecDiary_contr;
    }
    // */
    /////////////////////////////////////////////////



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpPtlCompSecDiaryContr that = (SpPtlCompSecDiaryContr) o;

        if (id != that.id) return false;
        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
        if (afm != null ? !afm.equals(that.afm) : that.afm != null) return false;
        //if (compSecDiaryId != null ? !compSecDiaryId.equals(that.compSecDiaryId) : that.compSecDiaryId != null) return false;
        if (addr != null ? !addr.equals(that.addr) : that.addr != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (companyId != null ? !companyId.equals(that.companyId) : that.companyId != null) return false;
        if (contractorSpecialty != null ? !contractorSpecialty.equals(that.contractorSpecialty) : that.contractorSpecialty != null)
            return false;
        if (birthplace != null ? !birthplace.equals(that.birthplace) : that.birthplace != null) return false;
        if (birthdate != null ? !birthdate.equals(that.birthdate) : that.birthdate != null) return false;
        if (cardNumber != null ? !cardNumber.equals(that.cardNumber) : that.cardNumber != null) return false;
        if (cardType != null ? !cardType.equals(that.cardType) : that.cardType != null) return false;
        if (cardIssuingAuth != null ? !cardIssuingAuth.equals(that.cardIssuingAuth) : that.cardIssuingAuth != null)
            return false;
        if (dateStart != null ? !dateStart.equals(that.dateStart) : that.dateStart != null) return false;
        if (dateEnd != null ? !dateEnd.equals(that.dateEnd) : that.dateEnd != null) return false;

        return true;
    }

    /*
    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (afm != null ? afm.hashCode() : 0);
        //result = 31 * result + (compSecDiaryId != null ? compSecDiaryId.hashCode() : 0);
        result = 31 * result + (addr != null ? addr.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (companyId != null ? companyId.hashCode() : 0);
        result = 31 * result + (contractorSpecialty != null ? contractorSpecialty.hashCode() : 0);
        result = 31 * result + (birthplace != null ? birthplace.hashCode() : 0);
        result = 31 * result + (birthdate != null ? birthdate.hashCode() : 0);
        result = 31 * result + (cardNumber != null ? cardNumber.hashCode() : 0);
        result = 31 * result + (cardType != null ? cardType.hashCode() : 0);
        result = 31 * result + (cardIssuingAuth != null ? cardIssuingAuth.hashCode() : 0);
        result = 31 * result + (dateStart != null ? dateStart.hashCode() : 0);
        result = 31 * result + (dateEnd != null ? dateEnd.hashCode() : 0);
        return result;
    }
    */
}
