package kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Projects;

import jakarta.persistence.EntityExistsException;
import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Issues.IssuesService;
import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Issues.dto.ResGetIssueDto;
import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.ProjectUsers.ProjectUsersService;
import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.ProjectUsers.dto.ReqAddProjectUserDto;
import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.ProjectUsers.entity.ProjectUsersEntity;
import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Projects.dto.ReqCreateProjectDto;
import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Projects.dto.ReqUpdateProjectDto;
import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Projects.dto.ResGetProjectDto;
import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Projects.dto.ResProjectDto;
import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Projects.entity.ProjectEntity;
import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Role.ProjectUsersRoleEnum;
import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Tasks.TaskService;
import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Tasks.dto.ResGetTaskDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository repository;

    @Autowired
    private ProjectUsersService projectUsersService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private IssuesService issuesService;

    // 사용자의 프로젝트 목록 반환
    public ResponseEntity<List<ResProjectDto>> getMyProjects(Long uid) {
        List<ResProjectDto> projectList= repository.findProjectEntitiesByUid(uid)
                .stream()
                .map(ResProjectDto::new)
                .toList();
        return ResponseEntity.ok(projectList);
    }

    // 특정 프로젝트 조회
    public ResponseEntity<ResGetProjectDto> getProjectById(Long pid) {
        ResGetProjectDto resGetProjectDto = new  ResGetProjectDto();

        ProjectEntity projectEntity = repository.findById(pid).orElseThrow(
                () -> new EntityExistsException("project not found"));

        List<ResGetTaskDto> taskList = taskService.getAllTasksByProjectId(pid).getBody();
        List<ResGetIssueDto> issueList = issuesService.getAllIssuesByTaskId(pid).getBody();

        resGetProjectDto.setProjectDto(new ResProjectDto(projectEntity));
        resGetProjectDto.setTaskList(taskList);
        resGetProjectDto.setIssueList(issueList);

        return ResponseEntity.ok(resGetProjectDto);
    }

    // 프로젝트 생성
    @Transactional
    public ResponseEntity<ResProjectDto> addProject(ReqCreateProjectDto requestDto) {
        // project 데이터 저장
        ProjectEntity projectEntity = repository.save(requestDto.toEntity());

        // member 데이터 저장
        ReqAddProjectUserDto addUserDTo = new ReqAddProjectUserDto();
        addUserDTo.setUid(requestDto.getCreatedBy());
        addUserDTo.setPid(projectEntity.getProjectId());
        addUserDTo.setRole(ProjectUsersRoleEnum.OWNER);

        ProjectUsersEntity projectUsersEntity = projectUsersService.addMember(addUserDTo);

        return ResponseEntity.ok(new ResProjectDto(projectEntity));
    }

    // 프로젝트 갱신
    @Transactional
    public ResponseEntity<ResProjectDto> updateProject(Long projectId, ReqUpdateProjectDto requestDto) {
        if(repository.existsById(projectId)) {
            ProjectEntity entity = repository.findById(projectId).orElseThrow(EntityExistsException::new);
            entity.updateProject(requestDto);
            return ResponseEntity.ok(new ResProjectDto(entity));
        }
        return ResponseEntity.notFound().build();
    }

    // 프로젝트 삭제
    @Transactional
    public ResponseEntity<Void> deleteProject(Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
