package org.javamentor.social.notification_service.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.javamentor.social.notification_service.exception.ErrorReqWebSocket;
import org.javamentor.social.notification_service.model.MessageDto;
import org.javamentor.social.notification_service.postForUser.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@Controller
@AllArgsConstructor
@Data
public class NotificationController {

    @Autowired
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    private final Post post;


    @MessageMapping("/hello")
    @SendTo("/topic/news")
    public MessageDto messageDto(String message) throws Exception {
        Thread.sleep(1000);
        String newsDate = new SimpleDateFormat("yyyy/MM/dd HH:mm").format(Calendar.getInstance().getTime());
        return new MessageDto(newsDate, message);
    }


    @SubscribeMapping("/user/{id}/news")
    public String subscribeMapping(@DestinationVariable("id_user") final Integer id) {
       return post.getUpdateList().get(id);
    }

    @MessageExceptionHandler
    @SendToUser(value = "/exchange/errors")
    public String handleProfanity(ErrorReqWebSocket e) {
        return e.getMessage();
    }
}
