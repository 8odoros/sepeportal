package sepe.repository.company;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;
import sepe.domain.company.TCompanyUserPrivilages;

import javax.annotation.Nullable;
import java.util.List;


public interface SpPtlCompanyUserPrivilagesRepositoryInternal extends CrudRepository<TCompanyUserPrivilages, Long> {

    @Query(
            value = "SELECT o FROM TCompanyUserPrivilages o "
                    + "WHERE  o.userId= ?1"
    )
    @Nullable
    public TCompanyUserPrivilages findByUser(Long uid);
}
