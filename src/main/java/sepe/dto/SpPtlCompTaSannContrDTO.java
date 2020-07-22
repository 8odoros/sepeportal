package sepe.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class SpPtlCompTaSannContrDTO implements Serializable {
    private String firstname;
    private String lastname;
    private String afm;
    private String addr;
    private Integer type;
    private Long contractorSpecialty;
    private String birthplace;
    private Timestamp birthdate;
    private String cardNumber;
    private Long cardType;
    private String cardIssuingAuth;

    public SpPtlCompTaSannContrDTO(String firstname, String lastname, String afm, String addr, Integer type,
                                   Long contractorSpecialty, String birthplace, Timestamp birthdate,
                                   String cardNumber, Long cardType, String cardIssuingAuth) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.afm = afm;
        this.addr = addr;
        this.type = type;
        this.contractorSpecialty = contractorSpecialty;
        this.birthplace = birthplace;
        this.birthdate = birthdate;
        this.cardNumber = cardNumber;
        this.cardType = cardType;
        this.cardIssuingAuth = cardIssuingAuth;
    }

    public SpPtlCompTaSannContrDTO(){
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

    public Long getContractorSpecialty() { return contractorSpecialty; }

    public void setContractorSpecialty(Long contractorSpecialty) {
        this.contractorSpecialty = contractorSpecialty;
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

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Long getCardType() { return cardType; }

    public void setCardType(Long cardType) {
        this.cardType = cardType;
    }

    public String getCardIssuingAuth() {
        return cardIssuingAuth;
    }

    public void setCardIssuingAuth(String cardIssuingAuth) {
        this.cardIssuingAuth = cardIssuingAuth;
    }
}
