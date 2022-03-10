package org.javamentor.social.email_service.service;

public interface EmailService {
    void sendEmail(String to, String subject, String text);
    void sendGreetingHtmlEmail(String email);
    void sendRegistrationNotification(String email, String firstName, String lastName);
    void sendPaymentNotification(String email, int lastDays, String firstName, String lastName);
}
