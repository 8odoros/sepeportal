package sepe.domain.general;

import javax.persistence.*;

/**
 * Created by root on 21/4/2015.
 */
//SP_RT_NATIONALITY@SP_SP_LINK
@Entity
@Table(name = "SN_SP_RT_NATIONALITY", schema = "", catalog = "")
public class SpRtNationality {
    private String id;
    private String description;

    @Id
    @Column(name = "SP_RTNAT_CODE", nullable = false, insertable = true, updatable = true, length = 3)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "SP_RTNAT_DESCR", nullable = false, insertable = false, updatable = false, length = 65)
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

        SpRtNationality obj = (SpRtNationality) o;

        if (id != obj.id) return false;
        if (description != null ? !description.equals(obj.description) : obj.description != null)
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