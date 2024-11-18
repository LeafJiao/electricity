package com.electricity.handler;

import com.alibaba.fastjson2.JSON;
import com.electricity.annotation.MQHandlerActualizer;
import com.electricity.constant.ElectricityEnum;
import com.electricity.handler.infrast.MQHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.Map;

/**
 * @author jiaowei
 * @since 2024/11/11 21:15
 */
@MQHandlerActualizer(topic = "topic1", tags = "tractics")
@Slf4j
public class TracticsMQHandler implements MQHandler {
    @Override
    public ConsumeConcurrentlyStatus handler(String tag, MessageExt messageExt) {
        String message = new String(messageExt.getBody());
        log.warn("接收消息: {}", message);
        Map tracticdElecMap = (Map) JSON.parse(message);
        Integer electricity = Integer.parseInt(tracticdElecMap.get(ElectricityEnum.DEFAULT_ELECTRICITY.getCode()).toString());
        Integer price = Integer.parseInt(tracticdElecMap.get(ElectricityEnum.DEFAULT_PRICE.getCode()).toString());
        log.warn("electricity: {}, price: {}", electricity, price);

        // TODO 生成交易策略
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
