package kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Tasks.dto;

import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Tasks.entity.TaskEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseReqDto {
    private Long projectId;
    private String taskName;

    // entity to dto
    public BaseReqDto(TaskEntity task){
        this.projectId = task.getProjectId();
        this.taskName = task.getTaskName();
    }

    // dto to entity
    public TaskEntity toEntity() {
        return TaskEntity.builder()
                .projectId(this.getProjectId())
                .taskName(this.getTaskName())
                .build();
    }
}
