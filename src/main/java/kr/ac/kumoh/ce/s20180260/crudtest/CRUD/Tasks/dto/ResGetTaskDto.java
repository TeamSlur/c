package kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Tasks.dto;

import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Tasks.entity.TaskEntity;

public class ResGetTaskDto extends BaseResDto{

    // entity to dto
    public ResGetTaskDto(TaskEntity entity){
        super(entity);
    }
}
