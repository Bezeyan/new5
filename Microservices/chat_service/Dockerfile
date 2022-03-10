FROM openjdk:11
EXPOSE 8542
ADD ./target/chat-service.jar chat-service/app.jar
WORKDIR ./chat-service/
ENV eureka.client.serviceUrl.defaultZone=http://eureka:8761/eureka/
ENV spring.datasource.url=jdbc:postgresql://postgres:5432/frienddb
ENTRYPOINT ["java", "-jar", "./app.jar"]
