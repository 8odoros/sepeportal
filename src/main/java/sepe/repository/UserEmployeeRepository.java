package sepe.repository;

import org.glassfish.jersey.internal.util.Property;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sepe.domain.SpPtlVUserCompany;
import sepe.domain.SpPtlVUserEmployee;
import sepe.domain.employee.TEmployeeComplaint;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.persistence.QueryHint;
import javax.ws.rs.OPTIONS;
import java.util.List;

// http://localhost:7001/SpPtlVUserEmployee
// http://localhost:7001/SpPtlVUserEmployee/search/findAll/
// http://localhost:7001/SpPtlVUserEmployee/search/findUsersByCriteria/
@Transactional( propagation = Propagation.REQUIRES_NEW )
@RepositoryRestResource(collectionResourceRel = "SpPtlVUserEmployee", path = "/SpPtlVUserEmployee")
public interface UserEmployeeRepository extends CrudRepository<SpPtlVUserEmployee, Long> {

    //@Nullable
    //public TUser findByEmailEquals(@Nonnull String email);

    @Query(
            value = "SELECT o FROM SpPtlVUserEmployee o "
            ,nativeQuery = false
    )
    @Nullable
    public List<SpPtlVUserEmployee> findAllUsers();
    //public Page<SpPtlVUserCompany> findAll(Pageable pageable);

    @Query(
            value = "SELECT o FROM SpPtlVUserEmployee o " +
            "WHERE o.username LIKE CONCAT('%' , :#{#username} , '%') " +
                    "AND o.afm LIKE CONCAT('%' , :#{#afm} , '%') " +
                    "AND o.email LIKE CONCAT('%' , :#{#email} , '%')"
            ,nativeQuery = false
    )
    @Nullable
    public Page<SpPtlVUserEmployee> findUsersByCriteria(@Param("username") String username, @Param("afm") String afm, @Param("email") String email, Pageable pageable);

    /*******************************************************************************
     ********************************************************************************/

    @RestResource(exported = false)
    public SpPtlVUserEmployee findByEmailEquals(@Nonnull String email);

    @RestResource(exported = false)
    public SpPtlVUserEmployee findByUsernameEquals(@Nonnull String username);

    @Override
    @RestResource(exported = false)
    public SpPtlVUserEmployee findOne(Long id);

    /*@Override
    @RestResource(exported = false)
    public List<TUser> findAll();*/

    @Override
    @RestResource(exported = false)
    public long count();

    /*@Override
    @RestResource(exported = false)
    public void delete(Long id);*/

//@Override
//@RestResource(exported = false)
//public void delete(List<OBJ_TYPE> entities);

//@Override
//@RestResource(exported = false)
//public void delete(OBJ_TYPE entity);

    /*@Override
    @RestResource(exported = false)
    public void deleteAll();*/

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
    public SpPtlVUserEmployee save(SpPtlVUserEmployee entity);

}
