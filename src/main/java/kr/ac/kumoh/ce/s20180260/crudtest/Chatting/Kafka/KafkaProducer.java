package kr.ac.kumoh.ce.s20180260.crudtest.Chatting.Kafka;

import kr.ac.kumoh.ce.s20180260.crudtest.Chatting.Chats.dto.ReqAddChatsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class KafkaProducer {
    @Autowired
    private KafkaTemplate<Long, ReqAddChatsDto> kafkaTemplate;

    public void sendMessage(ReqAddChatsDto dto) {
        String topic = "chats-" + dto.getProjectId();
        System.out.println("KafkaProducer - Sending message to topic: " + topic);

        /*
        // 현재시각
        dto.setTime(LocalDateTime.now());
        */

        kafkaTemplate.send(topic, dto);
    }
}