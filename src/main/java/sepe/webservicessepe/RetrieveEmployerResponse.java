
package sepe.webservicessepe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for retrieveEmployerResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="retrieveEmployerResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://webservicessepe/}webResponseEmployer" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "retrieveEmployerResponse1", namespace = "http://webservicessepe/", propOrder = {
    "_return"
})
public class RetrieveEmployerResponse {

    @XmlElement(name = "return")
    protected WebResponseEmployer _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link WebResponseEmployer }
     *     
     */
    public WebResponseEmployer getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link WebResponseEmployer }
     *     
     */
    public void setReturn(WebResponseEmployer value) {
        this._return = value;
    }

}
