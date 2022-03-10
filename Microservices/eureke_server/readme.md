## Commands to run via Docker:

all commands are executed in the root app directory

1) build project: 
   ```
   mvn clean package
   ```
2) create network:
   ```
   docker network create eureka-net
   ```
2) build docker image: 
   ```
   docker build -t microservices_eureka .
   ```
3) start container:
   ```
   docker run --name eureka-server -d -p 8761:8761 --rm --network=eureka-net --hostname=eureka microservices_eureka
   ```
4) stop container:
   ```
   docker stop eureka-server
   ```

## Commands to run via docker-compose:
 all commands are executed in the directory with the file docker-compose.yml
1) start container:
   ``` 
   docker-compose up -d eureka 
   ```

service uses *port*: **8761**, *hostname*: **eureka**, *network*: **eureka-net**

2) stop container:
   ``` 
   docker-compose stop eureka 
   ```

   stop all containers:
   ``` 
   docker-compose down 
   ```