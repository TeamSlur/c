package kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Projects;

import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Projects.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {
    // 사용자의 id를 통해 project_users, projects 테이블에 join
    // 결과값으로 사용자가 속한 project 목록 조회
    @Query(
            value = "SELECT p.* FROM projects p " +
                    "JOIN project_users m on p.project_id = m.project_id " +
                    "WHERE project_users.user_id = :uid",
            nativeQuery = true
    )
    public List<ProjectEntity> findProjectEntitiesByUid(Long uid);
}
