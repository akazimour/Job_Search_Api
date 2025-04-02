package com.akazimour.REVIEW_MS.review.service;

import com.akazimour.REVIEW_MS.review.dto.ReviewDto;
import com.akazimour.REVIEW_MS.review.dto.ReviewMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class KafkaProducerService {

    private final Logger LOGGER = LoggerFactory.getLogger(KafkaProducerService.class);

    @Autowired
    KafkaTemplate<UUID, ReviewDto> kafkaTemplate;

    public void sendMessage(String topicName,UUID id,ReviewDto reviewDto ){
        CompletableFuture<SendResult<UUID, ReviewDto>> send = kafkaTemplate.send(topicName, id, reviewDto);
        send.whenComplete((sendResult,exception)->{
            if (exception!=null){
                LOGGER.error(exception.getMessage());
                send.completeExceptionally(exception);
            }else {
                send.complete(sendResult);
            }
            LOGGER.info("Review has been added successfully with title: "+reviewDto.getTitle()+" Id: "+id
            );
        });
    }

}
