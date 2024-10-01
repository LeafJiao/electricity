package com.electricity.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.electricity.constant.BaseConstant;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 
 * @TableName electricity
 */
@TableName(value ="electricity")
@Data
public class Electricity implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    private Double lowest;

    /**
     * 
     */
    private Double middle;

    /**
     * 
     */
    private Double highest;

    /**
     * 
     */
    @JsonFormat(pattern = BaseConstant.TIME_FORMAT)
    private Date createTime;

    /**
     * 
     */
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}