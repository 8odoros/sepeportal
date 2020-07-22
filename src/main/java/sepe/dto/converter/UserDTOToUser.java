package sepe.dto.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import sepe.domain.TUser;
import sepe.dto.UserDTO;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.security.SecureRandom;


@Component
public final class UserDTOToUser implements Converter<UserDTO, TUser> {

    @Override
    @Nullable
    public TUser convert(@Nullable final UserDTO userDTO) {
        TUser user = null;
        if (userDTO != null) {
            user = new TUser(
                    userDTO.getId(),
                    0, // 0 user is not Admin, 1 user is Admin
                    userDTO.getEmail(),
                    userDTO.getRole(),
                    1, // 0 account is deactivate, 1 account is activated
                    userDTO.getUsername().toUpperCase(),
                    encryptPassword(userDTO.getPassword()),
                    userDTO.getEmailNotifEn(),
                    userDTO.getFirstname(),
                    userDTO.getLastname(),
                    userDTO.getPhone(),
                    userDTO.getMobile(),
                    userDTO.getFax(),
                    userDTO.getAddress(),
                    userDTO.getAddrTk()
            );
        }
        return user;
    }

    @Nonnull
    public String encryptPassword(@Nonnull final String rawPassword) {
        final String salt = BCrypt.gensalt(10, new SecureRandom());
        return BCrypt.hashpw(rawPassword, salt);
    }
}
