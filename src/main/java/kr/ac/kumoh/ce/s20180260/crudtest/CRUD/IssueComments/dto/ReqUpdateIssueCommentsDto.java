package kr.ac.kumoh.ce.s20180260.crudtest.CRUD.IssueComments.dto;

import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.IssueComments.entity.IssueCommentsEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReqUpdateIssueCommentsDto{
    private Long commentId;
    private String content;
}
