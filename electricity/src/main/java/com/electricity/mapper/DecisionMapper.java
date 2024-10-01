package com.electricity.mapper;

import com.electricity.model.entity.Decision;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author JiaoWei
* @description 针对表【decision】的数据库操作Mapper
* @createDate 2024-09-30 21:48:07
* @Entity com.electricity.model.entity.Decision
*/
@Mapper
public interface DecisionMapper extends BaseMapper<Decision> {

}




