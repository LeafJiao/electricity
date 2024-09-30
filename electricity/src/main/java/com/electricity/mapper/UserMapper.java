package com.electricity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.electricity.model.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {


    /**
     * 根据phone查询用户
     * @param phone
     * @return
     */
    User getUserByPhone(String phone);

    /**
     * 注册
     * @return
     */
    int registerUser(User user);
}
