package kr.ac.kumoh.ce.s20180260.crudtest.login.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long userId;  // 사용자 고유 ID

    @Column(name ="username", nullable = false, unique = true)
    private String username;  // 사용자명 (로그인용)

    @Column(name = "password", nullable = false)
    private String password;  // 암호화된 비밀번호

    @Column(name = "nickname", nullable = false)
    private String nickname;  // 사용자 닉네임

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;  // 사용자 역할 (DEVELOPER, PM, QM, BUSINESS)

    @Column(name = "email", nullable = false, unique = true) // 이메일 필드 추가
    private String email;  // 이메일 (고유)

    @Column(name = "createdAt", nullable = false, updatable = false)
    private LocalDateTime createdAt;  // 계정 생성 시간

    @Column(name = "updatedAt", nullable = false)
    private LocalDateTime updatedAt;  // 최근 정보 수정 시간

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public enum Role {
        DEVELOPER,
        PM,
        QM,
        BUSINESS
    }
}