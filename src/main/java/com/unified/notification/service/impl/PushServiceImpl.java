//package com.unified.notification.service.impl;
//
//import com.google.auth.oauth2.GoogleCredentials;
//import com.google.firebase.FirebaseApp;
//import com.google.firebase.FirebaseOptions;
//import com.google.firebase.messaging.FirebaseMessaging;
//import com.google.firebase.messaging.Message;
//import com.google.firebase.messaging.Notification;
//import com.unified.notification.dto.NotificationRequest;
//import com.unified.notification.service.NotificationService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import jakarta.annotation.PostConstruct;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//
//@Slf4j
//@Service
//@RequiredArgsConstructor
//public class PushServiceImpl implements NotificationService {
//
//	 @Value("${firebase.config.path}")
//	    private String configPath;
//
//	   
//	    public void initializeFirebase() {
//	        try {
//	            FileInputStream serviceAccount = new FileInputStream(configPath);
//	            FirebaseOptions options = FirebaseOptions.builder()
//	                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//	                    .build();
//
//	            if (FirebaseApp.getApps().isEmpty()) {
//	                FirebaseApp.initializeApp(options);
//	                log.info("Firebase App initialized successfully");
//	            }
//	        } catch (IOException e) {
//	            log.error("Failed to initialize Firebase: {}", e.getMessage());
//	        }
//	    }
//
//	    @Override
//	    public void sendNotification(NotificationRequest request) {
//	        log.info("Executing real PUSH delivery to token: {}", request.getRecipient());
//
//	        try {
//	            // Create the notification content
//	            Notification notification = Notification.builder()
//	                    .setTitle("Unified Notification")
//	                    .setBody(request.getMessage())
//	                    .build();
//
//	            // Build the message
//	            Message message = Message.builder()
//	                    .setNotification(notification)
//	                    .setToken(request.getRecipient()) // Device Token
//	                    .build();
//
//	            // Send it!
//	            String response = FirebaseMessaging.getInstance().send(message);
//	            log.info("Push notification sent successfully! Message ID: {}", response);
//	        } catch (Exception e) {
//	            log.error("Firebase Push failed to {}: {}", request.getRecipient(), e.getMessage());
//	        }
//	    }
//	}
//    
//
package com.unified.notification.service.impl;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.unified.notification.dto.NotificationRequest;
import com.unified.notification.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;

@Slf4j
@Service
public class PushServiceImpl implements NotificationService {

    @Value("${firebase.config.path}")
    private String configPath;

    private synchronized void ensureFirebaseInitialized() {
        log.info("[DEBUG STEP 1] Checking if Firebase is initialized...");
        if (FirebaseApp.getApps().isEmpty()) {
            log.info("[DEBUG STEP 2] Firebase NOT initialized. Attempting to load JSON from: {}", configPath);
            try {
                FileInputStream serviceAccount = new FileInputStream(configPath);
                log.info("[DEBUG STEP 3] JSON file found and opened successfully.");

                FirebaseOptions options = FirebaseOptions.builder()
                        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                        .build();

                FirebaseApp.initializeApp(options);
                log.info("[DEBUG STEP 4] SUCCESS: FirebaseApp [DEFAULT] has been initialized!");
            } catch (IOException e) {
                log.error("[DEBUG ERROR] IO Exception during initialization: {}", e.getMessage());
            } catch (Exception e) {
                log.error("[DEBUG ERROR] Unexpected error during initialization: {}", e.getClass().getName() + " - " + e.getMessage());
            }
        } else {
            log.info("[DEBUG STEP 1] Firebase is already initialized. Skipping.");
        }
    }

    @Override
    public void sendNotification(NotificationRequest request) {
        log.info("Executing real PUSH delivery to token: {}", request.getRecipient());

        try {
            ensureFirebaseInitialized();

            Notification notification = Notification.builder()
                    .setTitle("Unified Notification")
                    .setBody(request.getMessage())
                    .build();

            Message message = Message.builder()
                    .setNotification(notification)
                    .setToken(request.getRecipient())
                    .build();

            String response = FirebaseMessaging.getInstance().send(message);
            log.info("Push notification sent successfully! Message ID: {}", response);
        } catch (Exception e) {
            log.error("Firebase Push failed: {}", e.getMessage());
        }
    }
}
