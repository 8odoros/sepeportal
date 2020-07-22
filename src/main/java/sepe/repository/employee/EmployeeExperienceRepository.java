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
import sepe.domain.employee.SpPtlEmployeeExperience;

import javax.annotation.Nullable;

// http://localhost:7001/spPtlEmployeeExperiences
@Transactional( propagation = Propagation.REQUIRES_NEW )
@RepositoryRestResource
public interface EmployeeExperienceRepository extends PagingAndSortingRepository<SpPtlEmployeeExperience, Long> {
    @Query(
            value = "SELECT o FROM SpPtlEmployeeExperience o "
                     + "WHERE  o.userId =?#{principal.userId}"
            // ,nativeQuery = true
    )
    @Nullable
    //public List<TEmployeeComplaint> findAll();
    public Page<SpPtlEmployeeExperience> findAll(Pageable pageable);

    @Query(
            value = "SELECT o FROM SpPtlEmployeeExperience o "
                    + " WHERE  o.userId =?#{principal.userId}"
                    + " AND o.id = ?1"
    )
    @Nullable
    public SpPtlEmployeeExperience findOne(Long id);


    public SpPtlEmployeeExperience save(SpPtlEmployeeExperience entity);

    @Modifying
    @Transactional
    @Query(
            value = "DELETE FROM SpPtlEmployeeExperience o "
                    + "WHERE o.userId =?#{principal.userId} AND o.id =:#{#id}"
                    + " AND o.subStatus < 2"
    )
    @Nullable
    public void delete(@Param("id") Long id);
    /*******************************************************************************
     ********************************************************************************/

    //@Override
   // @RestResource(exported = false)
    //public OBJ_TYPE findOne(ID_TYPE id);

    //@Override
    //@RestResource(exported = false)
    //public Page<OBJ_TYPE> findAll(Pageable pageable);

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
    //public OBJ_TYPE save(OBJ_TYPE entity);
}
