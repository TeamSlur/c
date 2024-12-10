package kr.ac.kumoh.ce.s20180260.crudtest.Chatting.Chats.dto;

import kr.ac.kumoh.ce.s20180260.crudtest.Chatting.Chats.entity.ChatsEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseResChatsDto {
    private Long chatId;
    private Long projectId;
    private Long senderId;
    private String message;
    private LocalDateTime sentAt;
    private Boolean isRead;
    private Long parentMessageId;

    // entity to dto
    public BaseResChatsDto(ChatsEntity entity){
        this.chatId = entity.getChatId();
        this.projectId = entity.getProjectId();
        this.senderId = entity.getSender();
        this.message = entity.getMessage();
        this.sentAt = entity.getSentAt();
        this.isRead = entity.getIsRead();
        this.parentMessageId = entity.getParentMessageId();
    }

    // dto to entity
    public ChatsEntity toEntity(){
        return ChatsEntity.builder()
                .chatId(this.getChatId())
                .projectId(this.getProjectId())
                .sender(this.getSenderId())
                .message(this.getMessage())
                .sentAt(this.getSentAt())
                .isRead(this.getIsRead())
                .parentMessageId(this.getParentMessageId())
                .build();
    }
}