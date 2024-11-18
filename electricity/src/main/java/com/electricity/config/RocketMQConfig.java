package com.electricity.config;

import cn.hutool.core.thread.ThreadUtil;
import com.electricity.constant.RocketMQConstant;
import com.electricity.handler.infrast.MQHandler;
import com.electricity.listener.ConsumerListenerConcurrently;
import jakarta.annotation.Resource;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.remoting.protocol.heartbeat.MessageModel;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.concurrent.ExecutorService;

/**
 * @author jiaowei
 * @since 2024/11/5 21:23
 */
@Configuration
@ConfigurationProperties(prefix = "rocketmq")
@Data
@Slf4j
public class RocketMQConfig {

    private String pNamesrvAddr;

    private Integer maxMessageSize;

    private Integer sendMsgTimeout;

    private Integer retryTimesWhenSendFailed;

    private static ExecutorService executorService = ThreadUtil.newExecutor(32);

    /**
     * 普通消息生产者
     * @return
     */
    @Bean("defaultMQ")
    public DefaultMQProducer defaultMQProducer() {
        DefaultMQProducer producer = new DefaultMQProducer(RocketMQConstant.group1);
        producer.setNamesrvAddr(pNamesrvAddr);
        producer.setMaxMessageSize(maxMessageSize);
        producer.setSendMsgTimeout(sendMsgTimeout);
        producer.setRetryTimesWhenSendFailed(retryTimesWhenSendFailed);
        try {
            producer.start();
        } catch (MQClientException e) {
            log.warn("普通消息生产启动失败");
            throw new RuntimeException(e);
        }
        return producer;
    }

    /**
     * 事务消息生产者
     * @return
     */
    @Bean("transactionMQ")
    public TransactionMQProducer transactionMQProducer() {
        TransactionMQProducer producer = new TransactionMQProducer("transaction" + RocketMQConstant.group1);
        producer.setNamesrvAddr(pNamesrvAddr);
        producer.setNamesrvAddr(pNamesrvAddr);
        producer.setMaxMessageSize(maxMessageSize);
        producer.setSendMsgTimeout(sendMsgTimeout);
        producer.setRetryTimesWhenSendFailed(retryTimesWhenSendFailed);
        producer.setExecutorService(executorService);
        try {
            producer.start();
        } catch (MQClientException e) {
            log.warn("事务消息生产启动失败");
            throw new RuntimeException(e);
        }
        return producer;
    }
    
    @Resource
    private Map<String, MQHandler> mqHandlerMap;

    private int consumeThreadMin;

    private int consumeThreadMax;

    private String topics;

    private int consumeMessageBatchMaxSize;


    /**
     * 消息消费者
     * @return
     */
    public DefaultMQPushConsumer defaultMQPushConsumer() {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(RocketMQConstant.group1);
        consumer.setNamesrvAddr(pNamesrvAddr);
        consumer.setConsumeThreadMin(consumeThreadMin);
        consumer.setConsumeThreadMax(consumeThreadMax);
        consumer.registerMessageListener(messageListenerConcurrently());
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        // 设置消费模式为集群，抢占
        consumer.setMessageModel(MessageModel.CLUSTERING);
        // 设置一次消费的条数
        consumer.setConsumeMessageBatchMaxSize(consumeMessageBatchMaxSize);
        
        try {
            String[] topicTags = topics.split(";");
            for (String topicTag : topicTags) {
                String[] topicAndTag = topicTag.split(":");
                consumer.subscribe(topicAndTag[0], topicAndTag[1]);
            }
            consumer.start();
        } catch (MQClientException e) {
            log.warn("消息消费启动失败");
            throw new RuntimeException(e);
        }
        return consumer;
    }

    @Bean
    public MessageListenerConcurrently messageListenerConcurrently() {
        return new ConsumerListenerConcurrently(mqHandlerMap);
    }

}
