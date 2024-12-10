package kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Projects.dto;

import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Issues.dto.ResGetIssueDto;
import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Tasks.dto.ResGetTaskDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

// project 정보 조회 시 controller 에 반환할 dto
/*
 https://www.notion.so/rosemari/develop-pid-project_id-250b8c71079642bdbf640b4cd7d72035
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResGetProjectDto {
    private ResProjectDto projectDto;
    private List<ResGetTaskDto> taskList;
    private List<ResGetIssueDto> issueList;
}
