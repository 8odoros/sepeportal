package sepe.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
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
import java.util.List;

// http://localhost:7001/spPtlVUserCompany
// http://localhost:7001/SpPtlVUserCompany/search/findAll/
// http://localhost:7001/SpPtlVUserCompany/search/findUsersByCriteria/
@Transactional( propagation = Propagation.REQUIRES_NEW )
@RepositoryRestResource(collectionResourceRel = "SpPtlVUserCompany", path = "/SpPtlVUserCompany")
public interface UserCompanyRepository extends CrudRepository<SpPtlVUserCompany, Long> {

    //@Nullable
    //public TUser findByEmailEquals(@Nonnull String email);

    @Query(
            value = "SELECT o FROM SpPtlVUserCompany o "
            ,nativeQuery = false
    )
    @Nullable
    public List<SpPtlVUserCompany> findAllUsers();
    //public Page<SpPtlVUserCompany> findAll(Pageable pageable);


    @Query(
            value = "SELECT o FROM SpPtlVUserCompany o " +
                    "WHERE o.username LIKE CONCAT('%' , :#{#username} , '%') " +
                    "AND o.afm LIKE CONCAT('%' , :#{#afm} , '%') " +
                    "AND o.ame LIKE CONCAT('%' , :#{#ame} , '%') " +
                    "AND o.email LIKE CONCAT('%' , :#{#email} , '%')"
            ,nativeQuery = false
    )
    @Nullable
    public Page<SpPtlVUserCompany> findUsersByCriteria(@Param("username") String username, @Param("afm") String afm, @Param("ame") String ame, @Param("email") String email, Pageable pageable);

    /*******************************************************************************
     ********************************************************************************/

    @RestResource(exported = false)
    public SpPtlVUserCompany findByEmailEquals(@Nonnull String email);

    @RestResource(exported = false)
    public SpPtlVUserCompany findByUsernameEquals(@Nonnull String username);

    @Override
    @RestResource(exported = false)
    public SpPtlVUserCompany findOne(Long id);

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
    public SpPtlVUserCompany save(SpPtlVUserCompany entity);

}
