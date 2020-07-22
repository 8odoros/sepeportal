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
import sepe.domain.company.SpPtlCompShip;


import javax.annotation.Nullable;
import java.util.List;
import java.util.Set;

/**
 * Created by Nikos on 5/16/2015.
 */
// http://localhost:7001/compShip
// http://localhost:7001/compShip/search/findTaAnnounced
// http://localhost:7001/compShip/search/findByTaRegRequestId?taRegRequestId=499
@Transactional(propagation = Propagation.REQUIRES_NEW)
@RepositoryRestResource(collectionResourceRel = "compShip", path = "/compShip")
public interface SpPtlCompShipRepo extends PagingAndSortingRepository<SpPtlCompShip, Long> {
    @Query(
            value = "SELECT o FROM SpPtlCompShip o "
                    + "WHERE o.companyId =?#{principal.company.id}"
    )
    @Nullable
    public Page<SpPtlCompShip> findAll(Pageable pageable);

    @Query(
            value = "SELECT o FROM SpPtlCompShip o "
                    + "WHERE o.companyId =?#{principal.company.id} AND o.id =:#{#id}"
    )
    @Nullable
    public SpPtlCompShip findOne(@Param("id") Long id);

    @Query(
            value = "SELECT o FROM SpPtlCompShip o "
                    + "WHERE o.companyId =:#{#companyId} AND o.id =:#{#id}"
    )
    @Nullable
    public SpPtlCompShip findByCompanyId(@Param("companyId") Long companyId, @Param("id") Long id);

    @Query(
            value = "SELECT o FROM SpPtlCompShip o "
                    + "WHERE o.companyId =:#{#companyId}"
    )
    @Nullable
    public Set<SpPtlCompShip> findAllByCompanyId(@Param("companyId") Long companyId);

    public SpPtlCompShip save(SpPtlCompShip entity);

    @Modifying
    @Transactional
    @Query(
            value = "DELETE FROM SpPtlCompShip o "
                    + "WHERE  o.companyId =?#{principal.company.id} AND o.id =:#{#id}"
    )
    @Nullable
    public void delete(@Param("id") Long id);

    /*
    * For company: Find branches with active taAnn
     */
    @Query(
            /*
            value = "SELECT *" +
                    " FROM SP_PTL_COMP_PTL_BRANCH" +
                    " WHERE ID IN" +
                    " (SELECT COMP_PTL_BRANCH_ID" +
                    " FROM SP_PTL_COMP_IE_ANN" +
                    " WHERE COMPANY_ID =?#{principal.company.id} AND IE_ANN_STATUS > 0)" // TODO: Fix status code
            ,nativeQuery = true
            */
            value = "SELECT o" +
                    " FROM SpPtlCompShip o" +
                    " WHERE o.id IN" +
                    " (SELECT a.compShipId" +
                    " FROM SpPtlCompTaSann a" +
                    " WHERE a.companyId = ?#{principal.company.id} and a.taSannStatus > 0)" // To do: privilages?
            ,nativeQuery = false
    )
    @Nullable
    public List<SpPtlCompShip> findTaAnnounced();

    /*
    * For TA: Find branches which match TA's regRequest
    */
    @Query(
            /*
            value = "SELECT *" +
                    " FROM SP_PTL_COMP_PTL_BRANCH" +
                    " WHERE ID IN" +
                    " (SELECT COMP_PTL_BRANCH_ID" +
                    " FROM SP_PTL_COMP_IE_ANN" +
                    " WHERE DOCTOR_REGREQUEST_ID =:#{#ieRegRequestId})" // To do: privilages?
                    ,nativeQuery = true
            */
            value = "SELECT o" +
                    " FROM SpPtlCompShip o" +
                    " WHERE o.id IN" +
                    " (SELECT a.compShipId" +
                    " FROM SpPtlCompTaSann a" +
                    " WHERE a.technicianRegrequestId =:#{#taRegRequestId})" // To do: privilages?
            ,nativeQuery = false
    )
    @Nullable
    public List<SpPtlCompShip> findByTaRegRequestId(@Param("taRegRequestId") Long taRegRequestId);
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