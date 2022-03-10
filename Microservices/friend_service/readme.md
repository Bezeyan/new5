## Commands to run via Docker:

all commands are executed in the root app directory

1. eureka-server must be started first

1. create volume (on first launch or if deleted): 
   ```
   docker volume create microservices_postgresql-data
   ```
1. create a network (if not exists):
   ```
   docker network create postgres-net
   ```    
1. start PostgreSQL: 
   ```
   docker run -p 5432:5432 --name postgresdb --network=postgres-net -v microservices_postgresql-data:/var/lib/postgresql/data --rm -e POSTGRES_PASSWORD=root -d --hostname=postgres postgres
   ```
1. create a database "frienddb" (on first launch or if volume "microservices_postgresql-data" has been deleted):
   ```
   docker exec -it postgresdb bash
   psql -U postgres
   CREATE DATABASE frienddb;
   ```
1. build project: 
   ```
   mvn clean package
   ```
1. build docker image: 
   ```
   docker build -t microservices_friend-service .
   ```
1. start container:
   ```
   docker run --name friend_service -p 8542:8542 -d --rm --network=postgres-net --hostname=friend-service microservices_friend-service
   ```
1. connect to eureka:
   ```
   docker network connect eureka-net friend_service
   ```

## Commands to run via docker-compose:

all commands are executed in the directory with the file docker-compose.yml

if the database "frienddb" does not exist, then first you need to run:
   ``` 
   docker-compose up -d postgres
   docker exec -it postgresdb bash
   psql -U postgres
   CREATE DATABASE frienddb;
   ```
1. build project:
   ```
   mvn clean package
   ```

1. start container:
   ``` 
   docker-compose up -d friend-service 
   ```
service uses *port*: **8542**, *hostname*: **friend-service**, *network*: {**eureka-net**, **postgres-net**} *dependency on*: {**eureka-server**, **postgres**}   

3. stop container:
   ``` 
   docker-compose stop friend-service 
   ```

   stop all containers:
   ``` 
   docker-compose down 
   ```