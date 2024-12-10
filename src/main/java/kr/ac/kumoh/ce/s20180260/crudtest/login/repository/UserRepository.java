package kr.ac.kumoh.ce.s20180260.crudtest.login.repository;

import kr.ac.kumoh.ce.s20180260.crudtest.login.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);  // 이메일 중복 확인

    boolean existsByUsername(String username);  // 사용자명 중복 확인

    // 사용자명으로 User를 찾아 반환 (Optional로 반환)
    Optional<User> findByUsername(String username);

    // 역할로 사용자 찾기 (옵션)
    User findByRole(User.Role role);

    // 이메일로 User를 찾기
    Optional<User> findByEmail(String email);
}