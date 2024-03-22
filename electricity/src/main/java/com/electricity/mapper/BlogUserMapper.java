package com.electricity.mapper;

import com.electricity.model.entity.BlogUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BlogUserMapper {


    /**
     * 根据phone查询用户
     * @param phone
     * @return
     */
    BlogUser getUserByUsername(String phone);

    /**
     * 注册
     * @return
     */
    int registerUser(BlogUser blogUser);
}
