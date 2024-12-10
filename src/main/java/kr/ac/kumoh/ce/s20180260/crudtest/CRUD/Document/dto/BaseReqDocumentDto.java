package kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Document.dto;

import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Document.entity.DocumentEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseReqDocumentDto {
    private Long projectId;
    private String title;
    private String htmlContent;
    private Long createdBy;

    // entity to dto
    public BaseReqDocumentDto(DocumentEntity entity){
        this.projectId = entity.getProjectId();
        this.title = entity.getTitle();
        this.htmlContent = entity.getHtmlContent();
        this.createdBy = entity.getCreatedBy();
    }

    // dto to entity
    public DocumentEntity toEntity(){
        return DocumentEntity.builder()
                .projectId(this.getProjectId())
                .title(this.getTitle())
                .htmlContent(this.getHtmlContent())
                .createdBy(this.getCreatedBy())
                .build();
    }
}
