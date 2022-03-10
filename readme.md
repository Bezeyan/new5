#this jm_social_project with microservice...

## Commands to run via docker-compose:

all commands are executed in the directory with the file docker-compose.yml

if the databases "chatdb", "frienddb", "accountdb" does not exist, then first you need to run:

   ``` 
   docker-compose up -d postgres
   docker exec -it postgresdb bash
   psql -U postgres
   CREATE DATABASE chatdb;
   CREATE DATABASE frienddb;
   CREATE DATABASE accountdb;
   ```
1. build project:
   ```
   mvn clean package
   ```

* **the command is executed in the directory of each service**
1. start containers:
   ``` 
   docker-compose up -d 
   ```

3. stop all containers:
   ``` 
   docker-compose down 
   ```