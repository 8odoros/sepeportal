package sepe.repository.general;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import sepe.domain.general.SpRtAccidentAffectedTime;
import sepe.domain.general.SpRtAccidentAffectedType;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by root on 28/4/2015.
 */
//http://localhost:7001/accidentAffectedType/search/findById?id=1
//http://localhost:7001/accidentAffectedType
@RepositoryRestResource(collectionResourceRel = "accidentAffectedType", path = "/accidentAffectedType")
public interface SpRtAccidentAffectedTypeRepo extends CrudRepository<SpRtAccidentAffectedType, String> {


    @Query(
            value = "SELECT o FROM SpRtAccidentAffectedType o"
                    + " WHERE o.id =:#{#id}",
            nativeQuery = false
    )
    @Nullable
    public SpRtAccidentAffectedType findById(@Param("id") String id);

    @Query(
            value = "SELECT o FROM SpRtAccidentAffectedType o",
            nativeQuery = false
    )
    public List<SpRtAccidentAffectedType> findAll();
    /*******************************************************************************
     ********************************************************************************/

    @Override
    @RestResource(exported = false)
    public SpRtAccidentAffectedType findOne(String id);


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
    public SpRtAccidentAffectedType save(SpRtAccidentAffectedType entity);

}
