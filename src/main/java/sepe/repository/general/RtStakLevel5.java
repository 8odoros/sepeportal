package sepe.repository.general;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import sepe.domain.general.TRtStakLevel5;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by Nikolas on 2/24/2015.
 */


//http://localhost:7001/RtStakLevel5/search/findLevel5byId?id=4638-1
//http://localhost:7001/RtStakLevel5/search/findLevel5?level4=4634
@RepositoryRestResource(collectionResourceRel = "RtStakLevel5", path = "/RtStakLevel5")
public interface RtStakLevel5 extends CrudRepository<TRtStakLevel5, String> {

    @Query(
            value = "SELECT o from TRtStakLevel5 o"
                    + " WHERE o.spRtstackLevel5 = :#{#id}",
            nativeQuery = false
    )
    @Nullable
    public List<TRtStakLevel5> findLevel5byId(@Param("id") String id);

    @Query(
            value = "SELECT o from TRtStakLevel5 o"
                    + " WHERE o.spRtstackLevel4 = :#{#level4}",
            nativeQuery = false
    )
    @Nullable
    public List<TRtStakLevel5> findLevel5(@Param("level4") String level4);
    /*******************************************************************************
     ********************************************************************************/

    @Override
    @RestResource(exported = false)
    public TRtStakLevel5 findOne(String id);

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
    public TRtStakLevel5 save(TRtStakLevel5 entity);


}