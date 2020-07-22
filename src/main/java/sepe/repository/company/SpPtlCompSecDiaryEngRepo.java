package sepe.repository.company;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import sepe.domain.company.SpPtlCompSecDiaryEng;

import javax.annotation.Nullable;

/**
 * Created by Nikos on 6/10/2015.
 */
// http://localhost:7001/compSecDiaryEng/search/findByCompSecDiaryId?compSecDiaryId=1
// http://localhost:7001/compSecDiaryEng/1
@RepositoryRestResource(collectionResourceRel = "compSecDiaryEng", path = "/compSecDiaryEng")
public interface SpPtlCompSecDiaryEngRepo extends PagingAndSortingRepository<SpPtlCompSecDiaryEng, Long> {


    @Query(
            value = "SELECT o FROM SpPtlCompSecDiaryEng o "
                    + "WHERE  o.compSecDiary_eng.companyId =?#{principal.company.id} AND o.compSecDiary_eng.id =:#{#compSecDiaryId}"
    )
    @Nullable
    public Page<SpPtlCompSecDiaryEng> findByCompSecDiaryId(Pageable pageable, @Param("compSecDiaryId") Long compSecDiaryId);

    @Query(
            value = "SELECT o FROM SpPtlCompSecDiaryEng o "
                    + "WHERE  o.compSecDiary_eng.companyId =?#{principal.company.id} AND o.id =:#{#id}"
    )
    @Nullable
    public SpPtlCompSecDiaryEng findOne(@Param("id") Long id);

    public SpPtlCompSecDiaryEng save(SpPtlCompSecDiaryEng entity);

    /**
     * ****************************************************************************
     * ******************************************************************************
     */

    //@Override
    //@RestResource(exported = false)
    //public OBJ_TYPE findOne(ID_TYPE id);

    @Override
    @RestResource(exported = false)
    public Page<SpPtlCompSecDiaryEng> findAll(Pageable pageable);

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
