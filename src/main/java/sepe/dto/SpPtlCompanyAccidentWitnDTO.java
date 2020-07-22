package sepe.dto;

import java.io.Serializable;

/**
 * Created by Annita on 5/2/2015.
 */
public class SpPtlCompanyAccidentWitnDTO implements Serializable {
    private Long accidentId;
    private Integer incNum;
    private String firstname;
    private String lastname;
    private String addr;
    private String addrTk;
    private String typeId;
    private String citizenshipCd;
    private String phone;
    private Long companyId;

    public SpPtlCompanyAccidentWitnDTO(
            Integer incNum,
            String firstname,
            String lastname,
            String addr,
            String addrTk,
            String typeId,
            String citizenshipCd,
            String phone
    ) {
        this.incNum = incNum;
        this.firstname = firstname;
        this.lastname = lastname;
        this.addr = addr;
        this.addrTk = addrTk;
        this.typeId = typeId;
        this.citizenshipCd = citizenshipCd;
        this.phone = phone;
    }

    public SpPtlCompanyAccidentWitnDTO(){
    }

    public Integer getIncNum() {
        return incNum;
    }

    public void setIncNum(Integer incNum) {
        this.incNum = incNum;
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

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getAddrTk() {
        return addrTk;
    }

    public void setAddrTk(String addrTk) {
        this.addrTk = addrTk;
    }

    public String getTypeId() { return typeId; }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getCitizenshipCd() {
        return citizenshipCd;
    }

    public void setCitizenshipCd(String citizenshipCd) {
        this.citizenshipCd = citizenshipCd;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
