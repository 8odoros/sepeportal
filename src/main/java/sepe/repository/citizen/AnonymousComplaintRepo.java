package sepe.repository.citizen;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import sepe.domain.citizen.SpPtlAnonymousComplaint;

/**
 * Created by Nikolas on 3/21/2015.
 */
// http://localhost:7001/anonymousComplaint
@RepositoryRestResource(collectionResourceRel = "anonymousComplaint", path = "/anonymousComplaint")
public interface AnonymousComplaintRepo extends PagingAndSortingRepository<SpPtlAnonymousComplaint, Long> {

    public SpPtlAnonymousComplaint save(SpPtlAnonymousComplaint entity);

    /*******************************************************************************
     ********************************************************************************/

    @Override
    @RestResource(exported = false)
    public SpPtlAnonymousComplaint findOne(Long id);

    @Override
    @RestResource(exported = false)
    public Page<SpPtlAnonymousComplaint> findAll(Pageable pageable);

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

   // @Override
    //@RestResource(exported = false)
    //public SpPtlAnonymousComplaint save(SpPtlAnonymousComplaint entity);
}