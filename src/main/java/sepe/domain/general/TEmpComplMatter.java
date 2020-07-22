package sepe.domain.general;

import javax.persistence.*;

//SP_RT_COMPLAINT_MATTER@SP_SP_LINK
@Entity
@Table(name = "SN_SP_RT_COMPLAINT_MATTER", schema = "", catalog = "")
public class TEmpComplMatter {
    private Long id;
    private String descr;
    private Integer spCmInvolves;

    @Id
    @Column(name = "SP_CM_ID", nullable = false, insertable = true, updatable = true, precision = 0)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "SP_CM_ITEM", nullable = true, insertable = true, updatable = true, length = 50)
    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    @Basic
    @Column(name = "SP_CM_INVOLVES", nullable = false, insertable = true, updatable = true, precision = 0)
    public Integer getSpCmInvolves() {
        return spCmInvolves;
    }

    public void setSpCmInvolves(Integer spCmInvolves) {
        this.spCmInvolves = spCmInvolves;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TEmpComplMatter that = (TEmpComplMatter) o;

        if (id != that.id) return false;
        if (descr != null ? !descr.equals(that.descr) : that.descr != null) return false;
        if (spCmInvolves != null ? !spCmInvolves.equals(that.spCmInvolves) : that.spCmInvolves != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (descr != null ? descr.hashCode() : 0);
        result = 31 * result + (spCmInvolves != null ? spCmInvolves.hashCode() : 0);
        return result;
    }
}
