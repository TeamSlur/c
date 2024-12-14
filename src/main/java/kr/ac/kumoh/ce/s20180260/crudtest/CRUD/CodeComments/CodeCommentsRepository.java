package kr.ac.kumoh.ce.s20180260.crudtest.CRUD.CodeComments;

import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.CodeComments.entity.CodeCommentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CodeCommentsRepository extends JpaRepository<CodeCommentsEntity, Long> {
    public List<CodeCommentsEntity> findCodeCommentsByCodeId(Long codeId);
}
