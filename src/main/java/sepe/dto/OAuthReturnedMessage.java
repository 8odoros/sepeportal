package sepe.dto;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Marios on 23/1/2016.
 */
@XmlRootElement(name="WebUserDetails")
public class OAuthReturnedMessage {

    private String comments;
    private String tin;
    public String getMessageCode() {
        return comments;
    }

    public OAuthReturnedMessage() {
        super();
    }

    public OAuthReturnedMessage(String comments, String messageText) {
        super();
        this.comments = comments;
        this.tin = messageText;
    }
    public void setMessageCode(String comments) {
        this.comments = comments;
    }
    public String getMessageText() {
        return tin;
    }
    public void setMessageText(String messageText) {
        this.tin = messageText;
    }
}
