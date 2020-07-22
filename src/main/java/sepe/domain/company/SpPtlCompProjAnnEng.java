package sepe.domain.company;

import javax.persistence.*;

/**
 * Created by Nikos on 4/19/2015.
 */

@Entity
@Table(name = "SP_PTL_COMP_PROJ_ANN_ENG", schema = "SP_PTL", catalog = "")
public class SpPtlCompProjAnnEng {
    private Long id;
    private String firstname;
    private String lastname;
    private String afm;
    private String engineerSpeciality;
    private Long compProjAnnounId;
    private Integer invlolvementType;
    private String teeNum;
    private String addr;
    private Long companyId;

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
    @Column(name = "COMPANY_ID", nullable = false, insertable = true, updatable = true, precision = 0)
    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    @Basic
    @Column(name = "FIRSTNAME", nullable = true, insertable = true, updatable = true, length = 100)
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Basic
    @Column(name = "LASTNAME", nullable = true, insertable = true, updatable = true, length = 100)
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Basic
    @Column(name = "AFM", nullable = true, insertable = true, updatable = true, length = 50)
    public String getAfm() {
        return afm;
    }

    public void setAfm(String afm) {
        this.afm = afm;
    }

    @Basic
    @Column(name = "ENGINEER_SPECIALITY", nullable = true, insertable = true, updatable = true, length = 200)
    public String getEngineerSpeciality() {
        return engineerSpeciality;
    }

    public void setEngineerSpeciality(String engineerSpeciality) {
        this.engineerSpeciality = engineerSpeciality;
    }

    @Basic
    @Column(name = "INVLOLVEMENT_TYPE", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getInvlolvementType() {
        return invlolvementType;
    }

    public void setInvlolvementType(Integer invlolvementType) {
        this.invlolvementType = invlolvementType;
    }

    @Basic
    @Column(name = "TEE_NUM", nullable = true, insertable = true, updatable = true, length = 20)
    public String getTeeNum() {
        return teeNum;
    }

    public void setTeeNum(String teeNum) {
        this.teeNum = teeNum;
    }

    @Basic
    @Column(name = "ADDR", nullable = true, insertable = true, updatable = true, length = 200)
    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    /////////////////////////////////////////////////
    // /*
    private SpPtlCompProjAnn compProjAnn_eng;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMP_PROJ_ANNOUN_ID", nullable = false, insertable = true, updatable = false) // Name is the DB column name of the attribute
    public SpPtlCompProjAnn getCompProjAnn_eng() {
        return this.compProjAnn_eng;
    }

    public void setCompProjAnn_eng(SpPtlCompProjAnn compProjAnn) {
        this.compProjAnn_eng = compProjAnn;
    }
    // */
    /////////////////////////////////////////////////

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpPtlCompProjAnnEng that = (SpPtlCompProjAnnEng) o;

        if (addr != null ? !addr.equals(that.addr) : that.addr != null) return false;
        if (afm != null ? !afm.equals(that.afm) : that.afm != null) return false;
        if (compProjAnnounId != null ? !compProjAnnounId.equals(that.compProjAnnounId) : that.compProjAnnounId != null)
            return false;
        if (engineerSpeciality != null ? !engineerSpeciality.equals(that.engineerSpeciality) : that.engineerSpeciality != null)
            return false;
        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (invlolvementType != null ? !invlolvementType.equals(that.invlolvementType) : that.invlolvementType != null)
            return false;
        if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
        if (teeNum != null ? !teeNum.equals(that.teeNum) : that.teeNum != null) return false;

        return true;
    }

   /* @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (afm != null ? afm.hashCode() : 0);
        result = 31 * result + (engineerSpeciality != null ? engineerSpeciality.hashCode() : 0);
        result = 31 * result + (compProjAnnounId != null ? compProjAnnounId.hashCode() : 0);
        result = 31 * result + (invlolvementType != null ? invlolvementType.hashCode() : 0);
        result = 31 * result + (teeNum != null ? teeNum.hashCode() : 0);
        result = 31 * result + (addr != null ? addr.hashCode() : 0);
        return result;
    }*/
}
