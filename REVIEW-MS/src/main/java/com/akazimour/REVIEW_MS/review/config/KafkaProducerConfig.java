package com.akazimour.REVIEW_MS.review.config;
import com.akazimour.REVIEW_MS.review.dto.ReviewDto;
import com.akazimour.REVIEW_MS.review.dto.ReviewMessage;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Configuration
public class KafkaProducerConfig {

    @Bean
    public ProducerFactory<UUID, ReviewDto> producerFactory() {
        Map<String, Object> producerConfig = new HashMap<>();
        producerConfig.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        producerConfig.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, org.apache.kafka.common.serialization.UUIDSerializer.class);
        producerConfig.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, org.springframework.kafka.support.serializer.JsonSerializer.class);
        producerConfig.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, true);
        return new DefaultKafkaProducerFactory<>(producerConfig);
    }

    @Bean
    KafkaTemplate<UUID, ReviewDto> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
