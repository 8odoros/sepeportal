package sepe.repository.company;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import sepe.domain.company.SpPtlCompTaSannBookNote;

import javax.annotation.Nullable;

/**
 * Created by Nikos on 6/13/2015.
 */
// http://localhost:7001/compTaSannBookNote/search/findByCompTaSannBookId?compTaSannBookId=1304
// http://localhost:7001/compTaSannBookNote/search/findOne?id=1
// http://localhost:7001/compTaSannBookNote/search/findByCompShipId?compShipId=1262
// http://localhost:7001/compTaSannBookNote/search/findByCompTaSannId?compTaSannId=1350
@RepositoryRestResource(collectionResourceRel = "compTaSannBookNote", path = "/compTaSannBookNote")
public interface SpPtlCompTaSannBookNoteRepo extends PagingAndSortingRepository<SpPtlCompTaSannBookNote, Long> {


    @Query(
            value = "SELECT o FROM SpPtlCompTaSannBookNote o "
                    + "WHERE  o.compTaSannBook.companyId =?#{principal.company.id} AND  o.compTaSannBook.id =:#{#compTaSannBookId}"
    )
    @Nullable
    public Page<SpPtlCompTaSannBookNote> findByCompTaSannBookId(Pageable pageable, @Param("compTaSannBookId") Long compTaSannBookId);

    @Query(
            value = "SELECT o FROM SpPtlCompTaSannBookNote o "
                    + "WHERE  o.compTaSannBook.companyId =?#{principal.company.id} AND o.id =:#{#id}"
    )
    @Nullable
    public SpPtlCompTaSannBookNote findOne(@Param("id") Long id);

    public SpPtlCompTaSannBookNote save(SpPtlCompTaSannBookNote entity);

    @Query(
            value = "SELECT o FROM SpPtlCompTaSannBookNote o "
                    + "WHERE  o.compTaSannBook.companyId =?#{principal.company.id} AND o.compTaSannBook.compShipId =:#{#compShipId}"
    )
    @Nullable
    public Page<SpPtlCompTaSannBookNote> findByCompShipId(Pageable pageable, @Param("compShipId") Long compShipId);

    /*
    * Called by IE to list all previously published book notes
     */
    @Query(
            value = "SELECT o FROM SpPtlCompTaSannBookNote o "
                    + " WHERE o.compTaSannId =:compTaSannId" /* Todo: secure this by adding IE_USER_ID into announcement */
    )
    @Nullable
    public Page<SpPtlCompTaSannBookNote> findByCompTaSannId(Pageable pageable, @Param("compTaSannId") Long compTaSannId);

    /**
     * ****************************************************************************
     * ******************************************************************************
     */

    //@Override
    //@RestResource(exported = false)
    //public OBJ_TYPE findOne(ID_TYPE id);

    @Override
    @RestResource(exported = false)
    public Page<SpPtlCompTaSannBookNote> findAll(Pageable pageable);

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
    // @RestResource(exported = false)
    //public OBJ_TYPE save(OBJ_TYPE entity);
}
