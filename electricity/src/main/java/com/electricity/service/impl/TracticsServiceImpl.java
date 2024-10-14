package com.electricity.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.electricity.converter.TracticsConverter;
import com.electricity.model.entity.Tractics;
import com.electricity.model.vo.TracticsVo;
import com.electricity.service.TracticsService;
import com.electricity.mapper.TracticsMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author JiaoWei
* @description 针对表【tractics】的数据库操作Service实现
* @createDate 2024-09-30 21:50:29
*/
@Service
public class TracticsServiceImpl extends ServiceImpl<TracticsMapper, Tractics>
    implements TracticsService{

    @Resource
    private TracticsMapper tracticsMapper;

    @Resource
    private TracticsConverter tracticsConverter;

    @Override
    public List<TracticsVo> getTractics() {
        List<Tractics> tractics = tracticsMapper.selectList(null);
        return tracticsConverter.convert(tractics);
    }
}




