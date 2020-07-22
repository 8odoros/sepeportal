package sepe.repository.company;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sepe.domain.company.SpPtlCompanyDailyCard;

import javax.annotation.Nullable;
import java.util.Set;

/**
 * Created by Evangelos on 4/18/2015.
 */
// http://localhost:7001/companyDailyCards/search/findByProjectId?projectId=1
@Transactional(propagation = Propagation.REQUIRES_NEW)
@RepositoryRestResource(collectionResourceRel = "companyDailyCards", path = "/companyDailyCards")
public interface CompanyDailyCardtRepository extends PagingAndSortingRepository<SpPtlCompanyDailyCard, Long> {
    @Query(
            value = "SELECT o FROM SpPtlCompanyDailyCard o "
                    + "WHERE  o.spPtlCompanyProjects.companyid =?#{principal.company.id} AND  o.spPtlCompanyProjects.id =:#{#projectId}"
    )
    @Nullable
    public Page<SpPtlCompanyDailyCard> findByProjectId(Pageable pageable, @Param("projectId") Long projectId);

    @Query(
            value = "SELECT o FROM SpPtlCompanyDailyCard o "
                    + "WHERE  o.spPtlCompanyProjects.id =:#{#projectId}"
    )
    @Nullable
    public Set<SpPtlCompanyDailyCard> findAllCardsByProjectId(@Param("projectId") Long projectId);

    @Query(
            value = "SELECT o FROM SpPtlCompanyDailyCard o "
                    + "WHERE o.spPtlCompanyProjects.companyid =?#{principal.company.id} AND o.id =:#{#id}"

    )
    @Nullable
    public SpPtlCompanyDailyCard findOne( @Param("id") Long id);

    public SpPtlCompanyDailyCard save(SpPtlCompanyDailyCard entity);

    /**
     * ****************************************************************************
     * ******************************************************************************
     */

    //@Override
    //@RestResource(exported = false)
    //public OBJ_TYPE findOne(ID_TYPE id);

    @Override
    @RestResource(exported = false)
    public Page<SpPtlCompanyDailyCard> findAll(Pageable pageable);

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
    // @RestResource(exported = false)
    //public OBJ_TYPE save(OBJ_TYPE entity);
}
