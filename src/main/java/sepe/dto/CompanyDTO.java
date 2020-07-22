package sepe.dto;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.Serializable;

public final class CompanyDTO implements Serializable {

    private long id;
    private String name;
    private String title;
    private String afm;
    private String ame;
    private Integer isAssociation;
    private Long userId;
    private Double sumEmplA;
    private Double sumEmplB;
    private Double sumEmplC;
    private Double sumEmplTotal;
    private Integer isValidAllData;
    private Integer isTaNoneEmplrEmple;
    private String taAfm;
    private String taFullname;
    private Integer isValidData1;
    private Integer isValidData2;
    private Integer isExypp;
    private Integer isMilitary;
    private String taDegree;
    private String taVisitProgram;

    public CompanyDTO(
            @Nonnull final Long id,
            @Nullable final String name,
            @Nullable final String title,
            @Nullable final String afm,
            @Nullable final String ame,
            @Nullable final Integer isAssociation,
            @Nonnull final Long userId,
            @Nullable final Double sumEmplA,
            @Nullable final Double sumEmplB,
            @Nullable final Double sumEmplC,
            @Nullable final Double sumEmplTotal,
            @Nullable final Integer isValidAllData,
            @Nullable final Integer isTaNoneEmplrEmple,
            @Nullable final String taAfm,
            @Nullable final String taFullname,
            @Nullable final Integer isValidData1,
            @Nullable final Integer isValidData2,
            @Nullable final Integer isExypp,
            @Nullable final Integer isMilitary,
            @Nullable final String taDegree,
            @Nullable final String taVisitProgram
    ) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.afm = afm;
        this.ame = ame;
        this.isAssociation = isAssociation;
        this.userId = userId;
        this.sumEmplA = sumEmplA;
        this.sumEmplB = sumEmplB;
        this.sumEmplC = sumEmplC;
        this.sumEmplTotal = sumEmplTotal;
        this.isValidAllData = isValidAllData;
        this.isTaNoneEmplrEmple = isTaNoneEmplrEmple;
        this.taAfm = taAfm;
        this.taFullname = taFullname;
        this.isValidData1 = isValidData1;
        this.isValidData2 = isValidData2;
        this.isExypp = isExypp;
        this.isMilitary = isMilitary;
        this.taDegree = taDegree;
        this.taVisitProgram = taVisitProgram;
    }

    public CompanyDTO() {
    }

    @Nonnull
    public Long getId() {return id; }
    public void setId(@Nonnull final Long id) {
        this.id = id;
    }


    @Nonnull
    public String getName() {return name;}
    public void setName(@Nonnull String name) {this.name = name;}

    @Nullable
    public String getTitle() {
        return title;
    }
    public void setTitle(@Nonnull String title) {
        this.title = title;
    }

    @Nullable
    public String getAfm() {
        return afm;
    }
    public void setAfm(@Nonnull String afm) {
        this.afm = afm;
    }

    @Nullable
    public String getAme() { return ame; }
    public void setAme(@Nonnull String ame) {this.ame = ame;}

    @Nullable
    public Integer getIsAssociation() {
        return isAssociation;
    }

    public void setIsAssociation(Integer isAssociation) {
        this.isAssociation = isAssociation;
    }

    @Nonnull
    public long getUserId() {return userId; }
    public void setUserId(@Nonnull long userId) { this.userId = userId;}

    @Nullable
    public Double getSumEmplA() {
        return sumEmplA;
    }

    public void setSumEmplA(Double sumEmplA) {
        this.sumEmplA = sumEmplA;
    }

    @Nullable
    public Double getSumEmplB() {
        return sumEmplB;
    }

    public void setSumEmplB(@Nonnull Double sumEmplB) {
        this.sumEmplB = sumEmplB;
    }

    @Nullable
    public Double getSumEmplC() {
        return sumEmplC;
    }

    public void setSumEmplC(@Nonnull Double sumEmplC) {
        this.sumEmplC = sumEmplC;
    }

    @Nullable
    public Double getSumEmplTotal() {
        return sumEmplTotal;
    }

    public void setSumEmplTotal(@Nonnull Double sumEmplTotal) {
        this.sumEmplTotal = sumEmplTotal;
    }

    @Nullable
    public Integer getIsValidAllData() {
        return isValidAllData;
    }

    public void setIsValidAllData(@Nonnull Integer isValidAllData) {
        this.isValidAllData = isValidAllData;
    }

    @Nullable
    public Integer getIsValidData2() {
        return isValidData2;
    }

    public void setIsValidData2(@Nonnull Integer isValidData2) {
        this.isValidData2 = isValidData2;
    }

    @Nullable
    public Integer getIsTaNoneEmplrEmple() {
        return isTaNoneEmplrEmple;
    }

    public void setIsTaNoneEmplrEmple(@Nonnull Integer isTaNoneEmplrEmple) {
        this.isTaNoneEmplrEmple = isTaNoneEmplrEmple;
    }

    @Nullable
    public String getTaAfm() {
        return taAfm;
    }

    public void setTaAfm(@Nonnull String taAfm) {
        this.taAfm = taAfm;
    }

    @Nullable
    public String getTaFullname() {
        return taFullname;
    }

    public void setTaFullname(@Nonnull String taFullname) {
        this.taFullname = taFullname;
    }

    @Nullable
    public Integer getIsValidData1() {
        return isValidData1;
    }

    public void setIsValidData1(@Nonnull Integer isValidData1) {
        this.isValidData1 = isValidData1;
    }


    @Nullable
    public Integer getIsMilitary() {
        return isMilitary;
    }

    public void setIsMilitary(@Nonnull Integer isMilitary) {
        this.isMilitary = isMilitary;
    }

    @Nullable
    public Integer getIsExypp() {
        return isExypp;
    }

    public void setIsExypp(@Nonnull Integer isExypp) {
        this.isExypp = isExypp;
    }

    @Nullable
    public String getTaDegree() {
        return taDegree;
    }

    public void setTaDegree(@Nonnull String taDegree) {
        this.taDegree = taDegree;
    }

    @Nullable
    public String getTaVisitProgram() {
        return taVisitProgram;
    }

    public void setTaVisitProgram(@Nonnull String taVisitProgram) {
        this.taVisitProgram = taVisitProgram;
    }

    @Override
    public boolean equals(final Object other) {
        boolean equals = false;
        if (other instanceof CompanyDTO) {
            final CompanyDTO that = (CompanyDTO) other;
            equals = this.getId().equals(that.getId());
        }
        return equals;
    }
}
