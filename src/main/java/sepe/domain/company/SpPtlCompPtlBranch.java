package sepe.domain.company;

import javax.persistence.*;

/**
 * Created by Nikos on 5/16/2015.
 */
@Entity
@Table(name = "SP_PTL_COMP_PTL_BRANCH", schema = "SP_PTL", catalog = "")
public class SpPtlCompPtlBranch {
    private Long id;
    private Long companyId;
    private String brAddr;
    private String brAddrD;
    private String brAddrK;
    private String brAddrP;
    private String brAddrPe;
    private String brAddrTk;
    private Long ebrBranchId;
    private String brDescr;
    private Long brActive;

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
    @Column(name = "COMPANY_ID", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    @Basic
    @Column(name = "BR_ADDR", nullable = true, insertable = true, updatable = true, length = 50)
    public String getBrAddr() {
        return brAddr;
    }

    public void setBrAddr(String brAddr) {
        this.brAddr = (this.ebrBranchId != null ? (this.ebrBranchId.toString()  + ". ") : "") + brAddr;
    }

    @Basic
    @Column(name = "BR_ADDR_D", nullable = true, insertable = true, updatable = true, length = 100)
    public String getBrAddrD() {
        return brAddrD;
    }

    public void setBrAddrD(String brAddrD) {
        this.brAddrD = brAddrD;
    }

    @Basic
    @Column(name = "BR_ADDR_K", nullable = true, insertable = true, updatable = true, length = 100)
    public String getBrAddrK() {
        return brAddrK;
    }

    public void setBrAddrK(String brAddrK) {
        this.brAddrK = brAddrK;
    }

    @Basic
    @Column(name = "BR_ADDR_P", nullable = true, insertable = true, updatable = true, length = 100)
    public String getBrAddrP() {
        return brAddrP;
    }

    public void setBrAddrP(String brAddrP) {
        this.brAddrP = brAddrP;
    }

    @Basic
    @Column(name = "BR_ADDR_PE", nullable = true, insertable = true, updatable = true, length = 100)
    public String getBrAddrPe() {
        return brAddrPe;
    }

    public void setBrAddrPe(String brAddrPe) {
        this.brAddrPe = brAddrPe;
    }

    @Basic
    @Column(name = "BR_ADDR_TK", nullable = true, insertable = true, updatable = true, length = 50)
    public String getBrAddrTk() {
        return brAddrTk;
    }

    public void setBrAddrTk(String brAddrTk) {
        this.brAddrTk = brAddrTk;
    }

    @Basic
    @Column(name = "EBR_BRANCH_ID", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getEbrBranchId() {
        return ebrBranchId;
    }

    public void setEbrBranchId(Long ebrBranchId) {
        this.ebrBranchId = ebrBranchId;
    }

    @Basic
    @Column(name = "BR_DESCR", nullable = true, insertable = true, updatable = true, length = 100)
    public String getBrDescr() {
        return brDescr;
    }

    public void setBrDescr(String brDescr) {
        this.brDescr = brDescr;
    }

    @Basic
    @Column(name = "BR_ACTIVE", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getBrActive() {
        return brActive;
    }

    public void setBrActive(Long brActive) {
        this.brActive = brActive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpPtlCompPtlBranch that = (SpPtlCompPtlBranch) o;

        if (id != that.id) return false;
        if (brAddr != null ? !brAddr.equals(that.brAddr) : that.brAddr != null) return false;
        if (brAddrD != null ? !brAddrD.equals(that.brAddrD) : that.brAddrD != null) return false;
        if (brAddrK != null ? !brAddrK.equals(that.brAddrK) : that.brAddrK != null) return false;
        if (brAddrP != null ? !brAddrP.equals(that.brAddrP) : that.brAddrP != null) return false;
        if (brAddrPe != null ? !brAddrPe.equals(that.brAddrPe) : that.brAddrPe != null) return false;
        if (brAddrTk != null ? !brAddrTk.equals(that.brAddrTk) : that.brAddrTk != null) return false;
        if (brDescr != null ? !brDescr.equals(that.brDescr) : that.brDescr != null) return false;
        if (companyId != null ? !companyId.equals(that.companyId) : that.companyId != null) return false;
        if (ebrBranchId != null ? !ebrBranchId.equals(that.ebrBranchId) : that.ebrBranchId != null) return false;
        if (brActive != null ? !brActive.equals(that.brActive) : that.brActive != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (companyId != null ? companyId.hashCode() : 0);
        result = 31 * result + (brAddr != null ? brAddr.hashCode() : 0);
        result = 31 * result + (brAddrD != null ? brAddrD.hashCode() : 0);
        result = 31 * result + (brAddrK != null ? brAddrK.hashCode() : 0);
        result = 31 * result + (brAddrP != null ? brAddrP.hashCode() : 0);
        result = 31 * result + (brAddrPe != null ? brAddrPe.hashCode() : 0);
        result = 31 * result + (brAddrTk != null ? brAddrTk.hashCode() : 0);
        result = 31 * result + (ebrBranchId != null ? ebrBranchId.hashCode() : 0);
        result = 31 * result + (brDescr != null ? brDescr.hashCode() : 0);
        result = 31 * result + (brActive != null ? brActive.hashCode() : 0);
        return result;
    }
}