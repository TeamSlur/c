package kr.ac.kumoh.ce.s20180260.crudtest.Chatting.Chats;

import kr.ac.kumoh.ce.s20180260.crudtest.Chatting.Chats.dto.ReqAddChatsDto;
import kr.ac.kumoh.ce.s20180260.crudtest.Chatting.Chats.dto.ResGetChatsDto;
import kr.ac.kumoh.ce.s20180260.crudtest.Chatting.Kafka.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatsService {
    @Autowired
    private ChatsRepository repository;

    @Autowired
    private KafkaProducer kafkaProducer;

    @SendTo("/topic/chats-{pid}")
    public ResponseEntity<ReqAddChatsDto> sendMessage(
            @DestinationVariable Long pid,
            ReqAddChatsDto dto
    ) {
        System.out.println("Message Controller - room ID : " + pid);
        kafkaProducer.sendMessage(dto);
        return ResponseEntity.ok(dto);
    }

    // 채팅 조회
    public ResponseEntity<List<ResGetChatsDto>> getChatsByProjectId(Long projectId) {
        List<ResGetChatsDto> chatsList = repository.findChatsEntitiesByProjectId(projectId)
                .stream()
                .map(ResGetChatsDto::new)
                .toList();
        return ResponseEntity.ok(chatsList);
    }
}