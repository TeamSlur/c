package kr.ac.kumoh.ce.s20180260.crudtest.login.controller;

import kr.ac.kumoh.ce.s20180260.crudtest.login.dto.SignUpDTO;
import kr.ac.kumoh.ce.s20180260.crudtest.login.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // 회원가입 API
    @PostMapping("/login/signup")
    public ResponseEntity<String> signUp(@RequestBody SignUpDTO signUpDTO) {
        try {
            userService.registerUser(signUpDTO);
            return new ResponseEntity<>("회원가입 성공", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("회원가입 실패: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // 아이디 중복 확인 API
    @GetMapping("/login/signup/duple/id")
    public ResponseEntity<String> checkUsername(@RequestParam String username) {
        boolean isDuplicate = userService.isUsernameDuplicate(username);
        return isDuplicate
                ? new ResponseEntity<>("중복된 사용자명입니다.", HttpStatus.BAD_REQUEST)
                : new ResponseEntity<>("사용 가능한 사용자명입니다.", HttpStatus.OK);
    }

    // 이메일 중복 확인 API
    @GetMapping("/login/signup/duple/email")
    public ResponseEntity<String> checkEmail(@RequestParam String email) {
        boolean isDuplicate = userService.isEmailDuplicate(email);
        return isDuplicate
                ? new ResponseEntity<>("중복된 이메일입니다.", HttpStatus.BAD_REQUEST)
                : new ResponseEntity<>("사용 가능한 이메일입니다.", HttpStatus.OK);
    }

    // 아이디 찾기 API
    @PostMapping("/login/search/id")
    public ResponseEntity<String> findUsernameByEmail(@RequestParam String email) {
        try {
            String username = userService.findUsernameByEmail(email);
            return new ResponseEntity<>("아이디: " + username, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("아이디 찾기 실패: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // 비밀번호 찾기 API (DB에서 비밀번호 반환)
    @PostMapping("/login/search/password")
    public ResponseEntity<String> findPassword(@RequestParam String email) {
        try {
            String password = userService.findPasswordByEmail(email);
            return new ResponseEntity<>("비밀번호: " + password, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("비밀번호 찾기 실패: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}