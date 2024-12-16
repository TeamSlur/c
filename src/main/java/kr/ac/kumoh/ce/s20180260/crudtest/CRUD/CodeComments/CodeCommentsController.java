package kr.ac.kumoh.ce.s20180260.crudtest.CRUD.CodeComments;

import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.CodeComments.dto.ReqAddCodeCommentsDto;
import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.CodeComments.dto.ResGetCodeCommentsDto;
import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.CodeComments.dto.ReqUpdateCodeCommentsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/code/comment")
public class CodeCommentsController {
    @Autowired
    private CodeCommentsService service;
/*

    // 모든 이슈 코멘트 조회
    @GetMapping("/all")
    public ResponseEntity<List<ResGetCodeCommentsDto>> getAllIssuesComments(
    ){
        return service.getAllIssuesComments();
    }

*/
    // 특정 이슈의 코멘트들 조회
    @GetMapping("/all")
    public ResponseEntity<List<ResGetCodeCommentsDto>> getAllCodeCommentsById(
            @RequestParam Long cid
    ){
        return service.getAllIssueCommentsByIssueId(cid);
    }
/*

    // 특정 코멘트의 정보 조회
    @GetMapping("/details")
    public ResponseEntity<ResGetCodeCommentsDto> getIssueCommentById(
            @RequestParam Long isid,
            @RequestParam Long icid
    ){
        return service.getIssueCommentById(icid);
    }
*/

    // 이슈 코멘트 생성
    @PostMapping("/add")
    public ResponseEntity<ResGetCodeCommentsDto> postCodeComment(
            @RequestParam Long cid,
            @RequestBody ReqAddCodeCommentsDto reqIssueCommentDto,
            @RequestHeader("Authorization") String token
    ){
        return service.postCodeComment(reqIssueCommentDto, token);
    }

    // 이슈 코멘트 수정
    @PutMapping("/edit")
    public ResponseEntity<ResGetCodeCommentsDto> updateCodeComment(
            @RequestParam Long cid,
            @RequestParam Long ccid,
            @RequestBody ReqUpdateCodeCommentsDto reqUpdateIssueCommentDto
    ){
        return service.updateCodeComment(reqUpdateIssueCommentDto);
    }

    // 이슈 코멘트 삭제
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteCodeComment(
            @RequestParam Long cid,
            @RequestParam Long ccid
    ){
        return service.deleteCodeComment(ccid);
    }
}
