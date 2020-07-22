package sepe.domain.company;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by dimitrisf on 5/10/2018.
 */

@Entity
@Table(name = "SP_PTL_COMP_TA_ANN_SE_DIARY", schema = "SP_PTL", catalog = "")
public class SpPtlCompTaAnnSeDiary {

    private Long id;
    private Long companyId;
    private String visitTime;
    private Integer visitDurationMinutes;
    private Timestamp visitDate;

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
    @Column(name = "VISIT_DATE")
    public Timestamp getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Timestamp visitDate) {
        this.visitDate = visitDate;
    }


    /////////////////////////////////////////////////
    // /*
    private SpPtlCompTaAnnSe compTaAnnSe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMP_TA_ANN_SE_ID", nullable = false, insertable = true, updatable = false) // Name is the DB column name of the attribute
    public SpPtlCompTaAnnSe getCompTaAnnSe() {
        return this.compTaAnnSe;
    }

    public void setCompTaAnnSe(SpPtlCompTaAnnSe compTaAnnSe) {
        this.compTaAnnSe = compTaAnnSe;
    }
    // */
    /////////////////////////////////////////////////

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpPtlCompTaAnnSeDiary that = (SpPtlCompTaAnnSeDiary) o;

        if (id != that.id) return false;
        if (companyId != that.companyId) return false;
        if (visitTime != null ? !visitTime.equals(that.visitTime) : that.visitTime != null) return false;
        if (visitDurationMinutes != null ? !visitDurationMinutes.equals(that.visitDurationMinutes) : that.visitDurationMinutes != null) return false;
        if (visitDate != null ? !visitDate.equals(that.visitDate) : that.visitDate != null) return false;

        return true;
    }

}
