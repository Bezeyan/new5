FROM openjdk:11
EXPOSE 8082
ADD /target/email-service.jar /email-service/app.jar
WORKDIR ./email-service/
ENTRYPOINT ["java", "-jar", "./app.jar"]
