package org.javamentor.consumer;

import org.javamentor.consumer.service.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@SpringBootApplication
@EnableBinding(Sink.class)

public class ConsumerApplication {

    private Logger logger = LoggerFactory.getLogger(ConsumerApplication.class);

    @StreamListener("input")
    public void consumeMessage(Book book) {
        logger.info("Consumer from kafka topic: " + book);
    }


    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }

}
