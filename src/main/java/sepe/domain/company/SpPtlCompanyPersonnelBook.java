package sepe.domain.company;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Evangelos on 18/4/2015.
 */
@Entity
@Table(name = "SP_PTL_COMPANY_PERSONNEL_BOOK", schema = "SP_PTL", catalog = "")
public class SpPtlCompanyPersonnelBook {
    private Long id;
    private Integer dailyCardId;
    private Integer incNum;
    private String name;
    private String surname;
    private String ikaId;
    private String speciality;
    private Integer dailySalary;
    private Timestamp dateOfRecruitment;
    private String startWork;
    private String workingHourStart;
    private String workingHourStop;
    private Timestamp restingDay;
    private String notes;
    private Timestamp submitTime;
    private Timestamp editTime;
    private SpPtlCompanyDailyCard spPtlCompanyDailyCard;

    @Id
    @GeneratedValue
    @Column(name = "ID",nullable = false, insertable = true, updatable = true, precision = 0)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public void setDailyCardId(Integer dailyCardId) {
        this.dailyCardId = dailyCardId;
    }

    @Basic
    @Column(name = "INC_NUM")
    public Integer getIncNum() {
        return incNum;
    }

    public void setIncNum(Integer incNum) {
        this.incNum = incNum;
    }

    @Basic
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "SURNAME")
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Basic
    @Column(name = "IKA_ID")
    public String getIkaId() {
        return ikaId;
    }

    public void setIkaId(String ikaId) {
        this.ikaId = ikaId;
    }

    @Basic
    @Column(name = "SPECIALITY")
    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    @Basic
    @Column(name = "DAILY_SALARY")
    public Integer getDailySalary() {
        return dailySalary;
    }

    public void setDailySalary(Integer dailySalary) {
        this.dailySalary = dailySalary;
    }

    @Basic
    @Column(name = "DATE_OF_RECRUITMENT")
    public Timestamp getDateOfRecruitment() {
        return dateOfRecruitment;
    }

    public void setDateOfRecruitment(Timestamp dateOfRecruitment) {
        this.dateOfRecruitment = dateOfRecruitment;
    }

    @Basic
    @Column(name = "START_WORK")
    public String getStartWork() {
        return startWork;
    }

    public void setStartWork(String startWork) {
        this.startWork = startWork;
    }

    @Basic
    @Column(name = "WORKING_HOUR_START")
    public String getWorkingHourStart() {
        return workingHourStart;
    }

    public void setWorkingHourStart(String workingHourStart) {
        this.workingHourStart = workingHourStart;
    }

    @Basic
    @Column(name = "WORKING_HOUR_STOP")
    public String getWorkingHourStop() {
        return workingHourStop;
    }

    public void setWorkingHourStop(String workingHourStop) {
        this.workingHourStop = workingHourStop;
    }

    @Basic
    @Column(name = "RESTING_DAY")
    public Timestamp getRestingDay() {
        return restingDay;
    }

    public void setRestingDay(Timestamp restingDay) {
        this.restingDay = restingDay;
    }

    @Basic
    @Column(name = "NOTES")
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Basic
    @Column(name = "SUBMIT_TIME")
    public Timestamp getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Timestamp submitTime) {
        this.submitTime = submitTime;
    }

    @Basic
    @Column(name = "EDIT_TIME")
    public Timestamp getEditTime() {
        return editTime;
    }

    public void setEditTime(Timestamp editTime) {
        this.editTime = editTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpPtlCompanyPersonnelBook that = (SpPtlCompanyPersonnelBook) o;

        if (dailyCardId != null ? !dailyCardId.equals(that.dailyCardId) : that.dailyCardId != null) return false;
        if (dailySalary != null ? !dailySalary.equals(that.dailySalary) : that.dailySalary != null) return false;
        if (dateOfRecruitment != null ? !dateOfRecruitment.equals(that.dateOfRecruitment) : that.dateOfRecruitment != null)
            return false;
        if (editTime != null ? !editTime.equals(that.editTime) : that.editTime != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (ikaId != null ? !ikaId.equals(that.ikaId) : that.ikaId != null) return false;
        if (incNum != null ? !incNum.equals(that.incNum) : that.incNum != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (notes != null ? !notes.equals(that.notes) : that.notes != null) return false;
        if (restingDay != null ? !restingDay.equals(that.restingDay) : that.restingDay != null) return false;
        if (speciality != null ? !speciality.equals(that.speciality) : that.speciality != null) return false;
        if (startWork != null ? !startWork.equals(that.startWork) : that.startWork != null) return false;
        if (submitTime != null ? !submitTime.equals(that.submitTime) : that.submitTime != null) return false;
        if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;
        if (workingHourStart != null ? !workingHourStart.equals(that.workingHourStart) : that.workingHourStart != null)
            return false;
        if (workingHourStop != null ? !workingHourStop.equals(that.workingHourStop) : that.workingHourStop != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (dailyCardId != null ? dailyCardId.hashCode() : 0);
        result = 31 * result + (incNum != null ? incNum.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (ikaId != null ? ikaId.hashCode() : 0);
        result = 31 * result + (speciality != null ? speciality.hashCode() : 0);
        result = 31 * result + (dailySalary != null ? dailySalary.hashCode() : 0);
        result = 31 * result + (dateOfRecruitment != null ? dateOfRecruitment.hashCode() : 0);
        result = 31 * result + (startWork != null ? startWork.hashCode() : 0);
        result = 31 * result + (workingHourStart != null ? workingHourStart.hashCode() : 0);
        result = 31 * result + (workingHourStop != null ? workingHourStop.hashCode() : 0);
        result = 31 * result + (restingDay != null ? restingDay.hashCode() : 0);
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        result = 31 * result + (submitTime != null ? submitTime.hashCode() : 0);
        result = 31 * result + (editTime != null ? editTime.hashCode() : 0);
        return result;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DAILY_CARD_ID", nullable = false, insertable = true, updatable = false) /* Name is the DB column name of the attribute */
    public SpPtlCompanyDailyCard getSpPtlCompanyDailyCard() {
        return spPtlCompanyDailyCard;
    }

    public void setSpPtlCompanyDailyCard(SpPtlCompanyDailyCard spPtlCompanyDailyCard) {
        this.spPtlCompanyDailyCard = spPtlCompanyDailyCard;
    }

    @Transient
    public Integer getDCard() {
        return dailyCardId;
    }

    @Transient
    public void setDCard(Integer projectId) {
        this.dailyCardId = projectId;
    }

    public void setDailyCard(Integer dailyCardId) {
        this.dailyCardId = dailyCardId;
    }
}
