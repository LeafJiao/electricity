package com.electricity.model.response;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author jiaowei
 * @since 2024/11/11 15:36
 */
@Data
@Accessors(chain = true)
public class BaseResponse implements Serializable {

    private static final long serialVersionUID = -9145885550533756012L;

    /**
     * 业务状态码
     * 200 成功
     * 400 失败
     */
    private Integer code = 200;

    /**
     * 提示信息
     */
    private String message = "操作成功";

    /**
     * 快速返回操作成功响应结果
     * @return
     */
    public static BaseResponse instance() {
        return new BaseResponse();
    }
}
