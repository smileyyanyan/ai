package com.sanutty.ai.app;

import java.util.concurrent.Callable;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;

public class GoogleChatRunner implements Callable<AskQuestion>
{
  private static final String LLM_MODEL = "gemini-2.5-flash";
  
  private Client client = new Client();
  private AskQuestion question = null;
  
  public GoogleChatRunner(AskQuestion question) {
    this.question = question;
  }
  
  @Override
  public AskQuestion call() throws Exception
  {
      GenerateContentResponse response = client.models.generateContent(
          LLM_MODEL,
          question.getQuestion(),
          null);
      
      question.setAnswer( response.text() );
      
      return question;
  }

}
