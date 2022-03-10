package org.javamentor.social.email_service.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import lombok.ToString;

@Service
@ToString
public class EmailServiceImpl implements EmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);
    private final JavaMailSender emailSender;

    @Autowired
    public EmailServiceImpl(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    private String greetingMessage(){
        return "<div align='center'>"
               + "<h3>Thank you for your registration!</h3>"
               + "<p>Some text, some text!</p>"
               + "<img src='https://www.logopik.com/wp-content/uploads/edd/2018/09/Dating-Site-Vactor-Logo.png'>"
               + "</div>";
    }

    /**
     * Отправка email.
     * @param to адрес получателя
     * @param subject тема сообщения
     * @param text текст сообщения
     */
    @Override
    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        this.emailSender.send(message);
    }

    @Override
    public void sendGreetingHtmlEmail(String email)  {
        MimeMessage message = emailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
            String htmlMsg = greetingMessage();
            message.setContent(htmlMsg, "text/html");
            helper.setTo(email);
            helper.setSubject("Welcome on 'Dating Site!");
        } catch (MessagingException e) {
            LOGGER.error("Сообщение не было отправлено!", e);
        }
        this.emailSender.send(message);
    }

    private String welcomeMessage(String firstName, String lastName){
        return "<div align='left'><p>Dear " + firstName + " " + lastName + "!</p>"
               + "<p>Welcome to our dating website!</p>"
               + "<p>Enjoy nice conversation with <a href='#' target='blank'>A lya Tinder</a></p>"
               + "<p>Read <a href='#' target='blank'>our rules</a> to get maximum matches from other users</p>"
               + "</div>";
    }

    @Override
    public void sendRegistrationNotification(String email, String firstName, String lastName) {
        MimeMessage message = emailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
            String htmlMsg = welcomeMessage(firstName, lastName);
            message.setContent(htmlMsg, "text/html");
            helper.setTo(email);
            helper.setSubject("Welcome to 'Dating Site!");
        } catch (MessagingException e) {
            LOGGER.error("Сообщение не было отправлено!", e);
        }
        this.emailSender.send(message);
    }

    private String lastDaysMessage(int lastDays, String firstName, String lastName){
        return "<div align='left'>" + "<p>" + "Dear " + firstName + " " + lastName + "!</p>"
               + "<p>Your paid subscription will expire in " + lastDays + " days</p>"
               + "<p>To renew your subscription to the paid services of <a href='#' target='blank'>A lya Tinder</a>, click on the button below </p>"
               + "<a href='#' target='blank' style='background-color:#44c767; border-radius:28px; border:1px solid #18ab29;" +
               "display:inline-block; cursor:pointer; color:#ffffff; font-family:Arial; font-size:17px;" +
               "padding:10px 21px; text-decoration:none;text-shadow:0px 1px 0px #2f6627;'> Renew your subscription! </a>"
               + "</div>";
    }

    @Override
    public void sendPaymentNotification(String email, int lastDays, String firstName, String lastName) {
        MimeMessage message = emailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
            String htmlMsg = lastDaysMessage(lastDays, firstName, lastName);
            message.setContent(htmlMsg, "text/html");
            helper.setTo(email);
            helper.setSubject("Your paid subscription will expire in " + lastDays + " days");
        } catch (MessagingException e) {
            LOGGER.error("Сообщение не было отправлено!", e);
        }
        this.emailSender.send(message);
    }
}
