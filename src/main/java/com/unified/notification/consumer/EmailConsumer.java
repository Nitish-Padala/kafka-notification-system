package com.unified.notification.consumer;

import com.unified.notification.dto.NotificationRequest;
import com.unified.notification.service.NotificationService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service

public class EmailConsumer {

    @Qualifier("emailServiceImpl")
    private final NotificationService notificationService;
    public EmailConsumer(@Qualifier("emailServiceImpl") NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @KafkaListener(topics = "notifications_topic", groupId = "notification-group")
    public void listen(NotificationRequest request) {
        log.info("Email Consumer received a message: {}", request);

        if ("EMAIL".equalsIgnoreCase(request.getChannel())) {
            log.info("Processing EMAIL notification for: {}", request.getRecipient());
            notificationService.sendNotification(request);
        } else {
            log.info("Message ignored by EmailConsumer: channel is {}", request.getChannel());
        }
    }
}
