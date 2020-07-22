package sepe.domain.general;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;

/**
 * Created by root on 4/2/2015.
 */
//TMP_PERIFENOT@SP_SP_LINK
@Entity
@Table(name = "SN_TMP_PERIFENOT", schema = "", catalog = "")
public class TKalPe {
    private String enotCode;
    private String descr;
    private String perifCode;

    @Id
    @Column(name = "ENOT_CODE")
    public String getEnotCode() {
        return enotCode;
    }

    public void setEnotCode(String enotCode) {
        this.enotCode = enotCode;
    }

    @Basic
    @Column(name = "DESCR")
    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    @Basic
    @Column(name = "PERIF_CODE")
    public String getPerifCode() {
        return perifCode;
    }

    public void setPerifCode(String perifCode) {
        this.perifCode = perifCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TKalPe tKalPe = (TKalPe) o;

        if (descr != null ? !descr.equals(tKalPe.descr) : tKalPe.descr != null) return false;
        if (enotCode != null ? !enotCode.equals(tKalPe.enotCode) : tKalPe.enotCode != null) return false;
        if (perifCode != null ? !perifCode.equals(tKalPe.perifCode) : tKalPe.perifCode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = enotCode != null ? enotCode.hashCode() : 0;
        result = 31 * result + (descr != null ? descr.hashCode() : 0);
        result = 31 * result + (perifCode != null ? perifCode.hashCode() : 0);
        return result;
    }
}