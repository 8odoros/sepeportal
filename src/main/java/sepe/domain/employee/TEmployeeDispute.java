package sepe.domain.employee;

import javax.annotation.Nullable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.*;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Nikolas on 2/8/2015.
 */
@Entity
@javax.persistence.Table(name = "SP_PTL_EMPLOYEE_DISPUTE", schema = "SP_PTL", catalog = "")
public class TEmployeeDispute {
    private Long id;

    @Id
    @GeneratedValue
    @javax.persistence.Column(name = "ID", nullable = false, insertable = true, updatable = true, precision = 0)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private Long userId;

    @Basic
    @javax.persistence.Column(name = "USER_ID", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    private Integer subStatus;

    @Basic
    @javax.persistence.Column(name = "SUB_STATUS", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getSubStatus() {
        return subStatus;
    }

    public void setSubStatus(Integer subStatus) {
        this.subStatus = subStatus;
    }

    private Integer reqStatus;

    @Basic
    @javax.persistence.Column(name = "REQ_STATUS", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getReqStatus() {
        return reqStatus;
    }

    public void setReqStatus(Integer reqStatus) {
        this.reqStatus = reqStatus;
    }

    private String protNo;

    @Basic
    @javax.persistence.Column(name = "PROT_NO", nullable = true, insertable = true, updatable = true, length = 50)
    public String getProtNo() {
        return protNo;
    }

    public void setProtNo(String protNo) {
        this.protNo = protNo;
    }

    private String empFirstname;

    @Basic
    @javax.persistence.Column(name = "EMP_FIRSTNAME", nullable = true, insertable = true, updatable = true, length = 100)
    public String getEmpFirstname() {
        return empFirstname;
    }

    public void setEmpFirstname(String empFirstname) {
        this.empFirstname = empFirstname;
    }

    private String empLastname;

    @Basic
    @javax.persistence.Column(name = "EMP_LASTNAME", nullable = true, insertable = true, updatable = true, length = 100)
    public String getEmpLastname() {
        return empLastname;
    }

    public void setEmpLastname(String empLastname) {
        this.empLastname = empLastname;
    }

    private Long empSpecialityId;

    @Basic
    @javax.persistence.Column(name = "EMP_SPECIALITY_ID", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getEmpSpecialityId() {
        return empSpecialityId;
    }

    public void setEmpSpecialityId(Long empSpecialityId) {
        this.empSpecialityId = empSpecialityId;
    }

    private Integer protYear;

    @Basic
    @javax.persistence.Column(name = "PROT_YEAR", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getProtYear() {
        return protYear;
    }

    public void setProtYear(Integer protYear) {
        this.protYear = protYear;
    }

    private Integer empMaritalStatus;

    @Basic
    @javax.persistence.Column(name = "EMP_MARITAL_STATUS", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getEmpMaritalStatus() {
        return empMaritalStatus;
    }

    public void setEmpMaritalStatus(Integer empMaritalStatus) {
        this.empMaritalStatus = empMaritalStatus;
    }

    private Integer empChildrenNum;

    @Basic
    @javax.persistence.Column(name = "EMP_CHILDREN_NUM", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getEmpChildrenNum() {
        return empChildrenNum;
    }

    public void setEmpChildrenNum(Integer empChildrenNum) {
        this.empChildrenNum = empChildrenNum;
    }

    private String empNetSalary;

    @Basic
    @javax.persistence.Column(name = "EMP_NET_SALARY", nullable = true, insertable = true, updatable = true, length = 50)
    public String getEmpNetSalary() {
        return empNetSalary;
    }

    public void setEmpNetSalary(String empNetSalary) {
        this.empNetSalary = empNetSalary;
    }

    private String empGrossSalary;

    @Basic
    @javax.persistence.Column(name = "EMP_GROSS_SALARY", nullable = true, insertable = true, updatable = true, length = 50)
    public String getEmpGrossSalary() {
        return empGrossSalary;
    }

    public void setEmpGrossSalary(String empGrossSalary) {
        this.empGrossSalary = empGrossSalary;
    }

    private Timestamp empFromDate;

    @Basic
    @javax.persistence.Column(name = "EMP_FROM_DATE", nullable = true, insertable = true, updatable = true)
    public Timestamp getEmpFromDate() {
        return empFromDate;
    }

    public void setEmpFromDate(Timestamp empFromDate) {
        this.empFromDate = empFromDate;
    }

    private Timestamp empUntilDate;

    @Basic
    @javax.persistence.Column(name = "EMP_UNTIL_DATE", nullable = true, insertable = true, updatable = true)
    public Timestamp getEmpUntilDate() {
        return empUntilDate;
    }

    public void setEmpUntilDate(Timestamp empUntilDate) {
        this.empUntilDate = empUntilDate;
    }

    private String empWorkingHours;

    @Basic
    @javax.persistence.Column(name = "EMP_WORKING_HOURS", nullable = true, insertable = true, updatable = true, length = 20)
    public String getEmpWorkingHours() {
        return empWorkingHours;
    }

    public void setEmpWorkingHours(String empWorkingHours) {
        this.empWorkingHours = empWorkingHours;
    }

    private String empAddr;

    @Basic
    @javax.persistence.Column(name = "EMP_ADDR", nullable = true, insertable = true, updatable = true, length = 100)
    public String getEmpAddr() {
        return empAddr;
    }

    public void setEmpAddr(String empAddr) {
        this.empAddr = empAddr;
    }

    private String empAddrTk;

    @Basic
    @javax.persistence.Column(name = "EMP_ADDR_TK", nullable = true, insertable = true, updatable = true, length = 20)
    public String getEmpAddrTk() {
        return empAddrTk;
    }

    public void setEmpAddrTk(String empAddrTk) {
        this.empAddrTk = empAddrTk;
    }

    private String empAddrP;

    @Basic
    @javax.persistence.Column(name = "EMP_ADDR_P", nullable = true, insertable = true, updatable = true, length = 100)
    public String getEmpAddrP() {
        return empAddrP;
    }

    public void setEmpAddrP(String empAddrP) {
        this.empAddrP = empAddrP;
    }

    private String empAddrPe;

    @Basic
    @javax.persistence.Column(name = "EMP_ADDR_PE", nullable = true, insertable = true, updatable = true, length = 100)
    public String getEmpAddrPe() {
        return empAddrPe;
    }

    public void setEmpAddrPe(String empAddrPe) {
        this.empAddrPe = empAddrPe;
    }

    private String empAddrD;

    @Basic
    @javax.persistence.Column(name = "EMP_ADDR_D", nullable = true, insertable = true, updatable = true, length = 100)
    public String getEmpAddrD() {
        return empAddrD;
    }

    public void setEmpAddrD(String empAddrD) {
        this.empAddrD = empAddrD;
    }

    private String empAddrK;

    @Basic
    @javax.persistence.Column(name = "EMP_ADDR_K", nullable = true, insertable = true, updatable = true, length = 100)
    public String getEmpAddrK() {
        return empAddrK;
    }

    public void setEmpAddrK(String empAddrK) {
        this.empAddrK = empAddrK;
    }

    private String empAfm;

    @Basic
    @javax.persistence.Column(name = "EMP_AFM", nullable = true, insertable = true, updatable = true, length = 50)
    public String getEmpAfm() {
        return empAfm;
    }

    public void setEmpAfm(String empAfm) {
        this.empAfm = empAfm;
    }

    private String empAmka;

    @Basic
    @javax.persistence.Column(name = "EMP_AMKA", nullable = true, insertable = true, updatable = true, length = 50)
    public String getEmpAmka() {
        return empAmka;
    }

    public void setEmpAmka(String empAmka) {
        this.empAmka = empAmka;
    }

    private String empCardNumber;

    @Basic
    @javax.persistence.Column(name = "EMP_CARD_NUMBER", nullable = true, insertable = true, updatable = true, length = 50)
    public String getEmpCardNumber() {
        return empCardNumber;
    }

    public void setEmpCardNumber(String empCardNumber) {
        this.empCardNumber = empCardNumber;
    }

    private String empPhone;

    @Basic
    @javax.persistence.Column(name = "EMP_PHONE", nullable = true, insertable = true, updatable = true, length = 50)
    public String getEmpPhone() {
        return empPhone;
    }

    public void setEmpPhone(String empPhone) {
        this.empPhone = empPhone;
    }

    private String empMobile;

    @Basic
    @javax.persistence.Column(name = "EMP_MOBILE", nullable = true, insertable = true, updatable = true, length = 50)
    public String getEmpMobile() {
        return empMobile;
    }

    public void setEmpMobile(String empMobile) {
        this.empMobile = empMobile;
    }

    private String compName;

    @Basic
    @javax.persistence.Column(name = "COMP_NAME", nullable = true, insertable = true, updatable = true, length = 300)
    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    private String compScope;

    @Basic
    @javax.persistence.Column(name = "COMP_SCOPE", nullable = true, insertable = true, updatable = true, length = 300)
    public String getCompScope() {
        return compScope;
    }

    public void setCompScope(String compScope) {
        this.compScope = compScope;
    }

    private String compRepresentative;

    @Basic
    @javax.persistence.Column(name = "COMP_REPRESENTATIVE", nullable = true, insertable = true, updatable = true, length = 100)
    public String getCompRepresentative() {
        return compRepresentative;
    }

    public void setCompRepresentative(String compRepresentative) {
        this.compRepresentative = compRepresentative;
    }

    private String compAddr;

    @Basic
    @javax.persistence.Column(name = "COMP_ADDR", nullable = true, insertable = true, updatable = true, length = 100)
    public String getCompAddr() {
        return compAddr;
    }

    public void setCompAddr(String compAddr) {
        this.compAddr = compAddr;
    }

    private String compAddrTk;

    @Basic
    @javax.persistence.Column(name = "COMP_ADDR_TK", nullable = true, insertable = true, updatable = true, length = 50)
    public String getCompAddrTk() {
        return compAddrTk;
    }

    public void setCompAddrTk(String compAddrTk) {
        this.compAddrTk = compAddrTk;
    }

    private String compAddrP;

    @Basic
    @javax.persistence.Column(name = "COMP_ADDR_P", nullable = true, insertable = true, updatable = true, length = 100)
    public String getCompAddrP() {
        return compAddrP;
    }

    public void setCompAddrP(String compAddrP) {
        this.compAddrP = compAddrP;
    }

    private String compAddrPe;

    @Basic
    @javax.persistence.Column(name = "COMP_ADDR_PE", nullable = true, insertable = true, updatable = true, length = 100)
    public String getCompAddrPe() {
        return compAddrPe;
    }

    public void setCompAddrPe(String compAddrPe) {
        this.compAddrPe = compAddrPe;
    }

    private String compAddrD;

    @Basic
    @javax.persistence.Column(name = "COMP_ADDR_D", nullable = true, insertable = true, updatable = true, length = 100)
    public String getCompAddrD() {
        return compAddrD;
    }

    public void setCompAddrD(String compAddrD) {
        this.compAddrD = compAddrD;
    }

    private String compAddrK;

    @Basic
    @javax.persistence.Column(name = "COMP_ADDR_K", nullable = true, insertable = true, updatable = true, length = 100)
    public String getCompAddrK() {
        return compAddrK;
    }

    public void setCompAddrK(String compAddrK) {
        this.compAddrK = compAddrK;
    }

    private String compBrAddr;

    @Basic
    @javax.persistence.Column(name = "COMP_BR_ADDR", nullable = true, insertable = true, updatable = true, length = 100)
    public String getCompBrAddr() {
        return compBrAddr;
    }

    public void setCompBrAddr(String compBrAddr) {
        this.compBrAddr = compBrAddr;
    }

    private String compBrAddrTk;

    @Basic
    @javax.persistence.Column(name = "COMP_BR_ADDR_TK", nullable = true, insertable = true, updatable = true, length = 20)
    public String getCompBrAddrTk() {
        return compBrAddrTk;
    }

    public void setCompBrAddrTk(String compBrAddrTk) {
        this.compBrAddrTk = compBrAddrTk;
    }

    private String compBrAddrP;

    @Basic
    @javax.persistence.Column(name = "COMP_BR_ADDR_P", nullable = true, insertable = true, updatable = true, length = 100)
    public String getCompBrAddrP() {
        return compBrAddrP;
    }

    public void setCompBrAddrP(String compBrAddrP) {
        this.compBrAddrP = compBrAddrP;
    }

    private String compBrAddrPe;

    @Basic
    @javax.persistence.Column(name = "COMP_BR_ADDR_PE", nullable = true, insertable = true, updatable = true, length = 100)
    public String getCompBrAddrPe() {
        return compBrAddrPe;
    }

    public void setCompBrAddrPe(String compBrAddrPe) {
        this.compBrAddrPe = compBrAddrPe;
    }

    private String compBrAddrD;

    @Basic
    @javax.persistence.Column(name = "COMP_BR_ADDR_D", nullable = true, insertable = true, updatable = true, length = 100)
    public String getCompBrAddrD() {
        return compBrAddrD;
    }

    public void setCompBrAddrD(String compBrAddrD) {
        this.compBrAddrD = compBrAddrD;
    }

    private String compBrAddrK;

    @Basic
    @javax.persistence.Column(name = "COMP_BR_ADDR_K", nullable = true, insertable = true, updatable = true, length = 100)
    public String getCompBrAddrK() {
        return compBrAddrK;
    }

    public void setCompBrAddrK(String compBrAddrK) {
        this.compBrAddrK = compBrAddrK;
    }

    private String compAfm;

    @Basic
    @javax.persistence.Column(name = "COMP_AFM", nullable = true, insertable = true, updatable = true, length = 100)
    public String getCompAfm() {
        return compAfm;
    }

    public void setCompAfm(String compAfm) {
        this.compAfm = compAfm;
    }

    private String compDoy;

    @Basic
    @javax.persistence.Column(name = "COMP_DOY", nullable = true, insertable = true, updatable = true, length = 100)
    public String getCompDoy() {
        return compDoy;
    }

    public void setCompDoy(String compDoy) {
        this.compDoy = compDoy;
    }

    private String compAmeIka;

    @Basic
    @javax.persistence.Column(name = "COMP_AME_IKA", nullable = true, insertable = true, updatable = true, length = 100)
    public String getCompAmeIka() {
        return compAmeIka;
    }

    public void setCompAmeIka(String compAmeIka) {
        this.compAmeIka = compAmeIka;
    }

    private String compPhone;

    @Basic
    @javax.persistence.Column(name = "COMP_PHONE", nullable = true, insertable = true, updatable = true, length = 50)
    public String getCompPhone() {
        return compPhone;
    }

    public void setCompPhone(String compPhone) {
        this.compPhone = compPhone;
    }

    private String notes;

    @Basic
    @javax.persistence.Column(name = "NOTES", nullable = true, insertable = true, updatable = true, length = 3000)
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    private Integer groupDiscussion;

    @Basic
    @javax.persistence.Column(name = "GROUP_DISCUSSION", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getGroupDiscussion() {
        return groupDiscussion;
    }

    public void setGroupDiscussion(Integer groupDiscussion) {
        this.groupDiscussion = groupDiscussion;
    }

    private Timestamp protDate;

    @Basic
    @javax.persistence.Column(name = "PROT_DATE", nullable = true, insertable = true, updatable = true)
    public Timestamp getProtDate() {
        return protDate;
    }

    public void setProtDate(Timestamp protDate) {
        this.protDate = protDate;
    }

    private Integer applicantType;

    @Basic
    @javax.persistence.Column(name = "APPLICANT_TYPE", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getApplicantType() {
        return applicantType;
    }

    public void setApplicantType(Integer applicantType) {
        this.applicantType = applicantType;
    }

    private Integer applicationType;

    @Basic
    @javax.persistence.Column(name = "APPLICATION_TYPE", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getApplicationType() {
        return applicationType;
    }

    public void setApplicationType(Integer applicationType) {
        this.applicationType = applicationType;
    }

    private String empSex;

    @Basic
    @javax.persistence.Column(name = "EMP_SEX", nullable = true, insertable = true, updatable = true, length = 1)
    public String getEmpSex() {
        return empSex;
    }

    public void setEmpSex(String empSex) {
        this.empSex = empSex;
    }

    private Integer empAge;

    @Basic
    @javax.persistence.Column(name = "EMP_AGE", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getEmpAge() {
        return empAge;
    }

    public void setEmpAge(Integer empAge) {
        this.empAge = empAge;
    }

    private Integer empCitizenship;

    @Basic
    @javax.persistence.Column(name = "EMP_CITIZENSHIP", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getEmpCitizenship() {
        return empCitizenship;
    }

    public void setEmpCitizenship(Integer empCitizenship) {
        this.empCitizenship = empCitizenship;
    }

    private Integer empCardType;

    @Basic
    @javax.persistence.Column(name = "EMP_CARD_TYPE", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getEmpCardType() {
        return empCardType;
    }

    public void setEmpCardType(Integer empCardType) {
        this.empCardType = empCardType;
    }

    private Long docId;

    @Basic
    @javax.persistence.Column(name = "DOC_ID", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getDocId() {
        return docId;
    }

    public void setDocId(Long docId) {
        this.docId = docId;
    }

    private String submitTime;

    @Basic
    @javax.persistence.Column(name = "SUBMIT_TIME", nullable = true, insertable = true, updatable = true, length = 50)
    public String getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(String submitTime) {
        this.submitTime = submitTime;
    }

    private Long department;

    @Basic
    @javax.persistence.Column(name = "DEPARTMENT", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getDepartment() {
        return department;
    }

    public void setDepartment(Long department) {
        this.department = department;
    }

    private Long caseId;

    @Basic
    @javax.persistence.Column(name = "CASE_ID", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }

    private String rtstakLevel1;

    @Basic
    @javax.persistence.Column(name = "RTSTAK_LEVEL_1", nullable = true, insertable = true, updatable = true, length = 20)
    public String getRtstakLevel1() {
        return rtstakLevel1;
    }

    public void setRtstakLevel1(String rtstakLevel1) {
        this.rtstakLevel1 = rtstakLevel1;
    }

    private String rtstakLevel2;

    @Basic
    @javax.persistence.Column(name = "RTSTAK_LEVEL_2", nullable = true, insertable = true, updatable = true, length = 20)
    public String getRtstakLevel2() {
        return rtstakLevel2;
    }

    public void setRtstakLevel2(String rtstakLevel2) {
        this.rtstakLevel2 = rtstakLevel2;
    }

    private String rtstakLevel3;

    @Basic
    @javax.persistence.Column(name = "RTSTAK_LEVEL_3", nullable = true, insertable = true, updatable = true, length = 20)
    public String getRtstakLevel3() {
        return rtstakLevel3;
    }

    public void setRtstakLevel3(String rtstakLevel3) {
        this.rtstakLevel3 = rtstakLevel3;
    }

    private String rtstakLevel4;

    @Basic
    @javax.persistence.Column(name = "RTSTAK_LEVEL_4", nullable = true, insertable = true, updatable = true, length = 20)
    public String getRtstakLevel4() {
        return rtstakLevel4;
    }

    public void setRtstakLevel4(String rtstakLevel4) {
        this.rtstakLevel4 = rtstakLevel4;
    }

    private String rtstakLevel5;

    @Basic
    @javax.persistence.Column(name = "RTSTAK_LEVEL_5", nullable = true, insertable = true, updatable = true, length = 20)
    public String getRtstakLevel5() {
        return rtstakLevel5;
    }

    public void setRtstakLevel5(String rtstakLevel5) {
        this.rtstakLevel5 = rtstakLevel5;
    }

    private Integer branch0Id;

    @Basic
    @javax.persistence.Column(name = "BRANCH0_ID", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getBranch0Id() {
        return branch0Id;
    }

    public void setBranch0Id(Integer branch0Id) {
        this.branch0Id = branch0Id;
    }

    private Integer branch1Id;

    @Basic
    @javax.persistence.Column(name = "BRANCH1_ID", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getBranch1Id() {
        return branch1Id;
    }

    public void setBranch1Id(Integer branch1Id) {
        this.branch1Id = branch1Id;
    }

    private Long docIdAttached;

    @Basic
    @javax.persistence.Column(name = "DOC_ID_ATTACHED", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getDocIdAttached() {
        return docIdAttached;
    }

    public void setDocIdAttached(Long docIdAttached) {
        this.docIdAttached = docIdAttached;
    }

    /////////////////////////////////////////////////
    // /*
    private Set<TEmployeeDisputeReason> employeeDisputeReason = new HashSet<TEmployeeDisputeReason>(0);

    @Nullable
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "employeeDispute", cascade = CascadeType.ALL, orphanRemoval = true) /* SHOULD BE EQUAL TO ATTRIBUTE NAME OF OTHER CLASS!!!! */
    public Set<TEmployeeDisputeReason> getEmployeeDisputeReason() {
        return this.employeeDisputeReason;
    }

    public void setEmployeeDisputeReason(Set<TEmployeeDisputeReason> tEmployeeDisputeReason) {
        this.employeeDisputeReason = tEmployeeDisputeReason;
    }
    // */
    /////////////////////////////////////////////////

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TEmployeeDispute that = (TEmployeeDispute) o;

        if (id != that.id) return false;
        if (applicantType != null ? !applicantType.equals(that.applicantType) : that.applicantType != null)
            return false;
        if (applicationType != null ? !applicationType.equals(that.applicationType) : that.applicationType != null)
            return false;
        if (branch0Id != null ? !branch0Id.equals(that.branch0Id) : that.branch0Id != null) return false;
        if (branch1Id != null ? !branch1Id.equals(that.branch1Id) : that.branch1Id != null) return false;
        if (caseId != null ? !caseId.equals(that.caseId) : that.caseId != null) return false;
        if (compAddr != null ? !compAddr.equals(that.compAddr) : that.compAddr != null) return false;
        if (compAddrD != null ? !compAddrD.equals(that.compAddrD) : that.compAddrD != null) return false;
        if (compAddrK != null ? !compAddrK.equals(that.compAddrK) : that.compAddrK != null) return false;
        if (compAddrP != null ? !compAddrP.equals(that.compAddrP) : that.compAddrP != null) return false;
        if (compAddrPe != null ? !compAddrPe.equals(that.compAddrPe) : that.compAddrPe != null) return false;
        if (compAddrTk != null ? !compAddrTk.equals(that.compAddrTk) : that.compAddrTk != null) return false;
        if (compAfm != null ? !compAfm.equals(that.compAfm) : that.compAfm != null) return false;
        if (compAmeIka != null ? !compAmeIka.equals(that.compAmeIka) : that.compAmeIka != null) return false;
        if (compBrAddr != null ? !compBrAddr.equals(that.compBrAddr) : that.compBrAddr != null) return false;
        if (compBrAddrD != null ? !compBrAddrD.equals(that.compBrAddrD) : that.compBrAddrD != null) return false;
        if (compBrAddrK != null ? !compBrAddrK.equals(that.compBrAddrK) : that.compBrAddrK != null) return false;
        if (compBrAddrP != null ? !compBrAddrP.equals(that.compBrAddrP) : that.compBrAddrP != null) return false;
        if (compBrAddrPe != null ? !compBrAddrPe.equals(that.compBrAddrPe) : that.compBrAddrPe != null) return false;
        if (compBrAddrTk != null ? !compBrAddrTk.equals(that.compBrAddrTk) : that.compBrAddrTk != null) return false;
        if (compDoy != null ? !compDoy.equals(that.compDoy) : that.compDoy != null) return false;
        if (compName != null ? !compName.equals(that.compName) : that.compName != null) return false;
        if (compPhone != null ? !compPhone.equals(that.compPhone) : that.compPhone != null) return false;
        if (compRepresentative != null ? !compRepresentative.equals(that.compRepresentative) : that.compRepresentative != null)
            return false;
        if (compScope != null ? !compScope.equals(that.compScope) : that.compScope != null) return false;
        if (department != null ? !department.equals(that.department) : that.department != null) return false;
        if (docId != null ? !docId.equals(that.docId) : that.docId != null) return false;
        if (docIdAttached != null ? !docIdAttached.equals(that.docIdAttached) : that.docIdAttached != null)
            return false;
        if (empAddr != null ? !empAddr.equals(that.empAddr) : that.empAddr != null) return false;
        if (empAddrD != null ? !empAddrD.equals(that.empAddrD) : that.empAddrD != null) return false;
        if (empAddrK != null ? !empAddrK.equals(that.empAddrK) : that.empAddrK != null) return false;
        if (empAddrP != null ? !empAddrP.equals(that.empAddrP) : that.empAddrP != null) return false;
        if (empAddrPe != null ? !empAddrPe.equals(that.empAddrPe) : that.empAddrPe != null) return false;
        if (empAddrTk != null ? !empAddrTk.equals(that.empAddrTk) : that.empAddrTk != null) return false;
        if (empAfm != null ? !empAfm.equals(that.empAfm) : that.empAfm != null) return false;
        if (empAge != null ? !empAge.equals(that.empAge) : that.empAge != null) return false;
        if (empAmka != null ? !empAmka.equals(that.empAmka) : that.empAmka != null) return false;
        if (empCardNumber != null ? !empCardNumber.equals(that.empCardNumber) : that.empCardNumber != null)
            return false;
        if (empCardType != null ? !empCardType.equals(that.empCardType) : that.empCardType != null) return false;
        if (empChildrenNum != null ? !empChildrenNum.equals(that.empChildrenNum) : that.empChildrenNum != null)
            return false;
        if (empCitizenship != null ? !empCitizenship.equals(that.empCitizenship) : that.empCitizenship != null)
            return false;
        if (empFirstname != null ? !empFirstname.equals(that.empFirstname) : that.empFirstname != null) return false;
        if (empFromDate != null ? !empFromDate.equals(that.empFromDate) : that.empFromDate != null) return false;
        if (empLastname != null ? !empLastname.equals(that.empLastname) : that.empLastname != null) return false;
        if (empMaritalStatus != null ? !empMaritalStatus.equals(that.empMaritalStatus) : that.empMaritalStatus != null)
            return false;
        if (empMobile != null ? !empMobile.equals(that.empMobile) : that.empMobile != null) return false;
        if (empNetSalary != null ? !empNetSalary.equals(that.empNetSalary) : that.empNetSalary != null) return false;
        if (empGrossSalary != null ? !empGrossSalary.equals(that.empGrossSalary) : that.empGrossSalary != null) return false;
        if (empPhone != null ? !empPhone.equals(that.empPhone) : that.empPhone != null) return false;
        if (empSex != null ? !empSex.equals(that.empSex) : that.empSex != null) return false;
        if (empSpecialityId != null ? !empSpecialityId.equals(that.empSpecialityId) : that.empSpecialityId != null)
            return false;
        if (empUntilDate != null ? !empUntilDate.equals(that.empUntilDate) : that.empUntilDate != null) return false;
        if (empWorkingHours != null ? !empWorkingHours.equals(that.empWorkingHours) : that.empWorkingHours != null)
            return false;
        if (groupDiscussion != null ? !groupDiscussion.equals(that.groupDiscussion) : that.groupDiscussion != null)
            return false;
        if (notes != null ? !notes.equals(that.notes) : that.notes != null) return false;
        if (protDate != null ? !protDate.equals(that.protDate) : that.protDate != null) return false;
        if (protNo != null ? !protNo.equals(that.protNo) : that.protNo != null) return false;
        if (protYear != null ? !protYear.equals(that.protYear) : that.protYear != null) return false;
        if (reqStatus != null ? !reqStatus.equals(that.reqStatus) : that.reqStatus != null) return false;
        if (rtstakLevel1 != null ? !rtstakLevel1.equals(that.rtstakLevel1) : that.rtstakLevel1 != null) return false;
        if (rtstakLevel2 != null ? !rtstakLevel2.equals(that.rtstakLevel2) : that.rtstakLevel2 != null) return false;
        if (rtstakLevel3 != null ? !rtstakLevel3.equals(that.rtstakLevel3) : that.rtstakLevel3 != null) return false;
        if (rtstakLevel4 != null ? !rtstakLevel4.equals(that.rtstakLevel4) : that.rtstakLevel4 != null) return false;
        if (rtstakLevel5 != null ? !rtstakLevel5.equals(that.rtstakLevel5) : that.rtstakLevel5 != null) return false;
        if (subStatus != null ? !subStatus.equals(that.subStatus) : that.subStatus != null) return false;
        if (submitTime != null ? !submitTime.equals(that.submitTime) : that.submitTime != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (subStatus != null ? subStatus.hashCode() : 0);
        result = 31 * result + (reqStatus != null ? reqStatus.hashCode() : 0);
        result = 31 * result + (protNo != null ? protNo.hashCode() : 0);
        result = 31 * result + (empFirstname != null ? empFirstname.hashCode() : 0);
        result = 31 * result + (empLastname != null ? empLastname.hashCode() : 0);
        result = 31 * result + (empSpecialityId != null ? empSpecialityId.hashCode() : 0);
        result = 31 * result + (protYear != null ? protYear.hashCode() : 0);
        result = 31 * result + (empMaritalStatus != null ? empMaritalStatus.hashCode() : 0);
        result = 31 * result + (empChildrenNum != null ? empChildrenNum.hashCode() : 0);
        result = 31 * result + (empNetSalary != null ? empNetSalary.hashCode() : 0);
        result = 31 * result + (empGrossSalary != null ? empGrossSalary.hashCode() : 0);
        result = 31 * result + (empFromDate != null ? empFromDate.hashCode() : 0);
        result = 31 * result + (empUntilDate != null ? empUntilDate.hashCode() : 0);
        result = 31 * result + (empWorkingHours != null ? empWorkingHours.hashCode() : 0);
        result = 31 * result + (empAddr != null ? empAddr.hashCode() : 0);
        result = 31 * result + (empAddrTk != null ? empAddrTk.hashCode() : 0);
        result = 31 * result + (empAddrP != null ? empAddrP.hashCode() : 0);
        result = 31 * result + (empAddrPe != null ? empAddrPe.hashCode() : 0);
        result = 31 * result + (empAddrD != null ? empAddrD.hashCode() : 0);
        result = 31 * result + (empAddrK != null ? empAddrK.hashCode() : 0);
        result = 31 * result + (empAfm != null ? empAfm.hashCode() : 0);
        result = 31 * result + (empAmka != null ? empAmka.hashCode() : 0);
        result = 31 * result + (empCardNumber != null ? empCardNumber.hashCode() : 0);
        result = 31 * result + (empPhone != null ? empPhone.hashCode() : 0);
        result = 31 * result + (empMobile != null ? empMobile.hashCode() : 0);
        result = 31 * result + (compName != null ? compName.hashCode() : 0);
        result = 31 * result + (compScope != null ? compScope.hashCode() : 0);
        result = 31 * result + (compRepresentative != null ? compRepresentative.hashCode() : 0);
        result = 31 * result + (compAddr != null ? compAddr.hashCode() : 0);
        result = 31 * result + (compAddrTk != null ? compAddrTk.hashCode() : 0);
        result = 31 * result + (compAddrP != null ? compAddrP.hashCode() : 0);
        result = 31 * result + (compAddrPe != null ? compAddrPe.hashCode() : 0);
        result = 31 * result + (compAddrD != null ? compAddrD.hashCode() : 0);
        result = 31 * result + (compAddrK != null ? compAddrK.hashCode() : 0);
        result = 31 * result + (compBrAddr != null ? compBrAddr.hashCode() : 0);
        result = 31 * result + (compBrAddrTk != null ? compBrAddrTk.hashCode() : 0);
        result = 31 * result + (compBrAddrP != null ? compBrAddrP.hashCode() : 0);
        result = 31 * result + (compBrAddrPe != null ? compBrAddrPe.hashCode() : 0);
        result = 31 * result + (compBrAddrD != null ? compBrAddrD.hashCode() : 0);
        result = 31 * result + (compBrAddrK != null ? compBrAddrK.hashCode() : 0);
        result = 31 * result + (compAfm != null ? compAfm.hashCode() : 0);
        result = 31 * result + (compDoy != null ? compDoy.hashCode() : 0);
        result = 31 * result + (compAmeIka != null ? compAmeIka.hashCode() : 0);
        result = 31 * result + (compPhone != null ? compPhone.hashCode() : 0);
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        result = 31 * result + (groupDiscussion != null ? groupDiscussion.hashCode() : 0);
        result = 31 * result + (protDate != null ? protDate.hashCode() : 0);
        result = 31 * result + (applicantType != null ? applicantType.hashCode() : 0);
        result = 31 * result + (applicationType != null ? applicationType.hashCode() : 0);
        result = 31 * result + (empSex != null ? empSex.hashCode() : 0);
        result = 31 * result + (empAge != null ? empAge.hashCode() : 0);
        result = 31 * result + (empCitizenship != null ? empCitizenship.hashCode() : 0);
        result = 31 * result + (empCardType != null ? empCardType.hashCode() : 0);
        result = 31 * result + (docId != null ? docId.hashCode() : 0);
        result = 31 * result + (submitTime != null ? submitTime.hashCode() : 0);
        result = 31 * result + (department != null ? department.hashCode() : 0);
        result = 31 * result + (caseId != null ? caseId.hashCode() : 0);
        result = 31 * result + (rtstakLevel1 != null ? rtstakLevel1.hashCode() : 0);
        result = 31 * result + (rtstakLevel2 != null ? rtstakLevel2.hashCode() : 0);
        result = 31 * result + (rtstakLevel3 != null ? rtstakLevel3.hashCode() : 0);
        result = 31 * result + (rtstakLevel4 != null ? rtstakLevel4.hashCode() : 0);
        result = 31 * result + (rtstakLevel5 != null ? rtstakLevel5.hashCode() : 0);
        result = 31 * result + (branch0Id != null ? branch0Id.hashCode() : 0);
        result = 31 * result + (branch1Id != null ? branch1Id.hashCode() : 0);
        result = 31 * result + (docIdAttached != null ? docIdAttached.hashCode() : 0);
        return result;
    }
}