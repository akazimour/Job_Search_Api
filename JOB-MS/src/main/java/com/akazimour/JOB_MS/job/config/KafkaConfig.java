package com.akazimour.JOB_MS.job.config;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic jobTopic() {
        return TopicBuilder.name("job-topic")
                .partitions(2)
                .build();
    }
}
