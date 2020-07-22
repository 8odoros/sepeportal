package sepe.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sepe.domain.doctor.SpPtlDoctorRegrequest;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.sql.Date;
import java.sql.Timestamp;

@Service
public class WorkplaceDoctorStoredProcedures {

    @Autowired
    EntityManager em;

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkplaceDoctorStoredProcedures.class);

    public WorkplaceDoctorStoredProcedures() {
    }

    //Workplace Doctor RegRequest
    public SpPtlDoctorRegrequest procIntArchiveIERegrequest(SpPtlDoctorRegrequest ec) throws Exception {
        StoredProcedureQuery proc = em.createStoredProcedureQuery("SN_proc_int_ie_regrequest");
        proc.registerStoredProcedureParameter("SP_PORTAL_UID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_FIRSTNAME", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_LASTNAME", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_FATHERNAME", String.class, ParameterMode.IN)
                //.registerStoredProcedureParameter("SP_EDUC_LEVEL", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_AFM", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_AMKA", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_CARD_NUMBER", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_CARD_TYPE", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_PHONE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_MOBILE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_FAX", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EMAIL", String.class, ParameterMode.IN)
                //.registerStoredProcedureParameter("SP_TEE_NUMBER", String.class, ParameterMode.IN)
                //.registerStoredProcedureParameter("SP_SPECIALITY", Long.class, ParameterMode.IN)
                //.registerStoredProcedureParameter("SP_SPECIALITY_OTHER", String.class, ParameterMode.IN)
                //.registerStoredProcedureParameter("SP_COOPERATION_TYPE", Long.class, ParameterMode.IN)
                //.registerStoredProcedureParameter("SP_EDUCATION_100", Integer.class, ParameterMode.IN)
                //.registerStoredProcedureParameter("SP_BELONGS_TO_PEDY", Integer.class, ParameterMode.IN)
                //.registerStoredProcedureParameter("SP_ATTACHED_DOC_ID_PEDY_YES", Long.class, ParameterMode.IN)
                //.registerStoredProcedureParameter("SP_ATTACHED_DOC_ID_PEDY_NO", Long.class, ParameterMode.IN)
                //.registerStoredProcedureParameter("SP_MEDICAL_ASSOC_NUMBER", String.class, ParameterMode.IN)
                //.registerStoredProcedureParameter("SP_ATTACHED_DOC_ID_EMPL_TRAIN", Long.class, ParameterMode.IN)
                //.registerStoredProcedureParameter("SP_ATTACHED_DOC_ID_DIPLOMA", Long.class, ParameterMode.IN)
                //.registerStoredProcedureParameter("SP_ATTACHED_DOC_ID_MEDASSOC", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ATTACHED_DOC_ID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_DESCR", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_NOTES", String.class, ParameterMode.IN)
                //.registerStoredProcedureParameter("SP_IS_MILITARY_DOCTOR", Integer.class, ParameterMode.IN)
                //.registerStoredProcedureParameter("SP_ATTACHED_DOC_ID_MILITARY", Long.class, ParameterMode.IN)
                //.registerStoredProcedureParameter("SP_IE_DOC_PROT_NO", String.class, ParameterMode.IN)
                //.registerStoredProcedureParameter("SP_IE_DOC_DATE", Date.class, ParameterMode.IN)
                //.registerStoredProcedureParameter("SP_IE_DOC_COMPANY_NAME", String.class, ParameterMode.IN)
                //.registerStoredProcedureParameter("SP_IE_DOC_DEPARTMENT", String.class, ParameterMode.IN)
                //.registerStoredProcedureParameter("SP_DIFFERENT_COUNTY", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_DELIVERY_TYPE_CODE", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_DOC_ID", Long.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_PROTOCOL_NUMBER", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_PROTOCOL_DATE", Date.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_PROTOCOL_YEAR", Integer.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_TIME", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_COM_DEPARTMENT", Long.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("out_errMsg", String.class, ParameterMode.OUT);

        proc.setParameter("SP_PORTAL_UID", ec.getUserId());
        proc.setParameter("SP_FIRSTNAME", ec.getLastname());
        proc.setParameter("SP_LASTNAME", ec.getFirstname());
        proc.setParameter("SP_FATHERNAME", ec.getFathername());
        //proc.setParameter("SP_EDUC_LEVEL", ec.getEducationLevel());
        proc.setParameter("SP_AFM", ec.getAfm());
        proc.setParameter("SP_AMKA", ec.getAmka());
        proc.setParameter("SP_CARD_NUMBER", ec.getCardNumber());
        proc.setParameter("SP_CARD_TYPE", ec.getCardType());
        proc.setParameter("SP_PHONE", ec.getPhone());
        proc.setParameter("SP_MOBILE", ec.getMobile());
        proc.setParameter("SP_FAX", ec.getFax());
        proc.setParameter("SP_EMAIL", ec.getEmail());
        /*proc.setParameter("SP_TEE_NUMBER", ec.getTeeNumber());
        proc.setParameter("SP_SPECIALITY", ec.getSpeciality());
        proc.setParameter("SP_SPECIALITY_OTHER", ec.getSpecialityOther());
        proc.setParameter("SP_COOPERATION_TYPE", ec.getCooperationType());
        proc.setParameter("SP_EDUCATION_100", ec.getEducation100());
        proc.setParameter("SP_BELONGS_TO_PEDY", ec.getBelongsToPedy());
        proc.setParameter("SP_ATTACHED_DOC_ID_PEDY_YES", ec.getAttachedDocIdPedyYes());
        proc.setParameter("SP_ATTACHED_DOC_ID_PEDY_NO", ec.getAttachedDocIdPedyNo());
        proc.setParameter("SP_MEDICAL_ASSOC_NUMBER", ec.getMedicalAssocNumber());
        proc.setParameter("SP_ATTACHED_DOC_ID_EMPL_TRAIN", ec.getAttachedDocIdEmplTraining());
        proc.setParameter("SP_ATTACHED_DOC_ID_DIPLOMA", ec.getAttachedDocIdDiploma());
        proc.setParameter("SP_ATTACHED_DOC_ID_MEDASSOC", ec.getAttachedDocIdMedassoc());*/
        proc.setParameter("SP_ATTACHED_DOC_ID", ec.getAttachedDocId());
        proc.setParameter("SP_DESCR", ec.getDescr());
        proc.setParameter("SP_NOTES", ec.getNotes());
        /*proc.setParameter("SP_IS_MILITARY_DOCTOR", ec.getIsMilitaryDoctor());
        proc.setParameter("SP_ATTACHED_DOC_ID_MILITARY", ec.getAttachedDocIdMilitary());
        proc.setParameter("SP_IE_DOC_PROT_NO", ec.getIeDocProtNo());
        proc.setParameter("SP_IE_DOC_DATE", new Date(ec.getIeDocDate().getTime()));
        proc.setParameter("SP_IE_DOC_COMPANY_NAME", ec.getIeDocCompanyName());
        proc.setParameter("SP_IE_DOC_DEPARTMENT", ec.getIeDocDepartment());
        proc.setParameter("SP_DIFFERENT_COUNTY", ec.getSpDifferentCounty());*/
        proc.setParameter("SP_DELIVERY_TYPE_CODE", 2);

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

            String protoclNumber = (String) proc.getOutputParameterValue("SP_PROTOCOL_NUMBER");
            Date protoclDate = (Date) proc.getOutputParameterValue("SP_PROTOCOL_DATE");
            Integer year = (Integer) proc.getOutputParameterValue("SP_PROTOCOL_YEAR");
            String time = (String) proc.getOutputParameterValue("SP_TIME");
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
            ec.setDepartment(department);
        } else {
            throw new Exception(out_errMsg);
        }

        return ec;
    }
}
