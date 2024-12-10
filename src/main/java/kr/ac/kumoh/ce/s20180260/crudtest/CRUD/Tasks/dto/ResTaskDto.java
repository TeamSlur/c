package kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Tasks.dto;

import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Tasks.entity.TaskEntity;
import lombok.*;

public class ResTaskDto extends BaseResDto {
    // entity to dto
    public ResTaskDto(TaskEntity entity){
        super(entity);
    }
}
