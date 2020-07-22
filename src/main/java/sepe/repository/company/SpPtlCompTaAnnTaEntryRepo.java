package sepe.repository.company;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import sepe.domain.company.SpPtlCompTaAnn;
import sepe.domain.company.SpPtlCompTaAnnDiaryEntry;
import sepe.domain.company.SpPtlCompTaAnnTaEntry;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by Marios on 5/9/2016.
 */
// http://localhost:7001/compTaAnnTaEntry/search/findByCompTaAnnId?compTaAnnId=761
// http://localhost:7001/compTaAnnTaEntry/search/findOne?id=1
@RepositoryRestResource(collectionResourceRel = "compTaAnnTaEntry", path = "/compTaAnnTaEntry")
public interface SpPtlCompTaAnnTaEntryRepo extends PagingAndSortingRepository<SpPtlCompTaAnnTaEntry, Long> {


    @Query(
            value = "SELECT o FROM SpPtlCompTaAnnTaEntry o "
                    + "WHERE  o.compTaAnn.companyId =?#{principal.company.id} AND  o.compTaAnn.id =:#{#compTaAnnId} ORDER BY o.cooperationType DESC"
    )
    @Nullable
    public List<SpPtlCompTaAnnTaEntry> findByCompTaAnnId(@Param("compTaAnnId") Long compTaAnnId);

    @Query(
            value = "SELECT o FROM SpPtlCompTaAnnTaEntry o "
                    + "WHERE  o.compTaAnn.companyId =?#{principal.company.id} AND o.id =:#{#id}"
    )
    @Nullable
    public SpPtlCompTaAnnTaEntry findOne(@Param("id") Long id);

    public SpPtlCompTaAnnTaEntry save(SpPtlCompTaAnnTaEntry entity);

    /**
     * ****************************************************************************
     * ******************************************************************************
     */
/*
    * Called by TA [Controller cCompTaAnnRespond]
     */
    @Query(
            value = "SELECT o FROM SpPtlCompTaAnnTaEntry o "
                    + " WHERE o.id =:#{#id}" /* TODO: enable access to TA (update accepted/rejected status)+ COMPANY */
    )
    @Nullable
    public SpPtlCompTaAnnTaEntry taFindOne(@Param("id") Long id);
    //@Override
    //@RestResource(exported = false)
    //public OBJ_TYPE findOne(ID_TYPE id);

    @Override
    @RestResource(exported = false)
    public Page<SpPtlCompTaAnnTaEntry> findAll(Pageable pageable);

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
