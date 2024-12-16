package kr.ac.kumoh.ce.s20180260.crudtest.CRUD.CodeComments;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.CodeComments.dto.ReqAddCodeCommentsDto;
import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.CodeComments.dto.ReqUpdateCodeCommentsDto;
import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.CodeComments.dto.ResGetCodeCommentsDto;
import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.CodeComments.entity.CodeCommentsEntity;
import kr.ac.kumoh.ce.s20180260.crudtest.login.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CodeCommentsService {
    @Autowired
    private CodeCommentsRepository repository;
    @Autowired
    private JwtUtil jwtUtil;

    // 모든 Issue Comment 조회 (테스트용)
    public ResponseEntity<List<ResGetCodeCommentsDto>> getAllIssuesComments() {
        List<ResGetCodeCommentsDto> issueComments = repository.findAll()
                .stream()
                .map(ResGetCodeCommentsDto::new)
                .toList();
        return ResponseEntity.ok(issueComments);
    }

    // 특정 Issue의 코멘트 조회
    public ResponseEntity<List<ResGetCodeCommentsDto>> getAllIssueCommentsByIssueId(Long iid) {
        List<ResGetCodeCommentsDto> issueComments = repository.findCodeCommentsByCodeId(iid)
                .stream()
                .map(ResGetCodeCommentsDto::new)
                .toList();
        return ResponseEntity.ok(issueComments);
    }

    // 특정 이슈 코멘트의 정보 조회
    public ResponseEntity<ResGetCodeCommentsDto> getIssueCommentById(Long commentId) {
        CodeCommentsEntity issueCommentsEntity = repository.findById(commentId)
                        .orElseThrow(EntityNotFoundException::new);
        return ResponseEntity.ok(new ResGetCodeCommentsDto(issueCommentsEntity));
    }

    // 이슈 코멘트 생성
    // 예외처리 임시적용
    @Transactional
    public ResponseEntity<ResGetCodeCommentsDto> postCodeComment(ReqAddCodeCommentsDto request, String token) {
        request.setUserId(jwtUtil.extractUserid(token));
        try {
            CodeCommentsEntity entity = repository.save(request.toEntity());
            return ResponseEntity.ok(new ResGetCodeCommentsDto(entity));
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
    public ResponseEntity<ResGetCodeCommentsDto> updateCodeComment(ReqUpdateCodeCommentsDto request) {
        if(repository.existsById(request.getCommentId())){
            CodeCommentsEntity entity = repository.findById(request.getCommentId()).orElseThrow(EntityExistsException::new);
            entity.updateComment(request);
            return ResponseEntity.ok(new ResGetCodeCommentsDto(entity));
        }
        return ResponseEntity.notFound().build();
    }

    // 이슈 코멘트 삭제
    @Transactional
    public ResponseEntity<Void> deleteCodeComment(Long id) {
        if(repository.existsById(id)){
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
