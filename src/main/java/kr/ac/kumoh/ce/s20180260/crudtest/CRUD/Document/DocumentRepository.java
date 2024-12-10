package kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Document;

import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Document.dto.ResGetDocumentDto;
import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Document.entity.DocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<DocumentEntity, Long> {
    List<DocumentEntity> findDocumentEntitiesByProjectId(Long pid);

}
