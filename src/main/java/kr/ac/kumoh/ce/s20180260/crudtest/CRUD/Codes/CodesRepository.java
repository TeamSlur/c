package kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Codes;

import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Codes.dto.ResGetCodeDto;
import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Codes.entity.CodesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CodesRepository extends JpaRepository<CodesEntity, Long> {
    @Query(
            value = "SELECT c.* FROM codes c " +
                    "JOIN issues i on i.issue_id = c.issues_id " +
                    "JOIN tasks t on t.task_id = i.task_id"+
                    "JOIN projects p on i.project_id = p.project_id " +
                    "WHERE p.project_id = :pid",
            nativeQuery = true
    )
    List<CodesEntity> findCodesEntitiesByProjectId(Long pid);
}
