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
import sepe.domain.company.SpPtlCompIeAnnBookNote;


import javax.annotation.Nullable;

/**
 * Created by Nikos on 5/16/2015.
 */
// http://localhost:7001/compIeAnnBookNote/search/findByCompIeAnnBookId?compIeAnnBookId=1304
// http://localhost:7001/compIeAnnBookNote/search/findOne?id=1
// http://localhost:7001/compIeAnnBookNote/search/findByPtlBranchId?ptlBranchId=1262
// http://localhost:7001/compIeAnnBookNote/search/findByCompIeAnnId?compIeAnnId=1350
@Transactional(propagation = Propagation.REQUIRES_NEW)
@RepositoryRestResource(collectionResourceRel = "compIeAnnBookNote", path = "/compIeAnnBookNote")
public interface SpPtlCompIeAnnBookNoteRepo extends PagingAndSortingRepository<SpPtlCompIeAnnBookNote, Long> {


    @Query(
            value = "SELECT o FROM SpPtlCompIeAnnBookNote o "
                    + "WHERE  o.compIeAnnBook.companyId =?#{principal.company.id} AND  o.compIeAnnBook.id =:#{#compIeAnnBookId}"
    )
    @Nullable
    public Page<SpPtlCompIeAnnBookNote> findByCompIeAnnBookId(Pageable pageable, @Param("compIeAnnBookId") Long compIeAnnBookId);

    @Query(
            value = "SELECT o FROM SpPtlCompIeAnnBookNote o "
                    + "WHERE  o.compIeAnnBook.companyId =?#{principal.company.id} AND o.id =:#{#id}"
    )
    @Nullable
    public SpPtlCompIeAnnBookNote findOne(@Param("id") Long id);

    public SpPtlCompIeAnnBookNote save(SpPtlCompIeAnnBookNote entity);

    @Query(
            value = "SELECT o FROM SpPtlCompIeAnnBookNote o "
                    + "WHERE  o.compIeAnnBook.companyId =?#{principal.company.id} AND o.compIeAnnBook.compPtlBranchId =:#{#ptlBranchId}"
    )
    @Nullable
    public Page<SpPtlCompIeAnnBookNote> findByPtlBranchId(Pageable pageable, @Param("ptlBranchId") Long ptlBranchId);

    /*
    * Called by IE to list all previously published book notes
     */
    @Query(
            value = "SELECT o FROM SpPtlCompIeAnnBookNote o "
                   + " WHERE o.compIeAnn.id =:compIeAnnId" /* Todo: secure this by adding IE_USER_ID into announcement */
    )
    @Nullable
    public Page<SpPtlCompIeAnnBookNote> findByCompIeAnnId(Pageable pageable, @Param("compIeAnnId") Long compIeAnnId);

    /**
     * ****************************************************************************
     * ******************************************************************************
     */

    //@Override
    //@RestResource(exported = false)
    //public OBJ_TYPE findOne(ID_TYPE id);

    @Override
    @RestResource(exported = false)
    public Page<SpPtlCompIeAnnBookNote> findAll(Pageable pageable);

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
