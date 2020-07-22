package sepe.repository.doctor;

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
import sepe.domain.doctor.SpPtlDoctorSpecialList;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by dimitrisf on 20/2/2020.
 */
// http://localhost:7001/doctorRegrequest
// http://localhost:7001/doctorRegrequest/search/findByAfm?afm=0123456789
@Transactional(propagation = Propagation.REQUIRES_NEW)
@RepositoryRestResource(collectionResourceRel = "doctorSpecialList", path = "/doctorSpecialList")
public interface SpPtlDoctorSpecialListRepo extends PagingAndSortingRepository<SpPtlDoctorSpecialList, Long> {
    @Query(
            value = "SELECT o FROM SpPtlDoctorRegrequest o "
                    + "WHERE o.userId =?#{principal.userId}"
    )
    @Nullable
    public Page<SpPtlDoctorSpecialList> findAll(Pageable pageable);

    @Query(
            value = "SELECT o FROM SpPtlDoctorSpecialList o WHERE o.afm =:#{#afm}"
    )
    @Nullable
    public List<SpPtlDoctorSpecialList> findByAfm(@Param("afm") String afm);

    public SpPtlDoctorSpecialList findOne(Long id);

    public SpPtlDoctorSpecialList save(SpPtlDoctorSpecialList entity);

    @Modifying
    @Transactional
    @Query(
            value = "DELETE FROM SpPtlDoctorSpecialList o "
                    + "WHERE  o.id =:#{#id}"
    )
    public void delete(@Param("id") Long id);
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
//public SpPtlEmployeeGenrequest save(SpPtlEmployeeGenrequest entity);
}
