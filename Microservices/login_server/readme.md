## Commands to run via docker-compose:

all commands are executed in the directory (root) with the file docker-compose.yml

if the database "accountdb" does not exist, then first you need to run:
   ``` 
   docker-compose up -d postgres
   docker exec -it postgresdb bash
   psql -U postgres
   CREATE DATABASE accountdb;
   ```
1. build project:
   ```
   mvn clean package
   ```

1. start container:
   ``` 
   docker-compose up -d login-service 
   ```
service uses *port*: **8000**, *hostname*: **login-service**, *network*: {**eureka-net**, **postgres-net**} *dependency on*: {**eureka-server**, **postgres**}   

3. stop container:
   ``` 
   docker-compose stop login-service 
   ```

   stop all containers:
   ``` 
   docker-compose down 
   ```