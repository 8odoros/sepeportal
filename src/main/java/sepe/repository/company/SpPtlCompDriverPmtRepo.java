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
import sepe.domain.company.SpPtlCompDriverPmt;


import javax.annotation.Nullable;

/**
 * Created by Nikos on 6/9/2015.
 */
// http://localhost:7001/compDriverPmt
@Transactional(propagation = Propagation.REQUIRES_NEW)
@RepositoryRestResource(collectionResourceRel = "compDriverPmt", path = "/compDriverPmt")
public interface SpPtlCompDriverPmtRepo extends PagingAndSortingRepository<SpPtlCompDriverPmt, Long> {
    @Query(
            /*value = "SELECT o FROM SpPtlCompDriverPmt o "
                    + "WHERE  o.companyId =?#{principal.company.id}"*/
            value = "SELECT DISTINCT a FROM SpPtlCompDriverPmt a, TCompanyUserPrivilages p, TUser u "
                    + " WHERE  a.companyId =?#{principal.company.id}"
                    + " AND "
                    + " u.id = ?#{principal.userId}"
                    + " AND ("
                    + " (u.role=7 AND p.userId=u.id AND p.branchIds LIKE CONCAT('%,' , a.branch1Id , ',%'))"
                    + " OR "
                    + " (u.role=3))"
    )
    @Nullable
    public Page<SpPtlCompDriverPmt> findAll(Pageable pageable);

    @Query(
            /*value = "SELECT o FROM SpPtlCompDriverPmt o "
                    + "WHERE  o.companyId =?#{principal.company.id} AND o.id =:#{#id}"*/

            value = "SELECT DISTINCT a FROM SpPtlCompDriverPmt a, TCompanyUserPrivilages p, TUser u "
                    + " WHERE  a.companyId =?#{principal.company.id}"
                    + " AND "
                    + " u.id = ?#{principal.userId}"
                    + " AND "
                    + " a.id = :#{#id}"
                    + " AND ("
                    + " (u.role=7 AND p.userId=u.id AND p.branchIds LIKE CONCAT('%,' , a.branch1Id , ',%'))"
                    + " OR "
                    + " (u.role=3))"
    )
    @Nullable
    public SpPtlCompDriverPmt findOne(@Param("id") Long id);

    @Query(
            value = "SELECT o FROM SpPtlCompDriverPmt o "
                    + "WHERE  o.companyId =?#{principal.company.id} AND o.ownerAfm =:#{#ownerAfm}"
                    + " AND o.pmtMonth =:#{#pmtMonth} AND o.pmtYear =:#{#pmtYear} AND o.subStatus =:#{#subStatus}"
    )
    @Nullable
    public SpPtlCompDriverPmt driverPmtExists(@Param("ownerAfm") String ownerAfm, @Param("pmtMonth") Integer pmtMonth, @Param("pmtYear") Integer pmtYear, @Param("subStatus") Integer subStatus);

    public SpPtlCompDriverPmt save(SpPtlCompDriverPmt entity);

    @Modifying
    @Transactional
    @Query(
            value = "DELETE FROM SpPtlCompDriverPmt o "
                    + "WHERE o.companyId =?#{principal.company.id} AND o.id =:#{#id}"
                    + " AND o.subStatus < 2"
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