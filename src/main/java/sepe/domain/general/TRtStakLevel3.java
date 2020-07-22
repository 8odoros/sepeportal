package sepe.domain.general;

import javax.persistence.*;

/**
 * Created by Nikolas on 2/24/2015.
 */
@Entity
@Table(name = "SP_PTL_V_STAKOD3", schema = "", catalog = "")
public class TRtStakLevel3 {

    private String spRtstackLevel3;
    private String spRtstackLevel3Desc;
    private String spRtstackLevel2;

    @Id
    @Column(name = "SP_RTSTAK_LEVEL_3", nullable = false, insertable = true, updatable = true, precision = 0)
    public String getSpRtstackLevel3() {
        return spRtstackLevel3;
    }
    public void setSpRtstackLevel3(String spRtstackLevel3) {
        this.spRtstackLevel3 = spRtstackLevel3;
    }

    @Basic
    @Column(name = "SP_RTSTAK_LEVEL_3_DESC")
    public String getSpRtstackLevel3Desc() {
        return spRtstackLevel3Desc;
    }
    public void setSpRtstackLevel3Desc(String spRtstackLevel3Desc) {
        this.spRtstackLevel3Desc = spRtstackLevel3Desc;
    }

    @Column(name = "SP_RTSTAK_LEVEL_2", nullable = false, insertable = true, updatable = true, precision = 0)
    public String getSpRtstackLevel2() {
        return spRtstackLevel2;
    }
    public void setSpRtstackLevel2(String spRtstackLevel2) {
        this.spRtstackLevel2 = spRtstackLevel2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TRtStakLevel3 tRtStakLevel = (TRtStakLevel3) o;

        if (spRtstackLevel3 != null ? !spRtstackLevel3.equals(tRtStakLevel.spRtstackLevel3) : tRtStakLevel.spRtstackLevel3 != null) return false;
        if (spRtstackLevel3Desc != null ? !spRtstackLevel3Desc.equals(tRtStakLevel.spRtstackLevel3Desc) : tRtStakLevel.spRtstackLevel3Desc != null) return false;
        if (spRtstackLevel2 != null ? !spRtstackLevel2.equals(tRtStakLevel.spRtstackLevel2) : tRtStakLevel.spRtstackLevel2 != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = spRtstackLevel3 != null ? spRtstackLevel3.hashCode() : 0;
        result = 31 * result + (spRtstackLevel3Desc != null ? spRtstackLevel3Desc.hashCode() : 0);
        result = 31 * result + (spRtstackLevel2 != null ? spRtstackLevel2.hashCode() : 0);
        return result;
    }
}