package sepe.repository.company;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import sepe.domain.company.SpPtlCompSecDiaryContr;

import javax.annotation.Nullable;

/**
 * Created by Nikos on 6/10/2015.
 */
// http://localhost:7001/compSecDiaryContr/search/findByCompSecDiaryId?compSecDiaryId=1
// http://localhost:7001/compSecDiaryContr/
@RepositoryRestResource(collectionResourceRel = "compSecDiaryContr", path = "/compSecDiaryContr")
public interface SpPtlCompSecDiaryContrRepo extends PagingAndSortingRepository<SpPtlCompSecDiaryContr, Long> {


    @Query(
            value = "SELECT o FROM SpPtlCompSecDiaryContr o "
                    + "WHERE  o.compSecDiary_contr.companyId =?#{principal.company.id} AND o.compSecDiary_contr.id =:#{#compSecDiaryId}"
    )
    @Nullable
    public Page<SpPtlCompSecDiaryContr> findByCompSecDiaryId(Pageable pageable, @Param("compSecDiaryId") Long compSecDiaryId);

    @Query(
            value = "SELECT o FROM SpPtlCompSecDiaryContr o "
                    + "WHERE  o.compSecDiary_contr.companyId =?#{principal.company.id} AND o.id =:#{#id}"
    )
    @Nullable
    public SpPtlCompSecDiaryContr findOne(@Param("id") Long id);

    public SpPtlCompSecDiaryContr save(SpPtlCompSecDiaryContr entity);

    /**
     * ****************************************************************************
     * ******************************************************************************
     */

    //@Override
    //@RestResource(exported = false)
    //public OBJ_TYPE findOne(ID_TYPE id);

    @Override
    @RestResource(exported = false)
    public Page<SpPtlCompSecDiaryContr> findAll(Pageable pageable);

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
