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
import sepe.domain.company.SpPtlCompIeAnn;

import javax.annotation.Nullable;

/**
 * Created by Nikos on 5/16/2015.
 */
// http://localhost:7001/compIeAnn
// http://localhost:7001/compIeAnn/search/findByPtlBranchId?branchId=1261
// http://localhost:7001/compIeAnn/search/countIeAnn?ptlBranchId=1348&startDate=25%2f5%2f2015
// http://localhost:7001/compIeAnn/search/findCategIeAnn?ptlBranchId=1348
@Transactional(propagation = Propagation.REQUIRES_NEW)
@RepositoryRestResource(collectionResourceRel = "compIeAnn", path = "/compIeAnn")
public interface SpPtlCompIeAnnRepo extends PagingAndSortingRepository<SpPtlCompIeAnn, Long> {
    @Query(
            value = "SELECT o FROM SpPtlCompIeAnn o "
                    + "WHERE  o.companyId =?#{principal.company.id}"
            /*value = "SELECT DISTINCT a FROM SpPtlCompIeAnn a, TCompanyUserPrivilages p, TUser u "
                    + " WHERE  a.companyId =?#{principal.company.id}"
                    + " AND "
                    + " u.id = ?#{principal.userId}"
                    + " AND ("
                    + " (u.role=7 AND p.userId=u.id AND p.branchIds LIKE CONCAT('%,' , a.compEbrBranchId , ',%'))"
                    + " OR "
                    + " (u.role=3))"*/
    )
    @Nullable
    public Page<SpPtlCompIeAnn> findAll(Pageable pageable);

    @Query(
            value = "SELECT o FROM SpPtlCompIeAnn o "
            + "WHERE o.id =:#{#id}"  /*TODO: privilages [kalled by ie controler*/

            /*value = "SELECT DISTINCT a FROM SpPtlCompIeAnn a, TCompanyUserPrivilages p, TUser u "
                    + " WHERE  a.companyId =?#{principal.company.id}"
                    + " AND "
                    + " u.id = ?#{principal.userId}"
                    + " AND "
                    + " a.id = :#{#id}"
                    + " AND ("
                    + " (u.role=7 AND p.userId=u.id AND p.branchIds LIKE CONCAT('%,' , a.compEbrBranchId , ',%'))"
                    + " OR "
                    + " (u.role=3))"*/
    )

    @Nullable
    public SpPtlCompIeAnn findOne(@Param("id") Long id);

    /*
    * Company checks if date range of new ieAnn conflicts with older ones.
    * 0 means no conflict found.
    */
    @Query(
            value = "SELECT count(*)"
                    + " FROM SP_PTL_COMP_IE_ANN"
                    + " WHERE ((IE_ANN_STATUS=0 AND REQ_STATUS=5) OR (IE_ANN_STATUS=1 AND REQ_STATUS=6))"
                    + " AND COMP_PTL_BRANCH_ID= :#{#ptlBranchId}"
                    + " AND DATE_END > TO_DATE( :#{#startDate} ,'DD-MM-YYYY')"
                    + " AND COMPANY_ID =?#{principal.company.id}"
            ,nativeQuery = true
    )
    @Nullable
    public Long countIeAnn(@Param("ptlBranchId") Long ptlBranchId, @Param("startDate") String startDate);

    /*
    *
    */
    @Query(
            value = "SELECT count(*)"
                    + " FROM SP_PTL_COMP_IE_ANN"
                    + " WHERE ((IE_ANN_STATUS=0 AND REQ_STATUS=5) OR (IE_ANN_STATUS=1 AND REQ_STATUS=6))"
                    + " AND TO_DATE(DATE_END ,'DD/MM/YYYY')> TO_DATE(SYSDATE ,'DD/MM/YYYY')"
                    + " AND COMP_PTL_BRANCH_ID= :#{#ptlBranchId}"
                    + " AND COMPANY_ID =?#{principal.company.id}"
            ,nativeQuery = true
    )
    @Nullable
    public Long findCategIeAnn(@Param("ptlBranchId") Long ptlBranchId);


    /*
    * Called by IE [Controller cCompIeAnnRespond]
     */
    @Query(
            value = "SELECT o FROM SpPtlCompIeAnn o "
                    + " WHERE o.id =:#{#id}" /* TODO: enable access to IE (update accepted/rejected status)+ COMPANY */
    )
    @Nullable
    public SpPtlCompIeAnn ieFindOne(@Param("id") Long id);

    @Query(
            value = "SELECT o FROM SpPtlCompIeAnn o "
                    + "WHERE  o.companyId =?#{principal.company.id} AND o.compPtlBranchId =:#{#branchId}"
    )

    @Nullable
    public Page<SpPtlCompIeAnn> findByPtlBranchId(Pageable pageable, @Param("branchId") Long branchId);


    @Modifying
    @Transactional
    @Query(
            value = "DELETE FROM SpPtlCompIeAnn o "
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
    public SpPtlCompIeAnn save(SpPtlCompIeAnn entity);
}