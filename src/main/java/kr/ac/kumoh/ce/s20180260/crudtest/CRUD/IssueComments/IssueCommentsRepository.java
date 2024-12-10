package kr.ac.kumoh.ce.s20180260.crudtest.CRUD.IssueComments;

import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.IssueComments.entity.IssueCommentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueCommentsRepository extends JpaRepository<IssueCommentsEntity, Long> {
    public List<IssueCommentsEntity> findIssueCommentsByIssueId(Long issueId);
}
