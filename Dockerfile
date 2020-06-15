FROM openjdk:11.0.7-jre-slim

ARG JAR_FILE=build/libs/jaeger-tracing-java-service-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/app.jar"]