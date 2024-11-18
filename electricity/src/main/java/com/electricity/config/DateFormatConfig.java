package com.electricity.config;

import com.electricity.constant.BaseConstant;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.context.annotation.Bean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

@JsonComponent
public class DateFormatConfig {

        /**
         * date 类型全局时间格式化
         *
         * @return
         */
        @Bean
        public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilder() {
            return builder -> {
                TimeZone tz = TimeZone.getTimeZone("Asia/Shanghai");
                DateFormat df = new SimpleDateFormat(BaseConstant.TIME_FORMAT);
                df.setTimeZone(tz);
                builder.failOnEmptyBeans(false).failOnUnknownProperties(false)
                        .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS).dateFormat(df);
            };
        }

        /**
         * LocalDate 类型全局时间格式化
         *
         * @return
         */
        public LocalDateTimeSerializer localDateTimeDeserializer() {
            return new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(BaseConstant.TIME_FORMAT));
        }

        @Bean
        public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
            return builder -> builder.serializerByType(LocalDateTime.class, localDateTimeDeserializer());
        }

}