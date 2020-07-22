package sepe.dto;

import java.io.Serializable;

public class SpPtlCompProjAnnContrDTO implements Serializable {
    private String firstname;
    private String lastname;
    private String afm;
    private String addr;
    private Integer type;
    private String phone;


    public SpPtlCompProjAnnContrDTO(String firstname, String lastname, String afm, String addr, Integer type, String phone) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.afm = afm;
        this.addr = addr;
        this.type = type;
        this.phone = phone;
    }

    public SpPtlCompProjAnnContrDTO(){
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

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public Integer getType() { return type; }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
