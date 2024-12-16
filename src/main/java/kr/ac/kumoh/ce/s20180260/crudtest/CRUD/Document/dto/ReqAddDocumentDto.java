package kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Document.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReqAddDocumentDto extends BaseReqDocumentDto {
    public ReqAddDocumentDto(Long projectId, String title, String htmlContent, Integer order) {
        super(projectId, title, htmlContent, order);
    }
}
