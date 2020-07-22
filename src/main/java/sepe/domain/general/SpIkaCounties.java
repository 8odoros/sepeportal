package sepe.domain.general;

import javax.persistence.*;

/**
 * Created by Evangelos on 2/24/2015.
 */
//IK_RT_COUNTIES@SP_IKA_LINK
@Entity
@Table(name = "SN_IK_RT_COUNTIES", schema = "", catalog = "")
public class SpIkaCounties {
    private String id;
    private String description;

    @Id
    @Column(name = "IK_RTCNT_COUNTY_CODE", nullable = false, insertable = true, updatable = true, precision = 0)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "IK_RTCNT_COUNTY_NAME", nullable = false, insertable = false, updatable = false, length = 65)
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

        SpIkaCounties tMaritalStatus = (SpIkaCounties) o;

        if (id != tMaritalStatus.id) return false;
        if (description != null ? !description.equals(tMaritalStatus.description) : tMaritalStatus.description != null)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = 31  + (id != null ? id.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}