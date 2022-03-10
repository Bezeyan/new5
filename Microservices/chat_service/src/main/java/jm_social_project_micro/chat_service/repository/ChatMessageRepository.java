package jm_social_project_micro.chat_service.repository;

import jm_social_project_micro.chat_service.models.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

}
