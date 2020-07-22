package sepe.repository.company;

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
import sepe.domain.company.SpPtlCompTaSann;

import javax.annotation.Nullable;

/**
 * Created by Nikos on 6/13/2015.
 */
// http://localhost:7001/compTaSann
// http://localhost:7001/compTaSann/search/findByPtlBranchId?branchId=1261
// http://localhost:7001/compTaSann/search/countIeAnn?ptlBranchId=1348&startDate=25%2f5%2f2015
// http://localhost:7001/compTaSann/search/findCategIeAnn?ptlBranchId=1348
@Transactional(propagation = Propagation.REQUIRES_NEW)
@RepositoryRestResource(collectionResourceRel = "compTaSann", path = "/compTaSann")
public interface SpPtlCompTaSannRepo extends PagingAndSortingRepository<SpPtlCompTaSann, Long> {
    @Query(
            value = "SELECT o FROM SpPtlCompTaSann o "
                    + "WHERE  o.companyId =?#{principal.company.id}"
    )
    @Nullable
    public Page<SpPtlCompTaSann> findAll(Pageable pageable);

    @Query(
            value = "SELECT o FROM SpPtlCompTaSann o "
                    + "WHERE o.id =:#{#id}" /* TODO: privilages [called by ie controler */
    )
    @Nullable
    public SpPtlCompTaSann findOne(@Param("id") Long id);

    /*
    * Company checks if date range of new taSAnn conflicts with older ones.
    * 0 means no conflict found.
    */
    @Query(
            value = "SELECT count(*)"
                    + " FROM SP_PTL_COMP_TA_SANN"
                    + " WHERE ((TA_SANN_STATUS=0 AND REQ_STATUS=5) OR (TA_SANN_STATUS=1 AND REQ_STATUS=6))"
                    + " AND COMP_SHIP_ID= :#{#compShipId}"
                    + " AND DATE_END > TO_DATE( :#{#startDate} ,'DD/MM/YYYY')"
                    + " AND COMPANY_ID =?#{principal.company.id}"
            ,nativeQuery = true
    )
    @Nullable
    public Long countTaSann(@Param("compShipId") Long compShipId, @Param("startDate") String startDate);


    @Query(
            value = "SELECT o FROM SpPtlCompTaSann o"
                    + " WHERE o.taSannStatus =:#{#taSannStatus} AND o.reqStatus =:#{#reqStatus}"
            ,nativeQuery = false
    )
    @Nullable
    public Page<SpPtlCompTaSann> findTaByCompShipId(Pageable pageable, @Param("taSannStatus") Integer taSannStatus, @Param("reqStatus") Integer reqStatus);


    /*
    *
    */
    @Query(
            value = "SELECT count(*)"
                    + " FROM SP_PTL_COMP_TA_SANN"
                    + " WHERE ((TA_SANN_STATUS=0 AND REQ_STATUS=5) OR (TA_SANN_STATUS=1 AND REQ_STATUS=6))"
                    + " AND TO_DATE(DATE_END ,'DD/MM/YYYY')> TO_DATE(SYSDATE ,'DD/MM/YYYY')"
                    + " AND COMP_SHIP_ID= :#{#compShipId}"
                    + " AND COMPANY_ID =?#{principal.company.id}"
            ,nativeQuery = true
    )
    @Nullable
    public Long findCategTaSann(@Param("compShipId") Long compShipId);


    /*
    * Called by IE [Controller cCompIeAnnRespond]
     */
    @Query(
            value = "SELECT o FROM SpPtlCompTaSann o "
                    + " WHERE o.id =:#{#id}" /* TODO: enable access to IE (update accepted/rejected status)+ COMPANY */
    )
    @Nullable
    public SpPtlCompTaSann taFindOne(@Param("id") Long id);


    @Query(
            value = "SELECT o FROM SpPtlCompTaSann o "
                    + "WHERE  o.companyId =?#{principal.company.id} AND o.compShipId =:#{#compShipId}"
    )
    @Nullable
    public Page<SpPtlCompTaSann> findByCompShipId(Pageable pageable, @Param("compShipId") Long compShipId);


    @Modifying
    @Transactional
    @Query(
            value = "DELETE FROM SpPtlCompTaSann o "
                    + "WHERE  o.companyId =?#{principal.company.id} AND o.id =:#{#id}"
    )
    @Nullable
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

    @Override
    @RestResource(exported = true)
    public SpPtlCompTaSann save(SpPtlCompTaSann entity);
}