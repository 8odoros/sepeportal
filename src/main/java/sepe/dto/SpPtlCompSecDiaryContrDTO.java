package sepe.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class SpPtlCompSecDiaryContrDTO implements Serializable {
    private String firstname;
    private String lastname;
    private String afm;
    private String addr;
    private Integer type;
    private Long contractor_specialty;
    private String birthplace;
    private Timestamp birthdate;
    private String card_number;
    private Long card_type;
    private String card_issuing_auth;
    private Timestamp date_start;
    private Timestamp date_end;

    public SpPtlCompSecDiaryContrDTO(String firstname, String lastname, String afm, String addr, Integer type, Long contractor_specialty,
                                     String birthplace, Timestamp birthdate, String card_number, Long card_type,
                                     String card_issuing_auth, Timestamp date_start, Timestamp date_end) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.afm = afm;
        this.addr = addr;
        this.type = type;
        this.contractor_specialty = contractor_specialty;
        this.birthplace = birthplace;
        this.birthdate = birthdate;
        this.card_number = card_number;
        this.card_type = card_type;
        this.card_issuing_auth = card_issuing_auth;
        this.date_start = date_start;
        this.date_end = date_end;
    }

    public SpPtlCompSecDiaryContrDTO(){
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

    public Long getContractor_specialty() { return contractor_specialty; }

    public void setContractor_specialty(Long contractor_specialty) {
        this.contractor_specialty = contractor_specialty;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    public Timestamp getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Timestamp birthdate) {
        this.birthdate = birthdate;
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public Long getCard_type() { return card_type; }

    public void setCard_type(Long card_type) {
        this.card_type = card_type;
    }

    public String getCard_issuing_auth() {
        return card_issuing_auth;
    }

    public void getCard_issuing_auth(String card_issuing_auth) {
        this.card_issuing_auth = card_issuing_auth;
    }

    public Timestamp getDate_start() {
        return date_start;
    }

    public void setDate_start(Timestamp date_start) {
        this.date_start = date_start;
    }

    public Timestamp getDate_end() {
        return date_end;
    }

    public void setDate_end(Timestamp date_end) {
        this.date_start = date_end;
    }
}
