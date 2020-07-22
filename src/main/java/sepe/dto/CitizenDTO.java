package sepe.dto;

import javax.annotation.Nonnull;
import java.io.Serializable;

public final class CitizenDTO implements Serializable {

    private long id;
    private Long userId;

    public CitizenDTO(
            @Nonnull final Long id,
            @Nonnull final Long userId
    ) {
        this.id = id;
        this.userId = userId;
    }

    public CitizenDTO() {
    }

    @Nonnull
    public long getId() {
        return id;
    }

    @Nonnull
    public void setId(long id) {
        this.id = id;
    }

    @Nonnull
    public long getUserId() {return userId; }

    @Nonnull
    public void setUserId(long userId) { this.userId = userId;}

}