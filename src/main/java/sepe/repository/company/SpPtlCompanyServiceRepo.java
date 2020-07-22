package sepe.repository.company;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import sepe.domain.company.SpPtlCompanyService;
import sepe.domain.company.TCompanyUserPrivilages;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by Evangelos on 5/5/2015.
 */


//http://localhost:7001/SpPtCompanyServices
@RepositoryRestResource(collectionResourceRel = "SpPtlCompanyServices", path = "/SpPtlCompanyServices")
public interface SpPtlCompanyServiceRepo extends CrudRepository<SpPtlCompanyService, Long> {

    @Query(
            value = "SELECT a.description FROM SpPtlCompanyService a "
                    + "WHERE  a.id IN (:#{#serviceIds})"
    )
    @Nullable
    public List<String> findByCompanyUser(@Param("serviceIds") String serviceIds);


    @Override
    @RestResource(exported = true)
    public SpPtlCompanyService findOne(Long id);

    @Override
    @RestResource(exported = true)
     public List<SpPtlCompanyService> findAll();

    //@Override
    //@RestResource(exported = false)
    // public List<OBJ_TYPE> findAll();

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

    @Override
    @RestResource(exported = false)
    public SpPtlCompanyService save(SpPtlCompanyService entity);


}