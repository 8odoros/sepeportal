package sepe.repository.general;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import sepe.domain.general.TRtStakLevel4;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by Nikolas on 2/24/2015.
 */



//http://localhost:7001/RtStakLevel4/search/findLevel4byId?id=0123
//http://localhost:7001/RtStakLevel4/search/findLevel4?level3=031
@RepositoryRestResource(collectionResourceRel = "RtStakLevel4", path = "/RtStakLevel4")
public interface RtStakLevel4 extends CrudRepository<TRtStakLevel4, String> {

    @Query(
            value = "SELECT o from TRtStakLevel4 o"
                    + " WHERE o.spRtstackLevel4 = :#{#id}",
            nativeQuery = false
    )
    @Nullable
    public List<TRtStakLevel4> findLevel4byId(@Param("id") String id);

    @Query(
            value = "SELECT o from TRtStakLevel4 o"
                    + " WHERE o.spRtstackLevel3 = :#{#level3}",
            nativeQuery = false
    )
    @Nullable
    public List<TRtStakLevel4> findLevel4(@Param("level3") String level3);
    /*******************************************************************************
     ********************************************************************************/

    @Override
    @RestResource(exported = false)
    public TRtStakLevel4 findOne(String id);

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
    public TRtStakLevel4 save(TRtStakLevel4 entity);


}