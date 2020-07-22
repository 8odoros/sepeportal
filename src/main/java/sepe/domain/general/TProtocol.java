package sepe.domain.general;

import javax.persistence.*;

/**
 * Created by Nikolas on 2/19/2015.
 */
@Entity
@Table(name = "SP_PTL_V_PROTOCOL", schema = "", catalog = "")
public class TProtocol {
    private Long spProtCaseId;
    private String spPtotStatusDesc;
    private long spProtDocId;
    private long spProtStatusCode;
    private String cdText;

    @Id
    @Column(name = "SP_PROT_CASE_ID", nullable = false, insertable = true, updatable = true, precision = 0)
    public Long getSpProtCaseId() {
        return spProtCaseId;
    }

    public void setSpProtCaseId(Long spProtCaseId) {
        this.spProtCaseId = spProtCaseId;
    }

    @Basic
    @Column(name = "SP_PTOT_STATUS_DESC", nullable = true, insertable = true, updatable = true, length = 65)
    public String getSpPtotStatus() {
        return spPtotStatusDesc;
    }
    public void setSpPtotStatus(String spPtotStatus) {
        this.spPtotStatusDesc = spPtotStatus;
    }

    @Basic
    @Column(name = "SP_PROT_DOC_ID", nullable = false, insertable = true, updatable = true, precision = 0)
    public long getSpProtDocId() {
        return spProtDocId;
    }
    public void setSpProtDocId(long spProtDocId) {
        this.spProtDocId = spProtDocId;
    }


    @Basic
    @Column(name = "SP_PROT_STATUS_CODE", nullable = false, insertable = true, updatable = true, precision = 0)
    public long getSpProtStatusCode() {
        return spProtStatusCode;
    }
    public void setSpProtStatusCode(long spProtStatusCode) {
        this.spProtStatusCode = spProtStatusCode;
    }

    @Basic
    @Column(name = "CD_TEXT", nullable = true, insertable = true, updatable = true, length = 80)
    public String getCdText() {
        return cdText;
    }

    public void setCdText(String cdText) {
        this.cdText = cdText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TProtocol tProtocol = (TProtocol) o;

        if (spProtCaseId != tProtocol.spProtCaseId) return false;
        if (spProtDocId != tProtocol.spProtDocId) return false;
        if (spPtotStatusDesc != null ? !spPtotStatusDesc.equals(tProtocol.spPtotStatusDesc) : tProtocol.spPtotStatusDesc != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (spProtCaseId ^ (spProtCaseId >>> 32));
        result = 31 * result + (spPtotStatusDesc != null ? spPtotStatusDesc.hashCode() : 0);
        result = 31 * result + (int) (spProtDocId ^ (spProtDocId >>> 32));
        return result;
    }
}