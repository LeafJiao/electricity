package com.electricity.controller;

import com.electricity.model.response.Result;
import com.electricity.service.TracticsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jiaowei
 * @since 2024/10/14 23:20
 */
@RestController
@RequestMapping("/tractics")
@Tag(name = "交易策略模块")
@Slf4j
public class TracticsController {

    @Resource
    private TracticsService tracticsService;

    @Operation(summary = "获取交易策略")
    @GetMapping("/get")
    public Result getTractics() {
        try {
            return Result.success(tracticsService.getTractics());
        } catch (Exception e) {
            log.warn("获取交易策略错误：{}", e);
            return Result.error(String.valueOf(e));
        }
    }
}
