package kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Codes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReqUpdateCodeDto{
    private Long codeId;
    private Long codeIssueId;
    private String codeTypes;
    private String codeName;
    private String codeContent;
    private String codeImage;
    private String codeDescription;
    private Long createBy;
}
