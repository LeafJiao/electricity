package com.electricity.controller;

import com.electricity.model.dto.UserDto;
import com.electricity.model.entity.Electricity;
import com.electricity.service.UserService;
import com.electricity.utils.ThreadLocalUtil;
import com.electricity.model.vo.Result;
import com.electricity.model.vo.TokenVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;


/**
 * @Title: UserController
 * @Author JiaoWei
 * @Date 2024/3/9 16:14
 * @description: 用户控制层
 */

@RestController
@RequestMapping("/user")
@Tag(name = "用户模块")
@Slf4j
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 注册
     * @return
     */
    @Operation(summary = "用户注册")
    @PostMapping("/register")
    public Result register(@RequestBody Map<String, String> user) {
        log.warn("用户注册信息入参：{}", user);
        userService.register(user.get("phone"), user.get("password"));
        return Result.success();
    }

    @Operation(summary = "用户登录")
    @PostMapping("/login")
    public Result login(@RequestBody UserDto userDto) {
        log.warn("UserDto:{}", userDto);
        TokenVo tokenVo = userService.login(userDto);
        return Result.success(tokenVo);
    }

    @GetMapping("/userInfo")
    public Result getUserById() {
        String userId = ThreadLocalUtil.get();
        return Result.success(userId);
    }

    @Operation(summary = "测试时间")
    @GetMapping("/time")
    public Result time() {
        Electricity electricity = new Electricity();
        electricity.setId(0L);
        electricity.setLowest(0.0D);
        electricity.setMiddle(0.0D);
        electricity.setHighest(0.0D);
        electricity.setCreateTime(new Date());
        electricity.setUpdateTime(new Date());
        return Result.success(electricity);
    }

}
