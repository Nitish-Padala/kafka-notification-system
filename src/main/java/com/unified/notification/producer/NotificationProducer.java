package com.unified.notification.producer;

import com.unified.notification.dto.NotificationRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationProducer {

    private final KafkaTemplate<String, NotificationRequest> kafkaTemplate;
    private static final String TOPIC = "notifications_topic";

    public void sendNotification(NotificationRequest request) {
        log.info("Producing message to Kafka: {}", request);

        // We send the request object.
        // KafkaTemplate handles the serialization to JSON (via the config we'll set up next).
        this.kafkaTemplate.send(TOPIC, request);
    }
}