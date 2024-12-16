package kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Codes;

import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Codes.dto.ReqAddCodeDto;
import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Codes.dto.ReqUpdateCodeDto;
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
    @GetMapping("/all")
    public ResponseEntity<List<ResGetCodeDto>> getAllCodesByProjectId(
            @RequestParam Long pid
    ){
        return service.getAllCodesByProjectId(pid);
    }

    @GetMapping("/details")
    public ResponseEntity<ResGetCodeDto> getCode(
            @RequestParam Long pid,
            @RequestParam Long cid
    ){
        return service.getCode(cid);
    }

    @PostMapping("/add")
    public ResponseEntity<ResGetCodeDto> postCode(
            @RequestParam Long pid,
            @RequestBody ReqAddCodeDto reqAddCodeDto,
            @RequestHeader("Authorization") String token
    ){
        return service.postCode(reqAddCodeDto,token);
    }

    @PutMapping("/edit")
    public ResponseEntity<ResGetCodeDto> editCode(
            @RequestParam Long pid,
            @RequestParam Long cid,
            @RequestBody ReqUpdateCodeDto reqUpdateCodeDto
            //@RequestHeader("Authorization") String token
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
