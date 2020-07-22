package sepe.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import sepe.domain.company.SpPtlCompPtlBranch;
import sepe.domain.company.TEmployerBranchIKA;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;

/**
 * Created by Evangelos on 4/4/2015.
 * Edited by Marios on 19/2/2016.
 */
@Service
public class MailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MailService.class);

    public MailService() {
    }

    public Boolean sendMail(String from, String to, String subject, String messagebody) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "172.18.17.33");
        props.put("mail.smtp.auth", "false");
        props.put("mail.smtp.port", "25");
        /*props.put("mail.smtp.host", "mailgate.cosmotemail.gr");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
        Authenticator authenticator = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("sepenet.support@sepe.gov.gr", "s3p3net!");
            }
        };*/
        Session session = Session.getInstance(props);
        /*Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("mail", "mail");
            }
        });*/
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject(subject);
            message.setContent(messagebody,"text/html; charset=UTF-8");
            Transport.send(message);
            LOGGER.debug("Mail Sent To:" + to + " : " + messagebody);
            return true;
        } catch (MessagingException e) {
            LOGGER.debug("Mail Failed To:" + to + " : " + messagebody + " " + e.getMessage());

        }
        return false;
    }

    public Boolean passwordReminder(String username, String password, String to){
        String message="Όνομα Χρήστη:"+username+"<br> Νέος Κωδικός Πρόσβασης: "+ password;
        message+="<br>Αλλάξτε τον κωδικό σας πρόσβασης στο Προφιλ σας μόλις συνθεθείτε στο Σύστημα του ΣΕΠΕ";
        return sendMail("support@sepenet.gr",to,"Sepe Password Reminder",message);
    }

    public Boolean createNewUser(String email, String username, String password, String firstName, String lastName){
        String message="Παρακάτω θα βρείτε τα στοιχεία για την είσοδό σας στο Σύστημα του ΣΕΠΕ";
        message+="<br>Username: " + username + "<br>Email Address: "+ email + "<br>Κωδικός Πρόσβασης: "+ password;
        message+="<br>Όνομα χρήστη: " + firstName + "<br>Επώνυμο χρήστη: " + lastName;
        return sendMail("support@sepenet.gr",email,"Sepe New Account",message);
    }

    public Boolean createNewCompanyUser(String email, String username, String password, String firstName, String lastName, String companyName, String companyAfm, List<TEmployerBranchIKA> companyBranches, List<String> companyServices){
        String message="Παρακάτω θα βρείτε τα στοιχεία για την είσοδό σας στο Σύστημα του ΣΕΠΕ";
        message+="<br>Username: " + username + "<br>Email Address: "+ email + "<br>Κωδικός Πρόσβασης: "+ password;
        message+="<br>Όνομα χρήστη: " + firstName + "<br>Επώνυμο χρήστη: " + lastName + "<br><br>Ο χρήστης δημιουργήθηκε από την εταιρία:<br><br>" + companyName+ " ΑΦΜ: " + companyAfm;
        message+="<br><br>Έχει δικαιώματα για τα υποκαταστήματα:<br><br><ul>";
        for (TEmployerBranchIKA branch : companyBranches)
        {
            message+="<li>"+branch.getRgEbrAddressStreet()+" TK: " + branch.getRgEbrZipCode();
        }
        message+="</ul><br><br>και τις ηλεκτρονικές υπηρεσίες:<br><br><ul>";
        for (String service : companyServices)
        {
            message+="<li>"+service;
        }
        message+="</ul>";
        return sendMail("support@sepenet.gr",email,"Sepe New Account",message);
    }

    public Boolean sendNotification(String email, Integer sendEmailEnabled, String title, String message){
        if (sendEmailEnabled == null) {
            String subject = "ΣΕΠΕ - " + title;
            return sendMail("support@sepenet.gr",email,subject,message);
        } else if (sendEmailEnabled == 1) {
            String subject = "ΣΕΠΕ - " + title;
            return sendMail("support@sepenet.gr",email,subject,message);
        } else {
            return false;
        }
    }
}
