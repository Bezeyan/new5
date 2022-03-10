FROM openjdk:11
EXPOSE 8761
ADD /target/eureka_server-0.0.1-SNAPSHOT.jar /eureka/app.jar
WORKDIR ./eureka/
ENTRYPOINT ["java", "-jar", "./app.jar"]