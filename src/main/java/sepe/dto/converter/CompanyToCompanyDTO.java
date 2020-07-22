package sepe.dto.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import sepe.domain.company.TCompany;
import sepe.dto.CompanyDTO;

import javax.annotation.Nullable;

@Component
public final class CompanyToCompanyDTO implements Converter<TCompany, CompanyDTO> {


    @Override
    @Nullable
    public CompanyDTO convert(@Nullable final TCompany company) {
        CompanyDTO dto = null;
        if (company != null) {
            dto = new CompanyDTO(
                    company.getId(),
                    company.getName(),
                    company.getTitle(),
                    company.getAfm(),
                    company.getAme(),
                    company.getIsAssociation(),
                    company.getUserId(),
                    company.getSumEmplA(),
                    company.getSumEmplB(),
                    company.getSumEmplC(),
                    company.getSumEmplTotal(),
                    company.getIsValidAllData(),
                    company.getIsTaNoneEmplrEmple(),
                    company.getTaAfm(),
                    company.getTaFullname(),
                    company.getIsValidData1(),
                    company.getIsValidData2(),
                    company.getIsExypp(),
                    company.getIsMilitary(),
                    company.getTaDegree(),
                    company.getTaVisitProgram()
            );
        }

        return dto;
    }
}


