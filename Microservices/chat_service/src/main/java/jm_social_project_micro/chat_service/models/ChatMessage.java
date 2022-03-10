package jm_social_project_micro.chat_service.models;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Builder
@Table(name = "message", schema = "public")
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "timestamp")
    private Date timestamp;

    @Column(name = "sender_id")
    private String senderId;

    @Column(name = "content")
    private String content;
}
