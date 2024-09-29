package com.electricity.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.electricity.model.dto.UserDto;
import com.electricity.model.entity.User;
import com.electricity.model.vo.TokenVo;

/**
 * @Title: BlogUserService
 * @Author JiaoWei
 * @Package com.myblog.service
 * @Date 2024/3/7 17:31
 * @description: 用户服务接口
 */
public interface UserService extends IService<User> {
    void register(String username, String password);

    TokenVo login(UserDto userDto);
}
