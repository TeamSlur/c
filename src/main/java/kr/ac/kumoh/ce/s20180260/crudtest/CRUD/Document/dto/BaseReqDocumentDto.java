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
    private Integer order; // order 필드 추가

    // entity to dto
    public BaseReqDocumentDto(DocumentEntity entity){
        this.projectId = entity.getProjectId();
        this.title = entity.getTitle();
        this.htmlContent = entity.getHtmlContent();
        this.createdBy = entity.getCreatedBy();
        this.order = entity.getOrder(); // order 필드도 포함
    }

    // 새로운 생성자 추가 (ReqAddDocumentDto에서 사용)
    public BaseReqDocumentDto(Long projectId, String title, String htmlContent, Integer order) {
        this.projectId = projectId;
        this.title = title;
        this.htmlContent = htmlContent;
        this.order = order; // 생성자에서 order 필드 추가
    }

    // dto to entity
    public DocumentEntity toEntity(){
        return DocumentEntity.builder()
                .projectId(this.getProjectId())
                .title(this.getTitle())
                .htmlContent(this.getHtmlContent())
                .createdBy(this.getCreatedBy())
                .order(this.getOrder()) // order 필드 포함
                .build();
    }
}
