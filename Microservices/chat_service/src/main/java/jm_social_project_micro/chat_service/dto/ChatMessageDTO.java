package jm_social_project_micro.chat_service.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageDTO {

    private Long id;

    private Date timestamp;

    private String senderId;

    private String content;

    private String chatId;

}
