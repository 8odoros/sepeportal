package sepe.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sepe.config.Constants;
import sepe.domain.technician.SpPtlTechnicianRegrequest;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.sql.Date;
import java.sql.Timestamp;

@Service
public class SafetyTechnicianStoredProcedures {

    @Autowired
    EntityManager em;

    private static final Logger LOGGER = LoggerFactory.getLogger(SafetyTechnicianStoredProcedures.class);

    public SafetyTechnicianStoredProcedures() {
    }

    //Safety Technician RegRequest
    public SpPtlTechnicianRegrequest procIntArchiveTARegrequest(SpPtlTechnicianRegrequest ec) throws Exception {
        StoredProcedureQuery proc = em.createStoredProcedureQuery("SN_proc_int_ta_regrequest");
        proc.registerStoredProcedureParameter("SP_FIRSTNAME", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_LASTNAME", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_FATHERNAME", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EDUC_LEVEL", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_AFM", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_AMKA", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_CARD_NUMBER", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_CARD_TYPE", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_PHONE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_MOBILE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_FAX", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EMAIL", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ADDRESS", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ZIP_CODE", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_KALLIKRATIS", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_TEE_NUMBER", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_DEPARTMENT_TE", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_SHIPYARD_DUTIES", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_SPECIALITY", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_SPECIALITY_OTHER", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COOPERATION_TYPE", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_DIPLOMAS", String.class, ParameterMode.IN)
//                .registerStoredProcedureParameter("SP_DIPLOMA_YEAR", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EDUCATION_100", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EDUCATION_35_10", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EDUCATION_10", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ATTACHED_DOC_ID_EMPL_TRNG", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ATTACHED_DOC_ID_DIPLOMA", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ATTACHED_DOC_ID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_DESCR", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_NOTES", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_FOREAS_100", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ARPRWT_100", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_FOREAS_35", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ARPRWT_35", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_FOREAS_10", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_ARPRWT_10", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_DELIVERY_TYPE_CODE", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_PORTAL_UID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_DOC_ID", Long.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_PROTOCOL_NUMBER", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_PROTOCOL_DATE", Date.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_PROTOCOL_YEAR", Integer.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_TIME", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_DEPARTMENT", Long.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("out_errMsg", String.class, ParameterMode.OUT);

        proc.setParameter("SP_FIRSTNAME", ec.getLastname());
        proc.setParameter("SP_LASTNAME", ec.getFirstname());
        proc.setParameter("SP_FATHERNAME", ec.getFathername());
        proc.setParameter("SP_EDUC_LEVEL", ec.getEducationLevel());
        proc.setParameter("SP_AFM", ec.getAfm());
        proc.setParameter("SP_AMKA", ec.getAmka());
        proc.setParameter("SP_CARD_NUMBER", ec.getCardNumber());
        proc.setParameter("SP_CARD_TYPE", ec.getCardType());
        proc.setParameter("SP_PHONE", ec.getPhone());
        proc.setParameter("SP_MOBILE", ec.getMobile());
        proc.setParameter("SP_FAX", ec.getFax());
        proc.setParameter("SP_EMAIL", ec.getEmail());
        proc.setParameter("SP_ADDRESS", ec.getReqAddr());
        proc.setParameter("SP_ZIP_CODE", ec.getReqAddrTk());
        proc.setParameter("SP_KALLIKRATIS", Constants.StringIsNullOrEmpty(ec.getReqAddrK()) ? -1 : Integer.parseInt(ec.getReqAddrK()));
        proc.setParameter("SP_TEE_NUMBER", ec.getTeeNumber());
        proc.setParameter("SP_DEPARTMENT_TE", new Long(-1));
        proc.setParameter("SP_SHIPYARD_DUTIES", ec.getShipyardDuties());
        proc.setParameter("SP_SPECIALITY", ec.getSpeciality().replace("\"", "").replace("[", "").replace("]", ""));  // proc.setParameter("SP_SPECIALITY", ec.getSpeciality());
        proc.setParameter("SP_SPECIALITY_OTHER", ec.getSpecialityOther());
        proc.setParameter("SP_COOPERATION_TYPE", ec.getCooperationType());
        proc.setParameter("SP_DIPLOMAS", ec.getDiplomaIdComma());
//        proc.setParameter("SP_DIPLOMA_YEAR", ""); //TODO
        proc.setParameter("SP_EDUCATION_100", ec.getEducation100());
        proc.setParameter("SP_EDUCATION_35_10", ec.getEducation3510());
        proc.setParameter("SP_EDUCATION_10", ec.getEducation10());
        proc.setParameter("SP_ATTACHED_DOC_ID_EMPL_TRNG", ec.getAttachedDocIdEmplTraining() == -1 ? null : ec.getAttachedDocIdEmplTraining());
        proc.setParameter("SP_ATTACHED_DOC_ID_DIPLOMA", null); //TODO
        proc.setParameter("SP_ATTACHED_DOC_ID", ec.getAttachedDocId() == -1 ? null : ec.getAttachedDocId());
        proc.setParameter("SP_DESCR", ec.getDescr());
        proc.setParameter("SP_NOTES", ec.getNotes());
        proc.setParameter("SP_FOREAS_100", ec.getForeas100());
        proc.setParameter("SP_ARPRWT_100", ec.getSeminarId100Text());
        proc.setParameter("SP_FOREAS_35", ec.getForeas3510());
        proc.setParameter("SP_ARPRWT_35", ec.getSeminarId3510Text());
        proc.setParameter("SP_FOREAS_10", ec.getForeas10());
        proc.setParameter("SP_ARPRWT_10", ec.getSeminarId10Text());
        proc.setParameter("SP_DELIVERY_TYPE_CODE", 2);
        proc.setParameter("SP_PORTAL_UID", ec.getUserId());

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
            if(null == proc.getOutputParameterValue("SP_DEPARTMENT")) {
                department = (Long) proc.getOutputParameterValue("SP_DEPARTMENT");
            }
            else {
                department = new Long((Integer) proc.getOutputParameterValue("SP_DEPARTMENT"));
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
