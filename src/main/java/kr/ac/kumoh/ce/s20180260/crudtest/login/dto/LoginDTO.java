package kr.ac.kumoh.ce.s20180260.crudtest.login.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {
    private String username;  // 사용자명
    private String password;  // 비밀번호
}