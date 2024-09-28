package com.electricity.service;


import com.electricity.model.dto.UserDto;
import com.electricity.model.vo.TokenVo;

/**
 * @Title: BlogUserService
 * @Author JiaoWei
 * @Package com.myblog.service
 * @Date 2024/3/7 17:31
 * @description: 用户服务接口
 */
public interface UserService {
    void register(String username, String password);

    TokenVo login(UserDto userDto);
}
