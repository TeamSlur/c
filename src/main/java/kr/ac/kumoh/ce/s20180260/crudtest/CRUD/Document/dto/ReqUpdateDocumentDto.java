package kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Document.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReqUpdateDocumentDto {
    private Long documentId;
    private String title;
    private String htmlContent;
}
