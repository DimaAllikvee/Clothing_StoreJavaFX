package ee.ivkhkdev.Clothing_StoreJavaFX.service;


import ee.ivkhkdev.Clothing_StoreJavaFX.model.ChatMessage;
import ee.ivkhkdev.Clothing_StoreJavaFX.repository.ChatMessageRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChatMessageService {
    private final ChatMessageRepository repo;

    public ChatMessageService(ChatMessageRepository repo) {
        this.repo = repo;
    }

    public void save(String sender, String content) {
        repo.save(new ChatMessage(sender, content, LocalDateTime.now()));
    }

    public List<ChatMessage> getHistory() {
        return repo.findTop100ByOrderByTimestampAsc();
    }
}

