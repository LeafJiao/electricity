package com.electricity.service;

import com.electricity.model.entity.Tractics;
import com.baomidou.mybatisplus.extension.service.IService;
import com.electricity.model.vo.TracticsVo;

import java.util.List;

/**
* @author JiaoWei
* @description 交易策略
* @createDate 2024-09-30 21:50:29
*/
public interface TracticsService extends IService<Tractics> {
    List<TracticsVo> getTractics();
}
