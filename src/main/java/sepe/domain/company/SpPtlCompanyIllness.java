package sepe.domain.company;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Nick on 4/23/2015.
 */

@Entity
@Table(name = "SP_PTL_COMPANY_ILLNESS", schema = "SP_PTL", catalog = "")
public class SpPtlCompanyIllness {
    private Long id;
    private Long companyId;
    private String compTaxNumber;
    private String compFullName;
    private String compPhone;
    private String compAddr;
    private String compAddrD;
    private String compAddrK;
    private String compAddrP;
    private String compAddrPe;
    private String compAddrTk;
    private Long compEbrBranch0Id;
    private Long compEbrBranchId;
    private String empAmkaNumber;
    private Long empInsuranceBureauCode;
    private String empTaxNumber;
    private String empSurname;
    private String empName;
    private String empSpecialty;
    private Integer empSexDesc;
    private Long empMaritalStatusCd;
    private String empAge;
    private Timestamp empRecruitmentDate;
    private String empTimeCode;
    private String empAddr;
    private String empAddrD;
    private String empAddrK;
    private String empAddrP;
    private String empAddrPe;
    private String empAddrTk;
    private Long empCardType;
    private String empCardNumber;
    private String empPhone;
    private String empMobile;
    private Timestamp diagnosisDate;
    private Timestamp prDiagnosisDate;
    private String illnessComments;
    private String elementsComments;
    private String infestationComments;
    private String factorComments;
    private String comments;
    private Long attachedDocId;
    private Long docId;
    private String protNo;
    private Timestamp protDate;
    private Integer protYear;
    private String submitTime;
    private Long caseId;
    private Integer reqStatus;
    private Integer subStatus;
    private Long sepeDept;
    private String empCitizenshipCd;
    private Timestamp empBirthdate;
    private String compAme;

    @Id
    @GeneratedValue
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "COMPANY_ID")
    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    @Basic
    @Column(name = "COMP_TAX_NUMBER")
    public String getCompTaxNumber() {
        return compTaxNumber;
    }

    public void setCompTaxNumber(String compTaxNumber) {
        this.compTaxNumber = compTaxNumber;
    }

    @Basic
    @Column(name = "COMP_FULL_NAME")
    public String getCompFullName() {
        return compFullName;
    }

    public void setCompFullName(String compFullName) {
        this.compFullName = compFullName;
    }

    @Basic
    @Column(name = "COMP_PHONE")
    public String getCompPhone() {
        return compPhone;
    }

    public void setCompPhone(String compPhone) {
        this.compPhone = compPhone;
    }

    @Basic
    @Column(name = "COMP_ADDR")
    public String getCompAddr() {
        return compAddr;
    }

    public void setCompAddr(String compAddr) {
        this.compAddr = compAddr;
    }

    @Basic
    @Column(name = "COMP_ADDR_D")
    public String getCompAddrD() {
        return compAddrD;
    }

    public void setCompAddrD(String compAddrD) {
        this.compAddrD = compAddrD;
    }

    @Basic
    @Column(name = "COMP_ADDR_K")
    public String getCompAddrK() {
        return compAddrK;
    }

    public void setCompAddrK(String compAddrK) {
        this.compAddrK = compAddrK;
    }

    @Basic
    @Column(name = "COMP_ADDR_P")
    public String getCompAddrP() {
        return compAddrP;
    }

    public void setCompAddrP(String compAddrP) {
        this.compAddrP = compAddrP;
    }

    @Basic
    @Column(name = "COMP_ADDR_PE")
    public String getCompAddrPe() {
        return compAddrPe;
    }

    public void setCompAddrPe(String compAddrPe) {
        this.compAddrPe = compAddrPe;
    }

    @Basic
    @Column(name = "COMP_ADDR_TK")
    public String getCompAddrTk() {
        return compAddrTk;
    }

    public void setCompAddrTk(String compAddrTk) {
        this.compAddrTk = compAddrTk;
    }

    @Basic
    @Column(name = "COMP_EBR_BRANCH0_ID")
    public Long getCompEbrBranch0Id() {
        return compEbrBranch0Id;
    }

    public void setCompEbrBranch0Id(Long compEbrBranch0Id) {
        this.compEbrBranch0Id = compEbrBranch0Id;
    }

    @Basic
    @Column(name = "COMP_EBR_BRANCH_ID")
    public Long getCompEbrBranchId() {
        return compEbrBranchId;
    }

    public void setCompEbrBranchId(Long compEbrBranchId) {
        this.compEbrBranchId = compEbrBranchId;
    }

    @Basic
    @Column(name = "EMP_AMKA_NUMBER")
    public String getEmpAmkaNumber() {
        return empAmkaNumber;
    }

    public void setEmpAmkaNumber(String empAmkaNumber) {
        this.empAmkaNumber = empAmkaNumber;
    }

    @Basic
    @Column(name = "EMP_INSURANCE_BUREAU_CODE")
    public Long getEmpInsuranceBureauCode() {
        return empInsuranceBureauCode;
    }

    public void setEmpInsuranceBureauCode(Long empInsuranceBureauCode) {
        this.empInsuranceBureauCode = empInsuranceBureauCode;
    }

    @Basic
    @Column(name = "EMP_TAX_NUMBER")
    public String getEmpTaxNumber() {
        return empTaxNumber;
    }

    public void setEmpTaxNumber(String empTaxNumber) {
        this.empTaxNumber = empTaxNumber;
    }

    @Basic
    @Column(name = "EMP_SURNAME")
    public String getEmpSurname() {
        return empSurname;
    }

    public void setEmpSurname(String empSurname) {
        this.empSurname = empSurname;
    }

    @Basic
    @Column(name = "EMP_NAME")
    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    @Basic
    @Column(name = "EMP_SPECIALTY")
    public String getEmpSpecialty() {
        return empSpecialty;
    }

    public void setEmpSpecialty(String empSpecialty) {
        this.empSpecialty = empSpecialty;
    }

    @Basic
    @Column(name = "EMP_SEX_DESC")
    public Integer getEmpSexDesc() {
        return empSexDesc;
    }

    public void setEmpSexDesc(Integer empSexDesc) {
        this.empSexDesc = empSexDesc;
    }

    @Basic
    @javax.persistence.Column(name = "EMP_MARITAL_STATUS_CD")
    public Long getEmpMaritalStatusCd() {
        return empMaritalStatusCd;
    }

    public void setEmpMaritalStatusCd(Long empMaritalStatusCd) {
        this.empMaritalStatusCd = empMaritalStatusCd;
    }

    @Basic
    @Column(name = "EMP_AGE")
    public String getEmpAge() {
        return empAge;
    }

    public void setEmpAge(String empAge) {
        this.empAge = empAge;
    }

    @Basic
    @Column(name = "EMP_RECRUITMENT_DATE")
    public Timestamp getEmpRecruitmentDate() {
        return empRecruitmentDate;
    }

    public void setEmpRecruitmentDate(Timestamp empRecruitmentDate) {
        this.empRecruitmentDate = empRecruitmentDate;
    }

    @Basic
    @Column(name = "EMP_TIME_CODE")
    public String getEmpTimeCode() {
        return empTimeCode;
    }

    public void setEmpTimeCode(String empTimeCode) {
        this.empTimeCode = empTimeCode;
    }

    @Basic
    @Column(name = "EMP_ADDR")
    public String getEmpAddr() {
        return empAddr;
    }

    public void setEmpAddr(String empAddr) {
        this.empAddr = empAddr;
    }

    @Basic
    @Column(name = "EMP_ADDR_D")
    public String getEmpAddrD() {
        return empAddrD;
    }

    public void setEmpAddrD(String empAddrD) {
        this.empAddrD = empAddrD;
    }

    @Basic
    @Column(name = "EMP_ADDR_K")
    public String getEmpAddrK() {
        return empAddrK;
    }

    public void setEmpAddrK(String empAddrK) {
        this.empAddrK = empAddrK;
    }

    @Basic
    @Column(name = "EMP_ADDR_P")
    public String getEmpAddrP() {
        return empAddrP;
    }

    public void setEmpAddrP(String empAddrP) {
        this.empAddrP = empAddrP;
    }

    @Basic
    @Column(name = "EMP_ADDR_PE")
    public String getEmpAddrPe() {
        return empAddrPe;
    }

    public void setEmpAddrPe(String empAddrPe) {
        this.empAddrPe = empAddrPe;
    }

    @Basic
    @Column(name = "EMP_ADDR_TK")
    public String getEmpAddrTk() {
        return empAddrTk;
    }

    public void setEmpAddrTk(String empAddrTk) {
        this.empAddrTk = empAddrTk;
    }

    @Basic
    @Column(name = "EMP_CARD_TYPE")
    public Long getEmpCardType() {
        return empCardType;
    }

    public void setEmpCardType(Long empCardType) {
        this.empCardType = empCardType;
    }

    @Basic
    @Column(name = "EMP_CARD_NUMBER")
    public String getEmpCardNumber() {
        return empCardNumber;
    }

    public void setEmpCardNumber(String empCardNumber) {
        this.empCardNumber = empCardNumber;
    }

    @Basic
    @Column(name = "EMP_PHONE")
    public String getEmpPhone() {
        return empPhone;
    }

    public void setEmpPhone(String empPhone) {
        this.empPhone = empPhone;
    }

    @Basic
    @Column(name = "EMP_MOBILE")
    public String getEmpMobile() {
        return empMobile;
    }

    public void setEmpMobile(String empMobile) {
        this.empMobile = empMobile;
    }

    @Basic
    @Column(name = "DIAGNOSIS_DATE")
    public Timestamp getDiagnosisDate() {
        return diagnosisDate;
    }

    public void setDiagnosisDate(Timestamp diagnosisDate) {
        this.diagnosisDate = diagnosisDate;
    }

    @Basic
    @Column(name = "PR_DIAGNOSIS_DATE")
    public Timestamp getPrDiagnosisDate() {
        return prDiagnosisDate;
    }

    public void setPrDiagnosisDate(Timestamp prDiagnosisDate) {
        this.prDiagnosisDate = prDiagnosisDate;
    }

    @Basic
    @Column(name = "ILLNESS_COMMENTS")
    public String getIllnessComments() {
        return illnessComments;
    }

    public void setIllnessComments(String illnessComments) {
        this.illnessComments = illnessComments;
    }

    @Basic
    @Column(name = "ELEMENTS_COMMENTS")
    public String getElementsComments() {
        return elementsComments;
    }

    public void setElementsComments(String elementsComments) {
        this.elementsComments = elementsComments;
    }

    @Basic
    @Column(name = "INFESTATION_COMMENTS")
    public String getInfestationComments() {
        return infestationComments;
    }

    public void setInfestationComments(String infestationComments) {
        this.infestationComments = infestationComments;
    }

    @Basic
    @Column(name = "FACTOR_COMMENTS")
    public String getFactorComments() {
        return factorComments;
    }

    public void setFactorComments(String factorComments) {
        this.factorComments = factorComments;
    }

    @Basic
    @Column(name = "COMMENTS")
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Basic
    @Column(name = "ATTACHED_DOC_ID")
    public Long getAttachedDocId() {
        return attachedDocId;
    }

    public void setAttachedDocId(Long attachedDocId) {
        this.attachedDocId = attachedDocId;
    }

    @Basic
    @Column(name = "DOC_ID")
    public Long getDocId() {
        return docId;
    }

    public void setDocId(Long docId) {
        this.docId = docId;
    }

    @Basic
    @Column(name = "PROT_NO")
    public String getProtNo() {
        return protNo;
    }

    public void setProtNo(String protNo) {
        this.protNo = protNo;
    }

    @Basic
    @Column(name = "PROT_DATE")
    public Timestamp getProtDate() {
        return protDate;
    }

    public void setProtDate(Timestamp protDate) {
        this.protDate = protDate;
    }

    @Basic
    @Column(name = "PROT_YEAR")
    public Integer getProtYear() {
        return protYear;
    }

    public void setProtYear(Integer protYear) {
        this.protYear = protYear;
    }

    @Basic
    @Column(name = "SUBMIT_TIME")
    public String getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(String submitTime) {
        this.submitTime = submitTime;
    }

    @Basic
    @Column(name = "CASE_ID")
    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }

    @Basic
    @Column(name = "REQ_STATUS")
    public Integer getReqStatus() {
        return reqStatus;
    }

    public void setReqStatus(Integer reqStatus) {
        this.reqStatus = reqStatus;
    }

    @Basic
    @Column(name = "SUB_STATUS")
    public Integer getSubStatus() {
        return subStatus;
    }

    public void setSubStatus(Integer subStatus) {
        this.subStatus = subStatus;
    }

    @Basic
    @Column(name = "SEPE_DEPT")
    public Long getSepeDept() {
        return sepeDept;
    }

    public void setSepeDept(Long sepeDept) {
        this.sepeDept = sepeDept;
    }

    @Basic
    @Column(name = "EMP_CITIZENSHIP_CD")
    public String getEmpCitizenshipCd() {
        return empCitizenshipCd;
    }

    public void setEmpCitizenshipCd(String empCitizenshipCd) {
        this.empCitizenshipCd = empCitizenshipCd;
    }

    @Basic
    @Column(name = "EMP_BIRTHDATE")
    public Timestamp getEmpBirthdate() {
        return empBirthdate;
    }

    public void setEmpBirthdate(Timestamp empBirthdate) {
        this.empBirthdate = empBirthdate;
    }

    @Basic
    @Column(name = "COMP_AME")
    public String getCompAme() {
        return compAme;
    }

    public void setCompAme(String compAme) {
        this.compAme = compAme;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpPtlCompanyIllness that = (SpPtlCompanyIllness) o;

        if (id != that.id) return false;
        if (attachedDocId != null ? !attachedDocId.equals(that.attachedDocId) : that.attachedDocId != null)
            return false;
        if (caseId != null ? !caseId.equals(that.caseId) : that.caseId != null) return false;
        if (comments != null ? !comments.equals(that.comments) : that.comments != null) return false;
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
        if (compPhone != null ? !compPhone.equals(that.compPhone) : that.compPhone != null) return false;
        if (compTaxNumber != null ? !compTaxNumber.equals(that.compTaxNumber) : that.compTaxNumber != null)
            return false;
        if (companyId != null ? !companyId.equals(that.companyId) : that.companyId != null) return false;
        if (diagnosisDate != null ? !diagnosisDate.equals(that.diagnosisDate) : that.diagnosisDate != null)
            return false;
        if (docId != null ? !docId.equals(that.docId) : that.docId != null) return false;
        if (elementsComments != null ? !elementsComments.equals(that.elementsComments) : that.elementsComments != null)
            return false;
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
        if (empMaritalStatusCd != null ? !empMaritalStatusCd.equals(that.empMaritalStatusCd) : that.empMaritalStatusCd != null)
            return false;
        if (empMobile != null ? !empMobile.equals(that.empMobile) : that.empMobile != null) return false;
        if (empName != null ? !empName.equals(that.empName) : that.empName != null) return false;
        if (empPhone != null ? !empPhone.equals(that.empPhone) : that.empPhone != null) return false;
        if (empRecruitmentDate != null ? !empRecruitmentDate.equals(that.empRecruitmentDate) : that.empRecruitmentDate != null)
            return false;
        if (empSexDesc != null ? !empSexDesc.equals(that.empSexDesc) : that.empSexDesc != null) return false;
        if (empSpecialty != null ? !empSpecialty.equals(that.empSpecialty) : that.empSpecialty != null) return false;
        if (empSurname != null ? !empSurname.equals(that.empSurname) : that.empSurname != null) return false;
        if (empTaxNumber != null ? !empTaxNumber.equals(that.empTaxNumber) : that.empTaxNumber != null) return false;
        if (empTimeCode != null ? !empTimeCode.equals(that.empTimeCode) : that.empTimeCode != null) return false;
        if (factorComments != null ? !factorComments.equals(that.factorComments) : that.factorComments != null)
            return false;
        if (illnessComments != null ? !illnessComments.equals(that.illnessComments) : that.illnessComments != null)
            return false;
        if (infestationComments != null ? !infestationComments.equals(that.infestationComments) : that.infestationComments != null)
            return false;
        if (prDiagnosisDate != null ? !prDiagnosisDate.equals(that.prDiagnosisDate) : that.prDiagnosisDate != null)
            return false;
        if (protDate != null ? !protDate.equals(that.protDate) : that.protDate != null) return false;
        if (protNo != null ? !protNo.equals(that.protNo) : that.protNo != null) return false;
        if (protYear != null ? !protYear.equals(that.protYear) : that.protYear != null) return false;
        if (reqStatus != null ? !reqStatus.equals(that.reqStatus) : that.reqStatus != null) return false;
        if (sepeDept != null ? !sepeDept.equals(that.sepeDept) : that.sepeDept != null) return false;
        if (subStatus != null ? !subStatus.equals(that.subStatus) : that.subStatus != null) return false;
        if (submitTime != null ? !submitTime.equals(that.submitTime) : that.submitTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (companyId != null ? companyId.hashCode() : 0);
        result = 31 * result + (compTaxNumber != null ? compTaxNumber.hashCode() : 0);
        result = 31 * result + (compFullName != null ? compFullName.hashCode() : 0);
        result = 31 * result + (compPhone != null ? compPhone.hashCode() : 0);
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
        result = 31 * result + (empSpecialty != null ? empSpecialty.hashCode() : 0);
        result = 31 * result + (empSexDesc != null ? empSexDesc.hashCode() : 0);
        result = 31 * result + (empMaritalStatusCd != null ? empMaritalStatusCd.hashCode() : 0);
        result = 31 * result + (empAge != null ? empAge.hashCode() : 0);
        result = 31 * result + (empRecruitmentDate != null ? empRecruitmentDate.hashCode() : 0);
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
        result = 31 * result + (diagnosisDate != null ? diagnosisDate.hashCode() : 0);
        result = 31 * result + (prDiagnosisDate != null ? prDiagnosisDate.hashCode() : 0);
        result = 31 * result + (illnessComments != null ? illnessComments.hashCode() : 0);
        result = 31 * result + (elementsComments != null ? elementsComments.hashCode() : 0);
        result = 31 * result + (infestationComments != null ? infestationComments.hashCode() : 0);
        result = 31 * result + (factorComments != null ? factorComments.hashCode() : 0);
        result = 31 * result + (comments != null ? comments.hashCode() : 0);
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
        result = 31 * result + (empCitizenshipCd != null ? empCitizenshipCd.hashCode() : 0);
        result = 31 * result + (empBirthdate != null ? empBirthdate.hashCode() : 0);
        result = 31 * result + (compAme != null ? compAme.hashCode() : 0);
        return result;
    }
}
