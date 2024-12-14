package kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Tasks;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Tasks.dto.*;
import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Tasks.entity.TaskEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository repository;

    // 모든 task 조회
    public ResponseEntity<List<ResGetTaskDto>> getAllTasks() {
        List<ResGetTaskDto> taskList = repository.findAll()
                .stream()
                .map(ResGetTaskDto::new)
                .toList();
        return ResponseEntity.ok(taskList);
    }

    // project 의 task 모두 조회
    public ResponseEntity<List<ResGetTaskDto>> getAllTasksByProjectId(Long projectId) {
        List<ResGetTaskDto> taskList = repository.findTaskEntitiesByProjectId(projectId)
                .stream()
                .map(ResGetTaskDto::new)
                .toList();
        return ResponseEntity.ok(taskList);
    }

    // 특정 task 에 대해 조회
    public ResponseEntity<ResTaskDto> getTaskById(Long taskId) {
        if(repository.existsById(taskId)){
            TaskEntity taskEntity =  repository.findById(taskId)
                    .orElseThrow(()->new EntityNotFoundException("Task" + taskId + " Not found"));
            return ResponseEntity.ok(new ResTaskDto(taskEntity));
        }
        return ResponseEntity.notFound().build();
    }

    // task 추가하기
    @Transactional
    public ResponseEntity<ResTaskDto> addTask(ReqAddTaskDto request) {
        TaskEntity taskEntity = repository.save(request.toEntity());
        return ResponseEntity.ok(new ResTaskDto(taskEntity));
    }

    // task 갱신하기

    @Transactional
    public ResponseEntity<ResTaskDto> updateTask(Long taskId, ReqUpdateTaskDto request) {
        if (repository.existsById(taskId)) {
            TaskEntity entity = repository.findById(taskId).orElseThrow(EntityExistsException::new);
            entity.updateTask(request);
            return ResponseEntity.ok(new ResTaskDto(entity));
        }
        return ResponseEntity.notFound().build();
    }

    // task 삭제
    @Transactional
    public ResponseEntity<Void> deleteTask(Long id) {
        try {
            if (!repository.existsById(id)) {
                return ResponseEntity.notFound().build();
            }
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
