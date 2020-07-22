package sepe.repository.company;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import sepe.domain.company.SpPtlCompanyAccidentWitn;

import javax.annotation.Nullable;

/**
 * Created by Nick on 4/29/2015.
 */

// http://localhost:7001/companyAccidentWitn/search/findByCompanyAccidentId?companyAccidentId=761
// http://localhost:7001/companyAccidentWitn/search/findOne?id=1
@RepositoryRestResource(collectionResourceRel = "companyAccidentWitn", path = "/companyAccidentWitn")
public interface SpPtlCompanyAccidentWitnRepo extends PagingAndSortingRepository<SpPtlCompanyAccidentWitn, Long> {


    @Query(
            value = "SELECT o FROM SpPtlCompanyAccidentWitn o "
                    + "WHERE  o.companyAccident.companyId =?#{principal.company.id} AND  o.companyAccident.id =:#{#companyAccidentId}"
    )
    @Nullable
    public Page<SpPtlCompanyAccidentWitn> findByCompanyAccidentId(Pageable pageable, @Param("companyAccidentId") Long companyAccidentId);

    @Query(
            value = "SELECT o FROM SpPtlCompanyAccidentWitn o "
                    + "WHERE  o.companyAccident.companyId =?#{principal.company.id} AND o.id =:#{#id}"
    )
    @Nullable
    public SpPtlCompanyAccidentWitn findOne(@Param("id") Long id);

    public SpPtlCompanyAccidentWitn save(SpPtlCompanyAccidentWitn entity);

    /**
     * ****************************************************************************
     * ******************************************************************************
     */

    //@Override
    //@RestResource(exported = false)
    //public OBJ_TYPE findOne(ID_TYPE id);

    @Override
    @RestResource(exported = false)
    public Page<SpPtlCompanyAccidentWitn> findAll(Pageable pageable);

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
