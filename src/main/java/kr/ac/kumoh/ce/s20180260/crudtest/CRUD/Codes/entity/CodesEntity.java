package kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Codes.entity;

import jakarta.persistence.*;
import kr.ac.kumoh.ce.s20180260.crudtest.CRUD.Codes.dto.ReqUpdateCodeDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "codes")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CodesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code_id")
    private Long codeId;

    @Column(name = "code_issue_id")
    private Long codeIssueId;

    @Column(name = "code_types")
    private String codeTypes;

    @Column(name = "code_name")
    private String codeName;

    @Column(name = "code_content")
    private String codeContent;

    @Column(name = "code_image")
    private String codeImage;

    @Column(name = "code_description")
    private String codeDescription;

    @Column(name = "createdBy")
    private String createdBy;

    @Column(name = "createDate")
    private LocalDateTime createDate;

    @Column(name = "updateDate")
    private LocalDateTime updateDate;

    @PrePersist
    private void prePersist(){
        if (this.createDate == null) this.createDate = LocalDateTime.now();
        if (this.updateDate == null) this.updateDate = LocalDateTime.now();
    }

    @PreUpdate
    private void preUpdate(){
        this.updateDate = LocalDateTime.now();
    }

    public void updateCode(ReqUpdateCodeDto dto){
        this.codeId = dto.getCodeId();
        this.codeIssueId = dto.getCodeIssueId();
        this.codeTypes = dto.getCodeTypes();
        this.codeName = dto.getCodeName();
        this.codeContent = dto.getCodeContent();
        this.codeImage = dto.getCodeImage();
        this.codeDescription = dto.getCodeDescription();
    }
}
