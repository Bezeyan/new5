FROM openjdk:11
EXPOSE 8762
ADD ./target/spring-gateway-0.0.1-SNAPSHOT.jar spring-gateway/app.jar
WORKDIR ./spring-gateway/
ENTRYPOINT ["java", "-jar", "./app.jar"]
