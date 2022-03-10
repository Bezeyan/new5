FROM openjdk:11
EXPOSE 8085
ADD ./target/notification_service-0.0.1-SNAPSHOT.jar notification_service/app.jar
WORKDIR ./notification_service/
ENTRYPOINT ["java", "-jar", "./app.jar"]
