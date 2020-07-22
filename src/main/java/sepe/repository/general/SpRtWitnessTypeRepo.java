package sepe.repository.general;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import sepe.domain.general.SpRtWitnessType;


import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by root on 29/4/2015.
 */
//http://localhost:7001/witnessType/search/findById?id=1
//http://localhost:7001/witnessType
@RepositoryRestResource(collectionResourceRel = "witnessType", path = "/witnessType")
public interface SpRtWitnessTypeRepo extends CrudRepository<SpRtWitnessType, String> {

    @Query(
            value = "SELECT o FROM SpRtWitnessType o"
                    + " WHERE o.id =:#{#id}",
            nativeQuery = false
    )
    @Nullable
    public SpRtWitnessType findById(@Param("id") String id);

    @Query(
            value = "SELECT o FROM SpRtWitnessType o",
            nativeQuery = false
    )
    public List<SpRtWitnessType> findAll();
    /*******************************************************************************
     ********************************************************************************/

    @Override
    @RestResource(exported = false)
    public SpRtWitnessType findOne(String id);


    @Override
    @RestResource(exported = false)
    public long count();

    @Override
    @RestResource(exported = false)
    public void delete(String id);

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
    public boolean exists(String id);

//@Override
//@RestResource(exported = false)
//public List<OBJ_TYPE> findAll(List<ID_TYPE> ids);

//@Override
//@RestResource(exported = false)
//public List<OBJ_TYPE> save(List<OBJ_TYPE> entities);

    @Override
    @RestResource(exported = false)
    public SpRtWitnessType save(SpRtWitnessType entity);

}
