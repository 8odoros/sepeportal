package sepe.domain.company;

import sepe.domain.TUser;
import sepe.domain.technician.SpPtlTechnicianDiploma;
import sepe.dto.SpPtlCompanyAccidentWitnDTO;
import sepe.dto.UserDTO;

import javax.annotation.Nullable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Nikos on 4/19/2015.
 */
@Entity
@javax.persistence.Table(name = "SP_PTL_COMPANY_ACCIDENT", schema = "SP_PTL", catalog = "")
public class SpPtlCompanyAccident {
    private Long id;

    @Id
    @GeneratedValue
    @javax.persistence.Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private Long companyId;

    @Basic
    @javax.persistence.Column(name = "COMPANY_ID")
    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    private String compTaxNumber;

    @Basic
    @javax.persistence.Column(name = "COMP_TAX_NUMBER")
    public String getCompTaxNumber() {
        return compTaxNumber;
    }

    public void setCompTaxNumber(String compTaxNumber) {
        this.compTaxNumber = compTaxNumber;
    }

    private String compFullName;

    @Basic
    @javax.persistence.Column(name = "COMP_FULL_NAME")
    public String getCompFullName() {
        return compFullName;
    }

    public void setCompFullName(String compFullName) {
        this.compFullName = compFullName;
    }

    private String compPhone;

    @Basic
    @javax.persistence.Column(name = "COMP_PHONE")
    public String getCompPhone() {
        return compPhone;
    }

    public void setCompPhone(String compPhone) {
        this.compPhone = compPhone;
    }

    private String compNameTe;

    @Basic
    @javax.persistence.Column(name = "COMP_NAME_TE")
    public String getCompNameTe() {
        return compNameTe;
    }

    public void setCompNameTe(String compNameTe) {
        this.compNameTe = compNameTe;
    }

    private String compNameIe;

    @Basic
    @javax.persistence.Column(name = "COMP_NAME_IE")
    public String getCompNameIe() {
        return compNameIe;
    }

    public void setCompNameIe(String compNameIe) {
        this.compNameIe = compNameIe;
    }

    private String compAddr;

    @Basic
    @javax.persistence.Column(name = "COMP_ADDR")
    public String getCompAddr() {
        return compAddr;
    }

    public void setCompAddr(String compAddr) {
        this.compAddr = compAddr;
    }

    private String compAddrD;

    @Basic
    @javax.persistence.Column(name = "COMP_ADDR_D")
    public String getCompAddrD() {
        return compAddrD;
    }

    public void setCompAddrD(String compAddrD) {
        this.compAddrD = compAddrD;
    }

    private String compAddrK;

    @Basic
    @javax.persistence.Column(name = "COMP_ADDR_K")
    public String getCompAddrK() {
        return compAddrK;
    }

    public void setCompAddrK(String compAddrK) {
        this.compAddrK = compAddrK;
    }

    private String compAddrP;

    @Basic
    @javax.persistence.Column(name = "COMP_ADDR_P")
    public String getCompAddrP() {
        return compAddrP;
    }

    public void setCompAddrP(String compAddrP) {
        this.compAddrP = compAddrP;
    }

    private String compAddrPe;

    @Basic
    @javax.persistence.Column(name = "COMP_ADDR_PE")
    public String getCompAddrPe() {
        return compAddrPe;
    }

    public void setCompAddrPe(String compAddrPe) {
        this.compAddrPe = compAddrPe;
    }

    private String compAddrTk;

    @Basic
    @javax.persistence.Column(name = "COMP_ADDR_TK")
    public String getCompAddrTk() {
        return compAddrTk;
    }

    public void setCompAddrTk(String compAddrTk) {
        this.compAddrTk = compAddrTk;
    }

    private Long compEbrBranch0Id;

    @Basic
    @javax.persistence.Column(name = "COMP_EBR_BRANCH0_ID")
    public Long getCompEbrBranch0Id() {
        return compEbrBranch0Id;
    }

    public void setCompEbrBranch0Id(Long compEbrBranch0Id) {
        this.compEbrBranch0Id = compEbrBranch0Id;
    }

    private Long compEbrBranchId;

    @Basic
    @javax.persistence.Column(name = "COMP_EBR_BRANCH_ID")
    public Long getCompEbrBranchId() {
        return compEbrBranchId;
    }

    public void setCompEbrBranchId(Long compEbrBranchId) {
        this.compEbrBranchId = compEbrBranchId;
    }

    private String empAmkaNumber;

    @Basic
    @javax.persistence.Column(name = "EMP_AMKA_NUMBER")
    public String getEmpAmkaNumber() {
        return empAmkaNumber;
    }

    public void setEmpAmkaNumber(String empAmkaNumber) {
        this.empAmkaNumber = empAmkaNumber;
    }

    private Long empInsuranceBureauCode;

    @Basic
    @javax.persistence.Column(name = "EMP_INSURANCE_BUREAU_CODE")
    public Long getEmpInsuranceBureauCode() {
        return empInsuranceBureauCode;
    }

    public void setEmpInsuranceBureauCode(Long empInsuranceBureauCode) {
        this.empInsuranceBureauCode = empInsuranceBureauCode;
    }

    private String empTaxNumber;

    @Basic
    @javax.persistence.Column(name = "EMP_TAX_NUMBER")
    public String getEmpTaxNumber() {
        return empTaxNumber;
    }

    public void setEmpTaxNumber(String empTaxNumber) {
        this.empTaxNumber = empTaxNumber;
    }

    private String empSurname;

    @Basic
    @javax.persistence.Column(name = "EMP_SURNAME")
    public String getEmpSurname() {
        return empSurname;
    }

    public void setEmpSurname(String empSurname) {
        this.empSurname = empSurname;
    }

    private String empName;

    @Basic
    @javax.persistence.Column(name = "EMP_NAME")
    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    private String empFathersName;

    @Basic
    @javax.persistence.Column(name = "EMP_FATHERS_NAME")
    public String getEmpFathersName() {
        return empFathersName;
    }

    public void setEmpFathersName(String empFathersName) {
        this.empFathersName = empFathersName;
    }

    private String empMothersName;

    /*@Basic
    @javax.persistence.Column(name = "EMP_MOTHERS_NAME")
    public String getEmpMothersName() {
        return empMothersName;
    }

    public void setEmpMothersName(String empMothersName) {
        this.empMothersName = empMothersName;
    }*/

    private String empSpecialty;

    @Basic
    @javax.persistence.Column(name = "EMP_SPECIALTY")
    public String getEmpSpecialty() {
        return empSpecialty;
    }

    public void setEmpSpecialty(String empSpecialty) {
        this.empSpecialty = empSpecialty;
    }

    private Integer empSexDesc;

    @Basic
    @javax.persistence.Column(name = "EMP_SEX_DESC")
    public Integer getEmpSexDesc() {
        return empSexDesc;
    }

    public void setEmpSexDesc(Integer empSexDesc) {
        this.empSexDesc = empSexDesc;
    }

    private Long empMaritalStatusCd;

    @Basic
    @javax.persistence.Column(name = "EMP_MARITAL_STATUS_CD")
    public Long getEmpMaritalStatusCd() {
        return empMaritalStatusCd;
    }

    public void setEmpMaritalStatusCd(Long empMaritalStatusCd) {
        this.empMaritalStatusCd = empMaritalStatusCd;
    }

    private String empAge;

    @Basic
    @javax.persistence.Column(name = "EMP_AGE")
    public String getEmpAge() {
        return empAge;
    }

    public void setEmpAge(String empAge) {
        this.empAge = empAge;
    }

    private Timestamp empRecruitmentDate;

    @Basic
    @javax.persistence.Column(name = "EMP_RECRUITMENT_DATE")
    public Timestamp getEmpRecruitmentDate() {
        return empRecruitmentDate;
    }

    public void setEmpRecruitmentDate(Timestamp empRecruitmentDate) {
        this.empRecruitmentDate = empRecruitmentDate;
    }

    private String empJobPositionCode;

    @Basic
    @javax.persistence.Column(name = "EMP_JOB_POSITION_CODE")
    public String getEmpJobPositionCode() {
        return empJobPositionCode;
    }

    public void setEmpJobPositionCode(String empJobPositionCode) {
        this.empJobPositionCode = empJobPositionCode;
    }

    private String empTimeCode;

    @Basic
    @javax.persistence.Column(name = "EMP_TIME_CODE")
    public String getEmpTimeCode() {
        return empTimeCode;
    }

    public void setEmpTimeCode(String empTimeCode) {
        this.empTimeCode = empTimeCode;
    }

    private String empAddr;

    @Basic
    @javax.persistence.Column(name = "EMP_ADDR")
    public String getEmpAddr() {
        return empAddr;
    }

    public void setEmpAddr(String empAddr) {
        this.empAddr = empAddr;
    }

    private String empAddrD;

    @Basic
    @javax.persistence.Column(name = "EMP_ADDR_D")
    public String getEmpAddrD() {
        return empAddrD;
    }

    public void setEmpAddrD(String empAddrD) {
        this.empAddrD = empAddrD;
    }

    private String empAddrK;

    @Basic
    @javax.persistence.Column(name = "EMP_ADDR_K")
    public String getEmpAddrK() {
        return empAddrK;
    }

    public void setEmpAddrK(String empAddrK) {
        this.empAddrK = empAddrK;
    }

    private String empAddrP;

    @Basic
    @javax.persistence.Column(name = "EMP_ADDR_P")
    public String getEmpAddrP() {
        return empAddrP;
    }

    public void setEmpAddrP(String empAddrP) {
        this.empAddrP = empAddrP;
    }

    private String empAddrPe;

    @Basic
    @javax.persistence.Column(name = "EMP_ADDR_PE")
    public String getEmpAddrPe() {
        return empAddrPe;
    }

    public void setEmpAddrPe(String empAddrPe) {
        this.empAddrPe = empAddrPe;
    }

    private String empAddrTk;

    @Basic
    @javax.persistence.Column(name = "EMP_ADDR_TK")
    public String getEmpAddrTk() {
        return empAddrTk;
    }

    public void setEmpAddrTk(String empAddrTk) {
        this.empAddrTk = empAddrTk;
    }

    private Long empCardType;

    @Basic
    @javax.persistence.Column(name = "EMP_CARD_TYPE")
    public Long getEmpCardType() {
        return empCardType;
    }

    public void setEmpCardType(Long empCardType) {
        this.empCardType = empCardType;
    }

    private String empCardNumber;

    @Basic
    @javax.persistence.Column(name = "EMP_CARD_NUMBER")
    public String getEmpCardNumber() {
        return empCardNumber;
    }

    public void setEmpCardNumber(String empCardNumber) {
        this.empCardNumber = empCardNumber;
    }

    private String empPhone;

    @Basic
    @javax.persistence.Column(name = "EMP_PHONE")
    public String getEmpPhone() {
        return empPhone;
    }

    public void setEmpPhone(String empPhone) {
        this.empPhone = empPhone;
    }

    private String empMobile;

    @Basic
    @javax.persistence.Column(name = "EMP_MOBILE")
    public String getEmpMobile() {
        return empMobile;
    }

    public void setEmpMobile(String empMobile) {
        this.empMobile = empMobile;
    }

    private Timestamp accidentDate;

    @Basic
    @javax.persistence.Column(name = "ACCIDENT_DATE")
    public Timestamp getAccidentDate() {
        return accidentDate;
    }

    public void setAccidentDate(Timestamp accidentDate) {
        this.accidentDate = accidentDate;
    }

    private String accidentTime;

    @Basic
    @javax.persistence.Column(name = "ACCIDENT_TIME")
    public String getAccidentTime() {
        return accidentTime;
    }

    public void setAccidentTime(String accidentTime) {
        this.accidentTime = accidentTime;
    }

    private String accidentAddr;

    @Basic
    @javax.persistence.Column(name = "ACCIDENT_ADDR")
    public String getAccidentAddr() {
        return accidentAddr;
    }

    public void setAccidentAddr(String accidentAddr) {
        this.accidentAddr = accidentAddr;
    }

    private String accidentAddrD;

    @Basic
    @javax.persistence.Column(name = "ACCIDENT_ADDR_D")
    public String getAccidentAddrD() {
        return accidentAddrD;
    }

    public void setAccidentAddrD(String accidentAddrD) {
        this.accidentAddrD = accidentAddrD;
    }

    private String accidentAddrK;

    @Basic
    @javax.persistence.Column(name = "ACCIDENT_ADDR_K")
    public String getAccidentAddrK() {
        return accidentAddrK;
    }

    public void setAccidentAddrK(String accidentAddrK) {
        this.accidentAddrK = accidentAddrK;
    }

    private String accidentAddrP;

    @Basic
    @javax.persistence.Column(name = "ACCIDENT_ADDR_P")
    public String getAccidentAddrP() {
        return accidentAddrP;
    }

    public void setAccidentAddrP(String accidentAddrP) {
        this.accidentAddrP = accidentAddrP;
    }

    private String accidentAddrPe;

    @Basic
    @javax.persistence.Column(name = "ACCIDENT_ADDR_PE")
    public String getAccidentAddrPe() {
        return accidentAddrPe;
    }

    public void setAccidentAddrPe(String accidentAddrPe) {
        this.accidentAddrPe = accidentAddrPe;
    }

    private String accidentAddrTk;

    @Basic
    @javax.persistence.Column(name = "ACCIDENT_ADDR_TK")
    public String getAccidentAddrTk() {
        return accidentAddrTk;
    }

    public void setAccidentAddrTk(String accidentAddrTk) {
        this.accidentAddrTk = accidentAddrTk;
    }

    private String accidentJobsComment;

    @Basic
    @javax.persistence.Column(name = "ACCIDENT_JOBS_COMMENT")
    public String getAccidentJobsComment() {
        return accidentJobsComment;
    }

    public void setAccidentJobsComment(String accidentJobsComment) {
        this.accidentJobsComment = accidentJobsComment;
    }

    private String accidentDescription;

    @Basic
    @javax.persistence.Column(name = "ACCIDENT_DESCRIPTION")
    public String getAccidentDescription() {
        return accidentDescription;
    }

    public void setAccidentDescription(String accidentDescription) {
        this.accidentDescription = accidentDescription;
    }

    private String compUserContactInfo;

    @Basic
    @javax.persistence.Column(name = "COMP_USER_CONTACT_INFO")
    public String getCompUserContactInfo() {
        return compUserContactInfo;
    }

    public void setCompUserContactInfo(String compUserContactInfo) {
        this.compUserContactInfo = compUserContactInfo;
    }

    private Integer trainingFlag;

    @Basic
    @javax.persistence.Column(name = "TRAINING_FLAG")
    public Integer getTrainingFlag() {
        return trainingFlag;
    }

    public void setTrainingFlag(Integer trainingFlag) {
        this.trainingFlag = trainingFlag;
    }

    private String witnesses;

    @Basic
    @javax.persistence.Column(name = "WITNESSES")
    public String getWitnesses() {
        return witnesses;
    }

    public void setWitnesses(String witnesses) {
        this.witnesses = witnesses;
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

    private Long docId;

    @Basic
    @javax.persistence.Column(name = "DOC_ID")
    public Long getDocId() {
        return docId;
    }

    public void setDocId(Long docId) {
        this.docId = docId;
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

    private Timestamp protDate;

    @Basic
    @javax.persistence.Column(name = "PROT_DATE")
    public Timestamp getProtDate() {
        return protDate;
    }

    public void setProtDate(Timestamp protDate) {
        this.protDate = protDate;
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

    private String submitTime;

    @Basic
    @javax.persistence.Column(name = "SUBMIT_TIME")
    public String getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(String submitTime) {
        this.submitTime = submitTime;
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

    private Integer reqStatus;

    @Basic
    @javax.persistence.Column(name = "REQ_STATUS")
    public Integer getReqStatus() {
        return reqStatus;
    }

    public void setReqStatus(Integer reqStatus) {
        this.reqStatus = reqStatus;
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

    private Long sepeDept;

    @Basic
    @javax.persistence.Column(name = "SEPE_DEPT")
    public Long getSepeDept() {
        return sepeDept;
    }

    public void setSepeDept(Long sepeDept) {
        this.sepeDept = sepeDept;
    }

    private Integer outdated;

    @Basic
    @javax.persistence.Column(name = "OUTDATED")
    public Integer getOutdated() {
        return outdated;
    }

    public void setOutdated(Integer outdated) {
        this.outdated = outdated;
    }

    private String empCitizenshipCd;

    @Basic
    @javax.persistence.Column(name = "EMP_CITIZENSHIP_CD")
    public String getEmpCitizenshipCd() {
        return empCitizenshipCd;
    }

    public void setEmpCitizenshipCd(String empCitizenshipCd) {
        this.empCitizenshipCd = empCitizenshipCd;
    }

    private String accidentPlace;

    @Basic
    @javax.persistence.Column(name = "ACCIDENT_PLACE")
    public String getAccidentPlace() {
        return accidentPlace;
    }

    public void setAccidentPlace(String accidentPlace) {
        this.accidentPlace = accidentPlace;
    }

    private Timestamp empBirthdate;

    @Basic
    @javax.persistence.Column(name = "EMP_BIRTHDATE")
    public Timestamp getEmpBirthdate() {
        return empBirthdate;
    }

    public void setEmpBirthdate(Timestamp empBirthdate) {
        this.empBirthdate = empBirthdate;
    }

    private String compAme;

    @Basic
    @javax.persistence.Column(name = "COMP_AME")
    public String getCompAme() {
        return compAme;
    }

    public void setCompAme(String compAme) {
        this.compAme = compAme;
    }

    private String accidentAffectedSeverity;

    @Basic
    @javax.persistence.Column(name = "ACCIDENT_AFFECTED_SEVERITY")
    public String getAccidentAffectedSeverity() {
        return accidentAffectedSeverity;
    }

    public void setAccidentAffectedSeverity(String accidentAffectedSeverity) {
        this.accidentAffectedSeverity = accidentAffectedSeverity;
    }

    private String accidentAffectedType;

    @Basic
    @javax.persistence.Column(name = "ACCIDENT_AFFECTED_TYPE")
    public String getAccidentAffectedType() {
        return accidentAffectedType;
    }

    public void setAccidentAffectedType(String accidentAffectedType) {
        this.accidentAffectedType = accidentAffectedType;
    }

    private String accidentWoundType;

    @Basic
    @javax.persistence.Column(name = "ACCIDENT_WOUND_TYPE")
    public String getAccidentWoundType() {
        return accidentWoundType;
    }

    public void setAccidentWoundType(String accidentWoundType) {
        this.accidentWoundType = accidentWoundType;
    }

    private String accidentBodyPart;

    @Basic
    @javax.persistence.Column(name = "ACCIDENT_BODY_PART")
    public String getAccidentBodyPart() {
        return accidentBodyPart;
    }

    public void setAccidentBodyPart(String accidentBodyPart) {
        this.accidentBodyPart = accidentBodyPart;
    }

    /////////////////////////////////////////////////
    // /*
    private Set<SpPtlCompanyAccidentWitn> companyAccidentWitnesses = new HashSet<SpPtlCompanyAccidentWitn>(0);

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "companyAccident", cascade = CascadeType.ALL, orphanRemoval = true) // SHOULD BE EQUAL TO ATTRIBUTE NAME OF OTHER CLASS!!!!
    public Set<SpPtlCompanyAccidentWitn> getCompanyAccidentWitnesses() {
        return this.companyAccidentWitnesses;
    }

    public void setCompanyAccidentWitnesses(Set<SpPtlCompanyAccidentWitn> companyAccidentWitnesses) {
        this.companyAccidentWitnesses = companyAccidentWitnesses;
    }

    // */
    /////////////////////////////////////////////////

    @Nullable
    private SpPtlCompanyAccidentWitnDTO[] witnessesObj;

    @Transient
    @Nullable
    public SpPtlCompanyAccidentWitnDTO[] getWitnessesObj() {
        return witnessesObj;
    }

    @Transient
    @Nullable
    public void setWitnessesObj(@Nullable SpPtlCompanyAccidentWitnDTO[] witnessesObj) {
        this.witnessesObj = witnessesObj;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpPtlCompanyAccident that = (SpPtlCompanyAccident) o;

        if (id != that.id) return false;
        if (accidentAddr != null ? !accidentAddr.equals(that.accidentAddr) : that.accidentAddr != null) return false;
        if (accidentAddrD != null ? !accidentAddrD.equals(that.accidentAddrD) : that.accidentAddrD != null)
            return false;
        if (accidentAddrK != null ? !accidentAddrK.equals(that.accidentAddrK) : that.accidentAddrK != null)
            return false;
        if (accidentAddrP != null ? !accidentAddrP.equals(that.accidentAddrP) : that.accidentAddrP != null)
            return false;
        if (accidentAddrPe != null ? !accidentAddrPe.equals(that.accidentAddrPe) : that.accidentAddrPe != null)
            return false;
        if (accidentAddrTk != null ? !accidentAddrTk.equals(that.accidentAddrTk) : that.accidentAddrTk != null)
            return false;
        if (accidentDate != null ? !accidentDate.equals(that.accidentDate) : that.accidentDate != null) return false;
        if (accidentDescription != null ? !accidentDescription.equals(that.accidentDescription) : that.accidentDescription != null)
            return false;
        if (accidentJobsComment != null ? !accidentJobsComment.equals(that.accidentJobsComment) : that.accidentJobsComment != null)
            return false;
        if (accidentPlace != null ? !accidentPlace.equals(that.accidentPlace) : that.accidentPlace != null)
            return false;
        if (accidentTime != null ? !accidentTime.equals(that.accidentTime) : that.accidentTime != null) return false;
        if (attachedDocId != null ? !attachedDocId.equals(that.attachedDocId) : that.attachedDocId != null)
            return false;
        if (caseId != null ? !caseId.equals(that.caseId) : that.caseId != null) return false;
        if (compAddr != null ? !compAddr.equals(that.compAddr) : that.compAddr != null) return false;
        if (compAddrD != null ? !compAddrD.equals(that.compAddrD) : that.compAddrD != null) return false;
        if (compAddrK != null ? !compAddrK.equals(that.compAddrK) : that.compAddrK != null) return false;
        if (compAddrP != null ? !compAddrP.equals(that.compAddrP) : that.compAddrP != null) return false;
        if (compAddrPe != null ? !compAddrPe.equals(that.compAddrPe) : that.compAddrPe != null) return false;
        if (compAddrTk != null ? !compAddrTk.equals(that.compAddrTk) : that.compAddrTk != null) return false;
        if (compAme != null ? !compAme.equals(that.compAme) : that.compAme != null) return false;
        if (compEbrBranch0Id != null ? !compEbrBranch0Id.equals(that.compEbrBranch0Id) : that.compEbrBranch0Id != null)
            return false;
        if (compEbrBranchId != null ? !compEbrBranchId.equals(that.compEbrBranchId) : that.compEbrBranchId != null)
            return false;
        if (compFullName != null ? !compFullName.equals(that.compFullName) : that.compFullName != null) return false;
        if (compNameIe != null ? !compNameIe.equals(that.compNameIe) : that.compNameIe != null) return false;
        if (compNameTe != null ? !compNameTe.equals(that.compNameTe) : that.compNameTe != null) return false;
        if (compPhone != null ? !compPhone.equals(that.compPhone) : that.compPhone != null) return false;
        if (compTaxNumber != null ? !compTaxNumber.equals(that.compTaxNumber) : that.compTaxNumber != null)
            return false;
        if (companyId != null ? !companyId.equals(that.companyId) : that.companyId != null) return false;
        if (docId != null ? !docId.equals(that.docId) : that.docId != null) return false;
        if (empAddr != null ? !empAddr.equals(that.empAddr) : that.empAddr != null) return false;
        if (empAddrD != null ? !empAddrD.equals(that.empAddrD) : that.empAddrD != null) return false;
        if (empAddrK != null ? !empAddrK.equals(that.empAddrK) : that.empAddrK != null) return false;
        if (empAddrP != null ? !empAddrP.equals(that.empAddrP) : that.empAddrP != null) return false;
        if (empAddrPe != null ? !empAddrPe.equals(that.empAddrPe) : that.empAddrPe != null) return false;
        if (empAddrTk != null ? !empAddrTk.equals(that.empAddrTk) : that.empAddrTk != null) return false;
        if (empAge != null ? !empAge.equals(that.empAge) : that.empAge != null) return false;
        if (empAmkaNumber != null ? !empAmkaNumber.equals(that.empAmkaNumber) : that.empAmkaNumber != null)
            return false;
        if (empBirthdate != null ? !empBirthdate.equals(that.empBirthdate) : that.empBirthdate != null) return false;
        if (empCardNumber != null ? !empCardNumber.equals(that.empCardNumber) : that.empCardNumber != null)
            return false;
        if (empCardType != null ? !empCardType.equals(that.empCardType) : that.empCardType != null) return false;
        if (empCitizenshipCd != null ? !empCitizenshipCd.equals(that.empCitizenshipCd) : that.empCitizenshipCd != null)
            return false;
        if (empInsuranceBureauCode != null ? !empInsuranceBureauCode.equals(that.empInsuranceBureauCode) : that.empInsuranceBureauCode != null)
            return false;
        if (empJobPositionCode != null ? !empJobPositionCode.equals(that.empJobPositionCode) : that.empJobPositionCode != null)
            return false;
        if (empMaritalStatusCd != null ? !empMaritalStatusCd.equals(that.empMaritalStatusCd) : that.empMaritalStatusCd != null)
            return false;
        if (empMobile != null ? !empMobile.equals(that.empMobile) : that.empMobile != null) return false;
        if (empName != null ? !empName.equals(that.empName) : that.empName != null) return false;
        if (empFathersName != null ? !empFathersName.equals(that.empFathersName) : that.empFathersName != null) return false;
        if (empPhone != null ? !empPhone.equals(that.empPhone) : that.empPhone != null) return false;
        if (empRecruitmentDate != null ? !empRecruitmentDate.equals(that.empRecruitmentDate) : that.empRecruitmentDate != null)
            return false;
        if (empSexDesc != null ? !empSexDesc.equals(that.empSexDesc) : that.empSexDesc != null) return false;
        if (empSpecialty != null ? !empSpecialty.equals(that.empSpecialty) : that.empSpecialty != null) return false;
        if (empSurname != null ? !empSurname.equals(that.empSurname) : that.empSurname != null) return false;
        if (empTaxNumber != null ? !empTaxNumber.equals(that.empTaxNumber) : that.empTaxNumber != null) return false;
        if (empTimeCode != null ? !empTimeCode.equals(that.empTimeCode) : that.empTimeCode != null) return false;
        if (outdated != null ? !outdated.equals(that.outdated) : that.outdated != null) return false;
        if (protDate != null ? !protDate.equals(that.protDate) : that.protDate != null) return false;
        if (protNo != null ? !protNo.equals(that.protNo) : that.protNo != null) return false;
        if (protYear != null ? !protYear.equals(that.protYear) : that.protYear != null) return false;
        if (reqStatus != null ? !reqStatus.equals(that.reqStatus) : that.reqStatus != null) return false;
        if (sepeDept != null ? !sepeDept.equals(that.sepeDept) : that.sepeDept != null) return false;
        if (subStatus != null ? !subStatus.equals(that.subStatus) : that.subStatus != null) return false;
        if (submitTime != null ? !submitTime.equals(that.submitTime) : that.submitTime != null) return false;
        if (trainingFlag != null ? !trainingFlag.equals(that.trainingFlag) : that.trainingFlag != null) return false;
        if (witnesses != null ? !witnesses.equals(that.witnesses) : that.witnesses != null) return false;
        if (accidentAffectedSeverity != null ? !accidentAffectedSeverity.equals(that.accidentAffectedSeverity) : that.accidentAffectedSeverity != null) return false;
        if (accidentAffectedType != null ? !accidentAffectedType.equals(that.accidentAffectedType) : that.accidentAffectedType != null) return false;
        if (compUserContactInfo != null ? !compUserContactInfo.equals(that.compUserContactInfo) : that.compUserContactInfo != null) return false;
        if (accidentWoundType != null ? !accidentWoundType.equals(that.accidentWoundType) : that.accidentWoundType != null) return false;
        if (accidentBodyPart != null ? !accidentBodyPart.equals(that.accidentBodyPart) : that.accidentBodyPart != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (companyId != null ? companyId.hashCode() : 0);
        result = 31 * result + (compTaxNumber != null ? compTaxNumber.hashCode() : 0);
        result = 31 * result + (compFullName != null ? compFullName.hashCode() : 0);
        result = 31 * result + (compPhone != null ? compPhone.hashCode() : 0);
        result = 31 * result + (compNameTe != null ? compNameTe.hashCode() : 0);
        result = 31 * result + (compNameIe != null ? compNameIe.hashCode() : 0);
        result = 31 * result + (compAddr != null ? compAddr.hashCode() : 0);
        result = 31 * result + (compAddrD != null ? compAddrD.hashCode() : 0);
        result = 31 * result + (compAddrK != null ? compAddrK.hashCode() : 0);
        result = 31 * result + (compAddrP != null ? compAddrP.hashCode() : 0);
        result = 31 * result + (compAddrPe != null ? compAddrPe.hashCode() : 0);
        result = 31 * result + (compAddrTk != null ? compAddrTk.hashCode() : 0);
        result = 31 * result + (compEbrBranch0Id != null ? compEbrBranch0Id.hashCode() : 0);
        result = 31 * result + (compEbrBranchId != null ? compEbrBranchId.hashCode() : 0);
        result = 31 * result + (empAmkaNumber != null ? empAmkaNumber.hashCode() : 0);
        result = 31 * result + (empInsuranceBureauCode != null ? empInsuranceBureauCode.hashCode() : 0);
        result = 31 * result + (empTaxNumber != null ? empTaxNumber.hashCode() : 0);
        result = 31 * result + (empSurname != null ? empSurname.hashCode() : 0);
        result = 31 * result + (empName != null ? empName.hashCode() : 0);
        result = 31 * result + (empFathersName != null ? empFathersName.hashCode() : 0);
        result = 31 * result + (empSpecialty != null ? empSpecialty.hashCode() : 0);
        result = 31 * result + (empSexDesc != null ? empSexDesc.hashCode() : 0);
        result = 31 * result + (empMaritalStatusCd != null ? empMaritalStatusCd.hashCode() : 0);
        result = 31 * result + (empAge != null ? empAge.hashCode() : 0);
        result = 31 * result + (empRecruitmentDate != null ? empRecruitmentDate.hashCode() : 0);
        result = 31 * result + (empJobPositionCode != null ? empJobPositionCode.hashCode() : 0);
        result = 31 * result + (empTimeCode != null ? empTimeCode.hashCode() : 0);
        result = 31 * result + (empAddr != null ? empAddr.hashCode() : 0);
        result = 31 * result + (empAddrD != null ? empAddrD.hashCode() : 0);
        result = 31 * result + (empAddrK != null ? empAddrK.hashCode() : 0);
        result = 31 * result + (empAddrP != null ? empAddrP.hashCode() : 0);
        result = 31 * result + (empAddrPe != null ? empAddrPe.hashCode() : 0);
        result = 31 * result + (empAddrTk != null ? empAddrTk.hashCode() : 0);
        result = 31 * result + (empCardType != null ? empCardType.hashCode() : 0);
        result = 31 * result + (empCardNumber != null ? empCardNumber.hashCode() : 0);
        result = 31 * result + (empPhone != null ? empPhone.hashCode() : 0);
        result = 31 * result + (empMobile != null ? empMobile.hashCode() : 0);
        result = 31 * result + (accidentDate != null ? accidentDate.hashCode() : 0);
        result = 31 * result + (accidentTime != null ? accidentTime.hashCode() : 0);
        result = 31 * result + (accidentAddr != null ? accidentAddr.hashCode() : 0);
        result = 31 * result + (accidentAddrD != null ? accidentAddrD.hashCode() : 0);
        result = 31 * result + (accidentAddrK != null ? accidentAddrK.hashCode() : 0);
        result = 31 * result + (accidentAddrP != null ? accidentAddrP.hashCode() : 0);
        result = 31 * result + (accidentAddrPe != null ? accidentAddrPe.hashCode() : 0);
        result = 31 * result + (accidentAddrTk != null ? accidentAddrTk.hashCode() : 0);
        result = 31 * result + (accidentJobsComment != null ? accidentJobsComment.hashCode() : 0);
        result = 31 * result + (accidentDescription != null ? accidentDescription.hashCode() : 0);
        result = 31 * result + (trainingFlag != null ? trainingFlag.hashCode() : 0);
        result = 31 * result + (witnesses != null ? witnesses.hashCode() : 0);
        result = 31 * result + (attachedDocId != null ? attachedDocId.hashCode() : 0);
        result = 31 * result + (docId != null ? docId.hashCode() : 0);
        result = 31 * result + (protNo != null ? protNo.hashCode() : 0);
        result = 31 * result + (protDate != null ? protDate.hashCode() : 0);
        result = 31 * result + (protYear != null ? protYear.hashCode() : 0);
        result = 31 * result + (submitTime != null ? submitTime.hashCode() : 0);
        result = 31 * result + (caseId != null ? caseId.hashCode() : 0);
        result = 31 * result + (reqStatus != null ? reqStatus.hashCode() : 0);
        result = 31 * result + (subStatus != null ? subStatus.hashCode() : 0);
        result = 31 * result + (sepeDept != null ? sepeDept.hashCode() : 0);
        result = 31 * result + (outdated != null ? outdated.hashCode() : 0);
        result = 31 * result + (empCitizenshipCd != null ? empCitizenshipCd.hashCode() : 0);
        result = 31 * result + (accidentPlace != null ? accidentPlace.hashCode() : 0);
        result = 31 * result + (empBirthdate != null ? empBirthdate.hashCode() : 0);
        result = 31 * result + (compAme != null ? compAme.hashCode() : 0);
        result = 31 * result + (accidentAffectedSeverity != null ? accidentAffectedSeverity.hashCode() : 0);
        result = 31 * result + (accidentAffectedType != null ? accidentAffectedType.hashCode() : 0);
        result = 31 * result + (compUserContactInfo != null ? compUserContactInfo.hashCode() : 0);
        result = 31 * result + (accidentWoundType != null ? accidentWoundType.hashCode() : 0);
        result = 31 * result + (accidentBodyPart != null ? accidentBodyPart.hashCode() : 0);
        return result;
    }
}
