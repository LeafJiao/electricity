package com.electricity.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName user_order
 */
@TableName(value ="user_order")
@Data
public class UserOrder implements Serializable {
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

    /**
     * 创建时间
     */
    private Date creatTime;

    /**
     * 更改时间
     */
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}