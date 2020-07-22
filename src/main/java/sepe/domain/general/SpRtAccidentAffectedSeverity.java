package sepe.domain.general;

import javax.persistence.*;

/**
 * Created by root on 28/4/2015.
 */
//SP_RT_AFFECTED_SEVERITY@SP_SP_LINK
@Entity
@Table(name = "SP_RT_AFFECTED_SEVERITY", schema = "", catalog = "")
public class SpRtAccidentAffectedSeverity {
    private String id;
    private String description;

    @Id
    @Column(name = "SP_ASV_ID", nullable = false, insertable = true, updatable = true, precision = 0)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "SP_ASV_DESC", nullable = false, insertable = false, updatable = false, length = 65)
    public String getDescription() {
        return description;
    }

    public void setDescription(String desc) {
        this.description = desc;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpRtAccidentAffectedSeverity spRtAccidentAffectedSeverity = (SpRtAccidentAffectedSeverity) o;

        if (id != spRtAccidentAffectedSeverity.id) return false;
        if (description != null ? !description.equals(spRtAccidentAffectedSeverity.description) : spRtAccidentAffectedSeverity.description != null)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}