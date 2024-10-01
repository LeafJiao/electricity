package com.electricity.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @Title: NowElectricity
 * @Author JiaoWei
 * @Package com.electricity.model.entity
 * @Date 2024/5/4 16:59
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Schema(title = "实时电价")
public class NowElectricityVo {
    @Schema(description = "实时电价id")
    private Long id;
    @Schema(description = "实时电价")
    private Double price;
    @Schema(description = "0-历史电价，1-预测电价")
    private Integer descript;
    @Schema(description = "电价时间")
    @JsonFormat(pattern = "HH:mm", timezone = "GMT+8")
    private LocalDateTime updateTime;
}
