package sepe.domain.general;

import javax.persistence.*;

/**
 * Created by Nikolas on 2/24/2015.
 */
@Entity
@Table(name = "SP_PTL_V_STAKOD2", schema = "", catalog = "")
public class TRtStakLevel2 {

    private String spRtstackLevel2;
    private String spRtstackLevel2Desc;
    private String spRtstackLevel1;

    @Id
    @Column(name = "SP_RTSTAK_LEVEL_2", nullable = false, insertable = true, updatable = true, precision = 0)
    public String getSpRtstackLevel2() {
        return spRtstackLevel2;
    }
    public void setSpRtstackLevel2(String spRtstackLevel2) {
        this.spRtstackLevel2 = spRtstackLevel2;
    }

    @Basic
    @Column(name = "SP_RTSTAK_LEVEL_2_DESC")
    public String getSpRtstackLevel2Desc() {
        return spRtstackLevel2Desc;
    }
    public void setSpRtstackLevel2Desc(String spRtstackLevel2Desc) {
        this.spRtstackLevel2Desc = spRtstackLevel2Desc;
    }

    @Column(name = "SP_RTSTAK_LEVEL_1", nullable = false, insertable = true, updatable = true, precision = 0)
    public String getSpRtstackLevel1() {
        return spRtstackLevel1;
    }
    public void setSpRtstackLevel1(String spRtstackLevel1) {
        this.spRtstackLevel1 = spRtstackLevel1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TRtStakLevel2 tRtStakLevel = (TRtStakLevel2) o;

        if (spRtstackLevel2 != null ? !spRtstackLevel2.equals(tRtStakLevel.spRtstackLevel2) : tRtStakLevel.spRtstackLevel2 != null) return false;
        if (spRtstackLevel2Desc != null ? !spRtstackLevel2Desc.equals(tRtStakLevel.spRtstackLevel2Desc) : tRtStakLevel.spRtstackLevel2Desc != null) return false;
        if (spRtstackLevel1 != null ? !spRtstackLevel1.equals(tRtStakLevel.spRtstackLevel1) : tRtStakLevel.spRtstackLevel1 != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = spRtstackLevel2 != null ? spRtstackLevel2.hashCode() : 0;
        result = 31 * result + (spRtstackLevel2Desc != null ? spRtstackLevel2Desc.hashCode() : 0);
        result = 31 * result + (spRtstackLevel1 != null ? spRtstackLevel1.hashCode() : 0);
        return result;
    }
}