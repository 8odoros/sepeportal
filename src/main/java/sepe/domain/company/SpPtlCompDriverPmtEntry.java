package sepe.domain.company;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Nikos on 6/9/2015.
 */
@Entity
@Table(name = "SP_PTL_COMP_DRIVER_PMT_ENTRY", schema = "SP_PTL", catalog = "")
public class SpPtlCompDriverPmtEntry {
    private Long id;
    private Long companyId;
    private String week;
    //private Long driverPmt;
    private Timestamp day;

    @Id
    @GeneratedValue
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
    @Column(name = "WEEK")
    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    /*
    @Basic
    @Column(name = "DRIVER_PMT_ID")
    public Long getDriverPmtId() {
        return driverPmtId;
    }

    public void setDriverPmtId(Long driverPmtId) {
        this.driverPmtId = driverPmtId;
    }
*/

    @Basic
    @Column(name = "DAY")
    public Timestamp getDay() {
        return day;
    }

    public void setDay(Timestamp day) {
        this.day = day;
    }

    /////////////////////////////////////////////////
    // /*
    private SpPtlCompDriverPmt compDriverPmt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DRIVER_PMT_ID", nullable = false, insertable = true, updatable = false) // Name is the DB column name of the attribute
    public SpPtlCompDriverPmt getCompDriverPmt() {
        return this.compDriverPmt;
    }

    public void setCompDriverPmt(SpPtlCompDriverPmt compDriverPmt) {
        this.compDriverPmt = compDriverPmt;
    }
    // */
    /////////////////////////////////////////////////

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpPtlCompDriverPmtEntry that = (SpPtlCompDriverPmtEntry) o;

        if (id != that.id) return false;
        if (companyId != that.companyId) return false;
        if (week != null ? !week.equals(that.week) : that.week != null) return false;
        //if (driverPmtId != null ? !driverPmtId.equals(that.driverPmtId) : that.driverPmtId != null) return false;
        if (day != null ? !day.equals(that.day) : that.day != null) return false;

        return true;
    }

    /*
    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (companyId ^ (companyId >>> 32));
        result = 31 * result + (week != null ? week.hashCode() : 0);
        //result = 31 * result + (driverPmtId != null ? driverPmtId.hashCode() : 0);
        result = 31 * result + (day != null ? day.hashCode() : 0);
        return result;
    }
    */
}
