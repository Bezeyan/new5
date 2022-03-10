package jm_social_project_micro.chat_service;

import jm_social_project_micro.chat_service.controllers.ChatController;
import jm_social_project_micro.chat_service.dto.ChatMessageDTO;
import jm_social_project_micro.chat_service.models.Conversation;
import jm_social_project_micro.chat_service.repository.ConversationRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import java.util.concurrent.TimeUnit;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan({"jm_social_project_micro.chat_service"})
public class JpaChatServiceTests {

    @Autowired
    private ConversationRepository conversationRepository;

    @Autowired
    private ChatController chatController;

    @SneakyThrows
    @Test
    void testSaveInDB() {
        Conversation conversation = Conversation.builder()
                .conversationName("testChatName")
                .chatId("testChatId")
                .build();

        chatController.processSubscribe(conversation);

        ChatMessageDTO messageDTO = ChatMessageDTO.builder()
                .senderId("artem")
                .content("Hello chick! What's ur name?")
                .chatId("testChatId")
                .build();

        chatController.processMessage(messageDTO);

        TimeUnit.SECONDS.sleep(10);

        Conversation conversation2 = Conversation.builder()
                .conversationName("testChatName")
                .chatId("testChatId")
                .build();

        chatController.processSubscribe(conversation2);

        ChatMessageDTO messageDTO2 = ChatMessageDTO.builder()
                .senderId("anna")
                .content("Hi man! My name is Anna!")
                .chatId("testChatId")
                .build();

        chatController.processMessage(messageDTO2);

        System.out.println(conversationRepository.findAll());
    }
}
