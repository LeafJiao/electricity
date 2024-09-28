package com.electricity.mapper;

import com.electricity.model.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {


    /**
     * 根据phone查询用户
     * @param phone
     * @return
     */
    User getUserByUsername(String phone);

    /**
     * 注册
     * @return
     */
    int registerUser(User user);
}
