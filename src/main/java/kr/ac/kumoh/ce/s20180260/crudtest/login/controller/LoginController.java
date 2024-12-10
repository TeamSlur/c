package kr.ac.kumoh.ce.s20180260.crudtest.login.controller;

import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.ProjectUsers.ProjectUsersService;
import kr.ac.kumoh.ce.s20180260.crudtest.login.dto.LoginDTO;
import kr.ac.kumoh.ce.s20180260.crudtest.login.service.UserService;
import kr.ac.kumoh.ce.s20180260.crudtest.login.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final ProjectUsersService projectUsersService;

    public LoginController(UserService userService, JwtUtil jwtUtil, ProjectUsersService projectUsersService) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.projectUsersService = projectUsersService;
    }

    @PostMapping
    public ResponseEntity<?> loginUser(@RequestBody LoginDTO loginDTO) {
        String username = loginDTO.getUsername();
        String password = loginDTO.getPassword();

        boolean isAuthenticated = userService.authenticateUser(username, password);

        if (isAuthenticated) {
            Long userId = userService.getUserIdByUsername(username);
            String token = jwtUtil.generateToken(username);

            // 사용자의 프로젝트 ID 목록 조회
            List<Long> projectIds = projectUsersService.getProjectIdsForUser(userId);

            return ResponseEntity.ok()
                    .header("Authorization", "Bearer " + token)
                    .body(projectIds); // 프로젝트 ID 목록 반환
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패");
        }
    }
}
