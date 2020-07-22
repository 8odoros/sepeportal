package sepe.repository.general;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import sepe.domain.general.SpRtAccidentAffectedPosition;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by root on 28/4/2015.
 */
//http://localhost:7001/accidentAffectedPosition/search/findById?id=1
//http://localhost:7001/accidentAffectedPosition
@RepositoryRestResource(collectionResourceRel = "accidentAffectedPosition", path = "/accidentAffectedPosition")
public interface SpRtAccidentAffectedPositionRepo extends CrudRepository<SpRtAccidentAffectedPosition, String> {


    @Query(
            value = "SELECT o FROM SpRtAccidentAffectedPosition o"
                    + " WHERE o.id =:#{#id}",
            nativeQuery = false
    )
    @Nullable
    public SpRtAccidentAffectedPosition findById(@Param("id") String id);

    @Query(
            value = "SELECT o FROM SpRtAccidentAffectedPosition o",
            nativeQuery = false
    )
    public List<SpRtAccidentAffectedPosition> findAll();
    /*******************************************************************************
     ********************************************************************************/

    @Override
    @RestResource(exported = false)
    public SpRtAccidentAffectedPosition findOne(String id);


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
    public SpRtAccidentAffectedPosition save(SpRtAccidentAffectedPosition entity);

}