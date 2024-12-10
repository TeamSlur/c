package kr.ac.kumoh.ce.s20180260.crudtest.CRUD.IssueComments.dto;

import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.IssueComments.entity.IssueCommentsEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
abstract class BaseReqIssueCommentsDto {
    private Long issueId;
    private Long userId;
    private String content;

    // entity to dto
    public BaseReqIssueCommentsDto(IssueCommentsEntity issueCommentsEntity) {
        this.issueId = issueCommentsEntity.getIssueId();
        this.userId = issueCommentsEntity.getUserId();
        this.content = issueCommentsEntity.getContent();
    }

    // dto to entity(builder)
    public IssueCommentsEntity toEntity() {
        return IssueCommentsEntity.builder()
                .issueId(this.getIssueId())
                .userId(this.getUserId())
                .content(this.getContent())
                .build();
    }
}
