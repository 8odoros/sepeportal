package sepe.service;

import org.eclipse.persistence.jpa.JpaEntityManager;
import org.eclipse.persistence.queries.StoredFunctionCall;
import org.eclipse.persistence.queries.ValueReadQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sepe.domain.company.TCompany;
import sepe.domain.company.TEmployerBranchIKAComp;
import sepe.dto.CompanyDTO;
import sepe.dto.converter.CompanyToCompanyDTO;
import sepe.dto.converter.EmployeeToEmployeeDTO;
import sepe.dto.converter.UserToUserDTO;
import sepe.repository.UserRepository;
import sepe.repository.company.CompanyRepository;
import sepe.repository.company.SpPtlCompanyUserPrivilagesRepository;
import sepe.repository.company.TEmployerBranchIKACompRepo;
import sepe.repository.employee.EmployeeRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Map;

@Service
public final class CompanyService {

    @Autowired
    EntityManager em;


    @Autowired
    UserRepository userRepository;

    @Autowired
    private UserToUserDTO userDTOConverter;


    @Autowired
    SpPtlCompanyUserPrivilagesRepository companyUserPrivilagesRepository;

    //@Autowired
    //TEmployerBranchIKARepo tEmployerBranchIKARepo;

    @Autowired
    TEmployerBranchIKACompRepo tEmployerBranchIKACompRepo;


    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    private EmployeeToEmployeeDTO employeeDTOConverter;

    @Autowired
    private CompanyToCompanyDTO companyDTOConverter;

    public String getCompanyBRanchIds(TCompany company) throws Exception {
        CompanyDTO company2 = companyDTOConverter.convert(company);

        return getCompanyBRanchIds(company2);
    }

    public String getCompanyBRanchIds(CompanyDTO company2) throws Exception {
        TEmployerBranchIKAComp branchIKAComp = tEmployerBranchIKACompRepo.findCompBranchesByCompanyAme(company2.getAme());

        String branchIds = ",,";
        if (branchIKAComp !=null)
            branchIds = "," + branchIKAComp.getBranches() + ",";
        else
            throw new Exception("You can not loggin");
        return branchIds;
    }

    public Integer funcIntCalculateTaHours(Map<String, Object> arguments) throws Exception {

        String functionName = "SN_FUNC_INT_CALCULATE_TA_HOURS";

        StoredFunctionCall func = new StoredFunctionCall();
        func.setProcedureName(functionName);
        func.setResult("RESULT",Integer.class);

        for(String key : arguments.keySet()){
            func.addNamedArgumentValue(key, arguments.get(key));
        }

        ValueReadQuery valQuery = new ValueReadQuery();
        valQuery.setCall(func);

        Query query = ((JpaEntityManager)em.getDelegate()).createQuery(valQuery);

        Integer call_result = (Integer)query.getSingleResult();

        return call_result;
    }

    public Integer funcIntCalculateMinTaHours(Map<String, Object> arguments) throws Exception {

        String functionName = "SN_FUNC_INT_CALC_MIN_TA_HOURS";

        StoredFunctionCall func = new StoredFunctionCall();
        func.setProcedureName(functionName);
        func.setResult("RESULT",Integer.class);

        for(String key : arguments.keySet()){
            func.addNamedArgumentValue(key, arguments.get(key));
        }

        ValueReadQuery valQuery = new ValueReadQuery();
        valQuery.setCall(func);

        Query query = ((JpaEntityManager)em.getDelegate()).createQuery(valQuery);

        Integer call_result = (Integer)query.getSingleResult();

        return call_result;
    }

    public Integer funcIntCalculateIeHours(Map<String, Object> arguments) throws Exception {

        String functionName = "SN_FUNC_INT_CALCULATE_IE_HOURS";

        StoredFunctionCall func = new StoredFunctionCall();
        func.setProcedureName(functionName);
        func.setResult("RESULT",Integer.class);

        for(String key : arguments.keySet()){
            func.addNamedArgumentValue(key, arguments.get(key));
        }

        ValueReadQuery valQuery = new ValueReadQuery();
        valQuery.setCall(func);

        Query query = ((JpaEntityManager)em.getDelegate()).createQuery(valQuery);

        Integer call_result = (Integer)query.getSingleResult();

        return call_result;
    }

    public Integer funcIntValidateTaShipAnncmnt(Map<String, Object> arguments) throws Exception {

        String functionName = "SN_VALIDATE_TA_SHIP_ANNCMNT";

        StoredFunctionCall func = new StoredFunctionCall();
        func.setProcedureName(functionName);
        func.setResult("RESULT",Integer.class);

        for(String key : arguments.keySet()){
            func.addNamedArgumentValue(key, arguments.get(key));
        }

        ValueReadQuery valQuery = new ValueReadQuery();
        valQuery.setCall(func);

        Query query = ((JpaEntityManager)em.getDelegate()).createQuery(valQuery);

        Integer call_result = (Integer)query.getSingleResult();

        return call_result;
    }
}
