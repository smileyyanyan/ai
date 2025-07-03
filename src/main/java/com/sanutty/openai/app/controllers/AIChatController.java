package com.sanutty.openai.app.controllers;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sanutty.openai.app.services.ChatService;

@RestController
@RequestMapping("ask-ai")
public class AIChatController
{
  
  @Autowired
  private ChatService chatService;
  

  
  @PostMapping("/question")
  public ResponseEntity<String> ask(@RequestBody String question) {
    
    String answer = chatService.chat( question );

    return ResponseEntity.of( Optional.of(answer));
    
  }
}
