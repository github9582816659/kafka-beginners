package com.kafka.consumer.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/*
    When using Manual Ack we will going to use LibraryEventConsumerManualOffsetAck class instead of this for default ack ( which is batch )
 */

//@Component
//@Slf4j
public class TwitterConsumer {

   /* @KafkaListener(topics = {"library-events"})
    public void onMessage(ConsumerRecord<Integer, String> consumerRecord) {
        log.info("ConsumerRecord : {}",consumerRecord);
    }*/
}
