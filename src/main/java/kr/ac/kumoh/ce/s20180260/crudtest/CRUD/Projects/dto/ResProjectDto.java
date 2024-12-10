package kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Projects.dto;

import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Projects.entity.ProjectEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResProjectDto {
    private Long projectId;
    private String projectName;
    private String description;
    private Long createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAy;

    // entity to dto
    public ResProjectDto(ProjectEntity entity){
        this.projectId = entity.getProjectId();
        this.projectName = entity.getProjectName();
        this.description = entity.getDescription();
        this.createdBy = entity.getCreatedBy();
        this.createdAt = entity.getCreatedAt();
        this.updatedAy = entity.getUpdatedAt();
    }
}