package sepe.repository.general;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import sepe.domain.general.SpRtEducLevel;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by Nikos on 4/4/2015.
 */
//http://localhost:7001/educLevel/search/findById?id=1
//http://localhost:7001/educLevel/
@RepositoryRestResource(collectionResourceRel = "educLevel", path = "/educLevel")
public interface SpRtEducLevelRepo extends CrudRepository<SpRtEducLevel, Long> {


    @Query(
            value = "SELECT o FROM SpRtEducLevel o"
                    + " WHERE o.id =:#{#id}",
            nativeQuery = false
    )
    @Nullable
    public SpRtEducLevel findById(@Param("id") Long id);

    @Query(
            value = "SELECT o FROM SpRtEducLevel o WHERE o.id IN (1,2)",
            nativeQuery = false
    )
    public List<SpRtEducLevel> findAll();
    /*******************************************************************************
     ********************************************************************************/

    @Override
    @RestResource(exported = false)
    public SpRtEducLevel findOne(Long id);


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
    public SpRtEducLevel save(SpRtEducLevel entity);

}
