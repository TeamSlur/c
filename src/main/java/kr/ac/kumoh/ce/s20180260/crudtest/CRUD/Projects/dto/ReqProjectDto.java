package kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Projects.dto;

import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Projects.entity.ProjectEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReqProjectDto {
    private String projectName;

    // dto to entity
    public ProjectEntity toEntity(){
        return ProjectEntity.builder()
                .projectName(this.getProjectName())
                .build();
    }
}
