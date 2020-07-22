package sepe.dto;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.Serializable;

public final class TechnicianDTO implements Serializable {

    private long id;
    private long userId;
    private String afm;
    private Long cardType;
    private String cardNumber;

    public TechnicianDTO(
            @Nonnull final Long id,
            @Nonnull final Long userId,
            @Nullable final String afm,
            @Nullable final Long cardType,
            @Nullable final String cardNumber
    ) {
        this.id = id;
        this.userId = userId;
        this.afm = afm;
        this.cardType = cardType;
        this.cardNumber = cardNumber;
    }

    public TechnicianDTO() {
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

    @Nullable
    public String getAfm() {
        return afm;
    }
    public void setAfm(@Nonnull String afm) {
        this.afm = afm;
    }

    @Nullable
    public Long getCardType() {
        return cardType;
    }
    public void setCardType(@Nonnull Long cardType) {
        this.cardType = cardType;
    }

    @Nullable
    public String getCardNumber() {
        return cardNumber;
    }
    public void setCardNumber(@Nonnull String cardNumber) {
        this.cardNumber = cardNumber;
    }

}