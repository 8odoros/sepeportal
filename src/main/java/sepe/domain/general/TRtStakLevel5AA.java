package sepe.domain.general;

import javax.persistence.*;

/**
 * Created by Nikolas on 2/24/2015.
 */
@Entity
@Table(name = "SP_PTL_V_STAKOD5AA", schema = "", catalog = "")
public class TRtStakLevel5AA {

    private String spRtstackLevel5;
    private String spRtstackLevel5Desc;
    private Long spRtstackAa;

    @Id
    @Column(name = "SP_RTSTAK_LEVEL_5", nullable = false, insertable = true, updatable = true, precision = 0)
    public String getSpRtstackLevel5() {
        return spRtstackLevel5;
    }
    public void setSpRtstackLevel5(String spRtstackLevel5) {
        this.spRtstackLevel5 = spRtstackLevel5;
    }

    @Basic
    @Column(name = "SP_RTSTAK_LEVEL_5_DESC")
    public String getSpRtstackLevel5Desc() {
        return spRtstackLevel5Desc;
    }
    public void setSpRtstackLevel5Desc(String spRtstackLevel5Desc) {
        this.spRtstackLevel5Desc = spRtstackLevel5Desc;
    }

    @Column(name = "SP_RTSTAK_AA", nullable = false, insertable = true, updatable = true, precision = 0)
    public Long getSpRtstackAa() {
        return spRtstackAa;
    }
    public void setSpRtstackAa(Long spRtstackAa) {
        this.spRtstackAa = spRtstackAa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TRtStakLevel5AA tRtStakLevel = (TRtStakLevel5AA) o;

        if (spRtstackLevel5 != null ? !spRtstackLevel5.equals(tRtStakLevel.spRtstackLevel5) : tRtStakLevel.spRtstackLevel5 != null) return false;
        if (spRtstackLevel5Desc != null ? !spRtstackLevel5Desc.equals(tRtStakLevel.spRtstackLevel5Desc) : tRtStakLevel.spRtstackLevel5Desc != null) return false;
        if (spRtstackAa != null ? !spRtstackAa.equals(tRtStakLevel.spRtstackAa) : tRtStakLevel.spRtstackAa != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = spRtstackLevel5 != null ? spRtstackLevel5.hashCode() : 0;
        result = 31 * result + (spRtstackLevel5Desc != null ? spRtstackLevel5Desc.hashCode() : 0);
        result = 31 * result + (spRtstackAa != null ? spRtstackAa.hashCode() : 0);
        return result;
    }
}