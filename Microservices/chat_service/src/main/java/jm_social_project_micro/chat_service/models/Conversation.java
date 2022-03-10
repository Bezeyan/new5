package jm_social_project_micro.chat_service.models;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "conversation", schema = "public")
public class Conversation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "timestamp_created")
    private Date timestampCreated;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "conversation_id")
    private Set<ChatMessage> messages;

    @ElementCollection
    private Set<String> participantsIds;

    @Column(name = "conversation_name")
    private String conversationName;

    @Column(name = "chat_id")
    private String chatId;

    public void addMessage(ChatMessage message) {
        messages.add(message);
    }


    public void addParticipant(String participantId) {
        participantsIds.add(participantId);
    }


}
