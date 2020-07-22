package sepe.repository.general;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import sepe.domain.general.SpRtTaSpeciality;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by Nikos on 4/4/2015.
 */

//http://localhost:7001/taSpeciality
//http://localhost:7001/taSpeciality/search/findById?id=99
//http://localhost:7001/taSpeciality/search/findByEducLevel?id=99
@RepositoryRestResource(collectionResourceRel = "taSpeciality", path = "/taSpeciality")
public interface SpRtTaSpecialityRepo extends CrudRepository<SpRtTaSpeciality, Long> {


    @Query(
            value = "SELECT o FROM SpRtTaSpeciality o"
                    + " WHERE o.id =:#{#id}",
            nativeQuery = false
    )
    @Nullable
    public SpRtTaSpeciality findById(@Param("id") Integer id);

    @Query(
            value = "SELECT o FROM SpRtTaSpeciality o"
                    + " WHERE o.spTaspEdulvl =:#{#spTaspEdulvl}",
            nativeQuery = false
    )
    @Nullable
    public List<SpRtTaSpeciality> findByEducLevel(@Param("spTaspEdulvl") Long spTaspEdulvl);

    @Query(
            value = "SELECT o FROM SpRtTaSpeciality o"
                    + " WHERE o.id IN (SELECT p.spRtTaSpecId FROM SpRtTaSectorCheck p WHERE p.spRtTaBrSsector =:#{#branchSectorId}) ",
            nativeQuery = false
    )
    @Nullable
    public List<SpRtTaSpeciality> findByBranchSectorId(@Param("branchSectorId") Long branchSectorId);

    @Query(
            value = "SELECT o FROM SpRtTaSpeciality o"
                    + " WHERE o.id IN (SELECT p.spRtTaSpecId FROM SpRtTaSectorCheck p WHERE p.spRtTaBrSsector =:#{#branchSectorId} AND p.spRtTaOblig =:#{#spRtTaOblig}) ",
            nativeQuery = false
    )
    @Nullable
    public List<SpRtTaSpeciality> findByBranchSectorIdAndObligId(@Param("branchSectorId") Long branchSectorId, @Param("spRtTaOblig") Long spRtTaOblig);

    @Query(
            value = "SELECT o FROM SpRtTaSpeciality o",
            nativeQuery = false
    )
    public List<SpRtTaSpeciality> findAll();
    /*******************************************************************************
     ********************************************************************************/

    @Override
    @RestResource(exported = false)
    public SpRtTaSpeciality findOne(Long id);


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
    public SpRtTaSpeciality save(SpRtTaSpeciality entity);

}
