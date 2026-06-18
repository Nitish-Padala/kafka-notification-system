package com.unified.notification.service.impl;

import com.unified.notification.dto.NotificationRequest;
import com.unified.notification.service.NotificationService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;


@Slf4j
@Service
@RequiredArgsConstructor
public class SmsServiceImpl implements NotificationService {
	
	
	  @Value("${twilio.account.sid}")
	    private String accountSid;

	    @Value("${twilio.auth.token}")
	    private String authToken;

	    @Value("${twilio.phone.number}")
	    private String fromPhoneNumber;
	    
	    @PostConstruct
	    public void initTwilio() {
	        // Initialize the Twilio client once when the app starts
	        Twilio.init(accountSid, authToken);
	    }


    // In a real app, we would inject a Twilio client here
    // private final TwilioClient twilioClient;
	
//    @Override
//    public void sendNotification(NotificationRequest request) {
//        log.info("Executing real SMS delivery to phone: {}", request.getRecipient());
//
//        try {
//            Message message = Message.creator(
//            		 request.getRecipient(), // Just the String
//            	        fromPhoneNumber,        // Just the String
//            	        request.getMessage()                      // Body
//            ).create();
//
//            log.info("SMS successfully sent! SID: {}", message.getSid());
//        } catch (Exception e) {
//            log.error("Twilio SMS failed to {}: {}", request.getRecipient(), e.getMessage());
//        }
//    }

    @Override
    public void sendNotification(NotificationRequest request) {
        log.info("Executing real SMS delivery to phone: {}", request.getRecipient());

        try {
            // Create PhoneNumber objects using the 'new' keyword
            PhoneNumber toPhone = new PhoneNumber(request.getRecipient());
            PhoneNumber fromPhone = new PhoneNumber(fromPhoneNumber);

            Message message = Message.creator(
                    toPhone,            // Use the object
                    fromPhone,          // Use the object
                    request.getMessage() // Body (String)
            ).create();

            log.info("SMS successfully sent! SID: {}", message.getSid());
        } catch (Exception e) {
            log.error("Twilio SMS failed to {}: {}", request.getRecipient(), e.getMessage());
        }
    }
}
