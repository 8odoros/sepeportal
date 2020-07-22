package sepe.repository.general;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import sepe.domain.general.TKalK;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
/**
 * Created by root on 4/2/2015.
 */
/// http://localhost:7001/TKalK
/// http://localhost:7001/TKalK/search/findByDimosCode?dimosCode=9134
@RepositoryRestResource(collectionResourceRel = "TKalK", path = "/TKalK")
public interface TKalKRestRepo extends CrudRepository<TKalK, String> {

    @Query(
            value = "SELECT o from TKalK o",
            nativeQuery = false
    )
    @Nullable
    public List<TKalK> findAll();

    @Query(
            value = "SELECT o from TKalK o"
                    + " WHERE o.dCode =:#{#dimosCode}",
            nativeQuery = false
    )
    @Nullable
    public List<TKalK> findByDimosCode(
            @Param("dimosCode") String dimosCode
    );
    

    @Query(
            value = "SELECT o from TKalK o"
                    + " WHERE o.koinCode = ?1",
            nativeQuery = false
    )
    @Nullable
    public TKalK findOne(@Nonnull String koinCode);

    /*******************************************************************************
     ********************************************************************************/

    //@Override
    //@RestResource(exported = false)
    //public OBJ_TYPE findOne(ID_TYPE id);

    //@Override
    //@RestResource(exported = false)
    //public List<OBJ_TYPE> findAll();

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
    public TKalK save(TKalK entity);

}