package sepe.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sepe.domain.TUser;
import sepe.domain.employee.TEmployeeComplaint;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

// http://localhost:7001/tUsers
@Transactional( propagation = Propagation.REQUIRES_NEW )
@RepositoryRestResource
public interface UserRepository extends CrudRepository<TUser, Long> {

    //@Nullable
    //public TUser findByEmailEquals(@Nonnull String email);

    @Query(
            value = "SELECT o FROM TUser o "
            ,nativeQuery = false
    )
    @Nullable
    //public List<TEmployeeComplaint> findAll();
    public Page<TUser> findAll(Pageable pageable);

    /*******************************************************************************
     ********************************************************************************/

    @RestResource(exported = false)
    public TUser findByEmailEquals(@Nonnull String email);

    @RestResource(exported = false)
    public TUser findByUsernameEquals(@Nonnull String username);

    @Override
    @RestResource(exported = false)
    public TUser findOne(Long id);

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
    public TUser save(TUser entity);

}
