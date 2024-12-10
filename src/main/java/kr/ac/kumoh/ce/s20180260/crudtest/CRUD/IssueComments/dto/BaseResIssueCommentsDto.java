package kr.ac.kumoh.ce.s20180260.crudtest.CRUD.IssueComments.dto;

import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.IssueComments.entity.IssueCommentsEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
abstract class BaseResIssueCommentsDto {
    private Long commentId;
    private Long issueId;
    private Long userId;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // entity to dto
    public BaseResIssueCommentsDto(IssueCommentsEntity entity) {
        this.commentId = entity.getCommentId();
        this.issueId = entity.getIssueId();
        this.userId = entity.getUserId();
        this.content = entity.getContent();
        this.createdAt = entity.getCreatedAt();
        this.updatedAt = entity.getUpdatedAt();
    }

    // dto to entity(builder)
    public IssueCommentsEntity toEntity() {
        return IssueCommentsEntity.builder()
                .commentId(this.getCommentId())
                .issueId(this.getIssueId())
                .userId(this.getUserId())
                .content(this.getContent())
                .createdAt(this.getCreatedAt())
                .updatedAt(this.getUpdatedAt())
                .build();
    }
}
