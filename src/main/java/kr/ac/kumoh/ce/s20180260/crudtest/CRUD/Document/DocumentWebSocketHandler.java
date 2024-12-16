package kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Document;

import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Document.dto.ReqAddDocumentDto;
import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Document.dto.ReqUpdateDocumentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.socket.TextMessage;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class DocumentWebSocketHandler extends TextWebSocketHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private DocumentService documentService; // 문서 서비스 추가

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        // JSON 메시지 파싱
        WebSocketMessage wsMessage = objectMapper.readValue(payload, WebSocketMessage.class);

        // 메시지 타입에 따른 처리
        switch (wsMessage.getType()) {
            case "CREATE":
                handleCreateBlock(wsMessage.getData());
                break;
            case "UPDATE":
                handleUpdateBlock(wsMessage.getData());
                break;
            case "DELETE":
                handleDeleteBlock(wsMessage.getData());
                break;
            case "REORDER":
                handleReorderBlocks(wsMessage.getData());
                break;
            case "MERGE":
                handleMergeBlocks(wsMessage.getData());
                break;
            default:
                break;
        }
    }

    private void handleCreateBlock(Object data) {
        // 블록 생성 처리
        System.out.println("Creating Block: " + data);
        ReqAddDocumentDto dto = (ReqAddDocumentDto) data;

        // DocumentService를 통해 블록(문서) 생성
        documentService.postDocument(dto);

        // 실제 DB나 다른 처리를 여기에 추가
    }

    private void handleUpdateBlock(Object data) {
        // 블록 수정 처리
        System.out.println("Updating Block: " + data);
        ReqUpdateDocumentDto dto = (ReqUpdateDocumentDto) data;

        // DocumentService를 통해 블록(문서) 수정
        documentService.updateDocument(dto);

        // 실제 DB나 다른 처리를 여기에 추가
    }

    private void handleDeleteBlock(Object data) {
        // 블록 삭제 처리
        System.out.println("Deleting Block: " + data);
        Long documentId = (Long) data;

        // DocumentService를 통해 블록(문서) 삭제
        documentService.deleteDocument(documentId);

        // 실제 DB나 다른 처리를 여기에 추가
    }

    private void handleReorderBlocks(Object data) {
        // 블록 순서 변경 처리
        System.out.println("Reordering Blocks: " + data);
        // 순서 변경에 대한 로직 추가 (예: 데이터베이스에서 순서 업데이트)
        // 데이터 처리 후, 클라이언트에게 순서 변경 메시지 전송
    }

    private void handleMergeBlocks(Object data) {
        // 블록 병합 처리
        System.out.println("Merging Blocks: " + data);
        // 병합 처리에 대한 로직 추가 (예: 두 블록을 병합하여 하나로 만듦)
        // 데이터 처리 후, 클라이언트에게 병합된 블록 메시지 전송
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        System.out.println("WebSocket connection established");
    }

    // WebSocket 메시지 구조에 맞는 DTO 클래스
    public static class WebSocketMessage {
        private String type;
        private Object data;

        // Getters and Setters
        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }
    }
}
