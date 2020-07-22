package sepe.repository.citizen;

import org.springframework.data.repository.CrudRepository;
import sepe.domain.citizen.TCitizen;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface CitizenRepository extends CrudRepository<TCitizen, Long> {

    @Nullable
    public TCitizen findByUserId(
            @Nonnull Long userID
    );
}
