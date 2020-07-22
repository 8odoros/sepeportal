package sepe.domain.doctor;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Nikos on 5/20/2015.
 */
@Entity
@javax.persistence.Table(name = "SP_PTL_V_COMP_IE_ANN_DIARY", schema = "SP_PTL", catalog = "")
public class SpPtlVCompIeAnnDiary {
    private Long doctorRegrequestUserId;

    @Basic
    @javax.persistence.Column(name = "DOCTOR_REGREQUEST_USER_ID", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getDoctorRegrequestUserId() {
        return doctorRegrequestUserId;
    }

    public void setDoctorRegrequestUserId(Long doctorRegrequestUserId) {
        this.doctorRegrequestUserId = doctorRegrequestUserId;
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

    private Integer doctorRegrequestId;

    @Basic
    @javax.persistence.Column(name = "DOCTOR_REGREQUEST_ID", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getDoctorRegrequestId() {
        return doctorRegrequestId;
    }

    public void setDoctorRegrequestId(Integer doctorRegrequestId) {
        this.doctorRegrequestId = doctorRegrequestId;
    }

    private Long compPtlBranchId;

    @Basic
    @javax.persistence.Column(name = "COMP_PTL_BRANCH_ID", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getCompPtlBranchId() {
        return compPtlBranchId;
    }

    public void setCompPtlBranchId(Long compPtlBranchId) {
        this.compPtlBranchId = compPtlBranchId;
    }

    private Long id;

    @Id
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

    private String visitTime;

    @Basic
    @javax.persistence.Column(name = "VISIT_TIME", nullable = true, insertable = true, updatable = true, length = 20)
    public String getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(String visitTime) {
        this.visitTime = visitTime;
    }

    private Integer visitDurationMinutes;

    @Basic
    @javax.persistence.Column(name = "VISIT_DURATION_MINUTES", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getVisitDurationMinutes() {
        return visitDurationMinutes;
    }

    public void setVisitDurationMinutes(Integer visitDurationMinutes) {
        this.visitDurationMinutes = visitDurationMinutes;
    }

    private Long compIeAnnId;

    @Basic
    @javax.persistence.Column(name = "COMP_IE_ANN_ID", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getCompIeAnnId() {
        return compIeAnnId;
    }

    public void setCompIeAnnId(Long compIeAnnId) {
        this.compIeAnnId = compIeAnnId;
    }

    private Timestamp visitDate;

    @Basic
    @javax.persistence.Column(name = "VISIT_DATE", nullable = true, insertable = true, updatable = true)
    public Timestamp getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Timestamp visitDate) {
        this.visitDate = visitDate;
    }


    private Long visitDateEpochSec;
    @Basic
    @javax.persistence.Column(name = "VISIT_DATE_EPOCH_SEC", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getVisitDateEpochSec() {
        return visitDateEpochSec;
    }

    public void setVisitDateEpochSec(Long compIeAnnId) {
        this.visitDateEpochSec = visitDateEpochSec;
    }

    private Integer visitMonth;
    @Basic
    @javax.persistence.Column(name = "VISIT_MONTH", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getVisitMonth() {
        return visitMonth;
    }

    public void setVisitMonth(Integer visitMonth) {
        this.visitMonth = visitMonth;
    }

    private Integer visitYear;
    @Basic
    @javax.persistence.Column(name = "VISIT_YEAR", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getVisitYear() {
        return visitYear;
    }

    public void setVisitYear(Integer visitYear) {
        this.visitYear = visitYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpPtlVCompIeAnnDiary that = (SpPtlVCompIeAnnDiary) o;

        if (companyId != that.companyId) return false;
        if (id != that.id) return false;
        if (brAddr != null ? !brAddr.equals(that.brAddr) : that.brAddr != null) return false;
        if (brAddrD != null ? !brAddrD.equals(that.brAddrD) : that.brAddrD != null) return false;
        if (brAddrK != null ? !brAddrK.equals(that.brAddrK) : that.brAddrK != null) return false;
        if (brAddrP != null ? !brAddrP.equals(that.brAddrP) : that.brAddrP != null) return false;
        if (brAddrPe != null ? !brAddrPe.equals(that.brAddrPe) : that.brAddrPe != null) return false;
        if (brAddrTk != null ? !brAddrTk.equals(that.brAddrTk) : that.brAddrTk != null) return false;
        if (brDescr != null ? !brDescr.equals(that.brDescr) : that.brDescr != null) return false;
        if (compFullName != null ? !compFullName.equals(that.compFullName) : that.compFullName != null) return false;
        if (compIeAnnId != null ? !compIeAnnId.equals(that.compIeAnnId) : that.compIeAnnId != null) return false;
        if (compPhone != null ? !compPhone.equals(that.compPhone) : that.compPhone != null) return false;
        if (compPtlBranchId != null ? !compPtlBranchId.equals(that.compPtlBranchId) : that.compPtlBranchId != null)
            return false;
        if (doctorRegrequestId != null ? !doctorRegrequestId.equals(that.doctorRegrequestId) : that.doctorRegrequestId != null)
            return false;
        if (doctorRegrequestUserId != null ? !doctorRegrequestUserId.equals(that.doctorRegrequestUserId) : that.doctorRegrequestUserId != null)
            return false;
        if (visitDate != null ? !visitDate.equals(that.visitDate) : that.visitDate != null) return false;
        if (visitDateEpochSec != null ? !visitDateEpochSec.equals(that.visitDate) : that.visitDateEpochSec != null) return false;

        if (visitDurationMinutes != null ? !visitDurationMinutes.equals(that.visitDurationMinutes) : that.visitDurationMinutes != null)
            return false;
        if (visitTime != null ? !visitTime.equals(that.visitTime) : that.visitTime != null) return false;
        if (visitMonth != null ? !visitMonth.equals(that.visitMonth) : that.visitMonth != null) return false;
        if (visitYear != null ? !visitYear.equals(that.visitYear) : that.visitYear != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = doctorRegrequestUserId != null ? doctorRegrequestUserId.hashCode() : 0;
        result = 31 * result + (brDescr != null ? brDescr.hashCode() : 0);
        result = 31 * result + (brAddr != null ? brAddr.hashCode() : 0);
        result = 31 * result + (brAddrD != null ? brAddrD.hashCode() : 0);
        result = 31 * result + (brAddrK != null ? brAddrK.hashCode() : 0);
        result = 31 * result + (brAddrP != null ? brAddrP.hashCode() : 0);
        result = 31 * result + (brAddrPe != null ? brAddrPe.hashCode() : 0);
        result = 31 * result + (brAddrTk != null ? brAddrTk.hashCode() : 0);
        result = 31 * result + (compFullName != null ? compFullName.hashCode() : 0);
        result = 31 * result + (compPhone != null ? compPhone.hashCode() : 0);
        result = 31 * result + (doctorRegrequestId != null ? doctorRegrequestId.hashCode() : 0);
        result = 31 * result + (compPtlBranchId != null ? compPtlBranchId.hashCode() : 0);
        result = 31 * result + (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (companyId ^ (companyId >>> 32));
        result = 31 * result + (visitTime != null ? visitTime.hashCode() : 0);
        result = 31 * result + (visitDurationMinutes != null ? visitDurationMinutes.hashCode() : 0);
        result = 31 * result + (compIeAnnId != null ? compIeAnnId.hashCode() : 0);
        result = 31 * result + (visitDate != null ? visitDate.hashCode() : 0);
        result = 31 * result + (visitDateEpochSec != null ? visitDateEpochSec.hashCode() : 0);
        result = 31 * result + (visitMonth != null ? visitMonth.hashCode() : 0);
        result = 31 * result + (visitYear != null ? visitYear.hashCode() : 0);
        return result;
    }
}
