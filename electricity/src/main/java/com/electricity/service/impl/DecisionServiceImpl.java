package com.electricity.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.electricity.model.entity.Decision;
import com.electricity.service.DecisionService;
import com.electricity.mapper.DecisionMapper;
import org.springframework.stereotype.Service;

/**
* @author JiaoWei
* @description 针对表【decision】的数据库操作Service实现
* @createDate 2024-09-30 21:48:07
*/
@Service
public class DecisionServiceImpl extends ServiceImpl<DecisionMapper, Decision>
    implements DecisionService {

}




