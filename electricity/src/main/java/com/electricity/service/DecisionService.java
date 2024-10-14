package com.electricity.service;

import com.electricity.model.entity.Decision;
import com.baomidou.mybatisplus.extension.service.IService;
import com.electricity.model.vo.DecisionVo;

import java.util.List;

/**
* @author JiaoWei
* @description 套餐
* @createDate 2024-09-30 21:48:07
*/
public interface DecisionService extends IService<Decision> {
    List<DecisionVo> getDecision();
}
