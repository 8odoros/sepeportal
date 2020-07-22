package sepe.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sepe.config.Constants;
import sepe.domain.company.*;
import sepe.domain.general.SnHrVwDepartments;
import sepe.dto.CompanyDTO;
import sepe.repository.company.SpPtlCompPtlBranchRepo;
import sepe.repository.company.TEmployerBranchIKARepo;
import sepe.repository.general.RtStakLevel5AA;
import sepe.repository.general.SnHrVwDepartmentsRepo;

import javax.annotation.Nonnull;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class CompanyStoredProcedures {

    @Autowired
    EntityManager em;

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyStoredProcedures.class);

    private final RtStakLevel5AA rtStakLevelRepo;

    private TEmployerBranchIKARepo ikaRepo;

    private SpPtlCompPtlBranchRepo spPtlCompPtlBranchRepo;

    @Autowired
    public CompanyStoredProcedures(
            @Nonnull final RtStakLevel5AA rtStakLevelRepo,
            @Nonnull final TEmployerBranchIKARepo ikaRepo,
            @Nonnull final SpPtlCompPtlBranchRepo spPtlCompPtlBranchRepo
    ){
        this.rtStakLevelRepo = rtStakLevelRepo;
        this.ikaRepo = ikaRepo;
        this.spPtlCompPtlBranchRepo = spPtlCompPtlBranchRepo;
    }

    @Autowired
    private UserService userService;


    @Autowired
    private CompanyService companyService;



    //Company Accident
    public SpPtlCompanyAccident procIntArchiveAccident(SpPtlCompanyAccident ec) throws Exception {
        StoredProcedureQuery proc = em.createStoredProcedureQuery("SN_proc_int_archive_accident");
        proc.registerStoredProcedureParameter("SP_ACC_EMP_TAX_NUMBER", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ACC_EMP_FULL_NAME", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ACC_EMP_PHONE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ACC_FULL_NAME_TE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ACC_FULL_NAME_IE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ACC_EMP_ADDRESS", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ACC_EMP_ZIP_CODE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ACC_EMP_KALLIKRATIS", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ACC_EBR_BRANCH0_ID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ACC_EBR_BRANCH_ID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ACC_DELIVERY_TYPE_CODE", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ACC_PORTAL_UID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ACC_AMKA_NUMBER", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ACC_INSURANCE_BUREAU_CODE", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ACC_TAX_NUMBER", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ACC_SURNAME", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ACC_NAME", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ACC_SPECIALTY", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ACC_SEX_DESC", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ACC_MARITAL_STATUS_CD", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ACC_AGE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ACC_CITIZENSHIP_CD", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ACC_RECRUITMENT_DATE", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ACC_JOB_POSITION_CODE", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ACC_TIME_CODE", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ACC_HOME_ADDRESS", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ACC_HOME_ZIP_CODE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ACC_HOME_KALLIKRATIS", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ACC_CARD_TYPE", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ACC_CARD_NUMBER", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ACC_PHONE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ACC_MOBILE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ACC_ACCIDENT_DATE", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ACC_ACCIDENT_TIME", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ACC_AUDIT_KALLIKRATIS", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ACC_AUDIT_ADDRESS", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ACC_AUDIT_ZIP_CODE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ACC_ACCIDENT_JOBS_COMMENT", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ACC_ACCIDENT_DESCRIPTION", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ACC_TRAINING_FLAG", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ACC_WITNESSES", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ACC_ATTACHED_DOC_ID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ACC_SEVERITY_CODE", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ACC_ACCIDENT_TYPE_CODE", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ACC_SPECIALITY", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ACC_WOUND_TYPE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ACC_BODY_PART", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ACC_FATHERSNAME", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ACC_DOC_ID", Long.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_ACC_PROTOCOL_NUMBER", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_ACC_PROTOCOL_DATE", Date.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_ACC_PROTOCOL_YEAR", Integer.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_ACC_TIME", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_ACC_DEPARTMENT", Long.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_ACC_OUTDATED", Integer.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("out_errMsg", String.class, ParameterMode.OUT);


        proc.setParameter("SP_ACC_EMP_TAX_NUMBER", ec.getCompTaxNumber());
        proc.setParameter("SP_ACC_EMP_FULL_NAME", ec.getCompFullName());
        proc.setParameter("SP_ACC_EMP_PHONE", ec.getCompPhone());
        proc.setParameter("SP_ACC_FULL_NAME_TE", ec.getCompNameTe());
        proc.setParameter("SP_ACC_FULL_NAME_IE", ec.getCompNameIe());
        proc.setParameter("SP_ACC_EMP_ADDRESS", ec.getCompAddr());
        proc.setParameter("SP_ACC_EMP_ZIP_CODE", ec.getCompAddrTk());
        proc.setParameter("SP_ACC_EMP_KALLIKRATIS", Constants.StringIsNullOrEmpty(ec.getCompAddrK()) ? -1 : Integer.parseInt(ec.getCompAddrK()));
        proc.setParameter("SP_ACC_EBR_BRANCH0_ID", ec.getCompEbrBranch0Id());
        proc.setParameter("SP_ACC_EBR_BRANCH_ID", ec.getCompEbrBranchId());
        proc.setParameter("SP_ACC_DELIVERY_TYPE_CODE", 2);
        proc.setParameter("SP_ACC_PORTAL_UID", ec.getCompanyId());
        proc.setParameter("SP_ACC_AMKA_NUMBER", ec.getEmpAmkaNumber());
        proc.setParameter("SP_ACC_INSURANCE_BUREAU_CODE", ec.getEmpInsuranceBureauCode());
        proc.setParameter("SP_ACC_TAX_NUMBER", ec.getEmpTaxNumber());
        proc.setParameter("SP_ACC_SURNAME", ec.getEmpSurname());
        proc.setParameter("SP_ACC_NAME", ec.getEmpName());
        proc.setParameter("SP_ACC_SPECIALTY", ec.getEmpSpecialty());
        proc.setParameter("SP_ACC_SEX_DESC", ec.getEmpSexDesc());
        proc.setParameter("SP_ACC_MARITAL_STATUS_CD", ec.getEmpMaritalStatusCd());
        proc.setParameter("SP_ACC_AGE", ec.getEmpAge());
        proc.setParameter("SP_ACC_CITIZENSHIP_CD", Constants.StringIsNullOrEmpty(ec.getEmpCitizenshipCd()) ? new Long(-1) : Long.parseLong(ec.getEmpCitizenshipCd()));
        proc.setParameter("SP_ACC_RECRUITMENT_DATE", new Date(ec.getEmpRecruitmentDate().getTime()));
        proc.setParameter("SP_ACC_JOB_POSITION_CODE", Constants.StringIsNullOrEmpty(ec.getEmpJobPositionCode()) ? new Long(-1) : Long.parseLong(ec.getEmpJobPositionCode()));
        proc.setParameter("SP_ACC_TIME_CODE", Constants.StringIsNullOrEmpty(ec.getEmpTimeCode()) ? new Long(-1) : Long.parseLong(ec.getEmpTimeCode()));
        proc.setParameter("SP_ACC_HOME_ADDRESS", ec.getEmpAddr());
        proc.setParameter("SP_ACC_HOME_ZIP_CODE", ec.getEmpAddrTk());
        proc.setParameter("SP_ACC_HOME_KALLIKRATIS", Constants.StringIsNullOrEmpty(ec.getEmpAddrK()) ? -1 : Integer.parseInt(ec.getEmpAddrK()));
        proc.setParameter("SP_ACC_CARD_TYPE", ec.getEmpCardType());
        proc.setParameter("SP_ACC_CARD_NUMBER", ec.getEmpCardNumber());
        proc.setParameter("SP_ACC_PHONE", ec.getEmpPhone());
        proc.setParameter("SP_ACC_MOBILE", ec.getEmpMobile());
        proc.setParameter("SP_ACC_ACCIDENT_DATE", new Date(ec.getAccidentDate().getTime()));
        proc.setParameter("SP_ACC_ACCIDENT_TIME", ec.getAccidentTime());
        proc.setParameter("SP_ACC_AUDIT_KALLIKRATIS", Constants.StringIsNullOrEmpty(ec.getAccidentAddrK()) ? -1 : Integer.parseInt(ec.getAccidentAddrK()));
        proc.setParameter("SP_ACC_AUDIT_ADDRESS", ec.getAccidentAddr());
        proc.setParameter("SP_ACC_AUDIT_ZIP_CODE", ec.getAccidentAddrTk());
        proc.setParameter("SP_ACC_ACCIDENT_JOBS_COMMENT", ec.getAccidentJobsComment());
        proc.setParameter("SP_ACC_ACCIDENT_DESCRIPTION", ec.getAccidentDescription());
        proc.setParameter("SP_ACC_TRAINING_FLAG", ec.getTrainingFlag());
        proc.setParameter("SP_ACC_WITNESSES", ec.getId());
        proc.setParameter("SP_ACC_ATTACHED_DOC_ID", ec.getAttachedDocId());
        proc.setParameter("SP_ACC_SEVERITY_CODE", Constants.StringIsNullOrEmpty(ec.getAccidentAffectedSeverity()) ? new Long(-1) : Long.parseLong(ec.getAccidentAffectedSeverity()));
        proc.setParameter("SP_ACC_ACCIDENT_TYPE_CODE", Constants.StringIsNullOrEmpty(ec.getAccidentAffectedType()) ? new Long(-1) : Long.parseLong(ec.getAccidentAffectedType()));
        proc.setParameter("SP_ACC_SPECIALITY", null);
        proc.setParameter("SP_ACC_WOUND_TYPE", ec.getAccidentWoundType());
        proc.setParameter("SP_ACC_BODY_PART", ec.getAccidentBodyPart());
        proc.setParameter("SP_ACC_FATHERSNAME", ec.getEmpFathersName());

        proc.execute();
        String out_errMsg = (String) proc.getOutputParameterValue("out_errMsg");
        if (out_errMsg == null || out_errMsg.isEmpty()) {
            Long docId = null;
            Long department = null;
            if(null == proc.getOutputParameterValue("SP_ACC_DOC_ID")) {
                docId = (Long) proc.getOutputParameterValue("SP_ACC_DOC_ID");
            }
            else {
                docId = new Long((Integer) proc.getOutputParameterValue("SP_ACC_DOC_ID"));
            }

            String protoclNumber = (String) proc.getOutputParameterValue("SP_ACC_PROTOCOL_NUMBER");
            Date protoclDate = (Date) proc.getOutputParameterValue("SP_ACC_PROTOCOL_DATE");
            Integer year = (Integer) proc.getOutputParameterValue("SP_ACC_PROTOCOL_YEAR");
            String time = (String) proc.getOutputParameterValue("SP_ACC_TIME");
            if(null == proc.getOutputParameterValue("SP_ACC_DEPARTMENT")) {
                department = (Long) proc.getOutputParameterValue("SP_ACC_DEPARTMENT");
            }
            else {
                department = new Long((Integer) proc.getOutputParameterValue("SP_ACC_DEPARTMENT"));
            }

            Integer outdated = (Integer) proc.getOutputParameterValue("SP_ACC_OUTDATED");
            ec.setDocId(docId);
            ec.setProtNo(protoclNumber);
            ec.setProtDate(new Timestamp(protoclDate.getTime()));
            ec.setProtYear(year);
            ec.setSubmitTime(time);
            ec.setSepeDept(department);
            ec.setOutdated(outdated);
        } else {
            throw new Exception(out_errMsg);
        }

        return ec;
    }

    //Company Illness
    public SpPtlCompanyIllness procIntArchiveIllness(SpPtlCompanyIllness ec) throws Exception {
        Date date = null;
        if (ec.getPrDiagnosisDate().getTime() != 0) {
            date = new Date(ec.getPrDiagnosisDate().getTime());
        }

        StoredProcedureQuery proc = em.createStoredProcedureQuery("SN_proc_int_archive_illness");
        proc.registerStoredProcedureParameter("SP_ILL_EMP_TAX_NUMBER", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ILL_EMP_FULL_NAME", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ILL_EMP_PHONE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ILL_EMP_ADDRESS", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ILL_EMP_ZIP_CODE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ILL_EMP_KALLIKRATIS", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ILL_EBR_BRANCH0_ID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ILL_EBR_BRANCH_ID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ILL_DELIVERY_TYPE_CODE", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ILL_PORTAL_UID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ILL_AMKA_NUMBER", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ILL_INSURANCE_BUREAU_CODE", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ILL_TAX_NUMBER", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ILL_SURNAME", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ILL_NAME", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ILL_SPECIALTY", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ILL_SEX_DESC", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ILL_MARITAL_STATUS_CD", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ILL_AGE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ILL_CITIZENSHIP_CD", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ILL_RECRUITMENT_DATE", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ILL_TIME_CODE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ILL_HOME_ADDRESS", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ILL_HOME_ZIP_CODE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ILL_HOME_KALLIKRATIS", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ILL_CARD_TYPE", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ILL_CARD_NUMBER", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ILL_PHONE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ILL_MOBILE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ILL_ILLNESS_COMMENTS", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ILL_ELEMENTS_COMMENTS", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ILL_DIAGNOSIS_DATE", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ILL_PR_INFESTATION_COMMENTS", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ILL_PR_DIAGNOSIS_DATE", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ILL_FACTOR_COMMENTS", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ILL_COMMENTS", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ILL_ATTACHED_DOC_ID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ILL_DOC_ID", Long.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_ILL_PROTOCOL_NUMBER", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_ILL_PROTOCOL_DATE", Date.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_ILL_PROTOCOL_YEAR", Integer.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_ILL_TIME", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_ILL_DEPARTMENT", Long.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("out_errMsg", String.class, ParameterMode.OUT);

        proc.setParameter("SP_ILL_EMP_TAX_NUMBER", ec.getCompTaxNumber());
        proc.setParameter("SP_ILL_EMP_FULL_NAME", ec.getCompFullName());
        proc.setParameter("SP_ILL_EMP_PHONE", ec.getCompPhone());
        proc.setParameter("SP_ILL_EMP_ADDRESS", ec.getCompAddr());
        proc.setParameter("SP_ILL_EMP_ZIP_CODE", ec.getCompAddrTk());
        proc.setParameter("SP_ILL_EMP_KALLIKRATIS", Constants.StringIsNullOrEmpty(ec.getCompAddrK()) ? -1 : Integer.parseInt(ec.getCompAddrK()));
        proc.setParameter("SP_ILL_EBR_BRANCH0_ID", ec.getCompEbrBranch0Id());
        proc.setParameter("SP_ILL_EBR_BRANCH_ID", ec.getCompEbrBranchId());
        proc.setParameter("SP_ILL_DELIVERY_TYPE_CODE", 2);
        proc.setParameter("SP_ILL_PORTAL_UID", ec.getCompanyId());
        proc.setParameter("SP_ILL_AMKA_NUMBER", ec.getEmpAmkaNumber());
        proc.setParameter("SP_ILL_INSURANCE_BUREAU_CODE", ec.getEmpInsuranceBureauCode());
        proc.setParameter("SP_ILL_TAX_NUMBER", ec.getEmpTaxNumber());
        proc.setParameter("SP_ILL_SURNAME", ec.getEmpSurname());
        proc.setParameter("SP_ILL_NAME", ec.getEmpName());
        proc.setParameter("SP_ILL_SPECIALTY", ec.getEmpSpecialty());
        proc.setParameter("SP_ILL_SEX_DESC", ec.getEmpSexDesc());
        proc.setParameter("SP_ILL_MARITAL_STATUS_CD", ec.getEmpMaritalStatusCd());
        proc.setParameter("SP_ILL_AGE", ec.getEmpAge());
        proc.setParameter("SP_ILL_CITIZENSHIP_CD", Constants.StringIsNullOrEmpty(ec.getEmpCitizenshipCd()) ? new Long(-1) : Long.parseLong(ec.getEmpCitizenshipCd()));
        proc.setParameter("SP_ILL_RECRUITMENT_DATE", new Date(ec.getEmpRecruitmentDate().getTime()));
        proc.setParameter("SP_ILL_TIME_CODE", ec.getEmpTimeCode());
        proc.setParameter("SP_ILL_HOME_ADDRESS", ec.getEmpAddr());
        proc.setParameter("SP_ILL_HOME_ZIP_CODE", ec.getEmpAddrTk());
        proc.setParameter("SP_ILL_HOME_KALLIKRATIS", Constants.StringIsNullOrEmpty(ec.getEmpAddrK()) ? -1 : Integer.parseInt(ec.getEmpAddrK()));
        proc.setParameter("SP_ILL_CARD_TYPE", ec.getEmpCardType());
        proc.setParameter("SP_ILL_CARD_NUMBER", ec.getEmpCardNumber());
        proc.setParameter("SP_ILL_PHONE", ec.getEmpPhone());
        proc.setParameter("SP_ILL_MOBILE", ec.getEmpMobile());
        proc.setParameter("SP_ILL_ILLNESS_COMMENTS", ec.getIllnessComments());
        proc.setParameter("SP_ILL_ELEMENTS_COMMENTS", ec.getElementsComments());
        proc.setParameter("SP_ILL_DIAGNOSIS_DATE", new Date(ec.getDiagnosisDate().getTime()));
        proc.setParameter("SP_ILL_PR_INFESTATION_COMMENTS", ec.getInfestationComments());
        proc.setParameter("SP_ILL_PR_DIAGNOSIS_DATE", date);
        proc.setParameter("SP_ILL_FACTOR_COMMENTS", ec.getFactorComments());
        proc.setParameter("SP_ILL_COMMENTS", ec.getComments());
        proc.setParameter("SP_ILL_ATTACHED_DOC_ID", ec.getAttachedDocId());

        proc.execute();
        String out_errMsg = (String) proc.getOutputParameterValue("out_errMsg");
        if (out_errMsg == null || out_errMsg.isEmpty()) {
            Long docId = null;
            Long department = null;
            if(null == proc.getOutputParameterValue("SP_ILL_DOC_ID")) {
                docId = (Long) proc.getOutputParameterValue("SP_ILL_DOC_ID");
            }
            else {
                docId = new Long((Integer) proc.getOutputParameterValue("SP_ILL_DOC_ID"));
            }

            String protoclNumber = (String) proc.getOutputParameterValue("SP_ILL_PROTOCOL_NUMBER");
            Date protoclDate = (Date) proc.getOutputParameterValue("SP_ILL_PROTOCOL_DATE");
            Integer year = (Integer) proc.getOutputParameterValue("SP_ILL_PROTOCOL_YEAR");
            String time = (String) proc.getOutputParameterValue("SP_ILL_TIME");
            if(null == proc.getOutputParameterValue("SP_ILL_DEPARTMENT")) {
                department = (Long) proc.getOutputParameterValue("SP_ILL_DEPARTMENT");
            }
            else {
                department = new Long((Integer) proc.getOutputParameterValue("SP_ILL_DEPARTMENT"));
            }

            ec.setDocId(docId);
            ec.setProtNo(protoclNumber);
            ec.setProtDate(new Timestamp(protoclDate.getTime()));
            ec.setProtYear(year);
            ec.setSubmitTime(time);
            ec.setSepeDept(department);
        } else {
            throw new Exception(out_errMsg);
        }

        return ec;
    }

    //Company Sunday Permission
    public SpPtlCompSundayPmt procIntArchiveSundayPermission(SpPtlCompSundayPmt ec) throws Exception {
        StoredProcedureQuery proc = em.createStoredProcedureQuery("SN_proc_int_sunday_perm");
        proc.registerStoredProcedureParameter("SP_SUN_DELIVERY_TYPE_CODE", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_SUN_PORTAL_UID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_SUN_COMP_NAME", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_SUN_COMP_AFM", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_SUN_COMP_PHONE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_SUN_COMP_ADDR", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_SUN_COMP_ZIP_CODE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_SUN_COMP_KALLIKRATIS", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_SUN_EBR_BRANCH0_ID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_SUN_EBR_BRANCH_ID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_SUN_ETIOLOGY", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_SUN_COM_STATEMENT", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_SUN_DATE", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_SUN_COM_DOY_ID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_SUN_COM_LEGAL_FORM_ID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_SUN_INSPECTOR_NAME", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_SUN_COM_KAD", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_SUN_MEN_NUM", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_SUN_WOMEN_NUM", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_DOC_ID_ATTACHED", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_SUN_WP_KALLIKRATIS", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_SUN_WP_ZIP_CODE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_SUN_WP_ADDR", String.class, ParameterMode.IN)
                //.registerStoredProcedureParameter("SP_SUN_WP_ADDR", String.class, ParameterMode.IN)
                //.registerStoredProcedureParameter("SP_EXTINF_ATTFILE_DESCRIPTION", String.class, ParameterMode.IN)
                //.registerStoredProcedureParameter("SP_HOLIDAY_DATE", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_DOC_ID", Long.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_PROTOCOL_NUMBER", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_PROTOCOL_DATE", Date.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_PROTOCOL_YEAR", Integer.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_TIME", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_COM_DEPARTMENT", Long.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("out_errMsg", String.class, ParameterMode.OUT);

        proc.setParameter("SP_SUN_DELIVERY_TYPE_CODE", 2);
        proc.setParameter("SP_SUN_PORTAL_UID", ec.getCompanyId());
        proc.setParameter("SP_SUN_COMP_NAME", ec.getCompName());
        proc.setParameter("SP_SUN_COMP_AFM", ec.getCompAfm());
        proc.setParameter("SP_SUN_COMP_PHONE", ec.getCompPhone());
        proc.setParameter("SP_SUN_COMP_ADDR", ec.getCompAddr());
        proc.setParameter("SP_SUN_COMP_ZIP_CODE", ec.getCompAddrTk());
        proc.setParameter("SP_SUN_COMP_KALLIKRATIS", Constants.StringIsNullOrEmpty(ec.getCompAddrK()) ? -1 : Integer.parseInt(ec.getCompAddrK()));
        proc.setParameter("SP_SUN_EBR_BRANCH0_ID", ec.getCompEbrBranch0Id());
        proc.setParameter("SP_SUN_EBR_BRANCH_ID", ec.getCompEbrBranchId());
        proc.setParameter("SP_SUN_ETIOLOGY", ec.getEtiology());
        proc.setParameter("SP_SUN_COM_STATEMENT", ec.getCompStatement());
        proc.setParameter("SP_SUN_DATE", new Date(ec.getSundayDate() != null ? ec.getSundayDate().getTime() : ec.getHolidayDate().getTime()));
        proc.setParameter("SP_SUN_COM_DOY_ID", ec.getCompDoyId());
        proc.setParameter("SP_SUN_COM_LEGAL_FORM_ID", ec.getCompLegalFormId());
        proc.setParameter("SP_SUN_INSPECTOR_NAME", ec.getInspectorName().intValue());
        proc.setParameter("SP_SUN_COM_KAD", ec.getCompKad());
        proc.setParameter("SP_SUN_MEN_NUM", String.valueOf(ec.getMenNum()));
        proc.setParameter("SP_SUN_WOMEN_NUM", String.valueOf(ec.getWomenNum()));
        proc.setParameter("SP_DOC_ID_ATTACHED", ec.getAttachedDocId());
        proc.setParameter("SP_SUN_WP_KALLIKRATIS", Constants.StringIsNullOrEmpty(ec.getInspAddrK()) ? -1 : Integer.parseInt(ec.getInspAddrK()));
        proc.setParameter("SP_SUN_WP_ZIP_CODE", ec.getInspAddrTk());
        proc.setParameter("SP_SUN_WP_ADDR", ec.getInspAddr());
        //proc.setParameter("SP_SUN_WP_ADDR", ec.getCompAddressDesc());
        //proc.setParameter("SP_EXTINF_ATTFILE_DESCRIPTION", ec.getAttachedDocDescr());
        //proc.setParameter("SP_HOLIDAY_DATE", new Date(ec.getHolidayDate().getTime()));

        proc.execute();
        String out_errMsg = (String) proc.getOutputParameterValue("out_errMsg");
        if (out_errMsg == null || out_errMsg.isEmpty()) {
            Long docId = null;
            Long department = null;
            if(null == proc.getOutputParameterValue("SP_DOC_ID")) {
                docId = (Long) proc.getOutputParameterValue("SP_DOC_ID");
            }
            else {
                docId = new Long((Integer) proc.getOutputParameterValue("SP_DOC_ID"));
            }
            String protocolNumber = (String) proc.getOutputParameterValue("SP_PROTOCOL_NUMBER");
            Date protocolDate = (Date) proc.getOutputParameterValue("SP_PROTOCOL_DATE");
            Integer year = (Integer) proc.getOutputParameterValue("SP_PROTOCOL_YEAR");
            String time = (String) proc.getOutputParameterValue("SP_TIME");
            if(null == proc.getOutputParameterValue("SP_COM_DEPARTMENT")) {
                department = (Long) proc.getOutputParameterValue("SP_COM_DEPARTMENT");
            }
            else {
                department = new Long((Integer) proc.getOutputParameterValue("SP_COM_DEPARTMENT"));
            }

            ec.setDocId(docId);
            ec.setProtNo(protocolNumber);
            ec.setProtDate(new Timestamp(protocolDate.getTime()));
            ec.setProtYear(year);
            ec.setSubmitTime(time);
            ec.setSepeDept(department);
        } else {
            throw new Exception(out_errMsg);
        }

        return ec;
    }

    //Company Announcement Oikodomiki Ergasia
    public SpPtlCompProjAnn procIntArchiveCompProjAnn(SpPtlCompProjAnn ec) throws Exception {
        StoredProcedureQuery proc = em.createStoredProcedureQuery("SN_proc_int_comp_projAnn");
        proc.registerStoredProcedureParameter("SP_PORTAL_UID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ACC_DELIVERY_TYPE_CODE", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_AMOE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_PROJ_ADDR", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_PROJ_ADDR_TK", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_PROJ_ADDR_KALLIKRATIS", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_MAX_EMPLOYEE_NUM", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_PROJ_DURATION", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_PROJ_START_DATE", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_SUBCONTRACTOR_NUM", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_PROJ_TYPE", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_LICENCE_NUM", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ATTACHED_DOC_ID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_DESCR", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_NOTES", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_PROJANN_ID", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_DOC_ID", Long.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_PROTOCOL_NUMBER", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_PROTOCOL_DATE", Date.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_PROTOCOL_YEAR", Integer.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_TIME", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_COM_DEPARTMENT", Long.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("out_errMsg", String.class, ParameterMode.OUT);

        proc.setParameter("SP_PORTAL_UID", ec.getUserId());
        proc.setParameter("SP_ACC_DELIVERY_TYPE_CODE", 2);
        proc.setParameter("SP_AMOE", ec.getAmoe()); // μη υποχρεωτικο
        proc.setParameter("SP_PROJ_ADDR", ec.getProjAddr());
        proc.setParameter("SP_PROJ_ADDR_TK", ec.getProjAddrTk());
        proc.setParameter("SP_PROJ_ADDR_KALLIKRATIS", Constants.StringIsNullOrEmpty(ec.getProjAddrK()) ? -1 : Integer.parseInt(ec.getProjAddrK()));
        proc.setParameter("SP_MAX_EMPLOYEE_NUM", ec.getMaxEmployeeNum());
        proc.setParameter("SP_PROJ_DURATION", ec.getProjDuration());
        proc.setParameter("SP_PROJ_START_DATE", new Date(ec.getProjStartDate().getTime()));
        proc.setParameter("SP_SUBCONTRACTOR_NUM", ec.getSubcontractorNum());
        proc.setParameter("SP_PROJ_TYPE", ec.getProjType());
        proc.setParameter("SP_LICENCE_NUM", ec.getLicenceNum()); // μη υποχρεωτικό
        proc.setParameter("SP_ATTACHED_DOC_ID", ec.getAttachedDocId());
        proc.setParameter("SP_DESCR", ec.getDescr());
        proc.setParameter("SP_NOTES", ec.getNotes());
        proc.setParameter("SP_PROJANN_ID", ec.getId());

        proc.execute();
        String out_errMsg = (String) proc.getOutputParameterValue("out_errMsg");
        if (out_errMsg == null || out_errMsg.isEmpty()) {
            Long docId = null;
            Long department = null;
            if(null == proc.getOutputParameterValue("SP_DOC_ID")) {
                docId = (Long) proc.getOutputParameterValue("SP_DOC_ID");
            }
            else {
                docId = new Long((Integer) proc.getOutputParameterValue("SP_DOC_ID"));
            }
            String protocolNumber = (String) proc.getOutputParameterValue("SP_PROTOCOL_NUMBER");
            Date protocolDate = (Date) proc.getOutputParameterValue("SP_PROTOCOL_DATE");
            Integer year = (Integer) proc.getOutputParameterValue("SP_PROTOCOL_YEAR");
            String time = (String) proc.getOutputParameterValue("SP_TIME");
            if(null == proc.getOutputParameterValue("SP_COM_DEPARTMENT")) {
                department = (Long) proc.getOutputParameterValue("SP_COM_DEPARTMENT");
            }
            else {
                department = new Long((Integer) proc.getOutputParameterValue("SP_COM_DEPARTMENT"));
            }
            ec.setDocId(docId);
            ec.setProtNo(protocolNumber);
            ec.setProtDate(new Timestamp(protocolDate.getTime()));
            ec.setProtYear(year);
            ec.setSubmitTime(time);
            ec.setDepartment(department);
        } else {
            throw new Exception(out_errMsg);
        }

        return ec;
    }

    //Company Job Recruitment Office
    public SpPtlCompJobRecrOff procIntArchiveComJobRecrOff(SpPtlCompJobRecrOff ec) throws Exception {
        StoredProcedureQuery proc = em.createStoredProcedureQuery("SN_proc_compJobRecrOff");
        proc.registerStoredProcedureParameter("SP_PORTAL_UID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ACC_DELIVERY_TYPE_CODE", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_AFM", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_NAME", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_PHONE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_ADDR", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_ADDR_TK", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_ADDR_KALLIKRATIS", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_EBR_BRANCH0_ID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_EBR_BRANCH_ID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_REQUEST_EMPTY", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_REQUEST_YEAR", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_REQUEST_HALF_YEAR", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_DOY_ID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_LEGAL_FORM_ID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_KAD", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_NOTES", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ATTACHED_DOC_ID", Long.class, ParameterMode.IN)
                //.registerStoredProcedureParameter("SP_EXTINF_ATTFILE_DESCRIPTION", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_INTERMEDIATION_NUM", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_DOC_ID", Long.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_PROTOCOL_NUMBER", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_PROTOCOL_DATE", Date.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_PROTOCOL_YEAR", Integer.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_TIME", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_COM_DEPARTMENT", Long.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_OUTDATED", Integer.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("out_errMsg", String.class, ParameterMode.OUT);

        proc.setParameter("SP_PORTAL_UID", ec.getCompanyId());
        proc.setParameter("SP_ACC_DELIVERY_TYPE_CODE", 2);
        proc.setParameter("SP_COMP_AFM", ec.getCompAfm());
        proc.setParameter("SP_COMP_NAME", ec.getCompName());
        proc.setParameter("SP_COMP_PHONE", ec.getCompPhone());
        proc.setParameter("SP_COMP_ADDR", ec.getCompAddr());
        proc.setParameter("SP_COMP_ADDR_TK", ec.getCompAddrTk());
        proc.setParameter("SP_COMP_ADDR_KALLIKRATIS", Constants.StringIsNullOrEmpty(ec.getCompAddrK()) ? -1 : Integer.parseInt(ec.getCompAddrK()));
        proc.setParameter("SP_COMP_EBR_BRANCH0_ID", ec.getCompEbrBranch0Id());
        proc.setParameter("SP_COMP_EBR_BRANCH_ID", ec.getCompEbrBranchId());
        proc.setParameter("SP_REQUEST_EMPTY", ec.getRequestEmpty().longValue());
        proc.setParameter("SP_REQUEST_YEAR", ec.getRequestYear());
        proc.setParameter("SP_REQUEST_HALF_YEAR", ec.getRequestHalfYear());
        proc.setParameter("SP_COMP_DOY_ID", ec.getCompDoyId());
        proc.setParameter("SP_COMP_LEGAL_FORM_ID", ec.getCompLegalFormId());
        proc.setParameter("SP_COMP_KAD", ec.getCompKad());
        proc.setParameter("SP_NOTES", ec.getNotes());
        proc.setParameter("SP_ATTACHED_DOC_ID", ec.getAttachedDocId());
        //proc.setParameter("SP_EXTINF_ATTFILE_DESCRIPTION", ec.getAttachedDocDescr());
        proc.setParameter("SP_INTERMEDIATION_NUM", ec.getIntermedationNum());

        proc.execute();
        String out_errMsg = (String) proc.getOutputParameterValue("out_errMsg");
        if (out_errMsg == null || out_errMsg.isEmpty()) {
            Long docId = null;
            Long department = null;
            if(null == proc.getOutputParameterValue("SP_DOC_ID")) {
                docId = (Long) proc.getOutputParameterValue("SP_DOC_ID");
            }
            else {
                docId = new Long((Integer) proc.getOutputParameterValue("SP_DOC_ID"));
            }
            String protocolNumber = (String) proc.getOutputParameterValue("SP_PROTOCOL_NUMBER");
            Date protocolDate = (Date) proc.getOutputParameterValue("SP_PROTOCOL_DATE");
            Integer year = (Integer) proc.getOutputParameterValue("SP_PROTOCOL_YEAR");
            String time = (String) proc.getOutputParameterValue("SP_TIME");
            if(null == proc.getOutputParameterValue("SP_COM_DEPARTMENT")) {
                department = (Long) proc.getOutputParameterValue("SP_COM_DEPARTMENT");
            }
            else {
                department = new Long((Integer) proc.getOutputParameterValue("SP_COM_DEPARTMENT"));
            }
            Integer outdated = (Integer) proc.getOutputParameterValue("SP_OUTDATED");
            ec.setDocId(docId);
            ec.setProtNo(protocolNumber);
            ec.setProtDate(new Timestamp(protocolDate.getTime()));
            ec.setProtYear(year);
            ec.setSubmitTime(time);
            ec.setSepeDept(department);
            ec.setOutdated(outdated);
        } else {
            throw new Exception(out_errMsg);
        }

        return ec;
    }

    //Company Complaint
    public SpPtlCompComplaint procIntArchiveCompComplaint(SpPtlCompComplaint ec) throws Exception {
        StoredProcedureQuery proc = em.createStoredProcedureQuery("SN_proc_int_compComplaint");
        proc.registerStoredProcedureParameter("SP_COMPL_MATTER", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMPL_DESCR", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMPL_INVLOVES_SAFETY_INSP", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMPL_INVOLVES_LAB_RELAT", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMPL_IS_ANONYMOUS", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_PORTAL_UID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COM_DELIVERY_TYPE_CODE", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_EBR_BRANCH0_ID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_EBR_BRANCH_ID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COM_ATTRIBUTE_CODE", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EMP_FIRSTNAME", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EMP_LASTNAME", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EMP_PHONE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EMP_MOBILE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EMP_AMKA", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EMP_AFM", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EMP_SEX_DESC", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EMP_AGE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EMP_CITIZENSHIP_CD", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EMP_CARD_NUMBER", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EMP_CARD_TYPE", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_INSP_ADDR", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_INSP_ADDR_TK", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_INSP_ADDR_KALLIKRATIS", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_AFM", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_AME", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_NAME", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_PHONE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_HAS_LABOUR_UNION", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_RTSTAK_LEVEL_5", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_EMPLOYEE_NO", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_ADDR", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_ADDR_TK", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_ADDR_KALLIKRATIS", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_DOC_ID_ATTACHED", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COM_DOC_ID", Long.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_COM_PROTOCOL_NUMBER", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_COM_PROTOCOL_DATE", Date.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_COM_PROTOCOL_YEAR", Integer.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_COM_TIME", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_COM_DEPARTMENT", Long.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("out_errMsg", String.class, ParameterMode.OUT);

        proc.setParameter("SP_COMPL_MATTER", ec.getComplMatter()); //comma delimited?
        proc.setParameter("SP_COMPL_DESCR", ec.getComplDescr());
        proc.setParameter("SP_COMPL_INVLOVES_SAFETY_INSP", ec.getComplInvlovesSafetyInsp());
        proc.setParameter("SP_COMPL_INVOLVES_LAB_RELAT", ec.getComplInvolvesLabRelations());
        proc.setParameter("SP_COMPL_IS_ANONYMOUS", ec.getComplIsAnonymous());
        proc.setParameter("SP_PORTAL_UID", ec.getCompanyId());
        proc.setParameter("SP_COM_DELIVERY_TYPE_CODE", 2);
        proc.setParameter("SP_COMP_EBR_BRANCH0_ID", ec.getBranch0Id());
        proc.setParameter("SP_COMP_EBR_BRANCH_ID", ec.getBranch1Id());
        proc.setParameter("SP_COM_ATTRIBUTE_CODE", 3);
        //(1 - ΕΡΓΑΖΟΜΕΝΟΣ,2 ΠΟΛΙΤΗΣ,3 ΕΠΙΧΕΙΡΗΣΗ/ΕΡΓΟΔΟΤΗΣ,4 ΤΕΧΝΙΚΟΣ ΑΣΦΑΛΕΙΑΣ
        // 5 ΙΑΤΡΟΣ ΕΡΓΑΣΙΑΣ,6 ΣΩΜΑΤΕΙΟ ΕΡΓΑΖΟΜΈΝΩΝ,7 ΑΓΝΩΣΤΟ)
        proc.setParameter("SP_EMP_FIRSTNAME", ec.getEmpFirstname());
        proc.setParameter("SP_EMP_LASTNAME", ec.getEmpLastname());
        proc.setParameter("SP_EMP_PHONE", ec.getEmpPhone());
        proc.setParameter("SP_EMP_MOBILE", ec.getEmpMobile());
        proc.setParameter("SP_EMP_AMKA", ec.getEmpAmka());
        proc.setParameter("SP_EMP_AFM", ec.getEmpAfm());
        proc.setParameter("SP_EMP_SEX_DESC", ec.getEmpSexDesc());
        proc.setParameter("SP_EMP_AGE", ec.getEmpAge());
        proc.setParameter("SP_EMP_CITIZENSHIP_CD", ec.getEmpCitizenshipCd());
        proc.setParameter("SP_EMP_CARD_NUMBER", ec.getEmpCardNumber());
        proc.setParameter("SP_EMP_CARD_TYPE", ec.getEmpCardType());
        proc.setParameter("SP_INSP_ADDR", ec.getInspAddr());
        proc.setParameter("SP_INSP_ADDR_TK", ec.getInspAddrTk());
        proc.setParameter("SP_INSP_ADDR_KALLIKRATIS", Constants.StringIsNullOrEmpty(ec.getInspAddrK()) ? -1 : Integer.parseInt(ec.getInspAddrK()));
        proc.setParameter("SP_COMP_AFM", ec.getCompAfm());
        proc.setParameter("SP_COMP_AME", ec.getCompAme());
        proc.setParameter("SP_COMP_NAME", ec.getCompName());
        proc.setParameter("SP_COMP_PHONE", ec.getCompPhone());
        proc.setParameter("SP_COMP_HAS_LABOUR_UNION", ec.getCompHasLabourUnion().toString());
        Long levelAA = rtStakLevelRepo.getAA(ec.getRtstakLevel5());
        proc.setParameter("SP_RTSTAK_LEVEL_5", levelAA);
        proc.setParameter("SP_COMP_EMPLOYEE_NO", ec.getEmployeeNum());
        proc.setParameter("SP_COMP_ADDR", ec.getCompAddr());
        proc.setParameter("SP_COMP_ADDR_TK", ec.getCompAddrTk());
        proc.setParameter("SP_COMP_ADDR_KALLIKRATIS", Constants.StringIsNullOrEmpty(ec.getCompAddrK()) ? -1 : Integer.parseInt(ec.getCompAddrK()));
        proc.setParameter("SP_DOC_ID_ATTACHED", ec.getDocIdAttached());

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

            String protocolNumber = (String) proc.getOutputParameterValue("SP_COM_PROTOCOL_NUMBER");
            Date protocolDate = (Date) proc.getOutputParameterValue("SP_COM_PROTOCOL_DATE");
            Integer year = (Integer) proc.getOutputParameterValue("SP_COM_PROTOCOL_YEAR");
            String time = (String) proc.getOutputParameterValue("SP_COM_TIME");
            if(null == proc.getOutputParameterValue("SP_COM_DEPARTMENT")) {
                department = (Long) proc.getOutputParameterValue("SP_COM_DEPARTMENT");
            }
            else {
                department = new Long((Integer) proc.getOutputParameterValue("SP_COM_DEPARTMENT"));
            }
            ec.setDocId(docId);
            ec.setProtNo(protocolNumber);
            ec.setProtDate(new Timestamp(protocolDate.getTime()));
            ec.setProtYear(year);
            ec.setSubmitTime(time);
            ec.setSepeDept(department);
        } else {
            throw new Exception(out_errMsg);
        }

        return ec;
    }

    //Company Ypoboli prosthetwn stoixeiwn
    public SpPtlCompExtraInfo procIntArchiveCompExtraInfo(SpPtlCompExtraInfo ec) throws Exception {
        StoredProcedureQuery proc = em.createStoredProcedureQuery("SN_proc_int_archive_extra_info");
        proc.registerStoredProcedureParameter("SP_EXTINF_EMP_TAX_NUMBER", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EXTINF_EMP_FULL_NAME", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EXTINF_EMP_PHONE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EXTINF_FULL_NAME_TA", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EXTINF_FULL_NAME_IE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EXTINF_EMP_ADDRESS", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EXTINF_EMP_ZIP_CODE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EXTINF_EMP_KALLIKRATIS", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EXTINF_EMP_AME", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ETSTAK_LEVEL_5", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EXTINF_PORTAL_UID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EXTINF_EBR_BRANCH0_ID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EXTINF_EBR_BRANCH_ID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EXTINF_INSPECTOR_NAME", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EXTINF_COMMENTS", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EXTINF_CASE_ID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EXTINF_CTRL_NO", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EXTINF_CTRL_DATE", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EXTINF_ATTFILE_DESCRIPTION", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EXTINF_ATTACHED_DOC_ID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EXTINF_KE_TE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EXTINF_DEPARTMENT", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EXTINF_DOC_ID", Long.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_EXTINF_PROTOCOL_NUMBER", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_EXTINF_PROTOCOL_DATE", Date.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_EXTINF_PROTOCOL_YEAR", Integer.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_EXTINF_TIME", String.class, ParameterMode.OUT)

                .registerStoredProcedureParameter("out_errMsg", String.class, ParameterMode.OUT);

        proc.setParameter("SP_EXTINF_EMP_TAX_NUMBER", ec.getCompAfm());
        proc.setParameter("SP_EXTINF_EMP_FULL_NAME", ec.getCompName());
        proc.setParameter("SP_EXTINF_EMP_PHONE", ec.getCompPhone());
        proc.setParameter("SP_EXTINF_FULL_NAME_TA", ec.getTaFullname());
        proc.setParameter("SP_EXTINF_FULL_NAME_IE", ec.getIeFullname());
        proc.setParameter("SP_EXTINF_EMP_ADDRESS", ec.getCompAddr());
        proc.setParameter("SP_EXTINF_EMP_ZIP_CODE", ec.getCompAddrTk());
        proc.setParameter("SP_EXTINF_EMP_KALLIKRATIS", Constants.StringIsNullOrEmpty(ec.getCompAddrK()) ? -1 : Integer.parseInt(ec.getCompAddrK()));
        proc.setParameter("SP_EXTINF_EMP_AME", ec.getCompAme());
        Long levelAA = rtStakLevelRepo.getAA(ec.getRtstakLevel5());
        proc.setParameter("SP_ETSTAK_LEVEL_5", levelAA);
        proc.setParameter("SP_EXTINF_PORTAL_UID", ec.getCompanyId());
        proc.setParameter("SP_EXTINF_EBR_BRANCH0_ID", ec.getBranch0Id());
        proc.setParameter("SP_EXTINF_EBR_BRANCH_ID", ec.getBranch1Id());
        proc.setParameter("SP_EXTINF_INSPECTOR_NAME", ec.getInspectorName());
        proc.setParameter("SP_EXTINF_COMMENTS", ec.getDescr());
        proc.setParameter("SP_EXTINF_CASE_ID", new Long(-1)); //ec.getExternalProtNo());
        proc.setParameter("SP_EXTINF_CTRL_NO", ec.getControlStatementNum());
        proc.setParameter("SP_EXTINF_CTRL_DATE", new Date(ec.getInspectionDate().getTime()));
        proc.setParameter("SP_EXTINF_ATTFILE_DESCRIPTION", ec.getAttachedDocDescr());
        proc.setParameter("SP_EXTINF_ATTACHED_DOC_ID", ec.getAttachedDocId());
        proc.setParameter("SP_EXTINF_KE_TE", ec.getTeKe());
        proc.setParameter("SP_EXTINF_DEPARTMENT", ec.getSepeDeptCombobox());

        proc.execute();
        String out_errMsg = (String) proc.getOutputParameterValue("out_errMsg");
        if (out_errMsg == null || out_errMsg.isEmpty()) {
            Long docId = null;
            if(null == proc.getOutputParameterValue("SP_EXTINF_DOC_ID")) {
                docId = (Long) proc.getOutputParameterValue("SP_EXTINF_DOC_ID");
            }
            else {
                docId = new Long((Integer) proc.getOutputParameterValue("SP_EXTINF_DOC_ID"));
            }

            String protocolNumber = (String) proc.getOutputParameterValue("SP_EXTINF_PROTOCOL_NUMBER");
            Date protocolDate = (Date) proc.getOutputParameterValue("SP_EXTINF_PROTOCOL_DATE");
            Integer year = (Integer) proc.getOutputParameterValue("SP_EXTINF_PROTOCOL_YEAR");
            String time = (String) proc.getOutputParameterValue("SP_EXTINF_TIME");

            ec.setDocId(docId);
            ec.setProtNo(protocolNumber);
            ec.setProtDate(new Timestamp(protocolDate.getTime()));
            ec.setProtYear(year);
            ec.setSubmitTime(time);
        } else {
            throw new Exception(out_errMsg);
        }

        return ec;
    }

    //Company Ypoboli aitisis suzitisis ergatikis diaforas/sumfiliwtikis diadikasias
    public SpPtlCompDisputeNeg procIntArchiveCompDisputeNeg(SpPtlCompDisputeNeg ec) throws Exception {
        StoredProcedureQuery proc = em.createStoredProcedureQuery("SN_proc_int_archive_disputeneg");
        proc.registerStoredProcedureParameter("SP_PORTAL_UID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ACC_DELIVERY_TYPE_CODE", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_APPLICANT_TYPE", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_APPLICATION_TYPE", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_NAME", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_AFM", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_AME_IKA", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_DOY", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_PHONE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_ADDR", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_ADDR_TK", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_KALLIKRATIS", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_REPRESENTATIVE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_BR_ADDR", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_BR_ADDR_TK", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_BR_KALLIKRATIS", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_BRANCH0_ID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_BRANCH_ID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EMP_FIRSTNAME", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EMP_LASTNAME", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EMP_PHONE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EMP_MOBILE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EMP_AMKA", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EMP_AFM", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EMP_SEX", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EMP_AGE", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EMP_CITIZENSHIP", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EMP_CARD_NUMBER", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EMP_CARD_TYPE", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EMP_ADDR", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EMP_ADDR_TK", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EMP_KALLIKRATIS", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EMP_SPECIALITY", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EMP_MARITAL_STATUS", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EMP_CHILDREN_NUM", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EMP_NET_SALARY", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EMP_GROSS_SALARY", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EMP_WORKING_HOURS", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EMP_FROM_DATE", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EMP_UNTIL_DATE", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_DISPUTE_OR_CONCILIATION", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_RTSTAK_LEVEL_5", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_DISPUTE_CONCILIATION_CATEG", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_DOC_ID_ATTACHED", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_DESCR", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_NOTES", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_DOC_ID", Long.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_PROTOCOL_NUMBER", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_PROTOCOL_DATE", Date.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_PROTOCOL_YEAR", Integer.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_TIME", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_COM_DEPARTMENT", Long.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("out_errMsg", String.class, ParameterMode.OUT);

        proc.setParameter("SP_PORTAL_UID", ec.getCompanyId());
        proc.setParameter("SP_ACC_DELIVERY_TYPE_CODE", 2);
        proc.setParameter("SP_APPLICANT_TYPE", 3);
        //(1 - ΕΡΓΑΖΟΜΕΝΟΣ,2 ΠΟΛΙΤΗΣ,3 ΕΠΙΧΕΙΡΗΣΗ/ΕΡΓΟΔΟΤΗΣ,4 ΤΕΧΝΙΚΟΣ ΑΣΦΑΛΕΙΑΣ
        // 5 ΙΑΤΡΟΣ ΕΡΓΑΣΙΑΣ,6 ΣΩΜΑΤΕΙΟ ΕΡΓΑΖΟΜΈΝΩΝ,7 ΑΓΝΩΣΤΟ)
        proc.setParameter("SP_APPLICATION_TYPE", 0);
        proc.setParameter("SP_COMP_NAME", ec.getCompName());
        proc.setParameter("SP_COMP_AFM", ec.getCompAfm());
        proc.setParameter("SP_COMP_AME_IKA", ec.getCompAmeIka());
        proc.setParameter("SP_COMP_DOY", ec.getCompDoy());
        proc.setParameter("SP_COMP_PHONE", ec.getCompPhone());
        proc.setParameter("SP_COMP_ADDR", ec.getCompAddr());
        proc.setParameter("SP_COMP_ADDR_TK", ec.getCompAddrTk());
        proc.setParameter("SP_COMP_KALLIKRATIS", Constants.StringIsNullOrEmpty(ec.getCompAddrK()) ? -1 : Integer.parseInt(ec.getCompAddrK()));
        proc.setParameter("SP_COMP_REPRESENTATIVE", ec.getCompRepresentative());
        proc.setParameter("SP_COMP_BR_ADDR", ec.getCompBrAddr());
        proc.setParameter("SP_COMP_BR_ADDR_TK", ec.getCompBrAddrTk());
        proc.setParameter("SP_COMP_BR_KALLIKRATIS", Constants.StringIsNullOrEmpty(ec.getCompBrAddrK()) ? -1 : Integer.parseInt(ec.getCompBrAddrK()));
        proc.setParameter("SP_COMP_BRANCH0_ID", ec.getBranch0Id());
        proc.setParameter("SP_COMP_BRANCH_ID", ec.getBranch1Id());
        proc.setParameter("SP_EMP_FIRSTNAME", ec.getEmpFirstname());
        proc.setParameter("SP_EMP_LASTNAME", ec.getEmpLastname());
        proc.setParameter("SP_EMP_PHONE", ec.getEmpPhone());
        proc.setParameter("SP_EMP_MOBILE", ec.getEmpMobile());
        proc.setParameter("SP_EMP_AMKA", ec.getEmpAmka());
        proc.setParameter("SP_EMP_AFM", ec.getEmpAfm());
        proc.setParameter("SP_EMP_SEX", ec.getEmpSex());
        proc.setParameter("SP_EMP_AGE", ec.getEmpAge());
        proc.setParameter("SP_EMP_CITIZENSHIP", ec.getEmpCitizenship());
        proc.setParameter("SP_EMP_CARD_NUMBER", ec.getEmpCardNumber());
        proc.setParameter("SP_EMP_CARD_TYPE", ec.getEmpCardType());
        proc.setParameter("SP_EMP_ADDR", ec.getEmpAddr());
        proc.setParameter("SP_EMP_ADDR_TK", ec.getEmpAddrTk());
        proc.setParameter("SP_EMP_KALLIKRATIS", Constants.StringIsNullOrEmpty(ec.getEmpAddrK()) ? -1 : Integer.parseInt(ec.getEmpAddrK()));
        proc.setParameter("SP_EMP_SPECIALITY", ec.getEmpSpecialityId());
        proc.setParameter("SP_EMP_MARITAL_STATUS", ec.getEmpMaritalStatus());
        proc.setParameter("SP_EMP_CHILDREN_NUM", ec.getEmpChildrenNum());
        proc.setParameter("SP_EMP_NET_SALARY", ec.getEmpNetSalary());
        proc.setParameter("SP_EMP_GROSS_SALARY", ec.getEmpGrossSalary());
        proc.setParameter("SP_EMP_WORKING_HOURS", ec.getEmpWorkingHours());
        proc.setParameter("SP_EMP_FROM_DATE", new Date(ec.getEmpFromDate().getTime()));
        proc.setParameter("SP_EMP_UNTIL_DATE", new Date(ec.getEmpUntilDate().getTime()));
        proc.setParameter("SP_DISPUTE_OR_CONCILIATION", ec.getDisputeOrConciliation());
        Long levelAA = rtStakLevelRepo.getAA(ec.getRtstakLevel5()); // TODO pass in getAA mainStakod!
        proc.setParameter("SP_RTSTAK_LEVEL_5", levelAA);
        proc.setParameter("SP_DISPUTE_CONCILIATION_CATEG", ec.getDisputeConciliationCategId().toString());//TO FIX
        proc.setParameter("SP_DOC_ID_ATTACHED", ec.getDocIdAttached());
        proc.setParameter("SP_DESCR", ec.getDescr());
        proc.setParameter("SP_NOTES", ec.getNotes());

        proc.execute();
        String out_errMsg = (String) proc.getOutputParameterValue("out_errMsg");
        if (out_errMsg == null || out_errMsg.isEmpty()) {
            Long docId = null;
            Long department = null;
            if(null == proc.getOutputParameterValue("SP_DOC_ID")) {
                docId = (Long) proc.getOutputParameterValue("SP_DOC_ID");
            }
            else {
                docId = new Long((Integer) proc.getOutputParameterValue("SP_DOC_ID"));
            }

            String protocolNumber = (String) proc.getOutputParameterValue("SP_PROTOCOL_NUMBER");
            Date protocolDate = (Date) proc.getOutputParameterValue("SP_PROTOCOL_DATE");
            Integer year = (Integer) proc.getOutputParameterValue("SP_PROTOCOL_YEAR");
            String time = (String) proc.getOutputParameterValue("SP_TIME");
            if(null == proc.getOutputParameterValue("SP_COM_DEPARTMENT")) {
                department = (Long) proc.getOutputParameterValue("SP_COM_DEPARTMENT");
            }
            else {
                department = new Long((Integer) proc.getOutputParameterValue("SP_COM_DEPARTMENT"));
            }

            ec.setDocId(docId);
            ec.setProtNo(protocolNumber);
            ec.setProtDate(new Timestamp(protocolDate.getTime()));
            ec.setProtYear(year);
            ec.setSubmitTime(time);
            ec.setDepartment(department);
        } else {
            throw new Exception(out_errMsg);
        }

        return ec;
    }

    //Company Xorigisi Antigrafwn
    public SpPtlCompGenreq procIntArchiveCompGenReq(SpPtlCompGenreq ec) throws Exception {
        StoredProcedureQuery proc = em.createStoredProcedureQuery("SN_proc_int_comp_genreq");
        proc.registerStoredProcedureParameter("SP_PORTAL_UID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ACC_DELIVERY_TYPE_CODE", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_REPR_FIRSTNAME", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_REPR_LASTNAME", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_REPR_FATHERNAME", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_REPR_MOTHERNAME", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_REPR_ADDR", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_REPR_ADDR_TK", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_REPR_ADDR_KALLIKRATIS", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_REPR_AFM", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_REPR_AMKA", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_REPR_PHONE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_REPR_MOBILE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_REPR_CARD_TYPE", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_REPR_CARD_NUMBER", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_REPR_REQUEST_TYPE_ID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_DOC_ID_ATTACHED", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_DESCR", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_NOTES", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_DEPARTMENT", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_OTHER_DEPARTMENT", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_DEP_KALLIKRATIS", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_GENREQ_OBJECT", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_GENREQ_PTLID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_DOC_ID", Long.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_PROTOCOL_NUMBER", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_PROTOCOL_DATE", Date.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_PROTOCOL_YEAR", Integer.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_TIME", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_COM_DEPARTMENT", Long.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("out_errMsg", String.class, ParameterMode.OUT);

        proc.setParameter("SP_PORTAL_UID", ec.getCompanyId());
        proc.setParameter("SP_ACC_DELIVERY_TYPE_CODE", 2);
        proc.setParameter("SP_REPR_FIRSTNAME", ec.getReprFirstname());
        proc.setParameter("SP_REPR_LASTNAME", ec.getReprLastname());
        proc.setParameter("SP_REPR_FATHERNAME", ec.getReprFathername());
        proc.setParameter("SP_REPR_MOTHERNAME", ec.getReprMothername());
        proc.setParameter("SP_REPR_ADDR", ec.getReprAddr());
        proc.setParameter("SP_REPR_ADDR_TK", ec.getReprAddrTk());
        proc.setParameter("SP_REPR_ADDR_KALLIKRATIS", Constants.StringIsNullOrEmpty(ec.getReprAddrK()) ? -1 : Integer.parseInt(ec.getReprAddrK()));
        proc.setParameter("SP_REPR_AFM", ec.getReprAfm());
        proc.setParameter("SP_REPR_AMKA", ec.getReprAmka());
        proc.setParameter("SP_REPR_PHONE", ec.getReprPhone());
        proc.setParameter("SP_REPR_MOBILE", ec.getReprMobile());
        proc.setParameter("SP_REPR_CARD_TYPE", ec.getReprCardType());
        proc.setParameter("SP_REPR_CARD_NUMBER", ec.getReprCardNumber());
        proc.setParameter("SP_REPR_REQUEST_TYPE_ID", ec.getRequestTypeId());
        proc.setParameter("SP_DOC_ID_ATTACHED", ec.getAttachedDocId());
        proc.setParameter("SP_DESCR", ec.getDescr());
        proc.setParameter("SP_NOTES", ec.getNotes());
        proc.setParameter("SP_DEPARTMENT", ec.getBranch1Id());
        proc.setParameter("SP_OTHER_DEPARTMENT", ec.getComplAddressDesc());
        proc.setParameter("SP_DEP_KALLIKRATIS", Constants.StringIsNullOrEmpty(ec.getInspAddrK()) ? -1 : Integer.parseInt(ec.getInspAddrK()));
        proc.setParameter("SP_GENREQ_OBJECT", ec.getReqSubject());
        proc.setParameter("SP_GENREQ_PTLID", ec.getId());
        proc.execute();
        String out_errMsg = (String) proc.getOutputParameterValue("out_errMsg");
        if (out_errMsg == null || out_errMsg.isEmpty()) {
            Long docId = null;
            Long department = null;
            if(null == proc.getOutputParameterValue("SP_DOC_ID")) {
                docId = (Long) proc.getOutputParameterValue("SP_DOC_ID");
            }
            else {
                docId = new Long((Integer) proc.getOutputParameterValue("SP_DOC_ID"));
            }

            String protocolNumber = (String) proc.getOutputParameterValue("SP_PROTOCOL_NUMBER");
            Date protocolDate = (Date) proc.getOutputParameterValue("SP_PROTOCOL_DATE");
            Integer year = (Integer) proc.getOutputParameterValue("SP_PROTOCOL_YEAR");
            String time = (String) proc.getOutputParameterValue("SP_TIME");
            if(null == proc.getOutputParameterValue("SP_COM_DEPARTMENT")) {
                department = (Long) proc.getOutputParameterValue("SP_COM_DEPARTMENT");
            }
            else {
                department = new Long((Integer) proc.getOutputParameterValue("SP_COM_DEPARTMENT"));
            }

            ec.setDocId(docId);
            ec.setProtNo(protocolNumber);
            ec.setProtDate(new Timestamp(protocolDate.getTime()));
            ec.setProtYear(year);
            ec.setSubmitTime(time);
            ec.setSepeDept(department);
        } else {
            throw new Exception(out_errMsg);
        }

        return ec;
    }

    // Ένοπλες Δυνάμεις
    public SpPtlCompTaAnnMilitary procIntArchiveCompTaAnnMilitary(SpPtlCompTaAnnMilitary ec) throws Exception {
        StoredProcedureQuery proc = em.createStoredProcedureQuery("SN_PROC_INT_ARCHIVE_TA_MIL");
        proc.registerStoredProcedureParameter("SP_ANNOUNCEMENT_ID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_BRANCH0_ID", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_BRANCH_ID", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_REPR_FIRSTNAME", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_REPR_LASTNAME", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_REPR_PHONE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_REPR_MOBILE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_TECH_FIRSTNAME", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_TECH_LASTNAME", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_TECH_FATHERNAME", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_TECH_TYPE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_MIL_CATEGORY", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_MIL_RANK", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_CIV_BRANCH", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_CIV_SPECIALITY", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("REQUEST_TYPE_ID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("INSP_ADDR", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("INSP_ADDR_K", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("INSP_ADDR_TK", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("COMPL_ADDRESS_DESC", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ATTACHED_DOC_ID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_DELIVERY_TYPE_CODE", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_PORTAL_UID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_DOC_ID", Long.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_PROTOCOL_NUMBER", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_PROTOCOL_DATE", Date.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_PROTOCOL_YEAR", Integer.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_TIME", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_DEPARTMENT", Long.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("out_errMsg", String.class, ParameterMode.OUT);

        String techType = ec.getTa_type() == 0 ? "ΕΝΣΤΟΛΟΣ":"ΠΟΛΙΤΙΚΟ ΠΡΟΣΩΠΟ";
        String milCategory = "";
        String civBranch = "";
        if (ec.getTa_categ() != null)
        {
            switch (Math.toIntExact(ec.getTa_categ()))
            {
                default:
                case 0:
                    milCategory = "ΑΣΕΙ";
                    break;
                case 1:
                    milCategory = "ΑΣΣΥ";
                    break;

                case 2:
                    milCategory = "Ν.3850/2010";
                    break;
            }
        }
        if (ec.getTa_branch() != null) civBranch = ec.getTa_branch() == 1 ? "ΤΕ":"ΠΕ";

        proc.setParameter("SP_ANNOUNCEMENT_ID", ec.getId());
        proc.setParameter("SP_BRANCH0_ID", ec.getBranch0Id());
        proc.setParameter("SP_BRANCH_ID", ec.getBranch1Id());
        proc.setParameter("SP_REPR_FIRSTNAME", ec.getReprFirstname());
        proc.setParameter("SP_REPR_LASTNAME", ec.getReprLastname());
        proc.setParameter("SP_REPR_PHONE", ec.getReprPhone());
        proc.setParameter("SP_REPR_MOBILE", ec.getReprMobile());
        proc.setParameter("SP_TECH_FIRSTNAME", ec.getTa_firstname());
        proc.setParameter("SP_TECH_LASTNAME", ec.getTa_lastname());
        proc.setParameter("SP_TECH_FATHERNAME", ec.getTa_fathername());
        proc.setParameter("SP_TECH_TYPE", techType);
        proc.setParameter("SP_MIL_CATEGORY", milCategory);
        proc.setParameter("SP_MIL_RANK", ec.getTa_rank());
        proc.setParameter("SP_CIV_BRANCH", civBranch);
        proc.setParameter("SP_CIV_SPECIALITY", ec.getTa_speciality());
        proc.setParameter("REQUEST_TYPE_ID", 25);
        proc.setParameter("INSP_ADDR", ec.getInspAddr());
        proc.setParameter("INSP_ADDR_K", Long.parseLong(ec.getInspAddrK()));
        proc.setParameter("INSP_ADDR_TK", ec.getInspAddrTk());
        proc.setParameter("COMPL_ADDRESS_DESC", ec.getComplAddressDesc());
        proc.setParameter("SP_ATTACHED_DOC_ID", ec.getAttachedDocId());
        proc.setParameter("SP_DELIVERY_TYPE_CODE", 2);
        proc.setParameter("SP_PORTAL_UID", ec.getCompanyId());
        proc.execute();
        String out_errMsg = (String) proc.getOutputParameterValue("out_errMsg");
        if (out_errMsg == null || out_errMsg.isEmpty()) {
            Long docId = null;
            Long department = null;
            if(null == proc.getOutputParameterValue("SP_DOC_ID")) {
                docId = (Long) proc.getOutputParameterValue("SP_DOC_ID");
            }
            else {
                docId = new Long((Integer) proc.getOutputParameterValue("SP_DOC_ID"));
            }

            String protocolNumber = (String) proc.getOutputParameterValue("SP_PROTOCOL_NUMBER");
            Date protocolDate = (Date) proc.getOutputParameterValue("SP_PROTOCOL_DATE");
            Integer year = (Integer) proc.getOutputParameterValue("SP_PROTOCOL_YEAR");
            String time = (String) proc.getOutputParameterValue("SP_TIME");
            if(null == proc.getOutputParameterValue("SP_DEPARTMENT")) {
                department = (Long) proc.getOutputParameterValue("SP_DEPARTMENT");
            }
            else {
                department = new Long((Integer) proc.getOutputParameterValue("SP_DEPARTMENT"));
            }

            ec.setDocId(docId);
            ec.setProtNo(protocolNumber);
            ec.setProtDate(new Timestamp(protocolDate.getTime()));
            ec.setProtYear(year);
            ec.setSubmitTime(time);
            ec.setSepeDept(department);
        } else {
            throw new Exception(out_errMsg);
        }

        return ec;
    }

    // Αναγγελίες Τεχνικών Δευτεροβάθμιας Εκπαίδευσης
    public SpPtlCompTaAnnSe procIntArchiveCompTaAnnSe(SpPtlCompTaAnnSe ec) throws Exception {
        StoredProcedureQuery proc = em.createStoredProcedureQuery("SN_PROC_INT_ARCHIVE_TA_SE");
        proc.registerStoredProcedureParameter("SP_ANNOUNCEMENT_ID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_PORTAL_UID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_BRANCH0_ID", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_BRANCH_ID", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_DATE_START", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_DATE_END", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_BRANCH_SECTOR", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_TECH_FIRSTNAME", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_TECH_LASTNAME", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_TECH_FATHERNAME", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_TECH_AFM", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_TECH_PHONE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_TECH_EMAIL", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("TECH_ADDR", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("TECH_ADDR_K", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("TECH_ADDR_TK", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EDUCATION_TITLE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EDUCATION_NUM", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EMPLOYERS_B", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EMPLOYERS_C", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_VISIT_MINUTES", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ATTACHED_DOC_ID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_DOC_ID", Long.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_PROTOCOL_NUMBER", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_PROTOCOL_DATE", Date.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_PROTOCOL_YEAR", Integer.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_TIME", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_DEPARTMENT", Long.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("out_errMsg", String.class, ParameterMode.OUT);

        Map<String, Object> input = new LinkedHashMap<String, Object>();
        input.put("SUMCOMPANY_A", 0);
        input.put("SUMCOMPANY_B", ec.getCategBNum());
        input.put("SUMCOMPANY_C", ec.getCategCNum());
        Integer computedMinutes = (companyService).funcIntCalculateMinTaHours(input);

        proc.setParameter("SP_ANNOUNCEMENT_ID", ec.getId());
        proc.setParameter("SP_PORTAL_UID", ec.getCompanyId());
        proc.setParameter("SP_BRANCH0_ID", ec.getCompEbrBranch0Id());
        proc.setParameter("SP_BRANCH_ID", ec.getCompEbrBranchId());
        proc.setParameter("SP_DATE_START", ec.getDateStart());
        proc.setParameter("SP_DATE_END", ec.getDateEnd());
        proc.setParameter("SP_BRANCH_SECTOR", ec.getBranchSector());
        proc.setParameter("SP_TECH_FIRSTNAME", ec.getTaFirstName());
        proc.setParameter("SP_TECH_LASTNAME", ec.getTaLastName());
        proc.setParameter("SP_TECH_FATHERNAME", ec.getTaFatherName());
        proc.setParameter("SP_TECH_AFM", ec.getTaAfm());
        proc.setParameter("SP_TECH_PHONE", ec.getTaPhone());
        proc.setParameter("SP_TECH_EMAIL", ec.getTaEmail());
        proc.setParameter("TECH_ADDR", ec.getTaAddr());
        proc.setParameter("TECH_ADDR_K", Constants.StringIsNullOrEmpty(ec.getTaAddrK()) ? -1 : Long.parseLong(ec.getTaAddrK()));
        proc.setParameter("TECH_ADDR_TK", ec.getTaAddrTk());
        proc.setParameter("SP_EDUCATION_TITLE", ec.getEduTitle());
        proc.setParameter("SP_EDUCATION_NUM", ec.getEduNum());
        proc.setParameter("SP_EMPLOYERS_B", ec.getCategBNum());
        proc.setParameter("SP_EMPLOYERS_C", ec.getCategCNum());
        proc.setParameter("SP_VISIT_MINUTES", computedMinutes);
        proc.setParameter("SP_ATTACHED_DOC_ID", ec.getAttachedDocId());
        proc.execute();
        String out_errMsg = (String) proc.getOutputParameterValue("out_errMsg");
        if (out_errMsg == null || out_errMsg.isEmpty()) {
            Long docId = null;
            Long department = null;
            if(null == proc.getOutputParameterValue("SP_DOC_ID")) {
                docId = (Long) proc.getOutputParameterValue("SP_DOC_ID");
            }
            else {
                docId = new Long((Integer) proc.getOutputParameterValue("SP_DOC_ID"));
            }

            String protocolNumber = (String) proc.getOutputParameterValue("SP_PROTOCOL_NUMBER");
            Date protocolDate = (Date) proc.getOutputParameterValue("SP_PROTOCOL_DATE");
            Integer year = (Integer) proc.getOutputParameterValue("SP_PROTOCOL_YEAR");
            String time = (String) proc.getOutputParameterValue("SP_TIME");
            if(null == proc.getOutputParameterValue("SP_DEPARTMENT")) {
                department = (Long) proc.getOutputParameterValue("SP_DEPARTMENT");
            }
            else {
                department = new Long((Integer) proc.getOutputParameterValue("SP_DEPARTMENT"));
            }

            ec.setDocId(docId);
            ec.setProtNo(protocolNumber);
            ec.setProtDate(new Timestamp(protocolDate.getTime()));
            ec.setProtYear(year);
            ec.setSubmitTime(time);
            ec.setSepeDept(department);
        } else {
            throw new Exception(out_errMsg);
        }

        return ec;
    }

    //Company Xorigisi Antigrafwn
    public SpPtlCompGenreq procIntArchiveCompGenReqCertificate(SpPtlCompGenreq ec) throws Exception {
        StoredProcedureQuery proc = em.createStoredProcedureQuery("SN_proc_int_comp_genreq");
        proc.registerStoredProcedureParameter("SP_PORTAL_UID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ACC_DELIVERY_TYPE_CODE", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_REPR_FIRSTNAME", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_REPR_LASTNAME", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_REPR_FATHERNAME", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_REPR_MOTHERNAME", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_REPR_ADDR", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_REPR_ADDR_TK", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_REPR_ADDR_KALLIKRATIS", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_REPR_AFM", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_REPR_AMKA", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_REPR_PHONE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_REPR_MOBILE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_REPR_CARD_TYPE", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_REPR_CARD_NUMBER", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_REPR_REQUEST_TYPE_ID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_DOC_ID_ATTACHED", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_DESCR", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_NOTES", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_DEPARTMENT", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_OTHER_DEPARTMENT", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_DEP_KALLIKRATIS", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_GENREQ_OBJECT", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_GENREQ_PTLID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_DOC_ID", Long.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_PROTOCOL_NUMBER", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_PROTOCOL_DATE", Date.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_PROTOCOL_YEAR", Integer.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_TIME", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_COM_DEPARTMENT", Long.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("out_errMsg", String.class, ParameterMode.OUT);

        proc.setParameter("SP_PORTAL_UID", ec.getCompanyId());
        proc.setParameter("SP_ACC_DELIVERY_TYPE_CODE", 2);
        proc.setParameter("SP_REPR_FIRSTNAME", ec.getReprFirstname());
        proc.setParameter("SP_REPR_LASTNAME", ec.getReprLastname());
        proc.setParameter("SP_REPR_FATHERNAME", ec.getReprFathername());
        proc.setParameter("SP_REPR_MOTHERNAME", ec.getReprMothername());
        proc.setParameter("SP_REPR_ADDR", ec.getReprAddr());
        proc.setParameter("SP_REPR_ADDR_TK", ec.getReprAddrTk());
        proc.setParameter("SP_REPR_ADDR_KALLIKRATIS", Constants.StringIsNullOrEmpty(ec.getReprAddrK()) ? -1 : Integer.parseInt(ec.getReprAddrK()));
        proc.setParameter("SP_REPR_AFM", ec.getReprAfm());
        proc.setParameter("SP_REPR_AMKA", ec.getReprAmka());
        proc.setParameter("SP_REPR_PHONE", ec.getReprPhone());
        proc.setParameter("SP_REPR_MOBILE", ec.getReprMobile());
        proc.setParameter("SP_REPR_CARD_TYPE", ec.getReprCardType());
        proc.setParameter("SP_REPR_CARD_NUMBER", ec.getReprCardNumber());
        proc.setParameter("SP_REPR_REQUEST_TYPE_ID", ec.getRequestTypeId());
        proc.setParameter("SP_DOC_ID_ATTACHED", ec.getAttachedDocId());
        proc.setParameter("SP_DESCR", ec.getDescr());
        proc.setParameter("SP_NOTES", ec.getNotes());
        proc.setParameter("SP_DEPARTMENT", ec.getBranch1Id());
        proc.setParameter("SP_OTHER_DEPARTMENT", ec.getComplAddressDesc());
        proc.setParameter("SP_DEP_KALLIKRATIS", Constants.StringIsNullOrEmpty(ec.getInspAddrK()) ? -1 : Integer.parseInt(ec.getInspAddrK()));
        proc.setParameter("SP_GENREQ_OBJECT", ec.getReqSubject());
        proc.setParameter("SP_GENREQ_PTLID", ec.getId());
        proc.execute();
        String out_errMsg = (String) proc.getOutputParameterValue("out_errMsg");
        if (out_errMsg == null || out_errMsg.isEmpty()) {
            Long docId = null;
            Long department = null;
            if(null == proc.getOutputParameterValue("SP_DOC_ID")) {
                docId = (Long) proc.getOutputParameterValue("SP_DOC_ID");
            }
            else {
                docId = new Long((Integer) proc.getOutputParameterValue("SP_DOC_ID"));
            }

            String protocolNumber = (String) proc.getOutputParameterValue("SP_PROTOCOL_NUMBER");
            Date protocolDate = (Date) proc.getOutputParameterValue("SP_PROTOCOL_DATE");
            Integer year = (Integer) proc.getOutputParameterValue("SP_PROTOCOL_YEAR");
            String time = (String) proc.getOutputParameterValue("SP_TIME");
            if(null == proc.getOutputParameterValue("SP_COM_DEPARTMENT")) {
                department = (Long) proc.getOutputParameterValue("SP_COM_DEPARTMENT");
            }
            else {
                department = new Long((Integer) proc.getOutputParameterValue("SP_COM_DEPARTMENT"));
            }

            ec.setDocId(docId);
            ec.setProtNo(protocolNumber);
            ec.setProtDate(new Timestamp(protocolDate.getTime()));
            ec.setProtYear(year);
            ec.setSubmitTime(time);
            ec.setSepeDept(department);

            // Call generic procedure for insert empty doc and get docId
            ec = procIntArchiveInsertEmptyDoc(ec);
        } else {
            throw new Exception(out_errMsg);
        }

        return ec;
    }

    public SpPtlCompGenreq procIntArchiveInsertEmptyDoc(SpPtlCompGenreq ec) throws Exception {
        StoredProcedureQuery proc = em.createStoredProcedureQuery("SN_PROC_INT_PORTAL_PROT");
        proc.registerStoredProcedureParameter("SP_PROT_SENDER", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_PROT_IN", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_PROT_OUT", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_PROT_LOCAL", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_PROT_CANCEL", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_PROT_SUBJECT", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_PROT_CATEG_CODE", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_PROT_USER_ARCHIVE", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_PROT_USER_UPDATE", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_PROT_RECEIVERS", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_PROT_NOTIFIERS", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_PROT_STATUS", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_PROT_ASSIGNEE", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_PROT_DEPARTMENT", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_PROT_REPLY_DOC", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_PROT_REPLY_DOCS", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_PROT_HANDLING_DATE", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_PROT_CASE_ID", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_PROT_CHARGE_EMPLOYEE", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_PROT_IN_RECEIVERS", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_PROT_IN_NOTIFIERS", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_PROT_DELIVERY", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_PROT_SENDER_PROT_NUMBER", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_PROT_SENDER_PROT_DATE", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_PROT_GRADUATION", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_PROT_RECEIPT", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_PROT_CORRECT_DATE", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_PROT_FOLDER", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_PROT_REPLY_CHECK", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_PROT_PORTAL_UID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_PROT_UID", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_PROT_AUDITREPORT_ID", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_PROT_DOC_ID", Long.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_PROT_PROTOCOL_NUMBER", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_PROT_PROTOCOL_YEAR", Long.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_PROT_PROTOCOL_DATE", Date.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_PROT_FULL_PROTOCOL_NUMBER", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_PROT_TIME", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_PROT_DEPARTMENT_CODE", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("out_errMsg", String.class, ParameterMode.OUT);

        CompanyDTO company = userService.findCompany(ec.getCompanyId());
        String companyName = "";
        if (company != null) {
            companyName = company.getName();
        }

        proc.setParameter("SP_PROT_IN", 0);
        proc.setParameter("SP_PROT_OUT", 1);
        proc.setParameter("SP_PROT_LOCAL", 0);
        proc.setParameter("SP_PROT_CANCEL", 0);
        proc.setParameter("SP_PROT_SUBJECT", "Πιστοποιητικό του παρ.2γ, άρθρου 73 του Ν.4412/2016 της Εταιρίας" + companyName);
        proc.setParameter("SP_PROT_CATEG_CODE", 44);
        proc.setParameter("SP_PROT_USER_ARCHIVE", 1000000L);
        proc.setParameter("SP_PROT_STATUS", 2);
        proc.setParameter("SP_PROT_ASSIGNEE", 1000000L);
        proc.setParameter("SP_PROT_DEPARTMENT", "10031");
        proc.setParameter("SP_PROT_REPLY_DOC", ec.getDocId());
        proc.setParameter("SP_PROT_HANDLING_DATE", new Date(new Timestamp(System.currentTimeMillis()).getTime()));
        proc.setParameter("SP_PROT_CASE_ID", "");
        proc.setParameter("SP_PROT_CHARGE_EMPLOYEE", 100443L);
        proc.setParameter("SP_PROT_DELIVERY", "Αυτόματη Πρωτοκόλληση");
        proc.setParameter("SP_PROT_GRADUATION", "Κανονικό");
        proc.setParameter("SP_PROT_RECEIPT", "Διαλειτουργικότητα");
        proc.setParameter("SP_PROT_REPLY_CHECK", 1);
        proc.setParameter("SP_PROT_PORTAL_UID", ec.getCompanyId());
        proc.execute();
        String out_errMsg = (String) proc.getOutputParameterValue("out_errMsg");
        if (out_errMsg == null || out_errMsg.isEmpty()) {
            Long docId = null;
            Long department = null;
            if(null == proc.getOutputParameterValue("SP_PROT_DOC_ID")) {
                docId = (Long) proc.getOutputParameterValue("SP_PROT_DOC_ID");
            }
            else {
                docId = new Long((Integer) proc.getOutputParameterValue("SP_PROT_DOC_ID"));
            }

            String protocolNumber = (String) proc.getOutputParameterValue("SP_PROT_PROTOCOL_NUMBER");
            Date protocolDate = (Date) proc.getOutputParameterValue("SP_PROT_PROTOCOL_DATE");
            Integer year = (Integer) proc.getOutputParameterValue("SP_PROT_PROTOCOL_YEAR");
            String time = (String) proc.getOutputParameterValue("SP_PROT_TIME");

            ec.setAnswerPdfDocId(docId);
            ec.setAnswerPdfProtNo(protocolNumber);
            ec.setAnswerPdfProtYear(year);

        } else {
            throw new Exception(out_errMsg);
        }

        return ec;
    }

    //Company Ypoboli eksigisewn epixeirisis (meta apo elegxo)
    public SpPtlCompExplanation procIntArchiveCompExplanation(SpPtlCompExplanation ec) throws Exception {
        StoredProcedureQuery proc = em.createStoredProcedureQuery("SN_proc_int_explanations");
        proc.registerStoredProcedureParameter("SP_EXPL_EMP_FULL_NAME", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EXPL_EMP_PHONE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EXPL_EMP_FULL_NAME_TA", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EXPL_EMP_FULL_NAME_IE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EXPL_EMP_ADDRESS", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EXPL_EMP_ZIP_CODE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EXPL_EMP_KALLIKRATIS", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EXPL_EMP_AME", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EXPL_EKP_FIRST_NAME", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EXPL_EKP_LAST_NAME", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EXPL_EKP_PHONE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EXPL_EKP_ADDRESS", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EXPL_EKP_ZIP_CODE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EXPL_EKP_KALLIKRATIS", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EXPL_EKP_TAX_NUMBER", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EXPL_EKP_AMKA", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EXPL_DELIVERY_TYPE_CODE", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EXPL_PORTAL_UID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EXPL_EBR_BRANCH0_ID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EXPL_EBR_BRANCH_ID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EXPL_COMMENTS", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EXPL_CASE_ID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EXPL_CTRL_NO", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EXPL_INSPECTOR_NAME", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EXPL_CTRL_DATE", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EXPL_ATTFILE_DESCRIPTION", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EXPL_ATTACHED_DOC_ID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EXPL_KE_TE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EXPL_DEPARTMENT", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EXPL_DOC_ID", Long.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_EXPL_PROTOCOL_NUMBER", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_EXPL_PROTOCOL_DATE", Date.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_EXPL_PROTOCOL_YEAR", Integer.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_EXPL_TIME", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("out_errMsg", String.class, ParameterMode.OUT);

        proc.setParameter("SP_EXPL_EMP_FULL_NAME", ec.getCompName());
        proc.setParameter("SP_EXPL_EMP_PHONE", ec.getCompPhone());
        proc.setParameter("SP_EXPL_EMP_FULL_NAME_TA", ec.getTaFullname());
        proc.setParameter("SP_EXPL_EMP_FULL_NAME_IE", ec.getIeFullname());
        proc.setParameter("SP_EXPL_EMP_ADDRESS", ec.getCompAddr());
        proc.setParameter("SP_EXPL_EMP_ZIP_CODE", ec.getCompAddrTk());
        proc.setParameter("SP_EXPL_EMP_KALLIKRATIS", Constants.StringIsNullOrEmpty(ec.getCompAddrK()) ? -1 : Integer.parseInt(ec.getCompAddrK()));
        proc.setParameter("SP_EXPL_EMP_AME", ec.getCompAme());
        proc.setParameter("SP_EXPL_EKP_FIRST_NAME", "");
        proc.setParameter("SP_EXPL_EKP_LAST_NAME","");
        proc.setParameter("SP_EXPL_EKP_PHONE", "");
        proc.setParameter("SP_EXPL_EKP_ADDRESS", "");
        proc.setParameter("SP_EXPL_EKP_ZIP_CODE", "");
        proc.setParameter("SP_EXPL_EKP_KALLIKRATIS", -1);
        proc.setParameter("SP_EXPL_EKP_TAX_NUMBER", "");
        proc.setParameter("SP_EXPL_EKP_AMKA","");
        proc.setParameter("SP_EXPL_DELIVERY_TYPE_CODE",2);
        proc.setParameter("SP_EXPL_PORTAL_UID", ec.getCompanyId());
        proc.setParameter("SP_EXPL_EBR_BRANCH0_ID", ec.getBranch0Id());
        proc.setParameter("SP_EXPL_EBR_BRANCH_ID", ec.getBranch1Id());
        proc.setParameter("SP_EXPL_COMMENTS", ec.getDescr());
        proc.setParameter("SP_EXPL_CASE_ID", Constants.StringIsNullOrEmpty(ec.getExternalProtNo()) ? new Long(-1) : Long.parseLong(ec.getExternalProtNo()));
        proc.setParameter("SP_EXPL_CTRL_NO", ec.getControlStatementNum());
        proc.setParameter("SP_EXPL_INSPECTOR_NAME", ec.getInspectorName()); //change to NUMBER
        proc.setParameter("SP_EXPL_CTRL_DATE", new Date(ec.getInspectionDate().getTime()));
        proc.setParameter("SP_EXPL_ATTFILE_DESCRIPTION", ec.getAttachedDocDescr());
        proc.setParameter("SP_EXPL_ATTACHED_DOC_ID", ec.getAttachedDocId());
        proc.setParameter("SP_EXPL_KE_TE", ec.getTeKe());
        proc.setParameter("SP_EXPL_DEPARTMENT", ec.getSepeDept());

        proc.execute();
        String out_errMsg = (String) proc.getOutputParameterValue("out_errMsg");
        if (out_errMsg == null || out_errMsg.isEmpty()) {
            Long docId = null;
            if(null == proc.getOutputParameterValue("SP_EXPL_DOC_ID")) {
                docId = (Long) proc.getOutputParameterValue("SP_EXPL_DOC_ID");
            }
            else {
                docId = new Long((Integer) proc.getOutputParameterValue("SP_EXPL_DOC_ID"));
            }

            String protocolNumber = (String) proc.getOutputParameterValue("SP_EXPL_PROTOCOL_NUMBER");
            Date protocolDate = (Date) proc.getOutputParameterValue("SP_EXPL_PROTOCOL_DATE");
            Integer year = (Integer) proc.getOutputParameterValue("SP_EXPL_PROTOCOL_YEAR");
            String time = (String) proc.getOutputParameterValue("SP_EXPL_TIME");

            ec.setDocId(docId);
            ec.setProtNo(protocolNumber);
            ec.setProtDate(new Timestamp(protocolDate.getTime()));
            ec.setProtYear(year);
            ec.setSubmitTime(time);
        } else {
            throw new Exception(out_errMsg);
        }

        return ec;
    }

    //Company Τήρηση Βιβλιαρίου Εργασίας (ρεπό) οδηγών τουριστικών λεωφορείων
    public SpPtlCompDriverPmt procIntArchiveCompDriverPmt(SpPtlCompDriverPmt ec) throws Exception {
        StoredProcedureQuery proc = em.createStoredProcedureQuery("SN_proc_int_archive_driverPmt");
        proc.registerStoredProcedureParameter("SP_PORTAL_UID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_DELIVERY_TYPE_CODE", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_NAME", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_AFM", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_AME_IKA", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_PHONE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_ADDR", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_ADDR_TK", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_KALLIKRATIS", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_BRANCH0_ID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_BRANCH_ID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_OWNER_FIRSTNAME", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_OWNER_LASTNAME", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_OWNER_AFM", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_OWNER_CARD_NUMBER", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_OWNER_CARD_TYPE", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_OWNER_CARD_ISSUER", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_OWNER_LICENCE_NUM", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_VEHICLE_LICENCE_NUM", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_PMT_MONTH", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_PMT_YEAR", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_INSPECTOR_NAME", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ETIOLOGY", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_VEHICLE_PLATE_NUM", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_DRIVERPMT_ID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_DOC_ID", Long.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_PROTOCOL_NUMBER", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_PROTOCOL_DATE", Date.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_PROTOCOL_YEAR", Integer.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_TIME", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_COM_DEPARTMENT", Long.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("out_errMsg", String.class, ParameterMode.OUT);

        proc.setParameter("SP_PORTAL_UID", ec.getCompanyId());
        proc.setParameter("SP_DELIVERY_TYPE_CODE", 2);
        proc.setParameter("SP_COMP_NAME", ec.getCompName());
        proc.setParameter("SP_COMP_AFM", ec.getCompAfm());
        proc.setParameter("SP_COMP_AME_IKA", ec.getCompAmeIka());
        proc.setParameter("SP_COMP_PHONE", ec.getCompPhone());
        proc.setParameter("SP_COMP_ADDR", ec.getCompAddr());
        proc.setParameter("SP_COMP_ADDR_TK", ec.getCompAddrTk());
        proc.setParameter("SP_COMP_KALLIKRATIS", Constants.StringIsNullOrEmpty(ec.getCompAddrK()) ? -1 : Integer.parseInt(ec.getCompAddrK()));
        proc.setParameter("SP_COMP_BRANCH0_ID", ec.getBranch0Id());
        proc.setParameter("SP_COMP_BRANCH_ID", ec.getBranch1Id());
        proc.setParameter("SP_OWNER_FIRSTNAME", ec.getOwnerFirstname());
        proc.setParameter("SP_OWNER_LASTNAME", ec.getOwnerLastname());
        proc.setParameter("SP_OWNER_AFM", ec.getOwnerAfm());
        proc.setParameter("SP_OWNER_CARD_NUMBER", ec.getOwnerCardNumber());
        proc.setParameter("SP_OWNER_CARD_TYPE", ec.getOwnerCardType());
        proc.setParameter("SP_OWNER_CARD_ISSUER", ec.getOwnerCardIssuer());
        proc.setParameter("SP_OWNER_LICENCE_NUM", ec.getOwnerLicenceNum());
        proc.setParameter("SP_VEHICLE_LICENCE_NUM", ec.getVehicleLicenceNum());
        proc.setParameter("SP_PMT_MONTH", ec.getPmtMonth());
        proc.setParameter("SP_PMT_YEAR", ec.getPmtYear());
        proc.setParameter("SP_INSPECTOR_NAME", ec.getInspectorName());
        proc.setParameter("SP_ETIOLOGY", ec.getEtiology());
        proc.setParameter("SP_VEHICLE_PLATE_NUM", ec.getVehiclePlateNum());
        proc.setParameter("SP_DRIVERPMT_ID", ec.getId());

        proc.execute();
        String out_errMsg = (String) proc.getOutputParameterValue("out_errMsg");
        if (out_errMsg == null || out_errMsg.isEmpty()) {
            Long docId = null;
            //Long protocolNumber = null;
            Long department = null;
            if(null == proc.getOutputParameterValue("SP_DOC_ID")) {
                docId = (Long) proc.getOutputParameterValue("SP_DOC_ID");
            }
            else {
                docId = new Long((Integer) proc.getOutputParameterValue("SP_DOC_ID"));
            }
            /*if(null == proc.getOutputParameterValue("SP_PROTOCOL_NUMBER")) {
                protocolNumber = (Long) proc.getOutputParameterValue("SP_PROTOCOL_NUMBER");
            }
            else {
                protocolNumber = new Long((Integer) proc.getOutputParameterValue("SP_PROTOCOL_NUMBER"));
            }*/

            String protocolNumber = (String) proc.getOutputParameterValue("SP_PROTOCOL_NUMBER");
            Date protocolDate = (Date) proc.getOutputParameterValue("SP_PROTOCOL_DATE");
            Integer year = (Integer) proc.getOutputParameterValue("SP_PROTOCOL_YEAR");
            String time = (String) proc.getOutputParameterValue("SP_TIME");
            if(null == proc.getOutputParameterValue("SP_COM_DEPARTMENT")) {
                department = (Long) proc.getOutputParameterValue("SP_COM_DEPARTMENT");
            }
            else {
                department = new Long((Integer) proc.getOutputParameterValue("SP_COM_DEPARTMENT"));
            }

            ec.setDocId(docId);
            ec.setProtNo(protocolNumber);
            ec.setProtDate(new Timestamp(protocolDate.getTime()));
            ec.setProtYear(year);
            ec.setSubmitTime(time);
            ec.setSepeDept(department);
        } else {
            throw new Exception(out_errMsg);
        }

        return ec;
    }

    //Company Βιβλίο Δρομολογίων Οχημάτων
    public SpPtlCompVehicleBook procIntArchiveCompVehicleBook(SpPtlCompVehicleBook ec) throws Exception {
        StoredProcedureQuery proc = em.createStoredProcedureQuery("SN_proc_int_vehicleBook");
        proc.registerStoredProcedureParameter("SP_PORTAL_UID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_DELIVERY_TYPE_CODE", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_NAME", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_AFM", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_AME", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_PHONE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_ADDR", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_ADDR_TK", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_KALLIKRATIS", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_BRANCH0_ID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_BRANCH_ID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_OWNER_FIRSTNAME", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_OWNER_LASTNAME", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_OWNER_AFM", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_OWNER_CARD_NUMBER", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_OWNER_CARD_TYPE", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_OWNER_CARD_ISSUING", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_OWNER_BIRTHPLACE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_OWNER_BIRTHDATE", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_OWNER_ADDR", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_VEHICLE_TYPE", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_VEHICLE_LICENCE_NUM", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_DOC_ID", Long.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_PROTOCOL_NUMBER", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_PROTOCOL_DATE", Date.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_PROTOCOL_YEAR", Integer.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_TIME", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_COM_DEPARTMENT", Long.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("out_errMsg", String.class, ParameterMode.OUT);

        proc.setParameter("SP_PORTAL_UID", ec.getCompanyId());
        proc.setParameter("SP_DELIVERY_TYPE_CODE", 2);
        proc.setParameter("SP_COMP_NAME", ec.getCompName());
        proc.setParameter("SP_COMP_AFM", ec.getCompAfm());
        proc.setParameter("SP_COMP_AME", ec.getCompAme());
        proc.setParameter("SP_COMP_PHONE", ec.getCompPhone());
        proc.setParameter("SP_COMP_ADDR", ec.getCompAddr());
        proc.setParameter("SP_COMP_ADDR_TK", ec.getCompAddrTk());
        proc.setParameter("SP_COMP_KALLIKRATIS", Constants.StringIsNullOrEmpty(ec.getCompAddrK()) ? -1 : Integer.parseInt(ec.getCompAddrK()));
        proc.setParameter("SP_COMP_BRANCH0_ID", ec.getCompEbrBranch0Id());
        proc.setParameter("SP_COMP_BRANCH_ID", ec.getCompEbrBranch1Id());
        proc.setParameter("SP_OWNER_FIRSTNAME", ec.getOwnerFirstname());
        proc.setParameter("SP_OWNER_LASTNAME", ec.getOwnerLastname());
        proc.setParameter("SP_OWNER_AFM", ec.getOwnerAfm());
        proc.setParameter("SP_OWNER_CARD_NUMBER", ec.getOwnerCardNumber());
        proc.setParameter("SP_OWNER_CARD_TYPE", ec.getOwnerCardType());
        proc.setParameter("SP_OWNER_CARD_ISSUING", ec.getOwnerCardIssuingAuth());
        proc.setParameter("SP_OWNER_BIRTHPLACE", ec.getOwnerBirthplace());
        proc.setParameter("SP_OWNER_BIRTHDATE", new Date(ec.getOwnerBirthdate().getTime()));
        proc.setParameter("SP_OWNER_ADDR", ec.getOwnerAddr());
        proc.setParameter("SP_VEHICLE_TYPE", ec.getVehicleType());
        proc.setParameter("SP_VEHICLE_LICENCE_NUM", ec.getVehicleLicenceNum());

        proc.execute();
        String out_errMsg = (String) proc.getOutputParameterValue("out_errMsg");
        if (out_errMsg == null || out_errMsg.isEmpty()) {
            Long docId = null;
            Long department = null;
            if(null == proc.getOutputParameterValue("SP_DOC_ID")) {
                docId = (Long) proc.getOutputParameterValue("SP_DOC_ID");
            }
            else {
                docId = new Long((Integer) proc.getOutputParameterValue("SP_DOC_ID"));
            }
            String protocolNumber = (String) proc.getOutputParameterValue("SP_PROTOCOL_NUMBER");
            Date protocolDate = (Date) proc.getOutputParameterValue("SP_PROTOCOL_DATE");
            Integer year = (Integer) proc.getOutputParameterValue("SP_PROTOCOL_YEAR");
            String time = (String) proc.getOutputParameterValue("SP_TIME");
            if(null == proc.getOutputParameterValue("SP_COM_DEPARTMENT")) {
                department = (Long) proc.getOutputParameterValue("SP_COM_DEPARTMENT");
            }
            else {
                department = new Long((Integer) proc.getOutputParameterValue("SP_COM_DEPARTMENT"));
            }

            ec.setDocId(docId);
            ec.setProtNo(protocolNumber);
            ec.setProtDate(new Timestamp(protocolDate.getTime()));
            ec.setProtYear(year);
            ec.setSubmitTime(time);
            ec.setSepeDept(department);
        } else {
            throw new Exception(out_errMsg);
        }

        return ec;
    }

    /*//Company Anaggelia Iatrou
    public SpPtlCompIeAnn procIntArchiveCompIeAnn(SpPtlCompIeAnn ec) throws Exception {
        StoredProcedureQuery proc = em.createStoredProcedureQuery("SN_proc_int_compIeAnn");
        proc.registerStoredProcedureParameter("SP_PORTAL_UID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_DELIVERY_TYPE_CODE", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_NAME", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_AFM", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_AME", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_PHONE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_ADDR", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_ADDR_TK", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_KALLIKRATIS", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_BRANCH0_ID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_BRANCH_ID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_IE_FULLNAME", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_IE_AFM", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_IE_SPECIALITY", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_IE_SPECIALITY_OTHER", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_IE_ANN_STATUS", Integer.class, ParameterMode.IN)
                //.registerStoredProcedureParameter("SP_IE_REPLACE_RESPONSE", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_CATEGORY_A_NUM", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_CATEGORY_B_NUM", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_CATEGORY_C_NUM", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_PTL_BRANCH", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COOPERATION_TYPE", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_DATE_START", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_DATE_END", Date.class, ParameterMode.IN)
                //.registerStoredProcedureParameter("SP_STATEMENT_IE_SEARCH", Integer.class, ParameterMode.IN)
                //.registerStoredProcedureParameter("SP_STATEMENT_ADJ_COUNTY", Integer.class, ParameterMode.IN)
                //.registerStoredProcedureParameter("SP_STATEMENT_DIFF_COUNTY", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_REG_REQUEST_USER_ID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ATTACHED_DOC_ID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_DOC_ID", Long.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_PROTOCOL_NUMBER", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_PROTOCOL_DATE", Date.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_PROTOCOL_YEAR", Integer.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_TIME", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_COM_DEPARTMENT", Long.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("out_errMsg", String.class, ParameterMode.OUT);

        proc.setParameter("SP_PORTAL_UID", ec.getCompanyId());
        proc.setParameter("SP_DELIVERY_TYPE_CODE", 2);
        proc.setParameter("SP_COMP_NAME", ec.getCompFullName());
        proc.setParameter("SP_COMP_AFM", ec.getCompTaxNumber());
        proc.setParameter("SP_COMP_AME", ec.getCompAme());
        proc.setParameter("SP_COMP_PHONE", ec.getCompPhone());
        proc.setParameter("SP_COMP_ADDR", ec.getCompAddr());
        proc.setParameter("SP_COMP_ADDR_TK", ec.getCompAddrTk());
        proc.setParameter("SP_COMP_KALLIKRATIS", Constants.StringIsNullOrEmpty(ec.getCompAddrK()) ? -1 : Integer.parseInt(ec.getCompAddrK()));
        proc.setParameter("SP_COMP_BRANCH0_ID", ec.getCompEbrBranch0Id());
        proc.setParameter("SP_COMP_BRANCH_ID", ec.getCompEbrBranchId());
        proc.setParameter("SP_IE_FULLNAME", ec.getIeFullname());
        proc.setParameter("SP_IE_AFM", ec.getIeAfm());
        proc.setParameter("SP_IE_SPECIALITY", ec.getIeSpeciality());
        proc.setParameter("SP_IE_SPECIALITY_OTHER", ec.getIeSpecialityOther());
        proc.setParameter("SP_IE_ANN_STATUS", ec.getIeAnnStatus());
        //proc.setParameter("SP_IE_REPLACE_RESPONSE", ec.getIeReplaceResponse());
        proc.setParameter("SP_CATEGORY_A_NUM", ec.getCategANum());
        proc.setParameter("SP_CATEGORY_B_NUM", ec.getCategBNum());
        proc.setParameter("SP_CATEGORY_C_NUM", ec.getCategCNum());
        proc.setParameter("SP_COMP_PTL_BRANCH", ec.getCompPtlBranchId());
        proc.setParameter("SP_COOPERATION_TYPE", ec.getCooperationTypeBasic());
        proc.setParameter("SP_DATE_START", new Date(ec.getDateStart().getTime()));
        proc.setParameter("SP_DATE_END", new Date(ec.getDateEnd().getTime()));
        //proc.setParameter("SP_STATEMENT_IE_SEARCH", ec.getStatementIeSearched());
        //proc.setParameter("SP_STATEMENT_ADJ_COUNTY", ec.getStatementAdjCounty());
        //proc.setParameter("SP_STATEMENT_DIFF_COUNTY", ec.getStatementDifCounty());
        proc.setParameter("SP_REG_REQUEST_USER_ID", ec.getDoctorRegrequestUserId());
        proc.setParameter("SP_ATTACHED_DOC_ID", ec.getAttachedDocId());

        proc.execute();
        String out_errMsg = (String) proc.getOutputParameterValue("out_errMsg");
        if (out_errMsg == null || out_errMsg.isEmpty()) {
            Long department = null;
            Integer docId = (Integer) proc.getOutputParameterValue("SP_DOC_ID");
            String protocolNumber = (String) proc.getOutputParameterValue("SP_PROTOCOL_NUMBER");
            Date protocolDate = (Date) proc.getOutputParameterValue("SP_PROTOCOL_DATE");
            Integer year = (Integer) proc.getOutputParameterValue("SP_PROTOCOL_YEAR");
            String time = (String) proc.getOutputParameterValue("SP_TIME");
            if(null == proc.getOutputParameterValue("SP_COM_DEPARTMENT")) {
                department = (Long) proc.getOutputParameterValue("SP_COM_DEPARTMENT");
            }
            else {
                department = new Long((Integer) proc.getOutputParameterValue("SP_COM_DEPARTMENT"));
            }

            ec.setDocId(docId);
            ec.setProtNo(protocolNumber);
            ec.setProtDate(new Timestamp(protocolDate.getTime()));
            ec.setProtYear(year);
            ec.setSubmitTime(time);
            ec.setSepeDept(department);
        } else {
            throw new Exception(out_errMsg);
        }

        return ec;
    }*/

    //Company Anaggelia Iatrou
    public SpPtlCompIeAnn procIntArchiveCompIeAnn(SpPtlCompIeAnn ec) throws Exception {
        StoredProcedureQuery proc = em.createStoredProcedureQuery("SN_proc_int_compIeAnn");
        proc.registerStoredProcedureParameter("SP_PORTAL_UID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_DELIVERY_TYPE_CODE", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_DESCR", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_NOTES", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ANNOUNCEMENT_TYPE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ANNOUNCEMENT_STATUS", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EXYPP", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_DATE_FROM", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_DATE_TO", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EMPLOYERS_A", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EMPLOYERS_B", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EMPLOYERS_C", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_VISIT_MINUTES", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_BRANCH0_ID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_BRANCH_ID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_AFM", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_ADDRESS", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_ZIP_CODE", String.class, ParameterMode.IN)

                .registerStoredProcedureParameter("SP_COMP_SERV_LOCATION", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_LEGAL_REPRESENTATIVE", String.class, ParameterMode.IN)

                .registerStoredProcedureParameter("SP_COMP_KALLIKRATIS", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ANNOUNCEMENT_ID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ATTACHED_DOC_ID", Long.class, ParameterMode.IN)

                .registerStoredProcedureParameter("SP_COMP_BR_ADDRESS", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_BR_ZIP_CODE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_BR_KALLIKRATIS", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_DOC_ID", Long.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_PROTOCOL_NUMBER", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_PROTOCOL_DATE", Date.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_PROTOCOL_YEAR", Integer.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_TIME", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_DEPARTMENT", Long.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("out_errMsg", String.class, ParameterMode.OUT);

        Map<String, Object> input = new LinkedHashMap<String, Object>();
        Integer oneDay = 24 * 60 * 60 * 1000;
        ec.getDateEnd().getTime();
        String dateEnd1 = new SimpleDateFormat("yyyy-M-d").format(new Date(ec.getDateEnd().getTime()));
        String dateStart1 = new SimpleDateFormat("yyyy-M-d").format(new Date(ec.getDateStart().getTime()));
        Long daysDiff = ((new SimpleDateFormat("yyyy-M-d")).parse(dateEnd1).getTime() - (new SimpleDateFormat("yyyy-M-d")).parse(dateStart1).getTime()) / oneDay;

        final CompanyDTO company = userService.findCompany(ec.getCompanyId());
        input.put("SUMCOMPANY", company.getSumEmplTotal());
        input.put("SUMCOMPANY_A", company.getSumEmplA());
        input.put("SUMCOMPANY_B", company.getSumEmplB());
        input.put("SUMCOMPANY_C", company.getSumEmplC());
        input.put("SUMDEPARTMENT_A", ec.getCategANum());
        input.put("SUMDEPARTMENT_B", ec.getCategBNum());
        input.put("SUMDEPARTMENT_C", ec.getCategCNum());
        input.put("NO_OF_DAYS", daysDiff);
        input.put("DATE_FROM", (new SimpleDateFormat("yyyy-M-d")).parse(dateStart1));
        input.put("DATE_TO", (new SimpleDateFormat("yyyy-M-d")).parse(dateEnd1));
        Integer computedHours = (companyService).funcIntCalculateIeHours(input);

        SpPtlCompPtlBranch branchPtl = spPtlCompPtlBranchRepo.findById(ec.getCompPtlBranchId());
        Long branch0Idnew;
        try {
            //TEmployerBranchIKA ika = ikaRepo.findCompanyMainBranchByAme(ec.getCompAme());
            TEmployerBranchIKA ika = ikaRepo.findCompanyMainBranchByAfmAndAme(ec.getCompTaxNumber(),ec.getCompAme());
            branch0Idnew = ika.getRgEbrEmpSepeId();
        }
        catch (Exception e){
            branch0Idnew = new Long(-1);
        }
        proc.setParameter("SP_PORTAL_UID", ec.getCompanyId());
        proc.setParameter("SP_DELIVERY_TYPE_CODE", 2);
        proc.setParameter("SP_DESCR", "");
        proc.setParameter("SP_NOTES", "");
        proc.setParameter("SP_ANNOUNCEMENT_TYPE", ec.getCooperationTypeBasic()==1 ? "ΑΝΕΞΑΡΤΗΤΟΙ ΤΕΧΝΙΚΟΙ" : "ΕΞΥΠΠ");
        proc.setParameter("SP_ANNOUNCEMENT_STATUS", ec.getIeAnnStatus());
        proc.setParameter("SP_EXYPP", ec.getExyppBasic()==null ? new Long(-1) : ec.getExyppBasic());
        proc.setParameter("SP_DATE_FROM", new Date(ec.getDateStart().getTime()));
        proc.setParameter("SP_DATE_TO", new Date(ec.getDateEnd().getTime()));
        proc.setParameter("SP_EMPLOYERS_A", ec.getCategANum());
        proc.setParameter("SP_EMPLOYERS_B", ec.getCategBNum());
        proc.setParameter("SP_EMPLOYERS_C", ec.getCategCNum());
        proc.setParameter("SP_VISIT_MINUTES", computedHours);
        proc.setParameter("SP_BRANCH0_ID",branch0Idnew);
        proc.setParameter("SP_BRANCH_ID", ec.getCompEbrBranchId());
        proc.setParameter("SP_COMP_AFM", ec.getCompTaxNumber());
        proc.setParameter("SP_COMP_ADDRESS", ec.getCompAddr());
        proc.setParameter("SP_COMP_ZIP_CODE", ec.getCompAddrTk());
        proc.setParameter("SP_COMP_SERV_LOCATION", branchPtl.getBrDescr());
        proc.setParameter("SP_COMP_LEGAL_REPRESENTATIVE", "");
        proc.setParameter("SP_COMP_KALLIKRATIS", Constants.StringIsNullOrEmpty(ec.getCompAddrK()) ? -1 : Integer.parseInt(ec.getCompAddrK()));
        proc.setParameter("SP_ANNOUNCEMENT_ID", ec.getId());
        proc.setParameter("SP_ATTACHED_DOC_ID", ec.getAttachedDocId());

        proc.setParameter("SP_COMP_BR_ADDRESS", branchPtl.getBrAddr());
        proc.setParameter("SP_COMP_BR_ZIP_CODE", branchPtl.getBrAddrTk());
        proc.setParameter("SP_COMP_BR_KALLIKRATIS", Constants.StringIsNullOrEmpty(branchPtl.getBrAddrK()) ? -1 : Integer.parseInt(branchPtl.getBrAddrK()));

        proc.execute();
        String out_errMsg = (String) proc.getOutputParameterValue("out_errMsg");
        String out_isProtNull = (String) proc.getOutputParameterValue("SP_PROTOCOL_NUMBER");
        if (out_isProtNull != null || !out_isProtNull.isEmpty()) {
            Integer docId = null;
            Long department = null;
            if(null == proc.getOutputParameterValue("SP_DOC_ID")) {
                docId = (Integer) proc.getOutputParameterValue("SP_DOC_ID");
            }
            else {
                docId = (Integer) proc.getOutputParameterValue("SP_DOC_ID");
            }
            String protocolNumber = (String) proc.getOutputParameterValue("SP_PROTOCOL_NUMBER");
            Date protocolDate = (Date) proc.getOutputParameterValue("SP_PROTOCOL_DATE");
            Integer year = (Integer) proc.getOutputParameterValue("SP_PROTOCOL_YEAR");
            String time = (String) proc.getOutputParameterValue("SP_TIME");
            if(null == proc.getOutputParameterValue("SP_DEPARTMENT")) {
                department = (Long) proc.getOutputParameterValue("SP_DEPARTMENT");
            }
            else {
                department = new Long((Integer) proc.getOutputParameterValue("SP_DEPARTMENT"));
            }

            ec.setDocId(docId);
            ec.setProtNo(protocolNumber);
            ec.setProtDate(new Timestamp(protocolDate.getTime()));
            ec.setProtYear(year);
            ec.setSubmitTime(time);
            ec.setSepeDept(department);
        } else {
            throw new Exception(out_errMsg);
        }

        return ec;
    }

    //Company Anaggelia Texnikou
    public SpPtlCompTaAnn procIntArchiveCompTaAnn(SpPtlCompTaAnn ec) throws Exception {
        StoredProcedureQuery proc = em.createStoredProcedureQuery("SN_proc_int_compTaAnn");
        proc.registerStoredProcedureParameter("SP_PORTAL_UID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_SHIP_OR_BRANCH", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_DELIVERY_TYPE_CODE", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_DESCR", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_NOTES", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ANNOUNCEMENT_TYPE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ANNOUNCEMENT_STATUS", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EXYPP", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_DATE_FROM", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_DATE_TO", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EMPLOYERS_A", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EMPLOYERS_B", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EMPLOYERS_C", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_VISIT_MINUTES", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_BRANCH0_ID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_BRANCH_ID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_AFM", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_ADDRESS", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_ZIP_CODE", String.class, ParameterMode.IN)

                .registerStoredProcedureParameter("SP_COMP_SERV_LOCATION", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_LEGAL_REPRESENTATIVE", String.class, ParameterMode.IN)

                .registerStoredProcedureParameter("SP_COMP_KALLIKRATIS", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ANNOUNCEMENT_ID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ATTACHED_DOC_ID", Long.class, ParameterMode.IN)

                .registerStoredProcedureParameter("SP_COMP_BR_ADDRESS", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_BR_ZIP_CODE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_BR_KALLIKRATIS", Integer.class, ParameterMode.IN)

                .registerStoredProcedureParameter("SP_COMP_SHIP", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_SHIP_MIN_WRKING_HRS", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_SHIP_DATE_START", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_SHIP_START_TIME", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_SHIP_DURATION_DAYS", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_SHIP_MAX_WRKRS_NUM", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_SHIP_DANGER_CTGR", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_SHIP_PORT_AUTHORITY", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_SHIP_SHIPYARD", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_SHP_ADDRESS", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_SHP_ZIP_CODE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_SHP_KALLIKRATIS", Integer.class, ParameterMode.IN)

                .registerStoredProcedureParameter("SP_DOC_ID", Long.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_PROTOCOL_NUMBER", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_PROTOCOL_DATE", Date.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_PROTOCOL_YEAR", Integer.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_TIME", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_DEPARTMENT", Long.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("out_errMsg", String.class, ParameterMode.OUT);

        Map<String, Object> input = new LinkedHashMap<String, Object>();
        Integer oneDay = 24 * 60 * 60 * 1000;
        ec.getDateEnd().getTime();
        String dateEnd1 = new SimpleDateFormat("yyyy-M-d").format(new Date(ec.getDateEnd().getTime()));
        String dateStart1 = new SimpleDateFormat("yyyy-M-d").format(new Date(ec.getDateStart().getTime()));
        Long daysDiff = ((new SimpleDateFormat("yyyy-M-d")).parse(dateEnd1).getTime() - (new SimpleDateFormat("yyyy-M-d")).parse(dateStart1).getTime()) / oneDay;

        final CompanyDTO company = userService.findCompany(ec.getCompanyId());
        input.put("SUMCOMPANY", company.getSumEmplTotal());
        input.put("SUMCOMPANY_A", company.getSumEmplA());
        input.put("SUMCOMPANY_B", company.getSumEmplB());
        input.put("SUMCOMPANY_C", company.getSumEmplC());
        input.put("SUMDEPARTMENT_A", ec.getCategANum());
        input.put("SUMDEPARTMENT_B", ec.getCategBNum());
        input.put("SUMDEPARTMENT_C", ec.getCategCNum());
        input.put("NO_OF_DAYS", daysDiff);
        input.put("DATE_FROM", (new SimpleDateFormat("yyyy-M-d")).parse(dateStart1));
        input.put("DATE_TO", (new SimpleDateFormat("yyyy-M-d")).parse(dateEnd1));
        Integer computedHours = (companyService).funcIntCalculateTaHours(input);

        SpPtlCompPtlBranch branchPtl = spPtlCompPtlBranchRepo.findById(ec.getCompPtlBranchId());
        Long branch0Idnew;
        try {
            //TEmployerBranchIKA ika = ikaRepo.findCompanyMainBranchByAme(ec.getCompAme());
            TEmployerBranchIKA ika = ikaRepo.findCompanyMainBranchByAfmAndAme(ec.getCompTaxNumber(),ec.getCompAme());
            branch0Idnew = ika.getRgEbrEmpSepeId();
        }
        catch (Exception e){
            branch0Idnew = new Long(-1);
        }

        proc.setParameter("SP_PORTAL_UID", ec.getCompanyId());
        proc.setParameter("SP_SHIP_OR_BRANCH", 1);
        proc.setParameter("SP_DELIVERY_TYPE_CODE", 2);
        proc.setParameter("SP_DESCR", "");
        proc.setParameter("SP_NOTES", "");
        proc.setParameter("SP_ANNOUNCEMENT_TYPE", ec.getCooperationTypeBasic()==1 ? "ΑΝΕΞΑΡΤΗΤΟΙ ΤΕΧΝΙΚΟΙ" : "ΕΞΥΠΠ");
        proc.setParameter("SP_ANNOUNCEMENT_STATUS", ec.getTaAnnStatus());
        proc.setParameter("SP_EXYPP", ec.getExyppBasic()==null ? new Long(-1) : ec.getExyppBasic());
        proc.setParameter("SP_DATE_FROM", new Date(ec.getDateStart().getTime()));
        proc.setParameter("SP_DATE_TO", new Date(ec.getDateEnd().getTime()));
        proc.setParameter("SP_EMPLOYERS_A", ec.getCategANum());
        proc.setParameter("SP_EMPLOYERS_B", ec.getCategBNum());
        proc.setParameter("SP_EMPLOYERS_C", ec.getCategCNum());
        proc.setParameter("SP_VISIT_MINUTES", computedHours);
        proc.setParameter("SP_BRANCH0_ID",branch0Idnew);
        proc.setParameter("SP_BRANCH_ID", ec.getCompEbrBranchId());
        proc.setParameter("SP_COMP_AFM", ec.getCompTaxNumber());
        proc.setParameter("SP_COMP_ADDRESS", ec.getCompAddr());
        proc.setParameter("SP_COMP_ZIP_CODE", ec.getCompAddrTk());
        proc.setParameter("SP_COMP_SERV_LOCATION", branchPtl.getBrDescr());
        proc.setParameter("SP_COMP_LEGAL_REPRESENTATIVE", "");
        proc.setParameter("SP_COMP_KALLIKRATIS", Constants.StringIsNullOrEmpty(ec.getCompAddrK()) ? -1 : Integer.parseInt(ec.getCompAddrK()));
        proc.setParameter("SP_ANNOUNCEMENT_ID", ec.getId());
        proc.setParameter("SP_ATTACHED_DOC_ID", ec.getAttachedDocId());


        proc.setParameter("SP_COMP_BR_ADDRESS", branchPtl.getBrAddr());
        proc.setParameter("SP_COMP_BR_ZIP_CODE", branchPtl.getBrAddrTk());
        proc.setParameter("SP_COMP_BR_KALLIKRATIS", Constants.StringIsNullOrEmpty(branchPtl.getBrAddrK()) ? -1 : Integer.parseInt(branchPtl.getBrAddrK()));

        proc.setParameter("SP_COMP_SHIP", null);
        proc.setParameter("SP_SHIP_MIN_WRKING_HRS", null);
        proc.setParameter("SP_SHIP_DATE_START", null);
        proc.setParameter("SP_SHIP_START_TIME", null);
        proc.setParameter("SP_SHIP_DURATION_DAYS", null);
        proc.setParameter("SP_SHIP_MAX_WRKRS_NUM", null);
        proc.setParameter("SP_SHIP_DANGER_CTGR", null);
        proc.setParameter("SP_SHIP_PORT_AUTHORITY", null);
        proc.setParameter("SP_SHIP_SHIPYARD", null);
        proc.setParameter("SP_COMP_SHP_ADDRESS", null);
        proc.setParameter("SP_COMP_SHP_ZIP_CODE", null);
        proc.setParameter("SP_COMP_SHP_KALLIKRATIS", null);

        proc.execute();
        String out_errMsg = (String) proc.getOutputParameterValue("out_errMsg");
        String out_isProtNull = (String) proc.getOutputParameterValue("SP_PROTOCOL_NUMBER");
        if (out_isProtNull != null || !out_isProtNull.isEmpty()) {
            Long docId = null;
            Long department = null;
            if(null == proc.getOutputParameterValue("SP_DOC_ID")) {
                docId = (Long) proc.getOutputParameterValue("SP_DOC_ID");
            }
            else {
                docId = new Long((Integer) proc.getOutputParameterValue("SP_DOC_ID"));
            }
            String protocolNumber = (String) proc.getOutputParameterValue("SP_PROTOCOL_NUMBER");
            Date protocolDate = (Date) proc.getOutputParameterValue("SP_PROTOCOL_DATE");
            Integer year = (Integer) proc.getOutputParameterValue("SP_PROTOCOL_YEAR");
            String time = (String) proc.getOutputParameterValue("SP_TIME");
            if(null == proc.getOutputParameterValue("SP_DEPARTMENT")) {
                department = (Long) proc.getOutputParameterValue("SP_DEPARTMENT");
            }
            else {
                department = new Long((Integer) proc.getOutputParameterValue("SP_DEPARTMENT"));
            }

            ec.setDocId(docId);
            ec.setProtNo(protocolNumber);
            ec.setProtDate(new Timestamp(protocolDate.getTime()));
            ec.setProtYear(year);
            ec.setSubmitTime(time);
            ec.setSepeDept(department);
        } else {
            throw new Exception(out_errMsg);
        }

        return ec;
    }

    //Company Anaggelia Texnikou Pause
    public SpPtlCompTaAnn procIntArchiveCompTaAnnPause(SpPtlCompTaAnn ec) throws Exception {
        StoredProcedureQuery proc = em.createStoredProcedureQuery("SN_PROC_INT_TA_DISMISSAL");
        proc.registerStoredProcedureParameter("SP_SHIP_OR_BRANCH", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ANNOUNCEMENT_TYPE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ANNOUNCEMENT_STATUS", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EXYPP", Long.class, ParameterMode.IN)

                .registerStoredProcedureParameter("SP_BRANCH0_ID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_BRANCH_ID", Long.class, ParameterMode.IN)

                .registerStoredProcedureParameter("SP_COMP_ADDRESS", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_ZIP_CODE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_AFM", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_SERV_LOCATION", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_KALLIKRATIS", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_LEGAL_REPRESENTATIVE", String.class, ParameterMode.IN)

                .registerStoredProcedureParameter("SP_COMP_BR_ADDRESS", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_BR_ZIP_CODE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_BRANCH_SERV_LOCATION", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_BR_KALLIKRATIS", Integer.class, ParameterMode.IN)

                .registerStoredProcedureParameter("SP_ATTACHED_DOC_ID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ANNOUNCEMENT_DOCID", Long.class, ParameterMode.IN)

                .registerStoredProcedureParameter("SP_DESCR", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_NOTES", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_DELIVERY_TYPE_CODE", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_PORTAL_UID", Long.class, ParameterMode.IN)

                .registerStoredProcedureParameter("SP_DOC_ID", Long.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_PROTOCOL_NUMBER", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_PROTOCOL_DATE", Date.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_PROTOCOL_YEAR", Integer.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_TIME", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_DEPARTMENT", Long.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("out_errMsg", String.class, ParameterMode.OUT);

        SpPtlCompPtlBranch branchPtl = spPtlCompPtlBranchRepo.findById(ec.getCompPtlBranchId());
        Long branch0Idnew;
        try {
            //TEmployerBranchIKA ika = ikaRepo.findCompanyMainBranchByAme(ec.getCompAme());
            TEmployerBranchIKA ika = ikaRepo.findCompanyMainBranchByAfmAndAme(ec.getCompTaxNumber(),ec.getCompAme());
            branch0Idnew = ika.getRgEbrEmpSepeId();
        }
        catch (Exception e){
            branch0Idnew = new Long(-1);
        }

        proc.setParameter("SP_SHIP_OR_BRANCH", 1);
        proc.setParameter("SP_ANNOUNCEMENT_TYPE", ec.getCooperationTypeBasic()==1 ? "ΑΝΕΞΑΡΤΗΤΟΙ ΤΕΧΝΙΚΟΙ" : "ΕΞΥΠΠ");
        proc.setParameter("SP_ANNOUNCEMENT_STATUS", ec.getTaAnnStatus());
        proc.setParameter("SP_EXYPP", ec.getExyppBasic()==null ? new Long(-1) : ec.getExyppBasic());
        proc.setParameter("SP_BRANCH0_ID",branch0Idnew);
        proc.setParameter("SP_BRANCH_ID", ec.getCompEbrBranchId());
        proc.setParameter("SP_COMP_ADDRESS", ec.getCompAddr());
        proc.setParameter("SP_COMP_ZIP_CODE", ec.getCompAddrTk());
        proc.setParameter("SP_COMP_AFM", ec.getCompTaxNumber());
        proc.setParameter("SP_COMP_SERV_LOCATION", branchPtl.getBrDescr());
        proc.setParameter("SP_COMP_KALLIKRATIS", Constants.StringIsNullOrEmpty(ec.getCompAddrK()) ? -1 : Integer.parseInt(ec.getCompAddrK()));
        proc.setParameter("SP_COMP_LEGAL_REPRESENTATIVE", "");
        proc.setParameter("SP_COMP_BR_ADDRESS", branchPtl.getBrAddr());
        proc.setParameter("SP_COMP_BR_ZIP_CODE", branchPtl.getBrAddrTk());
        proc.setParameter("SP_BRANCH_SERV_LOCATION", "");
        proc.setParameter("SP_COMP_BR_KALLIKRATIS", Constants.StringIsNullOrEmpty(branchPtl.getBrAddrK()) ? -1 : Integer.parseInt(branchPtl.getBrAddrK()));
        proc.setParameter("SP_ATTACHED_DOC_ID", ec.getAttachedDocIdPause());
        proc.setParameter("SP_ANNOUNCEMENT_DOCID", ec.getId());
        proc.setParameter("SP_DESCR", ec.getPauseExplanation());
        proc.setParameter("SP_NOTES", "");
        proc.setParameter("SP_DELIVERY_TYPE_CODE", 2);
        proc.setParameter("SP_PORTAL_UID", ec.getCompanyId());

        proc.execute();
        String out_errMsg = (String) proc.getOutputParameterValue("out_errMsg");
        String out_isProtNull = (String) proc.getOutputParameterValue("SP_PROTOCOL_NUMBER");
        if (out_isProtNull != null || !out_isProtNull.isEmpty()) {
            Long docId = null;
            Long department = null;
            if(null == proc.getOutputParameterValue("SP_DOC_ID")) {
                docId = (Long) proc.getOutputParameterValue("SP_DOC_ID");
            }
            else {
                docId = new Long((Integer) proc.getOutputParameterValue("SP_DOC_ID"));
            }
            String protocolNumber = (String) proc.getOutputParameterValue("SP_PROTOCOL_NUMBER");
            Date protocolDate = (Date) proc.getOutputParameterValue("SP_PROTOCOL_DATE");
            Integer year = (Integer) proc.getOutputParameterValue("SP_PROTOCOL_YEAR");
            String time = (String) proc.getOutputParameterValue("SP_TIME");
            if(null == proc.getOutputParameterValue("SP_DEPARTMENT")) {
                department = (Long) proc.getOutputParameterValue("SP_DEPARTMENT");
            }
            else {
                department = new Long((Integer) proc.getOutputParameterValue("SP_DEPARTMENT"));
            }

//            ec.setDocId(docId);
//            ec.setProtNo(protocolNumber);
//            ec.setProtDate(new Timestamp(protocolDate.getTime()));
//            ec.setProtYear(year);
//            ec.setSubmitTime(time);
//            ec.setSepeDept(department);
            ec.setDocIdPause(docId);
            ec.setProtNoPause(protocolNumber);
            ec.setProtDatePause(new Timestamp(protocolDate.getTime()));
        } else {
            throw new Exception(out_errMsg);
        }

        return ec;
    }

    // Αριθμός Πρωτοκόλλου Παραίτησης
    public SpPtlCompTaAnn procIntArchiveCompTaAnnResign(SpPtlCompTaAnn ec) throws Exception {
        StoredProcedureQuery proc = em.createStoredProcedureQuery("SN_PROC_INT_TA_DISMISSAL");
        proc.registerStoredProcedureParameter("SP_SHIP_OR_BRANCH", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ANNOUNCEMENT_TYPE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ANNOUNCEMENT_STATUS", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EXYPP", Long.class, ParameterMode.IN)

                .registerStoredProcedureParameter("SP_BRANCH0_ID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_BRANCH_ID", Long.class, ParameterMode.IN)

                .registerStoredProcedureParameter("SP_COMP_ADDRESS", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_ZIP_CODE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_AFM", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_SERV_LOCATION", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_KALLIKRATIS", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_LEGAL_REPRESENTATIVE", String.class, ParameterMode.IN)

                .registerStoredProcedureParameter("SP_COMP_BR_ADDRESS", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_BR_ZIP_CODE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_BRANCH_SERV_LOCATION", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_BR_KALLIKRATIS", Integer.class, ParameterMode.IN)

                .registerStoredProcedureParameter("SP_ATTACHED_DOC_ID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ANNOUNCEMENT_DOCID", Long.class, ParameterMode.IN)

                .registerStoredProcedureParameter("SP_DESCR", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_NOTES", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_DELIVERY_TYPE_CODE", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_PORTAL_UID", Long.class, ParameterMode.IN)

                .registerStoredProcedureParameter("SP_DOC_ID", Long.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_PROTOCOL_NUMBER", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_PROTOCOL_DATE", Date.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_PROTOCOL_YEAR", Integer.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_TIME", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_DEPARTMENT", Long.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("out_errMsg", String.class, ParameterMode.OUT);

        SpPtlCompPtlBranch branchPtl = spPtlCompPtlBranchRepo.findById(ec.getCompPtlBranchId());
        Long branch0Idnew;
        try {
            //TEmployerBranchIKA ika = ikaRepo.findCompanyMainBranchByAme(ec.getCompAme());
            TEmployerBranchIKA ika = ikaRepo.findCompanyMainBranchByAfmAndAme(ec.getCompTaxNumber(),ec.getCompAme());
            branch0Idnew = ika.getRgEbrEmpSepeId();
        }
        catch (Exception e){
            branch0Idnew = new Long(-1);
        }

        proc.setParameter("SP_SHIP_OR_BRANCH", 1);
        proc.setParameter("SP_ANNOUNCEMENT_TYPE", ec.getCooperationTypeBasic()==1 ? "ΑΝΕΞΑΡΤΗΤΟΙ ΤΕΧΝΙΚΟΙ" : "ΕΞΥΠΠ");
        proc.setParameter("SP_ANNOUNCEMENT_STATUS", ec.getTaAnnStatus());
        proc.setParameter("SP_EXYPP", ec.getExyppBasic()==null ? new Long(-1) : ec.getExyppBasic());
        proc.setParameter("SP_BRANCH0_ID",branch0Idnew);
        proc.setParameter("SP_BRANCH_ID", ec.getCompEbrBranchId());
        proc.setParameter("SP_COMP_ADDRESS", ec.getCompAddr());
        proc.setParameter("SP_COMP_ZIP_CODE", ec.getCompAddrTk());
        proc.setParameter("SP_COMP_AFM", ec.getCompTaxNumber());
        proc.setParameter("SP_COMP_SERV_LOCATION", branchPtl.getBrDescr());
        proc.setParameter("SP_COMP_KALLIKRATIS", Constants.StringIsNullOrEmpty(ec.getCompAddrK()) ? -1 : Integer.parseInt(ec.getCompAddrK()));
        proc.setParameter("SP_COMP_LEGAL_REPRESENTATIVE", "");
        proc.setParameter("SP_COMP_BR_ADDRESS", branchPtl.getBrAddr());
        proc.setParameter("SP_COMP_BR_ZIP_CODE", branchPtl.getBrAddrTk());
        proc.setParameter("SP_BRANCH_SERV_LOCATION", "");
        proc.setParameter("SP_COMP_BR_KALLIKRATIS", Constants.StringIsNullOrEmpty(branchPtl.getBrAddrK()) ? -1 : Integer.parseInt(branchPtl.getBrAddrK()));
        proc.setParameter("SP_ATTACHED_DOC_ID", ec.getAttachedDocIdPause());
        proc.setParameter("SP_ANNOUNCEMENT_DOCID", ec.getId());
        proc.setParameter("SP_DESCR", ec.getPauseExplanation());
        proc.setParameter("SP_NOTES", "");
        proc.setParameter("SP_DELIVERY_TYPE_CODE", 2);
        proc.setParameter("SP_PORTAL_UID", ec.getCompanyId());

        proc.execute();
        String out_errMsg = (String) proc.getOutputParameterValue("out_errMsg");
        String out_isProtNull = (String) proc.getOutputParameterValue("SP_PROTOCOL_NUMBER");
        if (out_isProtNull != null || !out_isProtNull.isEmpty()) {
            Long docId = null;
            Long department = null;
            if(null == proc.getOutputParameterValue("SP_DOC_ID")) {
                docId = (Long) proc.getOutputParameterValue("SP_DOC_ID");
            }
            else {
                docId = new Long((Integer) proc.getOutputParameterValue("SP_DOC_ID"));
            }
            String protocolNumber = (String) proc.getOutputParameterValue("SP_PROTOCOL_NUMBER");
            Date protocolDate = (Date) proc.getOutputParameterValue("SP_PROTOCOL_DATE");
            Integer year = (Integer) proc.getOutputParameterValue("SP_PROTOCOL_YEAR");
            String time = (String) proc.getOutputParameterValue("SP_TIME");
            if(null == proc.getOutputParameterValue("SP_DEPARTMENT")) {
                department = (Long) proc.getOutputParameterValue("SP_DEPARTMENT");
            }
            else {
                department = new Long((Integer) proc.getOutputParameterValue("SP_DEPARTMENT"));
            }

//            ec.setDocId(docId);
//            ec.setProtNo(protocolNumber);
//            ec.setProtDate(new Timestamp(protocolDate.getTime()));
//            ec.setProtYear(year);
//            ec.setSubmitTime(time);
//            ec.setSepeDept(department);
            //ec.setDocIdPause(docId);
            ec.setProtNoResign(protocolNumber);
            ec.setProtDateResign(new Timestamp(protocolDate.getTime()));
        } else {
            throw new Exception(out_errMsg);
        }

        return ec;
    }

    //Company Anaggelia Texnikou se ploia
    public SpPtlCompTaSann procIntArchiveCompTaAnn(SpPtlCompTaSann ec) throws Exception {
        /*StoredProcedureQuery proc = em.createStoredProcedureQuery("SN_proc_int_compTaSann");
        proc.registerStoredProcedureParameter("SP_PORTAL_UID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_DELIVERY_TYPE_CODE", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_NAME", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_AFM", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_AME", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_PHONE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_ADDR", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_ADDR_TK", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_KALLIKRATIS", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_BRANCH0_ID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_BRANCH_ID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_TA_FULLNAME", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_TA_AFM", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_TA_SPECIALITY", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_TA_SPECIALITY_OTHER", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_TA_ANN_STATUS", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COOPERATION_TYPE", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_DATE_START", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_DATE_END", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_REG_REQUEST_USER_ID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ATTACHED_DOC_ID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_SHIP", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_REPRESENTATIVE_ID", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_TA_RESPONSE_REPLACE", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_PROJ_START_DATE", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_PROJ_ADDR", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_PROJ_ADDR_KALLIKRATIS", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_PROJ_DURATION", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_PROJ_EMPL_NUM", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_SHIP_DANGER_ZONE", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_PORT_AUTHORITY", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_SHIPYARD", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_DOC_ID", Long.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_PROTOCOL_NUMBER", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_PROTOCOL_DATE", Date.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_PROTOCOL_YEAR", Integer.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_TIME", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_COM_DEPARTMENT", Long.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("out_errMsg", String.class, ParameterMode.OUT);

        proc.setParameter("SP_PORTAL_UID", ec.getCompanyId());
        proc.setParameter("SP_DELIVERY_TYPE_CODE", 2);
        proc.setParameter("SP_COMP_NAME", ec.getCompFullName());
        proc.setParameter("SP_COMP_AFM", ec.getCompTaxNumber());
        proc.setParameter("SP_COMP_AME", ec.getCompAme());
        proc.setParameter("SP_COMP_PHONE", ec.getCompPhone());
        proc.setParameter("SP_COMP_ADDR", ec.getCompAddr());
        proc.setParameter("SP_COMP_ADDR_TK", ec.getCompAddrTk());
        proc.setParameter("SP_COMP_KALLIKRATIS", Constants.StringIsNullOrEmpty(ec.getCompAddrK()) ? -1 : Integer.parseInt(ec.getCompAddrK()));
        proc.setParameter("SP_COMP_BRANCH0_ID", ec.getCompEbrBranch0Id());
        proc.setParameter("SP_COMP_BRANCH_ID", ec.getCompEbrBranchId());
        proc.setParameter("SP_TA_FULLNAME", ec.getTaFullname());
        proc.setParameter("SP_TA_AFM", ec.getTaAfm());
        proc.setParameter("SP_TA_SPECIALITY", ec.getTaSpeciality());
        proc.setParameter("SP_TA_SPECIALITY_OTHER", ec.getTaSpecialityOther());
        proc.setParameter("SP_TA_ANN_STATUS", ec.getReqStatus());
        proc.setParameter("SP_COOPERATION_TYPE", ec.getCooperationType());
        proc.setParameter("SP_DATE_START", new Date(ec.getDateStart().getTime()));
        proc.setParameter("SP_DATE_END", new Date(ec.getDateEnd().getTime()));
        proc.setParameter("SP_REG_REQUEST_USER_ID", ec.getTechnicianRegrequestUserId());
        proc.setParameter("SP_ATTACHED_DOC_ID", ec.getAttachedDocId());
        proc.setParameter("SP_COMP_SHIP", ec.getCompShipId());
        proc.setParameter("SP_COMP_REPRESENTATIVE_ID", ec.getCompRepresentativeId());
        proc.setParameter("SP_TA_RESPONSE_REPLACE", ec.getTaResponseReplace());
        proc.setParameter("SP_PROJ_START_DATE", new Date(ec.getProjStartDate().getTime()));
        proc.setParameter("SP_PROJ_ADDR", ec.getProjAddr());
        proc.setParameter("SP_PROJ_ADDR_KALLIKRATIS", Constants.StringIsNullOrEmpty(ec.getProjAddrK()) ? -1 : Integer.parseInt(ec.getProjAddrK()));
        proc.setParameter("SP_PROJ_DURATION", ec.getProjDuration());
        proc.setParameter("SP_PROJ_EMPL_NUM", ec.getProjEmplNum());
        proc.setParameter("SP_SHIP_DANGER_ZONE", ec.getShipDangerZone());
        proc.setParameter("SP_PORT_AUTHORITY", ec.getPortAuthority());
        proc.setParameter("SP_SHIPYARD", ec.getShipyard());*/

        StoredProcedureQuery proc = em.createStoredProcedureQuery("SN_proc_int_compTaAnn");
        proc.registerStoredProcedureParameter("SP_PORTAL_UID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_SHIP_OR_BRANCH", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_DELIVERY_TYPE_CODE", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_DESCR", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_NOTES", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ANNOUNCEMENT_TYPE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ANNOUNCEMENT_STATUS", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EXYPP", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_DATE_FROM", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_DATE_TO", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EMPLOYERS_A", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EMPLOYERS_B", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EMPLOYERS_C", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_VISIT_MINUTES", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_BRANCH0_ID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_BRANCH_ID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_AFM", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_ADDRESS", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_ZIP_CODE", String.class, ParameterMode.IN)

                .registerStoredProcedureParameter("SP_COMP_SERV_LOCATION", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_LEGAL_REPRESENTATIVE", String.class, ParameterMode.IN)

                .registerStoredProcedureParameter("SP_COMP_KALLIKRATIS", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ANNOUNCEMENT_ID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ATTACHED_DOC_ID", Long.class, ParameterMode.IN)

                .registerStoredProcedureParameter("SP_COMP_BR_ADDRESS", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_BR_ZIP_CODE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_BR_KALLIKRATIS", Integer.class, ParameterMode.IN)

                .registerStoredProcedureParameter("SP_COMP_SHIP", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_SHIP_MIN_WRKING_HRS", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_SHIP_DATE_START", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_SHIP_START_TIME", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_SHIP_DURATION_DAYS", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_SHIP_MAX_WRKRS_NUM", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_SHIP_DANGER_CTGR", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_SHIP_PORT_AUTHORITY", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_SHIP_SHIPYARD", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_SHP_ADDRESS", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_SHP_ZIP_CODE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COMP_SHP_KALLIKRATIS", Integer.class, ParameterMode.IN)

                .registerStoredProcedureParameter("SP_DOC_ID", Long.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_PROTOCOL_NUMBER", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_PROTOCOL_DATE", Date.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_PROTOCOL_YEAR", Integer.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_TIME", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_DEPARTMENT", Long.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("out_errMsg", String.class, ParameterMode.OUT);

        //SpPtlCompPtlBranch branchPtl = spPtlCompPtlBranchRepo.findById(ec.getCompPtlBranchId());
        Long branch0Idnew;
        try {
            //TEmployerBranchIKA ika = ikaRepo.findCompanyMainBranchByAme(ec.getCompAme());
            TEmployerBranchIKA ika = ikaRepo.findCompanyMainBranchByAfmAndAme(ec.getCompTaxNumber(),ec.getCompAme());
            branch0Idnew = ika.getRgEbrEmpSepeId();
        }
        catch (Exception e){
            branch0Idnew = new Long(-1);
        }

        int minWrkngHours = 0;
        if (ec.getShipDangerZone() == 1) {
            minWrkngHours = 5;
        } else if (ec.getShipDangerZone() == 2) {
            minWrkngHours = 2;
        } else {
            minWrkngHours = 1;
        }

        proc.setParameter("SP_PORTAL_UID", ec.getCompanyId());
        proc.setParameter("SP_SHIP_OR_BRANCH", 2);
        proc.setParameter("SP_DELIVERY_TYPE_CODE", 2);
        proc.setParameter("SP_DESCR", "");
        proc.setParameter("SP_NOTES", "");
        proc.setParameter("SP_ANNOUNCEMENT_TYPE", "ΑΝΕΞΑΡΤΗΤΟΙ ΤΕΧΝΙΚΟΙ");
        proc.setParameter("SP_ANNOUNCEMENT_STATUS", ec.getTaSannStatus());
        proc.setParameter("SP_EXYPP", new Long(-1));
        proc.setParameter("SP_DATE_FROM", new Date(ec.getDateStart().getTime()));
        proc.setParameter("SP_DATE_TO", new Date(ec.getDateEnd().getTime()));
        proc.setParameter("SP_EMPLOYERS_A", null);
        proc.setParameter("SP_EMPLOYERS_B", null);
        proc.setParameter("SP_EMPLOYERS_C", null);
        proc.setParameter("SP_VISIT_MINUTES", null);
        proc.setParameter("SP_BRANCH0_ID",branch0Idnew);
        proc.setParameter("SP_BRANCH_ID", ec.getCompEbrBranchId());
        proc.setParameter("SP_COMP_AFM", ec.getCompTaxNumber());
        proc.setParameter("SP_COMP_ADDRESS", ec.getCompAddr());
        proc.setParameter("SP_COMP_ZIP_CODE", ec.getCompAddrTk());
        proc.setParameter("SP_COMP_SERV_LOCATION", null);
        proc.setParameter("SP_COMP_LEGAL_REPRESENTATIVE", "");
        proc.setParameter("SP_COMP_KALLIKRATIS", Constants.StringIsNullOrEmpty(ec.getCompAddrK()) ? -1 : Integer.parseInt(ec.getCompAddrK()));
        proc.setParameter("SP_ANNOUNCEMENT_ID", ec.getId());
        proc.setParameter("SP_ATTACHED_DOC_ID", ec.getAttachedDocId());


        proc.setParameter("SP_COMP_BR_ADDRESS",  ec.getCompAddr());
        proc.setParameter("SP_COMP_BR_ZIP_CODE", ec.getCompAddrTk());
        proc.setParameter("SP_COMP_BR_KALLIKRATIS", Constants.StringIsNullOrEmpty(ec.getCompAddrK()) ? -1 : Integer.parseInt(ec.getCompAddrK()));


        proc.setParameter("SP_COMP_SHIP", ec.getCompShipId());
        proc.setParameter("SP_SHIP_MIN_WRKING_HRS", minWrkngHours);
        proc.setParameter("SP_SHIP_DATE_START", new Date(ec.getProjStartDate().getTime()));
        proc.setParameter("SP_SHIP_START_TIME", null);
        proc.setParameter("SP_SHIP_DURATION_DAYS", Constants.StringIsNullOrEmpty(ec.getProjDuration()) ? -1 : Integer.parseInt(ec.getProjDuration()));
        proc.setParameter("SP_SHIP_MAX_WRKRS_NUM", Constants.StringIsNullOrEmpty(ec.getProjEmplNum()) ? -1 : Integer.parseInt(ec.getProjEmplNum()));
        proc.setParameter("SP_SHIP_DANGER_CTGR", ec.getShipDangerZone());
        proc.setParameter("SP_SHIP_PORT_AUTHORITY", ec.getPortAuthority());
        proc.setParameter("SP_SHIP_SHIPYARD", ec.getShipyard());
        proc.setParameter("SP_COMP_SHP_ADDRESS", ec.getProjAddr());
        proc.setParameter("SP_COMP_SHP_ZIP_CODE", ec.getProjAddrTk());
        proc.setParameter("SP_COMP_SHP_KALLIKRATIS", Constants.StringIsNullOrEmpty(ec.getProjAddrK()) ? -1 : Integer.parseInt(ec.getProjAddrK()));

        proc.execute();
        String out_errMsg = (String) proc.getOutputParameterValue("out_errMsg");
        if (out_errMsg == null || out_errMsg.isEmpty()) {
            Long department = null;
            Integer docId = (Integer) proc.getOutputParameterValue("SP_DOC_ID");
            String protocolNumber = (String) proc.getOutputParameterValue("SP_PROTOCOL_NUMBER");
            Date protocolDate = (Date) proc.getOutputParameterValue("SP_PROTOCOL_DATE");
            Integer year = (Integer) proc.getOutputParameterValue("SP_PROTOCOL_YEAR");
            String time = (String) proc.getOutputParameterValue("SP_TIME");
            if(null == proc.getOutputParameterValue("SP_DEPARTMENT")) {
                department = (Long) proc.getOutputParameterValue("SP_DEPARTMENT");
            }
            else {
                department = new Long((Integer) proc.getOutputParameterValue("SP_DEPARTMENT"));
            }

            ec.setDocId(docId);
            ec.setProtNo(protocolNumber);
            ec.setProtDate(new Timestamp(protocolDate.getTime()));
            ec.setProtYear(year);
            ec.setSubmitTime(time);
            ec.setSepeDept(department);
        } else {
            throw new Exception(out_errMsg);
        }

        return ec;
    }
}
