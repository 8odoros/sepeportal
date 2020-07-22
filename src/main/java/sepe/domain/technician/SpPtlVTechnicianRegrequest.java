package sepe.domain.technician;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by dimitrisf on 29/1/2019.
 */
@Entity
@javax.persistence.Table(name = "SP_PTL_V_TECHNICIAN_REGREQUEST", schema = "SP_PTL", catalog = "")
public class SpPtlVTechnicianRegrequest {
    private Long id;

    @Id
    @javax.persistence.Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private Long userId;

    @Basic
    @javax.persistence.Column(name = "USER_ID")
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    private Integer subStatus;

    @Basic
    @javax.persistence.Column(name = "SUB_STATUS")
    public Integer getSubStatus() {
        return subStatus;
    }

    public void setSubStatus(Integer subStatus) {
        this.subStatus = subStatus;
    }

    private Integer reqStatus;

    @Basic
    @javax.persistence.Column(name = "REQ_STATUS")
    public Integer getReqStatus() {
        return reqStatus;
    }

    public void setReqStatus(Integer reqStatus) {
        this.reqStatus = reqStatus;
    }

    private String protNo;

    @Basic
    @javax.persistence.Column(name = "PROT_NO")
    public String getProtNo() {
        return protNo;
    }

    public void setProtNo(String protNo) {
        this.protNo = protNo;
    }

    private String firstname;

    @Basic
    @javax.persistence.Column(name = "FIRSTNAME")
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    private String lastname;

    @Basic
    @javax.persistence.Column(name = "LASTNAME")
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    private Integer protYear;

    @Basic
    @javax.persistence.Column(name = "PROT_YEAR")
    public Integer getProtYear() {
        return protYear;
    }

    public void setProtYear(Integer protYear) {
        this.protYear = protYear;
    }

    private Long educationLevel;

    @Basic
    @javax.persistence.Column(name = "EDUCATION_LEVEL")
    public Long getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(Long educationLevel) {
        this.educationLevel = educationLevel;
    }

    private String afm;

    @Basic
    @javax.persistence.Column(name = "AFM")
    public String getAfm() {
        return afm;
    }

    public void setAfm(String afm) {
        this.afm = afm;
    }

    private String amka;

    @Basic
    @javax.persistence.Column(name = "AMKA")
    public String getAmka() {
        return amka;
    }

    public void setAmka(String amka) {
        this.amka = amka;
    }

    private String cardNumber;

    @Basic
    @javax.persistence.Column(name = "CARD_NUMBER")
    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    private String phone;

    @Basic
    @javax.persistence.Column(name = "PHONE")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    private String mobile;

    @Basic
    @javax.persistence.Column(name = "MOBILE")
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    private String descr;

    @Basic
    @javax.persistence.Column(name = "DESCR")
    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    private Timestamp protDate;

    @Basic
    @javax.persistence.Column(name = "PROT_DATE")
    public Timestamp getProtDate() {
        return protDate;
    }

    public void setProtDate(Timestamp protDate) {
        this.protDate = protDate;
    }

    private Long cardType;

    @Basic
    @javax.persistence.Column(name = "CARD_TYPE")
    public Long getCardType() {
        return cardType;
    }

    public void setCardType(Long cardType) {
        this.cardType = cardType;
    }

    private Long docId;

    @Basic
    @javax.persistence.Column(name = "DOC_ID")
    public Long getDocId() {
        return docId;
    }

    public void setDocId(Long docId) {
        this.docId = docId;
    }

    private String submitTime;

    @Basic
    @javax.persistence.Column(name = "SUBMIT_TIME")
    public String getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(String submitTime) {
        this.submitTime = submitTime;
    }

    private Long department;

    @Basic
    @javax.persistence.Column(name = "DEPARTMENT")
    public Long getDepartment() {
        return department;
    }

    public void setDepartment(Long department) {
        this.department = department;
    }

    private Long attachedDocId;

    @Basic
    @javax.persistence.Column(name = "ATTACHED_DOC_ID")
    public Long getAttachedDocId() {
        return attachedDocId;
    }

    public void setAttachedDocId(Long attachedDocId) {
        this.attachedDocId = attachedDocId;
    }

    private String teeNumber;

    @Basic
    @javax.persistence.Column(name = "TEE_NUMBER")
    public String getTeeNumber() {
        return teeNumber;
    }

    public void setTeeNumber(String teeNumber) {
        this.teeNumber = teeNumber;
    }

    private String fathername;

    @Basic
    @javax.persistence.Column(name = "FATHERNAME")
    public String getFathername() {
        return fathername;
    }

    public void setFathername(String fathername) {
        this.fathername = fathername;
    }

    private String speciality;

    @Basic
    @javax.persistence.Column(name = "SPECIALITY")
    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    private Integer shipyardDuties;

    @Basic
    @javax.persistence.Column(name = "SHIPYARD_DUTIES")
    public Integer getShipyardDuties() {
        return shipyardDuties;
    }

    public void setShipyardDuties(Integer shipyardDuties) {
        this.shipyardDuties = shipyardDuties;
    }

    private String email;

    @Basic
    @javax.persistence.Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private Integer education100;

    @Basic
    @javax.persistence.Column(name = "EDUCATION_100")
    public Integer getEducation100() {
        return education100;
    }

    public void setEducation100(Integer education100) {
        this.education100 = education100;
    }

    private Integer education3510;

    @Basic
    @javax.persistence.Column(name = "EDUCATION_35_10")
    public Integer getEducation3510() {
        return education3510;
    }

    public void setEducation3510(Integer education3510) {
        this.education3510 = education3510;
    }

    private Integer education10;

    @Basic
    @javax.persistence.Column(name = "EDUCATION_10")
    public Integer getEducation10() {
        return education10;
    }

    public void setEducation10(Integer education10) {
        this.education10 = education10;
    }

    private Long attachedDocIdEmplTraining;

    @Basic
    @javax.persistence.Column(name = "ATTACHED_DOC_ID_EMPL_TRAINING")
    public Long getAttachedDocIdEmplTraining() {
        return attachedDocIdEmplTraining;
    }

    public void setAttachedDocIdEmplTraining(Long attachedDocIdEmplTraining) {
        this.attachedDocIdEmplTraining = attachedDocIdEmplTraining;
    }

    private String specialityOther;

    @Basic
    @javax.persistence.Column(name = "SPECIALITY_OTHER")
    public String getSpecialityOther() {
        return specialityOther;
    }

    public void setSpecialityOther(String specialityOther) {
        this.specialityOther = specialityOther;
    }

    private String fax;

    @Basic
    @javax.persistence.Column(name = "FAX")
    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    private String diplomaIdComma;

    @Basic
    @javax.persistence.Column(name = "DIPLOMA_ID_COMMA")
    public String getDiplomaIdComma() {
        return diplomaIdComma;
    }

    public void setDiplomaIdComma(String diplomaIdComma) {
        this.diplomaIdComma = diplomaIdComma;
    }

    private String notes;

    @Basic
    @javax.persistence.Column(name = "NOTES")
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    private Integer sepeProfilStatus;

    @Basic
    @javax.persistence.Column(name = "SEPE_PROFIL_STATUS")
    public Integer getSepeProfilStatus() {
        return sepeProfilStatus;
    }

    public void setSepeProfilStatus(Integer sepeProfilStatus) {
        this.sepeProfilStatus = sepeProfilStatus;
    }

    private Long caseId;

    @Basic
    @javax.persistence.Column(name = "CASE_ID")
    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }

    private String reqAddrD;

    @Basic
    @javax.persistence.Column(name = "REQ_ADDR_D")
    public String getReqAddrD() {
        return reqAddrD;
    }

    public void setReqAddrD(String reqAddrD) {
        this.reqAddrD = reqAddrD;
    }

    private String reqAddrK;

    @Basic
    @javax.persistence.Column(name = "REQ_ADDR_K")
    public String getReqAddrK() {
        return reqAddrK;
    }

    public void setReqAddrK(String reqAddrK) {
        this.reqAddrK = reqAddrK;
    }

    private String reqAddrP;

    @Basic
    @javax.persistence.Column(name = "REQ_ADDR_P")
    public String getReqAddrP() {
        return reqAddrP;
    }

    public void setReqAddrP(String reqAddrP) {
        this.reqAddrP = reqAddrP;
    }

    private String reqAddrPe;

    @Basic
    @javax.persistence.Column(name = "REQ_ADDR_PE")
    public String getReqAddrPe() {
        return reqAddrPe;
    }

    public void setReqAddrPe(String reqAddrPe) {
        this.reqAddrPe = reqAddrPe;
    }

    private Long cooperationType;

    @Basic
    @javax.persistence.Column(name = "COOPERATION_TYPE")
    public Long getCooperationType() {
        return cooperationType;
    }

    public void setCooperationType(Long cooperationType) {
        this.cooperationType = cooperationType;
    }


    private String reqAddr;

    @Basic
    @javax.persistence.Column(name = "REQ_ADDR")
    public String getReqAddr() {
        return reqAddr;
    }

    public void setReqAddr(String reqAddr) {
        this.reqAddr = reqAddr;
    }

    private String reqAddrTk;

    @Basic
    @javax.persistence.Column(name = "REQ_ADDR_TK")
    public String getReqAddrTk() {
        return reqAddrTk;
    }

    public void setReqAddrTk(String reqAddrTk) {
        this.reqAddrTk = reqAddrTk;
    }

    private String foreas3510;

    @Basic
    @javax.persistence.Column(name = "FOREAS_3510")
    public String getForeas3510() {
        return foreas3510;
    }

    public void setForeas3510(String foreas3510) {
        this.foreas3510 = foreas3510;
    }

    private String foreas10;

    @Basic
    @javax.persistence.Column(name = "FOREAS_10")
    public String getForeas10() {
        return foreas10;
    }

    public void setForeas10(String foreas10) {
        this.foreas10 = foreas10;
    }

    private String foreas100;

    @Basic
    @javax.persistence.Column(name = "FOREAS_100")
    public String getForeas100() {
        return foreas100;
    }

    public void setForeas100(String foreas100) {
        this.foreas100 = foreas100;
    }

    private Long seminarId100;

    @Basic
    @javax.persistence.Column(name = "SEMINAR_ID_100")
    public Long getSeminarId100() {
        return seminarId100;
    }

    public void setSeminarId100(Long seminarId100) {
        this.seminarId100 = seminarId100;
    }

    private Long seminarId3510;

    @Basic
    @javax.persistence.Column(name = "SEMINAR_ID_3510")
    public Long getSeminarId3510() {
        return seminarId3510;
    }

    public void setSeminarId3510(Long seminarId3510) {
        this.seminarId3510 = seminarId3510;
    }

    private Long seminarId10;

    @Basic
    @javax.persistence.Column(name = "SEMINAR_ID_10")
    public Long getSeminarId10() {
        return seminarId10;
    }

    public void setSeminarId10(Long seminarId10) {
        this.seminarId10 = seminarId10;
    }

    private String etiology;

    @Basic
    @javax.persistence.Column(name = "ETIOLOGY")
    public String getEtiology() {
        return etiology;
    }

    public void setEtiology(String etiology) {
        this.etiology = etiology;
    }

    private String seminarId100Text;

    @Basic
    @javax.persistence.Column(name = "SEMINAR_ID_100_TEXT")
    public String getSeminarId100Text() {
        return seminarId100Text;
    }

    public void setSeminarId100Text(String seminarId100Text) {
        this.seminarId100Text = seminarId100Text;
    }

    private String seminarId3510Text;

    @Basic
    @javax.persistence.Column(name = "SEMINAR_ID_3510_TEXT")
    public String getSeminarId3510Text() {
        return seminarId3510Text;
    }

    public void setSeminarId3510Text(String seminarId3510Text) {
        this.seminarId3510Text = seminarId3510Text;
    }

    private String seminarId10Text;

    @Basic
    @javax.persistence.Column(name = "SEMINAR_ID_10_TEXT")
    public String getSeminarId10Text() {
        return seminarId10Text;
    }

    public void setSeminarId10Text(String seminarId10Text) {
        this.seminarId10Text = seminarId10Text;
    }

    private Long papTaStatus;

    @Basic
    @javax.persistence.Column(name = "PAP_TA_STATUS")
    public Long getPapTaStatus() {
        return papTaStatus;
    }

    public void setPapTaStatus(Long papTaStatus) {
        this.papTaStatus = papTaStatus;
    }

    private Long papTaRegId;

    @Basic
    @javax.persistence.Column(name = "PAP_TA_REG_ID")
    public Long getPapTaRegId() {
        return papTaRegId;
    }

    public void setPapTaRegId(Long papTaRegId) {
        this.papTaRegId = papTaRegId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpPtlVTechnicianRegrequest that = (SpPtlVTechnicianRegrequest) o;

        if (id != that.id) return false;
        if (userId != that.userId) return false;
        if (subStatus != null ? !subStatus.equals(that.subStatus) : that.subStatus != null) return false;
        if (reqStatus != null ? !reqStatus.equals(that.reqStatus) : that.reqStatus != null) return false;
        if (protNo != null ? !protNo.equals(that.protNo) : that.protNo != null) return false;
        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
        if (protYear != null ? !protYear.equals(that.protYear) : that.protYear != null) return false;
        if (educationLevel != null ? !educationLevel.equals(that.educationLevel) : that.educationLevel != null)
            return false;
        if (afm != null ? !afm.equals(that.afm) : that.afm != null) return false;
        if (amka != null ? !amka.equals(that.amka) : that.amka != null) return false;
        if (cardNumber != null ? !cardNumber.equals(that.cardNumber) : that.cardNumber != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (mobile != null ? !mobile.equals(that.mobile) : that.mobile != null) return false;
        if (descr != null ? !descr.equals(that.descr) : that.descr != null) return false;
        if (protDate != null ? !protDate.equals(that.protDate) : that.protDate != null) return false;
        if (cardType != null ? !cardType.equals(that.cardType) : that.cardType != null) return false;
        if (docId != null ? !docId.equals(that.docId) : that.docId != null) return false;
        if (submitTime != null ? !submitTime.equals(that.submitTime) : that.submitTime != null) return false;
        if (department != null ? !department.equals(that.department) : that.department != null) return false;
        if (attachedDocId != null ? !attachedDocId.equals(that.attachedDocId) : that.attachedDocId != null)
            return false;
        if (teeNumber != null ? !teeNumber.equals(that.teeNumber) : that.teeNumber != null) return false;
        if (fathername != null ? !fathername.equals(that.fathername) : that.fathername != null) return false;
        if (speciality != null ? !speciality.equals(that.speciality) : that.speciality != null) return false;
        if (shipyardDuties != null ? !shipyardDuties.equals(that.shipyardDuties) : that.shipyardDuties != null)
            return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (education100 != null ? !education100.equals(that.education100) : that.education100 != null) return false;
        if (education3510 != null ? !education3510.equals(that.education3510) : that.education3510 != null)
            return false;
        if (education10 != null ? !education10.equals(that.education10) : that.education10 != null) return false;
        if (attachedDocIdEmplTraining != null ? !attachedDocIdEmplTraining.equals(that.attachedDocIdEmplTraining) : that.attachedDocIdEmplTraining != null)
            return false;
        if (specialityOther != null ? !specialityOther.equals(that.specialityOther) : that.specialityOther != null)
            return false;
        if (fax != null ? !fax.equals(that.fax) : that.fax != null) return false;
        if (diplomaIdComma != null ? !diplomaIdComma.equals(that.diplomaIdComma) : that.diplomaIdComma != null)
            return false;
        if (notes != null ? !notes.equals(that.notes) : that.notes != null) return false;
        if (sepeProfilStatus != null ? !sepeProfilStatus.equals(that.sepeProfilStatus) : that.sepeProfilStatus != null)
            return false;
        if (caseId != null ? !caseId.equals(that.caseId) : that.caseId != null) return false;
        if (reqAddrD != null ? !reqAddrD.equals(that.reqAddrD) : that.reqAddrD != null) return false;
        if (reqAddrK != null ? !reqAddrK.equals(that.reqAddrK) : that.reqAddrK != null) return false;
        if (reqAddrP != null ? !reqAddrP.equals(that.reqAddrP) : that.reqAddrP != null) return false;
        if (reqAddrPe != null ? !reqAddrPe.equals(that.reqAddrPe) : that.reqAddrPe != null) return false;
        if (cooperationType != null ? !cooperationType.equals(that.cooperationType) : that.cooperationType != null)
            return false;
        if (reqAddr != null ? !reqAddr.equals(that.reqAddr) : that.reqAddr != null) return false;
        if (reqAddrTk != null ? !reqAddrTk.equals(that.reqAddrTk) : that.reqAddrTk != null) return false;
        if (foreas10 != null ? !foreas10.equals(that.foreas10) : that.foreas10 != null) return false;
        if (foreas3510 != null ? !foreas3510.equals(that.foreas3510) : that.foreas3510 != null) return false;
        if (foreas100 != null ? !foreas100.equals(that.foreas100) : that.foreas100 != null) return false;
        if (seminarId100 != null ? !seminarId100.equals(that.seminarId100) : that.seminarId100 != null) return false;
        if (seminarId3510 != null ? !seminarId3510.equals(that.seminarId3510) : that.seminarId3510 != null) return false;
        if (seminarId10 != null ? !seminarId10.equals(that.seminarId10) : that.seminarId10 != null) return false;
        if (etiology != null ? !etiology.equals(that.etiology) : that.etiology != null) return false;
        if (seminarId100Text != null ? !seminarId100Text.equals(that.seminarId100Text) : that.seminarId100Text != null) return false;
        if (seminarId3510Text != null ? !seminarId3510Text.equals(that.seminarId3510Text) : that.seminarId3510Text != null) return false;
        if (seminarId10Text != null ? !seminarId10Text.equals(that.seminarId10Text) : that.seminarId10Text != null) return false;
        if (papTaStatus != null ? !papTaStatus.equals(that.papTaStatus) : that.papTaStatus != null) return false;
        if (papTaRegId != null ? !papTaRegId.equals(that.papTaRegId) : that.papTaRegId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (subStatus != null ? subStatus.hashCode() : 0);
        result = 31 * result + (reqStatus != null ? reqStatus.hashCode() : 0);
        result = 31 * result + (protNo != null ? protNo.hashCode() : 0);
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (protYear != null ? protYear.hashCode() : 0);
        result = 31 * result + (educationLevel != null ? educationLevel.hashCode() : 0);
        result = 31 * result + (afm != null ? afm.hashCode() : 0);
        result = 31 * result + (amka != null ? amka.hashCode() : 0);
        result = 31 * result + (cardNumber != null ? cardNumber.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
        result = 31 * result + (descr != null ? descr.hashCode() : 0);
        result = 31 * result + (protDate != null ? protDate.hashCode() : 0);
        result = 31 * result + (cardType != null ? cardType.hashCode() : 0);
        result = 31 * result + (docId != null ? docId.hashCode() : 0);
        result = 31 * result + (submitTime != null ? submitTime.hashCode() : 0);
        result = 31 * result + (department != null ? department.hashCode() : 0);
        result = 31 * result + (attachedDocId != null ? attachedDocId.hashCode() : 0);
        result = 31 * result + (teeNumber != null ? teeNumber.hashCode() : 0);
        result = 31 * result + (fathername != null ? fathername.hashCode() : 0);
        result = 31 * result + (speciality != null ? speciality.hashCode() : 0);
        result = 31 * result + (shipyardDuties != null ? shipyardDuties.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (education100 != null ? education100.hashCode() : 0);
        result = 31 * result + (education3510 != null ? education3510.hashCode() : 0);
        result = 31 * result + (education10 != null ? education10.hashCode() : 0);
        result = 31 * result + (attachedDocIdEmplTraining != null ? attachedDocIdEmplTraining.hashCode() : 0);
        result = 31 * result + (specialityOther != null ? specialityOther.hashCode() : 0);
        result = 31 * result + (fax != null ? fax.hashCode() : 0);
        result = 31 * result + (diplomaIdComma != null ? diplomaIdComma.hashCode() : 0);
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        result = 31 * result + (sepeProfilStatus != null ? sepeProfilStatus.hashCode() : 0);
        result = 31 * result + (caseId != null ? caseId.hashCode() : 0);
        result = 31 * result + (reqAddrD != null ? reqAddrD.hashCode() : 0);
        result = 31 * result + (reqAddrK != null ? reqAddrK.hashCode() : 0);
        result = 31 * result + (reqAddrP != null ? reqAddrP.hashCode() : 0);
        result = 31 * result + (reqAddrPe != null ? reqAddrPe.hashCode() : 0);
        result = 31 * result + (cooperationType != null ? cooperationType.hashCode() : 0);
        result = 31 * result + (reqAddr != null ? reqAddr.hashCode() : 0);
        result = 31 * result + (reqAddrTk != null ? reqAddrTk.hashCode() : 0);
        result = 31 * result + (foreas100 != null ? foreas100.hashCode() : 0);
        result = 31 * result + (foreas3510 != null ? foreas3510.hashCode() : 0);
        result = 31 * result + (foreas10 != null ? foreas10.hashCode() : 0);
        result = 31 * result + (seminarId100 != null ? seminarId100.hashCode() : 0);
        result = 31 * result + (seminarId3510 != null ? seminarId3510.hashCode() : 0);
        result = 31 * result + (seminarId10 != null ? seminarId10.hashCode() : 0);
        result = 31 * result + (etiology != null ? etiology.hashCode() : 0);
        result = 31 * result + (seminarId100Text != null ? seminarId100Text.hashCode() : 0);
        result = 31 * result + (seminarId3510Text != null ? seminarId3510Text.hashCode() : 0);
        result = 31 * result + (seminarId10Text != null ? seminarId10Text.hashCode() : 0);
        result = 31 * result + (papTaStatus != null ? papTaStatus.hashCode() : 0);
        result = 31 * result + (papTaRegId != null ? papTaRegId.hashCode() : 0);
        return result;
    }
}
