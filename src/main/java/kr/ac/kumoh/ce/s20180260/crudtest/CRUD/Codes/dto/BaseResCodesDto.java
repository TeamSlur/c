package kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Codes.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Codes.entity.CodesEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseResCodesDto {
    private Long codeId;
    private Long codeIssueId;
    private String codeTypes;
    private String codeName;
    private String codeContent;
    private String codeImage;
    private String codeDescription;
    private String createdBy;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    // entity to dto
    public BaseResCodesDto(CodesEntity entity){
        this.codeId = entity.getCodeId();
        this.codeIssueId = entity.getCodeIssueId();
        this.codeTypes = entity.getCodeTypes();
        this.codeName = entity.getCodeName();
        this.codeImage = entity.getCodeImage();
        this.codeDescription = entity.getCodeDescription();
        this.createdBy = entity.getCreatedBy();
        this.createDate = entity.getCreateDate();
        this.updateDate = entity.getUpdateDate();
    }

    // dto to entity
    public CodesEntity toEntity(){
        return CodesEntity.builder()
                .codeId(this.getCodeId())
                .codeIssueId(this.getCodeIssueId())
                .codeTypes(this.getCodeTypes())
                .codeName(this.getCodeName())
                .codeContent(this.getCodeContent())
                .codeImage(this.getCodeImage())
                .codeDescription(this.getCodeDescription())
                .createdBy(this.getCreatedBy())
                .createDate(this.getCreateDate())
                .updateDate(this.getUpdateDate())
                .build();
    }
}
