package kr.ac.kumoh.ce.s20180260.crudtest.Chatting.Chats.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import kr.ac.kumoh.ce.s20180260.crudtest.Chatting.Chats.entity.ChatsEntity;

public class ReqAddChatsDto extends BaseReqChatsDto {

    // JSON 역직렬화를 위한 생성자
    @JsonCreator
    public ReqAddChatsDto(
            @JsonProperty("projectId") Long projectId,
            @JsonProperty("senderId") Long senderId,
            @JsonProperty("message") String message,
            @JsonProperty("parentMessageId") Long parentMessageId
    ) {
        this.setProjectId(projectId);
        this.setSenderId(senderId);
        this.setMessage(message);
        this.setParentMessageId(parentMessageId);
    }

    public ReqAddChatsDto(ChatsEntity entity){
        super(entity);
    }
}