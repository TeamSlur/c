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

@Service
public class DocumentService {
    @Autowired
    private DocumentRepository repository;

    public ResponseEntity<List<ResGetDocumentDto>> getDocumentsByProjectId(Long pid) {
        List<ResGetDocumentDto> documentList = repository.findDocumentEntitiesByProjectId(pid)
                .stream()
                .map(ResGetDocumentDto::new)
                .toList();
        return ResponseEntity.ok(documentList);
    }

    // document 생성
    @Transactional
    public ResponseEntity<ResGetDocumentDto> postDocument(ReqAddDocumentDto request) {
        DocumentEntity entity = repository.save(request.toEntity());
        return ResponseEntity.ok(new ResGetDocumentDto(entity));
    }

    // document 수정
    @Transactional
    public ResponseEntity<ResGetDocumentDto> updateDocument(ReqUpdateDocumentDto dto){
        DocumentEntity entity = repository.findById(dto.getDocumentId())
                .orElseThrow(EntityNotFoundException::new);
        entity.updateDocument(dto);

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
