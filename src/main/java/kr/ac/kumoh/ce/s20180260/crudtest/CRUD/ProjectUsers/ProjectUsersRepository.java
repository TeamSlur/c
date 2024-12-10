package kr.ac.kumoh.ce.s20180260.crudtest.CRUD.ProjectUsers;

import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.ProjectUsers.entity.ProjectUsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectUsersRepository extends JpaRepository<ProjectUsersEntity, Long> {
    // 사용자 ID로 프로젝트 ID 목록을 조회
    List<Long> findProjectIdsByUserId(Long userId);
}
