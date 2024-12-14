package kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Codes.dto;

import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.CodeComments.dto.ResGetCodeCommentsDto;
import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Codes.entity.CodesEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResGetCodeDto extends BaseResCodesDto{
    private List<ResGetCodeCommentsDto> dtoList;

    public ResGetCodeDto(CodesEntity entity, List<ResGetCodeCommentsDto> dto){
        super(entity);
        this.dtoList = dto;
    }
}
