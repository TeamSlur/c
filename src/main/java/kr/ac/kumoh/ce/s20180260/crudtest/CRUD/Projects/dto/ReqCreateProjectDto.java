package kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Projects.dto;

import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Projects.entity.ProjectEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ReqCreateProjectDto {
    private String projectName;
    private String description;
    private Long createdBy;

    // dto to entity
    public ProjectEntity toEntity(){
        return ProjectEntity.builder()
                .projectName(this.getProjectName())
                .description(this.getDescription())
                .createdBy(this.getCreatedBy())
                .build();
    }
}
