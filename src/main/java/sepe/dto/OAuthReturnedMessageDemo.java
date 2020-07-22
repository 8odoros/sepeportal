package sepe.dto;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Marios on 23/1/2016.
 */
@XmlRootElement(name="ReturnedMessage")
public class OAuthReturnedMessageDemo {

    private String messageCode;
    private String messageText;
    public String getMessageCode() {
        return messageCode;
    }

    public OAuthReturnedMessageDemo() {
        super();
    }

    public OAuthReturnedMessageDemo(String messageCode, String messageText) {
        super();
        this.messageCode = messageCode;
        this.messageText = messageText;
    }
    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }
    public String getMessageText() {
        return messageText;
    }
    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }
}
