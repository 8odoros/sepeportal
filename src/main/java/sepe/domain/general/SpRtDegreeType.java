package sepe.domain.general;

import javax.persistence.*;

/**
 * Created by Nikos on 4/4/2015.
 */
//SP_RT_DEGREE_TYPE@SP_SP_LINK
@Entity
@Table(name = "SN_SP_RT_DEGREE_TYPE", schema = "", catalog = "")
public class SpRtDegreeType {
    private Long id;
    private String description;

    @Id
    @Column(name = "SP_DEGR_ID", nullable = false, insertable = true, updatable = true, precision = 0)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "SP_DEGR_DESC", nullable = false, insertable = false, updatable = false, length = 65)
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

        SpRtDegreeType spRtDegreeType = (SpRtDegreeType) o;

        if (id != spRtDegreeType.id) return false;
        if (description != null ? !description.equals(spRtDegreeType.description) : spRtDegreeType.description != null)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}