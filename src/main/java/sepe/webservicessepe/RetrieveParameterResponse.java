
package sepe.webservicessepe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for retrieveParameterResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="retrieveParameterResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://webservicessepe/}webResponseParameter" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "retrieveParameterResponse1", namespace = "http://webservicessepe/", propOrder = {
    "_return"
})
public class RetrieveParameterResponse {

    @XmlElement(name = "return")
    protected WebResponseParameter _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link WebResponseParameter }
     *     
     */
    public WebResponseParameter getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link WebResponseParameter }
     *     
     */
    public void setReturn(WebResponseParameter value) {
        this._return = value;
    }

}
