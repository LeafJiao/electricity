package com.electricity.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Title: BlogUserVo
 * @Author JiaoWei
 * @Package com.myblog.vo
 * @Date 2024/3/9 16:31
 * @description: 前端展示用户信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVo {
    private String phone;
    private String username;
    private String avatar;
}
