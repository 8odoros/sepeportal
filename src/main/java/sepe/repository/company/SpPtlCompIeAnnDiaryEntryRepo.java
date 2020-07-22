package sepe.repository.company;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import sepe.domain.company.SpPtlCompIeAnnDiaryEntry;


import javax.annotation.Nullable;

/**
 * Created by Nikos on 5/16/2015.
 */
// http://localhost:7001/compIeAnnDiaryEntry/search/findByCompIeAnnId?compIeAnnId=761
// http://localhost:7001/compIeAnnDiaryEntry/search/findOne?id=1
@RepositoryRestResource(collectionResourceRel = "compIeAnnDiaryEntry", path = "/compIeAnnDiaryEntry")
public interface SpPtlCompIeAnnDiaryEntryRepo extends PagingAndSortingRepository<SpPtlCompIeAnnDiaryEntry, Long> {


    @Query(
            value = "SELECT o FROM SpPtlCompIeAnnDiaryEntry o "
                    + "WHERE  o.compIeAnn.companyId =?#{principal.company.id} AND  o.compIeAnn.id =:#{#compIeAnnId}"
    )
    @Nullable
    public Page<SpPtlCompIeAnnDiaryEntry> findByCompIeAnnId(Pageable pageable, @Param("compIeAnnId") Long compIeAnnId);

    @Query(
            value = "SELECT o FROM SpPtlCompIeAnnDiaryEntry o "
                    + "WHERE  o.compIeAnn.companyId =?#{principal.company.id} AND o.id =:#{#id}"
    )
    @Nullable
    public SpPtlCompIeAnnDiaryEntry findOne(@Param("id") Long id);

    @Query(
            value = "SELECT sum(o.visitDurationMinutes) FROM SpPtlCompIeAnnDiaryEntry o"
                    + " WHERE o.compIeAnn.id =:#{#ieAnnId}"
            ,nativeQuery = false
    )
    public Long sumOfIeAnnMinutes(@Param("ieAnnId") Long ieAnnId);

    public SpPtlCompIeAnnDiaryEntry save(SpPtlCompIeAnnDiaryEntry entity);

    /**
     * ****************************************************************************
     * ******************************************************************************
     */

    //@Override
    //@RestResource(exported = false)
    //public OBJ_TYPE findOne(ID_TYPE id);

    @Override
    @RestResource(exported = false)
    public Page<SpPtlCompIeAnnDiaryEntry> findAll(Pageable pageable);

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
