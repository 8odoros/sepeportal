package sepe.dto;

import javax.annotation.Nullable;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by Annita on 5/2/2015.
 */
public class SpPtlCompSundayPmtPersDTO implements Serializable {
    private Integer incNum;
    private String empFirstname;
    private String empLastname;
    private String empIka;
    private String empSpeciality;
    private String empWorkHourStart;
    private String empWorkHourStop;
    private Timestamp empAlternateRestingDay;
    private String inspectorComments;
    private String empAfm;
    private String empAmka;
    private String empSex;

    public SpPtlCompSundayPmtPersDTO(
            Integer incNum,
            String empFirstname,
            String empLastname,
            String empIka,
            String empSpeciality,
            String empWorkHourStart,
            String empWorkHourStop,
            @Nullable Timestamp empAlternateRestingDay,
            String inspectorComments,
            String empAfm,
            String empAmka,
            String empSex
    ) {
        this.incNum = incNum;
        this.empFirstname = empFirstname;
        this.empLastname = empLastname;
        this.empIka = empIka;
        this.empSpeciality = empSpeciality;
        this.empWorkHourStart = empWorkHourStart;
        this.empWorkHourStop = empWorkHourStop;
        this.empAlternateRestingDay = empAlternateRestingDay;
        this.inspectorComments = inspectorComments;
        this.empAfm = empAfm;
        this.empAmka = empAmka;
        this.empSex = empSex;
    }

    public SpPtlCompSundayPmtPersDTO(){
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

    public String getEmpLastname() {return empLastname;}

    public void setEmpLastname(String empLastname) {
        this.empLastname = empLastname;
    }

    public String getEmpIka() {
        return empIka;
    }

    public void setEmpIka(String empIka) {
        this.empIka = empIka;
    }

    public String getEmpSpeciality() {
        return empSpeciality;
    }

    public void setEmpSpeciality(String empSpeciality) {
        this.empSpeciality = empSpeciality;
    }

    public String getEmpWorkHourStart() { return empWorkHourStart; }

    public void setEmpWorkHourStart(String empWorkHourStart) {
        this.empWorkHourStart = empWorkHourStart;
    }

    public String getEmpWorkHourStop() {
        return empWorkHourStop;
    }

    public void setEmpWorkHourStop(String empWorkHourStop) {
        this.empWorkHourStop = empWorkHourStop;
    }

    @Nullable
    public Timestamp getEmpAlternateRestingDay() {
        return empAlternateRestingDay;
    }

    @Nullable
    public void setEmpAlternateRestingDay(Timestamp empAlternateRestingDay) {this.empAlternateRestingDay = empAlternateRestingDay; }

    public String getInspectorComments() {
        return inspectorComments;
    }

    public void setInspectorComments(String inspectorComments) {
        this.inspectorComments = inspectorComments;
    }

    public String getEmpAfm() { return empAfm; }

    public void setEmpAfm(String empAfm) {
        this.empAfm = empAfm;
    }

    public String getEmpAmka() {
        return empAmka;
    }

    public void setEmpAmka(String empAmka) {
        this.empAmka = empAmka;
    }

    public String getEmpSex() {
        return empSex;
    }

    public void setEmpSex(String empSex) {this.empSex = empSex; }

}
