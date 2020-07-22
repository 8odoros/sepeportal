package sepe.domain.general;

import javax.persistence.*;

/**
 * Created by kirikosm on 5/3/2018.
 */
//SP_RT_TA_BRANCH_SECTOR@SP_SP_LINK
@Entity
@Table(name = "SN_SP_RT_TA_BRANCH_SECTOR", schema = "", catalog = "")
public class SpRtTaBranchSector {
    private Long id;
    private String description;
    private Long spTabrsectCheck;
    private Long spObligCheck;

    @Id
    @Column(name = "SP_TABRSECT_ID", nullable = false, insertable = true, updatable = true, precision = 0)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "SP_TABRSECT_DESC", nullable = false, insertable = false, updatable = false, length = 65)
    public String getDescription() {
        return description;
    }

    public void setDescription(String desc) {
        this.description = desc;
    }

    @Basic
    @Column(name = "SP_TABRSECT_CHECK", nullable = false, insertable = false, updatable = false, length = 65)
    public Long getSpTabrsectCheck() {
        return spTabrsectCheck;
    }

    public void setSpTabrsectCheck(Long spTabrsectCheck) {
        this.spTabrsectCheck = spTabrsectCheck;
    }

    @Basic
    @Column(name = "SP_OBLIG_CHECK", nullable = false, insertable = false, updatable = false, length = 65)
    public Long getSpObligCheck() {
        return spObligCheck;
    }

    public void setSpObligCheck(Long spObligCheck) {
        this.spObligCheck = spObligCheck;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpRtTaBranchSector spRtTaBranchSector = (SpRtTaBranchSector) o;

        if (id != spRtTaBranchSector.id) return false;
        if (description != null ? !description.equals(spRtTaBranchSector.description) : spRtTaBranchSector.description != null)
            return false;
        if (spTabrsectCheck != null ? !spTabrsectCheck.equals(spRtTaBranchSector.spTabrsectCheck) : spRtTaBranchSector.spTabrsectCheck != null)
            return false;
        if (spObligCheck != null ? !spObligCheck.equals(spRtTaBranchSector.spObligCheck) : spRtTaBranchSector.spObligCheck != null)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (spTabrsectCheck != null ? spTabrsectCheck.hashCode() : 0);
        result = 31 * result + (spObligCheck != null ? spObligCheck.hashCode() : 0);
        return result;
    }
}