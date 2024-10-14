package com.electricity.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.electricity.constant.BaseConstant;
import com.electricity.constant.ElectricityConstant;
import com.electricity.constant.ElectricityEnum;
import com.electricity.model.entity.Electricity;
import com.electricity.model.vo.ElectricityVo;
import com.electricity.service.ElectricityService;
import com.electricity.mapper.ElectricityMapper;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
* @author JiaoWei
* @description 针对表【electricity】的数据库操作Service实现
* @createDate 2024-09-30 21:50:29
*/
@Service
public class ElectricityServiceImpl extends ServiceImpl<ElectricityMapper, Electricity> implements ElectricityService{

    @Resource
    private ElectricityMapper electricityMapper;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public Electricity randomElec(LocalDateTime now) {
        double lowest = RandomUtil.randomDouble(0.7, 1.4);
        double middle = RandomUtil.randomDouble(lowest, 1.4);
        double highest = RandomUtil.randomDouble(middle, 1.4);
        lowest =  NumberUtil.round(lowest,1).doubleValue();
        middle =  NumberUtil.round(middle,1).doubleValue();
        highest =  NumberUtil.round(highest,1).doubleValue();
        Electricity electricity = new Electricity();
        electricity.setLowest(lowest);
        electricity.setMiddle(middle);
        electricity.setHighest(highest);
        electricity.setUpdateTime(new Date());
        return electricity;
    }

    public void creatElec(LocalDateTime now) {
        ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
        String maxDay = opsForValue.get(ElectricityEnum.MAX_DAY.getCode());
        if (StrUtil.isEmpty(maxDay)) {
            maxDay = String.valueOf(now);
        }
        LocalDateTime maxDayLocal = LocalDateTime.parse(maxDay);
        for (int i = 0; i < ElectricityConstant.MAX_DAYS; i++) {
            Electricity electricity = randomElec(now);
            if (now.isAfter(maxDayLocal)) {
                electricityMapper.insertElec(electricity);
                opsForValue.set(ElectricityEnum.MAX_DAY.getCode(), String.valueOf(now));
            }
            if (now.isBefore(maxDayLocal)) {
                int row = electricityMapper.updateElec(electricity);
                if (row == 0) {
                    electricityMapper.insertElec(electricity);
                }
            }
            now = now.plusDays(1L);
        }
    }

    @Override
    public List<ElectricityVo> getElec() {
        LocalDateTime now = LocalDateTime.now();
        now = now.plusNanos(-(long) now.getNano());
        now = now.plusSeconds(-(long) now.getSecond());
        now = now.plusMinutes(-(long) now.getMinute());
        now = now.plusHours(-(long) now.getHour());
        LocalDateTime beforeDateTime = now.plusDays(-15L);
        String format = DateUtil.format(now, BaseConstant.TIME_FORMAT);
        String format1 = DateUtil.format(beforeDateTime, BaseConstant.TIME_FORMAT);
        // 生成未来 15 天的电价
        creatElec(now);
        // 获取未来15天的电价
        List<Electricity> elec = electricityMapper.getElec(format);
        // 删除15天前的电价信息
        electricityMapper.deleteBefore(format1);
        return BeanUtil.copyToList(elec, ElectricityVo.class);
    }
}




