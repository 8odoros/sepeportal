package sepe.domain.company;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Nikos on 6/16/2015.
 */
@Entity
@Table(name = "SP_PTL_COMP_TA_ANN_BOOK", schema = "SP_PTL", catalog = "")
public class SpPtlCompTaAnnBook {
    private Long id;
    private Long companyId;
    private Timestamp dateCreated;
    private Long compPtlBranchId;

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
    @Column(name = "DATE_CREATED")
    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Basic
    @Column(name = "COMP_PTL_BRANCH_ID")
    public Long getCompPtlBranchId() {
        return compPtlBranchId;
    }

    public void setCompPtlBranchId(Long compPtlBranchId) {
        this.compPtlBranchId = compPtlBranchId;
    }

    /////////////////////////////////////////////////
    // /*
    private Set<SpPtlCompTaAnnBookNote> compTaAnnBookNotes = new HashSet<SpPtlCompTaAnnBookNote>(0);

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "compTaAnnBook", cascade = CascadeType.ALL, orphanRemoval = true) // SHOULD BE EQUAL TO ATTRIBUTE NAME OF OTHER CLASS!!!!
    public Set<SpPtlCompTaAnnBookNote> getCompTaAnnBookNotes() {
        return this.compTaAnnBookNotes;
    }

    public void setCompTaAnnBookNotes(Set<SpPtlCompTaAnnBookNote> compTaAnnBookNotes) {
        this.compTaAnnBookNotes = compTaAnnBookNotes;
    }
    // */
    /////////////////////////////////////////////////

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpPtlCompTaAnnBook that = (SpPtlCompTaAnnBook) o;

        if (id != that.id) return false;
        if (companyId != that.companyId) return false;
        if (dateCreated != null ? !dateCreated.equals(that.dateCreated) : that.dateCreated != null) return false;
        if (compPtlBranchId != null ? !compPtlBranchId.equals(that.compPtlBranchId) : that.compPtlBranchId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (companyId ^ (companyId >>> 32));
        result = 31 * result + (dateCreated != null ? dateCreated.hashCode() : 0);
        result = 31 * result + (compPtlBranchId != null ? compPtlBranchId.hashCode() : 0);
        return result;
    }
}
