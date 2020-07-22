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
import sepe.domain.company.SpPtlCompGenreq;


import javax.annotation.Nullable;

/**
 * Created by Nick on 5/6/2015.
 */

// http://localhost:7001/compGenreq
@Transactional(propagation = Propagation.REQUIRES_NEW)
@RepositoryRestResource(collectionResourceRel = "compGenreq", path = "/compGenreq")
public interface SpPtlCompGenreqRepo extends PagingAndSortingRepository<SpPtlCompGenreq, Long> {
    @Query(
            /*value = "SELECT o FROM SpPtlCompGenreq o "
                    + "WHERE  o.companyId =?#{principal.company.id}"*/
            value = "SELECT DISTINCT a FROM SpPtlCompGenreq a, TCompanyUserPrivilages p, TUser u "
                    + " WHERE  a.companyId =?#{principal.company.id}"
                    + " AND a.requestTypeId NOT IN(24)"
                    + " AND "
                    + " u.id = ?#{principal.userId}"
                    + " AND ("
                    + " (u.role=7 AND p.userId=u.id AND p.branchIds LIKE CONCAT('%,' , a.branch1Id , ',%'))"
                    + " OR "
                    + " (u.role=3))"
    )
    @Nullable
    public Page<SpPtlCompGenreq> findAll(Pageable pageable);

    @Query(
            /*value = "SELECT o FROM SpPtlCompGenreq o "
                    + "WHERE  o.companyId =?#{principal.company.id}"*/
            value = "SELECT DISTINCT a FROM SpPtlCompGenreq a, TCompanyUserPrivilages p, TUser u "
                    + " WHERE  a.companyId =?#{principal.company.id}"
                    + " AND a.requestTypeId=24"
                    + " AND "
                    + " u.id = ?#{principal.userId}"
                    + " AND ("
                    + " (u.role=7 AND p.userId=u.id AND p.branchIds LIKE CONCAT('%,' , a.branch1Id , ',%'))"
                    + " OR "
                    + " (u.role=3))"
    )
    @Nullable
    public Page<SpPtlCompGenreq> findAllCertificate(Pageable pageable);

    @Query(
            /*value = "SELECT o FROM SpPtlCompGenreq o "
                    + "WHERE  o.companyId =?#{principal.company.id} AND o.id =:#{#id}"*/

            value = "SELECT DISTINCT a FROM SpPtlCompGenreq a, TCompanyUserPrivilages p, TUser u "
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
    public SpPtlCompGenreq findOne(@Param("id") Long id);

    @Query(
            /*value = "SELECT o FROM SpPtlCompGenreq o "
                    + "WHERE  o.companyId =?#{principal.company.id} AND o.id =:#{#id}"*/

            value = "SELECT DISTINCT a FROM SpPtlCompGenreq a, TCompanyUserPrivilages p, TUser u "
                    + " WHERE  a.companyId =?#{principal.company.id}"
                    + " AND "
                    + " u.id = ?#{principal.userId}"
                    + " AND "
                    + " a.protNo = :#{#protNo}"
                    + " AND ("
                    + " (u.role=7 AND p.userId=u.id AND p.branchIds LIKE CONCAT('%,' , a.branch1Id , ',%'))"
                    + " OR "
                    + " (u.role=3))"
    )
    @Nullable
    public SpPtlCompGenreq findByProtNo(@Param("protNo") String protNo);

    public SpPtlCompGenreq save(SpPtlCompGenreq entity);

    @Modifying
    @Transactional
    @Query(
            value = "DELETE FROM SpPtlCompGenreq o "
                    + "WHERE  o.companyId =?#{principal.company.id} AND o.id =:#{#id}"
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