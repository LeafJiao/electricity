package com.electricity.controller;

import com.electricity.model.dto.BlogUserDto;
import com.electricity.service.IBlogUserService;
import com.electricity.utils.ThreadLocalUtil;
import com.electricity.model.vo.Result;
import com.electricity.model.vo.TokenVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * @Title: BlogUserController
 * @Author JiaoWei
 * @Package com.myblog.controller
 * @Date 2024/3/9 16:14
 * @description: 用户控制层
 */
@RestController
@RequestMapping("/user")
@Tag(name = "用户模块")
public class BlogUserController {

    @Resource
    private IBlogUserService blogUserService;

    /**
     * 注册
     * @return
     */

    @Operation(summary = "用户注册")
    @PostMapping("/register")
    public Result register(@RequestBody Map<String, String> user) {
        blogUserService.register(user.get("phone"), user.get("password"));
        return Result.success();
    }

    @PostMapping("/login")
    public Result login(@RequestBody BlogUserDto blogUserDto) {
        TokenVo tokenVo = blogUserService.login(blogUserDto);
        return Result.success(tokenVo);
    }

    @GetMapping("/userInfo")
    public Result getUserById() {
        String userId = ThreadLocalUtil.get();
        return Result.success(userId);
    }

}
