package sepe.domain.general;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;


/**
 * Created by root on 4/2/2015.
 */
//TMP_DIMOS@SP_SP_LINK
@Entity
@Table(name = "SN_TMP_DIMOS", schema = "", catalog = "")
public class TKalD {
    private String dimosCode;
    private String dimosDescr;
    private String enotCode;


    @Id
    @Column(name = "DIMOS_CODE")
    public String getDimosCode() {
        return dimosCode;
    }

    public void setDimosCode(String dimosCode) {
        this.dimosCode = dimosCode;
    }

    @Basic
    @Column(name = "DIMOS_DESCR")
    public String getDimosDescr() {
        return dimosDescr;
    }

    public void setDimosDescr(String dimosDescr) {
        this.dimosDescr = dimosDescr;
    }

    @Basic
    @Column(name = "ENOT_CODE")
    public String getEnotCode() {
        return enotCode;
    }

    public void setEnotCode(String enotCode) {
        this.enotCode = enotCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TKalD tKalD = (TKalD) o;

        if (dimosCode != null ? !dimosCode.equals(tKalD.dimosCode) : tKalD.dimosCode != null) return false;
        if (dimosDescr != null ? !dimosDescr.equals(tKalD.dimosDescr) : tKalD.dimosDescr != null) return false;
        if (enotCode != null ? !enotCode.equals(tKalD.enotCode) : tKalD.enotCode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = dimosCode != null ? dimosCode.hashCode() : 0;
        result = 31 * result + (dimosDescr != null ? dimosDescr.hashCode() : 0);
        result = 31 * result + (enotCode != null ? enotCode.hashCode() : 0);
        return result;
    }
}
