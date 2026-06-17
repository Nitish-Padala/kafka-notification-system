package com.unified.notification.service.impl;

import com.unified.notification.dto.NotificationRequest;
import com.unified.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements NotificationService {

    private final JavaMailSender mailSender;

    @Override
    public void sendNotification(NotificationRequest request) {
        log.info("Executing real email delivery to: {}", request.getRecipient());

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(request.getRecipient());
            message.setSubject("Unified Notification System Alert");
            message.setText(request.getMessage());

            mailSender.send(message);
            log.info("Email successfully sent to {}", request.getRecipient());
        } catch (Exception e) {
            log.error("Failed to send email to {}: {}", request.getRecipient(), e.getMessage());
        }
    }
}