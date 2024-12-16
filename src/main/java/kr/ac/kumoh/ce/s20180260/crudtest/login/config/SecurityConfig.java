package kr.ac.kumoh.ce.s20180260.crudtest.login.config;

import kr.ac.kumoh.ce.s20180260.crudtest.login.service.JWTService;
import kr.ac.kumoh.ce.s20180260.crudtest.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    @Lazy
    private UserService userService;
    private final JWTService jwtService;  // JWTService 주입

    public SecurityConfig(@Lazy UserService userService, JWTService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;  // JWTService 주입
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // AuthenticationManager 빈 등록
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // SecurityFilterChain 설정
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/api").authenticated()
                                .requestMatchers("/login").permitAll()  // 로그인은 모두 허용
                                /*.requestMatchers("/document").authenticated()  // 개발 관련 페이지는 인증 필요
                                .requestMatchers("/issue").authenticated()  // 개발 관련 페이지는 인증 필요
                                .requestMatchers("/timeline").authenticated()  // 개발 관련 페이지는 인증 필요
                                .requestMatchers("/codeissue").authenticated()  // 개발 관련 페이지는 인증 필요
                                .requestMatchers("/codeissue/detail").authenticated()  // 개발 관련 페이지는 인증 필요
                                .requestMatchers("/codeissue/add").authenticated()  // 개발 관련 페이지는 인증 필요
                                */
                                .anyRequest().permitAll()  // 그 외는 모두 허용
                )
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 세션 비사용

                // CSRF 보호 비활성화
                .csrf(AbstractHttpConfigurer::disable)  // Spring Security 6 이상에서는 이렇게 설정

                // JWT 토큰 필터 추가
                .addFilterBefore(new JwtAuthenticationFilter(jwtService), UsernamePasswordAuthenticationFilter.class);  // JwtService 인스턴스를 필터에 주입

        return http.build();
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000")); // 허용할 도메인 설정
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true);
        //configuration.addExposedHeader("Authorization");
        source.registerCorsConfiguration("/**", configuration);

        return new CorsFilter(source);
    }
}