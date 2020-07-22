package sepe.domain.company;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by dimitrisf on 12/9/2018.
**/

@Entity
@Table(name = "SP_PTL_V_COMP_GENREQ_EXT", schema = "SP_PTL", catalog = "")
public class SpPtlVCompGenreqExt {
    private Long id;
    private Long companyId;
    private String reprFirstname;
    private String reprLastname;
    private String reprFathername;
    private String reprMothername;
    private String reprAddr;
    private String reprAddrD;
    private String reprAddrK;
    private String reprAddrP;
    private String reprAddrPe;
    private String reprAddrTk;
    private Long reprCardType;
    private String reprCardNumber;
    private String reprPhone;
    private String reprMobile;
    private String reprEmail;
    private String notes;
    private String descr;
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
    private String reprAfm;
    private String reprAmka;

    private String cdText;
    private String cdKind;

    private String spGreqTitle;
    private String spGreqHelpText;
    private Long spGreqTemplDocId;
    private Long spGreqActive;
    private Long spGreqHelplDocId;
    private Long spGreqTe;
    private Long spGreqKe;
    private String spGreqUsertypes;

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
    @Column(name = "REPR_FATHERNAME")
    public String getReprFathername() {
        return reprFathername;
    }

    public void setReprFathername(String reprFathername) {
        this.reprFathername = reprFathername;
    }

    @Basic
    @Column(name = "REPR_MOTHERNAME")
    public String getReprMothername() {
        return reprMothername;
    }

    public void setReprMothername(String reprMothername) {
        this.reprMothername = reprMothername;
    }

    @Basic
    @Column(name = "REPR_ADDR")
    public String getReprAddr() {
        return reprAddr;
    }

    public void setReprAddr(String reprAddr) {
        this.reprAddr = reprAddr;
    }

    @Basic
    @Column(name = "REPR_ADDR_D")
    public String getReprAddrD() {
        return reprAddrD;
    }

    public void setReprAddrD(String reprAddrD) {
        this.reprAddrD = reprAddrD;
    }

    @Basic
    @Column(name = "REPR_ADDR_K")
    public String getReprAddrK() {
        return reprAddrK;
    }

    public void setReprAddrK(String reprAddrK) {
        this.reprAddrK = reprAddrK;
    }

    @Basic
    @Column(name = "REPR_ADDR_P")
    public String getReprAddrP() {
        return reprAddrP;
    }

    public void setReprAddrP(String reprAddrP) {
        this.reprAddrP = reprAddrP;
    }

    @Basic
    @Column(name = "REPR_ADDR_PE")
    public String getReprAddrPe() {
        return reprAddrPe;
    }

    public void setReprAddrPe(String reprAddrPe) {
        this.reprAddrPe = reprAddrPe;
    }

    @Basic
    @Column(name = "REPR_ADDR_TK")
    public String getReprAddrTk() {
        return reprAddrTk;
    }

    public void setReprAddrTk(String reprAddrTk) {
        this.reprAddrTk = reprAddrTk;
    }

    @Basic
    @Column(name = "REPR_CARD_TYPE")
    public Long getReprCardType() {
        return reprCardType;
    }

    public void setReprCardType(Long reprCardType) {
        this.reprCardType = reprCardType;
    }

    @Basic
    @Column(name = "REPR_CARD_NUMBER")
    public String getReprCardNumber() {
        return reprCardNumber;
    }

    public void setReprCardNumber(String reprCardNumber) {
        this.reprCardNumber = reprCardNumber;
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
    @Column(name = "REPR_EMAIL")
    public String getReprEmail() {
        return reprEmail;
    }

    public void setReprEmail(String reprEmail) {
        this.reprEmail = reprEmail;
    }

    @Basic
    @Column(name = "NOTES")
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Basic
    @Column(name = "DESCR")
    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
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

    @Basic
    @Column(name = "REPR_AFM")
    public String getReprAfm() {
        return reprAfm;
    }

    public void setReprAfm(String reprAfm) {
        this.reprAfm = reprAfm;
    }

    @Basic
    @Column(name = "REPR_AMKA")
    public String getReprAmka() {
        return reprAmka;
    }

    public void setReprAmka(String reprAmka) {
        this.reprAmka = reprAmka;
    }

    private Long reqSubject;

    @Basic
    @javax.persistence.Column(name = "REQ_SUBJECT", nullable = true, insertable = true, updatable = true, precision = 0)
    public Long getReqSubject() {
        return reqSubject;
    }

    public void setReqSubject(Long reqSubject) {
        this.reqSubject = reqSubject;
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

    private String complAddressDesc;

    @Basic
    @javax.persistence.Column(name = "COMPL_ADDRESS_DESC", nullable = true, insertable = true, updatable = true, length = 300)
    public String getComplAddressDesc() {
        return complAddressDesc;
    }

    public void setComplAddressDesc(String complAddressDesc) {
        this.complAddressDesc = complAddressDesc;
    }

    private String contestTitle;

    @Basic
    @javax.persistence.Column(name = "CONTEST_TITLE", nullable = true, insertable = true, updatable = true, length = 1000)
    public String getContestTitle() {
        return contestTitle;
    }

    public void setContestTitle(String contestTitle) {
        this.contestTitle = contestTitle;
    }

    private String contestNum;

    @Basic
    @javax.persistence.Column(name = "CONTEST_NUM", nullable = true, insertable = true, updatable = true, length = 1000)
    public String getContestNum() {
        return contestNum;
    }

    public void setContestNum(String contestNum) {
        this.contestNum = contestNum;
    }

    private Timestamp contestExpiryDate;

    @Basic
    @Column(name = "CONTEST_EXPIRY_DATE")
    public Timestamp getContestExpiryDate() {
        return contestExpiryDate;
    }

    public void setContestExpiryDate(Timestamp contestExpiryDate) {
        this.contestExpiryDate = contestExpiryDate;
    }

    private String contestCarrier;

    @Basic
    @javax.persistence.Column(name = "CONTEST_CARRIER", nullable = true, insertable = true, updatable = true, length = 1000)
    public String getContestCarrier() {
        return contestCarrier;
    }

    public void setContestCarrier(String contestCarrier) {
        this.contestCarrier = contestCarrier;
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

    public void setAnswerPdfProtYear(Integer answerPdfProtYear) {
        this.answerPdfProtYear = answerPdfProtYear;
    }

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

    @Basic
    @Column(name = "SP_GREQ_TITLE", nullable = false, insertable = false, updatable = false, length = 150)
    public String getSpGreqTitle() {
        return spGreqTitle;
    }
    public void setSpGreqTitle(String spGreqTitle) {
        this.spGreqTitle = spGreqTitle;
    }

    @Basic
    @Column(name = "SP_GREQ_HELP_TEXT", nullable = false, insertable = false, updatable = false, length = 3000)
    public String getSpGreqHelpText() {
        return spGreqHelpText;
    }
    public void setSpGreqHelpText(String spGreqHelpText) {
        this.spGreqHelpText = spGreqHelpText;
    }

    @Basic
    @Column(name = "SP_GREQ_TEMPL_DOC_ID", nullable = false, insertable = true, updatable = true, precision = 0)
    public Long getSpGreqTemplDocId() {
        return spGreqTemplDocId;
    }
    public void setSpGreqTemplDocId(Long spGreqTemplDocId) {
        this.spGreqTemplDocId = spGreqTemplDocId;
    }

    @Basic
    @Column(name = "SP_GREQ_ACTIVE", nullable = false, insertable = true, updatable = true, precision = 0)
    public Long getSpGreqActive() {
        return spGreqActive;
    }
    public void setSpGreqActive(Long spGreqActive) {
        this.spGreqActive = spGreqActive;
    }

    @Basic
    @Column(name = "SP_GREQ_HELP_DOC_ID", nullable = false, insertable = true, updatable = true, precision = 0)
    public Long getSpGreqHelplDocId() {
        return spGreqHelplDocId;
    }
    public void setSpGreqHelplDocId(Long spGreqHelplDocId) {
        this.spGreqHelplDocId = spGreqHelplDocId;
    }

    @Basic
    @Column(name = "SP_GREQ_TE", nullable = false, insertable = true, updatable = true, precision = 0)
    public Long getSpGreqTe() {
        return spGreqTe;
    }
    public void setSpGreqTe(Long spGreqTe) {
        this.spGreqTe = spGreqTe;
    }

    @Basic
    @Column(name = "SP_GREQ_KE", nullable = false, insertable = true, updatable = true, precision = 0)
    public Long getSpGreqKe() {
        return spGreqKe;
    }
    public void setSpGreqKe(Long spGreqKe) {
        this.spGreqKe = spGreqKe;
    }

    @Basic
    @Column(name = "SP_GREQ_USERTYPES", nullable = false, insertable = false, updatable = false, length = 20)
    public String getSpGreqUsertypes() {
        return spGreqUsertypes;
    }
    public void setSpGreqUsertypes(String spGreqUsertypes) {
        this.spGreqUsertypes = spGreqUsertypes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpPtlVCompGenreqExt that = (SpPtlVCompGenreqExt) o;

        if (id != that.id) return false;
        if (attachedDocId != null ? !attachedDocId.equals(that.attachedDocId) : that.attachedDocId != null)
            return false;
        if (caseId != null ? !caseId.equals(that.caseId) : that.caseId != null) return false;
        if (companyId != null ? !companyId.equals(that.companyId) : that.companyId != null) return false;
        if (descr != null ? !descr.equals(that.descr) : that.descr != null) return false;
        if (docId != null ? !docId.equals(that.docId) : that.docId != null) return false;
        if (notes != null ? !notes.equals(that.notes) : that.notes != null) return false;
        if (protDate != null ? !protDate.equals(that.protDate) : that.protDate != null) return false;
        if (protNo != null ? !protNo.equals(that.protNo) : that.protNo != null) return false;
        if (protYear != null ? !protYear.equals(that.protYear) : that.protYear != null) return false;
        if (reprAddr != null ? !reprAddr.equals(that.reprAddr) : that.reprAddr != null) return false;
        if (reprAddrD != null ? !reprAddrD.equals(that.reprAddrD) : that.reprAddrD != null) return false;
        if (reprAddrK != null ? !reprAddrK.equals(that.reprAddrK) : that.reprAddrK != null) return false;
        if (reprAddrP != null ? !reprAddrP.equals(that.reprAddrP) : that.reprAddrP != null) return false;
        if (reprAddrPe != null ? !reprAddrPe.equals(that.reprAddrPe) : that.reprAddrPe != null) return false;
        if (reprAddrTk != null ? !reprAddrTk.equals(that.reprAddrTk) : that.reprAddrTk != null) return false;
        if (reprAfm != null ? !reprAfm.equals(that.reprAfm) : that.reprAfm != null) return false;
        if (reprAmka != null ? !reprAmka.equals(that.reprAmka) : that.reprAmka != null) return false;
        if (reprCardNumber != null ? !reprCardNumber.equals(that.reprCardNumber) : that.reprCardNumber != null)
            return false;
        if (reprCardType != null ? !reprCardType.equals(that.reprCardType) : that.reprCardType != null) return false;
        if (reprFathername != null ? !reprFathername.equals(that.reprFathername) : that.reprFathername != null)
            return false;
        if (reprFirstname != null ? !reprFirstname.equals(that.reprFirstname) : that.reprFirstname != null)
            return false;
        if (reprLastname != null ? !reprLastname.equals(that.reprLastname) : that.reprLastname != null) return false;
        if (reprMobile != null ? !reprMobile.equals(that.reprMobile) : that.reprMobile != null) return false;
        if (reprEmail != null ? !reprEmail.equals(that.reprEmail) : that.reprEmail != null) return false;
        if (reprMothername != null ? !reprMothername.equals(that.reprMothername) : that.reprMothername != null)
            return false;
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
        if (contestTitle != null ? !contestTitle.equals(that.contestTitle) : that.contestTitle != null) return false;
        if (contestNum != null ? !contestNum.equals(that.contestNum) : that.contestNum != null) return false;
        if (contestExpiryDate != null ? !contestExpiryDate.equals(that.contestExpiryDate) : that.contestExpiryDate != null) return false;
        if (contestCarrier != null ? !contestCarrier.equals(that.contestCarrier) : that.contestCarrier != null) return false;
        if (answerPdfDocId != null ? !answerPdfDocId.equals(that.answerPdfDocId) : that.answerPdfDocId != null) return false;
        if (answerPdfProtNo != null ? !answerPdfProtNo.equals(that.answerPdfProtNo) : that.answerPdfProtNo != null) return false;
        if (answerPdfProtYear != null ? !answerPdfProtYear.equals(that.answerPdfProtYear) : that.answerPdfProtYear != null) return false;
        if (cdText != null ? !cdText.equals(that.cdText) : that.cdText != null) return false;
        if (cdKind != null ? !cdKind.equals(that.cdKind) : that.cdKind != null) return false;
        if (spGreqTitle != null ? !spGreqTitle.equals(that.spGreqTitle) : that.spGreqTitle != null) return false;
        if (spGreqHelpText != null ? !spGreqHelpText.equals(that.spGreqHelpText) : that.spGreqHelpText != null) return false;
        if (spGreqTemplDocId != null ? !spGreqTemplDocId.equals(that.spGreqTemplDocId) : that.spGreqTemplDocId != null) return false;
        if (spGreqActive != null ? !spGreqActive.equals(that.spGreqActive) : that.spGreqActive != null) return false;
        if (spGreqHelplDocId != null ? !spGreqHelplDocId.equals(that.spGreqHelplDocId) : that.spGreqHelplDocId != null) return false;
        if (spGreqTe != null ? !spGreqTe.equals(that.spGreqTe) : that.spGreqTe != null) return false;
        if (spGreqKe != null ? !spGreqKe.equals(that.spGreqKe) : that.spGreqKe != null) return false;
        if (spGreqUsertypes != null ? !spGreqUsertypes.equals(that.spGreqUsertypes) : that.spGreqUsertypes != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (companyId != null ? companyId.hashCode() : 0);
        result = 31 * result + (reprFirstname != null ? reprFirstname.hashCode() : 0);
        result = 31 * result + (reprLastname != null ? reprLastname.hashCode() : 0);
        result = 31 * result + (reprFathername != null ? reprFathername.hashCode() : 0);
        result = 31 * result + (reprMothername != null ? reprMothername.hashCode() : 0);
        result = 31 * result + (reprAddr != null ? reprAddr.hashCode() : 0);
        result = 31 * result + (reprAddrD != null ? reprAddrD.hashCode() : 0);
        result = 31 * result + (reprAddrK != null ? reprAddrK.hashCode() : 0);
        result = 31 * result + (reprAddrP != null ? reprAddrP.hashCode() : 0);
        result = 31 * result + (reprAddrPe != null ? reprAddrPe.hashCode() : 0);
        result = 31 * result + (reprAddrTk != null ? reprAddrTk.hashCode() : 0);
        result = 31 * result + (reprCardType != null ? reprCardType.hashCode() : 0);
        result = 31 * result + (reprCardNumber != null ? reprCardNumber.hashCode() : 0);
        result = 31 * result + (reprPhone != null ? reprPhone.hashCode() : 0);
        result = 31 * result + (reprMobile != null ? reprMobile.hashCode() : 0);
        result = 31 * result + (reprEmail != null ? reprEmail.hashCode() : 0);
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        result = 31 * result + (descr != null ? descr.hashCode() : 0);
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
        result = 31 * result + (reprAfm != null ? reprAfm.hashCode() : 0);
        result = 31 * result + (reprAmka != null ? reprAmka.hashCode() : 0);
        result = 31 * result + (reqSubject != null ? reqSubject.hashCode() : 0);
        result = 31 * result + (branch0Id != null ? branch0Id.hashCode() : 0);
        result = 31 * result + (branch1Id != null ? branch1Id.hashCode() : 0);
        result = 31 * result + (inspAddr != null ? inspAddr.hashCode() : 0);
        result = 31 * result + (inspAddrD != null ? inspAddrD.hashCode() : 0);
        result = 31 * result + (inspAddrK != null ? inspAddrK.hashCode() : 0);
        result = 31 * result + (inspAddrP != null ? inspAddrP.hashCode() : 0);
        result = 31 * result + (inspAddrPe != null ? inspAddrPe.hashCode() : 0);
        result = 31 * result + (inspAddrTk != null ? inspAddrTk.hashCode() : 0);
        result = 31 * result + (contestTitle != null ? contestTitle.hashCode() : 0);
        result = 31 * result + (contestNum != null ? contestNum.hashCode() : 0);
        result = 31 * result + (contestExpiryDate != null ? contestExpiryDate.hashCode() : 0);
        result = 31 * result + (contestCarrier != null ? contestCarrier.hashCode() : 0);
        result = 31 * result + (answerPdfDocId != null ? answerPdfDocId.hashCode() : 0);
        result = 31 * result + (answerPdfProtNo != null ? answerPdfProtNo.hashCode() : 0);
        result = 31 * result + (answerPdfProtYear != null ? answerPdfProtYear.hashCode() : 0);
        result = 31 * result + (cdText != null ? cdText.hashCode() : 0);
        result = 31 * result + (cdKind != null ? cdKind.hashCode() : 0);
        result = 31 * result + (spGreqTitle != null ? spGreqTitle.hashCode() : 0);
        result = 31 * result + (spGreqHelpText != null ? spGreqHelpText.hashCode() : 0);
        result = 31 * result + (spGreqTemplDocId != null ? spGreqTemplDocId.hashCode() : 0);
        result = 31 * result + (spGreqActive != null ? spGreqActive.hashCode() : 0);
        result = 31 * result + (spGreqHelplDocId != null ? spGreqHelplDocId.hashCode() : 0);
        result = 31 * result + (spGreqTe != null ? spGreqTe.hashCode() : 0);
        result = 31 * result + (spGreqKe != null ? spGreqKe.hashCode() : 0);
        result = 31 * result + (spGreqUsertypes != null ? spGreqUsertypes.hashCode() : 0);
        return result;
    }
}
