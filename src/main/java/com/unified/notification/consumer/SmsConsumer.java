package com.unified.notification.consumer;

import com.unified.notification.dto.NotificationRequest;
import com.unified.notification.service.NotificationService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service

public class SmsConsumer {

    @Qualifier("smsServiceImpl")
    private final NotificationService notificationService;
    public SmsConsumer(@Qualifier("smsServiceImpl") NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @KafkaListener(topics = "notifications_topic", groupId = "notification-group")
    public void listen(NotificationRequest request) {
        log.info("SMS Consumer received a message: {}", request);

        if ("SMS".equalsIgnoreCase(request.getChannel())) {
            log.info("Processing SMS notification for phone: {}", request.getRecipient());
            notificationService.sendNotification(request);
        } else {
            log.info("Message ignored by SmsConsumer: channel is {}", request.getChannel());
        }
    }
}
