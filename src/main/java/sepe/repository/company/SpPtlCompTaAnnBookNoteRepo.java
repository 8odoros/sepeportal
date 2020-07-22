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
import sepe.domain.company.SpPtlCompTaAnnBookNote;

import javax.annotation.Nullable;

/**
 * Created by Nikos on 6/16/2015.
 */
// http://localhost:7001/compTaAnnBookNote/search/findByCompTaAnnBookId?compTaAnnBookId=1304
// http://localhost:7001/compTaAnnBookNote/search/findOne?id=1
// http://localhost:7001/compTaAnnBookNote/search/findByPtlBranchId?ptlBranchId=1262
// http://localhost:7001/compTaAnnBookNote/search/findByCompTaAnnId?compTaAnnId=1350
@Transactional(propagation = Propagation.REQUIRES_NEW)
@RepositoryRestResource(collectionResourceRel = "compTaAnnBookNote", path = "/compTaAnnBookNote")
public interface SpPtlCompTaAnnBookNoteRepo extends PagingAndSortingRepository<SpPtlCompTaAnnBookNote, Long> {


    @Query(
            value = "SELECT o FROM SpPtlCompTaAnnBookNote o "
                    + "WHERE  o.compTaAnnBook.companyId =?#{principal.company.id} AND  o.compTaAnnBook.id =:#{#compTaAnnBookId}"
    )
    @Nullable
    public Page<SpPtlCompTaAnnBookNote> findByCompTaAnnBookId(Pageable pageable, @Param("compTaAnnBookId") Long compTaAnnBookId);

    @Query(
            value = "SELECT o FROM SpPtlCompTaAnnBookNote o "
                    + "WHERE  o.compTaAnnBook.companyId =?#{principal.company.id} AND o.id =:#{#id}"
    )
    @Nullable
    public SpPtlCompTaAnnBookNote findOne(@Param("id") Long id);

    public SpPtlCompTaAnnBookNote save(SpPtlCompTaAnnBookNote entity);

    @Query(
            value = "SELECT o FROM SpPtlCompTaAnnBookNote o "
                    + "WHERE  o.compTaAnnBook.companyId =?#{principal.company.id} AND o.compTaAnnBook.compPtlBranchId =:#{#ptlBranchId}"
    )
    @Nullable
    public Page<SpPtlCompTaAnnBookNote> findByPtlBranchId(Pageable pageable, @Param("ptlBranchId") Long ptlBranchId);

    /*
    * Called by TA to list all previously published book notes PREVIOUS with only one TA. now need security to add userId in note: next query
     */
    /*@Query(
            value = "SELECT o FROM SpPtlCompTaAnnBookNote o "
                    + " WHERE o.compTaAnn.id =:compTaAnnId" *//* Todo: secure this by adding IE_USER_ID into announcement *//*
    )
    @Nullable
    public Page<SpPtlCompTaAnnBookNote> findByCompTaAnnId(Pageable pageable, @Param("compTaAnnId") Long compTaAnnId);*/


    /*
    * Called by IE to list all previously published book notes
     */
    @Query(
            value = "SELECT o FROM SpPtlCompTaAnnBookNote o "
                    + " WHERE o.authorUserId =?#{principal.userId} AND o.compTaAnn.id =:#{#compTaAnnId}"
    )
    @Nullable
    public Page<SpPtlCompTaAnnBookNote> findByCompTaAnnId(Pageable pageable, @Param("compTaAnnId") Long compTaAnnId);
    /**
     * ****************************************************************************
     * ******************************************************************************
     */

    //@Override
    //@RestResource(exported = false)
    //public OBJ_TYPE findOne(ID_TYPE id);

    @Override
    @RestResource(exported = false)
    public Page<SpPtlCompTaAnnBookNote> findAll(Pageable pageable);

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
