package sepe.domain.company;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Nikos on 6/16/2015.
 */
@Entity
@Table(name = "SP_PTL_COMP_TA_ANN_DIARY_ENTRY", schema = "SP_PTL", catalog = "")
public class SpPtlCompTaAnnDiaryEntry {
    private Long id;
    private Long companyId;
    private String visitTime;
    private Integer visitDurationMinutes;
    //private Long compTaAnnId;
    private Long compTaAnnTaId;
    private Timestamp visitDate;
    private Integer isSubmitted;

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
    @Column(name = "IS_SUBMITTED")
    public Integer getIsSubmitted() {
        return isSubmitted;
    }

    public void setIsSubmitted(Integer isSubmitted) {
        this.isSubmitted = isSubmitted;
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
    @Column(name = "COMP_TA_ANN_TA_ID")
    public Long getCompTaAnnTaId() {
        return compTaAnnTaId;
    }

    public void setCompTaAnnTaId(Long compTaAnnTaId) {
        this.compTaAnnTaId = compTaAnnTaId;
    }


    @Basic
    @Column(name = "VISIT_DATE")
    public Timestamp getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Timestamp visitDate) {
        this.visitDate = visitDate;
    }


    /////////////////////////////////////////////////
    // /*
    private SpPtlCompTaAnn compTaAnn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMP_TA_ANN_ID", nullable = false, insertable = true, updatable = false) // Name is the DB column name of the attribute
    public SpPtlCompTaAnn getCompTaAnn() {
        return this.compTaAnn;
    }

    public void setCompTaAnn(SpPtlCompTaAnn compTaAnn) {
        this.compTaAnn = compTaAnn;
    }
    // */
    /////////////////////////////////////////////////

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpPtlCompTaAnnDiaryEntry that = (SpPtlCompTaAnnDiaryEntry) o;

        if (id != that.id) return false;
        if (companyId != that.companyId) return false;
        if (visitTime != null ? !visitTime.equals(that.visitTime) : that.visitTime != null) return false;
        if (visitDurationMinutes != null ? !visitDurationMinutes.equals(that.visitDurationMinutes) : that.visitDurationMinutes != null)
            return false;
        if (compTaAnnTaId != null ? !compTaAnnTaId.equals(that.compTaAnnTaId) : that.compTaAnnTaId != null) return false;
        if (visitDate != null ? !visitDate.equals(that.visitDate) : that.visitDate != null) return false;
        if (isSubmitted != null ? !isSubmitted.equals(that.isSubmitted) : that.isSubmitted != null) return false;

        return true;
    }

    /*
    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (companyId ^ (companyId >>> 32));
        result = 31 * result + (visitTime != null ? visitTime.hashCode() : 0);
        result = 31 * result + (visitDurationMinutes != null ? visitDurationMinutes.hashCode() : 0);
       // result = 31 * result + (compTaAnnId != null ? compTaAnnId.hashCode() : 0);
        result = 31 * result + (visitDate != null ? visitDate.hashCode() : 0);
        return result;
    }
    */
}
