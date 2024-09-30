package com.electricity.controller;
import java.util.Date;

import com.electricity.converter.UserConverter;
import com.electricity.model.entity.User;
import com.electricity.model.vo.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jiaowei
 * @since 2024/9/30 16:26
 */
@RestController
@Slf4j
@RequestMapping("/test")
@Tag(name = "测试模块")
public class TestController {

    @Operation(summary = "测试用户类转换器")
    @GetMapping("/user")
    public Result testUserConverter() {
        User user = new User();
        user.setId(0L);
        user.setPhone("13214");
        user.setPassword("1321");
        user.setUsername("21312");
        user.setAvatar("13123");
        user.setCreate_time(new Date());
        user.setUpdate_time(new Date());
        user.setEmail("dasdas");
        user.setIs_delete(0);

        return Result.success(UserConverter.INSTANCE.convert(user));
    }
}
