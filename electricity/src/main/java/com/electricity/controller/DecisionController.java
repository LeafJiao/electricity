package com.electricity.controller;

import com.electricity.model.vo.Result;
import com.electricity.service.DecisionService;
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
@RequestMapping("/decision")
@Tag(name = "套餐模块")
@Slf4j
public class DecisionController {

    @Resource
    private DecisionService decisionService;

    @Operation(summary = "获取套餐模块")
    @GetMapping("/get")
    public Result getDecision() {
        try {
            return Result.success(decisionService.getDecision());
        } catch (Exception e) {
            log.warn("获取套餐错误：{}", e);
            return Result.error(String.valueOf(e));
        }
    }
}
