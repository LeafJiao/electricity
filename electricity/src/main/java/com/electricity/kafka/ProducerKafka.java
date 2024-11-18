package com.electricity.kafka;

import jakarta.annotation.Resource;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @author jiaowei
 * @since 2024/11/18 13:14
 */
@Component
public class ProducerKafka {

    @Resource
    private KafkaTemplate<Integer, String> kafkaTemplate;

    public void send(String topic, String message) {
        kafkaTemplate.send(topic, message);
    }

}
