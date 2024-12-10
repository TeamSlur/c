package kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Issues.dto;

import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Issues.entity.IssuesEntity;

public class ResAddIssueDto extends BaseResIssueDto {
    public ResAddIssueDto(IssuesEntity entity){
        super(entity);
    }
    public IssuesEntity toEntity() {
        return super.toEntity();
    }
}
