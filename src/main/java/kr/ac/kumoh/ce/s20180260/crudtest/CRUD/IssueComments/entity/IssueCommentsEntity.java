package kr.ac.kumoh.ce.s20180260.crudtest.CRUD.IssueComments.entity;

import jakarta.persistence.*;
import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.IssueComments.dto.ReqUpdateIssueCommentsDto;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "issue_comments")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IssueCommentsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id", nullable = false)
    private Long commentId;

    @Column(name = "issue_id", nullable = false)
    private Long issueId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    private void prePersist(){
        if( this.createdAt == null) this.createdAt = LocalDateTime.now();
        if( this.updatedAt == null) this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    private void preUpdate(){
        this.updatedAt = LocalDateTime.now();
    }

    public void updateComment(ReqUpdateIssueCommentsDto request) {
        this.content = request.getContent();
    }

    public IssueCommentsEntityBuilder toBuilder(){
        return IssueCommentsEntity.builder()
                .commentId(this.getCommentId())
                .issueId(this.getIssueId())
                .userId(this.getUserId())
                .content(this.getContent())
                .createdAt(this.getCreatedAt())
                .updatedAt(this.getUpdatedAt());
    }
}