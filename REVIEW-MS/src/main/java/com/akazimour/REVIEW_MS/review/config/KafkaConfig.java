package com.akazimour.REVIEW_MS.review.config;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic reviewTopic() {
        return TopicBuilder.name("review-topic")
                .partitions(2)
                .build();
    }
}
