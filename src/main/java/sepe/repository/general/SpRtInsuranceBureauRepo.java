package sepe.repository.general;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import sepe.domain.general.SpRtIeSpeciality;
import sepe.domain.general.SpRtInsuranceBureau;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by root on 20/4/2015.
 */

//http://localhost:7001/insuranceBureau
//http://localhost:7001/insuranceBureau/search/findById?id=2
@RepositoryRestResource(collectionResourceRel = "insuranceBureau", path = "/insuranceBureau")
public interface SpRtInsuranceBureauRepo extends CrudRepository<SpRtInsuranceBureau, Long> {


    @Query(
            value = "SELECT o FROM SpRtInsuranceBureau o"
                    + " WHERE o.id =:#{#id}",
            nativeQuery = false
    )
    @Nullable
    public SpRtInsuranceBureau findById(@Param("id") Integer id);

    @Query(
            value = "SELECT o FROM SpRtInsuranceBureau o",
            nativeQuery = false
    )
    public List<SpRtInsuranceBureau> findAll();
    /*******************************************************************************
     ********************************************************************************/

    @Override
    @RestResource(exported = false)
    public SpRtInsuranceBureau findOne(Long id);


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
    public SpRtInsuranceBureau save(SpRtInsuranceBureau entity);

}
