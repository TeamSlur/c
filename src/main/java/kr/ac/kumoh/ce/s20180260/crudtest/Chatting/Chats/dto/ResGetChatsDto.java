package kr.ac.kumoh.ce.s20180260.crudtest.Chatting.Chats.dto;

import kr.ac.kumoh.ce.s20180260.crudtest.Chatting.Chats.entity.ChatsEntity;

public class ResGetChatsDto extends BaseResChatsDto {
    public ResGetChatsDto(ChatsEntity entity){
       super(entity);
   }
}