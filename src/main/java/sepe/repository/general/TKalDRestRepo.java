package sepe.repository.general;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import sepe.domain.general.TKalD;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
/**
 * Created by root on 4/2/2015.
 */
/// http://localhost:7001/TKalD
/// http://localhost:7001/TKalD/search/findByEnotCode?enotCode=0702%20
@RepositoryRestResource(collectionResourceRel = "TKalD", path = "/TKalD")
public interface TKalDRestRepo extends CrudRepository<TKalD, String> {


    @Query(
            value = "select o from TKalD o",
            nativeQuery = false
    )
    @Nullable
    public List<TKalD> findAll();

    @Query(
            value = "select o from TKalD o WHERE o.enotCode=:#{#enotCode}",
            nativeQuery = false
    )
    @Nullable
    public List<TKalD> findByEnotCode(
            @Param("enotCode") String enotCode
    );

    @Query(
            value = "select o from TKalD o WHERE o.dimosCode = :#{#dimosCode}",
            nativeQuery = false
    )
    @Nullable
    public TKalD findOne(@Param("dimosCode") String dimosCode);

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
    public TKalD save(TKalD entity);

}
