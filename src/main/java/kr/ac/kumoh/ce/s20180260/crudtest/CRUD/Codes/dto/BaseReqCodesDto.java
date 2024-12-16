package kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Codes.dto;

import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Codes.entity.CodesEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseReqCodesDto {
    private Long codeIssueId;
    private String codeTypes;
    private String codeName;
    private String codeContent;
    private String codeImage;
    private String codeDescription;
    private Long createdBy;
    // entity to dto
    public BaseReqCodesDto(CodesEntity entity) {
        this.codeIssueId = entity.getCodeIssueId();
        this.codeTypes = entity.getCodeTypes();
        this.codeName = entity.getCodeName();
        this.codeImage = entity.getCodeImage();
        this.codeDescription = entity.getCodeDescription();
        this.createdBy = entity.getCreatedBy();
    }

    // dto to entity
    public CodesEntity toEntity(){
        return CodesEntity.builder()
                .codeIssueId(this.getCodeIssueId())
                .codeTypes(this.getCodeTypes())
                .codeName(this.getCodeName())
                .codeContent(this.getCodeContent())
                .codeImage(this.getCodeImage())
                .codeDescription(this.getCodeDescription())
                .createdBy(this.getCreatedBy())
                .build();
    }

}
