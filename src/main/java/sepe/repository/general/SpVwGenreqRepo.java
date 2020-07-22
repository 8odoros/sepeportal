package sepe.repository.general;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import sepe.domain.general.SpVwGenreq;


import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by Nikos on 5/27/2015.
 */
//http://localhost:7001/vwGenreq/
//http://localhost:7001/vwGenreq/search/findAllKeBus
//http://localhost:7001/vwGenreq/search/findAllKeEmp
//http://localhost:7001/vwGenreq/search/findAllTeBus
//http://localhost:7001/vwGenreq/search/findAllTeEmp
//http://localhost:7001/vwGenreq/search/findById?id=1
@RepositoryRestResource(collectionResourceRel = "vwGenreq", path = "/vwGenreq")
public interface SpVwGenreqRepo extends CrudRepository<SpVwGenreq, Long> {

    @Query(
            value = "SELECT o FROM SpVwGenreq o"
                    + " WHERE o.spGreqId =:#{#id}",
            nativeQuery = false
    )
    @Nullable
    public SpVwGenreq findById(@Param("id") Long id);

    @Query(
            value = "SELECT o FROM SpVwGenreq o",
            nativeQuery = false
    )
    public List<SpVwGenreq> findAll();

    @Query(
            value = "SELECT o FROM SpVwGenreq o"
                    + " WHERE o.spGreqKe = 1 and o.spGreqUsertypes like '_1%'  and o.spGreqId > 0",
            nativeQuery = false
    )
    public List<SpVwGenreq> findAllKeBus();

    @Query(
            value = "SELECT o FROM SpVwGenreq o"
                    + " WHERE o.spGreqKe = 1 and o.spGreqUsertypes like '1%' and o.spGreqId > 0",
            nativeQuery = false
    )
    public List<SpVwGenreq> findAllKeEmp();

    @Query(
            value = "SELECT o FROM SpVwGenreq o"
                    + " WHERE o.spGreqTe = 1 and o.spGreqUsertypes like '_1%'  and o.spGreqId > 0",
            nativeQuery = false
    )
    public List<SpVwGenreq> findAllTeBus();

    @Query(
            value = "SELECT o FROM SpVwGenreq o"
                    + " WHERE o.spGreqTe = 1 and o.spGreqUsertypes like '1%'  and o.spGreqId > 0",
            nativeQuery = false
    )
    public List<SpVwGenreq> findAllTeEmp();
    /*******************************************************************************
     ********************************************************************************/

    @Override
    @RestResource(exported = false)
    public SpVwGenreq findOne(Long id);


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
    public SpVwGenreq save(SpVwGenreq entity);

}