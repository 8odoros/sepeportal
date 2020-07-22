package sepe.repository.general;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import sepe.domain.general.SpRtNationality;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by root on 21/4/2015.
 */
//http://localhost:7001/nationality
//http://localhost:7001/nationality/search/findById?id=003
@RepositoryRestResource(collectionResourceRel = "nationality", path = "/nationality")
public interface SpRtNationalityRepo extends CrudRepository<SpRtNationality, String> {


    @Query(
            value = "SELECT o FROM SpRtNationality o"
                    + " WHERE o.id =:#{#id}",
            nativeQuery = false
    )
    @Nullable
    public SpRtNationality findById(@Param("id") String id);

    @Query(
            value = "SELECT o FROM SpRtNationality o",
            nativeQuery = false
    )
    public List<SpRtNationality> findAll();

    @Query(
            value = "SELECT o FROM SpRtNationality o"
                    + " WHERE UPPER(o.description) like UPPER(CONCAT('%',:description,'%'))",
            nativeQuery = false
    )
    public List<SpRtNationality> findByDescr(@Param("description") String description);
    /*******************************************************************************
     ********************************************************************************/

    @Override
    @RestResource(exported = false)
    public SpRtNationality findOne(String id);


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
    public SpRtNationality save(SpRtNationality entity);

}