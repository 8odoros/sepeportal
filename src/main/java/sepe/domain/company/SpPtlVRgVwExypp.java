package sepe.domain.company;

import javax.persistence.*;


/**
 * Created by Marios on 12/20/2015.
 */
@Entity
@Table(name = "SP_PTL_V_RG_VW_EXYPP", schema = "", catalog = "")
public class SpPtlVRgVwExypp {
    private Long id;
    private String rgEmpEmployerId;
    private String rgEmpTaxationNumber;
    private String rgEmpFullname;
    private String rgEmpEmployerStatus;

    @Id
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "RG_EMP_EMPLOYER_ID")
    public String getRgEmpEmployerId() {
        return rgEmpEmployerId;
    }

    public void setRgEmpEmployerId(String rgEmpEmployerId) {
        this.rgEmpEmployerId = rgEmpEmployerId;
    }

    @Basic
    @Column(name = "RG_EMP_FULLNAME")
    public String getRgEmpFullname() {
        return rgEmpFullname;
    }

    public void setRgEmpFullname(String rgEmpFullname) {
        this.rgEmpFullname = rgEmpFullname;
    }

    @Basic
    @Column(name = "RG_EMP_TAXATION_NUMBER")
    public String getRgEmpTaxationNumber() {
        return rgEmpTaxationNumber;
    }

    public void setRgEmpTaxationNumber(String rgEmpTaxationNumber) {
        this.rgEmpTaxationNumber = rgEmpTaxationNumber;
    }

    @Basic
    @Column(name = "RG_EMP_EMPLOYER_STATUS")
    public String getRgEmpEmployerStatus() {
        return rgEmpEmployerStatus;
    }

    public void setRgEmpEmployerStatus(String rgEmpEmployerStatus) {
        this.rgEmpEmployerStatus = rgEmpEmployerStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpPtlVRgVwExypp that = (SpPtlVRgVwExypp) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (rgEmpEmployerId != null ? !rgEmpEmployerId.equals(that.rgEmpEmployerId) : that.rgEmpEmployerId != null)
            return false;
        if (rgEmpTaxationNumber != null ? !rgEmpTaxationNumber.equals(that.rgEmpTaxationNumber) : that.rgEmpTaxationNumber != null)
            return false;
        if (rgEmpFullname != null ? !rgEmpFullname.equals(that.rgEmpFullname) : that.rgEmpFullname != null)
            return false;
        return !(rgEmpEmployerStatus != null ? !rgEmpEmployerStatus.equals(that.rgEmpEmployerStatus) : that.rgEmpEmployerStatus != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (rgEmpEmployerId != null ? rgEmpEmployerId.hashCode() : 0);
        result = 31 * result + (rgEmpTaxationNumber != null ? rgEmpTaxationNumber.hashCode() : 0);
        result = 31 * result + (rgEmpFullname != null ? rgEmpFullname.hashCode() : 0);
        result = 31 * result + (rgEmpEmployerStatus != null ? rgEmpEmployerStatus.hashCode() : 0);
        return result;
    }

}