package sepe.domain.general;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Marios on 1/25/2016.
 */
@Entity
@Table(name = "SN_SP_RG_GGDE_MHTRWO", schema = "", catalog = "")
public class SpRgGgdeMhtrwoUsers {
    private String afm;
    private String doy;
    private String doyDescr;
    private String iInFlag;
    private String deactivationFlag;
    private String addTxpActual;
    private String firmFlag;
    private String onomasia;
    private String mothersFirstName;
    private Integer nilLegalPurpose;
    private String nilLegalPurposeDescr;
    private Integer nilLegalStatus;
    private String nilLegalStatusDescr;
    private String cardNo;
    private String cardKind;
    private String cntCitizenshipDescr;
    private Timestamp birthdate;
    private String birthplace;
    private Timestamp deathDate;
    private String indPhone;
    private String postalAddress;
    private String postalAddressNo;
    private String postalZipCode;
    private String postalParDescr;
    private String cntResidenceDescr;
    private String recidenceAddress;
    private String recidenceAddressNo;
    private String recidenceZipCode;
    private String recidenceParDescr;
    private String firmCommerTitle;
    private String firmDoy;
    private String firmAddress;
    private String firmAddressNo;
    private String firmZipCode;
    private String firmParDescr;
    private Timestamp registDate;
    private Timestamp stopDate;
    private String firmPhone;
    private String firmFax;
    private Integer facMainActivity;
    private String actMainDescr;
    private Integer countOfBranches;
    private Integer seqId;
    private Timestamp InsDate;

    @Basic
    @Column(name = "AFM", nullable = true, insertable = true, updatable = true, length = 100)
    public String getAfm() {
        return afm;
    }

    public void setAfm(String afm) {
        this.afm = afm;
    }

    @Basic
    @Column(name = "DOY", nullable = true, insertable = true, updatable = true, length = 100)
    public String getDoy() {
        return doy;
    }

    public void setDoy(String doy) {
        this.doy = doy;
    }

    @Basic
    @Column(name = "DOY_DESCR", nullable = true, insertable = true, updatable = true, length = 100)
    public String getDoyDescr() {
        return doyDescr;
    }

    public void setDoyDescr(String doyDescr) {
        this.doyDescr = doyDescr;
    }

    @Basic
    @Column(name = "I_NI_FLAG", nullable = true, insertable = true, updatable = true, length = 100)
    public String getiInFlag() {
        return iInFlag;
    }

    public void setiInFlag(String iInFlag) {
        this.iInFlag = iInFlag;
    }

    @Basic
    @Column(name = "DEACTIVATION_FLAG", nullable = true, insertable = true, updatable = true, length = 100)
    public String getDeactivationFlag() {
        return deactivationFlag;
    }

    public void setDeactivationFlag(String deactivationFlag) {
        this.deactivationFlag = deactivationFlag;
    }

    @Basic
    @Column(name = "ASS_TXP_ACTUAL", nullable = true, insertable = true, updatable = true, length = 100)
    public String getAddTxpActual() {
        return addTxpActual;
    }

    public void setAddTxpActual(String addTxpActual) {
        this.addTxpActual = addTxpActual;
    }

    @Basic
    @Column(name = "FIRM_FLAG", nullable = true, insertable = true, updatable = true, length = 100)
    public String getFirmFlag() {
        return firmFlag;
    }

    public void setFirmFlag(String firmFlag) {
        this.firmFlag = firmFlag;
    }

    @Basic
    @Column(name = "ONOMASIA", nullable = true, insertable = true, updatable = true, length = 100)
    public String getOnomasia() {
        return onomasia;
    }

    public void setOnomasia(String onomasia) {
        this.onomasia = onomasia;
    }

    @Basic
    @Column(name = "MOTHERS_FIRST_NAME", nullable = true, insertable = true, updatable = true, length = 100)
    public String getMothersFirstName() {
        return mothersFirstName;
    }

    public void setMothersFirstName(String mothersFirstName) {
        this.mothersFirstName = mothersFirstName;
    }

    @Basic
    @Column(name = "NIN_LEGAL_PURPOSE", nullable = true, insertable = true, updatable = true, length = 100)
    public Integer getNilLegalPurpose() {
        return nilLegalPurpose;
    }

    public void setNilLegalPurpose(Integer nilLegalPurpose) {
        this.nilLegalPurpose = nilLegalPurpose;
    }

    @Basic
    @Column(name = "NIN_LEGAL_PURPOSE_DESCR", nullable = true, insertable = true, updatable = true, length = 100)
    public String getNilLegalPurposeDescr() {
        return nilLegalPurposeDescr;
    }

    public void setNilLegalPurposeDescr(String nilLegalPurposeDescr) {
        this.nilLegalPurposeDescr = nilLegalPurposeDescr;
    }

    @Basic
    @Column(name = "NIN_LEGAL_STATUS", nullable = true, insertable = true, updatable = true, length = 100)
    public Integer getNilLegalStatus() {
        return nilLegalStatus;
    }

    public void setNilLegalStatus(Integer nilLegalStatus) {
        this.nilLegalStatus = nilLegalStatus;
    }

    @Basic
    @Column(name = "NIN_LEGAL_STATUS_DESCR", nullable = true, insertable = true, updatable = true, length = 100)
    public String getNilLegalStatusDescr() {
        return nilLegalStatusDescr;
    }

    public void setNilLegalStatusDescr(String nilLegalStatusDescr) {
        this.nilLegalStatusDescr = nilLegalStatusDescr;
    }

    @Basic
    @Column(name = "CARD_NO", nullable = true, insertable = true, updatable = true, length = 100)
    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    @Basic
    @Column(name = "CARD_KIND", nullable = true, insertable = true, updatable = true, length = 100)
    public String getCardKind() {
        return cardKind;
    }

    public void setCardKind(String cardKind) {
        this.cardKind = cardKind;
    }

    @Basic
    @Column(name = "CNT_CITIZENSHIP_DESCR", nullable = true, insertable = true, updatable = true, length = 100)
    public String getCntCitizenshipDescr() {
        return cntCitizenshipDescr;
    }

    public void setCntCitizenshipDescr(String cntCitizenshipDescr) {
        this.cntCitizenshipDescr = cntCitizenshipDescr;
    }

    @Basic
    @Column(name = "BIRTH_DATE", nullable = true, insertable = true, updatable = true, length = 100)
    public Timestamp getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Timestamp birthdate) {
        this.birthdate = birthdate;
    }

    @Basic
    @Column(name = "BIRTH_PLACE", nullable = true, insertable = true, updatable = true, length = 100)
    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    @Basic
    @Column(name = "DEATH_DATE", nullable = true, insertable = true, updatable = true, length = 100)
    public Timestamp getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(Timestamp deathDate) {
        this.deathDate = deathDate;
    }

    @Basic
    @Column(name = "IND_PHONE", nullable = true, insertable = true, updatable = true, length = 100)
    public String getIndPhone() {
        return indPhone;
    }

    public void setIndPhone(String indPhone) {
        this.indPhone = indPhone;
    }

    @Basic
    @Column(name = "POSTAL_ADDRESS", nullable = true, insertable = true, updatable = true, length = 100)
    public String getPostalAddress() {
        return postalAddress;
    }

    public void setPostalAddress(String postalAddress) {
        this.postalAddress = postalAddress;
    }

    @Basic
    @Column(name = "POSTAL_ADDRESS_NO", nullable = true, insertable = true, updatable = true, length = 100)
    public String getPostalAddressNo() {
        return postalAddressNo;
    }

    public void setPostalAddressNo(String postalAddressNo) {
        this.postalAddressNo = postalAddressNo;
    }

    @Basic
    @Column(name = "POSTAL_ZIP_CODE", nullable = true, insertable = true, updatable = true, length = 100)
    public String getPostalZipCode() {
        return postalZipCode;
    }

    public void setPostalZipCode(String postalZipCode) {
        this.postalZipCode = postalZipCode;
    }

    @Basic
    @Column(name = "POSTAL_PAR_DESCR", nullable = true, insertable = true, updatable = true, length = 100)
    public String getPostalParDescr() {
        return postalParDescr;
    }

    public void setPostalParDescr(String postalParDescr) {
        this.postalParDescr = postalParDescr;
    }

    @Basic
    @Column(name = "CNT_RESIDENCE_DESCR", nullable = true, insertable = true, updatable = true, length = 100)
    public String getCntResidenceDescr() {
        return cntResidenceDescr;
    }

    public void setCntResidenceDescr(String cntResidenceDescr) {
        this.cntResidenceDescr = cntResidenceDescr;
    }

    @Basic
    @Column(name = "RESIDENCE_ADDRESS", nullable = true, insertable = true, updatable = true, length = 100)
    public String getRecidenceAddress() {
        return recidenceAddress;
    }

    public void setRecidenceAddress(String recidenceAddress) {
        this.recidenceAddress = recidenceAddress;
    }

    @Basic
    @Column(name = "RESIDENCE_ADDRESS_NO", nullable = true, insertable = true, updatable = true, length = 100)
    public String getRecidenceAddressNo() {
        return recidenceAddressNo;
    }

    public void setRecidenceAddressNo(String recidenceAddressNo) {
        this.recidenceAddressNo = recidenceAddressNo;
    }

    @Basic
    @Column(name = "RESIDENCE_ZIP_CODE", nullable = true, insertable = true, updatable = true, length = 100)
    public String getRecidenceZipCode() {
        return recidenceZipCode;
    }

    public void setRecidenceZipCode(String recidenceZipCode) {
        this.recidenceZipCode = recidenceZipCode;
    }

    @Basic
    @Column(name = "RESIDENCE_PAR_DESCR", nullable = true, insertable = true, updatable = true, length = 100)
    public String getRecidenceParDescr() {
        return recidenceParDescr;
    }

    public void setRecidenceParDescr(String recidenceParDescr) {
        this.recidenceParDescr = recidenceParDescr;
    }

    @Basic
    @Column(name = "FIRM_COMMER_TITLE", nullable = true, insertable = true, updatable = true, length = 100)
    public String getFirmCommerTitle() {
        return firmCommerTitle;
    }

    public void setFirmCommerTitle(String firmCommerTitle) {
        this.firmCommerTitle = firmCommerTitle;
    }

    @Basic
    @Column(name = "FIRM_DOY", nullable = true, insertable = true, updatable = true, length = 100)
    public String getFirmDoy() {
        return firmDoy;
    }

    public void setFirmDoy(String firmDoy) {
        this.firmDoy = firmDoy;
    }

    @Basic
    @Column(name = "FIRM_ADDRESS", nullable = true, insertable = true, updatable = true, length = 100)
    public String getFirmAddress() {
        return firmAddress;
    }

    public void setFirmAddress(String firmAddress) {
        this.firmAddress = firmAddress;
    }

    @Basic
    @Column(name = "FIRM_ADDRESS_NO", nullable = true, insertable = true, updatable = true, length = 100)
    public String getFirmAddressNo() {
        return firmAddressNo;
    }

    public void setFirmAddressNo(String firmAddressNo) {
        this.firmAddressNo = firmAddressNo;
    }

    @Basic
    @Column(name = "FIRM_ZIP_CODE", nullable = true, insertable = true, updatable = true, length = 100)
    public String getFirmZipCode() {
        return firmZipCode;
    }

    public void setFirmZipCode(String firmZipCode) {
        this.firmZipCode = firmZipCode;
    }

    @Basic
    @Column(name = "FIRM_PAR_DESCR", nullable = true, insertable = true, updatable = true, length = 100)
    public String getFirmParDescr() {
        return firmParDescr;
    }

    public void setFirmParDescr(String firmParDescr) {
        this.firmParDescr = firmParDescr;
    }

    @Basic
    @Column(name = "REGIST_DATE", nullable = true, insertable = true, updatable = true, length = 100)
    public Timestamp getRegistDate() {
        return registDate;
    }

    public void setRegistDate(Timestamp registDate) {
        this.registDate = registDate;
    }

    @Basic
    @Column(name = "STOP_DATE", nullable = true, insertable = true, updatable = true, length = 100)
    public Timestamp getStopDate() {
        return stopDate;
    }

    public void setStopDate(Timestamp stopDate) {
        this.stopDate = stopDate;
    }

    @Basic
    @Column(name = "FIRM_PHONE", nullable = true, insertable = true, updatable = true, length = 100)
    public String getFirmPhone() {
        return firmPhone;
    }

    public void setFirmPhone(String firmPhone) {
        this.firmPhone = firmPhone;
    }

    @Basic
    @Column(name = "FIRM_FAX", nullable = true, insertable = true, updatable = true, length = 100)
    public String getFirmFax() {
        return firmFax;
    }

    public void setFirmFax(String firmFax) {
        this.firmFax = firmFax;
    }

    @Basic
    @Column(name = "FAC_MAIN_ACTIVITY", nullable = true, insertable = true, updatable = true, length = 100)
    public Integer getFacMainActivity() {
        return facMainActivity;
    }

    public void setFacMainActivity(Integer facMainActivity) {
        this.facMainActivity = facMainActivity;
    }

    @Basic
    @Column(name = "ACT_MAIN_DESCR", nullable = true, insertable = true, updatable = true, length = 100)
    public String getActMainDescr() {
        return actMainDescr;
    }

    public void setActMainDescr(String actMainDescr) {
        this.actMainDescr = actMainDescr;
    }

    @Basic
    @Column(name = "COUNT_OF_BRANCHES", nullable = true, insertable = true, updatable = true, length = 100)
    public Integer getCountOfBranches() {
        return countOfBranches;
    }

    public void setCountOfBranches(Integer countOfBranches) {
        this.countOfBranches = countOfBranches;
    }

    @Id
    @Column(name = "SEQ_ID", nullable = true, insertable = true, updatable = true, length = 100)
    public Integer getSeqId() {
        return seqId;
    }

    public void setSeqId(Integer seqId) {
        this.seqId = seqId;
    }

    @Basic
    @Column(name = "INS_DATE", nullable = true, insertable = true, updatable = true, length = 100)
    public Timestamp getInsDate() {
        return InsDate;
    }

    public void setInsDate(Timestamp insDate) {
        InsDate = insDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpRgGgdeMhtrwoUsers that = (SpRgGgdeMhtrwoUsers) o;

        if (afm != null ? !afm.equals(that.afm) : that.afm != null) return false;
        if (doy != null ? !doy.equals(that.doy) : that.doy != null) return false;
        if (doyDescr != null ? !doyDescr.equals(that.doyDescr) : that.doyDescr != null) return false;
        if (iInFlag != null ? !iInFlag.equals(that.iInFlag) : that.iInFlag != null) return false;
        if (deactivationFlag != null ? !deactivationFlag.equals(that.deactivationFlag) : that.deactivationFlag != null)
            return false;
        if (addTxpActual != null ? !addTxpActual.equals(that.addTxpActual) : that.addTxpActual != null) return false;
        if (firmFlag != null ? !firmFlag.equals(that.firmFlag) : that.firmFlag != null) return false;
        if (onomasia != null ? !onomasia.equals(that.onomasia) : that.onomasia != null) return false;
        if (mothersFirstName != null ? !mothersFirstName.equals(that.mothersFirstName) : that.mothersFirstName != null)
            return false;
        if (nilLegalPurpose != null ? !nilLegalPurpose.equals(that.nilLegalPurpose) : that.nilLegalPurpose != null)
            return false;
        if (nilLegalPurposeDescr != null ? !nilLegalPurposeDescr.equals(that.nilLegalPurposeDescr) : that.nilLegalPurposeDescr != null)
            return false;
        if (nilLegalStatus != null ? !nilLegalStatus.equals(that.nilLegalStatus) : that.nilLegalStatus != null)
            return false;
        if (nilLegalStatusDescr != null ? !nilLegalStatusDescr.equals(that.nilLegalStatusDescr) : that.nilLegalStatusDescr != null)
            return false;
        if (cardNo != null ? !cardNo.equals(that.cardNo) : that.cardNo != null) return false;
        if (cardKind != null ? !cardKind.equals(that.cardKind) : that.cardKind != null) return false;
        if (cntCitizenshipDescr != null ? !cntCitizenshipDescr.equals(that.cntCitizenshipDescr) : that.cntCitizenshipDescr != null)
            return false;
        if (birthdate != null ? !birthdate.equals(that.birthdate) : that.birthdate != null) return false;
        if (birthplace != null ? !birthplace.equals(that.birthplace) : that.birthplace != null) return false;
        if (deathDate != null ? !deathDate.equals(that.deathDate) : that.deathDate != null) return false;
        if (indPhone != null ? !indPhone.equals(that.indPhone) : that.indPhone != null) return false;
        if (postalAddress != null ? !postalAddress.equals(that.postalAddress) : that.postalAddress != null)
            return false;
        if (postalAddressNo != null ? !postalAddressNo.equals(that.postalAddressNo) : that.postalAddressNo != null)
            return false;
        if (postalZipCode != null ? !postalZipCode.equals(that.postalZipCode) : that.postalZipCode != null)
            return false;
        if (postalParDescr != null ? !postalParDescr.equals(that.postalParDescr) : that.postalParDescr != null)
            return false;
        if (cntResidenceDescr != null ? !cntResidenceDescr.equals(that.cntResidenceDescr) : that.cntResidenceDescr != null)
            return false;
        if (recidenceAddress != null ? !recidenceAddress.equals(that.recidenceAddress) : that.recidenceAddress != null)
            return false;
        if (recidenceAddressNo != null ? !recidenceAddressNo.equals(that.recidenceAddressNo) : that.recidenceAddressNo != null)
            return false;
        if (recidenceZipCode != null ? !recidenceZipCode.equals(that.recidenceZipCode) : that.recidenceZipCode != null)
            return false;
        if (recidenceParDescr != null ? !recidenceParDescr.equals(that.recidenceParDescr) : that.recidenceParDescr != null)
            return false;
        if (firmCommerTitle != null ? !firmCommerTitle.equals(that.firmCommerTitle) : that.firmCommerTitle != null)
            return false;
        if (firmDoy != null ? !firmDoy.equals(that.firmDoy) : that.firmDoy != null) return false;
        if (firmAddress != null ? !firmAddress.equals(that.firmAddress) : that.firmAddress != null) return false;
        if (firmAddressNo != null ? !firmAddressNo.equals(that.firmAddressNo) : that.firmAddressNo != null)
            return false;
        if (firmZipCode != null ? !firmZipCode.equals(that.firmZipCode) : that.firmZipCode != null) return false;
        if (firmParDescr != null ? !firmParDescr.equals(that.firmParDescr) : that.firmParDescr != null) return false;
        if (registDate != null ? !registDate.equals(that.registDate) : that.registDate != null) return false;
        if (stopDate != null ? !stopDate.equals(that.stopDate) : that.stopDate != null) return false;
        if (firmPhone != null ? !firmPhone.equals(that.firmPhone) : that.firmPhone != null) return false;
        if (firmFax != null ? !firmFax.equals(that.firmFax) : that.firmFax != null) return false;
        if (facMainActivity != null ? !facMainActivity.equals(that.facMainActivity) : that.facMainActivity != null)
            return false;
        if (actMainDescr != null ? !actMainDescr.equals(that.actMainDescr) : that.actMainDescr != null) return false;
        if (countOfBranches != null ? !countOfBranches.equals(that.countOfBranches) : that.countOfBranches != null)
            return false;
        if (seqId != null ? !seqId.equals(that.seqId) : that.seqId != null) return false;
        return !(InsDate != null ? !InsDate.equals(that.InsDate) : that.InsDate != null);

    }

    @Override
    public int hashCode() {
        int result = afm != null ? afm.hashCode() : 0;
        result = 31 * result + (doy != null ? doy.hashCode() : 0);
        result = 31 * result + (doyDescr != null ? doyDescr.hashCode() : 0);
        result = 31 * result + (iInFlag != null ? iInFlag.hashCode() : 0);
        result = 31 * result + (deactivationFlag != null ? deactivationFlag.hashCode() : 0);
        result = 31 * result + (addTxpActual != null ? addTxpActual.hashCode() : 0);
        result = 31 * result + (firmFlag != null ? firmFlag.hashCode() : 0);
        result = 31 * result + (onomasia != null ? onomasia.hashCode() : 0);
        result = 31 * result + (mothersFirstName != null ? mothersFirstName.hashCode() : 0);
        result = 31 * result + (nilLegalPurpose != null ? nilLegalPurpose.hashCode() : 0);
        result = 31 * result + (nilLegalPurposeDescr != null ? nilLegalPurposeDescr.hashCode() : 0);
        result = 31 * result + (nilLegalStatus != null ? nilLegalStatus.hashCode() : 0);
        result = 31 * result + (nilLegalStatusDescr != null ? nilLegalStatusDescr.hashCode() : 0);
        result = 31 * result + (cardNo != null ? cardNo.hashCode() : 0);
        result = 31 * result + (cardKind != null ? cardKind.hashCode() : 0);
        result = 31 * result + (cntCitizenshipDescr != null ? cntCitizenshipDescr.hashCode() : 0);
        result = 31 * result + (birthdate != null ? birthdate.hashCode() : 0);
        result = 31 * result + (birthplace != null ? birthplace.hashCode() : 0);
        result = 31 * result + (deathDate != null ? deathDate.hashCode() : 0);
        result = 31 * result + (indPhone != null ? indPhone.hashCode() : 0);
        result = 31 * result + (postalAddress != null ? postalAddress.hashCode() : 0);
        result = 31 * result + (postalAddressNo != null ? postalAddressNo.hashCode() : 0);
        result = 31 * result + (postalZipCode != null ? postalZipCode.hashCode() : 0);
        result = 31 * result + (postalParDescr != null ? postalParDescr.hashCode() : 0);
        result = 31 * result + (cntResidenceDescr != null ? cntResidenceDescr.hashCode() : 0);
        result = 31 * result + (recidenceAddress != null ? recidenceAddress.hashCode() : 0);
        result = 31 * result + (recidenceAddressNo != null ? recidenceAddressNo.hashCode() : 0);
        result = 31 * result + (recidenceZipCode != null ? recidenceZipCode.hashCode() : 0);
        result = 31 * result + (recidenceParDescr != null ? recidenceParDescr.hashCode() : 0);
        result = 31 * result + (firmCommerTitle != null ? firmCommerTitle.hashCode() : 0);
        result = 31 * result + (firmDoy != null ? firmDoy.hashCode() : 0);
        result = 31 * result + (firmAddress != null ? firmAddress.hashCode() : 0);
        result = 31 * result + (firmAddressNo != null ? firmAddressNo.hashCode() : 0);
        result = 31 * result + (firmZipCode != null ? firmZipCode.hashCode() : 0);
        result = 31 * result + (firmParDescr != null ? firmParDescr.hashCode() : 0);
        result = 31 * result + (registDate != null ? registDate.hashCode() : 0);
        result = 31 * result + (stopDate != null ? stopDate.hashCode() : 0);
        result = 31 * result + (firmPhone != null ? firmPhone.hashCode() : 0);
        result = 31 * result + (firmFax != null ? firmFax.hashCode() : 0);
        result = 31 * result + (facMainActivity != null ? facMainActivity.hashCode() : 0);
        result = 31 * result + (actMainDescr != null ? actMainDescr.hashCode() : 0);
        result = 31 * result + (countOfBranches != null ? countOfBranches.hashCode() : 0);
        result = 31 * result + (seqId != null ? seqId.hashCode() : 0);
        result = 31 * result + (InsDate != null ? InsDate.hashCode() : 0);
        return result;
    }
}