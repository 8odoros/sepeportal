package sepe.domain.company;

/**
 * Created by Nikos on 5/16/2015.
 */

import sepe.dto.SpPtlCompIeAnnDiaryEntryDTO;
import sepe.dto.SpPtlCompIeAnnIeEntryDTO;

import javax.annotation.Nullable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Nikos on 5/16/2015.
 */
@Entity
@Table(name = "SP_PTL_COMP_IE_ANN", schema = "SP_PTL", catalog = "")
public class SpPtlCompIeAnn {
    private Long id;
    private Long companyId;
    private String compTaxNumber;
    private String compFullName;
    private String compPhone;
    private String compAddr;
    private String compAddrD;
    private String compAddrK;
    private String compAddrP;
    private String compAddrPe;
    private String compAddrTk;
    private Long compEbrBranch0Id;
    private Long compEbrBranchId;
    private Long attachedDocId;
    private Integer docId;
    private String protNo;
    private Timestamp protDate;
    private Integer protYear;
    private String submitTime;
    private Long caseId;
    private Integer reqStatus;
    private Integer subStatus;
    private Long sepeDept;
    private String compAme;
    private Integer categANum;
    private Integer categBNum;
    private Integer categCNum;
    private Integer cooperationTypeBasic;
    private Long doctorRegrequestId;
    private String ieFullname;
    private String ieAfm;
    private Long ieSpeciality;
    private Integer statementIeSearched;
    private Integer statementAdjCounty;
    private Integer statementDifCounty;
    private Integer ieAnnStatus;
    private String ieSpecialityOther;
    private Long compPtlBranchId;
    private Long doctorRegrequestUserId;
    private Timestamp dateStart;
    private Timestamp dateEnd;
    private Long compIeAnnPrevId;
    private Integer ieReplaceResponse;

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
    @Column(name = "COMPANY_ID")
    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    @Basic
    @Column(name = "COMP_TAX_NUMBER")
    public String getCompTaxNumber() {
        return compTaxNumber;
    }

    public void setCompTaxNumber(String compTaxNumber) {
        this.compTaxNumber = compTaxNumber;
    }

    @Basic
    @Column(name = "COMP_FULL_NAME")
    public String getCompFullName() {
        return compFullName;
    }

    public void setCompFullName(String compFullName) {
        this.compFullName = compFullName;
    }

    @Basic
    @Column(name = "COMP_PHONE")
    public String getCompPhone() {
        return compPhone;
    }

    public void setCompPhone(String compPhone) {
        this.compPhone = compPhone;
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
    @Column(name = "COMP_ADDR_D")
    public String getCompAddrD() {
        return compAddrD;
    }

    public void setCompAddrD(String compAddrD) {
        this.compAddrD = compAddrD;
    }

    @Basic
    @Column(name = "COMP_ADDR_K")
    public String getCompAddrK() {
        return compAddrK;
    }

    public void setCompAddrK(String compAddrK) {
        this.compAddrK = compAddrK;
    }

    @Basic
    @Column(name = "COMP_ADDR_P")
    public String getCompAddrP() {
        return compAddrP;
    }

    public void setCompAddrP(String compAddrP) {
        this.compAddrP = compAddrP;
    }

    @Basic
    @Column(name = "COMP_ADDR_PE")
    public String getCompAddrPe() {
        return compAddrPe;
    }

    public void setCompAddrPe(String compAddrPe) {
        this.compAddrPe = compAddrPe;
    }

    @Basic
    @Column(name = "COMP_ADDR_TK")
    public String getCompAddrTk() {
        return compAddrTk;
    }

    public void setCompAddrTk(String compAddrTk) {
        this.compAddrTk = compAddrTk;
    }

    @Basic
    @Column(name = "COMP_EBR_BRANCH0_ID")
    public Long getCompEbrBranch0Id() {
        return compEbrBranch0Id;
    }

    public void setCompEbrBranch0Id(Long compEbrBranch0Id) {
        this.compEbrBranch0Id = compEbrBranch0Id;
    }

    @Basic
    @Column(name = "COMP_EBR_BRANCH_ID")
    public Long getCompEbrBranchId() {
        return compEbrBranchId;
    }

    public void setCompEbrBranchId(Long compEbrBranchId) {
        this.compEbrBranchId = compEbrBranchId;
    }

    @Basic
    @Column(name = "ATTACHED_DOC_ID")
    public Long getAttachedDocId() {
        return attachedDocId;
    }

    public void setAttachedDocId(Long attachedDocId) {
        this.attachedDocId = attachedDocId;
    }

    @Basic
    @Column(name = "DOC_ID")
    public Integer getDocId() {
        return docId;
    }

    public void setDocId(Integer docId) {
        this.docId = docId;
    }

    @Basic
    @Column(name = "PROT_NO")
    public String getProtNo() {
        return protNo;
    }

    public void setProtNo(String protNo) {
        this.protNo = protNo;
    }

    @Basic
    @Column(name = "PROT_DATE")
    public Timestamp getProtDate() {
        return protDate;
    }

    public void setProtDate(Timestamp protDate) {
        this.protDate = protDate;
    }

    @Basic
    @Column(name = "PROT_YEAR")
    public Integer getProtYear() {
        return protYear;
    }

    public void setProtYear(Integer protYear) {
        this.protYear = protYear;
    }

    @Basic
    @Column(name = "SUBMIT_TIME")
    public String getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(String submitTime) {
        this.submitTime = submitTime;
    }

    @Basic
    @Column(name = "CASE_ID")
    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }

    @Basic
    @Column(name = "REQ_STATUS")
    public Integer getReqStatus() {
        return reqStatus;
    }

    public void setReqStatus(Integer reqStatus) {
        this.reqStatus = reqStatus;
    }

    @Basic
    @Column(name = "SUB_STATUS")
    public Integer getSubStatus() {
        return subStatus;
    }

    public void setSubStatus(Integer subStatus) {
        this.subStatus = subStatus;
    }

    @Basic
    @Column(name = "SEPE_DEPT")
    public Long getSepeDept() {
        return sepeDept;
    }

    public void setSepeDept(Long sepeDept) {
        this.sepeDept = sepeDept;
    }

    @Basic
    @Column(name = "COMP_AME")
    public String getCompAme() {
        return compAme;
    }

    public void setCompAme(String compAme) {
        this.compAme = compAme;
    }

    @Basic
    @Column(name = "CATEG_A_NUM")
    public Integer getCategANum() {
        return categANum;
    }

    public void setCategANum(Integer categANum) {
        this.categANum = categANum;
    }

    @Basic
    @Column(name = "CATEG_B_NUM")
    public Integer getCategBNum() {
        return categBNum;
    }

    public void setCategBNum(Integer categBNum) {
        this.categBNum = categBNum;
    }

    @Basic
    @Column(name = "CATEG_C_NUM")
    public Integer getCategCNum() {
        return categCNum;
    }

    public void setCategCNum(Integer categCNum) {
        this.categCNum = categCNum;
    }

    @Basic
    @Column(name = "COOPERATION_TYPE_BASIC")
    public Integer getCooperationTypeBasic() {
        return cooperationTypeBasic;
    }

    public void setCooperationTypeBasic(Integer cooperationTypeBasic) {
        this.cooperationTypeBasic = cooperationTypeBasic;
    }

    @Basic
    @Column(name = "DOCTOR_REGREQUEST_ID")
    public Long getDoctorRegrequestId() {
        return doctorRegrequestId;
    }

    public void setDoctorRegrequestId(Long doctorRegrequestId) {
        this.doctorRegrequestId = doctorRegrequestId;
    }

    @Basic
    @Column(name = "IE_FULLNAME")
    public String getIeFullname() {
        return ieFullname;
    }

    public void setIeFullname(String ieFullname) {
        this.ieFullname = ieFullname;
    }

    @Basic
    @Column(name = "IE_AFM")
    public String getIeAfm() {
        return ieAfm;
    }

    public void setIeAfm(String ieAfm) {
        this.ieAfm = ieAfm;
    }

    @Basic
    @Column(name = "IE_SPECIALITY")
    public Long getIeSpeciality() {
        return ieSpeciality;
    }

    public void setIeSpeciality(Long ieSpeciality) {
        this.ieSpeciality = ieSpeciality;
    }

    @Basic
    @Column(name = "STATEMENT_IE_SEARCHED")
    public Integer getStatementIeSearched() {
        return statementIeSearched;
    }

    public void setStatementIeSearched(Integer statementIeSearched) {
        this.statementIeSearched = statementIeSearched;
    }

    @Basic
    @Column(name = "STATEMENT_ADJ_COUNTY")
    public Integer getStatementAdjCounty() {
        return statementAdjCounty;
    }

    public void setStatementAdjCounty(Integer statementAdjCounty) {
        this.statementAdjCounty = statementAdjCounty;
    }

    @Basic
    @Column(name = "STATEMENT_DIF_COUNTY")
    public Integer getStatementDifCounty() {
        return statementDifCounty;
    }

    public void setStatementDifCounty(Integer statementDifCounty) {
        this.statementDifCounty = statementDifCounty;
    }

    @Basic
    @Column(name = "IE_ANN_STATUS")
    public Integer getIeAnnStatus() {
        return ieAnnStatus;
    }

    public void setIeAnnStatus(Integer ieAnnStatus) {
        this.ieAnnStatus = ieAnnStatus;
    }

    @Basic
    @Column(name = "IE_SPECIALITY_OTHER")
    public String getIeSpecialityOther() {
        return ieSpecialityOther;
    }

    public void setIeSpecialityOther(String ieSpecialityOther) {
        this.ieSpecialityOther = ieSpecialityOther;
    }

    @Basic
    @Column(name = "COMP_PTL_BRANCH_ID")
    public Long getCompPtlBranchId() {
        return compPtlBranchId;
    }

    public void setCompPtlBranchId(Long compPtlBranchId) {
        this.compPtlBranchId = compPtlBranchId;
    }

    @Basic
    @Column(name = "DOCTOR_REGREQUEST_USER_ID")
    public Long getDoctorRegrequestUserId() {
        return doctorRegrequestUserId;
    }

    public void setDoctorRegrequestUserId(Long doctorRegrequestUserId) {
        this.doctorRegrequestUserId = doctorRegrequestUserId;
    }

    @Basic
    @Column(name = "DATE_START")
    public Timestamp getDateStart() {
        return dateStart;
    }

    public void setDateStart(Timestamp dateStart) {
        this.dateStart = dateStart;
    }

    @Basic
    @Column(name = "DATE_END")
    public Timestamp getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Timestamp dateEnd) {
        this.dateEnd = dateEnd;
    }

    @Basic
    @Column(name = "COMP_IE_ANN_PREV_ID")
    public Long getCompIeAnnPrevId() {
        return compIeAnnPrevId;
    }

    public void setCompIeAnnPrevId(Long compIeAnnPrevId) {
        this.compIeAnnPrevId = compIeAnnPrevId;
    }


    @Basic
    @Column(name = "IE_REPLACE_RESPONSE")
    public Integer getIeReplaceResponse() {
        return ieReplaceResponse;
    }

    public void setIeReplaceResponse(Integer ieReplaceResponse) {
        this.ieReplaceResponse = ieReplaceResponse;
    }

    private Integer ieAnnIeTotal;

    @Basic
    @javax.persistence.Column(name = "IE_ANN_IE_TOTAL")
    public Integer getIeAnnIeTotal() {
        return ieAnnIeTotal;
    }

    public void setIeAnnIeTotal(Integer ieAnnIeTotal) {
        this.ieAnnIeTotal = ieAnnIeTotal;
    }

    private Integer ieAnnIeTotalAccept;

    @Basic
    @javax.persistence.Column(name = "IE_ANN_IE_TOTAL_ACCEPT")
    public Integer getIeAnnIeTotalAccept() {
        return ieAnnIeTotalAccept;
    }

    public void setIeAnnIeTotalAccept(Integer ieAnnIeTotalAccept) {
        this.ieAnnIeTotalAccept = ieAnnIeTotalAccept;
    }

    private Integer ieAnnIeTotalReject;

    @Basic
    @javax.persistence.Column(name = "IE_ANN_IE_TOTAL_REJECT")
    public Integer getIeAnnIeTotalReject() {
        return ieAnnIeTotalReject;
    }

    public void setIeAnnIeTotalReject(Integer ieAnnIeTotalReject) {
        this.ieAnnIeTotalReject = ieAnnIeTotalReject;
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


    /////////////////////////////////////////////////
    // /*
    private Set<SpPtlCompIeAnnDiaryEntry> compIeAnnDiaryEntries = new HashSet<SpPtlCompIeAnnDiaryEntry>(0);

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "compIeAnn", cascade = CascadeType.ALL, orphanRemoval = true) // SHOULD BE EQUAL TO ATTRIBUTE NAME OF OTHER CLASS!!!!
    public Set<SpPtlCompIeAnnDiaryEntry> getCompIeAnnDiaryEntries() {
        return this.compIeAnnDiaryEntries;
    }

    public void setCompIeAnnDiaryEntries(Set<SpPtlCompIeAnnDiaryEntry> compIeAnnDiaryEntries) {
        this.compIeAnnDiaryEntries = compIeAnnDiaryEntries;
    }
    // */
    /////////////////////////////////////////////////

    @Nullable
    private SpPtlCompIeAnnDiaryEntryDTO[] diaryEntries;

    @Transient
    @Nullable
    public SpPtlCompIeAnnDiaryEntryDTO[] getDiaryEntries() {
        return diaryEntries;
    }

    @Transient
    @Nullable
    public void setDiaryEntries(@Nullable SpPtlCompIeAnnDiaryEntryDTO[] diaryEntries) {
        this.diaryEntries = diaryEntries;
    }

    /////////////////////////////////////////////////
    // /*
    private Set<SpPtlCompIeAnnIeEntry> compIeAnnIeEntries = new HashSet<SpPtlCompIeAnnIeEntry>(0);

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "compIeAnn", cascade = CascadeType.ALL, orphanRemoval = true) // SHOULD BE EQUAL TO ATTRIBUTE NAME OF OTHER CLASS!!!!
    public Set<SpPtlCompIeAnnIeEntry> getCompIeAnnIeEntries() {
        return this.compIeAnnIeEntries;
    }

    public void setCompIeAnnIeEntries(Set<SpPtlCompIeAnnIeEntry> compIeAnnIeEntries) {
        this.compIeAnnIeEntries = compIeAnnIeEntries;
    }
    // */
    /////////////////////////////////////////////////

    @Nullable
    private SpPtlCompIeAnnIeEntryDTO[] ieEntries;

    @Transient
    @Nullable
    public SpPtlCompIeAnnIeEntryDTO[] getIeEntries() {
        return ieEntries;
    }

    @Transient
    @Nullable
    public void setIeEntries(@Nullable SpPtlCompIeAnnIeEntryDTO[] ieEntries) {
        this.ieEntries = ieEntries;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpPtlCompIeAnn that = (SpPtlCompIeAnn) o;

        if (id != that.id) return false;
        if (attachedDocId != null ? !attachedDocId.equals(that.attachedDocId) : that.attachedDocId != null)
            return false;
        if (caseId != null ? !caseId.equals(that.caseId) : that.caseId != null) return false;
        if (categANum != null ? !categANum.equals(that.categANum) : that.categANum != null) return false;
        if (categBNum != null ? !categBNum.equals(that.categBNum) : that.categBNum != null) return false;
        if (categCNum != null ? !categCNum.equals(that.categCNum) : that.categCNum != null) return false;
        if (compAddr != null ? !compAddr.equals(that.compAddr) : that.compAddr != null) return false;
        if (compAddrD != null ? !compAddrD.equals(that.compAddrD) : that.compAddrD != null) return false;
        if (compAddrK != null ? !compAddrK.equals(that.compAddrK) : that.compAddrK != null) return false;
        if (compAddrP != null ? !compAddrP.equals(that.compAddrP) : that.compAddrP != null) return false;
        if (compAddrPe != null ? !compAddrPe.equals(that.compAddrPe) : that.compAddrPe != null) return false;
        if (compAddrTk != null ? !compAddrTk.equals(that.compAddrTk) : that.compAddrTk != null) return false;
        if (compAme != null ? !compAme.equals(that.compAme) : that.compAme != null) return false;
        if (compEbrBranch0Id != null ? !compEbrBranch0Id.equals(that.compEbrBranch0Id) : that.compEbrBranch0Id != null)
            return false;
        if (compEbrBranchId != null ? !compEbrBranchId.equals(that.compEbrBranchId) : that.compEbrBranchId != null)
            return false;
        if (compFullName != null ? !compFullName.equals(that.compFullName) : that.compFullName != null) return false;
        if (compIeAnnPrevId != null ? !compIeAnnPrevId.equals(that.compIeAnnPrevId) : that.compIeAnnPrevId != null)
            return false;
        if (compPhone != null ? !compPhone.equals(that.compPhone) : that.compPhone != null) return false;
        if (compPtlBranchId != null ? !compPtlBranchId.equals(that.compPtlBranchId) : that.compPtlBranchId != null)
            return false;
        if (compTaxNumber != null ? !compTaxNumber.equals(that.compTaxNumber) : that.compTaxNumber != null)
            return false;
        if (companyId != null ? !companyId.equals(that.companyId) : that.companyId != null) return false;
        if (cooperationTypeBasic != null ? !cooperationTypeBasic.equals(that.cooperationTypeBasic) : that.cooperationTypeBasic != null)
            return false;
        if (dateEnd != null ? !dateEnd.equals(that.dateEnd) : that.dateEnd != null) return false;
        if (dateStart != null ? !dateStart.equals(that.dateStart) : that.dateStart != null) return false;
        if (docId != null ? !docId.equals(that.docId) : that.docId != null) return false;
        if (doctorRegrequestId != null ? !doctorRegrequestId.equals(that.doctorRegrequestId) : that.doctorRegrequestId != null)
            return false;
        if (doctorRegrequestUserId != null ? !doctorRegrequestUserId.equals(that.doctorRegrequestUserId) : that.doctorRegrequestUserId != null)
            return false;
        if (ieAfm != null ? !ieAfm.equals(that.ieAfm) : that.ieAfm != null) return false;
        if (ieAnnStatus != null ? !ieAnnStatus.equals(that.ieAnnStatus) : that.ieAnnStatus != null) return false;
        if (ieFullname != null ? !ieFullname.equals(that.ieFullname) : that.ieFullname != null) return false;
        if (ieSpeciality != null ? !ieSpeciality.equals(that.ieSpeciality) : that.ieSpeciality != null) return false;
        if (ieSpecialityOther != null ? !ieSpecialityOther.equals(that.ieSpecialityOther) : that.ieSpecialityOther != null)
            return false;
        if (protDate != null ? !protDate.equals(that.protDate) : that.protDate != null) return false;
        if (protNo != null ? !protNo.equals(that.protNo) : that.protNo != null) return false;
        if (protYear != null ? !protYear.equals(that.protYear) : that.protYear != null) return false;
        if (reqStatus != null ? !reqStatus.equals(that.reqStatus) : that.reqStatus != null) return false;
        if (sepeDept != null ? !sepeDept.equals(that.sepeDept) : that.sepeDept != null) return false;
        if (statementAdjCounty != null ? !statementAdjCounty.equals(that.statementAdjCounty) : that.statementAdjCounty != null)
            return false;
        if (statementDifCounty != null ? !statementDifCounty.equals(that.statementDifCounty) : that.statementDifCounty != null)
            return false;
        if (statementIeSearched != null ? !statementIeSearched.equals(that.statementIeSearched) : that.statementIeSearched != null)
            return false;
        if (subStatus != null ? !subStatus.equals(that.subStatus) : that.subStatus != null) return false;
        if (submitTime != null ? !submitTime.equals(that.submitTime) : that.submitTime != null) return false;
        if (ieReplaceResponse != null ? !ieReplaceResponse.equals(that.ieReplaceResponse) : that.ieReplaceResponse != null) return false;
        if (ieEntries != null ? !ieEntries.equals(that.ieEntries) : that.ieEntries != null) return false;
        if (ieAnnIeTotal != null ? !ieAnnIeTotal.equals(that.ieAnnIeTotal) : that.ieAnnIeTotal != null) return false;
        if (ieAnnIeTotalAccept != null ? !ieAnnIeTotalAccept.equals(that.ieAnnIeTotalAccept) : that.ieAnnIeTotalAccept != null) return false;
        if (ieAnnIeTotalReject != null ? !ieAnnIeTotalReject.equals(that.ieAnnIeTotalReject) : that.ieAnnIeTotalReject != null) return false;
        if (exyppBasic != null ? !exyppBasic.equals(that.exyppBasic) : that.exyppBasic != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));

        result = 31 * result + (companyId != null ? companyId.hashCode() : 0);
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
        result = 31 * result + (cooperationTypeBasic != null ? cooperationTypeBasic.hashCode() : 0);
        result = 31 * result + (doctorRegrequestId != null ? doctorRegrequestId.hashCode() : 0);
        result = 31 * result + (ieFullname != null ? ieFullname.hashCode() : 0);
        result = 31 * result + (ieAfm != null ? ieAfm.hashCode() : 0);
        result = 31 * result + (ieSpeciality != null ? ieSpeciality.hashCode() : 0);
        result = 31 * result + (statementIeSearched != null ? statementIeSearched.hashCode() : 0);
        result = 31 * result + (statementAdjCounty != null ? statementAdjCounty.hashCode() : 0);
        result = 31 * result + (statementDifCounty != null ? statementDifCounty.hashCode() : 0);
        result = 31 * result + (ieAnnStatus != null ? ieAnnStatus.hashCode() : 0);
        result = 31 * result + (ieSpecialityOther != null ? ieSpecialityOther.hashCode() : 0);
        result = 31 * result + (compPtlBranchId != null ? compPtlBranchId.hashCode() : 0);
        result = 31 * result + (doctorRegrequestUserId != null ? doctorRegrequestUserId.hashCode() : 0);
        result = 31 * result + (dateStart != null ? dateStart.hashCode() : 0);
        result = 31 * result + (dateEnd != null ? dateEnd.hashCode() : 0);
        result = 31 * result + (compIeAnnPrevId != null ? compIeAnnPrevId.hashCode() : 0);
        result = 31 * result + (ieReplaceResponse != null ? ieReplaceResponse.hashCode() : 0);
        result = 31 * result + (ieEntries != null ? ieEntries.hashCode() : 0);
        result = 31 * result + (ieAnnIeTotal != null ? ieAnnIeTotal.hashCode() : 0);
        result = 31 * result + (ieAnnIeTotalAccept != null ? ieAnnIeTotalAccept.hashCode() : 0);
        result = 31 * result + (ieAnnIeTotalReject != null ? ieAnnIeTotalReject.hashCode() : 0);
        result = 31 * result + (exyppBasic != null ? exyppBasic.hashCode() : 0);

        return result;
    }
}
