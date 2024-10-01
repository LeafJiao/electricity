package com.electricity.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @Title: ElectricityVo
 * @Author JiaoWei
 * @Package com.electricity.model.vo
 * @Date 2024/5/3 17:09
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ElectricityVo {
    private Long id;

    private Double lowest;

    private Double middle;

    private Double highest;

    @JsonFormat(pattern = "MM月dd日", timezone = "GMT+8")
    private LocalDateTime updateTime;
}
