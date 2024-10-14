package com.electricity.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserOrderVo implements Serializable {
    /**
     * 订单id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 企业id
     */
    private Long userId;

    /**
     * 订单日期
     */
    private Date date;

    /**
     * 户号
     */
    private String account;

    /**
     * 费用
     */
    private Double expenses;

    /**
     * 电量
     */
    private Double electricity;

    /**
     * 出售价格
     */
    private Double sellPrice;

    /**
     * 支付方式
     */
    private String payStyle;
}