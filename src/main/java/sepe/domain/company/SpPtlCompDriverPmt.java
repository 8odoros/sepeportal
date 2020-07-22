package sepe.domain.company;

import sepe.dto.SpPtlCompDriverPmtEntryDTO;

import javax.annotation.Nullable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Nikos on 6/9/2015.
 */
@Entity
@javax.persistence.Table(name = "SP_PTL_COMP_DRIVER_PMT", schema = "SP_PTL", catalog = "")
public class SpPtlCompDriverPmt {
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

    private String vehicleLicenceNum;

    @Basic
    @javax.persistence.Column(name = "VEHICLE_LICENCE_NUM")
    public String getVehicleLicenceNum() {
        return vehicleLicenceNum;
    }

    public void setVehicleLicenceNum(String vehicleLicenceNum) {
        this.vehicleLicenceNum = vehicleLicenceNum;
    }

    private String ownerLicenceNum;

    @Basic
    @javax.persistence.Column(name = "OWNER_LICENCE_NUM")
    public String getOwnerLicenceNum() {
        return ownerLicenceNum;
    }

    public void setOwnerLicenceNum(String ownerLicenceNum) {
        this.ownerLicenceNum = ownerLicenceNum;
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

    private String ownerAfm;

    @Basic
    @javax.persistence.Column(name = "OWNER_AFM")
    public String getOwnerAfm() {
        return ownerAfm;
    }

    public void setOwnerAfm(String ownerAfm) {
        this.ownerAfm = ownerAfm;
    }

    private String ownerCardIssuer;

    @Basic
    @javax.persistence.Column(name = "OWNER_CARD_ISSUER")
    public String getOwnerCardIssuer() {
        return ownerCardIssuer;
    }

    public void setOwnerCardIssuer(String ownerCardIssuer) {
        this.ownerCardIssuer = ownerCardIssuer;
    }

    private String compAmeIka;

    @Basic
    @javax.persistence.Column(name = "COMP_AME_IKA")
    public String getCompAmeIka() {
        return compAmeIka;
    }

    public void setCompAmeIka(String compAmeIka) {
        this.compAmeIka = compAmeIka;
    }

    private String compAfm;

    @Basic
    @javax.persistence.Column(name = "COMP_AFM")
    public String getCompAfm() {
        return compAfm;
    }

    public void setCompAfm(String compAfm) {
        this.compAfm = compAfm;
    }

    private String compName;

    @Basic
    @javax.persistence.Column(name = "COMP_NAME")
    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
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

    private Long branch0Id;

    @Basic
    @javax.persistence.Column(name = "BRANCH0_ID")
    public Long getBranch0Id() {
        return branch0Id;
    }

    public void setBranch0Id(Long branch0Id) {
        this.branch0Id = branch0Id;
    }

    private Long branch1Id;

    @Basic
    @javax.persistence.Column(name = "BRANCH1_ID")
    public Long getBranch1Id() {
        return branch1Id;
    }

    public void setBranch1Id(Long branch1Id) {
        this.branch1Id = branch1Id;
    }

    private Integer pmtMonth;

    @Basic
    @javax.persistence.Column(name = "PMT_MONTH")
    public Integer getPmtMonth() {
        return pmtMonth;
    }

    public void setPmtMonth(Integer pmtMonth) { this.pmtMonth = pmtMonth; }

    private Integer pmtYear;

    @Basic
    @javax.persistence.Column(name = "PMT_YEAR")
    public Integer getPmtYear() {
        return pmtYear;
    }

    public void setPmtYear(Integer pmtYear) { this.pmtYear = pmtYear; }

    private Integer inspectorName;

    @Basic
    @javax.persistence.Column(name = "INSPECTOR_NAME")
    public Integer getInspectorName() {
        return inspectorName;
    }

    public void setInspectorName(Integer inspectorName) { this.inspectorName = inspectorName; }

    private String etiology;

    @Basic
    @javax.persistence.Column(name = "ETIOLOGY")
    public String getEtiology() {
        return etiology;
    }

    public void setEtiology(String etiology) { this.etiology = etiology; }

    private String vehiclePlateNum;

    @Basic
    @javax.persistence.Column(name = "VEHICLE_PLATE_NUM")
    public String getVehiclePlateNum() {
        return vehiclePlateNum;
    }

    public void setVehiclePlateNum(String vehiclePlateNum) {
        this.vehiclePlateNum = vehiclePlateNum;
    }
    /////////////////////////////////////////////////
    // /*
    private Set<SpPtlCompDriverPmtEntry> compDriverPmtEntries = new HashSet<SpPtlCompDriverPmtEntry>(0);

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "compDriverPmt", cascade = CascadeType.ALL, orphanRemoval = true) // SHOULD BE EQUAL TO ATTRIBUTE NAME OF OTHER CLASS!!!!
    public Set<SpPtlCompDriverPmtEntry> getCompDriverPmtEntries() {
        return this.compDriverPmtEntries;
    }

    public void setCompDriverPmtEntries(Set<SpPtlCompDriverPmtEntry> compDriverPmtEntries) {
        this.compDriverPmtEntries = compDriverPmtEntries;
    }
    // */
    /////////////////////////////////////////////////


    @Nullable
    private SpPtlCompDriverPmtEntryDTO[] offdays;

    @Transient
    @Nullable
    public SpPtlCompDriverPmtEntryDTO[] getOffdays() {
        return offdays;
    }

    @Transient
    @Nullable
    public void setOffdays(@Nullable SpPtlCompDriverPmtEntryDTO[] offdays) {
        this.offdays = offdays;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpPtlCompDriverPmt that = (SpPtlCompDriverPmt) o;

        if (id != that.id) return false;
        if (companyId != null ? !companyId.equals(that.companyId) : that.companyId != null) return false;
        if (ownerFirstname != null ? !ownerFirstname.equals(that.ownerFirstname) : that.ownerFirstname != null)
            return false;
        if (ownerLastname != null ? !ownerLastname.equals(that.ownerLastname) : that.ownerLastname != null)
            return false;
        if (compAddr != null ? !compAddr.equals(that.compAddr) : that.compAddr != null) return false;
        if (compAddrD != null ? !compAddrD.equals(that.compAddrD) : that.compAddrD != null) return false;
        if (compAddrK != null ? !compAddrK.equals(that.compAddrK) : that.compAddrK != null) return false;
        if (compAddrP != null ? !compAddrP.equals(that.compAddrP) : that.compAddrP != null) return false;
        if (compAddrPe != null ? !compAddrPe.equals(that.compAddrPe) : that.compAddrPe != null) return false;
        if (compAddrTk != null ? !compAddrTk.equals(that.compAddrTk) : that.compAddrTk != null) return false;
        if (ownerCardType != null ? !ownerCardType.equals(that.ownerCardType) : that.ownerCardType != null)
            return false;
        if (ownerCardNumber != null ? !ownerCardNumber.equals(that.ownerCardNumber) : that.ownerCardNumber != null)
            return false;
        if (vehicleLicenceNum != null ? !vehicleLicenceNum.equals(that.vehicleLicenceNum) : that.vehicleLicenceNum != null)
            return false;
        if (ownerLicenceNum != null ? !ownerLicenceNum.equals(that.ownerLicenceNum) : that.ownerLicenceNum != null)
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
        if (ownerAfm != null ? !ownerAfm.equals(that.ownerAfm) : that.ownerAfm != null) return false;
        if (ownerCardIssuer != null ? !ownerCardIssuer.equals(that.ownerCardIssuer) : that.ownerCardIssuer != null)
            return false;
        if (compAmeIka != null ? !compAmeIka.equals(that.compAmeIka) : that.compAmeIka != null) return false;
        if (compAfm != null ? !compAfm.equals(that.compAfm) : that.compAfm != null) return false;
        if (compName != null ? !compName.equals(that.compName) : that.compName != null) return false;
        if (compPhone != null ? !compPhone.equals(that.compPhone) : that.compPhone != null) return false;
        if (branch0Id != null ? !branch0Id.equals(that.branch0Id) : that.branch0Id != null) return false;
        if (branch1Id != null ? !branch1Id.equals(that.branch1Id) : that.branch1Id != null) return false;
        if (pmtMonth != null ? !pmtMonth.equals(that.pmtMonth) : that.pmtMonth != null) return false;
        if (pmtYear != null ? !pmtYear.equals(that.pmtYear) : that.pmtYear != null) return false;
        if (inspectorName != null ? !inspectorName.equals(that.inspectorName) : that.inspectorName != null) return false;
        if (etiology != null ? !etiology.equals(that.etiology) : that.etiology != null) return false;
        if (vehiclePlateNum != null ? !vehiclePlateNum.equals(that.vehiclePlateNum) : that.vehiclePlateNum != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (companyId != null ? companyId.hashCode() : 0);
        result = 31 * result + (ownerFirstname != null ? ownerFirstname.hashCode() : 0);
        result = 31 * result + (ownerLastname != null ? ownerLastname.hashCode() : 0);
        result = 31 * result + (compAddr != null ? compAddr.hashCode() : 0);
        result = 31 * result + (compAddrD != null ? compAddrD.hashCode() : 0);
        result = 31 * result + (compAddrK != null ? compAddrK.hashCode() : 0);
        result = 31 * result + (compAddrP != null ? compAddrP.hashCode() : 0);
        result = 31 * result + (compAddrPe != null ? compAddrPe.hashCode() : 0);
        result = 31 * result + (compAddrTk != null ? compAddrTk.hashCode() : 0);
        result = 31 * result + (ownerCardType != null ? ownerCardType.hashCode() : 0);
        result = 31 * result + (ownerCardNumber != null ? ownerCardNumber.hashCode() : 0);
        result = 31 * result + (vehicleLicenceNum != null ? vehicleLicenceNum.hashCode() : 0);
        result = 31 * result + (ownerLicenceNum != null ? ownerLicenceNum.hashCode() : 0);
        result = 31 * result + (docId != null ? docId.hashCode() : 0);
        result = 31 * result + (protNo != null ? protNo.hashCode() : 0);
        result = 31 * result + (protDate != null ? protDate.hashCode() : 0);
        result = 31 * result + (protYear != null ? protYear.hashCode() : 0);
        result = 31 * result + (submitTime != null ? submitTime.hashCode() : 0);
        result = 31 * result + (caseId != null ? caseId.hashCode() : 0);
        result = 31 * result + (reqStatus != null ? reqStatus.hashCode() : 0);
        result = 31 * result + (subStatus != null ? subStatus.hashCode() : 0);
        result = 31 * result + (sepeDept != null ? sepeDept.hashCode() : 0);
        result = 31 * result + (ownerAfm != null ? ownerAfm.hashCode() : 0);
        result = 31 * result + (ownerCardIssuer != null ? ownerCardIssuer.hashCode() : 0);
        result = 31 * result + (compAmeIka != null ? compAmeIka.hashCode() : 0);
        result = 31 * result + (compAfm != null ? compAfm.hashCode() : 0);
        result = 31 * result + (compName != null ? compName.hashCode() : 0);
        result = 31 * result + (compPhone != null ? compPhone.hashCode() : 0);
        result = 31 * result + (branch0Id != null ? branch0Id.hashCode() : 0);
        result = 31 * result + (branch1Id != null ? branch1Id.hashCode() : 0);
        result = 31 * result + (pmtMonth != null ? pmtMonth.hashCode() : 0);
        result = 31 * result + (pmtYear != null ? pmtYear.hashCode() : 0);
        result = 31 * result + (inspectorName != null ? inspectorName.hashCode() : 0);
        result = 31 * result + (etiology != null ? etiology.hashCode() : 0);
        result = 31 * result + (vehiclePlateNum != null ? vehiclePlateNum.hashCode() : 0);
        return result;
    }
}
