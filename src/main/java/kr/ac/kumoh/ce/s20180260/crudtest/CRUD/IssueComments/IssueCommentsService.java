package kr.ac.kumoh.ce.s20180260.crudtest.CRUD.IssueComments;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.IssueComments.dto.ReqAddIssueCommentsDto;
import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.IssueComments.dto.ReqUpdateIssueCommentsDto;
import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.IssueComments.dto.ResGetIssueCommentsDto;
import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.IssueComments.entity.IssueCommentsEntity;
import kr.ac.kumoh.ce.s20180260.crudtest.login.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class IssueCommentsService {
    @Autowired
    private IssueCommentsRepository repository;
    @Autowired
    private JwtUtil jwtUtil;

    // 모든 Issue Comment 조회 (테스트용)
    public ResponseEntity<List<ResGetIssueCommentsDto>> getAllIssuesComments() {
        List<ResGetIssueCommentsDto> issueComments = repository.findAll()
                .stream()
                .map(ResGetIssueCommentsDto::new)
                .toList();
        return ResponseEntity.ok(issueComments);
    }

    // 특정 Issue의 코멘트 조회
    public ResponseEntity<List<ResGetIssueCommentsDto>> getAllIssueCommentsByIssueId(Long iid) {
        List<ResGetIssueCommentsDto> issueComments = repository.findIssueCommentsByIssueId(iid)
                .stream()
                .map(ResGetIssueCommentsDto::new)
                .toList();
        return ResponseEntity.ok(issueComments);
    }

    // 특정 이슈 코멘트의 정보 조회
    public ResponseEntity<ResGetIssueCommentsDto> getIssueCommentById(Long commentId) {
        IssueCommentsEntity issueCommentsEntity = repository.findById(commentId)
                        .orElseThrow(EntityNotFoundException::new);
        return ResponseEntity.ok(new ResGetIssueCommentsDto(issueCommentsEntity));
    }

    // 이슈 코멘트 생성
    // 예외처리 임시적용
    @Transactional
    public ResponseEntity<ResGetIssueCommentsDto> postIssueComment(ReqAddIssueCommentsDto request, String token) {
        // token 으로부터 userid 추출하여 dto 에 set
        request.setUserId(jwtUtil.extractUserid(token));
        try {
            IssueCommentsEntity entity = repository.save(request.toEntity());
            return ResponseEntity.ok(new ResGetIssueCommentsDto(entity));
        } catch (EntityExistsException e) {
            // 예외 처리: 이미 존재하는 엔티티
            return ResponseEntity.status(409).build();
        } catch (Exception e) {
            // 예외 처리: 서버 에러 발생 시
            return ResponseEntity.status(500).build();
        }
    }

    // 이슈 코멘트 수정
    @Transactional
    public ResponseEntity<ResGetIssueCommentsDto> updateIssueComment(ReqUpdateIssueCommentsDto request) {
        if(repository.existsById(request.getCommentId())){
            IssueCommentsEntity entity = repository.findById(request.getCommentId()).orElseThrow(EntityExistsException::new);
            entity.updateComment(request);
            return ResponseEntity.ok(new ResGetIssueCommentsDto(entity));
        }
        return ResponseEntity.notFound().build();
    }

    // 이슈 코멘트 삭제
    @Transactional
    public ResponseEntity<Void> deleteIssueComment(Long id) {
        if(repository.existsById(id)){
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
