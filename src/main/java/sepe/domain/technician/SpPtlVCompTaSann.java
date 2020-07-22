package sepe.domain.technician;

import sepe.domain.company.SpPtlCompTaSannContr;
import sepe.domain.company.SpPtlCompTaSannDiaryEntr;
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
@javax.persistence.Table(name = "SP_PTL_V_COMP_TA_SANN", schema = "SP_PTL", catalog = "")
public class SpPtlVCompTaSann {
    private Long compTaSannId;

    @Id
    @javax.persistence.Column(name = "COMP_TA_SANN_ID", nullable = false, insertable = true, updatable = true, precision = 0)
    public Long getCompTaSannId() {
        return compTaSannId;
    }

    public void setCompTaSannId(Long compTaSannId) {
        this.compTaSannId = compTaSannId;
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

    private Integer taSannStatus;

    @Basic
    @javax.persistence.Column(name = "TA_SANN_STATUS", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getTaSannStatus() {
        return taSannStatus;
    }

    public void setTaSannStatus(Integer taSannStatus) {
        this.taSannStatus = taSannStatus;
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

    private Integer taResponseReplace;

    @Basic
    @javax.persistence.Column(name = "TA_RESPONSE_REPLACE", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getTaResponseReplace() {
        return taResponseReplace;
    }

    public void setTaResponseReplace(Integer taResponseReplace) {
        this.taResponseReplace = taResponseReplace;
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

    private Long shipId;

    @Basic
    @javax.persistence.Column(name = "SHIP_ID", nullable = false, insertable = true, updatable = true, precision = 0)
    public Long getShipId() {
        return shipId;
    }

    public void setShipId(Long shipId) {
        this.shipId = shipId;
    }

    private String shipImo;

    @Basic
    @javax.persistence.Column(name = "SHIP_IMO", nullable = true, insertable = true, updatable = true, length = 200)
    public String getShipImo() {
        return shipImo;
    }

    public void setShipImo(String shipImo) {
        this.shipImo = shipImo;
    }

    private String shipName;

    @Basic
    @javax.persistence.Column(name = "SHIP_NAME", nullable = true, insertable = true, updatable = true, length = 200)
    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpPtlVCompTaSann that = (SpPtlVCompTaSann) o;

        if (compTaSannId != that.compTaSannId) return false;
        if (companyId != that.companyId) return false;
        if (shipId != that.shipId) return false;
        if (technicianRegrequestUserId != that.technicianRegrequestUserId) return false;
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
        if (shipImo != null ? !shipImo.equals(that.shipImo) : that.shipImo != null) return false;
        if (shipName != null ? !shipName.equals(that.shipName) : that.shipName != null) return false;
        if (taResponseReplace != null ? !taResponseReplace.equals(that.taResponseReplace) : that.taResponseReplace != null)
            return false;
        if (taSannStatus != null ? !taSannStatus.equals(that.taSannStatus) : that.taSannStatus != null) return false;
        if (technicianRegrequestId != null ? !technicianRegrequestId.equals(that.technicianRegrequestId) : that.technicianRegrequestId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (compTaSannId ^ (compTaSannId >>> 32));
        result = 31 * result + (int) (companyId ^ (companyId >>> 32));
        result = 31 * result + (technicianRegrequestId != null ? technicianRegrequestId.hashCode() : 0);
        result = 31 * result + (compFullName != null ? compFullName.hashCode() : 0);
        result = 31 * result + (compPhone != null ? compPhone.hashCode() : 0);
        result = 31 * result + (taSannStatus != null ? taSannStatus.hashCode() : 0);
        result = 31 * result + (reqStatus != null ? reqStatus.hashCode() : 0);
        result = 31 * result + (taResponseReplace != null ? taResponseReplace.hashCode() : 0);
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
        result = 31 * result + (int) (shipId ^ (shipId >>> 32));
        result = 31 * result + (shipImo != null ? shipImo.hashCode() : 0);
        result = 31 * result + (shipName != null ? shipName.hashCode() : 0);
        result = 31 * result + (int) (technicianRegrequestUserId ^ (technicianRegrequestUserId >>> 32));
        return result;
    }
}
