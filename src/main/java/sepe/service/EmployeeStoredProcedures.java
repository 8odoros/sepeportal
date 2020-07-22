package sepe.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sepe.config.Constants;
import sepe.domain.employee.*;
import sepe.repository.general.RtStakLevel5AA;

import javax.annotation.Nonnull;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Service
public class EmployeeStoredProcedures {
    @Autowired
    EntityManager em;

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeStoredProcedures.class);
    private final RtStakLevel5AA rtStakLevelRepo;

    @Autowired
    public EmployeeStoredProcedures(
        @Nonnull final RtStakLevel5AA rtStakLevelRepo
    ){
        this.rtStakLevelRepo = rtStakLevelRepo;
    }

    //Employee Complaint
    public TEmployeeComplaint procIntArchiveComplaint(TEmployeeComplaint ec) throws Exception {
        StoredProcedureQuery proc = em.createStoredProcedureQuery("SN_proc_int_archive_complaint");
        proc.registerStoredProcedureParameter("SP_COM_TYPE_DESC", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COM_MEANS_DESC", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COM_TE", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COM_KE", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COM_DESCRIPTION", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COM_TIME_REASON", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COM_MATTERS", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COM_ATTRIBUTE_CODE", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COM_PORTAL_UID", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COM_NAME", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COM_SURNAME", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COM_PHONE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COM_AUDIT_ADDRESS", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COM_AUDIT_ZIP_CODE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COM_AUDIT_KALLIKRATIS", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COM_TAXATION_NUMBER", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COM_FULLNAME", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COM_STAKOD_DESC", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COM_EMPLOYEE_NO", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COM_UNION", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COM_EMP_PHONE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COM_EMP_ADDRESS", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COM_EMP_ZIP_CODE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COM_EMP_KALLIKRATIS", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COM_ATTACHED_DOC_ID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COM_AUDIT_ADDRESS_DESC", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EBR_BRANCH0_ID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EBR_BRANCH_ID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COM_DOC_ID", Long.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_COM_PROTOCOL_NUMBER", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_COM_PROTOCOL_DATE", Date.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_COM_PROTOCOL_YEAR", Integer.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_COM_TIME", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_COM_DEPARTMENT", Integer.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("out_errMsg", String.class, ParameterMode.OUT);

        proc.setParameter("SP_COM_TYPE_DESC", ec.getComplIsAnonymous().toString());
        proc.setParameter("SP_COM_MEANS_DESC", "2");
        proc.setParameter("SP_COM_TE", ec.getComplInvlovesSafetyInsp() == null ? 0 : ec.getComplInvlovesSafetyInsp());
        proc.setParameter("SP_COM_KE", ec.getComplInvolvesLabRelations() == null ? 0 : ec.getComplInvolvesLabRelations());
        proc.setParameter("SP_COM_DESCRIPTION", ec.getComplDescr());
        proc.setParameter("SP_COM_AUDIT_ADDRESS_DESC", ec.getComplAddressDesc());
        proc.setParameter("SP_COM_TIME_REASON", ec.getComplInspTime());
        proc.setParameter("SP_COM_MATTERS", ec.getComplMatter().replace("\"", "").replace("[", "").replace("]", ""));
        proc.setParameter("SP_COM_ATTRIBUTE_CODE", Constants.USER_TYPE.EMPLOYEE.getCode());
        proc.setParameter("SP_COM_PORTAL_UID", ec.getUserId().intValue());
        proc.setParameter("SP_COM_NAME", ec.getEmpFirstname());
        proc.setParameter("SP_COM_SURNAME", ec.getEmpLastname());
        proc.setParameter("SP_COM_PHONE", ec.getEmpPhone());
        proc.setParameter("SP_COM_AUDIT_ADDRESS", ec.getCompAddr());
        proc.setParameter("SP_COM_AUDIT_ZIP_CODE", ec.getCompAddrTk());
        proc.setParameter("SP_COM_AUDIT_KALLIKRATIS", Constants.StringIsNullOrEmpty(ec.getInspAddrK()) ? 0 : Long.parseLong(ec.getInspAddrK()));
        proc.setParameter("SP_COM_TAXATION_NUMBER", ec.getCompAfm());
        proc.setParameter("SP_COM_FULLNAME", ec.getCompName());
        proc.setParameter("SP_COM_STAKOD_DESC", ec.getRtstakLevel5());
        proc.setParameter("SP_COM_EMPLOYEE_NO", ec.getCompEmpNum().isEmpty() ? 0 : Long.parseLong(ec.getCompEmpNum()));
        proc.setParameter("SP_COM_UNION", ec.getCompHasLabourUnion() == null ? "" : ec.getCompHasLabourUnion().toString());
        proc.setParameter("SP_COM_EMP_PHONE", ec.getEmpPhone());
        proc.setParameter("SP_COM_EMP_ADDRESS", ec.getCompAddr());
        proc.setParameter("SP_COM_EMP_ZIP_CODE", ec.getCompAddrTk());
        proc.setParameter("SP_COM_EMP_KALLIKRATIS", Constants.StringIsNullOrEmpty(ec.getCompAddrK()) ? 0 : Long.parseLong(ec.getCompAddrK()));
        proc.setParameter("SP_COM_ATTACHED_DOC_ID", ec.getDocIdAttached().longValue());
        proc.setParameter("SP_EBR_BRANCH0_ID", ec.getBranch0Id());
        proc.setParameter("SP_EBR_BRANCH_ID", ec.getBranch1Id());

        proc.execute();
        String out_errMsg = (String) proc.getOutputParameterValue("out_errMsg");
        if (out_errMsg == null || out_errMsg.isEmpty()) {
            Long docId = null;
            Long department = null;
            if(null == proc.getOutputParameterValue("SP_COM_DOC_ID")) {
                docId = (Long) proc.getOutputParameterValue("SP_COM_DOC_ID");
            }
            else {
                docId = new Long((Integer) proc.getOutputParameterValue("SP_COM_DOC_ID"));
            }

            String protoclNumber = (String) proc.getOutputParameterValue("SP_COM_PROTOCOL_NUMBER");
            Date protoclDate = (Date) proc.getOutputParameterValue("SP_COM_PROTOCOL_DATE");
            Integer year = (Integer) proc.getOutputParameterValue("SP_COM_PROTOCOL_YEAR");
            String time = (String) proc.getOutputParameterValue("SP_COM_TIME");
            if(null == proc.getOutputParameterValue("SP_COM_DEPARTMENT")) {
                department = (Long) proc.getOutputParameterValue("SP_COM_DEPARTMENT");
            }
            else {
                department = new Long((Integer) proc.getOutputParameterValue("SP_COM_DEPARTMENT"));
            }

            ec.setDocId(docId);
            ec.setProtNo(protoclNumber);
            ec.setProtDate(new Timestamp(protoclDate.getTime()));
            ec.setProtYear(year);
            ec.setSubmitTime(time);
            ec.setSepeDept(department);
        } else {//handle error
            //parse string gia mia logiki exception
            throw new Exception(out_errMsg);
        }

        return ec;
    }

    /*private String  employeeDisputeReasonsToString(TEmployeeDispute o){
        String finalStr = new String("");
        StringBuilder str = new StringBuilder("");
        Set<TEmployeeDisputeReason> tEmployeeDisputeReason = new HashSet<TEmployeeDisputeReason>(0);

        tEmployeeDisputeReason = o.getEmployeeDisputeReason();
        for (Iterator<TEmployeeDisputeReason> it = tEmployeeDisputeReason.iterator(); it.hasNext(); ) {
            TEmployeeDisputeReason reasonTmp = it.next();
            str.append(reasonTmp.getReasonId());
            if(it.hasNext())
                str.append(",");
        }

        finalStr = str.toString();
        LOGGER.debug("^^^^^^^^^^^^^ [" + finalStr + "]");
        return finalStr;
    }*/

    //Employee Dispute
    public TEmployeeDispute procIntArchiveLad(TEmployeeDispute o) throws Exception {
        StoredProcedureQuery proc = em.createStoredProcedureQuery("SN_proc_int_archive_lad");
        proc.registerStoredProcedureParameter("SP_LAD_APPLICANT_TYPE_CODE", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_LAD_APPLICATION_TYPE_CODE", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_LAD_DELIVERY_TYPE_CODE", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_LAD_PORTAL_UID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_LAD_AMKA", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_LAD_TAX_NUMBER", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_LAD_NAME", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_LAD_SURNAME", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_LAD_SPECIALTY", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_LAD_SEX_CODE", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_LAD_MARITAL_STATUS_CODE", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_LAD_AGE", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_LAD_CITIZENSHIP_CODE", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_LAD_CHILDREN_NUM", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_LAD_NET_SALARY", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_LAD_GROSS_SALARY", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_LAD_PERIOD_FROM", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_LAD_PERIOD_TO", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_LAD_EMPLOYMENT_TIME", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_LAD_HOME_ADDRESS", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_LAD_HOME_ZIP_CODE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_LAD_HOME_KALLIKRATIS", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_LAD_CARD_TYPE", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_LAD_CARD_NUMBER", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_LAD_PHONE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_LAD_MOBILE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_LAD_EMP_TAX_NUMBER", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_LAD_EMP_FULL_NAME", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_LAD_EMP_RTSTAK_LEVEL_5", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_LAD_EIN_FULL_NAME", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_LAD_EMP_ADDRESS", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_LAD_EMP_ZIP_CODE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_LAD_EMP_KALLIKRATIS", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_LAD_EMP_TAX_OFFICE_CODE", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_LAD_EMP_AME", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_LAD_EMP_PHONE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_LAD_EBR_BRANCH0_ID", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_LAD_EBR_BRANCH_ID", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_LAD_WPLACE_ADDRESS", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_LAD_WPLACE_ZIP_CODE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_LAD_WPLACE_KALLIKRATIS", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_LAD_SUBJECTS", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_LAD_GROUP_DISCUSSION_FLAG", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_LAD_COMMENT", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_LAD_ATTACHED_DOC_ID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_LAD_DOC_ID", Long.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_LAD_PROTOCOL_NUMBER", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_LAD_PROTOCOL_DATE", Date.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_LAD_PROTOCOL_YEAR", Integer.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_LAD_TIME", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_LAD_DEPARTMENT", Integer.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("out_errMsg", String.class, ParameterMode.OUT);

        proc.setParameter("SP_LAD_APPLICANT_TYPE_CODE", 1);
        proc.setParameter("SP_LAD_APPLICATION_TYPE_CODE", 1);
        proc.setParameter("SP_LAD_DELIVERY_TYPE_CODE", 2);
        proc.setParameter("SP_LAD_PORTAL_UID", o.getUserId());
        proc.setParameter("SP_LAD_AMKA", o.getEmpAmka());
        proc.setParameter("SP_LAD_TAX_NUMBER", o.getEmpAfm());
        proc.setParameter("SP_LAD_NAME", o.getEmpFirstname() );
        proc.setParameter("SP_LAD_SURNAME",  o.getEmpLastname());
        proc.setParameter("SP_LAD_SPECIALTY", o.getEmpSpecialityId().longValue());
        proc.setParameter("SP_LAD_SEX_CODE", Constants.StringIsNullOrEmpty(o.getEmpSex()) ? 0 : Integer.valueOf(o.getEmpSex()));
        proc.setParameter("SP_LAD_MARITAL_STATUS_CODE", o.getEmpMaritalStatus().intValue());
        proc.setParameter("SP_LAD_AGE", o.getEmpAge().intValue());
        proc.setParameter("SP_LAD_CITIZENSHIP_CODE", o.getEmpCitizenship().intValue());
        proc.setParameter("SP_LAD_CHILDREN_NUM", String.valueOf(o.getEmpChildrenNum()));
        proc.setParameter("SP_LAD_NET_SALARY", o.getEmpNetSalary());
        proc.setParameter("SP_LAD_GROSS_SALARY", o.getEmpGrossSalary());
        proc.setParameter("SP_LAD_PERIOD_FROM", new Date(o.getEmpFromDate().getTime()));
        proc.setParameter("SP_LAD_PERIOD_TO", new Date(o.getEmpUntilDate().getTime()));
        proc.setParameter("SP_LAD_EMPLOYMENT_TIME", o.getEmpWorkingHours());
        proc.setParameter("SP_LAD_HOME_ADDRESS", o.getEmpAddr());
        proc.setParameter("SP_LAD_HOME_ZIP_CODE", o.getEmpAddrTk());
        proc.setParameter("SP_LAD_HOME_KALLIKRATIS", Constants.StringIsNullOrEmpty(o.getEmpAddrK()) ? -1 : Integer.parseInt(o.getEmpAddrK()));
        proc.setParameter("SP_LAD_CARD_TYPE", o.getEmpCardType().intValue());
        proc.setParameter("SP_LAD_CARD_NUMBER", o.getEmpCardNumber());
        proc.setParameter("SP_LAD_PHONE", o.getEmpPhone());
        proc.setParameter("SP_LAD_MOBILE", o.getEmpMobile());
        proc.setParameter("SP_LAD_EMP_TAX_NUMBER",o.getCompAfm());
        proc.setParameter("SP_LAD_EMP_FULL_NAME", o.getCompName());
        Long levelAA = rtStakLevelRepo.getAA(o.getRtstakLevel5());
        proc.setParameter("SP_LAD_EMP_RTSTAK_LEVEL_5", levelAA);
        proc.setParameter("SP_LAD_EIN_FULL_NAME", o.getCompRepresentative());
        proc.setParameter("SP_LAD_EMP_ADDRESS",o.getCompAddr() );
        proc.setParameter("SP_LAD_EMP_ZIP_CODE", o.getCompAddrTk());
        proc.setParameter("SP_LAD_EMP_KALLIKRATIS", Constants.StringIsNullOrEmpty(o.getCompAddrK()) ? -1 : Integer.parseInt(o.getCompAddrK()));
        proc.setParameter("SP_LAD_EMP_TAX_OFFICE_CODE",Constants.StringIsNullOrEmpty(o.getCompDoy()) ? -1 : Integer.valueOf(o.getCompDoy()));
        proc.setParameter("SP_LAD_EMP_AME", o.getCompAmeIka());
        proc.setParameter("SP_LAD_EMP_PHONE", o.getCompPhone());
        proc.setParameter("SP_LAD_EBR_BRANCH0_ID", o.getBranch0Id().intValue());
        proc.setParameter("SP_LAD_EBR_BRANCH_ID", o.getBranch1Id().intValue());
        proc.setParameter("SP_LAD_WPLACE_ADDRESS", o.getCompBrAddr());
        proc.setParameter("SP_LAD_WPLACE_ZIP_CODE", o.getCompBrAddrTk());
        proc.setParameter("SP_LAD_WPLACE_KALLIKRATIS",Constants.StringIsNullOrEmpty(o.getCompBrAddrK()) ? -1 : Integer.parseInt(o.getCompBrAddrK()));
        proc.setParameter("SP_LAD_SUBJECTS", o.getId());
        proc.setParameter("SP_LAD_GROUP_DISCUSSION_FLAG", o.getGroupDiscussion().intValue());
        proc.setParameter("SP_LAD_COMMENT", o.getNotes() );
        proc.setParameter("SP_LAD_ATTACHED_DOC_ID", o.getDocIdAttached().longValue());

        proc.execute();
        String out_errMsg = (String) proc.getOutputParameterValue("out_errMsg");
        if (out_errMsg == null || out_errMsg.isEmpty()) {
            Long docId = null;
            Long department = null;
            if(null == proc.getOutputParameterValue("SP_LAD_DOC_ID")) {
                docId = (Long) proc.getOutputParameterValue("SP_LAD_DOC_ID");
            }
            else {
                docId = new Long((Integer) proc.getOutputParameterValue("SP_LAD_DOC_ID"));
            }

            String protoclNumber = (String) proc.getOutputParameterValue("SP_LAD_PROTOCOL_NUMBER");
            Date protoclDate = (Date) proc.getOutputParameterValue("SP_LAD_PROTOCOL_DATE");
            Integer year = (Integer) proc.getOutputParameterValue("SP_LAD_PROTOCOL_YEAR");
            String time = (String) proc.getOutputParameterValue("SP_LAD_TIME");
            if(null == proc.getOutputParameterValue("SP_LAD_DEPARTMENT")) {
                department = (Long) proc.getOutputParameterValue("SP_LAD_DEPARTMENT");
            }
            else {
                department = new Long((Integer) proc.getOutputParameterValue("SP_LAD_DEPARTMENT"));
            }

            o.setDocId(docId);
            o.setProtNo(protoclNumber); /////////////////////////////////////////////////////////* TODO it was string in the previous entity*/
            o.setProtDate(new Timestamp(protoclDate.getTime()));
            o.setProtYear(year);
            o.setSubmitTime(time);
            o.setDepartment(department); ////////////////////////////////////////////* TODO where is setSepeDept*/
        } else {//handle error
            //parse string gia mia logiki exception
            throw new Exception(out_errMsg);
        }

        return o;
    }

    //Employee Experience
    public SpPtlEmployeeExperience procIntArchiveExperience(SpPtlEmployeeExperience ec) throws Exception {
        StoredProcedureQuery proc = em.createStoredProcedureQuery("SN_proc_int_archive_experience");
        proc.registerStoredProcedureParameter("SP_PEXP_SURNAME", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_PEXP_NAME", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_PEXP_SEPCIALITY", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_PEXP_FROM_DATE", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_PEXP_TO_DATE", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_PEXP_HOME_ADDRESS", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_PEXP_HOME_ZIP_CODE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_PEXP_HOME_KALLIKRATIS", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_PEXP_CARD_TYPE", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_PEXP_CARD_NUMBER", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_PEXP_AMKA_NUMBER", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_PEXP_TAX_NUMBER", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_PEXP_PHONE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_PEXP_MOBILE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_PEXP_EMP_FULLNAME", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_PEXP_EMP_RTSTAK_LEVEL_5", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_PEXP_EMP_TAX_NUMBER", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_PEXP_EMP_AME", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_PEXP_EMP_PHONE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_PEXP_INTENTED_USE", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_PEXP_REMARKS", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_PEXP_EMPL_VER_DOCID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_PEXP_IKA_DOCID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_PEXP_JUDGMNT_DOCID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_PEXP_ASEP_DOCID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_PEXP_DELIVERY_TYPE_CODE", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_PEXP_PORTAL_UID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EBR_BRANCH0_ID", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EBR_BRANCH_ID", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EBR_BRANCH_KALLIKRATIS", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_PEXP_DOC_ID", Long.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_PEXP_PROTOCOL_NUMBER", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_PEXP_PROTOCOL_DATE", Date.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_PEXP_PROTOCOL_YEAR", Integer.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_PEXP_TIME", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_PEXP_DEPARTMENT", Integer.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("out_errMsg", String.class, ParameterMode.OUT);

        proc.setParameter("SP_PEXP_SURNAME", ec.getEmpLastname());
        proc.setParameter("SP_PEXP_NAME", ec.getEmpFirstname());
        proc.setParameter("SP_PEXP_SEPCIALITY", ec.getEmpSpecialityId());
        proc.setParameter("SP_PEXP_FROM_DATE", new Date(ec.getEmpFromDate().getTime()));
        proc.setParameter("SP_PEXP_TO_DATE", new Date(ec.getEmpUntilDate().getTime()));
        proc.setParameter("SP_PEXP_HOME_ADDRESS", ec.getEmpAddr());
        proc.setParameter("SP_PEXP_HOME_ZIP_CODE", ec.getEmpAddrTk());
        proc.setParameter("SP_PEXP_HOME_KALLIKRATIS", Constants.StringIsNullOrEmpty(ec.getEmpAddrK()) ? -1 : Integer.parseInt(ec.getEmpAddrK()));
        proc.setParameter("SP_PEXP_CARD_TYPE", ec.getEmpCardType());
        proc.setParameter("SP_PEXP_CARD_NUMBER", ec.getEmpCardNumber());
        proc.setParameter("SP_PEXP_AMKA_NUMBER", ec.getEmpAmka());
        proc.setParameter("SP_PEXP_TAX_NUMBER", ec.getEmpAfm());
        proc.setParameter("SP_PEXP_PHONE", ec.getEmpPhone());
        proc.setParameter("SP_PEXP_MOBILE", ec.getEmpMobile());
        proc.setParameter("SP_PEXP_EMP_FULLNAME", ec.getCompName());
        Long levelAA = rtStakLevelRepo.getAA(ec.getRtstakLevel5());
        proc.setParameter("SP_PEXP_EMP_RTSTAK_LEVEL_5", levelAA);
        proc.setParameter("SP_PEXP_EMP_TAX_NUMBER", ec.getCompAfm());
        proc.setParameter("SP_PEXP_EMP_AME", ec.getCompAmeIka());
        proc.setParameter("SP_PEXP_EMP_PHONE", ec.getCompPhone());
        proc.setParameter("SP_PEXP_INTENTED_USE", ec.getIntentedUse());
        proc.setParameter("SP_PEXP_REMARKS", ec.getNotes());
        proc.setParameter("SP_PEXP_EMPL_VER_DOCID", ec.getAttachedDocIdEmplVer() == -1 ? null : ec.getAttachedDocIdEmplVer());
        proc.setParameter("SP_PEXP_IKA_DOCID", ec.getAttachedDocIdIka() == -1 ? null : ec.getAttachedDocIdIka());
        proc.setParameter("SP_PEXP_JUDGMNT_DOCID", ec.getAttachedDocIdJudgmnt() == -1 ? null : ec.getAttachedDocIdJudgmnt());
        proc.setParameter("SP_PEXP_ASEP_DOCID", ec.getAttachedDocIdSepe() == -1 ? null : ec.getAttachedDocIdSepe());
        proc.setParameter("SP_PEXP_DELIVERY_TYPE_CODE", 2); //portal's id
        proc.setParameter("SP_PEXP_PORTAL_UID", ec.getUserId());
        proc.setParameter("SP_EBR_BRANCH0_ID", ec.getBranch0Id().intValue());
        proc.setParameter("SP_EBR_BRANCH_ID", ec.getBranch1Id().intValue());
        proc.setParameter("SP_EBR_BRANCH_KALLIKRATIS", Constants.StringIsNullOrEmpty(ec.getRgEbrKallikratis()) ? -1 : Integer.parseInt(ec.getRgEbrKallikratis()));

        proc.execute();
        String out_errMsg = (String) proc.getOutputParameterValue("out_errMsg");
        if (out_errMsg == null || out_errMsg.isEmpty()) {
            Long docId = null;
            Long department = null;
            if(null == proc.getOutputParameterValue("SP_PEXP_DOC_ID")) {
                docId = (Long) proc.getOutputParameterValue("SP_PEXP_DOC_ID");
            }
            else {
                docId = new Long((Integer) proc.getOutputParameterValue("SP_PEXP_DOC_ID"));
            }

            String protoclNumber = (String) proc.getOutputParameterValue("SP_PEXP_PROTOCOL_NUMBER");
            Date protoclDate = (Date) proc.getOutputParameterValue("SP_PEXP_PROTOCOL_DATE");
            Integer year = (Integer) proc.getOutputParameterValue("SP_PEXP_PROTOCOL_YEAR");
            String time = (String) proc.getOutputParameterValue("SP_PEXP_TIME");

            if(null == proc.getOutputParameterValue("SP_PEXP_DEPARTMENT")) {
                department = (Long) proc.getOutputParameterValue("SP_PEXP_DEPARTMENT");
            }
            else {
                department = new Long((Integer) proc.getOutputParameterValue("SP_PEXP_DEPARTMENT"));
            }

            ec.setDocId(docId);
            ec.setProtNo(protoclNumber);
            ec.setProtDate(new Timestamp(protoclDate.getTime()));
            ec.setProtYear(year);
            ec.setSubmitTime(time);
            ec.setDepartment(department);
        } else {//handle error
            //parse string gia mia logiki exception
            throw new Exception(out_errMsg);
        }

        return ec;
    }

    //Employee GenRequest
    public SpPtlEmployeeGenrequest procIntArchiveGenRequest(SpPtlEmployeeGenrequest ec) throws Exception {
        StoredProcedureQuery proc = em.createStoredProcedureQuery("SN_proc_int_archive_genreq");
        proc.registerStoredProcedureParameter("SP_GREQ_OBJECT", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_GREQ_PLACE", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_GREQ_TYPE", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_GREQ_SURNAME", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_GREQ_NAME", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_GREQ_FATHERNAME", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_GREQ_MOTHERNAME", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_GREQ_HOME_ADDRESS", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_GREQ_HOME_ZIP_CODE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_GREQ_HOME_KALLIKRATIS", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_GREQ_CARD_TYPE", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_GREQ_CARD_NUMBER", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_GREQ_AMKA_NUMBER", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_GREQ_TAX_NUMBER", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_GREQ_PHONE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_GREQ_MOBILE", String.class, ParameterMode.IN)
                //.registerStoredProcedureParameter("SP_GREQ_EMAIL", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_GREQ_DESCRIPTION", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_GREQ_REMARKS", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_GREQ_ATTACH_DOCID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_GREQ_DELIVERY_TYPE_CODE", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_GREQ_PORTAL_UID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_GREQ_DOC_ID", Long.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_GREQ_PROTOCOL_NUMBER", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_GREQ_PROTOCOL_DATE", Date.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_GREQ_PROTOCOL_YEAR", Integer.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_GREQ_TIME", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_GREQ_DEPARTMENT", Long.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("out_errMsg", String.class, ParameterMode.OUT);

        proc.setParameter("SP_GREQ_OBJECT", ec.getReqSubject());/////TODO
        proc.setParameter("SP_GREQ_PLACE", Constants.StringIsNullOrEmpty(ec.getReqAddrK()) ? -1 : Integer.parseInt(ec.getReqAddrK()));/////TODO
        proc.setParameter("SP_GREQ_TYPE", ec.getReqType());
        proc.setParameter("SP_GREQ_SURNAME", ec.getEmpLastname());
        proc.setParameter("SP_GREQ_NAME", ec.getEmpFirstname());
        proc.setParameter("SP_GREQ_FATHERNAME", ec.getEmpFathername());
        proc.setParameter("SP_GREQ_MOTHERNAME", ec.getEmpMothername());
        proc.setParameter("SP_GREQ_HOME_ADDRESS", ec.getEmpAddr());
        proc.setParameter("SP_GREQ_HOME_ZIP_CODE", ec.getEmpAddrTk());
        proc.setParameter("SP_GREQ_HOME_KALLIKRATIS", Constants.StringIsNullOrEmpty(ec.getEmpAddrK()) ? -1 : Integer.parseInt(ec.getEmpAddrK()));
        proc.setParameter("SP_GREQ_CARD_TYPE", ec.getEmpCardType());
        proc.setParameter("SP_GREQ_CARD_NUMBER", ec.getEmpCardNumber());
        proc.setParameter("SP_GREQ_AMKA_NUMBER", ec.getEmpAmka());
        proc.setParameter("SP_GREQ_TAX_NUMBER", ec.getEmpAfm());
        proc.setParameter("SP_GREQ_PHONE", ec.getEmpPhone());
        proc.setParameter("SP_GREQ_MOBILE", ec.getEmpMobile());
        //proc.setParameter("SP_GREQ_EMAIL", ec.getEmpEmail());
        proc.setParameter("SP_GREQ_DESCRIPTION", ec.getReqDescription());
        proc.setParameter("SP_GREQ_REMARKS", ec.getReqRemarks());
        proc.setParameter("SP_GREQ_ATTACH_DOCID", ec.getAttachedDocId());
        proc.setParameter("SP_GREQ_DELIVERY_TYPE_CODE", 2);
        proc.setParameter("SP_GREQ_PORTAL_UID", ec.getUserId());

        proc.execute();
        String out_errMsg = (String) proc.getOutputParameterValue("out_errMsg");
        if (out_errMsg == null || out_errMsg.isEmpty()) {
            Long docId = null;
            Long department = null;
            if(null == proc.getOutputParameterValue("SP_GREQ_DOC_ID")) {
                docId = (Long) proc.getOutputParameterValue("SP_GREQ_DOC_ID");
            }
            else {
                docId = new Long((Integer) proc.getOutputParameterValue("SP_GREQ_DOC_ID"));
            }

            String protoclNumber = (String) proc.getOutputParameterValue("SP_GREQ_PROTOCOL_NUMBER");
            Date protoclDate = (Date) proc.getOutputParameterValue("SP_GREQ_PROTOCOL_DATE");
            Integer year = (Integer) proc.getOutputParameterValue("SP_GREQ_PROTOCOL_YEAR");
            String time = (String) proc.getOutputParameterValue("SP_GREQ_TIME");
            if(null == proc.getOutputParameterValue("SP_GREQ_DEPARTMENT")) {
                department = (Long) proc.getOutputParameterValue("SP_GREQ_DEPARTMENT");
            }
            else {
                department = new Long((Integer) proc.getOutputParameterValue("SP_GREQ_DEPARTMENT"));
            }

            ec.setDocId(docId);
            ec.setProtNo(protoclNumber);
            ec.setProtDate(new Timestamp(protoclDate.getTime()));
            ec.setProtYear(year);
            ec.setSubmitTime(time);
            ec.setDepartment(department);
        } else {//handle error
            //parse string gia mia logiki exception
            throw new Exception(out_errMsg);
        }

        return ec;
    }
}
