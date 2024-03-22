package com.electricity.service.impl;


import com.electricity.model.dto.BlogUserDto;
import com.electricity.model.dto.Constants;
import com.electricity.model.entity.BlogUser;
import com.electricity.exception.GlobalException;
import com.electricity.mapper.BlogUserMapper;
import com.electricity.service.IBlogUserService;
import com.electricity.utils.JwtUtil;
import com.electricity.utils.Md5Util;
import com.electricity.model.vo.TokenVo;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Title: BlogUserServiceImpl
 * @Author JiaoWei
 * @Package com.myblog.service.impl
 * @Date 2024/3/9 16:12
 * @description: 用户服务实现
 */
@Service
public class BlogUserServiceImpl implements IBlogUserService {

    @Resource
    private BlogUserMapper blogUserMapper;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void register(String phone, String password) {
        BlogUser user = blogUserMapper.getUserByUsername(phone);
        if (user != null) {
            throw new GlobalException("该用户已存在");
        }
        BlogUser blogUser = new BlogUser();
        blogUser.setPhone(phone)
                .setPassword(Md5Util.getMD5String(password))
                .setUsername(Constants.username)
                .setAvatar(Constants.avatar);
        int i = blogUserMapper.registerUser(blogUser);
        if (i != 1) {
            throw new GlobalException("注册失败");
        }
    }

    @Override
    public TokenVo login(BlogUserDto blogUserDto) {
        BlogUser user = blogUserMapper.getUserByUsername(blogUserDto.getPhone());
        if (user == null) {
            throw new GlobalException("账号不存在");
        }
        if (blogUserDto.getPassword() == null || "".equals(blogUserDto.getPassword())) {
            throw new GlobalException("密码为空");
        }
        if (Md5Util.getMD5String(blogUserDto.getPassword()).equals(user.getPassword())) {
            // 生成token
            String token = JwtUtil.createJWT(String.valueOf(user.getId()));
            // 将token存入redis
            ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
            opsForValue.set(token, token, 1, TimeUnit.DAYS);
            String refreshToken = JwtUtil.refreshToken(token);
            Date expireTime = new Date(System.currentTimeMillis() + JwtUtil.JWT_TTL);
            // 返回token
            return new TokenVo(token, refreshToken, expireTime);
        }else {
            throw new GlobalException("密码错误");
        }
    }
}
