package sepe.domain.company;

import sepe.dto.SpPtlCompTaAnnDiaryEntryDTO;
import sepe.dto.SpPtlCompTaAnnTaEntryDTO;

import javax.annotation.Nullable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by dimitrisf on 5/10/2018.
 */

@Entity
@javax.persistence.Table(name = "SP_PTL_COMP_TA_ANN_SE", schema = "SP_PTL", catalog = "")
public class SpPtlCompTaAnnSe {

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

    private Long companyId;

    @Basic
    @javax.persistence.Column(name = "COMPANY_ID")
    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    private String compTaxNumber;

    @Basic
    @javax.persistence.Column(name = "COMP_TAX_NUMBER")
    public String getCompTaxNumber() {
        return compTaxNumber;
    }

    public void setCompTaxNumber(String compTaxNumber) {
        this.compTaxNumber = compTaxNumber;
    }

    private String compFullName;

    @Basic
    @javax.persistence.Column(name = "COMP_FULL_NAME")
    public String getCompFullName() {
        return compFullName;
    }

    public void setCompFullName(String compFullName) {
        this.compFullName = compFullName;
    }

    private String compPhone;

    @Basic
    @javax.persistence.Column(name = "COMP_PHONE")
    public String getCompPhone() {
        return compPhone;
    }

    public void setCompPhone(String compPhone) {
        this.compPhone = compPhone;
    }

    private String compAddr;

    @Basic
    @javax.persistence.Column(name = "COMP_ADDR")
    public String getCompAddr() {
        return compAddr;
    }

    public void setCompAddr(String compAddr) {
        this.compAddr = compAddr;
    }

    private String compAddrD;

    @Basic
    @javax.persistence.Column(name = "COMP_ADDR_D")
    public String getCompAddrD() {
        return compAddrD;
    }

    public void setCompAddrD(String compAddrD) {
        this.compAddrD = compAddrD;
    }

    private String compAddrK;

    @Basic
    @javax.persistence.Column(name = "COMP_ADDR_K")
    public String getCompAddrK() {
        return compAddrK;
    }

    public void setCompAddrK(String compAddrK) {
        this.compAddrK = compAddrK;
    }

    private String compAddrP;

    @Basic
    @javax.persistence.Column(name = "COMP_ADDR_P")
    public String getCompAddrP() {
        return compAddrP;
    }

    public void setCompAddrP(String compAddrP) {
        this.compAddrP = compAddrP;
    }

    private String compAddrPe;

    @Basic
    @javax.persistence.Column(name = "COMP_ADDR_PE")
    public String getCompAddrPe() {
        return compAddrPe;
    }

    public void setCompAddrPe(String compAddrPe) {
        this.compAddrPe = compAddrPe;
    }

    private String compAddrTk;

    @Basic
    @javax.persistence.Column(name = "COMP_ADDR_TK")
    public String getCompAddrTk() {
        return compAddrTk;
    }

    public void setCompAddrTk(String compAddrTk) {
        this.compAddrTk = compAddrTk;
    }

    private Long compEbrBranch0Id;

    @Basic
    @javax.persistence.Column(name = "COMP_EBR_BRANCH0_ID")
    public Long getCompEbrBranch0Id() {
        return compEbrBranch0Id;
    }

    public void setCompEbrBranch0Id(Long compEbrBranch0Id) {
        this.compEbrBranch0Id = compEbrBranch0Id;
    }

    private Long compEbrBranchId;

    @Basic
    @javax.persistence.Column(name = "COMP_EBR_BRANCH_ID")
    public Long getCompEbrBranchId() {
        return compEbrBranchId;
    }

    public void setCompEbrBranchId(Long compEbrBranchId) {
        this.compEbrBranchId = compEbrBranchId;
    }

    private Long compPtlBranchId;

    @Basic
    @javax.persistence.Column(name = "COMP_PTL_BRANCH_ID")
    public Long getCompPtlBranchId() {
        return compPtlBranchId;
    }

    public void setCompPtlBranchId(Long compPtlBranchId) {
        this.compPtlBranchId = compPtlBranchId;
    }

    private Long attachedDocId;

    @Basic
    @javax.persistence.Column(name = "ATTACHED_DOC_ID")
    public Long getAttachedDocId() {
        return attachedDocId;
    }

    public void setAttachedDocId(Long attachedDocId) {
        this.attachedDocId = attachedDocId;
    }

    private Long docId;

    @Basic
    @javax.persistence.Column(name = "DOC_ID")
    public Long getDocId() {
        return docId;
    }

    public void setDocId(Long docId) {
        this.docId = docId;
    }
    private String protNo;

    @Basic
    @javax.persistence.Column(name = "PROT_NO")
    public String getProtNo() {
        return protNo;
    }

    public void setProtNo(String protNo) {
        this.protNo = protNo;
    }

    private Timestamp protDate;

    @Basic
    @javax.persistence.Column(name = "PROT_DATE")
    public Timestamp getProtDate() {
        return protDate;
    }

    public void setProtDate(Timestamp protDate) {
        this.protDate = protDate;
    }

    private Integer protYear;

    @Basic
    @javax.persistence.Column(name = "PROT_YEAR")
    public Integer getProtYear() {
        return protYear;
    }

    public void setProtYear(Integer protYear) {
        this.protYear = protYear;
    }

    private String submitTime;

    @Basic
    @javax.persistence.Column(name = "SUBMIT_TIME")
    public String getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(String submitTime) {
        this.submitTime = submitTime;
    }

    private Long caseId;

    @Basic
    @javax.persistence.Column(name = "CASE_ID")
    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }

    private Integer reqStatus;

    @Basic
    @javax.persistence.Column(name = "REQ_STATUS")
    public Integer getReqStatus() {
        return reqStatus;
    }

    public void setReqStatus(Integer reqStatus) {
        this.reqStatus = reqStatus;
    }

    private Integer subStatus;

    @Basic
    @javax.persistence.Column(name = "SUB_STATUS")
    public Integer getSubStatus() {
        return subStatus;
    }

    public void setSubStatus(Integer subStatus) {
        this.subStatus = subStatus;
    }

    private Long sepeDept;

    @Basic
    @javax.persistence.Column(name = "SEPE_DEPT")
    public Long getSepeDept() {
        return sepeDept;
    }

    public void setSepeDept(Long sepeDept) {
        this.sepeDept = sepeDept;
    }

    private String compAme;

    @Basic
    @javax.persistence.Column(name = "COMP_AME")
    public String getCompAme() {
        return compAme;
    }

    public void setCompAme(String compAme) {
        this.compAme = compAme;
    }

    private Integer categBNum;

    @Basic
    @javax.persistence.Column(name = "CATEG_B_NUM")
    public Integer getCategBNum() {
        return categBNum;
    }

    public void setCategBNum(Integer categBNum) {
        this.categBNum = categBNum;
    }

    private Integer categCNum;

    @Basic
    @javax.persistence.Column(name = "CATEG_C_NUM")
    public Integer getCategCNum() {
        return categCNum;
    }

    public void setCategCNum(Integer categCNum) {
        this.categCNum = categCNum;
    }

    private Double compCategBNum;

    @Basic
    @javax.persistence.Column(name = "COMP_CATEG_B_NUM")
    public Double getCompCategBNum() {
        return compCategBNum;
    }

    public void setCompCategBNum(Double compCategBNum) {
        this.compCategBNum = compCategBNum;
    }

    private Double compCategCNum;

    @Basic
    @javax.persistence.Column(name = "COMP_CATEG_C_NUM")
    public Double getCompCategCNum() {
        return compCategCNum;
    }

    public void setCompCategCNum(Double compCategCNum) {
        this.compCategCNum = compCategCNum;
    }

    private Long totalHours;

    @Basic
    @javax.persistence.Column(name = "TOTAL_HOURS")
    public Long getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(Long totalHours) {
        this.totalHours = totalHours;
    }

    private String taFirstName;

    @Basic
    @javax.persistence.Column(name = "TA_FIRSTNAME")
    public String getTaFirstName() {
        return taFirstName;
    }

    public void setTaFirstName(String taFirstName) {
        this.taFirstName = taFirstName;
    }

    private String taLastName;

    @Basic
    @javax.persistence.Column(name = "TA_LASTNAME")
    public String getTaLastName() {
        return taLastName;
    }

    public void setTaLastName(String taLastName) {
        this.taLastName = taLastName;
    }

    private String taFatherName;

    @Basic
    @javax.persistence.Column(name = "TA_FATHERNAME")
    public String getTaFatherName() {
        return taFatherName;
    }

    public void setTaFatherName(String taFatherName) {
        this.taFatherName = taFatherName;
    }

    private String taPhone;

    @Basic
    @javax.persistence.Column(name = "TA_PHONE")
    public String getTaPhone() {
        return taPhone;
    }

    public void setTaPhone(String taPhone) {
        this.taPhone = taPhone;
    }

    private String taEmail;

    @Basic
    @javax.persistence.Column(name = "TA_EMAIL")
    public String getTaEmail() {
        return taEmail;
    }

    public void setTaEmail(String taEmail) {
        this.taEmail = taEmail;
    }

    private String taAfm;

    @Basic
    @javax.persistence.Column(name = "TA_AFM")
    public String getTaAfm() {
        return taAfm;
    }

    public void setTaAfm(String taAfm) {
        this.taAfm = taAfm;
    }

    private String taAddr;

    @Basic
    @javax.persistence.Column(name = "TA_ADDR")
    public String getTaAddr() {
        return taAddr;
    }

    public void setTaAddr(String taAddr) {
        this.taAddr = taAddr;
    }

    private String taAddrD;

    @Basic
    @javax.persistence.Column(name = "TA_ADDR_D")
    public String getTaAddrD() {
        return taAddrD;
    }

    public void setTaAddrD(String taAddrD) {
        this.taAddrD = taAddrD;
    }

    private String taAddrK;

    @Basic
    @javax.persistence.Column(name = "TA_ADDR_K")
    public String getTaAddrK() {
        return taAddrK;
    }

    public void setTaAddrK(String taAddrK) {
        this.taAddrK = taAddrK;
    }

    private String taAddrP;

    @Basic
    @javax.persistence.Column(name = "TA_ADDR_P")
    public String getTaAddrP() {
        return taAddrP;
    }

    public void setTaAddrP(String taAddrP) {
        this.taAddrP = taAddrP;
    }

    private String taAddrPe;

    @Basic
    @javax.persistence.Column(name = "TA_ADDR_PE")
    public String getTaAddrPe() {
        return taAddrPe;
    }

    public void setTaAddrPe(String taAddrPe) {
        this.taAddrPe = taAddrPe;
    }

    private String taAddrTk;

    @Basic
    @javax.persistence.Column(name = "TA_ADDR_TK")
    public String getTaAddrTk() {
        return taAddrTk;
    }

    public void setTaAddrTk(String taAddrTk) {
        this.taAddrTk = taAddrTk;
    }

    private String eduTitle;

    @Basic
    @javax.persistence.Column(name = "EDU_TITLE")
    public String getEduTitle() {
        return eduTitle;
    }

    public void setEduTitle(String eduTitle) {
        this.eduTitle = eduTitle;
    }

    private String eduNum;

    @Basic
    @javax.persistence.Column(name = "EDU_NUM")
    public String getEduNum() {
        return eduNum;
    }

    public void setEduNum(String eduNum) {
        this.eduNum = eduNum;
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

    private Long branchSector;

    @Basic
    @javax.persistence.Column(name = "BRANCH_SECTOR")
    public Long getBranchSector() {
        return branchSector;
    }

    public void setBranchSector(Long branchSector) {
        this.branchSector = branchSector;
    }

    private Long userId;

    @Basic
    @javax.persistence.Column(name = "USER_ID")
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /////////////////////////////////////////////////
    // /*
    private Set<SpPtlCompTaAnnSeDiary> compTaAnnSeDiaryEntries = new HashSet<SpPtlCompTaAnnSeDiary>(0);

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "compTaAnnSe", cascade = CascadeType.ALL, orphanRemoval = true) // SHOULD BE EQUAL TO ATTRIBUTE NAME OF OTHER CLASS!!!!
    public Set<SpPtlCompTaAnnSeDiary> getCompTaAnnSeDiaryEntries() {
        return this.compTaAnnSeDiaryEntries;
    }

    public void setCompTaAnnSeDiaryEntries(Set<SpPtlCompTaAnnSeDiary> compTaAnnSeDiaryEntries) {
        this.compTaAnnSeDiaryEntries = compTaAnnSeDiaryEntries;
    }
    // */
    /////////////////////////////////////////////////

    @Nullable
    private SpPtlCompTaAnnDiaryEntryDTO[] diaryEntries;

    @Transient
    @Nullable
    public SpPtlCompTaAnnDiaryEntryDTO[] getDiaryEntries() {
        return diaryEntries;
    }

    @Transient
    @Nullable
    public void setDiaryEntries(@Nullable SpPtlCompTaAnnDiaryEntryDTO[] diaryEntries) {
        this.diaryEntries = diaryEntries;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpPtlCompTaAnnSe that = (SpPtlCompTaAnnSe) o;

        if (id != that.id) return false;
        if (companyId != that.companyId) return false;
        if (compTaxNumber != null ? !compTaxNumber.equals(that.compTaxNumber) : that.compTaxNumber != null)
            return false;
        if (compFullName != null ? !compFullName.equals(that.compFullName) : that.compFullName != null) return false;
        if (compPhone != null ? !compPhone.equals(that.compPhone) : that.compPhone != null) return false;
        if (compAddr != null ? !compAddr.equals(that.compAddr) : that.compAddr != null) return false;
        if (compAddrD != null ? !compAddrD.equals(that.compAddrD) : that.compAddrD != null) return false;
        if (compAddrK != null ? !compAddrK.equals(that.compAddrK) : that.compAddrK != null) return false;
        if (compAddrP != null ? !compAddrP.equals(that.compAddrP) : that.compAddrP != null) return false;
        if (compAddrPe != null ? !compAddrPe.equals(that.compAddrPe) : that.compAddrPe != null) return false;
        if (compAddrTk != null ? !compAddrTk.equals(that.compAddrTk) : that.compAddrTk != null) return false;
        if (compEbrBranch0Id != null ? !compEbrBranch0Id.equals(that.compEbrBranch0Id) : that.compEbrBranch0Id != null)
            return false;
        if (compEbrBranchId != null ? !compEbrBranchId.equals(that.compEbrBranchId) : that.compEbrBranchId != null)
            return false;
        if (compPtlBranchId != null ? !compPtlBranchId.equals(that.compPtlBranchId) : that.compPtlBranchId != null)
            return false;
        if (attachedDocId != null ? !attachedDocId.equals(that.attachedDocId) : that.attachedDocId != null)
            return false;
        if (docId != null ? !docId.equals(that.docId) : that.docId != null) return false;
        if (protNo != null ? !protNo.equals(that.protNo) : that.protNo != null) return false;
        if (protDate != null ? !protDate.equals(that.protDate) : that.protDate != null) return false;
        if (protYear != null ? !protYear.equals(that.protYear) : that.protYear != null) return false;
        if (submitTime != null ? !submitTime.equals(that.submitTime) : that.submitTime != null) return false;
        if (caseId != null ? !caseId.equals(that.caseId) : that.caseId != null) return false;
        if (reqStatus != null ? !reqStatus.equals(that.reqStatus) : that.reqStatus != null) return false;
        if (subStatus != null ? !subStatus.equals(that.subStatus) : that.subStatus != null) return false;
        if (sepeDept != null ? !sepeDept.equals(that.sepeDept) : that.sepeDept != null) return false;
        if (compAme != null ? !compAme.equals(that.compAme) : that.compAme != null) return false;
        if (categBNum != null ? !categBNum.equals(that.categBNum) : that.categBNum != null) return false;
        if (categCNum != null ? !categCNum.equals(that.categCNum) : that.categCNum != null) return false;
        if (compCategBNum != null ? !compCategBNum.equals(that.compCategBNum) : that.compCategBNum != null) return false;
        if (compCategCNum != null ? !compCategCNum.equals(that.compCategCNum) : that.compCategCNum != null) return false;
        if (totalHours != null ? !totalHours.equals(that.totalHours) : that.totalHours != null) return false;
        if (taFirstName != null ? !taFirstName.equals(that.taFirstName) : that.taFirstName != null) return false;
        if (taLastName != null ? !taLastName.equals(that.taLastName) : that.taLastName != null) return false;
        if (taFatherName != null ? !taFatherName.equals(that.taFatherName) : that.taFatherName != null) return false;
        if (taAfm != null ? !taAfm.equals(that.taAfm) : that.taAfm != null) return false;
        if (taPhone != null ? !taPhone.equals(that.taPhone) : that.taPhone != null) return false;
        if (taEmail != null ? !taEmail.equals(that.taEmail) : that.taEmail != null) return false;
        if (taAddr != null ? !taAddr.equals(that.taAddr) : that.taAddr != null) return false;
        if (taAddrD != null ? !taAddrD.equals(that.taAddrD) : that.taAddrD != null) return false;
        if (taAddrK != null ? !taAddrK.equals(that.taAddrK) : that.taAddrK != null) return false;
        if (taAddrP != null ? !taAddrP.equals(that.taAddrP) : that.taAddrP != null) return false;
        if (taAddrPe != null ? !taAddrPe.equals(that.taAddrPe) : that.taAddrPe != null) return false;
        if (taAddrTk != null ? !taAddrTk.equals(that.taAddrTk) : that.taAddrTk != null) return false;
        if (eduTitle != null ? !eduTitle.equals(that.eduTitle) : that.eduTitle != null) return false;
        if (eduNum != null ? !eduNum.equals(that.eduNum) : that.eduNum != null) return false;
        if (dateStart != null ? !dateStart.equals(that.dateStart) : that.dateStart != null) return false;
        if (dateEnd != null ? !dateEnd.equals(that.dateEnd) : that.dateEnd != null) return false;
        if (branchSector != null ? !branchSector.equals(that.branchSector) : that.branchSector != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (companyId ^ (companyId >>> 32));
        result = 31 * result + (compTaxNumber != null ? compTaxNumber.hashCode() : 0);
        result = 31 * result + (compFullName != null ? compFullName.hashCode() : 0);
        result = 31 * result + (compPhone != null ? compPhone.hashCode() : 0);
        result = 31 * result + (compAddr != null ? compAddr.hashCode() : 0);
        result = 31 * result + (compAddrD != null ? compAddrD.hashCode() : 0);
        result = 31 * result + (compAddrK != null ? compAddrK.hashCode() : 0);
        result = 31 * result + (compAddrP != null ? compAddrP.hashCode() : 0);
        result = 31 * result + (compAddrPe != null ? compAddrPe.hashCode() : 0);
        result = 31 * result + (compAddrTk != null ? compAddrTk.hashCode() : 0);
        result = 31 * result + (compEbrBranch0Id != null ? compEbrBranch0Id.hashCode() : 0);
        result = 31 * result + (compEbrBranchId != null ? compEbrBranchId.hashCode() : 0);
        result = 31 * result + (compPtlBranchId != null ? compPtlBranchId.hashCode() : 0);
        result = 31 * result + (attachedDocId != null ? attachedDocId.hashCode() : 0);
        result = 31 * result + (docId != null ? docId.hashCode() : 0);
        result = 31 * result + (protNo != null ? protNo.hashCode() : 0);
        result = 31 * result + (protDate != null ? protDate.hashCode() : 0);
        result = 31 * result + (protYear != null ? protYear.hashCode() : 0);
        result = 31 * result + (submitTime != null ? submitTime.hashCode() : 0);
        result = 31 * result + (caseId != null ? caseId.hashCode() : 0);
        result = 31 * result + (reqStatus != null ? reqStatus.hashCode() : 0);
        result = 31 * result + (subStatus != null ? subStatus.hashCode() : 0);
        result = 31 * result + (sepeDept != null ? sepeDept.hashCode() : 0);
        result = 31 * result + (compAme != null ? compAme.hashCode() : 0);
        result = 31 * result + (categBNum != null ? categBNum.hashCode() : 0);
        result = 31 * result + (categCNum != null ? categCNum.hashCode() : 0);
        result = 31 * result + (compCategBNum != null ? compCategBNum.hashCode() : 0);
        result = 31 * result + (compCategCNum != null ? compCategCNum.hashCode() : 0);
        result = 31 * result + (totalHours != null ? totalHours.hashCode() : 0);
        result = 31 * result + (taFirstName != null ? taFirstName.hashCode() : 0);
        result = 31 * result + (taLastName != null ? taLastName.hashCode() : 0);
        result = 31 * result + (taFatherName != null ? taFatherName.hashCode() : 0);
        result = 31 * result + (taAfm != null ? taAfm.hashCode() : 0);
        result = 31 * result + (taPhone != null ? taPhone.hashCode() : 0);
        result = 31 * result + (taEmail != null ? taEmail.hashCode() : 0);
        result = 31 * result + (taAddr != null ? taAddr.hashCode() : 0);
        result = 31 * result + (taAddrD != null ? taAddrD.hashCode() : 0);
        result = 31 * result + (taAddrK != null ? taAddrK.hashCode() : 0);
        result = 31 * result + (taAddrP != null ? taAddrP.hashCode() : 0);
        result = 31 * result + (taAddrPe != null ? taAddrPe.hashCode() : 0);
        result = 31 * result + (taAddrTk != null ? taAddrTk.hashCode() : 0);
        result = 31 * result + (eduTitle != null ? eduTitle.hashCode() : 0);
        result = 31 * result + (eduNum != null ? eduNum.hashCode() : 0);
        result = 31 * result + (dateStart != null ? dateStart.hashCode() : 0);
        result = 31 * result + (dateEnd != null ? dateEnd.hashCode() : 0);
        result = 31 * result + (branchSector != null ? branchSector.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }

}
