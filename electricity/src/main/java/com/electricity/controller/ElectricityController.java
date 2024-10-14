package com.electricity.controller;

import com.electricity.model.vo.ElectricityVo;
import com.electricity.model.vo.Result;
import com.electricity.service.ElectricityService;
import com.electricity.service.NowElectricityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author jiaowei
 * @since 2024/10/14 22:38
 */
@RestController
@RequestMapping("/electricity")
@Slf4j
@Tag(name = "电价模块")
public class ElectricityController {

    @Resource
    private ElectricityService electricityService;

    @Resource
    private NowElectricityService nowElectricityService;

    @Operation(summary = "生成长期电价")
    @GetMapping("/getElec")
    public Result getElec() {
        try {
            List<ElectricityVo> elec = electricityService.getElec();
            return Result.success(elec);
        } catch (Exception e) {
            log.warn("Exception: {}", e);
            return Result.error(String.valueOf(e));
        }
    }

}
