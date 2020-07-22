package sepe.domain.company;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Nikos on 5/16/2015.
 */
@Entity
@Table(name = "SP_PTL_COMP_IE_ANN_DIARY_ENTRY", schema = "SP_PTL", catalog = "")
public class SpPtlCompIeAnnDiaryEntry {
    private Long id;
    private Long companyId;
    private String visitTime;
    private Integer visitDurationMinutes;
    //private Long compIeAnnId;
    private Timestamp visitDate;
    private Long compIeAnnIeId;

    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false, insertable = true, updatable = true, precision = 0)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "COMPANY_ID", nullable = false, insertable = true, updatable = true, precision = 0)
    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    @Basic
    @Column(name = "VISIT_TIME", nullable = true, insertable = true, updatable = true)
    public String getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(String visitTime) {
        this.visitTime = visitTime;
    }

    @Basic
    @Column(name = "VISIT_DURATION_MINUTES", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getVisitDurationMinutes() {
        return visitDurationMinutes;
    }

    public void setVisitDurationMinutes(Integer visitDurationMinutes) {
        this.visitDurationMinutes = visitDurationMinutes;
    }

    /*@Basic
    @Column(name = "COMP_IE_ANN_ID", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getCompIeAnnId() {
        return compIeAnnId;
    }

    public void setCompIeAnnId(Long compIeAnnId) {
        this.compIeAnnId = compIeAnnId;
    }*/


    @Basic
    @Column(name = "VISIT_DATE", nullable = true, insertable = true, updatable = true)
    public Timestamp getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Timestamp visitDate) {
        this.visitDate = visitDate;
    }

    @Basic
    @Column(name = "COMP_IE_ANN_IE_ID")
    public Long getCompIeAnnIeId() {
        return compIeAnnIeId;
    }

    public void setCompIeAnnIeId(Long compIeAnnIeId) {
        this.compIeAnnIeId = compIeAnnIeId;
    }

    /////////////////////////////////////////////////
    // /*
    private SpPtlCompIeAnn compIeAnn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMP_IE_ANN_ID", nullable = false, insertable = true, updatable = false) // Name is the DB column name of the attribute
    public SpPtlCompIeAnn getCompIeAnn() {
        return this.compIeAnn;
    }

    public void setCompIeAnn(SpPtlCompIeAnn compIeAnn) {
        this.compIeAnn = compIeAnn;
    }
    // */
    /////////////////////////////////////////////////

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpPtlCompIeAnnDiaryEntry that = (SpPtlCompIeAnnDiaryEntry) o;

        if (companyId != that.companyId) return false;
        if (id != that.id) return false;
       // if (compIeAnn != null ? !compIeAnn.equals(that.compIeAnn) : that.compIeAnn != null) return false;
        if (visitDate != null ? !visitDate.equals(that.visitDate) : that.visitDate != null) return false;
        if (visitDurationMinutes != null ? !visitDurationMinutes.equals(that.visitDurationMinutes) : that.visitDurationMinutes != null)
            return false;
        if (visitTime != null ? !visitTime.equals(that.visitTime) : that.visitTime != null) return false;
        if (compIeAnnIeId != null ? !compIeAnnIeId.equals(that.compIeAnnIeId) : that.compIeAnnIeId != null) return false;

        return true;
    }

    /*
    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (companyId ^ (companyId >>> 32));
        result = 31 * result + (visitTime != null ? visitTime.hashCode() : 0);
        result = 31 * result + (visitDurationMinutes != null ? visitDurationMinutes.hashCode() : 0);
        //result = 31 * result + (compIeAnn != null ? compIeAnn.hashCode() : 0);
        result = 31 * result + (visitDate != null ? visitDate.hashCode() : 0);
        return result;
    }
    */
}
