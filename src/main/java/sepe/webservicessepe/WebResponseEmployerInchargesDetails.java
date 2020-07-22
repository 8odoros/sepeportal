
package sepe.webservicessepe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for webResponseEmployerInchargesDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="webResponseEmployerInchargesDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="addressStreet" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="birthDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="birthPlace" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="city" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cntyCountyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="empEmployerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fatherName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fatherSurname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="homeAddressNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="identificationIssueOrg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="identificationNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="identificationType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="incstatVirtueCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="issueDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="motherName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nationalServiceStartDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nationalServiceTerminDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="phoneNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="physicalFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="secondPhoneNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="spouseName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="spouseSurname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="statusEmployerIncharges" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="surname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="taxationNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="taxoffTaxationOfficeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="virtueDescr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="zipCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "webResponseEmployerInchargesDetails1", namespace = "http://webservicessepe/", propOrder = {
    "addressStreet",
    "birthDate",
    "birthPlace",
    "city",
    "cntyCountyCode",
    "email",
    "empEmployerId",
    "fatherName",
    "fatherSurname",
    "homeAddressNo",
    "identificationIssueOrg",
    "identificationNumber",
    "identificationType",
    "incstatVirtueCode",
    "issueDate",
    "motherName",
    "name",
    "nationalServiceStartDate",
    "nationalServiceTerminDate",
    "phoneNumber",
    "physicalFlag",
    "secondPhoneNumber",
    "spouseName",
    "spouseSurname",
    "statusEmployerIncharges",
    "surname",
    "taxationNumber",
    "taxoffTaxationOfficeCode",
    "virtueDescr",
    "zipCode"
})
public class WebResponseEmployerInchargesDetails {

    protected String addressStreet;
    protected String birthDate;
    protected String birthPlace;
    protected String city;
    protected String cntyCountyCode;
    protected String email;
    protected String empEmployerId;
    protected String fatherName;
    protected String fatherSurname;
    protected String homeAddressNo;
    protected String identificationIssueOrg;
    protected String identificationNumber;
    protected String identificationType;
    protected String incstatVirtueCode;
    protected String issueDate;
    protected String motherName;
    protected String name;
    protected String nationalServiceStartDate;
    protected String nationalServiceTerminDate;
    protected String phoneNumber;
    protected String physicalFlag;
    protected String secondPhoneNumber;
    protected String spouseName;
    protected String spouseSurname;
    protected String statusEmployerIncharges;
    protected String surname;
    protected String taxationNumber;
    protected String taxoffTaxationOfficeCode;
    protected String virtueDescr;
    protected String zipCode;

    /**
     * Gets the value of the addressStreet property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddressStreet() {
        return addressStreet;
    }

    /**
     * Sets the value of the addressStreet property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddressStreet(String value) {
        this.addressStreet = value;
    }

    /**
     * Gets the value of the birthDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBirthDate() {
        return birthDate;
    }

    /**
     * Sets the value of the birthDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBirthDate(String value) {
        this.birthDate = value;
    }

    /**
     * Gets the value of the birthPlace property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBirthPlace() {
        return birthPlace;
    }

    /**
     * Sets the value of the birthPlace property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBirthPlace(String value) {
        this.birthPlace = value;
    }

    /**
     * Gets the value of the city property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the value of the city property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCity(String value) {
        this.city = value;
    }

    /**
     * Gets the value of the cntyCountyCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCntyCountyCode() {
        return cntyCountyCode;
    }

    /**
     * Sets the value of the cntyCountyCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCntyCountyCode(String value) {
        this.cntyCountyCode = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the empEmployerId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmpEmployerId() {
        return empEmployerId;
    }

    /**
     * Sets the value of the empEmployerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmpEmployerId(String value) {
        this.empEmployerId = value;
    }

    /**
     * Gets the value of the fatherName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFatherName() {
        return fatherName;
    }

    /**
     * Sets the value of the fatherName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFatherName(String value) {
        this.fatherName = value;
    }

    /**
     * Gets the value of the fatherSurname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFatherSurname() {
        return fatherSurname;
    }

    /**
     * Sets the value of the fatherSurname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFatherSurname(String value) {
        this.fatherSurname = value;
    }

    /**
     * Gets the value of the homeAddressNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHomeAddressNo() {
        return homeAddressNo;
    }

    /**
     * Sets the value of the homeAddressNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHomeAddressNo(String value) {
        this.homeAddressNo = value;
    }

    /**
     * Gets the value of the identificationIssueOrg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificationIssueOrg() {
        return identificationIssueOrg;
    }

    /**
     * Sets the value of the identificationIssueOrg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificationIssueOrg(String value) {
        this.identificationIssueOrg = value;
    }

    /**
     * Gets the value of the identificationNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificationNumber() {
        return identificationNumber;
    }

    /**
     * Sets the value of the identificationNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificationNumber(String value) {
        this.identificationNumber = value;
    }

    /**
     * Gets the value of the identificationType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificationType() {
        return identificationType;
    }

    /**
     * Sets the value of the identificationType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificationType(String value) {
        this.identificationType = value;
    }

    /**
     * Gets the value of the incstatVirtueCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIncstatVirtueCode() {
        return incstatVirtueCode;
    }

    /**
     * Sets the value of the incstatVirtueCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIncstatVirtueCode(String value) {
        this.incstatVirtueCode = value;
    }

    /**
     * Gets the value of the issueDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIssueDate() {
        return issueDate;
    }

    /**
     * Sets the value of the issueDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIssueDate(String value) {
        this.issueDate = value;
    }

    /**
     * Gets the value of the motherName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMotherName() {
        return motherName;
    }

    /**
     * Sets the value of the motherName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMotherName(String value) {
        this.motherName = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the nationalServiceStartDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNationalServiceStartDate() {
        return nationalServiceStartDate;
    }

    /**
     * Sets the value of the nationalServiceStartDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNationalServiceStartDate(String value) {
        this.nationalServiceStartDate = value;
    }

    /**
     * Gets the value of the nationalServiceTerminDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNationalServiceTerminDate() {
        return nationalServiceTerminDate;
    }

    /**
     * Sets the value of the nationalServiceTerminDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNationalServiceTerminDate(String value) {
        this.nationalServiceTerminDate = value;
    }

    /**
     * Gets the value of the phoneNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the value of the phoneNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhoneNumber(String value) {
        this.phoneNumber = value;
    }

    /**
     * Gets the value of the physicalFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhysicalFlag() {
        return physicalFlag;
    }

    /**
     * Sets the value of the physicalFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhysicalFlag(String value) {
        this.physicalFlag = value;
    }

    /**
     * Gets the value of the secondPhoneNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSecondPhoneNumber() {
        return secondPhoneNumber;
    }

    /**
     * Sets the value of the secondPhoneNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSecondPhoneNumber(String value) {
        this.secondPhoneNumber = value;
    }

    /**
     * Gets the value of the spouseName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSpouseName() {
        return spouseName;
    }

    /**
     * Sets the value of the spouseName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSpouseName(String value) {
        this.spouseName = value;
    }

    /**
     * Gets the value of the spouseSurname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSpouseSurname() {
        return spouseSurname;
    }

    /**
     * Sets the value of the spouseSurname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSpouseSurname(String value) {
        this.spouseSurname = value;
    }

    /**
     * Gets the value of the statusEmployerIncharges property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatusEmployerIncharges() {
        return statusEmployerIncharges;
    }

    /**
     * Sets the value of the statusEmployerIncharges property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatusEmployerIncharges(String value) {
        this.statusEmployerIncharges = value;
    }

    /**
     * Gets the value of the surname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets the value of the surname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSurname(String value) {
        this.surname = value;
    }

    /**
     * Gets the value of the taxationNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaxationNumber() {
        return taxationNumber;
    }

    /**
     * Sets the value of the taxationNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaxationNumber(String value) {
        this.taxationNumber = value;
    }

    /**
     * Gets the value of the taxoffTaxationOfficeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaxoffTaxationOfficeCode() {
        return taxoffTaxationOfficeCode;
    }

    /**
     * Sets the value of the taxoffTaxationOfficeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaxoffTaxationOfficeCode(String value) {
        this.taxoffTaxationOfficeCode = value;
    }

    /**
     * Gets the value of the virtueDescr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVirtueDescr() {
        return virtueDescr;
    }

    /**
     * Sets the value of the virtueDescr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVirtueDescr(String value) {
        this.virtueDescr = value;
    }

    /**
     * Gets the value of the zipCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * Sets the value of the zipCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZipCode(String value) {
        this.zipCode = value;
    }

}
