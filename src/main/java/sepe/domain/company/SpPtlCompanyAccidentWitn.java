package sepe.domain.company;

import javax.persistence.*;

/**
 * Created by Nick on 4/29/2015.
 */
@Entity
@Table(name = "SP_PTL_COMPANY_ACCIDENT_WITN", schema = "SP_PTL", catalog = "")
public class SpPtlCompanyAccidentWitn {
    private Long id;
    private Long accidentId;
    private Integer incNum;
    private String firstname;
    private String lastname;
    private String addr;
    private String addrTk;
    private String typeId;
    private String citizenshipCd;
    private String phone;
    private Long companyId;

    @Id
    @GeneratedValue
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /*
    @Basic
    @Column(name = "ACCIDENT_ID")
    public Long getAccidentId() {
        return accidentId;
    }

    public void setAccidentId(Long accidentId) {
        this.accidentId = accidentId;
    }
    */

    @Basic
    @Column(name = "INC_NUM")
    public Integer getIncNum() {
        return incNum;
    }

    public void setIncNum(Integer incNum) {
        this.incNum = incNum;
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
    @Column(name = "ADDR")
    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    @Basic
    @Column(name = "ADDR_TK")
    public String getAddrTk() {
        return addrTk;
    }

    public void setAddrTk(String addrTk) {
        this.addrTk = addrTk;
    }

    @Basic
    @Column(name = "TYPE_ID")
    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    @Basic
    @Column(name = "CITIZENSHIP_CD")
    public String getCitizenshipCd() {
        return citizenshipCd;
    }

    public void setCitizenshipCd(String citizenshipCd) {
        this.citizenshipCd = citizenshipCd;
    }

    @Basic
    @Column(name = "PHONE")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "COMPANY_ID")
    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    /////////////////////////////////////////////////
    // /*
    private SpPtlCompanyAccident companyAccident;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCIDENT_ID", nullable = false, insertable = true, updatable = false) // Name is the DB column name of the attribute
    public SpPtlCompanyAccident getCompanyAccident() {
        return this.companyAccident;
    }

    public void setCompanyAccident(SpPtlCompanyAccident companyAccident) {
        this.companyAccident = companyAccident;
    }
    // */
    /////////////////////////////////////////////////

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpPtlCompanyAccidentWitn that = (SpPtlCompanyAccidentWitn) o;

        if (id != that.id) return false;
        if (accidentId != null ? !accidentId.equals(that.accidentId) : that.accidentId != null) return false;
        if (addr != null ? !addr.equals(that.addr) : that.addr != null) return false;
        if (addrTk != null ? !addrTk.equals(that.addrTk) : that.addrTk != null) return false;
        if (citizenshipCd != null ? !citizenshipCd.equals(that.citizenshipCd) : that.citizenshipCd != null)
            return false;
        if (companyId != null ? !companyId.equals(that.companyId) : that.companyId != null) return false;
        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        if (incNum != null ? !incNum.equals(that.incNum) : that.incNum != null) return false;
        if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (typeId != null ? !typeId.equals(that.typeId) : that.typeId != null) return false;

        return true;
    }

    /*@Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (accidentId != null ? accidentId.hashCode() : 0);
        result = 31 * result + (incNum != null ? incNum.hashCode() : 0);
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (addr != null ? addr.hashCode() : 0);
        result = 31 * result + (addrTk != null ? addrTk.hashCode() : 0);
        result = 31 * result + (typeId != null ? typeId.hashCode() : 0);
        result = 31 * result + (citizenshipCd != null ? citizenshipCd.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (companyId != null ? companyId.hashCode() : 0);
        return result;
    }*/
}
