package sepe.repository.company;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import sepe.domain.company.SpPtlCompProjAnnStudier;

import javax.annotation.Nullable;

/**
 * Created by Nikos on 4/19/2015.
 */

// http://localhost:7001/compProjAnnStudier/search/findByCompProjAnnId?compProjAnnId=1
// http://localhost:7001/compProjAnnStudier/
@RepositoryRestResource(collectionResourceRel = "compProjAnnStudier", path = "/compProjAnnStudier")
public interface SpPtlCompProjAnnStudierRepo extends PagingAndSortingRepository<SpPtlCompProjAnnStudier, Long> {


    @Query(
            value = "SELECT o FROM SpPtlCompProjAnnStudier o "
                    + "WHERE  o.compProjAnn_studier.userId =?#{principal.company.id} AND  o.compProjAnn_studier.id =:#{#compProjAnnId}"
    )
    @Nullable
    public Page<SpPtlCompProjAnnStudier> findByCompProjAnnId(Pageable pageable, @Param("compProjAnnId") Long compProjAnnId);

    @Query(
            value = "SELECT o FROM SpPtlCompProjAnnStudier o "
                    + "WHERE  o.compProjAnn_studier.userId =?#{principal.company.id} AND o.id =:#{#id}"
    )
    @Nullable
    public SpPtlCompProjAnnStudier findOne(@Param("id") Long id);

    public SpPtlCompProjAnnStudier save(SpPtlCompProjAnnStudier entity);

    /**
     * ****************************************************************************
     * ******************************************************************************
     */

    //@Override
    //@RestResource(exported = false)
    //public OBJ_TYPE findOne(ID_TYPE id);

    @Override
    @RestResource(exported = false)
    public Page<SpPtlCompProjAnnStudier> findAll(Pageable pageable);

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
