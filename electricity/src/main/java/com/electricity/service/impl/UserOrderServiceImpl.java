package com.electricity.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.electricity.model.entity.UserOrder;
import com.electricity.service.UserOrderService;
import com.electricity.mapper.UserOrderMapper;
import org.springframework.stereotype.Service;

/**
* @author JiaoWei
* @description 针对表【user_order】的数据库操作Service实现
* @createDate 2024-09-30 21:50:29
*/
@Service
public class UserOrderServiceImpl extends ServiceImpl<UserOrderMapper, UserOrder>
    implements UserOrderService{

}




