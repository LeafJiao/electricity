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
public class DecisionVo implements Serializable {
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
}