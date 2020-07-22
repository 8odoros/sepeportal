package sepe.repository.company;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import sepe.domain.company.SpPtlCompTaSannBook;

import javax.annotation.Nullable;

/**
 * Created by Nikos on 6/13/2015.
 */
// http://localhost:7001/compTaSannBook
// http://localhost:7001/compTaSannBook/search/findByCompShipId?compShipId=1261
@RepositoryRestResource(collectionResourceRel = "compTaSannBook", path = "/compTaSannBook")
public interface SpPtlCompTaSannBookRepo extends PagingAndSortingRepository<SpPtlCompTaSannBook, Long> {
    @Query(
            value = "SELECT o FROM SpPtlCompTaSannBook o "
                    + "WHERE  o.companyId =?#{principal.company.id}"
    )
    @Nullable
    public Page<SpPtlCompTaSannBook> findAll(Pageable pageable);

    @Query(
            value = "SELECT o FROM SpPtlCompTaSannBook o "
                    + "WHERE  o.companyId =?#{principal.company.id} AND o.id =:#{#id}"
    )
    @Nullable
    public SpPtlCompTaSannBook findOne(@Param("id") Long id);

    /*
    * Used by IE to get access to the IE_ANN_BOOK_ID of the branch
     */
    @Query(
            value = "SELECT o FROM SpPtlCompTaSannBook o"
                    + " WHERE o.compShipId =:#{#compShipId}"
    )
    @Nullable
    public SpPtlCompTaSannBook findByCompShipId(@Param("compShipId") Long compShipId);


    public SpPtlCompTaSannBook save(SpPtlCompTaSannBook entity);

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