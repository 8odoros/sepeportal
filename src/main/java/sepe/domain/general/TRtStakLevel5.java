package sepe.domain.general;

import javax.persistence.*;

/**
 * Created by Nikolas on 2/24/2015.
 */
@Entity
@Table(name = "SP_PTL_V_STAKOD5", schema = "", catalog = "")
public class TRtStakLevel5 {

    private String spRtstackLevel5;
    private String spRtstackLevel5Desc;
    private String spRtstackLevel4;

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

    @Column(name = "SP_RTSTAK_LEVEL_4", nullable = false, insertable = true, updatable = true, precision = 0)
    public String getSpRtstackLevel4() {
        return spRtstackLevel4;
    }
    public void setSpRtstackLevel4(String spRtstackLevel4) {
        this.spRtstackLevel4 = spRtstackLevel4;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TRtStakLevel5 tRtStakLevel = (TRtStakLevel5) o;

        if (spRtstackLevel5 != null ? !spRtstackLevel5.equals(tRtStakLevel.spRtstackLevel5) : tRtStakLevel.spRtstackLevel5 != null) return false;
        if (spRtstackLevel5Desc != null ? !spRtstackLevel5Desc.equals(tRtStakLevel.spRtstackLevel5Desc) : tRtStakLevel.spRtstackLevel5Desc != null) return false;
        if (spRtstackLevel4 != null ? !spRtstackLevel4.equals(tRtStakLevel.spRtstackLevel4) : tRtStakLevel.spRtstackLevel4 != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = spRtstackLevel5 != null ? spRtstackLevel5.hashCode() : 0;
        result = 31 * result + (spRtstackLevel5Desc != null ? spRtstackLevel5Desc.hashCode() : 0);
        result = 31 * result + (spRtstackLevel4 != null ? spRtstackLevel4.hashCode() : 0);
        return result;
    }
}