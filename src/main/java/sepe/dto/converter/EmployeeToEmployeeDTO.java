package sepe.dto.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import sepe.domain.employee.TEmployee;
import sepe.dto.EmployeeDTO;

import javax.annotation.Nullable;

@Component
public final class EmployeeToEmployeeDTO implements Converter<TEmployee, EmployeeDTO> {


    @Override
    @Nullable
    public EmployeeDTO convert(@Nullable final TEmployee employee) {
        EmployeeDTO dto = null;
        if (employee != null) {
            dto = new EmployeeDTO(
                    employee.getId(),
                    employee.getUserId(),
                    employee.getFatherName(),
                    employee.getMotherName(),
                    employee.getAfm(),
                    employee.getAmka(),
                    employee.getCardNumber(),
                    employee.getCompanyName(),
                    employee.getJobTitle(),
                    employee.getSex(),
                    employee.getCitizenshipCode(),
                    employee.getCardType()
            );
        }

        return dto;
    }
}

