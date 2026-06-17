package com.unified.notification.service.impl;

import com.unified.notification.dto.NotificationRequest;
import com.unified.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PushServiceImpl implements NotificationService {

    // In a real app, we would inject the Firebase Messaging client here
    // private final FirebaseMessaging firebaseMessaging;

    @Override
    public void sendNotification(NotificationRequest request) {
        log.info("Executing real PUSH delivery to token: {}", request.getRecipient());

        try {
            // SIMULATION: In Phase 3.2, we will integrate the FCM SDK
            log.info("[FCM SIMULATION] Sending Push Notification to {}: {}", request.getRecipient(), request.getMessage());
        } catch (Exception e) {
            log.error("Failed to send Push notification to {}: {}", request.getRecipient(), e.getMessage());
        }
    }
}
