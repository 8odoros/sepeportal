package sepe.repository.company;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sepe.domain.company.SpPtlCompTaAnn;
import sepe.domain.company.SpPtlCompTaAnnSe;

import javax.annotation.Nullable;

/**
 * Created by dimitrisf on 5/10/2018.
 */

@Transactional(propagation = Propagation.REQUIRES_NEW)
@RepositoryRestResource(collectionResourceRel = "compTaAnnSe", path = "/compTaAnnSe")
public interface SpPtlCompTaAnnSeRepo extends PagingAndSortingRepository<SpPtlCompTaAnnSe, Long> {

    @Query(
            value = "SELECT o FROM SpPtlCompTaAnnSe o "
                    + "WHERE  o.companyId =?#{principal.company.id}"
    )
    @Nullable
    public Page<SpPtlCompTaAnnSe> findAll(Pageable pageable);

    @Query(
            value = "SELECT o FROM SpPtlCompTaAnnSe o "
                    + "WHERE o.id =:#{#id}" /* TODO: privilages [kalled by ie controler */
    )

    @Nullable
    public SpPtlCompTaAnnSe findOne(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(
            value = "DELETE FROM SpPtlCompTaAnnSe o "
                    + "WHERE  o.companyId =?#{principal.company.id} AND o.id =:#{#id}"
    )
    @Nullable
    public void delete(@Param("id") Long id);

    @Query(
            value = "SELECT o FROM SpPtlCompTaAnnSe o "
                    + "WHERE  o.companyId =?#{principal.company.id} AND o.compPtlBranchId =:#{#branchId}"
    )

    @Nullable
    public Page<SpPtlCompTaAnn> findByPtlBranchId(Pageable pageable, @Param("branchId") Long branchId);

    @Override
    @RestResource(exported = false)
    public long count();

    @Override
    @RestResource(exported = false)
    public void deleteAll();

    @Override
    @RestResource(exported = false)
    public boolean exists(Long id);

    @Override
    @RestResource(exported = true)
    public SpPtlCompTaAnnSe save(SpPtlCompTaAnnSe entity);
}
