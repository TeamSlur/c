package kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Codes;

import jakarta.persistence.EntityNotFoundException;
import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.CodeComments.CodeCommentsService;
import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.CodeComments.dto.ResGetCodeCommentsDto;
import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Codes.dto.ReqAddCodeDto;
import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Codes.dto.ReqUpdateCodeDto;
import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Codes.dto.ResCodeDto;
import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Codes.dto.ResGetCodeDto;
import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Codes.entity.CodesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CodesService {
    @Autowired
    private CodesRepository codesRepository;
    @Autowired
    private CodeCommentsService commentsService;

    public ResponseEntity<List<ResCodeDto>> getAllCodesByProjectId(Long pid) {
        List<ResCodeDto> codesList = codesRepository.findCodesEntitiesByProjectId(pid)
                .stream()
                .map(ResCodeDto::new)
                .toList();
        return ResponseEntity.ok(codesList);
    }

    public ResponseEntity<ResGetCodeDto> getCodeDetailByCodeId(Long cid) {
        CodesEntity entity = codesRepository.findById(cid).orElseThrow(EntityNotFoundException::new);
        List<ResGetCodeCommentsDto> dtoList = commentsService.getAllCodeCommentsByCodeId(cid).getBody();

        ResGetCodeDto dto = new ResGetCodeDto(entity,dtoList);

        return ResponseEntity.ok(dto);
    }

    @Transactional
    public ResponseEntity<ResCodeDto> postCode(ReqAddCodeDto reqAddCodeDto){
        CodesEntity entity = codesRepository.save(reqAddCodeDto.toEntity());
        return ResponseEntity.ok(new ResCodeDto(entity));
    }

    @Transactional
    public ResponseEntity<ResCodeDto> editCode(ReqUpdateCodeDto dto){
        CodesEntity entity = codesRepository.findById(dto.getCodeId())
                .orElseThrow(EntityNotFoundException::new);
        entity.updateCode(dto);
        return ResponseEntity.ok(new ResCodeDto(entity));
    }

    @Transactional
    public ResponseEntity<Void> deleteCode(Long cid){
        if (codesRepository.existsById(cid)){
            codesRepository.deleteById(cid);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
