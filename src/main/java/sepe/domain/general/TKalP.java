package sepe.domain.general;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;

/**
 * Created by root on 4/2/2015.
 */
//TMP_PERIFEREIA@SP_SP_LINK
@Entity
@Table(name = "SN_TMP_PERIFEREIA", schema = "", catalog = "")
public class TKalP {
    private String code;
    private String descr;

    @Id
    @Column(name = "CODE")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "DESCR")
    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TKalP tKalP = (TKalP) o;

        if (code != null ? !code.equals(tKalP.code) : tKalP.code != null) return false;
        if (descr != null ? !descr.equals(tKalP.descr) : tKalP.descr != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + (descr != null ? descr.hashCode() : 0);
        return result;
    }
}
