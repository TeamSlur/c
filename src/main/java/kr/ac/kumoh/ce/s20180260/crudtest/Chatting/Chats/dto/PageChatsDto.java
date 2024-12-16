package kr.ac.kumoh.ce.s20180260.crudtest.Chatting.Chats.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageChatsDto {
    private Long chatId;
    private Long projectId;
    private Long senderId;
    private String senderName;
    private String message;
    private LocalDateTime sentAt;
    private Boolean isRead;
    private Long parentMessageId;

    public PageChatsDto(ResGetChatsDto dto, String username){
        this.chatId = dto.getChatId();
        this.projectId = dto.getProjectId();
        this.senderId = dto.getSenderId();
        this.message = dto.getMessage();
        this.sentAt = dto.getSentAt();
        this.isRead = dto.getIsRead();
        this.parentMessageId = dto.getParentMessageId();
        this.senderName = username;
    }
}
