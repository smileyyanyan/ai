# Tiny AI Chat Service
 
This is a Spring Boot application with a simple text box to ask AI a question. The current design uses the goole chat client to the free tier API key. The chat service is configurable and the google chat can be easily replaced with another chat model by simply changing it in application.properties and adding a new service implementation. A simple ConcurrentMapCacheManager cache is used for commonly asked questions. 


### Software Design  

The software architecture diagram is listed below. The google chat service uses a callable task to answer the question. Currently it only answers one question. It is easy to add new methods to answer multiple questions. 

![Alt text](Design.jpg?raw=true "Architectural Design")


### Running the application 

Just run AISpringBootApplication as a Java application with a GOOGLE_API_KEY environment variable where the value is the free tier API key. Alternatively, build and run with docker with the docker instructions below. 

### Docker  

The dockerfile exposes port 9888.

  * docker build -t spring/chat-service-spring-boot-docker .
  * docker run -p 9888:8080 spring/chat-service-spring-boot-docker



 


