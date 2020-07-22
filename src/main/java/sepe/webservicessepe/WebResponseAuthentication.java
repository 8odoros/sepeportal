
package sepe.webservicessepe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for webResponseAuthentication complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="webResponseAuthentication">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="employerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="resultId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="resultReason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "webResponseAuthentication1", namespace = "http://webservicessepe/", propOrder = {
    "employerId",
    "resultId",
    "resultReason"
})
public class WebResponseAuthentication {

    protected String employerId;
    protected int resultId;
    protected String resultReason;

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

}
