FROM openjdk:11
EXPOSE 8061
ADD ./target/payments_service-0.0.1-SNAPSHOT.war payments_service/app.war
WORKDIR ./payments_service/
ENTRYPOINT ["java", "-jar", "./app.war"]
