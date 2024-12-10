package kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Tasks.dto;

import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Tasks.entity.TaskEntity;

public class TaskMapper {

    // mapper 테스트 (당장은 사용 X)

    // baseDto to entity
    public static TaskEntity toEntity(BaseReqDto dto) {
        return TaskEntity.builder()
                .projectId(dto.getProjectId())
                .taskName(dto.getTaskName())
                .build();
    }

    // entity to resTaskDto
    public static ResTaskDto toResTaskDto(TaskEntity entity) {
        return new ResTaskDto(entity);
    }

    public static TaskDto toTaskDto(TaskEntity entity){
        return new TaskDto(entity);
    }

}

