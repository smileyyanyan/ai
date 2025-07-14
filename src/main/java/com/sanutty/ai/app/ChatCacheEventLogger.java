package com.sanutty.ai.app;

import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChatCacheEventLogger implements CacheEventListener<Object, Object> {
  
  Logger log = LoggerFactory.getLogger(ChatCacheEventLogger.class);

  @Override
  public void onEvent(
    CacheEvent<? extends Object, ? extends Object> cacheEvent) {
      log.info("ChatCacheEventLogger.onEvent");
  }
}



