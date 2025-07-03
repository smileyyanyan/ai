package com.sanutty.openai.app.services;


import org.springframework.stereotype.Service;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;

@Service
public class ChatService {
  
    private Client client = new Client();
  
    public String chat(String question) {
      
      GenerateContentResponse response = client.models.generateContent(
                                                      "gemini-2.5-flash",
                                                      question,
                                                      null);
      return response.text();
    }
    
}
