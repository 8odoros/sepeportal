package sepe.repository.company;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import sepe.domain.company.SpPtlCompSundayPmtPers;

import javax.annotation.Nullable;

/**
 * Created by Nick on 4/25/2015.
 */

// http://localhost:7001/compSundayPmtPers/search/findByCompSundayPmtId?compSundayPmtId=1
// http://localhost:7001/compSundayPmtPers
@RepositoryRestResource(collectionResourceRel = "compSundayPmtPers", path = "/compSundayPmtPers")
public interface SpPtlCompSundayPmtPersRepo extends PagingAndSortingRepository<SpPtlCompSundayPmtPers, Long> {


    @Query(
            value = "SELECT o FROM SpPtlCompSundayPmtPers o "
                    + "WHERE  o.compSundayPmt.companyId =?#{principal.company.id} AND  o.compSundayPmt.id =:#{#compSundayPmtId}"
    )
    @Nullable
    public Page<SpPtlCompSundayPmtPers> findByCompSundayPmtId(Pageable pageable, @Param("compSundayPmtId") Long compSundayPmtId);

    @Query(
            value = "SELECT o FROM SpPtlCompSundayPmtPers o "
                    + "WHERE  o.compSundayPmt.companyId =?#{principal.company.id} AND o.id =:#{#id}"
    )
    @Nullable
    public SpPtlCompSundayPmtPers findOne(@Param("id") Long id);

    public SpPtlCompSundayPmtPers save(SpPtlCompSundayPmtPers entity);

    /**
     * ****************************************************************************
     * ******************************************************************************
     */

    //@Override
    //@RestResource(exported = false)
    //public OBJ_TYPE findOne(ID_TYPE id);

    @Override
    @RestResource(exported = false)
    public Page<SpPtlCompSundayPmtPers> findAll(Pageable pageable);

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
