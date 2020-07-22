package sepe.repository.general;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import sepe.domain.general.SpRtAccidentAffectedSeverity;
import sepe.domain.general.SpRtAccidentAffectedTime;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by root on 28/4/2015.
 */
//http://localhost:7001/accidentAffectedSeverity/search/findById?id=1
//http://localhost:7001/accidentAffectedSeverity
@RepositoryRestResource(collectionResourceRel = "accidentAffectedSeverity", path = "/accidentAffectedSeverity")
public interface SpRtAccidentAffectedSeverityRepo extends CrudRepository<SpRtAccidentAffectedSeverity, String> {


    @Query(
            value = "SELECT o FROM SpRtAccidentAffectedSeverity o"
                    + " WHERE o.id =:#{#id}",
            nativeQuery = false
    )
    @Nullable
    public SpRtAccidentAffectedSeverity findById(@Param("id") String id);

    @Query(
            value = "SELECT o FROM SpRtAccidentAffectedSeverity o",
            nativeQuery = false
    )
    public List<SpRtAccidentAffectedSeverity> findAll();
    /*******************************************************************************
     ********************************************************************************/

    @Override
    @RestResource(exported = false)
    public SpRtAccidentAffectedSeverity findOne(String id);


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
    public SpRtAccidentAffectedSeverity save(SpRtAccidentAffectedSeverity entity);

}
