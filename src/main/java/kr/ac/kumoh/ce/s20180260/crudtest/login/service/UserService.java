package kr.ac.kumoh.ce.s20180260.crudtest.login.service;

import jakarta.persistence.EntityNotFoundException;
import kr.ac.kumoh.ce.s20180260.crudtest.login.domain.User;
import kr.ac.kumoh.ce.s20180260.crudtest.login.dto.SignUpDTO;
import kr.ac.kumoh.ce.s20180260.crudtest.login.repository.UserRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Lazy
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // 이메일 중복 확인
    public boolean isEmailDuplicate(String email) {
        return userRepository.existsByEmail(email);
    }

    // 사용자명 중복 확인
    public boolean isUsernameDuplicate(String username) {
        return userRepository.existsByUsername(username);
    }

    // 사용자명으로 사용자 ID 조회
    public Long getUserIdByUsername(String username) {
        // 사용자명으로 사용자를 조회 (Optional 반환)
        Optional<User> user = userRepository.findByUsername(username);

        // Optional 값이 존재하면 사용자 ID 반환
        return user.map(User::getUserId)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));  // 사용자 없을 시 예외 처리
    }

    // 회원가입 메서드
    public void registerUser(SignUpDTO signUpDTO) {
        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(signUpDTO.getPassword());

        // Role Enum으로 변환
        User.Role role = User.Role.valueOf(signUpDTO.getRole());

        // User 객체 생성
        User newUser = new User();
        newUser.setUsername(signUpDTO.getUsername());
        newUser.setPassword(encodedPassword);
        newUser.setEmail(signUpDTO.getEmail());
        newUser.setNickname(signUpDTO.getNickname());
        newUser.setRole(role);

        // 사용자 저장
        userRepository.save(newUser);
    }

    // 로그인 인증
    public boolean authenticateUser(String username, String password) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // 입력된 비밀번호와 DB에 저장된 비밀번호 비교
            return passwordEncoder.matches(password, user.getPassword());
        }
        return false;  // 사용자 없으면 로그인 실패
    }

    // 이메일로 사용자명 찾기
    public String findUsernameByEmail(String email) throws Exception {
        return userRepository.findByEmail(email)
                .map(user -> user.getUsername())  // 이메일에 해당하는 사용자명을 반환
                .orElseThrow(() -> new Exception("이메일에 해당하는 사용자가 없습니다."));
    }

    // 이메일로 비밀번호 찾기 (실제 비밀번호 반환)
    public String findPasswordByEmail(String email) throws Exception {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // 실제 비밀번호를 반환
            return user.getPassword();
        } else {
            throw new Exception("이메일에 해당하는 사용자가 없습니다.");
        }
    }

    public String getUserNameByUserId(Long senderId) {
        User entity = userRepository.findById(senderId).orElseThrow(EntityNotFoundException::new);
        return entity.getUsername();
    }
}
