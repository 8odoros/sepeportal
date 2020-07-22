package sepe.domain.company;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Nikos on 6/13/2015.
 */
@Entity
@Table(name = "SP_PTL_COMP_TA_SANN_CONTR", schema = "SP_PTL", catalog = "")
public class SpPtlCompTaSannContr {
    private Long id;
    private String firstname;
    private String lastname;
    private String afm;
    //private Long compTaSannId;
    private String addr;
    private Integer type;
    private Long companyId;
    private Long contractorSpecialty;
    private String birthplace;
    private Timestamp birthdate;
    private String cardNumber;
    private Long cardType;
    private String cardIssuingAuth;

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
    @Column(name = "FIRSTNAME", nullable = true, insertable = true, updatable = true, length = 100)
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Basic
    @Column(name = "LASTNAME", nullable = true, insertable = true, updatable = true, length = 100)
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Basic
    @Column(name = "AFM", nullable = true, insertable = true, updatable = true, length = 50)
    public String getAfm() {
        return afm;
    }

    public void setAfm(String afm) {
        this.afm = afm;
    }

    /*
    @Basic
    @Column(name = "COMP_TA_SANN_ID", nullable = false, insertable = true, updatable = true, precision = 0)
    public Long getCompTaSannId() {
        return compTaSannId;
    }

    public void setCompTaSannId(Long compTaSannId) {
        this.compTaSannId = compTaSannId;
    }
    */

    @Basic
    @Column(name = "ADDR", nullable = true, insertable = true, updatable = true, length = 200)
    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    @Basic
    @Column(name = "TYPE", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Basic
    @Column(name = "COMPANY_ID", nullable = false, insertable = true, updatable = true, precision = 0)
    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    @Basic
    @Column(name = "CONTRACTOR_SPECIALTY", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getContractorSpecialty() {
        return contractorSpecialty;
    }

    public void setContractorSpecialty(Long contractorSpecialty) {
        this.contractorSpecialty = contractorSpecialty;
    }

    @Basic
    @Column(name = "BIRTHPLACE", nullable = true, insertable = true, updatable = true, length = 200)
    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    @Basic
    @Column(name = "BIRTHDATE", nullable = true, insertable = true, updatable = true)
    public Timestamp getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Timestamp birthdate) {
        this.birthdate = birthdate;
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
    @Column(name = "CARD_TYPE", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getCardType() {
        return cardType;
    }

    public void setCardType(Long cardType) {
        this.cardType = cardType;
    }

    @Basic
    @Column(name = "CARD_ISSUING_AUTH", nullable = true, insertable = true, updatable = true, length = 200)
    public String getCardIssuingAuth() {
        return cardIssuingAuth;
    }

    public void setCardIssuingAuth(String cardIssuingAuth) {
        this.cardIssuingAuth = cardIssuingAuth;
    }

    /////////////////////////////////////////////////
    // /*
    private SpPtlCompTaSann compTaSann_contr;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMP_TA_SANN_ID", nullable = false, insertable = true, updatable = false) // Name is the DB column name of the attribute
    public SpPtlCompTaSann getCompTaSann_contr() {
        return this.compTaSann_contr;
    }

    public void setCompTaSann_contr(SpPtlCompTaSann compTaSann_contr) {
        this.compTaSann_contr = compTaSann_contr;
    }
    // */
    /////////////////////////////////////////////////


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpPtlCompTaSannContr that = (SpPtlCompTaSannContr) o;

        //if (compTaSannId != that.compTaSannId) return false;
        if (companyId != that.companyId) return false;
        if (id != that.id) return false;
        if (addr != null ? !addr.equals(that.addr) : that.addr != null) return false;
        if (afm != null ? !afm.equals(that.afm) : that.afm != null) return false;
        if (birthdate != null ? !birthdate.equals(that.birthdate) : that.birthdate != null) return false;
        if (birthplace != null ? !birthplace.equals(that.birthplace) : that.birthplace != null) return false;
        if (cardIssuingAuth != null ? !cardIssuingAuth.equals(that.cardIssuingAuth) : that.cardIssuingAuth != null)
            return false;
        if (cardNumber != null ? !cardNumber.equals(that.cardNumber) : that.cardNumber != null) return false;
        if (cardType != null ? !cardType.equals(that.cardType) : that.cardType != null) return false;
        if (contractorSpecialty != null ? !contractorSpecialty.equals(that.contractorSpecialty) : that.contractorSpecialty != null)
            return false;
        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;

        return true;
    }

    /*
    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (afm != null ? afm.hashCode() : 0);
        //result = 31 * result + (int) (compTaSannId ^ (compTaSannId >>> 32));
        result = 31 * result + (addr != null ? addr.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (int) (companyId ^ (companyId >>> 32));
        result = 31 * result + (contractorSpecialty != null ? contractorSpecialty.hashCode() : 0);
        result = 31 * result + (birthplace != null ? birthplace.hashCode() : 0);
        result = 31 * result + (birthdate != null ? birthdate.hashCode() : 0);
        result = 31 * result + (cardNumber != null ? cardNumber.hashCode() : 0);
        result = 31 * result + (cardType != null ? cardType.hashCode() : 0);
        result = 31 * result + (cardIssuingAuth != null ? cardIssuingAuth.hashCode() : 0);
        return result;
    }
    */
}
