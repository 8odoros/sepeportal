package sepe.repository.general;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import sepe.domain.general.TEmployeeSpeciality;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by Nikolas on 3/2/2015.
 */

//http://localhost:7001/TEmployeeSpeciality
//http://localhost:7001/TEmployeeSpeciality/search/findOne?id=9133
//http://localhost:7001/TEmployeeSpeciality/search/findByDescr?descr=
@RepositoryRestResource(collectionResourceRel = "TEmployeeSpeciality", path = "/TEmployeeSpeciality")
public interface TEmployeeSpecialityRepo extends PagingAndSortingRepository<TEmployeeSpeciality, Long> {

    @Query(
            value = "SELECT o from TEmployeeSpeciality o"
            //nativeQuery = true
    )
    @Nullable
    public Page<TEmployeeSpeciality> findAll(Pageable pageable);

    @Query(
            value = "SELECT o from TEmployeeSpeciality o"
                    + " WHERE UPPER(o.spRtspDescription) like UPPER(CONCAT('%',:descr,'%'))",
            nativeQuery = false
    )
    @Nullable
    public Page<TEmployeeSpeciality> findByDescr(@Param("descr") String descr, Pageable pageable);

}