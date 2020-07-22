package sepe.domain.general;

import javax.persistence.*;

/**
 * Created by Nikolas on 3/16/2015.
 */
//SP_RT_EMPL_GENREQUEST_SUBJECT@SP_SP_LINK
@Entity
@Table(name = "SN_SP_RT_EMPL_GENREQUEST_SUBJ", schema = "", catalog = "")
public class SpRtEmplGenrequestSubject {
    private Long id;
    private String descr;

    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false, insertable = true, updatable = true, precision = 0)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "DESCR", nullable = true, insertable = true, updatable = true, length = 100)
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

        SpRtEmplGenrequestSubject SpRtEmplGenrequestSubject = (SpRtEmplGenrequestSubject) o;

        if (id != SpRtEmplGenrequestSubject.id) return false;
        if (descr != null ? !descr.equals(SpRtEmplGenrequestSubject.descr) : SpRtEmplGenrequestSubject.descr != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (descr != null ? descr.hashCode() : 0);
        return result;
    }
}