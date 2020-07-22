package sepe.domain.general;

import javax.persistence.*;

/**
 * Created by Nikolas on 2/16/2015.
 */
//SP_RT_LAD_SUBJECTS@SP_SP_LINK
@Entity
@Table(name = "SN_SP_RT_LAD_SUBJECTS", schema = "", catalog = "")
public class TEmployeeDisputeSubject {
    private Long spRlsDetailCode;
    private String spRlsDetailDesc;

    @Id
    @GeneratedValue
    @Column(name = "SP_RLS_DETAIL_CODE", nullable = false, insertable = true, updatable = true, precision = 0)
    public Long getSpRlsDetailCode() {
        return spRlsDetailCode;
    }

    public void setSpRlsDetailCode(Long spRlsDetailCode) {
        this.spRlsDetailCode = spRlsDetailCode;
    }

    @Basic
    @Column(name = "SP_RLS_DETAIL_DESC", nullable = true, insertable = true, updatable = true, length = 100)
    public String getSpRlsDetailDesc() {
        return spRlsDetailDesc;
    }

    public void setSpRlsDetailDesc(String spRlsDetailDesc) {
        this.spRlsDetailDesc = spRlsDetailDesc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TEmployeeDisputeSubject that = (TEmployeeDisputeSubject) o;

        if (spRlsDetailCode != that.spRlsDetailCode) return false;
        if (spRlsDetailDesc != null ? !spRlsDetailDesc.equals(that.spRlsDetailDesc) : that.spRlsDetailDesc != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (spRlsDetailCode ^ (spRlsDetailCode >>> 32));
        result = 31 * result + (spRlsDetailDesc != null ? spRlsDetailDesc.hashCode() : 0);
        return result;
    }
}