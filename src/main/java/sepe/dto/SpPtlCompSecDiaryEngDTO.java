package sepe.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class SpPtlCompSecDiaryEngDTO implements Serializable {
    private String firstname;
    private String lastname;
    private String afm;
    private String engineerSpeciality;
    private String teeNum;
    private String addr;
    private String cardNumber;
    private Long cardType;
    private Timestamp birthdate;
    private String birthplace;
    private String cardIssuingAuth;

    public SpPtlCompSecDiaryEngDTO(String firstname, String lastname, String afm, String engineerSpeciality,
                                   String teeNum, String addr, String cardNumber, Long cardType,
                                   Timestamp birthdate, String birthplace, String cardIssuingAuth) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.afm = afm;
        this.engineerSpeciality = engineerSpeciality;
        this.teeNum = teeNum;
        this.addr = addr;
        this.cardNumber = cardNumber;
        this.cardType = cardType;
        this.birthdate = birthdate;
        this.birthplace = birthplace;
        this.cardIssuingAuth = cardIssuingAuth;
    }

    public SpPtlCompSecDiaryEngDTO(){
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

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Long getCardType() {
        return cardType;
    }

    public void setCardType(Long cardType) {
        this.cardType = cardType;
    }

    public Timestamp getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Timestamp birthdate) {
        this.birthdate = birthdate;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    public String getCardIssuingAuth() {
        return cardIssuingAuth;
    }

    public void setCardIssuingAuth(String cardIssuingAuth) {
        this.cardIssuingAuth = cardIssuingAuth;
    }
}
