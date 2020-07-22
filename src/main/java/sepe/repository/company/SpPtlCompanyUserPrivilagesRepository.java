package sepe.repository.company;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;
import sepe.domain.company.TCompanyUserPrivilages;

import javax.annotation.Nullable;
import java.util.List;

@Repository
public interface SpPtlCompanyUserPrivilagesRepository extends CrudRepository<TCompanyUserPrivilages, Long> {

    @Query(
            value = "SELECT o FROM TCompanyUserPrivilages o "
                    + "WHERE  o.compId =?#{principal.company.id}"
            // ,nativeQuery = true
    )
    @Nullable
    public List<TCompanyUserPrivilages> findAll();


    @Override
    @RestResource(exported = true)
    public TCompanyUserPrivilages findOne(Long id);

    @Query(
            value = "SELECT o FROM TCompanyUserPrivilages o "
                    + "WHERE  o.compId =:#{#cid} AND o.userId=:#{#uid}"
    )
    @Nullable
    public TCompanyUserPrivilages findByCompanyUser(@Param("cid") Long cid, @Param("uid") Long uid);



    @Query(
            value = "SELECT o FROM TCompanyUserPrivilages o "
                    + "WHERE  o.userId=:#{#uid}"
    )
    @Nullable
    public TCompanyUserPrivilages findByUser( @Param("uid") Long uid);
}
