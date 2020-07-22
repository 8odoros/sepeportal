package sepe.domain.company;

import javax.persistence.*;

@Entity
@Table(name = "SP_PTL_BRANCH", schema = "SP_PTL", catalog = "")
public class TBranch {
    private Long id;
    private String name;
    private String title;
    private String afm;
    private String phone;
    private String fax;
    private String email;
    private Long compId;
    private String ame;

    @Id
    @GeneratedValue
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "TITLE")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "AFM")
    public String getAfm() {
        return afm;
    }

    public void setAfm(String afm) {
        this.afm = afm;
    }

    @Basic
    @Column(name = "PHONE")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "FAX")
    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    @Basic
    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "COMP_ID")
    public Long getCompId() {
        return compId;
    }

    public void setCompId(Long compId) {
        this.compId = compId;
    }

    @Basic
    @Column(name = "AME")
    public String getAme() {
        return ame;
    }

    public void setAme(String ame) {
        this.ame = ame;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TBranch that = (TBranch) o;

        if (id != that.id) return false;
        if (afm != null ? !afm.equals(that.afm) : that.afm != null) return false;
        if (ame != null ? !ame.equals(that.ame) : that.ame != null) return false;
        if (compId != null ? !compId.equals(that.compId) : that.compId != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (fax != null ? !fax.equals(that.fax) : that.fax != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (afm != null ? afm.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (fax != null ? fax.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (compId != null ? compId.hashCode() : 0);
        result = 31 * result + (ame != null ? ame.hashCode() : 0);
        return result;
    }
}

