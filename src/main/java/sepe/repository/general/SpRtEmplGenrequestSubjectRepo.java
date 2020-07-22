package sepe.repository.general;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import sepe.domain.general.SpRtEmplGenrequestSubject;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by Nikolas on 3/16/2015.
 */
//http://localhost:7001/emplGenrequestSubject/search/findOne?id=1
//http://localhost:7001/emplGenrequestSubject
@RepositoryRestResource(collectionResourceRel = "emplGenrequestSubject", path = "/emplGenrequestSubject")
public interface SpRtEmplGenrequestSubjectRepo extends CrudRepository<SpRtEmplGenrequestSubject, Long> {


    @Query(
            value = "SELECT o from SpRtEmplGenrequestSubject o",
            nativeQuery = false
    )
    @Nullable
    public List<SpRtEmplGenrequestSubject> findAll();


    @Query(
            value = "SELECT o from SpRtEmplGenrequestSubject o"
                    + " WHERE  o.id =:#{#id} ",
            nativeQuery = false
    )
    @Nullable
    public SpRtEmplGenrequestSubject findOne(@Param("id") Long id);

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
    public SpRtEmplGenrequestSubject save(SpRtEmplGenrequestSubject entity);

}