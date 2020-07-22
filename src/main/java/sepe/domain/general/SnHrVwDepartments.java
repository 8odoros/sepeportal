package sepe.domain.general;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;


/**
 * Created by kirikosm on 9/12/2016.
 */
@Entity
@javax.persistence.Table(name = "SN_HR_VW_DEPARTMENTS", schema = "SP_PTL", catalog = "")
public class SnHrVwDepartments {
    private Long hrDeptCd;

    @Id
    @javax.persistence.Column(name = "HR_DEPT_CD")
    public Long getHrDeptCd() {
        return hrDeptCd;
    }

    public void setHrDeptCd(Long hrDeptCd) {
        this.hrDeptCd = hrDeptCd;
    }

    private String hrDeptDescr;

    @Basic
    @GeneratedValue
    @javax.persistence.Column(name = "HR_DEPT_DESCR")
    public String getHrDeptDescr() {
        return hrDeptDescr;
    }

    public void setHrDeptDescr(String hrDeptDescr) {
        this.hrDeptDescr = hrDeptDescr;
    }

    private Long hrDeptReferenceCd;

    @Basic
    @javax.persistence.Column(name = "HR_DEPT_REFERENCE_CD")
    public Long getHrDeptReferenceCd() {
        return hrDeptReferenceCd;
    }

    public void setHrDeptReferenceCd(Long hrDeptReferenceCd) {
        this.hrDeptReferenceCd = hrDeptReferenceCd;
    }

    private String hrDeptKind;

    @Basic
    @javax.persistence.Column(name = "HR_DEPT_KIND")
    public String getHrDeptKind() {
        return hrDeptKind;
    }

    public void setHrDeptKind(String hrDeptKind) {
        this.hrDeptKind = hrDeptKind;
    }

    private Integer hrDeptSeqment;

    @Basic
    @javax.persistence.Column(name = "HR_DEPT_SEQMENT")
    public Integer getHrDeptSeqment() {
        return hrDeptSeqment;
    }

    public void setHrDeptSeqment(Integer hrDeptSeqment) {
        this.hrDeptSeqment = hrDeptSeqment;
    }

    private Timestamp hrDeptSeqmentDescr;

    @Basic
    @javax.persistence.Column(name = "HR_DEPT_SEQMENT_DESCR")
    public Timestamp getHrDeptSeqmentDescr() {
        return hrDeptSeqmentDescr;
    }

    public void setHrDeptSeqmentDescr(Timestamp hrDeptSeqmentDescr) {
        this.hrDeptSeqmentDescr = hrDeptSeqmentDescr;
    }

    private Long hrDeptDivision;

    @Basic
    @javax.persistence.Column(name = "HR_DEPT_DIVISION")
    public Long getHrDeptDivision() {
        return hrDeptDivision;
    }

    public void setHrDeptDivision(Long rgTaProfileCheckFlag) {
        this.hrDeptDivision = hrDeptDivision;
    }

    private Long hrDeptDivisionDescr;

    @Basic
    @javax.persistence.Column(name = "HR_DEPT_DIVISION_DESCR")
    public Long getHrDeptDivisionDescr() {
        return hrDeptDivisionDescr;
    }

    public void setHrDeptDivisionDescr(Long hrDeptDivisionDescr) {
        this.hrDeptDivisionDescr = hrDeptDivisionDescr;
    }

    private Long hrDeptStreet;

    @Basic
    @javax.persistence.Column(name = "HR_DEPT_STREET")
    public Long getHrDeptStreet() {
        return hrDeptStreet;
    }

    public void setHrDeptStreet(Long hrDeptStreet) {
        this.hrDeptStreet = hrDeptStreet;
    }

    private Long hrDeptStreetNr;

    @Basic
    @javax.persistence.Column(name = "HR_DEPT_STREET_NR")
    public Long getHrDeptStreetNr() {
        return hrDeptStreetNr;
    }

    public void setHrDeptStreetNr(Long hrDeptStreetNr) {
        this.hrDeptStreetNr = hrDeptStreetNr;
    }

    private Long hrDeptCity;

    @Basic
    @javax.persistence.Column(name = "HR_DEPT_CITY")
    public Long getHrDeptCity() {
        return hrDeptCity;
    }

    public void setHrDeptCity(Long hrDeptCity) {
        this.hrDeptCity = hrDeptCity;
    }

    private Long hrDeptTk;

    @Basic
    @javax.persistence.Column(name = "HR_DEPT_TK")
    public Long getHrDeptTk() {
        return hrDeptTk;
    }

    public void setHrDeptTk(Long hrDeptTk) {
        this.hrDeptTk = hrDeptTk;
    }

    private String hrDeptPhone;

    @Basic
    @javax.persistence.Column(name = "HR_DEPT_PHONE")
    public String getHrDeptPhone() {
        return hrDeptPhone;
    }

    public void setHrDeptPhone(String hrDeptPhone) {
        this.hrDeptPhone = hrDeptPhone;
    }

    private Long hrDeptFax;

    @Basic
    @javax.persistence.Column(name = "HR_DEPT_FAX")
    public Long getHrDeptFax() {
        return hrDeptFax;
    }

    public void setHrDeptFax(Long hrDeptFax) {
        this.hrDeptFax = hrDeptFax;
    }

    private String hrDeptEmail;

    @Basic
    @javax.persistence.Column(name = "HR_DEPT_EMAIL")
    public String getHrDeptEmail() {
        return hrDeptEmail;
    }

    public void setHrDeptEmail(String hrDeptEmail) {
        this.hrDeptEmail = hrDeptEmail;
    }

    private Long hrDeptYde;

    @Basic
    @javax.persistence.Column(name = "HR_DEPT_YDE")
    public Long getHrDeptYde() {
        return hrDeptYde;
    }

    public void setHrDeptYde(Long hrDeptYde) {
        this.hrDeptYde = hrDeptYde;
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SnHrVwDepartments that = (SnHrVwDepartments) o;

        if (hrDeptCd != that.hrDeptCd) return false;
        if (hrDeptDescr != null ? !hrDeptDescr.equals(that.hrDeptDescr) : that.hrDeptDescr != null) return false;
        if (hrDeptReferenceCd != null ? !hrDeptReferenceCd.equals(that.hrDeptReferenceCd) : that.hrDeptReferenceCd != null) return false;
        if (hrDeptKind != null ? !hrDeptKind.equals(that.hrDeptKind) : that.hrDeptKind != null) return false;
        if (hrDeptSeqment != null ? !hrDeptSeqment.equals(that.hrDeptSeqment) : that.hrDeptSeqment != null) return false;
        if (hrDeptSeqmentDescr != null ? !hrDeptSeqmentDescr.equals(that.hrDeptSeqmentDescr) : that.hrDeptSeqmentDescr != null) return false;
        if (hrDeptDivision != null ? !hrDeptDivision.equals(that.hrDeptDivision) : that.hrDeptDivision != null) return false;
        if (hrDeptDivisionDescr != null ? !hrDeptDivisionDescr.equals(that.hrDeptDivisionDescr) : that.hrDeptDivisionDescr != null)
            return false;
        if (hrDeptStreet != null ? !hrDeptStreet.equals(that.hrDeptStreet) : that.hrDeptStreet != null) return false;
        if (hrDeptStreetNr != null ? !hrDeptStreetNr.equals(that.hrDeptStreetNr) : that.hrDeptStreetNr != null) return false;
        if (hrDeptCity != null ? !hrDeptCity.equals(that.hrDeptCity) : that.hrDeptCity != null) return false;
        if (hrDeptTk != null ? !hrDeptTk.equals(that.hrDeptTk) : that.hrDeptTk != null) return false;
        if (hrDeptPhone != null ? !hrDeptPhone.equals(that.hrDeptPhone) : that.hrDeptPhone != null) return false;
        if (hrDeptFax != null ? !hrDeptFax.equals(that.hrDeptFax) : that.hrDeptFax != null) return false;
        if (hrDeptEmail != null ? !hrDeptEmail.equals(that.hrDeptEmail) : that.hrDeptEmail != null) return false;
        if (hrDeptYde != null ? !hrDeptYde.equals(that.hrDeptYde) : that.hrDeptYde != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (hrDeptCd ^ (hrDeptCd >>> 32));
        result = 31 * result + (hrDeptDescr != null ? hrDeptDescr.hashCode() : 0);
        result = 31 * result + (hrDeptReferenceCd != null ? hrDeptReferenceCd.hashCode() : 0);
        result = 31 * result + (hrDeptKind != null ? hrDeptKind.hashCode() : 0);
        result = 31 * result + (hrDeptSeqment != null ? hrDeptSeqment.hashCode() : 0);
        result = 31 * result + (hrDeptSeqmentDescr != null ? hrDeptSeqmentDescr.hashCode() : 0);
        result = 31 * result + (hrDeptDivision != null ? hrDeptDivision.hashCode() : 0);
        result = 31 * result + (hrDeptDivisionDescr != null ? hrDeptDivisionDescr.hashCode() : 0);
        result = 31 * result + (hrDeptStreet != null ? hrDeptStreet.hashCode() : 0);
        result = 31 * result + (hrDeptStreetNr != null ? hrDeptStreetNr.hashCode() : 0);
        result = 31 * result + (hrDeptCity != null ? hrDeptCity.hashCode() : 0);
        result = 31 * result + (hrDeptTk != null ? hrDeptTk.hashCode() : 0);
        result = 31 * result + (hrDeptPhone != null ? hrDeptPhone.hashCode() : 0);
        result = 31 * result + (hrDeptFax != null ? hrDeptFax.hashCode() : 0);
        result = 31 * result + (hrDeptEmail != null ? hrDeptEmail.hashCode() : 0);
        result = 31 * result + (hrDeptYde != null ? hrDeptYde.hashCode() : 0);
        return result;
    }
}

