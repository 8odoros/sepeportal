
package sepe.webservicessepe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for webRequestEmployerBranches complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="webRequestEmployerBranches">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="employerBranchId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "webRequestEmployerBranches1", namespace = "http://webservicessepe/", propOrder = {
    "employerBranchId",
    "employerId",
    "ypokUserName"
})
public class WebRequestEmployerBranches {

    protected String employerBranchId;
    protected String employerId;
    protected String ypokUserName;

    /**
     * Gets the value of the employerBranchId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmployerBranchId() {
        return employerBranchId;
    }

    /**
     * Sets the value of the employerBranchId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmployerBranchId(String value) {
        this.employerBranchId = value;
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
