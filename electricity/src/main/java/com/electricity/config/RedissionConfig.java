package com.electricity.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jiaowei
 * @since 2024/10/1 20:25
 *
 * Redission配置
 */
@Configuration
@ConfigurationProperties(prefix = "redission")
public class RedissionConfig {

    private String address;

    private Integer database;

    private String password;

    @Bean
    public RedissonClient redissionClient() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress(address)
                .setDatabase(database)
                .setPassword(password);
        RedissonClient redissonClient = Redisson.create(config);
        return redissonClient;
    }
}
