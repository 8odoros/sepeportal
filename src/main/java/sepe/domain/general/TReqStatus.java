package sepe.domain.general;

import javax.persistence.*;

/**
 * Created by Evangelos on 2/24/2015.
 */
//SP_VW_PROT_STATUS@SP_SP_LINK
@Entity
@Table(name = "SN_SP_VW_PROT_STATUS", schema = "", catalog = "")
public class TReqStatus {
    private Long id;
    private String description;

    @Id
    @Column(name = "ID", nullable = false, insertable = true, updatable = true, precision = 0)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ITEM", nullable = false, insertable = false, updatable = false, length = 65)
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

        TReqStatus tProtocol = (TReqStatus) o;

        if (id != tProtocol.id) return false;
        if (description != null ? !description.equals(tProtocol.description) : tProtocol.description != null)
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