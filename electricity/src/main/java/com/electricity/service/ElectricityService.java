package com.electricity.service;

import com.electricity.model.entity.Electricity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.electricity.model.vo.ElectricityVo;

import java.util.List;

/**
* @author JiaoWei
* @description 针对表【electricity】的数据库操作Service
* @createDate 2024-09-30 21:50:29
*/
public interface ElectricityService extends IService<Electricity> {
    /**
     * 获取电价
     * @return
     */
    List<ElectricityVo> getElec();
}
