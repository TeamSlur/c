package kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Issues.entity;

import jakarta.persistence.*;
import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Issues.dto.ReqUpdateIssueDto;
import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Role.IssueStatusEnum;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "issues")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IssuesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "issue_id", nullable = false)
    private Long issueId;

    @Column(name = "task_id", nullable = false)
    private Long taskId;

    @Column(name = "assigned_to")
    private Long assignedTo;

    @Column(name = "issue_name", nullable = false)
    private String issueName;

    @Column(name = "issue_category")
    private String issueCategory;

    @Column(name = "issue_status")
    private IssueStatusEnum issueStatus;

    @Column(name = "issue_type")
    private String issueType;

    @Column(name = "issue_content")
    private String issueContent;

    @Column(name = "issue_priority", nullable = false)
    private int issuePriority;

    @Column(name = "issue_related_front")
    private Long issueRelatedFront;

    @Column(name = "issue_related_end")
    private Long issueRelatedEnd;

    @Column(name = "created_by", nullable = false)
    private Long createBy;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    private void prePersist(){
        if (this.issueStatus == null) this.issueStatus = IssueStatusEnum.TODO;
        if (this.createdAt == null) this.createdAt = LocalDateTime.now();
        if (this.updatedAt == null) this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    private void preUpdate(){
        this.updatedAt = LocalDateTime.now();
    }

    public void updateIssue(ReqUpdateIssueDto dto){
        this.taskId = dto.getTaskId();
        this.assignedTo = dto.getAssignedTo();
        this.issueName = dto.getIssueName();
        this.issueStatus = dto.getIssueStatus();
        this.issueCategory = dto.getIssueCategory();
        this.issueType = dto.getIssueType();
        this.issueContent = dto.getIssueContent();
        this.issuePriority = dto.getIssuePriority();
        this.issueRelatedFront = dto.getIssueRelatedFront();
        this.issueRelatedEnd = dto.getIssueRelatedEnd();
        this.createBy = dto.getCreateBy();
    }

    /*
    public IssuesEntityBuilder toBuilder() {
        return IssuesEntity.builder()
                .issueId(this.getIssueId())
                .taskId(this.getTaskId())
                .assignedTo(this.getAssignedTo())
                .issueName(this.getIssueName())
                .issueCategory(this.getIssueCategory())
                .issueStatus(this.getIssueStatus())
                .issueType(this.getIssueType())
                .issueContent(this.getIssueContent())
                .issuePriority(this.getIssuePriority())
                .issueRelatedFront(this.getIssueRelatedFront())
                .issueRelatedEnd(this.getIssueRelatedEnd())
                .createBy(this.getCreateBy())
                .createdAt(this.getCreatedAt())
                .updatedAt(this.getUpdatedAt());
    }*/

}