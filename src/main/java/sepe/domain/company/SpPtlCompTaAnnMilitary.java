package sepe.domain.company;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Dimitris on 19/6/2018.
 */
@Entity
@Table(name = "SP_PTL_COMP_TA_ANN_MILITARY", schema = "SP_PTL", catalog = "")
public class SpPtlCompTaAnnMilitary {
    private Long id;
    private Long companyId;
    private String reprFirstname;
    private String reprLastname;
    private String reprPhone;
    private String reprMobile;
    private Long requestTypeId;
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
    private String ta_firstname;
    private String ta_lastname;
    private String ta_fathername;
    private Long ta_type;
    private Long ta_categ;
    private Long ta_branch;
    private String ta_speciality;
    private String ta_specialityOther;
    private  String ta_rank;

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
    @Column(name = "REPR_FIRSTNAME")
    public String getReprFirstname() {
        return reprFirstname;
    }

    public void setReprFirstname(String reprFirstname) {
        this.reprFirstname = reprFirstname;
    }

    @Basic
    @Column(name = "REPR_LASTNAME")
    public String getReprLastname() {
        return reprLastname;
    }

    public void setReprLastname(String reprLastname) {
        this.reprLastname = reprLastname;
    }

    @Basic
    @Column(name = "REPR_PHONE")
    public String getReprPhone() {
        return reprPhone;
    }

    public void setReprPhone(String reprPhone) {
        this.reprPhone = reprPhone;
    }

    @Basic
    @Column(name = "REPR_MOBILE")
    public String getReprMobile() {
        return reprMobile;
    }

    public void setReprMobile(String reprMobile) {
        this.reprMobile = reprMobile;
    }

    @Basic
    @Column(name = "REQUEST_TYPE_ID")
    public Long getRequestTypeId() {
        return requestTypeId;
    }

    public void setRequestTypeId(Long requestTypeId) {
        this.requestTypeId = requestTypeId;
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

    private Long reqSubject;

    @Basic
    @Column(name = "REQ_SUBJECT", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getReqSubject() {
        return reqSubject;
    }

    public void setReqSubject(Long reqSubject) {
        this.reqSubject = reqSubject;
    }

    private Long branch0Id;

    @Basic
    @Column(name = "BRANCH0_ID")
    public Long getBranch0Id() {
        return branch0Id;
    }

    public void setBranch0Id(Long branch0Id) {
        this.branch0Id = branch0Id;
    }

    private Long branch1Id;

    @Basic
    @Column(name = "BRANCH1_ID")
    public Long getBranch1Id() {
        return branch1Id;
    }

    public void setBranch1Id(Long branch1Id) {
        this.branch1Id = branch1Id;
    }

    private String inspAddr;

    @Basic
    @Column(name = "INSP_ADDR")
    public String getInspAddr() {
        return inspAddr;
    }

    public void setInspAddr(String inspAddr) {
        this.inspAddr = inspAddr;
    }

    private String inspAddrD;

    @Basic
    @Column(name = "INSP_ADDR_D")
    public String getInspAddrD() {
        return inspAddrD;
    }

    public void setInspAddrD(String inspAddrD) {
        this.inspAddrD = inspAddrD;
    }

    private String inspAddrK;

    @Basic
    @Column(name = "INSP_ADDR_K")
    public String getInspAddrK() {
        return inspAddrK;
    }

    public void setInspAddrK(String inspAddrK) {
        this.inspAddrK = inspAddrK;
    }

    private String inspAddrP;

    @Basic
    @Column(name = "INSP_ADDR_P")
    public String getInspAddrP() {
        return inspAddrP;
    }

    public void setInspAddrP(String inspAddrP) {
        this.inspAddrP = inspAddrP;
    }

    private String inspAddrPe;

    @Basic
    @Column(name = "INSP_ADDR_PE")
    public String getInspAddrPe() {
        return inspAddrPe;
    }

    public void setInspAddrPe(String inspAddrPe) {
        this.inspAddrPe = inspAddrPe;
    }

    private String inspAddrTk;

    @Basic
    @Column(name = "INSP_ADDR_TK")
    public String getInspAddrTk() {
        return inspAddrTk;
    }

    public void setInspAddrTk(String inspAddrTk) {
        this.inspAddrTk = inspAddrTk;
    }

    private String complAddressDesc;

    @Basic
    @Column(name = "COMPL_ADDRESS_DESC", nullable = true, insertable = true, updatable = true, length = 300)
    public String getComplAddressDesc() {
        return complAddressDesc;
    }

    private Long answerPdfDocId;

    @Basic
    @Column(name = "ANSWER_PDF_DOC_ID")
    public Long getAnswerPdfDocId() {
        return answerPdfDocId;
    }

    public void setAnswerPdfDocId(Long answerPdfDocId) {
        this.answerPdfDocId = answerPdfDocId;
    }

    private String answerPdfProtNo;

    @Basic
    @Column(name = "ANSWER_PDF_PROT_NO")
    public String getAnswerPdfProtNo() {
        return answerPdfProtNo;
    }

    public void setAnswerPdfProtNo(String answerPdfProtNo) {
        this.answerPdfProtNo = answerPdfProtNo;
    }

    private Integer answerPdfProtYear;

    @Basic
    @Column(name = "ANSWER_PDF_PROT_YEAR")
    public Integer getAnswerPdfProtYear() {
        return answerPdfProtYear;
    }

    @Basic
    @Column(name = "TECH_FIRSTNAME")
    public String getTa_firstname() {
        return ta_firstname;
    }

    public void setTa_firstname(String ta_firstname) {
        this.ta_firstname = ta_firstname;
    }

    @Basic
    @Column(name = "TECH_LASTNAME")
    public String getTa_lastname() {
        return ta_lastname;
    }

    public void setTa_lastname(String ta_lastname) {
        this.ta_lastname = ta_lastname;
    }

    @Basic
    @Column(name = "TECH_FATHERNAME")
    public String getTa_fathername() {
        return ta_fathername;
    }

    public void setTa_fathername(String ta_fathername) {
        this.ta_fathername = ta_fathername;
    }

    @Basic
    @Column(name = "TECH_TYPE")
    public Long getTa_type() {
        return ta_type;
    }

    public void setTa_type(Long ta_type) {
        this.ta_type = ta_type;
    }

    @Basic
    @Column(name = "MIL_CATEGORY")
    public Long getTa_categ() {
        return ta_categ;
    }

    public void setTa_categ(Long ta_categ) {
        this.ta_categ = ta_categ;
    }

    @Basic
    @Column(name = "MIL_RANK")
    public String getTa_rank() {
        return ta_rank;
    }

    public void setTa_rank(String ta_rank) {
        this.ta_rank = ta_rank;
    }

    @Basic
    @Column(name = "CIV_BRANCH")
    public Long getTa_branch() {
        return ta_branch;
    }

    public void setTa_branch(Long ta_branch) {
        this.ta_branch = ta_branch;
    }

    @Basic
    @Column(name = "CIV_SPECIALITY")
    public String getTa_speciality() {
        return ta_speciality;
    }

    public void setTa_speciality(String ta_speciality) {
        this.ta_speciality = ta_speciality;
    }

    @Basic
    @Column(name = "CIV_SPECIALITY_OTHER")
    public String getTa_specialityOther() {
        return ta_specialityOther;
    }

    public void setTa_specialityOther(String ta_specialityOther) {
        this.ta_specialityOther = ta_specialityOther;
    }

    public void setAnswerPdfProtYear(Integer answerPdfProtYear) {
        this.answerPdfProtYear = answerPdfProtYear;}

    public void setComplAddressDesc(String complAddressDesc) {
        this.complAddressDesc = complAddressDesc;}





    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpPtlCompTaAnnMilitary that = (SpPtlCompTaAnnMilitary) o;

        if (id != that.id) return false;
        if (attachedDocId != null ? !attachedDocId.equals(that.attachedDocId) : that.attachedDocId != null)
            return false;
        if (caseId != null ? !caseId.equals(that.caseId) : that.caseId != null) return false;
        if (companyId != null ? !companyId.equals(that.companyId) : that.companyId != null) return false;
        if (docId != null ? !docId.equals(that.docId) : that.docId != null) return false;
        if (protDate != null ? !protDate.equals(that.protDate) : that.protDate != null) return false;
        if (protNo != null ? !protNo.equals(that.protNo) : that.protNo != null) return false;
        if (protYear != null ? !protYear.equals(that.protYear) : that.protYear != null) return false;
        if (reprFirstname != null ? !reprFirstname.equals(that.reprFirstname) : that.reprFirstname != null)
            return false;
        if (reprLastname != null ? !reprLastname.equals(that.reprLastname) : that.reprLastname != null) return false;
        if (reprMobile != null ? !reprMobile.equals(that.reprMobile) : that.reprMobile != null) return false;
        if (reprPhone != null ? !reprPhone.equals(that.reprPhone) : that.reprPhone != null) return false;
        if (reqStatus != null ? !reqStatus.equals(that.reqStatus) : that.reqStatus != null) return false;
        if (requestTypeId != null ? !requestTypeId.equals(that.requestTypeId) : that.requestTypeId != null)
            return false;
        if (sepeDept != null ? !sepeDept.equals(that.sepeDept) : that.sepeDept != null) return false;
        if (subStatus != null ? !subStatus.equals(that.subStatus) : that.subStatus != null) return false;
        if (submitTime != null ? !submitTime.equals(that.submitTime) : that.submitTime != null) return false;
        if (reqSubject != null ? !reqSubject.equals(that.reqSubject) : that.reqSubject != null) return false;
        if (branch0Id != null ? !branch0Id.equals(that.branch0Id) : that.branch0Id != null) return false;
        if (branch1Id != null ? !branch1Id.equals(that.branch1Id) : that.branch1Id != null) return false;
        if (inspAddr != null ? !inspAddr.equals(that.inspAddr) : that.inspAddr != null) return false;
        if (inspAddrD != null ? !inspAddrD.equals(that.inspAddrD) : that.inspAddrD != null) return false;
        if (inspAddrK != null ? !inspAddrK.equals(that.inspAddrK) : that.inspAddrK != null) return false;
        if (inspAddrP != null ? !inspAddrP.equals(that.inspAddrP) : that.inspAddrP != null) return false;
        if (inspAddrPe != null ? !inspAddrPe.equals(that.inspAddrPe) : that.inspAddrPe != null) return false;
        if (inspAddrTk != null ? !inspAddrTk.equals(that.inspAddrTk) : that.inspAddrTk != null) return false;
        if (answerPdfDocId != null ? !answerPdfDocId.equals(that.answerPdfDocId) : that.answerPdfDocId != null) return false;
        if (answerPdfProtNo != null ? !answerPdfProtNo.equals(that.answerPdfProtNo) : that.answerPdfProtNo != null) return false;
        if (answerPdfProtYear != null ? !answerPdfProtYear.equals(that.answerPdfProtYear) : that.answerPdfProtYear != null) return false;
        if (ta_firstname != null ? !ta_firstname.equals(that.ta_firstname) : that.ta_firstname != null) return false;
        if (ta_lastname != null ? !ta_lastname.equals(that.ta_lastname) : that.ta_lastname != null) return false;
        if (ta_fathername != null ? !ta_fathername.equals(that.ta_fathername) : that.ta_fathername != null) return false;
        if (ta_type != null ? !ta_type.equals(that.ta_type) : that.ta_type != null) return false;
        if (ta_categ != null ? !ta_categ.equals(that.ta_categ) : that.ta_categ != null) return false;
        if (ta_rank != null ? !ta_rank.equals(that.ta_rank) : that.ta_rank != null) return false;
        if (ta_branch != null ? !ta_branch.equals(that.ta_branch) : that.ta_branch != null) return false;
        if (ta_speciality != null ? !ta_speciality.equals(that.ta_speciality) : that.ta_speciality != null) return false;
        if (ta_specialityOther != null ? !ta_specialityOther.equals(that.ta_specialityOther) : that.ta_specialityOther != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (companyId != null ? companyId.hashCode() : 0);
        result = 31 * result + (reprFirstname != null ? reprFirstname.hashCode() : 0);
        result = 31 * result + (reprLastname != null ? reprLastname.hashCode() : 0);
        result = 31 * result + (reprPhone != null ? reprPhone.hashCode() : 0);
        result = 31 * result + (reprMobile != null ? reprMobile.hashCode() : 0);
        result = 31 * result + (requestTypeId != null ? requestTypeId.hashCode() : 0);
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
        result = 31 * result + (reqSubject != null ? reqSubject.hashCode() : 0);
        result = 31 * result + (branch0Id != null ? branch0Id.hashCode() : 0);
        result = 31 * result + (branch1Id != null ? branch1Id.hashCode() : 0);
        result = 31 * result + (inspAddr != null ? inspAddr.hashCode() : 0);
        result = 31 * result + (inspAddrD != null ? inspAddrD.hashCode() : 0);
        result = 31 * result + (inspAddrK != null ? inspAddrK.hashCode() : 0);
        result = 31 * result + (inspAddrP != null ? inspAddrP.hashCode() : 0);
        result = 31 * result + (inspAddrPe != null ? inspAddrPe.hashCode() : 0);
        result = 31 * result + (inspAddrTk != null ? inspAddrTk.hashCode() : 0);
        result = 31 * result + (answerPdfDocId != null ? answerPdfDocId.hashCode() : 0);
        result = 31 * result + (answerPdfProtNo != null ? answerPdfProtNo.hashCode() : 0);
        result = 31 * result + (answerPdfProtYear != null ? answerPdfProtYear.hashCode() : 0);
        result = 31 * result + (ta_firstname != null ? ta_firstname.hashCode() : 0);
        result = 31 * result + (ta_lastname != null ? ta_lastname.hashCode() : 0);
        result = 31 * result + (ta_fathername != null ? ta_fathername.hashCode() : 0);
        result = 31 * result + (ta_type != null ? ta_type.hashCode() : 0);
        result = 31 * result + (ta_categ != null ? ta_categ.hashCode() : 0);
        result = 31 * result + (ta_rank != null ? ta_rank.hashCode() : 0);
        result = 31 * result + (ta_branch != null ? ta_branch.hashCode() : 0);
        result = 31 * result + (ta_speciality != null ? ta_speciality.hashCode() : 0);
        result = 31 * result + (ta_specialityOther != null ? ta_specialityOther.hashCode() : 0);
        return result;
    }
}
