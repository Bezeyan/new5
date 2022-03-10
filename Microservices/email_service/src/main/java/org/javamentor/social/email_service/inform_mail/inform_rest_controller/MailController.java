package org.javamentor.social.email_service.inform_mail.inform_rest_controller;

import org.javamentor.social.email_service.inform_mail.inform_dto.InformMailDto;
import org.javamentor.social.email_service.inform_mail.inform_service.MailService;
import org.javamentor.social.email_service.model.dto.MailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/email-flyers")
public class MailController {

    @Autowired
    MailService mailService;

    @PostMapping("/send-message-birthday")
    public void sendBirthday(@RequestBody InformMailDto informMailDto) throws IOException {
        mailService.sendBirthdayGreetings(informMailDto);
    }


    @PostMapping("/send-message-advertising")
    public void sendAdvertising(@RequestBody InformMailDto informMailDto) throws IOException {
        mailService.sendAdvertisingOffer(informMailDto);
    }


    @PostMapping("/send-message-buy")
    public void sendToBuy(@RequestBody InformMailDto informMailDto) throws IOException {
        mailService.sendToBuy(informMailDto);
    }

}
