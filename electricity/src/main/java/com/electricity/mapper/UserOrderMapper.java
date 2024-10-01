package com.electricity.mapper;

import com.electricity.model.entity.UserOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author JiaoWei
* @description 针对表【user_order】的数据库操作Mapper
* @createDate 2024-09-30 21:50:29
* @Entity com.electricity.model.entity.UserOrder
*/
@Mapper
public interface UserOrderMapper extends BaseMapper<UserOrder> {

}




