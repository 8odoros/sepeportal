package sepe.domain.company;

import javax.persistence.*;
import java.sql.Clob;

/**
 * Created by Evangelos on 2/24/2015.
 */

@Entity
@Table(name = "SP_PTL_V_BRANCH_IKA_COMP", schema = "SP_PTL", catalog = "")
public class TEmployerBranchIKAComp {

    private String rgEmpEmployerId;

    @Id
    @javax.persistence.Column(name = "RG_EMP_EMPLOYER_ID", nullable = true, insertable = true, updatable = true, length = 20)
    public String getRgEmpEmployerId() {
        return rgEmpEmployerId;
    }
    public void setRgEmpEmployerId(String rgEmpEmployerId) {
        this.rgEmpEmployerId = rgEmpEmployerId;
    }


    private String branches;

    @Basic
    @javax.persistence.Column(name = "BRANCHES", nullable = true, insertable = true, updatable = true, length = 10000)
    public String getBranches() {
        return branches;
    }
    public void setBranches(String branches) {
        this.branches = branches;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TEmployerBranchIKAComp that = (TEmployerBranchIKAComp) o;

        if (rgEmpEmployerId != null ? !rgEmpEmployerId.equals(that.rgEmpEmployerId) : that.rgEmpEmployerId != null) return false;
        if (branches != null ? !branches.equals(that.branches) : that.branches != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = rgEmpEmployerId != null ? rgEmpEmployerId.hashCode() : 0;
        result = 31 * result + (branches != null ? branches.hashCode() : 0);

        return result;
    }
}