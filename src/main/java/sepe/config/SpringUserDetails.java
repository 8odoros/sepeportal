package sepe.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import sepe.dto.CompanyDTO;
import sepe.dto.UserDTO;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Evangelos on 29/1/2015.
 */
public final class SpringUserDetails implements UserDetails {

    private final UserDTO userDTO;
    private final CompanyDTO company;
    private final long userId;
    private final String password;
    private final String branchIds;

    public SpringUserDetails(
            @Nonnull final UserDTO userDTO,
            long userId,
            @Nonnull final String password,
            final CompanyDTO company,
            final String branchIds
    ) {
        this.userDTO = userDTO;
        this.userId = userId;
        this.password = password;
        this.company = company;
        this.branchIds=branchIds;
    }

    @Override
    @Nonnull
    public String getUsername() {
        return this.userDTO.getEmail();
    }

    @Override
    @Nullable
    public String getPassword() {
        return this.password;
    }

    @Nullable
    public UserDTO getUserDTO() {
        return userDTO;
    }

    @Nullable
    public CompanyDTO getCompany() {
        return company;
    }

    @Override
    @Nonnull
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();

        Integer userRoleId;
        String userRoleStr;

        userRoleId = this.getUserDTO().getRole();
        userRoleStr = Constants.USER_TYPE.getUserType(userRoleId).getName();

        /* Roles should start with "ROLE_" prefix */
        authorities.add(new SimpleGrantedAuthority(userRoleStr));

        return authorities;
    }

    @Nonnull
    public long getUserId() {
        return userId;
    }

    @Nonnull
    public String getBranchIds() {
        return branchIds;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
