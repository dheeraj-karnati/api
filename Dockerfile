FROM public.ecr.aws/p6v2g3m5/openjdk:8-alpine3.9
COPY build/libs/api-service-1.0.jar api-service-1.0.jar
ENTRYPOINT ["java","-jar","api-service-1.0.jar"]
