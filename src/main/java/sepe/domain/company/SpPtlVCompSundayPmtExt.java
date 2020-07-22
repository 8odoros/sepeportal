package sepe.domain.company;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by dimitrisf on 11/9/2018.
 */
@Entity
@javax.persistence.Table(name = "SP_PTL_V_COMP_SUNDAY_PMT_EXT", schema = "SP_PTL", catalog = "")
public class SpPtlVCompSundayPmtExt {

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

    private String etiology;

    @Basic
    @javax.persistence.Column(name = "ETIOLOGY")
    public String getEtiology() {
        return etiology;
    }

    public void setEtiology(String etiology) {
        this.etiology = etiology;
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

    private String compStatement;

    @Basic
    @javax.persistence.Column(name = "COMP_STATEMENT")
    public String getCompStatement() {
        return compStatement;
    }

    public void setCompStatement(String compStatement) {
        this.compStatement = compStatement;
    }

    private Timestamp sundayDate;

    @Basic
    @javax.persistence.Column(name = "SUNDAY_DATE")
    public Timestamp getSundayDate() {
        return sundayDate;
    }

    public void setSundayDate(Timestamp sundayDate) {
        this.sundayDate = sundayDate;
    }

    private Long compDoyId;

    @Basic
    @javax.persistence.Column(name = "COMP_DOY_ID")
    public Long getCompDoyId() {
        return compDoyId;
    }

    public void setCompDoyId(Long compDoyId) {
        this.compDoyId = compDoyId;
    }

    private Long compLegalFormId;

    @Basic
    @javax.persistence.Column(name = "COMP_LEGAL_FORM_ID")
    public Long getCompLegalFormId() {
        return compLegalFormId;
    }

    public void setCompLegalFormId(Long compLegalFormId) {
        this.compLegalFormId = compLegalFormId;
    }

    private Integer inspectorName;

    @Basic
    @javax.persistence.Column(name = "INSPECTOR_NAME")
    public Integer getInspectorName() {
        return inspectorName;
    }

    public void setInspectorName(Integer inspectorName) {
        this.inspectorName = inspectorName;
    }

    private String compKad;

    @Basic
    @javax.persistence.Column(name = "COMP_KAD")
    public String getCompKad() {
        return compKad;
    }

    public void setCompKad(String compKad) {
        this.compKad = compKad;
    }

    private String compPhysicalFlag;

    @Basic
    @javax.persistence.Column(name = "COMP_PHYSICAL_FLAG")
    public String getCompPhysicalFlag() {
        return compPhysicalFlag;
    }

    public void setCompPhysicalFlag(String compPhysicalFlag) {
        this.compPhysicalFlag = compPhysicalFlag;
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

    private String attachedDocDescr;

    @Basic
    @javax.persistence.Column(name = "ATTACHED_DOC_DESCR")
    public String getAttachedDocDescr() {
        return attachedDocDescr;
    }

    public void setAttachedDocDescr(String attachedDocDescr) {
        this.attachedDocDescr = attachedDocDescr;
    }

    private String inspAddr;

    @Basic
    @javax.persistence.Column(name = "INSP_ADDR", nullable = true, insertable = true, updatable = true, length = 50)
    public String getInspAddr() {
        return inspAddr;
    }

    public void setInspAddr(String inspAddr) {
        this.inspAddr = inspAddr;
    }

    private String inspAddrD;

    @Basic
    @javax.persistence.Column(name = "INSP_ADDR_D", nullable = true, insertable = true, updatable = true, length = 100)
    public String getInspAddrD() {
        return inspAddrD;
    }

    public void setInspAddrD(String inspAddrD) {
        this.inspAddrD = inspAddrD;
    }

    private String inspAddrK;

    @Basic
    @javax.persistence.Column(name = "INSP_ADDR_K", nullable = true, insertable = true, updatable = true, length = 100)
    public String getInspAddrK() {
        return inspAddrK;
    }

    public void setInspAddrK(String inspAddrK) {
        this.inspAddrK = inspAddrK;
    }

    private String inspAddrP;

    @Basic
    @javax.persistence.Column(name = "INSP_ADDR_P", nullable = true, insertable = true, updatable = true, length = 100)
    public String getInspAddrP() {
        return inspAddrP;
    }

    public void setInspAddrP(String inspAddrP) {
        this.inspAddrP = inspAddrP;
    }

    private String inspAddrPe;

    @Basic
    @javax.persistence.Column(name = "INSP_ADDR_PE", nullable = true, insertable = true, updatable = true, length = 100)
    public String getInspAddrPe() {
        return inspAddrPe;
    }

    public void setInspAddrPe(String inspAddrPe) {
        this.inspAddrPe = inspAddrPe;
    }

    private String inspAddrTk;

    @Basic
    @javax.persistence.Column(name = "INSP_ADDR_TK", nullable = true, insertable = true, updatable = true, length = 50)
    public String getInspAddrTk() {
        return inspAddrTk;
    }

    public void setInspAddrTk(String inspAddrTk) {
        this.inspAddrTk = inspAddrTk;
    }

    private String compAddressDesc;

    @Basic
    @javax.persistence.Column(name = "COMP_ADDRESS_DESC", nullable = true, insertable = true, updatable = true, length = 300)
    public String getCompAddressDesc() {
        return compAddressDesc;
    }

    public void setCompAddressDesc(String compAddressDesc) {
        this.compAddressDesc = compAddressDesc;
    }

    private Long menNum;

    @Basic
    @javax.persistence.Column(name = "MEN_NUM")
    public Long getMenNum() {
        return menNum;
    }

    public void setMenNum(Long menNum) {
        this.menNum = menNum;
    }

    private Long womenNum;

    @Basic
    @javax.persistence.Column(name = "WOMEN_NUM")
    public Long getWomenNum() {
        return womenNum;
    }

    public void setWomenNum(Long womenNum) {
        this.womenNum = womenNum;
    }

    private Timestamp holidayDate;

    @Basic
    @javax.persistence.Column(name = "HOLIDAY_DATE")
    public Timestamp getHolidayDate() {
        return holidayDate;
    }

    public void setHolidayDate(Timestamp holidayDate) {
        this.holidayDate = holidayDate;
    }

    private String cdText;
    private String cdKind;

    @Basic
    @Column(name = "CD_TEXT", nullable = false, insertable = false, updatable = false, length = 65)
    public String getCdText() {
        return cdText;
    }

    public void setCdText(String cdText) {
        this.cdText = cdText;
    }

    @Basic
    @Column(name = "CD_KIND", nullable = false, insertable = false, updatable = false, length = 65)
    public String getCdKind() {
        return cdKind;
    }

    public void setCdKind(String cdKind) {
        this.cdKind = cdKind;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpPtlVCompSundayPmtExt that = (SpPtlVCompSundayPmtExt) o;

        if (id != that.id) return false;
        if (caseId != null ? !caseId.equals(that.caseId) : that.caseId != null) return false;
        if (compAddr != null ? !compAddr.equals(that.compAddr) : that.compAddr != null) return false;
        if (compAddrD != null ? !compAddrD.equals(that.compAddrD) : that.compAddrD != null) return false;
        if (compAddrK != null ? !compAddrK.equals(that.compAddrK) : that.compAddrK != null) return false;
        if (compAddrP != null ? !compAddrP.equals(that.compAddrP) : that.compAddrP != null) return false;
        if (compAddrPe != null ? !compAddrPe.equals(that.compAddrPe) : that.compAddrPe != null) return false;
        if (compAddrTk != null ? !compAddrTk.equals(that.compAddrTk) : that.compAddrTk != null) return false;
        if (compAfm != null ? !compAfm.equals(that.compAfm) : that.compAfm != null) return false;
        if (compDoyId != null ? !compDoyId.equals(that.compDoyId) : that.compDoyId != null) return false;
        if (compEbrBranch0Id != null ? !compEbrBranch0Id.equals(that.compEbrBranch0Id) : that.compEbrBranch0Id != null)
            return false;
        if (compEbrBranchId != null ? !compEbrBranchId.equals(that.compEbrBranchId) : that.compEbrBranchId != null)
            return false;
        if (compKad != null ? !compKad.equals(that.compKad) : that.compKad != null) return false;
        if (compLegalFormId != null ? !compLegalFormId.equals(that.compLegalFormId) : that.compLegalFormId != null)
            return false;
        if (compName != null ? !compName.equals(that.compName) : that.compName != null) return false;
        if (compPhone != null ? !compPhone.equals(that.compPhone) : that.compPhone != null) return false;
        if (compPhysicalFlag != null ? !compPhysicalFlag.equals(that.compPhysicalFlag) : that.compPhysicalFlag != null)
            return false;
        if (compStatement != null ? !compStatement.equals(that.compStatement) : that.compStatement != null)
            return false;
        if (companyId != null ? !companyId.equals(that.companyId) : that.companyId != null) return false;
        if (docId != null ? !docId.equals(that.docId) : that.docId != null) return false;
        if (etiology != null ? !etiology.equals(that.etiology) : that.etiology != null) return false;
        if (inspectorName != null ? !inspectorName.equals(that.inspectorName) : that.inspectorName != null)
            return false;
        if (protDate != null ? !protDate.equals(that.protDate) : that.protDate != null) return false;
        if (protNo != null ? !protNo.equals(that.protNo) : that.protNo != null) return false;
        if (protYear != null ? !protYear.equals(that.protYear) : that.protYear != null) return false;
        if (reqStatus != null ? !reqStatus.equals(that.reqStatus) : that.reqStatus != null) return false;
        if (sepeDept != null ? !sepeDept.equals(that.sepeDept) : that.sepeDept != null) return false;
        if (subStatus != null ? !subStatus.equals(that.subStatus) : that.subStatus != null) return false;
        if (submitTime != null ? !submitTime.equals(that.submitTime) : that.submitTime != null) return false;
        if (sundayDate != null ? !sundayDate.equals(that.sundayDate) : that.sundayDate != null) return false;
        if (attachedDocId != null ? !attachedDocId.equals(that.attachedDocId) : that.attachedDocId != null)
            return false;
        if (attachedDocDescr != null ? !attachedDocDescr.equals(that.attachedDocDescr) : that.attachedDocDescr != null)
            return false;
        if (inspAddr != null ? !inspAddr.equals(that.inspAddr) : that.inspAddr != null) return false;
        if (inspAddrD != null ? !inspAddrD.equals(that.inspAddrD) : that.inspAddrD != null) return false;
        if (inspAddrK != null ? !inspAddrK.equals(that.inspAddrK) : that.inspAddrK != null) return false;
        if (inspAddrP != null ? !inspAddrP.equals(that.inspAddrP) : that.inspAddrP != null) return false;
        if (inspAddrPe != null ? !inspAddrPe.equals(that.inspAddrPe) : that.inspAddrPe != null) return false;
        if (inspAddrTk != null ? !inspAddrTk.equals(that.inspAddrTk) : that.inspAddrTk != null) return false;
        if (compAddressDesc != null ? !compAddressDesc.equals(that.compAddressDesc) : that.compAddressDesc != null) return false;
        if (menNum != null ? !menNum.equals(that.menNum) : that.menNum != null) return false;
        if (womenNum != null ? !womenNum.equals(that.womenNum) : that.womenNum != null) return false;
        if (holidayDate != null ? !holidayDate.equals(that.holidayDate) : that.holidayDate != null) return false;
        if (cdText != null ? !cdText.equals(that.cdText) : that.cdText != null) return false;
        if (cdKind != null ? !cdKind.equals(that.cdKind) : that.cdKind != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (companyId != null ? companyId.hashCode() : 0);
        result = 31 * result + (compAfm != null ? compAfm.hashCode() : 0);
        result = 31 * result + (compName != null ? compName.hashCode() : 0);
        result = 31 * result + (compPhone != null ? compPhone.hashCode() : 0);
        result = 31 * result + (compAddr != null ? compAddr.hashCode() : 0);
        result = 31 * result + (compAddrD != null ? compAddrD.hashCode() : 0);
        result = 31 * result + (compAddrK != null ? compAddrK.hashCode() : 0);
        result = 31 * result + (compAddrP != null ? compAddrP.hashCode() : 0);
        result = 31 * result + (compAddrPe != null ? compAddrPe.hashCode() : 0);
        result = 31 * result + (compAddrTk != null ? compAddrTk.hashCode() : 0);
        result = 31 * result + (compEbrBranch0Id != null ? compEbrBranch0Id.hashCode() : 0);
        result = 31 * result + (compEbrBranchId != null ? compEbrBranchId.hashCode() : 0);
        result = 31 * result + (etiology != null ? etiology.hashCode() : 0);
        result = 31 * result + (docId != null ? docId.hashCode() : 0);
        result = 31 * result + (protNo != null ? protNo.hashCode() : 0);
        result = 31 * result + (protDate != null ? protDate.hashCode() : 0);
        result = 31 * result + (protYear != null ? protYear.hashCode() : 0);
        result = 31 * result + (submitTime != null ? submitTime.hashCode() : 0);
        result = 31 * result + (caseId != null ? caseId.hashCode() : 0);
        result = 31 * result + (reqStatus != null ? reqStatus.hashCode() : 0);
        result = 31 * result + (subStatus != null ? subStatus.hashCode() : 0);
        result = 31 * result + (sepeDept != null ? sepeDept.hashCode() : 0);
        result = 31 * result + (compStatement != null ? compStatement.hashCode() : 0);
        result = 31 * result + (sundayDate != null ? sundayDate.hashCode() : 0);
        result = 31 * result + (compDoyId != null ? compDoyId.hashCode() : 0);
        result = 31 * result + (compLegalFormId != null ? compLegalFormId.hashCode() : 0);
        result = 31 * result + (inspectorName != null ? inspectorName.hashCode() : 0);
        result = 31 * result + (compKad != null ? compKad.hashCode() : 0);
        result = 31 * result + (compPhysicalFlag != null ? compPhysicalFlag.hashCode() : 0);
        result = 31 * result + (attachedDocId != null ? attachedDocId.hashCode() : 0);
        result = 31 * result + (attachedDocDescr != null ? attachedDocDescr.hashCode() : 0);
        result = 31 * result + (inspAddr != null ? inspAddr.hashCode() : 0);
        result = 31 * result + (inspAddrD != null ? inspAddrD.hashCode() : 0);
        result = 31 * result + (inspAddrK != null ? inspAddrK.hashCode() : 0);
        result = 31 * result + (inspAddrP != null ? inspAddrP.hashCode() : 0);
        result = 31 * result + (inspAddrPe != null ? inspAddrPe.hashCode() : 0);
        result = 31 * result + (inspAddrTk != null ? inspAddrTk.hashCode() : 0);
        result = 31 * result + (compAddressDesc != null ? compAddressDesc.hashCode() : 0);
        result = 31 * result + (menNum != null ? menNum.hashCode() : 0);
        result = 31 * result + (womenNum != null ? womenNum.hashCode() : 0);
        result = 31 * result + (holidayDate != null ? holidayDate.hashCode() : 0);
        result = 31 * result + (cdText != null ? cdText.hashCode() : 0);
        result = 31 * result + (cdKind != null ? cdKind.hashCode() : 0);
        return result;
    }


}
