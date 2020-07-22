package sepe.domain.company;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created by Nick on 5/4/2015.
 */
@Entity
@javax.persistence.Table(name = "SP_PTL_COMP_COMPLAINT", schema = "SP_PTL", catalog = "")
public class SpPtlCompComplaint {
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

    private Integer compHasLabourUnion;

    @Basic
    @javax.persistence.Column(name = "COMP_HAS_LABOUR_UNION")
    public Integer getCompHasLabourUnion() {
        return compHasLabourUnion;
    }

    public void setCompHasLabourUnion(Integer compHasLabourUnion) {
        this.compHasLabourUnion = compHasLabourUnion;
    }

    private Integer employeeNum;

    @Basic
    @javax.persistence.Column(name = "EMPLOYEE_NUM")
    public Integer getEmployeeNum() {
        return employeeNum;
    }

    public void setEmployeeNum(Integer employeeNum) {
        this.employeeNum = employeeNum;
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

    private String complMatter;

    @Basic
    @javax.persistence.Column(name = "COMPL_MATTER")
    public String getComplMatter() {
        return complMatter;
    }

    public void setComplMatter(String complMatter) {
        this.complMatter = complMatter;
    }

    private String complDescr;

    @Basic
    @javax.persistence.Column(name = "COMPL_DESCR")
    public String getComplDescr() {
        return complDescr;
    }

    public void setComplDescr(String complDescr) {
        this.complDescr = complDescr;
    }


    private Integer complInvlovesSafetyInsp;

    @Basic
    @javax.persistence.Column(name = "COMPL_INVLOVES_SAFETY_INSP")
    public Integer getComplInvlovesSafetyInsp() {
        return complInvlovesSafetyInsp;
    }

    public void setComplInvlovesSafetyInsp(Integer complInvlovesSafetyInsp) {
        this.complInvlovesSafetyInsp = complInvlovesSafetyInsp;
    }

    private Integer complInvolvesLabRelations;

    @Basic
    @javax.persistence.Column(name = "COMPL_INVOLVES_LAB_RELATIONS")
    public Integer getComplInvolvesLabRelations() {
        return complInvolvesLabRelations;
    }

    public void setComplInvolvesLabRelations(Integer complInvolvesLabRelations) {
        this.complInvolvesLabRelations = complInvolvesLabRelations;
    }

    private Integer complIsAnonymous;

    @Basic
    @javax.persistence.Column(name = "COMPL_IS_ANONYMOUS")
    public Integer getComplIsAnonymous() {
        return complIsAnonymous;
    }

    public void setComplIsAnonymous(Integer complIsAnonymous) {
        this.complIsAnonymous = complIsAnonymous;
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

    private String empPhone;

    @Basic
    @javax.persistence.Column(name = "EMP_PHONE")
    public String getEmpPhone() {
        return empPhone;
    }

    public void setEmpPhone(String empPhone) {
        this.empPhone = empPhone;
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

    private String inspAddr;

    @Basic
    @javax.persistence.Column(name = "INSP_ADDR")
    public String getInspAddr() {
        return inspAddr;
    }

    public void setInspAddr(String inspAddr) {
        this.inspAddr = inspAddr;
    }

    private String inspAddrD;

    @Basic
    @javax.persistence.Column(name = "INSP_ADDR_D")
    public String getInspAddrD() {
        return inspAddrD;
    }

    public void setInspAddrD(String inspAddrD) {
        this.inspAddrD = inspAddrD;
    }

    private String inspAddrK;

    @Basic
    @javax.persistence.Column(name = "INSP_ADDR_K")
    public String getInspAddrK() {
        return inspAddrK;
    }

    public void setInspAddrK(String inspAddrK) {
        this.inspAddrK = inspAddrK;
    }

    private String inspAddrP;

    @Basic
    @javax.persistence.Column(name = "INSP_ADDR_P")
    public String getInspAddrP() {
        return inspAddrP;
    }

    public void setInspAddrP(String inspAddrP) {
        this.inspAddrP = inspAddrP;
    }

    private String inspAddrPe;

    @Basic
    @javax.persistence.Column(name = "INSP_ADDR_PE")
    public String getInspAddrPe() {
        return inspAddrPe;
    }

    public void setInspAddrPe(String inspAddrPe) {
        this.inspAddrPe = inspAddrPe;
    }

    private String inspAddrTk;

    @Basic
    @javax.persistence.Column(name = "INSP_ADDR_TK")
    public String getInspAddrTk() {
        return inspAddrTk;
    }

    public void setInspAddrTk(String inspAddrTk) {
        this.inspAddrTk = inspAddrTk;
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

    private String empFirstname;

    @Basic
    @javax.persistence.Column(name = "EMP_FIRSTNAME")
    public String getEmpFirstname() {
        return empFirstname;
    }

    public void setEmpFirstname(String empFirstname) {
        this.empFirstname = empFirstname;
    }

    private String empLastname;

    @Basic
    @javax.persistence.Column(name = "EMP_LASTNAME")
    public String getEmpLastname() {
        return empLastname;
    }

    public void setEmpLastname(String empLastname) {
        this.empLastname = empLastname;
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

    private Long docIdAttached;

    @Basic
    @javax.persistence.Column(name = "DOC_ID_ATTACHED")
    public Long getDocIdAttached() {
        return docIdAttached;
    }

    public void setDocIdAttached(Long docIdAttached) {
        this.docIdAttached = docIdAttached;
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

    private String empMobile;

    @Basic
    @javax.persistence.Column(name = "EMP_MOBILE")
    public String getEmpMobile() {
        return empMobile;
    }

    public void setEmpMobile(String empMobile) {
        this.empMobile = empMobile;
    }

    private String empAmka;

    @Basic
    @javax.persistence.Column(name = "EMP_AMKA")
    public String getEmpAmka() {
        return empAmka;
    }

    public void setEmpAmka(String empAmka) {
        this.empAmka = empAmka;
    }

    private String empAfm;

    @Basic
    @javax.persistence.Column(name = "EMP_AFM")
    public String getEmpAfm() {
        return empAfm;
    }

    public void setEmpAfm(String empAfm) {
        this.empAfm = empAfm;
    }

    private String empSexDesc;

    @Basic
    @javax.persistence.Column(name = "EMP_SEX_DESC")
    public String getEmpSexDesc() {
        return empSexDesc;
    }

    public void setEmpSexDesc(String empSexDesc) {
        this.empSexDesc = empSexDesc;
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

    private String empCitizenshipCd;

    @Basic
    @javax.persistence.Column(name = "EMP_CITIZENSHIP_CD")
    public String getEmpCitizenshipCd() {
        return empCitizenshipCd;
    }

    public void setEmpCitizenshipCd(String empCitizenshipCd) {
        this.empCitizenshipCd = empCitizenshipCd;
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

    private Long empCardType;

    @Basic
    @javax.persistence.Column(name = "EMP_CARD_TYPE")
    public Long getEmpCardType() {
        return empCardType;
    }

    public void setEmpCardType(Long empCardType) {
        this.empCardType = empCardType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpPtlCompComplaint that = (SpPtlCompComplaint) o;

        if (id != that.id) return false;
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
        if (compHasLabourUnion != null ? !compHasLabourUnion.equals(that.compHasLabourUnion) : that.compHasLabourUnion != null)
            return false;
        if (compName != null ? !compName.equals(that.compName) : that.compName != null) return false;
        if (compPhone != null ? !compPhone.equals(that.compPhone) : that.compPhone != null) return false;
        if (companyId != null ? !companyId.equals(that.companyId) : that.companyId != null) return false;
        if (complDescr != null ? !complDescr.equals(that.complDescr) : that.complDescr != null) return false;

        if (complInvlovesSafetyInsp != null ? !complInvlovesSafetyInsp.equals(that.complInvlovesSafetyInsp) : that.complInvlovesSafetyInsp != null)
            return false;
        if (complInvolvesLabRelations != null ? !complInvolvesLabRelations.equals(that.complInvolvesLabRelations) : that.complInvolvesLabRelations != null)
            return false;
        if (complIsAnonymous != null ? !complIsAnonymous.equals(that.complIsAnonymous) : that.complIsAnonymous != null)
            return false;
        if (complMatter != null ? !complMatter.equals(that.complMatter) : that.complMatter != null) return false;
        if (docId != null ? !docId.equals(that.docId) : that.docId != null) return false;
        if (docIdAttached != null ? !docIdAttached.equals(that.docIdAttached) : that.docIdAttached != null)
            return false;
        if (empAfm != null ? !empAfm.equals(that.empAfm) : that.empAfm != null) return false;
        if (empAge != null ? !empAge.equals(that.empAge) : that.empAge != null) return false;
        if (empAmka != null ? !empAmka.equals(that.empAmka) : that.empAmka != null) return false;
        if (empCardNumber != null ? !empCardNumber.equals(that.empCardNumber) : that.empCardNumber != null)
            return false;
        if (empCardType != null ? !empCardType.equals(that.empCardType) : that.empCardType != null) return false;
        if (empCitizenshipCd != null ? !empCitizenshipCd.equals(that.empCitizenshipCd) : that.empCitizenshipCd != null)
            return false;
        if (empFirstname != null ? !empFirstname.equals(that.empFirstname) : that.empFirstname != null) return false;
        if (empLastname != null ? !empLastname.equals(that.empLastname) : that.empLastname != null) return false;
        if (empMobile != null ? !empMobile.equals(that.empMobile) : that.empMobile != null) return false;
        if (empPhone != null ? !empPhone.equals(that.empPhone) : that.empPhone != null) return false;
        if (empSexDesc != null ? !empSexDesc.equals(that.empSexDesc) : that.empSexDesc != null) return false;
        if (inspAddr != null ? !inspAddr.equals(that.inspAddr) : that.inspAddr != null) return false;
        if (inspAddrD != null ? !inspAddrD.equals(that.inspAddrD) : that.inspAddrD != null) return false;
        if (inspAddrK != null ? !inspAddrK.equals(that.inspAddrK) : that.inspAddrK != null) return false;
        if (inspAddrP != null ? !inspAddrP.equals(that.inspAddrP) : that.inspAddrP != null) return false;
        if (inspAddrPe != null ? !inspAddrPe.equals(that.inspAddrPe) : that.inspAddrPe != null) return false;
        if (inspAddrTk != null ? !inspAddrTk.equals(that.inspAddrTk) : that.inspAddrTk != null) return false;
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
        if (employeeNum != null ? !employeeNum.equals(that.employeeNum) : that.employeeNum != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (employeeNum != null ? employeeNum.hashCode() : 0);
        result = 31 * result + (compAddr != null ? compAddr.hashCode() : 0);
        result = 31 * result + (compAddrD != null ? compAddrD.hashCode() : 0);
        result = 31 * result + (compAddrK != null ? compAddrK.hashCode() : 0);
        result = 31 * result + (compAddrP != null ? compAddrP.hashCode() : 0);
        result = 31 * result + (compAddrPe != null ? compAddrPe.hashCode() : 0);
        result = 31 * result + (compAddrTk != null ? compAddrTk.hashCode() : 0);
        result = 31 * result + (compAfm != null ? compAfm.hashCode() : 0);
        result = 31 * result + (compHasLabourUnion != null ? compHasLabourUnion.hashCode() : 0);
        result = 31 * result + (compName != null ? compName.hashCode() : 0);
        result = 31 * result + (compPhone != null ? compPhone.hashCode() : 0);
        result = 31 * result + (complMatter != null ? complMatter.hashCode() : 0);
        result = 31 * result + (complDescr != null ? complDescr.hashCode() : 0);
        result = 31 * result + (complInvlovesSafetyInsp != null ? complInvlovesSafetyInsp.hashCode() : 0);
        result = 31 * result + (complInvolvesLabRelations != null ? complInvolvesLabRelations.hashCode() : 0);
        result = 31 * result + (complIsAnonymous != null ? complIsAnonymous.hashCode() : 0);
        result = 31 * result + (compAme != null ? compAme.hashCode() : 0);
        result = 31 * result + (empPhone != null ? empPhone.hashCode() : 0);
        result = 31 * result + (sepeDept != null ? sepeDept.hashCode() : 0);
        result = 31 * result + (inspAddr != null ? inspAddr.hashCode() : 0);
        result = 31 * result + (inspAddrD != null ? inspAddrD.hashCode() : 0);
        result = 31 * result + (inspAddrK != null ? inspAddrK.hashCode() : 0);
        result = 31 * result + (inspAddrP != null ? inspAddrP.hashCode() : 0);
        result = 31 * result + (inspAddrPe != null ? inspAddrPe.hashCode() : 0);
        result = 31 * result + (inspAddrTk != null ? inspAddrTk.hashCode() : 0);
        result = 31 * result + (protDate != null ? protDate.hashCode() : 0);
        result = 31 * result + (protNo != null ? protNo.hashCode() : 0);
        result = 31 * result + (reqStatus != null ? reqStatus.hashCode() : 0);
        result = 31 * result + (subStatus != null ? subStatus.hashCode() : 0);
        result = 31 * result + (companyId != null ? companyId.hashCode() : 0);
        result = 31 * result + (empFirstname != null ? empFirstname.hashCode() : 0);
        result = 31 * result + (empLastname != null ? empLastname.hashCode() : 0);
        result = 31 * result + (protYear != null ? protYear.hashCode() : 0);
        result = 31 * result + (submitTime != null ? submitTime.hashCode() : 0);
        result = 31 * result + (docId != null ? docId.hashCode() : 0);
        result = 31 * result + (caseId != null ? caseId.hashCode() : 0);
        result = 31 * result + (rtstakLevel1 != null ? rtstakLevel1.hashCode() : 0);
        result = 31 * result + (rtstakLevel2 != null ? rtstakLevel2.hashCode() : 0);
        result = 31 * result + (rtstakLevel3 != null ? rtstakLevel3.hashCode() : 0);
        result = 31 * result + (rtstakLevel4 != null ? rtstakLevel4.hashCode() : 0);
        result = 31 * result + (rtstakLevel5 != null ? rtstakLevel5.hashCode() : 0);
        result = 31 * result + (docIdAttached != null ? docIdAttached.hashCode() : 0);
        result = 31 * result + (branch0Id != null ? branch0Id.hashCode() : 0);
        result = 31 * result + (branch1Id != null ? branch1Id.hashCode() : 0);
        result = 31 * result + (empMobile != null ? empMobile.hashCode() : 0);
        result = 31 * result + (empAmka != null ? empAmka.hashCode() : 0);
        result = 31 * result + (empAfm != null ? empAfm.hashCode() : 0);
        result = 31 * result + (empSexDesc != null ? empSexDesc.hashCode() : 0);
        result = 31 * result + (empAge != null ? empAge.hashCode() : 0);
        result = 31 * result + (empCitizenshipCd != null ? empCitizenshipCd.hashCode() : 0);
        result = 31 * result + (empCardNumber != null ? empCardNumber.hashCode() : 0);
        result = 31 * result + (empCardType != null ? empCardType.hashCode() : 0);
        return result;
    }
}
