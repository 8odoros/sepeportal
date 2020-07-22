
package sepe.webservicessepe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for retrieveEmployerInchargesResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="retrieveEmployerInchargesResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://webservicessepe/}webResponseEmployerIncharges" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "retrieveEmployerInchargesResponse1", namespace = "http://webservicessepe/", propOrder = {
    "_return"
})
public class RetrieveEmployerInchargesResponse {

    @XmlElement(name = "return")
    protected WebResponseEmployerIncharges _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link WebResponseEmployerIncharges }
     *     
     */
    public WebResponseEmployerIncharges getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link WebResponseEmployerIncharges }
     *     
     */
    public void setReturn(WebResponseEmployerIncharges value) {
        this._return = value;
    }

}
