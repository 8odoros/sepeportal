package sepe.repository.company;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import sepe.domain.company.SpPtlCompJobRecrOffPers;


import javax.annotation.Nullable;

/**
 * Created by Nick on 4/29/2015.
 */
// http://localhost:7001/compJobRecrOffPers/search/findByCompJobRecrOffId?compJobRecrOffId=1
// http://localhost:7001/compJobRecrOffPers
@RepositoryRestResource(collectionResourceRel = "compJobRecrOffPers", path = "/compJobRecrOffPers")
public interface SpPtlCompJobRecrOffPersRepo extends PagingAndSortingRepository<SpPtlCompJobRecrOffPers, Long> {


    @Query(
            value = "SELECT o FROM SpPtlCompJobRecrOffPers o "
                    + "WHERE  o.compJobRecrOff.companyId =?#{principal.company.id} AND  o.compJobRecrOff.id =:#{#compJobRecrOffId}"
    )
    @Nullable
    public Page<SpPtlCompJobRecrOffPers> findByCompJobRecrOffId(Pageable pageable, @Param("compJobRecrOffId") Long compJobRecrOffId);

    @Query(
            value = "SELECT o FROM SpPtlCompJobRecrOffPers o "
                    + "WHERE  o.compJobRecrOff.companyId =?#{principal.company.id} AND o.id =:#{#id}"
    )
    @Nullable
    public SpPtlCompJobRecrOffPers findOne(@Param("id") Long id);

    public SpPtlCompJobRecrOffPers save(SpPtlCompJobRecrOffPers entity);

    /**
     * ****************************************************************************
     * ******************************************************************************
     */

    //@Override
    //@RestResource(exported = false)
    //public OBJ_TYPE findOne(ID_TYPE id);

    @Override
    @RestResource(exported = false)
    public Page<SpPtlCompJobRecrOffPers> findAll(Pageable pageable);

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
