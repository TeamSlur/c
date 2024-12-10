package kr.ac.kumoh.ce.s20180260.crudtest.CRUD.ProjectUsers.dto;

import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.ProjectUsers.entity.ProjectUsersEntity;
import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Role.ProjectUsersRoleEnum;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReqAddProjectUserDto {
    private Long uid;
    private Long pid;
    private ProjectUsersRoleEnum role;

    // dto to entity
    public ProjectUsersEntity toEntity(){
        return ProjectUsersEntity.builder()
                .projectId(this.getPid())
                .userId(this.getUid())
                .role(this.getRole())
                .build();
    }
}