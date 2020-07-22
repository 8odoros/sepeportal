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
import sepe.domain.company.SnRgVwTaRegNez;
import sepe.domain.company.SpPtlCompComplaint;

import javax.annotation.Nullable;

/**
 * Created by Nick on 5/4/2015.
 */

// http://localhost:7001/snRgVwTaRegNez
@Transactional( propagation = Propagation.REQUIRES_NEW )
@RepositoryRestResource(collectionResourceRel = "snRgVwTaRegNez", path = "/snRgVwTaRegNez")
public interface SnRgVwTaRegNezRepo extends PagingAndSortingRepository<SnRgVwTaRegNez, Long> {

    @Query(
            value = "SELECT o FROM SnRgVwTaRegNez o "
                    + "WHERE o.rgTaDocId =:#{#rgTaDocId}"
    )
    @Nullable
    public SnRgVwTaRegNez findOne(@Param("rgTaDocId") Long rgTaDocId);

    /*
    * This is called from a company user through the "findTaByAfm" controller
    */
    @Query(
            value = "SELECT o FROM SnRgVwTaRegNez o WHERE o.rgTaTaxationNumber =:#{#afm}"
    )
    @Nullable
    public SnRgVwTaRegNez findByAfm(@Param("afm") String afm);

    @Modifying
    public SnRgVwTaRegNez save(SnRgVwTaRegNez entity);

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