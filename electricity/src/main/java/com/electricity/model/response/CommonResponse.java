package com.electricity.model.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author jiaowei
 * @since 2024/11/11 15:59
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class CommonResponse<T> extends BaseResponse{

    /**
     * 响应数据
     */
    private T data;


    public static CommonResponse instance(){
        return new CommonResponse();
    }
}
