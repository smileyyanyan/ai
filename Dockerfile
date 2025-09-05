FROM openjdk:17-jdk-slim
EXPOSE 8080
COPY target/*.jar tinyAIapp.jar
ENTRYPOINT ["java","-jar","/tinyAIapp.jar"]