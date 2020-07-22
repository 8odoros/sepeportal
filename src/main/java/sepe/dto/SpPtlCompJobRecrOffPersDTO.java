package sepe.dto;

import java.io.Serializable;

/**
 * Created by Annita on 5/2/2015.
 */
public class SpPtlCompJobRecrOffPersDTO implements Serializable {
    private Integer incNum;
    private String empFirstname;
    private String empLastname;
    private String empCardNumber;
    private String empSpeciality;
    private Long empCategoryId;
    private Long empEduLevelId;
    private String empAfm;
    private String empAmka;
    private String empSex;
    private String empAge;
    private Integer empPlacementNum;
    private String compAfm;
    private String compTitle;
    private String compAddr;
    private Long empCardType;

    public SpPtlCompJobRecrOffPersDTO(
            Integer incNum,
            String empFirstname,
            String empLastname,
            String empCardNumber,
            String empSpeciality,
            Long empCategoryId,
            Long empEduLevelId,
            String empAfm,
            String empAmka,
            String empSex,
            String empAge,
            Integer empPlacementNum,
            String compAfm,
            String compTitle,
            String compAddr,
            Long empCardType
    ) {
        this.incNum = incNum;
        this.empFirstname = empFirstname;
        this.empLastname = empLastname;
        this.empCardNumber = empCardNumber;
        this.empSpeciality = empSpeciality;
        this.empCategoryId = empCategoryId;
        this.empEduLevelId = empEduLevelId;
        this.empAfm = empAfm;
        this.empAmka = empAmka;
        this.empSex = empSex;
        this.empAge = empAge;
        this.empPlacementNum = empPlacementNum;
        this.compAfm = compAfm;
        this.compTitle = compTitle;
        this.compAddr = compAddr;
        this.empCardType = empCardType;
    }

    public SpPtlCompJobRecrOffPersDTO(){
    }

    public Integer getIncNum() {
        return incNum;
    }

    public void setIncNum(Integer incNum) {
        this.incNum = incNum;
    }

    public String getEmpFirstname() {
        return empFirstname;
    }

    public void setEmpFirstname(String empFirstname) {
        this.empFirstname = empFirstname;
    }

    public String getEmpLastname() {
        return empLastname;
    }

    public void setEmpLastname(String empLastname) {
        this.empLastname = empLastname;
    }

    public String getEmpCardNumber() {
        return empCardNumber;
    }

    public void setEmpCardNumber(String empCardNumber) {
        this.empCardNumber = empCardNumber;
    }

    public String getEmpSpeciality() {
        return empSpeciality;
    }

    public void setEmpSpeciality(String empSpeciality) {
        this.empSpeciality = empSpeciality;
    }

    public Long getEmpCategoryId() { return empCategoryId; }

    public void setEmpCategoryId(Long empCategoryId) {
        this.empCategoryId = empCategoryId;
    }

    public Long getEmpEduLevelId() {
        return empEduLevelId;
    }

    public void setEmpEduLevelId(Long empEduLevelId) {
        this.empEduLevelId = empEduLevelId;
    }

    public String getEmpAfm() {
        return empAfm;
    }

    public void setEmpAfm(String empAfm) {
        this.empAfm = empAfm;
    }

    public String getEmpAmka() {
        return empAmka;
    }

    public void setEmpAmka(String empAmka) { this.empAmka = empAmka; }

    public String getEmpSex() {
        return empSex;
    }

    public void setEmpSex(String empSex) {
        this.empSex = empSex;
    }

    public String getEmpAge() {
        return empAge;
    }

    public void setEmpAge(String empAge) {
        this.empAge = empAge;
    }

    public Integer getEmpPlacementNum() {
        return empPlacementNum;
    }

    public void setEmpPlacementNum(Integer empPlacementNum) {
        this.empPlacementNum = empPlacementNum;
    }

    public String getCompAfm() { return compAfm; }

    public void setCompAfm(String compAfm) {
        this.compAfm = compAfm;
    }

    public String getCompTitle() {
        return compTitle;
    }

    public void setCompTitle(String compTitle) {
        this.compTitle = compTitle;
    }

    public String getCompAddr() {
        return compAddr;
    }

    public void setCompAddr(String compAddr) {
        this.compAddr = compAddr;
    }

    public Long getEmpCardType() {
        return empCardType;
    }

    public void setEmpCardType(Long empCardType) {
        this.empCardType = empCardType;
    }
}
