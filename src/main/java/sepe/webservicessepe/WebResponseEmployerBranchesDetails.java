
package sepe.webservicessepe;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for webResponseEmployerBranchesDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="webResponseEmployerBranchesDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="addressNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="addressStreet" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="censusDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="city" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cntyCountyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="countyDescr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="empEmployerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="employerBranchId" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="employerBranchStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="faxNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fiveDayPeriodFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastUpdatedDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastUpdatedTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mainBranchFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orgbraBranchCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="phoneNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="seasonalFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="secondPhoneNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="selfAppointedCensus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="taxoffTaxationOfficeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="thirdPhoneNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "webResponseEmployerBranchesDetails1", namespace = "http://webservicessepe/", propOrder = {
    "addressNo",
    "addressStreet",
    "censusDate",
    "city",
    "cntyCountyCode",
    "countyDescr",
    "email",
    "empEmployerId",
    "employerBranchId",
    "employerBranchStatus",
    "faxNumber",
    "fiveDayPeriodFlag",
    "lastUpdatedDate",
    "lastUpdatedTime",
    "mainBranchFlag",
    "orgbraBranchCode",
    "phoneNumber",
    "seasonalFlag",
    "secondPhoneNumber",
    "selfAppointedCensus",
    "taxoffTaxationOfficeCode",
    "thirdPhoneNumber",
    "zipCode"
})
public class WebResponseEmployerBranchesDetails {

    protected String addressNo;
    protected String addressStreet;
    protected String censusDate;
    protected String city;
    protected String cntyCountyCode;
    protected String countyDescr;
    protected String email;
    protected String empEmployerId;
    protected BigDecimal employerBranchId;
    protected String employerBranchStatus;
    protected String faxNumber;
    protected String fiveDayPeriodFlag;
    protected String lastUpdatedDate;
    protected String lastUpdatedTime;
    protected String mainBranchFlag;
    protected String orgbraBranchCode;
    protected String phoneNumber;
    protected String seasonalFlag;
    protected String secondPhoneNumber;
    protected String selfAppointedCensus;
    protected String taxoffTaxationOfficeCode;
    protected String thirdPhoneNumber;
    protected String zipCode;

    /**
     * Gets the value of the addressNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddressNo() {
        return addressNo;
    }

    /**
     * Sets the value of the addressNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddressNo(String value) {
        this.addressNo = value;
    }

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
     * Gets the value of the censusDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCensusDate() {
        return censusDate;
    }

    /**
     * Sets the value of the censusDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCensusDate(String value) {
        this.censusDate = value;
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
     * Gets the value of the countyDescr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountyDescr() {
        return countyDescr;
    }

    /**
     * Sets the value of the countyDescr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountyDescr(String value) {
        this.countyDescr = value;
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
     * Gets the value of the employerBranchId property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getEmployerBranchId() {
        return employerBranchId;
    }

    /**
     * Sets the value of the employerBranchId property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setEmployerBranchId(BigDecimal value) {
        this.employerBranchId = value;
    }

    /**
     * Gets the value of the employerBranchStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmployerBranchStatus() {
        return employerBranchStatus;
    }

    /**
     * Sets the value of the employerBranchStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmployerBranchStatus(String value) {
        this.employerBranchStatus = value;
    }

    /**
     * Gets the value of the faxNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFaxNumber() {
        return faxNumber;
    }

    /**
     * Sets the value of the faxNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFaxNumber(String value) {
        this.faxNumber = value;
    }

    /**
     * Gets the value of the fiveDayPeriodFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFiveDayPeriodFlag() {
        return fiveDayPeriodFlag;
    }

    /**
     * Sets the value of the fiveDayPeriodFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFiveDayPeriodFlag(String value) {
        this.fiveDayPeriodFlag = value;
    }

    /**
     * Gets the value of the lastUpdatedDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    /**
     * Sets the value of the lastUpdatedDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastUpdatedDate(String value) {
        this.lastUpdatedDate = value;
    }

    /**
     * Gets the value of the lastUpdatedTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    /**
     * Sets the value of the lastUpdatedTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastUpdatedTime(String value) {
        this.lastUpdatedTime = value;
    }

    /**
     * Gets the value of the mainBranchFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMainBranchFlag() {
        return mainBranchFlag;
    }

    /**
     * Sets the value of the mainBranchFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMainBranchFlag(String value) {
        this.mainBranchFlag = value;
    }

    /**
     * Gets the value of the orgbraBranchCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgbraBranchCode() {
        return orgbraBranchCode;
    }

    /**
     * Sets the value of the orgbraBranchCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgbraBranchCode(String value) {
        this.orgbraBranchCode = value;
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
     * Gets the value of the seasonalFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSeasonalFlag() {
        return seasonalFlag;
    }

    /**
     * Sets the value of the seasonalFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSeasonalFlag(String value) {
        this.seasonalFlag = value;
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
     * Gets the value of the selfAppointedCensus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSelfAppointedCensus() {
        return selfAppointedCensus;
    }

    /**
     * Sets the value of the selfAppointedCensus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSelfAppointedCensus(String value) {
        this.selfAppointedCensus = value;
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
     * Gets the value of the thirdPhoneNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getThirdPhoneNumber() {
        return thirdPhoneNumber;
    }

    /**
     * Sets the value of the thirdPhoneNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setThirdPhoneNumber(String value) {
        this.thirdPhoneNumber = value;
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
