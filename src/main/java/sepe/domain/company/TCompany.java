package sepe.domain.company;

import javax.persistence.*;

@Entity
@Table(name = "SP_PTL_COMPANY", schema = "SP_PTL", catalog = "")
public class TCompany {
    private Long id;
    private String name;
    private String title;
    private String afm;
    private String ame;
    private Long userId;
    private Integer isAssociation;
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

    @Id
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "TITLE")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "AFM")
    public String getAfm() {
        return afm;
    }

    public void setAfm(String afm) {
        this.afm = afm;
    }

    @Basic
    @Column(name = "AME")
    public String getAme() {
        return ame;
    }

    public void setAme(String ame) {
        this.ame = ame;
    }

    @Basic
    @Column(name = "USER_ID")
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "IS_ASSOCIATION")
    public Integer getIsAssociation() {
        return isAssociation;
    }

    public void setIsAssociation(Integer isAssociation) {
        this.isAssociation = isAssociation;
    }

    @Basic
    @Column(name = "SUM_EMPL_A")
    public Double getSumEmplA() {
        return sumEmplA;
    }

    public void setSumEmplA(Double sumEmplA) {
        this.sumEmplA = sumEmplA;
    }

    @Basic
    @Column(name = "SUM_EMPL_B")
    public Double getSumEmplB() {
        return sumEmplB;
    }

    public void setSumEmplB(Double sumEmplB) {
        this.sumEmplB = sumEmplB;
    }

    @Basic
    @Column(name = "SUM_EMPL_C")
    public Double getSumEmplC() {
        return sumEmplC;
    }

    public void setSumEmplC(Double sumEmplC) {
        this.sumEmplC = sumEmplC;
    }

    @Basic
    @Column(name = "SUM_EMPL_TOTAL")
    public Double getSumEmplTotal() {
        return sumEmplTotal;
    }

    public void setSumEmplTotal(Double sumEmplTotal) {
        this.sumEmplTotal = sumEmplTotal;
    }

    @Basic
    @Column(name = "IS_VALID_ALL_DATA")
    public Integer getIsValidAllData() {
        return isValidAllData;
    }

    public void setIsValidAllData(Integer isValidAllData) {
        this.isValidAllData = isValidAllData;
    }

    @Basic
    @Column(name = "IS_TA_NONE_EMPLR_EMPLE")
    public Integer getIsTaNoneEmplrEmple() {
        return isTaNoneEmplrEmple;
    }

    public void setIsTaNoneEmplrEmple(Integer isTaNoneEmplrEmple) {
        this.isTaNoneEmplrEmple = isTaNoneEmplrEmple;
    }

    @Basic
    @Column(name = "TA_AFM")
    public String getTaAfm() {
        return taAfm;
    }

    public void setTaAfm(String taAfm) {
        this.taAfm = taAfm;
    }

    @Basic
    @Column(name = "TA_FULLNAME")
    public String getTaFullname() {
        return taFullname;
    }

    public void setTaFullname(String taFullname) {
        this.taFullname = taFullname;
    }

    @Basic
    @Column(name = "IS_VALID_DATA_1")
    public Integer getIsValidData1() {
        return isValidData1;
    }

    public void setIsValidData1(Integer isValidData1) {
        this.isValidData1 = isValidData1;
    }

    @Basic
    @Column(name = "IS_EXYPP")
    public Integer getIsExypp() {
        return isExypp;
    }

    public void setIsExypp(Integer isExypp) {
        this.isExypp = isExypp;
    }

    @Basic
    @Column(name = "IS_MILITARY")
    public Integer getIsMilitary() {
        return isMilitary;
    }

    public void setIsMilitary(Integer isMilitary) {
        this.isMilitary = isMilitary;
    }

    @Basic
    @Column(name = "TA_DEGREE")
    public String getTaDegree() {
        return taDegree;
    }

    public void setTaDegree(String taDegree) {
        this.taDegree = taDegree;
    }

    @Basic
    @Column(name = "TA_VISIT_PROGRAM")
    public String getTaVisitProgram() {
        return taVisitProgram;
    }

    public void setTaVisitProgram(String taVisitProgram) {
        this.taVisitProgram = taVisitProgram;
    }


    @Basic
    @Column(name = "IS_VALID_DATA_2")
    public Integer getIsValidData2() {
        return isValidData2;
    }

    public void setIsValidData2(Integer isValidData2) {
        this.isValidData2 = isValidData2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TCompany tCompany = (TCompany) o;

        if (!id.equals(tCompany.id)) return false;
        if (name != null ? !name.equals(tCompany.name) : tCompany.name != null) return false;
        if (title != null ? !title.equals(tCompany.title) : tCompany.title != null) return false;
        if (afm != null ? !afm.equals(tCompany.afm) : tCompany.afm != null) return false;
        if (ame != null ? !ame.equals(tCompany.ame) : tCompany.ame != null) return false;
        if (userId != null ? !userId.equals(tCompany.userId) : tCompany.userId != null) return false;
        if (isAssociation != null ? !isAssociation.equals(tCompany.isAssociation) : tCompany.isAssociation != null)
            return false;
        if (sumEmplA != null ? !sumEmplA.equals(tCompany.sumEmplA) : tCompany.sumEmplA != null) return false;
        if (sumEmplB != null ? !sumEmplB.equals(tCompany.sumEmplB) : tCompany.sumEmplB != null) return false;
        if (sumEmplC != null ? !sumEmplC.equals(tCompany.sumEmplC) : tCompany.sumEmplC != null) return false;
        if (sumEmplTotal != null ? !sumEmplTotal.equals(tCompany.sumEmplTotal) : tCompany.sumEmplTotal != null)
            return false;
        if (isValidAllData != null ? !isValidAllData.equals(tCompany.isValidAllData) : tCompany.isValidAllData != null)
            return false;
        if (isTaNoneEmplrEmple != null ? !isTaNoneEmplrEmple.equals(tCompany.isTaNoneEmplrEmple) : tCompany.isTaNoneEmplrEmple != null)
            return false;
        if (taAfm != null ? !taAfm.equals(tCompany.taAfm) : tCompany.taAfm != null) return false;
        if (taFullname != null ? !taFullname.equals(tCompany.taFullname) : tCompany.taFullname != null) return false;
        if (isValidData1 != null ? !isValidData1.equals(tCompany.isValidData1) : tCompany.isValidData1 != null)
            return false;

        if (isExypp != null ? !isExypp.equals(tCompany.isExypp) : tCompany.isExypp != null)
            return false;
        if (isMilitary != null ? !isMilitary.equals(tCompany.isMilitary) : tCompany.isMilitary != null) return false;
        if (taDegree != null ? !taDegree.equals(tCompany.taDegree) : tCompany.taDegree != null) return false;
        if (taVisitProgram != null ? !taVisitProgram.equals(tCompany.taVisitProgram) : tCompany.taVisitProgram != null) return false;
        //return !(isValidData2 != null ? !isValidData2.equals(tCompany.isValidData2) : tCompany.isValidData2 != null);
        if (isValidData2 != null ? !isValidData2.equals(tCompany.isValidData2) : tCompany.isValidData2 != null) return false;

        return true;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (afm != null ? afm.hashCode() : 0);
        result = 31 * result + (ame != null ? ame.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (isAssociation != null ? isAssociation.hashCode() : 0);
        result = 31 * result + (sumEmplA != null ? sumEmplA.hashCode() : 0);
        result = 31 * result + (sumEmplB != null ? sumEmplB.hashCode() : 0);
        result = 31 * result + (sumEmplC != null ? sumEmplC.hashCode() : 0);
        result = 31 * result + (sumEmplTotal != null ? sumEmplTotal.hashCode() : 0);
        result = 31 * result + (isValidAllData != null ? isValidAllData.hashCode() : 0);
        result = 31 * result + (isTaNoneEmplrEmple != null ? isTaNoneEmplrEmple.hashCode() : 0);
        result = 31 * result + (taAfm != null ? taAfm.hashCode() : 0);
        result = 31 * result + (taFullname != null ? taFullname.hashCode() : 0);
        result = 31 * result + (isValidData1 != null ? isValidData1.hashCode() : 0);
        result = 31 * result + (isValidData2 != null ? isValidData2.hashCode() : 0);
        result = 31 * result + (isExypp != null ? isExypp.hashCode() : 0);
        result = 31 * result + (isMilitary != null ? isMilitary.hashCode() : 0);
        result = 31 * result + (taDegree != null ? taDegree.hashCode() : 0);
        result = 31 * result + (taVisitProgram != null ? taVisitProgram.hashCode() : 0);
        return result;
    }

    /*    @Override
 public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    TCompany that = (TCompany) o;

    if (id != that.id) return false;
    if (afm != null ? !afm.equals(that.afm) : that.afm != null) return false;
    if (ame != null ? !ame.equals(that.ame) : that.ame != null) return false;
    if (name != null ? !name.equals(that.name) : that.name != null) return false;
    if (title != null ? !title.equals(that.title) : that.title != null) return false;
    if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
    if (isAssociation != null ? !isAssociation.equals(that.isAssociation) : that.isAssociation != null) return false;

    return true;
}

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (afm != null ? afm.hashCode() : 0);
        result = 31 * result + (ame != null ? ame.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (isAssociation != null ? isAssociation.hashCode() : 0);
        return result;
    }*/
}
