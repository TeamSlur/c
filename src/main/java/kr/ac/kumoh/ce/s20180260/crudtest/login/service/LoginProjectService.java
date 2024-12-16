/*
package kr.ac.kumoh.ce.s20180260.crudtest.login.service;

import kr.ac.kumoh.ce.s20180260.crudtest.login.repository.LoginProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginProjectService {

    @Autowired
    private LoginProjectRepository projectRepository;

    public List<Long> getProjectIdsForUser(Long userId) {
        // 사용자 ID로 프로젝트 ID 목록 조회
        return projectRepository.findProjectIdsByUserId(userId);
    }
}*/
