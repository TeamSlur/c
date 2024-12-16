package kr.ac.kumoh.ce.s20180260.crudtest.login.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class LoginResDTO {
    List<Long> projectIds;
}
