package sepe.domain.company;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Nikos on 6/10/2015.
 */
@Entity
@Table(name = "SP_PTL_COMP_SEC_DIARY_ENG", schema = "SP_PTL", catalog = "")
public class SpPtlCompSecDiaryEng {
    private Long id;
    private String firstname;
    private String lastname;
    private String afm;
    private String engineerSpeciality;
    //private Long compSecDiaryId;
    private String teeNum;
    private String addr;
    private Long companyId;
    private String cardNumber;
    private Long cardType;
    private Timestamp birthdate;
    private String birthplace;
    private String cardIssuingAuth;

    @Id
    @GeneratedValue
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "FIRSTNAME")
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Basic
    @Column(name = "LASTNAME")
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Basic
    @Column(name = "AFM")
    public String getAfm() {
        return afm;
    }

    public void setAfm(String afm) {
        this.afm = afm;
    }

    @Basic
    @Column(name = "ENGINEER_SPECIALITY")
    public String getEngineerSpeciality() {
        return engineerSpeciality;
    }

    public void setEngineerSpeciality(String engineerSpeciality) {
        this.engineerSpeciality = engineerSpeciality;
    }

    /*
    @Basic
    @Column(name = "COMP_SEC_DIARY_ID")
    public Long getCompSecDiaryId() {
        return compSecDiaryId;
    }

    public void setCompSecDiaryId(Long compSecDiaryId) {
        this.compSecDiaryId = compSecDiaryId;
    }
*/

    @Basic
    @Column(name = "TEE_NUM")
    public String getTeeNum() {
        return teeNum;
    }

    public void setTeeNum(String teeNum) {
        this.teeNum = teeNum;
    }

    @Basic
    @Column(name = "ADDR")
    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    @Basic
    @Column(name = "COMPANY_ID")
    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    @Basic
    @Column(name = "CARD_NUMBER")
    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Basic
    @Column(name = "CARD_TYPE")
    public Long getCardType() {
        return cardType;
    }

    public void setCardType(Long cardType) {
        this.cardType = cardType;
    }

    @Basic
    @Column(name = "BIRTHDATE")
    public Timestamp getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Timestamp birthdate) {
        this.birthdate = birthdate;
    }

    @Basic
    @Column(name = "BIRTHPLACE")
    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    @Basic
    @Column(name = "CARD_ISSUING_AUTH")
    public String getCardIssuingAuth() {
        return cardIssuingAuth;
    }

    public void setCardIssuingAuth(String cardIssuingAuth) {
        this.cardIssuingAuth = cardIssuingAuth;
    }

    /////////////////////////////////////////////////
    // /*
    private SpPtlCompSecDiary compSecDiary_eng;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "COMP_SEC_DIARY_ID", nullable = true, insertable = true, updatable = true) // Name is the DB column name of the attribute
    public SpPtlCompSecDiary getCompSecDiary_eng() {
        return this.compSecDiary_eng;
    }

    public void setCompSecDiary_eng(SpPtlCompSecDiary compSecDiary_eng) {
        this.compSecDiary_eng = compSecDiary_eng;
    }
    // */
    /////////////////////////////////////////////////

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpPtlCompSecDiaryEng that = (SpPtlCompSecDiaryEng) o;

        if (id != that.id) return false;
        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
        if (afm != null ? !afm.equals(that.afm) : that.afm != null) return false;
        if (engineerSpeciality != null ? !engineerSpeciality.equals(that.engineerSpeciality) : that.engineerSpeciality != null)
            return false;
       // if (compSecDiaryId != null ? !compSecDiaryId.equals(that.compSecDiaryId) : that.compSecDiaryId != null) return false;

        if (teeNum != null ? !teeNum.equals(that.teeNum) : that.teeNum != null) return false;
        if (addr != null ? !addr.equals(that.addr) : that.addr != null) return false;
        if (companyId != null ? !companyId.equals(that.companyId) : that.companyId != null) return false;
        if (cardNumber != null ? !cardNumber.equals(that.cardNumber) : that.cardNumber != null) return false;
        if (cardType != null ? !cardType.equals(that.cardType) : that.cardType != null) return false;
        if (birthdate != null ? !birthdate.equals(that.birthdate) : that.birthdate != null) return false;
        if (birthplace != null ? !birthplace.equals(that.birthplace) : that.birthplace != null) return false;
        if (cardIssuingAuth != null ? !cardIssuingAuth.equals(that.cardIssuingAuth) : that.cardIssuingAuth != null)
            return false;

        return true;
    }

    /*
    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (afm != null ? afm.hashCode() : 0);
        result = 31 * result + (engineerSpeciality != null ? engineerSpeciality.hashCode() : 0);
        //result = 31 * result + (compSecDiaryId != null ? compSecDiaryId.hashCode() : 0);
        result = 31 * result + (teeNum != null ? teeNum.hashCode() : 0);
        result = 31 * result + (addr != null ? addr.hashCode() : 0);
        result = 31 * result + (companyId != null ? companyId.hashCode() : 0);
        result = 31 * result + (cardNumber != null ? cardNumber.hashCode() : 0);
        result = 31 * result + (cardType != null ? cardType.hashCode() : 0);
        result = 31 * result + (birthdate != null ? birthdate.hashCode() : 0);
        result = 31 * result + (birthplace != null ? birthplace.hashCode() : 0);
        result = 31 * result + (cardIssuingAuth != null ? cardIssuingAuth.hashCode() : 0);
        return result;
    }
    */
}
