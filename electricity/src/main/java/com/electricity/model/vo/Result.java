package com.electricity.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Title: Result
 * @Author JiaoWei
 * @Package com.my-blog.utils
 * @Date 2024/3/6 23:09
 * @description: 返回浏览器结果
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Result<T> {
    /**
     * 业务状态码
     * 200 成功
     * 400 失败
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String message;
    /**
     * 响应数据
     */
    private Object data;

    /**
     * 快速返回操作成功响应结果（带想用数据）
     * @param data
     * @return
     * @param <E>
     */
    public static <E> Result<E> success(E data) {
        return new Result<>(200, "操作成功", data);
    }

    /**
     * 快速返回操作成功响应结果
     * @return
     */
    public static Result success() {
        return new Result(200, "操作成功", null);
    }

    /**
     * 返回错误结果
     * @param message
     * @return
     */
    public static Result error(String message) {
        return new Result(400, message, null);
    }
}
