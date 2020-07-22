package sepe.repository.general;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import sepe.domain.general.TDoy;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by Nikolas on 2/13/2015.
 */
//http://localhost:7001/TDoy
@RepositoryRestResource(collectionResourceRel = "TDoy", path = "/TDoy")
public interface TDoyRestRepo extends CrudRepository<TDoy, Long> {


    @Query(
            value = "SELECT o from TDoy o",
            nativeQuery = false
    )
    @Nullable
    public List<TDoy> findAll();


    @Query(
            value = "SELECT o from TDoy o"
                    + " WHERE  o.id = ?1 ",
            nativeQuery = false
    )
    @Nullable
    public TDoy findOne(@Nonnull Long id);

    /*******************************************************************************
     ********************************************************************************/

    //@Override
    //@RestResource(exported = false)
    //public TDoy findOne(Long id);

    //@Override
    //@RestResource(exported = false)
   //public List<TDoy> findAll();

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
    public TDoy save(TDoy entity);

}