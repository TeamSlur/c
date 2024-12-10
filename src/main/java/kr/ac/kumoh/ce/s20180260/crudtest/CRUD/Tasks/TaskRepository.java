package kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Tasks;

import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Tasks.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
    List<TaskEntity> findTaskEntitiesByProjectId(Long projectId);
}
