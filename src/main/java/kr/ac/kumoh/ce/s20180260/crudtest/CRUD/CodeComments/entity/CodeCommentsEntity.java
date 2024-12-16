package kr.ac.kumoh.ce.s20180260.crudtest.CRUD.CodeComments.entity;

import jakarta.persistence.*;
import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.CodeComments.dto.ReqUpdateCodeCommentsDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "code_comments")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CodeCommentsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id", nullable = false)
    private Long commentId;

    @Column(name = "code_id", nullable = false)
    private Long codeId;

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

    public void updateComment(ReqUpdateCodeCommentsDto request) {
        this.content = request.getContent();
    }

    public CodeCommentsEntity.CodeCommentsEntityBuilder toBuilder(){
        return CodeCommentsEntity.builder()
                .commentId(this.getCommentId())
                .codeId(this.getCodeId())
                .userId(this.getUserId())
                .content(this.getContent())
                .createdAt(this.getCreatedAt())
                .updatedAt(this.getUpdatedAt());
    }
}