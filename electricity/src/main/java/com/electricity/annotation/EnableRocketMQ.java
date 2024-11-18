package com.electricity.annotation;

import com.electricity.config.RocketMQConfig;
import com.electricity.handler.infrast.impl.DefaultMQHandler;
import com.electricity.listener.ConsumerListenerOrderly;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author jiaowei
 * @since 2024/11/11 13:57
 */
@Documented
@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({
        RocketMQConfig.class,
        ConsumerListenerOrderly.class,
        DefaultMQHandler.class
})
public @interface EnableRocketMQ {
}
