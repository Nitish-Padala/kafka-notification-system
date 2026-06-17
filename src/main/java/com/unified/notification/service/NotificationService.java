package com.unified.notification.service;

import com.unified.notification.dto.NotificationRequest;

public interface NotificationService {
    void sendNotification(NotificationRequest request);
}