package kr.ac.kumoh.ce.s20180260.crudtest.CRUD.ProjectUsers.entity;

import java.io.Serializable;
import jakarta.persistence.Column;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectUsersPK implements Serializable {

    @Column(name = "project_id")
    private Long projectId;

    @Column(name = "user_id")
    private Long userId;

    // equals와 hashCode 구현 (복합키에 필수)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectUsersPK that = (ProjectUsersPK) o;
        return projectId.equals(that.projectId) && userId.equals(that.userId);
    }

    @Override
    public int hashCode() {
        return 31 * projectId.hashCode() + userId.hashCode();
    }
}
