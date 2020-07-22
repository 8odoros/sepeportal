package sepe.dto.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import sepe.domain.technician.TSafetyTechnician;
import sepe.dto.TechnicianDTO;

import javax.annotation.Nullable;

@Component
public final class SafetyTechnicianToTechnicianDTO implements Converter<TSafetyTechnician, TechnicianDTO> {

    @Override
    @Nullable
    public TechnicianDTO convert(@Nullable final TSafetyTechnician technician) {
        TechnicianDTO dto = null;
        if (technician != null) {
            dto = new TechnicianDTO(
                    technician.getId(),
                    technician.getUserId(),
                    technician.getAfm(),
                    technician.getCardType(),
                    technician.getCardNumber()
            );
        }
        return dto;
    }
}
