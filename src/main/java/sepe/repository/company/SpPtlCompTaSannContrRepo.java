package sepe.repository.company;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import sepe.domain.company.SpPtlCompTaSannContr;


import javax.annotation.Nullable;

/**
 * Created by Nikos on 6/13/2015.
 */
// http://localhost:7001/compTaSannContr/search/findByCompTaSannId?compTaSannId=1
// http://localhost:7001/compTaSannContr/
@RepositoryRestResource(collectionResourceRel = "compTaSannContr", path = "/compTaSannContr")
public interface SpPtlCompTaSannContrRepo extends PagingAndSortingRepository<SpPtlCompTaSannContr, Long> {


    @Query(
            value = "SELECT o FROM SpPtlCompTaSannContr o "
                    + "WHERE  o.compTaSann_contr.companyId =?#{principal.company.id} AND o.compTaSann_contr.id =:#{#compTaSannId}"
    )
    @Nullable
    public Page<SpPtlCompTaSannContr> findByCompTaSannId(Pageable pageable, @Param("compTaSannId") Long compTaSannId);

    @Query(
            value = "SELECT o FROM SpPtlCompTaSannContr o "
                    + "WHERE  o.compTaSann_contr.companyId =?#{principal.company.id} AND o.id =:#{#id}"
    )
    @Nullable
    public SpPtlCompTaSannContr findOne(@Param("id") Long id);

    public SpPtlCompTaSannContr save(SpPtlCompTaSannContr entity);

    /**
     * ****************************************************************************
     * ******************************************************************************
     */

    //@Override
    //@RestResource(exported = false)
    //public OBJ_TYPE findOne(ID_TYPE id);

    @Override
    @RestResource(exported = false)
    public Page<SpPtlCompTaSannContr> findAll(Pageable pageable);

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
