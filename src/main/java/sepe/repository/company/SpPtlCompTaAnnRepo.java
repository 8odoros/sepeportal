package sepe.repository.company;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sepe.domain.company.SpPtlCompTaAnn;


import javax.annotation.Nullable;

/**
 * Created by Nikos on 6/16/2015.
 */
// http://localhost:7001/compTaAnn
// http://localhost:7001/compTaAnn/search/findByPtlBranchId?branchId=1261
// http://localhost:7001/compTaAnn/search/countTaAnn?ptlBranchId=1348&startDate=25%2f5%2f2015
// http://localhost:7001/compTaAnn/search/findCategTaAnn?ptlBranchId=1348
@Transactional(propagation = Propagation.REQUIRES_NEW)
@RepositoryRestResource(collectionResourceRel = "compTaAnn", path = "/compTaAnn")
public interface SpPtlCompTaAnnRepo extends PagingAndSortingRepository<SpPtlCompTaAnn, Long> {
    @Query(
            value = "SELECT o FROM SpPtlCompTaAnn o "
                    + "WHERE  o.companyId =?#{principal.company.id}"
    )
    @Nullable
    public Page<SpPtlCompTaAnn> findAll(Pageable pageable);

    @Query(
            value = "SELECT o FROM SpPtlCompTaAnn o "
                    + "WHERE o.id =:#{#id}" /* TODO: privilages [kalled by ie controler */
    )

    @Nullable
    public SpPtlCompTaAnn findOne(@Param("id") Long id);

    /*
    * Company checks if date range of new ieAnn conflicts with older ones.
    * 0 means no conflict found.
    */
    @Query(
            value = "SELECT count(*)"
                    + " FROM SP_PTL_COMP_TA_ANN"
                    + " WHERE ((TA_ANN_STATUS=0 AND REQ_STATUS=5) OR (TA_ANN_STATUS=1 AND REQ_STATUS=6))"
                    + " AND COMP_PTL_BRANCH_ID= :#{#ptlBranchId}"
                    + " AND TO_DATE(DATE_END ,'DD/MM/YYYY')> TO_DATE(SYSDATE ,'DD/MM/YYYY')"
                    + " AND DATE_END > TO_DATE( :#{#startDate} ,'DD/MM/YYYY')"
                    + " AND DATE_START < TO_DATE( :#{#endDate} ,'DD/MM/YYYY')"
                    + " AND COMPANY_ID =?#{principal.company.id}"
            ,nativeQuery = true
    )
    @Nullable
    public Long countTaAnn(@Param("ptlBranchId") Long ptlBranchId, @Param("startDate") String startDate, @Param("endDate") String endDate);

    @Query(
            value = "SELECT count(*)"
                    + " FROM SP_PTL_COMP_TA_ANN"
                    + " WHERE ((TA_ANN_STATUS=0 AND REQ_STATUS=5) OR (TA_ANN_STATUS=1 AND REQ_STATUS=6))"
                    + " AND ID != :#{#compTaAnnPrevId}"
                    + " AND COMP_PTL_BRANCH_ID= :#{#ptlBranchId}"
                    + " AND TO_DATE(DATE_END ,'DD/MM/YYYY')> TO_DATE(SYSDATE ,'DD/MM/YYYY')"
                    + " AND DATE_END > TO_DATE( :#{#startDate} ,'DD/MM/YYYY')"
                    + " AND COMPANY_ID =?#{principal.company.id}"
            ,nativeQuery = true
    )
    @Nullable
    public Long countTaAnn2(@Param("ptlBranchId") Long ptlBranchId, @Param("startDate") String startDate, @Param("compTaAnnPrevId") Long compTaAnnPrevId);



    @Query(
            value = "SELECT count(*)"
                    + " FROM SP_PTL_COMP_TA_ANN"
                    + " WHERE ((TA_ANN_STATUS=0 AND REQ_STATUS=5) OR (TA_ANN_STATUS=1 AND REQ_STATUS=6))"
                    + " AND TO_DATE(DATE_END ,'DD/MM/YYYY')> TO_DATE(SYSDATE ,'DD/MM/YYYY')"
                    + " AND COMP_PTL_BRANCH_ID= :#{#ptlBranchId}"
                    + " AND COMPANY_ID =?#{principal.company.id}"
            ,nativeQuery = true
    )
    @Nullable
    public Long findCategTaAnn(@Param("ptlBranchId") Long ptlBranchId);

    /*
    * Called by TA [Controller cCompTaAnnRespond]
     */
    @Query(
            value = "SELECT o FROM SpPtlCompTaAnn o "
                    + " WHERE o.id =:#{#id}" /* TODO: enable access to TA (update accepted/rejected status)+ COMPANY */
    )
    @Nullable
    public SpPtlCompTaAnn taFindOne(@Param("id") Long id);

    @Query(
            value = "SELECT o FROM SpPtlCompTaAnn o "
                    + "WHERE  o.companyId =?#{principal.company.id} AND o.compPtlBranchId =:#{#branchId}"
    )

    @Nullable
    public Page<SpPtlCompTaAnn> findByPtlBranchId(Pageable pageable, @Param("branchId") Long branchId);


    @Modifying
    @Transactional
    @Query(
            value = "DELETE FROM SpPtlCompTaAnn o "
                    + "WHERE  o.companyId =?#{principal.company.id} AND o.id =:#{#id}"
    )
    @Nullable
    public void delete(@Param("id") Long id);
    /**
     * ****************************************************************************
     * ******************************************************************************
     */

    //@Override
    //@RestResource(exported = false)
    //public SpPtlEmployeeGenrequest findOne(Long id);

    //@Override
    //@RestResource(exported = false)
    //public Page<SpPtlEmployeeGenrequest> findAll(Pageable pageable);
    @Override
    @RestResource(exported = false)
    public long count();

    //@Override
    //@RestResource(exported = false)
    //public void delete(Long id);

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
    @RestResource(exported = true)
    public SpPtlCompTaAnn save(SpPtlCompTaAnn entity);
}