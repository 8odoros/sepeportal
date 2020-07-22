package sepe.domain.general;

import javax.persistence.*;

/**
 * Created by Evangelos on 2/24/2015.
 */
//V_CD_DEPARTMENTS@SP_SP_LINK
@Entity
@Table(name = "SN_V_CD_DEPARTMENTS", schema = "", catalog = "")
public class TSepeDepartment {
    private Long cdId;
    private Long cdCode;
    private String cdText;
    private String cdKind;
    private Long cdStatus;

    @Id
    @Column(name = "CD_ID", nullable = false, insertable = true, updatable = true, precision = 0)
    public Long getCdId() {
        return cdId;
    }

    public void setCdId(Long cdId) {
        this.cdId = cdId;
    }

    @Basic
    @Column(name = "CD_CODE", nullable = false, insertable = false, updatable = false, precision = 0)
    public Long getCdCode() {
        return cdCode;
    }

    public void setCdCode(Long cdCode) {
        this.cdCode = cdCode;
    }

    @Basic
    @Column(name = "CD_TEXT", nullable = false, insertable = false, updatable = false, length = 65)
    public String getCdText() {
        return cdText;
    }

    public void setCdText(String cdText) {
        this.cdText = cdText;
    }

    @Basic
    @Column(name = "CD_KIND", nullable = false, insertable = false, updatable = false, length = 65)
    public String getCdKind() {
        return cdKind;
    }

    public void setCdKind(String cdKind) {
        this.cdKind = cdKind;
    }

    @Basic
    @Column(name = "CD_STATUS", nullable = false, insertable = false, updatable = false, precision = 0)
    public Long getCdStatus() {
        return cdStatus;
    }

    public void setCdStatus(Long cdStatus) {
        this.cdStatus = cdStatus;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TSepeDepartment tProtocol = (TSepeDepartment) o;

        if (cdId != tProtocol.cdId) return false;
        if (cdCode != null ? !cdCode.equals(tProtocol.cdCode) : tProtocol.cdCode != null)
            return false;
        if (cdText != null ? !cdText.equals(tProtocol.cdText) : tProtocol.cdText != null)
            return false;
        if (cdKind != null ? !cdKind.equals(tProtocol.cdKind) : tProtocol.cdKind != null)
            return false;
        if (cdStatus != null ? !cdStatus.equals(tProtocol.cdStatus) : tProtocol.cdStatus != null)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (cdId ^ (cdId >>> 32));
        result = 31 * result + (cdCode != null ? cdCode.hashCode() : 0);
        result = 31 * result + (cdText != null ? cdText.hashCode() : 0);
        result = 31 * result + (cdKind != null ? cdKind.hashCode() : 0);
        result = 31 * result + (cdStatus != null ? cdStatus.hashCode() : 0);
        return result;
    }
}