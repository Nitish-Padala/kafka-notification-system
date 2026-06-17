package com.unified.notification.controller;

import com.unified.notification.dto.NotificationRequest;
import com.unified.notification.producer.NotificationProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationProducer producer;

    @PostMapping("/send")
    public ResponseEntity<String> sendNotification(@RequestBody NotificationRequest request) {
        // Log the incoming request
        System.out.println("Received request to send notification: " + request);

        // Use the producer to send the message to Kafka
        producer.sendNotification(request);

        // Return a success response to the user
        return ResponseEntity.ok("Notification request sent to Kafka successfully!");
    }
}