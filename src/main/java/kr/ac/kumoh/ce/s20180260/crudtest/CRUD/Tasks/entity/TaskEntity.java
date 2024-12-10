package kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Tasks.entity;

import jakarta.persistence.*;
import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Tasks.dto.ReqTaskDto;
import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Tasks.dto.ReqUpdateTaskDto;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id", nullable = false)
    private Long taskId;
    @Column(name = "project_id", nullable = false)
    private Long projectId;
    @Column(name = "task_name", nullable = false)
    private String taskName;
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist(){
        if(this.createdAt == null) this.createdAt = LocalDateTime.now();
        if(this.updatedAt == null) this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate(){
        this.updatedAt = LocalDateTime.now();
    }

    public void updateTask(ReqUpdateTaskDto dto){
        this.taskName = dto.getTaskName();
    }
    /*
    // dto to entity
    public TaskEntityBuilder toBuilder(){
        return TaskEntity.builder()
                .taskId(this.getTaskId())
                .projectId(this.getProjectId())
                .taskName(this.getTaskName());
    }
    */
}
