/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.dao.bean;

import javax.annotation.Resource;
import javax.ejb.Asynchronous;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
@Stateless
@LocalBean
public class EmailBean {

    private static final Logger LOG = LoggerFactory.getLogger(EmailBean.class);

    /*@Resource(mappedName = "java:jboss/mail/gmail")
    Session gmailSession;*/

    @Asynchronous
    public void sendEmail(String to, String from, String subject, String content) {

        LOG.info("Sending Email from " + from + " to " + to + " : " + subject);

        /*try {

            MimeMessage message = new MimeMessage(gmailSession);
            //message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, "joakimjohansson@outlook.com");
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(content, "text/plain");

            /*if (!gmailSession.getTransport().isConnected()) {
             gmailSession.getTransport().connect("smtp.gmail.com", 465, "dibblerinfo@gmail.com", "5ejc58uz");
             }*/
            //gmailSession.getTransport().sendMessage(message, InternetAddress.parse(to));

            //Transport.send(message);

            //LOG.debug("Email was sent");

       // } catch (MessagingException e) {
       //     LOG.error("Error while sending email : " + e.getMessage());
       // }
    }

    @Asynchronous
    public void sendTestEmail() {
        String test = "<!DOCTYPE html><html>>head><title>Page Title</title></head><body><h1>This is a Heading</h1><p>This is a paragraph.</p></body></html>";
        sendEmail("joakimjohansson@outlook.com", "noreply@dibbler.com", "FATAL ERROR. ", "A test message");
    }

}
