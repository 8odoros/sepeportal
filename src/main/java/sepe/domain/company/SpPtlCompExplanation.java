package sepe.domain.company;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created by Nick on 5/10/2015.
 */
@Entity
@javax.persistence.Table(name = "SP_PTL_COMP_EXPLANATION", schema = "SP_PTL", catalog = "")
public class SpPtlCompExplanation {
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

    private String compAfm;

    @Basic
    @javax.persistence.Column(name = "COMP_AFM")
    public String getCompAfm() {
        return compAfm;
    }

    public void setCompAfm(String compAfm) {
        this.compAfm = compAfm;
    }

    private String compName;

    @Basic
    @javax.persistence.Column(name = "COMP_NAME")
    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
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

    private String descr;

    @Basic
    @javax.persistence.Column(name = "DESCR")
    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
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

    private Long sepeDept;

    @Basic
    @javax.persistence.Column(name = "SEPE_DEPT")
    public Long getSepeDept() {
        return sepeDept;
    }

    public void setSepeDept(Long sepeDept) {
        this.sepeDept = sepeDept;
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

    private String protNo;

    @Basic
    @javax.persistence.Column(name = "PROT_NO")
    public String getProtNo() {
        return protNo;
    }

    public void setProtNo(String protNo) {
        this.protNo = protNo;
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

    private Long companyId;

    @Basic
    @javax.persistence.Column(name = "COMPANY_ID")
    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    private String taFullname;

    @Basic
    @javax.persistence.Column(name = "TA_FULLNAME")
    public String getTaFullname() {
        return taFullname;
    }

    public void setTaFullname(String taFullname) {
        this.taFullname = taFullname;
    }

    private String ieFullname;

    @Basic
    @javax.persistence.Column(name = "IE_FULLNAME")
    public String getIeFullname() {
        return ieFullname;
    }

    public void setIeFullname(String ieFullname) {
        this.ieFullname = ieFullname;
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

    private Long docId;

    @Basic
    @javax.persistence.Column(name = "DOC_ID")
    public Long getDocId() {
        return docId;
    }

    public void setDocId(Long docId) {
        this.docId = docId;
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

    private String rtstakLevel1;

    @Basic
    @javax.persistence.Column(name = "RTSTAK_LEVEL_1")
    public String getRtstakLevel1() {
        return rtstakLevel1;
    }

    public void setRtstakLevel1(String rtstakLevel1) {
        this.rtstakLevel1 = rtstakLevel1;
    }

    private String rtstakLevel2;

    @Basic
    @javax.persistence.Column(name = "RTSTAK_LEVEL_2")
    public String getRtstakLevel2() {
        return rtstakLevel2;
    }

    public void setRtstakLevel2(String rtstakLevel2) {
        this.rtstakLevel2 = rtstakLevel2;
    }

    private String rtstakLevel3;

    @Basic
    @javax.persistence.Column(name = "RTSTAK_LEVEL_3")
    public String getRtstakLevel3() {
        return rtstakLevel3;
    }

    public void setRtstakLevel3(String rtstakLevel3) {
        this.rtstakLevel3 = rtstakLevel3;
    }

    private String rtstakLevel4;

    @Basic
    @javax.persistence.Column(name = "RTSTAK_LEVEL_4")
    public String getRtstakLevel4() {
        return rtstakLevel4;
    }

    public void setRtstakLevel4(String rtstakLevel4) {
        this.rtstakLevel4 = rtstakLevel4;
    }

    private String rtstakLevel5;

    @Basic
    @javax.persistence.Column(name = "RTSTAK_LEVEL_5")
    public String getRtstakLevel5() {
        return rtstakLevel5;
    }

    public void setRtstakLevel5(String rtstakLevel5) {
        this.rtstakLevel5 = rtstakLevel5;
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

    private Long branch0Id;

    @Basic
    @javax.persistence.Column(name = "BRANCH0_ID")
    public Long getBranch0Id() {
        return branch0Id;
    }

    public void setBranch0Id(Long branch0Id) {
        this.branch0Id = branch0Id;
    }

    private Long branch1Id;

    @Basic
    @javax.persistence.Column(name = "BRANCH1_ID")
    public Long getBranch1Id() {
        return branch1Id;
    }

    public void setBranch1Id(Long branch1Id) {
        this.branch1Id = branch1Id;
    }

    private String inspectorName;

    @Basic
    @javax.persistence.Column(name = "INSPECTOR_NAME")
    public String getInspectorName() {
        return inspectorName;
    }

    public void setInspectorName(String inspectorName) {
        this.inspectorName = inspectorName;
    }

    private String controlStatementNum;

    @Basic
    @javax.persistence.Column(name = "CONTROL_STATEMENT_NUM")
    public String getControlStatementNum() {
        return controlStatementNum;
    }

    public void setControlStatementNum(String controlStatementNum) {
        this.controlStatementNum = controlStatementNum;
    }

    private String attachedDocDescr;

    @Basic
    @javax.persistence.Column(name = "ATTACHED_DOC_DESCR")
    public String getAttachedDocDescr() {
        return attachedDocDescr;
    }

    public void setAttachedDocDescr(String attachedDocDescr) {
        this.attachedDocDescr = attachedDocDescr;
    }

    private Timestamp inspectionDate;

    @Basic
    @javax.persistence.Column(name = "INSPECTION_DATE")
    public Timestamp getInspectionDate() {
        return inspectionDate;
    }

    public void setInspectionDate(Timestamp inspectionDate) {
        this.inspectionDate = inspectionDate;
    }

    private String empInchargesId;

    @Basic
    @javax.persistence.Column(name = "EMP_INCHARGES_ID")
    public String getEmpInchargesId() {
        return empInchargesId;
    }

    public void setEmpInchargesId(String empInchargesId) {
        this.empInchargesId = empInchargesId;
    }

    private String externalProtNo;

    @Basic
    @javax.persistence.Column(name = "EXTERNAL_PROT_NO")
    public String getExternalProtNo() {
        return externalProtNo;
    }

    public void setExternalProtNo(String externalProtNo) {
        this.externalProtNo = externalProtNo;
    }

    private String teKe;

    @Basic
    @javax.persistence.Column(name = "TE_KE")
    public String getTeKe() {
        return teKe;
    }

    public void setTeKe(String teKe) {
        this.teKe = teKe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpPtlCompExplanation that = (SpPtlCompExplanation) o;

        if (id != that.id) return false;
        if (attachedDocDescr != null ? !attachedDocDescr.equals(that.attachedDocDescr) : that.attachedDocDescr != null)
            return false;
        if (branch0Id != null ? !branch0Id.equals(that.branch0Id) : that.branch0Id != null) return false;
        if (branch1Id != null ? !branch1Id.equals(that.branch1Id) : that.branch1Id != null) return false;
        if (caseId != null ? !caseId.equals(that.caseId) : that.caseId != null) return false;
        if (compAddr != null ? !compAddr.equals(that.compAddr) : that.compAddr != null) return false;
        if (compAddrD != null ? !compAddrD.equals(that.compAddrD) : that.compAddrD != null) return false;
        if (compAddrK != null ? !compAddrK.equals(that.compAddrK) : that.compAddrK != null) return false;
        if (compAddrP != null ? !compAddrP.equals(that.compAddrP) : that.compAddrP != null) return false;
        if (compAddrPe != null ? !compAddrPe.equals(that.compAddrPe) : that.compAddrPe != null) return false;
        if (compAddrTk != null ? !compAddrTk.equals(that.compAddrTk) : that.compAddrTk != null) return false;
        if (compAfm != null ? !compAfm.equals(that.compAfm) : that.compAfm != null) return false;
        if (compAme != null ? !compAme.equals(that.compAme) : that.compAme != null) return false;
        if (compName != null ? !compName.equals(that.compName) : that.compName != null) return false;
        if (compPhone != null ? !compPhone.equals(that.compPhone) : that.compPhone != null) return false;
        if (companyId != null ? !companyId.equals(that.companyId) : that.companyId != null) return false;
        if (controlStatementNum != null ? !controlStatementNum.equals(that.controlStatementNum) : that.controlStatementNum != null)
            return false;
        if (descr != null ? !descr.equals(that.descr) : that.descr != null) return false;
        if (docId != null ? !docId.equals(that.docId) : that.docId != null) return false;
        if (attachedDocId != null ? !attachedDocId.equals(that.attachedDocId) : that.attachedDocId != null)
            return false;
        if (externalProtNo != null ? !externalProtNo.equals(that.externalProtNo) : that.externalProtNo != null)
            return false;
        if (ieFullname != null ? !ieFullname.equals(that.ieFullname) : that.ieFullname != null) return false;
        if (inspectionDate != null ? !inspectionDate.equals(that.inspectionDate) : that.inspectionDate != null)
            return false;
        if (inspectorName != null ? !inspectorName.equals(that.inspectorName) : that.inspectorName != null)
            return false;
        if (protDate != null ? !protDate.equals(that.protDate) : that.protDate != null) return false;
        if (protNo != null ? !protNo.equals(that.protNo) : that.protNo != null) return false;
        if (protYear != null ? !protYear.equals(that.protYear) : that.protYear != null) return false;
        if (reqStatus != null ? !reqStatus.equals(that.reqStatus) : that.reqStatus != null) return false;
        if (rtstakLevel1 != null ? !rtstakLevel1.equals(that.rtstakLevel1) : that.rtstakLevel1 != null) return false;
        if (rtstakLevel2 != null ? !rtstakLevel2.equals(that.rtstakLevel2) : that.rtstakLevel2 != null) return false;
        if (rtstakLevel3 != null ? !rtstakLevel3.equals(that.rtstakLevel3) : that.rtstakLevel3 != null) return false;
        if (rtstakLevel4 != null ? !rtstakLevel4.equals(that.rtstakLevel4) : that.rtstakLevel4 != null) return false;
        if (rtstakLevel5 != null ? !rtstakLevel5.equals(that.rtstakLevel5) : that.rtstakLevel5 != null) return false;
        if (sepeDept != null ? !sepeDept.equals(that.sepeDept) : that.sepeDept != null) return false;
        if (subStatus != null ? !subStatus.equals(that.subStatus) : that.subStatus != null) return false;
        if (submitTime != null ? !submitTime.equals(that.submitTime) : that.submitTime != null) return false;
        if (taFullname != null ? !taFullname.equals(that.taFullname) : that.taFullname != null) return false;
        if (empInchargesId != null ? !empInchargesId.equals(that.empInchargesId) : that.empInchargesId != null) return false;
        if (teKe != null ? !teKe.equals(that.teKe) : that.teKe != null) return false;
        return true;
    }
    
    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (empInchargesId != null ? empInchargesId.hashCode() : 0);
        result = 31 * result + (compAddr != null ? compAddr.hashCode() : 0);
        result = 31 * result + (compAddrD != null ? compAddrD.hashCode() : 0);
        result = 31 * result + (compAddrK != null ? compAddrK.hashCode() : 0);
        result = 31 * result + (compAddrP != null ? compAddrP.hashCode() : 0);
        result = 31 * result + (compAddrPe != null ? compAddrPe.hashCode() : 0);
        result = 31 * result + (compAddrTk != null ? compAddrTk.hashCode() : 0);
        result = 31 * result + (compAfm != null ? compAfm.hashCode() : 0);
        result = 31 * result + (compName != null ? compName.hashCode() : 0);
        result = 31 * result + (compPhone != null ? compPhone.hashCode() : 0);
        result = 31 * result + (descr != null ? descr.hashCode() : 0);
        result = 31 * result + (compAme != null ? compAme.hashCode() : 0);
        result = 31 * result + (sepeDept != null ? sepeDept.hashCode() : 0);
        result = 31 * result + (protDate != null ? protDate.hashCode() : 0);
        result = 31 * result + (protNo != null ? protNo.hashCode() : 0);
        result = 31 * result + (reqStatus != null ? reqStatus.hashCode() : 0);
        result = 31 * result + (subStatus != null ? subStatus.hashCode() : 0);
        result = 31 * result + (companyId != null ? companyId.hashCode() : 0);
        result = 31 * result + (taFullname != null ? taFullname.hashCode() : 0);
        result = 31 * result + (ieFullname != null ? ieFullname.hashCode() : 0);
        result = 31 * result + (protYear != null ? protYear.hashCode() : 0);
        result = 31 * result + (submitTime != null ? submitTime.hashCode() : 0);
        result = 31 * result + (docId != null ? docId.hashCode() : 0);
        result = 31 * result + (caseId != null ? caseId.hashCode() : 0);
        result = 31 * result + (rtstakLevel1 != null ? rtstakLevel1.hashCode() : 0);
        result = 31 * result + (rtstakLevel2 != null ? rtstakLevel2.hashCode() : 0);
        result = 31 * result + (rtstakLevel3 != null ? rtstakLevel3.hashCode() : 0);
        result = 31 * result + (rtstakLevel4 != null ? rtstakLevel4.hashCode() : 0);
        result = 31 * result + (rtstakLevel5 != null ? rtstakLevel5.hashCode() : 0);
        result = 31 * result + (attachedDocId != null ? attachedDocId.hashCode() : 0);
        result = 31 * result + (branch0Id != null ? branch0Id.hashCode() : 0);
        result = 31 * result + (branch1Id != null ? branch1Id.hashCode() : 0);
        result = 31 * result + (inspectorName != null ? inspectorName.hashCode() : 0);
        result = 31 * result + (controlStatementNum != null ? controlStatementNum.hashCode() : 0);
        result = 31 * result + (attachedDocDescr != null ? attachedDocDescr.hashCode() : 0);
        result = 31 * result + (inspectionDate != null ? inspectionDate.hashCode() : 0);
        result = 31 * result + (externalProtNo != null ? externalProtNo.hashCode() : 0);
        result = 31 * result + (teKe != null ? teKe.hashCode() : 0);
        return result;
    }
}
