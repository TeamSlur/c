package kr.ac.kumoh.ce.s20180260.crudtest.login.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000") // 허용할 도메인
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // 허용할 HTTP 메소드
                        .allowedHeaders("*") // 모든 헤더 허용
                        .exposedHeaders("Authorization")
                        .allowCredentials(true); // 자격 증명 허용
            }
        };
    }
}