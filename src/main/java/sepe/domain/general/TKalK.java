package sepe.domain.general;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;

/**
 * Created by root on 4/2/2015.
 */
//TMP_KOIN@SP_SP_LINK
@Entity
@Table(name = "SN_TMP_KOIN", schema = "", catalog = "")
public class TKalK {
    private String koinCode;
    private String koinDescr;
    private String dCode;
    private String dDescr;
    private String pCode;
    private String pDescr;
    private String peCode;
    private String peDescr;

    @Id
    @Column(name = "KOIN_CODE", nullable = true, insertable = true, updatable = true, length = 8)
    public String getKoinCode() {
        return koinCode;
    }

    public void setKoinCode(String koinCode) {
        this.koinCode = koinCode;
    }

    @Basic
    @Column(name = "KOIN_DESCR", nullable = true, insertable = true, updatable = true, length = 200)
    public String getKoinDescr() {
        return koinDescr;
    }

    public void setKoinDescr(String koinDescr) {
        this.koinDescr = koinDescr;
    }

    @Basic
    @Column(name = "DIMOS_CODE", nullable = true, insertable = true, updatable = true, length = 20)
    public String getdCode() {
        return dCode;
    }

    public void setdCode(String dCode) {
        this.dCode = dCode;
    }

    @Basic
    @Column(name = "SP_RTKAL_MUNICIPAL_DESCR", nullable = true, insertable = true, updatable = true, length = 200)
    public String getdDescr() {
        return dDescr;
    }

    public void setdDescr(String dDescr) {
        this.dDescr = dDescr;
    }

    @Basic
    @Column(name = "SP_RTKAL_REGION_CODE", nullable = true, insertable = true, updatable = true, length = 20)
    public String getpCode() {
        return pCode;
    }

    public void setpCode(String pCode) {
        this.pCode = pCode;
    }

    @Basic
    @Column(name = "SP_RTKAL_REGION_DESCR", nullable = true, insertable = true, updatable = true, length = 200)
    public String getpDescr() {
        return pDescr;
    }

    public void setpDescr(String pDescr) {
        this.pDescr = pDescr;
    }

    @Basic
    @Column(name = "SP_RTKAL_COUNTY_CODE", nullable = true, insertable = true, updatable = true, length = 20)
    public String getPeCode() {
        return peCode;
    }

    public void setPeCode(String peCode) {
        this.peCode = peCode;
    }

    @Basic
    @Column(name = "SP_RTKAL_COUNTY_DESCR", nullable = true, insertable = true, updatable = true, length = 200)
    public String getPeDescr() {
        return peDescr;
    }

    public void setPeDescr(String peDescr) {
        this.peDescr = peDescr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TKalK that = (TKalK) o;

        if (dCode != null ? !dCode.equals(that.dCode) : that.dCode != null) return false;
        if (dDescr != null ? !dDescr.equals(that.dDescr) : that.dDescr != null) return false;
        if (koinCode != null ? !koinCode.equals(that.koinCode) : that.koinCode != null) return false;
        if (koinDescr != null ? !koinDescr.equals(that.koinDescr) : that.koinDescr != null) return false;
        if (pCode != null ? !pCode.equals(that.pCode) : that.pCode != null) return false;
        if (pDescr != null ? !pDescr.equals(that.pDescr) : that.pDescr != null) return false;
        if (peCode != null ? !peCode.equals(that.peCode) : that.peCode != null) return false;
        if (peDescr != null ? !peDescr.equals(that.peDescr) : that.peDescr != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = koinCode != null ? koinCode.hashCode() : 0;
        result = 31 * result + (koinDescr != null ? koinDescr.hashCode() : 0);
        result = 31 * result + (dCode != null ? dCode.hashCode() : 0);
        result = 31 * result + (dDescr != null ? dDescr.hashCode() : 0);
        result = 31 * result + (pCode != null ? pCode.hashCode() : 0);
        result = 31 * result + (pDescr != null ? pDescr.hashCode() : 0);
        result = 31 * result + (peCode != null ? peCode.hashCode() : 0);
        result = 31 * result + (peDescr != null ? peDescr.hashCode() : 0);
        return result;
    }
}
