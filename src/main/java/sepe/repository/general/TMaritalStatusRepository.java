package sepe.repository.general;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import sepe.domain.general.TMaritalStatus;

import javax.annotation.Nullable;
import java.util.List;

//http://localhost:7001/TMaritalStatus
//http://localhost:7001/TMaritalStatus/search/findById?id=1
 @RepositoryRestResource(collectionResourceRel = "TMaritalStatus", path = "/TMaritalStatus")
public interface TMaritalStatusRepository extends CrudRepository<TMaritalStatus, Long> {


    @Query(
            value = "SELECT o FROM TMaritalStatus o"
                    + " WHERE o.id =:#{#id}",
            nativeQuery = false
    )
    @Nullable
    public TMaritalStatus findById(@Param("id") Long id);

    @Query(
            value = "SELECT o FROM TMaritalStatus o",
            nativeQuery = false
    )
    public List<TMaritalStatus> findAll();
    /*******************************************************************************
     ********************************************************************************/

    @Override
    @RestResource(exported = false)
    public TMaritalStatus findOne(Long id);


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
    public TMaritalStatus save(TMaritalStatus entity);

}
