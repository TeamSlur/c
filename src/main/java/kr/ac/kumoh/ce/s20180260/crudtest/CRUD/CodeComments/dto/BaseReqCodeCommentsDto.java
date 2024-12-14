package kr.ac.kumoh.ce.s20180260.crudtest.CRUD.CodeComments.dto;

import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.CodeComments.entity.CodeCommentsEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
abstract class BaseReqCodeCommentsDto {
    private Long codeId;
    private Long userId;
    private String content;

    // entity to dto
    public BaseReqCodeCommentsDto(CodeCommentsEntity issueCommentsEntity) {
        this.codeId = issueCommentsEntity.getCodeId();
        this.userId = issueCommentsEntity.getUserId();
        this.content = issueCommentsEntity.getContent();
    }

    // dto to entity(builder)
    public CodeCommentsEntity toEntity() {
        return CodeCommentsEntity.builder()
                .codeId(this.getCodeId())
                .userId(this.getUserId())
                .content(this.getContent())
                .build();
    }
}
