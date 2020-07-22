package sepe.repository.general;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import sepe.domain.general.TReqStatus;

import javax.annotation.Nullable;
import java.util.List;

//http://localhost:7001/TReqStatus/search/findById?id=1
 @RepositoryRestResource(collectionResourceRel = "TReqStatus", path = "/TReqStatus")
public interface TReqStatusRepo extends CrudRepository<TReqStatus, Long> {


    @Query(
            value = "SELECT o FROM TReqStatus o"
                    + " WHERE o.id =:#{#id}",
            nativeQuery = false
    )
    @Nullable
    public TReqStatus findById(@Param("id") Long id);

    /*******************************************************************************
     ********************************************************************************/

    @Override
    @RestResource(exported = false)
    public TReqStatus findOne(Long id);

    @Override
    @RestResource
    public List<TReqStatus> findAll();

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
    public TReqStatus save(TReqStatus entity);

}
