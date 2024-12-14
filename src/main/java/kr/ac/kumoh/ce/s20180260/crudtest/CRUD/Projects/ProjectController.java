package kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Projects;

import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Projects.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

/*
    // 사용자의 프로젝트 목록 조회
    @GetMapping("/project/my")
    public ResponseEntity<List<ResProjectDto>> getMyProjects(
            @RequestParam Long uid
    ) {
        return projectService.getMyProjects(uid);
    }
*/

    // 프로젝트 정보 조회
    @GetMapping
    public ResponseEntity<ResGetProjectDto> getProjectById(
            @RequestParam Long pid
    ) {
        return projectService.getProjectById(pid);
    }

    // 프로젝트 생성
    @PostMapping("/add")
    public ResponseEntity<ResProjectDto> addProject(
            @RequestBody ReqCreateProjectDto request
    ) {
        return projectService.addProject(request);
    }

    //프로젝트 수정
    @PutMapping("/edit")
    public ResponseEntity<ResProjectDto> updateProject(
            @RequestParam Long pid,
            @RequestBody ReqUpdateProjectDto request
    ) {
        return projectService.updateProject(pid, request);
    }

    //프로젝트 삭제
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteProject(
            @RequestParam Long pid
    ) {
        return projectService.deleteProject(pid);
    }
}
