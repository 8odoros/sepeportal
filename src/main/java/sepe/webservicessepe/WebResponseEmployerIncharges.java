
package sepe.webservicessepe;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for webResponseEmployerIncharges complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="webResponseEmployerIncharges">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="resultId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="resultReason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="webResponseEmployerInchargesLst" type="{http://webservicessepe/}webResponseEmployerInchargesDetails" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "webResponseEmployerIncharges1", namespace = "http://webservicessepe/", propOrder = {
    "resultId",
    "resultReason",
    "webResponseEmployerInchargesLst"
})
public class WebResponseEmployerIncharges {

    protected int resultId;
    protected String resultReason;
    @XmlElement(nillable = true)
    protected List<WebResponseEmployerInchargesDetails> webResponseEmployerInchargesLst;

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
     * Gets the value of the webResponseEmployerInchargesLst property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the webResponseEmployerInchargesLst property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWebResponseEmployerInchargesLst().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WebResponseEmployerInchargesDetails }
     * 
     * 
     */
    public List<WebResponseEmployerInchargesDetails> getWebResponseEmployerInchargesLst() {
        if (webResponseEmployerInchargesLst == null) {
            webResponseEmployerInchargesLst = new ArrayList<WebResponseEmployerInchargesDetails>();
        }
        return this.webResponseEmployerInchargesLst;
    }

}
