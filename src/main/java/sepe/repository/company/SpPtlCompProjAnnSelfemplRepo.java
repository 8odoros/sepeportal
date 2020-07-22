package sepe.repository.company;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import sepe.domain.company.SpPtlCompProjAnnSelfempl;

import javax.annotation.Nullable;

/**
 * Created by Nikos on 4/19/2015.
 */

// http://localhost:7001/compProjAnnSelfempl/search/findByCompProjAnnId?compProjAnnId=1
// http://localhost:7001/compProjAnnSelfempl/
@RepositoryRestResource(collectionResourceRel = "compProjAnnSelfempl", path = "/compProjAnnSelfempl")
public interface SpPtlCompProjAnnSelfemplRepo extends PagingAndSortingRepository<SpPtlCompProjAnnSelfempl, Long> {


    @Query(
            value = "SELECT o FROM SpPtlCompProjAnnSelfempl o "
                    + "WHERE  o.compProjAnn_selfempl.userId =?#{principal.company.id} AND  o.compProjAnn_selfempl.id =:#{#compProjAnnId}"
    )
    @Nullable
    public Page<SpPtlCompProjAnnSelfempl> findByCompProjAnnId(Pageable pageable, @Param("compProjAnnId") Long compProjAnnId);

    @Query(
            value = "SELECT o FROM SpPtlCompProjAnnSelfempl o "
                    + "WHERE  o.compProjAnn_selfempl.userId =?#{principal.company.id} AND o.id =:#{#id}"
    )
    @Nullable
    public SpPtlCompProjAnnSelfempl findOne(@Param("id") Long id);

    public SpPtlCompProjAnnSelfempl save(SpPtlCompProjAnnSelfempl entity);

    /**
     * ****************************************************************************
     * ******************************************************************************
     */

    //@Override
    //@RestResource(exported = false)
    //public OBJ_TYPE findOne(ID_TYPE id);

    @Override
    @RestResource(exported = false)
    public Page<SpPtlCompProjAnnSelfempl> findAll(Pageable pageable);

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
