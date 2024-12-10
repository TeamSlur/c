package kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Projects.dto;

import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Projects.entity.ProjectEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReqUpdateProjectDto {
    private Long projectId;
    private String projectName;
    private String description;

    // dto to entity
    public ProjectEntity toEntity(){
        return ProjectEntity.builder()
                .projectId(this.getProjectId())
                .projectName(this.getProjectName())
                .description(this.getDescription())
                .build();
    }
}
