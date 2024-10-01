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
import com.electricity.mapper.ElectricityMapper;
import com.electricity.model.entity.Electricity;
import com.electricity.model.entity.NowElectricity;
import com.electricity.model.vo.ElectricityVo;
import com.electricity.model.vo.NowElectricityVo;
import com.electricity.service.NowElectricityService;
import com.electricity.mapper.NowElectricityMapper;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
* @author JiaoWei
* @createDate 2024-09-30 21:50:29
 *
 * 电价信息
*/
@Service
public class NowElectricityServiceImpl extends ServiceImpl<NowElectricityMapper, NowElectricity>
    implements NowElectricityService{

    @Resource
    private ElectricityMapper electricityMapper;

    @Resource
    private NowElectricityMapper nowElectricityMapper;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

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
    public List<NowElectricityVo> getNow() {
        LocalDateTime now = LocalDateTime.now();
        // 将当前时间格式化为 每 15 分钟一次
        long x = (long) now.getMinute() % 15;
        now = now.plusMinutes(-x);
        now = now.plusSeconds(-(long) now.getSecond());
        now = now.plusNanos(-(long) now.getNano());
        // 生成预测电价信息，生成后7个15分钟电价
        createNow(now);
        // 删除前一天的所有电价
        String beforeNow = DateUtil.format(now.plusDays(-1L), BaseConstant.TIME_FORMAT);
        nowElectricityMapper.deleteNow(beforeNow);
        // 修改当前时间之前的电价为历史电价
        String format = DateUtil.format(now.plusSeconds(-1L), BaseConstant.TIME_FORMAT);
        nowElectricityMapper.updateDescript(format);
        // 获取前7个15分钟和后7个15分钟
        List<NowElectricity> list =  nowElectricityMapper.getNow(DateUtil.format(now.plusMinutes(-7 * 15L), "yyyy-MM-dd HH:mm:ss"),
                DateUtil.format(now.plusMinutes(7 * 15L), BaseConstant.TIME_FORMAT));
        return BeanUtil.copyToList(list, NowElectricityVo.class);
    }

    /**
     * 生成一个电价信息
     * @param dateTime 生成的时间
     * @return 电价信息
     */
    public NowElectricity randomNow(LocalDateTime dateTime) {
        double randomDouble = RandomUtil.randomDouble(0.7, 1.3);
        BigDecimal round = NumberUtil.round(randomDouble, 1);
        double value = round.doubleValue();
        NowElectricity nowElectricity = new NowElectricity();
        nowElectricity.setPrice(value);
        nowElectricity.setDescript(1);
        // TODO
        nowElectricity.setUpdateTime(new Date());
        return nowElectricity;
    }

    /**
     * 生成后7个15分钟预测电价
     * @param now 当前时间
     */
    public void createNow(LocalDateTime now) {
        ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
        String maxTime = opsForValue.get(ElectricityEnum.MAX_TIME.getCode());
        if (StrUtil.isEmpty(maxTime)) {
            maxTime = String.valueOf(now);
        }
        LocalDateTime maxLocalDateTime = LocalDateTime.parse(maxTime);
        for (int i = 0; i < ElectricityConstant.MAX_NUMBER; i++) {
            if (now.isAfter(maxLocalDateTime)) {
                nowElectricityMapper.insertNow(randomNow(now));
                opsForValue.set(ElectricityEnum.MAX_TIME.getCode(), String.valueOf(now));
            }
            if (now.isBefore(maxLocalDateTime)) {
                int row = nowElectricityMapper.updateNow(randomNow(now));
                if (row == 0) {
                    nowElectricityMapper.insertNow(randomNow(now));
                }
            }
            now = now.plusMinutes(15L);
        }
    }
}




