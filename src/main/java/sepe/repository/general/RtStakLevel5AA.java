package sepe.repository.general;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import sepe.domain.general.TRtStakLevel5;
import sepe.domain.general.TRtStakLevel5AA;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by Nikolas on 2/24/2015.
 */


//http://localhost:7001/RtStakLevel5AA/search/getAA?param=4638-1
@RepositoryRestResource(collectionResourceRel = "RtStakLevel5AA", path = "/RtStakLevel5AA")
public interface RtStakLevel5AA extends CrudRepository<TRtStakLevel5AA, String> {

    @Query(
            value = "SELECT o.spRtstackAa from TRtStakLevel5AA o"
            + " WHERE o.spRtstackLevel5 = :#{#param}",
            nativeQuery = false
    )
    @Nullable
    public Long getAA( @Param("param")String param);


    /*******************************************************************************
     ********************************************************************************/

    @Override
    @RestResource(exported = false)
    public TRtStakLevel5AA findOne(String id);

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
    public TRtStakLevel5AA save(TRtStakLevel5AA entity);


}