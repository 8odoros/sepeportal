package sepe.repository.general;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import sepe.domain.general.SpRtDegreeType;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by Nikos on 4/4/2015.
 */
//http://localhost:7001/degreeType/search/findById?id=1
//http://localhost:7001/degreeType
@RepositoryRestResource(collectionResourceRel = "degreeType", path = "/degreeType")
public interface SpRtDegreeTypeRepo extends CrudRepository<SpRtDegreeType, Long> {


    @Query(
            value = "SELECT o FROM SpRtDegreeType o"
                    + " WHERE o.id =:#{#id}",
            nativeQuery = false
    )
    @Nullable
    public SpRtDegreeType findById(@Param("id") Long id);

    @Query(
            value = "SELECT o FROM SpRtDegreeType o",
            nativeQuery = false
    )
    public List<SpRtDegreeType> findAll();
    /*******************************************************************************
     ********************************************************************************/

    @Override
    @RestResource(exported = false)
    public SpRtDegreeType findOne(Long id);


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
    public SpRtDegreeType save(SpRtDegreeType entity);

}
