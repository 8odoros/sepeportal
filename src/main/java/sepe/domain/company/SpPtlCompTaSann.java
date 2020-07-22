package sepe.domain.company;

import sepe.dto.SpPtlCompTaSannContrDTO;
import sepe.dto.SpPtlCompTaSannDiaryEntryDTO;

import javax.annotation.Nullable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Nikos on 6/13/2015.
 */
@Entity
@javax.persistence.Table(name = "SP_PTL_COMP_TA_SANN", schema = "SP_PTL", catalog = "")
public class SpPtlCompTaSann {
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

    private Long companyId;

    @Basic
    @javax.persistence.Column(name = "COMPANY_ID", nullable = false, insertable = true, updatable = true, precision = 0)
    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    private String compTaxNumber;

    @Basic
    @javax.persistence.Column(name = "COMP_TAX_NUMBER", nullable = true, insertable = true, updatable = true, length = 128)
    public String getCompTaxNumber() {
        return compTaxNumber;
    }

    public void setCompTaxNumber(String compTaxNumber) {
        this.compTaxNumber = compTaxNumber;
    }

    private String compFullName;

    @Basic
    @javax.persistence.Column(name = "COMP_FULL_NAME", nullable = true, insertable = true, updatable = true, length = 300)
    public String getCompFullName() {
        return compFullName;
    }

    public void setCompFullName(String compFullName) {
        this.compFullName = compFullName;
    }

    private String compPhone;

    @Basic
    @javax.persistence.Column(name = "COMP_PHONE", nullable = true, insertable = true, updatable = true, length = 128)
    public String getCompPhone() {
        return compPhone;
    }

    public void setCompPhone(String compPhone) {
        this.compPhone = compPhone;
    }

    private String compAddr;

    @Basic
    @javax.persistence.Column(name = "COMP_ADDR", nullable = true, insertable = true, updatable = true, length = 50)
    public String getCompAddr() {
        return compAddr;
    }

    public void setCompAddr(String compAddr) {
        this.compAddr = compAddr;
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

    private String compAddrTk;

    @Basic
    @javax.persistence.Column(name = "COMP_ADDR_TK", nullable = true, insertable = true, updatable = true, length = 50)
    public String getCompAddrTk() {
        return compAddrTk;
    }

    public void setCompAddrTk(String compAddrTk) {
        this.compAddrTk = compAddrTk;
    }

    private Long compEbrBranch0Id;

    @Basic
    @javax.persistence.Column(name = "COMP_EBR_BRANCH0_ID", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getCompEbrBranch0Id() {
        return compEbrBranch0Id;
    }

    public void setCompEbrBranch0Id(Long compEbrBranch0Id) {
        this.compEbrBranch0Id = compEbrBranch0Id;
    }

    private Long compEbrBranchId;

    @Basic
    @javax.persistence.Column(name = "COMP_EBR_BRANCH_ID", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getCompEbrBranchId() {
        return compEbrBranchId;
    }

    public void setCompEbrBranchId(Long compEbrBranchId) {
        this.compEbrBranchId = compEbrBranchId;
    }

    private Long attachedDocId;

    @Basic
    @javax.persistence.Column(name = "ATTACHED_DOC_ID", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getAttachedDocId() {
        return attachedDocId;
    }

    public void setAttachedDocId(Long attachedDocId) {
        this.attachedDocId = attachedDocId;
    }

    private Integer docId;

    @Basic
    @javax.persistence.Column(name = "DOC_ID", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getDocId() {
        return docId;
    }

    public void setDocId(Integer docId) {
        this.docId = docId;
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

    private Timestamp protDate;

    @Basic
    @javax.persistence.Column(name = "PROT_DATE", nullable = true, insertable = true, updatable = true)
    public Timestamp getProtDate() {
        return protDate;
    }

    public void setProtDate(Timestamp protDate) {
        this.protDate = protDate;
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

    private String submitTime;

    @Basic
    @javax.persistence.Column(name = "SUBMIT_TIME", nullable = true, insertable = true, updatable = true, length = 50)
    public String getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(String submitTime) {
        this.submitTime = submitTime;
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

    private Integer reqStatus;

    @Basic
    @javax.persistence.Column(name = "REQ_STATUS", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getReqStatus() {
        return reqStatus;
    }

    public void setReqStatus(Integer reqStatus) {
        this.reqStatus = reqStatus;
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

    private Long sepeDept;

    @Basic
    @javax.persistence.Column(name = "SEPE_DEPT", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getSepeDept() {
        return sepeDept;
    }

    public void setSepeDept(Long sepeDept) {
        this.sepeDept = sepeDept;
    }

    private String compAme;

    @Basic
    @javax.persistence.Column(name = "COMP_AME", nullable = true, insertable = true, updatable = true, length = 50)
    public String getCompAme() {
        return compAme;
    }

    public void setCompAme(String compAme) {
        this.compAme = compAme;
    }

    private Long cooperationType;

    @Basic
    @javax.persistence.Column(name = "COOPERATION_TYPE", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getCooperationType() {
        return cooperationType;
    }

    public void setCooperationType(Long cooperationType) {
        this.cooperationType = cooperationType;
    }

    private Long technicianRegrequestId;

    @Basic
    @javax.persistence.Column(name = "TECHNICIAN_REGREQUEST_ID", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getTechnicianRegrequestId() {
        return technicianRegrequestId;
    }

    public void setTechnicianRegrequestId(Long technicianRegrequestId) {
        this.technicianRegrequestId = technicianRegrequestId;
    }

    private String taFullname;

    @Basic
    @javax.persistence.Column(name = "TA_FULLNAME", nullable = true, insertable = true, updatable = true, length = 200)
    public String getTaFullname() {
        return taFullname;
    }

    public void setTaFullname(String taFullname) {
        this.taFullname = taFullname;
    }

    private String taAfm;

    @Basic
    @javax.persistence.Column(name = "TA_AFM", nullable = true, insertable = true, updatable = true, length = 50)
    public String getTaAfm() {
        return taAfm;
    }

    public void setTaAfm(String taAfm) {
        this.taAfm = taAfm;
    }

    private String taSpeciality;

    @Basic
    @javax.persistence.Column(name = "TA_SPECIALITY", nullable = true, insertable = true, updatable = true, precision = 0)
    public String getTaSpeciality() {
        return taSpeciality;
    }

    public void setTaSpeciality(String taSpeciality) {
        this.taSpeciality = taSpeciality;
    }

    private Integer taSannStatus;

    @Basic
    @javax.persistence.Column(name = "TA_SANN_STATUS", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getTaSannStatus() {
        return taSannStatus;
    }

    public void setTaSannStatus(Integer taSannStatus) {
        this.taSannStatus = taSannStatus;
    }

    private String taSpecialityOther;

    @Basic
    @javax.persistence.Column(name = "TA_SPECIALITY_OTHER", nullable = true, insertable = true, updatable = true, length = 300)
    public String getTaSpecialityOther() {
        return taSpecialityOther;
    }

    public void setTaSpecialityOther(String taSpecialityOther) {
        this.taSpecialityOther = taSpecialityOther;
    }

    private Long compShipId;

    @Basic
    @javax.persistence.Column(name = "COMP_SHIP_ID", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getCompShipId() {
        return compShipId;
    }

    public void setCompShipId(Long compShipId) {
        this.compShipId = compShipId;
    }

    private Long technicianRegrequestUserId;

    @Basic
    @javax.persistence.Column(name = "TECHNICIAN_REGREQUEST_USER_ID", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getTechnicianRegrequestUserId() {
        return technicianRegrequestUserId;
    }

    public void setTechnicianRegrequestUserId(Long technicianRegrequestUserId) {
        this.technicianRegrequestUserId = technicianRegrequestUserId;
    }

    private Timestamp dateStart;

    @Basic
    @javax.persistence.Column(name = "DATE_START", nullable = true, insertable = true, updatable = true)
    public Timestamp getDateStart() {
        return dateStart;
    }

    public void setDateStart(Timestamp dateStart) {
        this.dateStart = dateStart;
    }

    private Timestamp dateEnd;

    @Basic
    @javax.persistence.Column(name = "DATE_END", nullable = true, insertable = true, updatable = true)
    public Timestamp getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Timestamp dateEnd) {
        this.dateEnd = dateEnd;
    }

    private Long compTaSannPrevId;

    @Basic
    @javax.persistence.Column(name = "COMP_TA_SANN_PREV_ID", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getCompTaSannPrevId() {
        return compTaSannPrevId;
    }

    public void setCompTaSannPrevId(Long compTaSannPrevId) {
        this.compTaSannPrevId = compTaSannPrevId;
    }

    private String compRepresentativeId;

    @Basic
    @javax.persistence.Column(name = "COMP_REPRESENTATIVE_ID", nullable = true, insertable = true, updatable = true, length = 20)
    public String getCompRepresentativeId() {
        return compRepresentativeId;
    }

    public void setCompRepresentativeId(String compRepresentativeId) {
        this.compRepresentativeId = compRepresentativeId;
    }

    private Integer taResponseReplace;

    @Basic
    @javax.persistence.Column(name = "TA_RESPONSE_REPLACE", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getTaResponseReplace() {
        return taResponseReplace;
    }

    public void setTaResponseReplace(Integer taResponseReplace) {
        this.taResponseReplace = taResponseReplace;
    }

    private String projAddr;

    @Basic
    @javax.persistence.Column(name = "PROJ_ADDR", nullable = true, insertable = true, updatable = true, length = 20)
    public String getProjAddr() {
        return projAddr;
    }

    public void setProjAddr(String projAddr) {
        this.projAddr = projAddr;
    }

    private String projAddrD;

    @Basic
    @javax.persistence.Column(name = "PROJ_ADDR_D", nullable = true, insertable = true, updatable = true, length = 100)
    public String getProjAddrD() {
        return projAddrD;
    }

    public void setProjAddrD(String projAddrD) {
        this.projAddrD = projAddrD;
    }

    private String projAddrK;

    @Basic
    @javax.persistence.Column(name = "PROJ_ADDR_K", nullable = true, insertable = true, updatable = true, length = 100)
    public String getProjAddrK() {
        return projAddrK;
    }

    public void setProjAddrK(String projAddrK) {
        this.projAddrK = projAddrK;
    }

    private String projAddrP;

    @Basic
    @javax.persistence.Column(name = "PROJ_ADDR_P", nullable = true, insertable = true, updatable = true, length = 100)
    public String getProjAddrP() {
        return projAddrP;
    }

    public void setProjAddrP(String projAddrP) {
        this.projAddrP = projAddrP;
    }

    private String projAddrPe;

    @Basic
    @javax.persistence.Column(name = "PROJ_ADDR_PE", nullable = true, insertable = true, updatable = true, length = 100)
    public String getProjAddrPe() {
        return projAddrPe;
    }

    public void setProjAddrPe(String projAddrPe) {
        this.projAddrPe = projAddrPe;
    }

    private String projAddrTk;

    @Basic
    @javax.persistence.Column(name = "PROJ_ADDR_TK", nullable = true, insertable = true, updatable = true, length = 100)
    public String getProjAddrTk() {
        return projAddrTk;
    }

    public void setProjAddrTk(String projAddrTk) {
        this.projAddrTk = projAddrTk;
    }

    private Long portAuthority;

    @Basic
    @javax.persistence.Column(name = "PORT_AUTHORITY", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getPortAuthority() {
        return portAuthority;
    }

    public void setPortAuthority(Long portAuthority) {
        this.portAuthority = portAuthority;
    }

    private Integer shipDangerZone;

    @Basic
    @javax.persistence.Column(name = "SHIP_DANGER_ZONE", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getShipDangerZone() {
        return shipDangerZone;
    }

    public void setShipDangerZone(Integer shipDangerZone) {
        this.shipDangerZone = shipDangerZone;
    }

    private Long shipyard;

    @Basic
    @javax.persistence.Column(name = "SHIPYARD", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getShipyard() {
        return shipyard;
    }

    public void setShipyard(Long shipyard) {
        this.shipyard = shipyard;
    }

    private String projDuration;

    @Basic
    @javax.persistence.Column(name = "PROJ_DURATION", nullable = true, insertable = true, updatable = true, length = 20)
    public String getProjDuration() {
        return projDuration;
    }

    public void setProjDuration(String projDuration) {
        this.projDuration = projDuration;
    }

    private Timestamp projStartDate;

    @Basic
    @javax.persistence.Column(name = "PROJ_START_DATE", nullable = true, insertable = true, updatable = true)
    public Timestamp getProjStartDate() {
        return projStartDate;
    }

    public void setProjStartDate(Timestamp projStartDate) {
        this.projStartDate = projStartDate;
    }

    private String projEmplNum;

    @Basic
    @javax.persistence.Column(name = "PROJ_EMPL_NUM", nullable = true, insertable = true, updatable = true, length = 20)
    public String getProjEmplNum() {
        return projEmplNum;
    }

    public void setProjEmplNum(String projEmplNum) {
        this.projEmplNum = projEmplNum;
    }

    private String compShipName;

    @Basic
    @Column(name = "COMP_SHIP_NAME", nullable = true, insertable = true, updatable = true, length = 200)
    public String getCompShipName() {
        return compShipName;
    }

    public void setCompShipName(String compShipName) {
        this.compShipName = compShipName;
    }

    private String compShipImo;

    @Basic
    @Column(name = "COMP_SHIP_IMO", nullable = true, insertable = true, updatable = true, length = 200)
    public String getCompShipImo() {
        return compShipImo;
    }

    public void setCompShipImo(String compShipImo) {
        this.compShipImo = compShipImo;
    }

    /////////////////////////////////////////////////
    // /*
    private Set<SpPtlCompTaSannDiaryEntr> compTaSannDiaryEntries = new HashSet<SpPtlCompTaSannDiaryEntr>(0);

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "compTaSann", cascade = CascadeType.ALL, orphanRemoval = true) // SHOULD BE EQUAL TO ATTRIBUTE NAME OF OTHER CLASS!!!!
    public Set<SpPtlCompTaSannDiaryEntr> getCompTaSannDiaryEntries() {
        return this.compTaSannDiaryEntries;
    }

    public void setCompTaSannDiaryEntries(Set<SpPtlCompTaSannDiaryEntr> compTaSannDiaryEntries) {
        this.compTaSannDiaryEntries = compTaSannDiaryEntries;
    }
    //*/
    /////////////////////////////////////////////////

    /////////////////////////////////////////////////
    // /*
    private Set<SpPtlCompTaSannContr> compTaSannContrs = new HashSet<SpPtlCompTaSannContr>(0);

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "compTaSann_contr", cascade = CascadeType.ALL, orphanRemoval = true) // SHOULD BE EQUAL TO ATTRIBUTE NAME OF OTHER CLASS!!!!
    public Set<SpPtlCompTaSannContr> getCompTaSannContrs() {
        return this.compTaSannContrs;
    }

    public void setCompTaSannContrs(Set<SpPtlCompTaSannContr> compTaSannContrs) {
        this.compTaSannContrs = compTaSannContrs;
    }
    //*/
    /////////////////////////////////////////////////


    @Nullable
    private SpPtlCompTaSannDiaryEntryDTO[] diaryEntries;

    @Transient
    @Nullable
    public SpPtlCompTaSannDiaryEntryDTO[] getDiaryEntries() {
        return diaryEntries;
    }

    @Transient
    @Nullable
    public void setDiaryEntries(@Nullable SpPtlCompTaSannDiaryEntryDTO[] diaryEntries) {
        this.diaryEntries = diaryEntries;
    }


    @Nullable
    private SpPtlCompTaSannContrDTO[] projscontrs;

    @Transient
    @Nullable
    public SpPtlCompTaSannContrDTO[] getProjscontrs() {
        return projscontrs;
    }

    @Transient
    @Nullable
    public void setProjscontrs(@Nullable SpPtlCompTaSannContrDTO[] projscontrs) {
        this.projscontrs = projscontrs;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpPtlCompTaSann that = (SpPtlCompTaSann) o;

        if (companyId != that.companyId) return false;
        if (id != that.id) return false;
        if (attachedDocId != null ? !attachedDocId.equals(that.attachedDocId) : that.attachedDocId != null)
            return false;
        if (caseId != null ? !caseId.equals(that.caseId) : that.caseId != null) return false;
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
        if (compPhone != null ? !compPhone.equals(that.compPhone) : that.compPhone != null) return false;
        if (compRepresentativeId != null ? !compRepresentativeId.equals(that.compRepresentativeId) : that.compRepresentativeId != null)
            return false;
        if (compShipId != null ? !compShipId.equals(that.compShipId) : that.compShipId != null) return false;
        if (compTaSannPrevId != null ? !compTaSannPrevId.equals(that.compTaSannPrevId) : that.compTaSannPrevId != null)
            return false;
        if (compTaxNumber != null ? !compTaxNumber.equals(that.compTaxNumber) : that.compTaxNumber != null)
            return false;
        if (cooperationType != null ? !cooperationType.equals(that.cooperationType) : that.cooperationType != null)
            return false;
        if (dateEnd != null ? !dateEnd.equals(that.dateEnd) : that.dateEnd != null) return false;
        if (dateStart != null ? !dateStart.equals(that.dateStart) : that.dateStart != null) return false;
        if (docId != null ? !docId.equals(that.docId) : that.docId != null) return false;
        if (portAuthority != null ? !portAuthority.equals(that.portAuthority) : that.portAuthority != null)
            return false;
        if (projAddr != null ? !projAddr.equals(that.projAddr) : that.projAddr != null) return false;
        if (projAddrD != null ? !projAddrD.equals(that.projAddrD) : that.projAddrD != null) return false;
        if (projAddrK != null ? !projAddrK.equals(that.projAddrK) : that.projAddrK != null) return false;
        if (projAddrP != null ? !projAddrP.equals(that.projAddrP) : that.projAddrP != null) return false;
        if (projAddrPe != null ? !projAddrPe.equals(that.projAddrPe) : that.projAddrPe != null) return false;
        if (projAddrTk != null ? !projAddrTk.equals(that.projAddrTk) : that.projAddrTk != null) return false;
        if (projDuration != null ? !projDuration.equals(that.projDuration) : that.projDuration != null) return false;
        if (projEmplNum != null ? !projEmplNum.equals(that.projEmplNum) : that.projEmplNum != null) return false;
        if (projStartDate != null ? !projStartDate.equals(that.projStartDate) : that.projStartDate != null)
            return false;
        if (protDate != null ? !protDate.equals(that.protDate) : that.protDate != null) return false;
        if (protNo != null ? !protNo.equals(that.protNo) : that.protNo != null) return false;
        if (protYear != null ? !protYear.equals(that.protYear) : that.protYear != null) return false;
        if (reqStatus != null ? !reqStatus.equals(that.reqStatus) : that.reqStatus != null) return false;
        if (sepeDept != null ? !sepeDept.equals(that.sepeDept) : that.sepeDept != null) return false;
        if (shipDangerZone != null ? !shipDangerZone.equals(that.shipDangerZone) : that.shipDangerZone != null)
            return false;
        if (shipyard != null ? !shipyard.equals(that.shipyard) : that.shipyard != null) return false;
        if (subStatus != null ? !subStatus.equals(that.subStatus) : that.subStatus != null) return false;
        if (submitTime != null ? !submitTime.equals(that.submitTime) : that.submitTime != null) return false;
        if (taAfm != null ? !taAfm.equals(that.taAfm) : that.taAfm != null) return false;
        if (taSannStatus != null ? !taSannStatus.equals(that.taSannStatus) : that.taSannStatus != null) return false;
        if (taFullname != null ? !taFullname.equals(that.taFullname) : that.taFullname != null) return false;
        if (taResponseReplace != null ? !taResponseReplace.equals(that.taResponseReplace) : that.taResponseReplace != null)
            return false;
        if (taSpeciality != null ? !taSpeciality.equals(that.taSpeciality) : that.taSpeciality != null) return false;
        if (taSpecialityOther != null ? !taSpecialityOther.equals(that.taSpecialityOther) : that.taSpecialityOther != null)
            return false;
        if (technicianRegrequestId != null ? !technicianRegrequestId.equals(that.technicianRegrequestId) : that.technicianRegrequestId != null)
            return false;
        if (technicianRegrequestUserId != null ? !technicianRegrequestUserId.equals(that.technicianRegrequestUserId) : that.technicianRegrequestUserId != null)
            return false;
        if (compShipName != null ? !compShipName.equals(that.compShipName) : that.compShipName != null) return false;
        if (compShipImo != null ? !compShipImo.equals(that.compShipImo) : that.compShipImo != null) return false;

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
        result = 31 * result + (protNo != null ? protNo.hashCode() : 0);
        result = 31 * result + (protDate != null ? protDate.hashCode() : 0);
        result = 31 * result + (protYear != null ? protYear.hashCode() : 0);
        result = 31 * result + (submitTime != null ? submitTime.hashCode() : 0);
        result = 31 * result + (caseId != null ? caseId.hashCode() : 0);
        result = 31 * result + (reqStatus != null ? reqStatus.hashCode() : 0);
        result = 31 * result + (subStatus != null ? subStatus.hashCode() : 0);
        result = 31 * result + (sepeDept != null ? sepeDept.hashCode() : 0);
        result = 31 * result + (compAme != null ? compAme.hashCode() : 0);
        result = 31 * result + (cooperationType != null ? cooperationType.hashCode() : 0);
        result = 31 * result + (technicianRegrequestId != null ? technicianRegrequestId.hashCode() : 0);
        result = 31 * result + (taFullname != null ? taFullname.hashCode() : 0);
        result = 31 * result + (taAfm != null ? taAfm.hashCode() : 0);
        result = 31 * result + (taSpeciality != null ? taSpeciality.hashCode() : 0);
        result = 31 * result + (taSannStatus != null ? taSannStatus.hashCode() : 0);
        result = 31 * result + (taSpecialityOther != null ? taSpecialityOther.hashCode() : 0);
        result = 31 * result + (compShipId != null ? compShipId.hashCode() : 0);
        result = 31 * result + (technicianRegrequestUserId != null ? technicianRegrequestUserId.hashCode() : 0);
        result = 31 * result + (dateStart != null ? dateStart.hashCode() : 0);
        result = 31 * result + (dateEnd != null ? dateEnd.hashCode() : 0);
        result = 31 * result + (compTaSannPrevId != null ? compTaSannPrevId.hashCode() : 0);
        result = 31 * result + (compRepresentativeId != null ? compRepresentativeId.hashCode() : 0);
        result = 31 * result + (taResponseReplace != null ? taResponseReplace.hashCode() : 0);
        result = 31 * result + (projAddr != null ? projAddr.hashCode() : 0);
        result = 31 * result + (projAddrD != null ? projAddrD.hashCode() : 0);
        result = 31 * result + (projAddrK != null ? projAddrK.hashCode() : 0);
        result = 31 * result + (projAddrP != null ? projAddrP.hashCode() : 0);
        result = 31 * result + (projAddrPe != null ? projAddrPe.hashCode() : 0);
        result = 31 * result + (projAddrTk != null ? projAddrTk.hashCode() : 0);
        result = 31 * result + (portAuthority != null ? portAuthority.hashCode() : 0);
        result = 31 * result + (shipDangerZone != null ? shipDangerZone.hashCode() : 0);
        result = 31 * result + (shipyard != null ? shipyard.hashCode() : 0);
        result = 31 * result + (projDuration != null ? projDuration.hashCode() : 0);
        result = 31 * result + (projStartDate != null ? projStartDate.hashCode() : 0);
        result = 31 * result + (projEmplNum != null ? projEmplNum.hashCode() : 0);
        result = 31 * result + (compShipName != null ? compShipName.hashCode() : 0);
        result = 31 * result + (compShipImo != null ? compShipImo.hashCode() : 0);
        return result;
    }
}
