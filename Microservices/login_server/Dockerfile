FROM openjdk:11
EXPOSE 8000
ADD ./target/demo-0.0.1-SNAPSHOT.jar friend_service/app.jar
WORKDIR ./friend_service/
ENV eureka.client.serviceUrl.defaultZone=http://eureka:8761/eureka/
ENV spring.datasource.url=jdbc:postgresql://postgres:5432/accountdb
ENTRYPOINT ["java", "-jar", "./app.jar"]
