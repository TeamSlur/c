package kr.ac.kumoh.ce.s20180260.crudtest.CRUD.ProjectUsers;

import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.ProjectUsers.dto.ReqAddProjectUserDto;
import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.ProjectUsers.entity.ProjectUsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProjectUsersService {
    @Autowired
    private ProjectUsersRepository memberRepository;

    @Transactional
    public ProjectUsersEntity addMember(ReqAddProjectUserDto requestDto) {
        return memberRepository.save(requestDto.toEntity());
    }

    // 사용자 ID로 프로젝트 ID 목록 조회
    public List<Long> getProjectIdsForUser(Long userId) {
        return memberRepository.findProjectIdsByUserId(userId);  // 쿼리 메서드 호출
    }
}
