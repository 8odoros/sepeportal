package sepe.domain.general;

import javax.persistence.*;

/**
 * Created by Nick on 4/10/2015.
 */
//SP_RT_MEDICAL_ASSOC@SP_SP_LINK
@Entity
@Table(name = "SN_SP_RT_MEDICAL_ASSOC", schema = "", catalog = "")
public class SpRtMedicalAssoc {
    private Long spMedasId;
    private String spMedasDescription;
    private String spMedasAddress;

    @Id
    @Column(name = "SP_MEDAS_ID")
    public Long getSpMedasId() {
        return spMedasId;
    }

    public void setSpMedasId(Long spMedasId) {
        this.spMedasId = spMedasId;
    }

    @Basic
    @Column(name = "SP_MEDAS_DESCRIPTION")
    public String getSpMedasDescription() {
        return spMedasDescription;
    }

    public void setSpMedasDescription(String spMedasDescription) {
        this.spMedasDescription = spMedasDescription;
    }

    @Basic
    @Column(name = "SP_MEDAS_ADDRESS")
    public String getSpMedasAddress() {
        return spMedasAddress;
    }

    public void setSpMedasAddress(String spMedasAddress) {
        this.spMedasAddress = spMedasAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpRtMedicalAssoc that = (SpRtMedicalAssoc) o;

        if (spMedasId != that.spMedasId) return false;
        if (spMedasAddress != null ? !spMedasAddress.equals(that.spMedasAddress) : that.spMedasAddress != null)
            return false;
        if (spMedasDescription != null ? !spMedasDescription.equals(that.spMedasDescription) : that.spMedasDescription != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (spMedasId ^ (spMedasId >>> 32));
        result = 31 * result + (spMedasDescription != null ? spMedasDescription.hashCode() : 0);
        result = 31 * result + (spMedasAddress != null ? spMedasAddress.hashCode() : 0);
        return result;
    }
}
