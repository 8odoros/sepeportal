package sepe.domain.general;

import javax.persistence.*;

/**
 * Created by kirikosm on 5/3/2018.
 */
//SP_RT_TA_BRANCH_SECTOR@SP_SP_LINK
@Entity
@Table(name = "SN_SP_RT_TA_SECTOR_CHECK", schema = "", catalog = "")
public class SpRtTaSectorCheck {
    private Long spRtTaSpecId;
    private Long spRtTaBrSsector;
    private Long spRtTaOblig;

    @Id
    @Column(name = "SP_RT_TA_SPEC_ID", nullable = false, insertable = true, updatable = true, precision = 0)
    public Long getSpRtTaSpecId() {
        return spRtTaSpecId;
    }

    public void setSpRtTaSpecId(Long spRtTaSpecId) {
        this.spRtTaSpecId = spRtTaSpecId;
    }

    @Basic
    @Column(name = "SP_RT_TA_BR_SSECTOR", nullable = false, insertable = false, updatable = false, length = 65)
    public Long getSpRtTaBrSsector() {
        return spRtTaBrSsector;
    }

    public void setSpRtTaBrSsector(Long spRtTaBrSsector) {
        this.spRtTaBrSsector = spRtTaBrSsector;
    }

    @Basic
    @Column(name = "SP_RT_TA_OBLIG", nullable = false, insertable = false, updatable = false, length = 65)
    public Long getSpRtTaOblig() {
        return spRtTaOblig;
    }

    public void setSpRtTaOblig(Long spRtTaOblig) {
        this.spRtTaOblig = spRtTaOblig;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpRtTaSectorCheck spRtTaSectorCheck = (SpRtTaSectorCheck) o;

        if (spRtTaSpecId != spRtTaSectorCheck.spRtTaSpecId) return false;
        if (spRtTaBrSsector != null ? !spRtTaBrSsector.equals(spRtTaSectorCheck.spRtTaBrSsector) : spRtTaSectorCheck.spRtTaBrSsector != null)
            return false;
        if (spRtTaOblig != null ? !spRtTaOblig.equals(spRtTaSectorCheck.spRtTaOblig) : spRtTaSectorCheck.spRtTaOblig != null)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (spRtTaSpecId ^ (spRtTaSpecId >>> 32));
        result = 31 * result + (spRtTaBrSsector != null ? spRtTaBrSsector.hashCode() : 0);
        result = 31 * result + (spRtTaOblig != null ? spRtTaOblig.hashCode() : 0);
        return result;
    }
}