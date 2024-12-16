package kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Document;

import jakarta.persistence.EntityNotFoundException;
import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Document.dto.ReqAddDocumentDto;
import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Document.dto.ReqUpdateDocumentDto;
import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Document.dto.ResGetDocumentDto;
import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Document.entity.DocumentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentService {
    @Autowired
    private DocumentRepository repository;

    public ResponseEntity<List<ResGetDocumentDto>> getDocumentsByProjectId(Long pid) {
        List<ResGetDocumentDto> documentList = repository.findByProjectId(pid)
                .stream()
                .map(ResGetDocumentDto::new)
                .toList();
        return ResponseEntity.ok(documentList);
    }

    // 문서 추가
    public ResponseEntity<ResGetDocumentDto> postDocument(ReqAddDocumentDto reqAddDocumentDto) {
        // ReqAddDocumentDto를 DocumentEntity로 변환
        DocumentEntity documentEntity = DocumentEntity.builder()
                .projectId(reqAddDocumentDto.getProjectId())
                .title(reqAddDocumentDto.getTitle())
                .htmlContent(reqAddDocumentDto.getHtmlContent())
                .createdBy(reqAddDocumentDto.getCreatedBy()) // 예: 요청자 정보
                .order(reqAddDocumentDto.getOrder()) // 문서 순서
                .build();

        // DB에 저장
        documentEntity = repository.save(documentEntity);

        // 저장된 문서를 DTO로 변환하여 반환
        ResGetDocumentDto resGetDocumentDto = new ResGetDocumentDto(documentEntity);
        return ResponseEntity.ok(resGetDocumentDto);
    }

    // document 수정
    @Transactional
    public ResponseEntity<ResGetDocumentDto> updateDocument(ReqUpdateDocumentDto dto) {
        DocumentEntity entity = repository.findById(dto.getDocumentId())
                .orElseThrow(() -> new EntityNotFoundException("Document with ID " + dto.getDocumentId() + " not found"));

        entity.updateDocument(dto); // entity에서 updateDocument 메서드 호출

        return ResponseEntity.ok(new ResGetDocumentDto(entity));
    }

    // document 제거
    @Transactional
    public ResponseEntity<Void> deleteDocument(Long documentId){
        if(repository.existsById(documentId)){
            repository.deleteById(documentId);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
