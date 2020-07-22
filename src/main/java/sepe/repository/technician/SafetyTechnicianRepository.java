package sepe.repository.technician;

import org.springframework.data.repository.CrudRepository;
import sepe.domain.technician.TSafetyTechnician;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface SafetyTechnicianRepository extends CrudRepository<TSafetyTechnician, Long> {

    @Nullable
    public TSafetyTechnician findByUserId(
            @Nonnull Long userId
    );

    @Nullable
    public TSafetyTechnician findByAfmEquals(@Nonnull String afm);
}
