package sepe.repository.company;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import sepe.domain.company.SpPtlVRgVwExypp;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;


/**
 * Created by Nikos on 6/20/2015.
 */
//http://localhost:7001/portal/exypp
@RepositoryRestResource(collectionResourceRel = "exypp", path = "/exypp")
public interface SpPtlVRgVwExyppRepo extends CrudRepository<SpPtlVRgVwExypp, Long> {


    @Query(
            value = "SELECT o from SpPtlVRgVwExypp o",
            nativeQuery = false
    )
    @Nullable
    public List<SpPtlVRgVwExypp> findAll();


    @Query(
            value = "SELECT o from SpPtlVRgVwExypp o"
                    + " WHERE  o.id = ?1 ",
            nativeQuery = false
    )
    @Nullable
    public SpPtlVRgVwExypp findOne(@Nonnull Long id);

    /*******************************************************************************
     ********************************************************************************/

    /*
    * This is called from a company user through the "findTaByAfm" controller
    */
    @Query(
            value = "SELECT o FROM SpPtlVRgVwExypp o WHERE o.rgEmpTaxationNumber =:#{#afm}"
    )
    @Nullable
    public List<SpPtlVRgVwExypp> findByAfm(@Param("afm") String afm);
    //@Override
    //@RestResource(exported = false)
    //public TDoy findOne(Long id);

    //@Override
    //@RestResource(exported = false)
    //public List<TDoy> findAll();
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
    public SpPtlVRgVwExypp save(SpPtlVRgVwExypp entity);

}