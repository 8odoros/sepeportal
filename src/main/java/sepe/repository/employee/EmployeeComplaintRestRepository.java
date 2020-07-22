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
import sepe.domain.employee.TEmployeeComplaint;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;


// http://localhost:7001/tEmployeeComplaints
// http://localhost:7001/tEmployeeComplaints/search/findAll2?reqStatus=1
@Transactional( propagation = Propagation.REQUIRES_NEW )
@RepositoryRestResource
public interface EmployeeComplaintRestRepository extends PagingAndSortingRepository<TEmployeeComplaint, Long> {

    @Query(
            value = "SELECT o FROM TEmployeeComplaint o "
            + "WHERE  o.userId =?#{principal.userId}"
            ,nativeQuery = false
    )
    @Nullable
    //public List<TEmployeeComplaint> findAll();
    public Page<TEmployeeComplaint> findAll(Pageable pageable);

    @Query(
            value = "SELECT o FROM TEmployeeComplaint o "
                    + " WHERE  o.userId =?#{principal.userId}"
                    + " AND o.id = ?1"
    )
    @Nullable
    public TEmployeeComplaint findOne(Long id);

/*
    @Query(
            value = "SELECT o FROM TEmployeeComplaint o "
                    + "WHERE  o.userId =?#{principal.userId} and o.reqStatus=:#{#reqStatus}"
            ,nativeQuery = false
    )
    @Nullable
    public Page<TEmployeeComplaint> findAll2(Pageable pageable, @Param("reqStatus") Integer reqStatus);

    @Query(
            value = "SELECT * FROM SP_PTL_EMPLOYEE_COMPLAINT "
                    + "WHERE user_id =?#{principal.userId} and req_status=:#{#reqStatus}"
             ,nativeQuery = false
    )
    @Nullable
    public List<TEmployeeComplaint> findAll3( @Param("reqStatus") Integer reqStatus);
*/

    @Nullable
    public TEmployeeComplaint save(TEmployeeComplaint entity);


    @Nullable
    public TEmployeeComplaint findById(@Nonnull Long Id);


    @Modifying
    @Transactional
    @Query(
            value = "DELETE FROM TEmployeeComplaint o "
                    + "WHERE o.userId =?#{principal.userId} AND o.id =:#{#id}"
                    + " AND o.subStatus < 2"
    )
    @Nullable
    public void delete(@Param("id") Long id);
    /*******************************************************************************
     ********************************************************************************/

   // @Override
   // @RestResource(exported = false)
   // public TEmployeeComplaint findOne(Long id);

    //@Override
    //@RestResource(exported = false)
    //public Page<OBJ_TYPE> findAll(Pageable pageable);

    @Override
    @RestResource(exported = false)
    public long count();

   // @Override
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
    //public TEmployeeComplaint save(TEmployeeComplaint entity);
}
