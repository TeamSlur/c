package kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Document;

import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Document.dto.ReqAddDocumentDto;
import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Document.dto.ReqUpdateDocumentDto;
import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Document.dto.ResGetDocumentDto;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/document")
public class DocumentController {
    @Autowired
    private DocumentService service;

    //
    @GetMapping
    public ResponseEntity<List<ResGetDocumentDto>> getDocumentsByProjectId(
            @RequestParam Long pid
    ){
        return service.getDocumentsByProjectId(pid);
    }

    @PostMapping("/add")
    public ResponseEntity<ResGetDocumentDto> postDocument(
        @RequestParam Long pid,
        @RequestBody ReqAddDocumentDto reqAddDocumentDto
    ){
        return service.postDocument(reqAddDocumentDto);
    }

    //
    @PutMapping("/edit")
    public ResponseEntity<ResGetDocumentDto> editDocument(
            @RequestParam Long pid,
            @RequestBody ReqUpdateDocumentDto dto
    ){
        return service.updateDocument(dto);
    }

    // document 제거
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteDocument(
            @RequestParam Long pid,
            @RequestParam Long did
    ){
        return service.deleteDocument(did);
    }
}
