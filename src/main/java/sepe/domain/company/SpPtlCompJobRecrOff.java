package sepe.domain.company;

import sepe.dto.SpPtlCompJobRecrOffPersDTO;

import javax.annotation.Nullable;
import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Nick on 4/29/2015.
 */
@Entity
@Table(name = "SP_PTL_COMP_JOB_RECR_OFF", schema = "SP_PTL", catalog = "")
public class SpPtlCompJobRecrOff {
    private Long id;
    private Long companyId;
    private String compAfm;
    private String compName;
    private String compPhone;
    private String compAddr;
    private String compAddrD;
    private String compAddrK;
    private String compAddrP;
    private String compAddrPe;
    private String compAddrTk;
    private Long compEbrBranch0Id;
    private Long compEbrBranchId;
    private BigInteger requestEmpty;
    private String requestYear;
    private String protNo;
    private Timestamp protDate;
    private Integer protYear;
    private String submitTime;
    private Long caseId;
    private Integer reqStatus;
    private Integer subStatus;
    private Long sepeDept;
    private String requestHalfYear;
    private String notes;
    private Long compDoyId;
    private Long compLegalFormId;
    private String compKad;
    private Integer outdated;
    private Long docId;
    private Long attachedDocId;
    private String attachedDocDescr;
    private Long intermedationNum;

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
    @Column(name = "COMP_AFM")
    public String getCompAfm() {
        return compAfm;
    }

    public void setCompAfm(String compAfm) {
        this.compAfm = compAfm;
    }

    @Basic
    @Column(name = "COMP_NAME")
    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
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
    @Column(name = "REQUEST_EMPTY")
    public BigInteger getRequestEmpty() {
        return requestEmpty;
    }

    public void setRequestEmpty(BigInteger requestEmpty) {
        this.requestEmpty = requestEmpty;
    }

    @Basic
    @Column(name = "REQUEST_YEAR")
    public String getRequestYear() {
        return requestYear;
    }

    public void setRequestYear(String requestYear) {
        this.requestYear = requestYear;
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
    @Column(name = "REQUEST_HALF_YEAR")
    public String getRequestHalfYear() {
        return requestHalfYear;
    }

    public void setRequestHalfYear(String requestHalfYear) {
        this.requestHalfYear = requestHalfYear;
    }

    @Basic
    @Column(name = "NOTES")
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Basic
    @Column(name = "COMP_DOY_ID")
    public Long getCompDoyId() {
        return compDoyId;
    }

    public void setCompDoyId(Long compDoyId) {
        this.compDoyId = compDoyId;
    }

    @Basic
    @Column(name = "COMP_LEGAL_FORM_ID")
    public Long getCompLegalFormId() {
        return compLegalFormId;
    }

    public void setCompLegalFormId(Long compLegalFormId) {
        this.compLegalFormId = compLegalFormId;
    }

    @Basic
    @Column(name = "COMP_KAD")
    public String getCompKad() {
        return compKad;
    }

    public void setCompKad(String compKad) {
        this.compKad = compKad;
    }

    @Basic
    @Column(name = "OUTDATED")
    public Integer getOutdated() {
        return outdated;
    }

    public void setOutdated(Integer outdated) {
        this.outdated = outdated;
    }

    @Basic
    @Column(name = "DOC_ID")
    public Long getDocId() {
        return docId;
    }

    public void setDocId(Long docId) {
        this.docId = docId;
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

    @Basic
    @javax.persistence.Column(name = "ATTACHED_DOC_ID")
    public Long getAttachedDocId() {
        return attachedDocId;
    }

    public void setAttachedDocId(Long attachedDocId) {
        this.attachedDocId = attachedDocId;
    }

    @Basic
    @javax.persistence.Column(name = "ATTACHED_DOC_DESCR")
    public String getAttachedDocDescr() {
        return attachedDocDescr;
    }

    public void setAttachedDocDescr(String attachedDocDescr) {
        this.attachedDocDescr = attachedDocDescr;
    }

    @Basic
    @Column(name = "INTERMEDATION_NUM")
    public Long getIntermedationNum() {
        return intermedationNum;
    }

    public void setIntermedationNum(Long intermedationNum) {
        this.intermedationNum = intermedationNum;
    }

    /////////////////////////////////////////////////
    // /*
    private Set<SpPtlCompJobRecrOffPers> compJobRecrOffPersons = new HashSet<SpPtlCompJobRecrOffPers>(0);

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "compJobRecrOff", cascade = CascadeType.ALL, orphanRemoval = true) // SHOULD BE EQUAL TO ATTRIBUTE NAME OF OTHER CLASS!!!!
    public Set<SpPtlCompJobRecrOffPers> getCompJobRecrOffPersons() {
        return this.compJobRecrOffPersons;
    }

    public void setCompJobRecrOffPersons(Set<SpPtlCompJobRecrOffPers> compJobRecrOffPersons) {
        this.compJobRecrOffPersons = compJobRecrOffPersons;
    }
    // */
    /////////////////////////////////////////////////


    @Nullable
    private SpPtlCompJobRecrOffPersDTO[] pers;

    @Transient
    @Nullable
    public SpPtlCompJobRecrOffPersDTO[] getPers() {
        return pers;
    }

    @Transient
    @Nullable
    public void setPers(@Nullable SpPtlCompJobRecrOffPersDTO[] pers) {
        this.pers = pers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpPtlCompJobRecrOff that = (SpPtlCompJobRecrOff) o;

        if (id != that.id) return false;
        if (compAme != null ? !compAme.equals(that.compAme) : that.compAme != null) return false;
        if (caseId != null ? !caseId.equals(that.caseId) : that.caseId != null) return false;
        if (compAddr != null ? !compAddr.equals(that.compAddr) : that.compAddr != null) return false;
        if (compAddrD != null ? !compAddrD.equals(that.compAddrD) : that.compAddrD != null) return false;
        if (compAddrK != null ? !compAddrK.equals(that.compAddrK) : that.compAddrK != null) return false;
        if (compAddrP != null ? !compAddrP.equals(that.compAddrP) : that.compAddrP != null) return false;
        if (compAddrPe != null ? !compAddrPe.equals(that.compAddrPe) : that.compAddrPe != null) return false;
        if (compAddrTk != null ? !compAddrTk.equals(that.compAddrTk) : that.compAddrTk != null) return false;
        if (compAfm != null ? !compAfm.equals(that.compAfm) : that.compAfm != null) return false;
        if (compDoyId != null ? !compDoyId.equals(that.compDoyId) : that.compDoyId != null) return false;
        if (compEbrBranch0Id != null ? !compEbrBranch0Id.equals(that.compEbrBranch0Id) : that.compEbrBranch0Id != null)
            return false;
        if (compEbrBranchId != null ? !compEbrBranchId.equals(that.compEbrBranchId) : that.compEbrBranchId != null)
            return false;
        if (compKad != null ? !compKad.equals(that.compKad) : that.compKad != null) return false;
        if (compLegalFormId != null ? !compLegalFormId.equals(that.compLegalFormId) : that.compLegalFormId != null)
            return false;
        if (compName != null ? !compName.equals(that.compName) : that.compName != null) return false;
        if (compPhone != null ? !compPhone.equals(that.compPhone) : that.compPhone != null) return false;
        if (companyId != null ? !companyId.equals(that.companyId) : that.companyId != null) return false;
        if (notes != null ? !notes.equals(that.notes) : that.notes != null) return false;
        if (outdated != null ? !outdated.equals(that.outdated) : that.outdated != null) return false;
        if (protDate != null ? !protDate.equals(that.protDate) : that.protDate != null) return false;
        if (protNo != null ? !protNo.equals(that.protNo) : that.protNo != null) return false;
        if (protYear != null ? !protYear.equals(that.protYear) : that.protYear != null) return false;
        if (reqStatus != null ? !reqStatus.equals(that.reqStatus) : that.reqStatus != null) return false;
        if (requestEmpty != null ? !requestEmpty.equals(that.requestEmpty) : that.requestEmpty != null) return false;
        if (requestHalfYear != null ? !requestHalfYear.equals(that.requestHalfYear) : that.requestHalfYear != null)
            return false;
        if (requestYear != null ? !requestYear.equals(that.requestYear) : that.requestYear != null) return false;
        if (sepeDept != null ? !sepeDept.equals(that.sepeDept) : that.sepeDept != null) return false;
        if (subStatus != null ? !subStatus.equals(that.subStatus) : that.subStatus != null) return false;
        if (submitTime != null ? !submitTime.equals(that.submitTime) : that.submitTime != null) return false;
        if (docId != null ? !docId.equals(that.docId) : that.docId != null) return false;
        if (attachedDocId != null ? !attachedDocId.equals(that.attachedDocId) : that.attachedDocId != null)
            return false;
        if (attachedDocDescr != null ? !attachedDocDescr.equals(that.attachedDocDescr) : that.attachedDocDescr != null)
            return false;
        if (intermedationNum != null ? !intermedationNum.equals(that.intermedationNum) : that.intermedationNum != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (companyId != null ? companyId.hashCode() : 0);
        result = 31 * result + (compAfm != null ? compAfm.hashCode() : 0);
        result = 31 * result + (compName != null ? compName.hashCode() : 0);
        result = 31 * result + (compPhone != null ? compPhone.hashCode() : 0);
        result = 31 * result + (compAddr != null ? compAddr.hashCode() : 0);
        result = 31 * result + (compAddrD != null ? compAddrD.hashCode() : 0);
        result = 31 * result + (compAddrK != null ? compAddrK.hashCode() : 0);
        result = 31 * result + (compAddrP != null ? compAddrP.hashCode() : 0);
        result = 31 * result + (compAddrPe != null ? compAddrPe.hashCode() : 0);
        result = 31 * result + (compAddrTk != null ? compAddrTk.hashCode() : 0);
        result = 31 * result + (compEbrBranch0Id != null ? compEbrBranch0Id.hashCode() : 0);
        result = 31 * result + (compEbrBranchId != null ? compEbrBranchId.hashCode() : 0);
        result = 31 * result + (requestEmpty != null ? requestEmpty.hashCode() : 0);
        result = 31 * result + (requestYear != null ? requestYear.hashCode() : 0);
        result = 31 * result + (protNo != null ? protNo.hashCode() : 0);
        result = 31 * result + (protDate != null ? protDate.hashCode() : 0);
        result = 31 * result + (protYear != null ? protYear.hashCode() : 0);
        result = 31 * result + (submitTime != null ? submitTime.hashCode() : 0);
        result = 31 * result + (caseId != null ? caseId.hashCode() : 0);
        result = 31 * result + (reqStatus != null ? reqStatus.hashCode() : 0);
        result = 31 * result + (subStatus != null ? subStatus.hashCode() : 0);
        result = 31 * result + (sepeDept != null ? sepeDept.hashCode() : 0);
        result = 31 * result + (requestHalfYear != null ? requestHalfYear.hashCode() : 0);
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        result = 31 * result + (compDoyId != null ? compDoyId.hashCode() : 0);
        result = 31 * result + (compLegalFormId != null ? compLegalFormId.hashCode() : 0);
        result = 31 * result + (compKad != null ? compKad.hashCode() : 0);
        result = 31 * result + (outdated != null ? outdated.hashCode() : 0);
        result = 31 * result + (docId != null ? docId.hashCode() : 0);
        result = 31 * result + (compAme != null ? compAme.hashCode() : 0);
        result = 31 * result + (attachedDocId != null ? attachedDocId.hashCode() : 0);
        result = 31 * result + (attachedDocDescr != null ? attachedDocDescr.hashCode() : 0);
        result = 31 * result + (intermedationNum != null ? intermedationNum.hashCode() : 0);
        return result;
    }
}
