package com.sanutty.ai.app;


import org.springframework.stereotype.Service;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;

@Service
public class ChatService {
  
    private Client client = new Client();
  
    public AskQuestion chat(AskQuestion question) {
      
      GenerateContentResponse response = client.models.generateContent(
                                                      "gemini-2.5-flash",
                                                      question.getQuestion(),
                                                      null);
      question.setAnswer( response.text() );
      
      return question;
    }
    
}
