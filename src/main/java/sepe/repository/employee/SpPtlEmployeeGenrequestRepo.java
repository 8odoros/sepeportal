package sepe.repository.employee;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sepe.domain.employee.SpPtlEmployeeGenrequest;

import javax.annotation.Nullable;

/**
 * Created by Nikolas on 3/16/2015.
 */
// http://localhost:7001/employeeGenrequest
@Transactional( propagation = Propagation.REQUIRES_NEW )
@RepositoryRestResource(collectionResourceRel = "employeeGenrequest", path = "/employeeGenrequest")
public interface SpPtlEmployeeGenrequestRepo extends PagingAndSortingRepository<SpPtlEmployeeGenrequest, Long> {
    @Query(
            value = "SELECT o FROM SpPtlEmployeeGenrequest o "
                    + "WHERE  o.userId =?#{principal.userId}"
            // ,nativeQuery = true
    )
    @Nullable
    public Page<SpPtlEmployeeGenrequest> findAll(Pageable pageable);

    @Query(
            value = "SELECT o FROM SpPtlEmployeeGenrequest o "
                    + " WHERE  o.userId =?#{principal.userId}"
                    + " AND o.id = ?1"
    )
    @Nullable
    public SpPtlEmployeeGenrequest findOne(Long id);

    public SpPtlEmployeeGenrequest save(SpPtlEmployeeGenrequest entity);

    @Modifying
    @Transactional
    @Query(
            value = "DELETE FROM SpPtlEmployeeGenrequest o "
                    + "WHERE o.userId =?#{principal.userId} AND o.id =:#{#id}"
                    + " AND o.subStatus < 2"
    )
    @Nullable
    public void delete(@Param("id") Long id);
    /*******************************************************************************
     ********************************************************************************/

    //@Override
    //@RestResource(exported = false)
    //public SpPtlEmployeeGenrequest findOne(Long id);

    //@Override
    //@RestResource(exported = false)
    //public Page<SpPtlEmployeeGenrequest> findAll(Pageable pageable);

    @Override
    @RestResource(exported = false)
    public long count();

    //@Override
    //@RestResource(exported = false)
   //public void delete(Long id);

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

    //@Override
    //@RestResource(exported = false)
    //public SpPtlEmployeeGenrequest save(SpPtlEmployeeGenrequest entity);
}