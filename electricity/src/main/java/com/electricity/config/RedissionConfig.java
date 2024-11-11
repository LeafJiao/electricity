package com.electricity.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jiaowei
 * @since 2024/10/1 20:25
 *
 * Redission配置
 */
@Configuration
public class RedissionConfig {

    @Bean
    public RedissonClient redissionClient() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://116.62.4.62:6379")
                .setDatabase(0)
                .setPassword("..jiao+-58961");

        RedissonClient redissonClient = Redisson.create(config);
        return redissonClient;
    }
}
