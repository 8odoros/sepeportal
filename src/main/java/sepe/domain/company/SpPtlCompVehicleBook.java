package sepe.domain.company;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created by Nikos on 6/11/2015.
 */
@Entity
@javax.persistence.Table(name = "SP_PTL_COMP_VEHICLE_BOOK", schema = "SP_PTL", catalog = "")
public class SpPtlCompVehicleBook {
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

    private String ownerAddr;

    @Basic
    @javax.persistence.Column(name = "OWNER_ADDR")
    public String getOwnerAddr() {
        return ownerAddr;
    }

    public void setOwnerAddr(String ownerAddr) {
        this.ownerAddr = ownerAddr;
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

    private String ownerCardIssuingAuth;

    @Basic
    @javax.persistence.Column(name = "OWNER_CARD_ISSUING_AUTH")
    public String getOwnerCardIssuingAuth() {
        return ownerCardIssuingAuth;
    }

    public void setOwnerCardIssuingAuth(String ownerCardIssuingAuth) {
        this.ownerCardIssuingAuth = ownerCardIssuingAuth;
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

    private Long vehicleType;

    @Basic
    @javax.persistence.Column(name = "VEHICLE_TYPE")
    public Long getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(Long vehicleType) {
        this.vehicleType = vehicleType;
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

    private Long compEbrBranch1Id;

    @Basic
    @javax.persistence.Column(name = "COMP_EBR_BRANCH1_ID")
    public Long getCompEbrBranch1Id() {
        return compEbrBranch1Id;
    }

    public void setCompEbrBranch1Id(Long compEbrBranch1Id) {
        this.compEbrBranch1Id = compEbrBranch1Id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpPtlCompVehicleBook that = (SpPtlCompVehicleBook) o;

        if (id != that.id) return false;
        if (companyId != that.companyId) return false;
        if (ownerFirstname != null ? !ownerFirstname.equals(that.ownerFirstname) : that.ownerFirstname != null)
            return false;
        if (ownerLastname != null ? !ownerLastname.equals(that.ownerLastname) : that.ownerLastname != null)
            return false;
        if (ownerBirthplace != null ? !ownerBirthplace.equals(that.ownerBirthplace) : that.ownerBirthplace != null)
            return false;
        if (ownerBirthdate != null ? !ownerBirthdate.equals(that.ownerBirthdate) : that.ownerBirthdate != null)
            return false;
        if (ownerAddr != null ? !ownerAddr.equals(that.ownerAddr) : that.ownerAddr != null) return false;
        if (ownerCardType != null ? !ownerCardType.equals(that.ownerCardType) : that.ownerCardType != null)
            return false;
        if (ownerCardNumber != null ? !ownerCardNumber.equals(that.ownerCardNumber) : that.ownerCardNumber != null)
            return false;
        if (ownerCardIssuingAuth != null ? !ownerCardIssuingAuth.equals(that.ownerCardIssuingAuth) : that.ownerCardIssuingAuth != null)
            return false;
        if (vehicleLicenceNum != null ? !vehicleLicenceNum.equals(that.vehicleLicenceNum) : that.vehicleLicenceNum != null)
            return false;
        if (vehicleType != null ? !vehicleType.equals(that.vehicleType) : that.vehicleType != null) return false;
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
        if (compAfm != null ? !compAfm.equals(that.compAfm) : that.compAfm != null) return false;
        if (compName != null ? !compName.equals(that.compName) : that.compName != null) return false;
        if (compPhone != null ? !compPhone.equals(that.compPhone) : that.compPhone != null) return false;
        if (compAddr != null ? !compAddr.equals(that.compAddr) : that.compAddr != null) return false;
        if (compAddrD != null ? !compAddrD.equals(that.compAddrD) : that.compAddrD != null) return false;
        if (compAddrK != null ? !compAddrK.equals(that.compAddrK) : that.compAddrK != null) return false;
        if (compAddrP != null ? !compAddrP.equals(that.compAddrP) : that.compAddrP != null) return false;
        if (compAddrPe != null ? !compAddrPe.equals(that.compAddrPe) : that.compAddrPe != null) return false;
        if (compAddrTk != null ? !compAddrTk.equals(that.compAddrTk) : that.compAddrTk != null) return false;
        if (compEbrBranch0Id != null ? !compEbrBranch0Id.equals(that.compEbrBranch0Id) : that.compEbrBranch0Id != null)
            return false;
        if (compEbrBranch1Id != null ? !compEbrBranch1Id.equals(that.compEbrBranch1Id) : that.compEbrBranch1Id != null)
            return false;
        if (compAme != null ? !compAme.equals(that.compAme) : that.compAme != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (companyId ^ (companyId >>> 32));
        result = 31 * result + (ownerFirstname != null ? ownerFirstname.hashCode() : 0);
        result = 31 * result + (ownerLastname != null ? ownerLastname.hashCode() : 0);
        result = 31 * result + (ownerBirthplace != null ? ownerBirthplace.hashCode() : 0);
        result = 31 * result + (ownerBirthdate != null ? ownerBirthdate.hashCode() : 0);
        result = 31 * result + (ownerAddr != null ? ownerAddr.hashCode() : 0);
        result = 31 * result + (ownerCardType != null ? ownerCardType.hashCode() : 0);
        result = 31 * result + (ownerCardNumber != null ? ownerCardNumber.hashCode() : 0);
        result = 31 * result + (ownerCardIssuingAuth != null ? ownerCardIssuingAuth.hashCode() : 0);
        result = 31 * result + (vehicleLicenceNum != null ? vehicleLicenceNum.hashCode() : 0);
        result = 31 * result + (vehicleType != null ? vehicleType.hashCode() : 0);
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
        result = 31 * result + (compEbrBranch1Id != null ? compEbrBranch1Id.hashCode() : 0);
        result = 31 * result + (compAme != null ? compAme.hashCode() : 0);
        return result;
    }
}
