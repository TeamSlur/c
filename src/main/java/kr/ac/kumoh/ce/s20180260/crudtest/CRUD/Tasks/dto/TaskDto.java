package kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Tasks.dto;

import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Tasks.entity.TaskEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto extends BaseReqDto {
    private Long taskId;

    // entity to dto
    public TaskDto(TaskEntity task){
        super(task);
        this.taskId = task.getTaskId();
    }
}
