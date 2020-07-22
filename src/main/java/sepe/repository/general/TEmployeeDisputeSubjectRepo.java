package sepe.repository.general;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import sepe.domain.general.TEmployeeDisputeSubject;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by Nikolas on 2/16/2015.
 */

// http://localhost:7001/tEmployeeDisputeSubjects
@RepositoryRestResource
public interface TEmployeeDisputeSubjectRepo extends CrudRepository<TEmployeeDisputeSubject, Long> {

    @Query(
            value = "SELECT o from TEmployeeDisputeSubject o ORDER BY o.spRlsDetailDesc",
            nativeQuery = false
    )
    @Nullable
    public List<TEmployeeDisputeSubject> findAll();

    /*******************************************************************************
     ********************************************************************************/

    @Override
    @RestResource(exported = false)
    public TEmployeeDisputeSubject findOne(Long id);

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
    public TEmployeeDisputeSubject save(TEmployeeDisputeSubject entity);
}