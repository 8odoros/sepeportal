package sepe.domain.employee;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created by Nikolas on 3/16/2015.
 */
@Entity
@javax.persistence.Table(name = "SP_PTL_EMPLOYEE_GENREQUEST", schema = "SP_PTL", catalog = "")
public class SpPtlEmployeeGenrequest {
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

    private String empFirstname;

    @Basic
    @javax.persistence.Column(name = "EMP_FIRSTNAME", nullable = true, insertable = true, updatable = true, length = 100)
    public String getEmpFirstname() {
        return empFirstname;
    }

    public void setEmpFirstname(String empFirstname) {
        this.empFirstname = empFirstname;
    }

    private String empLastname;

    @Basic
    @javax.persistence.Column(name = "EMP_LASTNAME", nullable = true, insertable = true, updatable = true, length = 100)
    public String getEmpLastname() {
        return empLastname;
    }

    public void setEmpLastname(String empLastname) {
        this.empLastname = empLastname;
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

    private Timestamp empFromDate;

    @Basic
    @javax.persistence.Column(name = "EMP_FROM_DATE", nullable = true, insertable = true, updatable = true)
    public Timestamp getEmpFromDate() {
        return empFromDate;
    }

    public void setEmpFromDate(Timestamp empFromDate) {
        this.empFromDate = empFromDate;
    }

    private Timestamp empUntilDate;

    @Basic
    @javax.persistence.Column(name = "EMP_UNTIL_DATE", nullable = true, insertable = true, updatable = true)
    public Timestamp getEmpUntilDate() {
        return empUntilDate;
    }

    public void setEmpUntilDate(Timestamp empUntilDate) {
        this.empUntilDate = empUntilDate;
    }

    private String empAddr;

    @Basic
    @javax.persistence.Column(name = "EMP_ADDR", nullable = true, insertable = true, updatable = true, length = 100)
    public String getEmpAddr() {
        return empAddr;
    }

    public void setEmpAddr(String empAddr) {
        this.empAddr = empAddr;
    }

    private String empAddrTk;

    @Basic
    @javax.persistence.Column(name = "EMP_ADDR_TK", nullable = true, insertable = true, updatable = true, length = 20)
    public String getEmpAddrTk() {
        return empAddrTk;
    }

    public void setEmpAddrTk(String empAddrTk) {
        this.empAddrTk = empAddrTk;
    }

    private String empAddrP;

    @Basic
    @javax.persistence.Column(name = "EMP_ADDR_P", nullable = true, insertable = true, updatable = true, length = 100)
    public String getEmpAddrP() {
        return empAddrP;
    }

    public void setEmpAddrP(String empAddrP) {
        this.empAddrP = empAddrP;
    }

    private String empAddrPe;

    @Basic
    @javax.persistence.Column(name = "EMP_ADDR_PE", nullable = true, insertable = true, updatable = true, length = 100)
    public String getEmpAddrPe() {
        return empAddrPe;
    }

    public void setEmpAddrPe(String empAddrPe) {
        this.empAddrPe = empAddrPe;
    }

    private String empAddrD;

    @Basic
    @javax.persistence.Column(name = "EMP_ADDR_D", nullable = true, insertable = true, updatable = true, length = 100)
    public String getEmpAddrD() {
        return empAddrD;
    }

    public void setEmpAddrD(String empAddrD) {
        this.empAddrD = empAddrD;
    }

    private String empAddrK;

    @Basic
    @javax.persistence.Column(name = "EMP_ADDR_K", nullable = true, insertable = true, updatable = true, length = 100)
    public String getEmpAddrK() {
        return empAddrK;
    }

    public void setEmpAddrK(String empAddrK) {
        this.empAddrK = empAddrK;
    }

    private String empAfm;

    @Basic
    @javax.persistence.Column(name = "EMP_AFM", nullable = true, insertable = true, updatable = true, length = 50)
    public String getEmpAfm() {
        return empAfm;
    }

    public void setEmpAfm(String empAfm) {
        this.empAfm = empAfm;
    }

    private String empAmka;

    @Basic
    @javax.persistence.Column(name = "EMP_AMKA", nullable = true, insertable = true, updatable = true, length = 50)
    public String getEmpAmka() {
        return empAmka;
    }

    public void setEmpAmka(String empAmka) {
        this.empAmka = empAmka;
    }

    private String empCardNumber;

    @Basic
    @javax.persistence.Column(name = "EMP_CARD_NUMBER", nullable = true, insertable = true, updatable = true, length = 50)
    public String getEmpCardNumber() {
        return empCardNumber;
    }

    public void setEmpCardNumber(String empCardNumber) {
        this.empCardNumber = empCardNumber;
    }

    private String empPhone;

    @Basic
    @javax.persistence.Column(name = "EMP_PHONE", nullable = true, insertable = true, updatable = true, length = 50)
    public String getEmpPhone() {
        return empPhone;
    }

    public void setEmpPhone(String empPhone) {
        this.empPhone = empPhone;
    }

    private String empMobile;

    @Basic
    @javax.persistence.Column(name = "EMP_MOBILE", nullable = true, insertable = true, updatable = true, length = 50)
    public String getEmpMobile() {
        return empMobile;
    }

    public void setEmpMobile(String empMobile) {
        this.empMobile = empMobile;
    }

    /*
    private String empEmail;

    @Basic
    @javax.persistence.Column(name = "EMP_EMAIL", nullable = true, insertable = true, updatable = true, length = 50)
    public String getEmpEmail() {
        return empEmail;
    }

    public void setEmpEmail(String empEmail) {
        this.empEmail = empEmail;
    }
    */

    private String reqDescription;

    @Basic
    @javax.persistence.Column(name = "REQ_DESCRIPTION", nullable = true, insertable = true, updatable = true, length = 3000)
    public String getReqDescription() {
        return reqDescription;
    }

    public void setReqDescription(String reqDescription) {
        this.reqDescription = reqDescription;
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

    private Long empCardType;

    @Basic
    @javax.persistence.Column(name = "EMP_CARD_TYPE", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getEmpCardType() {
        return empCardType;
    }

    public void setEmpCardType(Long empCardType) {
        this.empCardType = empCardType;
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

    private String reqRemarks;

    @Basic
    @javax.persistence.Column(name = "REQ_REMARKS", nullable = true, insertable = true, updatable = true, length = 1000)
    public String getReqRemarks() {
        return reqRemarks;
    }

    public void setReqRemarks(String reqRemarks) {
        this.reqRemarks = reqRemarks;
    }

    private String empFathername;

    @Basic
    @javax.persistence.Column(name = "EMP_FATHERNAME", nullable = true, insertable = true, updatable = true, length = 100)
    public String getEmpFathername() {
        return empFathername;
    }

    public void setEmpFathername(String empFathername) {
        this.empFathername = empFathername;
    }

    private String empMothername;

    @Basic
    @javax.persistence.Column(name = "EMP_MOTHERNAME", nullable = true, insertable = true, updatable = true, length = 100)
    public String getEmpMothername() {
        return empMothername;
    }

    public void setEmpMothername(String empMothername) {
        this.empMothername = empMothername;
    }

    private Integer reqType;

    @Basic
    @javax.persistence.Column(name = "REQ_TYPE", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getReqType() {
        return reqType;
    }

    public void setReqType(Integer reqType) {
        this.reqType = reqType;
    }

    private Integer reqSubject;

    @Basic
    @javax.persistence.Column(name = "REQ_SUBJECT", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getReqSubject() {
        return reqSubject;
    }

    public void setReqSubject(Integer reqSubject) {
        this.reqSubject = reqSubject;
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

    private Long caseId;

    @Basic
    @javax.persistence.Column(name = "CASE_ID", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpPtlEmployeeGenrequest that = (SpPtlEmployeeGenrequest) o;

        if (id != that.id) return false;
        if (attachedDocId != null ? !attachedDocId.equals(that.attachedDocId) : that.attachedDocId != null)
            return false;
        if (department != null ? !department.equals(that.department) : that.department != null) return false;
        if (docId != null ? !docId.equals(that.docId) : that.docId != null) return false;
        if (empAddr != null ? !empAddr.equals(that.empAddr) : that.empAddr != null) return false;
        if (empAddrD != null ? !empAddrD.equals(that.empAddrD) : that.empAddrD != null) return false;
        if (empAddrK != null ? !empAddrK.equals(that.empAddrK) : that.empAddrK != null) return false;
        if (empAddrP != null ? !empAddrP.equals(that.empAddrP) : that.empAddrP != null) return false;
        if (empAddrPe != null ? !empAddrPe.equals(that.empAddrPe) : that.empAddrPe != null) return false;
        if (empAddrTk != null ? !empAddrTk.equals(that.empAddrTk) : that.empAddrTk != null) return false;
        if (empAfm != null ? !empAfm.equals(that.empAfm) : that.empAfm != null) return false;
        if (empAmka != null ? !empAmka.equals(that.empAmka) : that.empAmka != null) return false;
        if (empCardNumber != null ? !empCardNumber.equals(that.empCardNumber) : that.empCardNumber != null)
            return false;
        if (empCardType != null ? !empCardType.equals(that.empCardType) : that.empCardType != null) return false;
        if (empFathername != null ? !empFathername.equals(that.empFathername) : that.empFathername != null)
            return false;
        if (empFirstname != null ? !empFirstname.equals(that.empFirstname) : that.empFirstname != null) return false;
        if (empFromDate != null ? !empFromDate.equals(that.empFromDate) : that.empFromDate != null) return false;
        if (empLastname != null ? !empLastname.equals(that.empLastname) : that.empLastname != null) return false;
        if (empMobile != null ? !empMobile.equals(that.empMobile) : that.empMobile != null) return false;
        //if (empEmail != null ? !empEmail.equals(that.empEmail) : that.empEmail != null) return false;
        if (empMothername != null ? !empMothername.equals(that.empMothername) : that.empMothername != null)
            return false;
        if (empPhone != null ? !empPhone.equals(that.empPhone) : that.empPhone != null) return false;
        if (empUntilDate != null ? !empUntilDate.equals(that.empUntilDate) : that.empUntilDate != null) return false;
        if (protDate != null ? !protDate.equals(that.protDate) : that.protDate != null) return false;
        if (protNo != null ? !protNo.equals(that.protNo) : that.protNo != null) return false;
        if (protYear != null ? !protYear.equals(that.protYear) : that.protYear != null) return false;
        if (reqAddrD != null ? !reqAddrD.equals(that.reqAddrD) : that.reqAddrD != null) return false;
        if (reqAddrK != null ? !reqAddrK.equals(that.reqAddrK) : that.reqAddrK != null) return false;
        if (reqAddrP != null ? !reqAddrP.equals(that.reqAddrP) : that.reqAddrP != null) return false;
        if (reqAddrPe != null ? !reqAddrPe.equals(that.reqAddrPe) : that.reqAddrPe != null) return false;
        if (reqDescription != null ? !reqDescription.equals(that.reqDescription) : that.reqDescription != null)
            return false;
        if (reqRemarks != null ? !reqRemarks.equals(that.reqRemarks) : that.reqRemarks != null) return false;
        if (reqStatus != null ? !reqStatus.equals(that.reqStatus) : that.reqStatus != null) return false;
        if (reqSubject != null ? !reqSubject.equals(that.reqSubject) : that.reqSubject != null) return false;
        if (reqType != null ? !reqType.equals(that.reqType) : that.reqType != null) return false;
        if (subStatus != null ? !subStatus.equals(that.subStatus) : that.subStatus != null) return false;
        if (submitTime != null ? !submitTime.equals(that.submitTime) : that.submitTime != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (caseId != null ? !caseId.equals(that.caseId) : that.caseId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (subStatus != null ? subStatus.hashCode() : 0);
        result = 31 * result + (reqStatus != null ? reqStatus.hashCode() : 0);
        result = 31 * result + (protNo != null ? protNo.hashCode() : 0);
        result = 31 * result + (empFirstname != null ? empFirstname.hashCode() : 0);
        result = 31 * result + (empLastname != null ? empLastname.hashCode() : 0);
        result = 31 * result + (protYear != null ? protYear.hashCode() : 0);
        result = 31 * result + (empFromDate != null ? empFromDate.hashCode() : 0);
        result = 31 * result + (empUntilDate != null ? empUntilDate.hashCode() : 0);
        result = 31 * result + (empAddr != null ? empAddr.hashCode() : 0);
        result = 31 * result + (empAddrTk != null ? empAddrTk.hashCode() : 0);
        result = 31 * result + (empAddrP != null ? empAddrP.hashCode() : 0);
        result = 31 * result + (empAddrPe != null ? empAddrPe.hashCode() : 0);
        result = 31 * result + (empAddrD != null ? empAddrD.hashCode() : 0);
        result = 31 * result + (empAddrK != null ? empAddrK.hashCode() : 0);
        result = 31 * result + (empAfm != null ? empAfm.hashCode() : 0);
        result = 31 * result + (empAmka != null ? empAmka.hashCode() : 0);
        result = 31 * result + (empCardNumber != null ? empCardNumber.hashCode() : 0);
        result = 31 * result + (empPhone != null ? empPhone.hashCode() : 0);
        result = 31 * result + (empMobile != null ? empMobile.hashCode() : 0);
        //result = 31 * result + (empEmail != null ? empEmail.hashCode() : 0);
        result = 31 * result + (reqDescription != null ? reqDescription.hashCode() : 0);
        result = 31 * result + (protDate != null ? protDate.hashCode() : 0);
        result = 31 * result + (empCardType != null ? empCardType.hashCode() : 0);
        result = 31 * result + (docId != null ? docId.hashCode() : 0);
        result = 31 * result + (submitTime != null ? submitTime.hashCode() : 0);
        result = 31 * result + (department != null ? department.hashCode() : 0);
        result = 31 * result + (attachedDocId != null ? attachedDocId.hashCode() : 0);
        result = 31 * result + (reqRemarks != null ? reqRemarks.hashCode() : 0);
        result = 31 * result + (empFathername != null ? empFathername.hashCode() : 0);
        result = 31 * result + (empMothername != null ? empMothername.hashCode() : 0);
        result = 31 * result + (reqType != null ? reqType.hashCode() : 0);
        result = 31 * result + (reqSubject != null ? reqSubject.hashCode() : 0);
        result = 31 * result + (reqAddrP != null ? reqAddrP.hashCode() : 0);
        result = 31 * result + (reqAddrPe != null ? reqAddrPe.hashCode() : 0);
        result = 31 * result + (reqAddrD != null ? reqAddrD.hashCode() : 0);
        result = 31 * result + (reqAddrK != null ? reqAddrK.hashCode() : 0);
        result = 31 * result + (caseId != null ? caseId.hashCode() : 0);
        return result;
    }
}
