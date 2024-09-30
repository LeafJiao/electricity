package com.electricity.controller;

import com.electricity.model.dto.UserDto;
import com.electricity.service.UserService;
import com.electricity.utils.ThreadLocalUtil;
import com.electricity.model.vo.Result;
import com.electricity.model.vo.TokenVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

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
        userService.register(user.get("phone"), user.get("password"));
        return Result.success();
    }

    @Operation(summary = "用户登录")
    @PostMapping("/login")
    public Result login(@RequestBody UserDto userDto) {
        TokenVo tokenVo = userService.login(userDto);
        return Result.success(tokenVo);
    }

    @GetMapping("/userInfo")
    public Result getUserById() {
        String userId = ThreadLocalUtil.get();
        return Result.success(userId);
    }

}
