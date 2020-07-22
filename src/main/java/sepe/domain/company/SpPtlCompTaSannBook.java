package sepe.domain.company;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Nikos on 6/13/2015.
 */
@Entity
@Table(name = "SP_PTL_COMP_TA_SANN_BOOK", schema = "SP_PTL", catalog = "")
public class SpPtlCompTaSannBook {
    private Long id;
    private Long companyId;
    private Timestamp dateCreated;
    private Long compShipId;

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
    @Column(name = "DATE_CREATED", nullable = true, insertable = true, updatable = true)
    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Basic
    @Column(name = "COMP_SHIP_ID", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getCompShipId() {
        return compShipId;
    }

    public void setCompShipId(Long compShipId) {
        this.compShipId = compShipId;
    }

    /////////////////////////////////////////////////
    // /*
    private Set<SpPtlCompTaSannBookNote> compTaSannBookNotes = new HashSet<SpPtlCompTaSannBookNote>(0);

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "compTaSannBook", cascade = CascadeType.ALL, orphanRemoval = true) // SHOULD BE EQUAL TO ATTRIBUTE NAME OF OTHER CLASS!!!!
    public Set<SpPtlCompTaSannBookNote> getCompTaSannBookNotes() {
        return this.compTaSannBookNotes;
    }

    public void setCompTaSannBookNotes(Set<SpPtlCompTaSannBookNote> compTaSannBookNotes) {
        this.compTaSannBookNotes = compTaSannBookNotes;
    }
    // */
    /////////////////////////////////////////////////
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpPtlCompTaSannBook that = (SpPtlCompTaSannBook) o;

        if (companyId != that.companyId) return false;
        if (id != that.id) return false;
        if (compShipId != null ? !compShipId.equals(that.compShipId) : that.compShipId != null) return false;
        if (dateCreated != null ? !dateCreated.equals(that.dateCreated) : that.dateCreated != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (companyId ^ (companyId >>> 32));
        result = 31 * result + (dateCreated != null ? dateCreated.hashCode() : 0);
        result = 31 * result + (compShipId != null ? compShipId.hashCode() : 0);
        return result;
    }
}
