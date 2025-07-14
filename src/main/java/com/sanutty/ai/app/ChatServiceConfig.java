package com.sanutty.ai.app;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;


public class ChatServiceConfig {
  
  @Bean
  @ConditionalOnProperty(name = "chat.model", havingValue = "google")
  public ChatService getGooleChatService() {
      return new GoogleChatService();
  }

}
