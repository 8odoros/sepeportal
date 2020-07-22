package sepe.domain.general;

import javax.persistence.*;

/**
 * Created by kirikosm on 27/10/2016.
 */

@Entity
@Table(name = "SP_PTL_V_SHIPYARD", schema = "", catalog = "")
public class SpPtlVShipyard {

    private Long id;
    private String item;

    @Id
    @Column(name = "ID", nullable = false, insertable = true, updatable = true, precision = 0)
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ITEM")
    public String getItem() {
        return item;
    }
    public void setItem(String item) {
        this.item = item;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpPtlVShipyard spPtlVShipyard = (SpPtlVShipyard) o;

        if (id != null ? !id.equals(spPtlVShipyard.id) : spPtlVShipyard.id != null) return false;
        if (item != null ? !item.equals(spPtlVShipyard.item) : spPtlVShipyard.item != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (item != null ? item.hashCode() : 0);
        return result;
    }
}
