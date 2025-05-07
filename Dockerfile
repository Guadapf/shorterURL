FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/shorterUrl-0.0.1.jar
COPY ${JAR_FILE} shorterUrl.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "shorterUrl.jar"]