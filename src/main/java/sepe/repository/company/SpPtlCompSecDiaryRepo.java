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
import sepe.domain.company.SpPtlCompSecDiary;


import javax.annotation.Nullable;

/**
 * Created by Nikos on 6/10/2015.
 */

// http://localhost:7001/compSecDiary
@Transactional(propagation = Propagation.REQUIRES_NEW)
@RepositoryRestResource(collectionResourceRel = "compSecDiary", path = "/compSecDiary")
public interface SpPtlCompSecDiaryRepo extends PagingAndSortingRepository<SpPtlCompSecDiary, Long> {
    @Query(
            value = "SELECT o FROM SpPtlCompSecDiary o "
                    + "WHERE o.companyId =?#{principal.company.id}"
    )
    @Nullable
    public Page<SpPtlCompSecDiary> findAll(Pageable pageable);

    @Query(
            value = "SELECT o FROM SpPtlCompSecDiary o "
                    + "WHERE o.companyId =?#{principal.company.id} AND o.id =:#{#id}"
    )
    @Nullable
    public SpPtlCompSecDiary findOne(@Param("id") Long id);

    public SpPtlCompSecDiary save(SpPtlCompSecDiary entity);

    @Modifying
    @Transactional
    @Query(
            value = "DELETE FROM SpPtlCompSecDiary o "
                    + "WHERE o.companyId =?#{principal.company.id} AND o.id =:#{#id}"
                    + " AND o.subStatus < 2"
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

//@Override
//@RestResource(exported = false)
//public SpPtlEmployeeGenrequest save(SpPtlEmployeeGenrequest entity);
}