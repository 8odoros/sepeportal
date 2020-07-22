package sepe.domain.doctor;

import sepe.domain.technician.SpPtlTechnicianRegrequest;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Dimitris F. on 28/05/2015.
 */
@Entity
@Table(name = "SP_PTL_DOCTOR_DIPLOMA", schema = "SP_PTL", catalog = "")
public class SpPtlDoctorDiploma {
    private Long id;
    private String speciality;
    private Long attachedDocId;
    private Long userId;
    private Long specialityId;

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
    @Column(name = "SPECIALITY")
    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    @Basic
    @Column(name = "ATTACHED_DOC_ID")
    public Long getAttachedDocId() {
        return attachedDocId;
    }

    public void setAttachedDocId(Long attachedDocId) {
        this.attachedDocId = attachedDocId;
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
    @Column(name = "SPECIALITY_ID")
    public Long getSpecialityId() {
        return specialityId;
    }

    public void setSpecialityId(Long specialityId) {
        this.specialityId = specialityId;
    }

    /////////////////////////////////////////////////
    // /*
    /*private Set<SpPtlTechnicianRegrequest> technicianRegrequests = new HashSet<SpPtlTechnicianRegrequest>(0);

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "SP_PTL_TECHNICIAN_REGREQ_DIPL", joinColumns = {
            @JoinColumn(name = "TECHNICIAN_DIPLOMA_ID", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "TECHNICIAN_REGREQUEST_ID",
                    nullable = false, updatable = false) })
    public Set<SpPtlTechnicianRegrequest> getTechnicianRegrequests() {
        return this.technicianRegrequests;
    }

    public void setTechnicianRegrequests(Set<SpPtlTechnicianRegrequest> technicianRegrequests) {
        this.technicianRegrequests = technicianRegrequests;
    }*/
    // */
    /////////////////////////////////////////////////
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpPtlDoctorDiploma that = (SpPtlDoctorDiploma) o;

        if (id != that.id) return false;
        if (attachedDocId != null ? !attachedDocId.equals(that.attachedDocId) : that.attachedDocId != null)
            return false;
        if (speciality != null ? !speciality.equals(that.speciality) : that.speciality != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (specialityId != null ? !specialityId.equals(that.specialityId) : that.specialityId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (speciality != null ? speciality.hashCode() : 0);
        result = 31 * result + (attachedDocId != null ? attachedDocId.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (specialityId != null ? specialityId.hashCode() : 0);
        return result;
    }
}
