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
import sepe.domain.SpPtlVUserEmployee;
import sepe.domain.SpPtlVUserSafetyTechnician;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by kirikosm on 14/12/2017.
 */
// http://localhost:7001/SpPtlVUserSafetyTechnician
// http://localhost:7001/SpPtlVUserSafetyTechnician/search/findAll/
// http://localhost:7001/SpPtlVUserSafetyTechnician/search/findUsersByCriteria/
@Transactional( propagation = Propagation.REQUIRES_NEW )
@RepositoryRestResource(collectionResourceRel = "SpPtlVUserSafetyTechnician", path = "/SpPtlVUserSafetyTechnician")
public interface UserSafetyTechnicianRepository extends CrudRepository<SpPtlVUserSafetyTechnician, Long> {

    //@Nullable
    //public TUser findByEmailEquals(@Nonnull String email);

    @Query(
            value = "SELECT o FROM SpPtlVUserSafetyTechnician o "
            ,nativeQuery = false
    )
    @Nullable
    public List<SpPtlVUserSafetyTechnician> findAllUsers();
    //public Page<SpPtlVUserSafetyTechnician> findAll(Pageable pageable);

    @Query(
            value = "SELECT o FROM SpPtlVUserSafetyTechnician o " +
                    "WHERE o.username LIKE CONCAT('%' , :#{#username} , '%') " +
                    "AND o.afm LIKE CONCAT('%' , :#{#afm} , '%') " +
                    "AND o.email LIKE CONCAT('%' , :#{#email} , '%')"
            ,nativeQuery = false
    )
    @Nullable
    public Page<SpPtlVUserSafetyTechnician> findUsersByCriteria(@Param("username") String username, @Param("afm") String afm, @Param("email") String email, Pageable pageable);

    @Query(
            value = "SELECT DISTINCT c FROM SpPtlVUserSafetyTechnician c, SpPtlVCompTaAnnDiary o"
                    + " WHERE o.compTaAnnId =:#{#compTaAnnId}"
                    + " AND o.technicianRegrequestUserId = c.id"
            //,nativeQuery = false
    )
    @Nullable
    public List<SpPtlVUserSafetyTechnician> findByCompTaAnnIdTech(@Param("compTaAnnId") Long compTaAnnId);

    /*******************************************************************************
     ********************************************************************************/

    @RestResource(exported = false)
    public SpPtlVUserSafetyTechnician findByEmailEquals(@Nonnull String email);

    @RestResource(exported = false)
    public SpPtlVUserSafetyTechnician findByUsernameEquals(@Nonnull String username);

    @Override
    @RestResource(exported = false)
    public SpPtlVUserSafetyTechnician findOne(Long id);

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
    public SpPtlVUserSafetyTechnician save(SpPtlVUserSafetyTechnician entity);

}
