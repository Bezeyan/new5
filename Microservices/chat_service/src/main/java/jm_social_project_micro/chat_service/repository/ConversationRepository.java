package jm_social_project_micro.chat_service.repository;

import jm_social_project_micro.chat_service.models.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {

    Conversation findConversationByChatId(String chatId);
}
