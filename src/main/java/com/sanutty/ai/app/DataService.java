package com.sanutty.ai.app;

import java.util.Map;

public interface DataService {
  /**
   * Update the count a specific question was asked
   * @param question
   */
  public void updateQuestionCount(String question);
  
  /**
   * Get the most frequently asked questions, each with count
   * @param questionCount
   * @return
   */
  public Map<String, Integer> getPopularQuestions(int askedFrequence) ;
}
