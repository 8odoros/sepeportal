package sepe.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import sepe.domain.general.TProtocol;

import javax.annotation.Nullable;
import java.util.List;

//http://localhost:7001/TProtocol/search/findByDocIdCaseId?caseId=36004&docId=204
@RepositoryRestResource(collectionResourceRel = "TProtocol", path = "/TProtocol")
public interface ProtocolRepo extends CrudRepository<TProtocol, Long> {

    @Query(
            /*
                value = "SELECT SP_PROT_DOC_ID, SP_PROT_CASE_ID, SP_PROT_STATUS_CODE, SP_PTOT_STATUS_DESC, V_CMT_CASES.CD_TEXT"
                + " FROM INT_VW_PROTOCOL@SP_INT_LINK INNER JOIN V_CMT_CASES@SP_INT_LINK"
                + " ON INT_VW_PROTOCOL.SP_PROT_CASE_ID = V_CMT_CASES.CD_CODE"
                + " WHERE INT_VW_PROTOCOL.SP_PROT_CASE_ID =:#{#caseId} AND INT_VW_PROTOCOL.SP_PROT_DOC_ID =:#{#docId}",
                nativeQuery = true
            */
            value = "SELECT o FROM TProtocol o"
                    + " WHERE o.spProtCaseId =:#{#caseId} AND o.spProtDocId =:#{#docId}",
            nativeQuery = false
    )
    @Nullable
    public TProtocol findByDocIdCaseId(@Param("caseId") Long case_id, @Param("docId") Long doc_id);

    /*******************************************************************************
     ********************************************************************************/

    @Override
    @RestResource(exported = false)
    public TProtocol findOne(Long id);

    @Override
    @RestResource(exported = false)
    public List<TProtocol> findAll();

    @Override
    @RestResource(exported = false)
    public long count();

    @Override
    @RestResource(exported = false)
    public void delete(Long id);

//@Override
//@RestResource(exported = false)
//public void delete(List<OBJ_TYPE> entities);

//@Override
//@RestResource(exported = false)
//public void delete(OBJ_TYPE entity);

    @Override
    @RestResource(exported = false)
    public void deleteAll();

    @Override
    @RestResource(exported = false)
    public boolean exists(Long id);

//@Override
//@RestResource(exported = false)
//public List<OBJ_TYPE> findAll(List<ID_TYPE> ids);

//@Override
//@RestResource(exported = false)
//public List<OBJ_TYPE> save(List<OBJ_TYPE> entities);

    @Override
    @RestResource(exported = false)
    public TProtocol save(TProtocol entity);

}
