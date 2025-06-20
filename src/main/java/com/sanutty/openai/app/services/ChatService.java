package com.sanutty.openai.app.services;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
  
    private final ChatClient chatClient;
    
    public ChatService(ChatClient.Builder builder) {
      this.chatClient = builder.build();
    }
    
    public String chat(String question) {
      return chatClient.prompt(question).call().content();
    }
    
}
