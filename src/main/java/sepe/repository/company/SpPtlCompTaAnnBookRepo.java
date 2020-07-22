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
import sepe.domain.company.SpPtlCompTaAnnBook;

import javax.annotation.Nullable;

/**
 * Created by Nikos on 6/16/2015.
 */
// http://localhost:7001/compTaAnnBook
// http://localhost:7001/compTaAnnBook/search/findByPtlBranchId?ptlBranchId=1261
@Transactional(propagation = Propagation.REQUIRES_NEW)
@RepositoryRestResource(collectionResourceRel = "compTaAnnBook", path = "/compTaAnnBook")
public interface SpPtlCompTaAnnBookRepo extends PagingAndSortingRepository<SpPtlCompTaAnnBook, Long> {
    @Query(
            value = "SELECT o FROM SpPtlCompTaAnnBook o "
                    + "WHERE  o.companyId =?#{principal.company.id}"
    )
    @Nullable
    public Page<SpPtlCompTaAnnBook> findAll(Pageable pageable);

    @Query(
            value = "SELECT o FROM SpPtlCompTaAnnBook o "
                    + "WHERE  o.companyId =?#{principal.company.id} AND o.id =:#{#id}"
    )
    @Nullable
    public SpPtlCompTaAnnBook findOne(@Param("id") Long id);

    /*
    * Used by IE to get access to the IE_ANN_BOOK_ID of the branch
     */
    @Query(
            value = "SELECT o FROM SpPtlCompTaAnnBook o"
                    + " WHERE o.compPtlBranchId =:#{#ptlBranchId}"
    )
    @Nullable
    public SpPtlCompTaAnnBook findByPtlBranchId(@Param("ptlBranchId") Long ptlBranchId);


    public SpPtlCompTaAnnBook save(SpPtlCompTaAnnBook entity);

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