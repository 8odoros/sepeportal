
package sepe.webservicessepe;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for webRequestParameter complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="webRequestParameter">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="parameterName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "webRequestParameter1", namespace = "http://webservicessepe/", propOrder = {
    "parameterName",
    "ypokUserName"
})
public class WebRequestParameter {

    protected String parameterName;
    protected String ypokUserName;

    /**
     * Gets the value of the parameterName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParameterName() {
        return parameterName;
    }

    /**
     * Sets the value of the parameterName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParameterName(String value) {
        this.parameterName = value;
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
