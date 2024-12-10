package kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Issues.dto;

import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Issues.entity.IssuesEntity;
import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Role.IssueStatusEnum;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseResIssueDto {
    private Long issueId;
    private Long taskId;
    private Long assignedTo;
    private String issueName;
    private IssueStatusEnum issueStatus;
    private String issueCategory;
    private String issueType;
    private String issueContent;
    private int issuePriority;
    private Long issueRelatedFront;
    private Long issueRelatedEnd;
    private Long createBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // entity to dto
    protected BaseResIssueDto(IssuesEntity entity) {
        this.issueId = entity.getIssueId();
        this.taskId = entity.getTaskId();
        this.issueName = entity.getIssueName();
        this.assignedTo = entity.getAssignedTo();
        this.issueCategory = entity.getIssueCategory();
        this.issueStatus = entity.getIssueStatus();
        this.issueType = entity.getIssueType();
        this.issueContent = entity.getIssueContent();
        this.issuePriority = entity.getIssuePriority();
        this.issueRelatedFront = entity.getIssueRelatedFront();
        this.issueRelatedEnd = entity.getIssueRelatedEnd();
        this.createBy = entity.getCreateBy();
        this.createdAt = entity.getCreatedAt();
        this.updatedAt = entity.getUpdatedAt();
    }

    // dto to entity
    public IssuesEntity toEntity(){
        return IssuesEntity.builder()
                .issueId(this.getIssueId())
                .taskId(this.getTaskId())
                .issueName(this.getIssueName())
                .assignedTo(this.getAssignedTo())
                .issueCategory(this.getIssueCategory())
                .issueStatus(this.getIssueStatus())
                .issueType(this.getIssueType())
                .issueContent(this.getIssueContent())
                .issuePriority(this.getIssuePriority())
                .issueRelatedFront(this.getIssueRelatedFront())
                .issueRelatedEnd(this.getIssueRelatedEnd())
                .createBy(this.getCreateBy())
                .createdAt(this.getCreatedAt())
                .updatedAt(this.getUpdatedAt())
                .build();
    }
}

