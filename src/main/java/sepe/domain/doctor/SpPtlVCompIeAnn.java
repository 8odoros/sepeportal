package sepe.domain.doctor;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created by Nikos on 5/21/2015.
 */
@Entity
@javax.persistence.Table(name = "SP_PTL_V_COMP_IE_ANN", schema = "SP_PTL", catalog = "")
public class SpPtlVCompIeAnn {
    private Long compIeAnnId;

    @Id
    @javax.persistence.Column(name = "COMP_IE_ANN_ID", nullable = false, insertable = true, updatable = true, precision = 0)
    public Long getCompIeAnnId() {
        return compIeAnnId;
    }

    public void setCompIeAnnId(Long compIeAnnId) {
        this.compIeAnnId = compIeAnnId;
    }

    private Long companyId;

    @Basic
    @javax.persistence.Column(name = "COMPANY_ID", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    private Long compIeAnnIeId;

    @Basic
    @javax.persistence.Column(name = "COMP_IE_ANN_IE_ID", nullable = false, insertable = true, updatable = true, precision = 0)
    public Long getCompIeAnnIeId() {
        return compIeAnnIeId;
    }

    public void setCompIeAnnIeId(Long compIeAnnIeId) {
        this.compIeAnnIeId = compIeAnnIeId;
    }

    private Long doctorRegrequestId;

    @Basic
    @javax.persistence.Column(name = "DOCTOR_REGREQUEST_ID", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getDoctorRegrequestId() {
        return doctorRegrequestId;
    }

    public void setDoctorRegrequestId(Long doctorRegrequestId) {
        this.doctorRegrequestId = doctorRegrequestId;
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

    private Integer ieAnnIeStatus;

    @Basic
    @javax.persistence.Column(name = "IE_ANN_IE_STATUS", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getIeAnnIeStatus() {
        return ieAnnIeStatus;
    }

    public void setIeAnnIeStatus(Integer ieAnnIeStatus) {
        this.ieAnnIeStatus = ieAnnIeStatus;
    }

    private Integer ieAnnStatus;

    @Basic
    @javax.persistence.Column(name = "IE_ANN_STATUS", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getIeAnnStatus() {
        return ieAnnStatus;
    }

    public void setIeAnnStatus(Integer ieAnnStatus) {
        this.ieAnnStatus = ieAnnStatus;
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

    private Integer ieAnnIeReplaceResponse;

    @Basic
    @javax.persistence.Column(name = "IE_ANN_IE_REPLACE_RESPONSE", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getIeAnnIeReplaceResponse() {
        return ieAnnIeReplaceResponse;
    }

    public void setIeAnnIeReplaceResponse(Integer ieAnnIeReplaceResponse) {
        this.ieAnnIeReplaceResponse = ieAnnIeReplaceResponse;
    }

    private Integer ieReplaceResponse;

    @Basic
    @javax.persistence.Column(name = "IE_REPLACE_RESPONSE", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getIeReplaceResponse() {
        return ieReplaceResponse;
    }

    public void setIeReplaceResponse(Integer ieReplaceResponse) {
        this.ieReplaceResponse = ieReplaceResponse;
    }
    private String compTaxNumber;

    @Basic
    @javax.persistence.Column(name = "COMP_TAX_NUMBER", nullable = true, insertable = true, updatable = true, length = 50)
    public String getCompTaxNumber() {
        return compTaxNumber;
    }

    public void setCompTaxNumber(String compTaxNumber) {
        this.compTaxNumber = compTaxNumber;
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




    private Long ptlBranchId;

    @Basic
    @javax.persistence.Column(name = "PTL_BRANCH_ID", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getPtlBranchId() {
        return ptlBranchId;
    }

    public void setPtlBranchId(Long ptlBranchId) {
        this.ptlBranchId = ptlBranchId;
    }

    private Long compPtlBranchId;

    @Basic
    @javax.persistence.Column(name = "COMP_PTL_BRANCH_ID", nullable = false, insertable = true, updatable = true, precision = 0)
    public Long getCompPtlBranchId() {
        return compPtlBranchId;
    }

    public void setCompPtlBranchId(Long ptlBranchId) {
        this.compPtlBranchId = compPtlBranchId;
    }
    private String brAddr;
    @Basic
    @javax.persistence.Column(name = "BR_ADDR", nullable = true, insertable = true, updatable = true, length = 50)
    public String getBrAddr() {
        return brAddr;
    }

    public void setBrAddr(String brAddr) {
        this.brAddr = brAddr;
    }

    private String brAddrD;

    @Basic
    @javax.persistence.Column(name = "BR_ADDR_D", nullable = true, insertable = true, updatable = true, length = 100)
    public String getBrAddrD() {
        return brAddrD;
    }

    public void setBrAddrD(String brAddrD) {
        this.brAddrD = brAddrD;
    }

    private String brAddrK;

    @Basic
    @javax.persistence.Column(name = "BR_ADDR_K", nullable = true, insertable = true, updatable = true, length = 100)
    public String getBrAddrK() {
        return brAddrK;
    }

    public void setBrAddrK(String brAddrK) {
        this.brAddrK = brAddrK;
    }

    private String brAddrP;

    @Basic
    @javax.persistence.Column(name = "BR_ADDR_P", nullable = true, insertable = true, updatable = true, length = 100)
    public String getBrAddrP() {
        return brAddrP;
    }

    public void setBrAddrP(String brAddrP) {
        this.brAddrP = brAddrP;
    }

    private String brAddrPe;

    @Basic
    @javax.persistence.Column(name = "BR_ADDR_PE", nullable = true, insertable = true, updatable = true, length = 100)
    public String getBrAddrPe() {
        return brAddrPe;
    }

    public void setBrAddrPe(String brAddrPe) {
        this.brAddrPe = brAddrPe;
    }

    private String brAddrTk;

    @Basic
    @javax.persistence.Column(name = "BR_ADDR_TK", nullable = true, insertable = true, updatable = true, length = 50)
    public String getBrAddrTk() {
        return brAddrTk;
    }

    public void setBrAddrTk(String brAddrTk) {
        this.brAddrTk = brAddrTk;
    }

    private String brDescr;

    @Basic
    @javax.persistence.Column(name = "BR_DESCR", nullable = true, insertable = true, updatable = true, length = 20)
    public String getBrDescr() {
        return brDescr;
    }

    public void setBrDescr(String brDescr) {
        this.brDescr = brDescr;
    }

    private Long doctorRegrequestUserId;

    @Basic
    @javax.persistence.Column(name = "DOCTOR_REGREQUEST_USER_ID", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getDoctorRegrequestUserId() {
        return doctorRegrequestUserId;
    }

    public void setDoctorRegrequestUserId(Long doctorRegrequestUserId) {
        this.doctorRegrequestUserId = doctorRegrequestUserId;
    }

    private Integer ieAnnIeTotal;

    @Basic
    @javax.persistence.Column(name = "IE_ANN_IE_TOTAL", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getIeAnnIeTotal() {
        return ieAnnIeTotal;
    }

    public void setIeAnnIeTotal(Integer ieAnnIeTotal) {
        this.ieAnnIeTotal = ieAnnIeTotal;
    }

    private Integer ieAnnIeTotalAccept;

    @Basic
    @javax.persistence.Column(name = "IE_ANN_IE_TOTAL_ACCEPT", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getIeAnnIeTotalAccept() {
        return ieAnnIeTotalAccept;
    }

    public void setIeAnnIeTotalAccept(Integer ieAnnIeTotalAccept) {
        this.ieAnnIeTotalAccept = ieAnnIeTotalAccept;
    }
    private Integer ieAnnIeTotalReject;

    @Basic
    @javax.persistence.Column(name = "IE_ANN_IE_TOTAL_REJECT", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getIeAnnIeTotalReject() {
        return ieAnnIeTotalReject;
    }

    public void setIeAnnIeTotalReject(Integer ieAnnIeTotalReject) {
        this.ieAnnIeTotalReject = ieAnnIeTotalReject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpPtlVCompIeAnn that = (SpPtlVCompIeAnn) o;

        if (compIeAnnId != that.compIeAnnId) return false;
        if (ptlBranchId != null ? !ptlBranchId.equals(that.ptlBranchId) : that.ptlBranchId != null) return false;
        if (brAddr != null ? !brAddr.equals(that.brAddr) : that.brAddr != null) return false;
        if (brAddrD != null ? !brAddrD.equals(that.brAddrD) : that.brAddrD != null) return false;
        if (brAddrK != null ? !brAddrK.equals(that.brAddrK) : that.brAddrK != null) return false;
        if (brAddrP != null ? !brAddrP.equals(that.brAddrP) : that.brAddrP != null) return false;
        if (brAddrPe != null ? !brAddrPe.equals(that.brAddrPe) : that.brAddrPe != null) return false;
        if (brAddrTk != null ? !brAddrTk.equals(that.brAddrTk) : that.brAddrTk != null) return false;
        if (brDescr != null ? !brDescr.equals(that.brDescr) : that.brDescr != null) return false;
        if (compAddr != null ? !compAddr.equals(that.compAddr) : that.compAddr != null) return false;
        if (compAddrD != null ? !compAddrD.equals(that.compAddrD) : that.compAddrD != null) return false;
        if (compAddrK != null ? !compAddrK.equals(that.compAddrK) : that.compAddrK != null) return false;
        if (compAddrP != null ? !compAddrP.equals(that.compAddrP) : that.compAddrP != null) return false;
        if (compAddrPe != null ? !compAddrPe.equals(that.compAddrPe) : that.compAddrPe != null) return false;
        if (compAddrTk != null ? !compAddrTk.equals(that.compAddrTk) : that.compAddrTk != null) return false;
        if (compFullName != null ? !compFullName.equals(that.compFullName) : that.compFullName != null) return false;
        if (compPhone != null ? !compPhone.equals(that.compPhone) : that.compPhone != null) return false;
        if (companyId != null ? !companyId.equals(that.companyId) : that.companyId != null) return false;
        if (doctorRegrequestId != null ? !doctorRegrequestId.equals(that.doctorRegrequestId) : that.doctorRegrequestId != null)
            return false;
        if (doctorRegrequestUserId != null ? !doctorRegrequestUserId.equals(that.doctorRegrequestUserId) : that.doctorRegrequestUserId != null)
            return false;
        if (ieAnnStatus != null ? !ieAnnStatus.equals(that.ieAnnStatus) : that.ieAnnStatus != null) return false;
        if (reqStatus != null ? !reqStatus.equals(that.reqStatus) : that.reqStatus != null) return false;
        if (compTaxNumber != null ? !compTaxNumber.equals(that.compTaxNumber) : that.compTaxNumber != null) return false;
        if (compAme != null ? !compAme.equals(that.compAme) : that.compAme != null) return false;
        if (dateStart != null ? !dateStart.equals(that.dateStart) : that.dateStart != null) return false;
        if (dateEnd != null ? !dateEnd.equals(that.dateEnd) : that.dateEnd != null) return false;
        if (ieReplaceResponse != null ? !ieReplaceResponse.equals(that.ieReplaceResponse) : that.ieReplaceResponse != null) return false;
        if (compIeAnnIeId != null ? !compIeAnnIeId.equals(that.compIeAnnIeId) : that.compIeAnnIeId != null) return false;
        if (ieAnnIeTotalReject != null ? !ieAnnIeTotalReject.equals(that.ieAnnIeTotalReject) : that.ieAnnIeTotalReject != null)
            return false;
        if (ieAnnIeTotalAccept != null ? !ieAnnIeTotalAccept.equals(that.ieAnnIeTotalAccept) : that.ieAnnIeTotalAccept != null)
            return false;
        if (ieAnnIeTotal != null ? !ieAnnIeTotal.equals(that.ieAnnIeTotal) : that.ieAnnIeTotal != null)
            return false;
        if (ieAnnIeStatus != null ? !ieAnnIeStatus.equals(that.ieAnnIeStatus) : that.ieAnnIeStatus != null)
            return false;
        if (ieAnnIeReplaceResponse != null ? !ieAnnIeReplaceResponse.equals(that.ieAnnIeReplaceResponse) : that.ieAnnIeReplaceResponse != null)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (compIeAnnId ^ (compIeAnnId >>> 32));
        result = 31 * result + (companyId != null ? companyId.hashCode() : 0);
        result = 31 * result + (doctorRegrequestId != null ? doctorRegrequestId.hashCode() : 0);
        result = 31 * result + (compFullName != null ? compFullName.hashCode() : 0);
        result = 31 * result + (compPhone != null ? compPhone.hashCode() : 0);
        result = 31 * result + (ieAnnStatus != null ? ieAnnStatus.hashCode() : 0);
        result = 31 * result + (reqStatus != null ? reqStatus.hashCode() : 0);
        result = 31 * result + (compTaxNumber != null ? compTaxNumber.hashCode() : 0);
        result = 31 * result + (compAme != null ? compAme.hashCode() : 0);
        result = 31 * result + (compAddr != null ? compAddr.hashCode() : 0);
        result = 31 * result + (compAddrD != null ? compAddrD.hashCode() : 0);
        result = 31 * result + (compAddrK != null ? compAddrK.hashCode() : 0);
        result = 31 * result + (compAddrP != null ? compAddrP.hashCode() : 0);
        result = 31 * result + (compAddrPe != null ? compAddrPe.hashCode() : 0);
        result = 31 * result + (compAddrTk != null ? compAddrTk.hashCode() : 0);
        result = 31 * result + (ptlBranchId != null ? ptlBranchId.hashCode() : 0);
        result = 31 * result + (brAddr != null ? brAddr.hashCode() : 0);
        result = 31 * result + (brAddrD != null ? brAddrD.hashCode() : 0);
        result = 31 * result + (brAddrK != null ? brAddrK.hashCode() : 0);
        result = 31 * result + (brAddrP != null ? brAddrP.hashCode() : 0);
        result = 31 * result + (brAddrPe != null ? brAddrPe.hashCode() : 0);
        result = 31 * result + (brAddrTk != null ? brAddrTk.hashCode() : 0);
        result = 31 * result + (brDescr != null ? brDescr.hashCode() : 0);
        result = 31 * result + (doctorRegrequestUserId != null ? doctorRegrequestUserId.hashCode() : 0);
        result = 31 * result + (dateStart != null ? dateStart.hashCode() : 0);
        result = 31 * result + (dateEnd != null ? dateEnd.hashCode() : 0);
        result = 31 * result + (ieReplaceResponse != null ? ieReplaceResponse.hashCode() : 0);
        result = 31 * result + (compIeAnnIeId != null ? compIeAnnIeId.hashCode() : 0);
        result = 31 * result + (ieAnnIeTotalReject != null ? ieAnnIeTotalReject.hashCode() : 0);
        result = 31 * result + (ieAnnIeTotalAccept != null ? ieAnnIeTotalAccept.hashCode() : 0);
        result = 31 * result + (ieAnnIeTotal != null ? ieAnnIeTotal.hashCode() : 0);
        result = 31 * result + (ieAnnIeStatus != null ? ieAnnIeStatus.hashCode() : 0);
        result = 31 * result + (ieAnnIeReplaceResponse != null ? ieAnnIeReplaceResponse.hashCode() : 0);
        return result;
    }
}
