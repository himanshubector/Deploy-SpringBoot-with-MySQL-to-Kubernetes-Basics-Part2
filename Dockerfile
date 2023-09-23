
FROM openjdk:17-oracle
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} article-service.jar
ENTRYPOINT ["java","-jar","/article-service.jar"]








