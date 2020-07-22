package sepe.domain.general;

import javax.persistence.*;

/**
 * Created by Nikos on 4/4/2015.
 */
//SP_RT_TA_SPECIALITY@SP_SP_LINK
@Entity
@Table(name = "SN_SP_RT_TA_SPECIALITY", schema = "", catalog = "")
public class SpRtTaSpeciality {
    private Long id;
    private String description;
    private Long spTaspEdulvl;

    @Id
    @Column(name = "SP_TASP_ID", nullable = false, insertable = true, updatable = true, precision = 0)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "SP_TASP_DESC", nullable = false, insertable = false, updatable = false, length = 65)
    public String getDescription() {
        return description;
    }

    public void setDescription(String desc) {
        this.description = desc;
    }

    @Basic
    @Column(name = "SP_TASP_EDULVL", nullable = false, insertable = false, updatable = false, length = 65)
    public Long getSpTaspEdulvl() {
        return spTaspEdulvl;
    }

    public void setSpTaspEdulvl(Long spTaspEdulvl) {
        this.spTaspEdulvl = spTaspEdulvl;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpRtTaSpeciality spRtTaSpeciality = (SpRtTaSpeciality) o;

        if (id != spRtTaSpeciality.id) return false;
        if (description != null ? !description.equals(spRtTaSpeciality.description) : spRtTaSpeciality.description != null)
            return false;
        if (spTaspEdulvl != null ? !spTaspEdulvl.equals(spRtTaSpeciality.spTaspEdulvl) : spRtTaSpeciality.spTaspEdulvl != null)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (spTaspEdulvl != null ? spTaspEdulvl.hashCode() : 0);
        return result;
    }
}