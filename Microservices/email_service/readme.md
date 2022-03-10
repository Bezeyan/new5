## Commands to run via docker-compose:

all commands are executed in the directory with the file docker-compose.yml

1. build project:
   ```
   mvn clean package
   ```
1. start container:
   ``` 
   docker-compose up -d chat-service 
   ```
service uses *port*: **8082**, *hostname*: **email-service**, *network*: **eureka-net**, *dependency on*: **eureka-server**
   
3. stop container:
   ``` 
   docker-compose stop chat-service 
   ```

   stop all containers:
   ``` 
   docker-compose down 
   ```




