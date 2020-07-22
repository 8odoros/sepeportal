package sepe.repository.company;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import sepe.domain.company.SpPtlCompIeAnnIeEntry;
import sepe.domain.company.SpPtlCompTaAnn;
import sepe.domain.company.SpPtlCompTaAnnDiaryEntry;
import sepe.domain.company.SpPtlCompTaAnnTaEntry;

import javax.annotation.Nullable;

/**
 * Created by Marios on 5/9/2016.
 */
// http://localhost:7001/compTaAnnTaEntry/search/findByCompTaAnnId?compTaAnnId=761
// http://localhost:7001/compTaAnnTaEntry/search/findOne?id=1
@RepositoryRestResource(collectionResourceRel = "compIeAnnIeEntry", path = "/compIeAnnIeEntry")
public interface SpPtlCompIeAnnIeEntryRepo extends PagingAndSortingRepository<SpPtlCompIeAnnIeEntry, Long> {


    @Query(
            value = "SELECT o FROM SpPtlCompIeAnnIeEntry o "
                    + "WHERE  o.compIeAnn.companyId =?#{principal.company.id} AND  o.compIeAnn.id =:#{#compIeAnnId}"
    )
    @Nullable
    public Page<SpPtlCompIeAnnIeEntry> findByCompIeAnnId(Pageable pageable, @Param("compIeAnnId") Long compIeAnnId);

    @Query(
            value = "SELECT o FROM SpPtlCompIeAnnIeEntry o "
                    + "WHERE  o.compIeAnn.companyId =?#{principal.company.id} AND o.id =:#{#id}"
    )
    @Nullable
    public SpPtlCompIeAnnIeEntry findOne(@Param("id") Long id);

    public SpPtlCompIeAnnIeEntry save(SpPtlCompIeAnnIeEntry entity);

    /**
     * ****************************************************************************
     * ******************************************************************************
     */
/*
    * Called by TA [Controller cCompTaAnnRespond]
     */
    @Query(
            value = "SELECT o FROM SpPtlCompIeAnnIeEntry o "
                    + " WHERE o.id =:#{#id}" /* TODO: enable access to TA (update accepted/rejected status)+ COMPANY */
    )
    @Nullable
    public SpPtlCompIeAnnIeEntry ieFindOne(@Param("id") Long id);
    //@Override
    //@RestResource(exported = false)
    //public OBJ_TYPE findOne(ID_TYPE id);

    @Override
    @RestResource(exported = false)
    public Page<SpPtlCompIeAnnIeEntry> findAll(Pageable pageable);

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
