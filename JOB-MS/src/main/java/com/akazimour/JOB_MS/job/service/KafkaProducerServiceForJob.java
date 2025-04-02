package com.akazimour.JOB_MS.job.service;

import com.akazimour.JOB_MS.job.dto.JobDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class KafkaProducerServiceForJob {

    private final Logger LOGGER = LoggerFactory.getLogger(KafkaProducerServiceForJob.class);

    @Autowired
    KafkaTemplate<UUID, JobDto> kafkaTemplate;

    public void sendMessage(String topicName,UUID id,JobDto jobDto ){
        CompletableFuture<SendResult<UUID, JobDto>> send = kafkaTemplate.send(topicName, id, jobDto);
        send.whenComplete((sendResult,exception)->{
            if (exception!=null){
                LOGGER.error(exception.getMessage());
                send.completeExceptionally(exception);
            }else {
                send.complete(sendResult);
            }
            LOGGER.info("Job has been added successfully with title: "+jobDto.getTitle()+" Id: "+id
            );
        });
    }

}
