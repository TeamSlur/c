package kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Issues;

import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Issues.entity.IssuesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssuesRepository extends JpaRepository<IssuesEntity, Long> {

    /*
     todo : db 구조 정리하기
     pid를 통해 해당 project에 속한 issus 모두 조회하기

     현재 issue에는 task Id 속성이 있고, projectId 속성이 없음
     따라서, project, task, issue 3개의 테이블에 대해 join을 통해 구현
     */
    @Query(
            value = "SELECT i.* FROM issues i " +
                    "JOIN tasks t on i.task_id = t.task_id " +
                    "JOIN projects p on t.project_id = p.project_id " +
                    "WHERE p.project_id = :pid",
            nativeQuery = true
    )
    List<IssuesEntity> findIssueEntitiesByProjectId(@Param("pid") Long pid);
}
