package com.electricity.service;


import com.electricity.model.dto.BlogUserDto;
import com.electricity.model.vo.TokenVo;

/**
 * @Title: BlogUserService
 * @Author JiaoWei
 * @Package com.myblog.service
 * @Date 2024/3/7 17:31
 * @description: 用户服务接口
 */
public interface IBlogUserService{
    void register(String username, String password);

    TokenVo login(BlogUserDto blogUserDto);
}
