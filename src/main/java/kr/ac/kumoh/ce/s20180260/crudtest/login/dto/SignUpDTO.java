package kr.ac.kumoh.ce.s20180260.crudtest.login.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpDTO {
    private String username;  // 사용자 아이디
    private String password;  // 비밀번호
    private String email;     // 이메일
    private String nickname;  // 사용자 닉네임
    private String role;      // 역할 (DEVELOPER, PM, QM, BUSINESS)
}
