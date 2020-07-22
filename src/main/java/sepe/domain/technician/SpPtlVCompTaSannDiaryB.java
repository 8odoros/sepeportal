package sepe.domain.technician;

        import javax.persistence.*;
        import java.sql.Timestamp;

/**
 * Created by Nikos on 6/14/2015.
 */
@Entity
@Table(name = "SP_PTL_V_COMP_TA_SANN_DIARY_B", schema = "SP_PTL", catalog = "")
public class SpPtlVCompTaSannDiaryB {
    private Long taRegrequestUserId;
    private String shipName;
    private String shipImo;
    private String shipType;
    private String compFullName;
    private String compPhone;
    private Long technicianRegrequestId;
    private Long compShipId;
    private Long id;
    private Long companyId;
    private String visitTime;
    private Integer visitDurationMinutes;
    private Long compTaSannId;
    private Timestamp visitDate;
    private Integer visitMonth;
    private Integer visitYear;
    private Long visitDateEpochSec;

    @Basic
    @Column(name = "TA_REGREQUEST_USER_ID")
    public Long getTaRegrequestUserId() {
        return taRegrequestUserId;
    }

    public void setTaRegrequestUserId(Long taRegrequestUserId) {
        this.taRegrequestUserId = taRegrequestUserId;
    }

    @Basic
    @Column(name = "SHIP_NAME")
    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    @Basic
    @Column(name = "SHIP_IMO")
    public String getShipImo() {
        return shipImo;
    }

    public void setShipImo(String shipImo) {
        this.shipImo = shipImo;
    }

    @Basic
    @Column(name = "SHIP_TYPE")
    public String getShipType() {
        return shipType;
    }

    public void setShipType(String shipType) {
        this.shipType = shipType;
    }

    @Basic
    @Column(name = "COMP_FULL_NAME")
    public String getCompFullName() {
        return compFullName;
    }

    public void setCompFullName(String compFullName) {
        this.compFullName = compFullName;
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
    @Column(name = "TECHNICIAN_REGREQUEST_ID")
    public Long getTechnicianRegrequestId() {
        return technicianRegrequestId;
    }

    public void setTechnicianRegrequestId(Long technicianRegrequestId) {
        this.technicianRegrequestId = technicianRegrequestId;
    }

    @Basic
    @Column(name = "COMP_SHIP_ID")
    public Long getCompShipId() {
        return compShipId;
    }

    public void setCompShipId(Long compShipId) {
        this.compShipId = compShipId;
    }

    @Id
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
    @Column(name = "VISIT_TIME")
    public String getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(String visitTime) {
        this.visitTime = visitTime;
    }

    @Basic
    @Column(name = "VISIT_DURATION_MINUTES")
    public Integer getVisitDurationMinutes() {
        return visitDurationMinutes;
    }

    public void setVisitDurationMinutes(Integer visitDurationMinutes) {
        this.visitDurationMinutes = visitDurationMinutes;
    }

    @Basic
    @Column(name = "COMP_TA_SANN_ID")
    public Long getCompTaSannId() {
        return compTaSannId;
    }

    public void setCompTaSannId(Long compTaSannId) {
        this.compTaSannId = compTaSannId;
    }

    @Basic
    @Column(name = "VISIT_DATE")
    public Timestamp getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Timestamp visitDate) {
        this.visitDate = visitDate;
    }

    @Basic
    @Column(name = "VISIT_MONTH")
    public Integer getVisitMonth() {
        return visitMonth;
    }

    public void setVisitMonth(Integer visitMonth) {
        this.visitMonth = visitMonth;
    }

    @Basic
    @Column(name = "VISIT_YEAR")
    public Integer getVisitYear() {
        return visitYear;
    }

    public void setVisitYear(Integer visitYear) {
        this.visitYear = visitYear;
    }

    @Basic
    @Column(name = "VISIT_DATE_EPOCH_SEC")
    public Long getVisitDateEpochSec() {
        return visitDateEpochSec;
    }

    public void setVisitDateEpochSec(Long visitDateEpochSec) {
        this.visitDateEpochSec = visitDateEpochSec;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpPtlVCompTaSannDiaryB that = (SpPtlVCompTaSannDiaryB) o;

        if (taRegrequestUserId != that.taRegrequestUserId) return false;
        if (id != that.id) return false;
        if (companyId != that.companyId) return false;
        if (shipName != null ? !shipName.equals(that.shipName) : that.shipName != null) return false;
        if (shipImo != null ? !shipImo.equals(that.shipImo) : that.shipImo != null) return false;
        if (shipType != null ? !shipType.equals(that.shipType) : that.shipType != null) return false;
        if (compFullName != null ? !compFullName.equals(that.compFullName) : that.compFullName != null) return false;
        if (compPhone != null ? !compPhone.equals(that.compPhone) : that.compPhone != null) return false;
        if (technicianRegrequestId != null ? !technicianRegrequestId.equals(that.technicianRegrequestId) : that.technicianRegrequestId != null)
            return false;
        if (compShipId != null ? !compShipId.equals(that.compShipId) : that.compShipId != null) return false;
        if (visitTime != null ? !visitTime.equals(that.visitTime) : that.visitTime != null) return false;
        if (visitDurationMinutes != null ? !visitDurationMinutes.equals(that.visitDurationMinutes) : that.visitDurationMinutes != null)
            return false;
        if (compTaSannId != null ? !compTaSannId.equals(that.compTaSannId) : that.compTaSannId != null) return false;
        if (visitDate != null ? !visitDate.equals(that.visitDate) : that.visitDate != null) return false;
        if (visitMonth != null ? !visitMonth.equals(that.visitMonth) : that.visitMonth != null) return false;
        if (visitYear != null ? !visitYear.equals(that.visitYear) : that.visitYear != null) return false;
        if (visitDateEpochSec != null ? !visitDateEpochSec.equals(that.visitDateEpochSec) : that.visitDateEpochSec != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (taRegrequestUserId ^ (taRegrequestUserId >>> 32));
        result = 31 * result + (shipName != null ? shipName.hashCode() : 0);
        result = 31 * result + (shipImo != null ? shipImo.hashCode() : 0);
        result = 31 * result + (shipType != null ? shipType.hashCode() : 0);
        result = 31 * result + (compFullName != null ? compFullName.hashCode() : 0);
        result = 31 * result + (compPhone != null ? compPhone.hashCode() : 0);
        result = 31 * result + (technicianRegrequestId != null ? technicianRegrequestId.hashCode() : 0);
        result = 31 * result + (compShipId != null ? compShipId.hashCode() : 0);
        result = 31 * result + (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (companyId ^ (companyId >>> 32));
        result = 31 * result + (visitTime != null ? visitTime.hashCode() : 0);
        result = 31 * result + (visitDurationMinutes != null ? visitDurationMinutes.hashCode() : 0);
        result = 31 * result + (compTaSannId != null ? compTaSannId.hashCode() : 0);
        result = 31 * result + (visitDate != null ? visitDate.hashCode() : 0);
        result = 31 * result + (visitMonth != null ? visitMonth.hashCode() : 0);
        result = 31 * result + (visitYear != null ? visitYear.hashCode() : 0);
        result = 31 * result + (visitDateEpochSec != null ? visitDateEpochSec.hashCode() : 0);
        return result;
    }
}
