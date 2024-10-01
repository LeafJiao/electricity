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
 * @TableName decision
 */
@TableName(value ="decision")
@Data
public class Decision implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    private Long user_id;

    /**
     * 
     */
    private Double electricity;

    /**
     * 售出价格
     */
    private Double price;

    /**
     * 1-单一电价，2-迎峰型，3-逆峰型，4-其他
     */
    private Integer type;

    /**
     * 发布日期
     */
    private Date date;

    /**
     * 1-月结，2-周结，3-日结
     */
    private Integer priceType;

    /**
     * 购买人数
     */
    private Integer people;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}