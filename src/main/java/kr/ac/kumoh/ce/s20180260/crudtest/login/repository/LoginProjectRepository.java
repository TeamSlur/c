/*
package kr.ac.kumoh.ce.s20180260.crudtest.login.repository;

import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Projects.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoginProjectRepository extends JpaRepository<ProjectEntity, Long> {
    @Query("SELECT pu.projectId FROM ProjectUsersEntity pu WHERE pu.userId = :userId")
    List<Long> findProjectIdsByUserId(@Param("userId") Long userId);
}*/
