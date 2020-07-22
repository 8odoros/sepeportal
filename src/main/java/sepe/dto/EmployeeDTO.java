package sepe.dto;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.Serializable;

public final class EmployeeDTO implements Serializable {

    private Long id;
    private Long userId;

    private String fatherName;
    private String motherName;
    private String afm;
    private String amka;
    private String cardNumber;
    private String companyName;
    private String jobTitle;
    private String sex;
    private Integer citizenCode;
    private Integer cardType;

    public EmployeeDTO(
            @Nonnull final Long id,
            @Nonnull final Long userId,
            @Nullable final String fatherName,
            @Nullable final String motherName,
            @Nullable final String afm,
            @Nullable final String amka,
            @Nullable final String cardNumber,
            @Nullable final String companyName,
            @Nullable final String jobTitle,
            @Nullable final String sex,
            @Nullable final Integer citizenCode,
            @Nullable final Integer cardType
    ) {
        this.id = id;
        this.userId = userId;
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.afm = afm;
        this.amka = amka;
        this.cardNumber = cardNumber;
        this.companyName = companyName;
        this.jobTitle = jobTitle;
        this.sex = sex;
        this.citizenCode = citizenCode;
        this.cardType = cardType;
    }

    public EmployeeDTO() {
    }

    @Nonnull
    public Long getId() {return id; }
    public void setId(@Nonnull final Long id) {
        this.id = id;
    }


    @Nonnull
    public Long getUserId() {return userId;}
    public void setUserId(@Nonnull Long userId) {this.userId = userId;}

    @Nullable
    public String getFatherName() {
        return fatherName;
    }
    public void setFatherName(@Nonnull String fatherName) {
        this.fatherName = fatherName;
    }

    @Nullable
    public String getMotherName() {
        return motherName;
    }
    public void setMotherName(@Nonnull String motherName) {
        this.motherName = motherName;
    }

    @Nullable
    public String getAfm() {
        return afm;
    }
    public void setAfm(@Nonnull String afm) {
        this.afm = afm;
    }

    @Nullable
    public String getAmka() {return amka;}
    public void setAmka(@Nonnull String amka) {this.amka = amka;}

    @Nullable
    public String getProfession() {return jobTitle;}
    public void setProfession(@Nonnull String profession) {this.jobTitle = profession;}

    @Nullable
    public String getCompanyName() {return companyName;}
    public void setCompanyName(@Nonnull String company) {this.companyName = company;}

    @Nullable
    public String getCardNumber() {
        return cardNumber;
    }
    public void setCardNumber(@Nonnull String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Nullable
    public String getSex() {
        return sex;
    }
    public void setSex(@Nonnull String sex) {
        this.sex = sex;
    }

    @Nullable
    public Integer getCitizenCode() {
        return citizenCode;
    }
    public void setCitizenCode(@Nonnull Integer citizenCode) {
        this.citizenCode = citizenCode;
    }

    @Nullable
    public Integer getCardType() {
        return cardType;
    }
    public void setCardType(@Nonnull Integer cardType) {
        this.cardType = cardType;
    }

    @Override
    public boolean equals(final Object other) {
        boolean equals = false;
        if (other instanceof UserDTO) {
            final UserDTO that = (UserDTO) other;
            equals = this.getId().equals(that.getId())
            ;
        }
        return equals;
    }

    /*
    @Override
    public int hashCode() {
        final int idHash = (id == null) ? 0 : id.hashCode();

        final int roleHash = (role == null) ? 0 : role.hashCode();

        return idHash  + roleHash;
    }*/

}
