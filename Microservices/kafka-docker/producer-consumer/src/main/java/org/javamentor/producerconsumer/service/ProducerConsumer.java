package org.javamentor.producerconsumer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerConsumer {
    public static final String topic = "mytopic2";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemp;

    @KafkaListener(topics = "mytopic", groupId = "mygroup")
    public void consumeFromTopic(String message) {
        String modifyMessage = message + 0;
        System.out.println("ProducerConsumer get: " + message +
                ", and send " + modifyMessage);
        publishToTopic(modifyMessage);
    }

    public void publishToTopic(String message) {
        this.kafkaTemp.send(topic, message);
    }
}