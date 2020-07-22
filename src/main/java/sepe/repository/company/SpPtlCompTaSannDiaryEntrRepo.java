package sepe.repository.company;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import sepe.domain.company.SpPtlCompTaSannDiaryEntr;


import javax.annotation.Nullable;

/**
 * Created by Nikos on 6/13/2015.
 */
// http://localhost:7001/compTaSannDiaryEntr/search/findByCompTaSannId?compTaSannId=761
// http://localhost:7001/compTaSannDiaryEntr/search/findOne?id=1
@RepositoryRestResource(collectionResourceRel = "compTaSannDiaryEntr", path = "/compTaSannDiaryEntr")
public interface SpPtlCompTaSannDiaryEntrRepo extends PagingAndSortingRepository<SpPtlCompTaSannDiaryEntr, Long> {


    @Query(
            value = "SELECT o FROM SpPtlCompTaSannDiaryEntr o "
                    + "WHERE  o.compTaSann.companyId =?#{principal.company.id} AND  o.compTaSann.id =:#{#compTaSannId}"
    )
    @Nullable
    public Page< SpPtlCompTaSannDiaryEntr> findByCompTaSannId(Pageable pageable, @Param("compTaSannId") Long compTaSannId);

    @Query(
            value = "SELECT o FROM SpPtlCompTaSannDiaryEntr o "
                    + "WHERE  o.compTaSann.companyId =?#{principal.company.id} AND o.id =:#{#id}"
    )
    @Nullable
    public  SpPtlCompTaSannDiaryEntr findOne(@Param("id") Long id);

    public  SpPtlCompTaSannDiaryEntr save( SpPtlCompTaSannDiaryEntr entity);

    /**
     * ****************************************************************************
     * ******************************************************************************
     */

    //@Override
    //@RestResource(exported = false)
    //public OBJ_TYPE findOne(ID_TYPE id);

    @Override
    @RestResource(exported = false)
    public Page< SpPtlCompTaSannDiaryEntr> findAll(Pageable pageable);

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
