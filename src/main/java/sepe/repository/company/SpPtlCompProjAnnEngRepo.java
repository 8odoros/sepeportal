package sepe.repository.company;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import sepe.domain.company.SpPtlCompProjAnnEng;

import javax.annotation.Nullable;

/**
 * Created by Nikos on 4/19/2015.
 */

// http://localhost:7001/compProjAnnEng/search/findByCompProjAnnId?compProjAnnId=1
// http://localhost:7001/compProjAnnEng/
@RepositoryRestResource(collectionResourceRel = "compProjAnnEng", path = "/compProjAnnEng")
public interface SpPtlCompProjAnnEngRepo extends PagingAndSortingRepository<SpPtlCompProjAnnEng, Long> {


    @Query(
            value = "SELECT o FROM SpPtlCompProjAnnEng o "
                    + "WHERE  o.compProjAnn_eng.userId =?#{principal.company.id} AND  o.compProjAnn_eng.id =:#{#compProjAnnId}"
    )
    @Nullable
    public Page<SpPtlCompProjAnnEng> findByCompProjAnnId(Pageable pageable, @Param("compProjAnnId") Long compProjAnnId);

    @Query(
            value = "SELECT o FROM SpPtlCompProjAnnEng o "
                    + "WHERE  o.compProjAnn_eng.userId =?#{principal.company.id} AND o.id =:#{#id}"
    )
    @Nullable
    public SpPtlCompProjAnnEng findOne(@Param("id") Long id);

    public SpPtlCompProjAnnEng save(SpPtlCompProjAnnEng entity);

    /**
     * ****************************************************************************
     * ******************************************************************************
     */

    //@Override
    //@RestResource(exported = false)
    //public OBJ_TYPE findOne(ID_TYPE id);

    @Override
    @RestResource(exported = false)
    public Page<SpPtlCompProjAnnEng> findAll(Pageable pageable);

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
