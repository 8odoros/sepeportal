package sepe.repository.technician;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import sepe.domain.doctor.SpPtlVCompIeAnn;
import sepe.domain.technician.SpPtlVCompTaAnn;


import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by Nikos on 6/18/2015.
 */
// http://localhost:7001/vCompTaAnn/search/taFindAll2
// http://localhost:7001/vCompTaAnn/search/taFindAll?taAnnStatus=1&reqStatus=5
// http://localhost:7001/portal/vCompTaAnn/search/findAllNotExpired?ieAnnStatus=2&reqStatus=7
@RepositoryRestResource(collectionResourceRel = "vCompTaAnn", path = "/vCompTaAnn")
public interface SpPtlVCompTaAnnRepo extends PagingAndSortingRepository<SpPtlVCompTaAnn, Long> {

    /*
    * TA asks all books
    */
    @Query(
            value = "SELECT o FROM SpPtlVCompTaAnn o"
                    + " WHERE (o.technicianRegrequestUserId = ?#{principal.userId} OR  o.companyId = ?#{principal.userId})" /* TODO return only active */
                    + " AND o.taAnnStatus =:#{#taAnnStatus} AND o.reqStatus =:#{#reqStatus}"
            ,nativeQuery = false
    )
    @Nullable
    public Page<SpPtlVCompTaAnn> taFindAll(Pageable pageable, @Param("taAnnStatus") Integer taAnnStatus, @Param("reqStatus") Integer reqStatus);

    @Query(
            value = "SELECT o FROM SpPtlVCompTaAnn o"
                    + " WHERE (o.technicianRegrequestUserId = ?#{principal.userId} OR  o.companyId = ?#{principal.userId})" /* TODO return only active */
                    + " AND ((o.taAnnStatus =:#{#taAnnStatus} AND o.reqStatus =:#{#reqStatus}) OR (o.taAnnStatus = 3 AND o.reqStatus = 6)) "
            ,nativeQuery = false
    )
    @Nullable
    public Page<SpPtlVCompTaAnn> taFindAllPaused(Pageable pageable, @Param("taAnnStatus") Integer taAnnStatus, @Param("reqStatus") Integer reqStatus);

    @Query(
            value = "SELECT * FROM SP_PTL_V_COMP_TA_ANN"
                    + " WHERE (TECHNICIAN_REGREQUEST_USER_ID  = ?#{principal.userId} OR  COMPANY_ID= ?#{principal.userId})" /* TODO return only active */
                    + " AND TA_ANN_STATUS =:#{#taAnnStatus} AND REQ_STATUS =:#{#reqStatus}"
                    + " AND TO_DATE(DATE_END ,'DD/MM/YYYY') >= TO_DATE(SYSDATE ,'DD/MM/YYYY')"
            ,nativeQuery = true
    )
    @Nullable
    public List<SpPtlVCompTaAnn> findAllNotExpired( @Param("taAnnStatus") Integer taAnnStatus, @Param("reqStatus") Integer reqStatus);

    /*@Query(
            value = "SELECT DISTINCT COMP_TA_ANN_ID ,TA_ANN_STATUS, COMPANY_ID, PTL_BRANCH_ID, BR_ADDR, BR_ADDR_D,BR_ADDR_Pe,BR_ADDR_P, BR_ADDR_K,  BR_ADDR_TK, BR_DESCR FROM SP_PTL_V_COMP_TA_ANN"
                    + " WHERE COMPANY_ID = ?#{principal.userId} AND REQ_STATUS=6"
            ,nativeQuery = true
    )
    @Nullable
    public List<SpPtlVCompTaAnn> taFindAll2();*/
/*
    @Query(
            value = "SELECT * FROM V_DOCTOR_REGREQUEST_SCH"
                    + " WHERE ID =:id"
                    + " AND DOCTOR_REGREQUEST_USER_ID = ?#{principal.userId}"
            ,nativeQuery = true
    )
    @Nullable
    public SpPtlVCompTaAnnBook taFindOne(@Param("id") Long id);
*/
    /**
     * ****************************************************************************
     * ******************************************************************************
     */

    @Override
    @RestResource(exported = false)
    public SpPtlVCompTaAnn findOne(Long id);

    //@Override
    //@RestResource(exported = false)
    //public Page<SpPtlEmployeeGenrequest> findAll(Pageable pageable);
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
    public SpPtlVCompTaAnn save(SpPtlVCompTaAnn entity);
}