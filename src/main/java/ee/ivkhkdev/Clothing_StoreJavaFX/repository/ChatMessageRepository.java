package ee.ivkhkdev.Clothing_StoreJavaFX.repository;


import ee.ivkhkdev.Clothing_StoreJavaFX.model.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    List<ChatMessage> findTop100ByOrderByTimestampAsc();
}
