package sepe.domain.technician;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created by Nikos on 6/18/2015.
 */
@Entity
@javax.persistence.Table(name = "SP_PTL_V_COMP_TA_ANN", schema = "SP_PTL", catalog = "")
public class SpPtlVCompTaAnn {
    private Long compTaAnnId;

    @Id
    @javax.persistence.Column(name = "COMP_TA_ANN_ID", nullable = false, insertable = true, updatable = true, precision = 0)
    public Long getCompTaAnnId() {
        return compTaAnnId;
    }

    public void setCompTaAnnId(Long compTaAnnId) {
        this.compTaAnnId = compTaAnnId;
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

    private Long compTaAnnTaId;

    @Basic
    @javax.persistence.Column(name = "COMP_TA_ANN_TA_ID", nullable = false, insertable = true, updatable = true, precision = 0)
    public Long getCompTaAnnTaId() {
        return compTaAnnTaId;
    }

    public void setCompTaAnnTaId(Long compTaAnnTaId) {
        this.compTaAnnTaId = compTaAnnTaId;
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

    private Integer taAnnTaStatus;

    @Basic
    @javax.persistence.Column(name = "TA_ANN_TA_STATUS", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getTaAnnTaStatus() {
        return taAnnTaStatus;
    }

    public void setTaAnnTaStatus(Integer taAnnTaStatus) {
        this.taAnnTaStatus = taAnnTaStatus;
    }

    private Integer taAnnStatus;

    @Basic
    @javax.persistence.Column(name = "TA_ANN_STATUS", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getTaAnnStatus() {
        return taAnnStatus;
    }

    public void setTaAnnStatus(Integer taAnnStatus) {
        this.taAnnStatus = taAnnStatus;
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

    private Integer taAnnTaReplaceResponse;

    @Basic
    @javax.persistence.Column(name = "TA_ANN_TA_REPLACE_RESPONSE", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getTaAnnTaReplaceResponse() {
        return taAnnTaReplaceResponse;
    }

    public void setTaAnnTaReplaceResponse(Integer taAnnTaReplaceResponse) {
        this.taAnnTaReplaceResponse = taAnnTaReplaceResponse;
    }

    private Integer taReplaceResponse;

    @Basic
    @javax.persistence.Column(name = "TA_REPLACE_RESPONSE", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getTaReplaceResponse() {
        return taReplaceResponse;
    }

    public void setTaReplaceResponse(Integer taReplaceResponse) {
        this.taReplaceResponse = taReplaceResponse;
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
    @javax.persistence.Column(name = "PTL_BRANCH_ID", nullable = false, insertable = true, updatable = true, precision = 0)
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

    private Long technicianRegrequestUserId;

    @Basic
    @javax.persistence.Column(name = "TECHNICIAN_REGREQUEST_USER_ID", nullable = false, insertable = true, updatable = true, precision = 0)
    public Long getTechnicianRegrequestUserId() {
        return technicianRegrequestUserId;
    }

    public void setTechnicianRegrequestUserId(Long technicianRegrequestUserId) {
        this.technicianRegrequestUserId = technicianRegrequestUserId;
    }

    private Integer taAnnTaTotal;

    @Basic
    @javax.persistence.Column(name = "TA_ANN_TA_TOTAL", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getTaAnnTaTotal() {
        return taAnnTaTotal;
    }

    public void setTaAnnTaTotal(Integer taAnnTaTotal) {
        this.taAnnTaTotal = taAnnTaTotal;
    }

    private Integer taAnnTaTotalAccept;

    @Basic
    @javax.persistence.Column(name = "TA_ANN_TA_TOTAL_ACCEPT", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getTaAnnTaTotalAccept() {
        return taAnnTaTotalAccept;
    }

    public void setTaAnnTaTotalAccept(Integer taAnnTaTotalAccept) {
        this.taAnnTaTotalAccept = taAnnTaTotalAccept;
    }

    private Integer taAnnTaTotalReject;

    @Basic
    @javax.persistence.Column(name = "TA_ANN_TA_TOTAL_REJECT", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getTaAnnTaTotalReject() {
        return taAnnTaTotalReject;
    }

    public void setTaAnnTaTotalReject(Integer taAnnTaTotalReject) {
        this.taAnnTaTotalReject = taAnnTaTotalReject;
    }

    private Integer categANum;

    @Basic
    @javax.persistence.Column(name = "CATEG_A_NUM", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getCategANum() {
        return categANum;
    }

    public void setCategANum(Integer categANum) {
        this.categANum = categANum;
    }

    private Integer categBNum;

    @Basic
    @javax.persistence.Column(name = "CATEG_B_NUM", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getCategBNum() {
        return categBNum;
    }

    public void setCategBNum(Integer categBNum) {
        this.categBNum = categBNum;
    }

    private Integer categCNum;

    @Basic
    @javax.persistence.Column(name = "CATEG_C_NUM", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getCategCNum() {
        return categCNum;
    }

    public void setCategCNum(Integer categCNum) {
        this.categCNum = categCNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpPtlVCompTaAnn that = (SpPtlVCompTaAnn) o;

        if (compTaAnnId != that.compTaAnnId) return false;
        if (compTaAnnId != that.compTaAnnTaId) return false;
        if (companyId != that.companyId) return false;
        if (ptlBranchId != that.ptlBranchId) return false;
        if (technicianRegrequestUserId != that.technicianRegrequestUserId) return false;
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
        if (compAme != null ? !compAme.equals(that.compAme) : that.compAme != null) return false;
        if (compFullName != null ? !compFullName.equals(that.compFullName) : that.compFullName != null) return false;
        if (compPhone != null ? !compPhone.equals(that.compPhone) : that.compPhone != null) return false;
        if (compTaxNumber != null ? !compTaxNumber.equals(that.compTaxNumber) : that.compTaxNumber != null)
            return false;
        if (dateEnd != null ? !dateEnd.equals(that.dateEnd) : that.dateEnd != null) return false;
        if (dateStart != null ? !dateStart.equals(that.dateStart) : that.dateStart != null) return false;
        if (reqStatus != null ? !reqStatus.equals(that.reqStatus) : that.reqStatus != null) return false;
        if (taAnnStatus != null ? !taAnnStatus.equals(that.taAnnStatus) : that.taAnnStatus != null) return false;
        if (taReplaceResponse != null ? !taReplaceResponse.equals(that.taReplaceResponse) : that.taReplaceResponse != null)
            return false;
        if (technicianRegrequestId != null ? !technicianRegrequestId.equals(that.technicianRegrequestId) : that.technicianRegrequestId != null)
            return false;
        if (taAnnTaTotalReject != null ? !taAnnTaTotalReject.equals(that.taAnnTaTotalReject) : that.taAnnTaTotalReject != null)
            return false;
        if (taAnnTaTotalAccept != null ? !taAnnTaTotalAccept.equals(that.taAnnTaTotalAccept) : that.taAnnTaTotalAccept != null)
            return false;
        if (taAnnTaTotal != null ? !taAnnTaTotal.equals(that.taAnnTaTotal) : that.taAnnTaTotal != null)
            return false;
        if (compPtlBranchId != null ? !compPtlBranchId.equals(that.compPtlBranchId) : that.compPtlBranchId != null)
            return false;
        if (subStatus != null ? !subStatus.equals(that.subStatus) : that.subStatus != null)
            return false;
        if (taAnnTaStatus != null ? !taAnnTaStatus.equals(that.taAnnTaStatus) : that.taAnnTaStatus != null)
            return false;
        if (taAnnTaReplaceResponse != null ? !taAnnTaReplaceResponse.equals(that.taAnnTaReplaceResponse) : that.taAnnTaReplaceResponse != null)
            return false;
        if (categANum != null ? !categANum.equals(that.categANum) : that.categANum != null) return false;
        if (categBNum != null ? !categBNum.equals(that.categBNum) : that.categBNum != null) return false;
        if (categCNum != null ? !categCNum.equals(that.categCNum) : that.categCNum != null) return false;
        return true;
    }

    /*@Override
    public int hashCode() {
        int result = (int) (compTaAnnId ^ (compTaAnnId >>> 32));
        result = 31 * result + (int) (companyId ^ (companyId >>> 32));
        result = 31 * result + (technicianRegrequestId != null ? technicianRegrequestId.hashCode() : 0);
        result = 31 * result + (compFullName != null ? compFullName.hashCode() : 0);
        result = 31 * result + (compPhone != null ? compPhone.hashCode() : 0);
        result = 31 * result + (taAnnStatus != null ? taAnnStatus.hashCode() : 0);
        result = 31 * result + (reqStatus != null ? reqStatus.hashCode() : 0);
        result = 31 * result + (taReplaceResponse != null ? taReplaceResponse.hashCode() : 0);
        result = 31 * result + (compTaxNumber != null ? compTaxNumber.hashCode() : 0);
        result = 31 * result + (compAme != null ? compAme.hashCode() : 0);
        result = 31 * result + (dateStart != null ? dateStart.hashCode() : 0);
        result = 31 * result + (dateEnd != null ? dateEnd.hashCode() : 0);
        result = 31 * result + (compAddr != null ? compAddr.hashCode() : 0);
        result = 31 * result + (compAddrD != null ? compAddrD.hashCode() : 0);
        result = 31 * result + (compAddrK != null ? compAddrK.hashCode() : 0);
        result = 31 * result + (compAddrP != null ? compAddrP.hashCode() : 0);
        result = 31 * result + (compAddrPe != null ? compAddrPe.hashCode() : 0);
        result = 31 * result + (compAddrTk != null ? compAddrTk.hashCode() : 0);
        result = 31 * result + (int) (ptlBranchId ^ (ptlBranchId >>> 32));
        result = 31 * result + (brAddr != null ? brAddr.hashCode() : 0);
        result = 31 * result + (brAddrD != null ? brAddrD.hashCode() : 0);
        result = 31 * result + (brAddrK != null ? brAddrK.hashCode() : 0);
        result = 31 * result + (brAddrP != null ? brAddrP.hashCode() : 0);
        result = 31 * result + (brAddrPe != null ? brAddrPe.hashCode() : 0);
        result = 31 * result + (brAddrTk != null ? brAddrTk.hashCode() : 0);
        result = 31 * result + (brDescr != null ? brDescr.hashCode() : 0);
        result = 31 * result + (int) (technicianRegrequestUserId ^ (technicianRegrequestUserId >>> 32));
        return result;
    }*/

    @Override
    public int hashCode() {
        int result = (int) (compTaAnnId ^ (compTaAnnId >>> 32));
        result = 31 * result + (int) (companyId ^ (companyId >>> 32));
        result = 31 * result + (int) (compTaAnnTaId ^ (compTaAnnTaId >>> 32));
        result = 31 * result + (int) (technicianRegrequestUserId ^ (technicianRegrequestUserId >>> 32));
        result = 31 * result + (compFullName != null ? compFullName.hashCode() : 0);
        result = 31 * result + (compPhone != null ? compPhone.hashCode() : 0);
        result = 31 * result + (taAnnTaStatus != null ? taAnnTaStatus.hashCode() : 0);
        result = 31 * result + (taAnnStatus != null ? taAnnStatus.hashCode() : 0);
        result = 31 * result + (subStatus != null ? subStatus.hashCode() : 0);
        result = 31 * result + (reqStatus != null ? reqStatus.hashCode() : 0);
        result = 31 * result + (taAnnTaReplaceResponse != null ? taAnnTaReplaceResponse.hashCode() : 0);
        result = 31 * result + (taReplaceResponse != null ? taReplaceResponse.hashCode() : 0);
        result = 31 * result + (compTaxNumber != null ? compTaxNumber.hashCode() : 0);
        result = 31 * result + (compAme != null ? compAme.hashCode() : 0);
        result = 31 * result + (dateStart != null ? dateStart.hashCode() : 0);
        result = 31 * result + (dateEnd != null ? dateEnd.hashCode() : 0);
        result = 31 * result + (compAddr != null ? compAddr.hashCode() : 0);
        result = 31 * result + (compAddrD != null ? compAddrD.hashCode() : 0);
        result = 31 * result + (compAddrK != null ? compAddrK.hashCode() : 0);
        result = 31 * result + (compAddrP != null ? compAddrP.hashCode() : 0);
        result = 31 * result + (compAddrPe != null ? compAddrPe.hashCode() : 0);
        result = 31 * result + (compAddrTk != null ? compAddrTk.hashCode() : 0);
        result = 31 * result + (ptlBranchId != null ? ptlBranchId.hashCode() : 0);
        result = 31 * result + (compPtlBranchId != null ? compPtlBranchId.hashCode() : 0);
        result = 31 * result + (brAddr != null ? brAddr.hashCode() : 0);
        result = 31 * result + (brAddrD != null ? brAddrD.hashCode() : 0);
        result = 31 * result + (brAddrK != null ? brAddrK.hashCode() : 0);
        result = 31 * result + (brAddrP != null ? brAddrP.hashCode() : 0);
        result = 31 * result + (brAddrPe != null ? brAddrPe.hashCode() : 0);
        result = 31 * result + (brAddrTk != null ? brAddrTk.hashCode() : 0);
        result = 31 * result + (brDescr != null ? brDescr.hashCode() : 0);
        result = 31 * result + (int) (technicianRegrequestId ^ (technicianRegrequestId >>> 32));
        result = 31 * result + (taAnnTaTotal != null ? taAnnTaTotal.hashCode() : 0);
        result = 31 * result + (taAnnTaTotalAccept != null ? taAnnTaTotalAccept.hashCode() : 0);
        result = 31 * result + (taAnnTaTotalReject != null ? taAnnTaTotalReject.hashCode() : 0);
        result = 31 * result + (categANum != null ? categANum.hashCode() : 0);
        result = 31 * result + (categBNum != null ? categBNum.hashCode() : 0);
        result = 31 * result + (categCNum != null ? categCNum.hashCode() : 0);
        return result;
    }
}
