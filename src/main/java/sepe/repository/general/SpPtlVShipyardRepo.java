package sepe.repository.general;

        import org.springframework.data.jpa.repository.Query;
        import org.springframework.data.repository.CrudRepository;
        import org.springframework.data.repository.query.Param;
        import org.springframework.data.rest.core.annotation.RepositoryRestResource;
        import org.springframework.data.rest.core.annotation.RestResource;
        import sepe.domain.general.SpPtlVPortAuth;
        import sepe.domain.general.SpPtlVShipyard;

        import javax.annotation.Nullable;
        import java.util.List;

/**
 * Created by Nikolas on 2/24/2015.
 */

//http://localhost:7001/SpPtlVShipyard/search/findShipyardbyId?id=4638-1
@RepositoryRestResource(collectionResourceRel = "SpPtlVShipyard", path = "/SpPtlVShipyard")
public interface SpPtlVShipyardRepo extends CrudRepository<SpPtlVShipyard, String> {

    @Query(
            value = "SELECT o from SpPtlVShipyard o",
            nativeQuery = false
    )
    @Nullable
    public List<SpPtlVShipyard> findAll();

    /*******************************************************************************
     ********************************************************************************/

    @Override
    @RestResource(exported = false)
    public SpPtlVShipyard findOne(String id);

    //@Override
    //@RestResource(exported = false)
    // public List<OBJ_TYPE> findAll();

    @Override
    @RestResource(exported = false)
    public long count();

    @Override
    @RestResource(exported = false)
    public void delete(String id);

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
    public boolean exists(String id);

//@Override
//@RestResource(exported = false)
//public List<OBJ_TYPE> findAll(List<ID_TYPE> ids);

//@Override
//@RestResource(exported = false)
//public List<OBJ_TYPE> save(List<OBJ_TYPE> entities);

    @Override
    @RestResource(exported = false)
    public SpPtlVShipyard save(SpPtlVShipyard entity);


}