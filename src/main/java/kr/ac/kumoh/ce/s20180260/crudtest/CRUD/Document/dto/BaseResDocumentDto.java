package kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Document.dto;

import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Document.entity.DocumentEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseResDocumentDto {
    private Long documentId;
    private Long projectId;
    private String title;
    private String htmlContent;
    private Long createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // entity to dto
    public BaseResDocumentDto(DocumentEntity entity){
        this.documentId = entity.getDocumentId();
        this.projectId = entity.getProjectId();
        this.title = entity.getTitle();
        this.htmlContent = entity.getHtmlContent();
        this.createdBy = entity.getCreatedBy();
        this.createdAt = entity.getCreatedAt();
        this.updatedAt = entity.getUpdatedAt();
    }

    // dto to entity
    public DocumentEntity toEntity(){
        return DocumentEntity.builder()
                .documentId(this.getDocumentId())
                .projectId(this.getProjectId())
                .title(this.getTitle())
                .htmlContent(this.getHtmlContent())
                .createdBy(this.getCreatedBy())
                .createdAt(this.getCreatedAt())
                .updatedAt(this.getUpdatedAt())
                .build();
    }
}
