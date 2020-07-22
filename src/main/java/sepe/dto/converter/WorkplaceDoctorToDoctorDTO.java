package sepe.dto.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import sepe.domain.doctor.TWorkplaceDoctor;
import sepe.dto.DoctorDTO;

import javax.annotation.Nullable;

@Component
public final class WorkplaceDoctorToDoctorDTO implements Converter<TWorkplaceDoctor, DoctorDTO> {

    @Override
    @Nullable
    public DoctorDTO convert(@Nullable final TWorkplaceDoctor doctor) {
        DoctorDTO dto = null;
        if (doctor != null) {
            dto = new DoctorDTO(
                    doctor.getId(),
                    doctor.getUserId(),
                    doctor.getAfm(),
                    doctor.getCardType(),
                    doctor.getCardNumber()
            );
        }
        return dto;
    }
}
