package sepe.config;

import com.google.common.base.Objects;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;


/**
 * Created by Evangelos on 29/1/2015.
 */


public class Role implements Serializable, GrantedAuthority {
    private String rolename;
    private Integer id;

    public Role(String rolename, Integer id) {
        this.rolename = rolename;
        this.id = id;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    @Override
    public String toString() {
        return String.format("%s(id=%d, rolename='%s')",
                this.getClass().getSimpleName(),
                this.getId(), this.getRolename());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;

        if (o instanceof Role) {
            final Role other = (Role) o;
            return Objects.equal(getId(), other.getId())
                    && Objects.equal(getRolename(), other.getRolename());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getRolename());
    }

    @Override
    public String getAuthority() {
        return getRolename();
    }

    public String getRolename() {
        return rolename;
    }

    public Integer getId() {
        return id;
    }
}
