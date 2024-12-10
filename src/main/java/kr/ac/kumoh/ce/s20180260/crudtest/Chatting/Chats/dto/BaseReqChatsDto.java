package kr.ac.kumoh.ce.s20180260.crudtest.Chatting.Chats.dto;

import kr.ac.kumoh.ce.s20180260.crudtest.Chatting.Chats.entity.ChatsEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseReqChatsDto {
    private Long projectId;
    private Long senderId;
    private String message;
    private Long parentMessageId;

    // entity to dto
    public BaseReqChatsDto(ChatsEntity entity){
        this.projectId = entity.getProjectId();
        this.senderId = entity.getSender();
        this.message = entity.getMessage();
        this.parentMessageId = entity.getParentMessageId();
    }

    // dto to entity
    public ChatsEntity toEntity(){
        return ChatsEntity.builder()
                .projectId(this.getProjectId())
                .sender(this.getSenderId())
                .message(this.getMessage())
                .parentMessageId(this.getParentMessageId())
                .build();
    }
}