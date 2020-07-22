package sepe.repository.company;

import org.eclipse.persistence.jpa.rs.annotations.RestPageableQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import sepe.domain.company.SpPtlCompanyAccident;
import sepe.domain.company.TCompanyUserPrivilages;

import javax.annotation.Nullable;
import javax.persistence.EntityResult;
import javax.persistence.SqlResultSetMapping;
import java.util.List;

/**
 * Created by root on 20/4/2015.
 */

/*
* checkthis : replaced || with concat
*/
// http://localhost:7001/companyAccident
@Transactional( propagation = Propagation.REQUIRES_NEW )
@RepositoryRestResource(collectionResourceRel = "companyAccident", path = "/companyAccident")
public interface SpPtlCompanyAccidentRepo extends PagingAndSortingRepository<SpPtlCompanyAccident, Long> {

    @Query(
            /*
            value = "SELECT o FROM SpPtlCompanyAccident o "
                    + " WHERE  o.companyId =?#{principal.company.id}"
                    + " AND ("
                    + " ?#{principal.branchIds} LIKE CONCAT('%,' , o.compEbrBranchId , ',%')"
                    + " OR "
                    + " ?#{principal.branchIds} LIKE CONCAT(o.compEbrBranchId , ',%')"
                    + " OR "
                    + " ?#{principal.branchIds} LIKE CONCAT('%,' , o.compEbrBranchId)"
                    + " OR "
                    + " ?#{principal.branchIds} LIKE o.compEbrBranchId"
                    + " )"
           */

/*
            value = "SELECT * FROM SP_PTL_COMPANY_ACCIDENT"
            + " WHERE  COMPANY_ID =?#{principal.company.id}"
            + " AND ("
            + " ?#{principal.branchIds} LIKE '%,' || RG_EBR_BRANCH_ID || ',%'"
            + " OR "
            + " ?#{principal.branchIds} LIKE RG_EBR_BRANCH_ID  || ',%'"
            + " OR "
            + " ?#{principal.branchIds} LIKE '%,' || RG_EBR_BRANCH_ID "
            + " OR "
            + " ?#{principal.branchIds} LIKE RG_EBR_BRANCH_ID"
            + " )",
            nativeQuery = true
*/

            /*SELECT *
                    FROM  SP_PTL_COMPANY_ACCIDENT a
            INNER JOIN SP_PTL_COMPANY_USER_PRIVILAGES p
            ON a.COMPANY_ID = P.COMP_ID
            --INNER JOIN SP_PTL_USER u
            --ON u.ID = p.USER_ID
            WHERE
            a.company_Id =16174
            --AND u.ID= 16174 AND u.ROLE=7
            AND p.USER_ID= 33351
            AND p.BRANCH_IDS like '%,' || a.COMP_EBR_BRANCH_ID || ',%';*/

            /*value = "SELECT a FROM SpPtlCompanyAccident AS a INNER JOIN a.tCompanyUserPrivilages AS p"
                    + " WHERE  a.companyId =?#{principal.company.id}"
                    + " AND "
                    + " p.branchIds LIKE CONCAT('%,' , a.compEbrBranchId , ',%')"
                    + " AND "
                    + " p.userId = ?#{principal.userId} "*/

            value = "SELECT DISTINCT a FROM SpPtlCompanyAccident a left outer join TCompanyUserPrivilages p on a.companyId = p.compId, TUser u "
                    + " WHERE  a.companyId =?#{principal.company.id}"
                    + " AND "
                    + " u.id = ?#{principal.userId}"
                    + " AND ("
                    + " (u.role=7 AND p.userId=u.id AND p.branchIds LIKE CONCAT('%,' , a.compEbrBranchId , ',%'))"
                    + " OR "
                    + " (u.role=3 AND a.companyId = u.id))"

            /*value = "SELECT DISTINCT a FROM SpPtlCompanyAccident a, TCompanyUserPrivilages p, TUser u "
            + " WHERE  a.companyId =?#{principal.company.id}"
            + " AND "
            + " u.id = ?#{principal.userId}"
            + " AND ("
            + " (u.role=7 AND p.userId=u.id AND p.branchIds LIKE CONCAT('%,' , a.compEbrBranchId , ',%'))"
            + " OR "
            + " (u.role=3))"*/


            /*value = "SELECT a FROM SpPtlCompanyAccident a "
                    + " WHERE  a.companyId =?#{principal.company.id}"
                    + " AND "
                    + " a.tUser.id = ?#{principal.userId} AND a.tUser.role = 7"
                    + " AND "
                    + " a.tCompanyUserPrivilages.branchIds LIKE '%,' || a.compEbrBranchId || ',%'"
                    + " AND "
                    + " a.tCompanyUserPrivilages.userId = ?#{principal.userId} "
                    + " UNION "
                    + " (SELECT a FROM SpPtlCompanyAccident a"
                    + " WHERE  a.companyId =?#{principal.company.id}"
                    + " AND "
                    + " a.tUser.id = ?#{principal.userId} AND a.tUser.role = 3) "*/

            /*value = "SELECT * FROM (SELECT a.* FROM SP_PTL_COMPANY_ACCIDENT a, SP_PTL_COMPANY_USER_PRIVILAGES p, sp_ptl_user u"
                    + " WHERE  a.COMPANY_ID =?#{principal.company.id}"
                    + " AND "
                    + " u.ID = ?#{principal.userId} AND u.role = 7"
                    + " AND "
                    + " p.BRANCH_IDS LIKE '%,' || a.COMP_EBR_BRANCH_ID || ',%'"
                    + " AND "
                    + " p.USER_ID = ?#{principal.userId} "
                    + " UNION "
                    + " (SELECT a.* FROM SP_PTL_COMPANY_ACCIDENT a, sp_ptl_user u"
                    + " WHERE  a.COMPANY_ID =?#{principal.company.id}"
                    + " AND "
                    + " u.ID = ?#{principal.userId} AND u.role = 3))"*/

            /*value = "SELECT o FROM SpPtlCompanyAccident o "
            + " WHERE  o.companyId =?#{principal.company.id}"
            + " AND "
            + " UPPER(?#{principal.branchIds}) LIKE CONCAT('%,' , o.compEbrBranchId , ',%')"*/
    )
    @Nullable
    public Page<SpPtlCompanyAccident> findAll(Pageable pageable);

    @Query(
            /*value = "SELECT o FROM SpPtlCompanyAccident o "
                    + "WHERE  o.companyId =?#{principal.company.id} AND o.id =:#{#id}"
                    + " AND "
                    + " UPPER(?#{principal.branchIds}) LIKE CONCAT('%,' , o.compEbrBranchId , ',%')"*/

            value = "SELECT DISTINCT a FROM SpPtlCompanyAccident a, TCompanyUserPrivilages p, TUser u "
                    + " WHERE  a.companyId =?#{principal.company.id}"
                    + " AND "
                    + " u.id = ?#{principal.userId}"
                    + " AND "
                    + " a.id = :#{#id}"
                    + " AND ("
                    + " (u.role=7 AND p.userId=u.id AND p.branchIds LIKE CONCAT('%,' , a.compEbrBranchId , ',%'))"
                    + " OR "
                    + " (u.role=3))"
    )
    @Nullable
    public SpPtlCompanyAccident findOne(@Param("id") Long id);

    public SpPtlCompanyAccident save(SpPtlCompanyAccident entity);


    @Modifying
    @Transactional
    @Query(
            /*value = "DELETE FROM SpPtlCompanyAccident o "
                    + "WHERE  o.companyId =?#{principal.company.id} AND o.id =:#{#id}"
                    + " AND o.subStatus < 2"
                    + " AND "
                    + " UPPER(?#{principal.branchIds}) LIKE CONCAT('%,' , o.compEbrBranchId , ',%')"*/

            value = "DELETE FROM SP_PTL_COMPANY_ACCIDENT o"
            + " WHERE o.ID IN (SELECT DISTINCT a.ID FROM SP_PTL_COMPANY_ACCIDENT a, SP_PTL_COMPANY_USER_PRIVILAGES p, sp_ptl_user u"
            + " WHERE a.COMPANY_ID =?#{principal.company.id}"
            + " AND u.ID = ?#{principal.userId}"
            + " AND a.ID = :#{#id}"
            + " AND ("
            + " (u.ROLE=7 AND p.USER_ID=u.ID AND p.BRANCH_IDS LIKE '%,' || a.COMP_EBR_BRANCH_ID || ',%')"
            + " OR "
            + " (u.ROLE=3)))"
            ,nativeQuery = true

            /*value = "DELETE FROM SpPtlCompanyAccident o WHERE o.id = :#{#id} AND o.id IN (SELECT DISTINCT a.id FROM SpPtlCompanyAccident a, TCompanyUserPrivilages p, TUser u "
                    + " WHERE  a.companyId =?#{principal.company.id}"
                    + " AND "
                    + " u.id = ?#{principal.userId}"
                    + " AND "
                    + " a.id = :#{#id}"
                    + " AND ("
                    + " (u.role=7 AND p.userId=u.id AND p.branchIds LIKE CONCAT('%,' , a.compEbrBranchId , ',%'))"
                    + " OR "
                    + " (u.role=3)))"*/
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

//@Override
//@RestResource(exported = false)
//public SpPtlEmployeeGenrequest save(SpPtlEmployeeGenrequest entity);
}