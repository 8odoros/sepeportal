package sepe.repository.general;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import sepe.domain.general.TKalPe;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by root on 4/2/2015.
 */
/// http://localhost:7001/TKalPe
/// http://localhost:7001/TKalPe/search/findByPerifCode?perifCode=07%20
@RepositoryRestResource(collectionResourceRel = "TKalPe", path = "/TKalPe")
public interface TKalPeRestRepo extends CrudRepository<TKalPe, String> {


    @Query(
            value = "SELECT o from TKalPe o",
            nativeQuery = false
    )
    @Nullable
    public List<TKalPe> findAll();

    @Query(
            value = "SELECT o from TKalPe o WHERE o.perifCode =:#{#perifCode}",
            nativeQuery = false
    )
    @Nullable
    public List<TKalPe> findByPerifCode(@Param("perifCode") String perifCode);


    @Query(
            value = "SELECT o from TKalPe o WHERE o.enotCode=:#{#enotCode}",
            nativeQuery = false
    )
    @Nullable
    public TKalPe findOne(@Param("enotCode") String enotCode);

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
    //public List<TKalPe> save(List<TKalPe> entities);

    @Override
    @RestResource(exported = false)
    public TKalPe save(TKalPe entity);
}