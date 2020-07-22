package sepe.repository.general;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import sepe.domain.general.TRtStakLevel1;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by Nikolas on 2/24/2015.
 */

//http://localhost:7001/RtStakLevel1/search/findLevel1byId?id=%CE%9C
//http://localhost:7001/RtStakLevel1
@RepositoryRestResource(collectionResourceRel = "RtStakLevel1", path = "/RtStakLevel1")
public interface RtStakLevel1 extends CrudRepository<TRtStakLevel1, String> {


    @Query(
            value = "SELECT o from TRtStakLevel1 o",
            nativeQuery = false
    )
    @Nullable
    public List<TRtStakLevel1> findAll();

    @Query(
            value = "SELECT o from TRtStakLevel1 o"
                    + " WHERE o.spRtstackLevel1 = :#{#id}",
            nativeQuery = false
    )
    @Nullable
    public List<TRtStakLevel1> findLevel1byId(@Param("id") String id);


    /*******************************************************************************
     ********************************************************************************/

    @Override
    @RestResource(exported = false)
    public TRtStakLevel1 findOne(String id);

   //@Override
   //@RestResource(exported = false)
   // public List<OBJ_TYPE> findAll();

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
    public TRtStakLevel1 save(TRtStakLevel1 entity);


}