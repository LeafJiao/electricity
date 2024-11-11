package com.electricity.model.exception;

import lombok.AllArgsConstructor;

/**
 * @Title: GlobalException
 * @Author JiaoWei
 * @Date 2024/3/16 22:22
 * @description: 全局异常
 */
@AllArgsConstructor
public class GlobalException extends RuntimeException {
    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
