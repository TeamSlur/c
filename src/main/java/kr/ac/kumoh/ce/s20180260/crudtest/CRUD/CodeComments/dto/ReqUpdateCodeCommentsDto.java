package kr.ac.kumoh.ce.s20180260.crudtest.CRUD.CodeComments.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReqUpdateCodeCommentsDto {
    private Long commentId;
    private Long userId;
    private String content;
}
