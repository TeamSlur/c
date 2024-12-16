package kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Document;

import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Document.dto.ReqAddDocumentDto;
import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Document.dto.ReqUpdateDocumentDto;
import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Document.dto.ResGetDocumentDto;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/document")
public class DocumentController {

    @Autowired
    private DocumentService service;

    private final SimpMessagingTemplate messagingTemplate;

    public DocumentController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    // 문서 동기화 메소드 (WebSocket)
    @MessageMapping("/syncDocument")
    public void syncDocument(String content) {
        // 클라이언트에서 보낸 내용 처리 후, 모든 구독자에게 전송
        messagingTemplate.convertAndSend("/topic/document", content);
    }

    // 특정 프로젝트에 대한 문서 목록 조회
    @GetMapping
    public ResponseEntity<List<ResGetDocumentDto>> getDocumentsByProjectId(
            @RequestParam Long pid) {
        return service.getDocumentsByProjectId(pid);
    }

    // 문서 추가 요청 처리
    @PostMapping("/add")
    public ResponseEntity<ResGetDocumentDto> postDocument(
            @RequestParam Long pid,
            @RequestBody ReqAddDocumentDto reqAddDocumentDto) {
        // DocumentService에서 문서 추가 처리
        ResGetDocumentDto document = service.postDocument(reqAddDocumentDto).getBody();

        // 문서가 추가된 후, 추가된 문서를 모든 구독자에게 브로드캐스트
        messagingTemplate.convertAndSend("/topic/document/" + pid, document);

        return ResponseEntity.ok(document);
    }

    // 문서 수정 요청 처리
    @PutMapping("/edit")
    public ResponseEntity<ResGetDocumentDto> editDocument(
            @RequestParam Long pid,
            @RequestBody ReqUpdateDocumentDto dto) {
        // DocumentService에서 문서 수정 처리
        ResGetDocumentDto updatedDocument = service.updateDocument(dto).getBody();

        // 수정된 문서를 모든 구독자에게 브로드캐스트
        messagingTemplate.convertAndSend("/topic/document/" + pid, updatedDocument);

        return ResponseEntity.ok(updatedDocument);
    }

    // 문서 삭제 요청 처리
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteDocument(
            @RequestParam Long pid,
            @RequestParam Long did) {
        // DocumentService에서 문서 삭제 처리
        service.deleteDocument(did);

        // 삭제된 문서의 ID를 모든 구독자에게 브로드캐스트
        messagingTemplate.convertAndSend("/topic/document/" + pid, "deleted:" + did);

        return ResponseEntity.noContent().build();
    }

    // WebSocket을 통해 특정 문서에 대한 동기화 요청 처리
    @MessageMapping("/syncDocument/{pid}")
    public void syncDocumentForProject(@PathVariable Long pid, String content) {
        // 특정 프로젝트의 문서 동기화 요청을 처리하고 해당 프로젝트에 관련된 구독자에게 전송
        messagingTemplate.convertAndSend("/topic/document/" + pid, content);
    }
}