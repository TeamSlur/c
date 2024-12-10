package kr.ac.kumoh.ce.s20180260.crudtest.login.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class PasswordEncoderConfig {
    @Bean(name = "customPasswordEncoder") // 이름 변경
    public PasswordEncoder passwordEncoder() {
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("bcrypt", new BCryptPasswordEncoder());
        // 다른 인코더를 추가할 수 있습니다.

        // "bcrypt"를 기본 인코더로 설정
        return new DelegatingPasswordEncoder("bcrypt", encoders);
    }
}