package sepe.domain.company;

import sepe.domain.technician.SpPtlTechnicianRegrequest;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Nikolas on 1/24/2015.
 */
@Entity
@Table(name = "SP_PTL_COMPANY_USER_PRIVILAGES", schema = "SP_PTL", catalog = "")
public class TCompanyUserPrivilages {
    private Long id;
    private Long compId;

    private Long userId;
    private Long active;

    private String branchIds;
    private String privilages;


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
    @Column(name = "COMP_ID", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getCompId() {
        return compId;
    }

    public void setCompId(Long compId) {
        this.compId = compId;
    }

    @Basic
    @Column(name = "USER_ID", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "ACTIVE", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getActive() {
        return active;
    }

    public void setActive(Long active) {
        this.active = active;
    }


    @Basic
    @Column(name = "BRANCH_IDS", nullable = true, insertable = true, updatable = true, length = 1024)
    public String getBranchIds() {
        return branchIds;
    }

    public void setBranchIds(String branchId) {
        this.branchIds = branchId;
    }


    @Basic
    @Column(name = "PRIVILAGES", nullable = true, insertable = true, updatable = true, length = 1024)
    public String getPrivilages() {
        return privilages;
    }

    public void setPrivilages(String privilages) {
        this.privilages = privilages;
    }

    /////////////////////////////////////////////////
    // /*
    /*private Set<SpPtlCompanyAccident> spPtlCompanyAccident = new HashSet<SpPtlCompanyAccident>(0);

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "SP_PTL_COMPANY_ACCIDENT", joinColumns = {
            @JoinColumn(name = "COMPANY_ID", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "COMPANY_ID",
                    nullable = false, updatable = false) })
    public Set<SpPtlCompanyAccident> getSpPtlCompanyAccident() {
        return this.spPtlCompanyAccident;
    }

    public void setSpPtlCompanyAccident(Set<SpPtlCompanyAccident> spPtlCompanyAccident) {
        this.spPtlCompanyAccident = spPtlCompanyAccident;
    }*/
    // */
    /////////////////////////////////////////////////


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TCompanyUserPrivilages that = (TCompanyUserPrivilages) o;

        if (id != that.id) return false;
        if (branchIds != null ? !branchIds.equals(that.branchIds) : that.branchIds != null) return false;
        if (compId != null ? !compId.equals(that.compId) : that.compId != null) return false;
        if (active != null ? !active.equals(that.active) : that.active != null) return false;
        if (privilages != null ? !privilages.equals(that.privilages) : that.privilages != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (compId != null ? compId.hashCode() : 0);
        result = 31 * result + (branchIds != null ? branchIds.hashCode() : 0);
        result = 31 * result + (active != null ? active.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (privilages != null ? privilages.hashCode() : 0);
        return result;
    }
}
