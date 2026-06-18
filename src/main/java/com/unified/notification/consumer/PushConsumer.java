package com.unified.notification.consumer;

import com.unified.notification.dto.NotificationRequest;
import com.unified.notification.service.NotificationService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service

public class PushConsumer {

    @Qualifier("pushServiceImpl")
    private final NotificationService notificationService;
    
    public PushConsumer(@Qualifier("pushServiceImpl") NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @KafkaListener(topics = "notifications_topic", groupId = "push-group")
    public void listen(NotificationRequest request) {
        log.info("Push Consumer received a message: {}", request);

        if ("PUSH".equalsIgnoreCase(request.getChannel())) {
            log.info("Processing PUSH notification for device token: {}", request.getRecipient());
            notificationService.sendNotification(request);
        } else {
            log.info("Message ignored by PushConsumer: channel is {}", request.getChannel());
        }
    }
}
