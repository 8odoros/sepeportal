package sepe.repository.general;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import sepe.domain.general.TSepeDepartment;

import javax.annotation.Nullable;
import java.util.List;

//http://localhost:7001/TSepeDepartment/search/findById?id=55000
//http://localhost:7001/TSepeDepartment/search/findByKind?id=55000
 @RepositoryRestResource(collectionResourceRel = "TSepeDepartment", path = "/TSepeDepartment")
public interface TSepeDepartmentRepository extends CrudRepository<TSepeDepartment, Long> {


    @Query(
            value = "SELECT o FROM TSepeDepartment o"
                    + " WHERE o.cdId =:#{#cdId}",
            nativeQuery = false
    )
    @Nullable
    public TSepeDepartment findById(@Param("cdId") Long cdId);

    @Query(
            value = "SELECT o FROM TSepeDepartment o"
                    + " WHERE o.cdKind =:#{#cdKind} ORDER BY o.cdCode",
            nativeQuery = false
    )
    @Nullable
    public List<TSepeDepartment> findByKind(@Param("cdKind") String cdKind);

    @Query(
            value = "SELECT o FROM TSepeDepartment o"
                    + " WHERE o.cdKind =:#{#cdKind}"
                    + " AND o.cdStatus = 1 ORDER BY o.cdCode",
            nativeQuery = false
    )
    @Nullable
    public List<TSepeDepartment> findByKindActive(@Param("cdKind") String cdKind);
    /*******************************************************************************
     ********************************************************************************/

    @Override
    @RestResource(exported = false)
    public TSepeDepartment findOne(Long id);

    @Override
    @RestResource(exported = false)
    public List<TSepeDepartment> findAll();

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
    public TSepeDepartment save(TSepeDepartment entity);

}
