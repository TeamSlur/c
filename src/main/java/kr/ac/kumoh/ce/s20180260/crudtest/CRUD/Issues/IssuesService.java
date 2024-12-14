package kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Issues;

import jakarta.persistence.EntityNotFoundException;
import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Issues.dto.*;
import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Issues.entity.IssuesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class IssuesService {
    private final String issueNotFound = "Issue Not Found";

    @Autowired
    private IssuesRepository issueRepository;

    // 모든 이슈를 조회하는 api (테스트용)
    public ResponseEntity<List<ResGetIssueDto>> getAllIssues() {
        List<ResGetIssueDto> issueList = issueRepository.findAll()
                .stream()
                .map(ResGetIssueDto::new)
                .toList();
        return ResponseEntity.ok(issueList);
    }

    // issue id에 대한 정보를 요청하는 API ( 복수 )
    public ResponseEntity<List<ResGetIssueDto>> getAllIssuesByTaskId(Long tid) {
        List<ResGetIssueDto> issueList = issueRepository.findIssueEntitiesByProjectId(tid)
                .stream()
                .map(ResGetIssueDto::new)
                .toList();
        return ResponseEntity.ok(issueList);
    }

    // issue 내용에 대한 정보를 요청하기 위한  API ( 단수 )
    public ResponseEntity<ResAddIssueDto> getIssueDetailByIssueId(Long isId) {
        try {
            // Repository 에서 이슈의 id를 통해 조회
            // 해당 entity 존재하지 않을 시, 예외처리(EntityNotFoundException)
            IssuesEntity issue = issueRepository.findById(isId)
                    .orElseThrow(() -> new EntityNotFoundException(issueNotFound));
            return ResponseEntity.ok(new ResAddIssueDto(issue));
        } catch(Exception e){
            // todo : 예외 처리 보완하기
            // 예외처리 좀 설정해두려고 하다가 지식이 없어서 걍 멈춤
            // 예외처리 핸들러 설정하는 등의 방법 등을 익히고서 예외처리하는 걸로..
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // issue 정보를 추가하기 위한 API
    @Transactional
    public ResponseEntity<ResAddIssueDto> addIssue(ReqAddResIssueDto request) {
        try {
            IssuesEntity entity = issueRepository.save(request.toEntity());
            return ResponseEntity.ok(new ResAddIssueDto(entity));
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // issue id에 대한 정보를 수정하는 API
    @Transactional
    public ResponseEntity<ResUpdateIssueDto> editIssue(Long issueId, ReqUpdateIssueDto request) {
        IssuesEntity entity = issueRepository
                .findById(issueId)
                .orElseThrow(EntityNotFoundException::new);
        entity.updateIssue(request);

        return ResponseEntity.ok(new ResUpdateIssueDto(entity));
    }

    // issue id에 대한 정보를 제거하는 API
    @Transactional
    public ResponseEntity<Void> deleteIssue(Long id) {
        if (issueRepository.existsById(id)){
            issueRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
