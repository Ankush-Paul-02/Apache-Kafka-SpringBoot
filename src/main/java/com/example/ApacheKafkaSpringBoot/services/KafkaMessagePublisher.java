package com.example.ApacheKafkaSpringBoot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaMessagePublisher {

    @Autowired
    private KafkaTemplate<String, Object> template;

    public void sendMessageToTopic(String message) {
        CompletableFuture<SendResult<String, Object>> future = template.send("dev3", message);
        future.whenComplete(
                (result, e) -> {
                    if (e == null) {
                        System.out.println("Sent message=[" + message +
                                "] with offset=[" + result.getRecordMetadata().offset() + "] with partition=[" + result.getRecordMetadata().partition() + "]");
                    } else {
                        System.out.println("Unable to send the message=[" + message +
                                "] due to: " + e.getMessage());
                    }
                }
        );
    }
}
