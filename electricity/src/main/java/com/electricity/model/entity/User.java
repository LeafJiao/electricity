package com.electricity.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "用户信息")
public class User {
    @Parameter(name = "用户id")
    private Integer id;

    private String phone;

    private String password;

    private String username;

    private String avatar;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
