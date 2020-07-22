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
import sepe.domain.company.SpPtlCompSundayPmt;

import javax.annotation.Nullable;

/**
 * Created by Nick on 4/25/2015.
 */
// http://localhost:7001/compSundayPmt
// http://localhost:7001/compSundayPmt/search/findOne?id=1
@Transactional(propagation = Propagation.REQUIRES_NEW)
@RepositoryRestResource(collectionResourceRel = "compSundayPmt", path = "/compSundayPmt")
public interface SpPtlCompSundayPmtRepo extends PagingAndSortingRepository<SpPtlCompSundayPmt, Long> {
    @Query(
            /*value = "SELECT o FROM SpPtlCompSundayPmt o "
                    + "WHERE  o.companyId =?#{principal.company.id}"
                    + " AND "
                    + " UPPER(?#{principal.branchIds}) LIKE CONCAT('%,' , o.compEbrBranchId , ',%')"*/
            value = "SELECT DISTINCT a FROM SpPtlCompSundayPmt a, TCompanyUserPrivilages p, TUser u "
                    + " WHERE  a.companyId =?#{principal.company.id}"
                    + " AND "
                    + " u.id = ?#{principal.userId}"
                    + " AND ("
                    + " (u.role=7 AND p.userId=u.id AND p.branchIds LIKE CONCAT('%,' , a.compEbrBranchId , ',%'))"
                    + " OR "
                    + " (u.role=3))"
    )
    @Nullable
    public Page<SpPtlCompSundayPmt> findAll(Pageable pageable);

    @Query(
            /*value = "SELECT o FROM SpPtlCompSundayPmt o "
                    + "WHERE  o.companyId =?#{principal.company.id} AND o.id =:#{#id}"
                    + " AND "
                    + " UPPER(?#{principal.branchIds}) LIKE CONCAT('%,' , o.compEbrBranchId , ',%')"*/

            value = "SELECT DISTINCT a FROM SpPtlCompSundayPmt a, TCompanyUserPrivilages p, TUser u "
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
    public SpPtlCompSundayPmt findOne(@Param("id") Long id);

    public SpPtlCompSundayPmt save(SpPtlCompSundayPmt entity);

    @Modifying
    @Transactional
    @Query(
            value = "DELETE FROM SpPtlCompSundayPmt o "
                    + "WHERE  o.companyId =?#{principal.company.id} AND o.id =:#{#id}"
                    + " AND o.subStatus < 2"
                    //+ " AND "
                    //+ " UPPER(?#{principal.branchIds}) LIKE CONCAT('%,' , o.compEbrBranchId , ',%')"
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