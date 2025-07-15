package com.sanutty.ai.app;

import jakarta.persistence.*;

@Entity
@Table(name = "questioncount")
public class QuestionEntity 
{ 
    @Id 
    @Column(length = 125, nullable = false)
    private String question;
    
    @Column(length = 2048, nullable = false)
    private int count;
    

    public String getValue()
    {
      return question;
    }
    public void setValue( String value )
    {
      this.question = value;
    }
    
}
