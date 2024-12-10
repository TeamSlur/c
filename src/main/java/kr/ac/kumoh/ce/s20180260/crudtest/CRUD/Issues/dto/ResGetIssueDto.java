package kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Issues.dto;

import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Issues.entity.IssuesEntity;
import lombok.*;

public class ResGetIssueDto extends BaseResIssueDto {
    // entity to dto
    public ResGetIssueDto(IssuesEntity entity){
        super(entity);
    }
}
