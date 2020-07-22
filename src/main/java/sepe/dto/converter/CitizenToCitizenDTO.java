package sepe.dto.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import sepe.domain.citizen.TCitizen;
import sepe.dto.CitizenDTO;
import javax.annotation.Nullable;

@Component
public final class CitizenToCitizenDTO implements Converter<TCitizen, CitizenDTO> {

    @Override
    @Nullable
    public CitizenDTO convert(@Nullable final TCitizen citizen) {
        CitizenDTO dto = null;
        if (citizen != null) {
            dto = new CitizenDTO(
                    citizen.getId(),
                    citizen.getUserId()
            );
        }
        return dto;
    }


}
