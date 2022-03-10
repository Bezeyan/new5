## Commands to run via docker-compose:

all commands are executed in the directory (root) with the file docker-compose.yml

1. build project:
   ```
   mvn clean package
   ```
1. start container:
   ``` 
   docker-compose up -d spring-gateway 
   ```
service uses *port*: **8762**, *hostname*: **spring-gateway**, *network*: **eureka-net** *dependency on*: **eureka-server**

3. stop container:
   ``` 
   docker-compose stop spring-gateway 
   ```

   stop all containers:
   ``` 
   docker-compose down 
   ```
   