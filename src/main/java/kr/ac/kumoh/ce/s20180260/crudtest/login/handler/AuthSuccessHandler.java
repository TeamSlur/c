package kr.ac.kumoh.ce.s20180260.crudtest.login.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

public class AuthSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response, Authentication authentication) throws IOException, jakarta.servlet.ServletException {
        // 로그인 성공 시
        System.out.println("로그인 성공");
        response.sendRedirect("/users/main"); // 여기를 수정하여 원하는 페이지로 리다이렉트합니다.
    }
}
