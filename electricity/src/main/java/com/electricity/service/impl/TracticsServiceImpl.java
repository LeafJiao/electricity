package com.electricity.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.electricity.converter.TracticsConverter;
import com.electricity.model.dto.TracticsElecDto;
import com.electricity.model.entity.Tractics;
import com.electricity.model.exception.GlobalException;
import com.electricity.model.vo.TracticsVo;
import com.electricity.service.TracticsService;
import com.electricity.mapper.TracticsMapper;
import com.electricity.utils.SnowflakeUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author JiaoWei
* @description 针对表【tractics】的数据库操作Service实现
* @createDate 2024-09-30 21:50:29
*/
@Service
@Slf4j
public class TracticsServiceImpl extends ServiceImpl<TracticsMapper, Tractics>
    implements TracticsService{

    @Resource
    private TracticsMapper tracticsMapper;

    @Resource
    private TracticsConverter tracticsConverter;

    @Qualifier("defaultMQ")
    @Resource
    private DefaultMQProducer producer;

    @Override
    public List<TracticsVo> getTractics() {
        List<Tractics> tractics = tracticsMapper.selectList(null);
        return tracticsConverter.convert(tractics);
    }

    @Override
    public TracticsElecDto setTracticsElec(TracticsElecDto tracticsElecDto) {

        tracticsElecDto.setId(SnowflakeUtils.getInstance().id());
        Message message = new Message("topic1", "tractics", JSON.toJSONString(tracticsElecDto).getBytes());
        try {
            SendResult send = producer.send(message);
            log.warn("发送状态：{}, 消息ID: {}, 队列: {}", send.getSendStatus(), send.getMsgId(), send.getMessageQueue().getQueueId());
        } catch (Exception e) {
            throw new GlobalException("消息发送失败");
        }

        return tracticsElecDto;
    }
}




