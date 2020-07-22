package sepe.domain.company;

import sepe.domain.technician.SpPtlTechnicianDiploma;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Nick on 4/2/2015.
 */
@Entity
@javax.persistence.Table(name = "SN_RG_VW_TA_REG_NEZ", schema = "SP_PTL", catalog = "")
public class SnRgVwTaRegNez {
    private Long rgTaDocId;

    @Id
    @javax.persistence.Column(name = "RG_TA_DOC_ID")
    public Long getRgTaDocId() {
        return rgTaDocId;
    }

    public void setRgTaDocId(Long rgTaDocId) {
        this.rgTaDocId = rgTaDocId;
    }

    private String rgTaSepeDepartmentDesc;

    @Basic
    @GeneratedValue
    @javax.persistence.Column(name = "RG_TA_SEPE_DEPARTMENT_DESC")
    public String getRgTaSepeDepartmentDesc() {
        return rgTaSepeDepartmentDesc;
    }

    public void setRgTaSepeDepartmentDesc(String rgTaSepeDepartmentDesc) {
        this.rgTaSepeDepartmentDesc = rgTaSepeDepartmentDesc;
    }

    private Long rgTaSepeDepartmentCode;

    @Basic
    @javax.persistence.Column(name = "RG_TA_SEPE_DEPARTMENT_CODE")
    public Long getRgTaSepeDepartmentCode() {
        return rgTaSepeDepartmentCode;
    }

    public void setRgTaSepeDepartmentCode(Long rgTaSepeDepartmentCode) {
        this.rgTaSepeDepartmentCode = rgTaSepeDepartmentCode;
    }

    private String rgTaStatusDesc;

    @Basic
    @javax.persistence.Column(name = "RG_TA_STATUS_DESC")
    public String getRgTaStatusDesc() {
        return rgTaStatusDesc;
    }

    public void setRgTaStatusDesc(String rgTaStatusDesc) {
        this.rgTaStatusDesc = rgTaStatusDesc;
    }

    private Integer rgTaStatusCode;

    @Basic
    @javax.persistence.Column(name = "RG_TA_STATUS_CODE")
    public Integer getRgTaStatusCode() {
        return rgTaStatusCode;
    }

    public void setRgTaStatusCode(Integer rgTaStatusCode) {
        this.rgTaStatusCode = rgTaStatusCode;
    }

    private Timestamp rgTaRegistrationDate;

    @Basic
    @javax.persistence.Column(name = "RG_TA_REGISTRATION_DATE")
    public Timestamp getRgTaRegistrationDate() {
        return rgTaRegistrationDate;
    }

    public void setRgTaRegistrationDate(Timestamp rgTaRegistrationDate) {
        this.rgTaRegistrationDate = rgTaRegistrationDate;
    }

    private Long rgTaProfileCheckFlag;

    @Basic
    @javax.persistence.Column(name = "RG_TA_PROFILE_CHECK_FLAG")
    public Long getRgTaProfileCheckFlag() {
        return rgTaProfileCheckFlag;
    }

    public void setRgTaProfileCheckFlag(Long rgTaProfileCheckFlag) {
        this.rgTaProfileCheckFlag = rgTaProfileCheckFlag;
    }

    private Long rgTaTrainingMeFlag;

    @Basic
    @javax.persistence.Column(name = "RG_TA_TRAINING_ME_FLAG")
    public Long getRgTaTrainingMeFlag() {
        return rgTaTrainingMeFlag;
    }

    public void setRgTaTrainingMeFlag(Long rgTaTrainingMeFlag) {
        this.rgTaTrainingMeFlag = rgTaTrainingMeFlag;
    }

    private Long rgTaTraining10Flag;

    @Basic
    @javax.persistence.Column(name = "RG_TA_TRAINING_10_FLAG")
    public Long getRgTaTraining10Flag() {
        return rgTaTraining10Flag;
    }

    public void setRgTaTraining10Flag(Long rgTaTraining10Flag) {
        this.rgTaTraining10Flag = rgTaTraining10Flag;
    }

    private Long rgTaTraining35Flag;

    @Basic
    @javax.persistence.Column(name = "RG_TA_TRAINING_35_FLAG")
    public Long getRgTaTraining35Flag() {
        return rgTaTraining35Flag;
    }

    public void setRgTaTraining35Flag(Long rgTaTraining35Flag) {
        this.rgTaTraining35Flag = rgTaTraining35Flag;
    }

    private Long rgTaTraining100Flag;

    @Basic
    @javax.persistence.Column(name = "RG_TA_TRAINING_100_FLAG")
    public Long getRgTaTraining100Flag() {
        return rgTaTraining100Flag;
    }

    public void setRgTaTraining100Flag(Long rgTaTraining100Flag) {
        this.rgTaTraining100Flag = rgTaTraining100Flag;
    }

    private Long rgTaNezDutiesFlag;

    @Basic
    @javax.persistence.Column(name = "RG_TA_NEZ_DUTIES_FLAG")
    public Long getRgTaNezDutiesFlag() {
        return rgTaNezDutiesFlag;
    }

    public void setRgTaNezDutiesFlag(Long rgTaNezDutiesFlag) {
        this.rgTaNezDutiesFlag = rgTaNezDutiesFlag;
    }

    private String rgTaSpecialityDesc;

    @Basic
    @javax.persistence.Column(name = "RG_TA_SPECIALTY_DESC")
    public String getRgTaSpecialityDesc() {
        return rgTaSpecialityDesc;
    }

    public void setRgTaSpecialityDesc(String rgTaSpecialityDesc) {
        this.rgTaSpecialityDesc = rgTaSpecialityDesc;
    }

    private Long rgTaSpecialityCode;

    @Basic
    @javax.persistence.Column(name = "RG_TA_SPECIALTY_CODE")
    public Long getRgTaSpecialityCode() {
        return rgTaSpecialityCode;
    }

    public void setRgTaSpecialityCode(Long rgTaSpecialityCode) {
        this.rgTaSpecialityCode = rgTaSpecialityCode;
    }

    private String rgTaEducationLevelDesc;

    @Basic
    @javax.persistence.Column(name = "RG_TA_EDUCATION_LEVEL_DESC")
    public String getRgTaEducationLevelDesc() {
        return rgTaEducationLevelDesc;
    }

    public void setRgTaEducationLevelDesc(String rgTaEducationLevelDesc) {
        this.rgTaEducationLevelDesc = rgTaEducationLevelDesc;
    }

    private Long rgTaEducationLevelCode;

    @Basic
    @javax.persistence.Column(name = "RG_TA_EDUCATION_LEVEL_CODE")
    public Long getrgTaEducationLevelCode() {
        return rgTaEducationLevelCode;
    }

    public void setrgTaEducationLevelCode(Long rgTaEducationLevelCode) {
        this.rgTaEducationLevelCode = rgTaEducationLevelCode;
    }

    private String rgTaUnionNumber;

    @Basic
    @javax.persistence.Column(name = "RG_TA_UNION_NUMBER")
    public String getRgTaUnionNumber() {
        return rgTaUnionNumber;
    }

    public void setRgTaUnionNumber(String rgTaUnionNumber) {
        this.rgTaUnionNumber = rgTaUnionNumber;
    }

    private String rgTaRegionDesc;

    @Basic
    @javax.persistence.Column(name = "RG_TA_REGION_DESC")
    public String getRgTaRegionDesc() {
        return rgTaRegionDesc;
    }

    public void setRgTaRegionDesc(String rgTaRegionDesc) {
        this.rgTaRegionDesc = rgTaRegionDesc;
    }

    private String rgTaRegionCode;

    @Basic
    @javax.persistence.Column(name = "RG_TA_REGION_CODE")
    public String getRgTaRegionCode() {
        return rgTaRegionCode;
    }

    public void setRgTaRegionCode(String rgTaRegionCode) {
        this.rgTaRegionCode = rgTaRegionCode;
    }

    private String rgTaCountyDesc;

    @Basic
    @javax.persistence.Column(name = "RG_TA_COUNTY_DESC")
    public String getRgTaCountyDesc() {
        return rgTaCountyDesc;
    }

    public void setRgTaCountyDesc(String rgTaCountyDesc) {
        this.rgTaCountyDesc = rgTaCountyDesc;
    }

    private String rgTaCountyCode;

    @Basic
    @javax.persistence.Column(name = "RG_TA_COUNTY_CODE")
    public String getRgTaCountyCode() {
        return rgTaCountyCode;
    }

    public void setRgTaCountyCode(String rgTaCountyCode) {
        this.rgTaCountyCode = rgTaCountyCode;
    }

    private String rgTaCommunDesc;

    @Basic
    @javax.persistence.Column(name = "RG_TA_COMMUN_DESC")
    public String getRgTaCommunDesc() {
        return rgTaCommunDesc;
    }

    public void setRgTaCommunDesc(String rgTaCommunDesc) {
        this.rgTaCommunDesc = rgTaCommunDesc;
    }

    private String rgTaCommunCode;

    @Basic
    @javax.persistence.Column(name = "RG_TA_COMMUN_CODE")
    public String getRgTaCommunCode() {
        return rgTaCommunCode;
    }

    public void setRgTaCommunCode(String rgTaCommunCode) {
        this.rgTaCommunCode = rgTaCommunCode;
    }

    private String rgTaMunicipalDesc;

    @Basic
    @javax.persistence.Column(name = "RG_TA_MUNICIPAL_DESC")
    public String getRgTaMunicipalDesc() {
        return rgTaMunicipalDesc;
    }

    public void setRgTaMunicipalDesc(String rgTaMunicipalDesc) {
        this.rgTaMunicipalDesc = rgTaMunicipalDesc;
    }

    private String rgTaMunicipalCode;

    @Basic
    @javax.persistence.Column(name = "RG_TA_MUNICIPAL_CODE")
    public String getRgTaMunicipalCode() {
        return rgTaMunicipalCode;
    }

    public void setRgTaMunicipalCode(String rgTaMunicipalCode) {
        this.rgTaMunicipalCode = rgTaMunicipalCode;
    }

    private String rgTaAddressTk;

    @Basic
    @javax.persistence.Column(name = "RG_TA_ADDRESS_TK")
    public String getRgTaAddressTk() {
        return rgTaAddressTk;
    }

    public void setRgTaAddressTk(String rgTaAddressTk) {
        this.rgTaAddressTk = rgTaAddressTk;
    }

    private String rgTaAddressStreet;

    @Basic
    @javax.persistence.Column(name = "RG_TA_ADDRESS_STREET")
    public String getRgTaAddressStreet() {
        return rgTaAddressStreet;
    }

    public void setRgTaAddressStreet(String rgTaAddressStreet) {
        this.rgTaAddressStreet = rgTaAddressStreet;
    }

    private String rgTaFax;

    @Basic
    @javax.persistence.Column(name = "RG_TA_FAX")
    public String getRgTaFax() {
        return rgTaFax;
    }

    public void setRgTaFax(String rgTaFax) {
        this.rgTaFax = rgTaFax;
    }

    private String rgTaMobile;

    @Basic
    @javax.persistence.Column(name = "RG_TA_MOBILE")
    public String getRgTaMobile() {
        return rgTaMobile;
    }

    public void setRgTaMobile(String rgTaMobile) {
        this.rgTaMobile = rgTaMobile;
    }

    private String rgTaPhoneNumber;

    @Basic
    @javax.persistence.Column(name = "RG_TA_PHONE_NUMBER")
    public String getRgTaPhoneNumber() {
        return rgTaPhoneNumber;
    }

    public void setRgTaPhoneNumber(String rgTaPhoneNumber) {
        this.rgTaPhoneNumber = rgTaPhoneNumber;
    }

    private String rgTaEmail;

    @Basic
    @javax.persistence.Column(name = "RG_TA_EMAIL")
    public String getRgTaEmail() {
        return rgTaEmail;
    }

    public void setRgTaEmail(String rgTaEmail) {
        this.rgTaEmail = rgTaEmail;
    }

    private String rgTaIdTypeDesc;

    @Basic
    @javax.persistence.Column(name = "RG_TA_ID_TYPE_DESC")
    public String getRgTaIdTypeDesc() {
        return rgTaIdTypeDesc;
    }

    public void setRgTaIdTypeDesc(String rgTaIdTypeDesc) {
        this.rgTaIdTypeDesc = rgTaIdTypeDesc;
    }

    private String rgTaIdTypeCode;

    @Basic
    @javax.persistence.Column(name = "RG_TA_ID_TYPE_CODE")
    public String getRgTaIdTypeCode() {
        return rgTaIdTypeCode;
    }

    public void setRgTaIdTypeCode(String rgTaIdTypeCode) {
        this.rgTaIdTypeCode = rgTaIdTypeCode;
    }

    private String rgTaAmka;

    @Basic
    @javax.persistence.Column(name = "RG_TA_AMKA")
    public String getRgTaAmka() {
        return rgTaAmka;
    }

    public void setRgTaAmka(String rgTaAmka) {
        this.rgTaAmka = rgTaAmka;
    }

    private String rgTaTaxationNumber;

    @Basic
    @javax.persistence.Column(name = "RG_TA_TAXATION_NUMBER")
    public String getRgTaTaxationNumber() {
        return rgTaTaxationNumber;
    }

    public void setRgTaTaxationNumber(String rgTaTaxationNumber) {
        this.rgTaTaxationNumber = rgTaTaxationNumber;
    }

    private String rgTaFatherName;

    @Basic
    @javax.persistence.Column(name = "RG_TA_FATHER_NAME")
    public String getRgTaFatherName() {
        return rgTaFatherName;
    }

    public void setRgTaFatherName(String rgTaFatherName) {
        this.rgTaFatherName = rgTaFatherName;
    }

    private String rgTaName;

    @Basic
    @javax.persistence.Column(name = "RG_TA_NAME")
    public String getRgTaName() {
        return rgTaName;
    }

    public void setRgTaName(String rgTaName) {
        this.rgTaName = rgTaName;
    }

    private String rgTaSurname;

    @Basic
    @javax.persistence.Column(name = "RG_TA_SURNAME")
    public String getRgTaSurname() {
        return rgTaSurname;
    }

    public void setRgTaSurname(String rgTaSurname) {
        this.rgTaSurname = rgTaSurname;
    }

    private String rgTaTypeDesc;

    @Basic
    @javax.persistence.Column(name = "RG_TA_TYPE_DESC")
    public String getRgTaTypeDesc() {
        return rgTaTypeDesc;
    }

    public void setRgTaTypeDesc(String rgTaTypeDesc) {
        this.rgTaTypeDesc = rgTaTypeDesc;
    }

    private Long rgTaTypeCode;

    @Basic
    @javax.persistence.Column(name = "RG_TA_TYPE_CODE")
    public Long getRgTaTypeCode() {
        return rgTaTypeCode;
    }

    public void setRgTaTypeCode(Long rgTaTypeCode) {
        this.rgTaTypeCode = rgTaTypeCode;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SnRgVwTaRegNez that = (SnRgVwTaRegNez) o;

        if (rgTaDocId != that.rgTaDocId) return false;
        if (rgTaSepeDepartmentDesc != null ? !rgTaSepeDepartmentDesc.equals(that.rgTaSepeDepartmentDesc) : that.rgTaSepeDepartmentDesc != null) return false;
        if (rgTaSepeDepartmentCode != null ? !rgTaSepeDepartmentCode.equals(that.rgTaSepeDepartmentCode) : that.rgTaSepeDepartmentCode != null) return false;
        if (rgTaStatusDesc != null ? !rgTaStatusDesc.equals(that.rgTaStatusDesc) : that.rgTaStatusDesc != null) return false;
        if (rgTaStatusCode != null ? !rgTaStatusCode.equals(that.rgTaStatusCode) : that.rgTaStatusCode != null) return false;
        if (rgTaRegistrationDate != null ? !rgTaRegistrationDate.equals(that.rgTaRegistrationDate) : that.rgTaRegistrationDate != null) return false;
        if (rgTaProfileCheckFlag != null ? !rgTaProfileCheckFlag.equals(that.rgTaProfileCheckFlag) : that.rgTaProfileCheckFlag != null) return false;
        if (rgTaTrainingMeFlag != null ? !rgTaTrainingMeFlag.equals(that.rgTaTrainingMeFlag) : that.rgTaTrainingMeFlag != null)
            return false;
        if (rgTaTraining10Flag != null ? !rgTaTraining10Flag.equals(that.rgTaTraining10Flag) : that.rgTaTraining10Flag != null) return false;
        if (rgTaTraining35Flag != null ? !rgTaTraining35Flag.equals(that.rgTaTraining35Flag) : that.rgTaTraining35Flag != null) return false;
        if (rgTaTraining100Flag != null ? !rgTaTraining100Flag.equals(that.rgTaTraining100Flag) : that.rgTaTraining100Flag != null) return false;
        if (rgTaNezDutiesFlag != null ? !rgTaNezDutiesFlag.equals(that.rgTaNezDutiesFlag) : that.rgTaNezDutiesFlag != null) return false;
        if (rgTaSpecialityDesc != null ? !rgTaSpecialityDesc.equals(that.rgTaSpecialityDesc) : that.rgTaSpecialityDesc != null) return false;
        if (rgTaSpecialityCode != null ? !rgTaSpecialityCode.equals(that.rgTaSpecialityCode) : that.rgTaSpecialityCode != null) return false;
        if (rgTaEducationLevelDesc != null ? !rgTaEducationLevelDesc.equals(that.rgTaEducationLevelDesc) : that.rgTaEducationLevelDesc != null) return false;
        if (rgTaEducationLevelCode != null ? !rgTaEducationLevelCode.equals(that.rgTaEducationLevelCode) : that.rgTaEducationLevelCode != null) return false;
        if (rgTaUnionNumber != null ? !rgTaUnionNumber.equals(that.rgTaUnionNumber) : that.rgTaUnionNumber != null) return false;
        if (rgTaRegionDesc != null ? !rgTaRegionDesc.equals(that.rgTaRegionDesc) : that.rgTaRegionDesc != null) return false;
        if (rgTaRegionCode != null ? !rgTaRegionCode.equals(that.rgTaRegionCode) : that.rgTaRegionCode != null) return false;
        if (rgTaCountyDesc != null ? !rgTaCountyDesc.equals(that.rgTaCountyDesc) : that.rgTaCountyDesc != null)
            return false;
        if (rgTaCountyCode != null ? !rgTaCountyCode.equals(that.rgTaCountyCode) : that.rgTaCountyCode != null) return false;
        if (rgTaCommunDesc != null ? !rgTaCommunDesc.equals(that.rgTaCommunDesc) : that.rgTaCommunDesc != null) return false;
        if (rgTaCommunCode != null ? !rgTaCommunCode.equals(that.rgTaCommunCode) : that.rgTaCommunCode != null) return false;
        if (rgTaMunicipalDesc != null ? !rgTaMunicipalDesc.equals(that.rgTaMunicipalDesc) : that.rgTaMunicipalDesc != null)
            return false;
        if (rgTaMunicipalCode != null ? !rgTaMunicipalCode.equals(that.rgTaMunicipalCode) : that.rgTaMunicipalCode != null) return false;
        if (rgTaAddressTk != null ? !rgTaAddressTk.equals(that.rgTaAddressTk) : that.rgTaAddressTk != null) return false;
        if (rgTaAddressStreet != null ? !rgTaAddressStreet.equals(that.rgTaAddressStreet) : that.rgTaAddressStreet != null)
            return false;
        if (rgTaFax != null ? !rgTaFax.equals(that.rgTaFax) : that.rgTaFax != null) return false;
        if (rgTaMobile != null ? !rgTaMobile.equals(that.rgTaMobile) : that.rgTaMobile != null)
            return false;
        if (rgTaPhoneNumber != null ? !rgTaPhoneNumber.equals(that.rgTaPhoneNumber) : that.rgTaPhoneNumber != null)
            return false;
        if (rgTaEmail != null ? !rgTaEmail.equals(that.rgTaEmail) : that.rgTaEmail != null) return false;
        if (rgTaIdTypeDesc != null ? !rgTaIdTypeDesc.equals(that.rgTaIdTypeDesc) : that.rgTaIdTypeDesc != null)
            return false;
        if (rgTaIdTypeCode != null ? !rgTaIdTypeCode.equals(that.rgTaIdTypeCode) : that.rgTaIdTypeCode != null) return false;
        if (rgTaAmka != null ? !rgTaAmka.equals(that.rgTaAmka) : that.rgTaAmka != null)
            return false;
        if (rgTaTaxationNumber != null ? !rgTaTaxationNumber.equals(that.rgTaTaxationNumber) : that.rgTaTaxationNumber != null) return false;
        if (rgTaFatherName != null ? !rgTaFatherName.equals(that.rgTaFatherName) : that.rgTaFatherName != null) return false;
        if (rgTaName != null ? !rgTaName.equals(that.rgTaName) : that.rgTaName != null) return false;
        if (rgTaSurname != null ? !rgTaSurname.equals(that.rgTaSurname) : that.rgTaSurname != null) return false;
        if (rgTaTypeDesc != null ? !rgTaTypeDesc.equals(that.rgTaTypeDesc) : that.rgTaTypeDesc != null) return false;
        if (rgTaTypeCode != null ? !rgTaTypeCode.equals(that.rgTaTypeCode) : that.rgTaTypeCode != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (rgTaDocId ^ (rgTaDocId >>> 32));
        result = 31 * result + (rgTaSepeDepartmentDesc != null ? rgTaSepeDepartmentDesc.hashCode() : 0);
        result = 31 * result + (rgTaSepeDepartmentCode != null ? rgTaSepeDepartmentCode.hashCode() : 0);
        result = 31 * result + (rgTaStatusDesc != null ? rgTaStatusDesc.hashCode() : 0);
        result = 31 * result + (rgTaStatusCode != null ? rgTaStatusCode.hashCode() : 0);
        result = 31 * result + (rgTaRegistrationDate != null ? rgTaRegistrationDate.hashCode() : 0);
        result = 31 * result + (rgTaProfileCheckFlag != null ? rgTaProfileCheckFlag.hashCode() : 0);
        result = 31 * result + (rgTaTrainingMeFlag != null ? rgTaTrainingMeFlag.hashCode() : 0);
        result = 31 * result + (rgTaTraining10Flag != null ? rgTaTraining10Flag.hashCode() : 0);
        result = 31 * result + (rgTaTraining35Flag != null ? rgTaTraining35Flag.hashCode() : 0);
        result = 31 * result + (rgTaTraining100Flag != null ? rgTaTraining100Flag.hashCode() : 0);
        result = 31 * result + (rgTaNezDutiesFlag != null ? rgTaNezDutiesFlag.hashCode() : 0);
        result = 31 * result + (rgTaSpecialityDesc != null ? rgTaSpecialityDesc.hashCode() : 0);
        result = 31 * result + (rgTaSpecialityCode != null ? rgTaSpecialityCode.hashCode() : 0);
        result = 31 * result + (rgTaEducationLevelDesc != null ? rgTaEducationLevelDesc.hashCode() : 0);
        result = 31 * result + (rgTaEducationLevelCode != null ? rgTaEducationLevelCode.hashCode() : 0);
        result = 31 * result + (rgTaUnionNumber != null ? rgTaUnionNumber.hashCode() : 0);
        result = 31 * result + (rgTaRegionDesc != null ? rgTaRegionDesc.hashCode() : 0);
        result = 31 * result + (rgTaRegionCode != null ? rgTaRegionCode.hashCode() : 0);
        result = 31 * result + (rgTaCountyDesc != null ? rgTaCountyDesc.hashCode() : 0);
        result = 31 * result + (rgTaCountyCode != null ? rgTaCountyCode.hashCode() : 0);
        result = 31 * result + (rgTaCommunDesc != null ? rgTaCommunDesc.hashCode() : 0);
        result = 31 * result + (rgTaCommunCode != null ? rgTaCommunCode.hashCode() : 0);
        result = 31 * result + (rgTaMunicipalDesc != null ? rgTaMunicipalDesc.hashCode() : 0);
        result = 31 * result + (rgTaMunicipalCode != null ? rgTaMunicipalCode.hashCode() : 0);
        result = 31 * result + (rgTaAddressTk != null ? rgTaAddressTk.hashCode() : 0);
        result = 31 * result + (rgTaAddressStreet != null ? rgTaAddressStreet.hashCode() : 0);
        result = 31 * result + (rgTaFax != null ? rgTaFax.hashCode() : 0);
        result = 31 * result + (rgTaMobile != null ? rgTaMobile.hashCode() : 0);
        result = 31 * result + (rgTaPhoneNumber != null ? rgTaPhoneNumber.hashCode() : 0);
        result = 31 * result + (rgTaEmail != null ? rgTaEmail.hashCode() : 0);
        result = 31 * result + (rgTaIdTypeDesc != null ? rgTaIdTypeDesc.hashCode() : 0);
        result = 31 * result + (rgTaIdTypeCode != null ? rgTaIdTypeCode.hashCode() : 0);
        result = 31 * result + (rgTaAmka != null ? rgTaAmka.hashCode() : 0);
        result = 31 * result + (rgTaTaxationNumber != null ? rgTaTaxationNumber.hashCode() : 0);
        result = 31 * result + (rgTaFatherName != null ? rgTaFatherName.hashCode() : 0);
        result = 31 * result + (rgTaName != null ? rgTaName.hashCode() : 0);
        result = 31 * result + (rgTaSurname != null ? rgTaSurname.hashCode() : 0);
        result = 31 * result + (rgTaTypeDesc != null ? rgTaTypeDesc.hashCode() : 0);
        result = 31 * result + (rgTaTypeCode != null ? rgTaTypeCode.hashCode() : 0);
        return result;
    }
}

