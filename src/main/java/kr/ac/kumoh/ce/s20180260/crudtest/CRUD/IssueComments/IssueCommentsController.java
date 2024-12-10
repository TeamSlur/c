package kr.ac.kumoh.ce.s20180260.crudtest.CRUD.IssueComments;

import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.IssueComments.dto.ReqAddIssueCommentsDto;
import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.IssueComments.dto.ReqUpdateIssueCommentsDto;
import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.IssueComments.dto.ResGetIssueCommentsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/issue/comment")
public class IssueCommentsController {
    @Autowired
    private IssueCommentsService service;

    // 모든 이슈 코멘트 조회
    @GetMapping("/all")
    public ResponseEntity<List<ResGetIssueCommentsDto>> getAllIssuesComments(
    ){
        return service.getAllIssuesComments();
    }

    // 특정 이슈의 코멘트들 조회
    @GetMapping
    public ResponseEntity<List<ResGetIssueCommentsDto>> getAllIssuesCommentsById(
            @RequestParam Long isid
    ){
        return service.getAllIssueCommentsByIssueId(isid);
    }

    // 특정 코멘트의 정보 조회
    @GetMapping("/details")
    public ResponseEntity<ResGetIssueCommentsDto> getIssueCommentById(
            @RequestParam Long isid,
            @RequestParam Long icid
    ){
        return service.getIssueCommentById(icid);
    }

    // 이슈 코멘트 생성
    @PostMapping("/add")
    public ResponseEntity<ResGetIssueCommentsDto> postIssueComment(
            @RequestParam Long isid,
            @RequestBody ReqAddIssueCommentsDto reqIssueCommentDto
    ){
        return service.postIssueComment(reqIssueCommentDto);
    }

    // 이슈 코멘트 수정
    @PutMapping("/edit")
    public ResponseEntity<ResGetIssueCommentsDto> updateIssueComment(
            @RequestParam Long isid,
            @RequestParam Long icid,
            @RequestBody ReqUpdateIssueCommentsDto reqUpdateIssueCommentDto
    ){
        return service.updateIssueComment(reqUpdateIssueCommentDto);
    }

    // 이슈 코멘트 삭제
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteIssueComment(
            @RequestParam Long isid,
            @RequestParam Long icid
    ){
        return service.deleteIssueComment(icid);
    }
}
