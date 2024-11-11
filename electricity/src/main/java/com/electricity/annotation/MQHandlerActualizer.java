package com.electricity.annotation;

import org.springframework.stereotype.Service;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 消息处理类标注（根据topic和tag区分不同消息处理类）
 *
 * @author jiaowei
 * @since 2024/11/11 14:05
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Service
public @interface MQHandlerActualizer {

    /**
     * 消息主题
     * @return
     */
    String topic() default "";

    /**
     * 消息标签
     * @return
     */
    String[] tags() default "*";

    /**
     * 备注
     * @return
     */
    String remark() default "";
}
