package com.electricity.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Title: TokenVo
 * @Author JiaoWei
 * @Package com.myblog.vo
 * @Date 2024/3/17 11:19
 * @description: 返回的token
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenVo {

    private String token;

    private String refreshToken;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date expireTime;

}
