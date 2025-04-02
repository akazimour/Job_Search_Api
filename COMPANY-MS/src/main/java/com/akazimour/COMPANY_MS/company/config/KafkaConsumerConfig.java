package com.akazimour.COMPANY_MS.company.config;

import com.akazimour.COMPANY_MS.company.dto.ReviewDto;
import com.akazimour.COMPANY_MS.company.external.JobDto;
import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Deserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
public class KafkaConsumerConfig {
    @Bean
    public ConsumerFactory<String, ReviewDto> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "com.akazimour.COMPANY_MS.company.dto");

        return new DefaultKafkaConsumerFactory<>(props, (Deserializer) new StringDeserializer(),
                new JsonDeserializer<>(ReviewDto.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ReviewDto> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, ReviewDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, JobDto> consumerFactoryForJob() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "com.akazimour.COMPANY_MS.company.dto");

        return new DefaultKafkaConsumerFactory<>(props, (Deserializer) new StringDeserializer(),
                new JsonDeserializer<>(JobDto.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, JobDto> kafkaListenerContainerFactoryForJobs() {
        ConcurrentKafkaListenerContainerFactory<String, JobDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactoryForJob());
        return factory;
    }
}
