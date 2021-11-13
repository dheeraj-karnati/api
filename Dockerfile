FROM openjdk:8-jre-alpine
COPY build/libs/api-service-1.0.jar api-service-1.0.jar
ENTRYPOINT ["java","-jar","api-service-1.0.jar"]
