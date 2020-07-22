
package sepe.webservicessepe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for webRequestEmployer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="webRequestEmployer">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="employerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ypokUserName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "webRequestEmployer1", namespace = "http://webservicessepe/", propOrder = {
    "employerId",
    "ypokUserName"
})
public class WebRequestEmployer {

    protected String employerId;
    protected String ypokUserName;

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
     * Gets the value of the ypokUserName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getYpokUserName() {
        return ypokUserName;
    }

    /**
     * Sets the value of the ypokUserName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setYpokUserName(String value) {
        this.ypokUserName = value;
    }

}
