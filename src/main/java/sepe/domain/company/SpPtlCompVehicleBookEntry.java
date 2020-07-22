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
@javax.persistence.Table(name = "SP_PTL_COMP_VEHICLE_BOOK_ENTRY", schema = "SP_PTL", catalog = "")
public class SpPtlCompVehicleBookEntry {
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

    private String driverFirstname;

    @Basic
    @javax.persistence.Column(name = "DRIVER_FIRSTNAME")
    public String getDriverFirstname() {
        return driverFirstname;
    }

    public void setDriverFirstname(String driverFirstname) {
        this.driverFirstname = driverFirstname;
    }

    private String driverLastname;

    @Basic
    @javax.persistence.Column(name = "DRIVER_LASTNAME")
    public String getDriverLastname() {
        return driverLastname;
    }

    public void setDriverLastname(String driverLastname) {
        this.driverLastname = driverLastname;
    }

    private String driverBirthplace;

    @Basic
    @javax.persistence.Column(name = "DRIVER_BIRTHPLACE")
    public String getDriverBirthplace() {
        return driverBirthplace;
    }

    public void setDriverBirthplace(String driverBirthplace) {
        this.driverBirthplace = driverBirthplace;
    }

    private Timestamp driverBirthdate;

    @Basic
    @javax.persistence.Column(name = "DRIVER_BIRTHDATE")
    public Timestamp getDriverBirthdate() {
        return driverBirthdate;
    }

    public void setDriverBirthdate(Timestamp driverBirthdate) {
        this.driverBirthdate = driverBirthdate;
    }

    private String driverAddr;

    @Basic
    @javax.persistence.Column(name = "DRIVER_ADDR")
    public String getDriverAddr() {
        return driverAddr;
    }

    public void setDriverAddr(String driverAddr) {
        this.driverAddr = driverAddr;
    }

    private Long driverCardType;

    @Basic
    @javax.persistence.Column(name = "DRIVER_CARD_TYPE")
    public Long getDriverCardType() {
        return driverCardType;
    }

    public void setDriverCardType(Long driverCardType) {
        this.driverCardType = driverCardType;
    }

    private String driverCardNumber;

    @Basic
    @javax.persistence.Column(name = "DRIVER_CARD_NUMBER")
    public String getDriverCardNumber() {
        return driverCardNumber;
    }

    public void setDriverCardNumber(String driverCardNumber) {
        this.driverCardNumber = driverCardNumber;
    }

    private String driverCardIssuingAuth;

    @Basic
    @javax.persistence.Column(name = "DRIVER_CARD_ISSUING_AUTH")
    public String getDriverCardIssuingAuth() {
        return driverCardIssuingAuth;
    }

    public void setDriverCardIssuingAuth(String driverCardIssuingAuth) {
        this.driverCardIssuingAuth = driverCardIssuingAuth;
    }

    private String driverAfm;

    @Basic
    @javax.persistence.Column(name = "DRIVER_AFM")
    public String getDriverAfm() {
        return driverAfm;
    }

    public void setDriverAfm(String driverAfm) {
        this.driverAfm = driverAfm;
    }

    private String driverLicenceNum;

    @Basic
    @javax.persistence.Column(name = "DRIVER_LICENCE_NUM")
    public String getDriverLicenceNum() {
        return driverLicenceNum;
    }

    public void setDriverLicenceNum(String driverLicenceNum) {
        this.driverLicenceNum = driverLicenceNum;
    }

    private String assistFirstname;

    @Basic
    @javax.persistence.Column(name = "ASSIST_FIRSTNAME")
    public String getAssistFirstname() {
        return assistFirstname;
    }

    public void setAssistFirstname(String assistFirstname) {
        this.assistFirstname = assistFirstname;
    }

    private String assistLastname;

    @Basic
    @javax.persistence.Column(name = "ASSIST_LASTNAME")
    public String getAssistLastname() {
        return assistLastname;
    }

    public void setAssistLastname(String assistLastname) {
        this.assistLastname = assistLastname;
    }

    private String assistBirthplace;

    @Basic
    @javax.persistence.Column(name = "ASSIST_BIRTHPLACE")
    public String getAssistBirthplace() {
        return assistBirthplace;
    }

    public void setAssistBirthplace(String assistBirthplace) {
        this.assistBirthplace = assistBirthplace;
    }

    private Timestamp assistBirthdate;

    @Basic
    @javax.persistence.Column(name = "ASSIST_BIRTHDATE")
    public Timestamp getAssistBirthdate() {
        return assistBirthdate;
    }

    public void setAssistBirthdate(Timestamp assistBirthdate) {
        this.assistBirthdate = assistBirthdate;
    }

    private String assistAddr;

    @Basic
    @javax.persistence.Column(name = "ASSIST_ADDR")
    public String getAssistAddr() {
        return assistAddr;
    }

    public void setAssistAddr(String assistAddr) {
        this.assistAddr = assistAddr;
    }

    private Long assistCardType;

    @Basic
    @javax.persistence.Column(name = "ASSIST_CARD_TYPE")
    public Long getAssistCardType() {
        return assistCardType;
    }

    public void setAssistCardType(Long assistCardType) {
        this.assistCardType = assistCardType;
    }

    private String assistCardNumber;

    @Basic
    @javax.persistence.Column(name = "ASSIST_CARD_NUMBER")
    public String getAssistCardNumber() {
        return assistCardNumber;
    }

    public void setAssistCardNumber(String assistCardNumber) {
        this.assistCardNumber = assistCardNumber;
    }

    private String assistIssuingAuth;

    @Basic
    @javax.persistence.Column(name = "ASSIST_ISSUING_AUTH")
    public String getAssistIssuingAuth() {
        return assistIssuingAuth;
    }

    public void setAssistIssuingAuth(String assistIssuingAuth) {
        this.assistIssuingAuth = assistIssuingAuth;
    }

    private String assistAfm;

    @Basic
    @javax.persistence.Column(name = "ASSIST_AFM")
    public String getAssistAfm() {
        return assistAfm;
    }

    public void setAssistAfm(String assistAfm) {
        this.assistAfm = assistAfm;
    }

    private String assistLicenceNum;

    @Basic
    @javax.persistence.Column(name = "ASSIST_LICENCE_NUM")
    public String getAssistLicenceNum() {
        return assistLicenceNum;
    }

    public void setAssistLicenceNum(String assistLicenceNum) {
        this.assistLicenceNum = assistLicenceNum;
    }

    private String routeSource;

    @Basic
    @javax.persistence.Column(name = "ROUTE_SOURCE")
    public String getRouteSource() {
        return routeSource;
    }

    public void setRouteSource(String routeSource) {
        this.routeSource = routeSource;
    }

    private String activityStartTime;

    @Basic
    @javax.persistence.Column(name = "ACTIVITY_START_TIME")
    public String getActivityStartTime() {
        return activityStartTime;
    }

    public void setActivityStartTime(String activityStartTime) {
        this.activityStartTime = activityStartTime;
    }

    private String driveStartTime;

    @Basic
    @javax.persistence.Column(name = "DRIVE_START_TIME")
    public String getDriveStartTime() {
        return driveStartTime;
    }

    public void setDriveStartTime(String driveStartTime) {
        this.driveStartTime = driveStartTime;
    }

    private String arivalTime;

    @Basic
    @javax.persistence.Column(name = "ARIVAL_TIME")
    public String getArivalTime() {
        return arivalTime;
    }

    public void setArivalTime(String arivalTime) {
        this.arivalTime = arivalTime;
    }

    private String routeDestination;

    @Basic
    @javax.persistence.Column(name = "ROUTE_DESTINATION")
    public String getRouteDestination() {
        return routeDestination;
    }

    public void setRouteDestination(String routeDestination) {
        this.routeDestination = routeDestination;
    }

    private String repairDuration;

    @Basic
    @javax.persistence.Column(name = "REPAIR_DURATION")
    public String getRepairDuration() {
        return repairDuration;
    }

    public void setRepairDuration(String repairDuration) {
        this.repairDuration = repairDuration;
    }

    private String loadType;

    @Basic
    @javax.persistence.Column(name = "LOAD_TYPE")
    public String getLoadType() {
        return loadType;
    }

    public void setLoadType(String loadType) {
        this.loadType = loadType;
    }

    private String breakDuration;

    @Basic
    @javax.persistence.Column(name = "BREAK_DURATION")
    public String getBreakDuration() {
        return breakDuration;
    }

    public void setBreakDuration(String breakDuration) {
        this.breakDuration = breakDuration;
    }

    private String betweenRouteDuration;

    @Basic
    @javax.persistence.Column(name = "BETWEEN_ROUTE_DURATION")
    public String getBetweenRouteDuration() {
        return betweenRouteDuration;
    }

    public void setBetweenRouteDuration(String betweenRouteDuration) {
        this.betweenRouteDuration = betweenRouteDuration;
    }

    private String activityEndTime;

    @Basic
    @javax.persistence.Column(name = "ACTIVITY_END_TIME")
    public String getActivityEndTime() {
        return activityEndTime;
    }

    public void setActivityEndTime(String activityEndTime) {
        this.activityEndTime = activityEndTime;
    }

    private String jobHours;

    @Basic
    @javax.persistence.Column(name = "JOB_HOURS")
    public String getJobHours() {
        return jobHours;
    }

    public void setJobHours(String jobHours) {
        this.jobHours = jobHours;
    }

    private String activityHours;

    @Basic
    @javax.persistence.Column(name = "ACTIVITY_HOURS")
    public String getActivityHours() {
        return activityHours;
    }

    public void setActivityHours(String activityHours) {
        this.activityHours = activityHours;
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

    private Long spPtlCompVehicleBookId;

    @Basic
    @javax.persistence.Column(name = "SP_PTL_COMP_VEHICLE_BOOK_ID")
    public Long getSpPtlCompVehicleBookId() {
        return spPtlCompVehicleBookId;
    }

    public void setSpPtlCompVehicleBookId(Long spPtlCompVehicleBookId) {
        this.spPtlCompVehicleBookId = spPtlCompVehicleBookId;
    }

    private Timestamp dateCreated;

    @Basic
    @javax.persistence.Column(name = "DATE_CREATED")
    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
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

    private String timeCreated;

    @Basic
    @javax.persistence.Column(name = "TIME_CREATED")
    public String getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(String timeCreated) {
        this.timeCreated = timeCreated;
    }

    private String timeUpdated;

    @Basic
    @javax.persistence.Column(name = "TIME_UPDATED")
    public String getTimeUpdated() {
        return timeUpdated;
    }

    public void setTimeUpdated(String timeUpdated) {
        this.timeUpdated = timeUpdated;
    }

    private Integer aa;

    @Basic
    @javax.persistence.Column(name = "AA")
    public Integer getAa() {
        return aa;
    }

    public void setAa(Integer aa) {
        this.aa = aa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpPtlCompVehicleBookEntry that = (SpPtlCompVehicleBookEntry) o;

        if (id != that.id) return false;
        if (companyId != that.companyId) return false;
        if (spPtlCompVehicleBookId != that.spPtlCompVehicleBookId) return false;
        if (driverFirstname != null ? !driverFirstname.equals(that.driverFirstname) : that.driverFirstname != null)
            return false;
        if (driverLastname != null ? !driverLastname.equals(that.driverLastname) : that.driverLastname != null)
            return false;
        if (driverBirthplace != null ? !driverBirthplace.equals(that.driverBirthplace) : that.driverBirthplace != null)
            return false;
        if (driverBirthdate != null ? !driverBirthdate.equals(that.driverBirthdate) : that.driverBirthdate != null)
            return false;
        if (driverAddr != null ? !driverAddr.equals(that.driverAddr) : that.driverAddr != null) return false;
        if (driverCardType != null ? !driverCardType.equals(that.driverCardType) : that.driverCardType != null)
            return false;
        if (driverCardNumber != null ? !driverCardNumber.equals(that.driverCardNumber) : that.driverCardNumber != null)
            return false;
        if (driverCardIssuingAuth != null ? !driverCardIssuingAuth.equals(that.driverCardIssuingAuth) : that.driverCardIssuingAuth != null)
            return false;
        if (driverAfm != null ? !driverAfm.equals(that.driverAfm) : that.driverAfm != null) return false;
        if (driverLicenceNum != null ? !driverLicenceNum.equals(that.driverLicenceNum) : that.driverLicenceNum != null)
            return false;
        if (assistFirstname != null ? !assistFirstname.equals(that.assistFirstname) : that.assistFirstname != null)
            return false;
        if (assistLastname != null ? !assistLastname.equals(that.assistLastname) : that.assistLastname != null)
            return false;
        if (assistBirthplace != null ? !assistBirthplace.equals(that.assistBirthplace) : that.assistBirthplace != null)
            return false;
        if (assistBirthdate != null ? !assistBirthdate.equals(that.assistBirthdate) : that.assistBirthdate != null)
            return false;
        if (assistAddr != null ? !assistAddr.equals(that.assistAddr) : that.assistAddr != null) return false;
        if (assistCardType != null ? !assistCardType.equals(that.assistCardType) : that.assistCardType != null)
            return false;
        if (assistCardNumber != null ? !assistCardNumber.equals(that.assistCardNumber) : that.assistCardNumber != null)
            return false;
        if (assistIssuingAuth != null ? !assistIssuingAuth.equals(that.assistIssuingAuth) : that.assistIssuingAuth != null)
            return false;
        if (assistAfm != null ? !assistAfm.equals(that.assistAfm) : that.assistAfm != null) return false;
        if (assistLicenceNum != null ? !assistLicenceNum.equals(that.assistLicenceNum) : that.assistLicenceNum != null)
            return false;
        if (routeSource != null ? !routeSource.equals(that.routeSource) : that.routeSource != null) return false;
        if (activityStartTime != null ? !activityStartTime.equals(that.activityStartTime) : that.activityStartTime != null)
            return false;
        if (driveStartTime != null ? !driveStartTime.equals(that.driveStartTime) : that.driveStartTime != null)
            return false;
        if (arivalTime != null ? !arivalTime.equals(that.arivalTime) : that.arivalTime != null) return false;
        if (routeDestination != null ? !routeDestination.equals(that.routeDestination) : that.routeDestination != null)
            return false;
        if (repairDuration != null ? !repairDuration.equals(that.repairDuration) : that.repairDuration != null)
            return false;
        if (loadType != null ? !loadType.equals(that.loadType) : that.loadType != null) return false;
        if (breakDuration != null ? !breakDuration.equals(that.breakDuration) : that.breakDuration != null)
            return false;
        if (betweenRouteDuration != null ? !betweenRouteDuration.equals(that.betweenRouteDuration) : that.betweenRouteDuration != null)
            return false;
        if (activityEndTime != null ? !activityEndTime.equals(that.activityEndTime) : that.activityEndTime != null)
            return false;
        if (jobHours != null ? !jobHours.equals(that.jobHours) : that.jobHours != null) return false;
        if (activityHours != null ? !activityHours.equals(that.activityHours) : that.activityHours != null)
            return false;
        if (notes != null ? !notes.equals(that.notes) : that.notes != null) return false;
        if (dateCreated != null ? !dateCreated.equals(that.dateCreated) : that.dateCreated != null) return false;
        if (dateUpdated != null ? !dateUpdated.equals(that.dateUpdated) : that.dateUpdated != null) return false;
        if (timeCreated != null ? !timeCreated.equals(that.timeCreated) : that.timeCreated != null) return false;
        if (timeUpdated != null ? !timeUpdated.equals(that.timeUpdated) : that.timeUpdated != null) return false;
        if (aa != null ? !aa.equals(that.aa) : that.aa != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (companyId ^ (companyId >>> 32));
        result = 31 * result + (driverFirstname != null ? driverFirstname.hashCode() : 0);
        result = 31 * result + (driverLastname != null ? driverLastname.hashCode() : 0);
        result = 31 * result + (driverBirthplace != null ? driverBirthplace.hashCode() : 0);
        result = 31 * result + (driverBirthdate != null ? driverBirthdate.hashCode() : 0);
        result = 31 * result + (driverAddr != null ? driverAddr.hashCode() : 0);
        result = 31 * result + (driverCardType != null ? driverCardType.hashCode() : 0);
        result = 31 * result + (driverCardNumber != null ? driverCardNumber.hashCode() : 0);
        result = 31 * result + (driverCardIssuingAuth != null ? driverCardIssuingAuth.hashCode() : 0);
        result = 31 * result + (driverAfm != null ? driverAfm.hashCode() : 0);
        result = 31 * result + (driverLicenceNum != null ? driverLicenceNum.hashCode() : 0);
        result = 31 * result + (assistFirstname != null ? assistFirstname.hashCode() : 0);
        result = 31 * result + (assistLastname != null ? assistLastname.hashCode() : 0);
        result = 31 * result + (assistBirthplace != null ? assistBirthplace.hashCode() : 0);
        result = 31 * result + (assistBirthdate != null ? assistBirthdate.hashCode() : 0);
        result = 31 * result + (assistAddr != null ? assistAddr.hashCode() : 0);
        result = 31 * result + (assistCardType != null ? assistCardType.hashCode() : 0);
        result = 31 * result + (assistCardNumber != null ? assistCardNumber.hashCode() : 0);
        result = 31 * result + (assistIssuingAuth != null ? assistIssuingAuth.hashCode() : 0);
        result = 31 * result + (assistAfm != null ? assistAfm.hashCode() : 0);
        result = 31 * result + (assistLicenceNum != null ? assistLicenceNum.hashCode() : 0);
        result = 31 * result + (routeSource != null ? routeSource.hashCode() : 0);
        result = 31 * result + (activityStartTime != null ? activityStartTime.hashCode() : 0);
        result = 31 * result + (driveStartTime != null ? driveStartTime.hashCode() : 0);
        result = 31 * result + (arivalTime != null ? arivalTime.hashCode() : 0);
        result = 31 * result + (routeDestination != null ? routeDestination.hashCode() : 0);
        result = 31 * result + (repairDuration != null ? repairDuration.hashCode() : 0);
        result = 31 * result + (loadType != null ? loadType.hashCode() : 0);
        result = 31 * result + (breakDuration != null ? breakDuration.hashCode() : 0);
        result = 31 * result + (betweenRouteDuration != null ? betweenRouteDuration.hashCode() : 0);
        result = 31 * result + (activityEndTime != null ? activityEndTime.hashCode() : 0);
        result = 31 * result + (jobHours != null ? jobHours.hashCode() : 0);
        result = 31 * result + (activityHours != null ? activityHours.hashCode() : 0);
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        result = 31 * result + (int) (spPtlCompVehicleBookId ^ (spPtlCompVehicleBookId >>> 32));
        result = 31 * result + (dateCreated != null ? dateCreated.hashCode() : 0);
        result = 31 * result + (dateUpdated != null ? dateUpdated.hashCode() : 0);
        result = 31 * result + (timeCreated != null ? timeCreated.hashCode() : 0);
        result = 31 * result + (timeUpdated != null ? timeUpdated.hashCode() : 0);
        result = 31 * result + (aa != null ? aa.hashCode() : 0);
        return result;
    }
}
