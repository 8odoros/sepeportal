package sepe.domain.employee;

import javax.annotation.Nullable;
import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Nikolas on 2/14/2015.
 */
@Entity
@Table(name = "SP_PTL_EMPLOYEE_DISPUTE_REASON", schema = "SP_PTL", catalog = "")
public class TEmployeeDisputeReason {
    private Long id;
    private Long disputeId;
    private Timestamp dateStart;
    private Timestamp dateEnd;
    private String comments;
    private Integer reasonId;

    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false, insertable = true, updatable = true, precision = 0)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /*
    @Basic
    @Column(name = "DISPUTE_ID", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getDisputeId() {
        return disputeId;
    }

    public void setDisputeId(Long disputeId) {
        this.disputeId = disputeId;
    }
    */

    @Basic
    @Column(name = "DATE_START", nullable = true, insertable = true, updatable = true)
    public Timestamp getDateStart() {
        return dateStart;
    }

    public void setDateStart(Timestamp dateStart) {
        this.dateStart = dateStart;
    }

    @Basic
    @Column(name = "DATE_END", nullable = true, insertable = true, updatable = true)
    public Timestamp getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Timestamp dateEnd) {
        this.dateEnd = dateEnd;
    }

    @Basic
    @Column(name = "COMMENTS", nullable = true, insertable = true, updatable = true, length = 1000)
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Basic
    @Column(name = "REASON_ID", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getReasonId() {
        return reasonId;
    }

    public void setReasonId(Integer reasonId) {
        this.reasonId = reasonId;
    }
    
    /////////////////////////////////////////////////
    // /*
    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "ID", nullable = false)
    private TEmployeeDispute employeeDispute;

    @Nullable
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "EMPLOYEE_DISPUTE", nullable = true, insertable = true, updatable = false) /* Name is the DB column name of the attribute */
    public TEmployeeDispute getEmployeeDispute() {

        return this.employeeDispute;
    }

    public void setEmployeeDispute(TEmployeeDispute employeeDispute) {

        this.employeeDispute = employeeDispute;
    }
    // */
    /////////////////////////////////////////////////

  /*  @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TEmployeeDisputeReason that = (TEmployeeDisputeReason) o;

        if (id != that.id) return false;
        if (comments != null ? !comments.equals(that.comments) : that.comments != null) return false;
        if (dateEnd != null ? !dateEnd.equals(that.dateEnd) : that.dateEnd != null) return false;
        if (dateStart != null ? !dateStart.equals(that.dateStart) : that.dateStart != null) return false;
        if (reasonId != null ? !reasonId.equals(that.reasonId) : that.reasonId != null) return false;
        if (employeeDispute != null ? !employeeDispute.equals(that.employeeDispute) : that.employeeDispute != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (employeeDispute != null ? employeeDispute.hashCode() : 0);
        result = 31 * result + (dateStart != null ? dateStart.hashCode() : 0);
        result = 31 * result + (dateEnd != null ? dateEnd.hashCode() : 0);
        result = 31 * result + (comments != null ? comments.hashCode() : 0);
        result = 31 * result + (reasonId != null ? reasonId.hashCode() : 0);
        return result;
    }*/
}
