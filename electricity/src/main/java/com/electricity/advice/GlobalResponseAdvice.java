package com.electricity.advice;

import com.electricity.annotation.ResponseResult;
import com.electricity.model.response.BaseResponse;
import com.electricity.model.response.CommonResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Objects;

/**
 * 统一返回封装处理
 *
 * @author jiaowei
 * @since 2024/11/11 15:21
 */
@RestControllerAdvice
public class GlobalResponseAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return Objects.nonNull(returnType.getMethodAnnotation(ResponseResult.class));
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        // 如果controller method本身返回void则用BaseResponse封装
        if (returnType.getParameterType() == Void.class) {
            return BaseResponse.instance();
        }
        // 如果controller method本身返回数据，则用CommonResponse封装
        return CommonResponse.instance().setData(body);
    }
}
