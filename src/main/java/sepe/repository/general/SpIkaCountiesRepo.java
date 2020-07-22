package sepe.repository.general;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import sepe.domain.general.SpIkaCounties;


import javax.annotation.Nullable;
import java.util.List;

//http://localhost:7001/IkaCounties/search/findById?id=ΑΙΤΩ
 @RepositoryRestResource(collectionResourceRel = "IkaCounties", path = "/IkaCounties")
public interface SpIkaCountiesRepo extends CrudRepository<SpIkaCounties, String> {


    @Query(
            value = "SELECT o FROM SpIkaCounties o"
                    + " WHERE o.id =:#{#id}",
            nativeQuery = false
    )
    @Nullable
    public SpIkaCounties findById(@Param("id") String id);

    @Query(
            value = "SELECT o FROM SpIkaCounties o",
            nativeQuery = false
    )
    public List<SpIkaCounties> findAll();
    /*******************************************************************************
     ********************************************************************************/

    @Override
    @RestResource(exported = false)
    public SpIkaCounties findOne(String id);


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
    public SpIkaCounties save(SpIkaCounties entity);

}
