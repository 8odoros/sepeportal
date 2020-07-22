package sepe.repository.technician;

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
import sepe.domain.doctor.SpPtlDoctorRegrequest;
import sepe.domain.technician.SpPtlVCompTaAnnDiary;
import sepe.domain.technician.SpPtlVTechnicianRegrequest;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by dimitrisf on 29/1/2019.
 */
// http://localhost:7001/technicianRegrequest
@Transactional(propagation = Propagation.REQUIRES_NEW)
@RepositoryRestResource(collectionResourceRel = "vTechnicianRegrequest", path = "/vTechnicianRegrequest")
public interface SpPtlVTechnicianRegrequestRepo extends PagingAndSortingRepository<SpPtlVTechnicianRegrequest, Long> {
    @Query(
            value = "SELECT o FROM SpPtlVTechnicianRegrequest o "
                    + "WHERE  o.userId =?#{principal.userId}"
            // ,nativeQuery = true
    )
    @Nullable
    public Page<SpPtlVTechnicianRegrequest> findAll(Pageable pageable);

    public SpPtlVTechnicianRegrequest findOne(Long id);

    public SpPtlVTechnicianRegrequest save(SpPtlVTechnicianRegrequest entity);

    @Modifying
    @Transactional
    @Query(
            value = "DELETE FROM SpPtlVTechnicianRegrequest o "
                    + "WHERE  o.userId =?#{principal.userId} AND o.id =:#{#id}"
            // ,nativeQuery = true
    )
    public void delete(@Param("id") Long id);

    /*
    * This is called from a company user through the "findTaByAfm" controller
    */
    @Query(
            value = "SELECT o FROM SpPtlVTechnicianRegrequest o WHERE o.afm =:#{#afm} AND o.reqStatus=6  AND o.papTaStatus = 1 ORDER BY o.protDate DESC"
    )
    @Nullable
    public List<SpPtlVTechnicianRegrequest> findByAfm(@Param("afm") String afm);

    @Query(
            value = "SELECT o FROM SpPtlVTechnicianRegrequest o WHERE o.userId =:#{principal.userId} AND o.reqStatus=6 ORDER BY o.protDate DESC"
    )
    @Nullable
    public List<SpPtlVTechnicianRegrequest> findByUserId(@Param("userId") String userId);

    @Query(
            value = "SELECT o FROM SpPtlVTechnicianRegrequest o WHERE o.userId =:#{principal.userId} ORDER BY o.protDate DESC"
    )
    @Nullable
    public List<SpPtlVTechnicianRegrequest> findByUserId2(@Param("userId") String userId);

    /*
    * This is called from a company user through the "findTaByAfm" controller
    */
    @Query(
            value = "SELECT o FROM SpPtlVTechnicianRegrequest o WHERE o.userId =?#{principal.userId} AND o.reqStatus =:#{#reqStatus} AND o.subStatus =:#{#subStatus} AND o.protNo is not null"
    )
    @Nullable
    public List<SpPtlVTechnicianRegrequest> findByReqStatus(@Param("reqStatus") Integer reqStatus, @Param("subStatus") Integer subStatus);

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