package com.sanutty.ai.app;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@EnableCaching
public class ChatCacheConfig {

  public static final String CHAT_QUESTION_CACHE_NAME = "chatQuestions";
  
  @Bean
  @Primary
  public CacheManager getCacheManager() {
      return new ConcurrentMapCacheManager(CHAT_QUESTION_CACHE_NAME);
  }
  
}
