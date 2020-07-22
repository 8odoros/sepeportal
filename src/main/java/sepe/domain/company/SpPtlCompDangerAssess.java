package sepe.domain.company;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * Created by Nikos on 6/10/2015.
 */
@Entity
@javax.persistence.Table(name = "SP_PTL_COMP_DANGER_ASSESS", schema = "SP_PTL", catalog = "")
public class SpPtlCompDangerAssess {
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

    private Long docId;

    @Basic
    @javax.persistence.Column(name = "DOC_ID")
    public Long getDocId() {
        return docId;
    }

    public void setDocId(Long docId) {
        this.docId = docId;
    }

    private Timestamp dateUpdated;

    @Basic
    @javax.persistence.Column(name = "DATE_UPDATED")
    public Timestamp getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Timestamp dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    private Integer dngAreaStatus;

    @Basic
    @javax.persistence.Column(name = "DNG_AREA_STATUS")
    public Integer getDngAreaStatus() {
        return dngAreaStatus;
    }

    public void setDngAreaStatus(Integer dngAreaStatus) {
        this.dngAreaStatus = dngAreaStatus;
    }

    private Integer dngEquipmentStatus;

    @Basic
    @javax.persistence.Column(name = "DNG_EQUIPMENT_STATUS")
    public Integer getDngEquipmentStatus() {
        return dngEquipmentStatus;
    }

    public void setDngEquipmentStatus(Integer dngEquipmentStatus) {
        this.dngEquipmentStatus = dngEquipmentStatus;
    }

    private Integer dngVehicleStatus;

    @Basic
    @javax.persistence.Column(name = "DNG_VEHICLE_STATUS")
    public Integer getDngVehicleStatus() {
        return dngVehicleStatus;
    }

    public void setDngVehicleStatus(Integer dngVehicleStatus) {
        this.dngVehicleStatus = dngVehicleStatus;
    }

    private BigInteger dngElectricalStatus;

    @Basic
    @javax.persistence.Column(name = "DNG_ELECTRICAL_STATUS")
    public BigInteger getDngElectricalStatus() {
        return dngElectricalStatus;
    }

    public void setDngElectricalStatus(BigInteger dngElectricalStatus) {
        this.dngElectricalStatus = dngElectricalStatus;
    }

    private Integer dngFireStatus;

    @Basic
    @javax.persistence.Column(name = "DNG_FIRE_STATUS")
    public Integer getDngFireStatus() {
        return dngFireStatus;
    }

    public void setDngFireStatus(Integer dngFireStatus) {
        this.dngFireStatus = dngFireStatus;
    }

    private Integer dngJobpositionStatus;

    @Basic
    @javax.persistence.Column(name = "DNG_JOBPOSITION_STATUS")
    public Integer getDngJobpositionStatus() {
        return dngJobpositionStatus;
    }

    public void setDngJobpositionStatus(Integer dngJobpositionStatus) {
        this.dngJobpositionStatus = dngJobpositionStatus;
    }

    private Integer dngNoiseStatus;

    @Basic
    @javax.persistence.Column(name = "DNG_NOISE_STATUS")
    public Integer getDngNoiseStatus() {
        return dngNoiseStatus;
    }

    public void setDngNoiseStatus(Integer dngNoiseStatus) {
        this.dngNoiseStatus = dngNoiseStatus;
    }

    private String dngNoiseDescr;

    @Basic
    @javax.persistence.Column(name = "DNG_NOISE_DESCR")
    public String getDngNoiseDescr() {
        return dngNoiseDescr;
    }

    public void setDngNoiseDescr(String dngNoiseDescr) {
        this.dngNoiseDescr = dngNoiseDescr;
    }

    private Integer dngChemicalStatus;

    @Basic
    @javax.persistence.Column(name = "DNG_CHEMICAL_STATUS")
    public Integer getDngChemicalStatus() {
        return dngChemicalStatus;
    }

    public void setDngChemicalStatus(Integer dngChemicalStatus) {
        this.dngChemicalStatus = dngChemicalStatus;
    }

    private String dngChemicalDescr;

    @Basic
    @javax.persistence.Column(name = "DNG_CHEMICAL_DESCR")
    public String getDngChemicalDescr() {
        return dngChemicalDescr;
    }

    public void setDngChemicalDescr(String dngChemicalDescr) {
        this.dngChemicalDescr = dngChemicalDescr;
    }

    private Integer dngOtherStatus;

    @Basic
    @javax.persistence.Column(name = "DNG_OTHER_STATUS")
    public Integer getDngOtherStatus() {
        return dngOtherStatus;
    }

    public void setDngOtherStatus(Integer dngOtherStatus) {
        this.dngOtherStatus = dngOtherStatus;
    }

    private String dngOtherDescr;

    @Basic
    @javax.persistence.Column(name = "DNG_OTHER_DESCR")
    public String getDngOtherDescr() {
        return dngOtherDescr;
    }

    public void setDngOtherDescr(String dngOtherDescr) {
        this.dngOtherDescr = dngOtherDescr;
    }

    private Integer dngEmplhealthStatus;

    @Basic
    @javax.persistence.Column(name = "DNG_EMPLHEALTH_STATUS")
    public Integer getDngEmplhealthStatus() {
        return dngEmplhealthStatus;
    }

    public void setDngEmplhealthStatus(Integer dngEmplhealthStatus) {
        this.dngEmplhealthStatus = dngEmplhealthStatus;
    }

    private String dngEmplhealthDescr;

    @Basic
    @javax.persistence.Column(name = "DNG_EMPLHEALTH_DESCR")
    public String getDngEmplhealthDescr() {
        return dngEmplhealthDescr;
    }

    public void setDngEmplhealthDescr(String dngEmplhealthDescr) {
        this.dngEmplhealthDescr = dngEmplhealthDescr;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpPtlCompDangerAssess that = (SpPtlCompDangerAssess) o;

        if (id != that.id) return false;
        if (companyId != that.companyId) return false;
        if (subStatus != null ? !subStatus.equals(that.subStatus) : that.subStatus != null) return false;
        if (reqStatus != null ? !reqStatus.equals(that.reqStatus) : that.reqStatus != null) return false;
        if (protNo != null ? !protNo.equals(that.protNo) : that.protNo != null) return false;
        if (protYear != null ? !protYear.equals(that.protYear) : that.protYear != null) return false;
        if (protDate != null ? !protDate.equals(that.protDate) : that.protDate != null) return false;
        if (submitTime != null ? !submitTime.equals(that.submitTime) : that.submitTime != null) return false;
        if (department != null ? !department.equals(that.department) : that.department != null) return false;
        if (attachedDocId != null ? !attachedDocId.equals(that.attachedDocId) : that.attachedDocId != null)
            return false;
        if (notes != null ? !notes.equals(that.notes) : that.notes != null) return false;
        if (caseId != null ? !caseId.equals(that.caseId) : that.caseId != null) return false;
        if (docId != null ? !docId.equals(that.docId) : that.docId != null) return false;
        if (dateUpdated != null ? !dateUpdated.equals(that.dateUpdated) : that.dateUpdated != null) return false;
        if (dngAreaStatus != null ? !dngAreaStatus.equals(that.dngAreaStatus) : that.dngAreaStatus != null)
            return false;
        if (dngEquipmentStatus != null ? !dngEquipmentStatus.equals(that.dngEquipmentStatus) : that.dngEquipmentStatus != null)
            return false;
        if (dngVehicleStatus != null ? !dngVehicleStatus.equals(that.dngVehicleStatus) : that.dngVehicleStatus != null)
            return false;
        if (dngElectricalStatus != null ? !dngElectricalStatus.equals(that.dngElectricalStatus) : that.dngElectricalStatus != null)
            return false;
        if (dngFireStatus != null ? !dngFireStatus.equals(that.dngFireStatus) : that.dngFireStatus != null)
            return false;
        if (dngJobpositionStatus != null ? !dngJobpositionStatus.equals(that.dngJobpositionStatus) : that.dngJobpositionStatus != null)
            return false;
        if (dngNoiseStatus != null ? !dngNoiseStatus.equals(that.dngNoiseStatus) : that.dngNoiseStatus != null)
            return false;
        if (dngNoiseDescr != null ? !dngNoiseDescr.equals(that.dngNoiseDescr) : that.dngNoiseDescr != null)
            return false;
        if (dngChemicalStatus != null ? !dngChemicalStatus.equals(that.dngChemicalStatus) : that.dngChemicalStatus != null)
            return false;
        if (dngChemicalDescr != null ? !dngChemicalDescr.equals(that.dngChemicalDescr) : that.dngChemicalDescr != null)
            return false;
        if (dngOtherStatus != null ? !dngOtherStatus.equals(that.dngOtherStatus) : that.dngOtherStatus != null)
            return false;
        if (dngOtherDescr != null ? !dngOtherDescr.equals(that.dngOtherDescr) : that.dngOtherDescr != null)
            return false;
        if (dngEmplhealthStatus != null ? !dngEmplhealthStatus.equals(that.dngEmplhealthStatus) : that.dngEmplhealthStatus != null)
            return false;
        if (dngEmplhealthDescr != null ? !dngEmplhealthDescr.equals(that.dngEmplhealthDescr) : that.dngEmplhealthDescr != null)
            return false;
        if (branch0Id != null ? !branch0Id.equals(that.branch0Id) : that.branch0Id != null) return false;
        if (branch1Id != null ? !branch1Id.equals(that.branch1Id) : that.branch1Id != null) return false;

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
        result = 31 * result + (protDate != null ? protDate.hashCode() : 0);
        result = 31 * result + (submitTime != null ? submitTime.hashCode() : 0);
        result = 31 * result + (department != null ? department.hashCode() : 0);
        result = 31 * result + (attachedDocId != null ? attachedDocId.hashCode() : 0);
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        result = 31 * result + (caseId != null ? caseId.hashCode() : 0);
        result = 31 * result + (docId != null ? docId.hashCode() : 0);
        result = 31 * result + (dateUpdated != null ? dateUpdated.hashCode() : 0);
        result = 31 * result + (dngAreaStatus != null ? dngAreaStatus.hashCode() : 0);
        result = 31 * result + (dngEquipmentStatus != null ? dngEquipmentStatus.hashCode() : 0);
        result = 31 * result + (dngVehicleStatus != null ? dngVehicleStatus.hashCode() : 0);
        result = 31 * result + (dngElectricalStatus != null ? dngElectricalStatus.hashCode() : 0);
        result = 31 * result + (dngFireStatus != null ? dngFireStatus.hashCode() : 0);
        result = 31 * result + (dngJobpositionStatus != null ? dngJobpositionStatus.hashCode() : 0);
        result = 31 * result + (dngNoiseStatus != null ? dngNoiseStatus.hashCode() : 0);
        result = 31 * result + (dngNoiseDescr != null ? dngNoiseDescr.hashCode() : 0);
        result = 31 * result + (dngChemicalStatus != null ? dngChemicalStatus.hashCode() : 0);
        result = 31 * result + (dngChemicalDescr != null ? dngChemicalDescr.hashCode() : 0);
        result = 31 * result + (dngOtherStatus != null ? dngOtherStatus.hashCode() : 0);
        result = 31 * result + (dngOtherDescr != null ? dngOtherDescr.hashCode() : 0);
        result = 31 * result + (dngEmplhealthStatus != null ? dngEmplhealthStatus.hashCode() : 0);
        result = 31 * result + (dngEmplhealthDescr != null ? dngEmplhealthDescr.hashCode() : 0);
        result = 31 * result + (branch0Id != null ? branch0Id.hashCode() : 0);
        result = 31 * result + (branch1Id != null ? branch1Id.hashCode() : 0);
        return result;
    }
}
