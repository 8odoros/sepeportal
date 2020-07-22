package sepe.domain.company;

import sepe.dto.SpPtlCompSecDiaryContrDTO;
import sepe.dto.SpPtlCompSecDiaryEngDTO;

import javax.annotation.Nullable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Nikos on 6/10/2015.
 */
@Entity
@javax.persistence.Table(name = "SP_PTL_COMP_SEC_DIARY", schema = "SP_PTL", catalog = "")
public class SpPtlCompSecDiary {
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

    private String projLicenceNum;

    @Basic
    @javax.persistence.Column(name = "PROJ_LICENCE_NUM")
    public String getProjLicenceNum() {
        return projLicenceNum;
    }

    public void setProjLicenceNum(String projLicenceNum) {
        this.projLicenceNum = projLicenceNum;
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

    private String projDescr;

    @Basic
    @javax.persistence.Column(name = "PROJ_DESCR")
    public String getProjDescr() {
        return projDescr;
    }

    public void setProjDescr(String projDescr) {
        this.projDescr = projDescr;
    }

    private String ownerFirstname;

    @Basic
    @javax.persistence.Column(name = "OWNER_FIRSTNAME")
    public String getOwnerFirstname() {
        return ownerFirstname;
    }

    public void setOwnerFirstname(String ownerFirstname) {
        this.ownerFirstname = ownerFirstname;
    }

    private String ownerLastname;

    @Basic
    @javax.persistence.Column(name = "OWNER_LASTNAME")
    public String getOwnerLastname() {
        return ownerLastname;
    }

    public void setOwnerLastname(String ownerLastname) {
        this.ownerLastname = ownerLastname;
    }

    private String ownerBirthplace;

    @Basic
    @javax.persistence.Column(name = "OWNER_BIRTHPLACE")
    public String getOwnerBirthplace() {
        return ownerBirthplace;
    }

    public void setOwnerBirthplace(String ownerBirthplace) {
        this.ownerBirthplace = ownerBirthplace;
    }

    private Timestamp ownerBirthdate;

    @Basic
    @javax.persistence.Column(name = "OWNER_BIRTHDATE")
    public Timestamp getOwnerBirthdate() {
        return ownerBirthdate;
    }

    public void setOwnerBirthdate(Timestamp ownerBirthdate) {
        this.ownerBirthdate = ownerBirthdate;
    }

    private Long ownerCardType;

    @Basic
    @javax.persistence.Column(name = "OWNER_CARD_TYPE")
    public Long getOwnerCardType() {
        return ownerCardType;
    }

    public void setOwnerCardType(Long ownerCardType) {
        this.ownerCardType = ownerCardType;
    }

    private String ownerCardNumber;

    @Basic
    @javax.persistence.Column(name = "OWNER_CARD_NUMBER")
    public String getOwnerCardNumber() {
        return ownerCardNumber;
    }

    public void setOwnerCardNumber(String ownerCardNumber) {
        this.ownerCardNumber = ownerCardNumber;
    }

    private String ownerAfm;

    @Basic
    @javax.persistence.Column(name = "OWNER_AFM")
    public String getOwnerAfm() {
        return ownerAfm;
    }

    public void setOwnerAfm(String ownerAfm) {
        this.ownerAfm = ownerAfm;
    }

    private String ownerCardIssuingAuth;

    @Basic
    @javax.persistence.Column(name = "OWNER_CARD_ISSUING_AUTH")
    public String getOwnerCardIssuingAuth() {
        return ownerCardIssuingAuth;
    }

    public void setOwnerCardIssuingAuth(String ownerCardIssuingAuth) {
        this.ownerCardIssuingAuth = ownerCardIssuingAuth;
    }

    private String ownerAddr;

    @Basic
    @javax.persistence.Column(name = "OWNER_ADDR")
    public String getOwnerAddr() {
        return ownerAddr;
    }

    public void setOwnerAddr(String ownerAddr) {
        this.ownerAddr = ownerAddr;
    }

    /////////////////////////////////////////////////
    // /*
    private Set<SpPtlCompSecDiaryContr> compSecDiaryContrs = new HashSet<SpPtlCompSecDiaryContr>(0);

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "compSecDiary_contr", cascade = CascadeType.ALL, orphanRemoval = true)
    // SHOULD BE EQUAL TO ATTRIBUTE NAME OF OTHER CLASS!!!!
    public Set<SpPtlCompSecDiaryContr> getCompSecDiaryContrs() {
        return this.compSecDiaryContrs;
    }

    public void setCompSecDiaryContrs(Set<SpPtlCompSecDiaryContr> compSecDiaryContrs) {
        this.compSecDiaryContrs = compSecDiaryContrs;
    }
    // */
    /////////////////////////////////////////////////

    /////////////////////////////////////////////////
    // /*
    private Set<SpPtlCompSecDiaryEng> compSecDiaryEngs = new HashSet<SpPtlCompSecDiaryEng>(0);

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "compSecDiary_eng", cascade = CascadeType.ALL, orphanRemoval = true)
    // SHOULD BE EQUAL TO ATTRIBUTE NAME OF OTHER CLASS!!!!
    public Set<SpPtlCompSecDiaryEng> getCompSecDiaryEngs() {
        return this.compSecDiaryEngs;
    }

    public void setCompSecDiaryEngs(Set<SpPtlCompSecDiaryEng> compSecDiaryEngs) {
        this.compSecDiaryEngs = compSecDiaryEngs;
    }
    // */
    /////////////////////////////////////////////////

    /////////////////////////////////////////////////
     /*
    private Set<SpPtlCompSecDiaryEntry> compSecDiaryEntries = new HashSet<SpPtlCompSecDiaryEntry>(0);

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "compSecDiary_entry", cascade = CascadeType.ALL, orphanRemoval = true)
    // SHOULD BE EQUAL TO ATTRIBUTE NAME OF OTHER CLASS!!!!
    public Set<SpPtlCompSecDiaryEntry> getCompSecDiaryEntries() {
        return this.compSecDiaryEntries;
    }

    public void setCompSecDiaryEntries(Set<SpPtlCompSecDiaryEntry> compSecDiaryEntries) {
        this.compSecDiaryEntries = compSecDiaryEntries;
    }
     */
    /////////////////////////////////////////////////

    @Nullable
    private SpPtlCompSecDiaryContrDTO[] projscontrs;

    @Transient
    @Nullable
    public SpPtlCompSecDiaryContrDTO[] getProjscontrs() {
        return projscontrs;
    }

    @Transient
    @Nullable
    public void setProjscontrs(@Nullable SpPtlCompSecDiaryContrDTO[] projscontrs) {
        this.projscontrs = projscontrs;
    }

    @Nullable
    private SpPtlCompSecDiaryEngDTO[] projsengs;

    @Transient
    @Nullable
    public SpPtlCompSecDiaryEngDTO[] getProjsengs() {
        return projsengs;
    }

    @Transient
    @Nullable
    public void setProjsengs(@Nullable SpPtlCompSecDiaryEngDTO[] projsengs) {
        this.projsengs = projsengs;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpPtlCompSecDiary that = (SpPtlCompSecDiary) o;

        if (id != that.id) return false;
        if (companyId != that.companyId) return false;
        if (subStatus != null ? !subStatus.equals(that.subStatus) : that.subStatus != null) return false;
        if (reqStatus != null ? !reqStatus.equals(that.reqStatus) : that.reqStatus != null) return false;
        if (protNo != null ? !protNo.equals(that.protNo) : that.protNo != null) return false;
        if (protYear != null ? !protYear.equals(that.protYear) : that.protYear != null) return false;
        if (descr != null ? !descr.equals(that.descr) : that.descr != null) return false;
        if (protDate != null ? !protDate.equals(that.protDate) : that.protDate != null) return false;
        if (submitTime != null ? !submitTime.equals(that.submitTime) : that.submitTime != null) return false;
        if (department != null ? !department.equals(that.department) : that.department != null) return false;
        if (attachedDocId != null ? !attachedDocId.equals(that.attachedDocId) : that.attachedDocId != null)
            return false;
        if (notes != null ? !notes.equals(that.notes) : that.notes != null) return false;
        if (caseId != null ? !caseId.equals(that.caseId) : that.caseId != null) return false;
        if (projLicenceNum != null ? !projLicenceNum.equals(that.projLicenceNum) : that.projLicenceNum != null)
            return false;
        if (projAddrTk != null ? !projAddrTk.equals(that.projAddrTk) : that.projAddrTk != null) return false;
        if (projAddrD != null ? !projAddrD.equals(that.projAddrD) : that.projAddrD != null) return false;
        if (projAddrK != null ? !projAddrK.equals(that.projAddrK) : that.projAddrK != null) return false;
        if (projAddrP != null ? !projAddrP.equals(that.projAddrP) : that.projAddrP != null) return false;
        if (projAddrPe != null ? !projAddrPe.equals(that.projAddrPe) : that.projAddrPe != null) return false;
        if (projAddr != null ? !projAddr.equals(that.projAddr) : that.projAddr != null) return false;
        if (docId != null ? !docId.equals(that.docId) : that.docId != null) return false;
        if (projDescr != null ? !projDescr.equals(that.projDescr) : that.projDescr != null) return false;
        if (ownerFirstname != null ? !ownerFirstname.equals(that.ownerFirstname) : that.ownerFirstname != null)
            return false;
        if (ownerLastname != null ? !ownerLastname.equals(that.ownerLastname) : that.ownerLastname != null)
            return false;
        if (ownerBirthplace != null ? !ownerBirthplace.equals(that.ownerBirthplace) : that.ownerBirthplace != null)
            return false;
        if (ownerBirthdate != null ? !ownerBirthdate.equals(that.ownerBirthdate) : that.ownerBirthdate != null)
            return false;
        if (ownerCardType != null ? !ownerCardType.equals(that.ownerCardType) : that.ownerCardType != null)
            return false;
        if (ownerCardNumber != null ? !ownerCardNumber.equals(that.ownerCardNumber) : that.ownerCardNumber != null)
            return false;
        if (ownerAfm != null ? !ownerAfm.equals(that.ownerAfm) : that.ownerAfm != null) return false;
        if (ownerCardIssuingAuth != null ? !ownerCardIssuingAuth.equals(that.ownerCardIssuingAuth) : that.ownerCardIssuingAuth != null)
            return false;
        if (ownerAddr != null ? !ownerAddr.equals(that.ownerAddr) : that.ownerAddr != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (companyId ^ (companyId >>> 32));
        result = 31 * result + (subStatus != null ? subStatus.hashCode() : 0);
        result = 31 * result + (reqStatus != null ? reqStatus.hashCode() : 0);
        result = 31 * result + (protNo != null ? protNo.hashCode() : 0);
        result = 31 * result + (protYear != null ? protYear.hashCode() : 0);
        result = 31 * result + (descr != null ? descr.hashCode() : 0);
        result = 31 * result + (protDate != null ? protDate.hashCode() : 0);
        result = 31 * result + (submitTime != null ? submitTime.hashCode() : 0);
        result = 31 * result + (department != null ? department.hashCode() : 0);
        result = 31 * result + (attachedDocId != null ? attachedDocId.hashCode() : 0);
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        result = 31 * result + (caseId != null ? caseId.hashCode() : 0);
        result = 31 * result + (projLicenceNum != null ? projLicenceNum.hashCode() : 0);
        result = 31 * result + (projAddrTk != null ? projAddrTk.hashCode() : 0);
        result = 31 * result + (projAddrD != null ? projAddrD.hashCode() : 0);
        result = 31 * result + (projAddrK != null ? projAddrK.hashCode() : 0);
        result = 31 * result + (projAddrP != null ? projAddrP.hashCode() : 0);
        result = 31 * result + (projAddrPe != null ? projAddrPe.hashCode() : 0);
        result = 31 * result + (projAddr != null ? projAddr.hashCode() : 0);
        result = 31 * result + (docId != null ? docId.hashCode() : 0);
        result = 31 * result + (projDescr != null ? projDescr.hashCode() : 0);
        result = 31 * result + (ownerFirstname != null ? ownerFirstname.hashCode() : 0);
        result = 31 * result + (ownerLastname != null ? ownerLastname.hashCode() : 0);
        result = 31 * result + (ownerBirthplace != null ? ownerBirthplace.hashCode() : 0);
        result = 31 * result + (ownerBirthdate != null ? ownerBirthdate.hashCode() : 0);
        result = 31 * result + (ownerCardType != null ? ownerCardType.hashCode() : 0);
        result = 31 * result + (ownerCardNumber != null ? ownerCardNumber.hashCode() : 0);
        result = 31 * result + (ownerAfm != null ? ownerAfm.hashCode() : 0);
        result = 31 * result + (ownerCardIssuingAuth != null ? ownerCardIssuingAuth.hashCode() : 0);
        result = 31 * result + (ownerAddr != null ? ownerAddr.hashCode() : 0);
        return result;
    }
}
