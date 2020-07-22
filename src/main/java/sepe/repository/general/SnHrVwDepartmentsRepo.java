package sepe.repository.general;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import sepe.domain.general.SnHrVwDepartments;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by kirikosm on 9/12/2016.
 */
//http://localhost:7001/snHrVwDepartments/search/findById?id=1
//http://localhost:7001/snHrVwDepartments
@RepositoryRestResource(collectionResourceRel = "snHrVwDepartments", path = "/snHrVwDepartments")
public interface SnHrVwDepartmentsRepo extends CrudRepository<SnHrVwDepartments, String> {


    @Query(
            value = "SELECT o FROM SnHrVwDepartments o"
                    + " WHERE o.hrDeptCd =:#{#hrDeptCd}",
            nativeQuery = false
    )
    @Nullable
    public SnHrVwDepartments findById(@Param("hrDeptCd") String hrDeptCd);

    @Query(
            value = "SELECT o FROM SnHrVwDepartments o",
            nativeQuery = false
    )
    public List<SnHrVwDepartments> findAll();
    /*******************************************************************************
     ********************************************************************************/

    @Override
    @RestResource(exported = false)
    public SnHrVwDepartments findOne(String id);


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
    public SnHrVwDepartments save(SnHrVwDepartments entity);

}
