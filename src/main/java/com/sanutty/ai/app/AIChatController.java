package com.sanutty.ai.app;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class AIChatController
{
  
  @Autowired
  private ChatService chatService;

  @Autowired
  private DataService dataService;

  
  @GetMapping("/ask-ai")
  public String greetingForm(Model model) {
    model.addAttribute("askquestion", new AskQuestion());
    return "askquestion";
  }

  @PostMapping("/ask-ai")
  public String greetingSubmit(@ModelAttribute AskQuestion question, Model model) {
    AskQuestion answer = chatService.chat( question );
    dataService.updateQuestionCount(question.getQuestion() );
    model.addAttribute("answer", answer);
    return "answer";
  }
  
  @GetMapping("/ask-ai/popular")
  public ModelAndView mostPopularQuestions() {
    ModelAndView mav = new ModelAndView("popularquestions");
    Map<String, Integer> popularQuestionsMap = dataService.getPopularQuestions( 2 );
    mav.addObject("popularQuestionsMap", popularQuestionsMap);
    return mav;
  }
  
  
}
