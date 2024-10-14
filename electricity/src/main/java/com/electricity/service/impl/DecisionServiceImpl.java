package com.electricity.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.electricity.converter.DecisionConverter;
import com.electricity.model.entity.Decision;
import com.electricity.model.vo.DecisionVo;
import com.electricity.service.DecisionService;
import com.electricity.mapper.DecisionMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author JiaoWei
* @description 针对表【decision】的数据库操作Service实现
* @createDate 2024-09-30 21:48:07
*/
@Service
public class DecisionServiceImpl extends ServiceImpl<DecisionMapper, Decision>
    implements DecisionService {

    @Resource
    private DecisionMapper decisionMapper;

    @Resource
    private DecisionConverter decisionConverter;

    @Override
    public List<DecisionVo> getDecision() {
        List<Decision> decisions = decisionMapper.selectList(null);

        return decisionConverter.convert(decisions);
    }
}




