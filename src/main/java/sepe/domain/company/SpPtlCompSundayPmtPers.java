package sepe.domain.company;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Nick on 4/25/2015.
 */
@Entity
@Table(name = "SP_PTL_COMP_SUNDAY_PMT_PERS", schema = "SP_PTL", catalog = "")
public class SpPtlCompSundayPmtPers {
    private Long id;
    //private Long sundayPmtId;
    private Integer incNum;
    private String empFirstname;
    private String empLastname;
    private String empIka;
    private String empSpeciality;
    private String empWorkHourStart;
    private String empWorkHourStop;
    private Timestamp empAlternateRestingDay;
    private String inspectorComments;
    private String empAfm;
    private String empAmka;
    private String empSex;
    private Long companyId;

    @Id
    @GeneratedValue
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /*
    @Basic
    @Column(name = "SUNDAY_PMT_ID")
    public Long getSundayPmtId() {
        return sundayPmtId;
    }

    public void setSundayPmtId(Long sundayPmtId) {
        this.sundayPmtId = sundayPmtId;
    }
	*/

    @Basic
    @Column(name = "INC_NUM")
    public Integer getIncNum() {
        return incNum;
    }

    public void setIncNum(Integer incNum) {
        this.incNum = incNum;
    }

    @Basic
    @Column(name = "EMP_FIRSTNAME")
    public String getEmpFirstname() {
        return empFirstname;
    }

    public void setEmpFirstname(String empFirstname) {
        this.empFirstname = empFirstname;
    }

    @Basic
    @Column(name = "EMP_LASTNAME")
    public String getEmpLastname() {
        return empLastname;
    }

    public void setEmpLastname(String empLastname) {
        this.empLastname = empLastname;
    }

    @Basic
    @Column(name = "EMP_IKA")
    public String getEmpIka() {
        return empIka;
    }

    public void setEmpIka(String empIka) {
        this.empIka = empIka;
    }

    @Basic
    @Column(name = "EMP_SPECIALITY")
    public String getEmpSpeciality() {
        return empSpeciality;
    }

    public void setEmpSpeciality(String empSpeciality) {
        this.empSpeciality = empSpeciality;
    }

    @Basic
    @Column(name = "EMP_WORK_HOUR_START")
    public String getEmpWorkHourStart() {
        return empWorkHourStart;
    }

    public void setEmpWorkHourStart(String empWorkHourStart) {
        this.empWorkHourStart = empWorkHourStart;
    }

    @Basic
    @Column(name = "EMP_WORK_HOUR_STOP")
    public String getEmpWorkHourStop() {
        return empWorkHourStop;
    }

    public void setEmpWorkHourStop(String empWorkHourStop) {
        this.empWorkHourStop = empWorkHourStop;
    }

    @Basic
    @Column(name = "EMP_ALTERNATE_RESTING_DAY")
    public Timestamp getEmpAlternateRestingDay() {
        return empAlternateRestingDay;
    }

    public void setEmpAlternateRestingDay(Timestamp empAlternateRestingDay) {
        this.empAlternateRestingDay = empAlternateRestingDay;
    }

    @Basic
    @Column(name = "INSPECTOR_COMMENTS")
    public String getInspectorComments() {
        return inspectorComments;
    }

    public void setInspectorComments(String inspectorComments) {
        this.inspectorComments = inspectorComments;
    }

    @Basic
    @Column(name = "EMP_AFM")
    public String getEmpAfm() {
        return empAfm;
    }

    public void setEmpAfm(String empAfm) {
        this.empAfm = empAfm;
    }

    @Basic
    @Column(name = "EMP_AMKA")
    public String getEmpAmka() {
        return empAmka;
    }

    public void setEmpAmka(String empAmka) {
        this.empAmka = empAmka;
    }

    @Basic
    @Column(name = "EMP_SEX")
    public String getEmpSex() {
        return empSex;
    }

    public void setEmpSex(String empSex) {
        this.empSex = empSex;
    }

    @Basic
    @Column(name = "COMPANY_ID")
    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    /////////////////////////////////////////////////
    // /*
    private SpPtlCompSundayPmt compSundayPmt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SUNDAY_PMT_ID", nullable = false, insertable = true, updatable = false) // Name is the DB column name of the attribute
    public SpPtlCompSundayPmt getCompSundayPmt() {
        return this.compSundayPmt;
    }

    public void setCompSundayPmt(SpPtlCompSundayPmt compSundayPmt) {
        this.compSundayPmt = compSundayPmt;
    }
    // */
    /////////////////////////////////////////////////
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpPtlCompSundayPmtPers that = (SpPtlCompSundayPmtPers) o;

        if (id != that.id) return false;
        if (companyId != null ? !companyId.equals(that.companyId) : that.companyId != null) return false;
        if (empAfm != null ? !empAfm.equals(that.empAfm) : that.empAfm != null) return false;
        if (empAlternateRestingDay != null ? !empAlternateRestingDay.equals(that.empAlternateRestingDay) : that.empAlternateRestingDay != null)
            return false;
        if (empAmka != null ? !empAmka.equals(that.empAmka) : that.empAmka != null) return false;
        if (empFirstname != null ? !empFirstname.equals(that.empFirstname) : that.empFirstname != null) return false;
        if (empIka != null ? !empIka.equals(that.empIka) : that.empIka != null) return false;
        if (empLastname != null ? !empLastname.equals(that.empLastname) : that.empLastname != null) return false;
        if (empSex != null ? !empSex.equals(that.empSex) : that.empSex != null) return false;
        if (empSpeciality != null ? !empSpeciality.equals(that.empSpeciality) : that.empSpeciality != null)
            return false;
        if (empWorkHourStart != null ? !empWorkHourStart.equals(that.empWorkHourStart) : that.empWorkHourStart != null)
            return false;
        if (empWorkHourStop != null ? !empWorkHourStop.equals(that.empWorkHourStop) : that.empWorkHourStop != null)
            return false;
        if (incNum != null ? !incNum.equals(that.incNum) : that.incNum != null) return false;
        if (inspectorComments != null ? !inspectorComments.equals(that.inspectorComments) : that.inspectorComments != null)
            return false;
        if (compSundayPmt != null ? !compSundayPmt.equals(that.compSundayPmt) : that.compSundayPmt != null) return false;

        return true;
    }

/*    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (compSundayPmt != null ? compSundayPmt.hashCode() : 0);
        result = 31 * result + (incNum != null ? incNum.hashCode() : 0);
        result = 31 * result + (empFirstname != null ? empFirstname.hashCode() : 0);
        result = 31 * result + (empLastname != null ? empLastname.hashCode() : 0);
        result = 31 * result + (empIka != null ? empIka.hashCode() : 0);
        result = 31 * result + (empSpeciality != null ? empSpeciality.hashCode() : 0);
        result = 31 * result + (empWorkHourStart != null ? empWorkHourStart.hashCode() : 0);
        result = 31 * result + (empWorkHourStop != null ? empWorkHourStop.hashCode() : 0);
        result = 31 * result + (empAlternateRestingDay != null ? empAlternateRestingDay.hashCode() : 0);
        result = 31 * result + (inspectorComments != null ? inspectorComments.hashCode() : 0);
        result = 31 * result + (empAfm != null ? empAfm.hashCode() : 0);
        result = 31 * result + (empAmka != null ? empAmka.hashCode() : 0);
        result = 31 * result + (empSex != null ? empSex.hashCode() : 0);
        result = 31 * result + (companyId != null ? companyId.hashCode() : 0);
        return result;
    }*/
}
