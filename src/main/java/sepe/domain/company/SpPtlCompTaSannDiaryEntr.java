package sepe.domain.company;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Nikos on 6/13/2015.
 */
@Entity
@Table(name = "SP_PTL_COMP_TA_SANN_DIARY_ENTR", schema = "SP_PTL", catalog = "")
public class SpPtlCompTaSannDiaryEntr {
    private Long id;
    private Long companyId;
    private String visitTime;
    private Integer visitDurationMinutes;
    //private Long compTaSannId;
    private Timestamp visitDate;

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
    @Column(name = "VISIT_TIME", nullable = true, insertable = true, updatable = true, length = 20)
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

    /*
    @Basic
    @Column(name = "COMP_TA_SANN_ID", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getCompTaSannId() {
        return compTaAnnId;
    }

    public void setCompTaSannId(Long compTaSannId) {
        this.compTaSannId = compTaSannId;
    }
    */

    @Basic
    @Column(name = "VISIT_DATE", nullable = true, insertable = true, updatable = true)
    public Timestamp getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Timestamp visitDate) {
        this.visitDate = visitDate;
    }

    /////////////////////////////////////////////////
    // /*
    private SpPtlCompTaSann compTaSann;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMP_TA_SANN_ID", nullable = false, insertable = true, updatable = false) // Name is the DB column name of the attribute
    public SpPtlCompTaSann getCompTaSann() {
        return this.compTaSann;
    }

    public void setCompTaSann(SpPtlCompTaSann compTaSann) {
        this.compTaSann = compTaSann;
    }
    // */
    /////////////////////////////////////////////////

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpPtlCompTaSannDiaryEntr that = (SpPtlCompTaSannDiaryEntr) o;

        if (companyId != that.companyId) return false;
        if (id != that.id) return false;
        //if (compTaAnnId != null ? !compTaAnnId.equals(that.compTaAnnId) : that.compTaAnnId != null) return false;
        if (visitDate != null ? !visitDate.equals(that.visitDate) : that.visitDate != null) return false;
        if (visitDurationMinutes != null ? !visitDurationMinutes.equals(that.visitDurationMinutes) : that.visitDurationMinutes != null)
            return false;
        if (visitTime != null ? !visitTime.equals(that.visitTime) : that.visitTime != null) return false;

        return true;
    }

    /*
    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (companyId ^ (companyId >>> 32));
        result = 31 * result + (visitTime != null ? visitTime.hashCode() : 0);
        result = 31 * result + (visitDurationMinutes != null ? visitDurationMinutes.hashCode() : 0);
        //result = 31 * result + (compTaAnnId != null ? compTaAnnId.hashCode() : 0);
        result = 31 * result + (visitDate != null ? visitDate.hashCode() : 0);
        return result;
    }
    */
}
