package com.electricity.service;

import com.electricity.model.entity.NowElectricity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.electricity.model.vo.ElectricityVo;
import com.electricity.model.vo.NowElectricityVo;

import java.util.List;

/**
* @author JiaoWei
* @description 针对表【now_electricity】的数据库操作Service
* @createDate 2024-09-30 21:50:29
*/
public interface NowElectricityService extends IService<NowElectricity> {

    /**
     * 获取实时电价
     * @return
     */
    List<NowElectricityVo> getNow();
}
