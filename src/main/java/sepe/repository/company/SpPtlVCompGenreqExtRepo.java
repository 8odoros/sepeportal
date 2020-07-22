package sepe.repository.company;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sepe.domain.company.SpPtlVCompGenreqExt;

import javax.annotation.Nullable;

/**
 * Created by dimitrisf on 12/9/2018.
 */

@Transactional(propagation = Propagation.REQUIRES_NEW)
@RepositoryRestResource(collectionResourceRel = "compGenreqExt", path = "/compGenreqExt")
public interface SpPtlVCompGenreqExtRepo extends PagingAndSortingRepository<SpPtlVCompGenreqExt, Long> {
    @Query(
            value = "SELECT DISTINCT a FROM SpPtlVCompGenreqExt a, TCompanyUserPrivilages p, TUser u "
                    + " WHERE  a.companyId =?#{principal.company.id}"
                    + " AND a.requestTypeId NOT IN(24)"
                    + " AND "
                    + " u.id = ?#{principal.userId}"
                    + " AND ("
                    + " (u.role=7 AND p.userId=u.id AND p.branchIds LIKE CONCAT('%,' , a.branch1Id , ',%'))"
                    + " OR "
                    + " (u.role=3))"
    )
    @Nullable
    public Page<SpPtlVCompGenreqExt> findAll(Pageable pageable);
}
