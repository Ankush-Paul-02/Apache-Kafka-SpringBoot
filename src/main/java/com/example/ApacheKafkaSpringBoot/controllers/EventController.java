package com.example.ApacheKafkaSpringBoot.controllers;

import com.example.ApacheKafkaSpringBoot.services.KafkaMessagePublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/producer")
@RequiredArgsConstructor
public class EventController {

    private final KafkaMessagePublisher kafkaMessagePublisher;

    @GetMapping("/publish/{message}")
    public ResponseEntity<?> publishMessage(@PathVariable String message) {
        try {
            for (int i = 1; i <= 10000; i++) {
                kafkaMessagePublisher.sendMessageToTopic(message + " " + i);
            }
            return ResponseEntity.ok("message publish successfully...");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }
}
