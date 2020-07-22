package sepe.domain.company;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Evangelos on 2/24/2015.
 */
/*
@Embeddable
class CompositeId implements Serializable{

    String companyId;
    long branchId;

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public long getBranchId() {
        return branchId;
    }

    public void setBranchId(long branchId) {
        this.branchId = branchId;
    }
}
*/


@Entity
@Table(name = "SP_PTL_V_BRANCH_IKA", schema = "SP_PTL", catalog = "")
public class TEmployerBranchIKA {

    private String id;

    @Id
    @javax.persistence.Column(name = "ID", nullable = true, insertable = true, updatable = true, length = 50)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    private String rgEbrEmployerId;

    @Basic
    @javax.persistence.Column(name = "RG_EBR_EMPLOYER_ID", nullable = true, insertable = true, updatable = true, length = 20)
    public String getRgEbrEmployerId() {
        return rgEbrEmployerId;
    }

    public void setRgEbrEmployerId(String rgEbrEmployerId) {
        this.rgEbrEmployerId = rgEbrEmployerId;
    }

    private Long rgEbrEmpSepeId;

    @Basic
    @javax.persistence.Column(name = "RG_EBR_EMP_SEPE_ID", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getRgEbrEmpSepeId() {
        return rgEbrEmpSepeId;
    }

    public void setRgEbrEmpSepeId(Long rgEbrEmpSepeId) {
        this.rgEbrEmpSepeId = rgEbrEmpSepeId;
    }


    private Long rgEbrBranchId;

    @Basic
    @javax.persistence.Column(name = "RG_EBR_BRANCH_ID", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getRgEbrBranchId() {
        return rgEbrBranchId;
    }

    public void setRgEbrBranchId(Long rgEbrBranchId) {
        this.rgEbrBranchId = rgEbrBranchId;
    }

    private String rgEbrAddressStreet;

    @Basic
    @javax.persistence.Column(name = "RG_EBR_ADDRESS_STREET", nullable = true, insertable = true, updatable = true, length = 200)
    public String getRgEbrAddressStreet() {
        return rgEbrAddressStreet;
    }

    public void setRgEbrAddressStreet(String rgEbrAddressStreet) {
        this.rgEbrAddressStreet = rgEbrAddressStreet;
    }

    private String rgEbrZipCode;

    @Basic
    @javax.persistence.Column(name = "RG_EBR_ZIP_CODE", nullable = true, insertable = true, updatable = true, length = 50)
    public String getRgEbrZipCode() {
        return rgEbrZipCode;
    }

    public void setRgEbrZipCode(String rgEbrZipCode) {
        this.rgEbrZipCode = rgEbrZipCode;
    }

    private String rgEbrKallikratis;

    @Basic
    @javax.persistence.Column(name = "RG_EBR_KALLIKRATIS", nullable = true, insertable = true, updatable = true, length = 20)
    public String getRgEbrKallikratis() {
        return rgEbrKallikratis;
    }

    public void setRgEbrKallikratis(String rgEbrKallikratis) {
        this.rgEbrKallikratis = rgEbrKallikratis;
    }

    private String rgEbrPhoneNumber;

    @Basic
    @javax.persistence.Column(name = "RG_EBR_PHONE_NUMBER", nullable = true, insertable = true, updatable = true, length = 20)
    public String getRgEbrPhoneNumber() {
        return rgEbrPhoneNumber;
    }

    public void setRgEbrPhoneNumber(String rgEbrPhoneNumber) {
        this.rgEbrPhoneNumber = rgEbrPhoneNumber;
    }

    /*private String rgEbrMainStakod;

    @Basic
    @javax.persistence.Column(name = "RG_EBR_MAIN_STAKOD", nullable = true, insertable = true, updatable = true, length = 20)
    public String getEbrMainStakod() {
        return rgEbrSecStakod1;
    }

    public void setRgEbrMainStakod(String rgEbrMainStakod) {
        this.rgEbrMainStakod = rgEbrMainStakod;
    }*/

    private String rgEbrSecStakod1;

    @Basic
    @javax.persistence.Column(name = "RG_EBR_SEC_STAKOD_1", nullable = true, insertable = true, updatable = true, length = 20)
    public String getRgEbrSecStakod1() {
        return rgEbrSecStakod1;
    }

    public void setRgEbrSecStakod1(String rgEbrSecStakod1) {
        this.rgEbrSecStakod1 = rgEbrSecStakod1;
    }

    private String rgEbrSecStakod2;

    @Basic
    @javax.persistence.Column(name = "RG_EBR_SEC_STAKOD_2", nullable = true, insertable = true, updatable = true, length = 20)
    public String getRgEbrSecStakod2() {
        return rgEbrSecStakod2;
    }

    public void setRgEbrSecStakod2(String rgEbrSecStakod2) {
        this.rgEbrSecStakod2 = rgEbrSecStakod2;
    }

    private String rgEbrSecStakod3;

    @Basic
    @javax.persistence.Column(name = "RG_EBR_SEC_STAKOD_3", nullable = true, insertable = true, updatable = true, length = 20)
    public String getRgEbrSecStakod3() {
        return rgEbrSecStakod3;
    }

    public void setRgEbrSecStakod3(String rgEbrSecStakod3) {
        this.rgEbrSecStakod3 = rgEbrSecStakod3;
    }

    private String rgEbrSecStakod4;

    @Basic
    @javax.persistence.Column(name = "RG_EBR_SEC_STAKOD_4", nullable = true, insertable = true, updatable = true, length = 20)
    public String getRgEbrSecStakod4() {
        return rgEbrSecStakod4;
    }

    public void setRgEbrSecStakod4(String rgEbrSecStakod4) {
        this.rgEbrSecStakod4 = rgEbrSecStakod4;
    }

    private String rgEbrSecStakod5;

    @Basic
    @javax.persistence.Column(name = "RG_EBR_SEC_STAKOD_5", nullable = true, insertable = true, updatable = true, length = 20)
    public String getRgEbrSecStakod5() {
        return rgEbrSecStakod5;
    }

    public void setRgEbrSecStakod5(String rgEbrSecStakod5) {
        this.rgEbrSecStakod5 = rgEbrSecStakod5;
    }

    private String rgEmpName;

    @Basic
    @javax.persistence.Column(name = "RG_EMP_NAME", nullable = true, insertable = true, updatable = true, length = 50)
    public String getRgEmpName() {
        return rgEmpName;
    }

    public void setRgEmpName(String rgEmpName) {
        this.rgEmpName = rgEmpName;
    }

    private String rgEmpFullname;

    @Basic
    @javax.persistence.Column(name = "RG_EMP_FULLNAME", nullable = true, insertable = true, updatable = true, length = 300)
    public String getRgEmpFullname() {
        return rgEmpFullname;
    }

    public void setRgEmpFullname(String rgEmpFullname) {
        this.rgEmpFullname = rgEmpFullname;
    }


    ///////////////////////////////////////////////////////////////////////////////////
    private String rgEbrTaxationOfficeCode;

    @Basic
    @javax.persistence.Column(name = "RG_EMP_TAXATION_OFFICE_CODE", nullable = true, insertable = true, updatable = true, length = 5)
    public String getRgEbrTaxationOfficeCode() {
        return rgEbrTaxationOfficeCode;
    }

    public void setRgEbrTaxationOfficeCode(String rgEbrTaxationOfficeCode) {
        this.rgEbrTaxationOfficeCode = rgEbrTaxationOfficeCode;
    }

    private String rgEbrTaxationNumber;

    @Basic
    @javax.persistence.Column(name = "RG_EMP_TAXATION_NUMBER", nullable = true, insertable = true, updatable = true, length = 10)
    public String getRgEbrTaxationNumber() {
        return rgEbrTaxationNumber;
    }

    public void setRgEbrTaxationNumber(String rgEbrTaxationNumber) {
        this.rgEbrTaxationNumber = rgEbrTaxationNumber;
    }

    private String rgEbrLegalCategoryCode;

    @Basic
    @javax.persistence.Column(name = "RG_EMP_LEGAL_CATEGORY_CODE", nullable = true, insertable = true, updatable = true, length = 5)
    public String getRgEbrLegalCategoryCode() {
        return rgEbrLegalCategoryCode;
    }

    public void setRgEbrLegalCategoryCode(String rgEbrLegalCategoryCode) {
        this.rgEbrLegalCategoryCode = rgEbrLegalCategoryCode;
    }

    private String rgEmpPhysicalFlag;

    @Basic
    @javax.persistence.Column(name = "RG_EMP_PHYSICAL_FLAG", nullable = true, insertable = true, updatable = true, length = 1)
    public String getRgEmpPhysicalFlag() {
        return rgEmpPhysicalFlag;
    }

    public void setRgEmpPhysicalFlag(String rgEmpPhysicalFlag) {
        this.rgEmpPhysicalFlag = rgEmpPhysicalFlag;
    }

    private String rgEmpDiscreetTitle;

    @Basic
    @javax.persistence.Column(name = "RG_EMP_DISCREET_TITLE", nullable = true, insertable = true, updatable = true, length = 50)
    public String getRgEmpDiscreetTitle() {
        return rgEmpDiscreetTitle;
    }

    public void setRgEmpDiscreetTitle(String rgEmpDiscreetTitle) {
        this.rgEmpDiscreetTitle = rgEmpDiscreetTitle;
    }



    private String rgEmpEmployerId;

    @Basic
    @javax.persistence.Column(name = "RG_EMP_EMPLOYER_ID", nullable = true, insertable = true, updatable = true, length = 20)
    public String getRgEmpEmployerId() {
        return rgEmpEmployerId;
    }

    public void setRgEmpEmployerId(String rgEmpEmployerId) {
        this.rgEmpEmployerId = rgEmpEmployerId;
    }


    private String rgEbrMainBranchFlag;

    @Basic
    @javax.persistence.Column(name = "RG_EBR_MAIN_BRANCH_FLAG", nullable = true, insertable = true, updatable = true, length = 1)
    public String getRgEbrMainBranchFlag() {
        return rgEbrMainBranchFlag;
    }

    public void setRgEbrMainBranchFlag(String rgEbrMainBranchFlag) {
        this.rgEbrMainBranchFlag = rgEbrMainBranchFlag;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TEmployerBranchIKA that = (TEmployerBranchIKA) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        if (rgEmpEmployerId != null ? !rgEmpEmployerId.equals(that.rgEmpEmployerId) : that.rgEmpEmployerId != null) return false;
        if (rgEbrMainBranchFlag != null ? !rgEbrMainBranchFlag.equals(that.rgEbrMainBranchFlag) : that.rgEbrMainBranchFlag != null) return false;

        if (rgEbrAddressStreet != null ? !rgEbrAddressStreet.equals(that.rgEbrAddressStreet) : that.rgEbrAddressStreet != null)
            return false;
        if (rgEbrBranchId != null ? !rgEbrBranchId.equals(that.rgEbrBranchId) : that.rgEbrBranchId != null)
            return false;
        if (rgEbrEmployerId != null ? !rgEbrEmployerId.equals(that.rgEbrEmployerId) : that.rgEbrEmployerId != null)
            return false;
        if (rgEbrKallikratis != null ? !rgEbrKallikratis.equals(that.rgEbrKallikratis) : that.rgEbrKallikratis != null)
            return false;
        if (rgEbrPhoneNumber != null ? !rgEbrPhoneNumber.equals(that.rgEbrPhoneNumber) : that.rgEbrPhoneNumber != null)
            return false;
        if (rgEbrSecStakod1 != null ? !rgEbrSecStakod1.equals(that.rgEbrSecStakod1) : that.rgEbrSecStakod1 != null)
            return false;
        if (rgEbrSecStakod2 != null ? !rgEbrSecStakod2.equals(that.rgEbrSecStakod2) : that.rgEbrSecStakod2 != null)
            return false;
        if (rgEbrSecStakod3 != null ? !rgEbrSecStakod3.equals(that.rgEbrSecStakod3) : that.rgEbrSecStakod3 != null)
            return false;
        if (rgEbrSecStakod4 != null ? !rgEbrSecStakod4.equals(that.rgEbrSecStakod4) : that.rgEbrSecStakod4 != null)
            return false;
        if (rgEbrSecStakod5 != null ? !rgEbrSecStakod5.equals(that.rgEbrSecStakod5) : that.rgEbrSecStakod5 != null)
            return false;
        if (rgEbrZipCode != null ? !rgEbrZipCode.equals(that.rgEbrZipCode) : that.rgEbrZipCode != null) return false;
        if (rgEmpFullname != null ? !rgEmpFullname.equals(that.rgEmpFullname) : that.rgEmpFullname != null)
            return false;
        if (rgEmpName != null ? !rgEmpName.equals(that.rgEmpName) : that.rgEmpName != null) return false;

        if (rgEbrTaxationOfficeCode != null ? !rgEbrTaxationOfficeCode.equals(that.rgEbrTaxationOfficeCode) : that.rgEbrTaxationOfficeCode != null) return false;
        if (rgEbrTaxationNumber != null ? !rgEbrTaxationNumber.equals(that.rgEbrTaxationNumber) : that.rgEbrTaxationNumber != null) return false;
        if (rgEbrLegalCategoryCode != null ? !rgEbrLegalCategoryCode.equals(that.rgEbrLegalCategoryCode) : that.rgEbrLegalCategoryCode != null) return false;
        if (rgEmpPhysicalFlag != null ? !rgEmpPhysicalFlag.equals(that.rgEmpPhysicalFlag) : that.rgEmpPhysicalFlag != null) return false;
        if (rgEmpDiscreetTitle != null ? !rgEmpDiscreetTitle.equals(that.rgEmpDiscreetTitle) : that.rgEmpDiscreetTitle != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = rgEbrEmployerId != null ? rgEbrEmployerId.hashCode() : 0;
        result = 31 * result + (rgEbrBranchId != null ? rgEbrBranchId.hashCode() : 0);
        result = 31 * result + (rgEbrAddressStreet != null ? rgEbrAddressStreet.hashCode() : 0);
        result = 31 * result + (rgEbrZipCode != null ? rgEbrZipCode.hashCode() : 0);
        result = 31 * result + (rgEbrKallikratis != null ? rgEbrKallikratis.hashCode() : 0);
        result = 31 * result + (rgEbrPhoneNumber != null ? rgEbrPhoneNumber.hashCode() : 0);
        result = 31 * result + (rgEbrSecStakod1 != null ? rgEbrSecStakod1.hashCode() : 0);
        result = 31 * result + (rgEbrSecStakod2 != null ? rgEbrSecStakod2.hashCode() : 0);
        result = 31 * result + (rgEbrSecStakod3 != null ? rgEbrSecStakod3.hashCode() : 0);
        result = 31 * result + (rgEbrSecStakod4 != null ? rgEbrSecStakod4.hashCode() : 0);
        result = 31 * result + (rgEbrSecStakod5 != null ? rgEbrSecStakod5.hashCode() : 0);
        result = 31 * result + (rgEmpName != null ? rgEmpName.hashCode() : 0);
        result = 31 * result + (rgEmpFullname != null ? rgEmpFullname.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (rgEbrTaxationOfficeCode != null ? rgEbrTaxationOfficeCode.hashCode() : 0);
        result = 31 * result + (rgEbrTaxationNumber != null ? rgEbrTaxationNumber.hashCode() : 0);
        result = 31 * result + (rgEbrLegalCategoryCode != null ? rgEbrLegalCategoryCode.hashCode() : 0);
        result = 31 * result + (rgEmpPhysicalFlag != null ? rgEmpPhysicalFlag.hashCode() : 0);
        result = 31 * result + (rgEmpDiscreetTitle != null ? rgEmpDiscreetTitle.hashCode() : 0);

        result = 31 * result + (rgEmpEmployerId != null ? rgEmpEmployerId.hashCode() : 0);
        result = 31 * result + (rgEbrMainBranchFlag != null ? rgEbrMainBranchFlag.hashCode() : 0);
        return result;
    }
}