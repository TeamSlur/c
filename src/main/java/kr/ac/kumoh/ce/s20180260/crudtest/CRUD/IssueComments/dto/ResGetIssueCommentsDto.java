package kr.ac.kumoh.ce.s20180260.crudtest.CRUD.IssueComments.dto;

import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.IssueComments.entity.IssueCommentsEntity;

public class ResGetIssueCommentsDto extends BaseResIssueCommentsDto{
    public ResGetIssueCommentsDto(IssueCommentsEntity entity){
        super(entity);
    }
}
