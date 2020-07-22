package sepe.repository.general;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import sepe.domain.general.TRtStakLevel2;
import sepe.domain.general.TRtStakLevel3;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by Nikolas on 2/24/2015.
 */


//http://localhost:7001/RtStakLevel3/search/findLevel3byId?id=012
//http://localhost:7001/RtStakLevel3/search/findLevel3?level2=03
@RepositoryRestResource(collectionResourceRel = "RtStakLevel3", path = "/RtStakLevel3")
public interface RtStakLevel3 extends CrudRepository<TRtStakLevel3, String> {

    @Query(
            value = "SELECT o from TRtStakLevel3 o"
                    + " WHERE o.spRtstackLevel3 = :#{#id}",
            nativeQuery = false
    )
    @Nullable
    public List<TRtStakLevel3> findLevel3byId(@Param("id") String id);

    @Query(
            value = "SELECT o from TRtStakLevel3 o"
                    + " WHERE o.spRtstackLevel2 = :#{#level2}",
            nativeQuery = false
    )
    @Nullable
    public List<TRtStakLevel3> findLevel3(@Param("level2") String level2);
    /*******************************************************************************
     ********************************************************************************/

    @Override
    @RestResource(exported = false)
    public TRtStakLevel3 findOne(String id);

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
    public TRtStakLevel3 save(TRtStakLevel3 entity);


}