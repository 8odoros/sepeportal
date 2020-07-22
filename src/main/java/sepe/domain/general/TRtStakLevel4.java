package sepe.domain.general;

import javax.persistence.*;

/**
 * Created by Nikolas on 2/24/2015.
 */
@Entity
@Table(name = "SP_PTL_V_STAKOD4", schema = "", catalog = "")
public class TRtStakLevel4 {

    private String spRtstackLevel4;
    private String spRtstackLevel4Desc;
    private String spRtstackLevel3;

    @Id
    @Column(name = "SP_RTSTAK_LEVEL_4", nullable = false, insertable = true, updatable = true, precision = 0)
    public String getSpRtstackLevel4() {
        return spRtstackLevel4;
    }
    public void setSpRtstackLevel4(String spRtstackLevel4) {
        this.spRtstackLevel4 = spRtstackLevel4;
    }

    @Basic
    @Column(name = "SP_RTSTAK_LEVEL_4_DESC")
    public String getSpRtstackLevel4Desc() {
        return spRtstackLevel4Desc;
    }
    public void setSpRtstackLevel4Desc(String spRtstackLevel4Desc) {
        this.spRtstackLevel4Desc = spRtstackLevel4Desc;
    }

    @Column(name = "SP_RTSTAK_LEVEL_3", nullable = false, insertable = true, updatable = true, precision = 0)
    public String getSpRtstackLevel3() {
        return spRtstackLevel3;
    }
    public void setSpRtstackLevel3(String spRtstackLevel3) {
        this.spRtstackLevel3 = spRtstackLevel3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TRtStakLevel4 tRtStakLevel = (TRtStakLevel4) o;

        if (spRtstackLevel4 != null ? !spRtstackLevel4.equals(tRtStakLevel.spRtstackLevel4) : tRtStakLevel.spRtstackLevel4 != null) return false;
        if (spRtstackLevel4Desc != null ? !spRtstackLevel4Desc.equals(tRtStakLevel.spRtstackLevel4Desc) : tRtStakLevel.spRtstackLevel4Desc != null) return false;
        if (spRtstackLevel3 != null ? !spRtstackLevel3.equals(tRtStakLevel.spRtstackLevel3) : tRtStakLevel.spRtstackLevel3 != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = spRtstackLevel4 != null ? spRtstackLevel4.hashCode() : 0;
        result = 31 * result + (spRtstackLevel4Desc != null ? spRtstackLevel4Desc.hashCode() : 0);
        result = 31 * result + (spRtstackLevel3 != null ? spRtstackLevel3.hashCode() : 0);
        return result;
    }
}