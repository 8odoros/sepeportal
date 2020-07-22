package sepe.domain.doctor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Nick on 4/2/2015.
 */
@Entity
@javax.persistence.Table(name = "SP_PTL_DOCTOR_REGREQUEST", schema = "SP_PTL", catalog = "")
public class SpPtlDoctorRegrequest {
    private Long id;

    @Id
    @GeneratedValue
    @javax.persistence.Column(name = "ID", nullable = false, insertable = true, updatable = true, precision = 0)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private Long userId;

    @Basic
    @javax.persistence.Column(name = "USER_ID", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    private Integer subStatus;

    @Basic
    @javax.persistence.Column(name = "SUB_STATUS", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getSubStatus() {
        return subStatus;
    }

    public void setSubStatus(Integer subStatus) {
        this.subStatus = subStatus;
    }

    private Integer reqStatus;

    @Basic
    @javax.persistence.Column(name = "REQ_STATUS", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getReqStatus() {
        return reqStatus;
    }

    public void setReqStatus(Integer reqStatus) {
        this.reqStatus = reqStatus;
    }

    private String protNo;

    @Basic
    @javax.persistence.Column(name = "PROT_NO", nullable = true, insertable = true, updatable = true, length = 50)
    public String getProtNo() {
        return protNo;
    }

    public void setProtNo(String protNo) {
        this.protNo = protNo;
    }

    private String firstname;

    @Basic
    @javax.persistence.Column(name = "FIRSTNAME", nullable = true, insertable = true, updatable = true, length = 100)
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    private String lastname;

    @Basic
    @javax.persistence.Column(name = "LASTNAME", nullable = true, insertable = true, updatable = true, length = 100)
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    private Integer protYear;

    @Basic
    @javax.persistence.Column(name = "PROT_YEAR", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getProtYear() {
        return protYear;
    }

    public void setProtYear(Integer protYear) {
        this.protYear = protYear;
    }

    private Long educationLevel;

    @Basic
    @javax.persistence.Column(name = "EDUCATION_LEVEL", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(Long educationLevel) {
        this.educationLevel = educationLevel;
    }

    private String afm;

    @Basic
    @javax.persistence.Column(name = "AFM", nullable = true, insertable = true, updatable = true, length = 50)
    public String getAfm() {
        return afm;
    }

    public void setAfm(String afm) {
        this.afm = afm;
    }

    private String amka;

    @Basic
    @javax.persistence.Column(name = "AMKA", nullable = true, insertable = true, updatable = true, length = 50)
    public String getAmka() {
        return amka;
    }

    public void setAmka(String amka) {
        this.amka = amka;
    }

    private String cardNumber;

    @Basic
    @javax.persistence.Column(name = "CARD_NUMBER", nullable = true, insertable = true, updatable = true, length = 50)
    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    private String phone;

    @Basic
    @javax.persistence.Column(name = "PHONE", nullable = true, insertable = true, updatable = true, length = 50)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    private String descr;

    @Basic
    @javax.persistence.Column(name = "DESCR", nullable = true, insertable = true, updatable = true, length = 3000)
    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    private Timestamp protDate;

    @Basic
    @javax.persistence.Column(name = "PROT_DATE", nullable = true, insertable = true, updatable = true)
    public Timestamp getProtDate() {
        return protDate;
    }

    public void setProtDate(Timestamp protDate) {
        this.protDate = protDate;
    }

    private Long cardType;

    @Basic
    @javax.persistence.Column(name = "CARD_TYPE", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getCardType() {
        return cardType;
    }

    public void setCardType(Long cardType) {
        this.cardType = cardType;
    }

    private Long docId;

    @Basic
    @javax.persistence.Column(name = "DOC_ID", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getDocId() {
        return docId;
    }

    public void setDocId(Long docId) {
        this.docId = docId;
    }

    private String submitTime;

    @Basic
    @javax.persistence.Column(name = "SUBMIT_TIME", nullable = true, insertable = true, updatable = true, length = 50)
    public String getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(String submitTime) {
        this.submitTime = submitTime;
    }

    private Long department;

    @Basic
    @javax.persistence.Column(name = "DEPARTMENT", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getDepartment() {
        return department;
    }

    public void setDepartment(Long department) {
        this.department = department;
    }

    private Long attachedDocId;

    @Basic
    @javax.persistence.Column(name = "ATTACHED_DOC_ID", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getAttachedDocId() {
        return attachedDocId;
    }

    public void setAttachedDocId(Long attachedDocId) {
        this.attachedDocId = attachedDocId;
    }

    private String teeNumber;

    @Basic
    @javax.persistence.Column(name = "TEE_NUMBER", nullable = true, insertable = true, updatable = true, length = 100)
    public String getTeeNumber() {
        return teeNumber;
    }

    public void setTeeNumber(String teeNumber) {
        this.teeNumber = teeNumber;
    }

    private String fathername;

    @Basic
    @javax.persistence.Column(name = "FATHERNAME", nullable = true, insertable = true, updatable = true, length = 100)
    public String getFathername() {
        return fathername;
    }

    public void setFathername(String fathername) {
        this.fathername = fathername;
    }

    private String speciality;

    @Basic
    @javax.persistence.Column(name = "SPECIALITY", nullable = true, insertable = true, updatable = true, precision = 0)
    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    private String email;

    @Basic
    @javax.persistence.Column(name = "EMAIL", nullable = true, insertable = true, updatable = true, length = 100)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private Integer education100;

    @Basic
    @javax.persistence.Column(name = "EDUCATION_100", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getEducation100() {
        return education100;
    }

    public void setEducation100(Integer education100) {
        this.education100 = education100;
    }

    private Long attachedDocIdEmplTraining;

    @Basic
    @javax.persistence.Column(name = "ATTACHED_DOC_ID_EMPL_TRAINING", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getAttachedDocIdEmplTraining() {
        return attachedDocIdEmplTraining;
    }

    public void setAttachedDocIdEmplTraining(Long attachedDocIdEmplTraining) {
        this.attachedDocIdEmplTraining = attachedDocIdEmplTraining;
    }

    private String specialityOther;

    @Basic
    @javax.persistence.Column(name = "SPECIALITY_OTHER", nullable = true, insertable = true, updatable = true, length = 300)
    public String getSpecialityOther() {
        return specialityOther;
    }

    public void setSpecialityOther(String specialityOther) {
        this.specialityOther = specialityOther;
    }

    private String fax;

    @Basic
    @javax.persistence.Column(name = "FAX", nullable = true, insertable = true, updatable = true, length = 50)
    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    private String notes;

    @Basic
    @javax.persistence.Column(name = "NOTES", nullable = true, insertable = true, updatable = true, length = 3000)
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    private Integer sepeProfilStatus;

    @Basic
    @javax.persistence.Column(name = "SEPE_PROFIL_STATUS", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getSepeProfilStatus() {
        return sepeProfilStatus;
    }

    public void setSepeProfilStatus(Integer sepeProfilStatus) {
        this.sepeProfilStatus = sepeProfilStatus;
    }

    private Long caseId;

    @Basic
    @javax.persistence.Column(name = "CASE_ID", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }

    private Integer belongsToPedy;

    @Basic
    @javax.persistence.Column(name = "BELONGS_TO_PEDY", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getBelongsToPedy() {
        return belongsToPedy;
    }

    public void setBelongsToPedy(Integer belongsToPedy) {
        this.belongsToPedy = belongsToPedy;
    }

    private Long attachedDocIdPedyYes;

    @Basic
    @javax.persistence.Column(name = "ATTACHED_DOC_ID_PEDY_YES", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getAttachedDocIdPedyYes() {
        return attachedDocIdPedyYes;
    }

    public void setAttachedDocIdPedyYes(Long attachedDocIdPedyYes) {
        this.attachedDocIdPedyYes = attachedDocIdPedyYes;
    }

    private Long attachedDocIdPedyNo;

    @Basic
    @javax.persistence.Column(name = "ATTACHED_DOC_ID_PEDY_NO", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getAttachedDocIdPedyNo() {
        return attachedDocIdPedyNo;
    }

    public void setAttachedDocIdPedyNo(Long attachedDocIdPedyNo) {
        this.attachedDocIdPedyNo = attachedDocIdPedyNo;
    }

    private Long cooperationType;

    @Basic
    @javax.persistence.Column(name = "COOPERATION_TYPE", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getCooperationType() {
        return cooperationType;
    }

    public void setCooperationType(Long cooperationType) {
        this.cooperationType = cooperationType;
    }

    private String ieDocProtNo;

    @Basic
    @javax.persistence.Column(name = "IE_DOC_PROT_NO", nullable = true, insertable = true, updatable = true, length = 100)
    public String getIeDocProtNo() {
        return ieDocProtNo;
    }

    public void setIeDocProtNo(String ieDocProtNo) {
        this.ieDocProtNo = ieDocProtNo;
    }

    private Timestamp ieDocDate;

    @Basic
    @javax.persistence.Column(name = "IE_DOC_DATE", nullable = true, insertable = true, updatable = true)
    public Timestamp getIeDocDate() {
        return ieDocDate;
    }

    public void setIeDocDate(Timestamp ieDocDate) {
        this.ieDocDate = ieDocDate;
    }

    private String ieDocCompanyName;

    @Basic
    @javax.persistence.Column(name = "IE_DOC_COMPANY_NAME", nullable = true, insertable = true, updatable = true, length = 300)
    public String getIeDocCompanyName() {
        return ieDocCompanyName;
    }

    public void setIeDocCompanyName(String ieDocCompanyName) {
        this.ieDocCompanyName = ieDocCompanyName;
    }

    private String ieDocDepartment;

    @Basic
    @javax.persistence.Column(name = "IE_DOC_DEPARTMENT", nullable = true, insertable = true, updatable = true, length = 100)
    public String getIeDocDepartment() {
        return ieDocDepartment;
    }

    public void setIeDocDepartment(String ieDocDepartment) {
        this.ieDocDepartment = ieDocDepartment;
    }

    private Long attachedDocIdMedassoc;

    @Basic
    @javax.persistence.Column(name = "ATTACHED_DOC_ID_MEDASSOC", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getAttachedDocIdMedassoc() {
        return attachedDocIdMedassoc;
    }

    public void setAttachedDocIdMedassoc(Long attachedDocIdMedassoc) {
        this.attachedDocIdMedassoc = attachedDocIdMedassoc;
    }

    private String medicalAssocNumber;

    @Basic
    @javax.persistence.Column(name = "MEDICAL_ASSOC_NUMBER", nullable = true, insertable = true, updatable = true, length = 100)
    public String getMedicalAssocNumber() {
        return medicalAssocNumber;
    }

    public void setMedicalAssocNumber(String medicalAssocNumber) {
        this.medicalAssocNumber = medicalAssocNumber;
    }

    private String spDifferentCounty;

    @Basic
    @javax.persistence.Column(name = "SP_DIFFERENT_COUNTY", nullable = true, insertable = true, updatable = true, length = 500)
    public String getSpDifferentCounty() {
        return spDifferentCounty;
    }

    public void setSpDifferentCounty(String spDifferentCounty) {
        this.spDifferentCounty = spDifferentCounty;
    }

    private String mobile;

    @Basic
    @javax.persistence.Column(name = "MOBILE", nullable = true, insertable = true, updatable = true, length = 50)
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    private Integer isMilitaryDoctor;

    @Basic
    @javax.persistence.Column(name = "IS_MILITARY_DOCTOR", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getIsMilitaryDoctor() {
        return isMilitaryDoctor;
    }

    public void setIsMilitaryDoctor(Integer isMilitaryDoctor) {
        this.isMilitaryDoctor = isMilitaryDoctor;
    }

    private Long attachedDocIdMilitary;

    @Basic
    @javax.persistence.Column(name = "ATTACHED_DOC_ID_MILITARY", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getAttachedDocIdMilitary() {
        return attachedDocIdMilitary;
    }

    public void setAttachedDocIdMilitary(Long attachedDocIdMilitary) {
        this.attachedDocIdMilitary = attachedDocIdMilitary;
    }

    private String reqAddr;

    @Basic
    @javax.persistence.Column(name = "REQ_ADDR", nullable = true, insertable = true, updatable = true, length = 100)
    public String getReqAddr() {
        return reqAddr;
    }

    public void setReqAddr(String reqAddr) {
        this.reqAddr = reqAddr;
    }

    private String reqAddrD;

    @Basic
    @javax.persistence.Column(name = "REQ_ADDR_D", nullable = true, insertable = true, updatable = true, length = 100)
    public String getReqAddrD() {
        return reqAddrD;
    }

    public void setReqAddrD(String reqAddrD) {
        this.reqAddrD = reqAddrD;
    }

    private String reqAddrK;

    @Basic
    @javax.persistence.Column(name = "REQ_ADDR_K", nullable = true, insertable = true, updatable = true, length = 100)
    public String getReqAddrK() {
        return reqAddrK;
    }

    public void setReqAddrK(String reqAddrK) {
        this.reqAddrK = reqAddrK;
    }

    private String reqAddrP;

    @Basic
    @javax.persistence.Column(name = "REQ_ADDR_P", nullable = true, insertable = true, updatable = true, length = 100)
    public String getReqAddrP() {
        return reqAddrP;
    }

    public void setReqAddrP(String reqAddrP) {
        this.reqAddrP = reqAddrP;
    }

    private String reqAddrPe;

    @Basic
    @javax.persistence.Column(name = "REQ_ADDR_PE", nullable = true, insertable = true, updatable = true, length = 100)
    public String getReqAddrPe() {
        return reqAddrPe;
    }

    public void setReqAddrPe(String reqAddrPe) {
        this.reqAddrPe = reqAddrPe;
    }

    private Long reqAddrTk;

    @Basic
    @javax.persistence.Column(name = "REQ_ADDR_TK", nullable = true, insertable = true, updatable = true, length = 100)
    public Long getReqAddrTk() {
        return reqAddrTk;
    }

    public void setReqAddrTk(Long reqAddrTk) {
        this.reqAddrTk = reqAddrTk;
    }

    private Long attachedDocIdDiploma;

    @Basic
    @javax.persistence.Column(name = "ATTACHED_DOC_ID_DIPLOMA", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getAttachedDocIdDiploma() {
        return attachedDocIdDiploma;
    }

    public void setAttachedDocIdDiploma(Long attachedDocIdDiploma) {
        this.attachedDocIdDiploma = attachedDocIdDiploma;
    }

    /////////////////////////////////////////////////
    ///*
    private Set<SpPtlDoctorCounty> doctorCounties = new HashSet<SpPtlDoctorCounty>(0);

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "doctorRegrequests")
    public Set<SpPtlDoctorCounty> getDoctorCounties() {
        return this.doctorCounties;
    }

    public void setDoctorCounties(Set<SpPtlDoctorCounty> doctorCounties) {
        this.doctorCounties = doctorCounties;
    }
    // */
    /////////////////////////////////////////////////
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpPtlDoctorRegrequest that = (SpPtlDoctorRegrequest) o;

        if (id != that.id) return false;
        if (afm != null ? !afm.equals(that.afm) : that.afm != null) return false;
        if (amka != null ? !amka.equals(that.amka) : that.amka != null) return false;
        if (attachedDocId != null ? !attachedDocId.equals(that.attachedDocId) : that.attachedDocId != null)
            return false;
        if (attachedDocIdDiploma != null ? !attachedDocIdDiploma.equals(that.attachedDocIdDiploma) : that.attachedDocIdDiploma != null)
            return false;
        if (attachedDocIdEmplTraining != null ? !attachedDocIdEmplTraining.equals(that.attachedDocIdEmplTraining) : that.attachedDocIdEmplTraining != null)
            return false;
        if (attachedDocIdMedassoc != null ? !attachedDocIdMedassoc.equals(that.attachedDocIdMedassoc) : that.attachedDocIdMedassoc != null)
            return false;
        if (attachedDocIdMilitary != null ? !attachedDocIdMilitary.equals(that.attachedDocIdMilitary) : that.attachedDocIdMilitary != null)
            return false;
        if (attachedDocIdPedyNo != null ? !attachedDocIdPedyNo.equals(that.attachedDocIdPedyNo) : that.attachedDocIdPedyNo != null)
            return false;
        if (attachedDocIdPedyYes != null ? !attachedDocIdPedyYes.equals(that.attachedDocIdPedyYes) : that.attachedDocIdPedyYes != null)
            return false;
        if (belongsToPedy != null ? !belongsToPedy.equals(that.belongsToPedy) : that.belongsToPedy != null)
            return false;
        if (cardNumber != null ? !cardNumber.equals(that.cardNumber) : that.cardNumber != null) return false;
        if (cardType != null ? !cardType.equals(that.cardType) : that.cardType != null) return false;
        if (caseId != null ? !caseId.equals(that.caseId) : that.caseId != null) return false;
        if (cooperationType != null ? !cooperationType.equals(that.cooperationType) : that.cooperationType != null)
            return false;
        if (department != null ? !department.equals(that.department) : that.department != null) return false;
        if (descr != null ? !descr.equals(that.descr) : that.descr != null) return false;
        if (docId != null ? !docId.equals(that.docId) : that.docId != null) return false;
        if (education100 != null ? !education100.equals(that.education100) : that.education100 != null) return false;
        if (educationLevel != null ? !educationLevel.equals(that.educationLevel) : that.educationLevel != null)
            return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (fathername != null ? !fathername.equals(that.fathername) : that.fathername != null) return false;
        if (fax != null ? !fax.equals(that.fax) : that.fax != null) return false;
        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        if (ieDocCompanyName != null ? !ieDocCompanyName.equals(that.ieDocCompanyName) : that.ieDocCompanyName != null)
            return false;
        if (ieDocDate != null ? !ieDocDate.equals(that.ieDocDate) : that.ieDocDate != null) return false;
        if (ieDocDepartment != null ? !ieDocDepartment.equals(that.ieDocDepartment) : that.ieDocDepartment != null)
            return false;
        if (ieDocProtNo != null ? !ieDocProtNo.equals(that.ieDocProtNo) : that.ieDocProtNo != null) return false;
        if (isMilitaryDoctor != null ? !isMilitaryDoctor.equals(that.isMilitaryDoctor) : that.isMilitaryDoctor != null)
            return false;
        if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
        if (medicalAssocNumber != null ? !medicalAssocNumber.equals(that.medicalAssocNumber) : that.medicalAssocNumber != null)
            return false;
        if (mobile != null ? !mobile.equals(that.mobile) : that.mobile != null) return false;
        if (notes != null ? !notes.equals(that.notes) : that.notes != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (protDate != null ? !protDate.equals(that.protDate) : that.protDate != null) return false;
        if (protNo != null ? !protNo.equals(that.protNo) : that.protNo != null) return false;
        if (protYear != null ? !protYear.equals(that.protYear) : that.protYear != null) return false;
        if (reqAddr != null ? !reqAddr.equals(that.reqAddr) : that.reqAddr != null) return false;
        if (reqAddrD != null ? !reqAddrD.equals(that.reqAddrD) : that.reqAddrD != null) return false;
        if (reqAddrK != null ? !reqAddrK.equals(that.reqAddrK) : that.reqAddrK != null) return false;
        if (reqAddrP != null ? !reqAddrP.equals(that.reqAddrP) : that.reqAddrP != null) return false;
        if (reqAddrPe != null ? !reqAddrPe.equals(that.reqAddrPe) : that.reqAddrPe != null) return false;
        if (reqAddrTk != null ? !reqAddrTk.equals(that.reqAddrTk) : that.reqAddrTk != null) return false;
        if (reqStatus != null ? !reqStatus.equals(that.reqStatus) : that.reqStatus != null) return false;
        if (sepeProfilStatus != null ? !sepeProfilStatus.equals(that.sepeProfilStatus) : that.sepeProfilStatus != null)
            return false;
        if (spDifferentCounty != null ? !spDifferentCounty.equals(that.spDifferentCounty) : that.spDifferentCounty != null)
            return false;
        if (speciality != null ? !speciality.equals(that.speciality) : that.speciality != null) return false;
        if (specialityOther != null ? !specialityOther.equals(that.specialityOther) : that.specialityOther != null)
            return false;
        if (subStatus != null ? !subStatus.equals(that.subStatus) : that.subStatus != null) return false;
        if (submitTime != null ? !submitTime.equals(that.submitTime) : that.submitTime != null) return false;
        if (teeNumber != null ? !teeNumber.equals(that.teeNumber) : that.teeNumber != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
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
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (education100 != null ? education100.hashCode() : 0);
        result = 31 * result + (attachedDocIdEmplTraining != null ? attachedDocIdEmplTraining.hashCode() : 0);
        result = 31 * result + (specialityOther != null ? specialityOther.hashCode() : 0);
        result = 31 * result + (fax != null ? fax.hashCode() : 0);
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        result = 31 * result + (sepeProfilStatus != null ? sepeProfilStatus.hashCode() : 0);
        result = 31 * result + (caseId != null ? caseId.hashCode() : 0);
        result = 31 * result + (belongsToPedy != null ? belongsToPedy.hashCode() : 0);
        result = 31 * result + (attachedDocIdPedyYes != null ? attachedDocIdPedyYes.hashCode() : 0);
        result = 31 * result + (attachedDocIdPedyNo != null ? attachedDocIdPedyNo.hashCode() : 0);
        result = 31 * result + (cooperationType != null ? cooperationType.hashCode() : 0);
        result = 31 * result + (ieDocProtNo != null ? ieDocProtNo.hashCode() : 0);
        result = 31 * result + (ieDocDate != null ? ieDocDate.hashCode() : 0);
        result = 31 * result + (ieDocCompanyName != null ? ieDocCompanyName.hashCode() : 0);
        result = 31 * result + (ieDocDepartment != null ? ieDocDepartment.hashCode() : 0);
        result = 31 * result + (attachedDocIdMedassoc != null ? attachedDocIdMedassoc.hashCode() : 0);
        result = 31 * result + (medicalAssocNumber != null ? medicalAssocNumber.hashCode() : 0);
        result = 31 * result + (spDifferentCounty != null ? spDifferentCounty.hashCode() : 0);
        result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
        result = 31 * result + (isMilitaryDoctor != null ? isMilitaryDoctor.hashCode() : 0);
        result = 31 * result + (attachedDocIdMilitary != null ? attachedDocIdMilitary.hashCode() : 0);
        result = 31 * result + (reqAddr != null ? reqAddr.hashCode() : 0);
        result = 31 * result + (reqAddrD != null ? reqAddrD.hashCode() : 0);
        result = 31 * result + (reqAddrK != null ? reqAddrK.hashCode() : 0);
        result = 31 * result + (reqAddrP != null ? reqAddrP.hashCode() : 0);
        result = 31 * result + (reqAddrPe != null ? reqAddrPe.hashCode() : 0);
        result = 31 * result + (reqAddrTk != null ? reqAddrTk.hashCode() : 0);
        result = 31 * result + (attachedDocIdDiploma != null ? attachedDocIdDiploma.hashCode() : 0);
        return result;
    }
}
