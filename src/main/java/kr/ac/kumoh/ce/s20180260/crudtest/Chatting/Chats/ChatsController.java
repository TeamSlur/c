package kr.ac.kumoh.ce.s20180260.crudtest.Chatting.Chats;

import kr.ac.kumoh.ce.s20180260.crudtest.Chatting.Chats.dto.PageChatsDto;
import kr.ac.kumoh.ce.s20180260.crudtest.Chatting.Chats.dto.ReqAddChatsDto;
import kr.ac.kumoh.ce.s20180260.crudtest.Chatting.Chats.dto.ResGetChatsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChatsController {
    @Autowired
    private ChatsService service;
    // 메시지 전송
    @MessageMapping("api/chats/send/{pid}")
    public ResponseEntity<ReqAddChatsDto> sendChat(
            @DestinationVariable Long pid,
            @RequestBody ReqAddChatsDto reqAddChatsDto,
            @RequestHeader("Authorization") String token
    ) {
        return service.sendMessage(pid, reqAddChatsDto, token);
    }

    // 채팅방 내역 조회
    @GetMapping("/api/chats")
    public ResponseEntity<List<PageChatsDto>> getChatsByProjectId(
            @RequestParam Long pid
    ) {
        return service.getChatsByProjectId(pid);
    }
}