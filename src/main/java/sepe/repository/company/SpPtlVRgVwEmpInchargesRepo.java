package sepe.repository.company;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import sepe.domain.company.SpPtlCompTaSann;
import sepe.domain.company.SpPtlVRgVwEmpIncharges;


import javax.annotation.Nullable;


/**
 * Created by Nikos on 6/20/2015.
 */
//http://localhost:7001/portal/empIncharges
@RepositoryRestResource(collectionResourceRel = "empIncharges", path = "/empIncharges")
public interface SpPtlVRgVwEmpInchargesRepo extends PagingAndSortingRepository<SpPtlVRgVwEmpIncharges, String> {
    @Query(
            value = "SELECT o FROM SpPtlVRgVwEmpIncharges o "
    )
    @Nullable
    public Page<SpPtlVRgVwEmpIncharges> findAll(Pageable pageable);

    @Query(
            value = "SELECT o FROM SpPtlVRgVwEmpIncharges o "
                    + "WHERE o.id =:#{#id}"
    )
    @Nullable
    public SpPtlVRgVwEmpIncharges findOne(@Param("id") String id);

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
        @RestResource(exported = true)
        public SpPtlVRgVwEmpIncharges save(SpPtlVRgVwEmpIncharges entity);
    }