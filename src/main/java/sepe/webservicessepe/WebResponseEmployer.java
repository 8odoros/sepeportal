
package sepe.webservicessepe;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for webResponseEmployer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="webResponseEmployer">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="branchesCount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="businessStartDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="censusDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="discreetTitle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="employerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="employerStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fatherName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fullName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastUpdatedDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastUpdatedTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="legCatLegalCategoryCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="physicalFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="resultId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="resultReason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="selfAppointedCensus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sicSpecialInsCategoryCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="taxationNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="taxoffTaxationOfficeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "webResponseEmployer1", namespace = "http://webservicessepe/", propOrder = {
    "branchesCount",
    "businessStartDate",
    "censusDate",
    "discreetTitle",
    "employerId",
    "employerStatus",
    "fatherName",
    "fullName",
    "lastUpdatedDate",
    "lastUpdatedTime",
    "legCatLegalCategoryCode",
    "name",
    "physicalFlag",
    "resultId",
    "resultReason",
    "selfAppointedCensus",
    "sicSpecialInsCategoryCode",
    "taxationNumber",
    "taxoffTaxationOfficeCode"
})
public class WebResponseEmployer {

    protected BigDecimal branchesCount;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar businessStartDate;
    protected String censusDate;
    protected String discreetTitle;
    protected String employerId;
    protected String employerStatus;
    protected String fatherName;
    protected String fullName;
    protected String lastUpdatedDate;
    protected String lastUpdatedTime;
    protected String legCatLegalCategoryCode;
    protected String name;
    protected String physicalFlag;
    protected int resultId;
    protected String resultReason;
    protected String selfAppointedCensus;
    protected String sicSpecialInsCategoryCode;
    protected String taxationNumber;
    protected String taxoffTaxationOfficeCode;

    /**
     * Gets the value of the branchesCount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getBranchesCount() {
        return branchesCount;
    }

    /**
     * Sets the value of the branchesCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setBranchesCount(BigDecimal value) {
        this.branchesCount = value;
    }

    /**
     * Gets the value of the businessStartDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getBusinessStartDate() {
        return businessStartDate;
    }

    /**
     * Sets the value of the businessStartDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setBusinessStartDate(XMLGregorianCalendar value) {
        this.businessStartDate = value;
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
     * Gets the value of the discreetTitle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDiscreetTitle() {
        return discreetTitle;
    }

    /**
     * Sets the value of the discreetTitle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDiscreetTitle(String value) {
        this.discreetTitle = value;
    }

    /**
     * Gets the value of the employerId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmployerId() {
        return employerId;
    }

    /**
     * Sets the value of the employerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmployerId(String value) {
        this.employerId = value;
    }

    /**
     * Gets the value of the employerStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmployerStatus() {
        return employerStatus;
    }

    /**
     * Sets the value of the employerStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmployerStatus(String value) {
        this.employerStatus = value;
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
     * Gets the value of the fullName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Sets the value of the fullName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFullName(String value) {
        this.fullName = value;
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
     * Gets the value of the legCatLegalCategoryCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLegCatLegalCategoryCode() {
        return legCatLegalCategoryCode;
    }

    /**
     * Sets the value of the legCatLegalCategoryCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLegCatLegalCategoryCode(String value) {
        this.legCatLegalCategoryCode = value;
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
     * Gets the value of the resultId property.
     * 
     */
    public int getResultId() {
        return resultId;
    }

    /**
     * Sets the value of the resultId property.
     * 
     */
    public void setResultId(int value) {
        this.resultId = value;
    }

    /**
     * Gets the value of the resultReason property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResultReason() {
        return resultReason;
    }

    /**
     * Sets the value of the resultReason property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResultReason(String value) {
        this.resultReason = value;
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
     * Gets the value of the sicSpecialInsCategoryCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSicSpecialInsCategoryCode() {
        return sicSpecialInsCategoryCode;
    }

    /**
     * Sets the value of the sicSpecialInsCategoryCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSicSpecialInsCategoryCode(String value) {
        this.sicSpecialInsCategoryCode = value;
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

}
