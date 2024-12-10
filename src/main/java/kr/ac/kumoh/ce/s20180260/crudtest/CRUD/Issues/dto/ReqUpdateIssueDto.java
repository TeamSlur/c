package kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Issues.dto;

import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Issues.entity.IssuesEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReqUpdateIssueDto extends BaseReqIssueDto {
    private Long issueId;

    // entity to dto
    public ReqUpdateIssueDto(IssuesEntity entity){
        super(entity);
        this.issueId = entity.getIssueId();
    }

    /*
    // dto to entity
    @Override
    public IssuesEntity toEntity() {
        IssuesEntity entity = super.toEntity();
        return entity.toBuilder()
                .issueId(this.issueId)
                .build();
    }*/
}
