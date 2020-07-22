package sepe.repository.company;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sepe.domain.company.SpPtlVCompSundayPmtExt;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by dimitrisf on 11/9/2018.
 */
@Transactional(propagation = Propagation.REQUIRES_NEW)
@RepositoryRestResource(collectionResourceRel = "compSundayPmtExt", path = "/compSundayPmtExt")
public interface SpPtlVCompSundayPmtExtRepo extends PagingAndSortingRepository<SpPtlVCompSundayPmtExt, Long> {
    @Query(
            /*value = "SELECT o FROM SpPtlCompSundayPmt o "
                    + "WHERE  o.companyId =?#{principal.company.id}"
                    + " AND "
                    + " UPPER(?#{principal.branchIds}) LIKE CONCAT('%,' , o.compEbrBranchId , ',%')"*/
            value = "SELECT DISTINCT a FROM SpPtlVCompSundayPmtExt a, TCompanyUserPrivilages p, TUser u"
                    + " WHERE  a.companyId =?#{principal.company.id}"
                    + " AND "
                    + " u.id = ?#{principal.userId}"
                    + " AND ("
                    + " (u.role=7 AND p.userId=u.id AND p.branchIds LIKE CONCAT('%,' , a.compEbrBranchId , ',%'))"
                    + " OR "
                    + " (u.role=3))"
    )
    @Nullable
    public Page<SpPtlVCompSundayPmtExt> findAll(Pageable pageable);
}
