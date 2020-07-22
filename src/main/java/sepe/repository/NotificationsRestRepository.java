package sepe.repository;

import org.eclipse.persistence.config.CacheUsage;
import org.eclipse.persistence.config.QueryHints;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import sepe.domain.general.TNotificationsAccountEntity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.persistence.QueryHint;


// http://localhost:7001/tNotificationsAccountEntities
//http://localhost:7001/tNotificationsAccountEntities/search/countByViewed?viewed=0
@RepositoryRestResource
public interface NotificationsRestRepository extends PagingAndSortingRepository<TNotificationsAccountEntity, Long> {

    @Query(
            value = "SELECT o FROM TNotificationsAccountEntity o "
                    + "WHERE  o.accountId =?#{principal.userId}"
    )
    //@QueryHint("javax.persistence.cache.storeMode", "REFRESH" )
    //@QueryHint(name = QueryHints.CACHE_USAGE, value = CacheUsage.DoNotCheckCache)
    @Nullable
    public Page<TNotificationsAccountEntity> findAll(Pageable pageable);

    @Nullable
    public TNotificationsAccountEntity findById(
            @Nonnull Long Id
    );

    @Query(
            value = "SELECT COUNT(o) FROM TNotificationsAccountEntity o "
                    + "WHERE  o.accountId =?#{principal.userId}  AND o.viewed=:#{#viewed}"
    )
    @Nullable
    public Long  countByViewed( @Param("viewed")Long viewed);

    public TNotificationsAccountEntity save(TNotificationsAccountEntity entity);

}
