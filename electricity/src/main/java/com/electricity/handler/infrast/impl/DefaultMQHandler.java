package com.electricity.handler.infrast.impl;

import com.electricity.handler.infrast.MQHandler;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Service;

/**
 * @author jiaowei
 * @since 2024/11/11 11:01
 */
@Service
public class DefaultMQHandler implements MQHandler {
    @Override
    public ConsumeConcurrentlyStatus handler(String tag, MessageExt messageExt) {
        return null;
    }
}
