package kr.ac.kumoh.ce.s20180260.crudtest.CRUD.CodeComments.dto;

import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.CodeComments.entity.CodeCommentsEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
abstract class BaseResCodeCommentsDto {
    private Long commentId;
    private Long codeId;
    private Long userId;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // entity to dto
    public BaseResCodeCommentsDto(CodeCommentsEntity entity) {
        this.commentId = entity.getCommentId();
        this.codeId = entity.getCodeId();
        this.userId = entity.getUserId();
        this.content = entity.getContent();
        this.createdAt = entity.getCreatedAt();
        this.updatedAt = entity.getUpdatedAt();
    }

    // dto to entity(builder)
    public CodeCommentsEntity toEntity() {
        return CodeCommentsEntity.builder()
                .commentId(this.getCommentId())
                .codeId(this.getCodeId())
                .userId(this.getUserId())
                .content(this.getContent())
                .createdAt(this.getCreatedAt())
                .updatedAt(this.getUpdatedAt())
                .build();
    }
}
