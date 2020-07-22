package sepe.domain.company;

import sepe.dto.SpPtlCompProjAnnContrDTO;
import sepe.dto.SpPtlCompProjAnnEngDTO;
import sepe.dto.SpPtlCompProjAnnSelfEmplDTO;
import sepe.dto.SpPtlCompProjAnnStudierDTO;

import javax.annotation.Nullable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Id;

/**
 * Created by Nikos on 4/19/2015.
 */

@Entity
@javax.persistence.Table(name = "SP_PTL_COMP_PROJ_ANN", schema = "SP_PTL", catalog = "")
public class SpPtlCompProjAnn {

    protected  Long id;

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
    @javax.persistence.Column(name = "USER_ID")
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    private Integer reqStatus;

    @Basic
    @javax.persistence.Column(name = "REQ_STATUS")
    public Integer getReqStatus() {
        return reqStatus;
    }

    public void setReqStatus(Integer reqStatus) {
        this.reqStatus = reqStatus;
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


    private String amoe;

    @Basic
    @javax.persistence.Column(name = "AMOE")
    public String getAmoe() {
        return amoe;
    }

    public void setAmoe(String amoe) {
        this.amoe = amoe;
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

    private String descr;

    @Basic
    @javax.persistence.Column(name = "DESCR")
    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
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

    private String submitTime;

    @Basic
    @javax.persistence.Column(name = "SUBMIT_TIME")
    public String getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(String submitTime) {
        this.submitTime = submitTime;
    }

    private Long department;

    @Basic
    @javax.persistence.Column(name = "DEPARTMENT")
    public Long getDepartment() {
        return department;
    }

    public void setDepartment(Long department) {
        this.department = department;
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

    private String notes;

    @Basic
    @javax.persistence.Column(name = "NOTES")
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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

    private String maxEmployeeNum;

    @Basic
    @javax.persistence.Column(name = "MAX_EMPLOYEE_NUM")
    public String getMaxEmployeeNum() {
        return maxEmployeeNum;
    }

    public void setMaxEmployeeNum(String maxEmployeeNum) {
        this.maxEmployeeNum = maxEmployeeNum;
    }

    private String projDuration;

    @Basic
    @javax.persistence.Column(name = "PROJ_DURATION")
    public String getProjDuration() {
        return projDuration;
    }

    public void setProjDuration(String projDuration) {
        this.projDuration = projDuration;
    }

    private Timestamp projStartDate;

    @Basic
    @javax.persistence.Column(name = "PROJ_START_DATE")
    public Timestamp getProjStartDate() {
        return projStartDate;
    }

    public void setProjStartDate(Timestamp projStartDate) {
        this.projStartDate = projStartDate;
    }

    private String subcontractorNum;

    @Basic
    @javax.persistence.Column(name = "SUBCONTRACTOR_NUM")
    public String getSubcontractorNum() {
        return subcontractorNum;
    }

    public void setSubcontractorNum(String subcontractorNum) {
        this.subcontractorNum = subcontractorNum;
    }

    private Integer projType;

    @Basic
    @javax.persistence.Column(name = "PROJ_TYPE")
    public Integer getProjType() {
        return projType;
    }

    public void setProjType(Integer projType) {
        this.projType = projType;
    }

    private String licenceNum;

    @Basic
    @javax.persistence.Column(name = "LICENCE_NUM")
    public String getLicenceNum() {
        return licenceNum;
    }

    public void setLicenceNum(String licenceNum) {
        this.licenceNum = licenceNum;
    }

    private String projAddrTk;

    @Basic
    @javax.persistence.Column(name = "PROJ_ADDR_TK")
    public String getProjAddrTk() {
        return projAddrTk;
    }

    public void setProjAddrTk(String projAddrTk) {
        this.projAddrTk = projAddrTk;
    }

    private String projAddrD;

    @Basic
    @javax.persistence.Column(name = "PROJ_ADDR_D")
    public String getProjAddrD() {
        return projAddrD;
    }

    public void setProjAddrD(String projAddrD) {
        this.projAddrD = projAddrD;
    }

    private String projAddrK;

    @Basic
    @javax.persistence.Column(name = "PROJ_ADDR_K")
    public String getProjAddrK() {
        return projAddrK;
    }

    public void setProjAddrK(String projAddrK) {
        this.projAddrK = projAddrK;
    }

    private String projAddrP;

    @Basic
    @javax.persistence.Column(name = "PROJ_ADDR_P")
    public String getProjAddrP() {
        return projAddrP;
    }

    public void setProjAddrP(String projAddrP) {
        this.projAddrP = projAddrP;
    }

    private String projAddrPe;

    @Basic
    @javax.persistence.Column(name = "PROJ_ADDR_PE")
    public String getProjAddrPe() {
        return projAddrPe;
    }

    public void setProjAddrPe(String projAddrPe) {
        this.projAddrPe = projAddrPe;
    }

    private String projAddr;

    @Basic
    @javax.persistence.Column(name = "PROJ_ADDR")
    public String getProjAddr() {
        return projAddr;
    }

    public void setProjAddr(String projAddr) {
        this.projAddr = projAddr;
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

    private String compAme;

    @Basic
    @javax.persistence.Column(name = "COMP_AME")
    public String getCompAme() {
        return compAme;
    }

    public void setCompAme(String compAme) {
        this.compAme = compAme;
    }

    /////////////////////////////////////////////////
    // /*
    private Set<SpPtlCompProjAnnContr> projAnnContrs = new HashSet<SpPtlCompProjAnnContr>(0);

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "compProjAnn_contr", cascade = CascadeType.ALL, orphanRemoval = true)
    // SHOULD BE EQUAL TO ATTRIBUTE NAME OF OTHER CLASS!!!!
    public Set<SpPtlCompProjAnnContr> getProjAnnContrs() {
        return this.projAnnContrs;
    }

    public void setProjAnnContrs(Set<SpPtlCompProjAnnContr> projAnnContrs) {
        this.projAnnContrs = projAnnContrs;
    }
    // */
    /////////////////////////////////////////////////

    /////////////////////////////////////////////////
    // /*
    private Set<SpPtlCompProjAnnEng> projAnnEngs = new HashSet<SpPtlCompProjAnnEng>(0);

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "compProjAnn_eng", cascade = CascadeType.ALL, orphanRemoval = true)
    // SHOULD BE EQUAL TO ATTRIBUTE NAME OF OTHER CLASS!!!!
    public Set<SpPtlCompProjAnnEng> getProjAnnEngs() {
        return this.projAnnEngs;
    }

    public void setProjAnnEngs(Set<SpPtlCompProjAnnEng> projAnnEngs) {
        this.projAnnEngs = projAnnEngs;
    }
    // */
    /////////////////////////////////////////////////

    /////////////////////////////////////////////////
    // /*
    private Set<SpPtlCompProjAnnSelfempl> projAnnSelfempls = new HashSet<SpPtlCompProjAnnSelfempl>(0);

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "compProjAnn_selfempl", cascade = CascadeType.ALL, orphanRemoval = true)
    // SHOULD BE EQUAL TO ATTRIBUTE NAME OF OTHER CLASS!!!!
    public Set<SpPtlCompProjAnnSelfempl> getProjAnnSelfempls() {
        return this.projAnnSelfempls;
    }

    public void setProjAnnSelfempls(Set<SpPtlCompProjAnnSelfempl> projAnnSelfempls) {
        this.projAnnSelfempls = projAnnSelfempls;
    }
    // */
    /////////////////////////////////////////////////

    /////////////////////////////////////////////////
    // /*
    private Set<SpPtlCompProjAnnStudier> projAnnStudiers = new HashSet<SpPtlCompProjAnnStudier>(0);

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "compProjAnn_studier", cascade = CascadeType.ALL, orphanRemoval = true)
    // SHOULD BE EQUAL TO ATTRIBUTE NAME OF OTHER CLASS!!!!
    public Set<SpPtlCompProjAnnStudier> getProjAnnStudiers() {
        return this.projAnnStudiers;
    }

    public void setProjAnnStudiers(Set<SpPtlCompProjAnnStudier> projAnnStudiers) {
        this.projAnnStudiers = projAnnStudiers;
    }

    // */
    /////////////////////////////////////////////////
    @Nullable
    private SpPtlCompProjAnnContrDTO[] projscontrs;

    @Transient
    @Nullable
    public SpPtlCompProjAnnContrDTO[] getProjscontrs() {
        return projscontrs;
    }

    @Transient
    @Nullable
    public void setProjscontrs(@Nullable SpPtlCompProjAnnContrDTO[] projscontrs) {
        this.projscontrs = projscontrs;
    }

    @Nullable
    private SpPtlCompProjAnnStudierDTO[] projsstudiers;

    @Transient
    @Nullable
    public SpPtlCompProjAnnStudierDTO[] getProjsstudiers() {
        return projsstudiers;
    }

    @Transient
    @Nullable
    public void setProjsstudiers(@Nullable SpPtlCompProjAnnStudierDTO[] projsstudiers) {
        this.projsstudiers = projsstudiers;
    }

    @Nullable
    private SpPtlCompProjAnnEngDTO[] projsengs;

    @Transient
    @Nullable
    public SpPtlCompProjAnnEngDTO[] getProjsengs() {
        return projsengs;
    }

    @Transient
    @Nullable
    public void setProjsengs(@Nullable SpPtlCompProjAnnEngDTO[] projsengs) {
        this.projsengs = projsengs;
    }

    @Nullable
    private SpPtlCompProjAnnSelfEmplDTO[] projsselfempls;

    @Transient
    @Nullable
    public SpPtlCompProjAnnSelfEmplDTO[] getProjsselfempls() {
        return projsselfempls;
    }

    @Transient
    @Nullable
    public void setProjsselfempls(@Nullable SpPtlCompProjAnnSelfEmplDTO[] projsselfempls) {
        this.projsselfempls = projsselfempls;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpPtlCompProjAnn that = (SpPtlCompProjAnn) o;

        if (id != that.id) return false;
        if (compAme != null ? !compAme.equals(that.compAme) : that.compAme != null) return false;
        if (amoe != null ? !amoe.equals(that.amoe) : that.amoe != null) return false;
        if (attachedDocId != null ? !attachedDocId.equals(that.attachedDocId) : that.attachedDocId != null)
            return false;
        if (caseId != null ? !caseId.equals(that.caseId) : that.caseId != null) return false;
        if (department != null ? !department.equals(that.department) : that.department != null) return false;
        if (descr != null ? !descr.equals(that.descr) : that.descr != null) return false;
        if (docId != null ? !docId.equals(that.docId) : that.docId != null) return false;
        if (licenceNum != null ? !licenceNum.equals(that.licenceNum) : that.licenceNum != null) return false;
        if (maxEmployeeNum != null ? !maxEmployeeNum.equals(that.maxEmployeeNum) : that.maxEmployeeNum != null)
            return false;
        if (notes != null ? !notes.equals(that.notes) : that.notes != null) return false;
        if (projAddr != null ? !projAddr.equals(that.projAddr) : that.projAddr != null) return false;
        if (projAddrD != null ? !projAddrD.equals(that.projAddrD) : that.projAddrD != null) return false;
        if (projAddrK != null ? !projAddrK.equals(that.projAddrK) : that.projAddrK != null) return false;
        if (projAddrP != null ? !projAddrP.equals(that.projAddrP) : that.projAddrP != null) return false;
        if (projAddrPe != null ? !projAddrPe.equals(that.projAddrPe) : that.projAddrPe != null) return false;
        if (projAddrTk != null ? !projAddrTk.equals(that.projAddrTk) : that.projAddrTk != null) return false;
        if (projDuration != null ? !projDuration.equals(that.projDuration) : that.projDuration != null) return false;
        if (projStartDate != null ? !projStartDate.equals(that.projStartDate) : that.projStartDate != null)
            return false;
        if (projType != null ? !projType.equals(that.projType) : that.projType != null) return false;
        if (protDate != null ? !protDate.equals(that.protDate) : that.protDate != null) return false;
        if (protNo != null ? !protNo.equals(that.protNo) : that.protNo != null) return false;
        if (protYear != null ? !protYear.equals(that.protYear) : that.protYear != null) return false;
        if (reqStatus != null ? !reqStatus.equals(that.reqStatus) : that.reqStatus != null) return false;
        if (subStatus != null ? !subStatus.equals(that.subStatus) : that.subStatus != null) return false;
        if (subcontractorNum != null ? !subcontractorNum.equals(that.subcontractorNum) : that.subcontractorNum != null)
            return false;
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
        result = 31 * result + (amoe != null ? amoe.hashCode() : 0);
        result = 31 * result + (protYear != null ? protYear.hashCode() : 0);
        result = 31 * result + (descr != null ? descr.hashCode() : 0);
        result = 31 * result + (protDate != null ? protDate.hashCode() : 0);
        result = 31 * result + (submitTime != null ? submitTime.hashCode() : 0);
        result = 31 * result + (department != null ? department.hashCode() : 0);
        result = 31 * result + (attachedDocId != null ? attachedDocId.hashCode() : 0);
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        result = 31 * result + (caseId != null ? caseId.hashCode() : 0);
        result = 31 * result + (maxEmployeeNum != null ? maxEmployeeNum.hashCode() : 0);
        result = 31 * result + (projDuration != null ? projDuration.hashCode() : 0);
        result = 31 * result + (projStartDate != null ? projStartDate.hashCode() : 0);
        result = 31 * result + (subcontractorNum != null ? subcontractorNum.hashCode() : 0);
        result = 31 * result + (projType != null ? projType.hashCode() : 0);
        result = 31 * result + (licenceNum != null ? licenceNum.hashCode() : 0);
        result = 31 * result + (projAddrTk != null ? projAddrTk.hashCode() : 0);
        result = 31 * result + (projAddrD != null ? projAddrD.hashCode() : 0);
        result = 31 * result + (projAddrK != null ? projAddrK.hashCode() : 0);
        result = 31 * result + (projAddrP != null ? projAddrP.hashCode() : 0);
        result = 31 * result + (projAddrPe != null ? projAddrPe.hashCode() : 0);
        result = 31 * result + (projAddr != null ? projAddr.hashCode() : 0);
        result = 31 * result + (docId != null ? docId.hashCode() : 0);
        result = 31 * result + (compAme != null ? compAme.hashCode() : 0);
        return result;
    }
}
