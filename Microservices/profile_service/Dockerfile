FROM openjdk:11
EXPOSE 8085
ADD ./target/profile_service-0.0.1-SNAPSHOT.war profile-service/app.war
WORKDIR ./profile-service/
ENTRYPOINT ["java", "-jar", "./app.war"]
