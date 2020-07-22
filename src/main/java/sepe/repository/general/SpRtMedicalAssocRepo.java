package sepe.repository.general;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import sepe.domain.general.SpRtMedicalAssoc;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by Nick on 4/10/2015.
 */
// http://localhost:7001/medicalAsso
// http://localhost:7001/medicalAsso/search/findById?id=1
@RepositoryRestResource(collectionResourceRel = "medicalAsso", path = "/medicalAsso")
public interface SpRtMedicalAssocRepo extends CrudRepository<SpRtMedicalAssoc, Long> {


    @Query(
            value = "SELECT o FROM SpRtMedicalAssoc o"
                    + " WHERE o.spMedasId =:#{#id}",
            nativeQuery = false
    )
    @Nullable
    public SpRtMedicalAssoc findById(@Param("id") Long id);

    @Query(
            value = "SELECT o FROM SpRtMedicalAssoc o",
            nativeQuery = false
    )
    public List<SpRtMedicalAssoc> findAll();
    /*******************************************************************************
     ********************************************************************************/

    @Override
    @RestResource(exported = false)
    public SpRtMedicalAssoc findOne(Long id);


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
    public SpRtMedicalAssoc save(SpRtMedicalAssoc entity);

}
