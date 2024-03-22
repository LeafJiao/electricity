package com.electricity.model.dto;

import lombok.Data;

/**
 * @Title: BlogUserDto
 * @Author JiaoWei
 * @Package com.myblog.dto
 * @Date 2024/3/9 16:56
 * @description: 用户接收前端信息数据
 */
@Data
public class BlogUserDto {
    private String phone;

    private String password;

    private String avatar;

    private String username;
}
