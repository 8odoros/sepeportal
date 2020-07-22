package sepe.domain.general;

import javax.persistence.*;

/**
 * Created by Nikolas on 3/2/2015.
 */
//SP_RT_SPECIALITY@SP_SP_LINK
@Entity
@Table(name = "SN_SP_RT_SPECIALITY", schema = "", catalog = "")
public class TEmployeeSpeciality {
    private Long spRtspId;
    private String spRtspDescription;

    @Id
    @GeneratedValue
    @Column(name = "SP_RTSP_ID", nullable = true, insertable = true, updatable = true, precision = -127)
    public Long getSpRtspId() {
        return spRtspId;
    }

    public void setSpRtspId(Long spRtspId) {
        this.spRtspId = spRtspId;
    }

    @Basic
    @Column(name = "SP_RTSP_DESCRIPTION", nullable = true, insertable = true, updatable = true, length = 20)
    public String getSpRtspDescription() {
        return spRtspDescription;
    }

    public void setSpRtspDescription(String spRtspDescription) {
        this.spRtspDescription = spRtspDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TEmployeeSpeciality that = (TEmployeeSpeciality) o;

        if (spRtspDescription != null ? !spRtspDescription.equals(that.spRtspDescription) : that.spRtspDescription != null)
            return false;
        if (spRtspId != null ? !spRtspId.equals(that.spRtspId) : that.spRtspId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = spRtspId != null ? spRtspId.hashCode() : 0;
        result = 31 * result + (spRtspDescription != null ? spRtspDescription.hashCode() : 0);
        return result;
    }
}