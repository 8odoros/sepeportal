package sepe.repository.company;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sepe.domain.company.SpPtlCompIeAnnBook;


import javax.annotation.Nullable;

/**
 * Created by Nikos on 5/16/2015.
 */
// http://localhost:7001/compIeAnnBook
// http://localhost:7001/compIeAnnBook/search/findByPtlBranchId?ptlBranchId=1261
@Transactional(propagation = Propagation.REQUIRES_NEW)
@RepositoryRestResource(collectionResourceRel = "compIeAnnBook", path = "/compIeAnnBook")
public interface SpPtlCompIeAnnBookRepo extends PagingAndSortingRepository<SpPtlCompIeAnnBook, Long> {
    @Query(
            value = "SELECT o FROM SpPtlCompIeAnnBook o "
                    + "WHERE  o.companyId =?#{principal.company.id}"
            /*value = "SELECT DISTINCT a FROM SpPtlCompIeAnnBook a, TCompanyUserPrivilages p, TUser u "
                    + " WHERE  a.companyId =?#{principal.company.id}"
                    + " AND "
                    + " u.id = ?#{principal.userId}"
                    + " AND ("
                    + " (u.role=7 AND p.userId=u.id AND p.branchIds LIKE CONCAT('%,' , a.compPtlBranchId , ',%'))"
                    + " OR "
                    + " (u.role=3))"*/
    )
    @Nullable
    public Page<SpPtlCompIeAnnBook> findAll(Pageable pageable);

    @Query(
            value = "SELECT o FROM SpPtlCompIeAnnBook o "
                    + "WHERE  o.companyId =?#{principal.company.id} AND o.id =:#{#id}"

            /*value = "SELECT DISTINCT a FROM SpPtlCompIeAnnBook a, TCompanyUserPrivilages p, TUser u "
                    + " WHERE  a.companyId =?#{principal.company.id}"
                    + " AND "
                    + " u.id = ?#{principal.userId}"
                    + " AND "
                    + " a.id = :#{#id}"
                    + " AND ("
                    + " (u.role=7 AND p.userId=u.id AND p.branchIds LIKE CONCAT('%,' , a.compPtlBranchId , ',%'))"
                    + " OR "
                    + " (u.role=3))"*/
    )
    @Nullable
    public SpPtlCompIeAnnBook findOne(@Param("id") Long id);

    /*
    * Used by IE to get access to the IE_ANN_BOOK_ID of the branch
     */
    @Query(
            value = "SELECT o FROM SpPtlCompIeAnnBook o"
                     + " WHERE o.compPtlBranchId =:#{#ptlBranchId}"
    )
    @Nullable
    public SpPtlCompIeAnnBook findByPtlBranchId(@Param("ptlBranchId") Long ptlBranchId);


    public SpPtlCompIeAnnBook save(SpPtlCompIeAnnBook entity);

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

//@Override
//@RestResource(exported = false)
//public SpPtlEmployeeGenrequest save(SpPtlEmployeeGenrequest entity);
}