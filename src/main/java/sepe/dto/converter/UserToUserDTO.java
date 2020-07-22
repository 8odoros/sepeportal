package sepe.dto.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import sepe.domain.TUser;
import sepe.dto.UserDTO;

import javax.annotation.Nullable;


@Component
public final class UserToUserDTO implements Converter<TUser, UserDTO> {


    @Override
    @Nullable
    public UserDTO convert(@Nullable final TUser user) {
        UserDTO dto = null;
        if (user != null) {
            dto = new UserDTO(
                    user.getId(),
                    user.getIsAdmin(),
                    user.getEmail(),
                    user.getRole(),
                    user.getStatus(),
                    user.getUsername(),
                    user.getPassword(),
                    user.getEmailNotifEn(),
                    user.getFirstname(),
                    user.getLastname(),
                    user.getPhone(),
                    user.getMobile(),
                    user.getFax(),
                    user.getAddr(),
                    user.getAddrTk()
            );
        }
        return dto;
    }


}
