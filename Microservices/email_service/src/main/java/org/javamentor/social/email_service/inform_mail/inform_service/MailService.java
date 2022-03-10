package org.javamentor.social.email_service.inform_mail.inform_service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import org.javamentor.social.email_service.inform_mail.inform_dto.InformMailDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class MailService {

    private static final Logger logger = LoggerFactory.getLogger(MailService.class);


    // поздравительное сообщение
    public void sendBirthdayGreetings(InformMailDto informMailDto) throws IOException {

        Email from = new Email("info@sharipov.tk"); // адрес электронной почты отправителя должен быть таким же, как мы использовали для создания проверки единого отправителя.

        Email to = new Email(informMailDto.getEmail());  //почта получателя

        Mail mail = new Mail(); //объект отправления

        /**
         * When sending an email with the v3 Mail Send endpoint, you define the various
         * metadata about your message, such as the recipients, sender, subject, and send time, at the root level of a J
         * SON request body. Personalizations allow you to override these various metadata for each email in an API request.
         *
         * Personalizations is an array of objects. Think of the personalizations array like the envelope of a letter:
         * the fields defined within personalizations apply to each email, not the recipient. Like an envelope, personalizations
         * are used to identify who should receive the email as well as details about how you would like the email to be handled. For example,
         * you can define when you would like it to be sent, what headers you would like to include, and any substitutions or custom arguments
         * you would like to be included with the email.
         *
         * Personalizations allow you to define:
         *
         * "to", "cc", "bcc" - The recipients of your email.
         * "from" - The sender or return path address of your email.
         * "subject" - The subject of your email.
         * "headers" - Any headers you would like to include in your email.
         * "substitutions" - Any substitutions you would like to be made for your email.
         * "custom_args" - Any custom arguments you would like to include in your email.
         * "send_at" - A specific time that you would like your email to be sent.
         * You must include at least one to object within the personalizations array.
         */
        Personalization personalization = new Personalization();

        personalization.addTo(to); //кому направляется данное письмо
        mail.setFrom(from);  // установка отправителя сообщения
        mail.setSubject(informMailDto.getSubject()); // определение темы сообщения
        personalization.addDynamicTemplateData("subject", informMailDto.getSubject()); // переменная subject, созданная в шаблоне
        personalization.addDynamicTemplateData("first_name", informMailDto.getFirstName()); // переменная first_name, созданная в шаблоне
        personalization.addDynamicTemplateData("message_text", informMailDto.getTextMessage());  // переменная message_text, созданная в шаблоне
        personalization.addDynamicTemplateData("date_of_birth", informMailDto.getDateOfBirth().toString());  // переменная date_of_birth, созданная в шаблоне
        mail.addPersonalization(personalization);

        mail.setTemplateId("d-6a3923192a18426caffed233c23b9f79");// идентификатор шаблона в Sendgrid

        SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY")); // API ключ для авторизации на сервере Sendgrid

        Request request = new Request();
        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        request.setBody(mail.build());
        Response response = sg.api(request);
        logger.info(response.getBody());
    }



    // персональное предложение
    public void sendToBuy(InformMailDto informMailDto) throws IOException {

        Email from = new Email("info@sharipov.tk"); // адрес электронной почты отправителя должен быть таким же, как мы использовали для создания проверки единого отправителя.

        Email to = new Email(informMailDto.getEmail()); //почта получателя

        Mail mail = new Mail(); //объект отправления

        Personalization personalization = new Personalization();

        personalization.addTo(to); //кому направляется данное письмо
        mail.setFrom(from);  // установка отправителя сообщения
        mail.setSubject(informMailDto.getSubject()); // определение темы сообщения
        personalization.addDynamicTemplateData("subject", informMailDto.getSubject()); // переменная subject, созданная в шаблоне
        personalization.addDynamicTemplateData("first_name", informMailDto.getFirstName());
        personalization.addDynamicTemplateData("message_text", informMailDto.getTextMessage());
        mail.addPersonalization(personalization);

        mail.setTemplateId("d-b541a7e56d6b4fccb592be79bcfe42fe"); //идентификатор шаблона

        SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY")); // API ключ для авторизации на сервере Sendgrid

        Request request = new Request();
        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        request.setBody(mail.build());
        Response response = sg.api(request);
        logger.info(response.getBody());
    }



    // рекламная рассылка
    public void sendAdvertisingOffer(InformMailDto informMailDto) throws IOException {

        Email from = new Email("info@sharipov.tk"); // адрес электронной почты отправителя должен быть таким же, как мы использовали для создания проверки единого отправителя.

        Email to = new Email(informMailDto.getEmail()); //почта получателя

        Mail mail = new Mail();  //объект отправления

        Personalization personalization = new Personalization();

        personalization.addTo(to); //кому направляется данное письмо
        mail.setFrom(from);  // установка отправителя сообщения
        mail.setSubject(informMailDto.getSubject()); // определение темы сообщения
        personalization.addDynamicTemplateData("subject", informMailDto.getSubject()); // переменная subject, созданная в шаблоне
        personalization.addDynamicTemplateData("message_text", informMailDto.getTextMessage());  // переменная message_text, созданная в шаблоне
        mail.addPersonalization(personalization);

        mail.setTemplateId("d-b88bf7c1c5a140c1ac2f7c6fc7731a65"); //идентификатор шаблона

        SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY")); // API ключ для авторизации на сервере Sendgrid

        Request request = new Request();
        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        request.setBody(mail.build());
        Response response = sg.api(request);
        logger.info(response.getBody());
    }
}
