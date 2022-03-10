## Commands to run via docker-compose:

all commands are executed in the directory (root) with the file docker-compose.yml

1. build project:
   ```
   mvn clean package
   ```
1. start container:
   ``` 
   docker-compose up -d payments-service 
   ```
service uses *port*: **8061**, *hostname*: **payments-service**, *network*: **eureka-net** *dependency on*: **eureka-server**

3. stop container:
   ``` 
   docker-compose stop payments-service
   ```
   
   stop all containers:
   ``` 
   docker-compose down
   ``` 
