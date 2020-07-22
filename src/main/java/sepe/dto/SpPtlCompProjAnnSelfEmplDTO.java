package sepe.dto;

import java.io.Serializable;

public class SpPtlCompProjAnnSelfEmplDTO  implements Serializable {
    private String firstname;
    private String lastname;
    private String afm;
    private String addr;

    public SpPtlCompProjAnnSelfEmplDTO(String firstname, String lastname, String afm, String addr) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.afm = afm;
        this.addr = addr;
    }

    public SpPtlCompProjAnnSelfEmplDTO(){
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
}
