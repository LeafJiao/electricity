package com.electricity.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author jiaowei
 * @since 2024/11/18 13:15
 */
@Component
@Slf4j
public class ConsumerKafka {

    @KafkaListener(topics = "tractics")
    public void consumer(ConsumerRecord<Integer, String> record) {
        log.info("consumer message: {}", record.toString());
    }
}
