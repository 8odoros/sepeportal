package sepe.domain.general;

import javax.persistence.*;

/**
 * Created by Nikolas on 2/24/2015.
 */
@Entity
@Table(name = "SP_PTL_V_STAKOD1", schema = "", catalog = "")
public class TRtStakLevel1 {

    private String spRtstackLevel1;
    private String spRtstackLevel1Desc;

    @Id
    @Column(name = "SP_RTSTAK_LEVEL_1", nullable = false, insertable = true, updatable = true, precision = 0)
    public String getSpRtstackLevel1() {
        return spRtstackLevel1;
    }
    public void setSpRtstackLevel1(String spRtstackLevel1) {
        this.spRtstackLevel1 = spRtstackLevel1;
    }

    @Basic
    @Column(name = "SP_RTSTAK_LEVEL_1_DESC")
    public String getSpRtstackLevel1Desc() {
        return spRtstackLevel1Desc;
    }
    public void setSpRtstackLevel1Desc(String spRtstackLevel1Desc) {
        this.spRtstackLevel1Desc = spRtstackLevel1Desc;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TRtStakLevel1 tRtStakLevel = (TRtStakLevel1) o;

        if (spRtstackLevel1 != null ? !spRtstackLevel1.equals(tRtStakLevel.spRtstackLevel1) : tRtStakLevel.spRtstackLevel1 != null) return false;
        if (spRtstackLevel1Desc != null ? !spRtstackLevel1Desc.equals(tRtStakLevel.spRtstackLevel1Desc) : tRtStakLevel.spRtstackLevel1Desc != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = spRtstackLevel1 != null ? spRtstackLevel1.hashCode() : 0;
        result = 31 * result + (spRtstackLevel1Desc != null ? spRtstackLevel1Desc.hashCode() : 0);
        return result;
    }
}