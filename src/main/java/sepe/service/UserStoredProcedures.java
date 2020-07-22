package sepe.service;


import org.eclipse.persistence.jpa.JpaEntityManager;
import org.eclipse.persistence.queries.StoredFunctionCall;
import org.eclipse.persistence.queries.ValueReadQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sepe.config.Constants;
import sepe.domain.citizen.SpPtlAnonymousComplaint;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserStoredProcedures {

    @Autowired
    EntityManager em;

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeStoredProcedures.class);

    public UserStoredProcedures() {
    }

    //Anonymous Complaint
    public SpPtlAnonymousComplaint procIntArchiveComplaintAnonymous(SpPtlAnonymousComplaint ec) throws Exception {
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
                .registerStoredProcedureParameter("SP_COM_AUDIT_ADDRESS_DESC", String.class, ParameterMode.IN)
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
                .registerStoredProcedureParameter("SP_EBR_BRANCH0_ID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_EBR_BRANCH_ID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("SP_COM_DOC_ID", Long.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_COM_PROTOCOL_NUMBER", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_COM_PROTOCOL_DATE", Date.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_COM_PROTOCOL_YEAR", Integer.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_COM_TIME", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("SP_COM_DEPARTMENT", Integer.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("out_errMsg", String.class, ParameterMode.OUT);

        proc.setParameter("SP_COM_TYPE_DESC", "2"); //anonymous
        proc.setParameter("SP_COM_MEANS_DESC", "2");
        proc.setParameter("SP_COM_TE", ec.getComplInvlovesSafetyInsp() == null ? 0 : ec.getComplInvlovesSafetyInsp());
        proc.setParameter("SP_COM_KE", ec.getComplInvolvesLabRelations() == null ? 0 : ec.getComplInvolvesLabRelations());
        proc.setParameter("SP_COM_DESCRIPTION", ec.getComplDescr());
        proc.setParameter("SP_COM_TIME_REASON", ec.getComplInspTime());
        proc.setParameter("SP_COM_MATTERS", ec.getComplMatter().replace("\"", "").replace("[", "").replace("]", ""));
        proc.setParameter("SP_COM_ATTRIBUTE_CODE", Constants.USER_TYPE.CITIZEN.getCode());
        proc.setParameter("SP_COM_PORTAL_UID", 0); //no userID
        proc.setParameter("SP_COM_NAME", ec.getEmpFirstname());
        proc.setParameter("SP_COM_SURNAME", ec.getEmpLastname());
        proc.setParameter("SP_COM_PHONE", ec.getEmpPhone());
        proc.setParameter("SP_COM_AUDIT_ADDRESS", ec.getInspAddr());
        proc.setParameter("SP_COM_AUDIT_ADDRESS_DESC", ec.getComplAddressDesc());
        proc.setParameter("SP_COM_AUDIT_ZIP_CODE", ec.getInspAddrTk());
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
            Integer department = (Integer) proc.getOutputParameterValue("SP_COM_DEPARTMENT");
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

    //CallGGDEWS
    public Map<String, Object> procRegProcCallGGDEWS(Map<String, Object> arguments) throws Exception {
        StoredProcedureQuery proc = em.createStoredProcedureQuery("SN_PROC_CALLGGDEWS");
        proc.registerStoredProcedureParameter("afm", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("auditEndUserApplUser", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("auditEndUserOsUser", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("auditUnitCode", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("auditUnitDescr", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("auditEndUserHostIp", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("auditEndUserHostName", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("logApplicationUserId", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("logAccessDate", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter("logEmployeeId", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("logIPs", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("logUsernames", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("out_CallId", Integer.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("out_result", Integer.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("out_errMsg", String.class, ParameterMode.OUT);

        for(String key : arguments.keySet()){
                proc.setParameter(key, arguments.get(key));
        }


        proc.execute();

        Map<String, Object> result = new LinkedHashMap<String, Object>();
        Integer out_result = (Integer) proc.getOutputParameterValue("out_result");
        if (out_result == null) { //TO-DO it 0 when storedproc is ready
            result.put("out_CallId",(Integer) proc.getOutputParameterValue("out_CallId"));
            result.put("out_result",(Integer) proc.getOutputParameterValue("out_result"));

        } else {//handle error
            String out_errMsg = (String) proc.getOutputParameterValue("out_errMsg");
            //parse string gia mia logiki exception
            throw new Exception(out_errMsg);
        }

        return result;
    }

    //Call LDAP Functions
    public int secOimCallFunction(Map<String, Object> arguments, String functionName) throws Exception {
        //String functionName = "PCG_SEC_OIM.SEC_OIM_CREATE_USER";

        StoredFunctionCall func = new StoredFunctionCall();
        func.setProcedureName(functionName);
        func.setResult("RESULT",Integer.class);

        for(String key : arguments.keySet()){
            //if (key.equals("p_username"))
            //    func.addNamedInOutputArgument(key, arguments.get(key).toString());
            //else
                func.addNamedArgumentValue(key, arguments.get(key));
        }
        func.addNamedOutputArgument("p_message", "");

        ValueReadQuery valQuery = new ValueReadQuery();
        valQuery.setCall(func);

        Query query = ((JpaEntityManager)em.getDelegate()).createQuery(valQuery);

        Integer call_result = (Integer)query.getSingleResult();
        //System.out.println(query.getParameterValue("p_message"));

        return call_result;
    }
}
