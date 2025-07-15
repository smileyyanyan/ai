package com.sanutty.ai.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class InMemoryDataService implements DataService
{
  Logger log = LoggerFactory.getLogger(InMemoryDataService.class);
  
  @Value("${spring.datasource.driver-class-name}")
  private String JDBC_DRIVER;
  
  @Value("${spring.datasource.url}")
  private String DB_URL;
  
  @Value("${spring.datasource.username}")
  private String USER;
  
  @Value("${spring.datasource.password}")
  private String PASS;
  

  @Override
  public void updateQuestionCount( String question )
  {
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement stmt = null;
    int existingCount = 0;
    String updateSQL = null;
    
    try {
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        stmt = conn.prepareStatement("select * from questioncount where question = '" +  question + "'");
        rs = stmt.executeQuery();
        
        if (rs.next()) {
          existingCount = rs.getInt("count");
          updateSQL = "update questioncount set count = " + (existingCount+1) + " where question= '" +  question + "'";
        } else {
          updateSQL = "insert into questioncount (question, count) values('" + question + "', 1)";  
        }
        
        stmt = conn.prepareStatement( updateSQL );
        stmt.execute();         
    
    } catch (Exception e) {
      log.error( e.getMessage() );
    } finally {
      try {
        if (rs != null ) {
          rs.close();
        }
        stmt.close();
        conn.close();
      } catch (Exception e) {
        log.error( e.getMessage() );  
      }
    }
    
  }

  @Override
  public Map<String, Integer> getPopularQuestions(int askedFrequence)
  {
    Map<String, Integer> results = new HashMap<>();
    
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement stmt = null;
    
    try {
      
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
      stmt = conn.prepareStatement("select * from questioncount where count >=" +  askedFrequence + " order by count desc");
      rs = stmt.executeQuery();
      
      while (rs.next()) {
        String question = rs.getString("question");
        int count = rs.getInt("count");
        results.put( question, count );
      }
      
    } catch (Exception e) {
      log.error( e.getMessage() );
    } finally {
      try {
        if (rs != null ) {
          rs.close();
        }
        stmt.close();
        conn.close();
      } catch (Exception e) {
        log.error( e.getMessage() );
      }
    }
    
    return results;
  }

}
