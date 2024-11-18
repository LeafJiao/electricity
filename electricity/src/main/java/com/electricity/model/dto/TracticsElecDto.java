package com.electricity.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 交易策略的数据传输对象
 *
 * @author jiaowei
 * @since 2024/11/9 16:18
 */
@Data
@Accessors(chain = true)
public class TracticsElecDto {

    private Long id;

    private String electricity;

    private String price;
}
