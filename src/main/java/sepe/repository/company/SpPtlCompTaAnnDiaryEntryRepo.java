package sepe.repository.company;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import sepe.domain.company.SpPtlCompTaAnnDiaryEntry;


import javax.annotation.Nullable;

/**
 * Created by Nikos on 6/16/2015.
 */
// http://localhost:7001/compTaAnnDiaryEntry/search/findByCompTaAnnId?compTaAnnId=761
// http://localhost:7001/compTaAnnDiaryEntry/search/findOne?id=1
@RepositoryRestResource(collectionResourceRel = "compTaAnnDiaryEntry", path = "/compTaAnnDiaryEntry")
public interface SpPtlCompTaAnnDiaryEntryRepo extends PagingAndSortingRepository<SpPtlCompTaAnnDiaryEntry, Long> {


    @Query(
            value = "SELECT o FROM SpPtlCompTaAnnDiaryEntry o "
                    + "WHERE  o.compTaAnn.companyId =?#{principal.company.id} AND  o.compTaAnn.id =:#{#compTaAnnId}"
    )
    @Nullable
    public Page<SpPtlCompTaAnnDiaryEntry> findByCompTaAnnId(Pageable pageable, @Param("compTaAnnId") Long compTaAnnId);

    @Query(
            value = "SELECT o FROM SpPtlCompTaAnnDiaryEntry o "
                    + "WHERE  o.compTaAnn.companyId =?#{principal.company.id} AND o.id =:#{#id}"
    )
    @Nullable
    public SpPtlCompTaAnnDiaryEntry findOne(@Param("id") Long id);

    @Query(
            value = "SELECT sum(o.visitDurationMinutes) FROM SpPtlCompTaAnnDiaryEntry o"
                    + " WHERE o.compTaAnn.id =:#{#taAnnId}"
            ,nativeQuery = false
    )
    public Long sumOfTaAnnMinutes(@Param("taAnnId") Long taAnnId);

    public SpPtlCompTaAnnDiaryEntry save(SpPtlCompTaAnnDiaryEntry entity);

    /**
     * ****************************************************************************
     * ******************************************************************************
     */

    //@Override
    //@RestResource(exported = false)
    //public OBJ_TYPE findOne(ID_TYPE id);

    @Override
    @RestResource(exported = false)
    public Page<SpPtlCompTaAnnDiaryEntry> findAll(Pageable pageable);

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
