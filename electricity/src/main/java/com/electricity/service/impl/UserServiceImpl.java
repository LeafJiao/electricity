package com.electricity.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.electricity.model.dto.UserDto;
import com.electricity.constant.UserConstants;
import com.electricity.model.entity.User;
import com.electricity.model.exception.GlobalException;
import com.electricity.mapper.UserMapper;
import com.electricity.service.UserService;
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
 * @Author JiaoWei
 * @Date 2024/3/9 16:12
 * @description: 用户服务实现
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void register(String phone, String password) {
        User user = userMapper.getUserByPhone(phone);
        if (user != null) {
            throw new GlobalException("该用户已存在");
        }
        User newUser = new User();
        newUser.setPhone(phone);
        newUser.setPassword(Md5Util.getMD5String(password));
        newUser.setUsername(UserConstants.username);
        newUser.setAvatar(UserConstants.avatar);
        newUser.setCreateTime(new Date());
        newUser.setUpdateTime(new Date());

        int i = userMapper.registerUser(newUser);
        if (i != 1) {
            throw new GlobalException("注册失败");
        }
    }

    @Override
    public TokenVo login(UserDto userDto) {
        User user = userMapper.getUserByPhone(userDto.getPhone());
        if (user == null) {
            throw new GlobalException("账号不存在");
        }
        if (userDto.getPassword() == null || "".equals(userDto.getPassword())) {
            throw new GlobalException("密码为空");
        }
        if (Md5Util.getMD5String(userDto.getPassword()).equals(user.getPassword())) {
            // 生成token
            String token = JwtUtil.createJWT(String.valueOf(user.getId()));
            // 将token存入redis
            ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
            opsForValue.set(token, token, 1, TimeUnit.HOURS);
            String refreshToken = JwtUtil.refreshToken(token);
            Date expireTime = new Date(System.currentTimeMillis() + JwtUtil.JWT_TTL);
            // 返回token
            return new TokenVo(token, refreshToken, expireTime);
        }else {
            throw new GlobalException("密码错误");
        }
    }
}
