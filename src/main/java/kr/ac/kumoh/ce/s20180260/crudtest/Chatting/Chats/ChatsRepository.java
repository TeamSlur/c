package kr.ac.kumoh.ce.s20180260.crudtest.Chatting.Chats;

import kr.ac.kumoh.ce.s20180260.crudtest.Chatting.Chats.entity.ChatsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatsRepository extends JpaRepository<ChatsEntity, Long> {
    List<ChatsEntity> findChatsEntitiesByProjectId(Long projectId);
}