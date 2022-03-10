## Commands to run via docker-compose:

all commands are executed in the directory (root) with the file docker-compose.yml

1. build project:
   ```
   mvn clean package
   ```
1. start container:
   ``` 
   docker-compose up -d media_storage 
   ```
service uses *port*: **8083**, *hostname*: **media_storage**, *network*: {**eureka-net**, **mongo-net**} *dependency on*: {**eureka-server**, **mongo**}

3. stop container:
   ``` 
   docker-compose stop media_storage 
   ```

   stop all containers:
   ``` 
   docker-compose down 
   ```