package sepe.repository.general;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import sepe.domain.general.TKalP;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by root on 4/2/2015.
 */
//http://localhost:7001/TKalP
@RepositoryRestResource(collectionResourceRel = "TKalP", path = "/TKalP")
public interface TKalPRestRepo extends CrudRepository<TKalP, String> {


    @Query(
            value = "SELECT o from TKalP o",
            nativeQuery = false
    )
    @Nullable
    public List<TKalP> findAll();

    /*******************************************************************************
    ********************************************************************************/
    //@Override
    //@RestResource(exported = false)
    //public List<TKalP> findAll();

    @Override
    @RestResource(exported = false)
    public TKalP findOne(String id);

    @Override
    @RestResource(exported = false)
    public long count();

    @Override
    @RestResource(exported = false)
    public void delete(String id);

    //@Override
    //@RestResource(exported = false)
    //public void delete(List<TKalP> entities);

    //@Override
    //@RestResource(exported = false)
    //public void delete(TKalP entity);

    @Override
    @RestResource(exported = false)
    public void deleteAll();

    @Override
    @RestResource(exported = false)
    public boolean exists(String id);

    //@Override
   //@RestResource(exported = false)
   //List<TKalP> findAll(List<String> ids);

    //@Override
   //@RestResource(exported = false)
   //List<TKalP> save(List<TKalP> entities);

    @Override
    @RestResource(exported = false)
    TKalP save(TKalP entity);
    /*******************************************************************************
     ********************************************************************************/
}