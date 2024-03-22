package com.electricity;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

/**
 * @Title: RedisTest
 * @Author JiaoWei
 * @Package com.myblog
 * @Date 2024/3/16 23:24
 * @description: redis测试
 */
@SpringBootTest
public class RedisTest {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void test() {
        ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
        System.out.println(opsForValue.get("aaa"));
    }
}
