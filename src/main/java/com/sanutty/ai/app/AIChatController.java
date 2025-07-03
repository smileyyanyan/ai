package com.sanutty.ai.app;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class AIChatController
{
  
  @Autowired
  private ChatService chatService;
  
  @GetMapping("/ask-ai")
  public String greetingForm(Model model) {
    model.addAttribute("askquestion", new AskQuestion());
    return "askquestion";
  }

  @PostMapping("/ask-ai")
  public String greetingSubmit(@ModelAttribute AskQuestion question, Model model) {
    AskQuestion answer = chatService.chat( question );
    model.addAttribute("answer", answer);
    return "answer";
  }
  
}
