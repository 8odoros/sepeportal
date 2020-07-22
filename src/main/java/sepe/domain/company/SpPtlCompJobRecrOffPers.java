package sepe.domain.company;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by Nick on 4/29/2015.
 */
@Entity
@Table(name = "SP_PTL_COMP_JOB_RECR_OFF_PERS", schema = "SP_PTL", catalog = "")
public class SpPtlCompJobRecrOffPers {
    private Long id;
    private BigDecimal jobRecrOffId;
    private Integer incNum;
    private String empFirstname;
    private String empLastname;
    private String empCardNumber;
    private String empSpeciality;
    private Long empCategoryId;
    private Long empEduLevelId;
    private String empAfm;
    private String empAmka;
    private String empSex;
    private Long companyId;
    private String empAge;
    private Integer empPlacementNum;
    private String compAfm;
    private String compTitle;
    private String compAddr;
    private Long empCardType;

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
    @Column(name = "JOB_RECR_OFF_ID")
    public BigDecimal getJobRecrOffId() {
        return jobRecrOffId;
    }

    public void setJobRecrOffId(BigDecimal jobRecrOffId) {
        this.jobRecrOffId = jobRecrOffId;
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
    @Column(name = "EMP_FIRSTNAME")
    public String getEmpFirstname() {
        return empFirstname;
    }

    public void setEmpFirstname(String empFirstname) {
        this.empFirstname = empFirstname;
    }

    @Basic
    @Column(name = "EMP_LASTNAME")
    public String getEmpLastname() {
        return empLastname;
    }

    public void setEmpLastname(String empLastname) {
        this.empLastname = empLastname;
    }

    @Basic
    @Column(name = "EMP_CARD_NUMBER")
    public String getEmpCardNumber() {
        return empCardNumber;
    }

    public void setEmpCardNumber(String empCardNumber) {
        this.empCardNumber = empCardNumber;
    }

    @Basic
    @Column(name = "EMP_SPECIALITY")
    public String getEmpSpeciality() {
        return empSpeciality;
    }

    public void setEmpSpeciality(String empSpeciality) {
        this.empSpeciality = empSpeciality;
    }

    @Basic
    @Column(name = "EMP_CATEGORY_ID")
    public Long getEmpCategoryId() {
        return empCategoryId;
    }

    public void setEmpCategoryId(Long empCategoryId) {
        this.empCategoryId = empCategoryId;
    }

    @Basic
    @Column(name = "EMP_EDU_LEVEL_ID")
    public Long getEmpEduLevelId() {
        return empEduLevelId;
    }

    public void setEmpEduLevelId(Long empEduLevelId) {
        this.empEduLevelId = empEduLevelId;
    }

    @Basic
    @Column(name = "EMP_AFM")
    public String getEmpAfm() {
        return empAfm;
    }

    public void setEmpAfm(String empAfm) {
        this.empAfm = empAfm;
    }

    @Basic
    @Column(name = "EMP_AMKA")
    public String getEmpAmka() {
        return empAmka;
    }

    public void setEmpAmka(String empAmka) {
        this.empAmka = empAmka;
    }

    @Basic
    @Column(name = "EMP_SEX")
    public String getEmpSex() {
        return empSex;
    }

    public void setEmpSex(String empSex) {
        this.empSex = empSex;
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
    @Column(name = "EMP_AGE")
    public String getEmpAge() {
        return empAge;
    }

    public void setEmpAge(String empAge) {
        this.empAge = empAge;
    }

    @Basic
    @Column(name = "EMP_PLACEMENT_NUM")
    public Integer getEmpPlacementNum() {
        return empPlacementNum;
    }

    public void setEmpPlacementNum(Integer empPlacementNum) {
        this.empPlacementNum = empPlacementNum;
    }

    @Basic
    @Column(name = "COMP_AFM")
    public String getCompAfm() {
        return compAfm;
    }

    public void setCompAfm(String compAfm) {
        this.compAfm = compAfm;
    }

    @Basic
    @Column(name = "COMP_TITLE")
    public String getCompTitle() {
        return compTitle;
    }

    public void setCompTitle(String compTitle) {
        this.compTitle = compTitle;
    }

    @Basic
    @Column(name = "COMP_ADDR")
    public String getCompAddr() {
        return compAddr;
    }

    public void setCompAddr(String compAddr) {
        this.compAddr = compAddr;
    }

    @Basic
    @Column(name = "EMP_CARD_TYPE")
    public Long getEmpCardType() {
        return empCardType;
    }

    public void setEmpCardType(Long empCardType) {
        this.empCardType = empCardType;
    }

    /////////////////////////////////////////////////
    // /*
    private SpPtlCompJobRecrOff compJobRecrOff;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "JOB_RECR_OFF_ID", nullable = false, insertable = true, updatable = false) // Name is the DB column name of the attribute
    public SpPtlCompJobRecrOff getCompJobRecrOff() {
        return this.compJobRecrOff;
    }

    public void setCompJobRecrOff(SpPtlCompJobRecrOff compJobRecrOff) {
        this.compJobRecrOff = compJobRecrOff;
    }
    // */
    /////////////////////////////////////////////////


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpPtlCompJobRecrOffPers that = (SpPtlCompJobRecrOffPers) o;

        if (compAddr != null ? !compAddr.equals(that.compAddr) : that.compAddr != null) return false;
        if (compAfm != null ? !compAfm.equals(that.compAfm) : that.compAfm != null) return false;
        if (compTitle != null ? !compTitle.equals(that.compTitle) : that.compTitle != null) return false;
        if (companyId != null ? !companyId.equals(that.companyId) : that.companyId != null) return false;
        if (empAfm != null ? !empAfm.equals(that.empAfm) : that.empAfm != null) return false;
        if (empAge != null ? !empAge.equals(that.empAge) : that.empAge != null) return false;
        if (empAmka != null ? !empAmka.equals(that.empAmka) : that.empAmka != null) return false;
        if (empCardNumber != null ? !empCardNumber.equals(that.empCardNumber) : that.empCardNumber != null)
            return false;
        if (empCardType != null ? !empCardType.equals(that.empCardType) : that.empCardType != null) return false;
        if (empCategoryId != null ? !empCategoryId.equals(that.empCategoryId) : that.empCategoryId != null)
            return false;
        if (empEduLevelId != null ? !empEduLevelId.equals(that.empEduLevelId) : that.empEduLevelId != null)
            return false;
        if (empFirstname != null ? !empFirstname.equals(that.empFirstname) : that.empFirstname != null) return false;
        if (empLastname != null ? !empLastname.equals(that.empLastname) : that.empLastname != null) return false;
        if (empPlacementNum != null ? !empPlacementNum.equals(that.empPlacementNum) : that.empPlacementNum != null)
            return false;
        if (empSex != null ? !empSex.equals(that.empSex) : that.empSex != null) return false;
        if (empSpeciality != null ? !empSpeciality.equals(that.empSpeciality) : that.empSpeciality != null)
            return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (incNum != null ? !incNum.equals(that.incNum) : that.incNum != null) return false;
        if (jobRecrOffId != null ? !jobRecrOffId.equals(that.jobRecrOffId) : that.jobRecrOffId != null) return false;

        return true;
    }

    /*@Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (jobRecrOffId != null ? jobRecrOffId.hashCode() : 0);
        result = 31 * result + (incNum != null ? incNum.hashCode() : 0);
        result = 31 * result + (empFirstname != null ? empFirstname.hashCode() : 0);
        result = 31 * result + (empLastname != null ? empLastname.hashCode() : 0);
        result = 31 * result + (empCardNumber != null ? empCardNumber.hashCode() : 0);
        result = 31 * result + (empSpeciality != null ? empSpeciality.hashCode() : 0);
        result = 31 * result + (empCategoryId != null ? empCategoryId.hashCode() : 0);
        result = 31 * result + (empEduLevelId != null ? empEduLevelId.hashCode() : 0);
        result = 31 * result + (empAfm != null ? empAfm.hashCode() : 0);
        result = 31 * result + (empAmka != null ? empAmka.hashCode() : 0);
        result = 31 * result + (empSex != null ? empSex.hashCode() : 0);
        result = 31 * result + (companyId != null ? companyId.hashCode() : 0);
        result = 31 * result + (empAge != null ? empAge.hashCode() : 0);
        result = 31 * result + (empPlacementNum != null ? empPlacementNum.hashCode() : 0);
        result = 31 * result + (compAfm != null ? compAfm.hashCode() : 0);
        result = 31 * result + (compTitle != null ? compTitle.hashCode() : 0);
        result = 31 * result + (compAddr != null ? compAddr.hashCode() : 0);
        result = 31 * result + (empCardType != null ? empCardType.hashCode() : 0);
        return result;
    }*/
}
