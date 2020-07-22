package sepe.repository.general;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import sepe.domain.general.SpRtTaBranchSector;
import sepe.domain.general.SpRtTaSpeciality;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by kirikosm on 5/3/2018.
 */

//http://localhost:7001/taBranchSector
//http://localhost:7001/taBranchSector/search/findById?id=99
//http://localhost:7001/taBranchSector/search/findByEducLevel?id=99
@RepositoryRestResource(collectionResourceRel = "taBranchSector", path = "/taBranchSector")
public interface SpRtTaBranchSectorRepo extends CrudRepository<SpRtTaBranchSector, Long> {


    @Query(
            value = "SELECT o FROM SpRtTaBranchSector o"
                    + " WHERE o.id =:#{#id}",
            nativeQuery = false
    )
    @Nullable
    public SpRtTaBranchSector findById(@Param("id") Integer id);

    @Query(
            value = "SELECT o FROM SpRtTaBranchSector o"
                    + " WHERE o.spTabrsectCheck =:#{#spTabrsectCheck}",
            nativeQuery = false
    )
    @Nullable
    public List<SpRtTaBranchSector> findBySpTabrsectCheck(@Param("spTabrsectCheck") Long spTabrsectCheck);

    @Query(
            value = "SELECT o FROM SpRtTaBranchSector o order by o.description",
            nativeQuery = false
    )
    public List<SpRtTaBranchSector> findAll();
    /*******************************************************************************
     ********************************************************************************/

    @Override
    @RestResource(exported = false)
    public SpRtTaBranchSector findOne(Long id);


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
    public SpRtTaBranchSector save(SpRtTaBranchSector entity);

}
