package sepe.domain.employee;

import sepe.dto.SpPtlCompDriverPmtEntryDTO;

import javax.annotation.Nullable;
import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Nikolas on 3/4/2015.
 */
@Entity
@javax.persistence.Table(name = "SP_PTL_EMPLOYEE_EXPERIENCE", schema = "SP_PTL", catalog = "")
public class SpPtlEmployeeExperience {
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

    private String compAfm;

    @Basic
    @javax.persistence.Column(name = "COMP_AFM", nullable = true, insertable = true, updatable = true, length = 100)
    public String getCompAfm() {
        return compAfm;
    }

    public void setCompAfm(String compAfm) {
        this.compAfm = compAfm;
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

    private Timestamp protDate;

    @Basic
    @javax.persistence.Column(name = "PROT_DATE", nullable = true, insertable = true, updatable = true)
    public Timestamp getProtDate() {
        return protDate;
    }

    public void setProtDate(Timestamp protDate) {
        this.protDate = protDate;
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

    private Integer intentedUse;

    @Basic
    @javax.persistence.Column(name = "INTENTED_USE", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getIntentedUse() {
        return intentedUse;
    }

    public void setIntentedUse(Integer intentedUse) {
        this.intentedUse = intentedUse;
    }

    private Long attachedDocIdEmplVer;

    @Basic
    @javax.persistence.Column(name = "ATTACHED_DOC_ID_EMPL_VER", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getAttachedDocIdEmplVer() {
        return attachedDocIdEmplVer;
    }

    public void setAttachedDocIdEmplVer(Long attachedDocIdEmplVer) {
        this.attachedDocIdEmplVer = attachedDocIdEmplVer;
    }

    private Long attachedDocIdIka;

    @Basic
    @javax.persistence.Column(name = "ATTACHED_DOC_ID_IKA", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getAttachedDocIdIka() {
        return attachedDocIdIka;
    }

    public void setAttachedDocIdIka(Long attachedDocIdIka) {
        this.attachedDocIdIka = attachedDocIdIka;
    }

    private Long attachedDocIdSepe;

    @Basic
    @javax.persistence.Column(name = "ATTACHED_DOC_ID_SEPE", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getAttachedDocIdSepe() {
        return attachedDocIdSepe;
    }

    public void setAttachedDocIdSepe(Long attachedDocIdSepe) {
        this.attachedDocIdSepe = attachedDocIdSepe;
    }

    private Long attachedDocIdJudgmnt;

    @Basic
    @javax.persistence.Column(name = "ATTACHED_DOC_ID_JUDGMNT", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getAttachedDocIdJudgmnt() {
        return attachedDocIdJudgmnt;
    }

    public void setAttachedDocIdJudgmnt(Long attachedDocIdJudgmnt) {
        this.attachedDocIdJudgmnt = attachedDocIdJudgmnt;
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

    @Nullable
    private String rgEbrKallikratis;

    @Transient
    @Nullable
    public String getRgEbrKallikratis() {
        return rgEbrKallikratis;
    }

    @Transient
    @Nullable
    public void setRgEbrKallikratis(@Nullable String rgEbrKallikratis) {
        this.rgEbrKallikratis = rgEbrKallikratis;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpPtlEmployeeExperience that = (SpPtlEmployeeExperience) o;

        if (id != that.id) return false;
        if (attachedDocIdEmplVer != null ? !attachedDocIdEmplVer.equals(that.attachedDocIdEmplVer) : that.attachedDocIdEmplVer != null)
            return false;
        if (attachedDocIdIka != null ? !attachedDocIdIka.equals(that.attachedDocIdIka) : that.attachedDocIdIka != null)
            return false;
        if (attachedDocIdJudgmnt != null ? !attachedDocIdJudgmnt.equals(that.attachedDocIdJudgmnt) : that.attachedDocIdJudgmnt != null)
            return false;
        if (attachedDocIdSepe != null ? !attachedDocIdSepe.equals(that.attachedDocIdSepe) : that.attachedDocIdSepe != null)
            return false;
        if (compAfm != null ? !compAfm.equals(that.compAfm) : that.compAfm != null) return false;
        if (compAmeIka != null ? !compAmeIka.equals(that.compAmeIka) : that.compAmeIka != null) return false;
        if (compName != null ? !compName.equals(that.compName) : that.compName != null) return false;
        if (compPhone != null ? !compPhone.equals(that.compPhone) : that.compPhone != null) return false;
        if (department != null ? !department.equals(that.department) : that.department != null) return false;
        if (docId != null ? !docId.equals(that.docId) : that.docId != null) return false;
        if (empAddr != null ? !empAddr.equals(that.empAddr) : that.empAddr != null) return false;
        if (empAddrD != null ? !empAddrD.equals(that.empAddrD) : that.empAddrD != null) return false;
        if (empAddrK != null ? !empAddrK.equals(that.empAddrK) : that.empAddrK != null) return false;
        if (empAddrP != null ? !empAddrP.equals(that.empAddrP) : that.empAddrP != null) return false;
        if (empAddrPe != null ? !empAddrPe.equals(that.empAddrPe) : that.empAddrPe != null) return false;
        if (empAddrTk != null ? !empAddrTk.equals(that.empAddrTk) : that.empAddrTk != null) return false;
        if (empAfm != null ? !empAfm.equals(that.empAfm) : that.empAfm != null) return false;
        if (empAmka != null ? !empAmka.equals(that.empAmka) : that.empAmka != null) return false;
        if (empCardNumber != null ? !empCardNumber.equals(that.empCardNumber) : that.empCardNumber != null)
            return false;
        if (empCardType != null ? !empCardType.equals(that.empCardType) : that.empCardType != null) return false;
        if (empFirstname != null ? !empFirstname.equals(that.empFirstname) : that.empFirstname != null) return false;
        if (empFromDate != null ? !empFromDate.equals(that.empFromDate) : that.empFromDate != null) return false;
        if (empLastname != null ? !empLastname.equals(that.empLastname) : that.empLastname != null) return false;
        if (empMobile != null ? !empMobile.equals(that.empMobile) : that.empMobile != null) return false;
        if (empPhone != null ? !empPhone.equals(that.empPhone) : that.empPhone != null) return false;
        if (empSpecialityId != null ? !empSpecialityId.equals(that.empSpecialityId) : that.empSpecialityId != null)
            return false;
        if (empUntilDate != null ? !empUntilDate.equals(that.empUntilDate) : that.empUntilDate != null) return false;
        if (intentedUse != null ? !intentedUse.equals(that.intentedUse) : that.intentedUse != null) return false;
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
        if (caseId != null ? !caseId.equals(that.caseId) : that.caseId != null) return false;
        if (branch0Id != null ? !branch0Id.equals(that.branch0Id) : that.branch0Id != null) return false;
        if (branch1Id != null ? !branch1Id.equals(that.branch1Id) : that.branch1Id != null) return false;

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
        result = 31 * result + (empFromDate != null ? empFromDate.hashCode() : 0);
        result = 31 * result + (empUntilDate != null ? empUntilDate.hashCode() : 0);
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
        result = 31 * result + (compAfm != null ? compAfm.hashCode() : 0);
        result = 31 * result + (compAmeIka != null ? compAmeIka.hashCode() : 0);
        result = 31 * result + (compPhone != null ? compPhone.hashCode() : 0);
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        result = 31 * result + (protDate != null ? protDate.hashCode() : 0);
        result = 31 * result + (empCardType != null ? empCardType.hashCode() : 0);
        result = 31 * result + (docId != null ? docId.hashCode() : 0);
        result = 31 * result + (submitTime != null ? submitTime.hashCode() : 0);
        result = 31 * result + (department != null ? department.hashCode() : 0);
        result = 31 * result + (rtstakLevel1 != null ? rtstakLevel1.hashCode() : 0);
        result = 31 * result + (rtstakLevel2 != null ? rtstakLevel2.hashCode() : 0);
        result = 31 * result + (rtstakLevel3 != null ? rtstakLevel3.hashCode() : 0);
        result = 31 * result + (rtstakLevel4 != null ? rtstakLevel4.hashCode() : 0);
        result = 31 * result + (rtstakLevel5 != null ? rtstakLevel5.hashCode() : 0);
        result = 31 * result + (intentedUse != null ? intentedUse.hashCode() : 0);
        result = 31 * result + (attachedDocIdEmplVer != null ? attachedDocIdEmplVer.hashCode() : 0);
        result = 31 * result + (attachedDocIdIka != null ? attachedDocIdIka.hashCode() : 0);
        result = 31 * result + (attachedDocIdSepe != null ? attachedDocIdSepe.hashCode() : 0);
        result = 31 * result + (attachedDocIdJudgmnt != null ? attachedDocIdJudgmnt.hashCode() : 0);
        result = 31 * result + (caseId != null ? caseId.hashCode() : 0);
        result = 31 * result + (branch0Id != null ? branch0Id.hashCode() : 0);
        result = 31 * result + (branch1Id != null ? branch1Id.hashCode() : 0);
        return result;
    }
}
