package sepe.repository.general;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import sepe.domain.general.SpRtCooperationType;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by root on 6/4/2015.
 */
//http://localhost:7001/cooperationType/search/findById?id=1
//http://localhost:7001/cooperationType
@RepositoryRestResource(collectionResourceRel = "cooperationType", path = "/cooperationType")
public interface SpRtCooperationTypeRepo extends CrudRepository<SpRtCooperationType, Long> {


    @Query(
            value = "SELECT o FROM SpRtCooperationType o"
                    + " WHERE o.id =:#{#id}",
            nativeQuery = false
    )
    @Nullable
    public SpRtCooperationType findById(@Param("id") Long id);

    @Query(
            value = "SELECT o FROM SpRtCooperationType o",
            nativeQuery = false
    )
    public List<SpRtCooperationType> findAll();
    /*******************************************************************************
     ********************************************************************************/

    @Override
    @RestResource(exported = false)
    public SpRtCooperationType findOne(Long id);


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
    public SpRtCooperationType save(SpRtCooperationType entity);

}
