package sepe.repository.doctor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sepe.domain.doctor.SpPtlDoctorCounty;

import javax.annotation.Nullable;

/**
 * Created by Nikos on 4/18/2015.
 */
//http://localhost:7001/doctorCounty
@Transactional(propagation = Propagation.REQUIRES_NEW)
@RepositoryRestResource(collectionResourceRel = "doctorCounty", path = "/doctorCounty")
public interface SpPtlDoctorCountyRepo extends PagingAndSortingRepository<SpPtlDoctorCounty, Long> {

    @Query(
            value = "SELECT o FROM SpPtlDoctorCounty o "
                    + "WHERE  o.userId =?#{principal.userId}"
            // ,nativeQuery = true
    )
    @Nullable
    public Page<SpPtlDoctorCounty> findAll(Pageable pageable);

    public SpPtlDoctorCounty findOne(Long id);

    public SpPtlDoctorCounty save(SpPtlDoctorCounty entity);

    /**
     * ****************************************************************************
     * ******************************************************************************
     */

    //@Override
    //@RestResource(exported = false)
    //public SpPtlEmployeeGenrequest findOne(Long id);

    //@Override
    //@RestResource(exported = false)
    //public Page<SpPtlEmployeeGenrequest> findAll(Pageable pageable);
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

//@Override
//@RestResource(exported = false)
//public SpPtlEmployeeGenrequest save(SpPtlEmployeeGenrequest entity);
}