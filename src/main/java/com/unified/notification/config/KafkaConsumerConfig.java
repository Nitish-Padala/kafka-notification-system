package com.unified.notification.config;

import com.unified.notification.dto.NotificationRequest;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    @Bean
    public ConsumerFactory<String, NotificationRequest> consumerFactory() {
        Map<String, Object> props = new HashMap<>();

        // 1. Where is Kafka running?
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

        // 2. Consumer Group ID (CRITICAL: This identifies this set of consumers)
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "notification-group");

        // 3. Key Deserializer (Convert bytes back to String)
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        // 4. Value Deserializer (Convert JSON bytes back to NotificationRequest object)
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        // 5. Tell the JsonDeserializer which class to trust and map to
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "com.unified.notification.dto");
        props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, "com.unified.notification.dto.NotificationRequest");

        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, NotificationRequest> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, NotificationRequest> factory =
            new ConcurrentKafkaListenerContainerFactory<>();

        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}