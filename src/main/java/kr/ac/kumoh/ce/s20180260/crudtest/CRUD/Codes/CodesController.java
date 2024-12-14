package kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Codes;

import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Codes.dto.ReqAddCodeDto;
import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Codes.dto.ReqUpdateCodeDto;
import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Codes.dto.ResCodeDto;
import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Codes.dto.ResGetCodeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/code")
public class CodesController {
    @Autowired
    private CodesService service;

    // project의 코드 조회
    @GetMapping
    public ResponseEntity<List<ResCodeDto>> getAllCodesByProjectId(
            @RequestParam Long pid
    ){
        return service.getAllCodesByProjectId(pid);
    }

    // code 상세 정보 조회
    @GetMapping("/details")
    public ResponseEntity<ResGetCodeDto> getCodeDetailByCodeId(
            @RequestParam Long pid,
            @RequestParam Long cid
    ){
        return service.getCodeDetailByCodeId(cid);
    }

    @PostMapping("/add")
    public ResponseEntity<ResCodeDto> postCode(
            @RequestParam Long pid,
            @RequestBody ReqAddCodeDto reqAddCodeDto
    ){
        return service.postCode(reqAddCodeDto);
    }

    @PutMapping("/edit")
    public ResponseEntity<ResCodeDto> editCode(
            @RequestParam Long pid,
            @RequestParam Long cid,
            @RequestBody ReqUpdateCodeDto reqUpdateCodeDto
    ){
        return service.editCode(reqUpdateCodeDto);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteCode(
            @RequestParam Long pid,
            @RequestParam Long cid
    ){
        return service.deleteCode(cid);
    }
}
