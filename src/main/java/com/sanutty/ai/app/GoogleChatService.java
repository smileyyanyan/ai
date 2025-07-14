package com.sanutty.ai.app;

import static com.sanutty.ai.app.ChatCacheConfig.CHAT_QUESTION_CACHE_NAME;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class GoogleChatService implements ChatService
{
  private ExecutorService executorService = null;
  
  Logger log = LoggerFactory.getLogger(GoogleChatService.class);
  
  public GoogleChatService() {
    executorService = Executors.newCachedThreadPool();
  }

  @Override
  @Cacheable(value = CHAT_QUESTION_CACHE_NAME, key = "#question.question")
  public AskQuestion chat( AskQuestion question )
  {
    GoogleChatRunner runner = new GoogleChatRunner(question);
    List<GoogleChatRunner> list = new ArrayList<>();
    list.add( runner );
    
    try {
      List<Future<AskQuestion>> futures = executorService.invokeAll( list );
      Future<AskQuestion> future = futures.get( 0 );
      return future.get();
    } catch (Exception e) {
      question.setAnswer( "Not found" );
    }
    return question;
  }
    
    
}
