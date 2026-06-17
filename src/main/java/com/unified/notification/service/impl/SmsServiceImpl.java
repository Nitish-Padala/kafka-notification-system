package com.unified.notification.service.impl;

import com.unified.notification.dto.NotificationRequest;
import com.unified.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SmsServiceImpl implements NotificationService {

    // In a real app, we would inject a Twilio client here
    // private final TwilioClient twilioClient;

    @Override
    public void sendNotification(NotificationRequest request) {
        log.info("Executing real SMS delivery to phone: {}", request.getRecipient());

        try {
            // SIMULATION: In Phase 3.2, we will integrate the Twilio SDK
            log.info("[TWILIO SIMULATION] Sending SMS to {}: {}", request.getRecipient(), request.getMessage());
        } catch (Exception e) {
            log.error("Failed to send SMS to {}: {}", request.getRecipient(), e.getMessage());
        }
    }
}
