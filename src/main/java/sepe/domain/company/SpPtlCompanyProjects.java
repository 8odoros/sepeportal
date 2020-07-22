package sepe.domain.company;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

/**
 * Created by Evangelos on 18/4/2015.
 */
@Entity
@Table(name = "SP_PTL_COMPANY_PROJECTS", schema = "SP_PTL", catalog = "")
public class SpPtlCompanyProjects {
    private Long id;
    private Set<SpPtlCompanyDailyCard> spPtlCompanyDailyCards;
    private Integer sepeDept;

    @Id
    @GeneratedValue
    @Column(name = "ID") // , nullable = false, insertable = false, updatable = true, precision = 0
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private Timestamp creationDate;

    @Basic
    @Column(name = "CREATION_DATE")
    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    private String licenceNumber;

    @Basic
    @Column(name = "LICENCE_NUMBER")
    public String getLicenceNumber() {
        return licenceNumber;
    }

    public void setLicenceNumber(String licenceNumber) {
        this.licenceNumber = licenceNumber;
    }

    private String projectAddr;

    @Basic
    @Column(name = "PROJECT_ADDR")
    public String getProjectAddr() {
        return projectAddr;
    }

    public void setProjectAddr(String projectAddr) {
        this.projectAddr = projectAddr;
    }

    private String projectAddrTk;

    @Basic
    @Column(name = "PROJECT_ADDR_TK")
    public String getProjectAddrTk() {
        return projectAddrTk;
    }

    public void setProjectAddrTk(String projectAddrTk) {
        this.projectAddrTk = projectAddrTk;
    }

    private String projectAddrP;

    @Basic
    @Column(name = "PROJECT_ADDR_P")
    public String getProjectAddrP() {
        return projectAddrP;
    }

    public void setProjectAddrP(String projectAddrP) {
        this.projectAddrP = projectAddrP;
    }

    private String projectAddrPe;

    @Basic
    @Column(name = "PROJECT_ADDR_PE")
    public String getProjectAddrPe() {
        return projectAddrPe;
    }

    public void setProjectAddrPe(String projectAddrPe) {
        this.projectAddrPe = projectAddrPe;
    }

    private String projectAddrD;

    @Basic
    @Column(name = "PROJECT_ADDR_D")
    public String getProjectAddrD() {
        return projectAddrD;
    }

    public void setProjectAddrD(String projectAddrD) {
        this.projectAddrD = projectAddrD;
    }

    private String projectAddrK;

    @Basic
    @Column(name = "PROJECT_ADDR_K")
    public String getProjectAddrK() {
        return projectAddrK;
    }

    public void setProjectAddrK(String projectAddrK) {
        this.projectAddrK = projectAddrK;
    }

    private String projectDescription;

    @Basic
    @Column(name = "PROJECT_DESCRIPTION")
    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    private String ikaDept;

    @Basic
    @Column(name = "IKA_DEPT")
    public String getIkaDept() {
        return ikaDept;
    }

    public void setIkaDept(String ikaDept) {
        this.ikaDept = ikaDept;
    }

    private String genRecordNum;

    @Basic
    @Column(name = "GEN_RECORD_NUM")
    public String getGenRecordNum() {
        return genRecordNum;
    }

    public void setGenRecordNum(String genRecordNum) {
        this.genRecordNum = genRecordNum;
    }

    private String empName;

    @Basic
    @Column(name = "EMP_NAME")
    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    private String empSurname;

    @Basic
    @Column(name = "EMP_SURNAME")
    public String getEmpSurname() {
        return empSurname;
    }

    public void setEmpSurname(String empSurname) {
        this.empSurname = empSurname;
    }

    private String empBirthPlace;

    @Basic
    @Column(name = "EMP_BIRTH_PLACE")
    public String getEmpBirthPlace() {
        return empBirthPlace;
    }

    public void setEmpBirthPlace(String empBirthPlace) {
        this.empBirthPlace = empBirthPlace;
    }

    private String empDateOfBirth;

    @Basic
    @Column(name = "EMP_DATE_OF_BIRTH")
    public String getEmpDateOfBirth() {
        return empDateOfBirth;
    }

    public void setEmpDateOfBirth(String empDateOfBirth) {
        this.empDateOfBirth = empDateOfBirth;
    }

    private String empHomeAddr;

    @Basic
    @Column(name = "EMP_HOME_ADDR")
    public String getEmpHomeAddr() {
        return empHomeAddr;
    }

    public void setEmpHomeAddr(String empHomeAddr) {
        this.empHomeAddr = empHomeAddr;
    }

    private Integer empCardType;

    @Basic
    @Column(name = "EMP_CARD_TYPE")
    public Integer getEmpCardType() {
        return empCardType;
    }

    public void setEmpCardType(Integer empCardType) {
        this.empCardType = empCardType;
    }

    private String empCardNum;

    @Basic
    @Column(name = "EMP_CARD_NUM")
    public String getEmpCardNum() {
        return empCardNum;
    }

    public void setEmpCardNum(String empCardNum) {
        this.empCardNum = empCardNum;
    }

    private String empCardIssuingAuth;

    @Basic
    @Column(name = "EMP_CARD_ISSUING_AUTH")
    public String getEmpCardIssuingAuth() {
        return empCardIssuingAuth;
    }

    public void setEmpCardIssuingAuth(String empCardIssuingAuth) {
        this.empCardIssuingAuth = empCardIssuingAuth;
    }

    private String empAfm;

    @Basic
    @Column(name = "EMP_AFM")
    public String getEmpAfm() {
        return empAfm;
    }

    public void setEmpAfm(String empAfm) {
        this.empAfm = empAfm;
    }

    private String contrName;

    @Basic
    @Column(name = "CONTR_NAME")
    public String getContrName() {
        return contrName;
    }

    public void setContrName(String contrName) {
        this.contrName = contrName;
    }

    private String contrSurname;

    @Basic
    @Column(name = "CONTR_SURNAME")
    public String getContrSurname() {
        return contrSurname;
    }

    public void setContrSurname(String contrSurname) {
        this.contrSurname = contrSurname;
    }

    private String contrBirthPlace;

    @Basic
    @Column(name = "CONTR_BIRTH_PLACE")
    public String getContrBirthPlace() {
        return contrBirthPlace;
    }

    public void setContrBirthPlace(String contrBirthPlace) {
        this.contrBirthPlace = contrBirthPlace;
    }

    private String contrDateOfBirth;

    @Basic
    @Column(name = "CONTR_DATE_OF_BIRTH")
    public String getContrDateOfBirth() {
        return contrDateOfBirth;
    }

    public void setContrDateOfBirth(String contrDateOfBirth) {
        this.contrDateOfBirth = contrDateOfBirth;
    }

    private String contrHomeAddr;

    @Basic
    @Column(name = "CONTR_HOME_ADDR")
    public String getContrHomeAddr() {
        return contrHomeAddr;
    }

    public void setContrHomeAddr(String contrHomeAddr) {
        this.contrHomeAddr = contrHomeAddr;
    }

    private String contrCardType;

    @Basic
    @Column(name = "CONTR_CARD_TYPE")
    public String getContrCardType() {
        return contrCardType;
    }

    public void setContrCardType(String contrCardType) {
        this.contrCardType = contrCardType;
    }

    private String contrCardNum;

    @Basic
    @Column(name = "CONTR_CARD_NUM")
    public String getContrCardNum() {
        return contrCardNum;
    }

    public void setContrCardNum(String contrCardNum) {
        this.contrCardNum = contrCardNum;
    }

    private String contrCardIssuingAuth;

    @Basic
    @Column(name = "CONTR_CARD_ISSUING_AUTH")
    public String getContrCardIssuingAuth() {
        return contrCardIssuingAuth;
    }

    public void setContrCardIssuingAuth(String contrCardIssuingAuth) {
        this.contrCardIssuingAuth = contrCardIssuingAuth;
    }

    private String contrAfm;

    @Basic
    @Column(name = "CONTR_AFM")
    public String getContrAfm() {
        return contrAfm;
    }

    public void setContrAfm(String contrAfm) {
        this.contrAfm = contrAfm;
    }

    private String subContrName;

    @Basic
    @Column(name = "SUB_CONTR_NAME")
    public String getSubContrName() {
        return subContrName;
    }

    public void setSubContrName(String subContrName) {
        this.subContrName = subContrName;
    }

    private String subContrSurname;

    @Basic
    @Column(name = "SUB_CONTR_SURNAME")
    public String getSubContrSurname() {
        return subContrSurname;
    }

    public void setSubContrSurname(String subContrSurname) {
        this.subContrSurname = subContrSurname;
    }

    private String subContrBirthPlace;

    @Basic
    @Column(name = "SUB_CONTR_BIRTH_PLACE")
    public String getSubContrBirthPlace() {
        return subContrBirthPlace;
    }

    public void setSubContrBirthPlace(String subContrBirthPlace) {
        this.subContrBirthPlace = subContrBirthPlace;
    }

    private String subContrDateOfBirth;

    @Basic
    @Column(name = "SUB_CONTR_DATE_OF_BIRTH")
    public String getSubContrDateOfBirth() {
        return subContrDateOfBirth;
    }

    public void setSubContrDateOfBirth(String subContrDateOfBirth) {
        this.subContrDateOfBirth = subContrDateOfBirth;
    }

    private String subContrHomeAddr;

    @Basic
    @Column(name = "SUB_CONTR_HOME_ADDR")
    public String getSubContrHomeAddr() {
        return subContrHomeAddr;
    }

    public void setSubContrHomeAddr(String subContrHomeAddr) {
        this.subContrHomeAddr = subContrHomeAddr;
    }

    private String subContrCardType;

    @Basic
    @Column(name = "SUB_CONTR_CARD_TYPE")
    public String getSubContrCardType() {
        return subContrCardType;
    }

    public void setSubContrCardType(String subContrCardType) {
        this.subContrCardType = subContrCardType;
    }

    private String subContrCardNum;

    @Basic
    @Column(name = "SUB_CONTR_CARD_NUM")
    public String getSubContrCardNum() {
        return subContrCardNum;
    }

    public void setSubContrCardNum(String subContrCardNum) {
        this.subContrCardNum = subContrCardNum;
    }

    private String subContrCardIssuingAuth;

    @Basic
    @Column(name = "SUB_CONTR_CARD_ISSUING_AUTH")
    public String getSubContrCardIssuingAuth() {
        return subContrCardIssuingAuth;
    }

    public void setSubContrCardIssuingAuth(String subContrCardIssuingAuth) {
        this.subContrCardIssuingAuth = subContrCardIssuingAuth;
    }

    private String subContrAfm;

    @Basic
    @Column(name = "SUB_CONTR_AFM")
    public String getSubContrAfm() {
        return subContrAfm;
    }

    public void setSubContrAfm(String subContrAfm) {
        this.subContrAfm = subContrAfm;
    }

    private String housedCompanyTile;

    @Basic
    @Column(name = "HOUSED_COMPANY_TILE")
    public String getHousedCompanyTile() {
        return housedCompanyTile;
    }

    public void setHousedCompanyTile(String housedCompanyTile) {
        this.housedCompanyTile = housedCompanyTile;
    }

    private String housedCompanyAddr;

    @Basic
    @Column(name = "HOUSED_COMPANY_ADDR")
    public String getHousedCompanyAddr() {
        return housedCompanyAddr;
    }

    public void setHousedCompanyAddr(String housedCompanyAddr) {
        this.housedCompanyAddr = housedCompanyAddr;
    }

    private String housedCompanyAddrTk;

    @Basic
    @Column(name = "HOUSED_COMPANY_ADDR_TK")
    public String getHousedCompanyAddrTk() {
        return housedCompanyAddrTk;
    }

    public void setHousedCompanyAddrTk(String housedCompanyAddrTk) {
        this.housedCompanyAddrTk = housedCompanyAddrTk;
    }

    private String housedCompanyAddrP;

    @Basic
    @Column(name = "HOUSED_COMPANY_ADDR_P")
    public String getHousedCompanyAddrP() {
        return housedCompanyAddrP;
    }

    public void setHousedCompanyAddrP(String housedCompanyAddrP) {
        this.housedCompanyAddrP = housedCompanyAddrP;
    }

    private String housedCompanyAddrPe;

    @Basic
    @Column(name = "HOUSED_COMPANY_ADDR_PE")
    public String getHousedCompanyAddrPe() {
        return housedCompanyAddrPe;
    }

    public void setHousedCompanyAddrPe(String housedCompanyAddrPe) {
        this.housedCompanyAddrPe = housedCompanyAddrPe;
    }

    private String housedCompanyAddrD;

    @Basic
    @Column(name = "HOUSED_COMPANY_ADDR_D")
    public String getHousedCompanyAddrD() {
        return housedCompanyAddrD;
    }

    public void setHousedCompanyAddrD(String housedCompanyAddrD) {
        this.housedCompanyAddrD = housedCompanyAddrD;
    }

    private String housedCompanyAddrK;

    @Basic
    @Column(name = "HOUSED_COMPANY_ADDR_K")
    public String getHousedCompanyAddrK() {
        return housedCompanyAddrK;
    }

    public void setHousedCompanyAddrK(String housedCompanyAddrK) {
        this.housedCompanyAddrK = housedCompanyAddrK;
    }

    @Basic
    @javax.persistence.Column(name = "SEPE_DEPT", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getSepeDept() {
        return sepeDept;
    }

    public void setSepeDept(Integer sepeDept) {
        this.sepeDept = sepeDept;
    }

    private String compAme;

    @Basic
    @javax.persistence.Column(name = "COMP_AME")
    public String getCompAme() {
        return compAme;
    }

    public void setCompAme(String compAme) {
        this.compAme = compAme;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpPtlCompanyProjects that = (SpPtlCompanyProjects) o;

        if (id != that.id) return false;
        if (compAme != null ? !compAme.equals(that.compAme) : that.compAme != null) return false;
        if (contrAfm != null ? !contrAfm.equals(that.contrAfm) : that.contrAfm != null) return false;
        if (contrBirthPlace != null ? !contrBirthPlace.equals(that.contrBirthPlace) : that.contrBirthPlace != null)
            return false;
        if (contrCardIssuingAuth != null ? !contrCardIssuingAuth.equals(that.contrCardIssuingAuth) : that.contrCardIssuingAuth != null)
            return false;
        if (contrCardNum != null ? !contrCardNum.equals(that.contrCardNum) : that.contrCardNum != null) return false;
        if (contrCardType != null ? !contrCardType.equals(that.contrCardType) : that.contrCardType != null)
            return false;
        if (contrDateOfBirth != null ? !contrDateOfBirth.equals(that.contrDateOfBirth) : that.contrDateOfBirth != null)
            return false;
        if (contrHomeAddr != null ? !contrHomeAddr.equals(that.contrHomeAddr) : that.contrHomeAddr != null)
            return false;
        if (contrName != null ? !contrName.equals(that.contrName) : that.contrName != null) return false;
        if (contrSurname != null ? !contrSurname.equals(that.contrSurname) : that.contrSurname != null) return false;
        if (creationDate != null ? !creationDate.equals(that.creationDate) : that.creationDate != null) return false;
        if (empAfm != null ? !empAfm.equals(that.empAfm) : that.empAfm != null) return false;
        if (empBirthPlace != null ? !empBirthPlace.equals(that.empBirthPlace) : that.empBirthPlace != null)
            return false;
        if (empCardIssuingAuth != null ? !empCardIssuingAuth.equals(that.empCardIssuingAuth) : that.empCardIssuingAuth != null)
            return false;
        if (empCardNum != null ? !empCardNum.equals(that.empCardNum) : that.empCardNum != null) return false;
        if (empCardType != null ? !empCardType.equals(that.empCardType) : that.empCardType != null) return false;
        if (empDateOfBirth != null ? !empDateOfBirth.equals(that.empDateOfBirth) : that.empDateOfBirth != null)
            return false;
        if (empHomeAddr != null ? !empHomeAddr.equals(that.empHomeAddr) : that.empHomeAddr != null) return false;
        if (empName != null ? !empName.equals(that.empName) : that.empName != null) return false;
        if (empSurname != null ? !empSurname.equals(that.empSurname) : that.empSurname != null) return false;
        if (genRecordNum != null ? !genRecordNum.equals(that.genRecordNum) : that.genRecordNum != null) return false;
        if (housedCompanyAddr != null ? !housedCompanyAddr.equals(that.housedCompanyAddr) : that.housedCompanyAddr != null)
            return false;
        if (housedCompanyAddrD != null ? !housedCompanyAddrD.equals(that.housedCompanyAddrD) : that.housedCompanyAddrD != null)
            return false;
        if (housedCompanyAddrK != null ? !housedCompanyAddrK.equals(that.housedCompanyAddrK) : that.housedCompanyAddrK != null)
            return false;
        if (housedCompanyAddrP != null ? !housedCompanyAddrP.equals(that.housedCompanyAddrP) : that.housedCompanyAddrP != null)
            return false;
        if (housedCompanyAddrPe != null ? !housedCompanyAddrPe.equals(that.housedCompanyAddrPe) : that.housedCompanyAddrPe != null)
            return false;
        if (housedCompanyAddrTk != null ? !housedCompanyAddrTk.equals(that.housedCompanyAddrTk) : that.housedCompanyAddrTk != null)
            return false;
        if (housedCompanyTile != null ? !housedCompanyTile.equals(that.housedCompanyTile) : that.housedCompanyTile != null)
            return false;
        if (ikaDept != null ? !ikaDept.equals(that.ikaDept) : that.ikaDept != null) return false;
        if (licenceNumber != null ? !licenceNumber.equals(that.licenceNumber) : that.licenceNumber != null)
            return false;
        if (projectAddr != null ? !projectAddr.equals(that.projectAddr) : that.projectAddr != null) return false;
        if (projectAddrD != null ? !projectAddrD.equals(that.projectAddrD) : that.projectAddrD != null) return false;
        if (projectAddrK != null ? !projectAddrK.equals(that.projectAddrK) : that.projectAddrK != null) return false;
        if (projectAddrP != null ? !projectAddrP.equals(that.projectAddrP) : that.projectAddrP != null) return false;
        if (projectAddrPe != null ? !projectAddrPe.equals(that.projectAddrPe) : that.projectAddrPe != null)
            return false;
        if (projectAddrTk != null ? !projectAddrTk.equals(that.projectAddrTk) : that.projectAddrTk != null)
            return false;
        if (projectDescription != null ? !projectDescription.equals(that.projectDescription) : that.projectDescription != null)
            return false;
        if (subContrAfm != null ? !subContrAfm.equals(that.subContrAfm) : that.subContrAfm != null) return false;
        if (subContrBirthPlace != null ? !subContrBirthPlace.equals(that.subContrBirthPlace) : that.subContrBirthPlace != null)
            return false;
        if (subContrCardIssuingAuth != null ? !subContrCardIssuingAuth.equals(that.subContrCardIssuingAuth) : that.subContrCardIssuingAuth != null)
            return false;
        if (subContrCardNum != null ? !subContrCardNum.equals(that.subContrCardNum) : that.subContrCardNum != null)
            return false;
        if (subContrCardType != null ? !subContrCardType.equals(that.subContrCardType) : that.subContrCardType != null)
            return false;
        if (subContrDateOfBirth != null ? !subContrDateOfBirth.equals(that.subContrDateOfBirth) : that.subContrDateOfBirth != null)
            return false;
        if (subContrHomeAddr != null ? !subContrHomeAddr.equals(that.subContrHomeAddr) : that.subContrHomeAddr != null)
            return false;
        if (subContrName != null ? !subContrName.equals(that.subContrName) : that.subContrName != null) return false;
        if (subContrSurname != null ? !subContrSurname.equals(that.subContrSurname) : that.subContrSurname != null)
            return false;
        if (sepeDept != null ? !sepeDept.equals(that.sepeDept) : that.sepeDept != null)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        Long result = id;
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + (licenceNumber != null ? licenceNumber.hashCode() : 0);
        result = 31 * result + (projectAddr != null ? projectAddr.hashCode() : 0);
        result = 31 * result + (projectAddrTk != null ? projectAddrTk.hashCode() : 0);
        result = 31 * result + (projectAddrP != null ? projectAddrP.hashCode() : 0);
        result = 31 * result + (projectAddrPe != null ? projectAddrPe.hashCode() : 0);
        result = 31 * result + (projectAddrD != null ? projectAddrD.hashCode() : 0);
        result = 31 * result + (projectAddrK != null ? projectAddrK.hashCode() : 0);
        result = 31 * result + (projectDescription != null ? projectDescription.hashCode() : 0);
        result = 31 * result + (ikaDept != null ? ikaDept.hashCode() : 0);
        result = 31 * result + (genRecordNum != null ? genRecordNum.hashCode() : 0);
        result = 31 * result + (empName != null ? empName.hashCode() : 0);
        result = 31 * result + (empSurname != null ? empSurname.hashCode() : 0);
        result = 31 * result + (empBirthPlace != null ? empBirthPlace.hashCode() : 0);
        result = 31 * result + (empDateOfBirth != null ? empDateOfBirth.hashCode() : 0);
        result = 31 * result + (empHomeAddr != null ? empHomeAddr.hashCode() : 0);
        result = 31 * result + (empCardType != null ? empCardType.hashCode() : 0);
        result = 31 * result + (empCardNum != null ? empCardNum.hashCode() : 0);
        result = 31 * result + (empCardIssuingAuth != null ? empCardIssuingAuth.hashCode() : 0);
        result = 31 * result + (empAfm != null ? empAfm.hashCode() : 0);
        result = 31 * result + (contrName != null ? contrName.hashCode() : 0);
        result = 31 * result + (contrSurname != null ? contrSurname.hashCode() : 0);
        result = 31 * result + (contrBirthPlace != null ? contrBirthPlace.hashCode() : 0);
        result = 31 * result + (contrDateOfBirth != null ? contrDateOfBirth.hashCode() : 0);
        result = 31 * result + (contrHomeAddr != null ? contrHomeAddr.hashCode() : 0);
        result = 31 * result + (contrCardType != null ? contrCardType.hashCode() : 0);
        result = 31 * result + (contrCardNum != null ? contrCardNum.hashCode() : 0);
        result = 31 * result + (contrCardIssuingAuth != null ? contrCardIssuingAuth.hashCode() : 0);
        result = 31 * result + (contrAfm != null ? contrAfm.hashCode() : 0);
        result = 31 * result + (subContrName != null ? subContrName.hashCode() : 0);
        result = 31 * result + (subContrSurname != null ? subContrSurname.hashCode() : 0);
        result = 31 * result + (subContrBirthPlace != null ? subContrBirthPlace.hashCode() : 0);
        result = 31 * result + (subContrDateOfBirth != null ? subContrDateOfBirth.hashCode() : 0);
        result = 31 * result + (subContrHomeAddr != null ? subContrHomeAddr.hashCode() : 0);
        result = 31 * result + (subContrCardType != null ? subContrCardType.hashCode() : 0);
        result = 31 * result + (subContrCardNum != null ? subContrCardNum.hashCode() : 0);
        result = 31 * result + (subContrCardIssuingAuth != null ? subContrCardIssuingAuth.hashCode() : 0);
        result = 31 * result + (subContrAfm != null ? subContrAfm.hashCode() : 0);
        result = 31 * result + (housedCompanyTile != null ? housedCompanyTile.hashCode() : 0);
        result = 31 * result + (housedCompanyAddr != null ? housedCompanyAddr.hashCode() : 0);
        result = 31 * result + (housedCompanyAddrTk != null ? housedCompanyAddrTk.hashCode() : 0);
        result = 31 * result + (housedCompanyAddrP != null ? housedCompanyAddrP.hashCode() : 0);
        result = 31 * result + (housedCompanyAddrPe != null ? housedCompanyAddrPe.hashCode() : 0);
        result = 31 * result + (housedCompanyAddrD != null ? housedCompanyAddrD.hashCode() : 0);
        result = 31 * result + (housedCompanyAddrK != null ? housedCompanyAddrK.hashCode() : 0);
        result = 31 * result + (sepeDept != null ? sepeDept.hashCode() : 0);
        result = 31 * result + (compAme != null ? compAme.hashCode() : 0);
        return result.intValue();
    }

    @OneToMany(mappedBy = "spPtlCompanyProjects")
    public Set<SpPtlCompanyDailyCard> getSpPtlCompanyDailyCards() {
        return spPtlCompanyDailyCards;
    }

    public void setSpPtlCompanyDailyCards(Set<SpPtlCompanyDailyCard> spPtlCompanyDailyCards) {
        this.spPtlCompanyDailyCards = spPtlCompanyDailyCards;
    }

    //private SpPtlCompany spPtlCompany;

    @Basic
    @Column(name = "COMPANY_ID")
    public Long getCompanyid() {
        return companyid;
    }

    private Long companyid;

    public void setCompanyid(Long companyid) {
        this.companyid = companyid;
    }

    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMPANY_ID", nullable = false, insertable = true, updatable = false)
    public SpPtlCompany getSpPtlCompany() {
        return spPtlCompany;
    }

    public void setSpPtlCompany(SpPtlCompany spPtlCompany) {
        this.spPtlCompany = spPtlCompany;
        this.companyid = spPtlCompany.getId();
    }*/
}
