package sepe.repository.company;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.transaction.annotation.Transactional;
import sepe.domain.company.SpPtlCompPtlBranch;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Set;

/**
 * Created by Nikos on 5/16/2015.
 */
// http://localhost:7001/compPtlBranch
// http://localhost:7001/compPtlBranch/search/findIeAnnounced
// http://localhost:7001/compPtlBranch/search/findByIeRegRequestId?ieRegRequestId=499
// http://localhost:7001/compPtlBranch/search/findByActivationCode?brActive=123
@RepositoryRestResource(collectionResourceRel = "compPtlBranch", path = "/compPtlBranch")
public interface SpPtlCompPtlBranchRepo extends PagingAndSortingRepository<SpPtlCompPtlBranch, Long> {
    @Query(
            /*value = "SELECT o FROM SpPtlCompPtlBranch o "
                    + "WHERE  o.companyId =?#{principal.company.id}"*/
            value = "SELECT DISTINCT a FROM SpPtlCompPtlBranch a, TCompanyUserPrivilages p, TUser u "
                    + " WHERE  a.companyId =?#{principal.company.id}"
                    + " AND "
                    + " u.id = ?#{principal.userId}"
                    + " AND ("
                    + " (u.role=7 AND p.userId=u.id AND p.branchIds LIKE CONCAT('%,' , a.ebrBranchId , ',%'))"
                    + " OR "
                    + " (u.role=3))"
    )
    @Nullable
    public Page<SpPtlCompPtlBranch> findAll(Pageable pageable);

    @Query(
            value = "SELECT DISTINCT a FROM SpPtlCompPtlBranch a"
                    + " WHERE  a.ebrBranchId IN (:#{#branchIds})"
    )
    @Nullable
    public List<SpPtlCompPtlBranch> findAllByUser(@Param("branchIds") String branchIds);

    @Query(
            value = "SELECT DISTINCT a FROM SpPtlCompPtlBranch a, TCompanyUserPrivilages p, TUser u "
                    + " WHERE  a.companyId =?#{principal.company.id}"
                    + " AND "
                    + " a.brActive = :#{#brActive}"
                    + " AND "
                    + " u.id = ?#{principal.userId}"
                    + " AND ("
                    + " (u.role=7 AND p.userId=u.id AND p.branchIds LIKE CONCAT('%,' , a.ebrBranchId , ',%'))"
                    + " OR "
                    + " (u.role=3))"
    )
    @Nullable
    public List<SpPtlCompPtlBranch> findByActivationCode(@Param("brActive") Long brActive);

    @Query(
            /*value = "SELECT o FROM SpPtlCompPtlBranch o "
                    + "WHERE  o.companyId =?#{principal.company.id}"*/
            value = "SELECT DISTINCT a FROM SpPtlCompPtlBranch a, TCompanyUserPrivilages p, TUser u "
                    + " WHERE  a.companyId =?#{principal.company.id}"
                    + " AND "
                    + " u.id = ?#{principal.userId}"
                    + " AND ("
                    + " (u.role=7 AND p.userId=u.id AND p.branchIds LIKE CONCAT('%,' , a.ebrBranchId , ',%'))"
                    + " OR "
                    + " (u.role=3))"
                    //+ " ORDER BY a.brDescr ASC"
    )
    @Nullable
    public List<SpPtlCompPtlBranch> findAllList();

    @Query(
            value = "SELECT DISTINCT a FROM SpPtlCompPtlBranch a, TCompanyUserPrivilages p, TUser u "
                    + " WHERE  a.companyId =?#{principal.company.id}"
                    + " AND "
                    + " u.id = ?#{principal.userId}"
                    + " AND "
                    + " UPPER(a.brDescr) LIKE CONCAT('%' , :#{#descr} , '%')"
                    + " AND ("
                    + " (u.role=7 AND p.userId=u.id AND p.branchIds LIKE CONCAT('%,' , a.ebrBranchId , ',%'))"
                    + " OR "
                    + " (u.role=3))"
    )
    @Nullable
    public List<SpPtlCompPtlBranch> findByDescr(@Param("descr") String descr);

    @Query(
            value = "SELECT o FROM SpPtlCompPtlBranch o "
                    + "WHERE  o.companyId =?#{principal.company.id} AND o.id =:#{#id}"

            /*value = "SELECT DISTINCT a FROM SpPtlCompPtlBranch a, TCompanyUserPrivilages p, TUser u "
                    + " WHERE  a.companyId =?#{principal.company.id}"
                    + " AND "
                    + " u.id = ?#{principal.userId}"
                    + " AND "
                    + " a.id = :#{#id}"
                    + " AND ("
                    + " (u.role=7 AND p.userId=u.id AND p.branchIds LIKE CONCAT('%,' , a.ebrBranchId , ',%'))"
                    + " OR "
                    + " (u.role=3))"*/
    )
    @Nullable
    public SpPtlCompPtlBranch findOne(@Param("id") Long id);

    
    /*
    * Called from IE from controller to get a rough description for a branch
     */
    @RestResource(exported = false)
    @Nullable
    public SpPtlCompPtlBranch findById(@Param("id") Long id);


    
    @Query(
            value = "SELECT o FROM SpPtlCompPtlBranch o "
                    + "WHERE  o.companyId = :#{#companyId}"
    )
    @Nullable
    public Set<SpPtlCompPtlBranch> findAllByCompanyId(@Param("companyId") Long companyId);

    public SpPtlCompPtlBranch save(SpPtlCompPtlBranch entity);

    @Modifying
    @Transactional
    @Query(
            value = "DELETE FROM SpPtlCompPtlBranch o "
                    + "WHERE  o.companyId =?#{principal.company.id} AND o.id =:#{#id}"
    )
    @Nullable
    public void delete(@Param("id") Long id);

    /*
    * For company: Find branches with active ieAnn
     */
    @Query(
            /*
            value = "SELECT *" +
                    " FROM SP_PTL_COMP_PTL_BRANCH" +
                    " WHERE ID IN" +
                    " (SELECT COMP_PTL_BRANCH_ID" +
                    " FROM SP_PTL_COMP_IE_ANN" +
                    " WHERE COMPANY_ID =?#{principal.company.id} AND IE_ANN_STATUS > 0)" // TODO: Fix status code
            ,nativeQuery = true
            */
            value = "SELECT o" +
                    " FROM SpPtlCompPtlBranch o" +
                    " WHERE o.id IN" +
                    " (SELECT a.compPtlBranchId" +
                    " FROM SpPtlCompIeAnn a" +
                    " WHERE a.companyId = ?#{principal.company.id} and a.ieAnnStatus > 0)" // To do: privilages?
            ,nativeQuery = false
    )
    @Nullable
    public List<SpPtlCompPtlBranch> findIeAnnounced();

    /*
    * For IE: Find branches which match IE's regRequest
    */
    @Query(
            /*
            value = "SELECT *" +
                    " FROM SP_PTL_COMP_PTL_BRANCH" +
                    " WHERE ID IN" +
                    " (SELECT COMP_PTL_BRANCH_ID" +
                    " FROM SP_PTL_COMP_IE_ANN" +
                    " WHERE DOCTOR_REGREQUEST_ID =:#{#ieRegRequestId})" // To do: privilages?
                    ,nativeQuery = true
            */
            value = "SELECT o" +
                    " FROM SpPtlCompPtlBranch o" +
                    " WHERE o.id IN" +
                    " (SELECT a.compPtlBranchId" +
                    " FROM SpPtlCompIeAnn a" +
                    " WHERE a.doctorRegrequestId =:#{#ieRegRequestId})" // To do: privilages?
            ,nativeQuery = false
    )
    @Nullable
    public List<SpPtlCompPtlBranch> findByIeRegRequestId(@Param("ieRegRequestId") Long ieRegRequestId);
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

//@Override
//@RestResource(exported = false)
//public SpPtlEmployeeGenrequest save(SpPtlEmployeeGenrequest entity);
}