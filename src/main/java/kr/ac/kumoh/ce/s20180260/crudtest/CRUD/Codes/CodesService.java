package kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Codes;

import jakarta.persistence.EntityNotFoundException;
import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Codes.dto.ReqAddCodeDto;
import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Codes.dto.ReqUpdateCodeDto;
import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Codes.dto.ResGetCodeDto;
import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Codes.entity.CodesEntity;
import kr.ac.kumoh.ce.s20180260.crudtest.login.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CodesService {
    @Autowired
    private CodesRepository repository;
    @Autowired
    private JwtUtil jwtUtil;

    public ResponseEntity<List<ResGetCodeDto>> getAllCodesByProjectId(Long pid) {
        List<ResGetCodeDto> codesList = repository.findCodesEntitiesByProjectId(pid)
                .stream()
                .map(ResGetCodeDto::new)
                .toList();
        return ResponseEntity.ok(codesList);
    }

    public ResponseEntity<ResGetCodeDto> getCode(Long cid) {
        CodesEntity entity = repository.findById(cid).orElseThrow(EntityNotFoundException::new);
        return ResponseEntity.ok(new ResGetCodeDto(entity));
    }

    @Transactional
    public ResponseEntity<ResGetCodeDto> postCode(ReqAddCodeDto reqAddCodeDto, String token){
        reqAddCodeDto.setCreatedBy(jwtUtil.extractUserid(token));
        CodesEntity entity = repository.save(reqAddCodeDto.toEntity());
        return ResponseEntity.ok(new ResGetCodeDto(entity));
    }

    @Transactional
    public ResponseEntity<ResGetCodeDto> editCode(ReqUpdateCodeDto dto){
        CodesEntity entity = repository.findById(dto.getCodeId())
                .orElseThrow(EntityNotFoundException::new);
        //dto.setCreateBy(jwtUtil.extractUserid(token));
        entity.updateCode(dto);
        return ResponseEntity.ok(new ResGetCodeDto(entity));
    }

    @Transactional
    public ResponseEntity<Void> deleteCode(Long cid){
        if (repository.existsById(cid)){
            repository.deleteById(cid);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
