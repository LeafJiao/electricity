package com.electricity.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.electricity.model.entity.Electricity;
import com.electricity.service.ElectricityService;
import com.electricity.mapper.ElectricityMapper;
import org.springframework.stereotype.Service;

/**
* @author JiaoWei
* @description 针对表【electricity】的数据库操作Service实现
* @createDate 2024-09-30 21:50:29
*/
@Service
public class ElectricityServiceImpl extends ServiceImpl<ElectricityMapper, Electricity>
    implements ElectricityService{

}




