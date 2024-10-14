package com.electricity.service;

import com.electricity.model.entity.UserOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import com.electricity.model.vo.UserOrderVo;

import java.util.List;

/**
* @author JiaoWei
* @description 交易额
* @createDate 2024-09-30 21:50:29
*/
public interface UserOrderService extends IService<UserOrder> {

    List<UserOrderVo> getUserOrder();

}
