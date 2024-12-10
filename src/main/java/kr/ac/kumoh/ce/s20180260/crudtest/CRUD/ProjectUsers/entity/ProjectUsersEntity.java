package kr.ac.kumoh.ce.s20180260.crudtest.CRUD.ProjectUsers.entity;

import jakarta.persistence.*;
import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Role.ProjectUsersRoleEnum;
import lombok.*;

@Entity
@Table(name = "project_users")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@IdClass(ProjectUsersPK.class)
public class ProjectUsersEntity {
    @Id
    @Column(name = "project_id", nullable = false)
    private Long projectId;

    @Id
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "role", nullable = false)
    private ProjectUsersRoleEnum role;
}
