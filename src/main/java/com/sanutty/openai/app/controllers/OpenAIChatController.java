package com.sanutty.openai.app.controllers;

import java.util.Optional;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sanutty.openai.app.services.ChatService;

@RestController
@RequestMapping("openai/ask-ai")
public class OpenAIChatController
{
  
  @Autowired
  private ChatService chatService;
  
  @Autowired
  private ConfigurableEnvironment env;
  
  @PostMapping("/question")
  public ResponseEntity<String> ask(@RequestBody String question) {
    
    String projectPath = System.getProperty("user.dir");
    
    System.out.println ("projectPath = " + projectPath);
    
    Object javaClassPath = env.getPropertySources().get( "systemProperties" ).getProperty( "java.class.path" );  
    
    
    PropertySource<?> sysEnvPropertySource = env.getPropertySources().get( "systemEnvironment" );
    
    
    
    
    StringTokenizer tokenizer = new StringTokenizer(javaClassPath.toString(), ";");  
    
    System.out.println(javaClassPath);
    System.out.println("-----------------");
    System.out.println("java.class.path");
    System.out.println("-----------------");
    while (tokenizer.hasMoreTokens() ) {
      System.out.println(tokenizer.nextToken());
    }
    System.out.println("-----------------");
    
    MutablePropertySources propertySources = env.getPropertySources();   
    //java.home=C:\Tools\java\java17\jdk-17.0.2, systemProperties   //sun.boot.library.path=C:\Tools\java\java17\jdk-17.0.2\bin
    String answer = chatService.chat( question );
    return ResponseEntity.of( Optional.of( answer ) );
  }
}
