package sepe.repository.general;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import sepe.domain.general.SpRtEmplExperienceCertUse;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by Nikolas on 3/12/2015.
 *
 * */
//http://localhost:7001/emplExperienceCertUse/search/findOne?id=1
//http://localhost:7001/emplExperienceCertUse
@RepositoryRestResource(collectionResourceRel = "emplExperienceCertUse", path = "/emplExperienceCertUse")
public interface EmplExperienceCertUseRepo extends CrudRepository<SpRtEmplExperienceCertUse, Long> {


    @Query(
            value = "SELECT o from SpRtEmplExperienceCertUse o",
            nativeQuery = false
    )
    @Nullable
    public List<SpRtEmplExperienceCertUse> findAll();


    @Query(
            value = "SELECT o from SpRtEmplExperienceCertUse o"
                    + " WHERE  o.id =:#{#id} ",
            nativeQuery = false
    )
    @Nullable
    public SpRtEmplExperienceCertUse findOne(@Param("id") Long id);

    /*******************************************************************************
     ********************************************************************************/

    //@Override
    //@RestResource(exported = false)
    //public SpRtEmplExperienceCertUse findOne(Long id);

    //@Override
    //@RestResource(exported = false)
   //public List<SpRtEmplExperienceCertUse> findAll();

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
    public SpRtEmplExperienceCertUse save(SpRtEmplExperienceCertUse entity);

}