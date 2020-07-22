package sepe.domain.company;

import sepe.dto.SpPtlCompTaAnnDiaryEntryDTO;
import sepe.dto.SpPtlCompTaAnnTaEntryDTO;

import javax.annotation.Nullable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Nikos on 6/16/2015.
 */
@Entity
@javax.persistence.Table(name = "SP_PTL_COMP_TA_ANN", schema = "SP_PTL", catalog = "")
public class SpPtlCompTaAnn {
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

    private Long attachedDocIdPause;

    @Basic
    @javax.persistence.Column(name = "ATTACHED_DOC_ID_PAUSE")
    public Long getAttachedDocIdPause() {
        return attachedDocIdPause;
    }

    public void setAttachedDocIdPause(Long attachedDocIdPause) {
        this.attachedDocIdPause = attachedDocIdPause;
    }

    private Long docIdPause;

    @Basic
    @javax.persistence.Column(name = "DOC_ID_PAUSE")
    public Long getDocIdPause() {
        return docIdPause;
    }

    public void setDocIdPause(Long docIdPause) {
        this.docIdPause = docIdPause;
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

    private Double compCategANum;

    @Basic
    @javax.persistence.Column(name = "COMP_CATEG_A_NUM")
    public Double getCompCategANum() {
        return compCategANum;
    }

    public void setCompCategANum(Double compCategANum) {
        this.compCategANum = compCategANum;
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

    private Double categANum;

    @Basic
    @javax.persistence.Column(name = "CATEG_A_NUM")
    public Double getCategANum() {
        return categANum;
    }

    public void setCategANum(Double categANum) {
        this.categANum = categANum;
    }

    private Double categBNum;

    @Basic
    @javax.persistence.Column(name = "CATEG_B_NUM")
    public Double getCategBNum() {
        return categBNum;
    }

    public void setCategBNum(Double categBNum) {
        this.categBNum = categBNum;
    }

    private Double categCNum;

    @Basic
    @javax.persistence.Column(name = "CATEG_C_NUM")
    public Double getCategCNum() {
        return categCNum;
    }

    public void setCategCNum(Double categCNum) {
        this.categCNum = categCNum;
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

    private Long cooperationTypeBasic;

    @Basic
    @javax.persistence.Column(name = "COOPERATION_TYPE_BASIC")
    public Long getCooperationTypeBasic() {
        return cooperationTypeBasic;
    }

    public void setCooperationTypeBasic(Long cooperationTypeBasic) {
        this.cooperationTypeBasic = cooperationTypeBasic;
    }

    private Long technicianRegrequestId;

    @Basic
    @javax.persistence.Column(name = "TECHNICIAN_REGREQUEST_ID")
    public Long getTechnicianRegrequestId() {
        return technicianRegrequestId;
    }

    public void setTechnicianRegrequestId(Long technicianRegrequestId) {
        this.technicianRegrequestId = technicianRegrequestId;
    }

    private String taFullname;

    @Basic
    @javax.persistence.Column(name = "TA_FULLNAME")
    public String getTaFullname() {
        return taFullname;
    }

    public void setTaFullname(String taFullname) {
        this.taFullname = taFullname;
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

    private Long exyppBasic;

    @Basic
    @javax.persistence.Column(name = "EXYPP_BASIC")
    public Long getExyppBasic() {
        return exyppBasic;
    }

    public void setExyppBasic(Long exyppBasic) {
        this.exyppBasic = exyppBasic;
    }

    private Integer taAnnStatus;

    @Basic
    @javax.persistence.Column(name = "TA_ANN_STATUS")
    public Integer getTaAnnStatus() {
        return taAnnStatus;
    }

    public void setTaAnnStatus(Integer taAnnStatus) {
        this.taAnnStatus = taAnnStatus;
    }

    private Integer taAnnTaTotal;

    @Basic
    @javax.persistence.Column(name = "TA_ANN_TA_TOTAL")
    public Integer getTaAnnTaTotal() {
        return taAnnTaTotal;
    }

    public void setTaAnnTaTotal(Integer taAnnTaTotal) {
        this.taAnnTaTotal = taAnnTaTotal;
    }

    private Integer taAnnTaTotalAccept;

    @Basic
    @javax.persistence.Column(name = "TA_ANN_TA_TOTAL_ACCEPT")
    public Integer getTaAnnTaTotalAccept() {
        return taAnnTaTotalAccept;
    }

    public void setTaAnnTaTotalAccept(Integer taAnnTaTotalAccept) {
        this.taAnnTaTotalAccept = taAnnTaTotalAccept;
    }

    private Integer taAnnTaTotalReject;

    @Basic
    @javax.persistence.Column(name = "TA_ANN_TA_TOTAL_REJECT")
    public Integer getTaAnnTaTotalReject() {
        return taAnnTaTotalReject;
    }

    public void setTaAnnTaTotalReject(Integer taAnnTaTotalReject) {
        this.taAnnTaTotalReject = taAnnTaTotalReject;
    }
    private String taSpecialityOther;

    @Basic
    @javax.persistence.Column(name = "TA_SPECIALITY_OTHER")
    public String getTaSpecialityOther() {
        return taSpecialityOther;
    }

    public void setTaSpecialityOther(String taSpecialityOther) {
        this.taSpecialityOther = taSpecialityOther;
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

    private Long technicianRegrequestUserId;

    @Basic
    @javax.persistence.Column(name = "TECHNICIAN_REGREQUEST_USER_ID")
    public Long getTechnicianRegrequestUserId() {
        return technicianRegrequestUserId;
    }

    public void setTechnicianRegrequestUserId(Long technicianRegrequestUserId) {
        this.technicianRegrequestUserId = technicianRegrequestUserId;
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

    private Long compTaAnnPrevId;

    @Basic
    @javax.persistence.Column(name = "COMP_TA_ANN_PREV_ID")
    public Long getCompTaAnnPrevId() {
        return compTaAnnPrevId;
    }

    public void setCompTaAnnPrevId(Long compTaAnnPrevId) {
        this.compTaAnnPrevId = compTaAnnPrevId;
    }

    private Integer taReplaceResponse;

    @Basic
    @javax.persistence.Column(name = "TA_REPLACE_RESPONSE")
    public Integer getTaReplaceResponse() {
        return taReplaceResponse;
    }

    public void setTaReplaceResponse(Integer taReplaceResponse) {
        this.taReplaceResponse = taReplaceResponse;
    }

    private String pauseExplanation;

    @Basic
    @javax.persistence.Column(name = "PAUSE_EXPLANATION")
    public String getPauseExplanation() {
        return pauseExplanation;
    }

    public void setPauseExplanation(String pauseExplanation) {
        this.pauseExplanation = pauseExplanation;
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

    private String protNoPause;

    @Basic
    @javax.persistence.Column(name = "PROT_NO_PAUSE")
    public String getProtNoPause() {
        return protNoPause;
    }

    public void setProtNoPause(String protNoPause) {
        this.protNoPause = protNoPause;
    }

    private Timestamp protDatePause;

    @Basic
    @javax.persistence.Column(name = "PROT_DATE_PAUSE")
    public Timestamp getProtDatePause() {
        return protDatePause;
    }

    public void setProtDatePause(Timestamp protDatePause) {
        this.protDatePause = protDatePause;
    }

    private String protNoResign;

    @Basic
    @javax.persistence.Column(name = "PROT_NO_RESIGN")
    public String getProtNoResign() {
        return protNoResign;
    }

    public void setProtNoResign(String protNoResign) {
        this.protNoResign = protNoResign;
    }

    private Timestamp protDateResign;

    @Basic
    @javax.persistence.Column(name = "PROT_DATE_RESIGN")
    public Timestamp getProtDateResign() {
        return protDateResign;
    }

    public void setProtDateResign(Timestamp protDateResign) {
        this.protDateResign = protDateResign;
    }

    /////////////////////////////////////////////////
    // /*
    private Set<SpPtlCompTaAnnDiaryEntry> compTaAnnDiaryEntries = new HashSet<SpPtlCompTaAnnDiaryEntry>(0);

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "compTaAnn", cascade = CascadeType.ALL, orphanRemoval = true) // SHOULD BE EQUAL TO ATTRIBUTE NAME OF OTHER CLASS!!!!
    public Set<SpPtlCompTaAnnDiaryEntry> getCompTaAnnDiaryEntries() {
        return this.compTaAnnDiaryEntries;
    }

    public void setCompTaAnnDiaryEntries(Set<SpPtlCompTaAnnDiaryEntry> compTaAnnDiaryEntries) {
        this.compTaAnnDiaryEntries = compTaAnnDiaryEntries;
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

    /////////////////////////////////////////////////
    // /*
    private Set<SpPtlCompTaAnnTaEntry> compTaAnnTaEntries = new HashSet<SpPtlCompTaAnnTaEntry>(0);

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "compTaAnn", cascade = CascadeType.ALL, orphanRemoval = true) // SHOULD BE EQUAL TO ATTRIBUTE NAME OF OTHER CLASS!!!!
    public Set<SpPtlCompTaAnnTaEntry> getCompTaAnnTaEntries() {
        return this.compTaAnnTaEntries;
    }

    public void setCompTaAnnTaEntries(Set<SpPtlCompTaAnnTaEntry> compTaAnnTaEntries) {
        this.compTaAnnTaEntries = compTaAnnTaEntries;
    }
    // */
    /////////////////////////////////////////////////

    @Nullable
    private SpPtlCompTaAnnTaEntryDTO[] taEntries;

    @Transient
    @Nullable
    public SpPtlCompTaAnnTaEntryDTO[] getTaEntries() {
        return taEntries;
    }

    @Transient
    @Nullable
    public void setTaEntries(@Nullable SpPtlCompTaAnnTaEntryDTO[] taEntries) {
        this.taEntries = taEntries;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpPtlCompTaAnn that = (SpPtlCompTaAnn) o;

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
        if (attachedDocId != null ? !attachedDocId.equals(that.attachedDocId) : that.attachedDocId != null)
            return false;
        if (docId != null ? !docId.equals(that.docId) : that.docId != null) return false;
        if (attachedDocIdPause != null ? !attachedDocIdPause.equals(that.attachedDocIdPause) : that.attachedDocIdPause != null)
            return false;
        if (docIdPause != null ? !docIdPause.equals(that.docIdPause) : that.docIdPause != null) return false;
        if (protNo != null ? !protNo.equals(that.protNo) : that.protNo != null) return false;
        if (protDate != null ? !protDate.equals(that.protDate) : that.protDate != null) return false;
        if (protYear != null ? !protYear.equals(that.protYear) : that.protYear != null) return false;
        if (submitTime != null ? !submitTime.equals(that.submitTime) : that.submitTime != null) return false;
        if (caseId != null ? !caseId.equals(that.caseId) : that.caseId != null) return false;
        if (reqStatus != null ? !reqStatus.equals(that.reqStatus) : that.reqStatus != null) return false;
        if (subStatus != null ? !subStatus.equals(that.subStatus) : that.subStatus != null) return false;
        if (sepeDept != null ? !sepeDept.equals(that.sepeDept) : that.sepeDept != null) return false;
        if (compAme != null ? !compAme.equals(that.compAme) : that.compAme != null) return false;
        if (categANum != null ? !categANum.equals(that.categANum) : that.categANum != null) return false;
        if (categBNum != null ? !categBNum.equals(that.categBNum) : that.categBNum != null) return false;
        if (categCNum != null ? !categCNum.equals(that.categCNum) : that.categCNum != null) return false;
        if (compCategANum != null ? !compCategANum.equals(that.compCategANum) : that.compCategANum != null) return false;
        if (compCategBNum != null ? !compCategBNum.equals(that.compCategBNum) : that.compCategBNum != null) return false;
        if (compCategCNum != null ? !compCategCNum.equals(that.compCategCNum) : that.compCategCNum != null) return false;
        if (totalHours != null ? !totalHours.equals(that.totalHours) : that.totalHours != null) return false;
        if (cooperationTypeBasic != null ? !cooperationTypeBasic.equals(that.cooperationTypeBasic) : that.cooperationTypeBasic != null)
            return false;
        if (technicianRegrequestId != null ? !technicianRegrequestId.equals(that.technicianRegrequestId) : that.technicianRegrequestId != null)
            return false;
        if (taFullname != null ? !taFullname.equals(that.taFullname) : that.taFullname != null) return false;
        if (taAfm != null ? !taAfm.equals(that.taAfm) : that.taAfm != null) return false;
        if (exyppBasic != null ? !exyppBasic.equals(that.exyppBasic) : that.exyppBasic != null) return false;
        if (taAnnStatus != null ? !taAnnStatus.equals(that.taAnnStatus) : that.taAnnStatus != null) return false;
        if (taSpecialityOther != null ? !taSpecialityOther.equals(that.taSpecialityOther) : that.taSpecialityOther != null)
            return false;
        if (compPtlBranchId != null ? !compPtlBranchId.equals(that.compPtlBranchId) : that.compPtlBranchId != null)
            return false;
        if (technicianRegrequestUserId != null ? !technicianRegrequestUserId.equals(that.technicianRegrequestUserId) : that.technicianRegrequestUserId != null)
            return false;
        if (dateStart != null ? !dateStart.equals(that.dateStart) : that.dateStart != null) return false;
        if (dateEnd != null ? !dateEnd.equals(that.dateEnd) : that.dateEnd != null) return false;
        if (compTaAnnPrevId != null ? !compTaAnnPrevId.equals(that.compTaAnnPrevId) : that.compTaAnnPrevId != null)
            return false;
        if (taReplaceResponse != null ? !taReplaceResponse.equals(that.taReplaceResponse) : that.taReplaceResponse != null)
            return false;
        if (pauseExplanation != null ? !pauseExplanation.equals(that.pauseExplanation) : that.pauseExplanation != null)
            return false;
        if (branchSector != null ? !branchSector.equals(that.branchSector) : that.branchSector != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (protNoPause != null ? !protNoPause.equals(that.protNoPause) : that.protNoPause != null) return false;
        if (protDatePause != null ? !protDatePause.equals(that.protDatePause) : that.protDatePause != null) return false;
        if (protNoResign != null ? !protNoResign.equals(that.protNoResign) : that.protNoResign != null) return false;
        if (protDateResign != null ? !protDateResign.equals(that.protDateResign) : that.protDateResign != null) return false;

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
        result = 31 * result + (attachedDocId != null ? attachedDocId.hashCode() : 0);
        result = 31 * result + (docId != null ? docId.hashCode() : 0);
        result = 31 * result + (attachedDocIdPause != null ? attachedDocIdPause.hashCode() : 0);
        result = 31 * result + (docIdPause != null ? docIdPause.hashCode() : 0);
        result = 31 * result + (protNo != null ? protNo.hashCode() : 0);
        result = 31 * result + (protDate != null ? protDate.hashCode() : 0);
        result = 31 * result + (protYear != null ? protYear.hashCode() : 0);
        result = 31 * result + (submitTime != null ? submitTime.hashCode() : 0);
        result = 31 * result + (caseId != null ? caseId.hashCode() : 0);
        result = 31 * result + (reqStatus != null ? reqStatus.hashCode() : 0);
        result = 31 * result + (subStatus != null ? subStatus.hashCode() : 0);
        result = 31 * result + (sepeDept != null ? sepeDept.hashCode() : 0);
        result = 31 * result + (compAme != null ? compAme.hashCode() : 0);
        result = 31 * result + (categANum != null ? categANum.hashCode() : 0);
        result = 31 * result + (categBNum != null ? categBNum.hashCode() : 0);
        result = 31 * result + (categCNum != null ? categCNum.hashCode() : 0);
        result = 31 * result + (compCategANum != null ? compCategANum.hashCode() : 0);
        result = 31 * result + (compCategBNum != null ? compCategBNum.hashCode() : 0);
        result = 31 * result + (compCategCNum != null ? compCategCNum.hashCode() : 0);
        result = 31 * result + (totalHours != null ? totalHours.hashCode() : 0);
        result = 31 * result + (cooperationTypeBasic != null ? cooperationTypeBasic.hashCode() : 0);
        result = 31 * result + (taFullname != null ? taFullname.hashCode() : 0);
        result = 31 * result + (taAfm != null ? taAfm.hashCode() : 0);
        result = 31 * result + (exyppBasic != null ? exyppBasic.hashCode() : 0);
        result = 31 * result + (taAnnStatus != null ? taAnnStatus.hashCode() : 0);
        result = 31 * result + (taSpecialityOther != null ? taSpecialityOther.hashCode() : 0);
        result = 31 * result + (technicianRegrequestId != null ? technicianRegrequestId.hashCode() : 0);
        result = 31 * result + (compPtlBranchId != null ? compPtlBranchId.hashCode() : 0);
        result = 31 * result + (technicianRegrequestUserId != null ? technicianRegrequestUserId.hashCode() : 0);
        result = 31 * result + (dateStart != null ? dateStart.hashCode() : 0);
        result = 31 * result + (dateEnd != null ? dateEnd.hashCode() : 0);
        result = 31 * result + (compTaAnnPrevId != null ? compTaAnnPrevId.hashCode() : 0);
        result = 31 * result + (taReplaceResponse != null ? taReplaceResponse.hashCode() : 0);
        result = 31 * result + (pauseExplanation != null ? pauseExplanation.hashCode() : 0);
        result = 31 * result + (branchSector != null ? branchSector.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (protNoPause != null ? protNoPause.hashCode() : 0);
        result = 31 * result + (protDatePause != null ? protDatePause.hashCode() : 0);
        result = 31 * result + (protNoResign != null ? protNoResign.hashCode() : 0);
        result = 31 * result + (protDateResign != null ? protDateResign.hashCode() : 0);
        return result;
    }
}
