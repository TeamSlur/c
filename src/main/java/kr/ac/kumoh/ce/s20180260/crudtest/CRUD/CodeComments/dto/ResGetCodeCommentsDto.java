package kr.ac.kumoh.ce.s20180260.crudtest.CRUD.CodeComments.dto;

import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.CodeComments.entity.CodeCommentsEntity;

public class ResGetCodeCommentsDto extends BaseResCodeCommentsDto {
    public ResGetCodeCommentsDto(CodeCommentsEntity entity){
        super(entity);
    }
}
