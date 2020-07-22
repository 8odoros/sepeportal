package sepe.repository.doctor;

import org.springframework.data.repository.CrudRepository;
import sepe.domain.doctor.TWorkplaceDoctor;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface WorkplaceDoctorRepository extends CrudRepository<TWorkplaceDoctor, Long> {

    @Nullable
    public TWorkplaceDoctor findByUserId(
            @Nonnull Long userId
    );

    @Nullable
    public TWorkplaceDoctor findByAfmEquals(@Nonnull String afm);
}
