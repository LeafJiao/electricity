package com.electricity.exception;

import com.electricity.model.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Title: GlobalExceptionHandler
 * @Author JiaoWei
 * @Package com.myblog.exception
 * @Date 2024/3/9 17:12
 * @description: 全局异常处理器
 */

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(GlobalException.class)
    public Result handleException(Exception e) {
//        e.printStackTrace();
        log.error("错误信息： {}", StringUtils.hasLength(e.getMessage())? e.getMessage() : "操作失败");
        return Result.error(StringUtils.hasLength(e.getMessage())? e.getMessage() : "操作失败");
    }

}
