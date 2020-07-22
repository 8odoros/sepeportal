package sepe.domain.doctor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Nick on 4/16/2015.
 */
@Entity
@Table(name = "SP_PTL_DOCTOR_COUNTY", schema = "SP_PTL", catalog = "")
public class SpPtlDoctorCounty {
    private Long id;
    private Long userId;
    private Long medassocNotifiedId;
    private Timestamp submitDate;
    private Long attachedDocId;

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
    @Column(name = "USER_ID")
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "MEDASSOC_NOTIFIED_ID")
    public Long getMedassocNotifiedId() {
        return medassocNotifiedId;
    }

    public void setMedassocNotifiedId(Long medassocNotifiedId) {
        this.medassocNotifiedId = medassocNotifiedId;
    }

    @Basic
    @Column(name = "SUBMIT_DATE")
    public Timestamp getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Timestamp submitDate) {
        this.submitDate = submitDate;
    }

    @Basic
    @Column(name = "ATTACHED_DOC_ID")
    public Long getAttachedDocId() {
        return attachedDocId;
    }

    public void setAttachedDocId(Long attachedDocId) {
        this.attachedDocId = attachedDocId;
    }

    /////////////////////////////////////////////////
    // /*
    private Set<SpPtlDoctorRegrequest> doctorRegrequests = new HashSet<SpPtlDoctorRegrequest>(0);

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "SP_PTL_DOCTOR_REGREQ_COUNTY", joinColumns = {
            @JoinColumn(name = "DOCTOR_COUNTY_ID", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "DOCTOR_REGREQUEST_ID",
                    nullable = false, updatable = false) })
    public Set<SpPtlDoctorRegrequest> getDoctorRegrequests() {
        return this.doctorRegrequests;
    }

    public void setDoctorRegrequests(Set<SpPtlDoctorRegrequest> doctorRegrequests) {
        this.doctorRegrequests = doctorRegrequests;
    }
    // */
    /////////////////////////////////////////////////

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpPtlDoctorCounty that = (SpPtlDoctorCounty) o;

        if (id != that.id) return false;
        if (attachedDocId != null ? !attachedDocId.equals(that.attachedDocId) : that.attachedDocId != null)
            return false;
        if (medassocNotifiedId != null ? !medassocNotifiedId.equals(that.medassocNotifiedId) : that.medassocNotifiedId != null)
            return false;
        if (submitDate != null ? !submitDate.equals(that.submitDate) : that.submitDate != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (medassocNotifiedId != null ? medassocNotifiedId.hashCode() : 0);
        result = 31 * result + (submitDate != null ? submitDate.hashCode() : 0);
        result = 31 * result + (attachedDocId != null ? attachedDocId.hashCode() : 0);
        return result;
    }
}
