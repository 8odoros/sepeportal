package sepe.domain.general;

import javax.persistence.*;

/**
 * Created by Nikolas on 2/13/2015.
 */
//SP_RT_DOY@SP_SP_LINK
@Entity
@Table(name = "SN_SP_RT_DOY", schema = "", catalog = "")
public class TDoy {
    private Long id;
    private String descr;

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
    @Column(name = "DESCR", nullable = true, insertable = true, updatable = true, length = 100)
    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TDoy tDoy = (TDoy) o;

        if (id != tDoy.id) return false;
        if (descr != null ? !descr.equals(tDoy.descr) : tDoy.descr != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (descr != null ? descr.hashCode() : 0);
        return result;
    }
}