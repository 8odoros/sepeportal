package sepe.repository.general;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import sepe.domain.general.SpInspector;


import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by Nikos on 6/14/2015.
 */
//http://localhost:7001/inspector
//http://localhost:7001/inspector/search/findById?id=99
@RepositoryRestResource(collectionResourceRel = "inspector", path = "/inspector")
public interface SpInspectorRepo extends CrudRepository<SpInspector, Long> {


    @Query(
            value = "SELECT o FROM SpInspector o"
                    + " WHERE o.id =:#{#id}",
            nativeQuery = false
    )
    @Nullable
    public SpInspector findById(@Param("id") Integer id);


    /*******************************************************************************
     ********************************************************************************/
    @Override
    @RestResource(exported = false)
    public List<SpInspector> findAll();

    @Override
    @RestResource(exported = false)
    public SpInspector findOne(Long id);


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
    public SpInspector save(SpInspector entity);

}
