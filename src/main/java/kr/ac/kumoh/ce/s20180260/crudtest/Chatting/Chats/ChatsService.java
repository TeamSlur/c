package kr.ac.kumoh.ce.s20180260.crudtest.Chatting.Chats;

import kr.ac.kumoh.ce.s20180260.crudtest.Chatting.Chats.dto.PageChatsDto;
import kr.ac.kumoh.ce.s20180260.crudtest.Chatting.Chats.dto.ReqAddChatsDto;
import kr.ac.kumoh.ce.s20180260.crudtest.Chatting.Chats.dto.ResGetChatsDto;
import kr.ac.kumoh.ce.s20180260.crudtest.Chatting.Kafka.KafkaProducer;
import kr.ac.kumoh.ce.s20180260.crudtest.login.service.UserService;
import kr.ac.kumoh.ce.s20180260.crudtest.login.util.JwtUtil;
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
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;

    @SendTo("/topic/chats-{pid}")
    public ResponseEntity<ReqAddChatsDto> sendMessage(
            @DestinationVariable Long pid,
            ReqAddChatsDto dto,
            String token) {
        //System.out.println("Message Controller - room ID : " + pid);
        dto.setSenderId(jwtUtil.extractUserid(token));
        kafkaProducer.sendMessage(dto);
        return ResponseEntity.ok(dto);
    }

    // 채팅 조회
    // 채팅 조회
    public ResponseEntity<List<PageChatsDto>> getChatsByProjectId(Long projectId) {
        // Fetch chat entities and convert to ResGetChatsDto
        List<ResGetChatsDto> chatsList = repository.findChatsEntitiesByProjectId(projectId)
                .stream()
                .map(ResGetChatsDto::new)
                .toList();

        // Convert ResGetChatsDto to PageChatsDto
        List<PageChatsDto> result = chatsList.stream()
                .map(chat -> {
                    String username = userService.getUserNameByUserId(chat.getSenderId());
                    return new PageChatsDto(chat, username);
                })
                .toList();

        return ResponseEntity.ok(result);
    }
}