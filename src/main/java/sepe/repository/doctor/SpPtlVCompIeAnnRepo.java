package sepe.repository.doctor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import sepe.domain.doctor.SpPtlVCompIeAnn;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by Nikos on 5/21/2015.
 */
// http://localhost:7001/vCompIeAnn/search/ieFindAll?ieAnnStatus=1&reqStatus=5
// http://localhost:7001/portal/vCompIeAnn/search/findAllNotExpired?ieAnnStatus=2&reqStatus=7
@RepositoryRestResource(collectionResourceRel = "vCompIeAnn", path = "/vCompIeAnn")
public interface SpPtlVCompIeAnnRepo extends PagingAndSortingRepository<SpPtlVCompIeAnn, Long> {

    /*
    * IE asks all books
    */
    @Query(
            value = "SELECT o FROM SpPtlVCompIeAnn o"
                    + " WHERE (o.doctorRegrequestUserId = ?#{principal.userId} OR  o.companyId = ?#{principal.userId})" /* TODO return only active */
                    + " AND o.ieAnnStatus =:#{#ieAnnStatus} AND o.reqStatus =:#{#reqStatus}"
            ,nativeQuery = false
    )
    @Nullable
    public Page<SpPtlVCompIeAnn> ieFindAll(Pageable pageable, @Param("ieAnnStatus") Integer ieAnnStatus, @Param("reqStatus") Integer reqStatus);


    @Query(
            value = "SELECT * FROM SP_PTL_V_COMP_IE_ANN"
                    + " WHERE (DOCTOR_REGREQUEST_USER_ID = ?#{principal.userId} OR  COMPANY_ID= ?#{principal.userId})" /* TODO return only active */
                    + " AND IE_ANN_STATUS =:#{#ieAnnStatus} AND REQ_STATUS =:#{#reqStatus}"
                    + " AND TO_DATE(DATE_END ,'DD/MM/YYYY') >= TO_DATE(SYSDATE ,'DD/MM/YYYY')"
            ,nativeQuery = true
    )
    @Nullable
    public List<SpPtlVCompIeAnn> findAllNotExpired( @Param("ieAnnStatus") Integer ieAnnStatus, @Param("reqStatus") Integer reqStatus);


/*
    @Query(
            value = "SELECT * FROM V_DOCTOR_REGREQUEST_SCH"
                    + " WHERE ID =:id"
                    + " AND DOCTOR_REGREQUEST_USER_ID = ?#{principal.userId}"
            ,nativeQuery = true
    )
    @Nullable
    public SpPtlVCompIeAnnBook ieFindOne(@Param("id") Long id);
*/
    /**
     * ****************************************************************************
     * ******************************************************************************
     */

    @Override
    @RestResource(exported = false)
    public SpPtlVCompIeAnn findOne(Long id);

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

    @Override
    @RestResource(exported = false)
    public SpPtlVCompIeAnn save(SpPtlVCompIeAnn entity);
}