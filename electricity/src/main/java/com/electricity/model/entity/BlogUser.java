package com.electricity.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;


/**
 * @Title: BlogUser
 * @Author JiaoWei
 * @Package com.myblog.entity
 * @Date 2024/3/6 19:34
 * @description: 用户信息
 */

@Data
@Accessors(chain = true)
public class BlogUser {
    private Integer id;

    private String phone;

    private String password;

    private String username;

    private String avatar;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
