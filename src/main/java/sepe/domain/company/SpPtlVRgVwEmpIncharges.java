package sepe.domain.company;

import javax.persistence.*;


/**
 * Created by Nikos on 6/20/2015.
 */
@Entity
@Table(name = "SP_PTL_V_RG_VW_EMP_INCHARGES", schema = "", catalog = "")
public class SpPtlVRgVwEmpIncharges {
    private String id;
    private String rgEinEmployerId;
    private Integer rgEinInChargeNo;
    private String rgEinName;
    private String rgEinSurname;
    private String rgEinTaxationNumber;

    @Id
    @Column(name = "ID")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "RG_EIN_EMPLOYER_ID")
    public String getRgEinEmployerId() {
        return rgEinEmployerId;
    }

    public void setRgEinEmployerId(String rgEinEmployerId) {
        this.rgEinEmployerId = rgEinEmployerId;
    }

    @Basic
    @Column(name = "RG_EIN_IN_CHARGE_NO")
    public Integer getRgEinInChargeNo() {
        return rgEinInChargeNo;
    }

    public void setRgEinInChargeNo(Integer rgEinInChargeNo) {
        this.rgEinInChargeNo = rgEinInChargeNo;
    }

    @Basic
    @Column(name = "RG_EIN_NAME")
    public String getRgEinName() {
        return rgEinName;
    }

    public void setRgEinName(String rgEinName) {
        this.rgEinName = rgEinName;
    }

    @Basic
    @Column(name = "RG_EIN_SURNAME")
    public String getRgEinSurname() {
        return rgEinSurname;
    }

    public void setRgEinSurname(String rgEinSurname) {
        this.rgEinSurname = rgEinSurname;
    }

    @Basic
    @Column(name = "RG_EIN_TAXATION_NUMBER")
    public String getRgEinTaxationNumber() {
        return rgEinTaxationNumber;
    }

    public void setRgEinTaxationNumber(String rgEinTaxationNumber) {
        this.rgEinTaxationNumber = rgEinTaxationNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpPtlVRgVwEmpIncharges that = (SpPtlVRgVwEmpIncharges) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (rgEinEmployerId != null ? !rgEinEmployerId.equals(that.rgEinEmployerId) : that.rgEinEmployerId != null)
            return false;
        if (rgEinInChargeNo != null ? !rgEinInChargeNo.equals(that.rgEinInChargeNo) : that.rgEinInChargeNo != null)
            return false;
        if (rgEinName != null ? !rgEinName.equals(that.rgEinName) : that.rgEinName != null) return false;
        if (rgEinSurname != null ? !rgEinSurname.equals(that.rgEinSurname) : that.rgEinSurname != null) return false;
        if (rgEinTaxationNumber != null ? !rgEinTaxationNumber.equals(that.rgEinTaxationNumber) : that.rgEinTaxationNumber != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (rgEinEmployerId != null ? rgEinEmployerId.hashCode() : 0);
        result = 31 * result + (rgEinInChargeNo != null ? rgEinInChargeNo.hashCode() : 0);
        result = 31 * result + (rgEinName != null ? rgEinName.hashCode() : 0);
        result = 31 * result + (rgEinSurname != null ? rgEinSurname.hashCode() : 0);
        result = 31 * result + (rgEinTaxationNumber != null ? rgEinTaxationNumber.hashCode() : 0);
        return result;
    }
}