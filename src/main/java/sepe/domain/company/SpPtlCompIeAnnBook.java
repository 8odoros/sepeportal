package sepe.domain.company;

/**
 * Created by Nikos on 5/16/2015.
 */

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Nikos on 5/16/2015.
 */
@Entity
@Table(name = "SP_PTL_COMP_IE_ANN_BOOK", schema = "SP_PTL", catalog = "")
public class SpPtlCompIeAnnBook {
    private Long id;
    private Long companyId;
    private Long compPtlBranchId;
    private Timestamp dateCreated;

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
    @Column(name = "COMP_PTL_BRANCH_ID", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getCompPtlBranchId() {
        return compPtlBranchId;
    }

    public void setCompPtlBranchId(Long compPtlBranchId) {
        this.compPtlBranchId = compPtlBranchId;
    }

    @Basic
    @Column(name = "DATE_CREATED", nullable = true, insertable = true, updatable = true)
    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }


    /////////////////////////////////////////////////
    // /*
    private Set<SpPtlCompIeAnnBookNote> compIeAnnBookNotes = new HashSet<SpPtlCompIeAnnBookNote>(0);

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "compIeAnnBook", cascade = CascadeType.ALL, orphanRemoval = true) // SHOULD BE EQUAL TO ATTRIBUTE NAME OF OTHER CLASS!!!!
    public Set<SpPtlCompIeAnnBookNote> getCompIeAnnBookNotes() {
        return this.compIeAnnBookNotes;
    }

    public void setCompIeAnnBookNotes(Set<SpPtlCompIeAnnBookNote> compIeAnnBookNotes) {
        this.compIeAnnBookNotes = compIeAnnBookNotes;
    }
    // */
    /////////////////////////////////////////////////




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpPtlCompIeAnnBook that = (SpPtlCompIeAnnBook) o;

        if (companyId != that.companyId) return false;
        if (id != that.id) return false;
        if (compPtlBranchId != null ? !compPtlBranchId.equals(that.compPtlBranchId) : that.compPtlBranchId != null)
            return false;
        if (dateCreated != null ? !dateCreated.equals(that.dateCreated) : that.dateCreated != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (compPtlBranchId != null ? compPtlBranchId.hashCode() : 0);
        result = 31 * result + (int) (companyId ^ (companyId >>> 32));
        result = 31 * result + (dateCreated != null ? dateCreated.hashCode() : 0);
        return result;
    }
}
