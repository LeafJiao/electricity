package com.electricity.controller;

import com.electricity.model.response.Result;
import com.electricity.service.UserOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jiaowei
 * @since 2024/10/14 23:02
 */
@RestController
@RequestMapping("/userOrder")
@Tag(name = "交易额模块")
@Slf4j
public class UserOrderController {
    @Resource
    private UserOrderService userOrderService;

    @Operation(summary = "获取交易信息")
    @GetMapping("/get")
    public Result getOrder() {
        try {
            return Result.success(userOrderService.getUserOrder());
        } catch (Exception e) {
            log.warn("获取交易信息错误：{}", e);
            return Result.error(String.valueOf(e));
        }
    }

}
