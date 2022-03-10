FROM openjdk:11
EXPOSE 8081
ADD ./target/media_storage-0.0.1-SNAPSHOT.jar media_storage/app.jar
WORKDIR ./media_storage/
ENTRYPOINT ["java", "-jar", "./app.jar"]
