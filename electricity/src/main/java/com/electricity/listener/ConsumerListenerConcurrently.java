package com.electricity.listener;

import cn.hutool.core.util.StrUtil;
import com.electricity.annotation.MQHandlerActualizer;
import com.electricity.handler.infrast.MQHandler;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 并发消息监听者
 *
 * @author jiaowei
 * @since 2024/11/9 17:06
 */
@Slf4j
public class ConsumerListenerConcurrently implements MessageListenerConcurrently {

    @Resource
    private Map<String, MQHandler> mqHandlerMap;

    public ConsumerListenerConcurrently(Map<String, MQHandler> mqHandlerMap) {
        this.mqHandlerMap = mqHandlerMap;
    }

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
        if (CollectionUtils.isEmpty(list)) {
            log.warn("接收的消息为空不处理");
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }

        MessageExt messageExt = list.get(0);
        //TODO 判断rocketmq是否重复消费

        // 获取消息的重试次数
        int reconsumeTimes = messageExt.getReconsumeTimes();
        if (reconsumeTimes > 2) {
            log.warn("消息重试已经超过两次: topic: {}, tag: {}, msg: {}", messageExt.getTopic(), messageExt.getTags(), new String(messageExt.getBody()));
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }

        String topic = messageExt.getTopic();
        String tags = messageExt.getTags();
        log.warn("监听器接收的消息主题为: " + topic + "; tags为: " + tags);

        MQHandler mqHandler = null;
        // 获取消息中的topic和tag，根据这两个值来判断消息类型，然后调用对应的handler进行处理
        for (Map.Entry<String, MQHandler> entry : mqHandlerMap.entrySet()) {
            // 获取注解中的信息
            MQHandlerActualizer mqHandlerActualizer = entry.getValue().getClass().getAnnotation(MQHandlerActualizer.class);
            if (null == mqHandlerActualizer) {
                // 非消息处理类
                continue;
            }

            String annotationTopic = mqHandlerActualizer.topic();
            if (!StrUtil.equals(annotationTopic, topic)) {
                // 非该主题处理类
                continue;
            }

            String[] annotationTags = mqHandlerActualizer.tags();
            if (StrUtil.equals("*", annotationTags[0])) {
                // 获取该实例
                mqHandler = entry.getValue();
                break;
            }

            boolean isContains = Arrays.asList(annotationTags).contains(tags);
            if (isContains) {
                // 注解中包含tag则获取该实例
                mqHandler = entry.getValue();
                break;
            }
        }

        if (null == mqHandler) {
            return ConsumeConcurrentlyStatus.RECONSUME_LATER;
        }

        ConsumeConcurrentlyStatus status = mqHandler.handler(tags, messageExt);
        // 如果没有消费成功，则会重新去消费知道消费成功
        return status;
    }
}
