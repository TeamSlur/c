package kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Tasks.dto;

import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Tasks.entity.TaskEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseResDto {
    private Long taskId;
    private Long projectId;
    private String taskName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public BaseResDto(TaskEntity entity){
        this.taskId = entity.getTaskId();
        this.projectId = entity.getProjectId();
        this.taskName = entity.getTaskName();
        this.createdAt = entity.getCreatedAt();
        this.updatedAt = entity.getUpdatedAt();
    }
    public TaskEntity toEntity(){
        return TaskEntity.builder()
                .taskId(this.getTaskId())
                .projectId(this.getProjectId())
                .taskName(this.getTaskName())
                .createdAt(this.getCreatedAt())
                .updatedAt(this.getUpdatedAt())
                .build();
    }
}
