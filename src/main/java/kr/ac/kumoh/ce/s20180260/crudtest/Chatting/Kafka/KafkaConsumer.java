package kr.ac.kumoh.ce.s20180260.crudtest.Chatting.Kafka;

import kr.ac.kumoh.ce.s20180260.crudtest.Chatting.Chats.ChatsRepository;
import kr.ac.kumoh.ce.s20180260.crudtest.Chatting.Chats.dto.ReqAddChatsDto;
import kr.ac.kumoh.ce.s20180260.crudtest.Chatting.WebSocket.WebSocketMessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
    @Autowired
    private ChatsRepository chatsRepository;

    @Autowired
    private WebSocketMessageSender webSocketMessageSender;

    @KafkaListener(topicPattern = "chats-.*", groupId = "consumerGroup")
    public void consumeChats(ReqAddChatsDto dto) {
        System.out.println("KafkaConsumer - Received chat from Kafka: " + dto);

        String topic = "/topic/chats-" + dto.getProjectId();
        webSocketMessageSender.sendChatMessage(topic, dto);

        chatsRepository.save(dto.toEntity());


        /*
        // dto to entity
        ChatsEntity entity = dto.toEntity();

        // save message
        try{
            chatsRepository.save(entity);
            System.out.println("Message saved to DB successfully.");
        } catch (Exception e) {
            System.err.println("Error saving message to DB: " + e.getMessage());
            e.printStackTrace();
        }*/
    }
}