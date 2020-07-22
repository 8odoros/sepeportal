
package sepe.webservicessepe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for webResponseParameterDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="webResponseParameterDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="activeDateFrom" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="activeDateTo" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="creationDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="extraAttribute1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="extraAttribute2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="extraAttribute3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="extraAttribute4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastUpdatedDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="lastUpdatedTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "webResponseParameterDetails1", namespace = "http://webservicessepe/", propOrder = {
    "activeDateFrom",
    "activeDateTo",
    "code",
    "creationDate",
    "extraAttribute1",
    "extraAttribute2",
    "extraAttribute3",
    "extraAttribute4",
    "lastUpdatedDate",
    "lastUpdatedTime",
    "name",
    "status"
})
public class WebResponseParameterDetails {

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar activeDateFrom;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar activeDateTo;
    protected String code;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar creationDate;
    protected String extraAttribute1;
    protected String extraAttribute2;
    protected String extraAttribute3;
    protected String extraAttribute4;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastUpdatedDate;
    protected String lastUpdatedTime;
    protected String name;
    protected String status;

    /**
     * Gets the value of the activeDateFrom property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getActiveDateFrom() {
        return activeDateFrom;
    }

    /**
     * Sets the value of the activeDateFrom property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setActiveDateFrom(XMLGregorianCalendar value) {
        this.activeDateFrom = value;
    }

    /**
     * Gets the value of the activeDateTo property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getActiveDateTo() {
        return activeDateTo;
    }

    /**
     * Sets the value of the activeDateTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setActiveDateTo(XMLGregorianCalendar value) {
        this.activeDateTo = value;
    }

    /**
     * Gets the value of the code property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the value of the code property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCode(String value) {
        this.code = value;
    }

    /**
     * Gets the value of the creationDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreationDate() {
        return creationDate;
    }

    /**
     * Sets the value of the creationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreationDate(XMLGregorianCalendar value) {
        this.creationDate = value;
    }

    /**
     * Gets the value of the extraAttribute1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtraAttribute1() {
        return extraAttribute1;
    }

    /**
     * Sets the value of the extraAttribute1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtraAttribute1(String value) {
        this.extraAttribute1 = value;
    }

    /**
     * Gets the value of the extraAttribute2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtraAttribute2() {
        return extraAttribute2;
    }

    /**
     * Sets the value of the extraAttribute2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtraAttribute2(String value) {
        this.extraAttribute2 = value;
    }

    /**
     * Gets the value of the extraAttribute3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtraAttribute3() {
        return extraAttribute3;
    }

    /**
     * Sets the value of the extraAttribute3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtraAttribute3(String value) {
        this.extraAttribute3 = value;
    }

    /**
     * Gets the value of the extraAttribute4 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtraAttribute4() {
        return extraAttribute4;
    }

    /**
     * Sets the value of the extraAttribute4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtraAttribute4(String value) {
        this.extraAttribute4 = value;
    }

    /**
     * Gets the value of the lastUpdatedDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    /**
     * Sets the value of the lastUpdatedDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastUpdatedDate(XMLGregorianCalendar value) {
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
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

}
