package kr.ac.kumoh.ce.s20180260.crudtest.Chatting.WebSocket;

import kr.ac.kumoh.ce.s20180260.crudtest.Chatting.Chats.dto.ReqAddChatsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketMessageSender {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public void sendChatMessage(String topic, ReqAddChatsDto dto) {
        messagingTemplate.convertAndSend(topic, dto);
        System.out.println("WebSocketMessageSender - Sent message to topic: " + topic);
    }
}
