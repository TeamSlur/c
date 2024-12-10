package kr.ac.kumoh.ce.s20180260.crudtest.Chatting.Chats.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "chats")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_id", nullable = false)
    private Long chatId;

    @Column(name = "project_id", nullable = false)
    private Long projectId;

    @Column(name = "sender_id", nullable = false)
    private Long sender;

    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "sent_at")
    private LocalDateTime sentAt;

    @Column(name = "is_read")
    private Boolean isRead;

    @Column(name = "parent_message_id")
    private Long parentMessageId;

    @PrePersist
    private void prePersist(){
        if (this.sentAt == null) this.sentAt = LocalDateTime.now();
        if (this.isRead == null) this.isRead = false;
    }

    /*
    public MessageEntityBuilder toBuilder(){
        return MessageEntity.builder()
                .id(this.getChatId())
                .roomId(this.getProjectId())
                .sender(this.getSender())
                .type(this.getType())
                .content(this.getMessage());
    }*/
}