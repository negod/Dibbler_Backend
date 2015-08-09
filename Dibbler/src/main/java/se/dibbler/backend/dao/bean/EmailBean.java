/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.dao.bean;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.Asynchronous;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.dibbler.backend.dto.ErrorLogDto;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
@Stateless
@LocalBean
public class EmailBean {

    private static final Logger LOG = LoggerFactory.getLogger(EmailBean.class);

    @Resource(mappedName = "java:jboss/mail/gmail")
    Session gmailSession;

    /**
     *
     * @param subject The subject of the mail
     * @param content The content of the mail, must be in [ text/html;
     * charset=utf-8 ] format
     * @param from Who the mail is from
     * @param to Whom the mail is intended for
     * @param toCC A list of other recipients, ( CC )
     */
    @Asynchronous
    public void sendEmail(String subject, String content, String from, String to, String... toCC) {

        LOG.info("Sending Email from " + from + " to " + to + " : " + subject);

        try {

            MimeMessage message = new MimeMessage(gmailSession);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

            if (toCC != null) {
                for (int i = 0; i < toCC.length; i++) {
                    Address adress = new InternetAddress(toCC[i]);
                    message.addRecipient(Message.RecipientType.CC, adress);
                }
            }

            message.setSubject(subject);
            message.setContent(content, "text/html; charset=utf-8");
            Transport.send(message);

            LOG.info("Email was sent");

        } catch (MessagingException e) {
            LOG.error("Error while sending email : ", e);
        }
    }

    public void sendErrorMessage(List<String> to, ErrorLogDto error) {
        String test = "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "<title>Dibbler Exception</title>"
                + "</head>"
                + "<body>"
                + "<h3>Dibbler service has encountered an Exception</h3>"
                + "<p>Error Code: " + error.getErrorCode() + "</p></br>"
                + "<p>Exception Message: " + error.getExceptionMessage() + "</p></br>"
                + "<p>Error Type: " + error.getErrorType() + "</p></br>"
                + "<p>Error Message: " + error.getErrorMessage() + "</p></br>"
                + "<p>Time Occured: " + DateFormat.getInstance().format(new Date()) + "</p></br>"
                + "</body>"
                + "</html>";
        sendEmail("Dibbler service error ", test, "dibblerinfo@gmail.com", "joakimjohansson@outlook.com", to.toArray(new String[0]));
    }

    public void sendTestEmail() {
        String test = "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "<title>Dibbler info mail</title>"
                + "</head>"
                + "<body>"
                + "<h1>This is a Heading</h1>"
                + "<p>This is a paragraph.</p>"
                + "</body>"
                + "</html>";
        sendEmail("Dibbler service error ", test, "dibblerinfo@gmail.com", "joakimjohansson@outlook.com", "joakim.johansson@kits.se");
    }

}
