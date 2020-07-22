package sepe.dto;

import java.io.Serializable;

public class SpPtlCompProjAnnEngDTO implements Serializable {
    private String firstname;
    private String lastname;
    private String afm;
    private String engineerSpeciality;
    private Integer invlolvementType;
    private String teeNum;
    private String addr;

    public SpPtlCompProjAnnEngDTO(String firstname, String lastname, String afm, String engineerSpeciality, Integer invlolvementType, String teeNum, String addr) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.afm = afm;
        this.engineerSpeciality = engineerSpeciality;
        this.invlolvementType = invlolvementType;
        this.teeNum = teeNum;
        this.addr = addr;
    }

    public SpPtlCompProjAnnEngDTO(){
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAfm() {
        return afm;
    }

    public void setAfm(String afm) {
        this.afm = afm;
    }

    public String getEngineerSpeciality() {
        return engineerSpeciality;
    }

    public void setEngineerSpeciality(String engineerSpeciality) {
        this.engineerSpeciality = engineerSpeciality;
    }

    public Integer getInvlolvementType() {
        return invlolvementType;
    }

    public void setInvlolvementType(Integer invlolvementType) {
        this.invlolvementType = invlolvementType;
    }

    public String getTeeNum() {
        return teeNum;
    }

    public void setTeeNum(String teeNum) {
        this.teeNum = teeNum;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }
}
