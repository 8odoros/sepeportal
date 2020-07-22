package sepe.repository.general;

/**
 * Created by Nikolas on 1/28/2015.
 */

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import sepe.domain.general.TEmpComplMatter;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by Nikolas on 1/15/2015.
 */

//http://localhost:7001/employeeComplaintMatter/search/findAll_custom
//http://localhost:7001/employeeComplaintMatter
//http://localhost:7001/employeeComplaintMatter/search/findByComplInvolves
@RepositoryRestResource(collectionResourceRel = "employeeComplaintMatter", path = "/employeeComplaintMatter")
public interface EmployeeComplaintMatterRestRepository extends CrudRepository<TEmpComplMatter, Long> {

    @Query(
            value = "SELECT o FROM TEmpComplMatter o",
            nativeQuery = false
    )
    @Nullable
    public List<TEmpComplMatter> findAll();

    @Query(
            value = "SELECT o FROM TEmpComplMatter o"
                    + " WHERE  o.id = ?1 ",
            nativeQuery = false
    )
    @Nullable
    public TEmpComplMatter findOne(@Nonnull Long id);

    @Query(
            value = "SELECT o FROM TEmpComplMatter o"
                    + " WHERE o.spCmInvolves =:#{#spCmInvolves}",
            nativeQuery = false
    )
    @Nullable
    public List<TEmpComplMatter> findByComplInvolves(@Param("spCmInvolves") Integer spCmInvolves);

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
    public TEmpComplMatter save(TEmpComplMatter entity);

}