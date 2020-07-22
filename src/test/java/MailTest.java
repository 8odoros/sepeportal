import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by Evangelos on 4/4/2015.
 */
@Service
public class MailTest {


    private static final Logger LOGGER = LoggerFactory.getLogger(MailTest.class);

    public static void main(String[] args) throws MessagingException {
        sendMail("support@sepenet.gr", "kendea@ceid.upatras.gr", "test", "Test");
    }

    public static void sendMail(String from, String to, String subject, String messagebody) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.host", "172.18.17.33");
        props.put("mail.smtp.auth", "false");
        props.put("mail.smtp.port", "25");
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("support@sepenet.gr", "mail");
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(messagebody);
            Transport.send(message);
            LOGGER.debug("Mail Sent To:" + to + " : " + messagebody);

        } catch (MessagingException e) {
            LOGGER.debug("Mail Failed To:" + to + " : " + messagebody + " " + e.getMessage());
            throw e;
        }

    }
}
