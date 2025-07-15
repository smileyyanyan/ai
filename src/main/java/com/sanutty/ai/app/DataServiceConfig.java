package com.sanutty.ai.app;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;


public class DataServiceConfig {
  
  @Bean
  @ConditionalOnProperty(name = "data.service.implementation", havingValue = "inmemory")
  public DataService geInMemoryDataService() {
      return new InMemoryDataService();
  }

}
