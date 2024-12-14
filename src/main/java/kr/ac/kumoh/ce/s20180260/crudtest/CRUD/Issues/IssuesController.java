package kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Issues;

import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Issues.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/issue")
public class IssuesController {
    @Autowired
    private IssuesService issuesService;

/*
    // 모든 이슈를 조회하는 api (개발 단계에서만 사용)
    @GetMapping("/all")
    public ResponseEntity<List<ResGetIssueDto>> getAllIssues() {
        return issuesService.getAllIssues();
    }
*/

    // issue id에 대한 정보를 요청하는 API ( 복수 )
    @GetMapping("/all")
    public ResponseEntity<List<ResGetIssueDto>> getAllIssuesByTaskId(
            @RequestParam Long tid){
        return issuesService.getAllIssuesByTaskId(tid);
    }

    // issue 내용에 대한 정보를 요청하기 위한  API ( 단수 )
    @GetMapping("/details")
    public ResponseEntity<ResAddIssueDto> getIssueDetailByIssueId(
            @RequestParam Long tid,
            @RequestParam Long isid) {
        return issuesService.getIssueDetailByIssueId(isid);
    }

    // issue 정보를 추가하기 위한 API
    @PostMapping("/add")
    public ResponseEntity<ResAddIssueDto> addIssue(
            @RequestParam Long tid,
            @RequestBody ReqAddResIssueDto reqAddIssueDto) {
        return issuesService.addIssue(reqAddIssueDto);
    }

    // issue id에 대한 정보를 수정하는 API
    @PutMapping("/edit")
    public ResponseEntity<ResUpdateIssueDto> editIssue(
            @RequestParam Long tid,
            @RequestParam Long isid,
            @RequestBody ReqUpdateIssueDto reqUpdateIssueDto) {
        return issuesService.editIssue(isid, reqUpdateIssueDto);
    }

    // issue id에 대한 정보를 제거하는 API
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteIssue(
            @RequestParam Long tid,
            @RequestParam Long isid) {
        return issuesService.deleteIssue(isid);
    }
}
