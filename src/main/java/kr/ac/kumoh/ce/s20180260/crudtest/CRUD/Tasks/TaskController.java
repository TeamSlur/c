package kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Tasks;

import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Issues.IssuesService;
import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Tasks.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private IssuesService issuesService;

    //project 의 task 모두 조회
    @GetMapping("/all")
    public ResponseEntity<List<ResGetTaskDto>> getAllTaskById(
            @RequestParam Long pid
    ){
        return taskService.getAllTasksByProjectId(pid);
    }

    //특정 task 조회
    @GetMapping("/details")
    public ResponseEntity<ResTaskDto> getTaskDetailByTaskId(
            @RequestParam Long pid,
            @RequestParam Long tid) {
        return taskService.getTaskById(tid);
    }

    //task 추가
    @PostMapping("/add")
    public ResponseEntity<ResTaskDto> addTask(
            @RequestParam Long pid,
            @RequestBody ReqAddTaskDto reqAddTaskDto) {
        return taskService.addTask(reqAddTaskDto);
    }

    //task 수정
    @PutMapping("/edit")
    public ResponseEntity<ResTaskDto> updateTask(
            @RequestParam Long pid,
            @RequestParam Long tid,
            @RequestBody ReqUpdateTaskDto reqUpdateTaskDto) {
        return taskService.updateTask(tid, reqUpdateTaskDto);
    }

    //task 삭제
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteTask(
            @RequestParam Long pid,
            @RequestParam Long tid) {
        return taskService.deleteTask(tid);
    }
}
