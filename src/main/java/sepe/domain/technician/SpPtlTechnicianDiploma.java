package sepe.domain.technician;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Nick on 4/16/2015.
 */
@Entity
@Table(name = "SP_PTL_TECHNICIAN_DIPLOMA", schema = "SP_PTL", catalog = "")
public class SpPtlTechnicianDiploma {
    private Long id;
    private String diplomaDescr;
    private Timestamp diplomaYear;
    private Long attachedDocId;
    private Long userId;

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
    @Column(name = "DIPLOMA_DESCR")
    public String getDiplomaDescr() {
        return diplomaDescr;
    }

    public void setDiplomaDescr(String diplomaDescr) {
        this.diplomaDescr = diplomaDescr;
    }

    @Basic
    @Column(name = "DIPLOMA_YEAR")
    public Timestamp getDiplomaYear() {
        return diplomaYear;
    }

    public void setDiplomaYear(Timestamp diplomaYear) {
        this.diplomaYear = diplomaYear;
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

    /////////////////////////////////////////////////
    // /*
    private Set<SpPtlTechnicianRegrequest> technicianRegrequests = new HashSet<SpPtlTechnicianRegrequest>(0);

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
    }
    // */
    /////////////////////////////////////////////////
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpPtlTechnicianDiploma that = (SpPtlTechnicianDiploma) o;

        if (id != that.id) return false;
        if (attachedDocId != null ? !attachedDocId.equals(that.attachedDocId) : that.attachedDocId != null)
            return false;
        if (diplomaDescr != null ? !diplomaDescr.equals(that.diplomaDescr) : that.diplomaDescr != null) return false;
        if (diplomaYear != null ? !diplomaYear.equals(that.diplomaYear) : that.diplomaYear != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (diplomaDescr != null ? diplomaDescr.hashCode() : 0);
        result = 31 * result + (diplomaYear != null ? diplomaYear.hashCode() : 0);
        result = 31 * result + (attachedDocId != null ? attachedDocId.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }
}
