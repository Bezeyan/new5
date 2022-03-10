## Commands to run via docker-compose:

all commands are executed in the directory (root) with the file docker-compose.yml

1. build project:
   ```
   mvn clean package
   ```
1. start container:
   ``` 
   docker-compose up -d notification_service 
   ```
service uses *port*: **8086**, *hostname*: **notification_service**, *network*: **eureka-net** *dependency on*: **eureka-server**

3. stop container:
   ``` 
   docker-compose stop notification_service 
   ```

   stop all containers:
   ``` 
   docker-compose down 
   ```