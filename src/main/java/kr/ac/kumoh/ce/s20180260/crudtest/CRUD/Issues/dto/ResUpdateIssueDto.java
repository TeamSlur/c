package kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Issues.dto;

import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Issues.entity.IssuesEntity;
import lombok.*;

public class ResUpdateIssueDto extends BaseResIssueDto {
    public ResUpdateIssueDto(IssuesEntity entity){
        super(entity);
    }
    public IssuesEntity toEntity() {
        return super.toEntity();
    }
}
