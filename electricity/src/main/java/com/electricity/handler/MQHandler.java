package com.electricity.handler;

import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * 消息处理类
 *
 * @author jiaowei
 * @since 2024/11/11 10:58
 */
public interface MQHandler {
    ConsumeConcurrentlyStatus handler(String tag, MessageExt messageExt);
}
